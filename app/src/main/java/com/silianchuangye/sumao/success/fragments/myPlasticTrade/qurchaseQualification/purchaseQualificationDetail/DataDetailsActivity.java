package com.silianchuangye.sumao.success.fragments.myPlasticTrade.qurchaseQualification.purchaseQualificationDetail;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ContentUris;
import android.content.Context;
import android.content.CursorLoader;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ActionBarOverlayLayout;
import android.util.Base64;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.messagePush.view.MActivity;
import com.silianchuangye.sumao.success.utils.LGImgCompressor;
import com.silianchuangye.sumao.success.utils.Loding;
import com.silianchuangye.sumao.success.utils.LogUtils;
import com.silianchuangye.sumao.success.utils.SuMaoConstant;
import com.zhy.m.permission.MPermissions;
import com.zhy.m.permission.PermissionDenied;
import com.zhy.m.permission.PermissionGrant;
import com.zhy.m.permission.ShowRequestPermissionRationale;

import org.xutils.common.Callback;
import org.xutils.common.util.DensityUtil;
import org.xutils.http.RequestParams;
import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DataDetailsActivity extends AppCompatActivity implements View.OnClickListener,LGImgCompressor.CompressListener {

    private TextView tv_child_title_bar_title;
    private TextView tv_child_title_bar_text;
    private String title;
    private EditText modify_information;
    private TextView prompt_information;
    private ImageView image;
    private Button upload_scan;
    private boolean needPicture;
    private View popupWindowView;
    private TextView takePhoto;
    private TextView chooseFromAlbum;
    private TextView textCancel;
    private RelativeLayout rootView;
    private File imageFile;
    private Uri imageUri;

    private int requestWidth;
    private PopupWindow popupWindow;
    private int requestHeight;

    private ImageOptions imageOptions;
    private String tupian="";

    public static final int TAKE_PHOTO = 1;
    public static final int OPEN_ALBUM = 2;
    public static final int REQUEST_CODE_TAKE_PHOTO = 1;
    public static final int REQUEST_CODE_OPEN_ALBUM = 2;
    private static final String PACKAGE_URL_SCHEME = "package:";
    static final String[] PERMISSIONS = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA};
    private boolean canTakePhoto = false;
    private String path="";
    private String url;
    private String fileCode;
    private String filepath = null;
    private String fileBianma = null;
    private static final int PHOTO_REQUEST_TAKEPHOTO = 1;// 拍照
    private static final int PHOTO_REQUEST_GALLERY = 2;// 从相册中选择
    private static final int PHOTO_REQUEST_CUT=3;
    private ImageView iv_child_title_bar_back;
    private RelativeLayout layout_title;
    private Bitmap bitmap;
    private String inputString="inputString";
    private String value;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_details);
        layout_title= (RelativeLayout) findViewById(R.id.title);
        iv_child_title_bar_back =(ImageView) findViewById(R.id.iv_child_title_bar_back);
        iv_child_title_bar_back.setOnClickListener(this);

        Intent intent = getIntent();
        title = intent.getStringExtra("title");
        Log.d("title的值",title);
        needPicture = intent.getBooleanExtra("picture", false);
        path = intent.getStringExtra("path");
        value=intent.getStringExtra("value");
        String resouce=intent.getStringExtra("resouce");
        Log.d("path的值", path);
        findView();
        tv_child_title_bar_title.setText(title);
        showUploadScan();
        if (resouce.equals("search")){
            modify_information.setVisibility(View.GONE);
            upload_scan.setVisibility(View.GONE);
        }
        modify_information.setText(value);
        if (title.equals("企业法人姓名")){
            modify_information.setKeyListener(null);
        }
        ViewTreeObserver observer = image.getViewTreeObserver();
        observer.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {

            @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onGlobalLayout() {
                image.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                requestHeight = image.getHeight();
                requestWidth = image.getWidth();
            }
        });


    }

    public void uploadPicture() {
        //tupian=getPhotoFileName();
        RequestParams rp = new RequestParams(SuMaoConstant.SUMAO_IP + "/rest/model/atg/commerce/order/myorder/OrderPaymentInfo/fileUpload");
        SharedPreferences sp = getSharedPreferences("sumao", Activity.MODE_PRIVATE);
        fileCode = sp.getString("filename", "");
        Log.d("編碼是否存在",fileCode);
        if (fileCode != null && fileCode != "") {
            Log.d("Xiangjide bianma ",fileCode);
            rp.addParameter("file", fileCode);
            SharedPreferences.Editor editor=sp.edit();
            editor.remove("filename");
            editor.commit();
        } else {
            Log.d("判断", "进来了");
            Log.d("相册的路径", filepath);
            Bitmap bmp= ((BitmapDrawable)image.getDrawable()).getBitmap();
            fileCode = BitmapToString(bmp);
            Log.d("图片编码", fileCode);

            rp.addParameter("file", fileCode);

        }

        Log.d("文件", fileCode + "");
        rp.addParameter("fileName",tupian );
        Log.d("名称", getPhotoFileName());
        Log.d("aaa", rp.getUri().toString());
        x.http().post(rp, new Callback.CommonCallback<String>() {
            @Override
            public void onFinished() {

            }

            @Override
            public void onSuccess(String result) {
                Log.d("图片上传的返回值", result);
                Loding.dis();
                if (result.contains("上传成功")){
                    if (!title.equals("企业法人姓名")) {
                        Log.d("图片的路径", "/mnt/docs/" + tupian);
                        Intent intent = new Intent();
                        intent.putExtra("returnInfo", modify_information.getText().toString());
                        intent.putExtra("path", "/mnt/docs/" + tupian);
                        setResult(RESULT_OK, intent);
                        finish();
                    }else{
                        Intent intent = new Intent();
                        intent.putExtra("returnInfo", modify_information.getText().toString());
                        intent.putExtra("path", "/mnt/docs/" + tupian);
                        setResult(RESULT_OK, intent);
                        finish();
                    }
                }


            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }
        });

    }

    //    显示上传文件按钮
    private void  showUploadScan() {
        if (needPicture) {
            upload_scan.setVisibility(View.VISIBLE);
           // Popupwindow();
        } else {
            upload_scan.setVisibility(View.GONE);
        }

    }



    //    设置背景
    private void backgroundAlpha(float backageAlpha) {
        WindowManager.LayoutParams layoutParams = getWindow().getAttributes();
        layoutParams.alpha = backageAlpha;
        getWindow().setAttributes(layoutParams);
    }


    private void findView() {
        rootView = ((RelativeLayout) findViewById(R.id.root_view));
//        标题
        tv_child_title_bar_title = ((TextView) findViewById(R.id.tv_child_title_bar_title));
        tv_child_title_bar_text = ((TextView) findViewById(R.id.tv_child_title_bar_text));
        tv_child_title_bar_text.setText("保存");
        tv_child_title_bar_text.setOnClickListener(this);
//        填写的信息
        modify_information = ((EditText) findViewById(R.id.modify_information));
        modify_information.clearFocus();
//        提示信息
        prompt_information = ((TextView) findViewById(R.id.prompt_information));
//        图片
        image = ((ImageView) findViewById(R.id.image));
//        上传扫描件
        upload_scan = ((Button) findViewById(R.id.upload_scan));
        upload_scan.setOnClickListener(this);
        if (!path.trim().equals("abc")) {
            Loding.show(this,"图片加载中...",false,null);
            Log.d("进来了","不是条件判断了嘛");
            url = SuMaoConstant.SUMAO_CONTRACT_IP + path;
            imageOptions = new ImageOptions
                    .Builder()
                    .setRadius(10)
                    .setSize(DensityUtil.dip2px(120), DensityUtil.dip2px(120))
                    .setRadius(DensityUtil.dip2px(5))
                    // 如果ImageView的大小不是定义为wrap_content, 不要crop.
                   // .setCrop(true) // 很多时候设置了合适的scaleType也不需要它.
                    // 加载中或错误图片的ScaleType
                    .setPlaceholderScaleType(ImageView.ScaleType.MATRIX)
                    .setImageScaleType(ImageView.ScaleType.FIT_CENTER)
//                    .setLoadingDrawableId(R.mipmap.ic_launcher)
//                    .setFailureDrawableId(R.mipmap.ic_launcher)
                    .build();
            Loding.dis();
            x.image().bind(image, url, imageOptions);

        }


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
//            case R.id.image:
//                Intent intent1=new Intent(DataDetailsActivity.this, MActivity.class);
//                if (tupian==""&&!path.equals("abc")) {
//                    intent1.putExtra("path", path);
//                }
////                }else{
////                    intent.putExtra("path","");
////                }
//                startActivity(intent1);
//                break;
            case R.id.tv_child_title_bar_text:
                //saveInformation();
                //
                if (modify_information.getText().toString().equals("")||modify_information.getText().toString()==""){
                    Toast.makeText(DataDetailsActivity.this, "请填写你要输入的内容!", Toast.LENGTH_SHORT).show();
                }else if (title.equals("企业法人姓名")){
                    if (tupian.equals("")){
                        //原来的图片
                        Intent intent = new Intent();
                        intent.putExtra("returnInfo", modify_information.getText().toString());
                        intent.putExtra("path", path);
                        setResult(RESULT_OK, intent);
                        finish();
                    }else{

                    }
                } else{
                  //  Toast.makeText(DataDetailsActivity.this, "输入的内容不是空的!", Toast.LENGTH_SHORT).show();
                    if (title.equals("开户银行许可证")||title.equals("姓名")){
                        Log.d("title的值",title);
                        if (tupian.equals("")){
                            if (path.equals("abc")) {
                                Log.d("有图片", "图片没有上传");
                                Toast.makeText(DataDetailsActivity.this, "请上传扫描件!", Toast.LENGTH_SHORT).show();
                            }else{
                                Intent intent = new Intent();
                                intent.putExtra("returnInfo", modify_information.getText().toString());
                                 intent.putExtra("path",path);
                                setResult(RESULT_OK, intent);
                                finish();
                            }
                        }else{
                            Log.d("有图片","图片上传了");
                            Loding.show(this, "图片上传中...", false, null);
                            uploadPicture();
                        }
                    }else{
                        Log.d("不需要上传图片","不需要上传图片");
                        Intent intent = new Intent();
                        intent.putExtra("returnInfo", modify_information.getText().toString());
                        // intent.putExtra("path","/mnt/"+getPhotoFileName());
                        setResult(RESULT_OK, intent);
                        finish();
                    }
                }


                break;
            case R.id.upload_scan:
                //showCartFloatView();
                backgroundAlpha(0.5f);
                //showPopupWindow();
                Popupwindow();
                break;
            case R.id.iv_child_title_bar_back:
                if (title.equals("企业法人姓名")||title.equals("姓名")||title.equals("开户银行许可证")){
                    if (tupian==""){
                        if (!path.equals("abc")){
                            Intent intent = new Intent();
                            intent.putExtra("returnInfo", modify_information.getText().toString());
                            intent.putExtra("path",path);
                            setResult(RESULT_OK, intent);
                            finish();
                        }else{
                            finish();
                        }
                    }else{
                        finish();
                    }
                }
                finish();
                break;
            default:
                break;
        }
    }

//    private void chooseFromAlbum() {
//        if (!MPermissions.shouldShowRequestPermissionRationale(this, Manifest.permission.WRITE_EXTERNAL_STORAGE, REQUEST_CODE_OPEN_ALBUM)) {
//            MPermissions.requestPermissions(this, REQUEST_CODE_OPEN_ALBUM, Manifest.permission.WRITE_EXTERNAL_STORAGE);
//        }
//
//
//    }




//
//    private void creteImageUri() {
//        if (Build.VERSION.SDK_INT > 24) {
//            imageUri = FileProvider.getUriForFile(DataDetailsActivity.this, "image.provider", imageFile);
//        } else {
//            imageUri = Uri.fromFile(imageFile);
//        }
//    }
//


    //    保存信息
//    private void saveInformation() {
//        if (needPicture) {
//            if (checkPicture()) {
//                if (checkInformation()) {
//                    uploadPicture();
//                } else {
//                    prompt_information.setText("请填写" + title);
//                    prompt_information.setVisibility(View.VISIBLE);
//                }
//            }
//        } else {
//            if (checkInformation()) {
//                returnInfomation();
//            } else {
//                prompt_information.setText("请填写" + title);
//                prompt_information.setVisibility(View.VISIBLE);
//            }
//        }
//
//    }

    //  把信息传递到上一个界面
    private void returnInfomation() {

    }

//    private void uploadPicture() {
////        图片上传
//        boolean pictureUpload = false;
//
//        pictureUpload = true;
//        if (pictureUpload) {
//            returnInfomation();
//        }
//    }

//    private Boolean checkPicture() {
//        boolean ownPicture = false;
//        ownPicture = true;
//        return ownPicture;
//    }

//    private Boolean checkInformation() {
//        boolean ownInformation = false;
//        if (!modify_information.getText().toString().equals("") && modify_information.getText().toString() != null)
//            ownInformation = true;
//        return ownInformation;
//    }
public static File saveFile(Bitmap bm,String path, String fileName) throws IOException {
    File dirFile = new File(path);
    if(!dirFile.exists()){
        dirFile.mkdir();
    }
    File myCaptureFile = new File(path , fileName);
    BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(myCaptureFile));
    bm.compress(Bitmap.CompressFormat.JPEG, 80, bos);
    bos.flush();
    bos.close();
    return myCaptureFile;
}

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case PHOTO_REQUEST_TAKEPHOTO://当选择拍照时调用

                if (resultCode == RESULT_CANCELED) {
                    Toast.makeText(DataDetailsActivity.this, "取消了拍照", Toast.LENGTH_LONG).show();
                    return;
                }
                tupian=getPhotoFileName();
                Bitmap photo = data.getParcelableExtra("data");
                image.setImageBitmap(photo);
                fileBianma = BitmapToString(photo);
                SharedPreferences sp=getSharedPreferences("sumao", Activity.MODE_PRIVATE);
                SharedPreferences.Editor editor=sp.edit();
                editor.putString("filename",fileBianma);
                editor.commit();
                File path= null;
                try {
                    path = saveFile(photo, Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)+"/",tupian);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Log.d("path的值",path+"");
                LGImgCompressor.getInstance(this).withListener(this).
                        starCompress(path+"", 1000, 1000, 2048);

                break;
            case PHOTO_REQUEST_GALLERY://当选择从本地获取图片时
//做非空判断，当我们觉得不满意想重新剪裁的时候便不会报异常，下同
                //  if (data != null)
                //Uri uri = data.getData();
                // startPhotoZoom(data.getData(), 150);
//                tv_Name.setVisibility(View.VISIBLE);
//                tv_Name.setText(getPhotoFileName());

                Uri uri = data.getData();
                try {
                    //get the image path
                    String[] projection = {MediaStore.Images.Media.DATA};
                    Log.d("projection的长度", "" + projection.length);
                    CursorLoader cursorLoader = new CursorLoader(this, uri, projection, null, null, null);
                    Cursor cursor = cursorLoader.loadInBackground();
                    int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                    cursor.moveToFirst();
                    tupian=getPhotoFileName();
                    filepath = cursor.getString(column_index);
                    Log.d("realpath", "" + filepath);

                    LGImgCompressor.getInstance(this).withListener(this).
                            starCompress(filepath, 1000, 1000, 100);
                    bitmap= BitmapFactory.decodeFile(filepath);
                    image.setImageBitmap(bitmap);
                } catch (Exception ex) {
                    Log.e("aaaa", "failed." + ex.getMessage());
                }
                break;

            case PHOTO_REQUEST_CUT://返回的结果
                if (data != null)
                    //  setPicToView(data);
                    break;
        }

    }



//    private void handleImageBeforeKitKat(Uri imageUri) {
//        String imagePath = getImagePath(imageUri, null);
//        Bitmap bitmap = decodeSampleBitmap(imagePath);
//        image.setImageBitmap(bitmap);
//    }

//    @TargetApi(Build.VERSION_CODES.KITKAT)
//    private void handleImageOnKitKat(Uri imageUri) {
//        String imagePath = null;
//        if (DocumentsContract.isDocumentUri(this, imageUri)) {
//            String docId = DocumentsContract.getDocumentId(imageUri);
//            if ("com.android.providers.medias.documents".equals(imageUri.getAuthority())) {
//                String id = docId.split(":")[1];
//                String selection = MediaStore.Images.Media._ID + "=" + id;
//                imagePath = getImagePath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, selection);
//            } else if ("com.android.providers.downloads.documents".equals(imageUri.getAuthority())) {
//                Uri docUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(docId));
//                imagePath = getImagePath(docUri, null);
//            }
//        } else if ("content".equalsIgnoreCase(imageUri.getScheme())) {
//            imagePath = getImagePath(imageUri, null);
//        } else if ("file".equalsIgnoreCase(imageUri.getScheme())) {
//            imagePath = imageUri.getPath();
//        }
//        Bitmap bitmap = decodeSampleBitmap(imagePath);
//        image.setImageBitmap(bitmap);
//    }
//
//    private Bitmap decodeSampleBitmap(String imagePath) {
//
//        LogUtils.log("文件原图大小:" + BitmapFactory.decodeFile(imagePath).getByteCount());
//        BitmapFactory.Options options = new BitmapFactory.Options();
//        options.inJustDecodeBounds = true;
//        BitmapFactory.decodeFile(imagePath, options);
//        options.inSampleSize = calculateSimpleSize(options, requestHeight, requestWidth);
//        options.inJustDecodeBounds = false;
//        LogUtils.log("文件压缩大小:" + BitmapFactory.decodeFile(imagePath, options).getByteCount());
//        return BitmapFactory.decodeFile(imagePath, options);
//    }
//
//    private String getImagePath(Uri uri, String selection) {
//        String imagePath = null;
//        Cursor cursor = getContentResolver().query(uri, null, selection, null, null);
//        if (cursor != null) {
//            if (cursor.moveToFirst()) {
//                imagePath = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
//            }
//            cursor.close();
//        }
//        return imagePath;
//    }
//
//    private Bitmap decodeSampleBitmap(Uri imageUri) throws FileNotFoundException {
//        LogUtils.log("原图大小:" + BitmapFactory.decodeStream(getContentResolver().openInputStream(imageUri)).getByteCount());
//        BitmapFactory.Options options = new BitmapFactory.Options();
//        options.inJustDecodeBounds = true;
//        BitmapFactory.decodeStream(getContentResolver().openInputStream(imageUri), null, options);
//        options.inSampleSize = calculateSimpleSize(options, requestHeight, requestWidth);
//        options.inJustDecodeBounds = false;
//        LogUtils.log("压缩大小:" + BitmapFactory.decodeStream(getContentResolver().openInputStream(imageUri), null, options).getByteCount());
//        return BitmapFactory.decodeStream(getContentResolver().openInputStream(imageUri), null, options);
//    }
//
//    private int calculateSimpleSize(BitmapFactory.Options options, int requestHeight, int requestWidth) {
//        int srcWidth = options.outWidth;
//        int srcHeight = options.outHeight;
//        int sampleSize = 1;
//        if (srcHeight > requestHeight || srcWidth > requestWidth) {
//            int widthRatio = Math.round(srcWidth / requestWidth);
//            int heightRatio = Math.round(srcHeight / requestHeight);
//            sampleSize = widthRatio < heightRatio ? widthRatio : heightRatio;
//        }
//        return sampleSize;
//    }

    // 显示缺失权限提示
    @ShowRequestPermissionRationale(REQUEST_CODE_TAKE_PHOTO)
    public void showMissingPermissionDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(DataDetailsActivity.this);
        builder.setTitle(R.string.help);
        builder.setMessage(R.string.string_help_text);

        // 拒绝, 退出应用
        builder.setNegativeButton(R.string.quit, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
//                setResult(PERMISSIONS_DENIED);

            }
        });

        builder.setPositiveButton(R.string.settings, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startAppSettings();
            }
        });

        builder.setCancelable(false);

        builder.show();
    }

    // 启动应用的设置
    private void startAppSettings() {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.setData(Uri.parse(PACKAGE_URL_SCHEME + getPackageName()));
        startActivity(intent);
    }

        @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        MPermissions.onRequestPermissionsResult(this, requestCode, permissions, grantResults);
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

//    @PermissionGrant(REQUEST_CODE_TAKE_PHOTO)
//    public void requestCameraSuccess() {
//        canTakePhoto = true;
//
//    }
//
//    @PermissionDenied(REQUEST_CODE_TAKE_PHOTO)
//    public void requestCameraFailed() {
//        canTakePhoto = false;
//        showMissingPermissionDialog();
//    }
//
//    @PermissionGrant(REQUEST_CODE_OPEN_ALBUM)
//    public void requestAlbumSuccess() {
//        openAlbum();
//    }
//
//    @PermissionDenied(REQUEST_CODE_OPEN_ALBUM)
//    public void requestAlbumFailed() {
//        showMissingPermissionDialog();
//    }
// 使用系统当前日期加以调整作为照片的名称
    public void Popupwindow() {
        View view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.item_firm_info_popupwindow, null);
        popupWindow = new PopupWindow(findViewById(R.id.layout_b), ActionBarOverlayLayout.LayoutParams.MATCH_PARENT, ActionBarOverlayLayout.LayoutParams.WRAP_CONTENT);
        popupWindow.setContentView(view);
        TextView texthotograph = (TextView) view.findViewById(R.id.tvphotograph);
        TextView textphoto = (TextView) view.findViewById(R.id.tvPhoto);
        TextView textCancel = (TextView) view.findViewById(R.id.tvCancel);
        texthotograph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 1);
                popupWindow.dismiss();
               // uploadPicture();
            }
        });
        textphoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // getImageFromAlbum();
                fileBianma = null;
                Intent intent = new Intent(Intent.ACTION_PICK, null);
                intent.setDataAndType(
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                        "image/*");
                startActivityForResult(intent, PHOTO_REQUEST_GALLERY);

                popupWindow.dismiss();
               // uploadPicture();
            }
        });
        textCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
        popupWindow.setTouchable(true);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.setOutsideTouchable(true);
        popupWindow.setFocusable(true);
        //popupWindow.showAtLocation(tv_child_title_bar_text,Gravity.BOTTOM,0,0);
        popupWindow.showAtLocation(tv_child_title_bar_text,Gravity.BOTTOM,0,0);
       // popupWindow.
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                //popupWindow.dismiss();
                backgroundAlpha(1f);
            }
        });


    }

    private String getPhotoFileName() {
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat dateFormat = new SimpleDateFormat("'IMG'_yyyyMMdd_HHmmss");
        return dateFormat.format(date) + ".jpg";
    }

    public static String BitmapToString(Bitmap bitmap) {
        String des = null;
        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
            out.flush();
            out.close();
            byte[] buffer = out.toByteArray();
            byte[] encode = Base64.encode(buffer, Base64.DEFAULT);
            des = new String(encode);
        } catch (Exception e) {
            e.printStackTrace();
        }


        return des;
    }


    @Override
    public void onCompressStart() {

    }

    @Override
    public void onCompressEnd(LGImgCompressor.CompressResult imageOutPath) {
        Log.d("info", "onCompressEnd outPath:" + imageOutPath.getOutPath());
        if (imageOutPath.getStatus() == LGImgCompressor.CompressResult.RESULT_ERROR)//压缩失败
            return;

        File file = new File(imageOutPath.getOutPath());
        Bitmap bitmap = null;
        try {
            bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), Uri.fromFile(file));
            //ivDemo.setImageBitmap(bitmap);
            float imageFileSize = file.length() / 1024f;
            Log.d("图片的信息","image info width:" + bitmap.getWidth() + " \nheight:" + bitmap.getHeight() +
                    " \nsize:" + imageFileSize + "kb" + "\nimagePath:" + file.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public  boolean isShouldHideInput(View v, MotionEvent event) {
        if (v != null && (v instanceof EditText)) {
            int[] leftTop = { 0, 0 };
            //获取输入框当前的location位置
            v.getLocationInWindow(leftTop);
            int left = leftTop[0];
            int top = leftTop[1];
            int bottom = top + v.getHeight();
            int right = left + v.getWidth();
            return !(event.getX() > left && event.getX() < right
                    && event.getY() > top && event.getY() < bottom);
        }
        return false;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (isShouldHideInput(v, ev)) {

                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                if (imm != null) {
                    // Search.clearFocus();
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }
            return super.dispatchTouchEvent(ev);
        }
        // 必不可少，否则所有的组件都不会有TouchEvent了
        if (getWindow().superDispatchTouchEvent(ev)) {
            return true;
        }
        return onTouchEvent(ev);
    }
}

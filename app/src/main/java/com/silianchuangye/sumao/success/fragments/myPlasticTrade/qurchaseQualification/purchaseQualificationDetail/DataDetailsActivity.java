package com.silianchuangye.sumao.success.fragments.myPlasticTrade.qurchaseQualification.purchaseQualificationDetail;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.ContentUris;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.utils.LogUtils;
import com.zhy.m.permission.MPermissions;
import com.zhy.m.permission.PermissionDenied;
import com.zhy.m.permission.PermissionGrant;
import com.zhy.m.permission.ShowRequestPermissionRationale;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class DataDetailsActivity extends AppCompatActivity implements View.OnClickListener {

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

    public static final int TAKE_PHOTO = 1;
    public static final int OPEN_ALBUM = 2;
    public static final int REQUEST_CODE_TAKE_PHOTO = 1;
    public static final int REQUEST_CODE_OPEN_ALBUM = 2;
    private static final String PACKAGE_URL_SCHEME = "package:";
    static final String[] PERMISSIONS = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA};
    private boolean canTakePhoto = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_details);
        findView();
        Intent intent = getIntent();
        title = intent.getStringExtra("title");
        needPicture = intent.getBooleanExtra("picture", false);
        tv_child_title_bar_title.setText(title);
        showUploadScan();
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

    //    显示上传文件按钮
    private void showUploadScan() {
        if (needPicture) {
            upload_scan.setVisibility(View.VISIBLE);
            initPopupwindow();
        }else {
            upload_scan.setVisibility(View.GONE);
        }

    }

    //    初始化PopupWindow
    private void initPopupwindow() {
        popupWindowView = View.inflate(getApplicationContext(), R.layout.item_firm_info_popupwindow, null);
//        拍照
        takePhoto = ((TextView) popupWindowView.findViewById(R.id.tvphotograph));
        takePhoto.setOnClickListener(this);
//        从相册获取
        chooseFromAlbum = ((TextView) popupWindowView.findViewById(R.id.tvPhoto));
        chooseFromAlbum.setOnClickListener(this);
//        取消
        textCancel = (TextView) popupWindowView.findViewById(R.id.tvCancel);
        textCancel.setOnClickListener(this);
//
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
//        提示信息
        prompt_information = ((TextView) findViewById(R.id.prompt_information));
//        图片
        image = ((ImageView) findViewById(R.id.image));
//        上传扫描件
        upload_scan = ((Button) findViewById(R.id.upload_scan));
        upload_scan.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_child_title_bar_text:
                saveInformation();
                break;
            case R.id.upload_scan:
                backgroundAlpha(0.5f);
                showPopupWindow();
                break;
//            拍照
            case R.id.tvphotograph:
                popupWindow.dismiss();
                if (canTakePhoto) {
                    takePhoto();
                } else {
                    if (!MPermissions.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA, REQUEST_CODE_TAKE_PHOTO)) {
                        MPermissions.requestPermissions(this, REQUEST_CODE_TAKE_PHOTO, PERMISSIONS);
                    }
                }

                break;
//            从相册获取
            case R.id.tvPhoto:
                popupWindow.dismiss();
                chooseFromAlbum();
                break;
            case R.id.tvCancel:
                popupWindow.dismiss();
                break;
            default:
                break;
        }
    }

    private void chooseFromAlbum() {
        if (!MPermissions.shouldShowRequestPermissionRationale(this, Manifest.permission.WRITE_EXTERNAL_STORAGE, REQUEST_CODE_OPEN_ALBUM)) {
            MPermissions.requestPermissions(this, REQUEST_CODE_OPEN_ALBUM, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }


    }

    private void openAlbum() {
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.setType("image/*");
        startActivityForResult(intent, OPEN_ALBUM);
    }

    private void takePhoto() {
        imageFile = new File(getExternalCacheDir(), "SuMao.jpg");
        if (imageFile.exists()) {
            imageFile.delete();
        }
        try {
            imageFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        creteImageUri();
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        startActivityForResult(intent, TAKE_PHOTO);

    }

    private void creteImageUri() {
        if (Build.VERSION.SDK_INT > 24) {
            imageUri = FileProvider.getUriForFile(DataDetailsActivity.this, "image.provider", imageFile);
        } else {
            imageUri = Uri.fromFile(imageFile);
        }
    }

    private void showPopupWindow() {
        popupWindow = new PopupWindow(popupWindowView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setTouchable(true);
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.showAtLocation(tv_child_title_bar_title, Gravity.BOTTOM, 0, 0);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                backgroundAlpha(1.0f);
            }
        });
    }

    //    保存信息
    private void saveInformation() {
        if (needPicture) {
            if (checkPicture()) {
                if (checkInformation()) {
                    uploadPicture();
                } else {
                    prompt_information.setText("请填写" + title);
                    prompt_information.setVisibility(View.VISIBLE);
                }
            }
        } else {
            if (checkInformation()) {
                returnInfomation();
            } else {
                prompt_information.setText("请填写" + title);
                prompt_information.setVisibility(View.VISIBLE);
            }
        }

    }

    //  把信息传递到上一个界面
    private void returnInfomation() {
        Intent intent = new Intent();
        intent.putExtra("returnInfo", modify_information.getText().toString());
        setResult(RESULT_OK, intent);
        finish();
    }

    private void uploadPicture() {
//        图片上传
        boolean pictureUpload = false;

        pictureUpload = true;
        if (pictureUpload) {
            returnInfomation();
        }
    }

    private Boolean checkPicture() {
        boolean ownPicture = false;
        ownPicture = true;
        return ownPicture;
    }

    private Boolean checkInformation() {
        boolean ownInformation = false;
        if (!modify_information.getText().toString().equals("") && modify_information.getText().toString() != null)
            ownInformation = true;
        return ownInformation;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case TAKE_PHOTO:
                if (resultCode == RESULT_OK) {
                    if (imageUri == null) {
                        creteImageUri();
                    }
                    try {
                        Bitmap bitmap = decodeSampleBitmap(imageUri);
                        image.setImageBitmap(bitmap);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
                break;
            case OPEN_ALBUM:
                if (resultCode == RESULT_OK) {
                    Uri imageUri = data.getData();
                    if (Build.VERSION.SDK_INT > 19) {
                        handleImageOnKitKat(imageUri);
                    } else {
                        handleImageBeforeKitKat(imageUri);
                    }
                }
                break;
            default:
                break;
        }
    }

    private void handleImageBeforeKitKat(Uri imageUri) {
        String imagePath = getImagePath(imageUri, null);
        Bitmap bitmap = decodeSampleBitmap(imagePath);
        image.setImageBitmap(bitmap);
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    private void handleImageOnKitKat(Uri imageUri) {
        String imagePath = null;
        if (DocumentsContract.isDocumentUri(this, imageUri)) {
            String docId = DocumentsContract.getDocumentId(imageUri);
            if ("com.android.providers.medias.documents".equals(imageUri.getAuthority())) {
                String id = docId.split(":")[1];
                String selection = MediaStore.Images.Media._ID + "=" + id;
                imagePath = getImagePath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, selection);
            } else if ("com.android.providers.downloads.documents".equals(imageUri.getAuthority())) {
                Uri docUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(docId));
                imagePath = getImagePath(docUri, null);
            }
        } else if ("content".equalsIgnoreCase(imageUri.getScheme())) {
            imagePath = getImagePath(imageUri, null);
        } else if ("file".equalsIgnoreCase(imageUri.getScheme())) {
            imagePath = imageUri.getPath();
        }
        Bitmap bitmap = decodeSampleBitmap(imagePath);
        image.setImageBitmap(bitmap);
    }

    private Bitmap decodeSampleBitmap(String imagePath) {

        LogUtils.log("文件原图大小:" + BitmapFactory.decodeFile(imagePath).getByteCount());
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(imagePath, options);
        options.inSampleSize = calculateSimpleSize(options, requestHeight, requestWidth);
        options.inJustDecodeBounds = false;
        LogUtils.log("文件压缩大小:" + BitmapFactory.decodeFile(imagePath, options).getByteCount());
        return BitmapFactory.decodeFile(imagePath, options);
    }

    private String getImagePath(Uri uri, String selection) {
        String imagePath = null;
        Cursor cursor = getContentResolver().query(uri, null, selection, null, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                imagePath = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            }
            cursor.close();
        }
        return imagePath;
    }

    private Bitmap decodeSampleBitmap(Uri imageUri) throws FileNotFoundException {
        LogUtils.log("原图大小:" + BitmapFactory.decodeStream(getContentResolver().openInputStream(imageUri)).getByteCount());
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(getContentResolver().openInputStream(imageUri), null, options);
        options.inSampleSize = calculateSimpleSize(options, requestHeight, requestWidth);
        options.inJustDecodeBounds = false;
        LogUtils.log("压缩大小:" + BitmapFactory.decodeStream(getContentResolver().openInputStream(imageUri), null, options).getByteCount());
        return BitmapFactory.decodeStream(getContentResolver().openInputStream(imageUri), null, options);
    }

    private int calculateSimpleSize(BitmapFactory.Options options, int requestHeight, int requestWidth) {
        int srcWidth = options.outWidth;
        int srcHeight = options.outHeight;
        int sampleSize = 1;
        if (srcHeight > requestHeight || srcWidth > requestWidth) {
            int widthRatio = Math.round(srcWidth / requestWidth);
            int heightRatio = Math.round(srcHeight / requestHeight);
            sampleSize = widthRatio < heightRatio ? widthRatio : heightRatio;
        }
        return sampleSize;
    }

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

    @PermissionGrant(REQUEST_CODE_TAKE_PHOTO)
    public void requestCameraSuccess() {
        canTakePhoto = true;

    }

    @PermissionDenied(REQUEST_CODE_TAKE_PHOTO)
    public void requestCameraFailed() {
        canTakePhoto = false;
        showMissingPermissionDialog();
    }

    @PermissionGrant(REQUEST_CODE_OPEN_ALBUM)
    public void requestAlbumSuccess() {
        openAlbum();
    }

    @PermissionDenied(REQUEST_CODE_OPEN_ALBUM)
    public void requestAlbumFailed() {
        showMissingPermissionDialog();
    }

}

package com.silianchuangye.sumao.success.fragments.myPlasticTrade.register;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.CursorLoader;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import android.support.v7.widget.ActionBarOverlayLayout;
import android.util.Base64;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.cloopen.rest.sdk.utils.encoder.BASE64Encoder;
import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.firmInfomation.decoding.Intents;
import com.silianchuangye.sumao.success.utils.FavFTPUtil;
import com.silianchuangye.sumao.success.utils.LGImgCompressor;
import com.silianchuangye.sumao.success.utils.Loding;
import com.silianchuangye.sumao.success.utils.LogUtils;
import com.silianchuangye.sumao.success.utils.SuMaoConstant;
//import com.zhy.m.permission.MPermissions;
//import com.zhy.m.permission.PermissionDenied;
//import com.zhy.m.permission.PermissionGrant;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RegisterPicture extends AppCompatActivity implements LGImgCompressor.CompressListener {
    private String filepath=null;
    private String fileBianma=null;
    private ImageView ivBack;
    private Button bt_upload;
    private Button bt_save;
    private TextView tv_Name;
    private RelativeLayout layout_title;
    private ImageView ivDemo;
    private static final int PHOTO_REQUEST_TAKEPHOTO = 1;// 拍照
    private static final int PHOTO_REQUEST_GALLERY = 2;// 从相册中选择
    private static final int PHOTO_REQUEST_CUT = 3;// 结果
    int number1;
    String title=null;
    EditText ed_number;
    String fileCode=null;
    private String tupian;
    private String path;
    private Uri mOutPutFileUri;
    private String trime="";
    private File file;
    private TextView tv_Title;
    private String value;
    private String tupianpath="";
    // 创建一个以当前时间为名称的文件
    File tempFile = new File(Environment.getExternalStorageDirectory(),getPhotoFileName());
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register__picture);
        Bundle bundle=getIntent().getExtras();
        title=bundle.getString("name");
        Log.d("title的值",title);
        number1=bundle.getInt("number");
        value=bundle.getString("value");
        tupianpath=bundle.getString("path");

        initData();
        if (value!=null&&!value.equals("")&&!value.equals("")){
            ed_number.setText(value);
        }
        if (tupianpath!=null&&!tupianpath.equals("")&&!tupianpath.equals("")){
            tv_Name.setVisibility(View.VISIBLE);
            tv_Name.setText(tupianpath.substring(10,tupianpath.length()));
        }

        event();
    }
    public void initData(){
        tv_Title= (TextView) findViewById(R.id.tv_Title);
        ed_number= (EditText) findViewById(R.id.ed_number);
        ivBack= (ImageView) findViewById(R.id.ivback);
        bt_upload= (Button) findViewById(R.id.bt_upload);
        bt_save= (Button) findViewById(R.id.bt_save);
        tv_Name= (TextView) findViewById(R.id.tv_Name);
        layout_title= (RelativeLayout) findViewById(R.id.layout_title);
        tv_Title.setText(title);
      //  ed_number.setText(value);
    }
    public void event(){
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tupianpath.equals("")) {
                    Intent intent = new Intent();
                    //Intent intent = new Intent();
                    intent.putExtra("value", ed_number.getText().toString());
                    intent.putExtra("picturePath", "abc");
                    Log.d("chuangu", "");
                    setResult(number1, intent);
                    RegisterPicture.this.finish();
                }else{
                    Intent intent = new Intent();
                    //Intent intent = new Intent();
                    intent.putExtra("value", ed_number.getText().toString());
                    intent.putExtra("picturePath",tupianpath);
                    Log.d("chuangu", "");
                    setResult(number1, intent);
                    RegisterPicture.this.finish();
                }

            }
        });
        bt_upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backgroundAlpha(0.5f);
                //弹出popupwindow
                Popupwindow();
            }
        });
        bt_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Log.d("点击事件","进来了");
                //tupian=getPhotoFileName();
                if (ed_number.getText().toString().equals("")){
                    Toast.makeText(RegisterPicture.this, "请输入证件号", Toast.LENGTH_SHORT).show();
                }else if(tv_Name.getVisibility()==View.VISIBLE &&!tv_Name.getText().equals("")) {

                    //上传图片
                    if (!trime.equals("")) {
                        Loding.show(RegisterPicture.this, "图片上传中...", false, null);
                        new Thread() {
                            @Override
                            public void run() {
                                super.run();
                                RequestParams rp = new RequestParams(SuMaoConstant.SUMAO_IP + "/rest/model/atg/commerce/order/myorder/OrderPaymentInfo/fileUpload");
                                SharedPreferences sp = getSharedPreferences("sumao", Activity.MODE_PRIVATE);
                                fileCode = sp.getString("filename", "");
                                Log.d("fileCode", fileCode);
                                if (fileCode != null && fileCode != "") {
                                    rp.addParameter("file", fileCode);
                                    SharedPreferences.Editor editor = sp.edit();
                                    editor.remove("filename");
                                    editor.commit();
                                } else {
                                    Log.d("判断", "进来了");
                                    Log.d("相册的路径", filepath);
                                    Bitmap bitmap = BitmapFactory.decodeFile(filepath);
                                    fileCode = BitmapToString(bitmap);
//                                Log.d("图片编码", fileCode);
//                               Toast.makeText(RegisterPicture.this, ""+fileCode, Toast.LENGTH_SHORT).show();
                                    rp.addParameter("file", fileCode);
                                }

                                Log.d("文件", fileCode + "");
                                rp.addParameter("fileName", trime);
                                Log.d("名称", trime);
                                Log.d("aaa", rp.getUri().toString());
                                x.http().post(rp, new Callback.CommonCallback<String>() {
                                    @Override
                                    public void onFinished() {

                                    }

                                    @Override
                                    public void onSuccess(String result) {
                                        Log.d("上穿陈功的返回值", result);
                                        Loding.dis();
                                        //  Intent intent=new Intent();
                                        Intent intent = new Intent();
                                        //Intent intent = new Intent();
                                        intent.putExtra("value", ed_number.getText().toString());
                                        intent.putExtra("picturePath", "/mnt/docs/" + trime);
                                        Log.d("chuangu", "");
                                        setResult(number1, intent);
                                        RegisterPicture.this.finish();


                                    }

                                    @Override
                                    public void onError(Throwable ex, boolean isOnCallback) {

                                    }

                                    @Override
                                    public void onCancelled(CancelledException cex) {

                                    }
                                });

                                // printStackTrace
                            }
                        }.start();
                    } else {
                        if (tv_Name.getVisibility() == View.VISIBLE) {
                            //有值
                            Intent intent = new Intent();
                            //Intent intent = new Intent();
                            intent.putExtra("value", ed_number.getText().toString());
                            intent.putExtra("picturePath", tupianpath);
                            Log.d("chuangu", "");
                            setResult(number1, intent);
                            RegisterPicture.this.finish();
                        } else {

                            Intent intent = new Intent();
                            //Intent intent = new Intent();
                            intent.putExtra("value", ed_number.getText().toString());
                            intent.putExtra("picturePath", "abc");
                            Log.d("chuangu", "");
                            setResult(number1, intent);
                            RegisterPicture.this.finish();
                        }
                    }
                }else{
                    Toast.makeText(RegisterPicture.this, "请确定你要上传的图片!", Toast.LENGTH_SHORT).show();
                }


            }
        });


    }

    //设置背景透明
    public void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        getWindow().setAttributes(lp);
    }

    public void Popupwindow() {
        View view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.item_firm_info_popupwindow, null);
        final PopupWindow popupWindow = new PopupWindow(findViewById(R.id.layout_title), ActionBarOverlayLayout.LayoutParams.MATCH_PARENT, ActionBarOverlayLayout.LayoutParams.WRAP_CONTENT);
        popupWindow.setContentView(view);
        TextView texthotograph = (TextView) view.findViewById(R.id.tvphotograph);
        TextView textphoto = (TextView) view.findViewById(R.id.tvPhoto);
        TextView textCancel = (TextView) view.findViewById(R.id.tvCancel);
        texthotograph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 调用系统的拍照功能
//                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
// //指定调用相机拍照后照片的储存路径
//                intent.putExtra(MediaStore.EXTRA_OUTPUT,
//                        Uri.fromFile(tempFile));
//              //  filepath=Uri.fromFile(tempFile).toString();
//               // Log.d("相机路径",filepath);
//                startActivityForResult(intent, PHOTO_REQUEST_TAKEPHOTO);
//                //getImageFromCamera();

//                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//                startActivityForResult(intent, 1);
//                popupWindow.dismiss();
//                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//
//
//                File fileDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
//                File imageFile = null;
//                try {
//                    imageFile = File.createTempFile(getPhotoFileName(), ".jpg", fileDir);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//                Log.d("imagepath_forpaizhai",""+Uri.fromFile(imageFile)+"");
//                SharedPreferences sp=getSharedPreferences("sumao",Activity.MODE_PRIVATE);
//                SharedPreferences.Editor editor=sp.edit();
//                editor.putString("imagepath_forpaizhai", Uri.fromFile(imageFile)+"");
//                editor.commit();
//                Log.d("imagepath_forpaizhai",""+Uri.fromFile(imageFile)+"");
//                intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(imageFile));
//                startActivityForResult(intent, 1);
//                popupWindow.dismiss();
                trime=getPhotoFileName();
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);


                //intent.putExtra(MediaStore.EXTRA_OUTPUT, mOutPutFileUri);
                startActivityForResult(intent, 1);
                popupWindow.dismiss();

            }
        });
        textphoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // getImageFromAlbum();
                fileBianma=null;
                Intent intent = new Intent(Intent.ACTION_PICK, null);
                intent.setDataAndType(
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                        "image/*");
                startActivityForResult(intent, PHOTO_REQUEST_GALLERY);
                popupWindow.dismiss();
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

        popupWindow.showAtLocation(ivBack, Gravity.BOTTOM, 0, 0);

        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                //popupWindow.dismiss();
                backgroundAlpha(1f);
            }
        });


    }
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
// TODO Auto-generated method stub

        switch (requestCode) {
            case PHOTO_REQUEST_TAKEPHOTO://当选择拍照时调用

                if (resultCode == RESULT_CANCELED) {
                    Toast.makeText(RegisterPicture.this, "取消了拍照", Toast.LENGTH_LONG).show();
                    return;
                }
                tv_Name.setVisibility(View.VISIBLE);
                tv_Name.setText(trime);
                Bitmap photo = data.getParcelableExtra("data");
                //image.setImageBitmap(photo);
                fileBianma = BitmapToString(photo);
               // if (fileBianma.)
                Log.d("fileBiama的值",fileBianma);
                SharedPreferences sharedPreferences=getSharedPreferences("sumao",Activity.MODE_PRIVATE);
                SharedPreferences.Editor editor=sharedPreferences.edit();
                editor.putString("filename",fileBianma);
                editor.commit();
                File path= null;
                try {
                    path = saveFile(photo,Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)+"/",trime);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Log.d("path的值",path+"");
                LGImgCompressor.getInstance(this).withListener(this).
                        starCompress(path+"", 1000, 1000, 2048);
                //Log.d("path的值",path+"");

                break;
            case PHOTO_REQUEST_GALLERY://当选择从本地获取图片时
//做非空判断，当我们觉得不满意想重新剪裁的时候便不会报异常，下同
              //  if (data != null)
                    //Uri uri = data.getData();
                    // startPhotoZoom(data.getData(), 150);
                if (data!=null) {
                    Uri uri = data.getData();
                    trime = getPhotoFileName();
                    tv_Name.setVisibility(View.VISIBLE);
                    tv_Name.setText(trime);


                    try {
                        //get the image path
                        String[] projection = {MediaStore.Images.Media.DATA};
                        Log.d("projection的长度", "" + projection.length);
                        CursorLoader cursorLoader = new CursorLoader(this, uri, projection, null, null, null);
                        Cursor cursor = cursorLoader.loadInBackground();
                        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                        cursor.moveToFirst();

                        filepath = cursor.getString(column_index);
                        Log.d("realpath", "" + filepath);
                        LGImgCompressor.getInstance(this).withListener(this).
                                starCompress(filepath, 1000, 1000, 100);

                    } catch (Exception ex) {
                        Log.e("aaaa", "failed." + ex.getMessage());
                    }
                }
                break;

            case PHOTO_REQUEST_CUT://返回的结果
                if (data != null)
                    //  setPicToView(data);
                    break;
        }
        super.onActivityResult(requestCode, resultCode, data);

    }

    // 使用系统当前日期加以调整作为照片的名称
    private String getPhotoFileName() {
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat dateFormat = new SimpleDateFormat("'IMG'_yyyyMMdd_HHmmss");
        return dateFormat.format(date) + ".jpg";
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (tupianpath.equals("")) {
                Intent intent = new Intent();
                //Intent intent = new Intent();
                intent.putExtra("value", ed_number.getText().toString());
                intent.putExtra("picturePath", "abc");
                Log.d("chuangu", "");
                setResult(number1, intent);
                RegisterPicture.this.finish();
            }else {
                Intent intent = new Intent();
                //Intent intent = new Intent();
                intent.putExtra("value", ed_number.getText().toString());
                intent.putExtra("picturePath", tupianpath);
                Log.d("chuangu", "");
                setResult(number1, intent);
                RegisterPicture.this.finish();
            }
            return true;
        } else if (keyCode == KeyEvent.KEYCODE_FOCUS || keyCode == KeyEvent.KEYCODE_CAMERA) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
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

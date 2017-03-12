package com.silianchuangye.sumao.success.fragments.myPlasticTrade.register;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ContentResolver;
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
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.cloopen.rest.sdk.utils.encoder.BASE64Encoder;
import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.utils.FavFTPUtil;
import com.silianchuangye.sumao.success.utils.LogUtils;
import com.silianchuangye.sumao.success.utils.SuMaoConstant;
//import com.zhy.m.permission.MPermissions;
//import com.zhy.m.permission.PermissionDenied;
//import com.zhy.m.permission.PermissionGrant;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RegisterPicture extends AppCompatActivity {
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
    // 创建一个以当前时间为名称的文件
    File tempFile = new File(Environment.getExternalStorageDirectory(),getPhotoFileName());
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register__picture);
        Bundle bundle=getIntent().getExtras();
        title=bundle.getString("name");
        number1=bundle.getInt("number");
        initData();
        event();
    }
    public void initData(){
        ed_number= (EditText) findViewById(R.id.ed_number);
        ivBack= (ImageView) findViewById(R.id.ivback);
        bt_upload= (Button) findViewById(R.id.bt_upload);
        bt_save= (Button) findViewById(R.id.bt_save);
        tv_Name= (TextView) findViewById(R.id.tv_Name);
        layout_title= (RelativeLayout) findViewById(R.id.layout_title);
    }
    public void event(){
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RegisterPicture.this.finish();
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
                Log.d("点击事件","进来了");
                //上传图片
                new Thread(){
                    @Override
                    public void run() {
                        super.run();
                        RequestParams rp = new RequestParams(SuMaoConstant.SUMAO_IP + "/rest/model/atg/commerce/order/myorder/OrderPaymentInfo/fileUpload");
                            SharedPreferences sp=getSharedPreferences("sumao",Activity.MODE_PRIVATE);
                           fileCode=sp.getString("filename","");
                        Log.d("fileCode",fileCode);
                           if (fileCode!=null && fileCode!="") {
                               rp.addParameter("file", fileCode);
                           }else{
                               Log.d("判断","进来了");
                               Log.d("相册的路径",filepath);
                               Bitmap bitmap=BitmapFactory.decodeFile(filepath);
                               fileCode=BitmapToString(bitmap);
                               Log.d("图片编码",fileCode);
                               Toast.makeText(RegisterPicture.this, ""+fileCode, Toast.LENGTH_SHORT).show();
                               rp.addParameter("file", fileCode);
                           }

                            Log.d("文件",fileCode+"");
                            rp.addParameter("fileName", getPhotoFileName());
                            Log.d("名称",getPhotoFileName());
                            Log.d("aaa",rp.getUri().toString());
                            x.http().post(rp, new Callback.CommonCallback<String>() {
                                @Override
                                public void onFinished() {

                                }

                                @Override
                                public void onSuccess(String result) {
                                    //  Intent intent=new Intent();
                                    Intent intent = new Intent();
                                    //Intent intent = new Intent();
                                    intent.putExtra("value", ed_number.getText().toString());
                                    intent.putExtra("picturePath", "/mnt/"+getPhotoFileName());
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


            }
        });
        tv_Name.setText(title);

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
          //      Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
// 指定调用相机拍照后照片的储存路径
//                intent.putExtra(MediaStore.EXTRA_OUTPUT,
//                        Uri.fromFile(tempFile));
//              //  filepath=Uri.fromFile(tempFile).toString();
//               // Log.d("相机路径",filepath);
//                startActivityForResult(intent, PHOTO_REQUEST_TAKEPHOTO);
//                //getImageFromCamera();

                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
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
                tv_Name.setText(getPhotoFileName());
                Bitmap photo = data.getParcelableExtra("data");
                fileBianma=BitmapToString(photo);
                SharedPreferences sp=getSharedPreferences("sumao", Activity.MODE_PRIVATE);
                SharedPreferences.Editor editor=sp.edit();
                editor.putString("filename",fileBianma);
                editor.commit();

                break;
            case PHOTO_REQUEST_GALLERY://当选择从本地获取图片时
//做非空判断，当我们觉得不满意想重新剪裁的时候便不会报异常，下同
              //  if (data != null)
                    //Uri uri = data.getData();
                    // startPhotoZoom(data.getData(), 150);
                tv_Name.setVisibility(View.VISIBLE);
                tv_Name.setText(getPhotoFileName());

                Uri uri = data.getData();
                try {
                    //get the image path
                    String[] projection = {MediaStore.Images.Media.DATA};
                    Log.d("projection的长度",""+projection.length);
                    CursorLoader cursorLoader = new CursorLoader(this,uri,projection,null,null,null);
                    Cursor cursor = cursorLoader.loadInBackground();
                    int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                    cursor.moveToFirst();

                    filepath = cursor.getString(column_index);
                    Log.d("realpath",""+filepath);

                } catch (Exception ex) {
                    Log.e("aaaa", "failed." + ex.getMessage());
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
            finish();
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








}

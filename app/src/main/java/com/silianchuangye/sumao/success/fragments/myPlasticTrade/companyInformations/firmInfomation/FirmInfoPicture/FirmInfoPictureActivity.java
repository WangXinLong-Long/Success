package com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.firmInfomation.FirmInfoPicture;

import android.Manifest;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ActionBarOverlayLayout;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.firmInfomation.CaptureActivity;
import com.silianchuangye.sumao.success.utils.LogUtils;
import com.zhy.m.permission.MPermissions;
import com.zhy.m.permission.PermissionDenied;
import com.zhy.m.permission.PermissionGrant;
import com.zhy.m.permission.ShowRequestPermissionRationale;

import org.xutils.http.annotation.HttpResponse;
import org.xutils.http.app.ResponseParser;
import org.xutils.http.request.UriRequest;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FirmInfoPictureActivity extends AppCompatActivity {
    private ImageView ivPictrue;
    private RelativeLayout layout;
    private Bitmap bitmap;
    private byte[] mcontent;
    private boolean isLogin = false;
    ImageView iv_title_bar_logo,
            iv_title_bar_back,
            iv_title_bar_service,
            iv_title_bar_search;
    Button sv_title_bar_serachView;
    TextView tv_title_bar_title, tv;
    RelativeLayout layoutTop;
    private static final int REQUEST_PERMISSION_CAMERA_CODE = 1;
    private static final int REQUEST_PERMISSION_WRITE_EXTERNAL_STORAGE_CODE = 3;
    private static final int REQUEST_PERMISSION_CAMERA_QR_CODE = 2;
    private File file;
    private Bitmap bitmapCamera;
    private Intent intent;
    private String path = "";
    private static final int TAKE_PICTURE = 0x000001;
    private TextView tvinfo_firm_info_pictrue;
    private Button btSave;
    private TextView tvinfoValue_firm_info_pictrue;
    private Button bt_save, bt_getNumber;
    int number1;
    private RelativeLayout layoutinfo_firm_info;
    static final String[] PERMISSIONS = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA};
    public static final int PERMISSIONS_GRANTED = 0; // 权限授权
    public static final int PERMISSIONS_DENIED = 1; // 权限拒绝
    private static final String PACKAGE_URL_SCHEME = "package:"; // 方案

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firm_info_picture);
        title_Bar();
        layoutinfo_firm_info = (RelativeLayout) findViewById(R.id.layoutinfo_firm_info);
        layoutinfo_firm_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**
                 * 首先要获取权限
                 */if (ActivityCompat.checkSelfPermission(FirmInfoPictureActivity.this,
                        Manifest.permission.CALL_PHONE)
                        != PackageManager.PERMISSION_GRANTED) {

                    MPermissions.requestPermissions(FirmInfoPictureActivity.this, REQUEST_PERMISSION_CAMERA_QR_CODE, PERMISSIONS);
                } else {
                    Intent intent = new Intent(FirmInfoPictureActivity.this, CaptureActivity.class);
                    startActivityForResult(intent, 8);
                }
//

            }
        });
        tvinfoValue_firm_info_pictrue = (TextView) findViewById(R.id.tvinfoValue_firm_info_pictrue);
        tvinfo_firm_info_pictrue = (TextView) findViewById(R.id.tvinfo_firm_info_pictrue);
        Bundle bundle = getIntent().getExtras();
        String name = bundle.getString("name");
        number1 = bundle.getInt("number");
        tvinfo_firm_info_pictrue.setText(name);
        bt_getNumber = (Button) findViewById(R.id.bt_getNumber);
        bt_getNumber.setText("获取" + name);
        bt_getNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FirmInfoPictureActivity.this, CaptureActivity.class);
                startActivityForResult(intent, 8);
            }
        });
        btSave = (Button) findViewById(R.id.bt_save_register_value);
        layout = (RelativeLayout) findViewById(R.id.layout_a);
        ivPictrue = (ImageView) findViewById(R.id.iv_firm_info_pictrue);
        btSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backgroundAlpha(0.5f);
                Popupwindow();
            }
        });
        bt_save = (Button) findViewById(R.id.bt_save);
        bt_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                String uri="http://192.168.32.126:7023/mnt/docs/";
//                RequestParams rp=new RequestParams(uri);
//
//                rp.addBodyParameter("file",new File("content://media/external/images/media/496"));
//
//                Callback.Cancelable cancelable
//                        = x.http().post(rp, new Callback.CommonCallback<ResponseEntity>() {
//
//                    @Override
//                    public void onSuccess(ResponseEntity result) {
//                        Log.d("success","上传成功");
////                        Snackbar.make(ivPictrue, "上传成功", Snackbar.LENGTH_LONG)
////                                .setAction("Action", null).show();
//                    }
//
//                    @Override
//                    public void onError(Throwable ex, boolean isOnCallback) {
//                        Log.d("feil","上传失败");
////                        Snackbar.make(ivDemo, "上传失败", Snackbar.LENGTH_LONG)
////                                .setAction("Action", null).show();
//
//                    }
//
//                    @Override
//                    public void onCancelled(CancelledException cex) {
//
//                    }
//
//                    @Override
//                    public void onFinished() {
//
//                    }
//
//                });

                Intent intent = new Intent();
                intent.putExtra("value", tvinfoValue_firm_info_pictrue.getText().toString());
                setResult(number1, intent);
                FirmInfoPictureActivity.this.finish();
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
        final PopupWindow popupWindow = new PopupWindow(findViewById(R.id.LayoutPictrue), ActionBarOverlayLayout.LayoutParams.MATCH_PARENT, ActionBarOverlayLayout.LayoutParams.WRAP_CONTENT);

        popupWindow.setContentView(view);
        TextView texthotograph = (TextView) view.findViewById(R.id.tvphotograph);
        TextView textphoto = (TextView) view.findViewById(R.id.tvPhoto);
        TextView textCancel = (TextView) view.findViewById(R.id.tvCancel);
        texthotograph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getImageFromCamera();
                popupWindow.dismiss();
            }
        });
        textphoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getImageFromAlbum();
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

        popupWindow.showAtLocation(ivPictrue, Gravity.BOTTOM, 0, 0);

        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                //popupWindow.dismiss();
                backgroundAlpha(1f);
            }
        });


    }

    /**
     * 调用系统相册
     */
    protected void getImageFromAlbum() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");//相片类型
        startActivityForResult(intent, 0);
    }

    public void useCamera() {
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");

        StringBuffer sDir = new StringBuffer();
        if (hasSDcard()) {
            sDir.append(Environment.getExternalStorageDirectory()
                    + "/DCIM/Camera/");
        } else {
            String dataPath = Environment.getRootDirectory().getPath();
            sDir.append(dataPath + "/DCIM/Camera/");
        }

        File fileDir = new File(sDir.toString());
        if (!fileDir.exists()) {
            fileDir.mkdirs();
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");

        file = new File(fileDir, "IMG_" + sdf.format(new Date()) + ".jpg");

        path = file.getPath();
        Uri imageUri = Uri.fromFile(file);
        Log.d("imageUri", imageUri + "");
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        startActivityForResult(intent, TAKE_PICTURE);
    }//低于6.0直接使用Camera

    // 显示缺失权限提示
    public void showMissingPermissionDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(FirmInfoPictureActivity.this);
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

    protected void getImageFromCamera() {
        MPermissions.requestPermissions(FirmInfoPictureActivity.this, REQUEST_PERMISSION_CAMERA_CODE, PERMISSIONS);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        MPermissions.onRequestPermissionsResult(FirmInfoPictureActivity.this, requestCode, permissions, grantResults);
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @PermissionGrant(REQUEST_PERMISSION_CAMERA_CODE)
    public void requestCameraSuccess() {
        useCamera();
    }

    @PermissionDenied(REQUEST_PERMISSION_CAMERA_CODE)
    public void requestCameraFailed() {
        showMissingPermissionDialog();
    }

    @PermissionGrant(REQUEST_PERMISSION_CAMERA_QR_CODE)
    public void requestCameraQRSuccess() {
        Intent intent = new Intent(FirmInfoPictureActivity.this, CaptureActivity.class);
        startActivityForResult(intent, 8);
    }

    @PermissionDenied(REQUEST_PERMISSION_CAMERA_QR_CODE)
    public void requestCameraQRFailed() {
        showMissingPermissionDialog();
    }

    @PermissionGrant(REQUEST_PERMISSION_WRITE_EXTERNAL_STORAGE_CODE)
    public void requestEXTERNAL_STORAGESuccess() {
    }

    @PermissionDenied(REQUEST_PERMISSION_WRITE_EXTERNAL_STORAGE_CODE)
    public void requestEXTERNAL_STORAGEFailed() {
        showMissingPermissionDialog();
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        ContentResolver resolver = this.getContentResolver();

        if (requestCode == 0) {
            if (data == null) {
                return;
            } else {
                try {
                    mcontent = readStream(resolver.openInputStream(Uri.parse(getUrl(data))));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                //将字节数组转换为ImageView可调用的Bitmap对象
                bitmap = getPicFromBytes(mcontent, null);
                if (getUrl(data) != null && !getUrl(data).equals("")) {
                    ivPictrue.setImageBitmap(bitmap);
                    path = getUrl(data);
                    Log.d("正确的存储路径", getUrl(data));
                } else {
                    ////把得到的图片绑定在控件上显示
                    Log.d("错误的存储路径", getUrl(data));
                }
            }

        } else if (requestCode == TAKE_PICTURE) {
            intent = data;
            MPermissions.requestPermissions(FirmInfoPictureActivity.this, REQUEST_PERMISSION_WRITE_EXTERNAL_STORAGE_CODE, Manifest.permission.WRITE_EXTERNAL_STORAGE);
            File newFile = insertFileToSd(file);
            LogUtils.log("test------------->newFile:" + newFile.toString());
            Bitmap copyBitmap = getBitmapByBytes(newFile);
            LogUtils.log("得到copyBitmap:---->");
//            bitmapCamera = BitmapFactory.decodeFile(newFile.toString());
//            Bitmap copyBitmap = copyBitmap(bitmapCamera);

            ivPictrue.setImageBitmap(copyBitmap);
        } else if (requestCode == 8) {
            String result = data.getStringExtra("result");
            Log.d("条形码", result);
            tvinfoValue_firm_info_pictrue.setText(result);


        }
//        if (resultCode == Activity.RESULT_CANCELED)//加上这个判断就好了
//        {
//            Intent it = new Intent(getApplicationContext(), FirmInfoPictureActivity.class);
////            it.putExtra("uno",uno);
//            startActivity(it);
//            finish();
//            return;
//        }
    }

    /**
     * 根据图片字节数组，对图片可能进行二次采样，不致于加载过大图片出现内存溢出
     *
     * @param
     * @return
     */
    public static Bitmap getBitmapByBytes(File file) {

        //对于图片的二次采样,主要得到图片的宽与高
        int width = 0;
        int height = 0;
        int sampleSize = 1; //默认缩放为1
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;  //仅仅解码边缘区域
        //如果指定了inJustDecodeBounds，decodeByteArray将返回为空
//        BitmapFactory.decodeByteArray(bytes, 0, bytes.length, options);
        BitmapFactory.decodeFile(file.getAbsolutePath(), options);

        //得到宽与高
        height = options.outHeight;
        width = options.outWidth;

        //图片实际的宽与高，根据默认最大大小值，得到图片实际的缩放比例
//        while ((height / sampleSize > Cache.IMAGE_MAX_HEIGHT)
//                || (width / sampleSize > Cache.IMAGE_MAX_WIDTH)) {
//            sampleSize *= 2;
//        }
        sampleSize *= 10;
        //不再只加载图片实际边缘
        options.inJustDecodeBounds = false;
        //并且制定缩放比例
        options.inSampleSize = sampleSize;
        return BitmapFactory.decodeFile(file.getAbsolutePath(), options);
    }

    public static File insertFileToSd(File file) {
        String path = Environment.getExternalStorageDirectory().getPath();
        LogUtils.log("path的值是-----》" + path);
        StringBuffer sDir = new StringBuffer();
        if (hasSDcard()) {
            sDir.append(Environment.getExternalStorageDirectory() + "/SuMao/picture/");
        } else {
            String dataPath = Environment.getRootDirectory().getPath();
            sDir.append(dataPath + "/SuMao/picture/");
        }

        File file_packge = new File(sDir.toString());
        if (!file_packge.exists()) {
            file_packge.mkdirs();
        }
        File file2 = null;
        try {
            file2 = new File(file_packge, String.valueOf(System.currentTimeMillis()) + ".jpg");
            LogUtils.log("file2的路径是：-------->" + file2.getPath());
            if (!file2.exists()) {
                file2.createNewFile();
            }
            FileOutputStream fOut = null;
            fOut = new FileOutputStream(file2);
            FileInputStream in = new FileInputStream(file);
            byte[] bytes = new byte[1024];
            int lenght = 0;
            while ((lenght = in.read(bytes)) != -1) {
                fOut.write(bytes, 0, lenght);
                fOut.flush();
            }
            fOut.close();
            in.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Log.e("error", e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("error", e.getMessage());
        }
        return file2;
    }

    public static boolean hasSDcard() {
        String status = Environment.getExternalStorageState();
        if (status.equals(Environment.MEDIA_MOUNTED)) {
            LogUtils.log("SD卡的状态是：-----》" + "true");
            return true;

        } else {
            LogUtils.log("SD卡的状态是：-----》" + "false");
            return false;
        }
    }

    public static Bitmap getPicFromBytes(byte[] bytes, BitmapFactory.Options opts) {
        if (bytes != null)
            if (opts != null)
                return BitmapFactory.decodeByteArray(bytes, 0, bytes.length, opts);
            else
                return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
        return null;
    }

    public static byte[] readStream(InputStream inStream) throws Exception {
        byte[] buffer = new byte[1024];
        int len = -1;
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        while ((len = inStream.read(buffer)) != -1) {
            outStream.write(buffer, 0, len);
        }
        byte[] data = outStream.toByteArray();
        outStream.close();
        inStream.close();
        return data;

    }

    public void title_Bar() {
        iv_title_bar_back = ((ImageView) findViewById(R.id.iv_title_bar_back));
        iv_title_bar_logo = ((ImageView) findViewById(R.id.iv_title_bar_logo));
        iv_title_bar_service = ((ImageView) findViewById(R.id.iv_title_bar_service));
        iv_title_bar_search = ((ImageView) findViewById(R.id.iv_title_bar_search));
        sv_title_bar_serachView = ((Button) findViewById(R.id.sv_title_bar_serachView));
        tv_title_bar_title = ((TextView) findViewById(R.id.tv_title_bar_title));

        iv_title_bar_logo.setVisibility(View.GONE);
        iv_title_bar_service.setVisibility(View.GONE);
        sv_title_bar_serachView.setVisibility(View.GONE);
        iv_title_bar_search.setVisibility(View.GONE);
        tv_title_bar_title.setText("上传图片");
        tv_title_bar_title.setTextColor(Color.WHITE);
        iv_title_bar_back.setVisibility(View.GONE);
        layoutTop = (RelativeLayout) findViewById(R.id.layoutTop_firm_info_pivtrue);
        layoutTop.setBackgroundColor(getResources().getColor(R.color.textColor_expandable_listview_show));

        iv_title_bar_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    public String getUrl(Intent data) {

        Uri uri = data.getData();
        Log.d("路径", uri.toString());
        return uri.toString();
    }


    @HttpResponse(parser = ResultParser.class)
    public class ResponseEntity {
        private String result;

        public String getResult() {
            return result;
        }

        public void setResult(String result) {
            this.result = result;
        }
    }

    public class ResultParser implements ResponseParser {
        @Override
        public void checkResponse(UriRequest request) throws Throwable {

        }

        @Override
        public Object parse(Type resultType, Class<?> resultClass, String result) throws Throwable {

            ResponseEntity responseEntity = new ResponseEntity();
            responseEntity.setResult(result);
            //返回ResponseEntity对象
            return responseEntity;
        }
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
//			setResult(RESULT_CANCELED);
//			finish();

            Intent intent=new Intent();
            //Intent intent = new Intent();
            intent.putExtra("value","");
            setResult(number1, intent);
            finish();
            return true;
        } else if (keyCode == KeyEvent.KEYCODE_FOCUS || keyCode == KeyEvent.KEYCODE_CAMERA) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


}

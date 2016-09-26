package com.silianchuangye.sumao.success.fragments.myPlasticTrade.register;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.provider.Settings;
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
import android.widget.TextView;
import android.widget.Toast;

import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.utils.FavFTPUtil;
import com.silianchuangye.sumao.success.utils.LogUtils;
import com.silianchuangye.sumao.success.utils.SuMaoConstant;
import com.zhy.m.permission.MPermissions;
import com.zhy.m.permission.PermissionDenied;
import com.zhy.m.permission.PermissionGrant;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RegisterPicture extends AppCompatActivity implements View.OnClickListener{

    private ImageView picture;
    private View get_picture;
    private static final int REQUEST_PERMISSION_CAMERA_CODE = 1;
    static final String[] PERMISSIONS = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA};
    private File file;
    private String path = "";
    private static final int TAKE_PICTURE = 0x000001;
    private static final String PACKAGE_URL_SCHEME = "package:"; // 方案
    private byte[] mcontent;
    private Bitmap bitmap;
    private String filePath = null;
    private Intent intent;
    int number1;
    private static final int REQUEST_PERMISSION_WRITE_EXTERNAL_STORAGE_CODE = 3;
    private TextView tv_child_title_bar_title;
    private Button save;
    private TextView prompt;
    private ProgressDialog dialog;
    String filename = System.currentTimeMillis() + "image.jpg";
    Handler piandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    boolean flag = (boolean) msg.obj;
                    LogUtils.log("handlerflag" + flag);
                    Toast.makeText(RegisterPicture.this, "--->" + flag + "", Toast.LENGTH_SHORT).show();
//                    new CustomProgress(FirmInfoPictureActivity.this).dismiss();
                    Intent intent = new Intent();
                    intent.putExtra("picturePath", filename);
                    intent.putExtra("value", "已有相片");
                    setResult(number1, intent);
                    RegisterPicture.this.finish();
                    break;
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register__picture);
        picture = ((ImageView) findViewById(R.id.picture));
        get_picture = findViewById(R.id.get_picture);
        get_picture.setOnClickListener(this);
        tv_child_title_bar_title = ((TextView) findViewById(R.id.tv_child_title_bar_title));
        tv_child_title_bar_title.setText("照片");
        save = ((Button) findViewById(R.id.save));
        save.setOnClickListener(this);
        prompt = ((TextView) findViewById(R.id.prompt));
        Bundle bundle = getIntent().getExtras();
        String name = bundle.getString("name");
        number1 = bundle.getInt("number");
        LogUtils.log("number1----->"+number1);
        dialog = new ProgressDialog(this);
        dialog.setTitle("提示");
        dialog.setMessage("正在上传…………");
        dialog.setIcon(R.mipmap.logo);
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setCancelable(false);
        dialog.setIndeterminate(false);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.get_picture:
                backgroundAlpha(0.5f);
                Popupwindow();
                break;
            case R.id.save:
//                prompt
                if (filePath == null||filePath.equals(""))
                {
                    prompt.setVisibility(View.VISIBLE);
                }else {
                    dialog.show();
                    new Thread(new Runnable() {



                        @Override
                        public void run() {
                            Message msg = new Message();

                            boolean flag = FavFTPUtil.uploadFileFromProduction(SuMaoConstant.hostname, SuMaoConstant.port, SuMaoConstant.username, SuMaoConstant.password, SuMaoConstant.pathname, filename, filePath);
                            LogUtils.log("flag-->" + flag);
                            msg.what = 1;
                            msg.obj = flag;
                            piandler.sendMessage(msg);
                            dialog.dismiss();
                        }
                    }).start();
                    LogUtils.log("照片背景---->"+filePath);

                    break;
                }

        }
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

        popupWindow.showAtLocation(picture, Gravity.BOTTOM, 0, 0);

        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                //popupWindow.dismiss();
                backgroundAlpha(1f);
            }
        });


    }

    protected void getImageFromCamera() {
        MPermissions.requestPermissions(RegisterPicture.this, REQUEST_PERMISSION_CAMERA_CODE, PERMISSIONS);
    }

    /**
     * 调用系统相册
     */
    protected void getImageFromAlbum() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");//相片类型
        startActivityForResult(intent, 0);
    }
    @PermissionGrant(REQUEST_PERMISSION_CAMERA_CODE)
    public void requestCameraSuccess() {
        useCamera();
    }

    @PermissionDenied(REQUEST_PERMISSION_CAMERA_CODE)
    public void requestCameraFailed() {
        showMissingPermissionDialog();
    }

    @PermissionGrant(REQUEST_PERMISSION_WRITE_EXTERNAL_STORAGE_CODE)
    public void requestEXTERNAL_STORAGESuccess() {
    }

    @PermissionDenied(REQUEST_PERMISSION_WRITE_EXTERNAL_STORAGE_CODE)
    public void requestEXTERNAL_STORAGEFailed() {
        showMissingPermissionDialog();
    }

    //低于6.0直接使用Camera
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
    }

    // 显示缺失权限提示
    public void showMissingPermissionDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(RegisterPicture.this);
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


    // 启动应用的设置
    private void startAppSettings() {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.setData(Uri.parse(PACKAGE_URL_SCHEME + getPackageName()));
        startActivity(intent);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        ContentResolver resolver = this.getContentResolver();

        if (requestCode == 0) {
            if (data == null) {
                return;
            } else {
                prompt.setVisibility(View.INVISIBLE);
                try {
                    mcontent = readStream(resolver.openInputStream(Uri.parse(getUrl(data))));
                } catch (Exception e) {
                    e.printStackTrace();
                }
//                因为在小米手机6.01的系统上不能将取到的图片展示出来，所以怀疑是手机版本API的问题，暂时的解决办法是这样，判断版本号
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    try {
                        Uri originalUri = data.getData(); // 获得图片的uri
                        bitmap = MediaStore.Images.Media.getBitmap(resolver, originalUri);
                        int width = picture.getMeasuredWidth();
                        int heigh = picture.getMeasuredHeight();
                        LogUtils.log("width---->" + width + "heigh------->" + heigh);
                        LogUtils.log("originalUri.toString()---->" + originalUri.toString());
                        int picwidth = bitmap.getWidth();
                        int pichigh = bitmap.getHeight();
                        LogUtils.log("picwidth---->" + picwidth + "pichigh------->" + pichigh);
                        double x = ((picwidth * 1.0) / width) > ((pichigh * 1.0) / heigh) ? ((picwidth * 1.0) / width) : ((pichigh * 1.0) / heigh);
                        //使用系统的一个工具类，参数列表为 Bitmap Width,Height  这里使用压缩后显示，否则在华为手机上ImageView 没有显示
                        picture.setImageBitmap(ThumbnailUtils.extractThumbnail(bitmap, (int) (picwidth / x), (int) (pichigh / x)));
//                        这里这个路径，就是图片的路径，在上传服务器的时候需要用到
                        String file = originalUri.toString();
                        int positon = file.indexOf("//");
                        LogUtils.log("positon---->" + positon);
                        filePath = file.substring(positon + 2);
                        LogUtils.log("filePath---->" + filePath);
                    } catch (IOException e) {
                        Log.e("TAG-->Error", e.toString());
                    } finally {
                        return;
                    }
                } else {
                    //将字节数组转换为ImageView可调用的Bitmap对象
                    bitmap = getPicFromBytes(mcontent, null);
                    if (getUrl(data) != null && !getUrl(data).equals("")) {
                        picture.setImageBitmap(bitmap);
                        path = getUrl(data);
                        filePath = path.toString();
                        LogUtils.log("filePath---->" + filePath);
                        Log.d("正确的存储路径", getUrl(data));
                    } else {
                        ////把得到的图片绑定在控件上显示
                        Log.d("错误的存储路径", getUrl(data));
                    }
                }

            }

        } else if (requestCode == TAKE_PICTURE) {
            intent = data;
            MPermissions.requestPermissions(RegisterPicture.this, REQUEST_PERMISSION_WRITE_EXTERNAL_STORAGE_CODE, Manifest.permission.WRITE_EXTERNAL_STORAGE);
            File newFile = insertFileToSd(file);
            LogUtils.log("test------------->newFile:" + newFile.toString());
            filePath = newFile.toString();
            LogUtils.log("filePath---->" + filePath);
            Bitmap copyBitmap = getBitmapByBytes(newFile);
            LogUtils.log("得到copyBitmap:---->");
//            bitmapCamera = BitmapFactory.decodeFile(newFile.toString());
//            Bitmap copyBitmap = copyBitmap(bitmapCamera);

            picture.setImageBitmap(copyBitmap);
        }
    }


    public String getUrl(Intent data) {

        Uri uri = data.getData();
        Log.d("路径", uri.toString());
        return uri.toString();
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

    public static Bitmap getPicFromBytes(byte[] bytes, BitmapFactory.Options opts) {
        if (bytes != null)
            if (opts != null)
                return BitmapFactory.decodeByteArray(bytes, 0, bytes.length, opts);
            else
                return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
        return null;
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

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
//			setResult(RESULT_CANCELED);
//			finish();

            Intent intent = new Intent();
            //Intent intent = new Intent();
            intent.putExtra("value", "");
            intent.putExtra("picturePath", "");
            setResult(number1, intent);
            finish();
            return true;
        } else if (keyCode == KeyEvent.KEYCODE_FOCUS || keyCode == KeyEvent.KEYCODE_CAMERA) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}

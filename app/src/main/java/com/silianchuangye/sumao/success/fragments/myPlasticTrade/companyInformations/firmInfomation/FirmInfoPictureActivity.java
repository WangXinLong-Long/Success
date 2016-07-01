package com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.firmInfomation;

import android.Manifest;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
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
import android.widget.Toast;

import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.utils.LogUtils;
import com.zhy.m.permission.MPermissions;
import com.zhy.m.permission.PermissionDenied;
import com.zhy.m.permission.PermissionGrant;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
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
    private File file;
    private Bitmap bitmapCamera;
    private Intent intent;
    private String path = "";
    private static final int TAKE_PICTURE = 0x000000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firm_info_picture);
        title_Bar();
        layout = (RelativeLayout) findViewById(R.id.layout_a);
        ivPictrue = (ImageView) findViewById(R.id.iv_firm_info_pictrue);
        ivPictrue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backgroundAlpha(0.5f);
                Popupwindow();
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

    protected void getImageFromCamera() {
        MPermissions.requestPermissions(FirmInfoPictureActivity.this, REQUEST_PERMISSION_CAMERA_CODE, Manifest.permission.CAMERA);
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

        File file = new File(fileDir, "IMG_" + sdf.format(new Date()) + ".jpg");

        path = file.getPath();
        Uri imageUri = Uri.fromFile(file);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        startActivityForResult(intent, TAKE_PICTURE);
    }//低于6.0直接使用Camera

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        MPermissions.requestPermissions(FirmInfoPictureActivity.this, REQUEST_PERMISSION_CAMERA_CODE, Manifest.permission.CAMERA);
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @PermissionGrant(REQUEST_PERMISSION_CAMERA_CODE)
    public void requestCameraSuccess() {
        useCamera();
    }

    @PermissionDenied(REQUEST_PERMISSION_CAMERA_CODE)
    public void requestCameeraFailed() {
        Toast.makeText(this, "请授权允许使用相机", Toast.LENGTH_SHORT).show();
    }

    @PermissionGrant(REQUEST_PERMISSION_WRITE_EXTERNAL_STORAGE_CODE)
    public void requestExternalStorageSuccess() {
        Intent data = intent;
        createData(data);
    }

    @PermissionDenied(REQUEST_PERMISSION_WRITE_EXTERNAL_STORAGE_CODE)
    public void requestExternalStorageFailed() {
        Toast.makeText(this, "请授权允许读写内存", Toast.LENGTH_SHORT).show();
    }

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
            LogUtils.log("test------------->" + newFile.toString());
            bitmapCamera = BitmapFactory.decodeFile(newFile.toString());
            ivPictrue.setImageBitmap(bitmapCamera);
        }
        if (resultCode == Activity.RESULT_CANCELED)//加上这个判断就好了
        {
            Intent it = new Intent(getApplicationContext(), FirmInfoPictureActivity.class);
//            it.putExtra("uno",uno);
            startActivity(it);
            finish();
            return;
        }
    }

    public void createData(Intent data) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
        StringBuffer sDir = new StringBuffer();
        if (hasSDcard()) {
            sDir.append(Environment.getExternalStorageDirectory() + "/DCIM/Camera/");
        } else {
            String dataPath = Environment.getRootDirectory().getPath();
            sDir.append(dataPath + "/DCIM/Camera/");
        }

        File file_packge = new File(sDir.toString());
        if (!file_packge.exists()) {
            file_packge.mkdirs();
        }
        file = new File(file_packge, "IMG_" + sdf.format(new Date()) + ".jpg");
        if (!file.exists())
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        if (data.getExtras() != null) {
            Bundle bundle = data.getExtras();
            bitmapCamera = (Bitmap) bundle.get("data");
            try {
                BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
                bitmapCamera.compress(Bitmap.CompressFormat.JPEG, 100, bos);
                bos.flush();
                bos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else return;
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
        iv_title_bar_back.setVisibility(View.VISIBLE);
        layoutTop = (RelativeLayout) findViewById(R.id.layoutTop_firm_info_pivtrue);
        layoutTop.setBackgroundColor(getResources().getColor(R.color.textColor_expandable_listview_show));

        iv_title_bar_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirmInfoPictureActivity.this.finish();
            }
        });
    }

    public String getUrl(Intent data) {

        Uri uri = data.getData();
        Log.d("路径", uri.toString());
        return uri.toString();
    }


}

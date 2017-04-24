package com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.firmInfomation;

import android.app.Activity;
import android.content.Context;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ActionBarOverlayLayout;
import android.util.Base64;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.register.FirmInfoTypeActivityMVP.presenter.GetEntInformationPresenter;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.register.FirmInfoTypeActivityMVP.view.IFirmInfoTypeActivity;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.register.RegisterFirmActivityMVP.presenter.RegisterFirmActivityPresenter;
import com.silianchuangye.sumao.success.model.EnterpriseInformation;
import com.silianchuangye.sumao.success.utils.LGImgCompressor;
import com.silianchuangye.sumao.success.utils.LogUtils;
import com.silianchuangye.sumao.success.utils.SuMaoConstant;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FirmInfoTypeActivity extends AppCompatActivity implements IFirmInfoTypeActivity, LGImgCompressor.CompressListener {
    ImageView iv_title_bar_logo,
            iv_title_bar_back,
            iv_title_bar_service,
            iv_title_bar_search;
    Button sv_title_bar_serachView;
    TextView tv_title_bar_title, tv;
    RelativeLayout layoutTop;
    // RadioButton one,two,three;
    RadioGroup rgDemo;
    private String name;
    private Button btSave;
    private LinearLayout three_layout;
    String title;
    int number;
    private ListView lv_type;
    private List<String> list;
    GetEntInformationPresenter getEntInformation;
    Intent intent;
    ListAdapter adapter;
    private TextView prompt;
    private List<EnterpriseInformation> typeAndLevel;
    private String level;
    private RegisterFirmActivityPresenter presenter;
    private Button btUpload;
    private String fileBianma=null;
    private String filepath=null;
    private String fileCode=null;
    private String tupian="";
    private String info="";
    private String tupianpath="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firm_info_type);
        getEntInformation = new GetEntInformationPresenter(this);
        lv_type = (ListView) findViewById(R.id.lv_Firm_Type);
        btUpload= (Button) findViewById(R.id.bt_upload);
        btUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backgroundAlpha(0.5f);
                Popupwindow();
            }
        });
        prompt = ((TextView) findViewById(R.id.prompt));
        list = new ArrayList<String>();
        intent = new Intent();
        Bundle bundle = getIntent().getExtras();
        title = bundle.getString("title");
        number = bundle.getInt("number");
        info=bundle.getString("value");

        tupianpath=bundle.getString("path");

        Log.d("number", number + "");
        LogUtils.log("title标题--->"+title);
        LogUtils.log("number--->"+number);
        title_Bar(title);
        if (title.equals("企业类型")) {
            getEntInformation.setDataInView(1);
        } else if (title.equals("纳税人类型")) {
            getEntInformation.setDataInView(2);
            btUpload.setVisibility(View.VISIBLE);
        }else if(title.equals("企业注册证件")){
            getEntInformation.setDataInView(3);
        }else if (title.equals("申请成为塑贸网")){
            getEntInformation.setDataInView(4);
        }


        lv_type.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        lv_type.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(FirmInfoTypeActivity.this, "进来listview的item的点击事件", Toast.LENGTH_SHORT).show();
                name = list.get(position).toString();
                Log.d("选中的文字", name);
            }
        });
        btSave = (Button) findViewById(R.id.btSave);
        btSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (name == null || name.isEmpty()) {
                    prompt.setText("请选择类型");
                    prompt.setTextColor(Color.RED);
                    prompt.setVisibility(View.VISIBLE);

                } else {

                    if (!tupian.equals("")){
                        Log.d("点击事件", "进来了");
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
                                    SharedPreferences sp1=getSharedPreferences("sumao",Activity.MODE_PRIVATE);
                                    SharedPreferences.Editor editor=sp1.edit();
                                    editor.remove("filename");
                                    editor.commit();

                                }else{
                                    Log.d("判断","进来了");
                                    Log.d("相册的路径",filepath);
                                    Bitmap bitmap=BitmapFactory.decodeFile(filepath);
                                    fileCode=BitmapToString(bitmap);
                                    Log.d("图片编码",fileCode);
                                   // Toast.makeText(FirmInfoTypeActivity.this, ""+fileCode, Toast.LENGTH_SHORT).show();
                                    rp.addParameter("file", fileCode);
                                }

                                Log.d("文件",fileCode+"");
                                rp.addParameter("fileName", tupian);
                                Log.d("名称",tupian);
                                Log.d("aaa",rp.getUri().toString());
                                x.http().post(rp, new Callback.CommonCallback<String>() {
                                    @Override
                                    public void onFinished() {

                                    }

                                    @Override
                                    public void onSuccess(String result) {
                                        //  Intent intent=new Intent();
//                                    IntentIntent intent = new Intent();
//                                    //Intent intent = new Intent();
//                                    intent.putExtra("value", ed_number.getText().toString());
//                                    intent.putExtra("picturePath", "/mnt/"+getPhotoFileName());
//                                    setResult(number1, intent);
//                                    RegisterPicture.this.finish();

                                        Intent intent = new Intent();
                                        intent.putExtra("name", name);
                                        intent.putExtra("level",level);
                                        intent.putExtra("picturePath","/mnt/docs/"+tupian);
                                        FirmInfoTypeActivity.this.setResult(RESULT_OK, intent);
                                        FirmInfoTypeActivity.this.finish();



                                    }

                                    @Override
                                    public void onError(Throwable ex, boolean isOnCallback) {

                                    }

                                    @Override
                                    public void onCancelled(CancelledException cex) {

                                    }
                                });

                            }
                        }.start();

                    }else {
                        Intent intent = new Intent();
                        intent.putExtra("name", name);
                        intent.putExtra("level",level);
                        intent.putExtra("picturePath","abc");
                         FirmInfoTypeActivity.this.setResult(RESULT_OK, intent);
                        FirmInfoTypeActivity.this.finish();


                    }




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
                //      Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
// 指定调用相机拍照后照片的储存路径
//                intent.putExtra(MediaStore.EXTRA_OUTPUT,
//                        Uri.fromFile(tempFile));
//              //  filepath=Uri.fromFile(tempFile).toString();
//               // Log.d("相机路径",filepath);
//                startActivityForResult(intent, PHOTO_REQUEST_TAKEPHOTO);
//                //getImageFromCamera();

//                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//                startActivityForResult(intent, 1);
//                popupWindow.dismiss();
                tupian=getPhotoFileName();
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
                //fileBianma=null;
                Intent intent = new Intent(Intent.ACTION_PICK, null);
                intent.setDataAndType(
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                        "image/*");
                startActivityForResult(intent, 2);
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

        popupWindow.showAtLocation(btUpload, Gravity.BOTTOM, 0, 0);

        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                //popupWindow.dismiss();
                backgroundAlpha(1f);
            }
        });


    }

    @Override
    public void initFirmInfoTypeActivityView(List<EnterpriseInformation> names) {
//        list.add(names.get(i).getDisplayName());

        typeAndLevel = names;
        Log.d("names",names.toString());
        if (names.contains("买卖双方身份")){
            names.remove("买卖双方身份");
        }
        Log.d("name的值",names.toString());
        adapter = new ListAdapter(getApplication(), names);
        lv_type.setAdapter(adapter);
    }

    public void title_Bar(final String name) {
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
        tv_title_bar_title.setText(name);
        tv_title_bar_title.setTextColor(Color.WHITE);
        iv_title_bar_back.setVisibility(View.GONE);
        layoutTop = (RelativeLayout) findViewById(R.id.layoutTop_firm_info_type);
        layoutTop.setBackgroundColor(getResources().getColor(R.color.textColor_expandable_listview_show));

        iv_title_bar_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (title.equals("纳税人类型")){
                    if (prompt.getVisibility()==View.GONE){
                        Toast.makeText(FirmInfoTypeActivity.this, "请上传纳税人图片", Toast.LENGTH_SHORT).show();
                    }else {
                        if (info.equals("")){
                            intent.putExtra("name", "");
                            intent.putExtra("level","");
                            intent.putExtra("picturePath","abc");
                            FirmInfoTypeActivity.this.setResult(RESULT_OK, intent);
                            finish();
                        }else{
                            intent.putExtra("name", name);
                            intent.putExtra("level",level);
                            intent.putExtra("picturePath","abc");
                            FirmInfoTypeActivity.this.setResult(RESULT_OK, intent);
                            finish();
                        }
                    }

                }else {
                    if (info.equals("")) {
                        intent.putExtra("name", "");
                        intent.putExtra("level", "");
                        FirmInfoTypeActivity.this.setResult(RESULT_OK, intent);
                        finish();
                    } else {
                        intent.putExtra("name", name);
                        intent.putExtra("level", level);
                        FirmInfoTypeActivity.this.setResult(RESULT_OK, intent);
                        finish();
                    }
                }
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
            case 1://当选择拍照时调用

                if (resultCode == RESULT_CANCELED) {
                    Toast.makeText(FirmInfoTypeActivity.this, "取消了拍照", Toast.LENGTH_LONG).show();
                    return;
                }
                prompt.setVisibility(View.VISIBLE);

                prompt.setText(tupian);
                Bitmap photo = data.getParcelableExtra("data");
                fileBianma=BitmapToString(photo);
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
            case 2://当选择从本地获取图片时
//做非空判断，当我们觉得不满意想重新剪裁的时候便不会报异常，下同
                //  if (data != null)
                //Uri uri = data.getData();
                // startPhotoZoom(data.getData(), 150);
                prompt.setVisibility(View.VISIBLE);
                tupian=getPhotoFileName();
                prompt.setText(tupian);

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
                    LGImgCompressor.getInstance(this).withListener(this).
                            starCompress(filepath+"", 1000, 1000, 2048);

                } catch (Exception ex) {
                    Log.e("aaaa", "failed." + ex.getMessage());
                }
                break;

            case 3://返回的结果
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
            if (title.equals("纳税人类型")){
                if (prompt.getVisibility()==View.GONE){
                    Toast.makeText(FirmInfoTypeActivity.this, "请上传纳税人图片", Toast.LENGTH_SHORT).show();
                }else {
                    if (info.equals("")){
                        intent.putExtra("name", "");
                        intent.putExtra("level","");
                        intent.putExtra("picturePath","abc");
                        FirmInfoTypeActivity.this.setResult(RESULT_OK, intent);
                        finish();
                    }else{
                        intent.putExtra("name", "");
                        intent.putExtra("level","");
                        intent.putExtra("picturePath","abc");
                        FirmInfoTypeActivity.this.setResult(RESULT_OK, intent);
                        finish();
                    }
                }

            }else {
                if (info.equals("")) {
                    intent.putExtra("name", "");
                    intent.putExtra("level", "");
                    FirmInfoTypeActivity.this.setResult(RESULT_OK, intent);
                    finish();
                }else{
                    intent.putExtra("name", name);
                    intent.putExtra("level", level);
                    FirmInfoTypeActivity.this.setResult(RESULT_OK, intent);
                    finish();
                }
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

    class ViewHolder {

        RadioButton rb_state;
    }

    class ListAdapter extends BaseAdapter {
        private Context context;
        private List<EnterpriseInformation> list;
        Map<String, Boolean> states = new HashMap<String, Boolean>();

        public ListAdapter(Context context,List<EnterpriseInformation> list) {
            this.context = context;
            this.list = list;
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            LayoutInflater inflater = LayoutInflater.from(FirmInfoTypeActivity.this);
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.item_type_radio_button, null);
                holder = new ViewHolder();
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            final RadioButton radio = (RadioButton) convertView.findViewById(R.id.rbCheck);
            holder.rb_state = radio;
            radio.setText(list.get(position).getDisplayName());
//            if (!info.equals("") && info.equals(radio.getText().toString())){
//
////                states.put(String.valueOf(position), radio.isChecked());
//                radio.setChecked(true);
//            }
            holder.rb_state.setOnClickListener(new View.OnClickListener() {

                public void onClick(View v) {
                    name = list.get(position).getDisplayName().toString();
                    level = list.get(position).getId().trim();
                    for (String key : states.keySet()) {
                        states.put(key, false);
                    }
                    //选中点中的
                    states.put(String.valueOf(position), radio.isChecked());
                    Log.d("states", String.valueOf(position) + "");
                    ListAdapter.this.notifyDataSetChanged();
                }
            });

            boolean res = false;
            if (states.get(String.valueOf(position)) == null || states.get(String.valueOf(position)) == false) {
                res = false;
                states.put(String.valueOf(position), false);
            } else
                res = true;


            holder.rb_state.setChecked(res);
            return convertView;


        }
    }



//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
//            setValueName();
//        }
//        return super.onKeyDown(keyCode, event);
//    }
//    void setValueName(){
//        intent.putExtra("name", "");
//        intent.putExtra("level","");
//        FirmInfoTypeActivity.this.setResult(RESULT_OK, intent);
//    }
}
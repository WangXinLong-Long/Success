package com.silianchuangye.sumao.success.fragments.myPlasticTrade.register.RegisterFirmActivityMVP.view;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.custom.CustomListView;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.firmInfomation.FirmInfoPicture.FirmInfoPictureActivity;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.firmInfomation.FirmInfoTypeActivity;

import com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.receiptAddress.SelectProvinceAreaMVP.view.SelectProvinceArea;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.login.LoginUserActivity;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.register.RegisterFirmActivityMVP.presenter.RegisterFirmActivityPresenter;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.register.RegisterValueActivity;
import com.silianchuangye.sumao.success.utils.LogUtils;
import com.silianchuangye.sumao.success.utils.SuMaoConstant;

import org.json.JSONArray;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.HttpMethod;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

public class RegisterFirmActivity extends AppCompatActivity implements IRegisterFirmActivityView {
    ImageView iv_title_bar_logo,
            iv_title_bar_back,
            iv_title_bar_service,
            iv_title_bar_search;
    Button sv_title_bar_serachView;
    TextView tv_title_bar_title, tv;
    RelativeLayout layoutTop;
    private CustomListView lvupdate_firm_info;
    private List<Map<String, Object>> list;
    private List<Map<String, Object>> list1;
    private ListView lvupdate_firm_info_two;
    String title;
    private SimpleAdapter adapter;
    private SimpleAdapter adapter1;
    private Button bt_save_register_value;
    private String account;
    private String pass;
    private String repass;
    private String name;
    private String email;
    private String phone;
    private SharedPreferences sp;
    private Spinner sp_firm_info;
    private SharedPreferences.Editor editor;
    private String unique;
    String Login;
    private RequestParams rp;
    private String leixing;
    private String mingcheng;
    private String yewu;
    private RegisterFirmActivityPresenter presenter;
    private StringBuilder sb;
    private String leixingLevel;
    private String naShuiRenLevel;
    private String picturePath;
    private String value;
    private String picturePath1;
    private String picturePath2;
    private String picturePath3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_firm);
        sp_firm_info = (Spinner) findViewById(R.id.sp_firm_info);

        title = "注册账户";
        String add = "新建";
        title_Bar(title);
        initList(add);
        Bundle bundle = getIntent().getExtras();
        account = bundle.getString("account");
        pass = bundle.getString("pass");
        repass = bundle.getString("repass");
        name = bundle.getString("name");
        email = bundle.getString("email");
        phone = bundle.getString("phone");
        String RegisterUri = SuMaoConstant.SUMAO_IP+"/rest/model/atg/store/profile/RegistrationActor/createUser";
        rp = new RequestParams(RegisterUri);
        rp.setConnectTimeout(30*1000);
        bt_save_register_value = (Button) findViewById(R.id.bt_save_register_value);

        bt_save_register_value.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerMethod();

            }
        });
    }

    private void registerMethodW() {
        rp.setMultipart(true);
        rp.addParameter("cl_mingcheng",mingcheng);//企业名称
        rp.addParameter("cl_yewu",yewu);//业务部门（可空）
        rp.addParameter("province",sb.substring(0,4));//省
        rp.addParameter("city",sb.substring(0,6));//市
        rp.addParameter("county",sb.toString());//县
        rp.addParameter("cl_dizhi",list.get(5).get("right").toString().trim());//详细地址
        rp.addParameter("cl_chuanzhen",list.get(6).get("right").toString().trim());//传真
        rp.addParameter("cl_zhengjian","4");//企业注册证件
        rp.addParameter("cl_nashuiren",naShuiRenLevel);//纳税人类型
        rp.addParameter("cl_leixing",leixingLevel);//企业类型
        if (picturePath!=null){
            rp.addParameter("cl_zhizhaoimage",new File(picturePath));//营业执照图片路劲
        }else {
            rp.addParameter("cl_zhizhaoimage",new File(picturePath1));
        }
        rp.addParameter("cl_zhizhaoimage",new File(picturePath2));//组织机构代码图片路劲
        rp.addParameter("cl_jigou", list1.get(1).get("right").toString().trim());//组织机构代码
        rp.addParameter("cl_shuiwuimage",new File(picturePath3));//税务登记号图片路劲
        rp.addParameter("cl_shuiwu", list1.get(2).get("right").toString().trim());//税务登记号
        rp.addParameter("cl_nashuirenimage",new File(picturePath1));//纳税人图片路劲
        rp.addParameter("cl_zhizhao", list1.get(3).get("right").toString().trim());//企业营业执照
        rp.addParameter("cl_login",account);//登录账号
        rp.addParameter("cl_password",pass);//密码
        rp.addParameter("cl_confirmPassword",repass);//确认密码
        rp.addParameter("cl_firstName",name);//姓名
        rp.addParameter("cl_email",email);//邮箱
        rp.addParameter("cl_mobilePhone",phone);//电话号码
        rp.addParameter("cl_applyType","8");//申请成为(买方)
        rp.addParameter("cl_entName",list.get(7).get("right").toString().trim());//企业法人
        rp.addParameter("cl_taxNum",list.get(8).get("right").toString().trim());//税号
        sp = getSharedPreferences("sumao", Activity.MODE_PRIVATE);
        String unique123 = sp.getString("unique", "");
        rp.addParameter("_dynSessConf", unique123);
         // String deviceIntoTime=deviceIntoTime.
        try {
            rp.addBodyParameter("deviceIntoTime", URLEncoder.encode(rp.toString(), "utf-8"));
            Log.d("bianma",rp.toString());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        x.http().request(HttpMethod.POST, rp, new Callback.CacheCallback<String>() {
            @Override
            public void onSuccess(String result) {
                LogUtils.log("onSuccess------->"+result+"");
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                LogUtils.log("Error------->"+ex.toString()+"");
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }

            @Override
            public boolean onCache(String result) {
                return false;
            }
        });
    }

    private void registerMethod() {
        String uri = SuMaoConstant.SUMAO_IP+"/rest/model/atg/store/profile/RegistrationActor/userVerify";
        RequestParams rp1 = new RequestParams(uri);
        rp1.addParameter("type", "mobile");
        rp1.addParameter("attr", phone);

        x.http().post(rp1, new Callback.CommonCallback<Boolean>() {
            @Override
            public void onSuccess(Boolean result) {
                if (result) {
                    Log.d("可以注册,终于可以了", "可以注册");

                   // rp.addParameter("cl_mingcheng", list.get(2).get("right").toString());
                    rp.addParameter("cl_mingcheng", "北京四联创业集团");
                    rp.addParameter("cl_yewu", list.get(3).get("right").toString());
                    rp.addParameter("province", "1414");
                    rp.addParameter("city", "141410");
                    rp.addParameter("county", "14141029");
                    //地址这里有问题
                    rp.addParameter("cl_dizhi", list.get(3).get("right").toString());
                    rp.addParameter("cl_chuanzhen", list.get(5).get("right").toString());
                    rp.addParameter("cl_entName", list.get(6).get("right").toString());
                    rp.addParameter("cl_taxNum", list.get(7).get("right").toString());


     /*
                    if (sp_firm_info.getClipBounds(0)=="三证独立")
                                    rp.addParameter("cl_entName","张三");
                                    rp.addParameter("cl_taxNum","217391472093417243");
                                    sp_firm_info.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                        @Override
                                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                            if (sp_firm_info.getItemAtPosition(0).toString().equals("三证独立")){
                                                rp.addParameter("cl_zhengjian",4);
                                                rp.addParameter("cl_zhizhao",list1.get(1).get("right").toString());
                                                rp.addParameter("cl_jigou",list1.get(2).get("right").toString());
                                                rp.addParameter("cl_shuiwu",list1.get(3).get("right").toString());
                                                if (list1.get(0).get("right").toString().equals("一般纳税人")){
                                                    rp.addParameter("cl_nashuiren",6);
                                                }else{
                                                    rp.addParameter("cl_nashuiren",7);
                                                }
                                            }else{
                                                rp.addParameter("cl_zhengjian",5);
                                                rp.addParameter("cl_zhizhao","0928201347189232203334");
//                                       rp.addParameter("cl_zhizhao",list1.get(0).get("right").toString());
                                                if (list1.get(1).get("right").toString().equals("一般纳税人")){
                                                    rp.addParameter("cl_nashuiren",6);
                                                }else{
                                                    rp.addParameter("cl_nashuiren",7);
                                                }
                                            }
                                        }
                                    });
                                   if (sp_firm_info.getItemAtPosition(0).toString().equals("三证独立")){
                                       rp.addParameter("cl_zhengjian",4);
                                       rp.addParameter("cl_zhizhao",list1.get(1).get("right").toString());
                                       rp.addParameter("cl_jigou",list1.get(2).get("right").toString());
                                       rp.addParameter("cl_shuiwu",list1.get(3).get("right").toString());
                                       if (list1.get(0).get("right").toString().equals("一般纳税人")){
                                           rp.addParameter("cl_nashuiren",6);
                                       }else{
                                           rp.addParameter("cl_nashuiren",7);
                                       }

                                   }else{
                                       rp.addParameter("cl_zhengjian",5);
                                       rp.addParameter("cl_zhizhao","0928201347189232203334");
//                                       rp.addParameter("cl_zhizhao",list1.get(0).get("right").toString());
                                       if (list1.get(1).get("right").toString().equals("一般纳税人")){
                                           rp.addParameter("cl_nashuiren",6);
                                       }else{
                                           rp.addParameter("cl_nashuiren",7);
                                       }

                                   }
     */

                    if (list.get(1).get("right").toString().equals("贸易商")) {
                        rp.addParameter("cl_leixing", 1);
                    } else if (list.get(1).get("right").toString().equals("工贸一体")) {
                        rp.addParameter("cl_leixing", 2);
                    } else if (list.get(1).get("right").toString().equals("生产商")) {
                        rp.addParameter("cl_leixing", 3);
                    }
                    rp.addParameter("cl_shuiwuimage", "/mnt/docs/100.jpg");
                    rp.addParameter("cl_jigouimage", "/mnt/docs/100.jpg");
                    rp.addParameter("cl_zhizhaoimage", "/mnt/docs/100.jpg");
                    rp.addParameter("cl_nashuirenimage", "/mnt/docs/100.jpg");
                    rp.addParameter("cl_login", account);
                    rp.addParameter("cl_password", pass);
                    rp.addParameter("cl_confirmPassword", repass);
                    try {
                        rp.addParameter("cl_firstName", new String(name.getBytes("UTF-8"),"UTF-8") );
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                    rp.addParameter("cl_email", email);
                    rp.addParameter("cl_mobilePhone", phone);
                    rp.addParameter("cl_applyType", 8);
                    sp = getSharedPreferences("sumao", Activity.MODE_PRIVATE);
                    String unique123 = sp.getString("unique", "");
                    rp.addParameter("_dynSessConf", unique123);
                    Log.d("unique", unique123);
                    Log.d("传的值", rp + "");
                    rp.getCharset();
                    try {
                        rp.addBodyParameter("deviceIntoTime", URLEncoder.encode(rp.toString(), "utf-8"));
                        Log.d("bianma",rp.toString());
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                    x.http().post(rp, new CommonCallback<String>() {
                        @Override
                        public void onSuccess(String result) {
                            Log.d("注册", "" + result);
                            //Toast.makeText(RegisterFirmActivity.this, "注册结果返回值", Toast.LENGTH_SHORT).show();
                            if (result.contains("success")) {
                                Intent intent = new Intent(RegisterFirmActivity.this, LoginUserActivity.class);
                                startActivity(intent);
                            } else {
                                Log.d("注册失败11", "" + result);
                                try {
                                    Log.d("result", result);
                                    String Exceptions = "";

                                    JSONObject object = new JSONObject(result);
                                    Exceptions = object.getString("formExceptions");

                                    // Log.d("json解析内容",Exceptions);
                                    JSONArray array = new JSONArray(Exceptions);
                                    JSONObject json = array.getJSONObject(0);
                                    String exception = json.getString("localizedMessage");
                                    // Log.d("exception",exception);
                                    Toast.makeText(RegisterFirmActivity.this, "" + exception, Toast.LENGTH_LONG).show();
                                } catch (Exception e) {
                                    //Log.d("解析失败","解析失败");
                                    e.printStackTrace();
                                }

                            }
                        }

                        @Override
                        public void onError(Throwable ex, boolean isOnCallback) {

                        }

                        @Override
                        public void onCancelled(CancelledException cex) {

                        }

                        @Override
                        public void onFinished() {

                        }
                    });
                } else {
                    Toast.makeText(RegisterFirmActivity.this, "该用户已经注册", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }


    public void initList(String add) {
        lvupdate_firm_info = (CustomListView) findViewById(R.id.lvUpdate_firm_info);
        list = new ArrayList<Map<String, Object>>();
        if (add.equals("新建")) {
            Map<String, Object> map1 = new Hashtable<String, Object>();
            map1.put("center", "填写企业信息");
            list.add(map1);
            //不从网络获取第二个参数
        }

        Map<String, Object> map2 = new Hashtable<String, Object>();
        map2.put("left", "企业类型");
        map2.put("right", "");
        map2.put("icon", R.mipmap.my_sumao_iv_order);
        list.add(map2);
        Map<String, Object> map3 = new Hashtable<String, Object>();
        map3.put("left", "企业名称");
        map3.put("right", "");
        map3.put("icon", R.mipmap.my_sumao_iv_order);
        list.add(map3);
        Map<String, Object> map4 = new Hashtable<String, Object>();
        map4.put("left", "业务部门");
        map4.put("right", "");
        map4.put("icon", R.mipmap.my_sumao_iv_order);
        list.add(map4);
        Map<String, Object> map5 = new Hashtable<String, Object>();
        map5.put("left", "办公地区");
        map5.put("right", "");
        map5.put("icon", R.mipmap.my_sumao_iv_order);
        list.add(map5);
        Map<String, Object> map9 = new Hashtable<String, Object>();
        map9.put("left", "办公地址");
        map9.put("right", "");
        map9.put("icon", R.mipmap.my_sumao_iv_order);
        list.add(map9);
        Map<String, Object> map6 = new Hashtable<String, Object>();
        map6.put("left", "传真号");
        map6.put("right", "");
        map6.put("icon", R.mipmap.my_sumao_iv_order);
        list.add(map6);
        Map<String, Object> map7 = new Hashtable<String, Object>();
        map7.put("left", "企业法人");
        map7.put("right", "");
        map7.put("icon", R.mipmap.my_sumao_iv_order);
        list.add(map7);
        Map<String, Object> map8 = new Hashtable<String, Object>();
        map8.put("left", "税号");
        map8.put("right", "");
        map8.put("icon", R.mipmap.my_sumao_iv_order);
        list.add(map8);

        adapter = new SimpleAdapter(RegisterFirmActivity.this, list, R.layout.item_firm_info, new String[]{"left", "center", "right", "icon"}, new int[]{R.id.tv_firm_info, R.id.tvTitle_firm_info, R.id.tvValue_firm_info, R.id.ivMore_firm_info});
        lvupdate_firm_info.setAdapter(adapter);
        Log.d("list.size", "" + list.size());

        lvupdate_firm_info.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 1) {
                    Intent intent = new Intent(RegisterFirmActivity.this, FirmInfoTypeActivity.class);
                    intent.putExtra("title", list.get(position).get("left").toString());
                    intent.putExtra("number", position);
                    startActivityForResult(intent, position);
                } else if (position == 3 || position == 2 || position == 5 || position == 6 || position == 7||position==8) {
                    if (position == 5&& (list.get(4).get("right").toString().trim()==null||list.get(4).get("right").toString().trim().isEmpty()))
                    {
                        Toast.makeText(RegisterFirmActivity.this,"请填写办公地区",Toast.LENGTH_SHORT).show();
                    }else{
                        Intent intent = new Intent(RegisterFirmActivity.this, RegisterValueActivity.class);
                        intent.putExtra("title", list.get(position).get("left").toString());
                        intent.putExtra("content", list.get(position).get("right").toString());
                        startActivityForResult(intent, position);
                    }

                } else if (position == 4) {
                    Intent intent = new Intent();
                    intent.putExtra("className","RegisterFirmActivity");
                    intent.setClass(RegisterFirmActivity.this, SelectProvinceArea.class);
                    startActivityForResult(intent,position);

                }
            }
        });

        lvupdate_firm_info_two = (ListView) findViewById(R.id.lvUpdate_firm_info_two);
        list1 = new ArrayList<Map<String, Object>>();
        sp_firm_info = (Spinner) findViewById(R.id.sp_firm_info);
        Map<String, Object> map12 = new Hashtable<String, Object>();
        map12.put("left", "统一社会信用代码");
        map12.put("right", "");
        map12.put("icon", R.mipmap.my_sumao_iv_order);
        list1.add(map12);
        Map<String, Object> map11 = new Hashtable<String, Object>();
        map11.put("left", "纳税人类型");
        map11.put("right", "");
        map11.put("icon", R.mipmap.my_sumao_iv_order);
        list1.add(map11);
        adapter1 = new SimpleAdapter(this, list1, R.layout.item_firm_info, new String[]{"left", "center", "right", "icon"}, new int[]{R.id.tv_firm_info, R.id.tvTitle_firm_info, R.id.tvValue_firm_info, R.id.ivMore_firm_info});
        lvupdate_firm_info_two.setAdapter(adapter1);

        sp_firm_info.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (sp_firm_info.getItemAtPosition(position).toString().equals("三证独立")) {
                    if (list1.size() == 2) {

                        list1.remove(0);
                        Map<String, Object> map8 = new Hashtable<String, Object>();
                        map8.put("left", "企业执照号");
                        map8.put("right", "");
                        map8.put("icon", R.mipmap.my_sumao_iv_order);
                        list1.add(map8);
                        Map<String, Object> map9 = new Hashtable<String, Object>();
                        map9.put("left", "组织机构代码");
                        map9.put("right", "");
                        map9.put("icon", R.mipmap.my_sumao_iv_order);
                        list1.add(map9);
                        Map<String, Object> map10 = new Hashtable<String, Object>();
                        map10.put("left", "税务登记号");
                        map10.put("right", "");
                        map10.put("icon", R.mipmap.my_sumao_iv_order);
                        list1.add(map10);

                        adapter1 = new SimpleAdapter(RegisterFirmActivity.this, list1, R.layout.item_firm_info, new String[]{"left", "center", "right", "icon"}, new int[]{R.id.tv_firm_info, R.id.tvTitle_firm_info, R.id.tvValue_firm_info, R.id.ivMore_firm_info});
                        lvupdate_firm_info_two.setAdapter(adapter1);
                    }
                    Toast.makeText(RegisterFirmActivity.this, "" + list1.size(), Toast.LENGTH_SHORT).show();
                    //把三证合一传的参数删除掉
                    rp.removeParameter("cl_zhengjian");
                    rp.removeParameter("cl_zhizhao");
                    rp.removeParameter("cl_nashuiren");
                    //添加三证独立时需要的参数
                    rp.addParameter("cl_zhengjian", 4);
//                    rp.addParameter("cl_zhizhao",list1.get(1).get("right").toString());
//                    rp.addParameter("cl_jigou",list1.get(2).get("right").toString());
//                    rp.addParameter("cl_shuiwu",list1.get(3).get("right").toString());
                    rp.addParameter("cl_zhizhao", "0928201347189232203334");
                    rp.addParameter("cl_jigou", "0928201347189232203334");
                    rp.addParameter("cl_shuiwu", "0928201347189232203334");
                    if (list1.get(0).get("right").toString().equals("一般纳税人")) {
                        rp.addParameter("cl_nashuiren", 6);
                    } else {
                        rp.addParameter("cl_nashuiren", 7);
                    }
                    Log.d("nihao", rp + "");
                } else if (sp_firm_info.getItemAtPosition(position).toString().equals("三证合一")) {
                    if (list1.size() > 2) {
                        for (int i = 0; i < 4; i++) {
                            list1.remove(0);
                            //   list1.remove(1);
                        }
                        Map<String, Object> map12 = new Hashtable<String, Object>();
                        map12.put("left", "统一社会信用代码");
                        map12.put("right", "");
                        map12.put("icon", R.mipmap.my_sumao_iv_order);
                        list1.add(map12);
                        Map<String, Object> map11 = new Hashtable<String, Object>();
                        map11.put("left", "纳税人类型");
                        map11.put("right", "");
                        map11.put("icon", R.mipmap.my_sumao_iv_order);
                        list1.add(map11);
                        adapter1 = new SimpleAdapter(RegisterFirmActivity.this, list1, R.layout.item_firm_info, new String[]{"left", "center", "right", "icon"}, new int[]{R.id.tv_firm_info, R.id.tvTitle_firm_info, R.id.tvValue_firm_info, R.id.ivMore_firm_info});
                        lvupdate_firm_info_two.setAdapter(adapter1);

                    }
                    if (rp.getUri().contains("cl_shuiwu")) {
                        Toast.makeText(RegisterFirmActivity.this, "由三证独立变换而来", Toast.LENGTH_SHORT).show();
                    }
                    //三证合一
                    rp.addParameter("cl_zhengjian", 5);
                    rp.addParameter("cl_zhizhao", "0928201347189232203334");
//                                       rp.addParameter("cl_zhizhao",list1.get(0).get("right").toString());
                    if (list1.get(1).get("right").toString().equals("一般纳税人")) {
                        rp.addParameter("cl_nashuiren", 6);
                    } else {
                        rp.addParameter("cl_nashuiren", 7);
                    }
                    Log.d("nihao", rp + "");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(RegisterFirmActivity.this, "请选择", Toast.LENGTH_SHORT).show();
            }
        });

        lvupdate_firm_info_two.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (list1.size() == 2) {
                    if (position == 0) {
                        Intent intent = new Intent(RegisterFirmActivity.this, FirmInfoPictureActivity.class);
                        intent.putExtra("name", list1.get(0).get("left").toString());
                        intent.putExtra("number", 10);
                        //startActivity(intent);
                        startActivityForResult(intent, 10);
                    } else if (position == 1) {
                        Intent intent = new Intent(RegisterFirmActivity.this, FirmInfoTypeActivity.class);
                        intent.putExtra("title", list1.get(1).get("left").toString());
                        //startActivity(intent);
                        intent.putExtra("number", 21);
                        startActivityForResult(intent, 21);
                    }

                } else if (list1.size() == 4) {
                    if (position == 0) {
                        Intent intent = new Intent(RegisterFirmActivity.this, FirmInfoTypeActivity.class);
                        intent.putExtra("title", list1.get(0).get("left").toString());
                        intent.putExtra("number", 20);

                        startActivityForResult(intent, 20);
                    } else if (position == 1) {
                        Intent intent = new Intent(RegisterFirmActivity.this, FirmInfoPictureActivity.class);
                        intent.putExtra("name", list1.get(0).get("left").toString());
                        intent.putExtra("number", 11);
                        startActivityForResult(intent, 11);
                    } else if (position == 2) {
                        Intent intent = new Intent(RegisterFirmActivity.this, FirmInfoPictureActivity.class);
                        intent.putExtra("name", list1.get(0).get("left").toString());
                        intent.putExtra("number", 12);
                        startActivityForResult(intent, 12);
                    } else if (position == 3) {
                        Intent intent = new Intent(RegisterFirmActivity.this, FirmInfoPictureActivity.class);
                        intent.putExtra("name", list1.get(0).get("left").toString());
                        intent.putExtra("number", 13);
                        startActivityForResult(intent, 13);
                    }

                }
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
//            企业类型
            case 1:
                leixing = data.getStringExtra("name");
                Log.d("nameValue", name);
                leixingLevel = data.getStringExtra("level");
                list.get(1).put("right", leixing);
                adapter.notifyDataSetChanged();
                break;
            //            企业名称
            case 2:
                mingcheng = data.getStringExtra("name");
                list.get(2).put("right", mingcheng);
                adapter.notifyDataSetChanged();
                break;
            //            业务部门
            case 3:
                yewu = data.getStringExtra("name");
                list.get(3).put("right", yewu);
                adapter.notifyDataSetChanged();
                break;
            //            办公地址
            /*case 4:
                String address=data.getStringExtra("address");
                list.get(4).put("right",address);
                adapter.notifyDataSetChanged();
                break;*/
            case 5:
                String number = data.getStringExtra("name");
                list.get(5).put("right", number);
                adapter.notifyDataSetChanged();
                break;
            case 6:
                String name2 = data.getStringExtra("name");
                list.get(6).put("right", name2);
                adapter.notifyDataSetChanged();
                break;
            case 7:
                String name3 = data.getStringExtra("name");
                list.get(7).put("right", name3);
                adapter.notifyDataSetChanged();
                break;
            case 8:
                String name8 = data.getStringExtra("name");
                list.get(8).put("right", name8);
                adapter.notifyDataSetChanged();
                break;

//            三证合一，统一社会信用代码
            case 10:
                value = data.getStringExtra("value");
                picturePath = data.getStringExtra("picturePath");
                list1.get(0).put("right", value);
                adapter1.notifyDataSetChanged();
                break;
//            三证独立，营业执照号
            case 11:
                String value1 = data.getStringExtra("value");
                picturePath1 = data.getStringExtra("picturePath");
                list1.get(1).put("right", value1);
                adapter1.notifyDataSetChanged();
                break;
//            三证独立，组织机构代码
            case 12:
                String value2 = data.getStringExtra("value");
                picturePath2 = data.getStringExtra("picturePath");
                list1.get(2).put("right", value2);
                adapter1.notifyDataSetChanged();
                break;
//            三证独立，税务登记号
            case 13:
                String value3 = data.getStringExtra("value");
                picturePath3 = data.getStringExtra("picturePath");
                list1.get(3).put("right", value3);
                adapter1.notifyDataSetChanged();
                break;
            case 20:
                String value4 = data.getStringExtra("name");
                list1.get(0).put("right", value4);
                adapter1.notifyDataSetChanged();
                break;
            case 21:
//                纳税人类型
                String value5 = data.getStringExtra("name");
                naShuiRenLevel = data.getStringExtra("level");
                LogUtils.log("naShuiRenLevel---------->"+naShuiRenLevel);
                Log.d("nnnnnn", value5);
                list1.get(1).put("right", value5);
                adapter1.notifyDataSetChanged();
                break;


        }
    }


    public void title_Bar(String title) {
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
        tv_title_bar_title.setText(title);
        tv_title_bar_title.setTextColor(Color.WHITE);
        iv_title_bar_back.setVisibility(View.VISIBLE);
        layoutTop = (RelativeLayout) findViewById(R.id.layoutTop_firm_info_update);
        layoutTop.setBackgroundColor(getResources().getColor(R.color.textColor_expandable_listview_show));

        iv_title_bar_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RegisterFirmActivity.this.finish();
            }
        });
    }

    @Override
    protected void onRestart() {
        super.onRestart();

    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);

    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        String address = getIntent().getStringExtra("address");
        LogUtils.log("address------->RegisterFirmActivity:"+address);
        sb = new StringBuilder();
        sb.append(address);
        LogUtils.log("StringBuilder------->RegisterFirmActivity:"+ sb.toString());
        presenter = new RegisterFirmActivityPresenter(this);
        LogUtils.log("StringBuilder------->RegisterFirmActivity:"+ sb.substring(0,4)+"+"+ sb.substring(0,6)+"+"+ sb.toString());
        presenter.setDetailAddress(sb.substring(0,4), sb.substring(0,6), sb.toString());

    }

    @Override
    public void setStringInText(String address) {
        LogUtils.log("address------->RegisterFirmActivity:"+address);
        list.get(4).put("right",address);
        adapter.notifyDataSetChanged();
    }
}
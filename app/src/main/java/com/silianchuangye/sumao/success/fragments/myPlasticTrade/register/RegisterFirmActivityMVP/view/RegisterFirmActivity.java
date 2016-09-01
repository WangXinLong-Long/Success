package com.silianchuangye.sumao.success.fragments.myPlasticTrade.register.RegisterFirmActivityMVP.view;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.firmInfomation.FirmInfoPicture.FirmInfoPictureMVP.view.FirmInfoPictureActivity;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.firmInfomation.FirmInfoTypeActivity;

import com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.receiptAddress.SelectProvinceAreaMVP.view.SelectProvinceArea;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.login.LoginUserActivity;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.register.RegisterFirmActivityMVP.presenter.RegisterFirmActivityPresenter;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.register.RegisterPicture;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.register.RegisterValueActivity;
import com.silianchuangye.sumao.success.utils.LogUtils;
import com.silianchuangye.sumao.success.utils.SuMaoConstant;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.File;
import java.util.List;
import java.util.Map;

public class RegisterFirmActivity extends AppCompatActivity implements IRegisterFirmActivityView, View.OnClickListener {
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
    private String value;
    private String picturePath1;
    private String picturePath2;
    private String picturePath3;
    private String zhuce_leixing;
    private String zhuce_leixingLevel;
    private Map<String, Object> map13, map14, map15, map12;
    private String qiYeZhuCeZhenJianLevel;
    private RelativeLayout type_of_enterprise_rl;
    private RelativeLayout enterprise_name_rl;
    private RelativeLayout business_department_rl;
    private RelativeLayout office_address_rl;
    private RelativeLayout detail_address_rl;
    private RelativeLayout fax_number_rl;
    private RelativeLayout name_of_enterprise_legal_person_rl;
    private RelativeLayout enterprise_registration_certificate_rl;
    private RelativeLayout business_license_number_rl;
    private RelativeLayout organization_code_rl;
    private RelativeLayout tax_registration_number_rl;
    private RelativeLayout type_of_taxpayer_rl;
    private RelativeLayout application_to_become_a_plastic_trade_network_rl;
    private TextView type_of_enterprise_name;
    private TextView enterprise_name_name;
    private TextView business_department_name;
    private TextView office_address_name;
    private TextView detail_address_name;
    private TextView fax_number_name;
    private TextView name_of_enterprise_legal_person_name;
    private TextView enterprise_registration_certificate_name;
    private TextView business_license_number_name;
    private TextView organization_code_name;
    private TextView tax_registration_number_name;
    private TextView type_of_taxpayer_name;
    private TextView application_to_become_a_plastic_trade_network_name;
    private TextView type_of_enterprise_image;
    private TextView enterprise_name_image;
    private TextView business_department_image;
    private TextView office_address_image;
    private TextView detail_address_image;
    private TextView
            fax_number_image;
    private TextView name_of_enterprise_legal_person_image;
    private TextView enterprise_registration_certificate_image;
    private TextView business_license_number_image;
    private TextView organization_code_image;
    private TextView tax_registration_number_image;
    private TextView type_of_taxpayer_image;
    private TextView application_to_become_a_plastic_trade_network_image;
    private String nashuiren;
    private String applicationAsSM;
    private String applicationAsSMLevel;
    private RelativeLayout taxpayer_photo_rl;
    private String picturePath4;
    private TextView taxpayer_photo_name;
    private TextView taxpayer_photo_image;
    private RelativeLayout unified_social_credit_code_rl;
    private TextView unified_social_credit_coder_image;
    private TextView unified_social_credit_code_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_firm);
        sp_firm_info = (Spinner) findViewById(R.id.sp_firm_info);

        title = "注册账户";
        String add = "新建";
        title_Bar(title);
        initList();
        Bundle bundle = getIntent().getExtras();
        account = bundle.getString("account");
        Log.e("TAG","登陆---"+account);
        pass = bundle.getString("pass");
        repass = bundle.getString("repass");
        name = bundle.getString("name");
        email = bundle.getString("email");
        phone = bundle.getString("phone");
//        String RegisterUri = SuMaoConstant.SUMAO_IP + "/rest/model/atg/store/profile/RegistrationActor/createUser";
        String RegisterUri = SuMaoConstant.SUMAO_IP+"/rest/model/atg/store/profile/RegistrationActor/createUser" ;
        rp = new RequestParams(RegisterUri);
        rp.setConnectTimeout(30 * 1000);
        bt_save_register_value = (Button) findViewById(R.id.bt_save_register_value);

        bt_save_register_value.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtils.log("////企业注册证件-->" + zhuce_leixingLevel);
                LogUtils.log("////纳税人类型-->" + naShuiRenLevel);
                LogUtils.log("////企业类型-->" + leixingLevel);
                LogUtils.log("picturePath1-->" + picturePath1);
                LogUtils.log("picturePath2-->" + picturePath2);
                LogUtils.log("picturePath3-->" + picturePath3);
                registerMethodW();

            }
        });
    }

    private void initList() {
        type_of_enterprise_rl = ((RelativeLayout) findViewById(R.id.type_of_enterprise_rl));
        enterprise_name_rl = ((RelativeLayout) findViewById(R.id.enterprise_name_rl));
        business_department_rl = ((RelativeLayout) findViewById(R.id.business_department_rl));
        office_address_rl = ((RelativeLayout) findViewById(R.id.office_address_rl));
        detail_address_rl = ((RelativeLayout) findViewById(R.id.detail_address_rl));
        fax_number_rl = ((RelativeLayout) findViewById(R.id.fax_number_rl));
        name_of_enterprise_legal_person_rl = ((RelativeLayout) findViewById(R.id.name_of_enterprise_legal_person_rl));
        enterprise_registration_certificate_rl = ((RelativeLayout) findViewById(R.id.enterprise_registration_certificate_rl));
        business_license_number_rl = ((RelativeLayout) findViewById(R.id.business_license_number_rl));
        organization_code_rl = ((RelativeLayout) findViewById(R.id.organization_code_rl));
        tax_registration_number_rl = ((RelativeLayout) findViewById(R.id.tax_registration_number_rl));
        type_of_taxpayer_rl = ((RelativeLayout) findViewById(R.id.type_of_taxpayer_rl));
        taxpayer_photo_rl = ((RelativeLayout) findViewById(R.id.taxpayer_photo_rl));
        application_to_become_a_plastic_trade_network_rl = ((RelativeLayout) findViewById(R.id.application_to_become_a_plastic_trade_network_rl));
        unified_social_credit_code_rl = ((RelativeLayout) findViewById(R.id.unified_social_credit_code_rl));

//        listener
        type_of_enterprise_rl.setOnClickListener(this);
        enterprise_name_rl.setOnClickListener(this);
        business_department_rl.setOnClickListener(this);
        office_address_rl.setOnClickListener(this);
        detail_address_rl.setOnClickListener(this);
        fax_number_rl.setOnClickListener(this);
        name_of_enterprise_legal_person_rl.setOnClickListener(this);
        enterprise_registration_certificate_rl.setOnClickListener(this);
        business_license_number_rl.setOnClickListener(this);
        organization_code_rl.setOnClickListener(this);
        tax_registration_number_rl.setOnClickListener(this);
        type_of_taxpayer_rl.setOnClickListener(this);
        taxpayer_photo_rl.setOnClickListener(this);
        application_to_become_a_plastic_trade_network_rl.setOnClickListener(this);
        unified_social_credit_code_rl.setOnClickListener(this);
//        textview
//        企业类型
        type_of_enterprise_name = ((TextView) findViewById(R.id.type_of_enterprise_name));
//        企业名称
        enterprise_name_name = ((TextView) findViewById(R.id.enterprise_name_name));
//        业务部门
        business_department_name = ((TextView) findViewById(R.id.business_department_name));
//        办公地址
        office_address_name = ((TextView) findViewById(R.id.office_address_name));
//        详细地址
        detail_address_name = ((TextView) findViewById(R.id.detail_address_name));
//        传真号
        fax_number_name = ((TextView) findViewById(R.id.fax_number_name));
//        企业法人姓名
        name_of_enterprise_legal_person_name = ((TextView) findViewById(R.id.name_of_enterprise_legal_person_name));
//        企业注册证件
        enterprise_registration_certificate_name = ((TextView) findViewById(R.id.enterprise_registration_certificate_name));
//        营业执照号
        business_license_number_name = ((TextView) findViewById(R.id.business_license_number_name));
//        组织机构代码
        organization_code_name = ((TextView) findViewById(R.id.organization_code_name));
//        税务登记号
        tax_registration_number_name = ((TextView) findViewById(R.id.tax_registration_number_name));
//        纳税人类型
        type_of_taxpayer_name = ((TextView) findViewById(R.id.type_of_taxpayer_name));
//        纳税人图片
        taxpayer_photo_name = ((TextView) findViewById(R.id.taxpayer_photo_name));
//        申请成为塑贸网
        application_to_become_a_plastic_trade_network_name = ((TextView) findViewById(R.id.application_to_become_a_plastic_trade_network_name));
//        统一社会信用代码
        unified_social_credit_code_name = ((TextView) findViewById(R.id.unified_social_credit_code_name));

//         返回的值
        type_of_enterprise_image = ((TextView) findViewById(R.id.type_of_enterprise_image)); //        企业类型
        enterprise_name_image = ((TextView) findViewById(R.id.enterprise_name_image));//        企业名称
        business_department_image = ((TextView) findViewById(R.id.business_department_image));//        业务部门
        office_address_image = ((TextView) findViewById(R.id.office_address_image));//        办公地址
        detail_address_image = ((TextView) findViewById(R.id.detail_address_image));//        详细地址
        fax_number_image = ((TextView) findViewById(R.id.fax_number_image));//        传真号
        name_of_enterprise_legal_person_image = ((TextView) findViewById(R.id.name_of_enterprise_legal_person_image));//        企业法人姓名
        enterprise_registration_certificate_image = ((TextView) findViewById(R.id.enterprise_registration_certificate_image));//        企业注册证件
        business_license_number_image = ((TextView) findViewById(R.id.business_license_number_image));//        营业执照号
        organization_code_image = ((TextView) findViewById(R.id.organization_code_image));//        组织机构代码
        tax_registration_number_image = ((TextView) findViewById(R.id.tax_registration_number_image));//        税务登记号
        type_of_taxpayer_image = ((TextView) findViewById(R.id.type_of_taxpayer_image));//        纳税人类型
        taxpayer_photo_image = ((TextView) findViewById(R.id.taxpayer_photo_image));//        纳税人图片
        application_to_become_a_plastic_trade_network_image = ((TextView) findViewById(R.id.application_to_become_a_plastic_trade_network_image));//        申请成为塑贸网
        unified_social_credit_coder_image = ((TextView) findViewById(R.id.unified_social_credit_coder_image));//        统一社会信用代码


    }

    private void registerMethodW() {
        rp.setAsJsonContent(true);
        JSONObject jsonObject = new JSONObject();
        try {
        jsonObject.put("cl_mingcheng", enterprise_name_image.getText().toString().trim());//企业名称
            jsonObject.put("cl_yewu", business_department_image.getText().toString().trim());//业务部门（可空）
           jsonObject.put("province", sb.substring(0, 4));//省
            jsonObject.put("city", sb.substring(0, 6));//市
            jsonObject.put("county", sb.toString());//县
            jsonObject.put("cl_dizhi",detail_address_image.getText().toString().trim());//详细地址

            jsonObject.put("cl_chuanzhen", fax_number_image.getText().toString().trim());//传真
            jsonObject.put("cl_zhengjian", zhuce_leixingLevel);//企业注册证件
            jsonObject.put("cl_nashuiren", naShuiRenLevel);//纳税人类型
            jsonObject.put("cl_leixing", leixingLevel);//企业类型
            jsonObject.put("cl_zhizhao", business_license_number_image.getText().toString().trim());//企业营业执照
            jsonObject.put("cl_login", account);//登录账号
            jsonObject.put("cl_password", pass);//密码
            jsonObject.put("cl_confirmPassword", repass);//确认密码
            jsonObject.put("cl_firstName", name);//姓名
            jsonObject.put("cl_email", email);//邮箱
            jsonObject.put("cl_mobilePhone", phone);//电话号码
            jsonObject.put("cl_applyType", applicationAsSMLevel);//申请成为(买方)
            jsonObject.put("cl_entName", name_of_enterprise_legal_person_image.getText().toString().trim());//企业法人
            jsonObject.put("cl_taxNum", tax_registration_number_image.getText().toString().trim());//税号
            sp = getSharedPreferences("sumao", Activity.MODE_PRIVATE);
            String unique123 = sp.getString("unique", "");
            jsonObject.put("_dynSessConf", unique123);
            jsonObject.put("cl_zhizhaoimage", picturePath1);//税号
            jsonObject.put("cl_jigouimage", picturePath2);//税号
            jsonObject.put("cl_shuiwuimage", picturePath3);//税号
            jsonObject.put("cl_nashuirenimage", picturePath4);//税号

        } catch (JSONException e) {
            e.printStackTrace();
        }
        rp.setBodyContent(jsonObject.toString());



//        LogUtils.log("json---------->"+jsonObject.toString()+"<-------json");
        LogUtils.log("picturePath1------->"+picturePath1);

        Log.e("TAG","rp------"+rp);
        x.http().post(rp, new Callback.CacheCallback<String>() {

            @Override
            public void onSuccess(String result) {
                Log.e("TAG","result------"+result);
               Intent intent = new Intent(RegisterFirmActivity.this, LoginUserActivity.class);
                startActivity(intent);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                LogUtils.log("注册Error------->" + ex.toString() + "");

            }

            @Override
            public void onCancelled(CancelledException cex) {
                LogUtils.log("注册CancelledException------->" + cex.toString() + "");
                LogUtils.log("注册CancelledException------->" + cex.toString() + "");
            }

            @Override
            public void onFinished() {
                LogUtils.log("注册onFinished------->" + "");
            }

            @Override
            public boolean onCache(String result) {
                return false;
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
                type_of_enterprise_image.setText(leixing);
                break;
            //            企业名称
            case 2:
                mingcheng = data.getStringExtra("name");
                enterprise_name_image.setText(mingcheng);
                break;
            //            业务部门
            case 3:
                yewu = data.getStringExtra("name");
                LogUtils.log("number3--->");
                business_department_image.setText(yewu);
                break;

            case 5://详细地址
                String detailAddress = data.getStringExtra("name");
                LogUtils.log("number5--->" + detailAddress);
                detail_address_image.setText(detailAddress);
                break;
            case 6://传真号
                String name2 = data.getStringExtra("name");
                LogUtils.log("number6--->" + name2);
                fax_number_image.setText(name2);
                break;
            case 7://企业法人姓名
                String name3 = data.getStringExtra("name");
                LogUtils.log("number7--->" + name3);
                name_of_enterprise_legal_person_image.setText(name3);
                break;
            case 8://        企业注册证件
                zhuce_leixing = data.getStringExtra("name");
                LogUtils.log("number8--->"+zhuce_leixing);
                zhuce_leixingLevel = data.getStringExtra("level");
                LogUtils.log("number8--->"+zhuce_leixingLevel);
                enterprise_registration_certificate_image.setText(zhuce_leixing);
//                三证独立  4
                if (zhuce_leixingLevel=="4"){
                    unified_social_credit_code_rl.setVisibility(View.GONE);
                    business_license_number_rl.setVisibility(View.VISIBLE);
                    organization_code_rl.setVisibility(View.VISIBLE);
                    tax_registration_number_rl.setVisibility(View.VISIBLE);
                }else if(zhuce_leixingLevel=="5"){//三证合一  5
                    unified_social_credit_code_rl.setVisibility(View.VISIBLE);
                    business_license_number_rl.setVisibility(View.GONE);
                    organization_code_rl.setVisibility(View.GONE);
                    tax_registration_number_rl.setVisibility(View.GONE);
                }
                break;
            case 9: //营业执照号
                String value1 = data.getStringExtra("value");
                picturePath1 = data.getStringExtra("picturePath");
                LogUtils.log(" 营业执照号9---------->" + value1);
                business_license_number_image.setText(value1);
                break;

            case 10://组织机构代码
                String value2 = data.getStringExtra("value");
                LogUtils.log("10---------->" + value2);
                picturePath2 = data.getStringExtra("picturePath");
                organization_code_image.setText(value2);
                break;

            case 11://税务登记号
                String value3 = data.getStringExtra("value");
                LogUtils.log("11---------->" + value3);
                picturePath3 = data.getStringExtra("picturePath");
                tax_registration_number_image.setText(value3);
                break;

            case 12://                纳税人类型
                nashuiren = data.getStringExtra("name");
                LogUtils.log("number12--->"+nashuiren);
                naShuiRenLevel = data.getStringExtra("level");
                LogUtils.log("number12--->"+naShuiRenLevel);
                type_of_taxpayer_image.setText(nashuiren);

                break;

            case 13://纳税人照片
                String value4 = data.getStringExtra("value");
                LogUtils.log("13---------->" + value4);
                picturePath4 = data.getStringExtra("picturePath");
                LogUtils.log("picturePath4--->"+picturePath4);
                taxpayer_photo_image.setText(value4);
                break;

            case 14://申请成为塑贸网
                applicationAsSM = data.getStringExtra("name");
                LogUtils.log("number13--->"+applicationAsSM);
                applicationAsSMLevel = data.getStringExtra("level");
                LogUtils.log("number13--->"+ applicationAsSMLevel);
                application_to_become_a_plastic_trade_network_image.setText(applicationAsSM);
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
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        String address = getIntent().getStringExtra("address");
        LogUtils.log("address------->RegisterFirmActivity:" + address);
        sb = new StringBuilder();
        sb.append(address);
        LogUtils.log("StringBuilder------->RegisterFirmActivity:" + sb.toString());
        presenter = new RegisterFirmActivityPresenter(this);
        LogUtils.log("StringBuilder------->RegisterFirmActivity:" + sb.substring(0, 4) + "+" + sb.substring(0, 6) + "+" + sb.toString());
        presenter.setDetailAddress(sb.substring(0, 4), sb.substring(0, 6), sb.toString());

    }

    @Override
    public void setStringInText(String address) {
        LogUtils.log("address------->RegisterFirmActivity:" + address);
        office_address_image.setText(address);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.type_of_enterprise_rl://            企业类型
                intent.setClass(RegisterFirmActivity.this, FirmInfoTypeActivity.class);
                intent.putExtra("title", type_of_enterprise_name.getText().toString());
                startActivityForResult(intent, 1);
                break;

            case R.id.enterprise_name_rl: //        企业名称
                intent.setClass(RegisterFirmActivity.this, RegisterValueActivity.class);
                intent.putExtra("content",enterprise_name_image.getText().toString());
                intent.putExtra("title", enterprise_name_name.getText().toString());
                startActivityForResult(intent, 2);
                break;

            case R.id.business_department_rl://        业务部门
                intent.setClass(RegisterFirmActivity.this, RegisterValueActivity.class);
                intent.putExtra("content",business_department_image.getText().toString());
                intent.putExtra("title", business_department_name.getText().toString());
                startActivityForResult(intent, 3);
                break;

            case R.id.office_address_rl://        办公地址
                intent.putExtra("className", "RegisterFirmActivity");
                intent.setClass(RegisterFirmActivity.this, SelectProvinceArea.class);
                startActivityForResult(intent, 4);
                break;

            case R.id.detail_address_rl:// 详细地址
                String office_address = office_address_image.getText().toString();
                if (office_address.isEmpty() || office_address.equals("")) {
                    Toast.makeText(RegisterFirmActivity.this, "请填写办公地区", Toast.LENGTH_SHORT).show();
                } else {
                    intent.setClass(RegisterFirmActivity.this, RegisterValueActivity.class);
                    intent.putExtra("content",detail_address_image.getText().toString());
                    intent.putExtra("title", detail_address_name.getText().toString());
                    startActivityForResult(intent, 5);
                }
                break;

            case R.id.fax_number_rl://        传真号
                intent.setClass(RegisterFirmActivity.this, RegisterValueActivity.class);
                intent.putExtra("content",fax_number_image.getText().toString());
                intent.putExtra("title", fax_number_name.getText().toString());
                startActivityForResult(intent, 6);
                break;

            case R.id.name_of_enterprise_legal_person_rl://        企业法人姓名
                intent.setClass(RegisterFirmActivity.this, RegisterValueActivity.class);
                intent.putExtra("content",name_of_enterprise_legal_person_image.getText().toString());
                intent.putExtra("title", name_of_enterprise_legal_person_name.getText().toString());
                startActivityForResult(intent, 7);
                break;

            case R.id.enterprise_registration_certificate_rl://            企业注册证件
                intent.setClass(RegisterFirmActivity.this, FirmInfoTypeActivity.class);
                intent.putExtra("title", enterprise_registration_certificate_name.getText().toString());
                startActivityForResult(intent, 8);
                break;
            case R.id.business_license_number_rl://        营业执照号
                intent.setClass(RegisterFirmActivity.this, FirmInfoPictureActivity.class);
                intent.putExtra("name", business_license_number_name.getText().toString());
                intent.putExtra("number", 9);
                startActivityForResult(intent, 9);
                break;
            case R.id.organization_code_rl://        组织机构代码
                intent.setClass(RegisterFirmActivity.this, FirmInfoPictureActivity.class);
                intent.putExtra("name", organization_code_name.getText().toString());
                intent.putExtra("number", 10);
                startActivityForResult(intent, 10);
                break;
            case R.id.tax_registration_number_rl://        税务登记号
                intent.setClass(RegisterFirmActivity.this, FirmInfoPictureActivity.class);
                intent.putExtra("name", tax_registration_number_name.getText().toString());
                intent.putExtra("number", 11);
                startActivityForResult(intent, 11);
                break;

            case R.id.type_of_taxpayer_rl://            纳税人类型
                intent.setClass(RegisterFirmActivity.this, FirmInfoTypeActivity.class);
                intent.putExtra("title", type_of_taxpayer_name.getText().toString());
                startActivityForResult(intent, 12);
                break;

            case R.id.taxpayer_photo_rl://        纳税人图片
                intent.setClass(RegisterFirmActivity.this, RegisterPicture.class);
                intent.putExtra("name", organization_code_name.getText().toString());
                intent.putExtra("number", 13);
                startActivityForResult(intent, 13);

                break;
            case R.id.application_to_become_a_plastic_trade_network_rl://        申请成为塑贸网
                intent.setClass(RegisterFirmActivity.this, FirmInfoTypeActivity.class);
                intent.putExtra("title", application_to_become_a_plastic_trade_network_name.getText().toString());
                startActivityForResult(intent, 14);
                break;

            default:
                break;
        }
    }
}
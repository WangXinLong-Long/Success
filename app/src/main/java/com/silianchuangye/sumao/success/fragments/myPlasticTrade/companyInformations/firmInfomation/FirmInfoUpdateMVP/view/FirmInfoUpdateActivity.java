package com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.firmInfomation.FirmInfoUpdateMVP.view;

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

import com.silianchuangye.sumao.success.MainActivity;
import com.silianchuangye.sumao.success.R;

import com.silianchuangye.sumao.success.custom.CustomListView;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.firmInfomation.FirmInfoPicture.FirmInfoPictureMVP.view.FirmInfoPictureActivity;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.firmInfomation.FirmInfoTypeActivity;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.firmInfomation.FirmInfoUpdateMVP.bean.FirmInfoUpdateActivityBean;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.firmInfomation.FirmInfoUpdateMVP.presenter.FirmInfoUpdatePresenter;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.receiptAddress.SelectProvinceAreaMVP.view.SelectProvinceArea;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.register.RegisterFirmActivityMVP.adapter.RegisterFirmListAdapter;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.register.RegisterFirmActivityMVP.model.RegisterFirmList;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.register.RegisterFirmActivityMVP.presenter.RegisterFirmActivityPresenter;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.register.RegisterPicture;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.register.RegisterValueActivity;
import com.silianchuangye.sumao.success.model.DifferentTypes;
import com.silianchuangye.sumao.success.model.EnterpriseInformation;
import com.silianchuangye.sumao.success.utils.LogUtils;
import com.silianchuangye.sumao.success.utils.SuMaoConstant;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FirmInfoUpdateActivity extends AppCompatActivity implements View.OnClickListener,IFirmInfoUpdateView{

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
    private String leixing = "";
    private String mingcheng= "";
    private String yewu= "";
    private RegisterFirmActivityPresenter presenter;
    private StringBuilder sb  = new StringBuilder();
    private String leixingLevel= "";
    private String naShuiRenLevel= "";
    private String value;
    private String picturePath1 = "";
    private String picturePath2= "";
    private String picturePath3= "";
    private String zhuce_leixing;
    private String zhuce_leixingLevel = "";
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
    //    营业执照号
    private ListView business_license_number_rl;
    //      组织机构代码、
    private RelativeLayout organization_code_rl;
    //    税务登记号
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
    private String picturePath4 = "";
    private TextView taxpayer_photo_name;
    private TextView taxpayer_photo_image;
    private RelativeLayout unified_social_credit_code_rl;
    private TextView unified_social_credit_coder_image;
    private TextView unified_social_credit_code_name;
    private List<RegisterFirmList> registerFirmLists;
    private RegisterFirmListAdapter registerFirmListAdapter;
    private String value1=  "123654";
    private String value2 = "123654";
    private String value3 = "123654";
    private DifferentTypes value4;
    private Map<String, String> cl_leixing;
    private HashMap<String, String> cl_zhengjian;
    private HashMap<String, String> cl_applyType;
    private HashMap<String, String> cl_nashuiren;
    private FirmInfoUpdatePresenter firmInfoUpdatePresenter;
    private FirmInfoUpdateActivityBean firmInfoUpdateActivityBean;
    private String address;
    private String zhengjian;
    private ImageView enterprise_namee_iv;
    private String addressnumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        activity_register_firm
        setContentView(R.layout.activity_register_firm);
        firmInfoUpdatePresenter = new FirmInfoUpdatePresenter(this);
        Intent intent = getIntent();
        firmInfoUpdateActivityBean = (FirmInfoUpdateActivityBean) intent.getSerializableExtra("firmInfoUpdateActivityBean");
//        TODO
        addressnumber = intent.getStringExtra("addressnumber");
        address = intent.getStringExtra("address");
        leixing = intent.getStringExtra("leixing");
        zhengjian = intent.getStringExtra("zhengjian");
        nashuiren = intent.getStringExtra("nashuiren");
        LogUtils.log("address:"+address+"--leixing:"+leixing+"--zhengjian:"+zhengjian);

        sb = sb.append(addressnumber);
        title = "注册账户";
        String add = "新建";
        title_Bar(title);
        initList();
        setInfoInActivity(firmInfoUpdateActivityBean);
//        String RegisterUri = SuMaoConstant.SUMAO_IP + "/rest/model/atg/store/profile/RegistrationActor/createUser";
        String RegisterUri = SuMaoConstant.SUMAO_IP + "/rest/model/atg/store/profile/RegistrationActor/updateEnterprise";
        rp = new RequestParams(RegisterUri);
        rp.setConnectTimeout(30 * 1000);
        bt_save_register_value = (Button) findViewById(R.id.bt_save_register_value);
        bt_save_register_value.setVisibility(View.INVISIBLE);
        bt_save_register_value.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (enterprise_registration_certificate_image.getText().toString().equals("三证合一")&&registerFirmLists.get(0).getValue().equals("")){
                        Toast.makeText(FirmInfoUpdateActivity.this,"请上传证件",Toast.LENGTH_SHORT).show();
                }else if (enterprise_registration_certificate_image.getText().toString().equals("三证独立")&&
                        (registerFirmLists.get(0).getValue().equals("")||registerFirmLists.get(1).getValue().equals("")||registerFirmLists.get(2).getValue().equals(""))){
                    Toast.makeText(FirmInfoUpdateActivity.this,"请上传证件",Toast.LENGTH_SHORT).show();
                }else if (type_of_enterprise_image.getText().toString().trim().equals("")){
                    Toast.makeText(FirmInfoUpdateActivity.this,"请填写企业类型",Toast.LENGTH_SHORT).show();
                }else if (enterprise_name_image.getText().toString().trim().equals("")){
                    Toast.makeText(FirmInfoUpdateActivity.this,"请填写企业名称",Toast.LENGTH_SHORT).show();
                }else if (office_address_image.getText().toString().trim().equals("")){
                    Toast.makeText(FirmInfoUpdateActivity.this,"请填写办公地址",Toast.LENGTH_SHORT).show();
                }else if (detail_address_image.getText().toString().trim().equals("")){
                    Toast.makeText(FirmInfoUpdateActivity.this,"请填写详细地址",Toast.LENGTH_SHORT).show();
                }else if (name_of_enterprise_legal_person_image.getText().toString().trim().equals("")){
                    Toast.makeText(FirmInfoUpdateActivity.this,"请填写企业法人姓名",Toast.LENGTH_SHORT).show();
                }else if (enterprise_registration_certificate_image.getText().toString().trim().equals("")){
                    Toast.makeText(FirmInfoUpdateActivity.this,"请填写企业注册证件",Toast.LENGTH_SHORT).show();
                }else if (type_of_taxpayer_image.getText().toString().trim().equals("")){
                    Toast.makeText(FirmInfoUpdateActivity.this,"请填写纳税人类型",Toast.LENGTH_SHORT).show();
                }else{
                    registerMethodW();
                }


            }
        });
    }

    private void initList() {
        type_of_enterprise_rl = ((RelativeLayout) findViewById(R.id.type_of_enterprise_rl));
        enterprise_name_rl = ((RelativeLayout) findViewById(R.id.enterprise_name_rl));
        enterprise_namee_iv = ((ImageView) findViewById(R.id.enterprise_namee_iv));
        enterprise_namee_iv.setVisibility(View.INVISIBLE);
        business_department_rl = ((RelativeLayout) findViewById(R.id.business_department_rl));
        office_address_rl = ((RelativeLayout) findViewById(R.id.office_address_rl));
        detail_address_rl = ((RelativeLayout) findViewById(R.id.detail_address_rl));
        fax_number_rl = ((RelativeLayout) findViewById(R.id.fax_number_rl));
        name_of_enterprise_legal_person_rl = ((RelativeLayout) findViewById(R.id.name_of_enterprise_legal_person_rl));
        enterprise_registration_certificate_rl = ((RelativeLayout) findViewById(R.id.enterprise_registration_certificate_rl));
        business_license_number_rl = ((ListView) findViewById(R.id.business_license_number_rl));
        registerFirmLists = new ArrayList<>();

        registerFirmLists.add(new RegisterFirmList("统一社会信用代码", ""));
        registerFirmListAdapter = new RegisterFirmListAdapter(this, registerFirmLists);
        business_license_number_rl.setAdapter(registerFirmListAdapter);
        business_license_number_rl.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
//                三证独立
                if (zhuce_leixingLevel .equals( "4")) {
                    if (position == 0){
                        intent.setClass(FirmInfoUpdateActivity.this, FirmInfoPictureActivity.class);//营业执照号
                        intent.putExtra("name", registerFirmLists.get(position).getName());
                        intent.putExtra("number", 9);
                        startActivityForResult(intent, 9);
                    }else if(position == 1){
                        intent.setClass(FirmInfoUpdateActivity.this, FirmInfoPictureActivity.class);//        组织机构代码
                        intent.putExtra("name",  registerFirmLists.get(position).getName());
                        intent.putExtra("number", 10);
                        startActivityForResult(intent, 10);
                    }else if(position == 2){
                        intent.setClass(FirmInfoUpdateActivity.this, FirmInfoPictureActivity.class);//        税务登记号
                        intent.putExtra("name",  registerFirmLists.get(position).getName());
                        intent.putExtra("number", 11);
                        startActivityForResult(intent, 11);
                    }

                } else if (zhuce_leixingLevel  .equals( "5")) {
                    intent.setClass(FirmInfoUpdateActivity.this, FirmInfoPictureActivity.class);//        统一社会信用代码
                    intent.putExtra("name",  registerFirmLists.get(position).getName());
                    intent.putExtra("number", 15);
                    startActivityForResult(intent, 15);
                }else  {
                    Toast.makeText(FirmInfoUpdateActivity.this, "请选择类型", Toast.LENGTH_SHORT).show();
                }
            }
        });
        type_of_taxpayer_rl = ((RelativeLayout) findViewById(R.id.type_of_taxpayer_rl));
        taxpayer_photo_rl = ((RelativeLayout) findViewById(R.id.taxpayer_photo_rl));
        application_to_become_a_plastic_trade_network_rl = ((RelativeLayout) findViewById(R.id.application_to_become_a_plastic_trade_network_rl));
        application_to_become_a_plastic_trade_network_rl.setVisibility(View.GONE);
        type_of_enterprise_rl.setOnClickListener(this);
//        enterprise_name_rl.setOnClickListener(this);
        business_department_rl.setOnClickListener(this);
        office_address_rl.setOnClickListener(this);
        detail_address_rl.setOnClickListener(this);
        fax_number_rl.setOnClickListener(this);
        name_of_enterprise_legal_person_rl.setOnClickListener(this);
        enterprise_registration_certificate_rl.setOnClickListener(this);
        type_of_taxpayer_rl.setOnClickListener(this);
        taxpayer_photo_rl.setOnClickListener(this);
//        application_to_become_a_plastic_trade_network_rl.setOnClickListener(this);
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
//        纳税人类型
        type_of_taxpayer_name = ((TextView) findViewById(R.id.type_of_taxpayer_name));
//        纳税人图片
        taxpayer_photo_name = ((TextView) findViewById(R.id.taxpayer_photo_name));
//        申请成为塑贸网
        application_to_become_a_plastic_trade_network_name = ((TextView) findViewById(R.id.application_to_become_a_plastic_trade_network_name));

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
        type_of_taxpayer_image = ((TextView) findViewById(R.id.type_of_taxpayer_image));//        纳税人类型
        taxpayer_photo_image = ((TextView) findViewById(R.id.taxpayer_photo_image));//        纳税人图片
        application_to_become_a_plastic_trade_network_image = ((TextView) findViewById(R.id.application_to_become_a_plastic_trade_network_image));//        申请成为塑贸网

    }

    private void registerMethodW() {
        rp.setAsJsonContent(true);
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("cl_mingcheng", enterprise_name_image.getText().toString().trim());//企业名称|--
            jsonObject.put("cl_yewu", business_department_image.getText().toString().trim());//业务部门（可空）|--
            jsonObject.put("province", sb.substring(0, 4));//省|--
            jsonObject.put("city", sb.substring(0, 6));//市|--
            jsonObject.put("county", sb.toString());//县|--
            jsonObject.put("cl_dizhi", detail_address_image.getText().toString().trim());//详细地址|--
            jsonObject.put("cl_chuanzhen", fax_number_image.getText().toString().trim());//传真|--
            jsonObject.put("cl_zhengjian", zhuce_leixingLevel);//企业注册证件|--
            jsonObject.put("cl_zhizhao", value1.trim());//企业营业执照|--
            jsonObject.put("cl_nashuiren", naShuiRenLevel);//纳税人类型|--
            jsonObject.put("cl_leixing", leixingLevel);//企业类型|--
            jsonObject.put("cl_zhizhaoimage", picturePath1);//营业执照图片路劲|--
            jsonObject.put("cl_jigouimage", picturePath2);//组织机构代码图片路劲|--
            jsonObject.put("cl_shuiwuimage", picturePath3);//税务登记号图片路劲|--
            jsonObject.put("cl_nashuirenimage", picturePath4);//纳税人图片路劲|--
            jsonObject.put("cl_jigou", value2.trim());//组织机构代码|--
            jsonObject.put("cl_shuiwu", value3.trim());//税务登记号|--
            jsonObject.put("cl_entName", name_of_enterprise_legal_person_image.getText().toString().trim());//企业法人|--
//            jsonObject.put("cl_taxNum",  value3.trim());//税号|--
            sp = getSharedPreferences("sumao", Activity.MODE_PRIVATE);
            String unique123 = sp.getString("unique", "");
            jsonObject.put("_dynSessConf", unique123);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        LogUtils.log("---------->shaochang"+jsonObject.toString());
        rp.setBodyContent(jsonObject.toString());


//        LogUtils.log("json---------->"+jsonObject.toString()+"<-------json");
        LogUtils.log("picturePath1------->" + picturePath1);


        x.http().post(rp, new Callback.CacheCallback<String>() {

            @Override
            public void onSuccess(String result) {
                if (result.contains("formError")){
                    Toast.makeText(FirmInfoUpdateActivity.this,"修改出错，请重试",Toast.LENGTH_SHORT).show();
                }else {
                    LogUtils.log("企业信息修改result---》"+result);
                    Toast.makeText(FirmInfoUpdateActivity.this,"修改信息已提交，请等待审核",Toast.LENGTH_LONG).show();
                    finish();
                }

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
    private void showSaveButton() {
        bt_save_register_value.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
//            企业类型
            case 1:
                String oldleixing = type_of_enterprise_image.getText().toString();
                leixing = data.getStringExtra("name");
                leixingLevel = data.getStringExtra("level");
                if (!oldleixing.equals(leixing)){
                    type_of_enterprise_image.setText(leixing);
                    showSaveButton();
                }

                break;
            //            企业名称
            case 2:
                String oldmingcheng = enterprise_name_image.getText().toString();
                mingcheng = data.getStringExtra("name");
                if (!oldmingcheng.equals(mingcheng)){
                    enterprise_name_image.setText(mingcheng);
                    showSaveButton();
                }

                break;
            //            业务部门
            case 3:
                String oldyewu = business_department_image.getText().toString();
                yewu = data.getStringExtra("name");
                LogUtils.log("number3--->");

                if (!oldyewu.equals(yewu)){
                    business_department_image.setText(yewu);
                    showSaveButton();
                }
                break;

            case 5://详细地址
                String olddetailAddress = detail_address_image.getText().toString();
                String detailAddress = data.getStringExtra("name");
                LogUtils.log("number5--->" + detailAddress);
                if (!olddetailAddress.equals(detailAddress)){
                    detail_address_image.setText(detailAddress);
                    showSaveButton();
                }

                break;
            case 6://传真号
                String oldfax_number = fax_number_image.getText().toString();
                String name2 = data.getStringExtra("name");
                LogUtils.log("number6--->" + name2);
                if (!oldfax_number.equals(name2)){
                    fax_number_image.setText(name2);
                    showSaveButton();
                }

                break;
            case 7://企业法人姓名
                String oldname_of_enterprise_legal_person = name_of_enterprise_legal_person_image.getText().toString();
                String name3 = data.getStringExtra("name");
                LogUtils.log("number7--->" + name3);
                if (!oldname_of_enterprise_legal_person.equals(name3)){
                    name_of_enterprise_legal_person_image.setText(name3);
                    showSaveButton();
                }

                break;
            case 8://        企业注册证件
                String oldzhuce_leixing = enterprise_registration_certificate_image.getText().toString();
                zhuce_leixing = data.getStringExtra("name");
                LogUtils.log("number8--->" + zhuce_leixing);
                zhuce_leixingLevel = data.getStringExtra("level");
                LogUtils.log("number8--->" + zhuce_leixingLevel);
                if (!oldzhuce_leixing.equals(zhuce_leixing)){
                    enterprise_registration_certificate_image.setText(zhuce_leixing);
                    showSaveButton();
                }

//                三证独立  4
                if (zhuce_leixingLevel .equals( "4")) {
                    registerFirmLists.removeAll(registerFirmLists);
                    registerFirmLists.add(new RegisterFirmList("营业执照号", ""));
                    registerFirmLists.add(new RegisterFirmList("组织机构代码", ""));
                    registerFirmLists.add(new RegisterFirmList("税务登记号", ""));
                    registerFirmListAdapter.notifyDataSetChanged();
                } else if (zhuce_leixingLevel .equals( "5")) {//三证合一  5
                    registerFirmLists.removeAll(registerFirmLists);
                    registerFirmLists.add(new RegisterFirmList("统一社会信用代码", ""));
                    registerFirmListAdapter.notifyDataSetChanged();
                }
                break;
            case 9: //营业执照号
                String oldYingYeZhiZhao = registerFirmLists.get(0).getValue().toString();
                value1 = data.getStringExtra("value");
                picturePath1 = data.getStringExtra("picturePath");
                LogUtils.log(" 营业执照号9---------->" + value1);
//                business_license_number_image.setText(value1);
                if (!oldYingYeZhiZhao.equals(value1)){
                    registerFirmLists.set(0,new RegisterFirmList("营业执照号", value1));
                    registerFirmListAdapter.notifyDataSetChanged();
                    showSaveButton();
                }

                break;

            case 10://组织机构代码
                String oldZuZhiJiGou = registerFirmLists.get(1).getValue().toString();
                value2 = data.getStringExtra("value");
                LogUtils.log("10---------->" + value2);
                picturePath2 = data.getStringExtra("picturePath");
                if (!oldZuZhiJiGou.equals(value2)){
                    registerFirmLists.set(1,new RegisterFirmList("组织机构代码", value2));
                    registerFirmListAdapter.notifyDataSetChanged();
                    showSaveButton();
                }

                break;

            case 11://税务登记号
                String oldShuiWuDengJi = registerFirmLists.get(2).getValue().toString();
                value3 = data.getStringExtra("value");
                LogUtils.log("11---------->" + value3);
                picturePath3 = data.getStringExtra("picturePath");
//                tax_registration_number_image.setText(value3);
                if (!oldShuiWuDengJi.equals(value3)){
                    registerFirmLists.set(2,new RegisterFirmList("税务登记号", value3));
                    registerFirmListAdapter.notifyDataSetChanged();
                    showSaveButton();
                }

                break;
//            TODO
            case 12://                纳税人类型
                String oldnashuiren = type_of_taxpayer_image.getText().toString();
                nashuiren = data.getStringExtra("name");
                LogUtils.log("number12--->" + nashuiren);
                naShuiRenLevel = data.getStringExtra("level");
                if (!oldnashuiren.equals(nashuiren)){
                    type_of_taxpayer_image.setText(nashuiren);
                    showSaveButton();
                }
                LogUtils.log("number12--->" + naShuiRenLevel);


                break;

            case 13://纳税人照片
                String oldnashuirenpic = taxpayer_photo_image.getText().toString();
                String value4 = data.getStringExtra("value");
                LogUtils.log("13---------->" + value4);
                picturePath4 = data.getStringExtra("picturePath");
                LogUtils.log("picturePath4--->" + picturePath4);
                if (!oldnashuirenpic.equals(value4)){
                    taxpayer_photo_image.setText(value4);
                    showSaveButton();
                }

                break;

            case 14://申请成为塑贸网
                String oldapplicationAsSM = taxpayer_photo_image.getText().toString();
                applicationAsSM = data.getStringExtra("name");
                LogUtils.log("number13--->" + applicationAsSM);
                applicationAsSMLevel = data.getStringExtra("level");
                LogUtils.log("number13--->" + applicationAsSMLevel);
                if (!oldapplicationAsSM.equals(applicationAsSM)){
                    application_to_become_a_plastic_trade_network_image.setText(applicationAsSM);
                    showSaveButton();
                }

                break;
            case 15:
                String oldTongYiSheHui = registerFirmLists.get(0).getValue().toString();
                value1 = data.getStringExtra("value");
                picturePath1 = data.getStringExtra("picturePath");
                LogUtils.log(" 统一社会信用代码9---------->" + value1);
//                business_license_number_image.setText(value1);
                if (!oldTongYiSheHui.equals(value1)){
                    registerFirmLists.set(0,new RegisterFirmList("统一社会信用代码", value1));
                    registerFirmListAdapter.notifyDataSetChanged();
                    showSaveButton();
                }

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
                FirmInfoUpdateActivity.this.finish();
            }
        });
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        String address = getIntent().getStringExtra("address");
        LogUtils.log("address------->RegisterFirmActivity:" + address);
       sb.delete(0,sb.length());
        sb.append(address);
        LogUtils.log("StringBuilder------->RegisterFirmActivity:" + sb.toString());
//        TODO
//        presenter = new RegisterFirmActivityPresenter(this);
        firmInfoUpdatePresenter.setDetailAddress(sb.substring(0, 4), sb.substring(0, 6), sb.toString());
        LogUtils.log("StringBuilder------->RegisterFirmActivity:" + sb.substring(0, 4) + "+" + sb.substring(0, 6) + "+" + sb.toString());

    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.type_of_enterprise_rl://            企业类型
                intent.setClass(FirmInfoUpdateActivity.this, FirmInfoTypeActivity.class);
                intent.putExtra("title", type_of_enterprise_name.getText().toString());
                startActivityForResult(intent, 1);
                break;

            case R.id.enterprise_name_rl: //        企业名称
                intent.setClass(FirmInfoUpdateActivity.this, RegisterValueActivity.class);
                intent.putExtra("content", enterprise_name_image.getText().toString());
                intent.putExtra("title", enterprise_name_name.getText().toString());
                startActivityForResult(intent, 2);
                break;

            case R.id.business_department_rl://        业务部门
                intent.setClass(FirmInfoUpdateActivity.this, RegisterValueActivity.class);
                intent.putExtra("content", business_department_image.getText().toString());
                intent.putExtra("title", business_department_name.getText().toString());
                startActivityForResult(intent, 3);
                break;

            case R.id.office_address_rl://        办公地址
                intent.putExtra("className", "RegisterFirmActivity");
                intent.setClass(FirmInfoUpdateActivity.this, SelectProvinceArea.class);
                startActivityForResult(intent, 4);
                break;

            case R.id.detail_address_rl:// 详细地址
                String office_address = office_address_image.getText().toString();
                if (office_address.isEmpty() || office_address.equals("")) {
                    Toast.makeText(FirmInfoUpdateActivity.this, "请填写办公地区", Toast.LENGTH_SHORT).show();
                } else {
                    intent.setClass(FirmInfoUpdateActivity.this, RegisterValueActivity.class);
                    intent.putExtra("content", detail_address_image.getText().toString());
                    intent.putExtra("title", detail_address_name.getText().toString());
                    startActivityForResult(intent, 5);
                }
                break;

            case R.id.fax_number_rl://        传真号
                intent.setClass(FirmInfoUpdateActivity.this, RegisterValueActivity.class);
                intent.putExtra("content", fax_number_image.getText().toString());
                intent.putExtra("title", fax_number_name.getText().toString());
                startActivityForResult(intent, 6);
                break;

            case R.id.name_of_enterprise_legal_person_rl://        企业法人姓名
                intent.setClass(FirmInfoUpdateActivity.this, RegisterValueActivity.class);
                intent.putExtra("content", name_of_enterprise_legal_person_image.getText().toString());
                intent.putExtra("title", name_of_enterprise_legal_person_name.getText().toString());
                startActivityForResult(intent, 7);
                break;

            case R.id.enterprise_registration_certificate_rl://            企业注册证件
                intent.setClass(FirmInfoUpdateActivity.this, FirmInfoTypeActivity.class);
                intent.putExtra("title", enterprise_registration_certificate_name.getText().toString());
                startActivityForResult(intent, 8);
                break;



            case R.id.type_of_taxpayer_rl://            纳税人类型
                intent.setClass(FirmInfoUpdateActivity.this, FirmInfoTypeActivity.class);
                intent.putExtra("title", type_of_taxpayer_name.getText().toString());
                startActivityForResult(intent, 12);
                break;

            case R.id.taxpayer_photo_rl://        纳税人图片
                intent.setClass(FirmInfoUpdateActivity.this, RegisterPicture.class);
                intent.putExtra("name", taxpayer_photo_name.getText().toString());
                intent.putExtra("number", 13);
                startActivityForResult(intent, 13);

                break;
            case R.id.application_to_become_a_plastic_trade_network_rl://        申请成为塑贸网
                intent.setClass(FirmInfoUpdateActivity.this, FirmInfoTypeActivity.class);
                intent.putExtra("title", application_to_become_a_plastic_trade_network_name.getText().toString());
                startActivityForResult(intent, 14);
                break;

            default:
                break;
        }
    }


    public void setInfoInActivity(FirmInfoUpdateActivityBean firmInfoUpdateActivityBean) {
        LogUtils.log(leixing+"----------->>");
        zhuce_leixingLevel = firmInfoUpdateActivityBean.getCl_zhengjian();
        value1 = firmInfoUpdateActivityBean.getCl_zhizhao();//执照
        naShuiRenLevel = firmInfoUpdateActivityBean.getCl_nashuiren();//纳税人类型
        leixingLevel = firmInfoUpdateActivityBean.getCl_leixing();//类型
        value2 = firmInfoUpdateActivityBean.getCl_jigou(); //组织机构代码
        value3 = firmInfoUpdateActivityBean.getCl_shuiwu();//税务登记号
//        企业类型
        type_of_enterprise_image.setText(leixing);
//        企业名称
        enterprise_name_image.setText(firmInfoUpdateActivityBean.getCl_mingcheng());
//业务部门
        business_department_image.setText(firmInfoUpdateActivityBean.getCl_yewu());
//        设置地区
        office_address_image.setText(address);

//        详细地址
        detail_address_image.setText(firmInfoUpdateActivityBean.getCl_dizhi());
//        传真号
        fax_number_image.setText(firmInfoUpdateActivityBean.getCl_chuanzhen());
//        企业法人
        name_of_enterprise_legal_person_image.setText(firmInfoUpdateActivityBean.getCl_corporation());
//        企业注册证件
        enterprise_registration_certificate_image.setText(zhengjian);
//          三证形式
        if (firmInfoUpdateActivityBean.getCl_zhengjian().equals("4")){//三证独立
            registerFirmLists.removeAll(registerFirmLists);
            registerFirmLists.add(new RegisterFirmList("营业执照号", firmInfoUpdateActivityBean.getCl_zhizhao()));
            registerFirmLists.add(new RegisterFirmList("组织机构代码", firmInfoUpdateActivityBean.getCl_jigou()));
            registerFirmLists.add(new RegisterFirmList("税务登记号", firmInfoUpdateActivityBean.getCl_shuiwu()));
            registerFirmListAdapter.notifyDataSetChanged();
        }else if(firmInfoUpdateActivityBean.getCl_zhengjian().equals("5")){//三证合一
            registerFirmLists.removeAll(registerFirmLists);
            registerFirmLists.add(new RegisterFirmList("统一社会信用代码",  firmInfoUpdateActivityBean.getCl_zhizhao()));
            registerFirmListAdapter.notifyDataSetChanged();
        }
//        TODO  明天接着干干干
//        纳税人类型
        type_of_taxpayer_image.setText(nashuiren);
//
    }


    @Override
    public void setStringInText(String address) {
        String olddetail_address_image = detail_address_image.getText().toString();
        if (!olddetail_address_image.equals(address)){
            detail_address_image.setText(address);
        }
    }
}
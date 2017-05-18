package com.silianchuangye.sumao.success.fragments.myPlasticTrade.qurchaseQualification.purchaseQualificationDetail.PurchaseQualificationDetailMVP;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.adapter.LogisticsDemandExpandableListViewAdapter;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.qurchaseQualification.purchaseQualificationDetail.DataDetailsActivity;
//import com.silianchuangye.sumao.success.fragments.myPlasticTrade.qurchaseQualification.purchaseQualificationDetail.PurchaseQualificationDetailMVP.presenter.PurchaseQualificationDetailPresenter;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.qurchaseQualification.purchaseQualificationDetail.PurchaseQualificationDetailMVP.view.IPurchaseQualificationDetailView;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.qurchaseQualification.purchaseQualificationDetail.WaitActivity;
import com.silianchuangye.sumao.success.utils.LogUtils;
import com.silianchuangye.sumao.success.utils.SuMaoConstant;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

public class PurchaseQualificationDetailActivity extends AppCompatActivity implements View.OnClickListener, IPurchaseQualificationDetailView {

    private RelativeLayout corporate_name_rl;
    private RelativeLayout customer_bank_license_rl;
    private RelativeLayout annual_demand_rl;
    private RelativeLayout number_of_employees_rl;
    private RelativeLayout name_rl;
    private RelativeLayout mailbox_rl;
    private RelativeLayout telephone_rl;
    private RelativeLayout fax_number_rl;
    private TextView corporate_name;
    private TextView customer_bank_license;
    private TextView annual_demand;
    private TextView number_of_employees;
    private TextView name;
    private TextView mailbox;
    private TextView telephone;
    private TextView fax_number;
    public static final int CORPORATE_NAME = 1;
    public static final int CUSTOMER_BANK_LICENSE = 2;
    public static final int ANNUAL_DEMAND = 3;
    public static final int NUMBER_OF_EMPLOYEES = 4;
    public static final int NAME = 5;
    public static final int MAILBOX = 6;
    public static final int TELEPHONE = 7;
    public static final int FAX_NUMBER = 8;
    private TextView corporate_name_content;
    private TextView customer_bank_license_content;
    private TextView fax_number_content;
    private TextView telephone_content;
    private TextView mailbox_content;
    private TextView name_content;
    private TextView number_of_employees_content;
    private TextView annual_demand_content;
    private Button submit_button;
    //    上游资源方id
    private String id;
    private String dynSessConf;

    private String path="";
    private String qy_Name;
    private String number;
    private String people;
    private String fileCode;
    private String path_qyName="", path_Name="", pathbank="";
    private SharedPreferences sp;
    private String unique;
    private ListView lvDemo;
    private List<String> list;
    private String number1;
    private TextView tv_child_title_bar_title;
    private ImageView iv_child_title_bar_back;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase_qualification_detail);
        getUsername();
//        上游资源方id
        id = getIntent().getStringExtra("id");
        //PurchaseQualificationDetailPresenter purchaseQualificationDetailPresenter = new PurchaseQualificationDetailPresenter(this);
        dynSessConf = getSharedPreferences("sumao", Activity.MODE_PRIVATE).getString("unique", "");
        //purchaseQualificationDetailPresenter.getAuditStatus(dynSessConf,id);
        findView();
        path = getSharedPreferences("sumao", Activity.MODE_PRIVATE).getString("path", "");


    }

    public void addData() {
        list = new ArrayList<>();
        list.add("0-20");
        list.add("21-50");
        list.add("51-100");
        list.add("101-500");
        list.add("500以上");
       // ArrayAdapter arrayAdapter=new ArrayAdapter()
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, R.layout.item_pop_lv, R.id.tv_pop_lv,list);
        lvDemo.setAdapter(arrayAdapter);
        arrayAdapter.notifyDataSetChanged();

        lvDemo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                  //number=list.get(position).toString();
                if (list.get(position).toString().equals("0-20")){
                    number1="10";
                }else if (list.get(position).toString().equals("21-50")){
                    number1="11";
                }else if (list.get(position).toString().equals("51-100")){
                    number1="12";
                }else if (list.get(position).toString().equals("101-500")){
                    number1="13";
                }else if (list.get(position).toString().equals("500以上")){
                    number1="14";
                }
                number_of_employees_content.setText(list.get(position).toString()+"人");
                lvDemo.setVisibility(View.GONE);

            }
        });


    }

    private void findView() {
        tv_child_title_bar_title= (TextView) findViewById(R.id.tv_child_title_bar_title);
        tv_child_title_bar_title.setText("申请成为上游资源方");
        iv_child_title_bar_back= (ImageView) findViewById(R.id.iv_child_title_bar_back);
        iv_child_title_bar_back.setOnClickListener(this);
//        企业法人姓名
        corporate_name_rl = ((RelativeLayout) findViewById(R.id.corporate_name_rl));
        corporate_name_rl.setOnClickListener(this);

//        客户银行许可证
        customer_bank_license_rl = ((RelativeLayout) findViewById(R.id.customer_bank_license_rl));
        customer_bank_license_rl.setOnClickListener(this);
//        年需求量
        annual_demand_rl = ((RelativeLayout) findViewById(R.id.annual_demand_rl));
        annual_demand_rl.setOnClickListener(this);
//        从业人员数量
        number_of_employees_rl = ((RelativeLayout) findViewById(R.id.number_of_employees_rl));
        number_of_employees_rl.setOnClickListener(this);
        lvDemo = (ListView) findViewById(R.id.lvdemo);

//        姓名
        name_rl = ((RelativeLayout) findViewById(R.id.name_rl));
        name_rl.setOnClickListener(this);
//        邮箱
        mailbox_rl = ((RelativeLayout) findViewById(R.id.mailbox_rl));
        mailbox_rl.setOnClickListener(this);
//        手机号
        telephone_rl = ((RelativeLayout) findViewById(R.id.telephone_rl));
        telephone_rl.setOnClickListener(this);
//        传真号
        fax_number_rl = ((RelativeLayout) findViewById(R.id.fax_number_rl));
        fax_number_rl.setOnClickListener(this);
//        企业法人姓名
        corporate_name = ((TextView) findViewById(R.id.corporate_name));
        corporate_name_content = ((TextView) findViewById(R.id.corporate_name_content));
//        Log.d("企业法人的名称",qy_Name);

//        客户银行许可证
        customer_bank_license = ((TextView) findViewById(R.id.customer_bank_license));
        customer_bank_license_content = ((TextView) findViewById(R.id.customer_bank_license_content));
//        年需求量
        annual_demand = ((TextView) findViewById(R.id.annual_demand));
        annual_demand_content = ((TextView) findViewById(R.id.annual_demand_content));
//        从业人员数量
        number_of_employees = ((TextView) findViewById(R.id.number_of_employees));
        number_of_employees_content = ((TextView) findViewById(R.id.number_of_employees_content));

//        姓名
        name = ((TextView) findViewById(R.id.name));
        name_content = ((TextView) findViewById(R.id.name_content));
//        邮箱
        mailbox = ((TextView) findViewById(R.id.mailbox));
        mailbox_content = ((TextView) findViewById(R.id.mailbox_content));
//        手机号
        telephone = ((TextView) findViewById(R.id.telephone));
        telephone_content = ((TextView) findViewById(R.id.telephone_content));
//        传真号
        fax_number = ((TextView) findViewById(R.id.fax_number));
        fax_number_content = ((TextView) findViewById(R.id.fax_number_content));
//        提交审核
        submit_button = ((Button) findViewById(R.id.submit_button));
        submit_button.setOnClickListener(this);
        sp = getSharedPreferences("sumao", Activity.MODE_PRIVATE);


    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.iv_child_title_bar_back:
                PurchaseQualificationDetailActivity.this.finish();
                break;
            case R.id.corporate_name_rl:
                intent.setClass(PurchaseQualificationDetailActivity.this, DataDetailsActivity.class);
                intent.putExtra("title", corporate_name.getText().toString());
//                判断是否需要上传图片
                intent.putExtra("picture", true);
                intent.putExtra("resouce", "abc");
                intent.putExtra("path", path_qyName);
                intent.putExtra("value",corporate_name_content.getText().toString());
                startActivityForResult(intent, CORPORATE_NAME);
                break;
            case R.id.customer_bank_license_rl:
                intent.setClass(PurchaseQualificationDetailActivity.this, DataDetailsActivity.class);
                intent.putExtra("title", customer_bank_license.getText().toString());
                intent.putExtra("picture", true);
                if (pathbank.equals("")) {
                    intent.putExtra("path", "abc");
                }else{
                    intent.putExtra("path", pathbank);
                }
                if (customer_bank_license_content.getText().toString().equals("请上传银行卡扫描件")){
                    intent.putExtra("value"," ");
                }else{
                    intent.putExtra("value",customer_bank_license_content.getText().toString());
                }
                intent.putExtra("resouce", "abc");
                startActivityForResult(intent, CUSTOMER_BANK_LICENSE);
                break;
            case R.id.annual_demand_rl:
                intent.setClass(PurchaseQualificationDetailActivity.this, DataDetailsActivity.class);
                intent.putExtra("title", annual_demand.getText().toString());
                intent.putExtra("picture", false);
                intent.putExtra("path", "abc");
//                Log.d("数量的值",annual_demand_content.getText().toString());
                if (annual_demand_content.getText().toString().equals("吨/年")){
                    intent.putExtra("value"," ");
                }else{
                    intent.putExtra("value",annual_demand_content.getText().toString().substring(0,annual_demand_content.getText().toString().length()-3));
                }
                intent.putExtra("resouce", "abc");
                startActivityForResult(intent, ANNUAL_DEMAND);
                break;
            //case R.id.number_of_employees_rl:
//                intent.setClass(PurchaseQualificationDetailActivity.this, DataDetailsActivity.class);
//                intent.putExtra("title", number_of_employees.getText().toString());
//                intent.putExtra("picture", false);
//                intent.putExtra("path", "abc");
//                intent.putExtra("resouce", "abc");
//                startActivityForResult(intent, NUMBER_OF_EMPLOYEES);
              //  break;
            case R.id.name_rl:
                intent.setClass(PurchaseQualificationDetailActivity.this, DataDetailsActivity.class);
                intent.putExtra("title", name.getText().toString());
                intent.putExtra("picture", true);
                if (path_Name.equals("")) {
                    intent.putExtra("path", "abc");
                }else{
                    intent.putExtra("path",path_Name);
                }
                if (name_content.getText().toString().equals("填写姓名并上传身份证")){
                    intent.putExtra("value"," ");
                }else{
                    intent.putExtra("value",name_content.getText().toString());
                }
                intent.putExtra("resouce", "abc");
                startActivityForResult(intent, NAME);
                break;
            case R.id.mailbox_rl:
                intent.setClass(PurchaseQualificationDetailActivity.this, DataDetailsActivity.class);
                intent.putExtra("title", mailbox.getText().toString());
                intent.putExtra("picture", false);
                intent.putExtra("resouce", "abc");
                if (mailbox_content.getText().toString().equals("请填写手机号")){
                    intent.putExtra("value","");
                }else{
                    intent.putExtra("value",mailbox_content.getText().toString());
                }
                intent.putExtra("path", "abc");
                startActivityForResult(intent, MAILBOX);
                break;
            case R.id.telephone_rl:
                intent.setClass(PurchaseQualificationDetailActivity.this, DataDetailsActivity.class);
                intent.putExtra("title", telephone.getText().toString());
                intent.putExtra("path", "abc");
                intent.putExtra("resouce", "abc");
                if (telephone_content.getText().toString().equals("请填写手机号")){
                    intent.putExtra("value"," ");
                }else{
                    intent.putExtra("value",telephone_content.getText().toString());
                }
                intent.putExtra("picture", false);
                startActivityForResult(intent, TELEPHONE);
                break;
            case R.id.fax_number_rl:
                intent.setClass(PurchaseQualificationDetailActivity.this, DataDetailsActivity.class);
                intent.putExtra("title", fax_number.getText().toString());
                intent.putExtra("picture", false);
                intent.putExtra("path", "abc");
                if (fax_number_content.getText().toString().equals("请填写传真号")){
                    intent.putExtra("value","");
                }else{
                    intent.putExtra("value",fax_number_content.getText().toString());
                }
                intent.putExtra("resouce", "abc");
                startActivityForResult(intent, FAX_NUMBER);
                break;
            case R.id.submit_button:
                String corporateName = corporate_name_content.getText().toString();
                LogUtils.log("企业法人姓名：" + corporateName);
                Log.d("银行许可证",customer_bank_license_content.getText().toString());
                Log.d("年需求量",annual_demand_content.getText().toString());
                Log.d("企业人数",number_of_employees_content.getText().toString());
                Log.d("姓名",name_content.getText().toString());
                Log.d("邮箱",mailbox_content.getText().toString());
                Log.d("手机号",telephone_content.getText().toString());
                Log.d("传真号",fax_number_content.getText().toString());
                if (!corporate_name_content.getText().toString().equals("")&&!customer_bank_license_content.getText().toString().equals("")
                        &&!annual_demand_content.equals("吨/年")&&!number_of_employees_content.getText().toString().equals("人")
                && !name_content.getText().toString().equals("")&&!mailbox_content.getText().toString().equals("")
                &&!telephone_content.getText().toString().equals("")&&!fax_number_content.getText().toString().equals("")
                         ) {

                    new Thread() {
                        @Override
                        public void run() {
                            super.run();
                            commitShangyou();
                        }
                    }.start();
                }else{
                    Toast.makeText(PurchaseQualificationDetailActivity.this, "请填写完整的信息!", Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.number_of_employees_rl:
                lvDemo.setVisibility(View.VISIBLE);
                addData();
                break;
            default:
                break;
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case CORPORATE_NAME:
                if (resultCode == RESULT_OK) {
                    String returnInfo = data.getStringExtra("returnInfo");
                    path_qyName = data.getStringExtra("path");
                    Log.d("path的值",path_qyName);
                    corporate_name_content.setText(returnInfo);
                }
                break;
            case CUSTOMER_BANK_LICENSE:
                if (resultCode == RESULT_OK) {
                    String returnInfo = data.getStringExtra("returnInfo");
                    customer_bank_license_content.setText(returnInfo);
                    pathbank = data.getStringExtra("path");
                   // Log.d("path的值",pathbank);
                }
                break;
            case ANNUAL_DEMAND:
                if (resultCode == RESULT_OK) {
                    String returnInfo = data.getStringExtra("returnInfo");
                    number = returnInfo;
                    annual_demand_content.setText(returnInfo + "吨/年");
                }
                break;
            case NUMBER_OF_EMPLOYEES:
                if (resultCode == RESULT_OK) {
                    String returnInfo = data.getStringExtra("returnInfo");
                    people = returnInfo;
                    number_of_employees_content.setText(returnInfo + "人");
                }
                break;
            case NAME:
                if (resultCode == RESULT_OK) {
                    String returnInfo = data.getStringExtra("returnInfo");
                    name_content.setText(returnInfo);
                    path_Name = data.getStringExtra("path");
                    Log.d("path的值",path_Name);
                }
                break;
            case MAILBOX:
                if (resultCode == RESULT_OK) {
                    String returnInfo = data.getStringExtra("returnInfo");
                    mailbox_content.setText(returnInfo);
                }
                break;
            case TELEPHONE:
                if (resultCode == RESULT_OK) {
                    String returnInfo = data.getStringExtra("returnInfo");
                    telephone_content.setText(returnInfo);
                }
                break;
            case FAX_NUMBER:
                if (resultCode == RESULT_OK) {
                    String returnInfo = data.getStringExtra("returnInfo");
                    fax_number_content.setText(returnInfo);
                }
                break;

        }
    }

    //获取到企业法人姓名
    public void getUsername() {
        String url = SuMaoConstant.SUMAO_IP + "/rest/model/atg/userprofiling/ProfileActor/corporation";
        RequestParams rp = new RequestParams(url);
        x.http().post(rp, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.d("获取企业法人名称的返回值", result);
                try {
                    if (result.contains("sucess")) {
                        JSONObject jsonObject = new JSONObject(result);
                        if (result.contains("cl_qysmj")) {
                            path_qyName = jsonObject.getString("cl_qysmj");
                            Log.d("图片路径的值", path_qyName);
                        }
                        qy_Name = jsonObject.getString("cl_qyfrxm");
                        Log.d("企业法人的值", qy_Name);
                        corporate_name_content.setText(qy_Name);
                        SharedPreferences sp = getSharedPreferences("sumao", Activity.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sp.edit();
                        editor.putString("path", path_qyName);
                        editor.commit();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
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

    //申请成为上游资源方客户
    public void commitShangyou() {
        String url = SuMaoConstant.SUMAO_IP + "/rest/model/atg/userprofiling/ProfileActor/applyUpresource";
        RequestParams rp = new RequestParams(url);
        rp.addParameter("org", id);
        rp.addParameter("bankPermit", customer_bank_license_content.getText().toString());
        rp.addParameter("annualDemand", number);
        rp.addParameter("employedCount", number1);
        rp.setAsJsonContent(true);
        JSONObject json = new JSONObject();
        try {
            json.put("contactName", name_content.getText().toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        rp.setBodyContent(json.toString());
        //rp.addParameter("contactName",);
        rp.addParameter("contactEmail", mailbox_content.getText().toString());
        rp.addParameter("contactMobile", telephone_content.getText().toString());
        rp.addParameter("contactFax", fax_number_content.getText().toString());
        if (!path_qyName.equals("")) {
            rp.addParameter("corporationNameImgae", path_qyName);
        }else{
            Toast.makeText(PurchaseQualificationDetailActivity.this, "请上传企业法人扫描件!", Toast.LENGTH_SHORT).show();
        }
        Log.d("企业法人的图片的值", path_qyName);
        if (!pathbank.equals("")) {
            rp.addParameter("bankPermitImgaes", pathbank);
        }else{
            Toast.makeText(PurchaseQualificationDetailActivity.this, "请上传银行扫描件!", Toast.LENGTH_SHORT).show();
        }
        Log.d("银行图片的值", pathbank);
        if (!path_Name.equals("")) {
            rp.addParameter("contactImgaes", path_Name);
        }else {
            Log.d("名称的值", path_Name);
            Toast.makeText(PurchaseQualificationDetailActivity.this, "请上传姓名的扫描件!", Toast.LENGTH_SHORT).show();
        }
        rp.addParameter("_dynSessConf", dynSessConf);
        Log.d("提交的url", rp.toString());
        x.http().post(rp, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.d("申请上游资源方的返回值", result);
                if (result.contains("sucess")) {
                    Toast.makeText(PurchaseQualificationDetailActivity.this, "提交成功", Toast.LENGTH_SHORT).show();
                    getresult();
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

    public void getresult() {
        String url = SuMaoConstant.SUMAO_IP + "/rest/model/atg/userprofiling/ProfileActor/upresource";
        RequestParams rp = new RequestParams(url);
        rp.addParameter("_dynSessConf", unique);
        rp.addParameter("up", id);
        x.http().post(rp, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Intent intent = new Intent(PurchaseQualificationDetailActivity.this, WaitActivity.class);
                intent.putExtra("id", id);
                intent.putExtra("result", result);
                startActivity(intent);
                finish();
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

    @Override
    public void setAuditStatus(String result) {

    }


}

package com.silianchuangye.sumao.success.fragments.myPlasticTrade.qurchaseQualification.purchaseQualificationDetail;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.utils.SuMaoConstant;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

public class WaitActivity extends AppCompatActivity implements View.OnClickListener {
    private Button bt_SearchName;
    private Button bt_SearchqiyeName;
    private Button bt_SearchBank;
    private String id;
    private String result;

    private TextView corporate_name_content;
    private TextView customer_bank_license_content;
    private TextView annual_demand_content;
    private TextView number_of_employees_content;
    private TextView name_content;
    private TextView mailbox_content;
    private TextView telephone_content;
    private TextView fax_number_content;
    private String path;
    private SharedPreferences sp;
    private String unique;
    private String pathName;
    private String pathBank;
    private ImageView iv_child_title_bar_back;
    private TextView tv_child_title_bar_title;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wait);
        Bundle bundle=getIntent().getExtras();
        id=bundle.getString("id");
        result=bundle.getString("result");
        Log.d("result的返回值",result);
        tv_child_title_bar_title= (TextView) findViewById(R.id.tv_child_title_bar_title);
        tv_child_title_bar_title.setText("申请成为上游资源方客户");
        iv_child_title_bar_back= (ImageView) findViewById(R.id.iv_child_title_bar_back);
        iv_child_title_bar_back.setOnClickListener(this);
        bt_SearchName= (Button) findViewById(R.id.bt_Search_xingming);
        bt_SearchName.setOnClickListener(this);
        bt_SearchqiyeName= (Button) findViewById(R.id.bt_Search_qyName);
        bt_SearchqiyeName.setOnClickListener(this);
        bt_SearchBank= (Button) findViewById(R.id.bt_Search_Bank);
        bt_SearchBank.setOnClickListener(this);
        sp=getSharedPreferences("sumao", Activity.MODE_PRIVATE);
        unique=sp.getString("unique","");
       // searchUser();

        corporate_name_content= (TextView) findViewById(R.id.corporate_name_content);
        customer_bank_license_content= (TextView) findViewById(R.id.customer_bank_license_content);
        annual_demand_content= (TextView) findViewById(R.id.annual_demand_content);
        number_of_employees_content= (TextView) findViewById(R.id.number_of_employees_content);
        name_content= (TextView) findViewById(R.id.name_content);
        mailbox_content= (TextView) findViewById(R.id.mailbox_content);
        telephone_content= (TextView) findViewById(R.id.telephone_content);
        fax_number_content= (TextView) findViewById(R.id.fax_number_content);
      //  getBank();
        if (!result.equals("abc")){
        try {
            JSONObject jsonObject=new JSONObject(result);
            path=jsonObject.getString("cl_qysmj");
            corporate_name_content.setText(jsonObject.getString("cl_qyfrxm"));
            customer_bank_license_content.setText(jsonObject.getString("cl_khyhxkz"));

            annual_demand_content.setText(jsonObject.getString("cl_nxql")+"吨/年");
            if (jsonObject.getString("cl_cyrysl").equals("10")){
                number_of_employees_content.setText("0-20"+"人");
            }else if (jsonObject.getString("cl_cyrysl").equals("11")){
                number_of_employees_content.setText("21-50人");
            }else if (jsonObject.getString("cl_cyrysl").equals("12")){
                number_of_employees_content.setText("51-100人");
            }else if (jsonObject.getString("cl_cyrysl").equals("13")){
                number_of_employees_content.setText("101-500人");
            }else if (jsonObject.getString("cl_cyrysl").equals("14")){
                number_of_employees_content.setText("500人以上");
            }

            name_content.setText(jsonObject.getString("cl_lxrxm"));
            mailbox_content.setText(jsonObject.getString("cl_youxiang"));
            telephone_content.setText(jsonObject.getString("cl_shouji"));
            fax_number_content.setText(jsonObject.getString("cl_chuanzhen"));
            pathName=jsonObject.getString("cl_lxrxmimg");
            pathBank=jsonObject.getString("cl_yhsmj");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        }



    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_child_title_bar_back:
                WaitActivity.this.finish();
                break;
            case R.id.bt_Search_xingming:
                Intent intent=new Intent();
                intent.setClass(WaitActivity.this, DataDetailsActivity.class);
                intent.putExtra("title", corporate_name_content.getText().toString());
//                判断是否需要上传图片
                intent.putExtra("picture", true);
                intent.putExtra("resouce","search");
                intent.putExtra("path",pathName);
                startActivity(intent);
                break;
            case R.id.bt_Search_qyName:
                Intent intent1=new Intent();
                intent1.setClass(WaitActivity.this, DataDetailsActivity.class);
                intent1.putExtra("title", name_content.getText().toString());
                intent1.putExtra("resouce","search");
//                判断是否需要上传图片
                intent1.putExtra("picture", true);
                intent1.putExtra("path",path );
                startActivity(intent1);
                break;
            case R.id.bt_Search_Bank:
//                sp=getSharedPreferences("sumao",Activity.MODE_PRIVATE);
//                String pathbank=sp.getString("pathbank","");
                Intent intent2=new Intent();
                intent2.setClass(WaitActivity.this, DataDetailsActivity.class);
                intent2.putExtra("resouce","search");
                intent2.putExtra("title", customer_bank_license_content.getText().toString());
//                判断是否需要上传图片
                intent2.putExtra("picture", true);
                intent2.putExtra("path",pathBank);
                startActivity(intent2);
                break;
        }

    }

    //public void getBank(){
//        String url=SuMaoConstant.SUMAO_IP+"/rest/model/atg/userprofiling/ProfileActor/upresource";
//        RequestParams rp=new RequestParams(url);
//        rp.addParameter("org",id);
//        rp.addParameter("_dynSessConf",unique);
//        x.http().post(rp, new Callback.CommonCallback<String>() {
//            @Override
//            public void onSuccess(String result) {
//                Log.d("查看银行的",result);
//                try {
//                    JSONObject object=new JSONObject(result);
//                    String bank=object.getString("cl_khyhxkz");
//                    String pathBank=object.getString("cl_qysmj");
//                    sp=getSharedPreferences("sumao",Activity.MODE_PRIVATE);
//                    SharedPreferences.Editor editor=sp.edit();
//                    editor.putString("pathbank",pathBank);
//                    editor.commit();
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//
//            @Override
//            public void onError(Throwable ex, boolean isOnCallback) {
//
//            }
//
//            @Override
//            public void onCancelled(CancelledException cex) {
//
//            }
//
//            @Override
//            public void onFinished() {
//
//            }
//        });

  //  }

}

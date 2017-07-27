package com.silianchuangye.sumao.success.seller.fragmentone.account;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.login.LoginUserActivity;
import com.silianchuangye.sumao.success.utils.SuMaoConstant;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

public class SellerUpdatePasssActivity extends AppCompatActivity {
    private ImageView iv_back;
    private TextView tv_Title;
    private TextView save;
//    private ListView lv_update_pass;
//    private List<Map<String,Object>> list;
    private ImageView iv_cancel_old;
    private EditText ed_content_old;
    private ImageView iv_cancel;
    private EditText ed_content;
    private ImageView iv_cancel_comfirm;
    private EditText ed_content_comfirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_update_passs);
        init();
        //addData();
    }
    public void init(){
        iv_cancel_old= (ImageView) findViewById(R.id.iv_cancel_old);
        ed_content_old= (EditText) findViewById(R.id.ed_content_old);
        iv_cancel= (ImageView) findViewById(R.id.iv_cancel);
        ed_content= (EditText) findViewById(R.id.ed_content);
        iv_cancel_comfirm= (ImageView) findViewById(R.id.iv_cancel_comfirm);
        ed_content_comfirm= (EditText) findViewById(R.id.ed_content_comfirm);
        iv_cancel_old.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ed_content_old.setText("");
            }
        });
        iv_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ed_content.setText("");
            }
        });
        iv_cancel_comfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ed_content_comfirm.setText("");
            }
        });
        iv_back= (ImageView) findViewById(R.id.iv_back);
        tv_Title= (TextView) findViewById(R.id.tv_Title);
        save= (TextView) findViewById(R.id.save);
//        lv_update_pass= (ListView) findViewById(R.id.lv_update_pass);
//        list=new ArrayList<>();
        tv_Title.setText("修改密码");
        save.setText("保存");
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ed_content.getText().toString() != null && !ed_content.getText().toString().equals("") && !ed_content.getText().toString().isEmpty()
                        && ed_content_old.getText().toString() != null && !ed_content_old.getText().toString().equals("") && !ed_content_old.getText().toString().isEmpty()
                        && ed_content_comfirm.getText().toString() != null && !ed_content_comfirm.getText().toString().equals("") && !ed_content_comfirm.getText().toString().isEmpty()
                        ) {
                    update_pass();
                } else {
                    Toast.makeText(SellerUpdatePasssActivity.this, "请把信息输入完整!", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }



    public void update_pass(){
        SharedPreferences sp=getSharedPreferences("sumao", Activity.MODE_PRIVATE);
        String seller_session=sp.getString("seller_session","");
        String url= SuMaoConstant.Seller_SUMAO_IP+"/smp/moble/shop/enterpriseInfo/modifySellerPasswordMobile.html";
        RequestParams rp=new RequestParams(url);
        rp.addParameter("oldPassword",ed_content_old.getText().toString());
        rp.addParameter("password",ed_content.getText().toString());
        rp.addParameter("passwordCfm",ed_content_comfirm.getText().toString());
        rp.addParameter("sessionId",seller_session);
        Log.d("修改密码的rp",rp+"");
        x.http().post(rp, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.d("修改密码的返回值",result);
                try {
                    JSONObject jsonobject=new JSONObject(result);
                    String executionResult=jsonobject.getString("executionResult");
                    if (executionResult.equals("true")){
                        Toast.makeText(SellerUpdatePasssActivity.this, "密码修改成功!", Toast.LENGTH_SHORT).show();
                    }else{
                        String errors=jsonobject.getString("errors");
                        JSONArray json_info=new JSONArray(errors);
                        String errorinfo=json_info.toString().substring(2,json_info.toString().length()-2);
                        if (errorinfo.equals("找不到相应用户")){
                            Toast.makeText(SellerUpdatePasssActivity.this, "用户没有登录!", Toast.LENGTH_SHORT).show();
                            Intent intent=new Intent(SellerUpdatePasssActivity.this, LoginUserActivity.class);
                            startActivity(intent);
                        }else{
                            Toast.makeText(SellerUpdatePasssActivity.this, errorinfo, Toast.LENGTH_SHORT).show();
                        }
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

}

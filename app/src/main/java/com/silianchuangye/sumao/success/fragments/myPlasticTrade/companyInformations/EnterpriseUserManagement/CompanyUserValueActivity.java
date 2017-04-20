package com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.EnterpriseUserManagement;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.register.RegisterPhoneMVP.RegisterPhonePresenter.RegisterPhonePresenter;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.register.RegisterPhoneMVP.RegisterPhoneView.IRegisterPhoneView;
import com.silianchuangye.sumao.success.utils.SuMaoConstant;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

public class CompanyUserValueActivity extends AppCompatActivity implements IRegisterPhoneView {
    ImageView iv_title_bar_logo,
            iv_title_bar_back,
            iv_title_bar_service,
            add_address,
            iv_title_bar_search;
    Button sv_title_bar_serachView;
    TextView tv_title_bar_title, tv, tvUpdate;
    RelativeLayout layoutTop, add_address_rl;
    private Button bt_Save;
    private EditText et_content_value;
    int position;
    String account;
    private TextView tv_a;
    String Content;
    private RegisterPhonePresenter registerPhonePresenter;
    private String title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_user_value);
        registerPhonePresenter=new RegisterPhonePresenter(this);
        tv_a= (TextView) findViewById(R.id.tv_a);
        Bundle bundle=getIntent().getExtras();
         title=bundle.getString("title");
        position=bundle.getInt("number");
        Content=bundle.getString("content");
        title_Bar(title);
        tv_a.setVisibility(View.VISIBLE);
        tv_a.setText("请填写正确的格式");


        et_content_value= (EditText) findViewById(R.id.et_content_value);
        et_content_value.setFocusable(true);
        et_content_value.setText(Content);
        //Log.d("账号的值",account);
        bt_Save= (Button) findViewById(R.id.bt_save_register_value);
        bt_Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (title.equals("电话号码")){
                    et_content_value.setInputType(InputType.TYPE_CLASS_PHONE);
                    registerPhonePresenter.sendRegisterPhoneToServer(et_content_value.getText().toString());
                }else if (title.equals("邮箱")){
                    et_content_value.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
                    yanzhengyouxiang();
                }



            }
        });


    }
    public void yanzhengyouxiang(){
        String url= SuMaoConstant.SUMAO_IP+"/rest/model/atg/store/profile/RegistrationActor/userVerify";
        RequestParams rp=new RequestParams(url);
        rp.addParameter("type","email");
        rp.addParameter("attr",et_content_value.getText().toString());
        x.http().post(rp, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.d("验证邮箱的返回值",result);
                if (result.contains("true")){
                                    account=et_content_value.getText().toString();
                Intent intent=new Intent();
                intent.putExtra("content",account);
                Log.d("账号的值",account);
                setResult(position,intent);
                Log.d("position的值",position+"");
                CompanyUserValueActivity.this.finish();
                }else{
                    Toast.makeText(CompanyUserValueActivity.this, "该邮箱已经注册!", Toast.LENGTH_SHORT).show();
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
    public void title_Bar(String string) {

        iv_title_bar_back = ((ImageView) findViewById(R.id.iv_title_bar_back));
        iv_title_bar_logo = ((ImageView) findViewById(R.id.iv_title_bar_logo));
        iv_title_bar_service = ((ImageView) findViewById(R.id.iv_title_bar_service));
        iv_title_bar_search = ((ImageView) findViewById(R.id.iv_title_bar_search));
        sv_title_bar_serachView = ((Button) findViewById(R.id.sv_title_bar_serachView));
        tv_title_bar_title = ((TextView) findViewById(R.id.tv_title_bar_title));

        tvUpdate = (TextView) findViewById(R.id.tvUpdate);
        iv_title_bar_logo.setVisibility(View.GONE);
        iv_title_bar_service.setVisibility(View.GONE);
        sv_title_bar_serachView.setVisibility(View.GONE);
        iv_title_bar_search.setVisibility(View.GONE);
        iv_title_bar_back.setVisibility(View.GONE);
        tvUpdate.setVisibility(View.GONE);
        tv_title_bar_title.setText(string);


        tv_title_bar_title.setTextColor(Color.WHITE);
        iv_title_bar_back.setVisibility(View.VISIBLE);
        layoutTop = (RelativeLayout) findViewById(R.id.layoutTop_register_value);
        layoutTop.setBackgroundColor(getResources().getColor(R.color.textColor_expandable_listview_show));
        iv_title_bar_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                account="";
                Intent intent=new Intent();
                intent.putExtra("content",account);
                setResult(position,intent);
                CompanyUserValueActivity.this.finish();
            }
        });

    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
//			setResult(RESULT_CANCELED);
//			finish();
            //account="";
            Intent intent=new Intent();
            //Intent intent = new Intent();
            intent.putExtra("content",Content);
            setResult(position, intent);
            finish();
            return true;
        } else if (keyCode == KeyEvent.KEYCODE_FOCUS || keyCode == KeyEvent.KEYCODE_CAMERA) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void getRegisterPhoneInfo(String result) {
        if (result.contains("true")){
                account=et_content_value.getText().toString();
                Intent intent=new Intent();
                intent.putExtra("content",account);
                Log.d("账号的值",account);
                setResult(position,intent);
                Log.d("position的值",position+"");
                CompanyUserValueActivity.this.finish();
        }else {
            Toast.makeText(CompanyUserValueActivity.this, "该手机号已经注册", Toast.LENGTH_SHORT).show();
        }

    }
}

package com.silianchuangye.sumao.success.fragments.myPlasticTrade.login.forgetPassword;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.utils.SuMaoConstant;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

public class RetrievePasswordActivity extends AppCompatActivity {
    private Button bt_Save;
    private EditText telephone_num;
    private EditText verification_code_num;
    private RelativeLayout child_title_bar;
    private ImageView iv_child_title_bar_back;
    private TextView tv_child_title_bar_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrieve_password);
        bt_Save= (Button) findViewById(R.id.submit);
        telephone_num= (EditText) findViewById(R.id.telephone_num);
        verification_code_num= (EditText) findViewById(R.id.verification_code_num);
        child_title_bar= (RelativeLayout) findViewById(R.id.title);
        iv_child_title_bar_back= (ImageView) findViewById(R.id.iv_child_title_bar_back);
        tv_child_title_bar_title= (TextView) findViewById(R.id.tv_child_title_bar_title);
        tv_child_title_bar_title.setText("找回密码");
        iv_child_title_bar_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RetrievePasswordActivity.this.finish();
            }
        });
        bt_Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //验证是否修改成功
                if (!telephone_num.getText().toString().equals(verification_code_num.getText().toString())){
                    Toast.makeText(RetrievePasswordActivity.this, "两次密码输入不一样", Toast.LENGTH_SHORT).show();
                }else{
                    new Thread(){
                        @Override
                        public void run() {
                            super.run();
                            getSuccess();
                        }
                    }.start();
                }
            }
        });


    }
    public void getSuccess(){
        RequestParams rp=new RequestParams(SuMaoConstant.SUMAO_IP+"/rest/model/atg/userprofiling/ProfileActor/phoneRetrievePwd");
        rp.addParameter("password",telephone_num.getText().toString());
        rp.addParameter("passwordConfirm",verification_code_num.getText().toString());
        x.http().post(rp, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.d("判断密码是否修改成功",result);
                if (result.contains("success")){
                    //密码修改成功
                    Log.d("准备跳转","准备跳转");
                    Intent intent=new Intent(RetrievePasswordActivity.this,ActivityPass.class);
                    startActivity(intent);
                }else{
                    //密码修改失败
                    Log.d("密码修改失败","密码修改失败");
                    Toast.makeText(RetrievePasswordActivity.this, "密码修改失败，请再次尝试", Toast.LENGTH_SHORT).show();
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

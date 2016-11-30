package com.silianchuangye.sumao.success.fragments.myPlasticTrade.personalInformation;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.playlog.internal.LogEvent;
import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.OrderManagement.SpotOrder.TiQu;
import com.silianchuangye.sumao.success.utils.Loding;
import com.silianchuangye.sumao.success.utils.SuMaoConstant;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

/**
 * Created by Administrator on 2016/5/9 0009.
 */
public class UserInformation extends Activity implements  View.OnClickListener{

            ImageView iv_title_bar_logo,iv_title_bar_back,iv_title_bar_service,iv_title_bar_search;
            Button sv_title_bar_serachView;
            TextView tv_title_bar_title;
            RelativeLayout title_user_information;

    TextView nu_information,mu_information,tu_information,ac_name;
    TextView tv_loginNum,tv_loginPassword,tv_loginName,tv_loginEmail,tv_loginPhoneNum;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_user_information);

            /**
             * 标题栏显示与隐藏的内容
             */
            title_user_information = ((RelativeLayout) findViewById(R.id.title_user_information));
            title_user_information.setBackgroundColor(getResources().getColor(R.color.textColor_expandable_listview_show));

            iv_title_bar_back = ((ImageView) findViewById(R.id.iv_title_bar_back));
            iv_title_bar_logo = ((ImageView) findViewById(R.id.iv_title_bar_logo));
            iv_title_bar_service = ((ImageView) findViewById(R.id.iv_title_bar_service));
            iv_title_bar_search = ((ImageView) findViewById(R.id.iv_title_bar_search));
            sv_title_bar_serachView = ((Button) findViewById(R.id.sv_title_bar_serachView));
            tv_title_bar_title  = ((TextView) findViewById(R.id.tv_title_bar_title));

            iv_title_bar_logo.setVisibility(View.INVISIBLE);
            iv_title_bar_service.setVisibility(View.INVISIBLE);
            sv_title_bar_serachView.setVisibility(View.INVISIBLE);
            iv_title_bar_search.setVisibility(View.INVISIBLE);
            iv_title_bar_back.setOnClickListener(this);
            tv_title_bar_title.setText("用户信息");
            tv_title_bar_title.setTextColor(Color.WHITE);
/**
 * 标题栏一下的内容
 */
            ac_name = ((TextView) findViewById(R.id.ac_name));
            nu_information = ((TextView) findViewById(R.id.nu_information));
            mu_information = ((TextView) findViewById(R.id.mu_information));
            tu_information = ((TextView) findViewById(R.id.tu_information));

            tv_loginNum= (TextView) findViewById(R.id.tv_loginNum);
            tv_loginPassword= (TextView) findViewById(R.id.tv_loginPassword);
            tv_loginName= (TextView) findViewById(R.id.tv_loginName);
            tv_loginPhoneNum= (TextView) findViewById(R.id.tv_loginPhoneNum);
            tv_loginEmail= (TextView) findViewById(R.id.tv_loginEmail);
            Loding.show(this,"正在请求网络",false,null);//网络请求之前调用
            sendNet();
        }

        @Override
        public void onClick(View v) {
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                switch (v.getId())
                {
                        case R.id.iv_title_bar_back:
                        finish();
                        break;

                        case R.id.name_user_information:
                        intent.setClass(this,ModifyName.class);
                        bundle.putString("receivingInformation","修改姓名");
                            bundle.putString("email",tv_loginEmail.getText().toString());
                            bundle.putString("phoneNum",tv_loginPhoneNum.getText().toString());
                        bundle.putBoolean("canBeAmpty",false);
                        intent.putExtras(bundle);
                        startActivityForResult(intent,1);
                        break;
                        case R.id.mailbox_user_information:
                        intent.setClass(this,ModifyName.class);
                        bundle.putString("receivingInformation","修改邮箱");
                            bundle.putString("name",tv_loginName.getText().toString());
                            bundle.putString("phoneNum",tv_loginPhoneNum.getText().toString());
                        bundle.putBoolean("canBeAmpty",true);
                        intent.putExtras(bundle);
                        startActivityForResult(intent,2);
                        break;
                        case R.id.telephone_user_information:
                        intent.setClass(this,ModifyTelephone.class);
                         intent.putExtra("name",tv_loginName.getText().toString());
                         intent.putExtra("email",tv_loginEmail.getText().toString());
                         intent.putExtra("phoneNum",tv_loginPhoneNum.getText().toString());
                        startActivityForResult(intent,0);
                        break;

                }
        }
        private void sendNet(){
            Log.e("TAG","用户信息");
            RequestParams params=new RequestParams(SuMaoConstant.SUMAO_IP+"/rest/model/atg/userprofiling/ProfileActor/appCustomerInfo");
            x.http().post(params, new Callback.CommonCallback<String>() {
                @Override
                public void onSuccess(String result) {
                    Loding.dis();//网络加载完成调用
                    Log.e("TAG","result====="+result);
                    try {
                        JSONObject job=new JSONObject(result);
                        String info=job.getString("info");
                        if(info.equals("fail")){
                            Toast.makeText(UserInformation.this,"请重新登陆",Toast.LENGTH_SHORT).show();
                            new TiQu(UserInformation.this).showLogin();
                            finish();
                        }
                        tv_loginNum.setText(job.getString("U_userid"));
                        tv_loginPassword.setText("********");
                        tv_loginName.setText(job.getString("U_uname"));
                        tv_loginEmail.setText(job.getString("U_email"));
                        tv_loginPhoneNum.setText(job.getString("U_phone"));
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
                    new Thread(){
                        @Override
                        public void run() {
                            super.run();
                            try {
                                Thread.sleep(1000);
                                Loding.dis();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }.start();

                }
            });
        }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK){
            Log.e("TAG","返回");
            sendNet();
        }
        if(resultCode==0){
            sendNet();
        }
    }
}


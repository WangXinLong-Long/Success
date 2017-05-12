package com.silianchuangye.sumao.success.fragments.myPlasticTrade.accountmeneger;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.utils.SuMaoConstant;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

public class KunlunJieyueActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView iv_child_title_bar_back;
    private TextView tv_child_title_bar_title;
    private Button bt_jieyue;
    private Button bt_jieyue_cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kunlun_jieyue);
        tv_child_title_bar_title= (TextView) findViewById(R.id.tv_child_title_bar_title);
        tv_child_title_bar_title.setText("账户解约");
        iv_child_title_bar_back= (ImageView) findViewById(R.id.iv_child_title_bar_back);
        iv_child_title_bar_back.setOnClickListener(this);
        bt_jieyue= (Button) findViewById(R.id.bt_jieyue);
        bt_jieyue.setOnClickListener(this);
        bt_jieyue_cancel= (Button) findViewById(R.id.bt_jieyue_cancel);
        bt_jieyue_cancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_child_title_bar_back:
                KunlunJieyueActivity.this.finish();
                break;
            case R.id.bt_jieyue:
                kunlunjieyue();
                break;
            case R.id.bt_jieyue_cancel:
                KunlunJieyueActivity.this.finish();
                break;
        }
    }
    public void kunlunjieyue(){
        String url= SuMaoConstant.SUMAO_IP+"/rest/model/atg/commerce/order/myorder/OrderPaymentInfo/kunLunoutSign";
        RequestParams rp=new RequestParams(url);
        x.http().post(rp, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                if (result.contains("success")){
                    Intent intent=new Intent(KunlunJieyueActivity.this,Account_Activity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(KunlunJieyueActivity.this, "解约失败!", Toast.LENGTH_SHORT).show();
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

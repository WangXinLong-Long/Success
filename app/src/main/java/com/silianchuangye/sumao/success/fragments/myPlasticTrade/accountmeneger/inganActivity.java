package com.silianchuangye.sumao.success.fragments.myPlasticTrade.accountmeneger;

import android.app.Activity;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.utils.SuMaoConstant;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

public class inganActivity extends AppCompatActivity {
    private int position;
    private RelativeLayout layout_bank;
    private RelativeLayout layout_bank_rig;
    private String money,available;
    private TextView monent_use_value;
    private TextView monent_value;
    private SharedPreferences sp;
    private String unique;
    private TextView tv_child_title_bar_title;
    private ImageView iv_child_title_bar_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingan);
        iv_child_title_bar_back= (ImageView) findViewById(R.id.iv_child_title_bar_back);
        tv_child_title_bar_title= (TextView) findViewById(R.id.tv_child_title_bar_title);
        tv_child_title_bar_title.setText("平安银行");
        monent_use_value= (TextView) findViewById(R.id.monent_use_value);
        monent_value= (TextView) findViewById(R.id.monent_value);
        layout_bank= (RelativeLayout) findViewById(R.id.layout_bank);
        layout_bank_rig= (RelativeLayout) findViewById(R.id.layout_bank_rig);
        Bundle bundle=getIntent().getExtras();
        position=bundle.getInt("position");
        sp=getSharedPreferences("sumao", Activity.MODE_PRIVATE);
        unique=sp.getString("unique","");
        new Thread(){
            @Override
            public void run() {
                super.run();
                getBankinfo();
            }
        }.start();
        iv_child_title_bar_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inganActivity.this.finish();
            }
        });

    }
    public void getBankinfo(){
        String url= SuMaoConstant.SUMAO_IP+"/rest/model/atg/commerce/order/myorder/OrderPaymentInfo/myaccountList";
        RequestParams rp=new RequestParams(url);
        rp.addParameter("bankId",position);
        rp.addParameter("_dynSessConf",unique);
        x.http().post(rp, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
               Log.d("平安银行的返回值",result);
                if (result.contains("money")){
                    layout_bank.setVisibility(View.VISIBLE);
                    layout_bank_rig.setVisibility(View.GONE);
                    try {
                        JSONObject jsonObject=new JSONObject(result);
                        money=jsonObject.getString("money");
                        available=jsonObject.getString("available");
                        monent_value.setText(money);
                        monent_use_value.setText(available);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }else {
                    layout_bank.setVisibility(View.GONE);
                    layout_bank_rig.setVisibility(View.VISIBLE);
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

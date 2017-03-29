package com.silianchuangye.sumao.success.fragments.myPlasticTrade.OrderManagement.SpotOrder.SpotOrderMVP.view.informationView;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.utils.LogUtils;
import com.silianchuangye.sumao.success.utils.SuMaoConstant;

/**
 * 合同信息
 */
public class ContractInformationActivity extends AppCompatActivity implements View.OnClickListener{

    private ImageView iv_child_title_bar_back;
    private TextView tv_child_title_bar_title;
    private WebView contract_web_view;
    private WebSettings settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contract_information);
        String contract = getIntent().getStringExtra("contract");
        iv_child_title_bar_back = ((ImageView) findViewById(R.id.iv_child_title_bar_back));
        iv_child_title_bar_back.setOnClickListener(this);
        tv_child_title_bar_title = ((TextView) findViewById(R.id.tv_child_title_bar_title));
        tv_child_title_bar_title.setText("合同信息");
        contract_web_view = ((WebView) findViewById(R.id.contract_web_view));
        settings = contract_web_view.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setBuiltInZoomControls(true);
        settings.setLoadWithOverviewMode(true);
        settings.setUseWideViewPort(true);

        contract_web_view.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                LogUtils.log("合同的地址是--->"+url);
                view.loadUrl(url);
                return true;
            }
        });
        contract_web_view.loadUrl(SuMaoConstant.SUMAO_CONTRACT_IP+contract);
        LogUtils.log(SuMaoConstant.SUMAO_CONTRACT_IP+contract);
        Log.d("合同的路径",SuMaoConstant.SUMAO_CONTRACT_IP+contract);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_child_title_bar_back:
                finish();
                break;
        }
    }
}

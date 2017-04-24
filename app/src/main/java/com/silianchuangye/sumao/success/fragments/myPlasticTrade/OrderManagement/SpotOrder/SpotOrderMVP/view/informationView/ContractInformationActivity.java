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
        //settings.setJavaScriptEnabled(true);
//        settings.setBuiltInZoomControls(true);
//        settings.setLoadWithOverviewMode(true);
//        settings.setUseWideViewPort(true);

//        contract_web_view.setWebViewClient(new WebViewClient(){
//            @Override
//            public boolean shouldOverrideUrlLoading(WebView view, String url) {
//                LogUtils.log("合同的地址是--->"+url);
//                view.loadUrl(url);
//                return true;
//            }
//        });
        //contract_web_view.loadUrl(SuMaoConstant.SUMAO_CONTRACT_IP+contract);

        contract_web_view.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);//设置js可以直接打开窗口，如window.open()，默认为false
        contract_web_view.getSettings().setJavaScriptEnabled(true);//是否允许执行js，默认为false。设置true时，会提醒可能造成XSS漏洞
        contract_web_view.getSettings().setSupportZoom(true);//是否可以缩放，默认true
        contract_web_view.getSettings().setBuiltInZoomControls(true);//是否显示缩放按钮，默认false
        contract_web_view.getSettings().setUseWideViewPort(true);//设置此属性，可任意比例缩放。大视图模式
        contract_web_view.getSettings().setLoadWithOverviewMode(true);//和setUseWideViewPort(true)一起解决网页自适应问题
        contract_web_view.getSettings().setAppCacheEnabled(true);//是否使用缓存
        contract_web_view.getSettings().setDomStorageEnabled(true);//DOM Storage
 //displayWebview.getSettings().setUserAgentString("User-Agent:Android");//设置用户代理，一般不用
        //contract_web_view.loadUrl("http://192.168.32.126:7003/crs/myaccount/profilePasswordChange.jsp?_requestid=258");
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

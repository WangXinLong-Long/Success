package com.silianchuangye.sumao.success.fragments.myPlasticTrade.accountmeneger.open;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.silianchuangye.sumao.success.R;

public class BankwebActivity extends AppCompatActivity {
    private WebView wbBank;
    private ImageView iv_child_title_bar_back;
    private TextView tv_child_title_bar_title;
    private String name="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bankweb);
        Bundle bundle=getIntent().getExtras();
        String url=bundle.getString("url");
        name=bundle.getString("name");
        wbBank= (WebView) findViewById(R.id.wbBank);
        tv_child_title_bar_title= (TextView) findViewById(R.id.tv_child_title_bar_title);
        if (name.equals("签约")) {
            tv_child_title_bar_title.setText("开通银行");
        }else{
            tv_child_title_bar_title.setText("银行出金");
        }
        iv_child_title_bar_back= (ImageView) findViewById(R.id.iv_child_title_bar_back);
        iv_child_title_bar_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        WebSettings webSettings = wbBank.getSettings();
        //设置WebView属性，能够执行Javascript脚本
        webSettings.setJavaScriptEnabled(true);
        //设置可以访问文件
        webSettings.setAllowFileAccess(true);
        //设置支持缩放
        webSettings.setBuiltInZoomControls(true);
        //加载需要显示的网页
        wbBank.loadUrl(url);
        //设置Web视图
       // wbBank.setWebViewClient(new webViewClient ());

    }
    //Web视图
    private class webViewClient extends WebViewClient {
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }
}

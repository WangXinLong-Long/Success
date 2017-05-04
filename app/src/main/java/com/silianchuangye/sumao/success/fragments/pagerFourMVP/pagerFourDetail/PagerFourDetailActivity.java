package com.silianchuangye.sumao.success.fragments.pagerFourMVP.pagerFourDetail;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.setting.ImageActivity;
import com.silianchuangye.sumao.success.fragments.pagerFourMVP.pagerFourDetail.bean.PagerFourDetailBean;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;

public class PagerFourDetailActivity extends AppCompatActivity implements View.OnClickListener{

    private WebView mWebView;
    private JavaScriptInterface javascriptInterface;
    private WebSettings webSettings;
    private PagerFourDetailBean pagerFourDetailBean;
    private String Result;
    private ImageView image_view;
    private TextView tv_timer;
    private TextView tv_liulan;
    private TextView tv_from;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pager_four_detail);
        tv_timer= (TextView) findViewById(R.id.time_text);
        tv_from= (TextView) findViewById(R.id.from_text_value);
        //tv_liulan= (TextView) findViewById(R.id.liulan_text_value);
        Bundle bundle=getIntent().getExtras();
        String fanhui=bundle.getString("result");
        Log.d("result的结果",fanhui);
        try {
            JSONObject jsonObject=new JSONObject(fanhui);
            Result=jsonObject.getString("content");
            tv_from.setText(jsonObject.getString("source"));
            //tv_timer.setText(jsonObject.getString(""));
            String createDate=jsonObject.getString("createDate");
            JSONObject object=new JSONObject(createDate);
            //SimpleDateFormat sdf =   new SimpleDateFormat(" yyyy-MM-dd HH:mm:ss " );
            //tv_timer.setText(sdf.format(object.getString("time")));
            tv_timer.setText(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Double.parseDouble(object.getString("time"))));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        image_view= (ImageView) findViewById(R.id.image_view);
        image_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PagerFourDetailActivity.this.finish();
            }
        });
//        Intent intent = getIntent();
        pagerFourDetailBean = (PagerFourDetailBean)  getIntent().getSerializableExtra("pagerFourDetailBean");
//        String haha = getIntent().getStringExtra("haha");
//        String pagerFourDetailBean = getIntent().getStringExtra("pagerFourDetailBean");
//        LogUtils.log("haha--->"+haha+"<---pagerFourDetailBean-->"+pagerFourDetailBean);
        TextView title = (TextView) findViewById(R.id.article_title);
        title.setVisibility(View.GONE);

        TextView time = (TextView) findViewById(R.id.time_text);
       // time.setText(""+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Double.parseDouble(pagerFourDetailBean.getUpdateDate().getTime())));

        TextView from = (TextView) findViewById(R.id.from_text);
      //  from.setText(pagerFourDetailBean.getSource());

        mWebView = (WebView) findViewById(R.id.webView);
        //webSettings.setJavaScriptEnabled(true);
        //initData();
        initView();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.image_view:
                finish();
                break;
        }
    }

    public void initView(){
        //支持javascript
        mWebView.getSettings().setJavaScriptEnabled(true);
// 设置可以支持缩放
        mWebView.getSettings().setSupportZoom(true);
// 设置出现缩放工具
        mWebView.getSettings().setBuiltInZoomControls(true);
//扩大比例的缩放
        mWebView.getSettings().setUseWideViewPort(true);
//自适应屏幕
        mWebView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        mWebView.getSettings().setLoadWithOverviewMode(true);
        mWebView.loadDataWithBaseURL(null,Result, "text/html", "utf-8", null);
    }
    //@SuppressLint({"JavascriptInterface", "SetJavaScriptEnabled"})
    private void initData() {
        //javascriptInterface = new JavaScriptInterface(this);
        webSettings = mWebView.getSettings();
        //是否支持js
        webSettings.setJavaScriptEnabled(true);
        // 设置支持缩放
        webSettings.setSupportZoom(true);
        //显示图片时自适应屏幕大小,但是4.4以前好用,4,4以后不好用
        //mWebView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        //weView中有链接,在当前 browser 中相应
        // 为图片添加放大缩小功能
        //mWebView.getSettings().setUseWideViewPort(true);

      //  mWebView.setInitialScale(70);   //100代表不缩放
       // mWebView.setWebViewClient(new MyWebViewClient());
        //设置进度条,处理提示框
        //mWebView.setWebChromeClient(new MyWebChromeClient());
        //添加监听
        //mWebView.addJavascriptInterface(javascriptInterface, "imagelistner");
        mWebView.loadDataWithBaseURL(null,Result, "text/html", "utf-8", null);

//        talkWithNet1();
       // talkWithNet2();

    }







    private class MyWebViewClient extends WebViewClient {

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            // html加载完成之后，添加监听图片的点击js函数
            addImageClickListner();
            imgReset();
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }


    /**
     * 循环遍历标签中的图片
     * js 语法
     */
    private void imgReset() {
        mWebView.loadUrl("javascript:(function(){" +
                "var objs = document.getElementsByTagName('img'); " +
                "for(var i=0;i<objs.length;i++)  " +
                "{"
                + "var img = objs[i];   " +
                "    img.style.maxWidth = '100%';   " +
                "}" +
                "})()");
    }

    // js通信接口
    public static class JavaScriptInterface {

        private Context context;

        public JavaScriptInterface(Context context) {
            this.context = context;
        }

        //点击图片回调方法
        //必须添加注解,否则无法响应
        @JavascriptInterface
        public void openImage(String img) {
            System.out.println(img);
            Log.i("TAG", "响应点击事件!");
            Intent intent = new Intent();
            intent.putExtra("image", img);
            intent.setClass(context, ImageActivity.class);
            context.startActivity(intent);
            System.out.println(img);
        }
    }

    /**
     * 设置进度条和提示框
     */
    private class MyWebChromeClient extends WebChromeClient {
        //该方法中可以设置进度条
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            super.onProgressChanged(view, newProgress);
        }

        //发方法中可以处理提示框
        @Override
        public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
            return super.onJsAlert(view, url, message, result);
        }

    }

    // 注入js函数监听
    private void addImageClickListner() {
        // 这段js函数的功能就是，遍历所有的img几点，并添加onclick函数，函数的功能是在图片点击的时候调用本地java接口并传递url过去
        mWebView.loadUrl("javascript:(function(){" +
                "var objs = document.getElementsByTagName(\"img\"); " +
                "for(var i=0;i<objs.length;i++)  " +
                "{"
                + "    objs[i].onclick=function()  " +
                "    {  "
                + "        window.imagelistner.openImage(this.src);  " +
                "    }  " +
                "}" +
                "})()");
    }

    //如果不作处理,点击 back 时整个 browser 会被 finish 掉
    //这样处理浏览器会回退,而不是退出浏览器
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && mWebView.canGoBack()) {
            mWebView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}

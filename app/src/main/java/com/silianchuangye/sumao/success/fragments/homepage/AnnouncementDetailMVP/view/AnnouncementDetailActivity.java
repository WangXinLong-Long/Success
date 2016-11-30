package com.silianchuangye.sumao.success.fragments.homepage.AnnouncementDetailMVP.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.webkit.JavascriptInterface;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.fragments.homepage.AnnouncementDetailMVP.bean.AnnouncementDetailBean;
import com.silianchuangye.sumao.success.fragments.homepage.AnnouncementDetailMVP.presenter.AnnouncementDetailPresenter;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.setting.ImageActivity;
import com.silianchuangye.sumao.success.utils.LogUtils;

import java.text.SimpleDateFormat;

/**
 * Created by Administrator on 2016/8/18 0018.
 */
public class AnnouncementDetailActivity extends AppCompatActivity implements IAnnouncementDetailView {

    private WebView mWebView;
    private AnnouncementDetailPresenter announcementDetailPresenter;
    private TextView release_date;
    private TextView source;
    private TextView title;
    private TextView tv_child_title_bar_title;
    private JavaScriptInterface javascriptInterface;
    private WebSettings webSettings;
    private String htmlcode;
    private String htmlcode1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_announcement);
        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        mWebView = ((WebView) findViewById(R.id.web_view));
        release_date = ((TextView) findViewById(R.id.release_date));
        source = ((TextView) findViewById(R.id.source));
        title = ((TextView) findViewById(R.id.title));
        tv_child_title_bar_title = ((TextView) findViewById(R.id.tv_child_title_bar_title));
        tv_child_title_bar_title.setText("公告详情");
        announcementDetailPresenter = new AnnouncementDetailPresenter(this);
        announcementDetailPresenter.getAnnouncementDetailInfoInAnnouncementDetailActivity(id);
    }

    @Override
    public void getHtmlCodeInActivity(AnnouncementDetailBean announcementDetailBean1) {
        htmlcode1 = announcementDetailBean1.getContent();
        source.setText(announcementDetailBean1.getAuthor());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 hh:mm:mm");
        release_date.setText(simpleDateFormat.format(Double.parseDouble(announcementDetailBean1.getCreationDate())));
        title.setText(announcementDetailBean1.getHeadline());
        initData();

    }


    @SuppressLint({"JavascriptInterface", "SetJavaScriptEnabled"})
    private void initData() {
        javascriptInterface = new JavaScriptInterface(this);
        webSettings = mWebView.getSettings();
        //是否支持js
        webSettings.setJavaScriptEnabled(true);
        // 设置支持缩放
        webSettings.setSupportZoom(true);
        //显示图片时自适应屏幕大小,但是4.4以前好用,4,4以后不好用
        //mWebView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        //weView中有链接,在当前 browser 中相应
        mWebView.setWebViewClient(new MyWebViewClient());
        //设置进度条,处理提示框
        mWebView.setWebChromeClient(new MyWebChromeClient());
        //添加监听
        mWebView.addJavascriptInterface(javascriptInterface, "imagelistner");

//        talkWithNet1();
        talkWithNet2();
    }


    private void talkWithNet2() {

        String content = htmlcode1;
        mWebView.loadDataWithBaseURL(null, /*bean.getData().getNewsContent()*/content, "text/html", "utf-8", null);

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

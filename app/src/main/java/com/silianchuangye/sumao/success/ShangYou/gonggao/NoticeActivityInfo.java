package com.silianchuangye.sumao.success.ShangYou.gonggao;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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
import com.silianchuangye.sumao.success.fragments.pagerFourMVP.pagerFourDetail.PagerFourDetailActivity;
import com.silianchuangye.sumao.success.utils.SuMaoConstant;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.text.SimpleDateFormat;

public class NoticeActivityInfo extends AppCompatActivity implements View.OnClickListener {
    private TextView tv_child_title_bar_title;
    private ImageView iv_child_title_bar_back;
    private TextView tv_title;
    private TextView tv_time;
    private WebView mWebView;
    private String Id;
    private SharedPreferences sp;
    private String unique;
    private String title, createtime, content;
    private JavaScriptInterface javascriptInterface;
    private WebSettings webSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice_activity_info);
        tv_child_title_bar_title = (TextView) findViewById(R.id.tv_child_title_bar_title);
        iv_child_title_bar_back = (ImageView) findViewById(R.id.iv_child_title_bar_back);
        iv_child_title_bar_back.setOnClickListener(this);
        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_time = (TextView) findViewById(R.id.tv_time);
        mWebView = (WebView) findViewById(R.id.web_news);
        Bundle bundle = getIntent().getExtras();
        Id = bundle.getString("id");
        sp = getSharedPreferences("sumao", Activity.MODE_PRIVATE);
        unique = sp.getString("unique", "");
        getinfo();
    }

    public void getinfo() {
        String url = SuMaoConstant.SUMAO_IP + "/rest/model/atg/commerce/userprofiling/PurchaseJurisdictionCheckActor/ItemLookupResult";
        RequestParams rp = new RequestParams(url);
        rp.addParameter("itemDescriptor", "announcement");
        rp.addParameter("id", Id);
        rp.addParameter("_dynSessConf", unique);
        x.http().post(rp, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.d("新闻的返回值", "" + result);
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    String element = jsonObject.getString("element");
                    //JSONObject jsonArray=new JSONObject(element);
                    //for (int i=0;i<jsonArray.length();i++){
                    JSONObject object = new JSONObject(element);
                    title = object.getString("name");
                    String time = object.getString("startDate");
                    JSONObject object_time = new JSONObject(time);
                    createtime = object_time.getString("time");
                    content = object.getString("content");
                    tv_child_title_bar_title.setText(title);
                    tv_title.setText(title);
                    tv_time.setText("发布时间:" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Double.parseDouble(createtime)));
                    initData(content);

                    //  }
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

            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_child_title_bar_back:
                NoticeActivityInfo.this.finish();
                break;

        }

    }

    @SuppressLint({"JavascriptInterface", "SetJavaScriptEnabled"})
    private void initData(String content) {
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
        mWebView.loadDataWithBaseURL(null, content, "text/html", "utf-8", null);

//        talkWithNet1();
        // talkWithNet2();

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
}

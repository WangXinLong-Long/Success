package com.silianchuangye.sumao.success.fragments.homepage.AnnouncementDetailMVP.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;

import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.fragments.homepage.AnnouncementDetailMVP.bean.AnnouncementDetailBean;
import com.silianchuangye.sumao.success.fragments.homepage.AnnouncementDetailMVP.presenter.AnnouncementDetailPresenter;
import com.silianchuangye.sumao.success.utils.LogUtils;

import java.text.SimpleDateFormat;

/**
 * Created by Administrator on 2016/8/18 0018.
 */
public class AnnouncementDetailActivity extends AppCompatActivity implements IAnnouncementDetailView{

    private WebView web_view;
    private AnnouncementDetailPresenter announcementDetailPresenter;
    private TextView release_date;
    private TextView source;
    private TextView title;
    private TextView tv_child_title_bar_title;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_announcement);
        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        web_view = ((WebView) findViewById(R.id.web_view));
        release_date = ((TextView) findViewById(R.id.release_date));
        source = ((TextView) findViewById(R.id.source));
        title = ((TextView) findViewById(R.id.title));
        tv_child_title_bar_title = ((TextView) findViewById(R.id.tv_child_title_bar_title));
        tv_child_title_bar_title.setText("公告详情");
        WebSettings webSettings = web_view.getSettings();
        webSettings.setJavaScriptEnabled(true);
//        webSettings.setSupportZoom(true);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setUseWideViewPort(true);
        announcementDetailPresenter = new AnnouncementDetailPresenter(this);
        announcementDetailPresenter.getAnnouncementDetailInfoInAnnouncementDetailActivity(id);
    }

    @Override
    public void getHtmlCodeInActivity(AnnouncementDetailBean announcementDetailBean1) {
        String htmlcode = announcementDetailBean1.getContent();
        web_view.loadDataWithBaseURL(null,htmlcode,"text/html","utf-8",null);
        source.setText(announcementDetailBean1.getAuthor());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 hh:mm:mm");
        release_date.setText(simpleDateFormat.format(Double.parseDouble(announcementDetailBean1.getCreationDate())));
        title.setText(announcementDetailBean1.getHeadline());

    }
}

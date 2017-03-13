package com.silianchuangye.sumao.success.fragments.pagerTwoMVP.PagerTwoView;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.silianchuangye.sumao.success.R;

public class NewsActivity extends AppCompatActivity {
    private TextView tv_Title;
    private TextView tv_Time;
    private TextView tv_Content;
    private TextView tv_child_title_bar_title;
    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        tv_child_title_bar_title= (TextView) findViewById(R.id.tv_child_title_bar_title);
        tv_Time= (TextView) findViewById(R.id.tv_Time);
        tv_Title= (TextView) findViewById(R.id.tv_Title);
        tv_Content= (TextView) findViewById(R.id.tv_Content);
        Bundle bundle=getIntent().getExtras();
        tv_child_title_bar_title.setText(bundle.getString("title"));
        tv_Title.setText(bundle.getString("title"));
        tv_Time.setText(bundle.getString("time"));
        tv_Content.setText(bundle.getString("title"));
        id=bundle.getString("id");
    }
}

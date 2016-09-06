package com.silianchuangye.sumao.success.fragments.homepage.sumaoconsultMVP;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.fragments.PagerOneMVP.bean.AnnounceBean;
import com.silianchuangye.sumao.success.fragments.PagerOneMVP.bean.AnnouncementBean;
import com.silianchuangye.sumao.success.fragments.homepage.AnnouncementDetailMVP.view.AnnouncementDetailActivity;

import java.util.ArrayList;
import java.util.List;

public class SuMaoConsult extends AppCompatActivity {

    private ListView cousult_listview;
    private TextView tv_child_title_bar_title;
    private List<AnnouncementBean> articles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_su_mao_consult);
        cousult_listview = ((ListView) findViewById(R.id.cousult_listview));
        tv_child_title_bar_title = ((TextView) findViewById(R.id.tv_child_title_bar_title));
        tv_child_title_bar_title.setText("塑贸资讯");
        List<String> title = new ArrayList<>();
        AnnounceBean announceBean = (AnnounceBean)getIntent().getSerializableExtra("announceBean");
        articles = announceBean.getArticles();
        for (int i = 0; i < articles.size(); i++) {
            title.add(articles.get(i).getHeadline());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,title);
        cousult_listview.setAdapter(adapter);
        cousult_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                intent.putExtra("id", articles.get(position).getId());
                intent.setClass(SuMaoConsult.this,AnnouncementDetailActivity.class);
                startActivity(intent);
            }
        });
    }
}

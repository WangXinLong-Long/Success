package com.silianchuangye.sumao.success.ShangYou;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.adapter.SearchSeePlanAdapter;

import java.util.ArrayList;
import java.util.List;

public class SearchSeePlan extends AppCompatActivity implements View.OnClickListener{
    String title;
    private ImageView img_back;
    private TextView tv_title;
    private ListView lv;
    private List<SearchSeePlanInfo> list;
    private SearchSeePlanAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_see_plan);
        title=getIntent().getStringExtra("bianhao");
        initDate();
        initView();
    }
    private void initView() {
        img_back= (ImageView) findViewById(R.id.img_search_see_plan_back);
        tv_title= (TextView) findViewById(R.id.tv_search_see_plan_title);
        tv_title.setText("计划编号 : "+title);
        lv= (ListView) findViewById(R.id.lv_search_see_plan);
        img_back.setOnClickListener(this);
        img_back.setOnClickListener(this);
        adapter=new SearchSeePlanAdapter(this,list);
        lv.setAdapter(adapter);
    }
    private void initDate() {
        list=new ArrayList<SearchSeePlanInfo>();
        SearchSeePlanInfo info1=new SearchSeePlanInfo();
        info1.date="2016-07-17";
        info1.name="兰州石化7042";
        info1.cangku="迅帮一号库";
        info1.num="50";
        info1.peisong="自提";
        info1.state="待确定";
        info1.dingdannum="000000111111111";
        info1.img_plan=R.mipmap.shangyouplan1;
        SearchSeePlanInfo info2=new SearchSeePlanInfo();
        info2.date="2016-07-17";
        info2.name="兰州石化7042";
        info2.cangku="迅帮一号库";
        info2.num="50";
        info2.peisong="自提";
        info2.state="已确定";
        info2.dingdannum="0000000000000";
        info2.img_plan=R.mipmap.shangyouplan2;
        list.add(info1);
        list.add(info2);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_search_see_plan_back:
                finish();
                break;
        }
    }
}

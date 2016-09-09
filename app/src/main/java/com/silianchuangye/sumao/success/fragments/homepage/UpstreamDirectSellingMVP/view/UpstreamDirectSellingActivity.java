package com.silianchuangye.sumao.success.fragments.homepage.UpstreamDirectSellingMVP.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.fragments.homepage.UpstreamDirectSellingMVP.UpstreamDirectSellingDetailMVP.UpstreamDirectSellingDetailsActivity;
import com.silianchuangye.sumao.success.fragments.homepage.UpstreamDirectSellingMVP.presenter.UpstreamDirectSellingPresenter;

import java.util.ArrayList;
import java.util.List;

public class UpstreamDirectSellingActivity extends AppCompatActivity implements IUpstreamDirectSellingView ,View.OnClickListener{

    private ListView upstream_listview;
    private UpstreamDirectSellingPresenter upstreamDirectSellingPresenter;
    private TextView tv_child_title_bar_title;
    private ImageView iv_child_title_bar_back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upstream_direct_selling);
        tv_child_title_bar_title = ((TextView) findViewById(R.id.tv_child_title_bar_title));
        tv_child_title_bar_title.setText("上游直销");
        upstreamDirectSellingPresenter = new UpstreamDirectSellingPresenter(this);
        upstream_listview = ((ListView) findViewById(R.id.upstream_listview));
        iv_child_title_bar_back = ((ImageView) findViewById(R.id.iv_child_title_bar_back));
        iv_child_title_bar_back.setOnClickListener(this);
        List<String> heh = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            heh.add("上游直销"+i);
        }
        ArrayAdapter<String> upstreamadapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,heh);
        upstream_listview.setAdapter(upstreamadapter);
        upstream_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                upstreamDirectSellingPresenter.getUpstreamDirectSellingDetail();
            }
        });
    }

    @Override
    public void getUpstreamDirectSellingDetailInfo() {
        Intent intent = new Intent();
        intent.setClass(this,UpstreamDirectSellingDetailsActivity.class);
        startActivity(intent);
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

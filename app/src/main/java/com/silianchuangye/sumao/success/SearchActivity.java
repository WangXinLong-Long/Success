package com.silianchuangye.sumao.success;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity {
    private GridView gv_More_Search;
    private ArrayAdapter<String> adapter;
    private List<String> list;
    private ImageView back;
    private TextView search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        init_GridView();
        back= (ImageView) findViewById(R.id.iv_Back_Search);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SearchActivity.this.finish();
            }
        });
        search= (TextView) findViewById(R.id.tv_Info_Search);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //进行搜索功能
            }
        });
    }
    public void init_GridView(){
        gv_More_Search= (GridView) findViewById(R.id.gv_More_Search);
        list=new ArrayList<String>();
        list.add("燕山石化7042");
        list.add("燕山石化7042");
        list.add("燕山石化7042");
        list.add("燕山石化7042");
        list.add("燕山石化7042");
        list.add("燕山石化");
        list.add("燕山石化7042");
        list.add("燕山石化7042");
        list.add("燕化");
        adapter=new ArrayAdapter<String>(this,R.layout.item_gridview_search,R.id.tv_item_More_Info_Search,list);
        gv_More_Search.setAdapter(adapter);


    }
}

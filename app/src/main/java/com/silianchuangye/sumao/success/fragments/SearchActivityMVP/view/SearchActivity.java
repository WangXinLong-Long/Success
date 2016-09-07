package com.silianchuangye.sumao.success.fragments.SearchActivityMVP.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.fragments.SearchActivityMVP.bean.SearchActivityBean;
import com.silianchuangye.sumao.success.fragments.SearchActivityMVP.presenter.SearchActivityPresenter;
import com.silianchuangye.sumao.success.fragments.type.view.TypeInfoActivity;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity implements ISearchActivityView  {
    private GridView gv_More_Search;
    private ArrayAdapter<String> adapter;
    private List<String> list;
    private ImageView back;
    private TextView search;
    private SearchActivityPresenter searchActivityPresenter;
    private EditText search_content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        searchActivityPresenter = new SearchActivityPresenter(SearchActivity.this);
        init_GridView();
        back= (ImageView) findViewById(R.id.iv_Back_Search);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SearchActivity.this.finish();
            }
        });
        search= (TextView) findViewById(R.id.tv_Info_Search);
        search_content = ((EditText) findViewById(R.id.search_content));

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //进行搜索功能,跳到现货界面

                searchActivityPresenter.sendSearchActivityData(search_content.getText().toString().trim());
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

    @Override
    public void getSearchActivityData(SearchActivityBean searchActivityBean) {
        Intent intent=new Intent(SearchActivity.this, TypeInfoActivity.class);
        intent.putExtra("searchActivityBean",searchActivityBean);
        intent.putExtra("Ntt",search_content.getText().toString().trim());
        startActivity(intent);
    }
}

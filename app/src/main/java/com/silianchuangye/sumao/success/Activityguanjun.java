package com.silianchuangye.sumao.success;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.LinkedList;
import java.util.List;

public class Activityguanjun extends AppCompatActivity {
    private PullToRefreshListView listDemo;
    private List<String> list;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activityguanjun);
        //guanjun17
        //1234567
        //提交一下
        //怎么提交失败呢？
        //1234568989///
        ////////////////////////
        listDemo= (PullToRefreshListView) findViewById(R.id.pre_sale_listView);
        list=new LinkedList<String>();
        for (int i=0;i<18;i++){
            list.add("测试数据"+i);
        }
        adapter=new ArrayAdapter(Activityguanjun.this,R.layout.item_type_show,R.id.tv_type_item,list);
        listDemo.setAdapter(adapter);
        listDemo.setMode(PullToRefreshBase.Mode.BOTH);
//        listDemo.setOnRefreshListener(
////                onRefresh(){
////
////        }
//        );
//        listDemo.setOnRefreshListener(onRefresh(){
//
//        });



    }
}

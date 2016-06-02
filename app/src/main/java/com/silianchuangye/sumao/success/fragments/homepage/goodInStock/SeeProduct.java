package com.silianchuangye.sumao.success.fragments.homepage.goodInStock;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.silianchuangye.sumao.success.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/6/2.
 * 浏览记录
 */
public class SeeProduct extends Activity implements AdapterView.OnItemClickListener,View.OnClickListener{
    private ImageView img_like_product_back;
    private ListView lv_like_product;
    private List<String> list=new ArrayList<String>();
    private ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_like_product);
        initDate();
        initView();
    }

    private void initDate() {
        for(int i=0;i<20;i++){
            list.add("浏览记录"+i);
        }
    }

    private void initView() {
        img_like_product_back= (ImageView) findViewById(R.id.img_activity_like_product_back);
        img_like_product_back.setOnClickListener(this);
        lv_like_product= (ListView) findViewById(R.id.lv_activity_like_priduct);

        adapter=new ArrayAdapter<String>(this,R.layout.item_like_product_view,R.id.tv_item_like_product,list);
        lv_like_product.setAdapter(adapter);

        lv_like_product.setOnItemClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(R.id.img_activity_like_product_back==v.getId()){
            finish();
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(SeeProduct.this,"点击了第"+position+"条数据",Toast.LENGTH_SHORT).show();
    }
}

package com.silianchuangye.sumao.success.fragments.homepage.goodInStock;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.adapter.PreSaleAdapter;
import com.silianchuangye.sumao.success.fragments.homepage.goodInStock.GoodsInStockActivityMVP.bean.SMCl;
import com.silianchuangye.sumao.success.fragments.homepage.goodInStock.GoodsInStockDetailActivityMVP.view.GoodsInStockDetailActivity;

import java.util.ArrayList;
import java.util.List;

/*
相似产品界面
 */
public class LikeProduct extends AppCompatActivity implements View.OnClickListener,AdapterView.OnItemClickListener{
private ImageView img_like_product_back;
    private ListView lv_like_product;
    private List<String> list=new ArrayList<String>();
    PreSaleAdapter adapter;
    List<SMCl> lists;
    SMCl preSaleModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_like_product);
        initdata();
        initView();
    }

    private void initdata() {

        lists = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            preSaleModel = new SMCl();
//            preSaleModel.setCompany("北京公司" + i + "分公司");
//            preSaleModel.setWarehouse(i + "仓库");
//            preSaleModel.setName("产品" + i);
//            preSaleModel.setNumber(i + "");
//            preSaleModel.setPrice(i * 100 + "");
//            preSaleModel.setProductType("现货");
            lists.add(preSaleModel);
        }
        adapter = new PreSaleAdapter(this, lists);

    }

    private void initView() {
        img_like_product_back= (ImageView) findViewById(R.id.img_activity_like_product_back);
        img_like_product_back.setOnClickListener(this);
        lv_like_product= (ListView) findViewById(R.id.lv_activity_like_priduct);
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
        Toast.makeText(LikeProduct.this,"点击了第"+position+"条数据",Toast.LENGTH_SHORT).show();
        Intent intent = new Intent();
        intent.setClass(this, GoodsInStockDetailActivity.class);
        startActivity(intent);
    }
}

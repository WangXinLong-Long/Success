package com.silianchuangye.sumao.success.fragments.homepage.goodInStock.SeeProductMVP.view;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.adapter.PreSaleAdapter;
import com.silianchuangye.sumao.success.adapter.SeeProductAdapter;
import com.silianchuangye.sumao.success.fragments.homepage.goodInStock.GoodsInStockActivityMVP.bean.SMCl;
import com.silianchuangye.sumao.success.fragments.homepage.goodInStock.GoodsInStockDetailActivityMVP.view.GoodsInStockDetailActivity;
import com.silianchuangye.sumao.success.fragments.homepage.goodInStock.SeeProductMVP.bean.RecentlyViewedProduct;
import com.silianchuangye.sumao.success.fragments.homepage.goodInStock.SeeProductMVP.bean.SeeProductBean;
import com.silianchuangye.sumao.success.fragments.homepage.goodInStock.SeeProductMVP.presenter.SeeProductPresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/6/2.
 * 浏览记录
 */
public class SeeProduct extends Activity implements AdapterView.OnItemClickListener, View.OnClickListener ,ISeeProductView{
    private ImageView img_like_product_back;
    private ListView lv_like_product;
    List<RecentlyViewedProduct> lists;
    SMCl preSaleModel;
    SeeProductPresenter seeProductPresenter;
    SeeProductAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_product);

        SharedPreferences sp = getSharedPreferences("sumao",MODE_PRIVATE);
        String sessioinId = sp.getString("unique","");
        lists = new ArrayList<>();
        initView();
        seeProductPresenter = new SeeProductPresenter(this);
        seeProductPresenter.getSeeProductInfo(sessioinId);
    }



    private void initView() {
        img_like_product_back = (ImageView) findViewById(R.id.img_activity_like_product_back);
        img_like_product_back.setOnClickListener(this);
        lv_like_product = (ListView) findViewById(R.id.lv_activity_like_priduct);
        lv_like_product.setAdapter(adapter);
        lv_like_product.setOnItemClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (R.id.img_activity_like_product_back == v.getId()) {
            finish();
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(SeeProduct.this, "点击了第" + position + "条数据", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent();
        intent.putExtra("cl_id", lists.get(position).getCl_id());
        intent.setClass(this, GoodsInStockDetailActivity.class);
        startActivity(intent);
    }

    @Override
    public void setDataInSeeProductActiity(SeeProductBean seeProductBean) {
        lists.addAll(seeProductBean.getRecentlyViewedProduct());
        adapter = new SeeProductAdapter(this,lists);
        lv_like_product.setAdapter(adapter);

    }
}

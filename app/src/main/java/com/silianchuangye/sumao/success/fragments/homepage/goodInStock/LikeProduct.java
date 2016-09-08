package com.silianchuangye.sumao.success.fragments.homepage.goodInStock;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.adapter.LikeProductAdapter;
import com.silianchuangye.sumao.success.adapter.PreSaleAdapter;
import com.silianchuangye.sumao.success.fragments.homepage.goodInStock.GoodsInStockActivityMVP.bean.SMCl;
import com.silianchuangye.sumao.success.fragments.homepage.goodInStock.GoodsInStockDetailActivityMVP.bean.RelatedProduct;
import com.silianchuangye.sumao.success.fragments.homepage.goodInStock.GoodsInStockDetailActivityMVP.bean.SimilarProduct;
import com.silianchuangye.sumao.success.fragments.homepage.goodInStock.GoodsInStockDetailActivityMVP.view.GoodsInStockDetailActivity;
import com.silianchuangye.sumao.success.utils.LogUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/*
相似产品界面
 */
public class LikeProduct extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {
    private ImageView img_like_product_back;
    private PullToRefreshListView lv_like_product;
    private List<String> list = new ArrayList<String>();
    LikeProductAdapter adapter;
    List<SimilarProduct> lists;
    SMCl preSaleModel;
    private RelatedProduct relatedProduct;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_like_product);
        Intent intent = getIntent();

        relatedProduct = (RelatedProduct) intent.getSerializableExtra("relatedProduct");
        for (int i = 0; i < relatedProduct.getSimilarProduct().size(); i++) {
            LogUtils.log("relatedProduct" + relatedProduct.getSimilarProduct().get(i).getCl_id());

        }
        lists = new ArrayList<>();
        initdata();
        initView();

    }

    private void initdata() {
        lists.addAll(relatedProduct.getSimilarProduct());

        adapter = new LikeProductAdapter(this, lists);

    }

    private void initView() {
        img_like_product_back = (ImageView) findViewById(R.id.img_activity_like_product_back);
        img_like_product_back.setOnClickListener(this);
        lv_like_product = (PullToRefreshListView) findViewById(R.id.lv_activity_like_priduct);
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
        Toast.makeText(LikeProduct.this, "点击了第" + position + "条数据", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent();
        intent.putExtra("cl_id", relatedProduct.getSimilarProduct().get(position).getCl_id());
        intent.setClass(this, GoodsInStockDetailActivity.class);
        startActivity(intent);
    }

}

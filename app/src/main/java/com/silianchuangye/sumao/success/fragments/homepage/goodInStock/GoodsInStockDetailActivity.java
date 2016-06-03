package com.silianchuangye.sumao.success.fragments.homepage.goodInStock;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.silianchuangye.sumao.success.MainActivity;
import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.custom.customCalendar.CalendarView;
import com.silianchuangye.sumao.success.custom.customCalendar.DayAndPrice;
import com.silianchuangye.sumao.success.fragments.homepage.auction.VesselThreeActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/6/1 0001.
 */
public class GoodsInStockDetailActivity extends Activity implements View.OnClickListener{
    CalendarView calendarView;
    ImageView title_bar_white_back;
    TextView title_bar_white_title;
    ImageView title_bar_white_shopping_cart;
    RelativeLayout pre_sale_sale_detail_detail;
    RelativeLayout pre_sale_sale_detail_similar_product,pre_sale_sale_detail_similar_liulan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_in_stock_detail);

        title_bar_white_back = ((ImageView) findViewById(R.id.title_bar_white_back));
        title_bar_white_title = ((TextView) findViewById(R.id.title_bar_white_title));
        title_bar_white_shopping_cart = ((ImageView) findViewById(R.id.title_bar_white_shopping_cart));
        pre_sale_sale_detail_detail = ((RelativeLayout) findViewById(R.id.pre_sale_sale_detail_detail));
        pre_sale_sale_detail_similar_product = ((RelativeLayout) findViewById(R.id.pre_sale_sale_detail_similar_product));
        pre_sale_sale_detail_similar_liulan= (RelativeLayout) findViewById(R.id.pre_sale_sale_detail_similar_liulan);
        title_bar_white_back.setOnClickListener(this);
        title_bar_white_shopping_cart.setOnClickListener(this);
        pre_sale_sale_detail_detail.setOnClickListener(this);
        pre_sale_sale_detail_similar_product.setOnClickListener(this);
        pre_sale_sale_detail_similar_liulan.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId())
        {
            case R.id.title_bar_white_back:
                finish();
                break;
            case R.id.title_bar_white_shopping_cart:

                intent.setClass(GoodsInStockDetailActivity.this, MainActivity.class);
                intent.putExtra("cart",1);
                startActivity(intent);
                break;
            case R.id.pre_sale_sale_detail_detail:
                intent.setClass(GoodsInStockDetailActivity.this, VesselThreeActivity.class);
                startActivity(intent);
                break;
            case R.id.pre_sale_sale_detail_similar_product:
               Toast.makeText(this,"相似产品",Toast.LENGTH_SHORT).show();
                intent.setClass(GoodsInStockDetailActivity.this, LikeProduct.class);
                startActivity(intent);
                break;
            case R.id.pre_sale_sale_detail_similar_liulan:
                Toast.makeText(this,"相似产品",Toast.LENGTH_SHORT).show();
                intent.setClass(GoodsInStockDetailActivity.this, SeeProduct.class);
                startActivity(intent);
                break;
        }
    }
}

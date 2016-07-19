package com.silianchuangye.sumao.success.fragments.SellerManagementPlatform.acutionManagement;


import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;

import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v7.widget.ActionBarOverlayLayout;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.google.android.gms.common.api.GoogleApiClient;
import com.silianchuangye.sumao.success.R;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class SearchPriceActivity extends AppCompatActivity {
    private ImageView iv_back, iv_search;
    private ListView lv_Demo;
    private List<Map<String, Object>> list;
    private SimpleAdapter adapter;
    private RelativeLayout layout_f;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_price);
        init();
        addData();
        event();

    }

    /**
     * 初始化数据
     */
    public void init() {
        iv_back = (ImageView) findViewById(R.id.iv_Back_Price_Search);
        iv_search = (ImageView) findViewById(R.id.iv_search);
        lv_Demo = (ListView) findViewById(R.id.lv_Price_Search);
        list = new ArrayList<Map<String, Object>>();
        layout_f= (RelativeLayout) findViewById(R.id.layout_f);
    }

    /**
     * 添加数据
     */
    public void addData() {
        for (int i = 0; i <= 4; i++) {
            Map<String, Object> map1 = new Hashtable<String, Object>();
            map1.put("name", "兰州石化7042");
            map1.put("state", "公开竞拍");
            map1.put("time", "结束时间 2016-07-18 12:00:00");
            map1.put("firmname", "兰州石化7042");
            map1.put("type", "默认");
            map1.put("min", "1吨");
            map1.put("max", "5吨");
            map1.put("way", "自提");
            map1.put("address", "河南安阳");
            map1.put("cangku", "迅邦1号仓库");
            map1.put("number", "6100元");
            map1.put("count", "100吨");
            list.add(map1);

        }

        adapter = new SimpleAdapter(SearchPriceActivity.this, list, R.layout.item_price_search,
                new String[]{"name", "state", "time", "firmname", "type", "min", "max", "way", "address", "cangku", "number", "count"},
                new int[]{R.id.tv_price_search_item,
                        R.id.tv_price_search_type,
                        R.id.tv_time_price_search,
                        R.id.tv_price_search_name_firm,
                        R.id.tv_price_search_type_firm,
                        R.id.tv_price_search_min,
                        R.id.tv_price_search_max,
                        R.id.tv_price_search_way,
                        R.id.tv_price_search_address,
                        R.id.tv_price_search_cangku,
                        R.id.tv_price_search_number,
                        R.id.tv_price_search_count}
        );
        lv_Demo.setAdapter(adapter);

    }

    /**
     * 事件监听
     */
    public void event() {
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SearchPriceActivity.this.finish();
            }
        });
        iv_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // backgroundAlpha(0.5f);
                //弹出popupwindow
                popupWindow_toShow();
            }
        });
        /**
         * ListView的item的点击事件
         */
        lv_Demo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //跳转到详情界面
               // Intent intent = new Intent(SearchPriceActivity.this, PriceSearchinfoActivity.class);
                //startActivity(intent);
                if (position%2==0){
                     Intent intent = new Intent(SearchPriceActivity.this, PriceSearchinfoActivity.class);
                    startActivity(intent);
                }else{
                     Intent intent = new Intent(SearchPriceActivity.this, AcutionResultActivity.class);
                    startActivity(intent);

                }
            }
        });

    }

    /**
     * popupwindow
     */

    public void popupWindow_toShow() {
        View view=View.inflate(SearchPriceActivity.this,R.layout.item_popupwindow_search,null);
        PopupWindow popupWindow=new PopupWindow(findViewById(R.id.layout_e), LinearLayoutCompat.LayoutParams.MATCH_PARENT, LinearLayoutCompat.LayoutParams.WRAP_CONTENT);
        Log.d("宽",popupWindow.getWidth()+"");
        Log.d("高",popupWindow.getHeight()+"");
        setContentView(view);
    }
    //设置背景透明
    public void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        getWindow().setAttributes(lp);
    }



}

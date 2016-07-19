package com.silianchuangye.sumao.success.fragments.SellerManagementPlatform.acutionManagement;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.silianchuangye.sumao.success.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AcutionResultActivity extends AppCompatActivity {
    private ImageView iv_Back,iv_Search;
    private ListView lv_acution_result;
    private List<Map<String,Object>> list;
    private SimpleAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acution_result);
        init();
        event();
        addDate();
    }
    public void init(){
        iv_Back= (ImageView) findViewById(R.id.iv_Back_acution_Result);
        iv_Search= (ImageView) findViewById(R.id.iv_auction_result_search);
        lv_acution_result= (ListView) findViewById(R.id.lv_acution_result);
        list=new ArrayList<Map<String,Object>>();

    }
    public void event(){
        iv_Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AcutionResultActivity.this.finish();
            }
        });

        iv_Search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**
                 * 显示popupwindow
                 */

            }
        });
        lv_acution_result.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (list.get(position).get("isok").toString().equals("竞拍成功")){
                    /**
                     * 跳转到竞拍成功界面
                     */
                    Intent intent=new Intent(AcutionResultActivity.this,AuctionSuccessActivity.class);
                    startActivity(intent);
                }else if (list.get(position).get("isok").toString().equals("竞拍失败")){
                    /**
                     * 跳转到竞拍失败的界面
                     */
                    Intent intent=new Intent(AcutionResultActivity.this,AcutionFailActivity.class);
                    startActivity(intent);
                }
            }
        });

    }
    public void addDate(){
        for (int i=0;i<=5;i++){
            Map<String,Object> map=new HashMap<String,Object>();
            map.put("name","兰州石化6038");
            if (i%2==0){
              map.put("isok","竞拍成功");

            }else{
              map.put("isok","竞拍失败");
            }
            map.put("firm_name","燕山石化7032");
            map.put("state","公开竞拍");
            map.put("price","10000元");
            map.put("number","200吨");
            map.put("min","1吨");
            map.put("max","1吨");
            map.put("way","自提");
            map.put("address","河南安阳");
            map.put("cangku","迅邦物流1号库");
            list.add(map);
        }
        adapter=new SimpleAdapter(AcutionResultActivity.this,list,R.layout.item_auction_result,
                 new String[]{"name","isok","firm_name","state","price","number",
                              "min","max","way","address","cangku"
                 },new int[]{
                R.id.tv_price_search_item,
                R.id.tv_isok,
                R.id.tv_price_search_name_firm,
                R.id.tv_price_search_type_firm,
                R.id.tv_price_search_number,
                R.id.tv_price_search_count,
                R.id.tv_price_search_min,
                R.id.tv_price_search_max,
                R.id.tv_price_search_way,
                R.id.tv_price_search_address,
                R.id.tv_price_search_cangku

        }
                );
        lv_acution_result.setAdapter(adapter);


    }
}

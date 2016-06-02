package com.silianchuangye.sumao.success.fragments.homepage.auction;

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
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

public class VesselTwoActivity extends AppCompatActivity {
    private ListView lv_vessel_tow;
    private List<Map<String,Object>> list;
    private ImageView ivBack_vessel_tow;
    private ImageView ivShop_vessel_tow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vessel_two);
        lv_vessel_tow= (ListView) findViewById(R.id.lv_vessel_tow);
        list=new ArrayList<Map<String,Object>>();
        Map<String,Object> map1=new Hashtable<String,Object>();
        map1.put("text","支付保证金比例");
        map1.put("value","10%");
        list.add(map1);

        Map<String,Object> map2=new Hashtable<String,Object>();
        map2.put("text","支付保证金截止时间");
        map2.put("value","2015-02-09 12:00:00");
        list.add(map2);
        Map<String,Object> map3=new Hashtable<String,Object>();
        map3.put("text","支付保证金金额");
        map3.put("value","1000");
        list.add(map3);
        Map<String,Object> map4=new Hashtable<String,Object>();
        map4.put("text","仓库");
        map4.put("value","金山广安库");
        list.add(map4);
        Map<String,Object> map5=new Hashtable<String,Object>();
        map5.put("text","详细地址");
        map5.put("value","金山石化沪杭公路夏盛路38号");
        list.add(map5);
        Map<String,Object> map6=new Hashtable<String,Object>();
        map6.put("text","交货时间");
        map6.put("value","2016-05-19 00:00:00 至2016-05-31 00:00:00");
        list.add(map6);
        Map<String,Object> map7=new Hashtable<String,Object>();
        map7.put("text","竞价方式");
        map7.put("value","公开竞价");
        list.add(map7);
        Map<String,Object> map8=new Hashtable<String,Object>();
        map8.put("text","竞价时间");
        map8.put("value","2016-05-19 00:00:00 至2016-05-31 00:00:00");
        list.add(map8);
        Map<String,Object> map9=new Hashtable<String,Object>();
        map9.put("text","竞价规则");
        map9.put("value","拍卖过程中，竞拍产品的价格按最小加价单位递增，买方可多次出价并能实时获取市场信息...");
        list.add(map9);
        Map<String,Object> map10=new Hashtable<String,Object>();
        map10.put("text","允许延时");
        map10.put("value","是");
        list.add(map10);
        Map<String,Object> map11=new Hashtable<String,Object>();
        map11.put("text","延长时间");
        map11.put("value","4分钟");
        list.add(map11);
        Map<String,Object> map12=new Hashtable<String,Object>();
        map12.put("text","延时规则");
        map12.put("value","在剩余数量小于最小购买数量时，竞价进入倒计时， \n" +
                "当新报价打破本次倒计时触发时的有效价格排序， \n" +
                "再次触发倒计时 \n" +
                "4 分钟 ");
        list.add(map12);
        Map<String,Object> map13=new Hashtable<String,Object>();
        map13.put("text","成交价格判定");
        map13.put("value","按自报价");
        list.add(map13);

        SimpleAdapter adapter=new SimpleAdapter(this,list,R.layout.item_vessel_tow,new String[]{"text","value"},new int[]{R.id.tv_a,R.id.tv_b});
        lv_vessel_tow.setAdapter(adapter);

        ivBack_vessel_tow= (ImageView) findViewById(R.id.ivBack_vessel_two);
        ivBack_vessel_tow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VesselTwoActivity.this.finish();
            }
        });
        ivBack_vessel_tow= (ImageView) findViewById(R.id.ivShop_vessel_two);
        ivBack_vessel_tow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转购物车界面
            }
        });
        lv_vessel_tow.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(VesselTwoActivity.this,VlaueActivity.class);
                intent.putExtra("text",list.get(position).get("text").toString());
                intent.putExtra("value",list.get(position).get("value").toString());
                startActivity(intent);
            }
        });


    }
}

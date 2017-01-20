package com.silianchuangye.sumao.success.fragments.homepage.groupbuying;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;

import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.fragments.homepage.auction.VlaueActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

public class RuleActivity extends AppCompatActivity {
    private ImageView ivBack, ivSearch;
    private ListView lv_rule;
    private SimpleAdapter adapter;
    private List<Map<String, Object>> list;
    private String state;
    private RelativeLayout layout_not_action, layout_Bottom_action, relative_tuangou_end;
    private String bili, jiezhishijian, can_gku, xiangxi, kaishishijian, jieshushijian, tuan_start, tuan_end;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rule);
        init();
        event();
        addData();
    }

    public void init() {
        layout_not_action = (RelativeLayout) findViewById(R.id.layout_not_action);
        layout_Bottom_action = (RelativeLayout) findViewById(R.id.layout_Bottom_action);
        relative_tuangou_end = (RelativeLayout) findViewById(R.id.relative_tuangou_end);
        ivBack = (ImageView) findViewById(R.id.ivBack);
        ivSearch = (ImageView) findViewById(R.id.gouwuche);
        lv_rule = (ListView) findViewById(R.id.lv_rule);
        list = new ArrayList<Map<String, Object>>();
        Bundle bundle = getIntent().getExtras();
        bili = bundle.getString("bili");
        state = bundle.getString("state");
        jiezhishijian = bundle.getString("jiezhi");
        can_gku = bundle.getString("cangku");
        xiangxi = bundle.getString("xiangxi");
        kaishishijian = bundle.getString("kaishi");
        Log.d("开始时间", kaishishijian);
        jieshushijian = bundle.getString("jieshu");
        tuan_start = bundle.getString("tuan_start");
        tuan_end = bundle.getString("tuan_end");
        if (state.equals("团购未开始")) {
            layout_not_action.setVisibility(View.VISIBLE);
            layout_Bottom_action.setVisibility(View.GONE);
            relative_tuangou_end.setVisibility(View.GONE);


        } else if (state.equals("团购已开始")) {
            layout_Bottom_action.setVisibility(View.GONE);
            layout_not_action.setVisibility(View.GONE);
            relative_tuangou_end.setVisibility(View.GONE);

        } else if (state.equals("团购已结束")) {
            relative_tuangou_end.setVisibility(View.VISIBLE);
            layout_Bottom_action.setVisibility(View.GONE);
            layout_not_action.setVisibility(View.GONE);

        }

    }

    public void event() {
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RuleActivity.this.finish();
            }
        });

    }

    public void addData() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat
                ("yyyy年MM月dd日 hh:mm:mm");
        String kaishi_data = simpleDateFormat.format(Double.parseDouble(kaishishijian));
        String jieshu_data ;
        if (jieshushijian.equals("")){
            jieshu_data = "";
        }else {
            jieshu_data = simpleDateFormat.format(Double.parseDouble(jieshushijian));
        }
         jieshu_data = simpleDateFormat.format(Double.parseDouble(jieshushijian));
        String tuan_kaishi = simpleDateFormat.format(Double.parseDouble(tuan_start));
        String tuan_jieshu = simpleDateFormat.format(Double.parseDouble(tuan_end));
        Map<String, Object> map1 = new Hashtable<String, Object>();
        map1.put("left", "保证金比例");
        map1.put("right", bili);
        list.add(map1);
        Map<String, Object> map2 = new Hashtable<String, Object>();
        map2.put("left", "保证金支付截至时间");
        map2.put("right", jiezhishijian);
        list.add(map2);
        Map<String, Object> map3 = new Hashtable<String, Object>();
        map3.put("left", "仓库");
        map3.put("right", can_gku);
        list.add(map3);
        Map<String, Object> map4 = new Hashtable<String, Object>();
        map4.put("left", "详细地址");
        map4.put("right", xiangxi);
        list.add(map4);
        Map<String, Object> map5 = new Hashtable<String, Object>();
        map5.put("left", "交货时间");
        map5.put("right", kaishi_data + "至" + jieshu_data);
        list.add(map5);
        Map<String, Object> map6 = new Hashtable<String, Object>();
        map6.put("left", "交易模式");
        map6.put("right", "团购");
        list.add(map6);///
        Map<String, Object> map7 = new Hashtable<String, Object>();
        map7.put("left", "团购时间");
        map7.put("right", tuan_kaishi + "至" + tuan_jieshu);
        list.add(map7);
        Map<String, Object> map8 = new Hashtable<String, Object>();
        map8.put("left", "团购规则");
        map8.put("right", "~~");
        list.add(map8);
        //////////////////////
        adapter = new SimpleAdapter(this, list, R.layout.item_vessel_tow, new String[]{"left", "right"}, new int[]{R.id.tv_a, R.id.tv_b});
        lv_rule.setAdapter(adapter);
        lv_rule.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(RuleActivity.this, VlaueActivity.class);
                intent.putExtra("text", list.get(position).get("left").toString());
                intent.putExtra("value", list.get(position).get("right").toString());
                startActivity(intent);
            }
        });

    }
}

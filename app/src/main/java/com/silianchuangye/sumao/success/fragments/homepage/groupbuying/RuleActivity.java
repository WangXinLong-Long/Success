package com.silianchuangye.sumao.success.fragments.homepage.groupbuying;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;

import com.silianchuangye.sumao.success.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

public class RuleActivity extends AppCompatActivity {
    private ImageView ivBack,ivSearch;
    private ListView lv_rule;
    private SimpleAdapter adapter;
    private List<Map<String,Object>> list;
    private String bili,jiezhishijian,can_gku,xiangxi,kaishishijian,jieshushijian,tuan_start,tuan_end;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rule);
        init();
        event();
        addData();
    }
    public void init(){
        ivBack= (ImageView) findViewById(R.id.ivBack);
        ivSearch= (ImageView) findViewById(R.id.gouwuche);
        lv_rule= (ListView) findViewById(R.id.lv_rule);
        list=new ArrayList<Map<String,Object>>();
        Bundle bundle=getIntent().getExtras();
        bili=bundle.getString("bili");
        jiezhishijian=bundle.getString("jiezhi");
        can_gku=bundle.getString("cangku");
        xiangxi=bundle.getString("xiangxi");
        kaishishijian=bundle.getString("kaishi");
        jieshushijian=bundle.getString("jieshu");
        tuan_start=bundle.getString("tuan_start");
        tuan_end=bundle.getString("tuan_end");

    }
    public void event(){
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RuleActivity.this.finish();
            }
        });

    }
    public void addData(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat
                ("yyyy年MM月dd日 hh:mm:mm");
        String kaishi_data=simpleDateFormat.format(Double.parseDouble(kaishishijian));
        String jieshu_data=simpleDateFormat.format(Double.parseDouble(jieshushijian));
        String tuan_kaishi=simpleDateFormat.format(Double.parseDouble(tuan_start));
        String tuan_jieshu=simpleDateFormat.format(Double.parseDouble(tuan_end));
        Map<String,Object> map1=new Hashtable<String,Object>();
        map1.put("left","保证金比例");
        map1.put("right",bili);
        list.add(map1);
        Map<String,Object> map2=new Hashtable<String,Object>();
        map2.put("left","保证金支付截至时间");
        map2.put("right",jiezhishijian);
        list.add(map2);
        Map<String,Object> map3=new Hashtable<String,Object>();
        map3.put("left","仓库");
        map3.put("right",can_gku);
        list.add(map3);
        Map<String,Object> map4=new Hashtable<String,Object>();
        map4.put("left","详细地址");
        map4.put("right",xiangxi);
        list.add(map4);
        Map<String,Object> map5=new Hashtable<String,Object>();
        map5.put("left","交货时间");
        map5.put("right",kaishi_data+"至"+jieshu_data);
        list.add(map5);
        Map<String,Object> map6=new Hashtable<String,Object>();
        map6.put("left","交易模式");
        map6.put("right","团购");
        list.add(map6);
        Map<String,Object> map7=new Hashtable<String,Object>();
        map7.put("left","团购时间");
        map7.put("right",tuan_kaishi+"至"+tuan_jieshu);
        list.add(map7);

        adapter=new SimpleAdapter(RuleActivity.this,list,R.layout.item_firm_info,
                new String[]{"left","right"},
                new int[]{R.id.tv_firm_info,R.id.tvValue_firm_info});
        lv_rule.setAdapter(adapter);


    }
}

package com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.firmInfomation;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.silianchuangye.sumao.success.R;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

public class FirmInfoActivity extends AppCompatActivity {
    ImageView iv_title_bar_logo,
            iv_title_bar_back,
            iv_title_bar_service,
            add_address,
            iv_title_bar_search;
    Button sv_title_bar_serachView;
    TextView tv_title_bar_title,tv,tvUpdate;
    RelativeLayout layoutTop,add_address_rl;
    private ListView lv_firm_info;
    private List<Map<String,Object>> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firm_info);

            initList();
            title_Bar();



    }
    public void initList(){
        lv_firm_info= (ListView) findViewById(R.id.lvShow_firm_info);
         //这里判断是新增还是修改，如果新增则没有后面的值
        list=new ArrayList<Map<String,Object>>();
        Map<String,Object> map1=new Hashtable<String,Object>();
        map1.put("center","恭喜你，企业已经注册成功");
        list.add(map1);
        Map<String,Object> map2=new Hashtable<String,Object>();
        map2.put("left","企业类型");
        map2.put("right","贸易商");
        list.add(map2);
        Map<String,Object> map3=new Hashtable<String,Object>();
        map3.put("left","企业名称");
        map3.put("right","四联创业");
        list.add(map3);
        Map<String,Object> map4=new Hashtable<String,Object>();
        map4.put("left","业务部门");
        map4.put("right","京津冀办事处");
        list.add(map4);
        Map<String,Object> map5=new Hashtable<String,Object>();
        map5.put("left","办公地址");
        map5.put("right","1111111111111111");
        list.add(map5);
        Map<String,Object> map6=new Hashtable<String,Object>();
        map6.put("left","传真号");
        map6.put("right","010-88888888");
        list.add(map6);
        Map<String,Object> map7=new Hashtable<String,Object>();
        map7.put("left","企业注册证件");
        map7.put("right","三证合一");
        list.add(map7);
        Map<String,Object> map8=new Hashtable<String,Object>();
        map8.put("left","统一社会信用代码");
        map8.put("right","1111111111");
        map8.put("icon",R.mipmap.my_sumao_iv_order);
        list.add(map8);

        Map<String,Object> map9=new Hashtable<String,Object>();
        map9.put("left","纳税人类型");
        map9.put("right","一般纳税人");
        map9.put("icon",R.mipmap.my_sumao_iv_order);
        list.add(map9);
        SimpleAdapter adapter=new SimpleAdapter(FirmInfoActivity.this,list,R.layout.item_firm_info,new String[]{"left","center","right","icon"},new int[]{R.id.tv_firm_info,R.id.tvTitle_firm_info,R.id.tvValue_firm_info,R.id.ivMore_firm_info});
        lv_firm_info.setAdapter(adapter);
        lv_firm_info.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position==7||position==8){
                    Intent intent=new Intent(FirmInfoActivity.this,FirmInfoPictureActivity.class);
                    startActivity(intent);
                }
            }
        });


    }
    public void title_Bar(){

        iv_title_bar_back = ((ImageView) findViewById(R.id.iv_title_bar_back));
        iv_title_bar_logo = ((ImageView) findViewById(R.id.iv_title_bar_logo));
        iv_title_bar_service = ((ImageView) findViewById(R.id.iv_title_bar_service));
        iv_title_bar_search = ((ImageView) findViewById(R.id.iv_title_bar_search));
        sv_title_bar_serachView = ((Button) findViewById(R.id.sv_title_bar_serachView));
        tv_title_bar_title  = ((TextView) findViewById(R.id.tv_title_bar_title));

        tvUpdate= (TextView) findViewById(R.id.tvUpdate);
        iv_title_bar_logo.setVisibility(View.GONE);
        iv_title_bar_service.setVisibility(View.GONE);
        sv_title_bar_serachView.setVisibility(View.GONE);
        iv_title_bar_search.setVisibility(View.GONE);
        tv_title_bar_title.setText("企业信息");

        tvUpdate.setText("修改");
        tv_title_bar_title.setTextColor(Color.WHITE);
        iv_title_bar_back.setVisibility(View.VISIBLE);
        layoutTop= (RelativeLayout) findViewById(R.id.layoutTop_firm_info);
        layoutTop.setBackgroundColor(getResources().getColor(R.color.textColor_expandable_listview_show));

        iv_title_bar_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirmInfoActivity.this.finish();
            }
        });
        tvUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(FirmInfoActivity.this,FirmInfoUpdateActivity.class);
                intent.putExtra("add","修改");
                startActivity(intent);
            }
        });
    }
}

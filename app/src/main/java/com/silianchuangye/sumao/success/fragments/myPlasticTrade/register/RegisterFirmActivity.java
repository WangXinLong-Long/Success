package com.silianchuangye.sumao.success.fragments.myPlasticTrade.register;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.firmInfomation.FirmInfoAddressActivity;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.firmInfomation.FirmInfoNameActivity;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.firmInfomation.FirmInfoOfficeActivity;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.firmInfomation.FirmInfoTypeActivity;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.receiptAddress.SelectProvinceArea;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

public class RegisterFirmActivity extends AppCompatActivity {
    ImageView iv_title_bar_logo,
            iv_title_bar_back,
            iv_title_bar_service,
            iv_title_bar_search;
    Button sv_title_bar_serachView;
    TextView tv_title_bar_title,tv;
    RelativeLayout layoutTop;
    private ListView lvupdate_firm_info;
    private List<Map<String,Object>> list;
    private List<Map<String,Object>> list1;
    private ListView lvupdate_firm_info_two;
    private Spinner sp_firm_info;
    String title;
    private SimpleAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_firm);


        title = "注册账户";
        String add="新建";
        title_Bar(title);
        initList(add);
    }
    public void initList(String add){
        lvupdate_firm_info= (ListView) findViewById(R.id.lvUpdate_firm_info);
        list=new ArrayList<Map<String,Object>>();
        if (add.equals("新建")){
            Map<String,Object> map1=new Hashtable<String,Object>();
            map1.put("center","填写企业信息");
            list.add(map1);
            //不从网络获取第二个参数
        }

        Map<String,Object> map2=new Hashtable<String,Object>();
        map2.put("left","企业类型");
        map2.put("right","");
        map2.put("icon",R.mipmap.my_sumao_iv_order);
        list.add(map2);
        Map<String,Object> map3=new Hashtable<String,Object>();
        map3.put("left","企业名称");
        map3.put("right","");
        map3.put("icon",R.mipmap.my_sumao_iv_order);
        list.add(map3);
        Map<String,Object> map4=new Hashtable<String,Object>();
        map4.put("left","业务部门");
        map4.put("right","");
        map4.put("icon",R.mipmap.my_sumao_iv_order);
        list.add(map4);
        Map<String,Object> map5=new Hashtable<String,Object>();
        map5.put("left","办公地址");
        map5.put("right","");
        map5.put("icon",R.mipmap.my_sumao_iv_order);
        list.add(map5);
        Map<String,Object> map6=new Hashtable<String,Object>();
        map6.put("left","传真号");
        map6.put("right","");
        map6.put("icon",R.mipmap.my_sumao_iv_order);
        list.add(map6);

         adapter=new SimpleAdapter(RegisterFirmActivity.this,list,R.layout.item_firm_info,new String[]{"left","center","right","icon"},new int[]{R.id.tv_firm_info,R.id.tvTitle_firm_info,R.id.tvValue_firm_info,R.id.ivMore_firm_info});
        lvupdate_firm_info.setAdapter(adapter);

        lvupdate_firm_info.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position==1){
                    Intent intent=new Intent(RegisterFirmActivity.this,FirmInfoTypeActivity.class);
                    //startActivity(intent);
                    startActivityForResult(intent,position);
                }else if(position==3){
                    Intent intent=new Intent(RegisterFirmActivity.this,FirmInfoNameActivity.class);
                    startActivityForResult(intent,position);
                }else if(position==2){
                    Intent intent=new Intent(RegisterFirmActivity.this,FirmInfoOfficeActivity.class);
                    startActivityForResult(intent,position);
                }else if(position==4){
                    Intent intent=new Intent(RegisterFirmActivity.this,SelectProvinceArea.class);
                    startActivityForResult(intent,position);

                }
            }
        });

        lvupdate_firm_info_two= (ListView) findViewById(R.id.lvUpdate_firm_info_two);
        list1=new ArrayList<Map<String,Object>>();
        sp_firm_info= (Spinner) findViewById(R.id.sp_firm_info);
        sp_firm_info.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(sp_firm_info.getItemAtPosition(position).toString().equals("三证独立")){
                    if(list1.size()==1){
                        Map<String,Object> map8=new Hashtable<String,Object>();
                        map8.put("left","企业执照号");
                        map8.put("right","");
                        map8.put("icon",R.mipmap.my_sumao_iv_order);
                        list1.add(map8);
                        Map<String,Object> map9=new Hashtable<String,Object>();
                        map9.put("left","组织机构代码");
                        map9.put("right","");
                        map9.put("icon",R.mipmap.my_sumao_iv_order);
                        list1.add(map9);
                        Map<String,Object> map10=new Hashtable<String,Object>();
                        map10.put("left","税务登记号");
                        map10.put("right","");
                        map10.put("icon",R.mipmap.my_sumao_iv_order);
                        list1.add(map10);

                        SimpleAdapter adapter1=new SimpleAdapter(RegisterFirmActivity.this,list1,R.layout.item_firm_info,new String[]{"left","center","right","icon"},new int[]{R.id.tv_firm_info,R.id.tvTitle_firm_info,R.id.tvValue_firm_info,R.id.ivMore_firm_info});
                        lvupdate_firm_info_two.setAdapter(adapter1);}
                    Toast.makeText(RegisterFirmActivity.this, ""+list1.size(), Toast.LENGTH_SHORT).show();
                }else if (sp_firm_info.getItemAtPosition(position).toString().equals("三证合一")){
                    if(list1.size()>1){
                        for (int i=0;i<4;i++){
                            list1.remove(0);
                        }
                        Map<String,Object> map11=new Hashtable<String,Object>();
                        map11.put("left","纳税人类型");
                        map11.put("right","");
                        map11.put("icon",R.mipmap.my_sumao_iv_order);
                        list1.add(map11);
                        SimpleAdapter adapter1=new SimpleAdapter(RegisterFirmActivity.this,list1,R.layout.item_firm_info,new String[]{"left","center","right","icon"},new int[]{R.id.tv_firm_info,R.id.tvTitle_firm_info,R.id.tvValue_firm_info,R.id.ivMore_firm_info});
                        lvupdate_firm_info_two.setAdapter(adapter1);
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(RegisterFirmActivity.this, "请选择", Toast.LENGTH_SHORT).show();
            }
        });

        Map<String,Object> map11=new Hashtable<String,Object>();
        map11.put("left","纳税人类型");
        map11.put("right","");
        map11.put("icon",R.mipmap.my_sumao_iv_order);
        list1.add(map11);
        SimpleAdapter adapter1=new SimpleAdapter(this,list1,R.layout.item_firm_info,new String[]{"left","center","right","icon"},new int[]{R.id.tv_firm_info,R.id.tvTitle_firm_info,R.id.tvValue_firm_info,R.id.ivMore_firm_info});
        lvupdate_firm_info_two.setAdapter(adapter1);



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode){
            case 1:
                String one=data.getStringExtra("name");
                list.get(1).put("right",one);
                adapter.notifyDataSetChanged();
                break;
            case 2:
                String name=data.getStringExtra("name");
                list.get(2).put("right",name);
                adapter.notifyDataSetChanged();
                break;
            case 3:
                String name1=data.getStringExtra("name");
                list.get(3).put("right",name1);
                adapter.notifyDataSetChanged();
                break;
            case 4:
                break;
        }
    }

    public void title_Bar(String title){
        iv_title_bar_back = ((ImageView) findViewById(R.id.iv_title_bar_back));
        iv_title_bar_logo = ((ImageView) findViewById(R.id.iv_title_bar_logo));
        iv_title_bar_service = ((ImageView) findViewById(R.id.iv_title_bar_service));
        iv_title_bar_search = ((ImageView) findViewById(R.id.iv_title_bar_search));
        sv_title_bar_serachView = ((Button) findViewById(R.id.sv_title_bar_serachView));
        tv_title_bar_title  = ((TextView) findViewById(R.id.tv_title_bar_title));

        iv_title_bar_logo.setVisibility(View.GONE);
        iv_title_bar_service.setVisibility(View.GONE);
        sv_title_bar_serachView.setVisibility(View.GONE);
        iv_title_bar_search.setVisibility(View.GONE);
        tv_title_bar_title.setText(title);
        tv_title_bar_title.setTextColor(Color.WHITE);
        iv_title_bar_back.setVisibility(View.VISIBLE);
        layoutTop= (RelativeLayout) findViewById(R.id.layoutTop_firm_info_update);
        layoutTop.setBackgroundColor(getResources().getColor(R.color.textColor_expandable_listview_show));

        iv_title_bar_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RegisterFirmActivity.this.finish();
            }
        });
    }
}

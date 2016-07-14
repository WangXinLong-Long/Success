package com.silianchuangye.sumao.success.customermanger;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.baidu.mapapi.map.Text;
import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.custom.CustomListView;

import org.apache.qpid.management.common.sasl.UsernameHashedPasswordCallbackHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class CustomerMessage extends AppCompatActivity implements View.OnClickListener{
    private ImageView img_back;
    private RelativeLayout relative_editor;//编辑tv
    private CustomListView lv,lv2,lv3;
    private List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
    private List<Map<String,Object>> list2=new ArrayList<Map<String,Object>>();
    private List<Map<String,Object>> list3=new ArrayList<Map<String,Object>>();
    private SimpleAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_message);
        initDate();
        initView();
    }

    private void initView() {
        img_back= (ImageView) findViewById(R.id.img_logistics_title_bar_back);
        relative_editor= (RelativeLayout) findViewById(R.id.tv_logistics_title_bar_search);
        lv= (CustomListView) findViewById(R.id.lv_customer_message);
        lv2= (CustomListView) findViewById(R.id.lv_customer_message_three);
        lv3= (CustomListView) findViewById(R.id.lv_customer_message_four);
        img_back.setOnClickListener(this);
        relative_editor.setOnClickListener(this);
        adapter=new SimpleAdapter(this,list,R.layout.item_customer_message_lv,
                new String[]{
                        "left","right","see","img"
             }, new int[]{
                R.id.tv_item_customer_message_lv_left,
                R.id.tv_item_customer_message_lv_right,
                R.id.tv_item_customer_message_lv_see,
                R.id.img_item_customer_message
        });
        lv.setAdapter(adapter);
    }

    private void initDate() {
        Map<String,Object>map2=new HashMap<String,Object>();
        map2.put("left","企业编号");
        map2.put("right","85");
        Map<String,Object>map3=new HashMap<String,Object>();
        map3.put("left","企业注册证件");
        map3.put("right","三证合一");
        Map<String,Object>map4=new HashMap<String,Object>();
        map4.put("left","企业法人");
        map4.put("right","张三");
        map4.put("see","查看扫描件");
        Map<String,Object>map5=new HashMap<String,Object>();
        map5.put("left","企业类型");
        map5.put("right","生产厂");
        Map<String,Object>map6=new HashMap<String,Object>();
        map6.put("left","统一社会信用代码");
        map6.put("img",R.mipmap.my_sumao_iv_order);
        map6.put("right","3321");
        map6.put("see","查看扫描件");
        Map<String,Object>map7=new HashMap<String,Object>();
        map7.put("left","开户银行许可证");
        map7.put("right","2134fsdf");
        map7.put("see","查看扫描件");
        map7.put("img",R.mipmap.my_sumao_iv_order);
        Map<String,Object>map8=new HashMap<String,Object>();
        map8.put("left","企业名称");
        map8.put("right","艾泽拉斯国家情报局");
        Map<String,Object>map9=new HashMap<String,Object>();
        map9.put("left","组织机构代码");
        map9.put("see","查看扫描件");
        Map<String,Object>map10=new HashMap<String,Object>();
        map10.put("left","年需求量");
        map10.put("right","22222吨/年");
        Map<String,Object>map11=new HashMap<String,Object>();
        map11.put("left","业务部门");
        map11.put("right","间谍部门");
        Map<String,Object>map12=new HashMap<String,Object>();
        map12.put("left","税务登记号");
        map12.put("see","查看扫描件");
        Map<String,Object>map13=new HashMap<String,Object>();
        map13.put("left","从业人员");
        map3.put("right","51-100人");
        Map<String,Object>map14=new HashMap<String,Object>();
        map14.put("left","办公地址");
        map4.put("right","河北省邢台市沙河市 暴风域");
        Map<String,Object>map15=new HashMap<String,Object>();
        map15.put("left","纳税人类型");
        map15.put("right","一般纳税人");
        map5.put("see","查看扫描件");
        Map<String,Object>map16=new HashMap<String,Object>();
        map16.put("left","购买资质");
        map16.put("right","有");
        Map<String,Object>map17=new HashMap<String,Object>();
        map17.put("left","传真号");
        map17.put("right","12312321312321");
        Map<String,Object>map18=new HashMap<String,Object>();
        map18.put("left","无购买资质原因");
        Map<String,Object>map19=new HashMap<String,Object>();
        map19.put("left","客户状态");
        map19.put("right","有效");
        Map<String,Object> map20=new HashMap<String,Object>();
        map20.put("left","指派业务员");
        map20.put("right","张三");
        list.add(map2);
        list.add(map3);
        list.add(map4);
        list.add(map5);
        list.add(map6);
        list.add(map7);
        list.add(map8);
        list.add(map9);
        list.add(map10);
    }

    @Override
    public void onClick(View v) {

    }
}

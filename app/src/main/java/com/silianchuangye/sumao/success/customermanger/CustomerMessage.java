package com.silianchuangye.sumao.success.customermanger;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

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
    private SimpleAdapter adapter,adapter2,adapter3;
    private String str,str1;
    private TextView tv_title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_message);
        initDate();
        initView();
    }

    private void initView() {
        String title=getIntent().getStringExtra("title");
        tv_title= (TextView) findViewById(R.id.tv_customer_manager_title);
        tv_title.setText(title);
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

        adapter2=new SimpleAdapter(this,list2,R.layout.item_customer_message_lv,
                new String[]{
                        "left","right","see","img"
                }, new int[]{
                R.id.tv_item_customer_message_lv_left,
                R.id.tv_item_customer_message_lv_right,
                R.id.tv_item_customer_message_lv_see,
                R.id.img_item_customer_message
        });
        lv2.setAdapter(adapter2);

        adapter3=new SimpleAdapter(this,list3,R.layout.item_customer_message_lv,
                new String[]{
                        "left","right","see","img"
                }, new int[]{
                R.id.tv_item_customer_message_lv_left,
                R.id.tv_item_customer_message_lv_right,
                R.id.tv_item_customer_message_lv_see,
                R.id.img_item_customer_message
        });
        lv3.setAdapter(adapter3);
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
        map7.put("see","查看扫描件");
        map7.put("img",R.mipmap.my_sumao_iv_order);
        Map<String,Object>map8=new HashMap<String,Object>();
        map8.put("left","企业名称");
        map8.put("right","艾泽拉斯国家情报局");
        Map<String,Object>map9=new HashMap<String,Object>();
        map9.put("left","组织机构代码");
        map9.put("see","查看扫描件");
        map9.put("img",R.mipmap.my_sumao_iv_order);
        Map<String,Object>map10=new HashMap<String,Object>();
        map10.put("left","年需求量");
        map10.put("right","22222吨/年");
        Map<String,Object>map11=new HashMap<String,Object>();
        map11.put("left","业务部门");
        map11.put("right","间谍部门");
        Map<String,Object>map12=new HashMap<String,Object>();
        map12.put("left","税务登记号");
        map12.put("right","22342fsfd");
        map12.put("see","查看扫描件");
        Map<String,Object>map13=new HashMap<String,Object>();
        map13.put("left","从业人员");
        map13.put("right","51-100人");
        Map<String,Object>map14=new HashMap<String,Object>();
        map14.put("left","办公地址");
        map14.put("right","河北省邢台市沙河市 暴风域");
        Map<String,Object>map15=new HashMap<String,Object>();
        map15.put("left","纳税人类型");
        map15.put("right","一般纳税人");
        map15.put("see","查看扫描件");
        Map<String,Object>map16=new HashMap<String,Object>();
        map16.put("left","购买资质");
        map16.put("right","有");
        map16.put("img",R.mipmap.my_sumao_iv_order);
        Map<String,Object>map17=new HashMap<String,Object>();
        map17.put("left","传真号");
        map17.put("right","12312321312321");

        Map<String,Object>map19=new HashMap<String,Object>();
        map19.put("left","客户状态");
        map19.put("right","有效");
        Map<String,Object> map20=new HashMap<String,Object>();
        map20.put("left","指派业务员");
        map20.put("right","张三");
        Map<String,Object> map21=new HashMap<String,Object>();
        map21.put("left","无购买资质原因");
        map21.put("right","企业三证不全这家客户可萨芬失联客机多拉卡积分是了了双方就卡了");
        list.add(map2);
        list.add(map8);
        list.add(map5);
        list.add(map3);
        list.add(map7);
        list.add(map9);
        list.add(map12);
        list.add(map15);
        list.add(map14);
        list.add(map11);
        list.add(map17);
        list.add(map13);
        list.add(map10);
        list.add(map16);
        list.add(map21);//原因
        list.add(map19);
        list.add(map20);

//        list.add(map18);
//        list.add(map19);
//        list.add(map20);

        Map<String,Object>two_map1=new HashMap<String,Object>();
        two_map1.put("left","纳税人类型");
        two_map1.put("right","一般纳税人");
        Map<String,Object>two_map2=new HashMap<String,Object>();
        two_map2.put("left","公司名称");
        two_map2.put("right","谁便吧");
        Map<String,Object>two_map3=new HashMap<String,Object>();
        two_map3.put("left","税号");
        two_map3.put("right","123214");
        Map<String,Object>two_map4=new HashMap<String,Object>();
        two_map4.put("left","地址");
        two_map4.put("right","少喝咖啡的空间发");
        Map<String,Object>two_map5=new HashMap<String,Object>();
        two_map5.put("left","电话");
        two_map5.put("right","1248934924");
        Map<String,Object>two_map6=new HashMap<String,Object>();
        two_map6.put("left","开户行");
        two_map6.put("right","暴风域国家银行");
        Map<String,Object>two_map7=new HashMap<String,Object>();
        two_map7.put("left","账户");
        two_map7.put("right","234242342423423423");
        list2.add(two_map1);
        list2.add(two_map2);
        list2.add(two_map3);
        list2.add(two_map4);
        list2.add(two_map5);
        list2.add(two_map6);
        list2.add(two_map7);

        Map<String,Object>three_map1=new HashMap<String,Object>();
        three_map1.put("left","姓名");
        three_map1.put("right","谁看见的发货速度");
        Map<String,Object>three_map2=new HashMap<String,Object>();
        three_map2.put("left","手机");
        three_map2.put("right","93482098409");
        Map<String,Object>three_map3=new HashMap<String,Object>();
        three_map3.put("left","固定电话");
        three_map3.put("right","20938432094");
        Map<String,Object>three_map4=new HashMap<String,Object>();
        three_map4.put("left","邮寄地址");
        three_map4.put("right","河北省开始的减肥和三等奖");
        Map<String,Object>three_map5=new HashMap<String,Object>();
        three_map5.put("left","邮编");
        three_map5.put("right","056600");
        list3.add(three_map1);
        list3.add(three_map2);
        list3.add(three_map3);
        list3.add(three_map4);
        list3.add(three_map5);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_logistics_title_bar_back:
                finish();
                break;
            case R.id.tv_logistics_title_bar_search:
                Intent intent=new Intent(this,SaveCustomerMessage.class);
                intent.putExtra("buy",list.get(13).get("right").toString());
                intent.putExtra("person",list.get(16).get("right").toString());
                intent.putExtra("title","编辑信息");
                startActivityForResult(intent,0);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==2){
            str=data.getStringExtra("select");
            str1=data.getStringExtra("name");
            list.get(13).put("right",str);
            list.get(16).put("right",str1);
            adapter.notifyDataSetChanged();
            Log.e("TAG","13"+list.get(13).get("right").toString());
        }
        if(requestCode==3){

        }
    }
}

package com.silianchuangye.sumao.success.customermanger;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.custom.CustomListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SaveCustomerMessage extends AppCompatActivity implements View.OnClickListener,AdapterView.OnItemClickListener{
    private ImageView img_back;
    private RelativeLayout relative_editor;//编辑tv
    private CustomListView lv,lv2,lv3;
    private List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
    private List<Map<String,Object>> list2=new ArrayList<Map<String,Object>>();
    private List<Map<String,Object>> list3=new ArrayList<Map<String,Object>>();
    private SimpleAdapter adapter;
    private SimpleAdapter adapter2,adapter3;
    private TextView tv;
    String str,str1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_message);
        initDate();
        initView();
    }

    private void initView() {
        tv= (TextView) findViewById(R.id.tv_customer_message_editor);
        tv.setText("保存");
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

        lv.setOnItemClickListener(this);
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
        map6.put("right","3321");
        map6.put("see","查看扫描件");
        Map<String,Object>map7=new HashMap<String,Object>();
        map7.put("left","开户银行许可证");
        map7.put("right","2134fsdf");
        map7.put("see","查看扫描件");
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
        map16.put("right",getIntent().getStringExtra("buy"));
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
        map20.put("right",getIntent().getStringExtra("person"));
        list.add(map2);
        list.add(map3);
        list.add(map4);
        list.add(map5);
        list.add(map6);
        list.add(map7);
        list.add(map8);
        list.add(map9);
        list.add(map10);
        list.add(map11);
        list.add(map12);
        list.add(map13);
        list.add(map14);
        list.add(map15);
        list.add(map16);
        list.add(map17);
        list.add(map18);
        list.add(map19);
        list.add(map20);

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
                Toast.makeText(this,"保存",Toast.LENGTH_SHORT).show();
                Intent intent=new Intent();
                if(str==null&&str1==null){
                    setResult(3,intent);
                }
                intent.putExtra("select", list.get(14).get("right").toString());
                intent.putExtra("name", list.get(18).get("right").toString());
                Log.e("TAG","werwrw"+list.get(14).get("right").toString());
                Log.e("TAG","sdfsf"+list.get(18).get("right").toString());
                setResult(2, intent);
                finish();
                break;
        }
    }
int i;
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        i=position;
        if(list.get(position).get("left").equals("购买资质")){
            Toast.makeText(this,"购买资质",Toast.LENGTH_SHORT).show();
            Intent intent=new Intent(this,Buy.class);
            intent.putExtra("title",list.get(position).get("left").toString());
            startActivityForResult(intent,0);
        }
        if(list.get(position).get("left").equals("指派业务员")){
            Toast.makeText(this,"指派业务员",Toast.LENGTH_SHORT).show();
            Intent intent=new Intent(this,ZhiPaiPerson.class);
            intent.putExtra("name",list.get(position).get("right").toString());
            startActivityForResult(intent,0);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.e("TAG","str=="+str);
        if(resultCode==1){
            str=data.getStringExtra("select");
            list.get(14).put("right",str);
        }
        if(resultCode==2){
            str1=data.getStringExtra("name");
            list.get(18).put("right",str1);
        }
        adapter.notifyDataSetChanged();
    }
}

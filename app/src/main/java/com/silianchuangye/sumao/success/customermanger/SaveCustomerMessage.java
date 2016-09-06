package com.silianchuangye.sumao.success.customermanger;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
    private SimpleAdapter adapter;
    private RelativeLayout relative_customer_message_two,relative_customer_message_three,relative_customer_message_four;
    private LinearLayout linear_bottem_customer;
    private TextView tv,tv_title;
    String str,str1;
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
        tv= (TextView) findViewById(R.id.tv_customer_message_editor);
        tv.setText("保存");
        img_back= (ImageView) findViewById(R.id.img_logistics_title_bar_back);
        relative_editor= (RelativeLayout) findViewById(R.id.tv_logistics_title_bar_search);
        relative_customer_message_two= (RelativeLayout) findViewById(R.id.relative_customer_message_two);
        relative_customer_message_three= (RelativeLayout) findViewById(R.id.relative_customer_message_three);
        relative_customer_message_four= (RelativeLayout) findViewById(R.id.relative_customer_message_four);
        linear_bottem_customer= (LinearLayout) findViewById(R.id.linear_bottem_customer);
        linear_bottem_customer.setVisibility(View.GONE);
        relative_customer_message_four.setVisibility(View.GONE);
        relative_customer_message_three.setVisibility(View.GONE);
        relative_customer_message_two.setVisibility(View.GONE);
        lv= (CustomListView) findViewById(R.id.lv_customer_message);
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

        lv.setOnItemClickListener(this);
    }

    private void initDate() {

        Map<String,Object>map1=new HashMap<String,Object>();
        map1.put("left","企业名称");
        map1.put("right","艾泽拉斯国家情报局");
        Map<String,Object>map2=new HashMap<String,Object>();
        map2.put("left","购买资质");
        map2.put("right",getIntent().getStringExtra("buy"));
        map2.put("img",R.mipmap.my_sumao_iv_order);
        Map<String,Object>map3=new HashMap<String,Object>();
        map3.put("left","业务员");
        map3.put("right",getIntent().getStringExtra("person"));
        map3.put("img",R.mipmap.my_sumao_iv_order);
        list.add(map1);
        list.add(map2);
        list.add(map3);

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
                intent.putExtra("select", list.get(1).get("right").toString());
                intent.putExtra("name", list.get(2).get("right").toString());
                Log.e("TAG","werwrw"+list.get(1).get("right").toString());
                Log.e("TAG","sdfsf"+list.get(2).get("right").toString());
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
        if(list.get(position).get("left").equals("业务员")){
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
            list.get(1).put("right",str);
        }
        if(resultCode==2){
            str1=data.getStringExtra("name");
            list.get(2).put("right",str1);
        }
        adapter.notifyDataSetChanged();
    }
}

package com.silianchuangye.sumao.success.fragments.myPlasticTrade.accountmeneger;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.silianchuangye.sumao.success.R;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

public class Account_Activity extends AppCompatActivity implements View.OnClickListener{
    private ImageView iv_child_title_bar_back;
    private TextView tv_child_title_bar_title;
    private ListView lv_Bank;
    private List<Map<String,Object>> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_);
        iv_child_title_bar_back= (ImageView) findViewById(R.id.iv_child_title_bar_back);
        tv_child_title_bar_title= (TextView) findViewById(R.id.tv_child_title_bar_title);
        lv_Bank= (ListView) findViewById(R.id.lv_Bank);
        list=new ArrayList<>();
        tv_child_title_bar_title.setText("账户管理");
        Map<String,Object> map=new Hashtable<String, Object>();
        map.put("icon",R.mipmap.pingan);
        map.put("name","平安银行");
        list.add(map);
        Map<String,Object> map1=new Hashtable<>();
        map1.put("icon",R.mipmap.kunlun);
        map1.put("name","昆仑银行");
        list.add(map1);
        Map<String,Object> map2=new Hashtable<>();
        map2.put("icon",R.mipmap.jianshe);
        map2.put("name","中国建设银行");
        list.add(map2);
        SimpleAdapter adapter=new SimpleAdapter(Account_Activity.this,list,R.layout.layout_item_banklist,new String[]{"icon","name"},new int[]{R.id.iv_Bank_icon,R.id.tv_Bank_name});
        lv_Bank.setAdapter(adapter);
        lv_Bank.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if (position==0){
                    Intent intent=new Intent(Account_Activity.this,inganActivity.class);
                    intent.putExtra("position",1);
                    startActivity(intent);
                }else if(position==1){
                    Intent intent=new Intent(Account_Activity.this,KunlunActivity.class);
                    intent.putExtra("position",2);
                    startActivity(intent);
                }else if (position==2){
                    Intent intent=new Intent(Account_Activity.this,JiansheActivity.class);
                    intent.putExtra("position",3);
                    startActivity(intent);
                }


            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_child_title_bar_back:
                Account_Activity.this.finish();
                break;

        }
    }
}

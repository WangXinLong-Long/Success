package com.silianchuangye.sumao.success.fragments.type;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.silianchuangye.sumao.success.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TypeInfoActivity extends AppCompatActivity {
    private ListView lv_Type;
    private List<Map<String,Object>> list;
    private SimpleAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_type_info);
        init_listView();
    }
    public void init_listView(){
        lv_Type= (ListView) findViewById(R.id.lv_Type);
        list = new ArrayList<Map<String, Object>>();
        for (int i=0;i<=10;i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("name","燕山石化7042");
            map.put("number","1234.6吨");
            map.put("price","10000");
            map.put("address","北京市房山区东流水路四联创业集团");
            map.put("cangku","讯邦仓库1号");
            map.put("state","现货产品");
            list.add(map);

        }
        adapter=new SimpleAdapter(this,list,R.layout.item_type_two,
                new String[]
                        {"name","number","price","address","cangku","state"},
                new int[]
                        {R.id.tv_name_type_two,R.id.tv_number_type_two,
                         R.id.tv_price_type_two,R.id.tv_address_type_two,
                         R.id.tv_cangku_type_two,R.id.tv_state_type_two
                        });
        lv_Type.setAdapter(adapter);

    }
}

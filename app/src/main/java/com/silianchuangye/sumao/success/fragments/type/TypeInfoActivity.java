package com.silianchuangye.sumao.success.fragments.type;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.easemob.easeui.domain.EaseEmojicon;
import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.fragments.homepage.goodInStock.GoodsInStockDetailActivity;
import com.silianchuangye.sumao.success.fragments.homepage.goodInStock.PaymentsOrder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class TypeInfoActivity extends AppCompatActivity {
    private ListView lv_Type;
    private List<Map<String,Object>> list;
    private SimpleAdapter adapter;
    private ImageView iv_Back;
    private TextView tv_Search;
    private EditText Search;
    private TextView tv_pattern_Type,tv_type_Type,tv_apple_Type_for_Type,tv_address_Type,tv_address_search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_type_info);
        TextView tv_pattern_Type= (TextView) findViewById(R.id.tv_pattern_Type);
        TextView tv_type_Type= (TextView) findViewById(R.id.tv_type_Type);
        TextView tv_apple_Type_for_Type= (TextView) findViewById(R.id.tv_apple_Type_for_Type);
        TextView tv_address_Type= (TextView) findViewById(R.id.tv_address_Type);
        TextView tv_address_search= (TextView) findViewById(R.id.tv_address_search);
        /**
         * 分类的popupWindow
         */
        tv_pattern_Type.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        /**
         *应用的popupwindow
         */
        tv_apple_Type_for_Type.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        /**
         * 牌号的popupwindow
         */
        tv_type_Type.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        /**
         * 地区的popupwindow
         */
        tv_address_Type.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        /**
         * 筛选的popupwindow
         */
        tv_address_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


       // closeInputMethod();
        iv_Back= (ImageView) findViewById(R.id.iv_Back_Type);
        iv_Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TypeInfoActivity.this.finish();
            }
        });
        tv_Search= (TextView) findViewById(R.id.tv_Info_Type);
        tv_Search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //进行搜索功能
            }
        });

        Search= (EditText) findViewById(R.id.et_Search_Type);
        Search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // openInputMethod(Search);
            }
        });
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
            map.put("state","现货");
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
        lv_Type.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (list.get(position).get("state").toString().equals("现货")){
                    Intent intent=new Intent(TypeInfoActivity.this, GoodsInStockDetailActivity.class);
                    startActivity(intent);
                }
            }
        });

    }
    class MyAdapter extends SimpleAdapter{


        public MyAdapter(Context context, List<? extends Map<String, ?>> data, int resource, String[] from, int[] to) {
            super(context, data, resource, from, to);
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;
            if (convertView==null){
                viewHolder=new ViewHolder();
                convertView=getLayoutInflater().inflate(R.layout.item_type_two,null);
                viewHolder.text_Name= (TextView) convertView.findViewById(R.id.tv_name_type_two);
                convertView.setTag(viewHolder);
            }else{
                viewHolder= (ViewHolder) convertView.getTag();
            }

            return convertView;
        }
    }
    class ViewHolder{
        TextView text_Name;

    }
}

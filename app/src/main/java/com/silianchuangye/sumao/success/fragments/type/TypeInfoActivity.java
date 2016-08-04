package com.silianchuangye.sumao.success.fragments.type;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ActionBarOverlayLayout;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.easemob.easeui.domain.EaseEmojicon;
import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.custom.customCalendar.CustomGridView;
import com.silianchuangye.sumao.success.fragments.homepage.goodInStock.GoodsInStockDetailActivity;
import com.silianchuangye.sumao.success.fragments.homepage.goodInStock.PaymentsOrder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class TypeInfoActivity extends AppCompatActivity {
    private ListView lv_Type;
    private List<Map<String,Object>> list;
    private SimpleAdapter adapter;
    private ImageView iv_Back;
    private TextView tv_Search;
    private EditText Search;
    private PopupWindow popupWindow;
    private RelativeLayout layout_Top_Type;
    private ImageView iv_Search;
    private String address,paihao,yingyong,fenlei;
    private RelativeLayout layout_type_list;
   // private List<String> list_String;
    private TextView tv_pattern_Type,tv_type_Type,tv_apple_Type_for_Type,tv_address_Type,tv_address_search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_type_info);
        layout_type_list= (RelativeLayout) findViewById(R.id.layout_type_list);
        layout_Top_Type= (RelativeLayout) findViewById(R.id.layout_Top_Type);
        iv_Search= (ImageView) findViewById(R.id.iv_search);
        tv_pattern_Type= (TextView) findViewById(R.id.tv_pattern_Type);
        tv_type_Type= (TextView) findViewById(R.id.tv_type_Type);
        tv_apple_Type_for_Type= (TextView) findViewById(R.id.tv_apple_Type_for_Type);
        tv_address_Type= (TextView) findViewById(R.id.tv_address_Type);
        tv_address_search= (TextView) findViewById(R.id.tv_address_search);
        /**
         * 分类的popupWindow
         */
        tv_pattern_Type.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (popupWindow!=null){
                    popupWindow.dismiss();
                }
                lv_Type.setDivider(getResources().getDrawable(R.color.transparent));
                layout_type_list.setBackgroundColor(getResources().getColor(R.color.transparent));
                tv_pattern_Type.setTextColor(getResources().getColor(R.color.btn_blue_normal));
                tv_type_Type.setTextColor(getResources().getColor(R.color.black_deep));
                tv_apple_Type_for_Type.setTextColor(getResources().getColor(R.color.black_deep));
                tv_address_Type.setTextColor(getResources().getColor(R.color.black_deep));
                tv_address_search.setTextColor(getResources().getColor(R.color.black_deep));
                List<String> list_String=new ArrayList<String>();
                /*
                添加分类的数据
                 */
                for (int i=0;i<=6;i++){
                    list_String.add("HPDE");
                }
                Popupwindow(list_String);
            }
        });

        tv_apple_Type_for_Type.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (popupWindow!=null){
                    popupWindow.dismiss();
                }
                layout_type_list.setBackgroundColor(getResources().getColor(R.color.transparent));
                tv_apple_Type_for_Type.setTextColor(getResources().getColor(R.color.btn_blue_normal));
                tv_pattern_Type.setTextColor(getResources().getColor(R.color.black_deep));
                tv_type_Type.setTextColor(getResources().getColor(R.color.black_deep));
                tv_address_Type.setTextColor(getResources().getColor(R.color.black_deep));
                tv_address_search.setTextColor(getResources().getColor(R.color.black_deep));
                List<String> list_yingyong=new ArrayList<String>();
                for (int i=0;i<=6;i++){
                    list_yingyong.add("拉私料");
                }
                Popupwindow(list_yingyong);
            }


        });
        /**
         * 牌号的popupwindow
         */
        tv_type_Type.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (popupWindow!=null){
                    popupWindow.dismiss();
                }
                layout_type_list.setBackgroundColor(getResources().getColor(R.color.transparent));
                tv_apple_Type_for_Type.setTextColor(getResources().getColor(R.color.black_deep));
                tv_pattern_Type.setTextColor(getResources().getColor(R.color.black_deep));
                tv_type_Type.setTextColor(getResources().getColor(R.color.btn_blue_normal));
                tv_address_Type.setTextColor(getResources().getColor(R.color.black_deep));
                tv_address_search.setTextColor(getResources().getColor(R.color.black_deep));
                List<String> list_paihao=new ArrayList<String>();
                // popupwindow_fenlei();
                for (int i=0;i<=6;i++){
                    list_paihao.add("8305");
                }
                Popupwindow(list_paihao);

            }
        });
        /**
         * 地区的popupwindow
         */
        tv_address_Type.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (popupWindow!=null){
                    popupWindow.dismiss();
                }
                layout_type_list.setBackgroundColor(getResources().getColor(R.color.transparent));
                tv_apple_Type_for_Type.setTextColor(getResources().getColor(R.color.black_deep));
                tv_pattern_Type.setTextColor(getResources().getColor(R.color.black_deep));
                tv_type_Type.setTextColor(getResources().getColor(R.color.black_deep));
                tv_address_Type.setTextColor(getResources().getColor(R.color.btn_blue_normal));
                tv_address_search.setTextColor(getResources().getColor(R.color.black_deep));
                List<String> list_diqu=new ArrayList<String>();
                // popupwindow_fenlei();
                for (int i=0;i<=6;i++){
                    list_diqu.add("北京");
                }
                Popupwindow(list_diqu);
            }
        });
        /**
         * 筛选的popupwindow
         */
        tv_address_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (popupWindow!=null){
                    popupWindow.dismiss();
                }
                tv_apple_Type_for_Type.setTextColor(getResources().getColor(R.color.black_deep));
                tv_pattern_Type.setTextColor(getResources().getColor(R.color.black_deep));
                tv_type_Type.setTextColor(getResources().getColor(R.color.black_deep));
                tv_address_Type.setTextColor(getResources().getColor(R.color.black_deep));
                tv_address_search.setTextColor(getResources().getColor(R.color.btn_blue_normal));
                layout_type_list.setBackgroundColor(getResources().getColor(R.color.transparent));
                popupwindow_shaixuan();
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



    public void Popupwindow(final List<String> list) {
        View view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.item_popupwindow_for_type_fenlei, null);
         popupWindow = new PopupWindow(findViewById(R.id.layout_ff), ActionBarOverlayLayout.LayoutParams.MATCH_PARENT, ActionBarOverlayLayout.LayoutParams.WRAP_CONTENT);
        popupWindow.setContentView(view);


        ListView lv_item_for_type= (ListView) view.findViewById(R.id.lv_item_for_type);
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(TypeInfoActivity.this,R.layout.item_text_popupwindow,R.id.tvDemo_popupWindow,list);
        lv_item_for_type.setAdapter(adapter);
        lv_item_for_type.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                /**
                 * 地区
                 */
                if (list.toString().contains("北京")){
                     address=list.get(position).toString();
                }else if (isAbc(list.toString())){//分类
                     fenlei=list.get(position).toString();
                    //Log.d("牌号",list.get(position).toString());
                }else if (list.toString().contains("拉丝料")){
                     yingyong=list.get(position).toString();
                }else{
                     paihao=list.get(position).toString();
                }
            }
        });

        Button bt_reset= (Button) view.findViewById(R.id.bt_reset);
        bt_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("令所选的为空","null");
                address="";
                fenlei="";
                yingyong="";
                paihao="";
                Log.d("令所选的为空",address+fenlei+yingyong+paihao);
            }
        });
        Button bt_finish= (Button) view.findViewById(R.id.bt_finish);
        bt_finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("根据所选的进行筛选","");
            }
        });

        popupWindow.setTouchable(true);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.setOutsideTouchable(true);
        popupWindow.setFocusable(true);
       popupWindow.showAsDropDown(findViewById(R.id.layout_center_Type),0,0);
       // popupWindow.showAtLocation(lv_Type, Gravity.TOP,0, 0);
      //popupWindow.showAtLocation();

        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                //popupWindow.dismiss();
                tv_type_Type .setTextColor(getResources().getColor(R.color.black_deep));
                tv_apple_Type_for_Type  .setTextColor(getResources().getColor(R.color.black_deep));
                tv_address_Type .setTextColor(getResources().getColor(R.color.black_deep));
                tv_address_search.setTextColor(getResources().getColor(R.color.black_deep));
                tv_pattern_Type.setTextColor(getResources().getColor(R.color.black_deep));
                layout_type_list.setBackgroundColor(getResources().getColor(R.color.white));
            }
        });


    }
    public void popupwindow_shaixuan(){
        View view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.item_shaixuan, null);
        popupWindow = new PopupWindow(findViewById(R.id.layout_ff), ActionBarOverlayLayout.LayoutParams.MATCH_PARENT, ActionBarOverlayLayout.LayoutParams.WRAP_CONTENT);
        popupWindow.setContentView(view);

        CustomGridView gv_Apple_Type= (CustomGridView) view.findViewById(R.id.gv_Apple_Type);
        List<String> list_gv_fenlei=new ArrayList<String>();
        for (int i=0;i<=8;i++){
            list_gv_fenlei.add("HDFE");
        }
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(TypeInfoActivity.this,R.layout.layout,R.id.tv_Type,list_gv_fenlei);
        gv_Apple_Type.setAdapter(adapter);
        gv_Apple_Type.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                
            }
        });
        /**
         * 牌号
         */
        CustomGridView gv_Number_Type= (CustomGridView) view.findViewById(R.id.gv_Number_Type);
        List<String> list_gv_paihao=new ArrayList<String>();
        for (int i=0;i<=8;i++){
            list_gv_paihao.add("FS8888");
        }
        ArrayAdapter<String> adapter1=new ArrayAdapter<String>(TypeInfoActivity.this,R.layout.layout,R.id.tv_Type,list_gv_paihao);
        gv_Number_Type.setAdapter(adapter1);
        /**
         * 应用
         */
        CustomGridView gv_Address_Type= (CustomGridView) view.findViewById(R.id.gv_Address_Type);
        List<String> list_gv_yingyong=new ArrayList<String>();
        for (int i=0;i<=8;i++){
            list_gv_yingyong.add("拉丝料");
        }
        ArrayAdapter<String> adapter2=new ArrayAdapter<String>(TypeInfoActivity.this,R.layout.item_type,R.id.tv_Type,list_gv_yingyong);
        gv_Address_Type.setAdapter(adapter2);
        /**
         * 地区
         */
        CustomGridView gv_Firm_Type= (CustomGridView) view.findViewById(R.id.gv_Firm_Type);
        List<String> list_gv_diqu=new ArrayList<String>();
        for (int i=0;i<=8;i++){
            list_gv_diqu.add("上海市");
        }
        ArrayAdapter<String> adapter3=new ArrayAdapter<String>(TypeInfoActivity.this,R.layout.item_type,R.id.tv_Type,list_gv_diqu);
        gv_Firm_Type.setAdapter(adapter3);

        /**
         * 重置按钮
         */
        Button bt_reset= (Button) view.findViewById(R.id.bt_reset);
        bt_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("令所选的为空","null");
                address="";
                fenlei="";
                yingyong="";
                paihao="";
                Log.d("令所选的为空",address+fenlei+yingyong+paihao);
            }
        });
        /**
         * 完成按钮
         */
        Button bt_finish= (Button) view.findViewById(R.id.bt_finish);
        bt_finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("根据所选的进行筛选","");
            }
        });

        popupWindow.setTouchable(true);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.setOutsideTouchable(true);
        popupWindow.setFocusable(true);

        popupWindow.showAsDropDown(findViewById(R.id.layout_center_Type),0,0);
        //  popupWindow.showAtLocation();

        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {

                tv_type_Type .setTextColor(getResources().getColor(R.color.black_deep));
                tv_apple_Type_for_Type  .setTextColor(getResources().getColor(R.color.black_deep));
                tv_address_Type .setTextColor(getResources().getColor(R.color.black_deep));
                tv_address_search.setTextColor(getResources().getColor(R.color.black_deep));
                tv_pattern_Type.setTextColor(getResources().getColor(R.color.black_deep));
                layout_type_list.setBackgroundColor(getResources().getColor(R.color.white));
            }
        });

    }
    public boolean isAbc(String str){
        while (true) {

            for (int i = 0; i < str.length(); i++) {
                if ((str.charAt(i) <= 'Z' && str.charAt(i) >= 'A')
                        || (str.charAt(i) <= 'z' && str.charAt(i) >= 'a')) {
                    //System.out.println(str.charAt(i) + "是字母");
                    return  true;
                }
                return false;
            }
        }

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

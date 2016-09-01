package com.silianchuangye.sumao.success.fragments.type;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ActionBarOverlayLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.custom.customCalendar.CustomGridView;
import com.silianchuangye.sumao.success.fragments.SearchActivityMVP.bean.SearchActivityBean;
import com.silianchuangye.sumao.success.fragments.homepage.goodInStock.GoodsInStockDetailActivityMVP.view.GoodsInStockDetailActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    private GridViewAdapter adpater3,adpater2,adpater1,adpater4;
    private String address,paihao,yingyong,fenlei;
    private RelativeLayout layout_type_list;
   // private List<String> list_String;
    private TextView tv_pattern_Type,tv_type_Type,tv_apple_Type_for_Type,tv_address_Type,tv_address_search;
    private SearchActivityBean searchActivityBean;
    //private String fenlei,paihao,yingyong,diqu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_type_info);
        Intent intent = getIntent();
        searchActivityBean = (SearchActivityBean)intent.getSerializableExtra("searchActivityBean");
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
                lv_Type.setDivider(getResources().getDrawable(R.color.transparent));
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
                lv_Type.setDivider(getResources().getDrawable(R.color.transparent));
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
                lv_Type.setDivider(getResources().getDrawable(R.color.transparent));
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
                lv_Type.setDivider(getResources().getDrawable(R.color.transparent));
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

    /**
     * 普通的pupopWindow
     * @param list
     */
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
                lv_Type.setDivider(getResources().getDrawable(R.color.gray_pressed));
                tv_type_Type .setTextColor(getResources().getColor(R.color.black_deep));
                tv_apple_Type_for_Type  .setTextColor(getResources().getColor(R.color.black_deep));
                tv_address_Type .setTextColor(getResources().getColor(R.color.black_deep));
                tv_address_search.setTextColor(getResources().getColor(R.color.black_deep));
                tv_pattern_Type.setTextColor(getResources().getColor(R.color.black_deep));
                layout_type_list.setBackgroundColor(getResources().getColor(R.color.white));
            }
        });


    }

    /**
     * 筛选的popupwindow
     */
    public void popupwindow_shaixuan(){
        View view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.item_shaixuan, null);
        popupWindow = new PopupWindow(findViewById(R.id.layout_ff), ActionBarOverlayLayout.LayoutParams.MATCH_PARENT, 1000);
        popupWindow.setContentView(view);

        final CustomGridView gv_Apple_Type= (CustomGridView) view.findViewById(R.id.gv_Apple_Type);
        final List<String> list_gv_fenlei=new ArrayList<String>();
        for (int i=0;i<=8;i++){
            list_gv_fenlei.add("HDFE");
        }
        adpater4=new GridViewAdapter(list_gv_fenlei);
//        final ArrayAdapter adapter=new ArrayAdapter(TypeInfoActivity.this,R.layout.layout,R.id.tv_Type,list_gv_fenlei);
        gv_Apple_Type.setAdapter(adpater4);
        gv_Apple_Type.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                adpater4.setSelectedPosition(position);
                adpater4.notifyDataSetChanged();
                fenlei=list_gv_fenlei.get(position).toString();
                Log.d("分类",fenlei);
            }
        });
        /**
         * 牌号
         */
        final CustomGridView gv_Number_Type= (CustomGridView) view.findViewById(R.id.gv_Number_Type);
        final List<String> list_gv_paihao=new ArrayList<String>();
        for (int i=0;i<=8;i++){
            list_gv_paihao.add("FS8888");
        }
        adpater1=new GridViewAdapter(list_gv_paihao);
         //ArrayAdapter adapter1=new ArrayAdapter(TypeInfoActivity.this,R.layout.layout,R.id.tv_Type,list_gv_paihao);
        gv_Number_Type.setAdapter(adpater1);
        gv_Number_Type.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                adpater1.setSelectedPosition(position);
                adpater1.notifyDataSetChanged();
                paihao=list_gv_paihao.get(position).toString();
            }
        });
        /**
         * 应用
         */
        final CustomGridView gv_Address_Type= (CustomGridView) view.findViewById(R.id.gv_Address_Type);
        final List<String> list_gv_yingyong=new ArrayList<String>();
        for (int i=0;i<=8;i++){
            list_gv_yingyong.add("拉丝料");
        }
        adpater2=new GridViewAdapter(list_gv_yingyong);
        //ArrayAdapter adapter2=new ArrayAdapter(TypeInfoActivity.this,R.layout.item_type,R.id.tv_Type,list_gv_yingyong);
        gv_Address_Type.setAdapter(adpater2);
        gv_Address_Type.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                adpater2.setSelectedPosition(position);
                adpater2.notifyDataSetChanged();
                yingyong=list_gv_yingyong.get(position).toString();
            }
        });
        /**
         * 地区
         */
        final CustomGridView gv_Firm_Type= (CustomGridView) view.findViewById(R.id.gv_Firm_Type);
        final List<String> list_gv_diqu=new ArrayList<String>();
        for (int i=0;i<=8;i++){
            list_gv_diqu.add("上海市");
        }
        adpater3=new GridViewAdapter(list_gv_diqu);
        //ArrayAdapter adapter3=new ArrayAdapter(TypeInfoActivity.this,R.layout.item_type,R.id.tv_Type,list_gv_diqu);
        gv_Firm_Type.setAdapter(adpater3);
        gv_Firm_Type.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(TypeInfoActivity.this, "dianjial", Toast.LENGTH_SHORT).show();
                //view.findViewById(R.id.layout).setBackgroundColor(getResources().getColor(R.color.btn_register_pressed));
                adpater3.setSelectedPosition(position);
                adpater3.notifyDataSetInvalidated();
                address=list_gv_diqu.get(position).toString();

            }
        });

        /**
         * 重置按钮
         */
        Button bt_reset= (Button) view.findViewById(R.id.bt_reset);
        bt_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("令所选的为空","null");
                //gv_Apple_Type.OnItemSelectedListener
//                gv_Apple_Type.setSelection(-1);
//                gv_Address_Type.setSelection(-1);
//                gv_Firm_Type.setSelection(-1);
//                gv_Number_Type.setSelection(-1);
                adpater1.setSelectedPosition(-1);
                adpater1.notifyDataSetChanged();
                adpater2.setSelectedPosition(-1);
                adpater2.notifyDataSetChanged();
                adpater3.setSelectedPosition(-1);
                adpater3.notifyDataSetChanged();
                adpater4.setSelectedPosition(-1);
                adpater4.notifyDataSetChanged();
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
                Log.d("参数",address+fenlei+yingyong+paihao);
                popupWindow.dismiss();
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
                lv_Type.setDivider(getResources().getDrawable(R.color.gray_pressed));
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
        RelativeLayout layout_Type;

    }
    class GridViewAdapter extends BaseAdapter{
        private List<String> list_String;
        private int selectedPosition=-1;
        LayoutInflater inflater;
        View view;
        ViewHolder Layoutholder;
        RelativeLayout Layout;
        TextView text = null;
        public GridViewAdapter(List<String> list) {
            // TODO Auto-generated constructor stub
           this.list_String=list;
        }
        @Override
        public int getCount() {
            return list_String.size();
        }

        @Override
        public String getItem(int position) {
            return list_String.get(position);
        }




        @Override
        public long getItemId(int position) {
            return position;
        }
        public void setSelectedPosition(int selectedPosition) {
            this.selectedPosition = selectedPosition;
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            inflater = (LayoutInflater) getApplication().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view=inflater.inflate(R.layout.item_type,parent,false);
            Layoutholder= (ViewHolder) view.getTag();
            if (Layoutholder==null){
                Layoutholder=new ViewHolder();
                Layoutholder.layout_Type= (RelativeLayout) view.findViewById(R.id.layout);
                Layoutholder.text_Name= (TextView) view.findViewById(R.id.tv_Type);
                view.setTag(Layoutholder);

            }else{
                Layoutholder= (ViewHolder) view.getTag();
            }
            Layout = Layoutholder.layout_Type;
            text = Layoutholder.text_Name;
            if (selectedPosition == position) {
                text.setSelected(true);
                text.setPressed(true);
               // Layout.setBackgroundColor(getResources().getColor(R.color.zixun_topbg));
                Layout.setBackgroundDrawable(getResources().getDrawable(R.drawable.text_corner));
                text.setTextColor(Color.BLACK);
            } else {
                text.setSelected(false);
                text.setPressed(false);
                Layout.setBackgroundColor(Color.WHITE);
                text.setTextColor(Color.BLACK);

            }


            text.setText(list_String.get(position).toString());

            return view;
        }
    }



}

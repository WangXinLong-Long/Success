package com.silianchuangye.sumao.success.fragments;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.silianchuangye.sumao.success.HX.Constant;
import com.silianchuangye.sumao.success.HX.ui.LoginActivity;
import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.fragments.SearchActivityMVP.view.SearchActivity;
import com.silianchuangye.sumao.success.fragments.type.TypeInfoActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/4/20 0020.
 */
public class PagerTwo extends BasePager {
     private RelativeLayout view;
     private ListView lv_Show_Type;
     private List<String> list;
     private ArrayAdapter<String> adapter;
     private GridView gv_Apple_Type;
     private GridView gv_Numebr_Type;
     private GridView gv_Address_Type;
     private GridView gv_Firm_Type;
     private RelativeLayout layout_Apple;
     private RelativeLayout layout_Number;
     private RelativeLayout layout_Address;
     private RelativeLayout layout_Firm;
     private ListAdapter adapter1;


    @Override
    public void myClickSearch() {
        //调到搜索页
        Log.d("点击搜索","点击搜素");
        Intent intent=new Intent(mActivity,SearchActivity.class);
        startActivity(intent);
    }
    @Override
    public void initDate() {
      view= (RelativeLayout) View.inflate(mActivity,R.layout.fragmenttwo,null);
      fl_content.addView(view);
        lv_Show_Type= (ListView) view.findViewById(R.id.lv_Show_Type);
        list=new ArrayList<String>();
        list.add("丁苯胶");
        list.add("丁苯胶");
        list.add("丁苯胶");
        list.add("丁苯胶");
        list.add("丁苯胶");
        list.add("丁苯胶");
        list.add("丁苯胶");
        list.add("其他");
        adapter1=new ListAdapter(list);
        lv_Show_Type.setAdapter(adapter1);
        lv_Show_Type.setDividerHeight(2);
        lv_Show_Type.setOnItemClickListener(new ListView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                // TODO Auto-generated method stub
                adapter1.setSelectedPosition(arg2);
                adapter1.notifyDataSetInvalidated();

            }
        });
        apple_Type();
        number_Type();
        address_Type();
        firm_Type();

    }
    public void apple_Type(){
        gv_Apple_Type= (GridView) view.findViewById(R.id.gv_Apple_Type);
        List<String> list_Apple=new ArrayList<String>();
        list_Apple.add("拉丝料");
        adapter=new ArrayAdapter<String>(getActivity(),R.layout.item_type,R.id.tv_Type,list_Apple);
        gv_Apple_Type.setAdapter(adapter);
        layout_Apple= (RelativeLayout) view.findViewById(R.id.layout_Apple_Type);

        gv_Apple_Type.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Log.d("点击","gridview的item");
                    Intent intent = new Intent(getActivity(), TypeInfoActivity.class);
                    startActivity(intent);
                }
            });
        if (list_Apple.size()<=0){
            layout_Apple.setVisibility(View.GONE);
        }




    }
    public void number_Type(){
        gv_Numebr_Type= (GridView) view.findViewById(R.id.gv_Number_Type);
        List<String> list_Number=new ArrayList<String>();
        list_Number.add("8305");
        list_Number.add("Ncbc-8320");
        list_Number.add("L5e89");
        list_Number.add("L5e90");
        list_Number.add("s1003");
        list_Number.add("T305");
        list_Number.add("T8395");
        list_Number.add("Xd-040");
        adapter=new ArrayAdapter<String>(getActivity(),R.layout.layout,R.id.tv_Type,list_Number);
        gv_Numebr_Type.setAdapter(adapter);
        layout_Number= (RelativeLayout) view.findViewById(R.id.layout_Number_Type);
        if(list_Number.size()<=0){
            layout_Number.setVisibility(View.GONE);
        }
        gv_Numebr_Type.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(getActivity(),TypeInfoActivity.class);
                startActivity(intent);
            }
        });

    }
    public void address_Type(){
        gv_Address_Type= (GridView) view.findViewById(R.id.gv_Address_Type);
        List<String> list_Address=new ArrayList<String>();
        list_Address.add("上海市");
        list_Address.add("兰州市");
        list_Address.add("天津市");
        list_Address.add("太原市");
        list_Address.add("常州市");
        list_Address.add("成都市");
        list_Address.add("武汉市");
        list_Address.add("其他");
        list_Address.add("上海市");
        list_Address.add("兰州市");
        list_Address.add("天津市");
        list_Address.add("太原市");
        list_Address.add("常州市");
        list_Address.add("成都市");
        list_Address.add("武汉市");
        list_Address.add("其他");
        list_Address.add("上海市");
        list_Address.add("兰州市");
        list_Address.add("天津市");
        list_Address.add("太原市");
        list_Address.add("常州市");
        list_Address.add("成都市");
        list_Address.add("武汉市");
        list_Address.add("其他");
        list_Address.add("上海市");
        list_Address.add("兰州市");
        list_Address.add("天津市");
        list_Address.add("太原市");
        list_Address.add("常州市");
        list_Address.add("成都市");
        list_Address.add("武汉市");
        list_Address.add("其他");
        list_Address.add("上海市");
        list_Address.add("兰州市");
        list_Address.add("天津市");
        list_Address.add("太原市");
        list_Address.add("常州市");
        list_Address.add("成都市");
        list_Address.add("武汉市");
        list_Address.add("其他");
        adapter=new ArrayAdapter<String>(getActivity(),R.layout.item_type,R.id.tv_Type,list_Address);
        gv_Address_Type.setAdapter(adapter);
        layout_Address= (RelativeLayout) view.findViewById(R.id.layout_Address_Type);
        if (list_Address.size()<=0){
            layout_Address.setVisibility(View.GONE);
        }

    }
    public void firm_Type(){
        gv_Firm_Type= (GridView) view.findViewById(R.id.gv_Firm_Type);
        List<String> list_Firm=new ArrayList<String>();
        list_Firm.add("神化煤炭");
        list_Firm.add("哈尔滨石化");
        list_Firm.add("大庆石化");
        list_Firm.add("广西石化");
        list_Firm.add("抚顺石化");
        list_Firm.add("独山子石化");
        adapter=new ArrayAdapter<String>(getActivity(),R.layout.layout,R.id.tv_Type,list_Firm);
        gv_Firm_Type.setAdapter(adapter);
        gv_Firm_Type.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(getActivity(),TypeInfoActivity.class);
                startActivity(intent);
            }
        });
        layout_Firm= (RelativeLayout) view.findViewById(R.id.layout_Firm_Type);
        if (list_Firm.size()<=0){
            layout_Firm.setVisibility(View.GONE);
        }

    }

    @Override
    public void myClickLeft() {

    }

    @Override
    public void myClickRight() {

        Intent intent = new Intent();
        intent.setClass(mActivity, LoginActivity.class);
        intent.putExtra(Constant.MESSAGE_TO_INTENT_EXTRA, Constant.MESSAGE_TO_DEFAULT);
        startActivity(intent);
    }
    class ListAdapter extends BaseAdapter{
        List<String> list = null;
        LayoutInflater inflater;
        View view;
        LayoutHolder Layoutholder;
        RelativeLayout Layout = null;
        TextView text = null;
        private int selectedPosition = -1;// 选中的位置


        public ListAdapter(List<String> List) {
            // TODO Auto-generated constructor stub
            list = List;
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
        public long getItemId(int position) {
            return position;
        }

        public void setSelectedPosition(int selectedPosition) {
            this.selectedPosition = selectedPosition;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            inflater = (LayoutInflater) mActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view=inflater.inflate(R.layout.item_type_show,parent,false);
            Layoutholder= (LayoutHolder) view.getTag();
            if (Layoutholder==null){
                Layoutholder=new LayoutHolder();
                Layoutholder.layout_Type= (RelativeLayout) view.findViewById(R.id.layout_Type);
                Layoutholder.tv_item= (TextView) view.findViewById(R.id.tv_type_item);
                view.setTag(Layoutholder);

            }
            Layout = Layoutholder.layout_Type;
            text = Layoutholder.tv_item;
            if (selectedPosition == position) {
                text.setSelected(true);
                text.setPressed(true);
                Layout.setBackgroundColor(getResources().getColor(R.color.gray_type));
            } else {
                text.setSelected(false);
                text.setPressed(false);
                Layout.setBackgroundColor(Color.WHITE);

            }
            text.setTextColor(Color.BLACK);
            text.setText(list.get(position).toString());

            return view;
        }
    }
    class LayoutHolder{

        RelativeLayout layout_Type;
        TextView tv_item;
    }



}

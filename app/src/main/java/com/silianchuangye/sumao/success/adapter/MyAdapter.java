package com.silianchuangye.sumao.success.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.utils.LogUtils;

import java.util.List;
import java.util.Map;
import java.util.zip.Inflater;

/**
 * Created by junjun on 2016/5/30.
 */
public  class MyAdapter extends BaseExpandableListAdapter {
    private List<Map<String,Object>> listparrent;
    private List<List<Map<String,Object>>> listitem;
    private Context context;
    //private String name;

    public MyAdapter(List<Map<String,Object>> listparrent,List<List<Map<String,Object>>> listitem,Context context){
        this.listparrent=listparrent;
        this.listitem=listitem;
        this.context=context;
        //this.name=name;
    }


    //获取到子项数据，arg0组的序号，arg1子项序号
    public Object getChild(int arg0, int arg1) {
        return listitem.get(arg0).get(arg1);
    }
    //获取到子项序号，arg0组的序号，arg1子项序号
    public long getChildId(int arg0, int arg1) {
        return arg1;
    }
    //获取到arg0组中子项个数
    public int getChildrenCount(int arg0) {
        return listitem.get(arg0).size();
    }
    //获取到arg0组的数据
    public Object getGroup(int arg0) {
        return listparrent.get(arg0);
    }
    //获取到一共多少组
    public int getGroupCount() {

        return listparrent.size();
    }
    //获取到组的序号
    public long getGroupId(int arg0) {
        return arg0;
    }
    //获取到组布局的效果
    public View getGroupView(int arg0, boolean arg1, View arg2, ViewGroup arg3) {
        //View view =LayoutInflater().inflate(R.layout.group, arg3, false);
        View view=View.inflate(context,R.layout.group,null);
        TextView tvTitle  = (TextView) view.findViewById(R.id.tv_Order_id_value);
        TextView tv_people= (TextView) view.findViewById(R.id.tv_order_price_people_name);
        TextView tv_order_price_state  = (TextView) view.findViewById(R.id.tv_order_price_state);
        TextView tvprice  = (TextView) view.findViewById(R.id.tv_order_price_value);
//        Log.d("map",listparrent.get(arg0).toString());
//        Log.d("采购员",listparrent.get(arg0).get("name").toString());
        tv_order_price_state.setText(listparrent.get(arg0).get("states").toString());
        tvTitle.setText(listparrent.get(arg0).get("id").toString());
     //  Log.e("TAG","listparrent.get(arg0).get(\"p\").toString()="+listparrent.get(arg0).get("p").toString());
        tv_people.setText(listparrent.get(arg0).get("name").toString());
        tvprice.setText(listparrent.get(arg0).get("price").toString());
        return view;
    }
    //获取到子项布局效果
    public View getChildView(int arg0, int arg1, boolean arg2, View arg3,
                             ViewGroup arg4) {
        //View view =getActivity().getLayoutInflater().inflate(R.layout.item, arg4,false);
        View view=View.inflate(context,R.layout.item,null);
        TextView tvName = (TextView) view.findViewById(R.id.tv_order_goods_type);
        tvName.setText(listitem.get(arg0).get(arg1).get("type").toString());
        TextView tvContent = (TextView) view.findViewById(R.id.tv_order_productionName);
        tvContent.setText(listitem.get(arg0).get(arg1).get("name").toString());
        return view;
    }
    public boolean hasStableIds() {
        return false;
    }




    public boolean isChildSelectable(int arg0, int arg1) {
        return true;
    }

}


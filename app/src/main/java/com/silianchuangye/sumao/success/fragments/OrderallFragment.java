package com.silianchuangye.sumao.success.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.fragments.OrderManagement.SpotOrder.SpotOrder;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class OrderallFragment extends Fragment {
    private ExpandableListView elvDemo;
    private List<Map<String,Object>> listparrent;
    private List<List<Map<String,Object>>> listitem;

    public OrderallFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_orderall, container, false);
                elvDemo= (ExpandableListView) view.findViewById(R.id.elvDemo);
        elvDemo.setGroupIndicator(null);
        elvDemo.setDivider(null);
        listparrent=new ArrayList<Map<String,Object>>();
        Map<String,Object> map1=new Hashtable<String,Object>();
        map1.put("id","1000001");
        map1.put("price","70000.0");
        listparrent.add(map1);
        Map<String,Object> map2=new Hashtable<String,Object>();
        map2.put("id","1000001");
        map2.put("price","88888888");
        listparrent.add(map2);

        listitem=new ArrayList<List<Map<String,Object>>>();
        List<Map<String,Object>> list1=new ArrayList<Map<String,Object>>();
        Map<String,Object> map=new Hashtable<String,Object>();
        map.put("type","四联创业");
        map.put("name","中国");
        Map<String,Object> map3=new Hashtable<String,Object>();
        map3.put("type","四联创业");
        map3.put("name","中国");
        Map<String,Object> map4=new Hashtable<String,Object>();
        map4.put("type","四联创业");
        map4.put("name","中国");
        list1.add(map);
        list1.add(map3);
        list1.add(map4);

        List<Map<String,Object>> list2=new ArrayList<Map<String,Object>>();

        Map<String,Object> map5=new Hashtable<String,Object>();
        map5.put("type","四联创业");
        map5.put("name","中国");

        list2.add(map5);

        listitem.add(list1);
        listitem.add(list2);

        MyAdapter adapter=new MyAdapter();
        elvDemo.setAdapter(adapter);
        if(adapter!=null && listparrent!=null){
            for (int i = 0; i < listparrent.size(); i++) {
                elvDemo.expandGroup(i);
            }}
        elvDemo.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
//                Toast.makeText(getContext(), "点击title", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent();
                intent.setClass(getActivity(), SpotOrder.class);
                startActivity(intent);
                return true;

            }
        });
        elvDemo.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
//                Toast.makeText(getContext(), "点击内容", Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        return view;
    }
    class MyAdapter extends BaseExpandableListAdapter {

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
            View view =getActivity().getLayoutInflater().inflate(R.layout.group, arg3, false);
            TextView tvTitle  = (TextView) view.findViewById(R.id.tv_Order_id_value);
            tvTitle.setText(listparrent.get(arg0).get("id").toString());
            TextView tvprice  = (TextView) view.findViewById(R.id.tv_order_price_value);
            tvTitle.setText(listparrent.get(arg0).get("price").toString());
            return view;
        }
        //获取到子项布局效果
        public View getChildView(int arg0, int arg1, boolean arg2, View arg3,
                                 ViewGroup arg4) {
            View view =getActivity().getLayoutInflater().inflate(R.layout.item, arg4,false);

            TextView tvName = (TextView) view.findViewById(R.id.tv_order_goods);
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


}

package com.silianchuangye.sumao.success.fragments.myPlasticTrade.OrderManagement.SpotOrder;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.adapter.MyAdapter;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.OrderManagement.OrderDetails.AlreadyPaidActivity;
import com.silianchuangye.sumao.success.utils.LogUtils;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class PresellallFragment extends Fragment {
    private ExpandableListView elvDemo;
    private List<Map<String,Object>> listparrent;
    private List<List<Map<String,Object>>> listitem;



    public PresellallFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_presellall, container, false);
        //实例化
        elvDemo= (ExpandableListView) view.findViewById(R.id.elvDemo);
        //去掉expandListview的特别的下拉标志
        elvDemo.setGroupIndicator(null);
        //去掉ListView之间的线
        elvDemo.setDivider(null);

        listparrent=new ArrayList<Map<String,Object>>();
        Map<String,Object> map1=new Hashtable<String,Object>();
        map1.put("id","1000001");
        map1.put("price","70000.0");
        map1.put("states","待支付");
        map1.put("name","张三");
        listparrent.add(map1);
        Map<String,Object> map2=new Hashtable<String,Object>();
        map2.put("id","1000001");
        map2.put("price","88888888");
        map2.put("states","已支付");
        map2.put("name","李四");
        listparrent.add(map2);

        listitem=new ArrayList<List<Map<String,Object>>>();
        final List<Map<String,Object>> list1=new ArrayList<Map<String,Object>>();
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


        MyAdapter adapter=new MyAdapter(listparrent,listitem,getActivity());
        elvDemo.setAdapter(adapter);
        if(adapter!=null && listparrent!=null){
            for (int i = 0; i < listparrent.size(); i++) {
                elvDemo.expandGroup(i);
            }}
        elvDemo.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
//                Toast.makeText(getContext(), "点击title", Toast.LENGTH_SHORT).show();
                LogUtils.log(listparrent.get(groupPosition).get("states")+"");
                if ("已支付".equals(listparrent.get(groupPosition).get("states"))){
                    Intent intent = new Intent();
                    intent.setClass(getActivity(),AlreadyPaidActivity.class);
                    startActivity(intent);
                }else if("待支付".equals(listparrent.get(groupPosition).get("states"))){
                    Intent intent = new Intent();
                    intent.setClass(getActivity(), SpotOrder.class);
                    startActivity(intent);
                }

                return true;

            }
        });

        return view;
    }

}

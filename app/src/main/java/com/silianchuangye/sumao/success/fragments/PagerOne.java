package com.silianchuangye.sumao.success.fragments;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.SimpleAdapter;
import android.widget.Toast;


import com.silianchuangye.sumao.success.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/4/20 0020.
 */
public class PagerOne extends BasePager {
    private ViewPager vpFragmentone;
    private GridView gvFragmentone;
    private List<Map<String,Object>> list;
    @Override
    public void myClickSearch() {
    }

    @Override
    public void initDate() {
        LinearLayout view= (LinearLayout) View.inflate(mActivity,R.layout.fragmentone,null);
        fl_content.addView(view);
        vpFragmentone= (ViewPager) view.findViewById(R.id.vpfragmentone);
        gvFragmentone= (GridView) view.findViewById(R.id.gvfragmentone);
        list=new ArrayList<Map<String,Object>>();
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("icon",R.mipmap.goods);
        list.add(map);
        Map<String,Object> map1=new HashMap<String,Object>();
        map1.put("icon",R.mipmap.presell);
        list.add(map1);
        Map<String,Object> map2=new HashMap<String,Object>();
        map2.put("icon",R.mipmap.adwords);
        list.add(map2);
        Map<String,Object> map3=new HashMap<String,Object>();
        map3.put("icon",R.mipmap.order);
        list.add(map3);
        Map<String,Object> map4=new HashMap<String,Object>();
        map4.put("icon",R.mipmap.direct);
        list.add(map4);
        Map<String,Object> map5=new HashMap<String,Object>();
        map5.put("icon",R.mipmap.aa);
        list.add(map5);
        Map<String,Object> map6=new HashMap<String,Object>();
        map6.put("icon",R.mipmap.consult);
        list.add(map6);
        Map<String,Object> map7=new HashMap<String,Object>();
        map7.put("icon",R.mipmap.more);
        list.add(map7);
        SimpleAdapter adapter=new SimpleAdapter(mActivity,list,R.layout.fragmentoneitem,new String[]{"icon"},new int[]{R.id.ivIcon});
        gvFragmentone.setAdapter(adapter);
        /**
         * gridView里面icon的点击事件
         */
        gvFragmentone.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(list.get(position).get("icon").equals(R.mipmap.more)){
                    if(list.size()==8){
                    list.remove(position);
                   // Toast.makeText(mActivity, "点击了更多按钮", Toast.LENGTH_SHORT).show();
                    Map<String,Object> map7=new HashMap<String,Object>();
                    map7.put("icon",R.mipmap.togther);
                    list.add(map7);
                    Map<String,Object> map8=new HashMap<String,Object>();
                    map8.put("icon",R.mipmap.groupon);
                    list.add(map8);
                    Map<String,Object> map9=new HashMap<String,Object>();
                    map9.put("icon",R.mipmap.more);
                    list.add(map9);
                    SimpleAdapter adapter=new SimpleAdapter(mActivity,list,R.layout.fragmentoneitem,new String[]{"icon"},new int[]{R.id.ivIcon});
                    gvFragmentone.setAdapter(adapter);
                }
                }else if(list.get(position).get("icon").equals(R.mipmap.togther)){
                    Toast.makeText(mActivity, "点击了撮合按钮", Toast.LENGTH_SHORT).show();
                }else if(list.get(position).get("icon").equals(R.mipmap.groupon)){
                    Toast.makeText(mActivity, "点击了团购按钮", Toast.LENGTH_SHORT).show();
                }else if(list.get(position).get("icon").equals(R.mipmap.goods)){
                    Toast.makeText(mActivity, "点击了现货按钮", Toast.LENGTH_SHORT).show();
                }else if(list.get(position).get("icon").equals(R.mipmap.presell)){
                    Toast.makeText(mActivity, "点击了预售按钮", Toast.LENGTH_SHORT).show();
                }else if(list.get(position).get("icon").equals(R.mipmap.adwords)){
                    Toast.makeText(mActivity, "点击了竞拍按钮", Toast.LENGTH_SHORT).show();
                }else if(list.get(position).get("icon").equals(R.mipmap.order)){
                    Toast.makeText(mActivity, "点击了点价按钮", Toast.LENGTH_SHORT).show();
                }else if(list.get(position).get("icon").equals(R.mipmap.direct)){
                    Toast.makeText(mActivity, "点击了上游直销按钮", Toast.LENGTH_SHORT).show();
                }else if(list.get(position).get("icon").equals(R.mipmap.aa)){
                    Toast.makeText(mActivity, "点击了物流按钮", Toast.LENGTH_SHORT).show();
                }else if(list.get(position).get("icon").equals(R.mipmap.consult)){
                    Toast.makeText(mActivity, "点击了塑贸咨询按钮", Toast.LENGTH_SHORT).show();
                }
            }
        });



    }

    @Override
    public void myClickLeft() {

    }

    @Override
    public void myClickRight() {

    }
}

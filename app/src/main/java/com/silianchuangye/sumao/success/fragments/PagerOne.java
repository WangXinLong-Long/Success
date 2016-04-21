package com.silianchuangye.sumao.success.fragments;

import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.silianchuangye.sumao.success.R;

import java.util.ArrayList;
import java.util.Hashtable;
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
        Map<String,Object> map1=new Hashtable<String,Object>();
        map1.put("icon",R.mipmap.goods);
        map1.put("text","现货");
        list.add(map1);
        Map<String,Object> map2=new Hashtable<String,Object>();
        map2.put("icon",R.mipmap.presell);
        map2.put("text","预售");
        list.add(map2);
        Map<String,Object> map4=new Hashtable<String,Object>();
        map4.put("icon",R.mipmap.adwords);
        map4.put("text","竞拍");
        list.add(map4);
        Map<String,Object> map5=new Hashtable<String,Object>();
        map5.put("icon",R.mipmap.vertet);
        map5.put("text","点价");
        list.add(map5);
        Map<String,Object> map6=new Hashtable<String,Object>();
        map6.put("icon",R.mipmap.direct);
        map6.put("text","上游直售");
        list.add(map6);
        Map<String,Object> map7=new Hashtable<String,Object>();
        map7.put("icon",R.mipmap.logistics);
        map7.put("text","物流");
        list.add(map7);
        Map<String,Object> map8=new Hashtable<String,Object>();
        map8.put("icon",R.mipmap.consult);
        map8.put("text","塑贸咨询");
        list.add(map8);
        Map<String,Object> map9=new Hashtable<String,Object>();
        map9.put("icon",R.mipmap.more);
        map9.put("text","更多");
        list.add(map9);
        SimpleAdapter adapter=new SimpleAdapter(mActivity,list,R.layout.fragmentoneitem,new String[]{"icon","text"},new int[]{R.id.ivIcon,R.id.tvIcon});
        gvFragmentone.setAdapter(adapter);



    }

    @Override
    public void myClickLeft() {

    }

    @Override
    public void myClickRight() {

    }
}

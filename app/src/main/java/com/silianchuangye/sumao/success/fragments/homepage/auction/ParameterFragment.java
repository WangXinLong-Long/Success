package com.silianchuangye.sumao.success.fragments.homepage.auction;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.fragments.homepage.goodInStock.GoodsInStockDetailActivityMVP.bean.CLAttribute;
import com.silianchuangye.sumao.success.utils.LogUtils;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class ParameterFragment extends Fragment {
    private ListView lv_parameter;
    private List<Map<String,Object>> list;


    public ParameterFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_parameter, container, false);
        lv_parameter= (ListView) view.findViewById(R.id.lv_parameter);
        Bundle bundle = getArguments();
        ArrayList<CLAttribute> cl_attribute =(ArrayList<CLAttribute>) bundle.getSerializable("cl_attribute");
        LogUtils.log("ParameterFragment---->cl_attribute:"+cl_attribute);
        list=new ArrayList<Map<String,Object>>();
        for (int i = 0;i<cl_attribute.size();i++){
            Map<String,Object> map=new Hashtable<String,Object>();
            map.put("text",cl_attribute.get(i).getAttrName());
            map.put("value",cl_attribute.get(i).getAttrValue());
            list.add(map);

        }
      /*  Map<String,Object> map1=new Hashtable<String,Object>();
        map1.put("text","分类");
        map1.put("value","LLDPE");
        list.add(map1);
        Map<String,Object> map2=new Hashtable<String,Object>();
        map2.put("text","应用");
        map2.put("value","注塑料");
        list.add(map2);
        Map<String,Object> map3=new Hashtable<String,Object>();
        map3.put("text","牌号");
        map3.put("value","DNDA8320");
        list.add(map3);
        Map<String,Object> map4=new Hashtable<String,Object>();
        map4.put("text","国家");
        map4.put("value","中国");
        list.add(map4);
        Map<String,Object> map5=new Hashtable<String,Object>();
        map5.put("text","集团公司");
        map5.put("value","中石油");
        list.add(map5);
        Map<String,Object> map6=new Hashtable<String,Object>();
        map6.put("text","生产企业");
        map6.put("value","大庆石化");
        list.add(map6);*/

        SimpleAdapter adapter=new SimpleAdapter(getActivity(),list,R.layout.item_vessel_tow,new String[]{"text","value"},new int[]{R.id.tv_a,R.id.tv_b});
        lv_parameter.setAdapter(adapter);
        return view;

    }

}

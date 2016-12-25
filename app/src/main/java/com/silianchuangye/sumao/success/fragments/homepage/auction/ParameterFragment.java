package com.silianchuangye.sumao.success.fragments.homepage.auction;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.fragments.homepage.goodInStock.goodsInStockDetail.GoodsInStockDetailActivityMVP.bean.GoodsInStockDetailBean;
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
        List<GoodsInStockDetailBean.ClAttributeBean> cl_attribute =( List<GoodsInStockDetailBean.ClAttributeBean>) bundle.getSerializable("cl_attribute");
        LogUtils.log("ParameterFragment---->cl_attribute:"+cl_attribute);
        list=new ArrayList<Map<String,Object>>();
        for (int i = 0;i<cl_attribute.size();i++){
            Map<String,Object> map=new Hashtable<String,Object>();
            map.put("text",cl_attribute.get(i).getAttrName());
            map.put("value",cl_attribute.get(i).getAttrValue());
            list.add(map);

        }


        SimpleAdapter adapter=new SimpleAdapter(getActivity(),list,R.layout.item_vessel_tow,new String[]{"text","value"},new int[]{R.id.tv_a,R.id.tv_b});
        lv_parameter.setAdapter(adapter);
        return view;

    }

}

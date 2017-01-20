package com.silianchuangye.sumao.success.fragments.homepage.auction;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.fragments.homepage.auction.AuctionMVP.bean.AuctionDetailBean;
import com.silianchuangye.sumao.success.fragments.homepage.goodInStock.goodsInStockDetail.GoodsInStockDetailActivityMVP.bean.GoodsInStockDetailBean;
import com.silianchuangye.sumao.success.fragments.homepage.groupbuying.groupBuyingDetailMVP.bean.GroupBuyingDetailBean;
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
    private List<Map<String, Object>> list;
    private AuctionDetailBean auctionDetailBean;
    private String type;
    private GroupBuyingDetailBean groupBuyingDetailBean;


    public ParameterFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_parameter, container, false);
        lv_parameter = (ListView) view.findViewById(R.id.lv_parameter);
        Bundle bundle = getArguments();
        List<GoodsInStockDetailBean.ClAttributeBean> cl_attribute = (List<GoodsInStockDetailBean.ClAttributeBean>) bundle.getSerializable("cl_attribute");
        type = bundle.getString("type");
        if (type.equals("group")) {
            groupBuyingDetailBean = (GroupBuyingDetailBean) bundle.getSerializable("auctionDetailBean");
        } else {
            auctionDetailBean = (AuctionDetailBean) bundle.getSerializable("auctionDetailBean");
        }
        LogUtils.log("ParameterFragment---->cl_attribute:" + cl_attribute);
        list = new ArrayList<Map<String, Object>>();
        initData();
        if (cl_attribute != null) {
            for (int i = 0; i < cl_attribute.size(); i++) {
                LogUtils.log("产品参数--->" + cl_attribute.get(i).getAttrValue());
                if (!cl_attribute.get(i).getAttrValue().equals("null")) {
                    Map<String, Object> map = new Hashtable<String, Object>();
                    map.put("text", cl_attribute.get(i).getAttrName());
                    map.put("value", cl_attribute.get(i).getAttrValue());
                    list.add(map);
                }
            }
        }
        SimpleAdapter adapter = new SimpleAdapter(getActivity(), list, R.layout.item_vessel_tow, new String[]{"text", "value"}, new int[]{R.id.tv_a, R.id.tv_b});
        lv_parameter.setAdapter(adapter);
        return view;

    }

    private void initData() {
        Map<String, Object> map1 = new Hashtable<>();
        map1.put("text", "产品名称");
        LogUtils.log("auctionDetailBean--->" + auctionDetailBean);
        if (type.equals("auction")) {
            if (auctionDetailBean.getCl_mingcheng() == null) {
                map1.put("value", "~~");
            } else {
                map1.put("value", auctionDetailBean.getCl_mingcheng());
            }
        } else if (type.equals("group")) {
            if (groupBuyingDetailBean.getCl_mingcheng() == null) {
                map1.put("value", "~~");
            } else {
                map1.put("value", groupBuyingDetailBean.getCl_mingcheng());
            }
        }
        list.add(map1);
        Map<String, Object> map2 = new Hashtable<>();
        map2.put("text", "分类");
        if (type.equals("auction")) {
            map2.put("value", auctionDetailBean.getCl_fenlei());
        } else if (type.equals("group")) {
            if (groupBuyingDetailBean.getCl_fenlei() == null) {
                map2.put("value", "~~");
            } else {
                map2.put("value", groupBuyingDetailBean.getCl_fenlei());
            }
        }
        list.add(map2);
        Map<String, Object> map3 = new Hashtable<>();
        map3.put("text", "应用");
        if (type.equals("auction")) {
            map3.put("value", auctionDetailBean.getYingyong());
        } else if (type.equals("group")) {
            map3.put("value", groupBuyingDetailBean.getYingyong());
        }
        list.add(map3);
        Map<String, Object> map4 = new Hashtable<>();
        map4.put("text", "牌号");
        if (type.equals("auction")) {
            map4.put("value", auctionDetailBean.getGradeNumber());
        } else if (type.equals("group")) {
            map4.put("value", groupBuyingDetailBean.getGradeNumber());
        }
        list.add(map4);
        Map<String, Object> map5 = new Hashtable<>();
        map5.put("text", "国家");
        if (type.equals("auction")) {
            map5.put("value", auctionDetailBean.getCountry());
        } else if (type.equals("group")) {
            if (groupBuyingDetailBean.getCl_guojia() == null) {
                map5.put("value", "~~");
            } else {

                map5.put("value", groupBuyingDetailBean.getCl_guojia());
            }
        }
        list.add(map5);
        Map<String, Object> map6 = new Hashtable<>();
        map6.put("text", "集团公司");
        if (type.equals("auction")) {

            if (auctionDetailBean.getGroup() == null) {
                map6.put("value", "--");
            } else {
                map6.put("value", auctionDetailBean.getGroup());
            }
        } else if (type.equals("group")) {
            if (groupBuyingDetailBean.getCl_gongsi() == null) {
                map6.put("value", "--");
            } else {
                map6.put("value", groupBuyingDetailBean.getCl_gongsi());
            }
        }
        list.add(map6);
        Map<String, Object> map7 = new Hashtable<>();
        map7.put("text", "生产企业");
        if (type.equals("auction")) {
            map7.put("value", auctionDetailBean.getManufacturer());
        } else if (type.equals("group")) {
            map7.put("value", groupBuyingDetailBean.getManufacturer());
        }
        list.add(map7);

    }

}

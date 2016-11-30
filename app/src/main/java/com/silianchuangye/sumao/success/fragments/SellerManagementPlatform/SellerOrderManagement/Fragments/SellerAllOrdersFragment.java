package com.silianchuangye.sumao.success.fragments.SellerManagementPlatform.SellerOrderManagement.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.adapter.SellerOrderManagementAdapter;
import com.silianchuangye.sumao.success.fragments.SellerManagementPlatform.SellerOrderManagement.SellerAlreadyPaidOrderDetails;
import com.silianchuangye.sumao.success.fragments.SellerManagementPlatform.SellerOrderManagement.SellerNotPaidOrderDetails;
import com.silianchuangye.sumao.success.model.SellerOrderanagementModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/7/14 0014.
 * 订单管理，全部订单fragment界面
 */
public class SellerAllOrdersFragment extends Fragment implements View.OnClickListener,AdapterView.OnItemClickListener{
    private List<SellerOrderanagementModel> lists;
    SellerOrderanagementModel sellerOrderanagementModel;
    private ListView all_orders_fragment_list;
    SellerOrderManagementAdapter adapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.seller_all_orders_fragment,container,false);
        all_orders_fragment_list = ((ListView) view.findViewById(R.id.all_orders_fragment_list));
        initData();
        adapter = new SellerOrderManagementAdapter(getActivity(),lists);
        all_orders_fragment_list.setAdapter(adapter);
        all_orders_fragment_list.setOnItemClickListener(this);
        return view;
    }

    private void initData() {
        lists = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            sellerOrderanagementModel = new SellerOrderanagementModel();
            //    订单号
            sellerOrderanagementModel.setOrderNumber(i+"12121");
            //    订单类型
            sellerOrderanagementModel.setOrderType("现货订单");
            //    订单总额
            sellerOrderanagementModel.setTotalOrder("12121");
            //    买方企业名称
            sellerOrderanagementModel.setNameOfBuyerEnterprise("四联创业深圳分公司");
            //    下单人
            sellerOrderanagementModel.setPlaceAnOrderPerson(i+"赵本");
            //    下单日期
            sellerOrderanagementModel.setOrderDate(i+"2012-12-12");
            //    订单状态
            if (i%2==0)
            {
                sellerOrderanagementModel.setOrderStatus("待支付");
            }else
            {
                sellerOrderanagementModel.setOrderStatus("已支付");
            }
            //    业务员
            sellerOrderanagementModel.setSalesman("潘长");
            lists.add(sellerOrderanagementModel);
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId())
        {

        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent();
        if ("待支付".equals(lists.get(position).getOrderStatus())){
            intent.putExtra("title", lists.get(position).getOrderNumber());
            intent.setClass(getActivity(),SellerNotPaidOrderDetails.class);
        }else {
            intent.setClass(getActivity(),SellerAlreadyPaidOrderDetails.class);
        }
        startActivity(intent);
    }
}

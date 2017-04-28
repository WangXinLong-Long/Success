package com.silianchuangye.sumao.success.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.OrderManagement.SpotOrder.preSell.presellallFragmentMVP.bean.PreSellAllBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by junjun on 2016/5/30.
 */
public class MyAdapter extends BaseExpandableListAdapter {
    //    private List<Map<String,Object>> listparrent;
//    private List<List<Map<String,Object>>> listitem;
    private List<PreSellAllBean.OrderBean> order;
    private Context context;
    private List<List<ChildBean>> childBeans;
    private List<GroupBean> groupBeans;

    //private String name;

    public MyAdapter(Context context) {
        this.order = new ArrayList<>();
        childBeans = new ArrayList<>();
        groupBeans = new ArrayList<>();
        this.context = context;
        //this.name=name;
    }

    public void addData(List<PreSellAllBean.OrderBean> list) {
        order.addAll(list);
        for (int i = 0; i < order.size(); i++) {

            List<ChildBean> childBeanList = new ArrayList<>();
            for (int j = 0; j < order.get(i).getCl().size(); j++) {
                ChildBean childBean = new ChildBean();
                childBean.setName(order.get(i).getCl().get(j).getCl_mingcheng());
                childBean.setType(order.get(i).getCl().get(j).getCl_fenlei());
                childBeanList.add(childBean);
            }
            childBeans.add(childBeanList);
            GroupBean groupBean = new GroupBean();
            groupBean.setName(order.get(i).getOwner());
            groupBean.setId(order.get(i).getOrderId());
            groupBean.setStates(order.get(i).getState());
            groupBean.setPrice(order.get(i).getOwner());
            groupBeans.add(groupBean);
        }
    }

    public void clearData() {
        order.clear();
        childBeans.clear();
        groupBeans.clear();

    }

    //获取到子项数据，arg0组的序号，arg1子项序号
    public Object getChild(int arg0, int arg1) {
        return childBeans.get(arg0).get(arg1);
    }

    //获取到子项序号，arg0组的序号，arg1子项序号
    public long getChildId(int arg0, int arg1) {
        return arg1;
    }

    //获取到arg0组中子项个数
    public int getChildrenCount(int arg0) {
        return childBeans.get(arg0).size();
    }

    //获取到arg0组的数据
    public Object getGroup(int arg0) {
        return groupBeans.get(arg0);
    }

    //获取到一共多少组
    public int getGroupCount() {

        return groupBeans.size();
    }

    //获取到组的序号
    public long getGroupId(int arg0) {
        return arg0;
    }

    //获取到组布局的效果
    public View getGroupView(int groupposition, boolean childPosition, View arg2, ViewGroup arg3) {
        //View view =LayoutInflater().inflate(R.layout.group, arg3, false);
        View view = View.inflate(context, R.layout.group, null);
        TextView tvTitle = (TextView) view.findViewById(R.id.tv_Order_id_value);
        TextView tv_people = (TextView) view.findViewById(R.id.tv_order_price_people_name);
        TextView tv_order_price_state = (TextView) view.findViewById(R.id.tv_order_price_state);
        TextView tvprice = (TextView) view.findViewById(R.id.tv_order_price_value);
        if (groupBeans.get(groupposition).getStates().equals("SUBMITTED")||groupBeans.get(groupposition).getStates().equals("PENDING_APPROVAL")
                ||groupBeans.get(groupposition).getStates().equals("APPROVED")||groupBeans.get(groupposition).getStates().equals("FAILED_APPROVAL")
                ){
            tv_order_price_state.setText("待支付");
        }else if (groupBeans.get(groupposition).getStates().equals("QUOTED")){
            tv_order_price_state.setText("已支付");
        }else if (groupBeans.get(groupposition).getStates().equals("REMOVED")){
            tv_order_price_state.setText("已取消");
        }else if (groupBeans.get(groupposition).getStates().equals("CHANGED")){
            tv_order_price_state.setText("已变更");
        }else if (groupBeans.get(groupposition).getStates().equals("PAYMENT_COMPLETION")){
            tv_order_price_state.setText("货款已支付");
        }else if (groupBeans.get(groupposition).getStates().equals("DEPOSIT_ERROR")){
            tv_order_price_state.setText("保证金解冻失败");
        }else if (groupBeans.get(groupposition).getStates().equals("DEPOSIT_SUCCESS")){
            tv_order_price_state.setText("保证金解冻成功");
        }else if (groupBeans.get(groupposition).getStates().equals("PRESSING1")){
            tv_order_price_state.setText("已发货");
        }else if (groupBeans.get(groupposition).getStates().equals("NO_PENDING_ACTION")){
            tv_order_price_state.setText("已完成");
        }else if (groupBeans.get(groupposition).getStates().equals("PAYMENT_PROCESSING")){
            tv_order_price_state.setText("订单处理中");
        }else if (groupBeans.get(groupposition).getStates().equals("SERVICE_ERROR")){
            tv_order_price_state.setText("服务费支付失败");
        }
        else {
            tv_order_price_state.setText("等待客服处理");
        }
        tvTitle.setText(groupBeans.get(groupposition).getId());
        tv_people.setText(groupBeans.get(groupposition).getName());
        Log.d("订单的金额",order.get(groupposition).getCl().get(0).getCl_amount());
        tvprice.setText(order.get(groupposition).getCl().get(0).getCl_amount());
        tvprice.setVisibility(View.VISIBLE);
        return view;
    }

    //获取到子项布局效果
    public View getChildView(int groupPosition, int childPosition, boolean arg2, View arg3,
                             ViewGroup arg4) {
        //View view =getActivity().getLayoutInflater().inflate(R.layout.item, arg4,false);
        View view = View.inflate(context, R.layout.item, null);
        TextView tvName = (TextView) view.findViewById(R.id.tv_order_goods_type);
        tvName.setText(childBeans.get(groupPosition).get(childPosition).getType());
        TextView tvContent = (TextView) view.findViewById(R.id.tv_order_productionName);
        tvContent.setText(childBeans.get(groupPosition).get(childPosition).getName());
        return view;
    }

    public boolean hasStableIds() {
        return false;
    }


    public boolean isChildSelectable(int arg0, int arg1) {
        return true;
    }

    public static class ChildBean {
        String type;
        String name;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public static class GroupBean {
        String id;
        String name;
        String states;
        String price;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getStates() {
            return states;
        }

        public void setStates(String states) {
            this.states = states;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }
    }
}


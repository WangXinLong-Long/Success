package com.silianchuangye.sumao.success.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseExpandableListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.custom.CustomListView;
import com.silianchuangye.sumao.success.fragments.bean.LogisticsListChild;
import com.silianchuangye.sumao.success.fragments.bean.LogisticsListParent;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.OrderManagement.OrderDetails.OrderDetailsActivity;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.OrderManagement.OrderDetails.OrderDetailsHaveBeenPaid;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.PhysicalDistributionManagement.ViewLogisticsDemands.TrackingDistributionDetails;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.receiptAddress.AddAddressMVP.presenter.AddAddressPresenter;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.receiptAddress.AddAddressMVP.view.IAddAddress;

import java.util.List;

/**
 * Created by Administrator on 2016/7/8 0008.
 */
public class LogisticsDemandExpandableListViewAdapter extends BaseExpandableListAdapter implements View.OnClickListener,IAddAddress{
    private List<LogisticsListParent> parentslist;
    private List<LogisticsListChild> childrenlist;
    LogisticsListChild logisticsListChild;
    LogisticsListParent logisticsListParent;
    Context context;
    LayoutInflater inflater;
    LogisticsChildItemListviewAdapter childItemListviewAdapter;

    public LogisticsDemandExpandableListViewAdapter(Context context, List<LogisticsListParent> logisticsListParentslist) {
        this.context = context;
        this.parentslist = logisticsListParentslist;
        inflater = LayoutInflater.from(context);

    }

    @Override
    public int getGroupCount() {
        return parentslist.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return 1;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return parentslist.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return parentslist.get(groupPosition).getLogisticsListChildren().get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    GroupHolder groupHolder = null;

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

        if (convertView == null) {
            groupHolder = new GroupHolder();
            convertView = inflater.inflate(R.layout.logistics_group_item, null);
//            物流需求号
            groupHolder.logistics_demand2 = ((TextView) convertView.findViewById(R.id.logistics_demand2));
//            配送方式
            groupHolder.distribution_mode2 = ((TextView) convertView.findViewById(R.id.distribution_mode2));
//            状态
            groupHolder.logistics_state1 = ((TextView) convertView.findViewById(R.id.logistics_state1));
            groupHolder.logistics_line3 = ((RelativeLayout) convertView.findViewById(R.id.logistics_line3));
//            groupHolder.logistics_line1 = ((RelativeLayout) convertView.findViewById(R.id.logistics_line1));
            convertView.setTag(groupHolder);
        } else {
            groupHolder = ((GroupHolder) convertView.getTag());
        }
        groupHolder.logistics_demand2.setText(parentslist.get(groupPosition).getLogisticsDemand());
        groupHolder.distribution_mode2.setText(parentslist.get(groupPosition).getDistributionMode());
        groupHolder.logistics_state1.setText(parentslist.get(groupPosition).getState());
        groupHolder.logistics_line3.setOnClickListener(this);
//        groupHolder.logistics_line1.setOnClickListener(this);
        return convertView;
    }
    ChildHolder childHolder = null;
    @Override
    public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        if (convertView == null) {
            childHolder = new ChildHolder();
            convertView = inflater.inflate(R.layout.logistics_child_item, null);
            childHolder.logistics_child_item_listview = ((CustomListView) convertView.findViewById(R.id.logistics_child_item_listview));
            childHolder.delivery_number2 = ((TextView) convertView.findViewById(R.id.delivery_number2));
            childHolder.pick_up_person2 = ((TextView) convertView.findViewById(R.id.pick_up_person2));
            childHolder.contact_information2 = ((TextView) convertView.findViewById(R.id.contact_information2));
            childHolder.id_card2 = ((TextView) convertView.findViewById(R.id.id_card2));
            childHolder.remarks2 = ((TextView) convertView.findViewById(R.id.remarks2));
            childHolder.seller_distribution = ((RelativeLayout) convertView.findViewById(R.id.seller_distribution));//卖家配送
            childHolder.buyer_from_mentioning = ((RelativeLayout) convertView.findViewById(R.id.buyer_from_mentioning));//买方自提
            childHolder.unloading_area2 = ((TextView) convertView.findViewById(R.id.unloading_area2));//卸货区域
            childHolder.discharge_address2 = ((TextView) convertView.findViewById(R.id.discharge_address2));//卸货地址
            childHolder.unloading_contact2 = ((TextView) convertView.findViewById(R.id.unloading_contact2));//卸货联系人
            childHolder.discharge_contact_phone2 = ((TextView) convertView.findViewById(R.id.discharge_contact_phone2));//卸货联系电话
            childHolder.expected_time_of_receipt2 = ((TextView) convertView.findViewById(R.id.expected_time_of_receipt2));//期望收货时间
            childHolder.receiving_company2 = ((TextView) convertView.findViewById(R.id.receiving_company2));//收货公司
            childHolder.shipper_contact2 = ((TextView) convertView.findViewById(R.id.shipper_contact2));//托运联系人
            childHolder.shipper_contact_information2 = ((TextView) convertView.findViewById(R.id.shipper_contact_information2));//托运人联系方式
            childHolder.seller_remarks2 = ((TextView) convertView.findViewById(R.id.seller_remarks2));//备注


            convertView.setTag(childHolder);
        } else {
            childHolder = ((ChildHolder) convertView.getTag());
        }
        childHolder.logistics_child_item_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.e("TAG","state="+parentslist.get(groupPosition).getState());
//                if(parentslist.get(groupPosition).getState().equals("已付款")) {
//                    Intent intent = new Intent(context, OrderDetailsHaveBeenPaid.class);
//                    Log.e("TAG","产品ID=="+parentslist.get(groupPosition).getLogisticsListChildren().get(childPosition).getProductOrderNumber());
//                    intent.putExtra("ID",parentslist.get(groupPosition).getLogisticsListChildren().get(childPosition).getProductOrderNumber());
//                    context.startActivity(intent);
//                }else if(parentslist.get(groupPosition).getState().equals("待付款")){
//                    Intent intent = new Intent(context,OrderDetailsActivity.class);
//                    Log.e("TAG","产品ID=="+parentslist.get(groupPosition).getLogisticsListChildren().get(childPosition).getProductOrderNumber());
//                    intent.putExtra("ID",parentslist.get(groupPosition).getLogisticsListChildren().get(childPosition).getProductOrderNumber());
//                    context.startActivity(intent);
//                }
            }
        });

        childItemListviewAdapter = new LogisticsChildItemListviewAdapter(context, parentslist.get(groupPosition).getLogisticsListChildren());
        childHolder.logistics_child_item_listview.setAdapter(childItemListviewAdapter);//parentslist.get(groupPosition).getLogisticsListChildren().get(childPosition)
        if (groupHolder.distribution_mode2.getText().toString().equals("买家自提")) {
            childHolder.seller_distribution.setVisibility(View.GONE);//卖家配送
            childHolder.buyer_from_mentioning.setVisibility(View.VISIBLE);//买方自提
            childHolder.delivery_number2.setText(parentslist.get(groupPosition).getDeliveryNumber());
            childHolder.pick_up_person2.setText(parentslist.get(groupPosition).getPickUpPerson());
            childHolder.contact_information2.setText(parentslist.get(groupPosition).getContactInformation());
            childHolder.id_card2.setText(parentslist.get(groupPosition).getIdCardNumber());
            childHolder.remarks2.setText(parentslist.get(groupPosition).getRemarks());
        } else if (groupHolder.distribution_mode2.getText().toString().equals("卖家配送")) {
            childHolder.seller_distribution.setVisibility(View.VISIBLE);//卖家配送
            childHolder.buyer_from_mentioning.setVisibility(View.GONE);//买方自提
            String addressstr=parentslist.get(groupPosition).getUnloadingArea2();
            AddAddressPresenter presenter = new AddAddressPresenter(this);
            presenter.setDetailAddress(addressstr.substring(0, 4), addressstr.substring(0, 6), addressstr.toString());
            Log.e("TAG","str==="+str);
            childHolder.discharge_address2.setText(parentslist.get(groupPosition).getDischargeAddress2());//卸货地址
            childHolder.unloading_contact2.setText(parentslist.get(groupPosition).getUnloadingContact2());//卸货联系人
            childHolder.discharge_contact_phone2.setText(parentslist.get(groupPosition).getDischargeContactPhone2());//卸货联系电话
            childHolder.expected_time_of_receipt2.setText(parentslist.get(groupPosition).getExpectedTimeOfReceipt2());//期望收货时间
            childHolder. receiving_company2.setText(parentslist.get(groupPosition).getReceivingCompany2());//收货公司
            childHolder.shipper_contact2.setText(parentslist.get(groupPosition).getShipperContact2());//托运联系人
            childHolder.shipper_contact_information2.setText(parentslist.get(groupPosition).getShipperContactInformation2());//托运人联系方式
            childHolder.seller_remarks2.setText(parentslist.get(groupPosition).getSellerRemarks2());//备注
        }
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
//            跳转到跟踪配送详情
            case R.id.logistics_line3:
                Intent intent = new Intent();
                intent.setClass(context, TrackingDistributionDetails.class);
                context.startActivity(intent);
                break;

            default:
                break;
        }
    }
String str="";
    @Override
    public void setAddressInText(String address) {
        str=address;
        childHolder.unloading_area2.setText(str);//卸货区域
//        notifyDataSetChanged();
        Log.e("TAG","str2="+str);
    }

    @Override
    public void sendAddAddressToServerSuccess() {

    }

    @Override
    public void sendAddAddressToServerFaild() {

    }

    @Override
    public void sendAddAddressToServer() {

    }

    class GroupHolder {
        TextView logistics_demand2;
        TextView distribution_mode2;
        TextView logistics_state1;
        RelativeLayout logistics_line3;
    }

    class ChildHolder {
        CustomListView logistics_child_item_listview;
        TextView delivery_number2;
        TextView pick_up_person2;
        TextView contact_information2;
        TextView id_card2;
        TextView remarks2;
        RelativeLayout buyer_from_mentioning;//买方自提
        RelativeLayout seller_distribution;//卖家配送
        TextView unloading_area2;//卸货区域
        TextView discharge_address2;//卸货地址
        TextView unloading_contact2;//卸货联系人
        TextView discharge_contact_phone2;//卸货联系电话
        TextView expected_time_of_receipt2;//期望收货时间
        TextView receiving_company2;//收货公司
        TextView shipper_contact2;//托运联系人
        TextView shipper_contact_information2;//托运人联系方式
        TextView seller_remarks2;//卖家配送备注


    }
}

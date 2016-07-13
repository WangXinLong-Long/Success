package com.silianchuangye.sumao.success.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.custom.CustomListView;
import com.silianchuangye.sumao.success.fragments.bean.LogisticsListChild;
import com.silianchuangye.sumao.success.fragments.bean.LogisticsListParent;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.PhysicalDistributionManagement.ViewLogisticsDemands.TrackingDistributionDetails;

import java.util.List;

/**
 * Created by Administrator on 2016/7/8 0008.
 */
public class LogisticsDemandExpandableListViewAdapter extends BaseExpandableListAdapter implements View.OnClickListener{
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

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        GroupHolder groupHolder = null;
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

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ChildHolder childHolder = null;
        if (convertView == null) {
            childHolder = new ChildHolder();
            convertView = inflater.inflate(R.layout.logistics_child_item, null);
            childHolder.logistics_child_item_listview = ((CustomListView) convertView.findViewById(R.id.logistics_child_item_listview));
            childHolder.delivery_number2 = ((TextView) convertView.findViewById(R.id.delivery_number2));
            childHolder.pick_up_person2 = ((TextView) convertView.findViewById(R.id.pick_up_person2));
            childHolder.contact_information2 = ((TextView) convertView.findViewById(R.id.contact_information2));
            childHolder.id_card2 = ((TextView) convertView.findViewById(R.id.id_card2));
            childHolder.remarks2 = ((TextView) convertView.findViewById(R.id.remarks2));
            convertView.setTag(childHolder);
        } else {
            childHolder = ((ChildHolder) convertView.getTag());
        }
        childItemListviewAdapter = new LogisticsChildItemListviewAdapter(context, parentslist.get(groupPosition).getLogisticsListChildren());
        childHolder.logistics_child_item_listview.setAdapter(childItemListviewAdapter);//parentslist.get(groupPosition).getLogisticsListChildren().get(childPosition)
        childHolder.delivery_number2.setText(parentslist.get(groupPosition).getDeliveryNumber());
        childHolder.pick_up_person2.setText(parentslist.get(groupPosition).getPickUpPerson());
        childHolder.contact_information2.setText(parentslist.get(groupPosition).getContactInformation());
        childHolder.id_card2.setText(parentslist.get(groupPosition).getIdCardNumber());
        childHolder.remarks2.setText(parentslist.get(groupPosition).getRemarks());
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
//            跳转到跟踪配送详情
            case R.id.logistics_line3:
                Intent intent = new Intent();
                intent.setClass(context,TrackingDistributionDetails.class);
                context.startActivity(intent);
                break;

            default:
                break;
        }
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
    }
}

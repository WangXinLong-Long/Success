package com.silianchuangye.sumao.success.adapter;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.ShangYou.DayPlanInfo;
import com.silianchuangye.sumao.success.utils.LogUtils;

import java.util.List;

/**
 * Created by Administrator on 2016/9/6.
 */
public class DayPlanAdapter extends BaseExpandableListAdapter {
    LayoutInflater inflater;
    Context ctx;
    private List<List<DayPlanInfo>> list;
    List<String> title;
    String[][] content;
    List<String> group;
    public DayPlanAdapter(List<List<DayPlanInfo>> list, Context ctx,List<String> group) {
        this.list = list;
        this.ctx = ctx;
        this.group = group;
    }


    @Override
    public int getGroupCount() {
        LogUtils.log("得到group的数量"+group.size());
        return group.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        LogUtils.log("得到child的数量"+list.get(groupPosition).size());
        return list.get(groupPosition).size();
    }

    @Override
    public String getGroup(int groupPosition) {
        return group.get(groupPosition);
    }

    @Override
    public DayPlanInfo getChild(int groupPosition, int childPosition) {
        return list.get(groupPosition).get(childPosition);
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
        LogUtils.log("进入getGroupView");
        ItemHolder itemHolder;
        if (convertView == null){
            itemHolder = new ItemHolder();
            convertView = LayoutInflater.from(ctx).inflate(R.layout.item_lv_dayplan_parent,null);
            itemHolder.group = ((TextView) convertView.findViewById(R.id.tv_my_sumao_parent));
            convertView.setTag(itemHolder);
            LogUtils.log("进入getGroupView，并且加载完控件");
        }else {
            itemHolder = ((ItemHolder) convertView.getTag());
        }
        itemHolder.group.setText(group.get(groupPosition));
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        LogUtils.log("进入getChildView");
        ViewHolder holder;
        if (convertView == null){
            holder = new ViewHolder();
            convertView = LayoutInflater.from(ctx).inflate(R.layout.item_lv_dayplan,null);
            holder.level = ((TextView) convertView.findViewById(R.id.tv_lv_dayplan_date));
            holder.name = ((TextView) convertView.findViewById(R.id.tv_lv_dayplan_date_right));
            convertView.setTag(holder);
            LogUtils.log("进入getChildView，并且加载完控件");
        }else {
            holder = ((ViewHolder) convertView.getTag());
        }
        holder.level.setText(getChild(groupPosition,childPosition).getLevel());
        holder.name.setText(getChild(groupPosition,childPosition).getName());
        LogUtils.log("进入getChildView，为控件设置值");
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    private class ViewHolder {
        TextView level,name;
    }
    class ItemHolder{
        TextView group;
    }
}

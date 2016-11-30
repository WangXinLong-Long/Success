package com.silianchuangye.sumao.success.fragments.myPlasticTrade.setting.FunctionIntroduction.FunctionIntroductionMVP.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.silianchuangye.sumao.success.R;

import java.util.List;

/**
 * Created by Administrator on 2016/9/22 0022.
 */
public class FunctionIntroductionAdapter extends BaseExpandableListAdapter {
    List<List<String>> child;
    List<String> group;
    Context context;
    LayoutInflater inflater;
    public FunctionIntroductionAdapter(List<List<String>> child, List<String> group, Context context) {
        this.child = child;
        this.group = group;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getGroupCount() {
        return group.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return child.get(groupPosition).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return group.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return child.get(groupPosition).get(childPosition);
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
            convertView = inflater.inflate(R.layout.my_sumao_expandable_layout_title, null);
            groupHolder = new GroupHolder();
            groupHolder.txt = ((TextView) convertView.findViewById(R.id.tv_my_sumao_parent));
            groupHolder.img = ((ImageView) convertView.findViewById(R.id.iv_my_sumao_parent));
            convertView.setTag(groupHolder);
        } else {
            groupHolder = ((GroupHolder) convertView.getTag());
        }

        if (!isExpanded) {
            groupHolder.img.setBackgroundResource(R.mipmap.my_sumao_iv_detail_hide);
            groupHolder.txt.setTextColor(context.getResources().getColor(R.color.textColor_expandable_listview_hide));
        } else {
            groupHolder.img.setBackgroundResource(R.mipmap.my_sumao_iv_detail_show);
            groupHolder.txt.setTextColor(context.getResources().getColor(R.color.textColor_expandable_listview_show));
        }
        groupHolder.txt.setText(getGroup(groupPosition).toString());
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ItemHolder itemHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.my_sumao_expandable_layout_content, null);
            itemHolder = new ItemHolder();
            itemHolder.txt = (TextView) convertView.findViewById(R.id.tv_my_sumao_child);
            convertView.setTag(itemHolder);
        } else {
            itemHolder = (ItemHolder) convertView.getTag();
        }
        itemHolder.txt.setText(getChild(groupPosition, childPosition).toString());
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
    class GroupHolder {
        public TextView txt;
        public ImageView img;
    }

    class ItemHolder {
        public TextView txt;

    }
}

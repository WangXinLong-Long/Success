package com.silianchuangye.sumao.success.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.silianchuangye.sumao.success.R;

/**
 * Created by Administrator on 2016/4/28 0028.
 */
public class ExpandableListViewAdapter extends BaseExpandableListAdapter {
    LayoutInflater inflater;
    Context context;
    String[] title;
    String[][] content;

    public ExpandableListViewAdapter(Context context, String role) {
        this.context = context;
        inflater = LayoutInflater.from(context);


        if (role.equals("buyer")) {
            title = context.getResources().getStringArray(R.array.title_buyer);
            content = new String[][]
                    {
                            context.getResources().getStringArray(R.array.child1_buyer),
                            context.getResources().getStringArray(R.array.child2_buyer),
                            context.getResources().getStringArray(R.array.child3_buyer),
                            context.getResources().getStringArray(R.array.child4_buyer),
                            context.getResources().getStringArray(R.array.child5_buyer),
                    };

        /*
        *
        *               {"现货订单", "预售订单", "点价意向单"},
                        {"采购需求", "供应资源", "撮合单信息"},
                        {"用户信息", "密码修改", "资讯订阅"},
                        {"企业信息", "企业用户管理", "收货地址", "开票信息", "企业等级和积分"},
                        {"资金账户信息", "账户金额明细", "入金操作", "出金操作"},
                        {"申请为卖方", "申请为上游资源方客户"},
          */
        }else if (role.equals("seller")){
            title = context.getResources().getStringArray(R.array.title_seller);
            content = new String[][]
                    {
                            context.getResources().getStringArray(R.array.child1_seller),
                            context.getResources().getStringArray(R.array.child2_seller),
                            context.getResources().getStringArray(R.array.child3_seller),
                    };
        }
    }

    @Override
    public int getGroupCount() {
        return title.length;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return content[groupPosition].length;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return title[groupPosition];
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return content[groupPosition][childPosition];
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

};




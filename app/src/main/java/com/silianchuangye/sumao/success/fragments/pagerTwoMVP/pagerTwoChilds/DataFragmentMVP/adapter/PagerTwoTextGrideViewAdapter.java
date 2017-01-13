package com.silianchuangye.sumao.success.fragments.pagerTwoMVP.pagerTwoChilds.DataFragmentMVP.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.fragments.pagerTwoMVP.pagerTwoChilds.DataFragmentMVP.bean.PagerTwoDataGridViewBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jobs Created on 2016/11/30.
 */
public class PagerTwoTextGrideViewAdapter extends BaseAdapter {
    private List<PagerTwoDataGridViewBean> lists;
    private Context context;
    private LayoutInflater inflater;

    public PagerTwoTextGrideViewAdapter( Context context) {
        this.lists = new ArrayList<>();
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    public void addData(List<PagerTwoDataGridViewBean> list){
        lists.addAll(list);
    }
    @Override
    public int getCount() {
        if (lists != null) {
            return lists.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return lists.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.home_text_gride_view_item, null);
            holder.textView = (TextView) convertView.findViewById(R.id.text);
            convertView.setTag(holder);
        } else {
            holder = ((ViewHolder) convertView.getTag());
        }
        holder.textView.setText(lists.get(position).getName());
        return convertView;
    }

    class ViewHolder {
        TextView textView;
    }
}

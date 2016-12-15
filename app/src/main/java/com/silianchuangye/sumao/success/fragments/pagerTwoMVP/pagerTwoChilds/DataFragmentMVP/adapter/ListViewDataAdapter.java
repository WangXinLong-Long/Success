package com.silianchuangye.sumao.success.fragments.pagerTwoMVP.pagerTwoChilds.DataFragmentMVP.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.fragments.pagerTwoMVP.pagerTwoChilds.DataFragmentMVP.bean.PagerTwoDataListViewBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jobs Created on 2016/12/14.
 */
public class ListViewDataAdapter extends BaseAdapter {

    Context context;
    List<PagerTwoDataListViewBean> pagerTwoDataListViewBeens;
    public ListViewDataAdapter(Context context) {
        this.context = context;
        pagerTwoDataListViewBeens = new ArrayList<>();
    }

    public void setPagerTwoDataListViewBeens(List<PagerTwoDataListViewBean> pagerTwoDataListViewBeens) {
        this.pagerTwoDataListViewBeens = pagerTwoDataListViewBeens;
    }

    @Override
    public int getCount() {
        return pagerTwoDataListViewBeens.size();
    }

    @Override
    public Object getItem(int position) {
        return pagerTwoDataListViewBeens.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null){
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.data_list_view_adapter,null);
            viewHolder.title =(TextView) convertView.findViewById(R.id.title_text_view);
            viewHolder.time =(TextView) convertView.findViewById(R.id.time_text_view);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = ((ViewHolder) convertView.getTag());
        }
        viewHolder.title.setText(pagerTwoDataListViewBeens.get(position).getTitle());
        viewHolder.time.setText(pagerTwoDataListViewBeens.get(position).getTime());
        return convertView;
    }
    class ViewHolder{
        TextView title;
        TextView time;
    }
}

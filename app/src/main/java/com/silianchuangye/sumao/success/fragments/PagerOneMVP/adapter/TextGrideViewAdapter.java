package com.silianchuangye.sumao.success.fragments.PagerOneMVP.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.silianchuangye.sumao.success.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jobs Created on 2016/11/30.
 */
public class TextGrideViewAdapter extends BaseAdapter {
    private List<String> list;
    private Context context;
    private LayoutInflater inflater;

    public TextGrideViewAdapter( Context context) {
        this.list = new ArrayList<>();
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    public void addData(List<String> list){
        this.list.addAll(list);
    }
    @Override
    public int getCount() {
        if (list != null) {
            return list.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
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
        if (list.get(position) == null){
            holder.textView.setText("什么鬼");
        }else {
            holder.textView.setText(list.get(position));
        }
        return convertView;
    }

    class ViewHolder {
        TextView textView;
    }
}

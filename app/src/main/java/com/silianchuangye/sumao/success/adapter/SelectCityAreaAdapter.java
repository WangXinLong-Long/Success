package com.silianchuangye.sumao.success.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.receiptAddress.SelectProvinceAreaMVP.bean.Area;
import com.silianchuangye.sumao.success.model.CityModel;
import com.silianchuangye.sumao.success.model.ProvinceModel;

import java.util.List;

/**
 * Created by Administrator on 2016/5/13 0013.
 */
public class SelectCityAreaAdapter extends BaseAdapter {

    Context context;

    List<Area> list;

    LayoutInflater inflater;
    public SelectCityAreaAdapter(Context context, List<Area> list) {
        this.context = context;
        this.list = list;
        inflater = LayoutInflater.from(context);
      }

    @Override
    public int getCount() {
        if (list!=null&&list.size()!=0)
        {
            return list.size();
        }
        return 0;
    }

    @Override
    public Area getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView==null)
        {
            viewHolder = new ViewHolder();
            convertView = inflater.inflate(R.layout.activity_area_item,null);
            viewHolder.textView = ((TextView) convertView.findViewById(R.id.area_text));
            convertView.setTag(viewHolder);
        }else {
            viewHolder = ((ViewHolder) convertView.getTag());
        }
        viewHolder.textView.setText(list.get(position).getName());
        return convertView;
    }
    class ViewHolder
    {
        TextView textView;
    }
}

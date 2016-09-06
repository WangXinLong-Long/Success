package com.silianchuangye.sumao.success.fragments.myPlasticTrade.register.RegisterFirmActivityMVP.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.register.RegisterFirmActivityMVP.model.RegisterFirmList;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.register.RegisterFirmActivityMVP.view.RegisterFirmActivity;

import java.util.List;
import java.util.zip.Inflater;

/**
 * Created by Administrator on 2016/9/2 0002.
 */
public class RegisterFirmListAdapter extends BaseAdapter{
    Context context;
    List<RegisterFirmList> registerFirmLists;
    LayoutInflater inflater;
    public RegisterFirmListAdapter(RegisterFirmActivity registerFirmActivity, List<RegisterFirmList> registerFirmLists) {
        context = registerFirmActivity;
        this.registerFirmLists = registerFirmLists;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return registerFirmLists.size();
    }

    @Override
    public Object getItem(int position) {
        return registerFirmLists.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView==null){
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.register_firm_list_item,null);
            holder.name = ((TextView) convertView.findViewById(R.id.business_license_number_name));
            holder.value = ((TextView) convertView.findViewById(R.id.business_license_number_image));
            convertView.setTag(holder);
        }else{
            holder = ((ViewHolder) convertView.getTag());
        }
       holder.name.setText(registerFirmLists.get(position).getName());
       holder.value.setText(registerFirmLists.get(position).getValue());

        return convertView;
    }
    class ViewHolder{
        TextView name;
        TextView value;
    }
}

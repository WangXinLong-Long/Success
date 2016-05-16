package com.silianchuangye.sumao.success.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.silianchuangye.sumao.success.R;

import java.util.List;

/**
 * Created by Administrator on 2016/5/16.
 */
public class searchitemAdapter extends BaseAdapter{
    private Context ctx;
    private List<String> list;

    public searchitemAdapter(Context ctx, List<String>list){
        this.ctx=ctx;
        this.list=list;
    }
    @Override
    public int getCount() {
        return list.size();
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
        if(convertView==null){
            convertView=View.inflate(ctx,R.layout.searchmoney_item,null);
            holder=new ViewHolder();
            holder.tv_searchitem_date= (TextView) convertView.findViewById(R.id.tv_searchitem_riqi);
            holder.tv_searchitem_dingdanhao= (TextView) convertView.findViewById(R.id.tv_searchitrm_dingdanhao);
            holder.tv_searchitem_liushiuhao= (TextView) convertView.findViewById(R.id.tv_searchmoney_item_num);
            holder.tv_searchitem_money= (TextView) convertView.findViewById(R.id.tv_searchitem_money);
            holder.tv_searchitem_state= (TextView) convertView.findViewById(R.id.tv_searchitem_zhuangtai);
            convertView.setTag(holder);
        }else{
            holder= (ViewHolder) convertView.getTag();
        }
        holder.tv_searchitem_liushiuhao.setText(list.get(0));
        holder.tv_searchitem_dingdanhao.setText(list.get(1));
        holder.tv_searchitem_money.setText(list.get(2)+"元");
        holder.tv_searchitem_date.setText(list.get(3));
        holder.tv_searchitem_state.setText(list.get(4));
        return convertView;
    }
    private class ViewHolder{
        TextView tv_searchitem_liushiuhao,tv_searchitem_dingdanhao,
        tv_searchitem_money,tv_searchitem_date,tv_searchitem_state;
    }
}

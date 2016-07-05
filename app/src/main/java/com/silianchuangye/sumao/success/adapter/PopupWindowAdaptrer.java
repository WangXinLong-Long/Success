package com.silianchuangye.sumao.success.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.fragments.homepage.auction.OpenAuction;
import com.silianchuangye.sumao.success.fragments.homepage.auction.OpenAuctionActivity;
import com.silianchuangye.sumao.success.fragments.homepage.auction.VesselOneActivity;

import java.util.List;
import java.util.Map;

/**
 * Created by junjun on 2016/7/4.
 */
public class PopupWindowAdaptrer extends BaseAdapter{
    private List<OpenAuction> data;
    private Context context;



    public PopupWindowAdaptrer(List<OpenAuction> data, Context context){
        this.context=context;
        this.data=data;

    }




    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView==null) {
            convertView = View.inflate(context,R.layout.item_popupwindow_auction_listview, null);
            viewHolder=new ViewHolder();
            viewHolder.iv_icon= (ImageView) convertView.findViewById(R.id.iv_Bank_icon);
            viewHolder.iv_select_null= (ImageView) convertView.findViewById(R.id.iv_select_null);
            viewHolder.tv_Name= (TextView) convertView.findViewById(R.id.tv_Bank_name);
            viewHolder.tv_money= (TextView) convertView.findViewById(R.id.tv_money);
            convertView.setTag(viewHolder);
        }else {
            viewHolder= (ViewHolder) convertView.getTag();

        }

        viewHolder.iv_icon.setImageResource(data.get(position).iv_icon);
        viewHolder.tv_Name.setText(data.get(position).tv_Name);
        viewHolder.tv_money.setText(data.get(position).tv_money);
        if (data.get(position).Flag){
            viewHolder.iv_select_null.setImageResource(R.mipmap.cart_select);
        }else{
            viewHolder.iv_select_null.setImageResource(R.mipmap.cart_select_null);
        }
        return convertView;
    }
    class ViewHolder{
        ImageView iv_icon,iv_select_null;
        TextView tv_Name,tv_money;
    }
}

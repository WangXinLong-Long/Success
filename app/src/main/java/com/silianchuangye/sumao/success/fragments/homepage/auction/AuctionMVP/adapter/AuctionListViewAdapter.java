package com.silianchuangye.sumao.success.fragments.homepage.auction.AuctionMVP.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.fragments.homepage.auction.AuctionMVP.bean.ListBean;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jobs Created on 2016/12/26.
 */
public class AuctionListViewAdapter extends BaseAdapter {
    private Context context;
    private List<ListBean.ProdBean> list;

    public AuctionListViewAdapter(Context context) {
        this.context = context;
        list = new ArrayList<>();
    }

    public void addData(List<ListBean.ProdBean> list) {
        this.list.addAll(list);
    }

    public void clearData() {
        this.list.clear();
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
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.auctionitem, null);
            viewHolder = new ViewHolder();
//      参与竞拍
            viewHolder.bu = (TextView) convertView.findViewById(R.id.bt_auctionitem);
//      名称
            viewHolder.tv_name = (TextView) convertView.findViewById(R.id.tv_auction_name);
//      汉字
            viewHolder.tv_kaishi = (TextView) convertView.findViewById(R.id.tv_auction_start);
//      时间
            viewHolder.startTime = (TextView) convertView.findViewById(R.id.tv_auction_time);
//      已经结束
            viewHolder.icon = (ImageView) convertView.findViewById(R.id.iv_auction_end);
//      价格
            viewHolder.tv_price = (TextView) convertView.findViewById(R.id.tv_auction_price);
//      竞拍总量
            viewHolder.tv_count = (TextView) convertView.findViewById(R.id.tv_auction_number);
//        仓库
            viewHolder.tv_cangku = (TextView) convertView.findViewById(R.id.tv_auction_acdress);
//        公司
            viewHolder.tv_commpany = (TextView) convertView.findViewById(R.id.tv_auction_commAdress);
//        竞拍标示
            viewHolder.iv_way = (ImageView) convertView.findViewById(R.id.iv_auction_icon);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = ((ViewHolder) convertView.getTag());
        }
        if (list.get(position).getCl_mingcheng() == null) {
            viewHolder.tv_name.setText("");
        } else {
            viewHolder.tv_name.setText(list.get(position).getCl_mingcheng().toString());
        }
        String state = list.get(position).getStatus();
        String startTimeMill = list.get(position).getStartDate();
        if (startTimeMill == null) {
            startTimeMill = "0";
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 hh:mm:mm");
        String data = simpleDateFormat.format(Double.parseDouble(startTimeMill));
        if (state == null || state.equals("0")) {
            //竞拍未开始
            viewHolder.tv_kaishi.setText("开始时间:");
            viewHolder.startTime.setText(data);
        } else if (state.equals("1")) {
            //正在竞拍
            viewHolder.tv_kaishi.setText("正在竞拍:");
            viewHolder.startTime.setText(data);
        } else if (state.equals("2")) {
            //竞拍结束
            viewHolder.startTime.setText("");
        }

        //icon.setImageResource(Integer.parseInt(list.get(position).get("icon").toString()));
        if (state.equals("2")) {
            viewHolder.icon.setVisibility(View.VISIBLE);
            viewHolder.bu.setVisibility(View.INVISIBLE);
        } else {
            viewHolder.icon.setVisibility(View.INVISIBLE);
            viewHolder.bu.setVisibility(View.VISIBLE);
        }
        if (list.get(position).getCl_qipai() == null) {
            viewHolder.tv_price.setText("");
        } else {
            viewHolder.tv_price.setText(list.get(position).getCl_qipai().toString());
        }
        viewHolder.tv_count.setText(list.get(position).getCl_zongliang().toString());

        viewHolder.tv_cangku.setText(list.get(position).getCl_cangku().toString());

        viewHolder.tv_commpany.setText(list.get(position).getCl_gongsi().toString());


        if (list.get(position).getType().equals("englishAuctionProduct")) {
            if (list.get(position).getStatus().equals("2")) {
                viewHolder.iv_way.setImageResource(R.mipmap.open_auction_is_over);
            } else {
                viewHolder.iv_way.setImageResource(R.mipmap.publicauction);
            }

        } else if (list.get(position).getType().equals("sealedAuctionProduct")) {
            if (list.get(position).getStatus().equals("2")) {
                viewHolder.iv_way.setImageResource(R.mipmap.sealed_bid_has_ended);
            } else {
                viewHolder.iv_way.setImageResource(R.mipmap.mifengauction);
            }

        }
        return convertView;
    }

    class ViewHolder {
        //      参与竞拍
        TextView bu;
        //      名称
        TextView tv_name;
        //      汉字
        TextView tv_kaishi;
        //      时间
        TextView startTime;
        //      已经结束
        ImageView icon;
        //      价格
        TextView tv_price;
        //      竞拍总量
        TextView tv_count;
        //        仓库
        TextView tv_cangku;
        //        公司
        TextView tv_commpany;
        //        竞拍标示
        ImageView iv_way;
    }
}

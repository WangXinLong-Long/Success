package com.silianchuangye.sumao.success.fragments.homepage.groupbuying.GroupBuyingMVP.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.fragments.homepage.groupbuying.GroupBuyingMVP.bean.GroupBuyingListBean;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jobs Created on 2017/1/11.
 */
public class GroupBuyingListViewAdapter extends BaseAdapter {

    private Context context;
    private List<GroupBuyingListBean.ProdBean> list;

    public GroupBuyingListViewAdapter(Context context) {
        this.context = context;
        this.list = new ArrayList<>();
    }

    public void addData(List<GroupBuyingListBean.ProdBean> data) {
        list.addAll(data);
    }

    public void clearData() {
        list.clear();
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
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.layout_for_group_buying, null);
            viewHolder.iv_icon = (ImageView) convertView.findViewById(R.id.iv_auction_icon);
            viewHolder.tv_name = (TextView) convertView.findViewById(R.id.tv_auction_name);
            viewHolder.tv_StartTime = (TextView) convertView.findViewById(R.id.tv_auction_time);
            viewHolder.tv_price = (TextView) convertView.findViewById(R.id.tv_auction_price);
            viewHolder.tv_count = (TextView) convertView.findViewById(R.id.tv_auction_number);
            viewHolder.bt_join = (Button) convertView.findViewById(R.id.bt_auctionitem);
            viewHolder.tv_chengtuan = (TextView) convertView.findViewById(R.id.tv_group_number);
            viewHolder.tv_way = (TextView) convertView.findViewById(R.id.tv_group_way_value);
            viewHolder.tv_cangku = (TextView) convertView.findViewById(R.id.tv_auction_acdress);
            viewHolder.tv_comm = (TextView) convertView.findViewById(R.id.tv_auction_commAdress);
            viewHolder.tv_final_num = (TextView) convertView.findViewById(R.id.tv_final_num);
            //            开始时间
            viewHolder.start_data = ((TextView) convertView.findViewById(R.id.start_data));
//            结束时间
            viewHolder.end_data = ((TextView) convertView.findViewById(R.id.end_data));
//            开始和结束的RelativeLayout
            viewHolder.end_data_rl = ((RelativeLayout) convertView.findViewById(R.id.end_data_rl));
            viewHolder.start_data_rl = ((RelativeLayout) convertView.findViewById(R.id.start_data_rl));
//            viewHolder.relative_endtime = (RelativeLayout) convertView.findViewById(R.id.relative_endtime);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = ((ViewHolder) convertView.getTag());
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:mm");
        String shangpinId = list.get(position).getId();
        viewHolder.tv_name.setText(list.get(position).getCl_mingcheng());

        viewHolder.tv_price.setText(list.get(position).getCl_jine());
        viewHolder.tv_count.setText(list.get(position).getCl_zongliang());
        viewHolder.tv_chengtuan.setText(list.get(position).getCl_groupQuantity());
        viewHolder.tv_way.setText("交货方式");
        viewHolder.tv_cangku.setText(list.get(position).getCl_cangku());
        viewHolder.tv_comm.setText(list.get(position).getCl_gongsi());
        String endData = simpleDateFormat.format(Double.parseDouble(list.get(position).getGroupEndDate().toString()));
        String startData = simpleDateFormat.format(Double.parseDouble(list.get(position).getGroupStartDate().toString()));
        viewHolder.start_data.setText(startData);
        viewHolder.end_data.setText(endData);
        viewHolder.tv_final_num.setText("交易总量:" + list.get(position).getSumAddQty() + "吨");
        if (list.get(position).getRuning().equals("true")) {//正在团购
            viewHolder.iv_icon.setImageResource(R.mipmap.underwey);
            viewHolder.tv_StartTime.setVisibility(View.VISIBLE);
            viewHolder.tv_final_num.setVisibility(View.GONE);
            viewHolder.bt_join.setBackgroundColor(context.getResources().getColor(R.color.btn_blue_normal));
            viewHolder.end_data_rl.setVisibility(View.VISIBLE);
            viewHolder.start_data_rl.setVisibility(View.VISIBLE);
            viewHolder.tv_StartTime.setText("团购进行中");
        } else if (list.get(position).getRuning().equals("false")) {//团购没开始
            viewHolder.iv_icon.setImageResource(R.mipmap.tuangouselect);
            viewHolder.tv_StartTime.setVisibility(View.VISIBLE);
            viewHolder.tv_final_num.setVisibility(View.GONE);
            viewHolder.bt_join.setBackgroundColor(context.getResources().getColor(R.color.btn_blue_normal));
            viewHolder.end_data_rl.setVisibility(View.VISIBLE);
            viewHolder.start_data_rl.setVisibility(View.VISIBLE);
            viewHolder.tv_StartTime.setText("团购未开始");
        } else if (list.get(position).getRuning().equals("end")) {//团购已结束

            if (list.get(position).getResult().equals("gone")) {
                viewHolder.iv_icon.setImageResource(R.mipmap.tuangou);
                viewHolder.bt_join.setBackgroundColor(context.getResources().getColor(R.color.gray));
                viewHolder.tv_StartTime.setVisibility(View.GONE);
                viewHolder.tv_final_num.setVisibility(View.GONE);
                viewHolder.bt_join.setText("未成团");
                viewHolder.end_data_rl.setVisibility(View.VISIBLE);
                viewHolder.start_data_rl.setVisibility(View.VISIBLE);
                //viewHolder.tv_StartTime
            }else {
                viewHolder.iv_icon.setImageResource(R.mipmap.tuangou);
                viewHolder.bt_join.setBackgroundColor(context.getResources().getColor(R.color.gray));
                viewHolder.tv_StartTime.setVisibility(View.GONE);
                viewHolder.tv_final_num.setVisibility(View.VISIBLE);
                viewHolder.bt_join.setText("团购结束");
                viewHolder.end_data_rl.setVisibility(View.VISIBLE);
                viewHolder.start_data_rl.setVisibility(View.VISIBLE);
            }


        }

        return convertView;
    }

    class ViewHolder {
        ImageView iv_icon;
        TextView tv_name;
        TextView tv_StartTime;
        TextView tv_price;
        TextView tv_count;
        Button bt_join;
        TextView tv_chengtuan;
        TextView tv_way;
        TextView tv_cangku;
        TextView tv_comm;
        TextView tv_auction_start;
        TextView tv_final_num;
        //        开始时间
        TextView start_data;
        //        结束时间
        TextView end_data;
        //        开始和结束的RelativeLayout
        RelativeLayout start_data_rl;
        RelativeLayout end_data_rl;
    }
}


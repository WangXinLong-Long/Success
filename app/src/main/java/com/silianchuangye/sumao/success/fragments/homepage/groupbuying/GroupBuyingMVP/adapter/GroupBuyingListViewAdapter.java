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
            viewHolder.tv_endTime = (TextView) convertView.findViewById(R.id.tv_time_end);
            viewHolder.tv_auction_start = (TextView) convertView.findViewById(R.id.tv_auction_start);
            viewHolder.tv_final_num = (TextView) convertView.findViewById(R.id.tv_final_num);
            viewHolder.relative_endtime = (RelativeLayout) convertView.findViewById(R.id.relative_endtime);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = ((ViewHolder) convertView.getTag());
        }
//        /* map.put("id", obj_array.getString("id"));
//                    map.put("state", obj_array.getString("runing"));
//                    map.put("name", obj_array.getString("cl_mingcheng"));
//                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat
//                            ("yyyy-MM-dd hh:mm:mm");
//                    String Start_data = simpleDateFormat.format(Double.parseDouble(obj_array.getString("groupStartDate")));
//                    map.put("startTime", Start_data);
//                    map.put("price", obj_array.getString("cl_jine"));
//                    map.put("number", obj_array.getString("cl_zongliang"));
//                    map.put("chengtuan", obj_array.getString("cl_groupQuantity"));
//                    map.put("way", "自提");
//                    map.put("cangku", obj_array.getString("cl_cangku"));
//                    if (obj_array.toString().contains("cl_gongsi")) {
//                        map.put("comm", obj_array.getString("cl_gongsi"));
//                    } else {
//                        map.put("comm", "");
//                    }
//                    String end_data = simpleDateFormat.format(Double.parseDouble(obj_array.getString("groupEndDate")));
//                    map.put("endTime", end_data);
//                    String cl_groupQuantity = obj_array.getString("cl_groupQuantity");
//                    map.put("cl_groupQuantity", cl_groupQuantity);
//                    list.add(map);*/
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:mm");
        String shangpinId = list.get(position).getId();
        viewHolder.tv_name.setText(list.get(position).getCl_mingcheng());
        viewHolder.tv_StartTime.setText(simpleDateFormat.format(Double.parseDouble(list.get(position).getGroupStartDate())));
        viewHolder.tv_price.setText(list.get(position).getCl_jine());
        viewHolder.tv_count.setText(list.get(position).getCl_zongliang());
        viewHolder.tv_chengtuan.setText(list.get(position).getCl_groupQuantity());
        viewHolder.tv_way.setText("交货方式");
        viewHolder.tv_cangku.setText(list.get(position).getCl_cangku());
        viewHolder.tv_comm.setText(list.get(position).getCl_gongsi());
        viewHolder.tv_endTime.setText(simpleDateFormat.format(Double.parseDouble(list.get(position).getGroupEndDate().toString())));
        viewHolder.tv_final_num.setText("最终成团量 : " + list.get(position).getCl_groupQuantity() + "T");
        if (list.get(position).getRuning().equals("true")) {//正在团购
            viewHolder.iv_icon.setImageResource(R.mipmap.underwey);
            viewHolder.tv_StartTime.setVisibility(View.VISIBLE);
            viewHolder.tv_final_num.setVisibility(View.GONE);
            viewHolder.relative_endtime.setVisibility(View.VISIBLE);
            viewHolder.tv_auction_start.setVisibility(View.VISIBLE);
            viewHolder.bt_join.setBackgroundColor(context.getResources().getColor(R.color.btn_blue_normal));
        } else if (list.get(position).getRuning().equals("false")) {//团购没开始
            viewHolder.iv_icon.setImageResource(R.mipmap.tuangouselect);
            viewHolder.tv_StartTime.setVisibility(View.VISIBLE);
            viewHolder.tv_final_num.setVisibility(View.GONE);
            viewHolder.relative_endtime.setVisibility(View.VISIBLE);
            viewHolder.tv_auction_start.setVisibility(View.VISIBLE);
            viewHolder.bt_join.setBackgroundColor(context.getResources().getColor(R.color.btn_blue_normal));
        } else if (list.get(position).getRuning().equals("end")) {//团购已结束
            viewHolder.relative_endtime.setVisibility(View.GONE);
            viewHolder.iv_icon.setImageResource(R.mipmap.tuangou);
            viewHolder.bt_join.setBackgroundColor(context.getResources().getColor(R.color.gray));
            viewHolder.tv_auction_start.setVisibility(View.GONE);
            viewHolder.tv_StartTime.setVisibility(View.GONE);
            viewHolder.tv_final_num.setVisibility(View.VISIBLE);
            viewHolder.bt_join.setText("团购结束");
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
        TextView tv_endTime;
        TextView tv_auction_start;
        TextView tv_final_num;
        RelativeLayout relative_endtime;
    }
}


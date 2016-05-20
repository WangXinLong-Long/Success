package com.silianchuangye.sumao.success.fragments.auction;


import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.silianchuangye.sumao.success.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyOfferFragment extends Fragment {
    private GridView gridView;
    private List<String> list;

    public MyOfferFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_my_offer, container, false);
        gridView= (GridView) view.findViewById(R.id.gv_market_vessel_two);
        list=new ArrayList<String>();
        list.add("报价编号");
        list.add("竞价单价(元/吨)");
        list.add("竞价数量(元/吨)");
        list.add("可接受最少成交数量(吨)");
        list.add("提交时间");
        list.add("报价编号");
        list.add("竞价单价(元/吨)");
        list.add("竞价数量(元/吨)");
        list.add("可接受最少成交数量(吨)");
        list.add("提交时间");
        list.add("报价编号");
        list.add("竞价单价(元/吨)");
        list.add("竞价数量(元/吨)");
        list.add("可接受最少成交数量(吨)");
        list.add("提交时间");  list.add("报价编号");
        list.add("竞价单价(元/吨)");
        list.add("竞价数量(元/吨)");
        list.add("可接受最少成交数量(吨)");
        list.add("提交时间");
        list.add("报价编号");
        list.add("竞价单价(元/吨)");
        list.add("竞价数量(元/吨)");
        list.add("可接受最少成交数量(吨)");
        list.add("提交时间");
        list.add("报价编号");
        list.add("竞价单价(元/吨)");
        list.add("竞价数量(元/吨)");
        list.add("可接受最少成交数量(吨)");
        list.add("提交时间");
        list.add("报价编号");
        list.add("竞价单价(元/吨)");
        list.add("竞价数量(元/吨)");
        list.add("可接受最少成交数量(吨)");
        list.add("提交时间");
        list.add("报价编号");
        list.add("竞价单价(元/吨)");
        list.add("竞价数量(元/吨)");
        list.add("可接受最少成交数量(吨)");
        list.add("提交时间");


        GridViewAdapter adapter=new GridViewAdapter (getContext(),R.layout.item_gridview_vessel_one,R.id.tv_item_vessel_one,list);
        gridView.setAdapter(adapter);


        return view;
    }
    class GridViewAdapter extends ArrayAdapter {


        public GridViewAdapter(Context context, int resource, int textViewResourceId, List objects) {
            super(context, resource, textViewResourceId, objects);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;
            if (convertView==null){
                viewHolder=new ViewHolder();
                convertView=getActivity().getLayoutInflater().inflate(R.layout.item_gridview_vessel_one,null);
                viewHolder.tv= (TextView) convertView.findViewById(R.id.tv_item_vessel_one);
                viewHolder.layout= (RelativeLayout) convertView.findViewById(R.id.layout_item_vessel_one);
//                AbsListView.LayoutParams params;
//                if(gridView.getChildCount()/5==0){
//                    params=new AbsListView.LayoutParams(95,85);
//                }else{
//                    params=new AbsListView.LayoutParams(95,60);
//                }
//                viewHolder.layout.setLayoutParams(params);
                convertView.setTag(viewHolder);
            }else{
                viewHolder= (ViewHolder) convertView.getTag();
            }
            if (position%5==0 &&position!=0){
                viewHolder.tv.setText("1111");
            }else if(position%5==1 &&position!=1){
                viewHolder.tv.setText("5980");
            }else if(position%5==2&&position!=2){
                viewHolder.tv.setText("40");
            }else if (position%5==3&&position!=3){
                viewHolder.tv.setText("40");
            }else if (position%5==4 &&position!=4){
                viewHolder.tv.setText("12:00:00");
            }else {
                viewHolder.tv.setText(list.get(position));
            }
            if (gridView.getChildCount()/5==0){
                viewHolder.layout.setBackgroundColor(getResources().getColor(R.color.bottom_background));
            }else if((gridView.getChildCount()/5)%2==0 &&gridView.getChildCount()/5!=0){
                viewHolder.layout.setBackgroundColor(getResources().getColor(R.color.colorGreen));
            }else{
                viewHolder.layout.setBackgroundColor(Color.WHITE);
            }
            return convertView;

        }
        class ViewHolder{
            RelativeLayout layout;
            TextView tv;
        }

    }

}



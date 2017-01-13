package com.silianchuangye.sumao.success.fragments.homepage.auction;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.fragments.homepage.auction.openAuction.bean.RealTimeMardet;
import com.silianchuangye.sumao.success.utils.LogUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class RealTimeMarketFragment extends Fragment {
    //private GridView gridView;
    private List<Map<String, Object>> list;
    private String id;
    private ListView lvDemo;
    private LinearLayout shang;
    private String u_clientID;
    private String uCompany;
    private String type_way;
    private RelativeLayout prompt;
    // private List<String> bianhai,danjia,shuliang,m_dun,time;


    public RealTimeMarketFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_real_time_market, container, false);
        lvDemo = (ListView) view.findViewById(R.id.lvDemo);
        prompt = ((RelativeLayout) view.findViewById(R.id.prompt));
        shang = (LinearLayout) view.findViewById(R.id.shang);
        String result = getArguments().getString("realTimeQuotes");
        uCompany = getArguments().getString("uCompany");
        type_way = getArguments().getString("type_way");
        list = new ArrayList<Map<String, Object>>();
        u_clientID = getActivity().getSharedPreferences("sumao", Activity.MODE_PRIVATE).getString("U_clientID", "");
        if (type_way.equals("公开竞拍")){
            getData(result);
            prompt.setVisibility(View.GONE);
            shang.setVisibility(View.VISIBLE);
        }else {
            prompt.setVisibility(View.VISIBLE);
            shang.setVisibility(View.GONE);
        }

        return view;
    }

    public void getData(String result) {
        Gson gson = new Gson();
        RealTimeMardet realTimeMardet = gson.fromJson(result, RealTimeMardet.class);
        if (result.contains("auctionPricelist")) {
            //layout.setVisibility(View.VISIBLE);
            List<RealTimeMardet.AuctionPricelistBean> auctionPricelist = realTimeMardet.getAuctionPricelist();
            for (int i = 0; i < auctionPricelist.size(); i++) {
                RealTimeMardet.AuctionPricelistBean auctionPricelistBean = auctionPricelist.get(i);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
                String data = simpleDateFormat.format(Double.parseDouble(auctionPricelistBean.getSubmitDate()));
                Map<String, Object> map = new Hashtable<String, Object>();
               if (u_clientID .equals(auctionPricelistBean.getBuyerId()) ){
                   map.put("bianhao", uCompany);
               }else {
                   map.put("bianhao", auctionPricelistBean.getBuyerId());
               }
                map.put("danjia", auctionPricelistBean.getBidPrice());
                map.put("shuliang", auctionPricelistBean.getBidQut());
//                map.put("min_shuliang", auctionPricelistBean.getBuyerId());
                map.put("time", data);
                list.add(map);
                LogUtils.log("编号---》"+auctionPricelistBean.getFormatSequenceNum());
            }
            LogUtils.log("公司id---》"+u_clientID);

            Log.d("list的值", list.toString());
            GridAdpater adapter = new GridAdpater(getActivity(), list, R.layout.item_gridview_vessel_one,
                    new String[]{"bianhao", "danjia", "shuliang"/*, "min_shuliang"*/, "time"},
                    new int[]{
                            R.id.tv_bianhao,
                            R.id.tv_danjia,
                            R.id.tv_shuliang,
                            /*R.id.tv_min_shuliang,*/
                            R.id.tv_time
                    });
            lvDemo.setAdapter(adapter);


        } else {
//            layout.setVisibility(View.GONE);
            Toast.makeText(getActivity(), "暂无人竞拍该商品", Toast.LENGTH_SHORT).show();
        }


    }

    class GridAdpater extends SimpleAdapter {


        public GridAdpater(Context context, List<? extends Map<String, ?>> data, int resource, String[] from, int[] to) {
            super(context, data, resource, from, to);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;
            if (convertView == null) {
                viewHolder = new ViewHolder();
                convertView = getActivity().getLayoutInflater().inflate(R.layout.item_gridview_vessel_one, null);
                viewHolder.tv_bianhao = (TextView) convertView.findViewById(R.id.tv_bianhao);
                viewHolder.tv_danjia = (TextView) convertView.findViewById(R.id.tv_danjia);
                viewHolder.tv_shuliang = (TextView) convertView.findViewById(R.id.tv_shuliang);
//                viewHolder.tv_min_shuliang = (TextView) convertView.findViewById(R.id.tv_min_shuliang);
                viewHolder.tv_time = (TextView) convertView.findViewById(R.id.tv_time);
                viewHolder.layout = (LinearLayout) convertView.findViewById(R.id.layout_item_vessel_one);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }

            //viewHolder.tv_bianhao.setText(list.get(position).get("bianhao").toString());
            Log.d("nihao", list.get(position).get("bianhao").toString());
            viewHolder.tv_bianhao.setText(list.get(position).get("bianhao").toString());
            viewHolder.tv_danjia.setText(list.get(position).get("danjia").toString());
            viewHolder.tv_shuliang.setText(list.get(position).get("shuliang").toString());
//            viewHolder.tv_min_shuliang.setText(list.get(position).get("min_shuliang").toString());
            viewHolder.tv_time.setText(list.get(position).get("time").toString());
            if (position % 2 == 0) {
                viewHolder.layout.setBackgroundColor(getResources().getColor(R.color.bottom_background));
            } else {
                viewHolder.layout.setBackgroundColor(getResources().getColor(R.color.colorGreen));
            }

            return convertView;

        }

        class ViewHolder {
            LinearLayout layout;
            TextView tv_bianhao, tv_danjia, tv_shuliang/*, tv_min_shuliang*/, tv_time;

        }

    }


}

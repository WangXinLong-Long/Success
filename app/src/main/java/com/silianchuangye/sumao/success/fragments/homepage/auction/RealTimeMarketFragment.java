package com.silianchuangye.sumao.success.fragments.homepage.auction;


import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.silianchuangye.sumao.success.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class RealTimeMarketFragment extends Fragment {
    //private GridView gridView;
    private List<Map<String,Object>> list;
    private String id;
    private ListView lvDemo;
    private LinearLayout layout;
   // private List<String> bianhai,danjia,shuliang,m_dun,time;


    public RealTimeMarketFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_real_time_market, container, false);
        lvDemo= (ListView) view.findViewById(R.id.lvDemo);
        layout= (LinearLayout) view.findViewById(R.id.layout_center);
        list=new ArrayList<Map<String,Object>>();
        SharedPreferences sharedPreferences=getActivity().getSharedPreferences("sumao", Activity.MODE_PRIVATE);
        id=sharedPreferences.getString("id","");
        Log.d("id",id);
        list=new ArrayList<Map<String,Object>>();
        new Thread(){
            @Override
            public void run() {
               // super.run();
                getData();
            }
        }.start();




        return view;
    }
    public void getData(){
        String url="http://192.168.32.126:7023/rest/model/atg/commerce/catalog/ProductCatalogActor/auctionProductPriceList";
        RequestParams requestParams=new RequestParams(url);
        requestParams.addParameter("productId",id);
        x.http().post(requestParams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.d("实时行情的result",result);
                if (result.contains("auctionPricelist")) {
                    layout.setVisibility(View.VISIBLE);
                    try {
                        JSONObject obj_result = new JSONObject(result);
                        String info = obj_result.getString("auctionPricelist");
                        JSONArray array = new JSONArray(info);

                        for (int i = 0; i < array.length(); i++) {
                            JSONObject obj_info = array.getJSONObject(i);
                            Map<String,Object> map=new Hashtable<String, Object>();
//                            list.add(obj_info.getString("formatSequenceNum"));
//                            list.add(obj_info.getString("bidPrice"));
//                            list.add(obj_info.getString("bidQut"));
//                            list.add(obj_info.getString("buyerId"));
//                            list.add(obj_info.getString("submitDate"));
                            map.put("bianhao",obj_info.getString("formatSequenceNum"));
                            map.put("danjia",obj_info.getString("bidPrice"));
                            map.put("shuliang",obj_info.getString("bidQut"));
                            map.put("min_shuliang",obj_info.getString("buyerId"));
                            map.put("time",obj_info.getString("submitDate"));
                            list.add(map);

                        }
                        Log.d("list的值",list.toString());
                        GridAdpater adapter=new GridAdpater(getActivity(),list,R.layout.item_gridview_vessel_one,
                                new String[]{"bianhao","danjia","shuliang","min_shuliang","time"},
                                new int[]{
                                        R.id.tv_bianhao,
                                        R.id.tv_danjia,
                                        R.id.tv_shuliang,
                                        R.id.tv_min_shuliang,
                                        R.id.tv_time
                                });
                        lvDemo.setAdapter(adapter);


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }else {
                   // layout.setVisibility(View.GONE);
                    Toast.makeText(getActivity(), "暂无人竞拍该商品", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }
    class GridAdpater extends SimpleAdapter{


        public GridAdpater(Context context, List<? extends Map<String, ?>> data, int resource, String[] from, int[] to) {
                  super(context, data, resource, from, to);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;
            if (convertView==null){
                viewHolder=new ViewHolder();
                convertView=getActivity().getLayoutInflater().inflate(R.layout.item_gridview_vessel_one,null);
                viewHolder.tv_bianhao= (TextView) convertView.findViewById(R.id.tv_bianhao);
                viewHolder.tv_danjia= (TextView) convertView.findViewById(R.id.tv_danjia);
                viewHolder.tv_shuliang= (TextView) convertView.findViewById(R.id.tv_shuliang);
                viewHolder.tv_min_shuliang= (TextView) convertView.findViewById(R.id.tv_min_shuliang);
                viewHolder.tv_time= (TextView) convertView.findViewById(R.id.tv_time);
                viewHolder.layout= (LinearLayout) convertView.findViewById(R.id.layout_item_vessel_one);
                convertView.setTag(viewHolder);
            }else{
                viewHolder= (ViewHolder) convertView.getTag();
            }

            //viewHolder.tv_bianhao.setText(list.get(position).get("bianhao").toString());
            Log.d("nihao",list.get(position).get("bianhao").toString());
            viewHolder.tv_bianhao.setText(list.get(position).get("bianhao").toString());
            viewHolder.tv_danjia.setText(list.get(position).get("danjia").toString());
            viewHolder.tv_shuliang.setText(list.get(position).get("shuliang").toString());
            viewHolder.tv_min_shuliang.setText(list.get(position).get("min_shuliang").toString());
            viewHolder.tv_time.setText(list.get(position).get("time").toString());
            if (position%2==0){
                viewHolder.layout.setBackgroundColor(getResources().getColor(R.color.bottom_background));
            }else{
                viewHolder.layout.setBackgroundColor(getResources().getColor(R.color.colorGreen));
            }

            return convertView;

        }
        class ViewHolder{
            LinearLayout layout;
            TextView tv_bianhao,tv_danjia,tv_shuliang,tv_min_shuliang,tv_time;

        }

    }


}

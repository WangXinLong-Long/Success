package com.silianchuangye.sumao.success.fragments.homepage.auction;


import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ShareActionProvider;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.fragments.homepage.auction.openAuction.bean.RealTimeMardet;
import com.silianchuangye.sumao.success.utils.LogUtils;
import com.silianchuangye.sumao.success.utils.SuMaoConstant;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

/**
 * A simple {@link Fragment} subclass.
 */
public class RealTimeMarketFragment extends Fragment {
    //private GridView gridView;
    private List<Map<String, Object>> list;
    private String productId;
    private ListView lvDemo;
    private LinearLayout shang;
    private String u_clientID;
    private String uCompany;
    private String type_way;
    private RelativeLayout prompt;
    private String unique;
    private SharedPreferences sp;
    private Handler handler;
    private Timer timer;
    private TimerTask task;
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
       // String result = getArguments().getString("realTimeQuotes");

        uCompany = getArguments().getString("uCompany");
        type_way = getArguments().getString("type_way");
        productId=getArguments().getString("productId");
        Log.d("穿过来的商品id",productId);


        list = new ArrayList<Map<String, Object>>();
        u_clientID = getActivity().getSharedPreferences("sumao", Activity.MODE_PRIVATE).getString("U_clientID", "");
        if (type_way.equals("公开竞拍")){

            prompt.setVisibility(View.GONE);
            shang.setVisibility(View.VISIBLE);
        }else {
            prompt.setVisibility(View.VISIBLE);
            shang.setVisibility(View.GONE);
        }

        return view;
    }
    public void getinfo(){
//        sp=getActivity().getSharedPreferences("sumao",Activity.MODE_PRIVATE);
//        unique=sp.getString("unique","");
        String url= SuMaoConstant.SUMAO_IP+"/rest/model/atg/commerce/catalog/ProductCatalogActor/auctionProductPriceList";
        RequestParams rp = new RequestParams(url);
        rp.addParameter("productId", productId);

        Log.d("实时行情的rp",""+rp);
        x.http().post(rp, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.d("公开竞拍的实是行情",""+result);
                getData(result);
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

    @Override
    public void onStart() {
        super.onStart();
        handler=new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what){
                    case 1:
                        getinfo();
                        break;
                    default:
                        break;
                }
            }
        };

        timer=new Timer();
        task=new TimerTask() {
            @Override
            public void run() {
                Message message = new Message();
                message.what = 1;
                handler.sendMessage(message);
            }
        };

        timer.schedule(task,3000,3000);
    }



    public void getData(String result) {
        Log.d("getDate的值",""+result);
        Gson gson = new Gson();
        RealTimeMardet realTimeMardet = gson.fromJson(result, RealTimeMardet.class);
        list.clear();
        if (result.contains("auctionPricelist")) {
            //layout.setVisibility(View.VISIBLE);
            Log.d("到if里来","到了");
            List<RealTimeMardet.AuctionPricelistBean> auctionPricelist = realTimeMardet.getAuctionPricelist();
            for (int i = 0; i < auctionPricelist.size(); i++) {
                Log.d("到了for循环里","循环");
                RealTimeMardet.AuctionPricelistBean auctionPricelistBean = auctionPricelist.get(i);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
                String data = simpleDateFormat.format(Double.parseDouble(auctionPricelistBean.getSubmitDate()));
                Map<String, Object> map = new Hashtable<String, Object>();
                Log.d("u_clientID",u_clientID);
               if (u_clientID.equals(auctionPricelistBean.getBuyerId()) ){
                   map.put("bianhao", uCompany);
                   Log.d("编号---》",uCompany);
               }else {
                   map.put("bianhao", auctionPricelistBean.getBuyerId());
                   Log.d("编号",auctionPricelistBean.getBuyerId());
               }
                map.put("danjia", auctionPricelistBean.getBidPrice());
                map.put("shuliang", auctionPricelistBean.getBidQut());
                map.put("min_shuliang", auctionPricelistBean.getDealQut());
                map.put("time", data);
                list.add(map);
                LogUtils.log("编号---》"+auctionPricelistBean.getFormatSequenceNum());
            }
            LogUtils.log("公司id---》"+u_clientID);

            Log.d("list的值", list.toString()+list.size());

            GridAdpater adapter = new GridAdpater(getActivity(), list, R.layout.item_gridview_vessel_one,
                    new String[]{"bianhao", "danjia", "shuliang", "min_shuliang", "time"},
                    new int[]{
                            R.id.tv_bianhao,
                            R.id.tv_danjia,
                            R.id.tv_shuliang,
                            R.id.tv_min_shuliang,
                            R.id.tv_time
                    });

            lvDemo.setAdapter(adapter);

            adapter.notifyDataSetChanged();


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
                viewHolder.tv_min_shuliang = (TextView) convertView.findViewById(R.id.tv_min_shuliang);
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
            viewHolder.tv_min_shuliang.setText(list.get(position).get("min_shuliang").toString());
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
            TextView tv_bianhao, tv_danjia, tv_shuliang, tv_min_shuliang, tv_time;

        }

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        timer.cancel();
    }

    @Override
    public void onStop() {
        super.onStop();
        timer.cancel();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
       // timer.cancel();
    }
}

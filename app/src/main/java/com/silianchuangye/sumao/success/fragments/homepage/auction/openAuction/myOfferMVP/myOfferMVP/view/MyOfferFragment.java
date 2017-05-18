package com.silianchuangye.sumao.success.fragments.homepage.auction.openAuction.myOfferMVP.myOfferMVP.view;


import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.fragments.homepage.auction.openAuction.myOfferMVP.bean.MyOfferBean;
import com.silianchuangye.sumao.success.fragments.homepage.auction.openAuction.myOfferMVP.myOfferMVP.presenter.MyOfferPresenter;
import com.silianchuangye.sumao.success.fragments.homepage.auction.openAuction.openAuctionPresenter.OpenAuctionPresenter;
import com.silianchuangye.sumao.success.utils.SuMaoConstant;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyOfferFragment extends Fragment implements IMyOfferView {
    private List<Map<String, Object>> list;
    private String id;
    private ListView lvDemo;
    private LinearLayout layout;
    private String type;////////
    private MyOfferPresenter myOfferPresenter;
    private String productId;
    private GridAdpater adapter;
    private String _dynSessConf;
    private OpenAuctionPresenter openAuctionPresenter;
    String type_way ;
    // private List<String> bianhai,danjia,shuliang,m_dun,time;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        myOfferPresenter = new MyOfferPresenter(this);

        View view = inflater.inflate(R.layout.fragment_my_offer, container, false);
        lvDemo = (ListView) view.findViewById(R.id.lvDemo);
        RelativeLayout five_rl = (RelativeLayout) view.findViewById(R.id.five_rl);
        list = new ArrayList<Map<String, Object>>();
        _dynSessConf = getActivity().getSharedPreferences("sumao", Activity.MODE_PRIVATE).getString("unique","");
        String result = getArguments().getString("myOffer");
        type_way= getArguments().getString("type_way");
        productId = getArguments().getString("productId");
        Log.d("type的值",type_way);
        myOfferPresenter.getMyOfferResult(_dynSessConf,productId,type_way,null);
        if (type_way.equals("密封报价")) {
            five_rl.setVisibility(View.VISIBLE);
            getinfo("sealedAuctionProduct");
        } else {
            five_rl.setVisibility(View.GONE);
            getinfo("englishAuctionProduct");
        }


        return view;
    }

    public void getData(String result) {
        Log.d("我的报价的返回值",result);
        adapter.clearData();
        list.clear();
        if (result.contains("myPrice")) {
            //layout.setVisibility(View.VISIBLE);
            Gson gson = new Gson();
            MyOfferBean myOfferBean = gson.fromJson(result, MyOfferBean.class);
            List<MyOfferBean.MyPriceBean> myPrice = myOfferBean.getMyPrice();
            for (int i = 0; i < myPrice.size(); i++) {
                MyOfferBean.MyPriceBean myPriceBean = myPrice.get(i);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
                String data = simpleDateFormat.format(Double.parseDouble(myPriceBean.getSubmitDate()));
                Map<String, Object> map = new Hashtable<String, Object>();
                if (myPriceBean.getBidObjId() == null){
                    map.put("bianhao", myPriceBean.getFormatSequenceNum());
                }else {
                    map.put("bianhao", myPriceBean.getBidObjId());
                }
                map.put("danjia", myPriceBean.getBidPrice());
                map.put("shuliang", myPriceBean.getBidQut());
                map.put("time", data);
                list.add(map);
            }
            adapter.addData(list);
            adapter.notifyDataSetChanged();
        } else {
            Toast.makeText(getActivity(), "暂无我的报价", Toast.LENGTH_SHORT).show();
        }

    }

    public void revoke(String bianhao,int position){
        myOfferPresenter.getWithdrawalOfOffer(position,productId,bianhao,_dynSessConf);

    }

    @Override
    public void setMyOfferWithdrawalOfOfferResult(String result,int position) {
        Toast.makeText(getActivity(), result, Toast.LENGTH_SHORT).show();
        if (result.contains("success")){
            list.remove(position);
            adapter.clearData();
            adapter.addData(list);
            adapter.notifyDataSetChanged();
        }

    }

    @Override
    public void setMyOfferResult(String result, String bianhao) {
        boolean existence = false;
        int position = -1;
        getData(result);
        for (int i = 0; i < list.size(); i++) {
            if (bianhao.equals(list.get(i).get("bianhao"))){
                existence = true;
                position = i;
            }
        }
        if (existence){
            revoke((String) list.get(position).get("bianhao"),position);
        }else {
            Toast.makeText(getActivity(), "您要操作的数据已被移除", Toast.LENGTH_SHORT).show();
        }

    }

    class GridAdpater extends BaseAdapter {
        List<Map<String, Object>> list;
        String type_way;

        public GridAdpater(String type_way) {
            this.list = new ArrayList<>();
            this.type_way = type_way;
        }

        public void addData(List<Map<String, Object>> list) {
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
        public View getView(final int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;
            if (convertView == null) {
                viewHolder = new ViewHolder();
                convertView = getActivity().getLayoutInflater().inflate(R.layout.listgridviewitem, null);
                viewHolder.tv_bianhao = (TextView) convertView.findViewById(R.id.tv_bianhao);
                viewHolder.tv_danjia = (TextView) convertView.findViewById(R.id.tv_danjia);
                viewHolder.tv_shuliang = (TextView) convertView.findViewById(R.id.tv_shuliang);
                viewHolder.tv_time = (TextView) convertView.findViewById(R.id.tv_time);
                viewHolder.revoke = (Button) convertView.findViewById(R.id.revoke);
                viewHolder.revoke_rl = (RelativeLayout) convertView.findViewById(R.id.revoke_rl);
                viewHolder.layout = (LinearLayout) convertView.findViewById(R.id.layout_item_vessel_one);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }

            viewHolder.tv_bianhao.setText(list.get(position).get("bianhao").toString());
            viewHolder.tv_danjia.setText(list.get(position).get("danjia").toString());
            viewHolder.tv_shuliang.setText(list.get(position).get("shuliang").toString());
            //  viewHolder.tv_min_shuliang.setText(list.get(position).get("min_shuliang").toString());
            viewHolder.tv_time.setText(list.get(position).get("time").toString());
            if (position % 2 == 0) {
                viewHolder.layout.setBackgroundColor(getResources().getColor(R.color.bottom_background));
            } else {
                viewHolder.layout.setBackgroundColor(getResources().getColor(R.color.colorGreen));
            }
            if (type_way.equals("密封报价")) {
                viewHolder.revoke_rl.setVisibility(View.VISIBLE);
            } else {
                viewHolder.revoke_rl.setVisibility(View.GONE);
            }
            viewHolder.revoke.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //myOfferPresenter.getMyOfferResult(_dynSessConf,productId,"sealedAuctionProduct",(String) list.get(position).get("bianhao"));

                    final AlertDialog.Builder alert=new AlertDialog.Builder(getContext());
                    alert.setMessage("是否要撤销报价?");
                    alert.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                             dialog.cancel();
                        }
                    });
                    alert.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            revoke(list.get(position).get("bianhao").toString(),position);
                            list.remove(position);
                            adapter.notifyDataSetChanged();
                        }
                    });
                    alert.create().show();
                }
            });
            return convertView;

        }

        class ViewHolder {
            LinearLayout layout;
            TextView tv_bianhao, tv_danjia, tv_shuliang, tv_time;
            Button revoke;
            RelativeLayout revoke_rl;
        }

    }

    public void getinfo(String type){
        String url = "";
        if (type.equals("sealedAuctionProduct")) {//密封竞拍我的报价
            url = SuMaoConstant.SUMAO_IP + "/rest/model/atg/commerce/catalog/ProductCatalogActor/sealedAuctionProductPrice";
        } else if (type.equals("englishAuctionProduct")) {//公开竞拍我的报价
            url = SuMaoConstant.SUMAO_IP + "/rest/model/atg/commerce/catalog/ProductCatalogActor/myAuctionProductPrice";
        }
        RequestParams rp = new RequestParams(url);
        rp.addParameter("productId", productId);
        rp.addParameter("_dynSessConf", _dynSessConf);
        Log.d("我的报价的rp",rp+"");
        x.http().post(rp, new Callback.CommonCallback<String>() {

            @Override
            public void onSuccess(String result) {
                 Log.d("result的值",result);
                 adapter = new GridAdpater(type_way);
                 lvDemo.setAdapter(adapter);
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


}



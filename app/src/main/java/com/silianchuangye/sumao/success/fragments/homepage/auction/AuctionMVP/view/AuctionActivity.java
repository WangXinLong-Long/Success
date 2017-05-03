package com.silianchuangye.sumao.success.fragments.homepage.auction.AuctionMVP.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.google.gson.Gson;
import com.jingchen.pulltorefresh.PullToRefreshLayout;
import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.fragments.homepage.auction.AuctionMVP.adapter.AuctionListViewAdapter;
import com.silianchuangye.sumao.success.fragments.homepage.auction.AuctionMVP.bean.AuctionDetailBean;
import com.silianchuangye.sumao.success.fragments.homepage.auction.AuctionMVP.bean.AuctionSessionBean;
import com.silianchuangye.sumao.success.fragments.homepage.auction.AuctionMVP.bean.ListBean;
import com.silianchuangye.sumao.success.fragments.homepage.auction.AuctionMVP.presenter.AuctionPresenter;
import com.silianchuangye.sumao.success.fragments.homepage.auction.openAuction.openAuctionView.OpenAuctionActivity;
import com.silianchuangye.sumao.success.utils.LogUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AuctionActivity extends AppCompatActivity implements IAuctionView {
    private ImageView imageback;
    private String name;
    private List<String> order_id;
    private List<String> SKUID;
    private ArrayList<Map<String, Object>> list_message;
    AuctionDetailBean auctionDetailBean;
    private String name_Auction;
    private String SellId;
    private String proType;

    private String id, namechuanpin, startTime = "", endTime, startPrice, count, cangku, company, state, type;
    private int currentPage = 1;
    // 默认每页条数
    private int pageSize = 10;
    // 如果已取得所有为true
    private boolean isAllGot = false;
    private AuctionListViewAdapter refreshListViewadapter;
    private List<ListBean.ProdBean> lists = new ArrayList<>();
    private AuctionPresenter auctionPresenter;
    private PullToRefreshLayout pullToRefreshLayout;
    private ListView pullableView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auction);
        auctionPresenter = new AuctionPresenter(this);
        Bundle bundle = getIntent().getExtras();
        name_Auction = bundle.getString("name");
        SellId = bundle.getString("result");

        getAddData(SellId);
        init();
    }

    public void init() {
        imageback = (ImageView) findViewById(R.id.ivBack_auction_layout_top);
        imageback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                setResult(1, intent);
                AuctionActivity.this.finish();
            }
        });
//        pullToRefreshListView
        pullToRefreshLayout = ((PullToRefreshLayout) findViewById(R.id.refresh_view));
        pullableView = ((ListView) pullToRefreshLayout.getPullableView());
        refreshListViewadapter = new AuctionListViewAdapter(AuctionActivity.this);
        pullableView.setAdapter(refreshListViewadapter);
        refreshListViewadapter.addData(lists);

        pullToRefreshLayout.setOnPullListener(new AuctionListViewPullListener());
        pullableView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                onListViewItemClick(position);
            }

        });


    }

    private void onListViewItemClick(int position) {
        String type_way = null;
        String productId = lists.get(position).getId();
        LogUtils.log("productId-->" + productId);
//        公开竞拍
        if (lists.get(position).getType().equals("englishAuctionProduct")) {
            type_way = "公开竞拍";
//        密封报价
        } else if (lists.get(position).getType().equals("sealedAuctionProduct")) {
            type_way = "密封报价";
        }
        proType = lists.get(position).getType();
        auctionPresenter.getOpenAuctionOrSealedBid(type_way, productId, proType);
//        Intent intent = new Intent(this, OpenAuctionActivity.class);
//        intent.putExtra("auctionDetailBean", auctionDetailBean);
//        intent.putExtra("type_way", type_way);
//        intent.putExtra("proType",proType);
//        intent.putExtra("productId",productId);
//        startActivity(intent);
    }

    public void getAddData(String result) {
//        getInfo();
        if (name_Auction.equals("竞拍")) {
            getInfo(result);
        } else if (name_Auction.equals("竞拍专场")) {
            getAuction_zhunchang(result);
        }
    }


    //获取到从首页获得的数据，转化为竞拍模型
    public void getInfo(String result) {
        Log.d("竞拍结果返回值",result);
        if (result.contains("prod")) {
            Gson gson = new Gson();
            ListBean listBean = gson.fromJson(result, ListBean.class);
            if (listBean.getProd().size() != 0) {
                lists.addAll(listBean.getProd());
            }
        }


    }

    public void getAuction_zhunchang(String result) {
        Log.d("竞拍专场的返回值",result);
        if (result.contains("productItem")) {
            Gson gson = new Gson();
            AuctionSessionBean auctionSessionBean = gson.fromJson(result, AuctionSessionBean.class);
            List<AuctionSessionBean.ProductItemBean> productItem = auctionSessionBean.getProductItem();
            for (int i = 0; i < productItem.size(); i++) {
                AuctionSessionBean.ProductItemBean productItemBean = productItem.get(i);
                ListBean.ProdBean prodBean = new ListBean.ProdBean();
                prodBean.setCl_cangku(productItemBean.getWareHouse());
                prodBean.setCl_gongsi(productItemBean.getSupplier());
                prodBean.setCl_mingcheng(productItemBean.getProductName());
                prodBean.setId(productItemBean.getProductId());
                prodBean.setStatus(productItemBean.getStatus());
                prodBean.setStartDate(productItemBean.getStartDate());
                prodBean.setEndDate(productItemBean.getEndDate());
                prodBean.setType(productItemBean.getType());
                prodBean.setCl_zongliang(productItemBean.getQuantity());
                lists.add(prodBean);
            }
        }
    }

    //    通过上啦或者下拉取得数据
    @Override
    public void setAucionListResult(String result, String updateOrLoad) {
        if (updateOrLoad == "update") {
            lists.clear();
        }
        Gson gson = new Gson();
        ListBean listBean = gson.fromJson(result, ListBean.class);
        lists.addAll(listBean.getProd());
        refreshListViewadapter.clearData();
        refreshListViewadapter.addData(lists);
        refreshListViewadapter.notifyDataSetChanged();
    }

    @Override
    public void setOpenAuctionOrSealedBidResult(String result, String type_way) {
        Log.d("竞拍产品的详情",""+result);
        Log.d("type_way",type_way);
        Log.d("proType",proType);
//        Gson gson = new Gson();
//        AuctionDetailBean auctionDetailBean = gson.fromJson(result, AuctionDetailBean.class);
        Intent intent = new Intent(AuctionActivity.this, OpenAuctionActivity.class);
        //intent.putExtra("auctionDetailBean", auctionDetailBean);
        intent.putExtra("result",result);
        intent.putExtra("type_way", type_way);
        intent.putExtra("proType",proType);
        startActivity(intent);
        //Log.d("变价单位",""+auctionDetailBean.getCl_xbianjia());

    }


    //    上啦加载，下拉刷新
    private class AuctionListViewPullListener implements PullToRefreshLayout.OnPullListener {
        String pageNum = "1";

        @Override
        public void onRefresh(final PullToRefreshLayout pullToRefreshLayout) {
            new Handler() {
                @Override
                public void handleMessage(Message msg) {
                    super.handleMessage(msg);
                    pageNum = "1";
                    auctionPresenter.getAuctionListResult(pageNum, "update");
                    pullToRefreshLayout.refreshFinish(PullToRefreshLayout.SUCCEED);
                }
            }.sendEmptyMessageDelayed(0, 3000);

        }

        @Override
        public void onLoadMore(final PullToRefreshLayout pullToRefreshLayout) {
            new Handler() {
                @Override
                public void handleMessage(Message msg) {
                    super.handleMessage(msg);
                    int num = Integer.parseInt(pageNum) + 1;
                    pageNum = num + "";
                    auctionPresenter.getAuctionListResult(pageNum, "load");
                    pullToRefreshLayout.refreshFinish(PullToRefreshLayout.SUCCEED);
                }
            }.sendEmptyMessageDelayed(0, 3000);
        }
    }
}

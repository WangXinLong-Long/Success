package com.silianchuangye.sumao.success.fragments.homepage.groupbuying.GroupBuyingMVP.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.google.gson.Gson;
import com.jingchen.pulltorefresh.PullToRefreshLayout;
import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.fragments.homepage.groupbuying.GroupBuyingMVP.adapter.GroupBuyingListViewAdapter;
import com.silianchuangye.sumao.success.fragments.homepage.groupbuying.GroupBuyingMVP.bean.GroupBuyingListBean;
import com.silianchuangye.sumao.success.fragments.homepage.groupbuying.GroupBuyingMVP.presenter.GroupBuyingPresenter;
import com.silianchuangye.sumao.success.fragments.homepage.groupbuying.groupBuyingDetailMVP.view.GroupBuyingSuccessActivity;
import com.silianchuangye.sumao.success.utils.Loding;

import java.util.ArrayList;
import java.util.List;

public class GroupBuyingActivity extends AppCompatActivity implements IGroupBuyingView {
    private ListView lv_group_buying;
    private List<GroupBuyingListBean.ProdBean> list = new ArrayList<>();
    ;
    private GroupBuyingListViewAdapter adapter;
    private ImageView back, search;
    private String shangpinId;
    private PullToRefreshLayout pullToRefreshLayout;
    private GroupBuyingPresenter groupBuyingPresenter;
    GroupBuyingListBean groupBuyingListBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_buying);
        groupBuyingPresenter = new GroupBuyingPresenter(this);

        back = (ImageView) findViewById(R.id.ivBack_group_layout_top);
        search = (ImageView) findViewById(R.id.ivSearch_group_layout_top);
        pullToRefreshLayout = ((PullToRefreshLayout) findViewById(R.id.refresh_view));
        lv_group_buying = ((ListView) pullToRefreshLayout.getPullableView());
        adapter = new GroupBuyingListViewAdapter(this);
        lv_group_buying.setAdapter(adapter);
        pullToRefreshLayout.setOnPullListener(new GroupBuyingListViewPullListener());
        addData();
        event();
    }


    /**
     * 添加数据
     */
    public void addData() {
        boolean updateOrLoad = true;
        Loding.show(GroupBuyingActivity.this, "加载中...", false, null);
        groupBuyingPresenter.getGroupBuyingResult("1",updateOrLoad);
    }

    /**
     * 事件监听
     */
    public void event() {
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GroupBuyingActivity.this.finish();
            }
        });
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        /**
         * ListView的item的点击事件
         */
        lv_group_buying.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //groupBuyingPresenter.getGroupBuyingDetailResult(list.get(position).getId(), list.get(position).getRuning(),list.get(position).getResult());
                Intent intent = new Intent(GroupBuyingActivity.this, GroupBuyingSuccessActivity.class);
                intent.putExtra("productId",groupBuyingListBean.getProd().get(position).getId());
                startActivity(intent);
            }
        });

    }

    @Override
    public void setGroupBuyingResult(String result, boolean updateOrLoad) {
        Loding.dis();
        if (updateOrLoad) {
            list.clear();
        }
        Gson gson = new Gson();
        groupBuyingListBean= gson.fromJson(result, GroupBuyingListBean.class);
        list.addAll(groupBuyingListBean.getProd());
        adapter.clearData();
        adapter.addData(list);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void setGroupBuyingDetailResult(String result, String state,String result2) {
        Intent intent = new Intent(GroupBuyingActivity.this, GroupBuyingSuccessActivity.class);

//        if (state.equals("true")) {
//            intent.putExtra("state", "团购已开始");
//            intent.putExtra("result2","abc");
//           // intent.putExtra("productOff","false");
//        } else if (state.equals("false")) {
//            intent.putExtra("state", "团购未开始");
//            intent.putExtra("result2","abc");
//            //intent.putExtra("productOff","false");
//        } else if (state.equals("end")){
//                if (result2.equals("gone")) {
//                    intent.putExtra("state", "团购结束");
//                    intent.putExtra("result2", "未成团");
//                   // intent.putExtra("productOff","false");
//                } else {
//                    intent.putExtra("state", "团购结束");
//                    intent.putExtra("result2", "已成团");
//                    //intent.putExtra("productOff","false");
//                }
//
//        }
//        intent.putExtra("result", result);
      //  startActivity(intent);


    }

    //    上啦加载，下拉刷新
    private class GroupBuyingListViewPullListener implements PullToRefreshLayout.OnPullListener {
        String pageNum = "1";

        @Override
        public void onRefresh(final PullToRefreshLayout pullToRefreshLayout) {
            new Handler() {
                @Override
                public void handleMessage(Message msg) {
                    super.handleMessage(msg);
                    pageNum = "1";
                    boolean updateOrLoad = true;
                    groupBuyingPresenter.getGroupBuyingResult(pageNum,updateOrLoad);
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
                    boolean updateOrLoad = false;
                    groupBuyingPresenter.getGroupBuyingResult(pageNum,updateOrLoad);
                    pullToRefreshLayout.refreshFinish(PullToRefreshLayout.SUCCEED);
                }
            }.sendEmptyMessageDelayed(0, 3000);
        }
    }


}

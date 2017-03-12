package com.silianchuangye.sumao.success.fragments.myPlasticTrade.messagePush.view;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.google.gson.Gson;
import com.jingchen.pulltorefresh.PullToRefreshLayout;
import com.jingchen.pulltorefresh.PullableListView;
import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.fragments.homepage.groupbuying.GroupBuyingMVP.adapter.GroupBuyingListViewAdapter;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.messagePush.adapter.MessagePushListViewAdapter;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.messagePush.bean.MessagePush;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.messagePush.presenter.MessagePushPresenter;
import com.silianchuangye.sumao.success.utils.Loding;

import java.util.ArrayList;
import java.util.List;

public class MessagePushActivity extends AppCompatActivity implements View.OnClickListener ,IMessagePushView{
    private ImageView iv_Back;
    private ImageView iv_Search;
    private ListView pullList;
   // private List<Map<String,String>> list;
    private PullToRefreshLayout layoutPull;
    private MessagePushPresenter messagePushPresenter;
    private List<MessagePush.Message> list;
    private MessagePushListViewAdapter adapter;


   // private GroupBuyingListViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_push);
        //messagePushPresenter=new MessagePushPresenter(this);
        messagePushPresenter=new MessagePushPresenter();
        iv_Back= (ImageView) findViewById(R.id.img_create_logistics_back);
        iv_Back.setOnClickListener(this);
        iv_Search= (ImageView) findViewById(R.id.img_create_logistics_search);
        iv_Search.setOnClickListener(this);

        layoutPull= (PullToRefreshLayout) findViewById(R.id.layoutPull);
        //pullList= (PullableListView) findViewById(R.id.pullList);
        pullList=((ListView)layoutPull.getPullableView());
        list=new ArrayList<>();

        addData();
        adapter=new MessagePushListViewAdapter(this,list);
        pullList.setAdapter(adapter);
        layoutPull.setOnPullListener(new GroupBuyingListViewPullListener());


    }
    /**
     * 添加数据
     */
    public void addData() {
        SharedPreferences sp=getSharedPreferences("sumao", Activity.MODE_PRIVATE);
        String unique=sp.getString("unique","");
        boolean updateOrLoad = true;
        Loding.show(MessagePushActivity.this, "加载中...", false, null);
        //groupBuyingPresenter.getGroupBuyingResult("1",updateOrLoad);
        messagePushPresenter.getMessagePushResult("1",updateOrLoad,unique);
    }
//    public void getinfo(){
//        SharedPreferences sp=getSharedPreferences("sumao", Activity.MODE_PRIVATE);
//        String unique=sp.getString("unique","");
//        RequestParams rp=new RequestParams(SuMaoConstant.SUMAO_IP+"/rest/model/atg/commerce/order/myorder/OrderPaymentInfo/noticeList");
//        rp.addParameter("_dynSessConf",unique);
//        rp.addParameter("page",1);
//        x.http().post(rp, new Callback.CommonCallback<String>() {
//            @Override
//            public void onSuccess(String result) {
//                Log.d("返回值",result);
//
//            }
//
//            @Override
//            public void onError(Throwable ex, boolean isOnCallback) {
//
//            }
//
//            @Override
//            public void onCancelled(CancelledException cex) {
//
//            }
//
//            @Override
//            public void onFinished() {
//
//            }
//        });
//    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_create_logistics_back:
                MessagePushActivity.this.finish();
                break;
            case R.id.img_create_logistics_search:
                //弹出popupwindow
                break;

        }

    }


    private class GroupBuyingListViewPullListener implements PullToRefreshLayout.OnPullListener {
        String pageNum = "1";
        SharedPreferences sp=getSharedPreferences("sumao", Activity.MODE_PRIVATE);
        String unique=sp.getString("unique","");
        @Override
        public void onRefresh(final PullToRefreshLayout pullToRefreshLayout) {
            new Handler() {
                @Override
                public void handleMessage(Message msg) {
                    super.handleMessage(msg);
                    pageNum = "1";
                    boolean updateOrLoad = true;
                    //groupBuyingPresenter.getGroupBuyingResult(pageNum,updateOrLoad);
                    messagePushPresenter.getMessagePushResult(pageNum,updateOrLoad,unique);
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
                    messagePushPresenter.getMessagePushResult(pageNum,updateOrLoad,unique);
                    //groupBuyingPresenter.getGroupBuyingResult(pageNum,updateOrLoad);
                    pullToRefreshLayout.refreshFinish(PullToRefreshLayout.SUCCEED);
                }
            }.sendEmptyMessageDelayed(0, 3000);
        }
    }


    @Override
    public void setMessagePushResult(String result, boolean UpdateorLoad,String unique) {
        Loding.dis();
        if (UpdateorLoad) {
            list.clear();
        }
        Gson gson = new Gson();
//        GroupBuyingListBean groupBuyingListBean = gson.fromJson(result, GroupBuyingListBean.class);
//        list.addAll(groupBuyingListBean.getProd());
        MessagePush messagePush=gson.fromJson(result,MessagePush.class);
        list.addAll(messagePush.getList());
        Log.d("list的列表","list列表"+list.toString());
        adapter.clearData();
        adapter.addData(list);
        adapter.notifyDataSetChanged();
    }
}

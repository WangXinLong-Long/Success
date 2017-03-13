package com.silianchuangye.sumao.success.fragments.pagerFourMVP.view;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ExpandableListView;

import com.google.gson.Gson;
import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.custom.CustomExpandableListView;
import com.silianchuangye.sumao.success.fragments.BasePager;
import com.silianchuangye.sumao.success.fragments.pagerFourMVP.adapter.PagerFourAdapter;
import com.silianchuangye.sumao.success.fragments.pagerFourMVP.bean.AboutSuMaoBean;
import com.silianchuangye.sumao.success.fragments.pagerFourMVP.pagerFourDetail.PagerFourDetailActivity;
import com.silianchuangye.sumao.success.fragments.pagerFourMVP.pagerFourDetail.bean.PagerFourDetailBean;
import com.silianchuangye.sumao.success.fragments.pagerFourMVP.presenter.PagerFourPresenter;
import com.silianchuangye.sumao.success.utils.LogUtils;
import com.silianchuangye.sumao.success.utils.SuMaoConstant;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

/**
 * Created by Jobs Created on 2016/12/6.
 */
public class PagerFour extends BasePager {
    private View pagerFourView;
    private CustomExpandableListView expandableListView;
    private Context context;
    private PagerFourAdapter pagerFourAdapter;
    private List<List<AboutSuMaoBean.NewsItemBean>> helpIds = new ArrayList<>();
    private PagerFourPresenter pagerFourPresenter;
    private List<String> groupList;
    private String item_title;
    private String url;
    private String fanhui_result;
    private String group;
    private RequestParams rp;

    @Override
    public void myClickSearch() {
    }

    @Override
    public void initDate() {
        rl_title.setVisibility(View.GONE);
        pagerFourView = View.inflate(mActivity, R.layout.pager_four, null);
        fl_content.addView(pagerFourView);
        context = mActivity;

        expandableListView = (CustomExpandableListView) pagerFourView.findViewById(R.id.expandable_listView);

        pagerFourAdapter = new PagerFourAdapter(context);
        expandableListView.setAdapter(pagerFourAdapter);

//        设置箭头图标消失
        expandableListView.setGroupIndicator(null);

//        默认列表展开
        for (int i = 0; i < pagerFourAdapter.getGroupCount(); i++) {
            expandableListView.expandGroup(i);
        }
        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                return false;
            }
        });

        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
//                String newsId = null;
//                if (groupPosition == 0){
//                    helpIds.get(0).get(childPosition);
//                }else {
//                    helpIds.get(1).get(childPosition);
//                }

                item_title=pagerFourAdapter.getChild(groupPosition,childPosition).toString();
                Log.d("item_title",item_title);
                Log.d("title", pagerFourAdapter.getGroup(groupPosition).toString());
                group=pagerFourAdapter.getGroup(groupPosition).toString();
                if (group.equals("塑贸指南")){
                    url=SuMaoConstant.SUMAO_IP+"/rest/model/atg/commerce/news/SumaoNewsActor/CMSNewsDroplet";
                }else if (group.equals("客服帮助")){
                    url=SuMaoConstant.SUMAO_IP+"/rest/model/atg/commerce/news/SumaoNewsActor/customerHelp";
                }
                new Thread(){
                    @Override
                    public void run() {
                        super.run();
                        getItemTitle(item_title,url);
                       // Log.d("aaaa",getItemTitle(url,item_title));
                    }
                }.start();


                return true;
            }
        });
    }

    //    得到页面中所有关于塑贸的数据
//    @Override
//    public void setPagerFourAboutSuMaoResult(String result) {
//        Gson gson = new Gson();
//        AboutSuMaoBean aboutSuMaoBean = gson.fromJson(result, AboutSuMaoBean.class);
//        helpIds.add(aboutSuMaoBean.getNewsItem());
//        LogUtils.log("关于塑贸的数据--->"+aboutSuMaoBean.getNewsItem().get(0).getCaption());
//
//        pagerFourPresenter.getPagerFourCustomerServiceGuideResult();
//    }

    //    得到页面中所有客服指南的数据
//    @Override
//    public void setPagerFourCustomerServiceGuideResult(String result) {
//        Gson gson = new Gson();
//        AboutSuMaoBean customerServiceGuideSuMaoBean = gson.fromJson(result, AboutSuMaoBean.class);
//        helpIds.add(customerServiceGuideSuMaoBean.getNewsItem());
//        LogUtils.log("客服指南的数据--->"+customerServiceGuideSuMaoBean.getNewsItem().get(0).getCaption());
//        pagerFourAdapter.addGroupData(groupList);
//        pagerFourAdapter.addChildData(helpIds);
//        pagerFourAdapter.notifyDataSetChanged();
//    }

//    获取详情页的内容
//    @Override
//    public void setPagerFourDetailResult( PagerFourDetailBean pagerFourDetailBean) {
//        Intent intent = new Intent(mActivity,PagerFourDetailActivity.class);
//        intent.putExtra("pagerFourDetailBean",pagerFourDetailBean);
//        intent.putExtra("haha","haha");
//        startActivity(intent);
//    }
    public void getItemTitle(String name,String url){

        rp=new RequestParams(url);
        //rp.setConnectTimeout(30 * 1000);


        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("infoType","常见问题");
            Log.d("name的值",name);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.d("canshu",jsonObject.toString());
        rp.setAsJsonContent(true);
        rp.setBodyContent(jsonObject.toString());


        //   rp.setBodyContent(data.toString());
        Log.d("rp的值",rp+"");
        x.http().post(rp, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.d("返回值",result);

                Intent intent=new Intent(mActivity,ChildPageActivity.class);
                intent.putExtra("result",result);
                intent.putExtra("title",item_title);
                intent.putExtra("group",group);
                startActivity(intent);

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
    public void myClickLeft() {
    }

    @Override
    public void myClickRight() {
    }


}

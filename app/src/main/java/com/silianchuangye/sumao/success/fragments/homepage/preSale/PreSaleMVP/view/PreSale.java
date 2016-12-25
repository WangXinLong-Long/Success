package com.silianchuangye.sumao.success.fragments.homepage.preSale.PreSaleMVP.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jingchen.pulltorefresh.PullToRefreshLayout;
import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.adapter.PreSaleAdapter;
import com.silianchuangye.sumao.success.fragments.homepage.goodInStock.GoodsInStockActivityMVP.bean.GoodsInStockActivityBean;
import com.silianchuangye.sumao.success.fragments.homepage.goodInStock.GoodsInStockActivityMVP.bean.TransactionTypeBean;
import com.silianchuangye.sumao.success.fragments.homepage.pagerOneGridViewDetailActivity.CustomPopupWindow.MultiselectPopupWindow;
import com.silianchuangye.sumao.success.fragments.homepage.pagerOneGridViewDetailActivity.CustomPopupWindow.MultiselectPopupWindowCompleteClickListener;
import com.silianchuangye.sumao.success.fragments.homepage.pagerOneGridViewDetailActivity.screenPopupWindow.ScreenMultiselectPopupWindowCompleteClickListener;
import com.silianchuangye.sumao.success.fragments.homepage.pagerOneGridViewDetailActivity.screenPopupWindow.ScreenPopupWindow;
import com.silianchuangye.sumao.success.fragments.homepage.preSale.PreSaleDetailActivityMVP.bean.PreSaleDetailBean;
import com.silianchuangye.sumao.success.fragments.homepage.preSale.PreSaleDetailActivityMVP.view.PreSaleDetailActivity;
import com.silianchuangye.sumao.success.fragments.homepage.preSale.PreSaleMVP.presenter.PreSalePresenter;
import com.silianchuangye.sumao.success.utils.LogUtils;
import com.silianchuangye.sumao.success.utils.ThreeLevelLindage.Area;
import com.silianchuangye.sumao.success.utils.ThreeLevelLindage.CascadingMenuPopWindow;
import com.silianchuangye.sumao.success.utils.ThreeLevelLindage.CascadingMenuViewOnSelectListener;
import com.silianchuangye.sumao.success.utils.ThreeLevelLindage.DBHelper;
import com.silianchuangye.sumao.success.utils.ThreeLevelLindage.ThreeLevelCompletedListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/5/17 0017.
 */
public class PreSale extends Activity implements View.OnClickListener, AdapterView.OnItemClickListener,IPreSaleView {

    private RelativeLayout tradingPatterns;
    private RelativeLayout region;
    private RelativeLayout classification;
    private RelativeLayout application;
    private CascadingMenuPopWindow cascadingMenuPopWindow;
    DBHelper dbHelper = new DBHelper(this);

    ArrayList<Area> provinceList = null;
    ArrayList<String> datas = new ArrayList<>();
    ArrayList<ArrayList<String>> lists = new ArrayList<>();
    private ImageView screen;
    private ArrayList<String> classification1;
    private ArrayList<String> application1;
    private ArrayList<String> gride;
    private TextView tv_screen_title_bar_title;
    private ListView pre_sale_listView;
    private List<GoodsInStockActivityBean.InBean> smClList = new ArrayList<>();
    private PreSaleAdapter adapter;
    private PullToRefreshLayout prl;
    private PreSalePresenter preSalePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pager_one_grid_view_detail_activity);
        provinceList = dbHelper.getProvince();
        preSalePresenter = new PreSalePresenter(this);
//        交易模式
        tradingPatterns = ((RelativeLayout) findViewById(R.id.trading_patterns_rl));
        tradingPatterns.setOnClickListener(this);
//        地区
        region = ((RelativeLayout) findViewById(R.id.region_rl));
        region.setOnClickListener(this);
//        分类
        classification = ((RelativeLayout) findViewById(R.id.classification_rl));
        classification.setOnClickListener(this);
//        应用
        application = ((RelativeLayout) findViewById(R.id.application_rl));
        application.setOnClickListener(this);

//        筛选
        screen = ((ImageView) findViewById(R.id.iv_screen_title_bar_search));
        screen.setVisibility(View.VISIBLE);
        screen.setOnClickListener(this);

//
        initData();
//        获取标题
        tv_screen_title_bar_title = ((TextView) findViewById(R.id.tv_screen_title_bar_title));
        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        tv_screen_title_bar_title.setText(title);

        prl = ((PullToRefreshLayout) findViewById(R.id.refresh_view));

        pre_sale_listView = ((ListView) prl.getPullableView());
            LogUtils.log("预售" + "--------------WXL");
            GoodsInStockActivityBean goodsInStockActivityBean = (GoodsInStockActivityBean) intent.getSerializableExtra("preSaleActivityBean");
            smClList.addAll(goodsInStockActivityBean.getIn());
            adapter = new PreSaleAdapter(this);
            pre_sale_listView.setAdapter(adapter);
            adapter.addData(smClList);
            adapter.notifyDataSetChanged();
        //        上啦加载，下拉刷新的监听
        prl.setOnPullListener(new PagerOneGoodsInStockActivityListPullListener( /*smClList,*/preSalePresenter, title));
        pre_sale_listView.setOnItemClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
//            交易模式
            case R.id.trading_patterns_rl:
//                这里填写类型（交易模式、分类、应用、牌号）
                showTradingPatternPopupWindow("trading", datas);
                break;
//            地区
            case R.id.region_rl:
                showPopMenu();
                break;
//            分类
            case R.id.classification_rl:
                showTradingPatternPopupWindow("classification", classification1);
                break;
//            应用
            case R.id.application_rl:
                showTradingPatternPopupWindow("application", application1);
                break;

//            筛选
            case R.id.iv_screen_title_bar_search:
                screenPopupWindow(lists);
                break;

        }
    }

    //    （交易模式、分类、应用、牌号）PopupWindow
    private void showTradingPatternPopupWindow(String type, List<String> datas) {
        MultiselectPopupWindow multiselectPopupWindow = null;
        if (multiselectPopupWindow == null) {
            multiselectPopupWindow = new MultiselectPopupWindow(getApplicationContext(), datas, type);
            multiselectPopupWindow.setOnClickCompleteListener(new CompleteClickListener());
            multiselectPopupWindow.setFocusable(true);
            multiselectPopupWindow.showAsDropDown(tradingPatterns, 5, 5);
        } else if (multiselectPopupWindow != null && multiselectPopupWindow.isShowing()) {
            multiselectPopupWindow.dismiss();
        } else {
            multiselectPopupWindow.showAsDropDown(tradingPatterns, 5, 5);
        }
    }

    //    筛选的PopupWindow
    private void screenPopupWindow(ArrayList<ArrayList<String>> datas) {
        ScreenPopupWindow screenPopupWindow = null;
        if (screenPopupWindow == null) {
            screenPopupWindow = new ScreenPopupWindow(getApplicationContext(), datas);
            screenPopupWindow.setOnClickCompleteListener(new ScreenCompleteClickListener());
            screenPopupWindow.setFocusable(true);
            screenPopupWindow.showAtLocation(findViewById(R.id.parent), Gravity.TOP, 5, 5);
        } else if (screenPopupWindow != null && screenPopupWindow.isShowing()) {
            screenPopupWindow.dismiss();
        } else {
            screenPopupWindow.showAtLocation(findViewById(R.id.parent), Gravity.TOP, 5, 5);
        }
    }

    //      地区PopupWindow
    private void showPopMenu() {

        if (cascadingMenuPopWindow == null) {
            cascadingMenuPopWindow = new CascadingMenuPopWindow(getApplicationContext(), provinceList);
            cascadingMenuPopWindow.setMenuViewOnSelectListener(new NMCascadingMenuViewOnSelectListener());
            cascadingMenuPopWindow.setThreeLevelCompletedListener(new ThreeLevelCompleteClickListener());
            cascadingMenuPopWindow.setFocusable(true);
            cascadingMenuPopWindow.showAsDropDown(region, 5, 5);
        } else if (cascadingMenuPopWindow != null && cascadingMenuPopWindow.isShowing()) {
            cascadingMenuPopWindow.dismiss();
        } else {
            cascadingMenuPopWindow.showAsDropDown(region, 5, 5);
        }
    }

    //点击 市 的点击事件
    class NMCascadingMenuViewOnSelectListener implements CascadingMenuViewOnSelectListener {
        @Override
        public void getValue(String provinceId, String cityId, String countyId) {
            Toast.makeText(getApplicationContext(), "省市县的id--" + provinceId + "--" + cityId + "--" + countyId, Toast.LENGTH_SHORT).show();
        }
    }

    //      点击完成后，
    class CompleteClickListener implements MultiselectPopupWindowCompleteClickListener {

        @Override
        public void getSelectionValues(List<String> selectItems,String type) {
            Toast.makeText(PreSale.this, "search" + selectItems.toString(), Toast.LENGTH_SHORT).show();
        }
    }

    //    筛选的完成按钮的动作
    class ScreenCompleteClickListener implements ScreenMultiselectPopupWindowCompleteClickListener {
        @Override
        public void getSelectionValues(List<String> regionSelectItems, List<String> classifcationSelectItems, List<String> applicationSelectItems,String grade) {

            Toast.makeText(PreSale.this, "search" + "地区:" + regionSelectItems + "   分类:" + classifcationSelectItems + "   应用:" + applicationSelectItems , Toast.LENGTH_SHORT).show();
        }

    }


    //    上啦加载下拉刷新的加载
    @Override
    public void setDataInActivity(GoodsInStockActivityBean goodsInStockActivityBean, String updateOrLoad) {
        if (updateOrLoad.equals("update")) {
            adapter.clearData();
        }
        List<GoodsInStockActivityBean.InBean> in = goodsInStockActivityBean.getIn();
        adapter.addData(in);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void getPreSaleDetailData(PreSaleDetailBean preSaleDetailBean,String skuId) {
        Intent intent = new Intent();
        intent.putExtra("preSaleDetailBean",preSaleDetailBean);
        intent.putExtra("skuId",skuId);
        intent.setClass(this, PreSaleDetailActivity.class);
        startActivity(intent);
    }



    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String productId = smClList.get(position).getIn_id();
        String skuId = smClList.get(position).getIn_cpid();
        String cl_jiner = smClList.get(position).getIn_prix();
        preSalePresenter.getPreSaleDetailInfo(productId,skuId,cl_jiner);
    }


    //
    public class ThreeLevelCompleteClickListener implements ThreeLevelCompletedListener {

        @Override
        public void getSelectionProvinceCityCounty(String provinceId, String cityId) {
            Toast.makeText(PreSale.this, "搜索地区id为：" + provinceId + "   省的id为：" + cityId, Toast.LENGTH_SHORT).show();
        }
    }

    private void initData() {
        TransactionTypeBean transactionTypeBean1 = new TransactionTypeBean();
        transactionTypeBean1.setCode("fixedProduct");
        transactionTypeBean1.setName("现货");
        TransactionTypeBean transactionTypeBean2 = new TransactionTypeBean();
        transactionTypeBean2.setCode("forward-pricing-product");
        transactionTypeBean2.setName("预售");
        List<TransactionTypeBean> list = new ArrayList<>();
        list.add(transactionTypeBean1);
        list.add(transactionTypeBean2);
        for (int i = 0; i < list.size(); i++) {
            datas.add(list.get(i).getName());
        }

        classification1 = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            classification1.add("分类" + i);
        }
        application1 = new ArrayList<>();
        for (int i = 0; i < 40; i++) {
            application1.add("应用" + i);
        }
        gride = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            gride.add("牌号" + i);
        }
        lists.add(datas);
        lists.add(classification1);
        lists.add(application1);
        lists.add(gride);

    }

    public class PagerOneGoodsInStockActivityListPullListener implements PullToRefreshLayout.OnPullListener {
        private PreSalePresenter preSalePresenter;
        private String title;
        String pageNum = "1";
        int num = Integer.parseInt(pageNum);

        //        List<GoodsInStockActivityBean.InBean> smClList;
        public PagerOneGoodsInStockActivityListPullListener(/*List<GoodsInStockActivityBean.InBean> smClList,*/ PreSalePresenter preSalePresenter, String title) {
            this.preSalePresenter = preSalePresenter;
            this.title = title;
//            this.smClList = smClList;
        }

        @Override
        public void onRefresh(final PullToRefreshLayout pullToRefreshLayout) {
            if (title.equals("现货")) {

                String pattern = "fixedProduct";
                String regions = null;
                String provinces = null;
                String cities = null;
                String categoryNames = null;
                String appNames = null;
                String gradeNums = null;
                preSalePresenter.getPreSaleInfo(pattern, regions, provinces, cities,
                        categoryNames, appNames, gradeNums, pageNum, "update");
            }


            // 千万别忘了告诉控件刷新完毕了哦！
            pullToRefreshLayout.refreshFinish(PullToRefreshLayout.SUCCEED);
        }

        @Override
        public void onLoadMore(final PullToRefreshLayout pullToRefreshLayout) {
            new Handler() {
                @Override
                public void handleMessage(Message msg) {
                    if (title.equals("现货")) {
                        String pattern = "fixedProduct";
                        String regions = null;
                        String provinces = null;
                        String cities = null;
                        String categoryNames = null;
                        String appNames = null;
                        String gradeNums = null;
                        String updateOrLoad = "load";
                        num++;
                        String pageNum = num + "";
                        preSalePresenter.getPreSaleInfo(pattern, regions, provinces, cities,
                                categoryNames, appNames, gradeNums, pageNum, updateOrLoad);
                    }
                    // 千万别忘了告诉控件加载完毕了哦！
                    pullToRefreshLayout.loadmoreFinish(PullToRefreshLayout.SUCCEED);
                }
            }.sendEmptyMessageDelayed(0, 1000);
        }

    }

}

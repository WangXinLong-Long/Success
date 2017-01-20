package com.silianchuangye.sumao.success.fragments.homepage.goodInStock.GoodsInStockActivityMVP.view;

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
import com.silianchuangye.sumao.success.fragments.PagerOneMVP.bean.ApplicationBean;
import com.silianchuangye.sumao.success.fragments.PagerOneMVP.bean.ClassificationBean;
import com.silianchuangye.sumao.success.fragments.PagerOneMVP.bean.SupplierBean;
import com.silianchuangye.sumao.success.fragments.homepage.UpstreamDirectSellingMVP.UpstreamDirectDetailMVP.bean.DirectSellingBean;
import com.silianchuangye.sumao.success.fragments.homepage.UpstreamDirectSellingMVP.UpstreamDirectDetailMVP.bean.ProductItemBean;
import com.silianchuangye.sumao.success.fragments.homepage.goodInStock.GoodsInStockActivityMVP.bean.GoodsInStockActivityBean;
import com.silianchuangye.sumao.success.fragments.homepage.goodInStock.GoodsInStockActivityMVP.bean.TransactionTypeBean;
import com.silianchuangye.sumao.success.fragments.homepage.goodInStock.GoodsInStockActivityMVP.presenter.GoodsInStockActivityPresenter;
import com.silianchuangye.sumao.success.fragments.homepage.goodInStock.goodsInStockDetail.GoodsInStockDetailActivityMVP.bean.GoodsInStockDetailBean;
import com.silianchuangye.sumao.success.fragments.homepage.goodInStock.goodsInStockDetail.GoodsInStockDetailActivityMVP.view.GoodsInStockDetailActivity;
import com.silianchuangye.sumao.success.fragments.homepage.pagerOneGridViewDetailActivity.CustomPopupWindow.MultiselectPopupWindow;
import com.silianchuangye.sumao.success.fragments.homepage.pagerOneGridViewDetailActivity.CustomPopupWindow.MultiselectPopupWindowCompleteClickListener;
import com.silianchuangye.sumao.success.fragments.homepage.pagerOneGridViewDetailActivity.screenPopupWindow.ScreenMultiselectPopupWindowCompleteClickListener;
import com.silianchuangye.sumao.success.fragments.homepage.pagerOneGridViewDetailActivity.screenPopupWindow.ScreenPopupWindow;
import com.silianchuangye.sumao.success.fragments.homepage.preSale.PreSaleDetailActivityMVP.bean.PreSaleDetailBean;
import com.silianchuangye.sumao.success.fragments.homepage.preSale.PreSaleDetailActivityMVP.view.PreSaleDetailActivity;
import com.silianchuangye.sumao.success.utils.LogUtils;
import com.silianchuangye.sumao.success.utils.ThreeLevelLindage.Area;
import com.silianchuangye.sumao.success.utils.ThreeLevelLindage.CascadingMenuPopWindow;
import com.silianchuangye.sumao.success.utils.ThreeLevelLindage.CascadingMenuViewOnSelectListener;
import com.silianchuangye.sumao.success.utils.ThreeLevelLindage.DBHelper;
import com.silianchuangye.sumao.success.utils.ThreeLevelLindage.ThreeLevelCompletedListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jobs on 2016/5/20 0020.
 */
public class GoodsInStockActivity extends Activity implements View.OnClickListener, AdapterView.OnItemClickListener, IGoodsInStockActivityView {

    private RelativeLayout supplierPatterns;
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
    private GoodsInStockActivityPresenter goodsInStockActivityPresenter;
    private ApplicationBean applicationBean;
    private ClassificationBean classificationBean;
    String pattern = "fixedProduct";//交易模式
    String regions = null;//    大区
    String provinces = null;//省
    String cities = null;//市
    String categoryNames = null;//分类
    String appNames = null;//应用
    String gradeNums = null;//牌号
    String groups = null;//牌号
    private TextView supplier_patterns_tv;//供应商
    private TextView region_tv;//地区
    private TextView classification_tv;//分类
    private TextView application_tv;//应用
    private ArrayList<String> supplier1;
    private SupplierBean supplierBean;
    private RelativeLayout selection_condition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pager_one_grid_view_detail_activity);
        provinceList = dbHelper.getProvince();
        goodsInStockActivityPresenter = new GoodsInStockActivityPresenter(this);
        selection_condition = ((RelativeLayout) findViewById(R.id.selection_condition));
//        供应商
        supplierPatterns = ((RelativeLayout) findViewById(R.id.supplier_patterns_rl));
        supplier_patterns_tv = ((TextView) findViewById(R.id.supplier_patterns_tv));
        supplierPatterns.setOnClickListener(this);
//        地区
        region = ((RelativeLayout) findViewById(R.id.region_rl));
        region_tv = ((TextView) findViewById(R.id.region_tv));
        region.setOnClickListener(this);
//        分类
        classification = ((RelativeLayout) findViewById(R.id.classification_rl));
        classification_tv = ((TextView) findViewById(R.id.classification_tv));
        classification.setOnClickListener(this);
//        应用
        application = ((RelativeLayout) findViewById(R.id.application_rl));
        application_tv = ((TextView) findViewById(R.id.application_tv));
        application.setOnClickListener(this);

//        筛选
        screen = ((ImageView) findViewById(R.id.iv_screen_title_bar_search));
        screen.setVisibility(View.VISIBLE);
        screen.setOnClickListener(this);

//

//        获取标题
        tv_screen_title_bar_title = ((TextView) findViewById(R.id.tv_screen_title_bar_title));
        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        if (title.contains("分类") || title.contains("地区") || title.contains("应用") || title.contains("供应")) {
            tv_screen_title_bar_title.setText(title.substring(2, title.length()));
            if (title.contains("分类")) {
                classification_tv.setText("交易模式");
            } else if (title.contains("地区")) {
                region_tv.setText("交易模式");
            } else if (title.contains("应用")) {
                application_tv.setText("交易模式");
            } else if (title.contains("供应")) {
                supplier_patterns_tv.setText("交易模式");
            }
        } else {
            tv_screen_title_bar_title.setText(title);
        }


        prl = ((PullToRefreshLayout) findViewById(R.id.refresh_view));

        pre_sale_listView = ((ListView) prl.getPullableView());
        LogUtils.log("标题是--->"+title);
        if (title.equals("现货") || title.equals("预售")|| title.contains("分类")|| title.contains("应用")|| title.contains("供应")) {
            GoodsInStockActivityBean goodsInStockActivityBean = (GoodsInStockActivityBean) intent.getSerializableExtra("goodsInStockActivityBean");
            applicationBean = (ApplicationBean) intent.getSerializableExtra("applicationBean");
            classificationBean = ((ClassificationBean) intent.getSerializableExtra("classificationBean"));
            supplierBean = ((SupplierBean) intent.getSerializableExtra("supplierBean"));
            smClList.addAll(goodsInStockActivityBean.getIn());
            LogUtils.log(smClList.size() + "--------------WXL");
            adapter = new PreSaleAdapter(this);
            pre_sale_listView.setAdapter(adapter);
            adapter.addData(smClList);
            adapter.notifyDataSetChanged();
            initData();
        } else {
            selection_condition.setVisibility(View.GONE);
            smClList = new ArrayList<GoodsInStockActivityBean.InBean>();
            DirectSellingBean directSellingBean = ((DirectSellingBean) intent.getSerializableExtra("directSellingBean"));
            List<ProductItemBean> productItem = directSellingBean.getProductItem();
            for (int i = 0; i < productItem.size(); i++) {
                GoodsInStockActivityBean.InBean smCl = new GoodsInStockActivityBean.InBean();
                smCl.setIn_leixing(productItem.get(i).getType());
                smCl.setIn_id(productItem.get(i).getProductId());
                smCl.setIn_cangku(productItem.get(i).getWareHouse());
                smCl.setIn_cpid(productItem.get(i).getSkuId());
//                TODO 差价格参数、刷新出来的是现货的数据
                smCl.setIn_prix(productItem.get(i).getProductId());
                smCl.setIn_name(productItem.get(i).getProductName());
                smCl.setIn_qiye(productItem.get(i).getSalerEnterpriseName());
                smCl.setIn_num(productItem.get(i).getQuantity());
                smClList.add(smCl);
            }
            adapter = new PreSaleAdapter(this);
            pre_sale_listView.setAdapter(adapter);
            adapter.addData(smClList);
        }
        //        上啦加载，下拉刷新的监听
        prl.setOnPullListener(new PagerOneGoodsInStockActivityListPullListener(/*goodsInStockActivityPresenter, pattern, regions, provinces, cities,
                categoryNames, appNames, gradeNums*/));
        pre_sale_listView.setOnItemClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
//            供应商
            case R.id.supplier_patterns_rl:
//                这里填写类型（交易模式、分类、应用、牌号）
                if (supplier_patterns_tv.getText().toString().equals("交易模式")) {
                    showTradingPatternPopupWindow("trading", datas);
                } else {
                    showTradingPatternPopupWindow("supplier", supplier1);
                }

                break;
//            地区
            case R.id.region_rl:
                if (region_tv.getText().toString().equals("交易模式")) {
                    showTradingPatternPopupWindow("trading", datas);
                } else {
                    showPopMenu();
                }

                break;
//            分类
            case R.id.classification_rl:
                if (classification_tv.getText().toString().equals("交易模式")) {
                    showTradingPatternPopupWindow("trading", datas);
                } else {
                    showTradingPatternPopupWindow("classification", classification1);
                }

                break;
//            应用
            case R.id.application_rl:
                if (application_tv.getText().toString().equals("交易模式")) {
                    showTradingPatternPopupWindow("trading", datas);
                } else {
                    showTradingPatternPopupWindow("application", application1);
                }
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
            multiselectPopupWindow.showAsDropDown(supplierPatterns, 5, 5);
        } else if (multiselectPopupWindow != null && multiselectPopupWindow.isShowing()) {
            multiselectPopupWindow.dismiss();
        } else {
            multiselectPopupWindow.showAsDropDown(supplierPatterns, 5, 5);
        }
    }

    //    筛选的PopupWindow
    private void screenPopupWindow(ArrayList<ArrayList<String>> lists) {
        ScreenPopupWindow screenPopupWindow = null;
        if (screenPopupWindow == null) {
            screenPopupWindow = new ScreenPopupWindow(getApplicationContext(), lists);
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
            regions = provinceId;
            provinces = cityId;
            cities = countyId;
            goodsInStockActivityPresenter.getGoodsInStockInfo(groups, pattern, regions, provinces, cities,
                    categoryNames, appNames, gradeNums, "1", "update");
        }
    }

    //      点击完成后，
    class CompleteClickListener implements MultiselectPopupWindowCompleteClickListener {

        @Override
        public void getSelectionValues(List<String> selectItems, String type) {

            if (type.equals("trading")) {
                String trading = selectItems.toString();
                StringBuffer sb = new StringBuffer();
                if (trading.contains("现货")) {
                    if (sb.toString().equals("")) {
                        sb.append("fixedProduct");
                    } else {
                        sb.append("&fixedProduct");
                    }
                }
                if (trading.contains("预售")) {
                    if (sb.toString().equals("")) {
                        sb.append("forward-pricing-product");
                    } else {
                        sb.append("&forward-pricing-product");
                    }
                }
                pattern = sb.toString();
                Toast.makeText(GoodsInStockActivity.this, "" + pattern, Toast.LENGTH_SHORT).show();
                goodsInStockActivityPresenter.getGoodsInStockInfo(groups, pattern, regions, provinces, cities,
                        categoryNames, appNames, gradeNums, "1", "update");
            } else if (type.equals("classification")) {
                StringBuffer stringBuffer2 = new StringBuffer();

                for (int i = 0; i < selectItems.size(); i++) {
                    if (i == selectItems.size() - 1) {
                        stringBuffer2.append(selectItems.get(i));
                    } else {
                        stringBuffer2.append(selectItems.get(i));
                        stringBuffer2.append("&");
                    }
                }
                categoryNames = stringBuffer2.toString();
                Toast.makeText(GoodsInStockActivity.this, categoryNames, Toast.LENGTH_SHORT).show();
                goodsInStockActivityPresenter.getGoodsInStockInfo(groups, pattern, regions, provinces, cities,
                        categoryNames, appNames, gradeNums, "1", "update");
            } else if (type.equals("application")) {
                StringBuffer stringBuffer3 = new StringBuffer();

                for (int i = 0; i < selectItems.size(); i++) {
                    if (i == selectItems.size() - 1) {
                        stringBuffer3.append(selectItems.get(i));
                    } else {
                        stringBuffer3.append(selectItems.get(i));
                        stringBuffer3.append("&");
                    }
                }
                appNames = stringBuffer3.toString();
                Toast.makeText(GoodsInStockActivity.this, "" + appNames, Toast.LENGTH_SHORT).show();
                goodsInStockActivityPresenter.getGoodsInStockInfo(groups, pattern, regions, provinces, cities,
                        categoryNames, appNames, gradeNums, "1", "update");
            } else if (type.equals("supplier")) {
                StringBuffer stringBuffer3 = new StringBuffer();

                for (int i = 0; i < selectItems.size(); i++) {
                    if (i == selectItems.size() - 1) {
                        stringBuffer3.append(selectItems.get(i));
                    } else {
                        stringBuffer3.append(selectItems.get(i));
                        stringBuffer3.append("&");
                    }
                }
                groups = stringBuffer3.toString();
                Toast.makeText(GoodsInStockActivity.this, "" + groups, Toast.LENGTH_SHORT).show();
                goodsInStockActivityPresenter.getGoodsInStockInfo(groups, pattern, regions, provinces, cities,
                        categoryNames, appNames, gradeNums, "1", "update");
            }
        }
    }

    //    筛选的完成按钮的动作
    class ScreenCompleteClickListener implements ScreenMultiselectPopupWindowCompleteClickListener {
        @Override
        public void getSelectionValues(List<String> regionSelectItems, List<String> classifcationSelectItems, List<String> applicationSelectItems, String grade) {

            Toast.makeText(GoodsInStockActivity.this, "search" + "地区:" + regionSelectItems + "   分类:" + classifcationSelectItems + "   应用:" + applicationSelectItems, Toast.LENGTH_SHORT).show();
            if (regionSelectItems.size() != 0) {
                StringBuffer stringBuffer2 = new StringBuffer();

                for (int i = 0; i < regionSelectItems.size(); i++) {
                    if (i == regionSelectItems.size() - 1) {
                        stringBuffer2.append(regionSelectItems.get(i));
                    } else {
                        stringBuffer2.append(regionSelectItems.get(i));
                        stringBuffer2.append("&");
                    }
                }
                regions = stringBuffer2.toString();
            }
            if (classifcationSelectItems.size() != 0) {
                StringBuffer stringBuffer2 = new StringBuffer();

                for (int i = 0; i < classifcationSelectItems.size(); i++) {
                    if (i == classifcationSelectItems.size() - 1) {
                        stringBuffer2.append(classifcationSelectItems.get(i));
                    } else {
                        stringBuffer2.append(classifcationSelectItems.get(i));
                        stringBuffer2.append("&");
                    }
                }
                categoryNames = stringBuffer2.toString();
            }
            if (applicationSelectItems.size() != 0) {
                StringBuffer stringBuffer2 = new StringBuffer();

                for (int i = 0; i < applicationSelectItems.size(); i++) {
                    if (i == applicationSelectItems.size() - 1) {
                        stringBuffer2.append(applicationSelectItems.get(i));
                    } else {
                        stringBuffer2.append(applicationSelectItems.get(i));
                        stringBuffer2.append("&");
                    }
                }
                appNames = stringBuffer2.toString();
            }
            gradeNums = grade;
            goodsInStockActivityPresenter.getGoodsInStockInfo(groups, pattern, regions, provinces, cities,
                    categoryNames, appNames, gradeNums, "1", "update");
        }

    }


    //    上啦加载下拉刷新的加载
    @Override
    public void setDataInActivity(GoodsInStockActivityBean goodsInStockActivityBean, String updateOrLoad) {
        if (updateOrLoad.equals("update")) {
            adapter.clearData();
            smClList.clear();
        }
        List<GoodsInStockActivityBean.InBean> in = goodsInStockActivityBean.getIn();
        smClList.addAll(in);
        adapter.addData(in);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void getGoodsInStockDetailData(GoodsInStockDetailBean goodsInStockDetailBean) {
        Intent intent = new Intent();
        intent.putExtra("goodsInStockDetailBean", goodsInStockDetailBean);
        intent.setClass(this, GoodsInStockDetailActivity.class);
        startActivity(intent);
    }

    @Override
    public void getPreSaleDetailData(PreSaleDetailBean preSaleDetailBean, String skuId) {
        Intent intent = new Intent();
        intent.putExtra("preSaleDetailBean", preSaleDetailBean);
        intent.putExtra("skuId", skuId);
        intent.setClass(this, PreSaleDetailActivity.class);
        startActivity(intent);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (smClList.get(position).getIn_leixing().equals("现货")||smClList.get(position).getIn_leixing().equals("fixedProduct")) {
            String cl_id = smClList.get(position).getIn_id();
            Toast.makeText(this, "走现货,id为" + cl_id, Toast.LENGTH_SHORT).show();
            LogUtils.log("走现货,id为" + cl_id);
            goodsInStockActivityPresenter.getGoodInStockDetailInfo(cl_id);
        } else if (smClList.get(position).getIn_leixing().equals("预售")){
            Toast.makeText(this, "走预售", Toast.LENGTH_SHORT).show();
            String productId = smClList.get(position).getIn_id();
            String skuId = smClList.get(position).getIn_cpid();
            String cl_jiner = smClList.get(position).getIn_prix();
            goodsInStockActivityPresenter.getPreSaleDetailInfo(productId, skuId, cl_jiner);
        }

    }


    //
    public class ThreeLevelCompleteClickListener implements ThreeLevelCompletedListener {

        @Override
        public void getSelectionProvinceCityCounty(String provinceId, String cityId) {
            if (cityId.equals("")) {
                cityId = null;
            }

            regions = provinceId;
            provinces = cityId;
            goodsInStockActivityPresenter.getGoodsInStockInfo(groups, pattern, regions, provinces, cities,
                    categoryNames, appNames, gradeNums, "1", "update");

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
        ArrayList<String> region = new ArrayList<>();
        for (int i = 0; i < provinceList.size(); i++) {
            region.add(provinceList.get(i).getName());
        }
        classification1 = new ArrayList<>();
        classification1.addAll(classificationBean.getIn_flname());

        application1 = new ArrayList<>();
        application1.addAll(applicationBean.getIn_yyname());
//      TODO 供应商
        supplier1 = new ArrayList<>();
        supplier1.addAll(supplierBean.getIn_gysname());

        lists.add(region);
        lists.add(classification1);
        lists.add(application1);
        lists.add(supplier1);

    }

    public class PagerOneGoodsInStockActivityListPullListener implements PullToRefreshLayout.OnPullListener {
        String pageNum = "1";


        @Override
        public void onRefresh(final PullToRefreshLayout pullToRefreshLayout) {
            pageNum = "1";
            if (tv_screen_title_bar_title.getText().toString().equals("现货")) {
                pattern = "fixedProduct";
            } else if (tv_screen_title_bar_title.getText().toString().equals("预售")) {
                pattern = "forward-pricing-product";
            }
            goodsInStockActivityPresenter.getGoodsInStockInfo(groups, pattern, regions, provinces, cities,
                    categoryNames, appNames, gradeNums, pageNum, "update");

            // 千万别忘了告诉控件刷新完毕了哦！
            pullToRefreshLayout.refreshFinish(PullToRefreshLayout.SUCCEED);
        }

        @Override
        public void onLoadMore(final PullToRefreshLayout pullToRefreshLayout) {
            if (tv_screen_title_bar_title.getText().toString().equals("现货")) {
                pattern = "fixedProduct";
            } else if (tv_screen_title_bar_title.getText().toString().equals("预售")) {
                pattern = "forward-pricing-product";
            }
            new Handler() {
                @Override
                public void handleMessage(Message msg) {
                    String updateOrLoad = "load";
                    int num = Integer.parseInt(pageNum) + 1;
                    pageNum = num + "";
                    goodsInStockActivityPresenter.getGoodsInStockInfo(groups, pattern, regions, provinces, cities,
                            categoryNames, appNames, gradeNums, pageNum, updateOrLoad);
                    // 千万别忘了告诉控件加载完毕了哦！
                    pullToRefreshLayout.loadmoreFinish(PullToRefreshLayout.SUCCEED);
                }
            }.sendEmptyMessageDelayed(0, 3000);
        }

    }

}
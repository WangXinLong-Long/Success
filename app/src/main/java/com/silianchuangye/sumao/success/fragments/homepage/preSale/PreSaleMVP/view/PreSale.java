package com.silianchuangye.sumao.success.fragments.homepage.preSale.PreSaleMVP.view;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.adapter.PreSaleAdapter;
import com.silianchuangye.sumao.success.fragments.homepage.goodInStock.GoodsInStockActivityMVP.bean.GoodsInStockActivityBean;
import com.silianchuangye.sumao.success.fragments.homepage.goodInStock.GoodsInStockActivityMVP.bean.SMCl;
import com.silianchuangye.sumao.success.fragments.homepage.preSale.PreSaleDetailActivityMVP.view.PreSaleDetailActivity;
import com.silianchuangye.sumao.success.fragments.homepage.preSale.PreSaleMVP.presenter.PreSalePresenter;
import com.silianchuangye.sumao.success.utils.LogUtils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/5/17 0017.
 */
public class PreSale extends Activity implements View.OnClickListener, AdapterView.OnItemClickListener,IPreSaleView {
    TextView tv_screen_title_bar_title;
    ImageView iv_screen_title_bar_search, iv_screen_title_bar_back;
    View popwindowView;
    View listPopupWindowView;
    ListView popupWindowListView;
    LinearLayout bottom_pre_sale_search,popup_window_back;

    List<SMCl> lists;
    SMCl preSaleModel;
    PreSaleAdapter adapter;
    PopupWindow popupWindow;
    PopupWindow listPopupWindow;
    RelativeLayout pre_sale_title;
    TextView classification, application, region;
    //    TextView manufacturing_enterprise;
    PullToRefreshListView pre_sale_listView;
    private List<SMCl> smClList = new ArrayList<>();
    private Handler handler = new Handler();
    RelativeLayout selection_condition;
    TextView split_line;
    private RelativeLayout application1;
    private RelativeLayout classification1;
    private RelativeLayout region1;
    private PreSalePresenter preSalePresenter;
    private String region2 = "";
    private String classification2 = "";
    private String application2 = "";
    private int Nrpp = 10;
    private int No = 0;
    private Map<String, String> jsonArrayClassification;
    private Map<String, String> jsonArrayApplication;
    private Map<String, String> jsonArrayRegion;
    private ArrayList<String> name;
    private ArrayList<String> number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pre_sale);
        tv_screen_title_bar_title = ((TextView) findViewById(R.id.tv_screen_title_bar_title));
        tv_screen_title_bar_title.setText("预售");
        iv_screen_title_bar_search = ((ImageView) findViewById(R.id.iv_screen_title_bar_search));
        iv_screen_title_bar_back = ((ImageView) findViewById(R.id.iv_screen_title_bar_back));
        iv_screen_title_bar_search.setOnClickListener(this);
        iv_screen_title_bar_back.setOnClickListener(this);
        pre_sale_title = ((RelativeLayout) findViewById(R.id.pre_sale_title));
        classification = ((TextView) findViewById(R.id.classification));
        application = ((TextView) findViewById(R.id.application));
        region = ((TextView) findViewById(R.id.region));
        application1 = ((RelativeLayout) findViewById(R.id.application1));
        classification1 = ((RelativeLayout) findViewById(R.id.classification1));
        region1 = ((RelativeLayout) findViewById(R.id.region1));
//        manufacturing_enterprise = ((TextView) findViewById(R.id.manufacturing_enterprise));
        pre_sale_listView = ((PullToRefreshListView) findViewById(R.id.pre_sale_listView));
        selection_condition = ((RelativeLayout) findViewById(R.id.selection_condition));
        split_line = ((TextView) findViewById(R.id.split_line));
        pre_sale_listView.setOnItemClickListener(this);
        application1.setOnClickListener(this);
        classification1.setOnClickListener(this);
        region1.setOnClickListener(this);
        preSalePresenter = new PreSalePresenter(this);
        preSalePresenter.getPreSaleInfo(region2, classification2, application2, Nrpp, No);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_screen_title_bar_search:
                initView();
                showSearchDialog();
                break;
            //            点击地区的点击事件
            case R.id.region1:
                if (jsonArrayRegion!=null){
                    //                初始化数据
                    initListPopupWindowView("地区");
                    //                显示listView
                    showListPopupWindow();
                }else {
                    Toast.makeText(this,"暂无其他地区",Toast.LENGTH_SHORT).show();
                }
                break;
//            点击分类的点击事件
            case R.id.classification1:
                if (jsonArrayClassification!=null){
                    //                初始化数据
                    initListPopupWindowView("分类");
                    //                显示listView
                    showListPopupWindow();
                }else {
                    Toast.makeText(this,"暂无其他分类",Toast.LENGTH_SHORT).show();
                }
                break;
//            点击应用的点击事件
            case R.id.application1:
                if (jsonArrayApplication!=null){
                    //                初始化数据
                    initListPopupWindowView("应用");
//                显示listView
                    showListPopupWindow();
                }else {
                    Toast.makeText(this,"暂无其他应用",Toast.LENGTH_SHORT).show();
                }
                break;
         /*   case R.id.manufacturing_enterprise:
                initListPopupWindowView("生产企业");
                showListPopupWindow();
                break;*/

            case R.id.bottom_pre_sale_search:
                popupWindow.dismiss();
                break;
            case R.id.popup_window_back:
                listPopupWindow.dismiss();
                break;
            case R.id.iv_screen_title_bar_back:
                finish();
                break;

            default:
                break;
        }

    }

    private void showListPopupWindow() {
        listPopupWindowView.measure(0, 0);
        int width = getWindowManager().getDefaultDisplay().getWidth();
        listPopupWindow = new PopupWindow(listPopupWindowView, width,
                listPopupWindowView.getMeasuredHeight());
        listPopupWindow.setFocusable(true);
        listPopupWindow.setBackgroundDrawable(new BitmapDrawable());
        listPopupWindow.showAsDropDown(split_line, 0, 0);
    }

    private void initListPopupWindowView(final String s) {
        listPopupWindowView = View.inflate(this, R.layout.list_popup_window_view, null);
        popupWindowListView = (ListView) listPopupWindowView.findViewById(R.id.popup_window_list_view);
        popup_window_back = ((LinearLayout) listPopupWindowView.findViewById(R.id.popup_window_back));
        popup_window_back.setOnClickListener(this);
        final ArrayAdapter<String> popupWindowListViewAdpter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, getData(s));
        popupWindowListView.setAdapter(popupWindowListViewAdpter);
        popupWindowListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if (s.equals("地区")) {
                    region.setText(popupWindowListViewAdpter.getItem(position));
                    region2 = number.get(position);
                    preSalePresenter.getPreSaleInfo(region2, classification2, application2, Nrpp, No);
                } else if (s.equals("分类")) {
                    classification.setText(popupWindowListViewAdpter.getItem(position));
                    classification2 = number.get(position);
                    preSalePresenter.getPreSaleInfo(region2, classification2, application2, Nrpp, No);
                } else if (s.equals("应用")) {
                    application.setText(popupWindowListViewAdpter.getItem(position));
                    application2 = number.get(position);
                    preSalePresenter.getPreSaleInfo(region2, classification2, application2, Nrpp, No);
                }/*else if (s.equals("生产企业"))
                {
                    manufacturing_enterprise.setText(popupWindowListViewAdpter.getItem(position));
                }*/
                    listPopupWindow.dismiss();
            }
        });

    }

    //为弹出的框添加数据   getData(s)
    private ArrayList<String> getData(String s) {
        if (s.equals("分类")) {
            if (name != null || number != null) {
                number.clear();
                name.clear();

                Iterator<Map.Entry<String, String>> iterator = jsonArrayClassification.entrySet().iterator();
                while (iterator.hasNext()) {
                    Map.Entry<String, String> entry = iterator.next();
                    String key = entry.getKey();
                    LogUtils.log("现货：" + key);
                    String value = entry.getValue();
                    LogUtils.log("现货：" + value);
                    name.add(key.toString());
                    number.add(value.toString());
                }
            }
        } else if (s.equals("应用")) {
            if (name != null || number != null) {
                number.clear();
                name.clear();

                Iterator<Map.Entry<String, String>> iterator = jsonArrayApplication.entrySet().iterator();
                while (iterator.hasNext()) {
                    Map.Entry<String, String> entry = iterator.next();
                    String key = entry.getKey();
                    String value = entry.getValue();
                    name.add(key);
                    number.add(value);
                }
            }
        } else if (s.equals("地区")) {
            if (name != null || number != null) {
                number.clear();
                name.clear();

                Iterator<Map.Entry<String, String>> iterator = jsonArrayRegion.entrySet().iterator();
                while (iterator.hasNext()) {
                    Map.Entry<String, String> entry = iterator.next();
                    String key = entry.getKey();
                    String value = entry.getValue();
                    name.add(key);
                    number.add(value);
                }
            }
        }
        return name;
    }

    private void initView() {
        popwindowView = View.inflate(this, R.layout.pre_sale_search, null);
        bottom_pre_sale_search = ((LinearLayout) popwindowView.findViewById(R.id.bottom_pre_sale_search));
        bottom_pre_sale_search.setOnClickListener(this);
    }

    private void showSearchDialog() {
        popwindowView.measure(0, 0);
        int width = getWindowManager().getDefaultDisplay().getWidth();
        popupWindow = new PopupWindow(popwindowView, width, popwindowView.getMeasuredHeight());
        popupWindow.setFocusable(true);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.showAsDropDown(pre_sale_title, 0, 0);
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Intent intent = new Intent();
//        产品编号
             intent.putExtra("productId",smClList.get(position-1).getCl_id());
//        skuId
            intent.putExtra("skuId",smClList.get(position-1).getCl_cpid());
            intent.setClass(this, PreSaleDetailActivity.class);
            startActivity(intent);
    }

    @Override
    public void setDataInActivity(GoodsInStockActivityBean goodsInStockActivityBean) {
        jsonArrayClassification = goodsInStockActivityBean.getSort();
        jsonArrayApplication = goodsInStockActivityBean.getApplication();
        jsonArrayRegion = goodsInStockActivityBean.getRegion();
        LogUtils.log("jsonArrayClassification-->" + jsonArrayClassification);
        LogUtils.log("jsonArrayApplication-->" + jsonArrayApplication);
        LogUtils.log("jsonArrayRegion-->" + jsonArrayRegion);
        List<SMCl> list = smClList;
        smClList = goodsInStockActivityBean.getCl();
        list.addAll(smClList);
        smClList = list;
        if (smClList == null) {
            Toast.makeText(PreSale.this, "已经到底了", Toast.LENGTH_SHORT).show();
        } else {
            adapter = new PreSaleAdapter(this, smClList);
            pre_sale_listView.setAdapter(adapter);
            pre_sale_listView.setMode(PullToRefreshBase.Mode.BOTH);
            refreshListener();

        }
    }
    //    上拉刷新，下拉加载的监听
    private void refreshListener() {
        pre_sale_listView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(1000);
                            if (smClList != null)
                                smClList.clear();
                            preSalePresenter.getPreSaleInfo(region2, classification2, application2, 10, 0);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        handler.post(new Runnable() {
                            @Override
                            public void run() {

                                adapter.notifyDataSetChanged();
                                pre_sale_listView.onRefreshComplete();
                            }
                        });
                    }
                }).start();

            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            preSalePresenter.getPreSaleInfo(region2, classification2, application2, Nrpp, No + Nrpp);
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        handler.post(new Runnable() {
                            @Override
                            public void run() {

                                adapter.notifyDataSetChanged();
                                pre_sale_listView.onRefreshComplete();
                            }
                        });
                    }
                }).start();


            }
        });
    }


}

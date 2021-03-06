package com.silianchuangye.sumao.success.fragments.homepage.preSale.PreSaleMVP.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jingchen.pulltorefresh.PullToRefreshLayout;
import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.adapter.PreSaleAdapter;
import com.silianchuangye.sumao.success.custom.customCalendar.CustomGridView;
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
    ListView pre_sale_listView;
    private List<SMCl> smClList = new ArrayList<>();
    List<SMCl> sList = new ArrayList<>();
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
    private PullToRefreshLayout ptrl;
    private RelativeLayout screen1;
    private GoodsInStockActivityBean goodsInStockActivityBean;
    private String total;
    private View view;
    private List<String> name1 = new ArrayList<String>();
    ;
    private List<String> name2 = new ArrayList<String>();
    private List<String> name3 = new ArrayList<String>();
    private List<String> number1 = new ArrayList<String>();
    private List<String> number2 = new ArrayList<String>();
    private List<String> number3 = new ArrayList<String>();
    private GridViewAdapter adpater3, adpater2, adpater1, adpater4;
    private String address = "";
    private String  yingyong = "";
    private String fenlei = "";
    private String regionString = "";
    private String classificationString = "";
    private String applicationString = "";
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
        screen1 = ((RelativeLayout) findViewById(R.id.screen1));
//        manufacturing_enterprise = ((TextView) findViewById(R.id.manufacturing_enterprise));
        ptrl = ((PullToRefreshLayout) findViewById(R.id.refresh_view));
        ptrl.setOnPullListener(new MyPullToRefreshListener());
        pre_sale_listView = ((ListView) findViewById(R.id.pre_sale_listView));
        selection_condition = ((RelativeLayout) findViewById(R.id.selection_condition));
        split_line = ((TextView) findViewById(R.id.split_line));
        pre_sale_listView.setOnItemClickListener(this);
        application1.setOnClickListener(this);
        classification1.setOnClickListener(this);
        region1.setOnClickListener(this);
        screen1.setOnClickListener(this);
        Intent intent = getIntent();
        goodsInStockActivityBean = (GoodsInStockActivityBean)intent.getSerializableExtra("preSaleActivityBean");
        jsonArrayClassification = goodsInStockActivityBean.getSort();
        jsonArrayApplication = goodsInStockActivityBean.getApplication();
        jsonArrayRegion = goodsInStockActivityBean.getRegion();
        smClList = goodsInStockActivityBean.getCl();
        total = goodsInStockActivityBean.getTotal();
        adapter = new PreSaleAdapter(this, smClList);
        preSalePresenter = new PreSalePresenter(this);
        pre_sale_listView.setAdapter(adapter);
        name = new ArrayList<>();
        number = new ArrayList<>();
//        preSalePresenter.getPreSaleInfo(region2, classification2, application2, Nrpp, No);
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
            case R.id.screen1:
                if (total.equals("0")) {
                    Toast.makeText(PreSale.this, "没有可供筛选的条件", Toast.LENGTH_LONG).show();
                } else {
                    popupwindow_shaixuan();
                    init_popupwindowView();
                }
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
//        mArrayList = new ArrayList<String>();
        final ArrayAdapter<String> popupWindowListViewAdpter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, getData(s));
        popupWindowListView.setAdapter(popupWindowListViewAdpter);
        popupWindowListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if (s.equals("地区")) {
                    region.setText(popupWindowListViewAdpter.getItem(position));
                    region2 = number.get(position);
                    sList.clear();
                    smClList.clear();
                    Nrpp = 10;
                    No =  0;
                    preSalePresenter.getPreSaleInfo(region2, classification2, application2, Nrpp, No);
                } else if (s.equals("分类")) {
                    classification.setText(popupWindowListViewAdpter.getItem(position));
                    classification2 = number.get(position);
                    sList.clear();
                    smClList.clear();
                    Nrpp = 10;
                    No =  0;
                    preSalePresenter.getPreSaleInfo(region2, classification2, application2, Nrpp, No);
                } else if (s.equals("应用")) {
                    application.setText(popupWindowListViewAdpter.getItem(position));
                    application2 = number.get(position);
                    sList.clear();
                    smClList.clear();
                    Nrpp = 10;
                    No =  0;
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
             intent.putExtra("productId",smClList.get(position).getCl_id());
//        skuId
            intent.putExtra("skuId",smClList.get(position).getCl_cpid());
            intent.putExtra("cl_jiner",smClList.get(position).getCl_jine());
        Log.e("TAG","上级skuId---------"+smClList.get(position).getCl_id());
        Log.e("TAG","上级cl_jiner---------"+smClList.get(position).getCl_cpid());
            intent.setClass(this, PreSaleDetailActivity.class);
            startActivity(intent);
    }

    @Override
    public void setDataInActivity(GoodsInStockActivityBean goodsInStockActivityBean) {
        total = goodsInStockActivityBean.getTotal();
        if (total.equals("0")){
            smClList.clear();
            sList.clear();
            adapter.notifyDataSetChanged();
        }else {
            jsonArrayClassification = goodsInStockActivityBean.getSort();
            jsonArrayApplication = goodsInStockActivityBean.getApplication();
            jsonArrayRegion = goodsInStockActivityBean.getRegion();
            LogUtils.log("jsonArrayClassification-->" + jsonArrayClassification);
            LogUtils.log("jsonArrayApplication-->" + jsonArrayApplication);
            LogUtils.log("jsonArrayRegion-->" + jsonArrayRegion);
            smClList.addAll(goodsInStockActivityBean.getCl());
            if (smClList == null || smClList.size() == 0) {
                Toast.makeText(PreSale.this, "已经到底了", Toast.LENGTH_SHORT).show();
            } else {
                adapter.notifyDataSetChanged();
            }
        }
    }
    //    上拉刷新，下拉加载的监听

    //    上拉刷新，下拉加载的监听
    private class MyPullToRefreshListener implements PullToRefreshLayout.OnPullListener {
        @Override
        public void onRefresh(final PullToRefreshLayout pullToRefreshLayout) {
            new Handler(){
                @Override
                public void handleMessage(Message msg) {
                    super.handleMessage(msg);

                        smClList.clear();

                    preSalePresenter.getPreSaleInfo(region2, classification2, application2, 10, 0);
                    pullToRefreshLayout.refreshFinish(PullToRefreshLayout.SUCCEED);
                    adapter.notifyDataSetChanged();
                }

            }.sendEmptyMessageDelayed(0,1000);




        }

        @Override
        public void onLoadMore(final PullToRefreshLayout pullToRefreshLayout) {
            new Handler(){
                @Override
                public void handleMessage(Message msg) {
                    super.handleMessage(msg);

                    preSalePresenter.getPreSaleInfo(region2, classification2, application2, Nrpp, No + Nrpp);
                    adapter.notifyDataSetChanged();
                    pullToRefreshLayout.refreshFinish(PullToRefreshLayout.SUCCEED);

                }
            }.sendEmptyMessageDelayed(0,1000);
        }
    }

    /**
     * 筛选的popupwindow
     */
    public void popupwindow_shaixuan() {

        view = View.inflate(this, R.layout.item_shaixuan, null);

        final CustomGridView gv_Apple_Type = (CustomGridView) view.findViewById(R.id.gv_Apple_Type);
        if (jsonArrayClassification != null) {
            number1.clear();
            name1.clear();

            Iterator<Map.Entry<String, String>> iterator = jsonArrayClassification.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, String> entry = iterator.next();
                String key = entry.getKey();
                String value = entry.getValue();
                name1.add(key.toString());
                number1.add(value.toString());
            }

        }else{
            number1.clear();
            name1.clear();
        }

        adpater4 = new GridViewAdapter(name1);
        gv_Apple_Type.setAdapter(adpater4);
        gv_Apple_Type.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                adpater4.setSelectedPosition(position);
                adpater4.notifyDataSetChanged();
                fenlei = name1.get(position).toString();
                Log.d("分类", fenlei);
            }
        });

        /**
         * 应用
         */
        final CustomGridView gv_Address_Type = (CustomGridView) view.findViewById(R.id.gv_Address_Type);
        if (jsonArrayApplication != null) {
            number2.clear();
            name2.clear();

            Iterator<Map.Entry<String, String>> iterator = jsonArrayApplication.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, String> entry = iterator.next();
                String key = entry.getKey();
                String value = entry.getValue();
                name2.add(key);
                number2.add(value);
            }
        }else{
            number2.clear();
            name2.clear();
        }
        adpater2 = new GridViewAdapter(name2);
        gv_Address_Type.setAdapter(adpater2);
        gv_Address_Type.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                adpater2.setSelectedPosition(position);
                adpater2.notifyDataSetChanged();
                yingyong = name2.get(position).toString();
            }
        });
        /**
         * 地区
         */
        final CustomGridView gv_Firm_Type = (CustomGridView) view.findViewById(R.id.gv_Firm_Type);
        if (jsonArrayRegion != null) {
            number3.clear();
            name3.clear();

            Iterator<Map.Entry<String, String>> iterator = jsonArrayRegion.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, String> entry = iterator.next();
                String key = entry.getKey();
                String value = entry.getValue();
                name3.add(key);
                number3.add(value);
            }
        }else{
            number3.clear();
            name3.clear();
        }
        adpater3 = new GridViewAdapter(name3);
        gv_Firm_Type.setAdapter(adpater3);
        gv_Firm_Type.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                adpater3.setSelectedPosition(position);
                adpater3.notifyDataSetInvalidated();
                address = name3.get(position).toString();

            }
        });

        /**
         * 重置按钮
         */
        Button bt_reset = (Button) view.findViewById(R.id.bt_reset);
        bt_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("令所选的为空", "null");
//                adpater1.setSelectedPosition(-1);
//                adpater1.notifyDataSetChanged();
                adpater2.setSelectedPosition(-1);
                adpater2.notifyDataSetChanged();
                adpater3.setSelectedPosition(-1);
                adpater3.notifyDataSetChanged();
                adpater4.setSelectedPosition(-1);
                adpater4.notifyDataSetChanged();
                Log.d("令所选的为空", address + fenlei + yingyong );
            }
        });
        /**
         * 完成按钮
         */
        Button bt_finish = (Button) view.findViewById(R.id.bt_finish);
        bt_finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Log.d("根据所选的进行筛选", "");
//                地区、分类、应用、交易模式
                if (address == null || address.isEmpty()) {
                    regionString = "";
                } else {
                    if (!(null==jsonArrayRegion.get(address))){

                        regionString = jsonArrayRegion.get(address);
                    }
                }
                if (fenlei == null || fenlei.isEmpty()) {
                    classificationString = "";
                } else {
                    if (!(null==jsonArrayClassification.get(fenlei))){

                        classificationString = jsonArrayClassification.get(fenlei);
                    }
                }
                if (yingyong == null || yingyong.isEmpty()) {
                    applicationString = "";
                } else {
                    if (!(null==jsonArrayApplication.get(yingyong))){

                        applicationString = jsonArrayApplication.get(yingyong);
                    }
                }

                if (smClList != null) {
                    sList.clear();
                    smClList.clear();
//                    smClList.clear();
                }
                Nrpp = 10;
                No = 0;
                preSalePresenter.getPreSaleInfo(regionString, classificationString, applicationString, Nrpp, No);

                LogUtils.log("参数-->" + regionString + classificationString + applicationString );
                popupWindow.dismiss();
            }

        });


    }

    private void init_popupwindowView() {
        view.measure(0, 0);
        int width = getWindowManager().getDefaultDisplay().getWidth();
        int heigh = view.getMeasuredHeight() > getWindowManager().getDefaultDisplay().getHeight() ? getWindowManager().getDefaultDisplay().getHeight() : view.getMeasuredHeight();
        popupWindow = new PopupWindow(view, width, 1600);
        popupWindow.setFocusable(true);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.showAsDropDown(split_line, 0, 0);

    }

    class GridViewAdapter extends BaseAdapter {
        private List<String> list_String;
        private int selectedPosition = -1;
        LayoutInflater inflater;
        View view;
        ViewHolder Layoutholder;
        RelativeLayout Layout;
        TextView text = null;

        public GridViewAdapter(List<String> list) {
            this.list_String = list;
        }

        @Override
        public int getCount() {
            return list_String.size();
        }

        @Override
        public String getItem(int position) {
            return list_String.get(position);
        }


        @Override
        public long getItemId(int position) {
            return position;
        }

        public void setSelectedPosition(int selectedPosition) {
            this.selectedPosition = selectedPosition;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            inflater = (LayoutInflater) getApplication().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item_type, parent, false);
            Layoutholder = (ViewHolder) view.getTag();
            if (Layoutholder == null) {
                Layoutholder = new ViewHolder();
                Layoutholder.layout_Type = (RelativeLayout) view.findViewById(R.id.layout);
                Layoutholder.text_Name = (TextView) view.findViewById(R.id.tv_Type);
                view.setTag(Layoutholder);

            } else {
                Layoutholder = (ViewHolder) view.getTag();
            }
            Layout = Layoutholder.layout_Type;
            text = Layoutholder.text_Name;
            if (selectedPosition == position) {
                text.setSelected(true);
                text.setPressed(true);
                // Layout.setBackgroundColor(getResources().getColor(R.color.zixun_topbg));
                Layout.setBackgroundDrawable(getResources().getDrawable(R.drawable.text_corner));
                text.setTextColor(Color.BLACK);
            } else {
                text.setSelected(false);
                text.setPressed(false);
                Layout.setBackgroundColor(Color.WHITE);
                text.setTextColor(Color.BLACK);

            }


            text.setText(list_String.get(position).toString());

            return view;
        }
    }

    class ViewHolder {
        TextView text_Name;
        RelativeLayout layout_Type;

    }

}

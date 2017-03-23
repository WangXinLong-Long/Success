package com.silianchuangye.sumao.success.fragments.homepage.goodInStock.GoodsInStockActivityMVP.view;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.ActionBarOverlayLayout;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
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
import com.silianchuangye.sumao.success.utils.SuMaoConstant;
import com.silianchuangye.sumao.success.utils.ThreeLevelLindage.Area;
import com.silianchuangye.sumao.success.utils.ThreeLevelLindage.CascadingMenuPopWindow;
import com.silianchuangye.sumao.success.utils.ThreeLevelLindage.CascadingMenuViewOnSelectListener;
import com.silianchuangye.sumao.success.utils.ThreeLevelLindage.DBHelper;
import com.silianchuangye.sumao.success.utils.ThreeLevelLindage.ThreeLevelCompletedListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

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
    private ImageView iv_screen_title_bar_back;
    private ImageView iv_screen_title_bar_search;
    private PopupWindow popupWindow;

    private GridView gv_Diqu;
    private GridView gv_Fenlei;
    private GridView gv_Yinyong;
    private GridView gv_shengchanshang;
    private EditText et_productName;
    private EditText et_warehouseName;
    private Button bt_reset;
    private Button bt_commit;
    private List<Map<String,Object>> list_feilei;
    private List<Map<String,Object>> list_shengchan;
    private List<Map<String,Object>> list_diqu;
    private List<Map<String,Object>> list_yingyong;
    private boolean fenlei=false,shengchan=false,diqu=false,yingyong=false;
    private String fenleiString,shengchanString,diquString,yingyongString;
    String title;
    String url;
    String urlSearch;
    GoodsInStockActivityBean goodsInStockActivityBean;
    private TextView cancel_tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pager_one_grid_view_detail_activity);
        provinceList = dbHelper.getProvince();
        cancel_tv= (TextView) findViewById(R.id.cancel_tv);
        goodsInStockActivityPresenter = new GoodsInStockActivityPresenter(this);
        selection_condition = ((RelativeLayout) findViewById(R.id.selection_condition));
        iv_screen_title_bar_search= (ImageView) findViewById(R.id.iv_screen_title_bar_search);
        iv_screen_title_bar_search.setVisibility(View.VISIBLE);
        iv_screen_title_bar_search.setOnClickListener(this);
//        获取标题
        tv_screen_title_bar_title = ((TextView) findViewById(R.id.tv_screen_title_bar_title));
        Intent intent = getIntent();
        title= intent.getStringExtra("title");
        tv_screen_title_bar_title.setText(title);
        fenleiString=intent.getStringExtra("id");
//        if (title.contains("分类") || title.contains("地区") || title.contains("应用") || title.contains("供应")) {
//            tv_screen_title_bar_title.setText(title.substring(2, title.length()));
//            if (title.contains("分类")) {
//                classification_tv.setText("交易模式");
//            } else if (title.contains("地区")) {
//                region_tv.setText("交易模式");
//            } else if (title.contains("应用")) {
//                application_tv.setText("交易模式");
//            } else if (title.contains("供应")) {
//                supplier_patterns_tv.setText("交易模式");
//            }
//        } else {
//            tv_screen_title_bar_title.setText(title);
//        }


        prl = ((PullToRefreshLayout) findViewById(R.id.refresh_view));

        pre_sale_listView = ((ListView) prl.getPullableView());
        LogUtils.log("标题是--->"+title);
        if (title.equals("现货") || title.equals("预售")) {
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
        } else if (title.contains("分类")|| title.contains("应用")|| title.contains("供应商")||title.contains("地区")){
            searchProductName("0");
        }
        else {
            selection_condition.setVisibility(View.GONE);
            smClList = new ArrayList<GoodsInStockActivityBean.InBean>();
            DirectSellingBean directSellingBean = (DirectSellingBean) getIntent().getSerializableExtra("directSellingBean");

            Log.d("现货的值",directSellingBean.getProductItem().toString()+"现货的值");

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

        iv_screen_title_bar_back = ((ImageView) findViewById(R.id.iv_screen_title_bar_back));
        iv_screen_title_bar_back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.iv_screen_title_bar_search:
               // screenPopupWindow(lists);
                backgroundAlpha(0.5f);
                getPopupwindow();
                break;

            case R.id.iv_screen_title_bar_back:
                finish();
                break;
            case R.id.reset:
                new Thread(){
                    @Override
                    public void run() {
                        super.run();
                        fenlei=false;
                        diqu=false;
                        shengchan=false;
                        yingyong=false;
                        getInfoForGridView();
                    }
                }.start();
                break;
            case R.id.completed:
                popupWindow.dismiss();

                new Thread(){
                    @Override
                    public void run() {
                        super.run();
                        searchProductName("0");
                    }
                }.start();


                break;

           // case R.id.iv_screen_title_bar_search:



        }
    }

    public void getPopupwindow(){

            View popView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.screen_multi_select_popupwindow_view, null);
            popupWindow = new PopupWindow(findViewById(R.id.layout_popup), ActionBarOverlayLayout.LayoutParams.MATCH_PARENT, LinearLayoutCompat.LayoutParams.WRAP_CONTENT);
            popupWindow.setContentView(popView);
        new Thread(){
            @Override
            public void run() {
                super.run();
                getInfoForGridView();
            }
        }.start();
        TextView tv_Title= (TextView) popView.findViewById(R.id.tv_Title);
        tv_Title.setText(title);
            gv_Diqu= (GridView) popView.findViewById(R.id.region_grid);
            gv_Fenlei= (GridView) popView.findViewById(R.id.classification_grid);
            gv_Yinyong= (GridView) popView.findViewById(R.id.application_grid);
            gv_shengchanshang= (GridView) popView.findViewById(R.id.shangchanshang_grid);
            et_productName= (EditText) popView.findViewById(R.id.et_productName);
            et_warehouseName= (EditText) popView.findViewById(R.id.et_warehouseName);
        bt_reset= (Button) popView.findViewById(R.id.reset);
        bt_reset.setOnClickListener(this);
        bt_commit= (Button) popView.findViewById(R.id.completed);
        bt_commit.setOnClickListener(this);
            popupWindow.setTouchable(true);
            popupWindow.setBackgroundDrawable(new BitmapDrawable());
            popupWindow.setOutsideTouchable(true);
            popupWindow.setFocusable(true);

            popupWindow.showAtLocation(iv_screen_title_bar_back, Gravity.TOP, 0, 0);
            popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                @Override
                public void onDismiss() {
                    backgroundAlpha(1f);
                }
            });
        gv_Fenlei.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                fenleiString=list_feilei.get(position).get("id").toString();
                fenlei=true;
                Log.d("分类选择的id",fenleiString);
                getInfoForGridView();
            }
        });
        gv_Diqu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                fenleiString=list_diqu.get(position).get("id").toString();
                diqu=true;
                Log.d("地区的选择id",fenleiString);
                getInfoForGridView();
            }
        });
        gv_Yinyong.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                fenleiString=list_yingyong.get(position).get("id").toString();
                yingyong=true;
                Log.d("应用的选择id",fenleiString);
                getInfoForGridView();
            }
        });
        gv_shengchanshang.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                fenleiString=list_shengchan.get(position).get("id").toString();
                shengchan=true;
                Log.d("生产的应用id",fenleiString);
                getInfoForGridView();
            }
        });




    }
    public void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = this.getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        this.getWindow().setAttributes(lp);
    }

    public void getInfoForGridView(){
        url= SuMaoConstant.SUMAO_IP+"/rest/model/atg/endeca/HomeActor/EndecaAppScreenSearch";
        if (!fenlei&&!diqu&&!shengchan&&!yingyong){
            if (title.equals("现货")){
                url+="?N=0&Nr=product.type:fixedProduct";
            }else{
                url+="?N=0&Nr=product.type:forward-pricing-product";
            }


        }

        if (fenlei){
            url+=fenleiString.trim();
        }
        if (diqu){
            url+=fenleiString.trim();
        }
        if (shengchan){
            url+=fenleiString.trim();
        }
        if (yingyong){
            url+=fenleiString.trim();
        }


        RequestParams rp=new RequestParams(url);
        Log.d("url的值",url);
        x.http().get(rp, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.d("gridview的返回值",result);
                try {
                    JSONObject jsonObject=new JSONObject(result);
                    if (result.contains("fl")){
                    String fenlei=jsonObject.getString("fl");
                    JSONArray array_fl=new JSONArray(fenlei);
                    list_feilei=new ArrayList<Map<String, Object>>();
                    for(int i=0;i<array_fl.length();i++){
                        JSONObject object_fl=array_fl.getJSONObject(i);
                        Map<String,Object > map_fl=new Hashtable<String, Object>();
                        map_fl.put("id",object_fl.getString("fl_id"));
                        map_fl.put("name",object_fl.getString("fl_name"));
                        list_feilei.add(map_fl);
                    }
                    SimpleAdapter adapter_fl=new SimpleAdapter(GoodsInStockActivity.this,list_feilei,R.layout.item_type,new String[]{"name"},new int[]{R.id.tv_Type});
                    gv_Fenlei.setAdapter(adapter_fl);
                    }if (result.contains("dq")){
                    String qidu=jsonObject.getString("dq");
                    JSONArray array_qd=new JSONArray(qidu);
                    list_diqu=new ArrayList<Map<String, Object>>();
                    for (int i=0;i<array_qd.length();i++){
                        JSONObject object_dq=array_qd.getJSONObject(i);
                        Map<String,Object> map_dq=new Hashtable<String, Object>();
                        map_dq.put("id",object_dq.getString("dq_id"));
                        map_dq.put("name",object_dq.getString("dq_name"));
                        list_diqu.add(map_dq);
                    }
                    SimpleAdapter adapter_dq=new SimpleAdapter(GoodsInStockActivity.this,list_diqu,R.layout.item_type,new String[]{"name"},new int[]{R.id.tv_Type});
                    gv_Diqu.setAdapter(adapter_dq);}
                    if (result.contains("sc")){
                    String shengchan=jsonObject.getString("sc");
                    JSONArray array_sc=new JSONArray(shengchan);
                    list_shengchan=new ArrayList<Map<String, Object>>();
                    for (int i=0;i<array_sc.length();i++){
                        JSONObject object_sc=array_sc.getJSONObject(i);
                        Map<String,Object> map_sc=new Hashtable<String, Object>();
                        map_sc.put("id",object_sc.getString("sc_id"));
                        map_sc.put("name",object_sc.getString("sc_name"));
                        list_shengchan.add(map_sc);

                    }
                    SimpleAdapter adapter_sc=new SimpleAdapter(GoodsInStockActivity.this,list_shengchan,R.layout.item_type,new String[]{"name"},new int[]{R.id.tv_Type});
                    gv_shengchanshang.setAdapter(adapter_sc);
                    }if (result.contains("yy")){
                    String yingyong=jsonObject.getString("yy");
                    JSONArray array_yy=new JSONArray(yingyong);
                    list_yingyong=new ArrayList<Map<String, Object>>();
                    for (int i=0;i<array_yy.length();i++){
                        JSONObject json_yy=array_yy.getJSONObject(i);
                        Map<String,Object> map_yy=new Hashtable<String, Object>();
                        map_yy.put("id",json_yy.getString("yy_id"));
                        map_yy.put("name",json_yy.getString("yy_name"));
                        list_yingyong.add(map_yy);
                    }
                    SimpleAdapter adapter_yy=new SimpleAdapter(GoodsInStockActivity.this,list_yingyong,R.layout.item_type,new String[]{"name"},new int[]{R.id.tv_Type});
                    gv_Yinyong.setAdapter(adapter_yy);}
                } catch (JSONException e) {
                    e.printStackTrace();
                }
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

    public void searchProductName(String number){
        urlSearch=SuMaoConstant.SUMAO_IP+"/rest/model/atg/endeca/HomeActor/EndecaAppScreenResult";
        if (!fenlei&&!diqu&&!shengchan&&!yingyong){
            if (title.equals("现货")){
                urlSearch+="?N=0&Nr=product.type:fixedProduct";
            }else if (title.equals("预售")){
                urlSearch+="?N=0&Nr=product.type:forward-pricing-product";
            }else if (title.equals("地区")||title.equals("分类")||title.equals("供应商")||title.equals("应用")){
                urlSearch+=fenleiString;
            }
        }else{
            urlSearch+=fenleiString;
        }
        if (!title.equals("地区")&&!title.equals("分类")&&!title.equals("供应商")&&!title.equals("应用")){
            if (et_productName.getText().toString().length()>0){
                urlSearch+="&alias="+et_productName.getText().toString();
            }
            if (et_warehouseName.getText().toString().length()>0){
                urlSearch+="&wareHouse="+et_warehouseName.getText().toString();
            }
        }

        urlSearch+="&No="+number;
        Log.d("查询的url",urlSearch);
        RequestParams rp=new RequestParams(urlSearch);

        x.http().get(rp, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                if (smClList.size()>0){
                smClList.clear();
                adapter.clearData();
                adapter.notifyDataSetChanged();
                }
                Log.d("查询的返回值",result);
                Gson gson = new Gson();
                goodsInStockActivityBean = gson.fromJson(result, GoodsInStockActivityBean.class);
                //callback.callbackInGoodsInStockActivity(goodsInStockActivityBean,updateOrLoad,"");
                smClList.addAll(goodsInStockActivityBean.getIn());
                LogUtils.log(smClList.size() + "--------------WXL");
                adapter = new PreSaleAdapter(GoodsInStockActivity.this);
                pre_sale_listView.setAdapter(adapter);
                adapter.addData(smClList);
                adapter.notifyDataSetChanged();
                prl.setOnPullListener(new InStockActivityListPullListener());
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

    //    （交易模式、分类、应用、牌号）PopupWindow
//    private void showTradingPatternPopupWindow(String type, List<String> datas) {
//        MultiselectPopupWindow multiselectPopupWindow = null;
//        if (multiselectPopupWindow == null) {
//            multiselectPopupWindow = new MultiselectPopupWindow(getApplicationContext(), datas, type);
//            multiselectPopupWindow.setOnClickCompleteListener(new CompleteClickListener());
//            multiselectPopupWindow.setFocusable(true);
//            multiselectPopupWindow.showAsDropDown(supplierPatterns, 5, 5);
//        } else if (multiselectPopupWindow != null && multiselectPopupWindow.isShowing()) {
//            multiselectPopupWindow.dismiss();
//        } else {
//            multiselectPopupWindow.showAsDropDown(supplierPatterns, 5, 5);
//        }
//    }

    //    筛选的PopupWindow
//    private void screenPopupWindow(ArrayList<ArrayList<String>> lists) {
//        ScreenPopupWindow screenPopupWindow = null;
//        if (screenPopupWindow == null) {
//            screenPopupWindow = new ScreenPopupWindow(getApplicationContext(), lists);
//            screenPopupWindow.setOnClickCompleteListener(new ScreenCompleteClickListener());
//            screenPopupWindow.setFocusable(true);
//            screenPopupWindow.showAtLocation(findViewById(R.id.parent), Gravity.TOP, 5, 5);
//        } else if (screenPopupWindow != null && screenPopupWindow.isShowing()) {
//            screenPopupWindow.dismiss();
//        } else {
//            screenPopupWindow.showAtLocation(findViewById(R.id.parent), Gravity.TOP, 5, 5);
//        }
   // }

    //      地区PopupWindow
//    private void showPopMenu() {
//
//        if (cascadingMenuPopWindow == null) {
//            cascadingMenuPopWindow = new CascadingMenuPopWindow(getApplicationContext(), provinceList);
//            cascadingMenuPopWindow.setMenuViewOnSelectListener(new NMCascadingMenuViewOnSelectListener());
//            cascadingMenuPopWindow.setThreeLevelCompletedListener(new ThreeLevelCompleteClickListener());
//            cascadingMenuPopWindow.setFocusable(true);
//            cascadingMenuPopWindow.showAsDropDown(region, 5, 5);
//        } else if (cascadingMenuPopWindow != null && cascadingMenuPopWindow.isShowing()) {
//            cascadingMenuPopWindow.dismiss();
//        } else {
//            cascadingMenuPopWindow.showAsDropDown(region, 5, 5);
//        }
//    }

    //点击 市 的点击事件
   // class NMCascadingMenuViewOnSelectListener implements CascadingMenuViewOnSelectListener {
//        @Override
//        public void getValue(String provinceId, String cityId, String countyId) {
//            Toast.makeText(getApplicationContext(), "省市县的id--" + provinceId + "--" + cityId + "--" + countyId, Toast.LENGTH_SHORT).show();
//            regions = provinceId;
//            provinces = cityId;
//            cities = countyId;
//            goodsInStockActivityPresenter.getGoodsInStockInfo(groups, pattern, regions, provinces, cities,
//                    categoryNames, appNames, gradeNums, "1", "update");
//        }


    //      点击完成后，
//    class CompleteClickListener implements MultiselectPopupWindowCompleteClickListener {
//
//        @Override
//        public void getSelectionValues(List<String> selectItems, String type) {
//
//            if (type.equals("trading")) {
//                String trading = selectItems.toString();
//                StringBuffer sb = new StringBuffer();
//                if (trading.contains("现货")) {
//                    if (sb.toString().equals("")) {
//                        sb.append("fixedProduct");
//                    } else {
//                        sb.append("&fixedProduct");
//                    }
//                }
//                if (trading.contains("预售")) {
//                    if (sb.toString().equals("")) {
//                        sb.append("forward-pricing-product");
//                    } else {
//                        sb.append("&forward-pricing-product");
//                    }
//                }
//                pattern = sb.toString();
//                Toast.makeText(GoodsInStockActivity.this, "" + pattern, Toast.LENGTH_SHORT).show();
//                goodsInStockActivityPresenter.getGoodsInStockInfo(groups, pattern, regions, provinces, cities,
//                        categoryNames, appNames, gradeNums, "1", "update");
//            } else if (type.equals("classification")) {
//                StringBuffer stringBuffer2 = new StringBuffer();
//
//                for (int i = 0; i < selectItems.size(); i++) {
//                    if (i == selectItems.size() - 1) {
//                        stringBuffer2.append(selectItems.get(i));
//                    } else {
//                        stringBuffer2.append(selectItems.get(i));
//                        stringBuffer2.append("&");
//                    }
//                }
//                categoryNames = stringBuffer2.toString();
//                Toast.makeText(GoodsInStockActivity.this, categoryNames, Toast.LENGTH_SHORT).show();
//                goodsInStockActivityPresenter.getGoodsInStockInfo(groups, pattern, regions, provinces, cities,
//                        categoryNames, appNames, gradeNums, "1", "update");
//            } else if (type.equals("application")) {
//                StringBuffer stringBuffer3 = new StringBuffer();
//
//                for (int i = 0; i < selectItems.size(); i++) {
//                    if (i == selectItems.size() - 1) {
//                        stringBuffer3.append(selectItems.get(i));
//                    } else {
//                        stringBuffer3.append(selectItems.get(i));
//                        stringBuffer3.append("&");
//                    }
//                }
//                appNames = stringBuffer3.toString();
//                Toast.makeText(GoodsInStockActivity.this, "" + appNames, Toast.LENGTH_SHORT).show();
//                goodsInStockActivityPresenter.getGoodsInStockInfo(groups, pattern, regions, provinces, cities,
//                        categoryNames, appNames, gradeNums, "1", "update");
//            } else if (type.equals("supplier")) {
//                StringBuffer stringBuffer3 = new StringBuffer();
//
//                for (int i = 0; i < selectItems.size(); i++) {
//                    if (i == selectItems.size() - 1) {
//                        stringBuffer3.append(selectItems.get(i));
//                    } else {
//                        stringBuffer3.append(selectItems.get(i));
//                        stringBuffer3.append("&");
//                    }
//                }
//                groups = stringBuffer3.toString();
//                Toast.makeText(GoodsInStockActivity.this, "" + groups, Toast.LENGTH_SHORT).show();
//                goodsInStockActivityPresenter.getGoodsInStockInfo(groups, pattern, regions, provinces, cities,
//                        categoryNames, appNames, gradeNums, "1", "update");
//            }
//        }
//    }

    //    筛选的完成按钮的动作
//    class ScreenCompleteClickListener implements ScreenMultiselectPopupWindowCompleteClickListener {
//        @Override
//        public void getSelectionValues(List<String> regionSelectItems, List<String> classifcationSelectItems, List<String> applicationSelectItems, String grade) {
//
//            Toast.makeText(GoodsInStockActivity.this, "search" + "地区:" + regionSelectItems + "   分类:" + classifcationSelectItems + "   应用:" + applicationSelectItems, Toast.LENGTH_SHORT).show();
//            if (regionSelectItems.size() != 0) {
//                StringBuffer stringBuffer2 = new StringBuffer();
//
//                for (int i = 0; i < regionSelectItems.size(); i++) {
//                    if (i == regionSelectItems.size() - 1) {
//                        stringBuffer2.append(regionSelectItems.get(i));
//                    } else {
//                        stringBuffer2.append(regionSelectItems.get(i));
//                        stringBuffer2.append("&");
//                    }
//                }
//                regions = stringBuffer2.toString();
//            }
//            if (classifcationSelectItems.size() != 0) {
//                StringBuffer stringBuffer2 = new StringBuffer();
//
//                for (int i = 0; i < classifcationSelectItems.size(); i++) {
//                    if (i == classifcationSelectItems.size() - 1) {
//                        stringBuffer2.append(classifcationSelectItems.get(i));
//                    } else {
//                        stringBuffer2.append(classifcationSelectItems.get(i));
//                        stringBuffer2.append("&");
//                    }
//                }
//                categoryNames = stringBuffer2.toString();
//            }
//            if (applicationSelectItems.size() != 0) {
//                StringBuffer stringBuffer2 = new StringBuffer();
//
//                for (int i = 0; i < applicationSelectItems.size(); i++) {
//                    if (i == applicationSelectItems.size() - 1) {
//                        stringBuffer2.append(applicationSelectItems.get(i));
//                    } else {
//                        stringBuffer2.append(applicationSelectItems.get(i));
//                        stringBuffer2.append("&");
//                    }
//                }
//                appNames = stringBuffer2.toString();
//            }
//            gradeNums = grade;
//            goodsInStockActivityPresenter.getGoodsInStockInfo(groups, pattern, regions, provinces, cities,
//                    categoryNames, appNames, gradeNums, "1", "update");
//        }

    // }


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
          //  Toast.makeText(this, "走现货,id为" + cl_id, Toast.LENGTH_SHORT).show();
            LogUtils.log("走现货,id为" + cl_id);
            goodsInStockActivityPresenter.getGoodInStockDetailInfo(cl_id);
        } else if (smClList.get(position).getIn_leixing().equals("预售")||smClList.get(position).getIn_leixing().equals("forward-pricing-product")){
          //  Toast.makeText(this, "走预售", Toast.LENGTH_SHORT).show();
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


    public class InStockActivityListPullListener implements PullToRefreshLayout.OnPullListener {
        String number = "0";


        @Override
        public void onRefresh(final PullToRefreshLayout pullToRefreshLayout) {
            number = "0";
            if (tv_screen_title_bar_title.getText().toString().equals("现货")) {
                pattern = "fixedProduct";
            } else if (tv_screen_title_bar_title.getText().toString().equals("预售")) {
                pattern = "forward-pricing-product";
            }
            searchProductName(number);
//            goodsInStockActivityPresenter.getGoodsInStockInfo(groups, pattern, regions, provinces, cities,
//                    categoryNames, appNames, gradeNums, pageNum, "update");

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
                    int num = Integer.parseInt(number) + 1;
                    number = num + "";
                    searchProductName(number);
//                    goodsInStockActivityPresenter.getGoodsInStockInfo(groups, pattern, regions, provinces, cities,
//                            categoryNames, appNames, gradeNums, pageNum, updateOrLoad);
                    // 千万别忘了告诉控件加载完毕了哦！
                    pullToRefreshLayout.loadmoreFinish(PullToRefreshLayout.SUCCEED);
                }
            }.sendEmptyMessageDelayed(0, 3000);
        }

    }

}
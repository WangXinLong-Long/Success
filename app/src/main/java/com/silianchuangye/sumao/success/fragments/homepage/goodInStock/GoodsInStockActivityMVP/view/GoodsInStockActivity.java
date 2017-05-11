package com.silianchuangye.sumao.success.fragments.homepage.goodInStock.GoodsInStockActivityMVP.view;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
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
import android.widget.AbsListView;
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

import com.bumptech.glide.disklrucache.DiskLruCache;
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

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
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
    private String fenleiString="",shengchanString,diquString,yingyongString;
    String title;
    String url;
    String urlSearch;
    GoodsInStockActivityBean goodsInStockActivityBean;
    private TextView cancel_tv;
    int page=0;
    private String biaoti;
    private TextView textValefenlei=null;
    private TextView textValediqu=null;
    private TextView textValeyingyong=null;
    private TextView textValeshengchan=null;
    private String tanchu="";
    private String productName="";
    private String cangkuingcheng="";
    private String fenleiString1;
    private String Id;
    private String unique="";
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




        prl = ((PullToRefreshLayout) findViewById(R.id.refresh_view));

        pre_sale_listView = ((ListView) prl.getPullableView());
        LogUtils.log("标题是--->"+title);
        Intent intent = getIntent();

        title= intent.getStringExtra("title");
        if (title.equals("现货") || title.equals("预售")) {

            biaoti=intent.getStringExtra("biaoti");
            fenleiString1=intent.getStringExtra("id");
            searchProductName("0","");
            tv_screen_title_bar_title.setText(biaoti);
        } else if (title.contains("分类")|| title.contains("应用")|| title.contains("供应商")||title.contains("地区")){
//            Intent intent = getIntent();
//            biaoti=intent.getStringExtra("biaoti");
//            title= intent.getStringExtra("title");
            biaoti=intent.getStringExtra("biaoti");
            fenleiString1=intent.getStringExtra("id");
            searchProductName("0","");
            tv_screen_title_bar_title.setText(biaoti);
        }else {
            Bundle bundle=getIntent().getExtras();
            Id=bundle.getString("sellerCompanyId");
            SharedPreferences sp=getSharedPreferences("sumao",Activity.MODE_PRIVATE);
            unique=sp.getString("unique","");
            iv_screen_title_bar_search.setVisibility(View.GONE);
            //title=bundle.getString("title");
            tv_screen_title_bar_title.setText(title);
            xianhuo_zhixiao("1");
            //现货直销的返回值
////            selection_condition.setVisibility(View.GONE);
//            smClList = new ArrayList<GoodsInStockActivityBean.InBean>();
//            DirectSellingBean directSellingBean = (DirectSellingBean) getIntent().getSerializableExtra("directSellingBean");
//
//            Log.d("现货的值",directSellingBean.getProductItem().toString()+"现货的值");
//
//            List<ProductItemBean> productItem = directSellingBean.getProductItem();
//            for (int i = 0; i < productItem.size(); i++) {
//                GoodsInStockActivityBean.InBean smCl = new GoodsInStockActivityBean.InBean();
//                smCl.setIn_leixing(productItem.get(i).getType());
//                smCl.setIn_id(productItem.get(i).getProductId());
//                smCl.setIn_cangku(productItem.get(i).getWareHouse());
//                smCl.setIn_cpid(productItem.get(i).getSkuId());
////                TODO 差价格参数、刷新出来的是现货的数据
//                smCl.setIn_prix("现货");
//                smCl.setIn_name(productItem.get(i).getProductName());
//                smCl.setIn_qiye(productItem.get(i).getSalerEnterpriseName());
//                smCl.setIn_num(productItem.get(i).getQuantity());
//                smClList.add(smCl);
//            }
//            adapter = new PreSaleAdapter(this,"现货直销");
//            pre_sale_listView.setAdapter(adapter);
//            adapter.addData(smClList);
        }
        //        上啦加载，下拉刷新的监听

        prl.setOnPullListener(new InStockActivityListPullListener());
        pre_sale_listView.setOnItemClickListener(this);

        iv_screen_title_bar_back = ((ImageView) findViewById(R.id.iv_screen_title_bar_back));
        iv_screen_title_bar_back.setOnClickListener(this);
    }

    public void xianhuo_zhixiao(String number){
        String url=SuMaoConstant.SUMAO_IP+"/rest/model/com/sumao/mobile/order/purchase/PlanOrderActor/directEnterpriseProductList";
        RequestParams rp=new RequestParams(url);
        rp.addParameter("pageNum",number);
        rp.addParameter("sellerCompanyId",Id);
        rp.addParameter("_dynSessConf",unique);
        x.http().post(rp, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                if (!result.equals("")){
                    Gson gson = new Gson();
                    DirectSellingBean directSellingBean = gson.fromJson(result, DirectSellingBean.class);
                    List<ProductItemBean> productItem = directSellingBean.getProductItem();
                    if (productItem.size()<=0){
                        Toast.makeText(GoodsInStockActivity.this, "没有找到你查询的产品", Toast.LENGTH_SHORT).show();
                    }else {
                        for (int i = 0; i < productItem.size(); i++) {

                            GoodsInStockActivityBean.InBean smCl = new GoodsInStockActivityBean.InBean();
                            smCl.setIn_leixing(productItem.get(i).getType());
                            smCl.setIn_id(productItem.get(i).getProductId());
                            smCl.setIn_cangku(productItem.get(i).getWareHouse());
                            smCl.setIn_cpid(productItem.get(i).getSkuId());
//                TODO 差价格参数、刷新出来的是现货的数据
                            smCl.setIn_prix("现货");
                            smCl.setIn_name(productItem.get(i).getProductName());
                            smCl.setIn_qiye(productItem.get(i).getSalerEnterpriseName());
                            smCl.setIn_num(productItem.get(i).getQuantity());
                            smClList.add(smCl);
                        }

                        adapter = new PreSaleAdapter(GoodsInStockActivity.this, "现货直销");
                        pre_sale_listView.setAdapter(adapter);
                        //adapter.addData(smClList);
                        smClList.addAll(goodsInStockActivityBean.getIn());
                        Log.d("现货类的长度", goodsInStockActivityBean.getIn().size() + "");
                        Log.d("smClList的长度", smClList.size() + "");
                        adapter.clearData();
                        adapter.addData(smClList);
                        adapter.notifyDataSetChanged();
                    }
                   // Log.d("number的值",number);
                }
                else{
                    Toast.makeText(GoodsInStockActivity.this, "没有找到数据", Toast.LENGTH_SHORT).show();
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
                SharedPreferences sp=getSharedPreferences("sumao",Activity.MODE_PRIVATE);
                SharedPreferences.Editor editor=sp.edit();
                editor.remove("fenlei_value");
                editor.remove("qidu_value");
                editor.remove("yingyong_value");
                editor.remove("shengsheng_value");
                editor.remove("fenleiId");
                editor.commit();
                textValefenlei.setVisibility(View.GONE);
                textValediqu.setVisibility(View.GONE);
                textValeyingyong.setVisibility(View.GONE);
                textValeshengchan.setVisibility(View.GONE);

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
                       // searchProductName("0","条件");
                        searchProductNamefortanchukuang("0","条件");
                    }
                }.start();
                break;




        }
    }

    public void getPopupwindow(){

            final View popView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.searchitem, null);
            popupWindow = new PopupWindow(findViewById(R.id.layout_popup), ActionBarOverlayLayout.LayoutParams.MATCH_PARENT, LinearLayoutCompat.LayoutParams.WRAP_CONTENT);
            popupWindow.setContentView(popView);
        tanchu="搜索";
        new Thread(){
            @Override
            public void run() {
                super.run();
                getInfoForGridView();
            }
        }.start();

        ImageView imageViewBack= (ImageView) popView.findViewById(R.id.iv_child_title_bar_back);
        imageViewBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
        TextView tv_child_title_bar_title1= (TextView) popView.findViewById(R.id.tv_child_title_bar_title);
        tv_child_title_bar_title1.setText(title);
        textValefenlei= (TextView)popView.findViewById(R.id.textValefenlei);
        textValediqu =(TextView) popView.findViewById(R.id.textValediqu);
        textValeyingyong= (TextView)popView.findViewById(R.id.textValeyingyong);
        textValeshengchan= (TextView) popView.findViewById(R.id.textValeshengchan);
        SharedPreferences sp=getSharedPreferences("sumao",Activity.MODE_PRIVATE);
        String fenlei_value=sp.getString("fenlei_value","");
        String qidu_value=sp.getString("qidu_value","");
        String yingyong_value=sp.getString("yingyong_value","");
        String shengsheng_value=sp.getString("shengsheng_value","");
        fenleiString=sp.getString("fenleiId","");
        Log.d("记录上一次的选择值",fenleiString);
        if (!fenlei_value.equals("")){
            //分类显示
            textValefenlei.setVisibility(View.VISIBLE);
            textValefenlei.setText(fenlei_value);
        }
        if (!qidu_value.equals("")){
            //地区显示
            textValediqu.setVisibility(View.VISIBLE);
            textValediqu.setText(qidu_value);
        }
        if (!yingyong_value.equals("")){
            //应用显示
            textValeyingyong.setVisibility(View.VISIBLE);
            textValeyingyong.setText(yingyong_value);
        }
        if (!shengsheng_value.equals("")){
            //生产显示
            textValeshengchan.setVisibility(View.VISIBLE);
            textValeshengchan.setText(shengsheng_value);
        }


            gv_Diqu= (GridView) popView.findViewById(R.id.region_grid);
            gv_Fenlei= (GridView) popView.findViewById(R.id.classification_grid);
            gv_Yinyong= (GridView) popView.findViewById(R.id.application_grid);
            gv_shengchanshang= (GridView) popView.findViewById(R.id.shangchanshang_grid);
            et_productName= (EditText) popView.findViewById(R.id.et_productName);
        et_productName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                productName=et_productName.getText().toString();
            }
        });
            et_warehouseName= (EditText) popView.findViewById(R.id.et_warehouseName);
        et_warehouseName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                cangkuingcheng=et_warehouseName.getText().toString();
            }
        });
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
                String fenleiname=list_feilei.get(position).get("name").toString();
                textValefenlei.setVisibility(View.VISIBLE);
                textValefenlei.setText(fenleiname);
                SharedPreferences sp=getSharedPreferences("sumao",Activity.MODE_PRIVATE);
                SharedPreferences.Editor editor=sp.edit();
                editor.putString("fenlei_value",fenleiname);
                editor.putString("fenleiId",fenleiString);
                editor.commit();

                //取出来
                fenlei=true;
                Log.d("分类选择的id",fenleiString);
                getInfoForGridView();
            }
        });
        gv_Diqu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                fenleiString=list_diqu.get(position).get("id").toString();
                String diquname=list_diqu.get(position).get("name").toString();
                Log.d("地区的值",diquname);
                textValediqu.setVisibility(View.VISIBLE);
                textValediqu.setText(diquname);
                SharedPreferences sp=getSharedPreferences("sumao",Activity.MODE_PRIVATE);
                SharedPreferences.Editor editor=sp.edit();
                editor.putString("qidu_value",diquname);
                editor.putString("fenleiId",fenleiString);
                editor.commit();
                diqu=true;
                Log.d("地区的选择id",fenleiString);
                getInfoForGridView();
            }
        });
        gv_Yinyong.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                fenleiString=list_yingyong.get(position).get("id").toString();
                String yingyongname=list_yingyong.get(position).get("name").toString();
                textValeyingyong.setVisibility(View.VISIBLE);
                textValeyingyong.setText(yingyongname);
                SharedPreferences sp=getSharedPreferences("sumao",Activity.MODE_PRIVATE);
                SharedPreferences.Editor editor=sp.edit();
                editor.putString("yingyong_value",yingyongname);
                editor.putString("fenleiId",fenleiString);
                editor.commit();
                yingyong=true;
                Log.d("应用的选择id",fenleiString);
                getInfoForGridView();
            }
        });
        gv_shengchanshang.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                fenleiString=list_shengchan.get(position).get("id").toString();
                String shengchanname=list_shengchan.get(position).get("name").toString();
                textValeshengchan.setVisibility(View.VISIBLE);
                textValeshengchan.setText(shengchanname);
                SharedPreferences sp=getSharedPreferences("sumao",Activity.MODE_PRIVATE);
                SharedPreferences.Editor editor=sp.edit();
                editor.putString("shengsheng_value",shengchanname);
                editor.putString("fenleiId",fenleiString);
                editor.commit();
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
        if (!fenlei&&!diqu&&!shengchan&&!yingyong) {
            SharedPreferences sp = getSharedPreferences("sumao", Activity.MODE_PRIVATE);
            SharedPreferences.Editor editor = sp.edit();
            editor.remove("fenlei_value");
            editor.remove("qidu_value");
            editor.remove("yingyong_value");
            editor.remove("shengsheng_value");
            editor.remove("fenleiId");
            editor.commit();
            if (textValefenlei!=null) {
                textValefenlei.setVisibility(View.GONE);
            }
            if (textValediqu!=null) {
                textValediqu.setVisibility(View.GONE);
            }
            if (textValeyingyong!=null) {
                textValeyingyong.setVisibility(View.GONE);
            }
            if (textValeshengchan!=null) {
                textValeshengchan.setVisibility(View.GONE);
            }
            if (title.equals("现货")) {
                url += "?N=0&Nr=product.type:fixedProduct";
            } else {
                url += "?N=0&Nr=product.type:forward-pricing-product";
            }
        }


        url+=fenleiString.trim();

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



    //弹出框的值
    public void searchProductNamefortanchukuang(final String number, final String tiaojian){
    urlSearch=SuMaoConstant.SUMAO_IP+"/rest/model/atg/endeca/HomeActor/EndecaAppScreenResult";

        if (fenleiString.equals("")){
            urlSearch+="?No="+number;
        }else{
            urlSearch+=fenleiString;
            urlSearch+="&No="+number;
        }

        try {
            if (productName.length()>0){

                String alias = new String(productName.trim().getBytes("ISO8859-1"),"UTF-8");
                //Log.d("产品名称的值",URLEncoder.encode(productName, "UTF-8"));
                Log.d("产品名称的值",alias);
                urlSearch+="&alias="+productName;
            }
            if (cangkuingcheng.length()>0){
                Log.d("cangkuingcheng",cangkuingcheng);
                String alias = new String(cangkuingcheng.trim().getBytes("ISO8859-1"),"UTF-8");
                //Log.d("产品名称的值",URLEncoder.encode(productName, "UTF-8"));
                Log.d("产品名称的值",alias);
                String name = URLEncoder.encode(cangkuingcheng.trim(), "UTF-8");
                name = URLEncoder.encode(name, "UTF-8");
                urlSearch+="&wareHouse="+cangkuingcheng;
                //Log.d("仓库的名称的值",URLEncoder.encode(cangkuingcheng, "UTF-8"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        RequestParams requestParams=new RequestParams(urlSearch);
        requestParams.setCharset("UTF-8");
        Log.d("rp的值",requestParams.getUri().toString());


    x.http().get(requestParams, new Callback.CommonCallback<String>() {
        @Override
        public void onSuccess(String result) {
            Log.d("查询的返回值",result);
            if (result.contains("in")) {
                Gson gson = new Gson();
                goodsInStockActivityBean = gson.fromJson(result, GoodsInStockActivityBean.class);
                LogUtils.log(smClList.size() + "--------------WXL");
                adapter = new PreSaleAdapter(GoodsInStockActivity.this);
                pre_sale_listView.setAdapter(adapter);

                if (tiaojian.equals("条件")){
                    smClList.clear();
                }
                if (number.equals("0") && smClList.size()>0){
                    smClList.clear();
                }
                smClList.addAll(goodsInStockActivityBean.getIn());
                Log.d("现货类的长度",goodsInStockActivityBean.getIn().size()+"");
                Log.d("smClList的长度",smClList.size()+"");

                adapter.clearData();
                adapter.addData(smClList);
                Log.d("number的值",number);
                if (smClList.size()>10) {
                    pre_sale_listView.setSelection(Integer.parseInt(number) - 10);
                }
                Log.d("adapter的长度",adapter.getCount()+"");
                adapter.notifyDataSetChanged();
            }// prl.setOnPullListener(new InStockActivityListPullListener());
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
    public void searchProductName(final String number, final String tiaojian){
        urlSearch=SuMaoConstant.SUMAO_IP+"/rest/model/atg/endeca/HomeActor/EndecaAppScreenResult";

       RequestParams requestParams = null;
            if (!fenlei&&!diqu&&!shengchan&&!yingyong) {//这里是不弹搜索框的搜索
                Log.d("没有弹出搜索框", "没有弹出搜索框");
                if (title.equals("现货")) {
                    urlSearch += "?N=0&Nr=product.type:fixedProduct";
                } else if (title.equals("预售")) {
                    urlSearch += "?N=0&Nr=product.type:forward-pricing-product";
                } else if (title.equals("地区") || title.equals("分类") || title.equals("供应商") || title.equals("应用")) {
                    urlSearch += fenleiString1;
                }
            }
                urlSearch+="&No="+number;
                requestParams=new RequestParams(urlSearch);

        x.http().get(requestParams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.d("查询的返回值",result);
                if (result.contains("in")) {
                    Gson gson = new Gson();
                    goodsInStockActivityBean = gson.fromJson(result, GoodsInStockActivityBean.class);
                    LogUtils.log(smClList.size() + "--------------WXL");
                    adapter = new PreSaleAdapter(GoodsInStockActivity.this);
                    pre_sale_listView.setAdapter(adapter);

                    if (tiaojian.equals("条件")){
                        smClList.clear();
                    }
                    if (number.equals("0") && smClList.size()>0){
                        smClList.clear();
                    }
                    smClList.addAll(goodsInStockActivityBean.getIn());
                    Log.d("现货类的长度",goodsInStockActivityBean.getIn().size()+"");
                    Log.d("smClList的长度",smClList.size()+"");

                    adapter.clearData();
                    adapter.addData(smClList);
                    Log.d("number的值",number);
                    if (smClList.size()>10) {
                        pre_sale_listView.setSelection(Integer.parseInt(number) - 10);
                    }
                    Log.d("adapter的长度",adapter.getCount()+"");
                    adapter.notifyDataSetChanged();
                }// prl.setOnPullListener(new InStockActivityListPullListener());
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

    //    上啦加载下拉刷新的加载
    @Override
    public void setDataInActivity(GoodsInStockActivityBean goodsInStockActivityBean, String updateOrLoad) {
        if (updateOrLoad.equals("update")) {
            adapter.clearData();
            smClList.clear();
        }
        List<GoodsInStockActivityBean.InBean> in = goodsInStockActivityBean.getIn();
        smClList.addAll(in);
        adapter.addData(smClList);
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




    public class InStockActivityListPullListener implements PullToRefreshLayout.OnPullListener {
        String number = "0";


        @Override
        public void onRefresh(final PullToRefreshLayout pullToRefreshLayout) {
            number = "0";
            page =0;

            if (title.equals("现货直销")){
                xianhuo_zhixiao("1");
            }else {
                searchProductName(number,"");
            }

            pullToRefreshLayout.refreshFinish(PullToRefreshLayout.SUCCEED);
        }

        @Override
        public void onLoadMore(final PullToRefreshLayout pullToRefreshLayout) {
            new Handler() {
                @Override
                public void handleMessage(Message msg) {
                    Log.d("page的值",page+"");
                    int num = page + 1;
                    Log.d("下一页number的值",""+num);
                    page=num;
                    number = page*10 + "";
                    Log.d("number的值",number+"");

                    if (title.equals("现货直销")){
                        xianhuo_zhixiao(page+"");
                    }else {
                        searchProductName(number,"");
                    }
                    pullToRefreshLayout.refreshFinish(PullToRefreshLayout.SUCCEED);
                }
            }.sendEmptyMessageDelayed(0, 1000);
        }

    }

}
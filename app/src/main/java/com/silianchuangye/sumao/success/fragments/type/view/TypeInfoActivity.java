package com.silianchuangye.sumao.success.fragments.type.view;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.jingchen.pulltorefresh.PullToRefreshLayout;
import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.fragments.SearchActivityMVP.bean.Cls;
import com.silianchuangye.sumao.success.fragments.SearchActivityMVP.bean.SearchActivityBean;
import com.silianchuangye.sumao.success.fragments.homepage.auction.openAuction.openAuctionView.OpenAcutionOrMifengActivity;
import com.silianchuangye.sumao.success.fragments.homepage.goodInStock.GoodsInStockActivityMVP.bean.GoodsInStockActivityBean;
import com.silianchuangye.sumao.success.fragments.homepage.goodInStock.GoodsInStockActivityMVP.presenter.GoodsInStockActivityPresenter;
import com.silianchuangye.sumao.success.fragments.homepage.goodInStock.GoodsInStockActivityMVP.view.IGoodsInStockActivityView;
import com.silianchuangye.sumao.success.fragments.homepage.goodInStock.goodsInStockDetail.GoodsInStockDetailActivityMVP.bean.GoodsInStockDetailBean;
import com.silianchuangye.sumao.success.fragments.homepage.goodInStock.goodsInStockDetail.GoodsInStockDetailActivityMVP.view.GoodsInStockDetailActivity;
import com.silianchuangye.sumao.success.fragments.homepage.groupbuying.groupBuyingDetailMVP.view.GroupBuyingSuccessActivity;
import com.silianchuangye.sumao.success.fragments.homepage.preSale.PreSaleDetailActivityMVP.bean.PreSaleDetailBean;
import com.silianchuangye.sumao.success.fragments.homepage.preSale.PreSaleDetailActivityMVP.view.PreSaleDetailActivity;
import com.silianchuangye.sumao.success.fragments.type.TypeInfoActivityAdapter;
import com.silianchuangye.sumao.success.fragments.type.presenter.TypeInfoPresenter;
import com.silianchuangye.sumao.success.utils.LogUtils;
import com.silianchuangye.sumao.success.utils.SuMaoConstant;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class TypeInfoActivity extends AppCompatActivity implements OnClickListener, ITypeInfoView,IGoodsInStockActivityView {
    private ListView lv_Type;
    private List<Cls> list;
    private TypeInfoActivityAdapter adapter;
    private ImageView iv_Back;
    private TextView tv_Search;
    private EditText Search;
    private RelativeLayout layout_Top_Type;
    private ImageView iv_Search;
    private GridViewAdapter adpater3, adpater2, adpater1, adpater4;
    private String address = "";
    String  paihao = "";
    String yingyong = "", fenlei = "";
    private RelativeLayout layout_type_list;
    // private List<String> list_String;
    private TextView tv_pattern_Type, tv_type_Type, tv_apple_Type_for_Type, tv_address_Type, tv_address_search;
    private SearchActivityBean searchActivityBean;
    private Map<String, String> classificationMap;//分类
    private Map<String, String> applicationMap;//应用
    private Map<String, String> regionMap;//地区
    private Map<String, String> tradingPatternsMap;//交易模式对应的英文名字
    private ArrayList<String> name;
    private List<String> name1 = new ArrayList<String>();
    private List<String> name2 = new ArrayList<String>();
    private List<String> name3 = new ArrayList<String>();
    private List<String> name4 = new ArrayList<String>();
    private List<String> number;
    private List<String> number1 = new ArrayList<String>();
    private List<String> number2 = new ArrayList<String>();
    private List<String> number3 = new ArrayList<String>();
    private List<String> number4 = new ArrayList<String>();
    private Map<String, String> types;//交易模式对应的汉字
    //private String fenlei,paihao,yingyong,diqu;
    View listPopupWindowView;
    ListView popupWindowListView;
    LinearLayout bottom_pre_sale_search, popup_window_back;
    PopupWindow popupWindow;
    PopupWindow listPopupWindow;
    private RelativeLayout layout_center_Type;
    private String region2 = "";
    private String classification2 = "";
    private String application2 = "";
    private int Nrpp = 10;
    private int No = 0;
    private GoodsInStockActivityPresenter goodsInStockActivityPresenter;
    ArrayList<String> mArrayList;
    private View view;
    TypeInfoPresenter typeInfoPresenter;
    private String region = "";
    private String classification = "";
    private String application = "";
    private String tradingmethod = "";
    private List<Cls> cl;
    private List<Cls> lists = new ArrayList<>();
    private String Ntt;

    private PullToRefreshLayout ptrl;
    private String total;
    private String  max,min,people,quty,statu="",jingpaileixiang;
    private String tuangoustatus;

    private PullToRefreshLayout ptr;
    private RelativeLayout layout_ff;
    private int page=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_type_info);
        layout_ff = ((RelativeLayout) findViewById(R.id.layout_ff));
        typeInfoPresenter = new TypeInfoPresenter(this);
        Intent intent = getIntent();
        searchActivityBean = (SearchActivityBean) intent.getSerializableExtra("searchActivityBean");
        Ntt = intent.getStringExtra("Ntt");
        Log.d("Ntt的值",""+Ntt);
        //goodsInStockActivityPresenter=new GoodsInStockActivityPresenter()
        goodsInStockActivityPresenter = new GoodsInStockActivityPresenter(this);
//        分类列表
//        classificationMap = searchActivityBean.getSort();
////        应用列表
//        applicationMap = searchActivityBean.getApplication();
////        地区列表
//        regionMap = searchActivityBean.getRegion();
////        交易模式列表
//        tradingPatternsMap = searchActivityBean.getType();
//        total = searchActivityBean.getTotal();
        ptr = ((PullToRefreshLayout) findViewById(R.id.refresh_view));
        ptr.setOnPullListener(new MyPullToRefreshListener());

        layout_type_list = (RelativeLayout) findViewById(R.id.layout_type_list);
        layout_Top_Type = (RelativeLayout) findViewById(R.id.layout_Top_Type);
        iv_Search = (ImageView) findViewById(R.id.iv_search);
        tv_pattern_Type = (TextView) findViewById(R.id.tv_pattern_Type);//分类
        tv_type_Type = (TextView) findViewById(R.id.tv_type_Type);//牌号
        tv_apple_Type_for_Type = (TextView) findViewById(R.id.tv_apple_Type_for_Type);//应用
        tv_address_Type = (TextView) findViewById(R.id.tv_address_Type);//地区
        tv_address_search = (TextView) findViewById(R.id.tv_address_search);
        layout_center_Type = ((RelativeLayout) findViewById(R.id.layout_center_Type));

        name = new ArrayList<>();
        number = new ArrayList<>();
//          分类
        tv_pattern_Type.setOnClickListener(this);
//          应用
        tv_apple_Type_for_Type.setOnClickListener(this);
//          牌号
        tv_type_Type.setOnClickListener(this);
//          地区
        tv_address_Type.setOnClickListener(this);
//       筛选的popupwindow
        tv_address_search.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (total.equals("0")) {
                    Toast.makeText(TypeInfoActivity.this, "没有可供筛选的条件", Toast.LENGTH_LONG).show();
                } else {

                }

            }


        });



        iv_Back = (ImageView) findViewById(R.id.iv_Back_Type);
        iv_Back.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                TypeInfoActivity.this.finish();
            }
        });
        tv_Search = (TextView) findViewById(R.id.tv_Info_Type);
        tv_Search.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //进行搜索功能

              //  Ntt = Search.getText().toString();
                if (Search.getText().toString().equals("")||Search.getText().toString().isEmpty())
                {
                    Toast.makeText(TypeInfoActivity.this,"请输入内容",Toast.LENGTH_SHORT).show();
                }else {

                    Search.clearFocus();
                    Log.d("Ntt的值",""+Ntt);
                    typeInfoPresenter.getSearchFromSever(Search.getText().toString(),"0");
                }

            }
        });

        Search = (EditText) findViewById(R.id.et_Search_Type);
        Search.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // openInputMethod(Search);
            }
        });
        init_listView();

    }

    public void init_listView() {
        lv_Type = (ListView) findViewById(R.id.lv_Type);

        list = new ArrayList<Cls>();
        cl = searchActivityBean.getIn();
        Log.e("TAG","cl------"+cl);
        list.addAll(cl);
        adapter = new TypeInfoActivityAdapter(TypeInfoActivity.this,list);
        lv_Type.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        LogUtils.log("设置适配器----> ");
        getcilck();

    }
    public void getcilck(){
        lv_Type.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String type =list.get(position).getIn_leixing().toString();
                LogUtils.log("type-->"+type);
                if (type .equals("englishAuctionProduct")){//公开竞拍
                    Log.d("公开竞拍",list.get(position).getIn_leixing());
                    getResult(list.get(position).getIn_id(),"公开竞拍");

                }else if(type.equals("fixedProduct")){//现货
                    String cl_id =list.get(position).getIn_id();
                    LogUtils.log("走现货,id为" + cl_id);
                    goodsInStockActivityPresenter.getGoodInStockDetailInfo(cl_id);
                }else if(type .equals("forward-pricing-product")){//预售
                    String productId = list.get(position).getIn_id();
                    String skuId = list.get(position).getIn_cpid();
                    String cl_jiner = list.get(position).getIn_prix();
                    goodsInStockActivityPresenter.getPreSaleDetailInfo(productId, skuId, cl_jiner);
                }else if(type .equals("sealedAuctionProduct")){//密封竞拍

                    getResult(list.get(position).getIn_id(),"密封竞拍");


                }else if(type .equals("groupProduct")){//团购
                    //Toast.makeText(TypeInfoActivity.this,"管俊需要写跳转->团购",Toast.LENGTH_SHORT).show();
                    // TODO 管俊需要写跳转
                    //getGoupBuying(list.get(position).getCl_id());
                    Intent intent=new Intent(TypeInfoActivity.this,GroupBuyingSuccessActivity.class);
                    intent.putExtra("productId",list.get(position).getIn_id());
                    startActivity(intent);

                }

            }
        });

    }

    public void getResult(String productId, final String type){
        String uri="";
        if (type.equals("公开竞拍")){
            uri = SuMaoConstant.SUMAO_IP + "/rest/model/atg/commerce/catalog/ProductCatalogActor/auctionProduct";
        }else if (type.equals("密封竞拍")){
            uri = SuMaoConstant.SUMAO_IP + "/rest/model/atg/commerce/catalog/ProductCatalogActor/sealAuctionProduct";
        }
        RequestParams rp=new RequestParams(uri);
        rp.addParameter("productId",productId);
        Log.d("竞拍结果的返回值的rp",rp+"woshi");
        x.http().post(rp, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.d("竞拍的返回值",result);
                try {
                    if(result.contains("cl_xbianjia")) {
                        JSONObject object = new JSONObject(result);
                        String object_result = object.getString("cl_type");
                        //String array_result=object.getString("List");
                        //String status=object.getString("status");
                        Intent intent=new Intent(TypeInfoActivity.this,OpenAcutionOrMifengActivity.class);
                        intent.putExtra("result",result);


                        if (type.equals("公开竞拍")){
                            intent.putExtra("type_way", "公开竞拍");
                            intent.putExtra("proType",object_result);
                        }else if (type.equals("密封竞拍")){
                            intent.putExtra("type_way", "密封报价");
                            intent.putExtra("proType",object_result);
                        }
                        startActivity(intent);

                    }


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

    public void getGoupBuying(String productId){
        String url=SuMaoConstant.SUMAO_IP+"/rest/model/atg/commerce/catalog/ProductCatalogActor/groupProduct";
        //String url= SuMaoConstant.SUMAO_IP+"/rest/model/atg/commerce/catalog/ProductCatalogActor/groupProduct";
        RequestParams rp=new RequestParams(url);
        rp.addParameter("productId",productId);
        Log.e("TAG",productId);
        Log.d("跳转团购rp的值",rp+"");
        x.http().post(rp, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.d("跳转团购时的返回值",result);
                try {
                    if (result.contains("")) {
                        Intent intent = new Intent(getApplication(), GroupBuyingSuccessActivity.class);
                        intent.putExtra("result",result);

                        JSONObject object = new JSONObject(result);
                        String state = object.getString("section");
                        if (state.equals("true")) {
                            intent.putExtra("state", "团购已开始");
                            intent.putExtra("result2","abc");
                            // intent.putExtra("productOff","false");
                        } else if (state.equals("false")) {
                            intent.putExtra("state", "团购未开始");
                            intent.putExtra("result2","abc");
                            //intent.putExtra("productOff","false");
                        } else if (state.equals("end")){
//                            if (result2.equals("gone")) {
//                                intent.putExtra("state", "团购结束");
//                                intent.putExtra("result2", "未成团");
//                                // intent.putExtra("productOff","false");
//                            } else {
//                                intent.putExtra("state", "团购结束");
//                                intent.putExtra("result2", "已成团");
//                                //intent.putExtra("productOff","false");
//                            }

                        }
                        intent.putExtra("result", result);
                        startActivity(intent);
                    }
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


    @Override
    public void onClick(View v) {
        switch (v.getId()) {


        }
    }

    //    实现接口中的方法
    @Override
    public void getTypeInfoInActivity(SearchActivityBean searchActivityBean) {
        Log.d("进来这个方发了","进来这个方法了");


        Log.d("list的长度",list.size()+"");
        list.clear();
        Log.d("list的长度",list.size()+"");
        cl = searchActivityBean.getIn();
        Log.e("TAG","cl------"+cl.size()+"");

        Log.d("list的size",list.size()+"");
        adapter.clearData();
        list.addAll(cl);
       // adapadapter.clearData();ter.notifyDataSetChanged();
        Log.d("list的长度",list.size()+"");
        adapter.addData(list);
        adapter.notifyDataSetChanged();
        Log.d("listdesize",list.size()+"");
        getcilck();

   }

    @Override
    public void setDataInActivity(GoodsInStockActivityBean goodsInStockActivityBean, String updateOrLoad) {

    }

    @Override
    public void getGoodsInStockDetailData(GoodsInStockDetailBean goodsInStockDetailBean) {
        Intent intent = new Intent();
        intent.putExtra("goodsInStockDetailBean", goodsInStockDetailBean);
        intent.setClass(this, GoodsInStockDetailActivity.class);
        startActivity(intent);
    }

    @Override
    public void getPreSaleDetailData(PreSaleDetailBean preSaleDetailBean, String sku) {
        Intent intent = new Intent();
        intent.putExtra("preSaleDetailBean", preSaleDetailBean);
       // intent.putExtra("skuId", skuId);
        intent.setClass(this, PreSaleDetailActivity.class);
        startActivity(intent);
    }


    //    上拉刷新，下拉加载的监听
    private class MyPullToRefreshListener implements PullToRefreshLayout.OnPullListener {

        @Override
        public void onRefresh(final PullToRefreshLayout pullToRefreshLayout) {
            new Handler() {
                @Override
                public void handleMessage(Message msg) {
                    super.handleMessage(msg);
                    Nrpp = 10;
                    No = 0;
                    Log.d("Ntt的值",""+Ntt);
                    if (Search.getText().toString()!=null&&!Search.getText().toString().equals("")){
                        typeInfoPresenter.getSearchFromSever(Search.getText().toString(),"0");
//                    }else{
//                        typeInfoPresenter.getSearchFromSever(Ntt,"0");
                    }
                    pullToRefreshLayout.refreshFinish(PullToRefreshLayout.SUCCEED);
                    adapter.notifyDataSetChanged();

                }

            }.sendEmptyMessageDelayed(0, 1000);


        }

        @Override
        public void onLoadMore(final PullToRefreshLayout pullToRefreshLayout) {
            page=page+10;

            new Handler() {
                @Override
                public void handleMessage(Message msg) {
                    super.handleMessage(msg);
                    Log.d("Ntt的值",Ntt);
                    if (Search.getText().toString()!=null&&!Search.getText().toString().equals("")){
                        typeInfoPresenter.getSearchFromSever(Search.getText().toString(),(page)+"");
//                    }else{
//                        typeInfoPresenter.getSearchFromSever(Ntt,page+"");

                    }
                    pullToRefreshLayout.refreshFinish(PullToRefreshLayout.SUCCEED);
                    adapter.notifyDataSetChanged();

                }
            }.sendEmptyMessageDelayed(0, 1000);
        }
    }


    class MyAdapter extends SimpleAdapter {


        public MyAdapter(Context context, List<? extends Map<String, ?>> data, int resource, String[] from, int[] to) {
            super(context, data, resource, from, to);
        }

        @Override
        public int getCount() {
            LogUtils.log("list.size()"+list.size());
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;
            if (convertView == null) {
                viewHolder = new ViewHolder();
                convertView = getLayoutInflater().inflate(R.layout.item_type_two, null);
                viewHolder.text_Name = (TextView) convertView.findViewById(R.id.tv_name_type_two);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }

            return convertView;
        }
    }

    class ViewHolder {
        TextView text_Name;
        RelativeLayout layout_Type;

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

    //初始化popupWindow数据（分类，应用、地区）
    private void initListPopupWindowView(final String s) {
        listPopupWindowView = View.inflate(this, R.layout.list_popup_window_view, null);
        popupWindowListView = (ListView) listPopupWindowView.findViewById(R.id.popup_window_list_view);
        popup_window_back = ((LinearLayout) listPopupWindowView.findViewById(R.id.popup_window_back));
        popup_window_back.setOnClickListener(this);
        mArrayList = new ArrayList<String>();
//        为弹出的框添加数据   getData(s)

        final ArrayAdapter<String> popupWindowListViewAdpter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, getData(s));
//        这句应该懂吧
        popupWindowListView.setAdapter(popupWindowListViewAdpter);
//        点击（分类，应用、地区）后弹出的popupWindow上面的listview的点击事件
        popupWindowListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if (s.equals("地区")) {
                    tv_address_Type.setText(popupWindowListViewAdpter.getItem(position));
                    region = number.get(position);
                    list.clear();
                    lists.clear();
                    Nrpp = 10;
                    No = 0;
                    typeInfoPresenter.getSearchFromSever(Ntt,"10");
                } else if (s.equals("分类")) {
                    tv_pattern_Type.setText(popupWindowListViewAdpter.getItem(position));
                    classification = number.get(position);
                    list.clear();
                    lists.clear();
                    Nrpp = 10;
                    No = 0;
                    typeInfoPresenter.getSearchFromSever( Ntt,"10");
                    lv_Type.postInvalidate();

                } else if (s.equals("应用")) {
                    tv_apple_Type_for_Type.setText(popupWindowListViewAdpter.getItem(position));
                    application = number.get(position);
                    list.clear();
                    lists.clear();
                    Nrpp = 10;
                    No = 0;
                    typeInfoPresenter.getSearchFromSever(Ntt,"10");

                } else if (s.equals("交易模式")) {
                    tv_type_Type.setText(popupWindowListViewAdpter.getItem(position));
                    tradingmethod = number.get(position);
                    LogUtils.log("交易类型---->" + tradingmethod);
                    list.clear();
                    lists.clear();
                    Nrpp = 10;
                    No = 0;
                    typeInfoPresenter.getSearchFromSever( Ntt,"10");
                }
                listPopupWindow.dismiss();
            }
        });

    }


    //显示popupWindow（分类，应用、地区）
    private void showListPopupWindow() {
        listPopupWindowView.measure(0, 0);
        int width = getWindowManager().getDefaultDisplay().getWidth();
        listPopupWindow = new PopupWindow(listPopupWindowView, width,1600);
        listPopupWindow.setFocusable(true);
        listPopupWindow.setBackgroundDrawable(new BitmapDrawable());
        listPopupWindow.showAsDropDown(layout_center_Type, 0, 0);
    }

    //为弹出的框添加数据   getData(s)
    private ArrayList<String> getData(String s) {
        if (s.equals("分类")) {
            if (classificationMap != null) {
                number.clear();
                name.clear();

                Iterator<Map.Entry<String, String>> iterator = classificationMap.entrySet().iterator();
                while (iterator.hasNext()) {
                    Map.Entry<String, String> entry = iterator.next();
                    String key = entry.getKey();
                    LogUtils.log("现货：" + key);
                    String value = entry.getValue();
                    LogUtils.log("现货：" + value);
                    name.add(key.toString());
                    number.add(value.toString());
                }

            } else {
                Toast.makeText(this, "暂无其他分类", Toast.LENGTH_SHORT).show();
            }
        } else if (s.equals("应用")) {
            if (applicationMap != null) {
                number.clear();
                name.clear();

                Iterator<Map.Entry<String, String>> iterator = applicationMap.entrySet().iterator();
                while (iterator.hasNext()) {
                    Map.Entry<String, String> entry = iterator.next();
                    String key = entry.getKey();
                    String value = entry.getValue();
                    name.add(key);
                    number.add(value);
                }
            } else {
                Toast.makeText(this, "暂无其他应用", Toast.LENGTH_SHORT).show();
            }
        } else if (s.equals("地区")) {
            if (regionMap != null) {
                number.clear();
                name.clear();

                Iterator<Map.Entry<String, String>> iterator = regionMap.entrySet().iterator();
                while (iterator.hasNext()) {
                    Map.Entry<String, String> entry = iterator.next();
                    String key = entry.getKey();
                    String value = entry.getValue();
                    name.add(key);
                    number.add(value);
                }
            } else {
                Toast.makeText(this, "暂无其他地区", Toast.LENGTH_SHORT).show();
            }
        } else if (s.equals("交易模式")) {
            if (tradingPatternsMap != null) {
                number.clear();
                name.clear();

                Iterator<Map.Entry<String, String>> iterator = tradingPatternsMap.entrySet().iterator();
                while (iterator.hasNext()) {
                    Map.Entry<String, String> entry = iterator.next();
                    String key = types.get(entry.getKey());
                    String value = entry.getValue();
                    name.add(key);
                    number.add(value);
                }
            } else {
                Toast.makeText(this, "暂无其他交易模式", Toast.LENGTH_SHORT).show();
            }
        }
        return name;
    }
    public  boolean isShouldHideInput(View v, MotionEvent event) {
        if (v != null && (v instanceof EditText)) {
            int[] leftTop = { 0, 0 };
            //获取输入框当前的location位置
            v.getLocationInWindow(leftTop);
            int left = leftTop[0];
            int top = leftTop[1];
            int bottom = top + v.getHeight();
            int right = left + v.getWidth();
            return !(event.getX() > left && event.getX() < right
                    && event.getY() > top && event.getY() < bottom);
        }
        return false;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (isShouldHideInput(v, ev)) {

                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                if (imm != null) {
                    Search.clearFocus();
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }
            return super.dispatchTouchEvent(ev);
        }
        // 必不可少，否则所有的组件都不会有TouchEvent了
        if (getWindow().superDispatchTouchEvent(ev)) {
            return true;
        }
        return onTouchEvent(ev);
    }

}

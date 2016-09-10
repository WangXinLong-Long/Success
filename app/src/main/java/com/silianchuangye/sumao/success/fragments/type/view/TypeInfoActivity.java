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
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
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
import com.silianchuangye.sumao.success.adapter.PreSaleAdapter;
import com.silianchuangye.sumao.success.custom.customCalendar.CustomGridView;
import com.silianchuangye.sumao.success.fragments.SearchActivityMVP.bean.Cls;
import com.silianchuangye.sumao.success.fragments.SearchActivityMVP.bean.SearchActivityBean;
import com.silianchuangye.sumao.success.fragments.homepage.goodInStock.GoodsInStockActivityMVP.bean.GoodsInStockActivityBean;
import com.silianchuangye.sumao.success.fragments.homepage.goodInStock.GoodsInStockActivityMVP.bean.SMCl;
import com.silianchuangye.sumao.success.fragments.homepage.goodInStock.GoodsInStockDetailActivityMVP.view.GoodsInStockDetailActivity;
import com.silianchuangye.sumao.success.fragments.homepage.preSale.PreSaleDetailActivityMVP.view.PreSaleDetailActivity;
import com.silianchuangye.sumao.success.fragments.type.TypeInfoActivityAdapter;
import com.silianchuangye.sumao.success.fragments.type.presenter.TypeInfoPresenter;
import com.silianchuangye.sumao.success.utils.LogUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class TypeInfoActivity extends AppCompatActivity implements OnClickListener, ITypeInfoView {
    private ListView lv_Type;
    private List<Cls> list;
    private TypeInfoActivityAdapter adapter;
    private ImageView iv_Back;
    private TextView tv_Search;
    private EditText Search;
    private RelativeLayout layout_Top_Type;
    private ImageView iv_Search;
    private GridViewAdapter adpater3, adpater2, adpater1, adpater4;
    private String address, paihao, yingyong, fenlei;
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
    ;
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

    //    Handler handler = new Handler() {
//        @Override
//        public void handleMessage(Message msg) {
//            super.handleMessage(msg);
//            int what = msg.what;
//            switch (what) {
//                case 998:
//                    adapter.notifyDataSetChanged();
//                    layout_ff.invalidate();
//                    break;
//                default:
//                    break;
//            }
//
//
//        }
//    };
    private PullToRefreshLayout ptr;
    private RelativeLayout layout_ff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_type_info);
        layout_ff = ((RelativeLayout) findViewById(R.id.layout_ff));
        typeInfoPresenter = new TypeInfoPresenter(this);
        Intent intent = getIntent();
        searchActivityBean = (SearchActivityBean) intent.getSerializableExtra("searchActivityBean");
        Ntt = intent.getStringExtra("Ntt");
//        分类列表
        classificationMap = searchActivityBean.getSort();
//        应用列表
        applicationMap = searchActivityBean.getApplication();
//        地区列表
        regionMap = searchActivityBean.getRegion();
//        交易模式列表
        tradingPatternsMap = searchActivityBean.getType();
        total = searchActivityBean.getTotal();
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
                    popupwindow_shaixuan();
                    init_popupwindowView();
                }

            }


        });


        // closeInputMethod();
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
                Ntt = Search.getText().toString();
                list.clear();
                lists.clear();
                Nrpp = 10;
                No = 0;
                region = "";
                classification = "";
                application = "";
                tradingmethod = "";
                typeInfoPresenter.getSearchFromSever(region, classification, application, tradingmethod, Nrpp, No, Ntt);
                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(0,InputMethodManager.HIDE_NOT_ALWAYS);
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
        types = new HashMap<>();
        types.put("forward-pricing-sku", "预售");
        types.put("sealed-auction-sku", "密封竞拍");
        types.put("english-auction-sku", "公开竞拍");
        types.put("fixed-price-sku", "现货");
        types.put("group-sku", "团购");

        cl = searchActivityBean.getCl();
        Log.e("TAG","cl------"+cl);
        lists.addAll(cl);
        list.addAll(lists);
        adapter = new TypeInfoActivityAdapter(this,list);
        LogUtils.log("设置适配器----> ");
        lv_Type.setAdapter(adapter);
        lv_Type.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String type =(String) list.get(position).getCl_type();
                LogUtils.log("type-->"+type);
                if (type .equals("englishAuctionProduct")){//公开竞拍
                    Toast.makeText(TypeInfoActivity.this,"管俊需要写跳转->公开竞拍",Toast.LENGTH_SHORT).show();
                    // TODO 管俊需要写跳转
                    /*
                    管俊需要写跳转
                     */
                }else if(type .equals("fixedProduct")){//现货
                    Intent intent = new Intent();
                    intent.putExtra("cl_id", list.get(position).getCl_id());
                    intent.setClass(TypeInfoActivity.this, GoodsInStockDetailActivity.class);
                    startActivity(intent);
                }else if(type .equals("forward-pricing-product")){//预售
                    Intent intent = new Intent();
                    //        产品编号
                    intent.putExtra("productId",list.get(position).getCl_id());
                    //        skuId
                    intent.putExtra("skuId",list.get(position).getCl_cpid());
                    intent.setClass(TypeInfoActivity.this, PreSaleDetailActivity.class);
                    startActivity(intent);
                }else if(type .equals("sealedAuctionProduct")){//密封竞拍
                    Toast.makeText(TypeInfoActivity.this,"管俊需要写跳转->密封竞拍",Toast.LENGTH_SHORT).show();
                    // TODO 管俊需要写跳转
                    /*
                    管俊需要写跳转
                     */
                }else if(type .equals("groupProduct")){//团购
                    Toast.makeText(TypeInfoActivity.this,"管俊需要写跳转->团购",Toast.LENGTH_SHORT).show();
                    // TODO 管俊需要写跳转
                    /*
                    管俊需要写跳转
                     */
                }
//                demandScheduleProduct--->段少昌
//                forward-pricing-product
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
        popupWindow.showAsDropDown(layout_center_Type, 0, 0);

    }

    /**
     * 筛选的popupwindow
     */
    public void popupwindow_shaixuan() {

        view = View.inflate(this, R.layout.item_shaixuan, null);

        final CustomGridView gv_Apple_Type = (CustomGridView) view.findViewById(R.id.gv_Apple_Type);
        if (classificationMap != null) {
            number1.clear();
            name1.clear();

            Iterator<Map.Entry<String, String>> iterator = classificationMap.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, String> entry = iterator.next();
                String key = entry.getKey();
                String value = entry.getValue();
                name1.add(key.toString());
                number1.add(value.toString());
            }

        }else {
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
         * 交易模式
         */
        final CustomGridView gv_Number_Type = (CustomGridView) view.findViewById(R.id.gv_Number_Type);
        if (tradingPatternsMap != null) {
            number4.clear();
            name4.clear();

            Iterator<Map.Entry<String, String>> iterator = tradingPatternsMap.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, String> entry = iterator.next();
                String key = types.get(entry.getKey());
                String value = entry.getValue();
                name4.add(key);
                number4.add(value);
            }
        }else {
            number4.clear();
            name4.clear();
        }
        adpater1 = new GridViewAdapter(name4);
        gv_Number_Type.setAdapter(adpater1);
        gv_Number_Type.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                adpater1.setSelectedPosition(position);
                adpater1.notifyDataSetChanged();
                paihao = name4.get(position).toString();
            }
        });

        /**
         * 应用
         */
        final CustomGridView gv_Address_Type = (CustomGridView) view.findViewById(R.id.gv_Address_Type);
        if (applicationMap != null) {
            number2.clear();
            name2.clear();

            Iterator<Map.Entry<String, String>> iterator = applicationMap.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, String> entry = iterator.next();
                String key = entry.getKey();
                String value = entry.getValue();
                name2.add(key);
                number2.add(value);
            }
        }else {
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
        if (regionMap != null) {
            number3.clear();
            name3.clear();

            Iterator<Map.Entry<String, String>> iterator = regionMap.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, String> entry = iterator.next();
                String key = entry.getKey();
                String value = entry.getValue();
                name3.add(key);
                number3.add(value);
            }
        }else {
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
        bt_reset.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("令所选的为空", "null");
                adpater1.setSelectedPosition(-1);
                adpater1.notifyDataSetChanged();
                adpater2.setSelectedPosition(-1);
                adpater2.notifyDataSetChanged();
                adpater3.setSelectedPosition(-1);
                adpater3.notifyDataSetChanged();
                adpater4.setSelectedPosition(-1);
                adpater4.notifyDataSetChanged();
                Log.d("令所选的为空", address + fenlei + yingyong + paihao);
            }
        });
        /**
         * 完成按钮
         */
        Button bt_finish = (Button) view.findViewById(R.id.bt_finish);
        bt_finish.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {


                Log.d("根据所选的进行筛选", "");
//                地区、分类、应用、交易模式
                if (address == null || address.isEmpty()) {
                    region = "";
                } else {
                    region = regionMap.get(address);
                }
                if (fenlei == null || fenlei.isEmpty()) {
                    classification = "";
                } else {
                    classification = classificationMap.get(fenlei);
                }
                if (yingyong == null || yingyong.isEmpty()) {
                    application = "";
                } else {
                    application = applicationMap.get(yingyong);
                }
                if (paihao == null || paihao.isEmpty()) {
                    tradingmethod = "";
                } else {
                    tradingmethod = number4.get(name4.indexOf(paihao));
                }
                if (list != null) {
                    lists.clear();
                    list.clear();
                }
                Nrpp = 10;
                No = 0;
                typeInfoPresenter.getSearchFromSever(region, classification, application, tradingmethod, Nrpp, No, Ntt);


                LogUtils.log("参数-->" + address + fenlei + yingyong + paihao);
                LogUtils.log("参数-->" + region + classification + application + tradingmethod);
                popupWindow.dismiss();
            }

        });


    }

    public boolean isAbc(String str) {
        while (true) {

            for (int i = 0; i < str.length(); i++) {
                if ((str.charAt(i) <= 'Z' && str.charAt(i) >= 'A')
                        || (str.charAt(i) <= 'z' && str.charAt(i) >= 'a')) {
                    //System.out.println(str.charAt(i) + "是字母");
                    return true;
                }
                return false;
            }
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
//            分类
            case R.id.tv_pattern_Type:

                if (classificationMap != null || total.equals("0")) {
                    //                初始化数据
                    initListPopupWindowView("分类");
                    //                显示listView
                    showListPopupWindow();
                } else {
                    Toast.makeText(this, "暂无其他分类", Toast.LENGTH_SHORT).show();
                }
                break;
//            应用
            case R.id.tv_apple_Type_for_Type:

                if (applicationMap != null || total.equals("0")) {
                    //                初始化数据
                    initListPopupWindowView("应用");
                    //                显示listView
                    showListPopupWindow();
                } else {
                    Toast.makeText(this, "暂无其他应用", Toast.LENGTH_SHORT).show();
                }
                break;
//            交易模式的popupwindow
            case R.id.tv_type_Type:
                if (tradingPatternsMap != null || total.equals("0")) {
                    //                初始化数据
                    initListPopupWindowView("交易模式");
                    //                显示listView
                    showListPopupWindow();
                } else {
                    Toast.makeText(this, "暂无其他交易模式", Toast.LENGTH_SHORT).show();
                }
                break;
//            地区的popupwindow
            case R.id.tv_address_Type:
                if (regionMap != null || total.equals("0")) {
                    //                初始化数据
                    initListPopupWindowView("地区");
                    //                显示listView
                    showListPopupWindow();
                } else {
                    Toast.makeText(this, "暂无其他地区", Toast.LENGTH_SHORT).show();
                }
                break;

        }
    }

    //    实现接口中的方法
    @Override
    public void getTypeInfoInActivity(SearchActivityBean searchActivityBean) {
        total = searchActivityBean.getTotal();
        lists.clear();
        if (total.equals("0")) {
            Toast.makeText(this, "抱歉暂时没有搜索到您需要的产品", Toast.LENGTH_LONG).show();
            lists.clear();
            list.clear();
            adapter.notifyDataSetChanged();
        } else {

//        分类列表
            classificationMap = searchActivityBean.getSort();
//        应用列表
            applicationMap = searchActivityBean.getApplication();
//        地区列表
            regionMap = searchActivityBean.getRegion();
//        交易模式列表
            tradingPatternsMap = searchActivityBean.getType();
            cl = searchActivityBean.getCl();
            LogUtils.log("获取到cl的值----> " + cl.get(0).getCl_mingcheng());
            if ( null== cl || cl.size() == 0) {
                Toast.makeText(TypeInfoActivity.this, "没有更多内容", Toast.LENGTH_LONG).show();
            } else {
                LogUtils.log("为list添加数据----> ");

                    lists.addAll(cl);

                list.addAll(lists);
                LogUtils.log(list.size()+"<--list.size()");
                adapter.notifyDataSetChanged();

            }
        }
    }


    //    上拉刷新，下拉加载的监听
    private class MyPullToRefreshListener implements PullToRefreshLayout.OnPullListener {
        @Override
        public void onRefresh(final PullToRefreshLayout pullToRefreshLayout) {
            new Handler() {
                @Override
                public void handleMessage(Message msg) {
                    super.handleMessage(msg);
                    if (list != null) {
                        lists.clear();
                        list.clear();
                    }
                    Nrpp = 10;
                    No = 0;
                    typeInfoPresenter.getSearchFromSever(region, classification, application, tradingmethod, Nrpp, No, Ntt);
                    pullToRefreshLayout.refreshFinish(PullToRefreshLayout.SUCCEED);
                    adapter.notifyDataSetChanged();

                }

            }.sendEmptyMessageDelayed(0, 1000);


        }

        @Override
        public void onLoadMore(final PullToRefreshLayout pullToRefreshLayout) {
            new Handler() {
                @Override
                public void handleMessage(Message msg) {
                    super.handleMessage(msg);

                    typeInfoPresenter.getSearchFromSever(region, classification, application, tradingmethod, Nrpp, No + Nrpp, Ntt);
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
                    typeInfoPresenter.getSearchFromSever(region, classification, application, tradingmethod, Nrpp, No, Ntt);
                } else if (s.equals("分类")) {
                    tv_pattern_Type.setText(popupWindowListViewAdpter.getItem(position));
                    classification = number.get(position);
                    list.clear();
                    lists.clear();
                    Nrpp = 10;
                    No = 0;
                    typeInfoPresenter.getSearchFromSever(region, classification, application, tradingmethod, Nrpp, No, Ntt);
                    lv_Type.postInvalidate();

                } else if (s.equals("应用")) {
                    tv_apple_Type_for_Type.setText(popupWindowListViewAdpter.getItem(position));
                    application = number.get(position);
                    list.clear();
                    lists.clear();
                    Nrpp = 10;
                    No = 0;
                    typeInfoPresenter.getSearchFromSever(region, classification, application, tradingmethod, Nrpp, No, Ntt);

                } else if (s.equals("交易模式")) {
                    tv_type_Type.setText(popupWindowListViewAdpter.getItem(position));
                    tradingmethod = number.get(position);
                    LogUtils.log("交易类型---->" + tradingmethod);
                    list.clear();
                    lists.clear();
                    Nrpp = 10;
                    No = 0;
                    typeInfoPresenter.getSearchFromSever(region, classification, application, tradingmethod, Nrpp, No, Ntt);
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

}

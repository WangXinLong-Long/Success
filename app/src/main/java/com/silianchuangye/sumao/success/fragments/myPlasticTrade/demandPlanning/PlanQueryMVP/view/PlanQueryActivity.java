package com.silianchuangye.sumao.success.fragments.myPlasticTrade.demandPlanning.PlanQueryMVP.view;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.SharedPreferences;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.jingchen.pulltorefresh.PullToRefreshLayout;
import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.demandPlanning.PlanQueryMVP.adapter.PlanQueryAdapter;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.demandPlanning.PlanQueryMVP.bean.PlanBean;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.demandPlanning.PlanQueryMVP.bean.PlanQueryBean;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.demandPlanning.PlanQueryMVP.presenter.PlanQueryPresenter;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.messagePush.adapter.MessagePushListViewAdapter;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.messagePush.bean.MessagePush;
import com.silianchuangye.sumao.success.utils.ShowCalendar;
import com.silianchuangye.sumao.success.utils.SuMaoConstant;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * 计划查询
 */
public class PlanQueryActivity extends AppCompatActivity implements IPlanQueryView, View.OnClickListener {

    private PlanQueryPresenter planQueryPresenter;
    //    ListView的父容器
    private PullToRefreshLayout pullToRefreshLayout;
    private ListView listview;
    private ImageView search;
    private View popupWindowView;
    private LinearLayout mentionState;
    private ListView mentionStateListView;
    private List<String> mentionStateList;
    private RelativeLayout title;
    private LinearLayout outOfPopupWindow;
    private PopupWindow popupWindow;
    private TextView demand_end_time;
    private TextView demand_start_time;
    private LinearLayout demand_end_time_rl;
    private LinearLayout demand_start_time_rl;
    private List<PlanBean.PlanOrderList> planOrderList;
    private PlanQueryAdapter adapter;
    private SharedPreferences sp;
   // private List<PlanQueryBean> list;
    private String unique;
    private List<Map<String,Object>> list_plan_order;
    private Button bt_Search;
    private EditText product_name;
    private EditText warehouse_name;
    private EditText seller_enterprise;
    private EditText demand_number;
    private TextView tv_pop_customer_qu;
    private String state;
    private ListView lv_wuliu,lv_qiye;
    private List<String> list_wuliu,list_qiye,list_qiyeId,list_wuliyId;
    private LinearLayout mention_logistc_ll,layout_seller_enterprise;
    private String wuliu,qiye;
    private ImageView img_create_logistics_back;
    Dialog mDialog;
    private String name="";
    private String cangku="";
    private String maifang="";
    private String xuqiuId="";
    private String xuqiuStart="";
    private String xuqiuEnd="";
    private String tibaoState="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan_query);
        planQueryPresenter = new PlanQueryPresenter(this);
        pullToRefreshLayout = ((PullToRefreshLayout) findViewById(R.id.refresh_view));
        listview = ((ListView) pullToRefreshLayout.getPullableView());
        //PlanQueryAdapter planQueryAdapter = new PlanQueryAdapter(this);
        img_create_logistics_back= (ImageView) findViewById(R.id.img_create_logistics_back);
        img_create_logistics_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PlanQueryActivity.this.finish();
            }
        });
        //i//nitData(planQueryAdapter);
        title = ((RelativeLayout) findViewById(R.id.title));

        search = ((ImageView) findViewById(R.id.img_create_logistics_search));
        search.setOnClickListener(this);
        sp=getSharedPreferences("sumao", Activity.MODE_PRIVATE);
        unique=sp.getString("unique","");
        planQueryPresenter.getList("1","10","","","","","","","",unique,true);
        pullToRefreshLayout.setOnPullListener(new GroupBuyingListViewPullListener());

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_create_logistics_search:
                initPopupListView();
                initPopupWindow();
                dialog();
                break;
            case R.id.mention_state_ll:
                if (mentionStateListView.getVisibility() == View.VISIBLE) {
                    mentionStateListView.setVisibility(View.GONE);
                } else {
                    mentionStateListView.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.demand_end_time_rl:
                showEndDataDialog();
                break;
            case R.id.demand_start_time_rl:
                showStartDataDialog();
                break;
            case R.id.search_button:
               // popupWindow.dismiss();
                mDialog.dismiss();
                if (tv_pop_customer_qu.getText().toString().equals("已确定")){
                    state="confirmed";
                }else if (tv_pop_customer_qu.getText().toString().equals("待确定")){
                    state="Pending";
                }else if (tv_pop_customer_qu.getText().toString().equals("已取消")){
                    state="canceled";
                }
                Log.d("提报状态的值",state+"");
//                private String name;
//                private String cangku;
//                private String maifang;
//                private String xuqiuId;
//                private String xuqiuStart;
//                private String xuqiuEnd;
//                private String tibaoState;
                name=product_name.getText().toString();
                cangku=warehouse_name.getText().toString();
                maifang=seller_enterprise.getText().toString();
                xuqiuId=demand_number.getText().toString();
                xuqiuStart=demand_start_time.getText().toString();
                xuqiuEnd=demand_end_time.getText().toString();
                tibaoState=state;
                planQueryPresenter.getList("1","10",product_name.getText().toString(),demand_start_time.getText().toString(),demand_end_time.getText().toString()
                        ,state,seller_enterprise.getText().toString(),demand_number.getText().toString(),warehouse_name.getText().toString(),unique,true);
                break;


        }
    }
    //查询全部接口有问题,还有少个物流的接口
    private void showStartDataDialog() {
        if (demand_end_time.getText().toString().equals("") || demand_end_time.getText().toString().isEmpty())
            ShowCalendar.showDate(demand_start_time, this, false);
        else {
            Calendar d = Calendar.getInstance(Locale.CHINA);
//创建一个日历引用d，通过静态方法getInstance() 从指定时区 Locale.CHINA 获得一个日期实例
            Date myDate = new Date();
//创建一个Date实例
            d.setTime(myDate);
//设置日历的时间，把一个新建Date实例myDate传入
            int year = d.get(Calendar.YEAR);
            int month = d.get(Calendar.MONTH);
            int day = d.get(Calendar.DAY_OF_MONTH);
//获得日历中的 year month day
            DatePickerDialog dlg = new DatePickerDialog(this, new MyClick1(), year, month, day);
            DatePicker datePicker = dlg.getDatePicker();
            try {
                datePicker.setMaxDate(new SimpleDateFormat("yyyy-MM-dd").parse(demand_end_time.getText().toString())
                        .getTime());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            dlg.show();
        }
    }

    private void showEndDataDialog() {
        if (demand_start_time.getText().toString().equals("") || demand_start_time.getText().toString().isEmpty())
            ShowCalendar.showDate(demand_end_time, this, false);
        else {

            Calendar d = Calendar.getInstance(Locale.CHINA);
//创建一个日历引用d，通过静态方法getInstance() 从指定时区 Locale.CHINA 获得一个日期实例
            Date myDate = new Date();
//创建一个Date实例
            d.setTime(myDate);
//设置日历的时间，把一个新建Date实例myDate传入
            int year = d.get(Calendar.YEAR);
            int month = d.get(Calendar.MONTH);
            int day = d.get(Calendar.DAY_OF_MONTH);
//获得日历中的 year month day
            DatePickerDialog dlg = new DatePickerDialog(this, new MyClick(), year, month, day);
            DatePicker datePicker = dlg.getDatePicker();
            try {
                datePicker.setMinDate(new SimpleDateFormat("yyyy-MM-dd").parse(demand_start_time.getText().toString())
                        .getTime());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            dlg.show();
        }

    }

    @Override
    public void setPlan(String result, boolean update) {
        list_plan_order=new ArrayList<Map<String,Object>>();
        if (update) {
            list_plan_order.clear();
        }
        Log.d("测试","能不能走到这里");
        try {
            Log.d("result的值",result);
            JSONObject jsonObject=new JSONObject(result);
            String vipPlanOrder=jsonObject.getString("planOrderListlist");
            JSONArray jsonArray=new JSONArray(vipPlanOrder);
            if (jsonArray.length()>0) {
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject info = jsonArray.getJSONObject(i);
                    Log.d("info的值", "" + info.toString());
                    Map<String, Object> map = new Hashtable<String, Object>();
                    map.put("warehouseName", info.getString("warehouseName"));
                    map.put("scheduleDateChange", info.getString("scheduleDateChange"));
                    map.put("scheduleDate", info.getString("scheduleDate"));
                    map.put("demandScheduleState", info.getString("demandScheduleState"));
                    map.put("logisticsName", info.getString("logisticsName"));
                    map.put("planID", info.getString("planID"));
                    map.put("quantity", info.getString("quantity"));
                    map.put("productName", info.getString("productName"));

                    map.put("sellerEnterpriseName", info.getString("sellerEnterpriseName"));
                    if (info.toString().contains("productNameChange")) {
                        map.put("productNameChange", info.getString("productNameChange"));
                    }
                    if (info.toString().contains("logisticsChangeName")) {
                        map.put("logisticsChangeName", info.getString("logisticsChangeName"));
                    }
                    if (info.toString().contains("quantityChange")) {
                        map.put("quantityChange", info.getString("quantityChange"));
                    }
                    if (info.toString().contains("warehouseChangeName")) {
                        map.put("warehouseChangeName", info.getString("warehouseChangeName"));
                    }
                    if (info.toString().contains("preAmount")) {
                        map.put("preAmount", info.getString("preAmount"));
                    }
                    if (info.toString().contains("prePirce")) {
                        map.put("prePirce", info.getString("prePirce"));
                    }
                    if (info.toString().contains("price")) {
                        map.put("price", info.getString("price"));
                    }
                    if (info.toString().contains("amount")) {
                        map.put("amount", info.getString("amount"));
                    }
                    if (info.toString().contains("orderId")) {
                        map.put("orderId", info.getString("orderId"));
                    }
                    if (info.toString().contains("categoryName")) {//分类的值
                        map.put("categoryName", info.getString("categoryName"));
                    }
                    if (info.toString().contains("registerDate")) {
                        map.put("registerDate", info.getString("registerDate"));
                    }


                    list_plan_order.add(map);
                    Log.d("list的值", list_plan_order.size() + "aa");
                }
                Log.d("查询计划的值", list_plan_order.size() + "条");
                adapter = new PlanQueryAdapter(PlanQueryActivity.this, list_plan_order);
                // adapter.addData(list_plan_order);
                listview.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    class MyClick implements DatePickerDialog.OnDateSetListener {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            demand_end_time.setText(year + "-" + (monthOfYear+1) + "-" + dayOfMonth);
        }
    }
    class MyClick1 implements DatePickerDialog.OnDateSetListener {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            demand_start_time.setText(year + "-" + (monthOfYear+1) + "-" + dayOfMonth);
        }
    }
    private void showPopupWindow() {
        popupWindowView.measure(0,0);
        popupWindow = new PopupWindow(popupWindowView, ViewGroup.LayoutParams.MATCH_PARENT,popupWindowView.getMeasuredHeight());
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.setFocusable(true);
        popupWindow.showAsDropDown(title);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                backgroundAlpha(1.0f);
            }
        });
    }
    public void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = this.getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        this.getWindow().setAttributes(lp);
    }
    private void initPopupListView() {
        mentionStateList = new ArrayList<>();
        mentionStateList.add("已确定");
        mentionStateList.add("待确定");
        mentionStateList.add("已取消");
       // list_wuliu=new ArrayList<>();
//        new Thread(){
//            @Override
//            public void run() {
//                super.run();
//              //  getQiye();
//            }
//        }.start();
//        list_wuliu=new ArrayList<>();
//        list_wuliu.add("自提");
//        list_wuliyId=new ArrayList<>();
//        list_wuliyId.add(" ");


    }
//    public void getQiye(){
//        String url=SuMaoConstant.SUMAO_IP+"/rest/model/atg/commerce/order/demand/DemandActor/directEnterpriseDetailStore";
//        RequestParams rp=new RequestParams(url);
//        rp.addParameter("_dynSessConf",unique);
//        x.http().post(rp, new Callback.CommonCallback<String>() {
//            @Override
//            public void onSuccess(String result) {
//                Log.d("上游资源的返回值",result);
//                try {
//                    JSONObject jsonObject=new JSONObject(result);
//                    String enterprisesItem=jsonObject.getString("enterprisesItem");
//                    JSONArray jsonArray=new JSONArray(enterprisesItem);
//                    list_qiye=new ArrayList<>();
//                    list_qiyeId=new ArrayList<String>();
//                    for (int i=0;i<jsonArray.length();i++){
//                        JSONObject info=jsonArray.getJSONObject(i);
//                        list_qiye.add(info.getString("sellerCompanyName"));
//                        list_qiyeId.add(info.getString("sellerCompanyId"));
//                    }
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
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

   public void dialog(){
        mDialog= new Dialog(PlanQueryActivity.this);
//创建dialog
       mDialog.getWindow().setBackgroundDrawable(getResources().getDrawable(R.color.bottom_text_color_normal));

//设置背景颜色
       Window win = mDialog.getWindow();
       mDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
//获取dialog窗口
       popupWindowView = View.inflate(this, R.layout.plan_query_search_popup_window, null);
       win.setContentView(popupWindowView);
      // 提报状态
       mentionState = ((LinearLayout) popupWindowView.findViewById(R.id.mention_state_ll));
//        提报状态的ListView

       mentionStateListView = ((ListView) popupWindowView.findViewById(R.id.mention_state_lv));
       outOfPopupWindow =(LinearLayout) popupWindowView.findViewById(R.id.out_of_popupwindow);
       demand_end_time_rl =(LinearLayout) popupWindowView.findViewById(R.id.demand_end_time_rl);//结束的日历dialog
       demand_start_time_rl =(LinearLayout) popupWindowView.findViewById(R.id.demand_start_time_rl);//开始的日历dialog
       demand_end_time =(TextView) popupWindowView.findViewById(R.id.demand_end_time);
       demand_start_time =(TextView) popupWindowView.findViewById(R.id.demand_start_time);
       img_create_logistics_back= (ImageView) popupWindowView.findViewById(R.id.img_create_logistics_back);
       img_create_logistics_back.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               mDialog.dismiss();
           }
       });
       ArrayAdapter mentionStateAdapter = new ArrayAdapter(this, R.layout.item_pop_lv, R.id.tv_pop_lv, mentionStateList);
       mentionStateListView.setAdapter(mentionStateAdapter);
       mentionState.setOnClickListener(this);
       outOfPopupWindow.setOnClickListener(this);
       demand_end_time_rl.setOnClickListener(this);
       demand_start_time_rl.setOnClickListener(this);
       bt_Search= (Button) popupWindowView.findViewById(R.id.search_button);
       bt_Search.setOnClickListener(this);
       product_name= (EditText) popupWindowView.findViewById(R.id.product_name);
       warehouse_name= (EditText) popupWindowView.findViewById(R.id.warehouse_name);
       demand_number= (EditText) popupWindowView.findViewById(R.id.demand_number);
       seller_enterprise= (EditText) popupWindowView.findViewById(R.id.seller_enterprise);
       tv_pop_customer_qu= (TextView) popupWindowView.findViewById(R.id.tv_pop_customer_qu);
//        lv_qiye= (ListView) popupWindowView.findViewById(R.id.qiye_lv);
//        ArrayAdapter adapter_qiye=new ArrayAdapter(PlanQueryActivity.this,R.layout.item_pop_lv,R.id.tv_pop_lv, list_qiye);
//        lv_qiye.setAdapter(adapter_qiye);
//        lv_wuliu= (ListView) popupWindowView.findViewById(R.id.wuliu_lv);
//        ArrayAdapter adapter_wuliu=new ArrayAdapter(PlanQueryActivity.this,R.layout.item_pop_lv,R.id.tv_pop_lv,list_wuliu);
//        lv_qiye.setAdapter(adapter_wuliu);
       mentionStateListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               tv_pop_customer_qu.setText(mentionStateList.get(position).toString());
               mentionStateListView.setVisibility(View.GONE);
           }
       });
/*实例化Window*/
       WindowManager.LayoutParams lp = win.getAttributes();
       win.setGravity(Gravity.LEFT | Gravity.TOP);
/*实例化Window操作者*/
       lp.width = WindowManager.LayoutParams.MATCH_PARENT;
       lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
       mDialog.setCanceledOnTouchOutside(false);
       win.setAttributes(lp);
//设置显示的布局文件
       mDialog.show();
   }
    private void initPopupWindow() {
        popupWindowView = View.inflate(this, R.layout.plan_query_search_popup_window, null);
//        提报状态
        mentionState = ((LinearLayout) popupWindowView.findViewById(R.id.mention_state_ll));
//        提报状态的ListView

        mentionStateListView = ((ListView) popupWindowView.findViewById(R.id.mention_state_lv));
        outOfPopupWindow =(LinearLayout) popupWindowView.findViewById(R.id.out_of_popupwindow);
        demand_end_time_rl =(LinearLayout) popupWindowView.findViewById(R.id.demand_end_time_rl);//结束的日历dialog
        demand_start_time_rl =(LinearLayout) popupWindowView.findViewById(R.id.demand_start_time_rl);//开始的日历dialog
        demand_end_time =(TextView) popupWindowView.findViewById(R.id.demand_end_time);
        demand_start_time =(TextView) popupWindowView.findViewById(R.id.demand_start_time);
        ArrayAdapter mentionStateAdapter = new ArrayAdapter(this, R.layout.item_pop_lv, R.id.tv_pop_lv, mentionStateList);
        mentionStateListView.setAdapter(mentionStateAdapter);
        mentionState.setOnClickListener(this);
        outOfPopupWindow.setOnClickListener(this);
        demand_end_time_rl.setOnClickListener(this);
        demand_start_time_rl.setOnClickListener(this);
        bt_Search= (Button) popupWindowView.findViewById(R.id.search_button);
        bt_Search.setOnClickListener(this);
        product_name= (EditText) popupWindowView.findViewById(R.id.product_name);
        warehouse_name= (EditText) popupWindowView.findViewById(R.id.warehouse_name);
        demand_number= (EditText) popupWindowView.findViewById(R.id.demand_number);
        seller_enterprise= (EditText) popupWindowView.findViewById(R.id.seller_enterprise);
        tv_pop_customer_qu= (TextView) popupWindowView.findViewById(R.id.tv_pop_customer_qu);
//        lv_qiye= (ListView) popupWindowView.findViewById(R.id.qiye_lv);
//        ArrayAdapter adapter_qiye=new ArrayAdapter(PlanQueryActivity.this,R.layout.item_pop_lv,R.id.tv_pop_lv, list_qiye);
//        lv_qiye.setAdapter(adapter_qiye);
//        lv_wuliu= (ListView) popupWindowView.findViewById(R.id.wuliu_lv);
//        ArrayAdapter adapter_wuliu=new ArrayAdapter(PlanQueryActivity.this,R.layout.item_pop_lv,R.id.tv_pop_lv,list_wuliu);
//        lv_qiye.setAdapter(adapter_wuliu);
        mentionStateListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                tv_pop_customer_qu.setText(mentionStateList.get(position).toString());
                mentionStateListView.setVisibility(View.GONE);
            }
        });
//        lv_qiye.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                seller_enterprise.setText(list_qiye.get(position).toString());
//                lv_qiye.setVisibility(View.GONE);
//                qiye=list_qiyeId.get(position).toString();
//            }
//        });
//        lv_wuliu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                demand_number.setText(list_wuliu.get(position).toString());
//                lv_wuliu.setVisibility(View.GONE);
//                wuliu=list_wuliyId.get(position).toString();
//            }
//        });
    }



    //还没有刷新呢

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
                    planQueryPresenter.getList(pageNum,"10","","","","","","","",unique,updateOrLoad);
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
                    planQueryPresenter.getList(pageNum,"10",name,xuqiuStart,xuqiuEnd
                            ,tibaoState,maifang,xuqiuId,cangku,unique,true);
                  //  planQueryPresenter.getList(pageNum,"10","","","","","","","",unique,updateOrLoad);
                    pullToRefreshLayout.refreshFinish(PullToRefreshLayout.SUCCEED);
                }
            }.sendEmptyMessageDelayed(0, 3000);
        }
    }

}

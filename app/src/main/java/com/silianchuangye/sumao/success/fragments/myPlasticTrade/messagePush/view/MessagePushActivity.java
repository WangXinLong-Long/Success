package com.silianchuangye.sumao.success.fragments.myPlasticTrade.messagePush.view;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.BitmapDrawable;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ActionBarOverlayLayout;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
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
import com.jingchen.pulltorefresh.PullableListView;
import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.ShangYou.SeePlanMVP.presenter.SeePlanPresenter;
import com.silianchuangye.sumao.success.fragments.homepage.auction.AuctionMVP.bean.ListBean;
import com.silianchuangye.sumao.success.fragments.homepage.goodInStock.GoodsInStockActivityMVP.bean.GoodsInStockActivityBean;
import com.silianchuangye.sumao.success.fragments.homepage.goodInStock.goodsInStockDetail.GoodsInStockDetailActivityMVP.bean.GoodsInStockDetailBean;
import com.silianchuangye.sumao.success.fragments.homepage.goodInStock.goodsInStockDetail.GoodsInStockDetailActivityMVP.view.GoodsInStockDetailActivity;
import com.silianchuangye.sumao.success.fragments.homepage.groupbuying.GroupBuyingMVP.adapter.GroupBuyingListViewAdapter;
import com.silianchuangye.sumao.success.fragments.homepage.preSale.PreSaleDetailActivityMVP.bean.PreSaleDetailBean;
import com.silianchuangye.sumao.success.fragments.homepage.preSale.PreSaleDetailActivityMVP.view.PreSaleDetailActivity;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.firmInfomation.decoding.Intents;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.messagePush.adapter.MessagePushListViewAdapter;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.messagePush.bean.MessagePush;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.messagePush.presenter.MessagePushPresenter;
import com.silianchuangye.sumao.success.utils.Loding;
import com.silianchuangye.sumao.success.utils.MarqueeView;
import com.silianchuangye.sumao.success.utils.ShowCalendar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MessagePushActivity extends AppCompatActivity implements View.OnClickListener ,IMessagePushView, AdapterView.OnItemClickListener{
    private ImageView iv_Back;
    private ImageView iv_Search;
    private ListView pullList;
   // private List<Map<String,String>> list;
    private PullToRefreshLayout layoutPull;
    private MessagePushPresenter messagePushPresenter;
    private List<MessagePush.NoticeList> noticeList;
    private MessagePushListViewAdapter adapter;
    private ImageView img_pop_back;
    private TextView tv_pop_canle;
    private TextView start_pop_date, end_pop_date, tv_state_pop;
    private EditText tv_pop_peisong;
    private EditText edt_pop_name, edt_pop_cangku;
    private ListView lv_start_date, lv_end_date, lv_state, lv_peisong;
    private LinearLayout linear2, linear_shi, linear_qu, linear_sheng;
    private Button btn_pop_customer_search;
    private List<String> popList;
    private List<String> planStateList;
    private List<String> planStateEnglish;
    private List<String> logisticsNameList;
    private ArrayAdapter pop_adapter;
    String productName = "productName";//通知编号
    String planStartDate = "";//开始日
    String planEndDate = "";//结束日
    String planState = "";//提报状态
    String warehouseName = "";//仓库名字
    String logisticsID = "";//物流ID
    private List<String> logisticsNameIDList;
    private PopupWindow popupWindow;
    private String sendId="";
    private RelativeLayout title;


   // private GroupBuyingListViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_push);
        //messageresenter=new MessagePushPresenter();
        messagePushPresenter=new MessagePushPresenter(this);
        //thismessagePushPresenter=new MessagePushPresenter();
        title= (RelativeLayout) findViewById(R.id.title);
        iv_Back= (ImageView) findViewById(R.id.img_create_logistics_back);
        iv_Back.setOnClickListener(this);
        iv_Search= (ImageView) findViewById(R.id.img_create_logistics_search);
        iv_Search.setOnClickListener(this);
        initDate();
        layoutPull= (PullToRefreshLayout) findViewById(R.id.layoutPull);
        //pullList= (PullableListView) findViewById(R.id.pullList);
        pullList=((ListView)layoutPull.getPullableView());
        noticeList=new ArrayList<>();


       //  adapter.addData(list);


        addData();
        layoutPull.setOnPullListener(new GroupBuyingListViewPullListener());
        pullList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(MessagePushActivity.this,MessageDetailsActivity.class);
                intent.putExtra("sendId",sendId);
                startActivity(intent);

            }
        });


    }
    /**
     * 添加数据
     */
    public void addData() {
        SharedPreferences sp=getSharedPreferences("sumao", Activity.MODE_PRIVATE);
        String unique=sp.getString("unique","");
        boolean updateOrLoad = true;
        //Loding.show(MessagePushActivity.this, "加载中...", false, null);
        //groupBuyingPresenter.getGroupBuyingResult("1",updateOrLoad);
        //messagePushPresenter.getMessagePushResult("1",updateOrLoad,unique);
        messagePushPresenter.getMessagePushForselect("1",updateOrLoad,unique,"","","","","");
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_create_logistics_back:
                MessagePushActivity.this.finish();
                break;
            case R.id.img_create_logistics_search:
                //弹出popupwindow
                iv_Search.setVisibility(View.GONE);
                showPop();
                //backgroundAlpha(0.5f);
                break;
            case R.id.linear_sheng:
//                ShowLv(lv_start_date);
//                ShowCalendar.showDate(start_pop_date, this, false);
                if (end_pop_date.getText().toString().equals("") || end_pop_date.getText().toString().isEmpty())
                    ShowCalendar.showDate(start_pop_date, this, false);
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
                        datePicker.setMaxDate(new SimpleDateFormat("yyyy-MM-dd").parse(end_pop_date.getText().toString())
                                .getTime());
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    dlg.show();
                }
                HideLv(lv_end_date);
                HideLv(lv_peisong);
                HideLv(lv_state);
                break;
            case R.id.linear_shi:
//                ShowLv(lv_end_date);

                if (start_pop_date.getText().toString().equals("") || start_pop_date.getText().toString().isEmpty())
                    ShowCalendar.showDate(end_pop_date, this, false);
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
                        datePicker.setMinDate(new SimpleDateFormat("yyyy-MM-dd").parse(start_pop_date.getText().toString())
                                .getTime());
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    dlg.show();
                }


                HideLv(lv_start_date);
                HideLv(lv_peisong);
                HideLv(lv_state);
                break;
            case R.id.mention_state_ll:
                ShowLv(lv_state);
                HideLv(lv_end_date);
                HideLv(lv_peisong);
                HideLv(lv_start_date);
                break;
//            case R.id.linear2:
//                ShowLv(lv_peisong);
//                HideLv(lv_end_date);
//                HideLv(lv_start_date);
//                HideLv(lv_state);
//                break;
            case R.id.btn_pop_customer_search:
                productName = edt_pop_name.getText().toString();//通知编号
                planStartDate = start_pop_date.getText().toString();//开始日
                planEndDate = end_pop_date.getText().toString();//结束日
                if (!(tv_state_pop.getText().toString().equals("") || tv_state_pop.getText().toString().isEmpty()))
                    planState = tv_state_pop.getText().toString();
                else
                    planState = "";
                warehouseName = tv_pop_peisong.getText().toString();//仓库名字
                Log.d("传的值",productName+planStartDate+planEndDate+planState+warehouseName+"是什么");
                //messagePushPresenter.getMessagePushForselect("1",true,);
                SharedPreferences sp=getSharedPreferences("sumao",Activity.MODE_PRIVATE);
                String unique=sp.getString("unique","");
                boolean updateLoad=true;
                messagePushPresenter.getMessagePushForselect("1",updateLoad,unique,productName,warehouseName,planState,planStartDate,planEndDate);
                popupWindow.dismiss();
                iv_Search.setVisibility(View.VISIBLE);
                break;

        }

    }
    private void showPop() {
        View popView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.pop_message, null);
        popupWindow = new PopupWindow(findViewById(R.id.layout_find), ActionBarOverlayLayout.LayoutParams.MATCH_PARENT, ActionBarOverlayLayout.LayoutParams.WRAP_CONTENT);
        TextView tv_name= (TextView) popView.findViewById(R.id.name);
//        ImageView img_create_logistics_back1= (ImageView) popView.findViewById(R.id.img_create_logistics_back1);
//        img_create_logistics_back1.setOnClickListener(new View.OnClickListener() {
//                                                          @Override
//                                                          public void onClick(View v) {
//                                                              popupWindow.dismiss();
//                                                          }
//                                                      }
//        );

        tv_name.setText("编号通知:");
//        TextView tv_create_logistics_search= (TextView) popView.findViewById(R.id.tv_create_logistics_search);
//        tv_create_logistics_search.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                popupWindow.dismiss();
//            }
//        });

        TextView tv_Time= (TextView) popView.findViewById(R.id.Time);
        tv_Time.setText("发送时间:");
        TextView tv_sheng= (TextView) popView.findViewById(R.id.sheng);
        tv_sheng.setText("至");
        TextView tv_state= (TextView) popView.findViewById(R.id.state);
        tv_state.setText("通知类型:");
//        TextView tv_cangku= (TextView) popView.findViewById(R.id.cangku);
//        tv_cangku.setText("仓库名称:");
        TextView tv_way= (TextView) popView.findViewById(R.id.way);
        tv_way.setText("通知标题:");

       // tv_pop_canle = (TextView) popView.findViewById(R.id.tv_create_logistics_search);
        //img_pop_back = (ImageView) popView.findViewById(R.id.img_create_logistics_back1);
        edt_pop_name = (EditText) popView.findViewById(R.id.tv_pop_customer_num);//产品名称
        edt_pop_cangku = (EditText) popView.findViewById(R.id.tv_pop_customer_name);//仓库名称
        start_pop_date = (TextView) popView.findViewById(R.id.demand_start_time);//计划开始日期
        end_pop_date = (TextView) popView.findViewById(R.id.demand_end_time);//计划结束日期
        tv_state_pop = (TextView) popView.findViewById(R.id.tv_pop_customer_qu);//提报状态
        tv_pop_peisong = (EditText) popView.findViewById(R.id.tv_pop_customer_zhuangtai);//配送方式
        lv_start_date = (ListView) popView.findViewById(R.id.lv_sheng);//开始日期下
        lv_end_date = (ListView) popView.findViewById(R.id.lv_shi);//结束日期下的
        lv_state = (ListView) popView.findViewById(R.id.lv_qu);//提报状态下
        lv_peisong = (ListView) popView.findViewById(R.id.lv_zhuangtai);//配送方式下
        btn_pop_customer_search = (Button) popView.findViewById(R.id.btn_pop_customer_search);//立即查询
        linear2 = (LinearLayout) popView.findViewById(R.id.linear2);
        linear_sheng = (LinearLayout) popView.findViewById(R.id.linear_sheng);
        linear_shi = (LinearLayout) popView.findViewById(R.id.linear_shi);
        linear_qu = (LinearLayout) popView.findViewById(R.id.mention_state_ll);
       // img_pop_back.setOnClickListener(this);
        //tv_pop_canle.setOnClickListener(this);
        linear2.setOnClickListener(this);
        linear_sheng.setOnClickListener(this);
        linear_shi.setOnClickListener(this);
        linear_qu.setOnClickListener(this);
       // lv_peisong.setOnItemClickListener(this);
        lv_state.setOnItemClickListener(this);
        lv_end_date.setOnItemClickListener(this);
        lv_start_date.setOnItemClickListener(this);
        btn_pop_customer_search.setOnClickListener(this);
        popupWindow.setContentView(popView);
        popupWindow.setTouchable(true);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.setOutsideTouchable(true);
        popupWindow.setFocusable(true);

       // popupWindow.showAtLocation(iv_Search, Gravity.TOP, 0, 0);
        popupWindow.showAsDropDown(title,0,0);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                backgroundAlpha(1f);
                iv_Search.setVisibility(View.VISIBLE);
            }
        });



    }
    private void initDate() {
        popList = new ArrayList<String>();
        planStateList = new ArrayList<>();
//        confirmed:已确定，Pending: 待确定，canceled:已取消
        planStateList.add("塑贸通知");
        planStateList.add("企业信息修改");
      //  planStateList.add("已取消");
        planStateEnglish = new ArrayList<>();
        planStateEnglish.add("0");
        planStateEnglish.add("1");

        logisticsNameList = new ArrayList<>();
        logisticsNameList.add("卖家配送");
        logisticsNameList.add("自提");
//        logisticsNameIDList = new ArrayList<>();
//        //TODO   这里需要修改
//        logisticsNameIDList.add("250072");
//        logisticsNameIDList.add("250072");
    }
    private void ShowLv(ListView lv) {
        if (lv == lv_state) {
            popList.clear();
            popList.addAll(planStateList);
        }
        lv.setVisibility(View.VISIBLE);
        pop_adapter = new ArrayAdapter<String>(this, R.layout.item_pop_lv, R.id.tv_pop_lv, popList);
        lv.setAdapter(pop_adapter);
    }

    private void HideLv(ListView lv) {
        lv.setVisibility(View.GONE);
    }


    public void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = this.getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        this.getWindow().setAttributes(lp);
    }

    class MyClick implements DatePickerDialog.OnDateSetListener {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            end_pop_date.setText(year + "-" + (monthOfYear+1) + "-" + dayOfMonth);
        }
    }

    class MyClick1 implements DatePickerDialog.OnDateSetListener {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            start_pop_date.setText(year + "-" + (monthOfYear+1) + "-" + dayOfMonth);
        }
    }
    @Override
    public void setMessagePushResult(String result, boolean UpdateorLoad) {
        Loding.dis();
        iv_Search.setVisibility(View.VISIBLE);
        if (UpdateorLoad) {
            noticeList.clear();
        }
        Gson gson = new Gson();
//        GroupBuyingListBean groupBuyingListBean = gson.fromJson(result, GroupBuyingListBean.class);
//        list.addAll(groupBuyingListBean.getProd());
        Log.d("result的值",result);
        if (result.contains("title")){
            MessagePush messagePush=gson.fromJson(result,MessagePush.class);
            Log.d("查看list的是否有值",messagePush.getNoticeList().toString());

            noticeList.addAll(messagePush.getNoticeList());
            Log.d("list的列表","list列表"+noticeList.size());
            adapter=new MessagePushListViewAdapter(this,noticeList);
            pullList.setAdapter(adapter);
//        adapter.clearData();
//        adapter.addData(noticeList);
            adapter.notifyDataSetChanged();

        }else{
            noticeList.clear();
            adapter.notifyDataSetChanged();
            Toast.makeText(MessagePushActivity.this, "查不到该商品的有关信息", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()) {
            case R.id.lv_sheng:
                start_pop_date.setText(popList.get(position).toString());
                HideLv(lv_start_date);
                break;
            case R.id.lv_shi:
                end_pop_date.setText(popList.get(position).toString());
                HideLv(lv_end_date);
                break;
            case R.id.lv_qu:
                tv_state_pop.setText(popList.get(position).toString());
                HideLv(lv_state);
                break;
//            case R.id.lv_zhuangtai:
//                tv_pop_peisong.setText(popList.get(position).toString());
//                HideLv(lv_peisong);
//                break;
        }
    }


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
                    //groupBuyingPresenter.getGroupBuyingResult(pageNum,updateOrLoad);
                    //messagePushPresenter.getMessagePushResult(pageNum,updateOrLoad,unique);
                  //  messagePushPresenter.getMessagePushForselect();
                    messagePushPresenter.getMessagePushForselect("1",updateOrLoad,unique,"","","","","");
                    //messagePushPresenter.getMessagePushForselect("","","","","","1",updateOrLoad,unique);
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
                    messagePushPresenter.getMessagePushResult(pageNum,true,unique);
                    //groupBuyingPresenter.getGroupBuyingResult(pageNum,updateOrLoad);
                    pullToRefreshLayout.refreshFinish(PullToRefreshLayout.SUCCEED);
                }
            }.sendEmptyMessageDelayed(0, 3000);
        }
    }




}

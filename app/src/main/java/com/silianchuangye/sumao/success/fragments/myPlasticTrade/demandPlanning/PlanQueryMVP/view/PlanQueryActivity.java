package com.silianchuangye.sumao.success.fragments.myPlasticTrade.demandPlanning.PlanQueryMVP.view;

import android.app.DatePickerDialog;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jingchen.pulltorefresh.PullToRefreshLayout;
import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.demandPlanning.PlanQueryMVP.adapter.PlanQueryAdapter;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.demandPlanning.PlanQueryMVP.bean.PlanQueryBean;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.demandPlanning.PlanQueryMVP.presenter.PlanQueryPresenter;
import com.silianchuangye.sumao.success.utils.ShowCalendar;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan_query);
        planQueryPresenter = new PlanQueryPresenter(this);
        pullToRefreshLayout = ((PullToRefreshLayout) findViewById(R.id.refresh_view));
        listview = ((ListView) pullToRefreshLayout.getPullableView());
        PlanQueryAdapter planQueryAdapter = new PlanQueryAdapter(this);
        listview.setAdapter(planQueryAdapter);
        initData(planQueryAdapter);
        title = ((RelativeLayout) findViewById(R.id.title));
        search = ((ImageView) findViewById(R.id.img_create_logistics_search));
        search.setOnClickListener(this);
    }

    private void initData(PlanQueryAdapter planQueryAdapter) {
        List<PlanQueryBean> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            PlanQueryBean planQueryBean = new PlanQueryBean();
            planQueryBean.setProduct_name_content("产品名称" + i);
            planQueryBean.setClassification_content("分类" + i);
            planQueryBean.setDate_of_presentation_num("2017/2/1" + i);
            planQueryBean.setDemand_date_num("2017/2/30");
            if (i % 2 == 0) {
                planQueryBean.setOrderOrdemand("需求编号:");
            } else {
                planQueryBean.setOrderOrdemand("订单编号:");
            }
            planQueryBean.setOrderOrdemandNum("20170213" + i);
            list.add(planQueryBean);
        }
        planQueryAdapter.addData(list);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_create_logistics_search:
                initPopupListView();
                initPopupWindow();
                showPopupWindow();
                break;
            case R.id.mention_state_ll:
                if (mentionStateListView.getVisibility() == View.VISIBLE) {
                    mentionStateListView.setVisibility(View.GONE);
                } else {
                    mentionStateListView.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.out_of_popupwindow:
                popupWindow.dismiss();
                break;
            case R.id.demand_end_time_rl:
                showEndDataDialog();
                break;
            case R.id.demand_start_time_rl:
                showStartDataDialog();
                break;

        }
    }

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
    class MyClick implements DatePickerDialog.OnDateSetListener {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            demand_end_time.setText(year + "-" + monthOfYear + "-" + dayOfMonth);
        }
    }
    class MyClick1 implements DatePickerDialog.OnDateSetListener {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            demand_start_time.setText(year + "-" + monthOfYear + "-" + dayOfMonth);
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
    }
}

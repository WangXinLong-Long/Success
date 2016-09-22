package com.silianchuangye.sumao.success.ShangYou.CaiGouMVP.view;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
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
import android.widget.TextView;

import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.ShangYou.CaiGouMVP.bean.CaiGouBean;
import com.silianchuangye.sumao.success.ShangYou.CaiGouMVP.presenter.CaiGouPresenter;
import com.silianchuangye.sumao.success.ShangYou.DayPlanInfo;
import com.silianchuangye.sumao.success.ShangYou.PlanDayMVP.view.PlanDay;
import com.silianchuangye.sumao.success.ShangYou.SeePlanMVP.presenter.SeePlanPresenter;
import com.silianchuangye.sumao.success.ShangYou.SeePlanMVP.view.SeePlan;
import com.silianchuangye.sumao.success.adapter.MyPageAdapter;
import com.silianchuangye.sumao.success.custom.customCalendar.CalendarUtil;
import com.silianchuangye.sumao.success.fragments.homepage.UpstreamDirectSellingMVP.bean.vipProductBean.VipProductBean;
import com.silianchuangye.sumao.success.utils.LogUtils;
import com.silianchuangye.sumao.success.utils.ShowCalendar;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class CaiGou extends AppCompatActivity implements OnClickListener, AdapterView.OnItemClickListener, ICaiGouView {
    private ArrayList<Fragment> listFragment;
    private TabLayout tlDemo;
    private ViewPager vpDemo;
    private MyPageAdapter adapter;
    private ImageView img_create_logistics_back;
    private ImageView img_create_logistics_search;
    private PopupWindow popupWindow;
    private View popView;
    private ImageView img_pop_back;
    private TextView tv_pop_canle;
    private TextView start_pop_date, end_pop_date, tv_state_pop, tv_pop_peisong;
    private EditText edt_pop_name, edt_pop_cangku;
    private ListView lv_start_date, lv_end_date, lv_state, lv_peisong;
    private LinearLayout linear2, linear_shi, linear_qu, linear_sheng;
    private Button btn_pop_customer_search;
    private List<String> popList;
    private ArrayAdapter pop_adapter;
    private VipProductBean vipProductBean;
    private CaiGouPresenter presenter;
    private String sellerCompanyId;
    private CaiGouBean caiGouBeans;
    private PlanDay plan;
    private SeePlan see;
    private String sessionID;
    private List<String> planStateList;
    private List<String> planStateEnglish;
    private List<String> logisticsNameList;

    String pageNum = "1"; //页数
    String pageSize = "10";//每页条数
    String sellerEnterpriseId;//上游资源方ID
    //    String _dynSessConf = getActivity().getSharedPreferences("sumao",getActivity().MODE_PRIVATE).getString("unique",""); //sessionID
    String productName = "";//商品名字
    String planStartDate = "";//开始日
    String planEndDate = "";//结束日
    String planState = "";//提报状态
    String warehouseName = "";//仓库名字
    String logisticsID = "";//物流ID
    private List<String> logisticsNameIDList;
    private Bundle bundle1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cai_gou);
        Intent intent = getIntent();
        vipProductBean = (VipProductBean) intent.getSerializableExtra("vipProductBean");
        sellerCompanyId = intent.getStringExtra("sellerCompanyId");
        initDate();
        img_create_logistics_back = (ImageView) findViewById(R.id.img_create_logistics_back);
        img_create_logistics_search = (ImageView) findViewById(R.id.img_create_logistics_search);
        img_create_logistics_search.setOnClickListener(this);
        img_create_logistics_back.setOnClickListener(this);
        tlDemo = (TabLayout) findViewById(R.id.tlDemo_OrderPrice_activity);
        vpDemo = (ViewPager) findViewById(R.id.vpDemo_OrderPrice_activity);
        listFragment = new ArrayList<Fragment>();
        plan = new PlanDay();
        see = new SeePlan();
        listFragment.add(plan);
        listFragment.add(see);
        presenter = new CaiGouPresenter(this);
        sessionID = getSharedPreferences("sumao", MODE_PRIVATE).getString("unique", "");
        presenter.requestSeePlanDate("1", "10", sellerCompanyId, sessionID, "", "", "", "", "", "");

    }

    private void initDate() {
        popList = new ArrayList<String>();
        planStateList = new ArrayList<>();
//        confirmed:已确定，Pending: 待确定，canceled:已取消
        planStateList.add("已确定");
        planStateList.add("待确定");
        planStateList.add("已取消");
        planStateEnglish = new ArrayList<>();
        planStateEnglish.add("confirmed");
        planStateEnglish.add("Pending");
        planStateEnglish.add("canceled");
        logisticsNameList = new ArrayList<>();
        logisticsNameList.add("卖家配送");
        logisticsNameList.add("自提");
        logisticsNameIDList = new ArrayList<>();
        //TODO   这里需要修改
        logisticsNameIDList.add("250072");
        logisticsNameIDList.add("250072");
    }

    private void initView() {
        LogUtils.log("initView-->走你");


        bundle1 = new Bundle();
        bundle1.putSerializable("caiGouBeans", caiGouBeans);
        bundle1.putString("sellerCompanyId", sellerCompanyId);
        bundle1.putString("sessionID", sessionID);
        bundle1.putString("productName", productName);
        bundle1.putString("planStartDate", planStartDate);
        bundle1.putString("planEndDate", planEndDate);
        bundle1.putString("planState", planState);
        bundle1.putString("warehouseName", warehouseName);
        bundle1.putString("logisticsID", logisticsID);

        see.setArguments(bundle1);
        Bundle bundle2 = new Bundle();
        bundle2.putSerializable("vipProductBean", vipProductBean);
        plan.setArguments(bundle2);

        adapter = new MyPageAdapter(getSupportFragmentManager());
        adapter.setData(listFragment);

        ArrayList<String> listString = new ArrayList<String>();
        listString.add("日计划提报");
        listString.add("查看计划");

        adapter.setTitles(listString);


        vpDemo.setAdapter(adapter);
        tlDemo.setupWithViewPager(vpDemo);
        img_create_logistics_search.setVisibility(View.GONE);
        tlDemo.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tlDemo.getSelectedTabPosition() == 0) {
                    img_create_logistics_search.setVisibility(View.GONE);
                    vpDemo.setCurrentItem(0);
                } else {
                    img_create_logistics_search.setVisibility(View.VISIBLE);
                    vpDemo.setCurrentItem(1);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        popView = View.inflate(this, R.layout.plan_popwindow, null);
        tv_pop_canle = (TextView) popView.findViewById(R.id.tv_create_logistics_search);
        img_pop_back = (ImageView) popView.findViewById(R.id.img_create_logistics_back1);
        edt_pop_name = (EditText) popView.findViewById(R.id.tv_pop_customer_num);//产品名称
        edt_pop_cangku = (EditText) popView.findViewById(R.id.tv_pop_customer_name);//仓库名称
        start_pop_date = (TextView) popView.findViewById(R.id.tv_pop_customer_sheng);//计划开始日期
        end_pop_date = (TextView) popView.findViewById(R.id.tv_pop_customer_shi);//计划结束日期
        tv_state_pop = (TextView) popView.findViewById(R.id.tv_pop_customer_qu);//提报状态
        tv_pop_peisong = (TextView) popView.findViewById(R.id.tv_pop_customer_zhuangtai);//配送方式
        lv_start_date = (ListView) popView.findViewById(R.id.lv_sheng);//开始日期下
        lv_end_date = (ListView) popView.findViewById(R.id.lv_shi);//结束日期下的
        lv_state = (ListView) popView.findViewById(R.id.lv_qu);//提报状态下
        lv_peisong = (ListView) popView.findViewById(R.id.lv_zhuangtai);//配送方式下
        btn_pop_customer_search = (Button) popView.findViewById(R.id.btn_pop_customer_search);//立即查询
        linear2 = (LinearLayout) popView.findViewById(R.id.linear2);
        linear_sheng = (LinearLayout) popView.findViewById(R.id.linear_sheng);
        linear_shi = (LinearLayout) popView.findViewById(R.id.linear_shi);
        linear_qu = (LinearLayout) popView.findViewById(R.id.linear_qu);
        img_pop_back.setOnClickListener(this);
        tv_pop_canle.setOnClickListener(this);
        linear2.setOnClickListener(this);
        linear_sheng.setOnClickListener(this);
        linear_shi.setOnClickListener(this);
        linear_qu.setOnClickListener(this);
        lv_peisong.setOnItemClickListener(this);
        lv_state.setOnItemClickListener(this);
        lv_end_date.setOnItemClickListener(this);
        lv_start_date.setOnItemClickListener(this);
        btn_pop_customer_search.setOnClickListener(this);
        LogUtils.log("走完initView");
    }


    private void ShowLv(ListView lv) {
        if (lv == lv_state) {
            popList.clear();
            popList.addAll(planStateList);
        } else if (lv == lv_peisong) {
            popList.clear();
            popList.addAll(logisticsNameList);
        }
        lv.setVisibility(View.VISIBLE);
        pop_adapter = new ArrayAdapter<String>(this, R.layout.item_pop_lv, R.id.tv_pop_lv, popList);
        lv.setAdapter(pop_adapter);
    }

    private void HideLv(ListView lv) {
        lv.setVisibility(View.GONE);
    }

    private void showPop() {

        int w = getWindowManager().getDefaultDisplay().getWidth();
        popView.measure(0, 0);
        popupWindow = new PopupWindow(popView, w, popView.getMeasuredHeight());
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.setFocusable(true);
        popupWindow.showAtLocation(img_create_logistics_search, Gravity.TOP, 0, 0);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                backgroundAlpha(1f);
            }
        });
    }

    public void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = this.getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        this.getWindow().setAttributes(lp);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_create_logistics_back:
                finish();
                break;
            case R.id.img_create_logistics_search:
                showPop();
                backgroundAlpha(0.5f);
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
                    DatePickerDialog dlg = new DatePickerDialog(this, new MyClick1(), year, month-1, day);
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
            case R.id.linear_qu:
                ShowLv(lv_state);
                HideLv(lv_end_date);
                HideLv(lv_peisong);
                HideLv(lv_start_date);
                break;
            case R.id.linear2:
                ShowLv(lv_peisong);
                HideLv(lv_end_date);
                HideLv(lv_start_date);
                HideLv(lv_state);
                break;
            case R.id.btn_pop_customer_search:
                productName = edt_pop_name.getText().toString();//商品名字
                planStartDate = start_pop_date.getText().toString();//开始日
                planEndDate = end_pop_date.getText().toString();//结束日
                if (!(tv_state_pop.getText().toString().equals("") || tv_state_pop.getText().toString().isEmpty()))
                    planState = planStateEnglish.get(planStateList.indexOf(tv_state_pop.getText().toString()));//提报状态
                else
                    planState = "";
                warehouseName = edt_pop_cangku.getText().toString();//仓库名字
                if (!(tv_pop_peisong.getText().toString().equals("") || tv_pop_peisong.getText().toString().isEmpty()))
                    logisticsID = logisticsNameIDList.get(logisticsNameList.indexOf(tv_pop_peisong.getText().toString()));//物流ID
                else
                    logisticsID = "";
//                see
                new SeePlanPresenter(see).requestSeePlanDate("1", "10", sellerCompanyId, sessionID, productName, planStartDate, planEndDate, planState, warehouseName, logisticsID);

//                presenter.requestSeePlanDate("1","10",sellerCompanyId,sessionID,productName,planStartDate,planEndDate,planState,warehouseName,logisticsID);
                popupWindow.dismiss();
                break;
            case R.id.tv_create_logistics_search:
                popupWindow.dismiss();
                break;
            case R.id.img_create_logistics_back1:
                popupWindow.dismiss();
                break;
        }
    }

    class MyClick implements DatePickerDialog.OnDateSetListener {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            end_pop_date.setText(year + "-" + monthOfYear + "-" + dayOfMonth);
        }
    }
    class MyClick1 implements DatePickerDialog.OnDateSetListener {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            start_pop_date.setText(year + "-" + monthOfYear + "-" + dayOfMonth);
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
            case R.id.lv_zhuangtai:
                tv_pop_peisong.setText(popList.get(position).toString());
                HideLv(lv_peisong);
                break;
        }
    }

    @Override
    public void setSeePlanInFragment(CaiGouBean caiGouBean) {
        caiGouBeans = caiGouBean;
        initView();
    }


}

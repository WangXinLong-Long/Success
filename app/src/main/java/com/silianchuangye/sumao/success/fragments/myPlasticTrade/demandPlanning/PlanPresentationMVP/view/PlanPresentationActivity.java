package com.silianchuangye.sumao.success.fragments.myPlasticTrade.demandPlanning.PlanPresentationMVP.view;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.Toast;

import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.ShangYou.DayPlanInfo;
import com.silianchuangye.sumao.success.adapter.DayPlanAdapter;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.demandPlanning.PlanPresentationMVP.presenter.PlanPresentationPresenter;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * 计划提报
 */
public class PlanPresentationActivity extends AppCompatActivity implements View.OnClickListener,IPlanPresentationView{

    private ExpandableListView lv_day_plan;
    private Button btn_day_plan_add;
    private Button btn_day_plan_tijiao;
    private ArrayList<String> group;
    private ArrayList<List<DayPlanInfo>> list;
    private DayPlanAdapter adapter;
    private ArrayList<DayPlanInfo> lists;
    private int groupPositionPublic;
    private boolean flag;
    private DayPlanAlert alert;
    private PlanPresentationPresenter planPresentationPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan_presentation);
        planPresentationPresenter = new PlanPresentationPresenter(this);
        lv_day_plan = (ExpandableListView) findViewById(R.id.lv_day_plan);
        btn_day_plan_add = (Button) findViewById(R.id.btn_day_plan_add);
        btn_day_plan_tijiao = (Button) findViewById(R.id.btn_day_plan_tijiao);
        btn_day_plan_tijiao.setOnClickListener(this);
        btn_day_plan_add.setOnClickListener(this);
        group = new ArrayList<>();
        list = new ArrayList<List<DayPlanInfo>>();
        initDate();
        adapter = new DayPlanAdapter(list, PlanPresentationActivity.this, group);

        lv_day_plan.setAdapter(adapter);
        for (int i = 0; i < group.size(); i++) {
            lv_day_plan.expandGroup(i);
        }
        lv_day_plan.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {

            @Override
            public boolean onGroupClick(ExpandableListView parent, View v,
                                        int groupPosition, long id) {
                return true;
            }
        });

        lv_day_plan.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                if (adapter.getChild(groupPosition, childPosition).getLevel().equals("需求日期")) {
                    groupPositionPublic = groupPosition;
                    onClickPlanDate(groupPosition);
                } else if (adapter.getChild(groupPosition, childPosition).getLevel().equals("产品名称")) {
                    Toast.makeText(PlanPresentationActivity.this, "产品名称", Toast.LENGTH_SHORT).show();
                } else if (adapter.getChild(groupPosition, childPosition).getLevel().equals("数量（吨）")) {
                    Toast.makeText(PlanPresentationActivity.this, "数量", Toast.LENGTH_SHORT).show();
                } else if (adapter.getChild(groupPosition, childPosition).getLevel().equals("仓库")) {
                    Toast.makeText(PlanPresentationActivity.this, "仓库", Toast.LENGTH_SHORT).show();
                } else if (adapter.getChild(groupPosition, childPosition).getLevel().equals("配送方式")) {
                    Toast.makeText(PlanPresentationActivity.this, "配送方式", Toast.LENGTH_SHORT).show();
                } else if (adapter.getChild(groupPosition, childPosition).getLevel().equals("备注")) {
                    Toast.makeText(PlanPresentationActivity.this, "备注", Toast.LENGTH_SHORT).show();
                } else if (adapter.getChild(groupPosition, childPosition).getLevel().equals("")) {
                    Toast.makeText(PlanPresentationActivity.this, "删除的是：" + groupPosition + "组。" + "--childPosition" + childPosition + "位置", Toast.LENGTH_SHORT).show();
                    list.remove(groupPosition);
                    group.remove(groupPosition);
                    adapter.notifyDataSetChanged();
                }

                return true;
            }
        });
        alert = new DayPlanAlert(this);
    }

    private void initDate() {
            lists = new ArrayList<DayPlanInfo>();
            lists.add(new DayPlanInfo("卖方企业", "", "~~"));
            lists.add(new DayPlanInfo("需求日期", "", "~~"));
            lists.add(new DayPlanInfo("产品名称", "", "~~"));
            lists.add(new DayPlanInfo("仓库", "", "~~"));
            lists.add(new DayPlanInfo("购买价格(元/吨)", "", "~~"));
            lists.add(new DayPlanInfo("购买数量(吨)", "", "~~"));
            lists.add(new DayPlanInfo("配送方式", "", "~~"));
            lists.add(new DayPlanInfo("备注", "", "~~"));
            lists.add(new DayPlanInfo("", "删除计划", "~~"));
            list.add(lists);
            group.add("");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_day_plan_add:
                for (int i = 0; i < list.size(); i++) {
                    flag = false;
                    if (list.get(i).get(0).getDelete() == "" || list.get(i).get(0).getDelete().isEmpty()) {
                        TOAST("卖方企业");
                        flag = true;
                    } else if (list.get(i).get(1).getDelete() == "" || list.get(i).get(1).getDelete().isEmpty()) {
                        TOAST("需求日期");
                        flag = true;
                    } else if (list.get(i).get(2).getDelete() == "" || list.get(i).get(2).getDelete().isEmpty()) {
                        TOAST("请填写产品名称");
                        flag = true;
                    } else if (list.get(i).get(3).getDelete() == "" || list.get(i).get(3).getDelete().isEmpty()) {
                        TOAST("请选择仓库");
                        flag = true;
                    } else if (list.get(i).get(4).getDelete() == "" || list.get(i).get(4).getDelete().isEmpty()) {
                        TOAST("请选择购买价格");
                        flag = true;
                    } else if (list.get(i).get(5).getDelete() == "" || list.get(i).get(5).getDelete().isEmpty()) {
                        TOAST("请填写购买数量");
                        flag = true;
                    }else if (list.get(i).get(6).getDelete() == "" || list.get(i).get(6).getDelete().isEmpty()) {
                        TOAST("请填写配送方式");
                        flag = true;
                    }else if (list.get(i).get(7).getDelete() == "" || list.get(i).get(7).getDelete().isEmpty()) {
                        TOAST("请填写备注");
                        flag = true;
                    }
                }
                if (!flag) {


                    lists = new ArrayList<DayPlanInfo>();
                    lists.add(new DayPlanInfo("卖方企业", "", "~~"));
                    lists.add(new DayPlanInfo("需求日期", "", "~~"));
                    lists.add(new DayPlanInfo("产品名称", "", "~~"));
                    lists.add(new DayPlanInfo("仓库", "", "~~"));
                    lists.add(new DayPlanInfo("购买价格(元/吨)", "", "~~"));
                    lists.add(new DayPlanInfo("购买数量(吨)", "", "~~"));
                    lists.add(new DayPlanInfo("配送方式", "", "~~"));
                    lists.add(new DayPlanInfo("备注", "", "~~"));
                    lists.add(new DayPlanInfo("", "删除计划", "~~"));
                    list.add(lists);
                    group.add("");
                    adapter.notifyDataSetChanged();
                    for (int i = 0; i < group.size(); i++) {
                        lv_day_plan.expandGroup(i);
                    }
                }
                break;
            case R.id.btn_day_plan_tijiao:
                for (int i = 0; i < list.size(); i++) {
                    flag = false;
                    if (list.get(i).get(0).getDelete() == "" || list.get(i).get(0).getDelete().isEmpty()) {
                        TOAST("卖方企业");
                        flag = true;
                    } else if (list.get(i).get(1).getDelete() == "" || list.get(i).get(1).getDelete().isEmpty()) {
                        TOAST("需求日期");
                        flag = true;
                    } else if (list.get(i).get(2).getDelete() == "" || list.get(i).get(2).getDelete().isEmpty()) {
                        TOAST("请填写产品名称");
                        flag = true;
                    } else if (list.get(i).get(3).getDelete() == "" || list.get(i).get(3).getDelete().isEmpty()) {
                        TOAST("请选择仓库");
                        flag = true;
                    } else if (list.get(i).get(4).getDelete() == "" || list.get(i).get(4).getDelete().isEmpty()) {
                        TOAST("请选择购买价格");
                        flag = true;
                    } else if (list.get(i).get(5).getDelete() == "" || list.get(i).get(5).getDelete().isEmpty()) {
                        TOAST("请填写购买数量");
                        flag = true;
                    }else if (list.get(i).get(6).getDelete() == "" || list.get(i).get(6).getDelete().isEmpty()) {
                        TOAST("请填写配送方式");
                        flag = true;
                    }else if (list.get(i).get(7).getDelete() == "" || list.get(i).get(7).getDelete().isEmpty()) {
                        TOAST("请填写备注");
                        flag = true;
                    }
                }
                if (!flag) {
                    if (PlanPresentationActivity.this.getSharedPreferences("sumao", Context.MODE_PRIVATE).getString("unique", "").equals("false")) {
                        TOAST("请登陆后进行操作");
                        /*Intent intent = new Intent();
                        intent.setClass(getActivity(), LoginUserActivity.class);
                        intent.putExtra("roles", "buyer");
                        intent.putExtra("activity", "NotMainActivity");
                        startActivity(intent);*/
                    } else {
                        Toast.makeText(PlanPresentationActivity.this, "提交", Toast.LENGTH_SHORT).show();for (int i = 0; i < list.size(); i++) {
//                        String date = list.get(i).get(0).getDelete();
//                        String name = vipProductsIDList.get(productNameList.indexOf(list.get(i).get(1).getDelete()));
//                        String number = list.get(i).get(2).getDelete();
//                        LogUtils.log("number" + number);
//                        String ck = list.get(i).get(3).getDelete();
//                        LogUtils.log("ck" + ck);
//                        int position = wareHouseNameLists.get(i).indexOf(ck);
//                        LogUtils.log("i--->" + i);
//                        LogUtils.log("wareHouseNameLists.get(i).size()" + wareHouseNameLists.get(i).size());
//                        for (int k = 0; k < wareHouseNameLists.get(i).size(); k++) {
//                            LogUtils.log("wareHouseNameLists的元素"+k+"-->" +  wareHouseNameLists.get(i).get(k));
//                        }
//                        String wareHouse = wareHouseIdLists.get(i).get(position);
//                        String delivery = deliveryMethodIdLists.get(i).get(deliveryMethodNameLists.get(i).indexOf(list.get(i).get(4).getDelete()));
//                        String remark = list.get(i).get(5).getDelete();

                            planPresentationPresenter.setSubmitPlan(/*date, name, wareHouse, number, delivery, remark, productId, getActivity().getSharedPreferences("sumao", Context.MODE_PRIVATE).getString("unique", "")*/);

                        }
//                        alert.show();
                    }


                }
                break;
        }
    }
    public void TOAST(String s) {
        Toast.makeText(PlanPresentationActivity.this, s, Toast.LENGTH_SHORT).show();
    }
    private void onClickPlanDate(int position) {
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
        DatePickerDialog dlg = new DatePickerDialog(PlanPresentationActivity.this, new MyClick(), year, month, day);
        DatePicker datePicker = dlg.getDatePicker();
        datePicker.setMinDate(System.currentTimeMillis());
        dlg.show();
    }

    @Override
    public void getSubmitResults(String result) {

    }

    class MyClick implements DatePickerDialog.OnDateSetListener {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            list.get(groupPositionPublic).set(0, new DayPlanInfo("计划日期", "", year + "-" + monthOfYear + "-" + dayOfMonth));
            adapter.notifyDataSetChanged();
        }
    }


    class DayPlanAlert extends AlertDialog implements View.OnClickListener {
        private ImageView img;
        private Button btn_canle;
        private Button btn_tijiao;

        public DayPlanAlert(Context context) {
            super(context);
        }

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.dayplan_alert);
            img = (ImageView) findViewById(R.id.img_margin_dialog_errey);
            btn_canle = (Button) findViewById(R.id.btn_dayplan_alert_canle);
            btn_tijiao = (Button) findViewById(R.id.btn_dayplan_alert_tijiao);
            img.setOnClickListener(this);
            btn_canle.setOnClickListener(this);
            btn_tijiao.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.img_margin_dialog_errey:
                    this.dismiss();
                    break;
                case R.id.btn_dayplan_alert_canle:
                    this.dismiss();
                    break;
                case R.id.btn_dayplan_alert_tijiao:
                    for (int i = 0; i < list.size(); i++) {
//                        String date = list.get(i).get(0).getDelete();
//                        String name = vipProductsIDList.get(productNameList.indexOf(list.get(i).get(1).getDelete()));
//                        String number = list.get(i).get(2).getDelete();
//                        LogUtils.log("number" + number);
//                        String ck = list.get(i).get(3).getDelete();
//                        LogUtils.log("ck" + ck);
//                        int position = wareHouseNameLists.get(i).indexOf(ck);
//                        LogUtils.log("i--->" + i);
//                        LogUtils.log("wareHouseNameLists.get(i).size()" + wareHouseNameLists.get(i).size());
//                        for (int k = 0; k < wareHouseNameLists.get(i).size(); k++) {
//                            LogUtils.log("wareHouseNameLists的元素"+k+"-->" +  wareHouseNameLists.get(i).get(k));
//                        }
//                        String wareHouse = wareHouseIdLists.get(i).get(position);
//                        String delivery = deliveryMethodIdLists.get(i).get(deliveryMethodNameLists.get(i).indexOf(list.get(i).get(4).getDelete()));
//                        String remark = list.get(i).get(5).getDelete();

                        planPresentationPresenter.setSubmitPlan(/*date, name, wareHouse, number, delivery, remark, productId, getActivity().getSharedPreferences("sumao", Context.MODE_PRIVATE).getString("unique", "")*/);

                    }
                    break;

            }
        }
    }
}

package com.silianchuangye.sumao.success.fragments.myPlasticTrade.demandPlanning.PlanPresentationMVP.view;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.ShangYou.DayPlanInfo;
import com.silianchuangye.sumao.success.adapter.DayPlanAdapter;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.firmInfomation.decoding.Intents;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.demandPlanning.PlanPresentationMVP.presenter.PlanPresentationPresenter;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.demandPlanning.planType.ProductNameActivity;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.demandPlanning.planType.TypeActivity;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.demandPlanning.planType.WarehouseActivity;

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

    private boolean flag;
    private DayPlanAlert alert;
    private PlanPresentationPresenter planPresentationPresenter;

    private ImageView iv_child_title_bar_back;
    private TextView tv_child_title_bar_title;
    private String price="";
    private String number="";
    private String remark="";
    private String sellerCompanyId="";
    private String sellerCompanyName="";
    private String productId="";
    private String productName="";
    private String warehouseId="";
    private String warehouseName="";
    private String wayId="";
    private String wayName="";
    private String date;
    String dateinfo;
    private int groupPositionPublic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan_presentation);
        planPresentationPresenter = new PlanPresentationPresenter(this);
        tv_child_title_bar_title= (TextView) findViewById(R.id.tv_child_title_bar_title);
        tv_child_title_bar_title.setText("计划提交");
        iv_child_title_bar_back= (ImageView) findViewById(R.id.iv_child_title_bar_back);
        iv_child_title_bar_back.setOnClickListener(this);
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


        lv_day_plan.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                if (adapter.getChild(groupPosition, childPosition).getLevel().equals("需求日期")) {
                    groupPositionPublic = groupPosition;
                    onClickPlanDate(groupPosition);
                } else if (adapter.getChild(groupPosition, childPosition).getLevel().equals("产品名称")) {
                    Log.d("上游资源方ID",sellerCompanyId+"asd");
                    Log.d("产品名称的groupPosition的值",groupPosition+"");
                    groupPositionPublic = groupPosition;
                    // Toast.makeText(PlanPresentationActivity.this, "产品名称", Toast.LENGTH_SHORT).show();
                    if (sellerCompanyId!=""){
                        Intent intent=new Intent(PlanPresentationActivity.this, ProductNameActivity.class);
                        intent.putExtra("title","产品名称");
                        intent.putExtra("sellerCompanyId",sellerCompanyId);

                        //startActivity(intent);
                        startActivityForResult(intent,2);

                    }else{
                        Toast.makeText(PlanPresentationActivity.this, "请先选择卖方企业", Toast.LENGTH_SHORT).show();
                    }

                } else if (adapter.getChild(groupPosition, childPosition).getLevel().equals("购买数量(吨)")) {
                    // Toast.makeText(PlanPresentationActivity.this, "数量", Toast.LENGTH_SHORT).show();
                    groupPositionPublic = groupPosition;
                    Intent intent=new Intent(PlanPresentationActivity.this,ValueActivity.class);
                    intent.putExtra("title","购买数量");
                    intent.putExtra("id",5);

                    startActivityForResult(intent,5);
                } else if (adapter.getChild(groupPosition, childPosition).getLevel().equals("仓库")) {
                    groupPositionPublic = groupPosition;
                    if (productId!=""){
                        Intent intent=new Intent(PlanPresentationActivity.this, WarehouseActivity.class);
                        intent.putExtra("title","仓库");
                        intent.putExtra("productId",productId);
                        intent.putExtra("position",3);
                        startActivityForResult(intent,3);
                    }else{
                        Toast.makeText(PlanPresentationActivity.this, "请先选择产品", Toast.LENGTH_SHORT).show();
                    }

                } else if (adapter.getChild(groupPosition, childPosition).getLevel().equals("配送方式")) {
                    groupPositionPublic = groupPosition;
                    if (productId!=""){
                        Intent intent=new Intent(PlanPresentationActivity.this, WarehouseActivity.class);
                        intent.putExtra("title","配送方式");
                        intent.putExtra("productId",productId);
                        intent.putExtra("position",6);
                        startActivityForResult(intent,6);
                    }else{
                        Toast.makeText(PlanPresentationActivity.this, "请先选择产品", Toast.LENGTH_SHORT).show();
                    }

                    // Toast.makeText(PlanPresentationActivity.this, "配送方式", Toast.LENGTH_SHORT).show();
                } else if (adapter.getChild(groupPosition, childPosition).getLevel().equals("备注")) {
                    groupPositionPublic = groupPosition;
                    Intent intent=new Intent(PlanPresentationActivity.this,ValueActivity.class);
                    intent.putExtra("title","备注");
                    intent.putExtra("id",7);
                    //setResult(7,intent);
                    startActivityForResult(intent,7);
                    //Toast.makeText(PlanPresentationActivity.this, "备注", Toast.LENGTH_SHORT).show();
                } else if (adapter.getChild(groupPosition, childPosition).getLevel().equals("")) {

                    //Toast.makeText(PlanPresentationActivity.this, "删除的是：" + groupPosition + "组。" + "--childPosition" + childPosition + "位置", Toast.LENGTH_SHORT).show();
                    list.remove(groupPosition);
                    group.remove(groupPosition);
                    adapter.notifyDataSetChanged();
                }else if (adapter.getChild(groupPosition,childPosition).getLevel().equals("卖方企业")){
                    groupPositionPublic = groupPosition;
                    //卖方企业的跳转界面
                    Intent intent=new Intent(PlanPresentationActivity.this, TypeActivity.class);
                    intent.putExtra("title","卖方企业");
                    startActivityForResult(intent,0);
                }else if (adapter.getChild(groupPosition,childPosition).getLevel().equals("购买价格(元/吨)")){
                    groupPositionPublic = groupPosition;
                    Intent intent=new Intent(PlanPresentationActivity.this,ValueActivity.class);
                    intent.putExtra("title","购买价格");
                    intent.putExtra("id",4);
                    startActivityForResult(intent,4);
                }


                return true;
            }
        });
        alert = new DayPlanAlert(this);

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        SharedPreferences sharedPreferences=getSharedPreferences("sumao",Activity.MODE_PRIVATE);
        dateinfo=sharedPreferences.getString("xuqiudata","");
        Log.d("onActivityResult的date",dateinfo+"aaa");
        Log.d("groupPositionPublic的值",groupPositionPublic+"qqq"+requestCode);
        switch (resultCode){
            case 0:
                //if (requestCode==groupPositionPublic) {
                    sellerCompanyId = data.getStringExtra("id");
                    sellerCompanyName = data.getStringExtra("name");
                     Log.d("值",sellerCompanyName);
                    //new MyClick();
                    list.get(groupPositionPublic).set(0, new DayPlanInfo("卖方企业", "", sellerCompanyName));
                    // list.get(groupPositionPublic).set(1, new DayPlanInfo("需求日期", "", dateinfo));
                    adapter.notifyDataSetChanged();
               // }


                break;
            case 2:
               // if (requestCode==groupPositionPublic) {
                    productId = data.getStringExtra("id");
                    productName = data.getStringExtra("name");
                    Log.d("产品名称的值",productName);
                    list.get(groupPositionPublic).set(2, new DayPlanInfo("产品名称", "", productName));
                    adapter.notifyDataSetChanged();
               // }
                break;
            case 3:
               // if (requestCode==groupPositionPublic) {
                    warehouseId = data.getStringExtra("id");
                    warehouseName = data.getStringExtra("name");
                    Log.d("仓库名称的值",warehouseName);
                    list.get(groupPositionPublic).set(3, new DayPlanInfo("仓库", "", warehouseName));
                    adapter.notifyDataSetChanged();
               // }
                break;
            case 4:
              //  if (requestCode==groupPositionPublic) {
                    price = data.getStringExtra("content");
                    Log.d("price的返回值", price);
                    list.get(groupPositionPublic).set(4, new DayPlanInfo("购买价格(元/吨)", "", price));
                    adapter.notifyDataSetChanged();
                //}
                break;
            case 5:
              //  if (requestCode==groupPositionPublic) {
                    number = data.getStringExtra("content");
                    list.get(groupPositionPublic).set(5, new DayPlanInfo("购买数量(吨)", "", number));
                    adapter.notifyDataSetChanged();
              //  }
                break;
            case 6:
               // if (requestCode==groupPositionPublic) {
                    wayId = data.getStringExtra("id");
                    wayName = data.getStringExtra("name");
                    list.get(groupPositionPublic).set(6, new DayPlanInfo("配送方式", "", wayName));
                    adapter.notifyDataSetChanged();
               // }
                break;
            case 7:
               // if (requestCode==groupPositionPublic) {
                    remark = data.getStringExtra("content");
                    list.get(groupPositionPublic).set(7, new DayPlanInfo("备注", "", remark));
                    adapter.notifyDataSetChanged();
              //  }
                break;

        }
    }

    /**
     * 将数据添加到List列表中
     */
    private void initDate() {
            lists = new ArrayList<DayPlanInfo>();
            lists.add(new DayPlanInfo("卖方企业", "", ""));
            lists.add(new DayPlanInfo("需求日期", "", ""));
            lists.add(new DayPlanInfo("产品名称", "", ""));
            lists.add(new DayPlanInfo("仓库", "", ""));
            lists.add(new DayPlanInfo("购买价格(元/吨)", "", ""));
            lists.add(new DayPlanInfo("购买数量(吨)", "", ""));
            lists.add(new DayPlanInfo("配送方式", "", ""));
            lists.add(new DayPlanInfo("备注", "", ""));
            lists.add(new DayPlanInfo("", "删除计划", ""));
            list.add(lists);
            group.add("");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
//            case R.id.iv_child_title_bar_back:
//                PlanPresentationActivity.this.finish();
//                break;
            case R.id.btn_day_plan_add://添加新计划
                //initDate();
//                for (int i = 0; i < list.size(); i++) {
//                    flag = false;
//                    if (list.get(i).get(0).getDelete() == "" || list.get(i).get(0).getDelete().isEmpty()) {
//                        TOAST("卖方企业");
//                        flag = true;
//                    } else if (list.get(i).get(1).getDelete() == "" || list.get(i).get(1).getDelete().isEmpty()) {
//                        TOAST("需求日期");
//                        flag = true;
//                    } else if (list.get(i).get(2).getDelete() == "" || list.get(i).get(2).getDelete().isEmpty()) {
//                        TOAST("请填写产品名称");
//                        flag = true;
//                    } else if (list.get(i).get(3).getDelete() == "" || list.get(i).get(3).getDelete().isEmpty()) {
//                        TOAST("请选择仓库");
//                        flag = true;
//                    } else if (list.get(i).get(4).getDelete() == "" || list.get(i).get(4).getDelete().isEmpty()) {
//                        TOAST("请选择购买价格");
//                        flag = true;
//                    } else if (list.get(i).get(5).getDelete() == "" || list.get(i).get(5).getDelete().isEmpty()) {
//                        TOAST("请填写购买数量");
//                        flag = true;
//                    }else if (list.get(i).get(6).getDelete() == "" || list.get(i).get(6).getDelete().isEmpty()) {
//                        TOAST("请填写配送方式");
//                        flag = true;
//                    }else if (list.get(i).get(7).getDelete() == "" || list.get(i).get(7).getDelete().isEmpty()) {
//                        TOAST("请填写备注");
//                        flag = true;
//                    }
//                }
//                if (!flag) {
//
//                    lists = new ArrayList<DayPlanInfo>();
//                    lists.add(new DayPlanInfo("卖方企业", "", sellerCompanyName));
//                    lists.add(new DayPlanInfo("需求日期", "", " "));
//                    lists.add(new DayPlanInfo("产品名称", "", productName));
//                    lists.add(new DayPlanInfo("仓库", "", warehouseName));
//                    lists.add(new DayPlanInfo("购买价格(元/吨)", "", price));
//                    lists.add(new DayPlanInfo("购买数量(吨)", "", number));
//                    lists.add(new DayPlanInfo("配送方式", "", wayName));
//                    lists.add(new DayPlanInfo("备注", "", remark));
//                    lists.add(new DayPlanInfo("", "删除计划", ""));
//                    list.add(lists);
//                    group.add("");
                    lists = new ArrayList<DayPlanInfo>();
                    lists.add(new DayPlanInfo("卖方企业", "", " "));
                    lists.add(new DayPlanInfo("需求日期", "", " "));
                    lists.add(new DayPlanInfo("产品名称", "", " "));
                    lists.add(new DayPlanInfo("仓库", "", " "));
                    lists.add(new DayPlanInfo("购买价格(元/吨)", "", " "));
                    lists.add(new DayPlanInfo("购买数量(吨)", "", " "));
                    lists.add(new DayPlanInfo("配送方式", "", " "));
                    lists.add(new DayPlanInfo("备注", "", " "));
                    lists.add(new DayPlanInfo("", "删除计划", ""));
                    list.add(lists);
                    group.add("");
                    adapter.notifyDataSetChanged();
                    for (int i = 0; i < group.size(); i++) {
                        lv_day_plan.expandGroup(i);
                    }
                // }
                break;
            case R.id.btn_day_plan_tijiao://计划提交
                for (int i = 0; i < list.size(); i++) {
                    flag = false;
                    if (list.get(i).get(0).getDelete() == "" || list.get(i).get(0).getDelete().isEmpty()) {
                        //TOAST("卖方企业");
                        Toast.makeText(PlanPresentationActivity.this, "请填写卖方企业", Toast.LENGTH_SHORT).show();
                        flag = true;
                    } else if (list.get(i).get(1).getDelete() == "" || list.get(i).get(1).getDelete().isEmpty()) {
                        // TOAST("需求日期");
                        Toast.makeText(PlanPresentationActivity.this, "请填写需求日期", Toast.LENGTH_SHORT).show();
                        flag = true;
                    } else if (list.get(i).get(2).getDelete() == "" || list.get(i).get(2).getDelete().isEmpty()) {
                        // TOAST("请填写产品名称");
                        Toast.makeText(PlanPresentationActivity.this, "请填写产品名称", Toast.LENGTH_SHORT).show();
                        flag = true;
                    } else if (list.get(i).get(3).getDelete() == "" || list.get(i).get(3).getDelete().isEmpty()) {
                        //TOAST("请选择仓库");
                        Toast.makeText(PlanPresentationActivity.this, "请选择仓库", Toast.LENGTH_SHORT).show();
                        flag = true;
                    } else if (list.get(i).get(4).getDelete() == "" || list.get(i).get(4).getDelete().isEmpty()) {
                        //TOAST("请选择购买价格");
                        Toast.makeText(PlanPresentationActivity.this, "请选择购买价格", Toast.LENGTH_SHORT).show();
                        flag = true;
                    } else if (list.get(i).get(5).getDelete() == "" || list.get(i).get(5).getDelete().isEmpty()) {
                        // TOAST("请填写购买数量");
                        Toast.makeText(PlanPresentationActivity.this, "请填写购买数量", Toast.LENGTH_SHORT).show();
                        flag = true;
                    } else if (list.get(i).get(6).getDelete() == "" || list.get(i).get(6).getDelete().isEmpty()) {
                        //TOAST("请填写配送方式");
                        Toast.makeText(PlanPresentationActivity.this, "请填写配送方式", Toast.LENGTH_SHORT).show();
                        flag = true;
                    } else if (list.get(i).get(7).getDelete() == "" || list.get(i).get(7).getDelete().isEmpty()) {
                        // TOAST("请填写备注");
                        Toast.makeText(PlanPresentationActivity.this, "请填写备注", Toast.LENGTH_SHORT).show();
                        flag = true;
                    }
                }
                if (!flag) {
                    if (PlanPresentationActivity.this.getSharedPreferences("sumao", Context.MODE_PRIVATE).getString("unique", "").equals("false")) {
                        // TOAST("请登陆后进行操作");
                        Toast.makeText(PlanPresentationActivity.this, "请登陆后进行操作", Toast.LENGTH_SHORT).show();
                        /*Intent intent = new Intent();
                        intent.setClass(getActivity(), LoginUserActivity.class);
                        intent.putExtra("roles", "buyer");
                        intent.putExtra("activity", "NotMainActivity");
                        startActivity(intent);*/
                    } else {
                        //Toast.makeText(PlanPresentationActivity.this, "提交", Toast.LENGTH_SHORT).show();for (int i = 0; i < list.size(); i++) {
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
                        // planPresentationPresenter.setSubmitPlan(/*date, name, wareHouse, number, delivery, remark, productId, getActivity().getSharedPreferences("sumao", Context.MODE_PRIVATE).getString("unique", "")*/);
                        SharedPreferences sp = getSharedPreferences("sumao", Activity.MODE_PRIVATE);
                        String unique = sp.getString("unique", "");
                        planPresentationPresenter.setSubmitPlan(sellerCompanyId, date, productId, price, warehouseId, number, wayId, remark, unique);
                    }
                        alert.show();
                }
                break;
            case R.id.iv_child_title_bar_back:
                PlanPresentationActivity.this.finish();
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
        Log.d("计划提交的返回值",""+result);
        if (result.contains("success")) {
            alert.dismiss();
            TOAST("上传成功");
            for (int i = 0; i < list.size(); i++) {
                list.remove(i);
                group.remove(i);
                adapter.notifyDataSetChanged();
            }
            lists = new ArrayList<DayPlanInfo>();
            lists.add(new DayPlanInfo("卖方企业", "", " "));
            lists.add(new DayPlanInfo("需求日期", "", " "));
            lists.add(new DayPlanInfo("产品名称", "", " "));
            lists.add(new DayPlanInfo("仓库", "", " "));
            lists.add(new DayPlanInfo("购买价格(元/吨)", "", " "));
            lists.add(new DayPlanInfo("购买数量(吨)", "", " "));
            lists.add(new DayPlanInfo("配送方式", "", " "));
            lists.add(new DayPlanInfo("备注", "", " "));
            lists.add(new DayPlanInfo("", "删除计划", ""));
            list.add(lists);
            group.add("");
            adapter.notifyDataSetChanged();
            for (int i = 0; i < group.size(); i++) {
                lv_day_plan.expandGroup(i);
            }

        } else {
            alert.dismiss();
            TOAST("上传失败");
        }


    }

    class MyClick implements DatePickerDialog.OnDateSetListener {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            //list.get(groupPositionPublic).get()

            date=year + "-" +(monthOfYear+1) + "-" + dayOfMonth;
            Log.d("date的值",date+"date的值");
            SharedPreferences sp=getSharedPreferences("sumao",Activity.MODE_PRIVATE);
            SharedPreferences.Editor editor=sp.edit();
            editor.putString("xuqiudata",date);
            editor.commit();
            list.get(groupPositionPublic).set(1, new DayPlanInfo("需求日期", "", date));
            adapter.notifyDataSetChanged();
        }
    }

    //提交时弹出的Dialog
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
                        SharedPreferences sp=getSharedPreferences("sumao", Activity.MODE_PRIVATE);
                        String unique=sp.getString("unique","");
                        planPresentationPresenter.setSubmitPlan(sellerCompanyId,date,productId,productName,warehouseId,number,warehouseId,remark,unique);
                        //planPresentationPresenter.setSubmitPlan(/*date, name, wareHouse, number, delivery, remark, productId, getActivity().getSharedPreferences("sumao", Context.MODE_PRIVATE).getString("unique", "")*/);

                    }
                    break;

            }
        }
    }
}

package com.silianchuangye.sumao.success.ShangYou.PlanDayMVP.view;


import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.Toast;

import com.silianchuangye.sumao.success.HX.ui.LoginActivity;
import com.silianchuangye.sumao.success.MainActivity;
import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.ShangYou.DayPlanInfo;
import com.silianchuangye.sumao.success.ShangYou.PlanDayMVP.Bean.DeliveryMethods;
import com.silianchuangye.sumao.success.ShangYou.PlanDayMVP.Bean.PlanDayBean;
import com.silianchuangye.sumao.success.ShangYou.PlanDayMVP.Bean.WareHouse;
import com.silianchuangye.sumao.success.ShangYou.PlanDayMVP.ShangYouRemarks;
import com.silianchuangye.sumao.success.ShangYou.PlanDayMVP.presenter.PlanDayPresenter;
import com.silianchuangye.sumao.success.ShangYou.ShangYouSelectNumber;
import com.silianchuangye.sumao.success.adapter.DayPlanAdapter;
import com.silianchuangye.sumao.success.fragments.homepage.UpstreamDirectSellingMVP.UpstreamDirectSellingDetailMVP.VipProductList;
import com.silianchuangye.sumao.success.fragments.homepage.UpstreamDirectSellingMVP.bean.vipProductBean.VipProduct;
import com.silianchuangye.sumao.success.fragments.homepage.UpstreamDirectSellingMVP.bean.vipProductBean.VipProductBean;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.login.LoginUserActivity;
import com.silianchuangye.sumao.success.utils.LogUtils;
import com.silianchuangye.sumao.success.utils.SuMaoConstant;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 */
public class PlanDay extends Fragment implements OnClickListener, IPlanDayView {
    private Button btn_day_plan_add, btn_day_plan_tijiao;
    private ExpandableListView lv_day_plan;
    private List<List<DayPlanInfo>> list;
    private List<String> group;
    private DayPlanAdapter adapter;
    private DayPlanAlert alert;
    private List<DayPlanInfo> lists;
    private Bundle arguments;
    private VipProductBean vipProductBean;
    private List<VipProduct> vipProducts;
    private String vipProductsID;
    private String productName;

    private List<DeliveryMethods> deliveryMethodsList = new ArrayList<>();

    private List<WareHouse> wareHouseList = new ArrayList<>();

    //要创建多个集合，存放对应的名字，id,仓库，
    List<String> vipProductsIDList = new ArrayList<>();
    private List<List<String>> vipProductsIDLists = new ArrayList<>();
    List<String> productNameList = new ArrayList<>();

    private String conmpanyId;
    private String productId;
    private int groupPositionPublic;
    private String selectedName;

    private List<String> wareHouseIdList ;
    private List<List<String>> wareHouseIdLists = new ArrayList<>();

    private List<String> wareHouseNameList;
    private List<List<String>> wareHouseNameLists = new ArrayList<>();

    private List<String> deliveryMethodIdList ;
    private List<List<String>> deliveryMethodIdLists = new ArrayList<>();

    private List<String> deliveryMethodNameList ;
    private List<List<String>> deliveryMethodNameLists = new ArrayList<>();

    private String wareHouseId;
    private String wareHouseName;
    private String deliveryMethodId;
    private String deliveryMethodName;
    private String number;
    private String remarks;
    private boolean flag;
    private PlanDayPresenter planDayPresenter;

    public PlanDay() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = View.inflate(getActivity(), R.layout.fragment_plan_day2, null);
        planDayPresenter = new PlanDayPresenter(this);

        LogUtils.log("sessionId-->" + getActivity().getSharedPreferences("sumao", Context.MODE_PRIVATE).getString("unique", ""));
        arguments = getArguments();
        vipProductBean = (VipProductBean) arguments.getSerializable("vipProductBean");
        vipProducts = vipProductBean.getVipProduct();
        for (int i = 0; i < vipProducts.size(); i++) {
            vipProductsID = vipProducts.get(i).getProductID();
            productName = vipProducts.get(i).getProductName();
            vipProductsIDList.add(vipProductsID);
            productNameList.add(productName);

        }
        lv_day_plan = (ExpandableListView) v.findViewById(R.id.lv_day_plan);
        btn_day_plan_add = (Button) v.findViewById(R.id.btn_day_plan_add);
        btn_day_plan_tijiao = (Button) v.findViewById(R.id.btn_day_plan_tijiao);
        btn_day_plan_tijiao.setOnClickListener(this);
        btn_day_plan_add.setOnClickListener(this);
        group = new ArrayList<>();
        list = new ArrayList<List<DayPlanInfo>>();
        initDate();
        adapter = new DayPlanAdapter(list, getActivity(), group);

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
                if (adapter.getChild(groupPosition, childPosition).getLevel().equals("计划日期")) {

                    onClickPlanDate(groupPosition);
                } else if (adapter.getChild(groupPosition, childPosition).getLevel().equals("产品名称")) {
                    groupPositionPublic = groupPosition;
                    Intent intent = new Intent();
                    intent.putExtra("ID", (Serializable) vipProductsIDList);
                    intent.putExtra("Name", (Serializable) productNameList);
                    intent.putExtra("titleName", "产品名称");
                    intent.setClass(getActivity(), VipProductList.class);

                    startActivityForResult(intent, groupPosition);
                } else if (adapter.getChild(groupPosition, childPosition).getLevel().equals("数量（吨）")) {
                    groupPositionPublic = groupPosition;
                    Intent intent = new Intent();
                    intent.setClass(getActivity(), ShangYouSelectNumber.class);
                    intent.putExtra("titleName", "数量");
                    startActivityForResult(intent, groupPosition);
                } else if (adapter.getChild(groupPosition, childPosition).getLevel().equals("仓库")) {
                    groupPositionPublic = groupPosition;
                    if (list.get(groupPositionPublic).get(1).getDelete() == "" || list.get(groupPositionPublic).get(1).getDelete().isEmpty()) {
                        Toast.makeText(getActivity(), "请选择产品", Toast.LENGTH_SHORT).show();
                    } else {
                        wareHouseIdList = new ArrayList<String>();
                        wareHouseNameList = new ArrayList<String>();
                        for (int i = 0; i < wareHouseList.size(); i++) {

                            wareHouseIdList.add(wareHouseList.get(i).getWareHouseID());
                            wareHouseNameList.add(wareHouseList.get(i).getWareHouseName());
                        }

                        if (wareHouseIdLists.size() <= groupPosition) {
                            wareHouseIdLists.add( wareHouseIdList);
                            wareHouseNameLists.add( wareHouseNameList);
                            LogUtils.log("周这里--》");
                            LogUtils.log(" wareHouseIdLists.size()--》"+ wareHouseIdLists.size());
                            LogUtils.log(" wareHouseNameLists.size()--》"+ wareHouseNameLists.size());

                        }
                        Intent intent = new Intent();
                        intent.putExtra("ID", (Serializable) wareHouseIdList);
                        intent.putExtra("Name", (Serializable) wareHouseNameList);
                        intent.putExtra("titleName", "仓库");
                        intent.setClass(getActivity(), VipProductList.class);

                        startActivityForResult(intent, groupPosition);
                    }

                } else if (adapter.getChild(groupPosition, childPosition).getLevel().equals("配送方式")) {
                    groupPositionPublic = groupPosition;
                    if (list.get(groupPositionPublic).get(1).getDelete() == "" || list.get(groupPositionPublic).get(1).getDelete().isEmpty()) {
                        Toast.makeText(getActivity(), "请选择产品", Toast.LENGTH_SHORT).show();
                    } else {
                        deliveryMethodIdList = new ArrayList<String>();
                        deliveryMethodNameList = new ArrayList<String>();
                        for (int i = 0; i < deliveryMethodsList.size(); i++) {
                            deliveryMethodIdList.add(deliveryMethodsList.get(i).getDeliveryMethodID());
                            deliveryMethodNameList.add(deliveryMethodsList.get(i).getDeliveryMethodName());
                        }
                        if (deliveryMethodIdLists.size() <= groupPosition) {
                            deliveryMethodIdLists.add( deliveryMethodIdList);
                            deliveryMethodNameLists.add( deliveryMethodNameList);
                        }
                        Intent intent = new Intent();
                        intent.putExtra("ID", (Serializable) deliveryMethodIdList);
                        intent.putExtra("Name", (Serializable) deliveryMethodNameList);
                        intent.putExtra("titleName", "配送方式");
                        intent.setClass(getActivity(), VipProductList.class);
                        startActivityForResult(intent, groupPosition);
                    }
                } else if (adapter.getChild(groupPosition, childPosition).getLevel().equals("备注")) {
                    groupPositionPublic = groupPosition;
                    Intent intent = new Intent();
                    intent.setClass(getActivity(), ShangYouRemarks.class);
                    intent.putExtra("titleName", "备注");
                    startActivityForResult(intent, groupPosition);
                } else if (adapter.getChild(groupPosition, childPosition).getLevel().equals("")) {
                    Toast.makeText(getActivity(), "删除的是：" + groupPosition + "组。" + "--childPosition" + childPosition + "位置", Toast.LENGTH_SHORT).show();
                    list.remove(groupPosition);
                    group.remove(groupPosition);
                    adapter.notifyDataSetChanged();
                }

                return true;
            }
        });
        alert = new DayPlanAlert(getActivity());
        return v;
    }

    private void onClickPlanDate(int position) {
        groupPositionPublic = position;
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
        DatePickerDialog dlg = new DatePickerDialog(getActivity(), new MyClick(), year, month, day);
        DatePicker datePicker = dlg.getDatePicker();
        datePicker.setMinDate(System.currentTimeMillis());
        dlg.show();
    }

    @Override
    public void getProductWarehouse(PlanDayBean planDayBean) {


        wareHouseList = planDayBean.getVipWareHouseAndDeliveryMethodSearch().getWareHouse();
        /*wareHouseIdList = */
        deliveryMethodsList = planDayBean.getVipWareHouseAndDeliveryMethodSearch().getDeliveryMethods();

    }

    @Override
    public void returnSubmissionPlanState(String result) {
        if (result.contains("success")) {
            alert.dismiss();
            TOAST("上传成功");
            for (int i = 0; i < list.size(); i++) {
                list.remove(i);
                group.remove(i);
                adapter.notifyDataSetChanged();
//                list.get(i).set(0, new DayPlanInfo("计划时间", "", ""));
//                list.get(i).set(1, new DayPlanInfo("产品名称", "", ""));
//                list.get(i).set(2, new DayPlanInfo("数量（吨）", "", ""));
//                list.get(i).set(3, new DayPlanInfo("仓库", "", ""));
//                list.get(i).set(4, new DayPlanInfo("配送方式", "", ""));
//                list.get(i).set(5, new DayPlanInfo("备注", "", ""));
//                adapter.notifyDataSetChanged();

            }
//            lists = new ArrayList<DayPlanInfo>();
//            lists.add(new DayPlanInfo("计划日期", "", ""));
//            lists.add(new DayPlanInfo("产品名称", "", ""));
//            lists.add(new DayPlanInfo("数量（吨）", "", ""));
//            lists.add(new DayPlanInfo("仓库", "", ""));
//            lists.add(new DayPlanInfo("配送方式", "", ""));
//            lists.add(new DayPlanInfo("备注", "", ""));
//            lists.add(new DayPlanInfo("", "删除计划", ""));
//            list.add(lists);
//            group.add("");
//            adapter.notifyDataSetChanged();
//            for (int j = 0; j < group.size(); j++) {
//                lv_day_plan.expandGroup(j);
//            }

        } else {
            alert.dismiss();
            TOAST("上传失败");
        }
    }

    class MyClick implements DatePickerDialog.OnDateSetListener {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            list.get(groupPositionPublic).set(0, new DayPlanInfo("计划日期", "", year + "-" + monthOfYear + "-" + dayOfMonth));
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode) {
            case SuMaoConstant.VIP_PRODUCT_ID:
                if (requestCode == groupPositionPublic) {
                    productId = data.getStringExtra("selectedId");
                    selectedName = data.getStringExtra("selectedName");
                    LogUtils.log("productId-->" + productId + "selectedName-->" + selectedName);
                    list.get(groupPositionPublic).set(1, new DayPlanInfo("产品名称", "", selectedName));
                    adapter.notifyDataSetChanged();
                    planDayPresenter.getPlanDayWarehouse(getActivity().getSharedPreferences("sumao", Context.MODE_PRIVATE).getString("unique", ""), productId);
                }
                break;
            case SuMaoConstant.VIP_SELECTNUMBER_ID:
                if (requestCode == groupPositionPublic) {
                    number = data.getStringExtra("number");

                    list.get(groupPositionPublic).set(2, new DayPlanInfo("数量（吨）", "", number));
                    adapter.notifyDataSetChanged();
                }
                break;

            case SuMaoConstant.VIP_WAREHOUSE_ID:
                if (requestCode == groupPositionPublic) {
                    wareHouseId = data.getStringExtra("selectedId");
                    wareHouseName = data.getStringExtra("selectedName");
                    LogUtils.log("wareHouseId-->" + wareHouseId + "wareHouseName-->" + wareHouseName);
                    list.get(groupPositionPublic).set(3, new DayPlanInfo("仓库", "", wareHouseName));
                    adapter.notifyDataSetChanged();
                }
                break;
            case SuMaoConstant.VIP_DELIVERYMETHOD_ID:
                if (requestCode == groupPositionPublic) {
                    deliveryMethodId = data.getStringExtra("selectedId");
                    deliveryMethodName = data.getStringExtra("selectedName");
                    LogUtils.log("deliveryMethodId-->" + deliveryMethodId + "deliveryMethodName-->" + deliveryMethodName);
                    list.get(groupPositionPublic).set(4, new DayPlanInfo("配送方式", "", deliveryMethodName));
                    adapter.notifyDataSetChanged();
                }
                break;
            case SuMaoConstant.VIP_REMARKS_ID:
                if (requestCode == groupPositionPublic) {
                    remarks = data.getStringExtra("number");
                    LogUtils.log("remarks-->" + remarks);
                    list.get(groupPositionPublic).set(5, new DayPlanInfo("备注", "", remarks));
                    adapter.notifyDataSetChanged();
                }

                break;

        }
    }

    private void initDate() {
        LogUtils.log("将要进行ExpandableListView的初始化");

        for (int i = 0; i < 1; i++) {
            lists = new ArrayList<DayPlanInfo>();
            lists.add(new DayPlanInfo("计划日期", "", ""));
            lists.add(new DayPlanInfo("产品名称", "", ""));
            lists.add(new DayPlanInfo("数量（吨）", "", ""));
            lists.add(new DayPlanInfo("仓库", "", ""));
            lists.add(new DayPlanInfo("配送方式", "", ""));
            lists.add(new DayPlanInfo("备注", "", ""));
            lists.add(new DayPlanInfo("", "删除计划", ""));
            list.add(lists);
            group.add("");

        }
        LogUtils.log("进行ExpandableListView的初始化完毕");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_day_plan_add:
                for (int i = 0; i < list.size(); i++) {
                    flag = false;
                    if (list.get(i).get(0).getDelete() == "" || list.get(i).get(0).getDelete().isEmpty()) {
                        TOAST("请选择时间");
                        flag = true;
                    } else if (list.get(i).get(1).getDelete() == "" || list.get(i).get(1).getDelete().isEmpty()) {
                        TOAST("请选择产品名称");
                        flag = true;
                    } else if (list.get(i).get(2).getDelete() == "" || list.get(i).get(2).getDelete().isEmpty()) {
                        TOAST("请填写数量");
                        flag = true;
                    } else if (list.get(i).get(3).getDelete() == "" || list.get(i).get(3).getDelete().isEmpty()) {
                        TOAST("请选择仓库");
                        flag = true;
                    } else if (list.get(i).get(4).getDelete() == "" || list.get(i).get(4).getDelete().isEmpty()) {
                        TOAST("请选择配送方式");
                        flag = true;
                    } else if (list.get(i).get(5).getDelete() == "" || list.get(i).get(5).getDelete().isEmpty()) {
                        TOAST("请填写备注");
                        flag = true;
                    }
                }
                if (!flag) {


                    lists = new ArrayList<DayPlanInfo>();
                    lists.add(new DayPlanInfo("计划日期", "", ""));
                    lists.add(new DayPlanInfo("产品名称", "", ""));
                    lists.add(new DayPlanInfo("数量（吨）", "", ""));
                    lists.add(new DayPlanInfo("仓库", "", ""));
                    lists.add(new DayPlanInfo("配送方式", "", ""));
                    lists.add(new DayPlanInfo("备注", "", ""));
                    lists.add(new DayPlanInfo("", "删除计划", ""));
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
                        TOAST("请选择时间");
                        flag = true;
                    } else if (list.get(i).get(1).getDelete() == "" || list.get(i).get(1).getDelete().isEmpty()) {
                        TOAST("请选择产品名称");
                        flag = true;
                    } else if (list.get(i).get(2).getDelete() == "" || list.get(i).get(2).getDelete().isEmpty()) {
                        TOAST("请填写数量");
                        flag = true;
                    } else if (list.get(i).get(3).getDelete() == "" || list.get(i).get(3).getDelete().isEmpty()) {
                        TOAST("请选择仓库");
                        flag = true;
                    } else if (list.get(i).get(4).getDelete() == "" || list.get(i).get(4).getDelete().isEmpty()) {
                        TOAST("请选择配送方式");
                        flag = true;
                    } else if (list.get(i).get(5).getDelete() == "" || list.get(i).get(5).getDelete().isEmpty()) {
                        TOAST("请填写备注");
                        flag = true;
                    }
                }
                if (!flag) {
                    if (getActivity().getSharedPreferences("sumao", Context.MODE_PRIVATE).getString("unique", "").equals("false")) {
                        TOAST("请登陆后进行操作");
                        /*Intent intent = new Intent();
                        intent.setClass(getActivity(), LoginUserActivity.class);
                        intent.putExtra("roles", "buyer");
                        intent.putExtra("activity", "NotMainActivity");
                        startActivity(intent);*/
                    } else {
                        Toast.makeText(getActivity(), "提交", Toast.LENGTH_SHORT).show();
                        alert.show();
                    }


                }
                break;
        }
    }

    public void TOAST(String s) {
        Toast.makeText(getActivity(), s, Toast.LENGTH_SHORT).show();
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
//                    new AsyncTask<Void, Void, String>() {
//                        @Override
//                        protected void onPreExecute() {
//                            super.onPreExecute();
//                        }
//
//                        @Override
//                        protected void onPostExecute(String s) {
//                            super.onPostExecute(s);
//                        }
//
//                        @Override
//                        protected String doInBackground(Void... params) {
//                            return null;
//                        }
//                    };
                    for (int i = 0; i < list.size(); i++) {
                        String date = list.get(i).get(0).getDelete();
                        String name = vipProductsIDList.get(productNameList.indexOf(list.get(i).get(1).getDelete()));
                        String number = list.get(i).get(2).getDelete();
                        LogUtils.log("number" + number);

                        String ck = list.get(i).get(3).getDelete();
                        LogUtils.log("ck" + ck);
                        int position = wareHouseNameLists.get(i).indexOf(ck);
                        LogUtils.log("i--->" + i);
                        LogUtils.log("wareHouseNameLists.get(i).size()" + wareHouseNameLists.get(i).size());
                        for (int k = 0; k < wareHouseNameLists.get(i).size(); k++) {
                            LogUtils.log("wareHouseNameLists的元素"+k+"-->" +  wareHouseNameLists.get(i).get(k));
                        }
                        LogUtils.log("position" + position);
                        String wareHouse = wareHouseIdLists.get(i).get(position);
                        String delivery = deliveryMethodIdLists.get(i).get(deliveryMethodNameLists.get(i).indexOf(list.get(i).get(4).getDelete()));
                        String remark = list.get(i).get(5).getDelete();

                        planDayPresenter.SubmissionPlan(date, name, wareHouse, number, delivery, remark, productId, getActivity().getSharedPreferences("sumao", Context.MODE_PRIVATE).getString("unique", ""));

                    }
                    break;
            }
        }
    }

}

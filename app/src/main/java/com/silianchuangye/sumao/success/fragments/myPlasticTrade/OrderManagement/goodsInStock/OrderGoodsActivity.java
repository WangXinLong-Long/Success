package com.silianchuangye.sumao.success.fragments.myPlasticTrade.OrderManagement.goodsInStock;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
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

import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.adapter.MyPageAdapter;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.OrderManagement.SpotOrder.preSell.PresellAlreadyPaidFragmentMVP.view.PresellAlreadyPaidFragment;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.OrderManagement.SpotOrder.preSell.PresellCancelFragmentMVP.view.PresellCancelFragment;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.OrderManagement.SpotOrder.preSell.PresellChangeFragmentMVP.view.PresellChangeFragment;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.OrderManagement.SpotOrder.preSell.PresellToBePaidFragmentMVP.view.PresellToBePaidFragment;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.OrderManagement.SpotOrder.preSell.presellallFragmentMVP.view.PresellallFragment;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.OrderManagement.SpotOrder.service.ServiceAllFragment;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.OrderManagement.SpotOrder.service.ServiceBornFragment;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.OrderManagement.SpotOrder.service.ServiceChangeFragment;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.OrderManagement.SpotOrder.service.ServiceFinishFragment;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.OrderManagement.SpotOrder.service.ServiceShipmentsFragment;
import com.silianchuangye.sumao.success.utils.LogUtils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

public class OrderGoodsActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {
    //popViewdow
    private PopupWindow popupWindow;
    private View popView;
    private LinearLayout linear;
    private TextView Tv_jiaoyi, Tv_kaipiao, Tv_dingdan, Tv_dingdan_startdate, Tv_dingdan_enddate;
    private EditText edt_dingdan_company, edt_dingdan_num;
    private ListView Lv_jiaoyi, Lv_kaipiao, Lv_dingdan;
    private Button btn_dingdan_my, btn_dingdan_all;
    private List<String> list = new ArrayList<String>();
    private ArrayAdapter<String> LvAdapter;
    private List<String> Jylist = new ArrayList<String>();
    private ArrayAdapter<String> JyLvAdapter;
    private List<String> Kplist = new ArrayList<String>();
    private ArrayAdapter<String> KpLvAdapter;
    private boolean flag = true;
    String first = "first";
    String second = "second";

    private ArrayList<Fragment> listFragment;
    private TabLayout tlDemo;
    private ViewPager vpDemo;
    private MyPageAdapter adapter;
    private ImageView back;
    private ImageView search;
    private RelativeLayout relative;
    private TextView tv_title_name;
    private String title;
    private Dialog mDialog;

    int i;
    private String OrderType;
    private String OrderState;
    private String CheckType;
    private String startDate, endDate;
    private String company, OrderId;
    private List<Map<String, Object>> listparrent;
    private List<List<Map<String, Object>>> listitem;
    private ArrayList<String> listString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_goods);
        tv_title_name = (TextView) findViewById(R.id.tv_title_name_order);
        title = getIntent().getStringExtra("title");
        LogUtils.log("title--->" + title);
        listFragment = new ArrayList<Fragment>();
        if (title.equals("现货订单")) {
            //现货订单访问数据库的内容
            //现货订单全部
            PresellallFragment all = new PresellallFragment();
            all.setSearchOrderType("0");
            listFragment.add(all);
//           待支付
            PresellToBePaidFragment presellToBePaidFragment = new PresellToBePaidFragment();
            presellToBePaidFragment.setSearchOrderType("0");
            listFragment.add(presellToBePaidFragment);
//            已支付
            PresellAlreadyPaidFragment presellAlreadyPaidFragment = new PresellAlreadyPaidFragment();
            presellAlreadyPaidFragment.setSearchOrderType("0");
            listFragment.add(presellAlreadyPaidFragment);
//            已变更
            PresellChangeFragment presellChangeFragment = new PresellChangeFragment();
            presellChangeFragment.setSearchOrderType("0");
            listFragment.add(presellChangeFragment);
//            已取消
            PresellCancelFragment presellCancelFragment = new PresellCancelFragment();
            presellCancelFragment.setSearchOrderType("0");
            listFragment.add(presellCancelFragment);

        } else if (title.equals("预售订单")) {
            //预售订单全部
            PresellallFragment all = new PresellallFragment();
            all.setSearchOrderType("1");
            listFragment.add(all);
//           待支付
            PresellToBePaidFragment presellToBePaidFragment = new PresellToBePaidFragment();
            presellToBePaidFragment.setSearchOrderType("1");
            listFragment.add(presellToBePaidFragment);
//            已支付
            PresellAlreadyPaidFragment presellAlreadyPaidFragment = new PresellAlreadyPaidFragment();
            presellAlreadyPaidFragment.setSearchOrderType("1");
            listFragment.add(presellAlreadyPaidFragment);
//            已变更
            PresellChangeFragment presellChangeFragment = new PresellChangeFragment();
            presellChangeFragment.setSearchOrderType("1");
            listFragment.add(presellChangeFragment);
//            已取消
            PresellCancelFragment presellCancelFragment = new PresellCancelFragment();
            presellCancelFragment.setSearchOrderType("1");
            listFragment.add(presellCancelFragment);
        } else if (title.equals("客服订单")) {
            //客服订单访问数据库的内容
            ServiceAllFragment all = new ServiceAllFragment();
            listFragment.add(all);
            ServiceBornFragment bornFragment = new ServiceBornFragment();
            listFragment.add(bornFragment);
            ServiceShipmentsFragment shipmentsFragment = new ServiceShipmentsFragment();
            listFragment.add(shipmentsFragment);
            ServiceFinishFragment finishFragment = new ServiceFinishFragment();
            listFragment.add(finishFragment);
            ServiceChangeFragment change = new ServiceChangeFragment();
            listFragment.add(change);
        }

        tv_title_name.setText(title);


        relative = (RelativeLayout) findViewById(R.id.order_layout_top);
        back = (ImageView) findViewById(R.id.ivBack_order_layout_top);
        search = (ImageView) findViewById(R.id.ivSearch_order_layout_top);

        adapter = new MyPageAdapter(getSupportFragmentManager());
        adapter.setData(listFragment);


        listString = new ArrayList<String>();

        if (title.equals("客服订单")) {
            listString.add("全部订单");
            listString.add("订单生成");
            listString.add("已发货");
            listString.add("已完成");
            listString.add("已变更");
        }
        if (title.equals("预售订单") || title.equals("现货订单")) {
            listString.add("全部订单");
            listString.add("待支付");
            listString.add("已支付");
            listString.add("已变更");
            listString.add("已取消");
        }


        adapter.setTitles(listString);


        tlDemo = (TabLayout) findViewById(R.id.tab_layout);
        vpDemo = (ViewPager) findViewById(R.id.view_pager);
        vpDemo.setAdapter(adapter);
        tlDemo.setupWithViewPager(vpDemo);
//        vpDemo.setOffscreenPageLimit(7);
        tlDemo.setTabMode(TabLayout.MODE_SCROLLABLE);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OrderGoodsActivity.this.finish();
            }
        });

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //PopupWindow
//                Toast.makeText(OrderGoodsActivity.this, "弹出对话框", Toast.LENGTH_SHORT).show();
                initData();
               // initView();
               // showPopView();
                dialog();
//                WindowManager.LayoutParams lp =getWindow().getAttributes();
//                lp.alpha =0.5f; //0.0-1.0
//                getWindow().setAttributes(lp);
            }
        });

    }
    public void dialog(){
        mDialog= new Dialog(OrderGoodsActivity.this);
//创建dialog
        mDialog.getWindow().setBackgroundDrawable(getResources().getDrawable(R.color.bottom_text_color_normal));

//设置背景颜色
        Window win = mDialog.getWindow();
        mDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
//获取dialog窗口
        popView = View.inflate(this, R.layout.popwindow, null);
        win.setContentView(popView);
        Tv_jiaoyi = (TextView) popView.findViewById(R.id.tv_dingdan_jiaoyi);
        Tv_kaipiao = (TextView) popView.findViewById(R.id.tv_dingdan_kaioiao);
        Tv_dingdan = (TextView) popView.findViewById(R.id.tv_dingdan_zhuangtai);
        Lv_jiaoyi = (ListView) popView.findViewById(R.id.lv_dingdan_jiaoyi);
        Lv_kaipiao = (ListView) popView.findViewById(R.id.lv_dingdan_kaipiao);
        Lv_dingdan = (ListView) popView.findViewById(R.id.lv_dingdan_zhuangtai);
        Tv_dingdan_startdate = (TextView) popView.findViewById(R.id.tv_dingdan_start);
        Tv_dingdan_enddate = (TextView) popView.findViewById(R.id.tv_dingdan_end);
        edt_dingdan_company = (EditText) popView.findViewById(R.id.edt_dingdan_gongsi);
        edt_dingdan_num = (EditText) popView.findViewById(R.id.edt_dingdan_bianhao);
        btn_dingdan_my = (Button) popView.findViewById(R.id.btn_dingdan_my);
        btn_dingdan_all = (Button) popView.findViewById(R.id.btn_dingdan_all);
        ImageView ivBack_order_layout_top= (ImageView) popView.findViewById(R.id.ivBack_order_layout_top);
        ivBack_order_layout_top.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialog.dismiss();
            }
        });
        linear = (LinearLayout) popView.findViewById(R.id.linear7_dingdan_bottem);
        linear.setOnClickListener(this);
        Tv_jiaoyi.setOnClickListener(this);
        Tv_kaipiao.setOnClickListener(this);
        Tv_dingdan.setOnClickListener(this);
        Tv_dingdan_startdate.setOnClickListener(this);
        Tv_dingdan_enddate.setOnClickListener(this);
        btn_dingdan_my.setOnClickListener(this);
        btn_dingdan_all.setOnClickListener(this);
        Lv_dingdan.setAdapter(LvAdapter);
        Lv_jiaoyi.setAdapter(JyLvAdapter);
        Lv_kaipiao.setAdapter(KpLvAdapter);
        Lv_jiaoyi.setOnItemClickListener(this);
        Lv_kaipiao.setOnItemClickListener(this);
        Lv_dingdan.setOnItemClickListener(this);
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
      //  mDialog.dismiss();
    }



    @Override
    public void onClick(View v) {
        String submitTypeInput;
        switch (v.getId()) {
            case R.id.tv_dingdan_jiaoyi:
                //点击显示listview
                first = "one";
                logicdisplayshowListView(Lv_jiaoyi, first);
                hideListView(Lv_kaipiao);
                hideListView(Lv_dingdan);
                break;
            case R.id.tv_dingdan_kaioiao:
                //点击显示listview
                first = "two";
                logicdisplayshowListView(Lv_kaipiao, first);
                hideListView(Lv_jiaoyi);
                hideListView(Lv_jiaoyi);
                break;
            case R.id.tv_dingdan_zhuangtai:
                //点击显示listview
                first = "three";
                logicdisplayshowListView(Lv_dingdan, first);
                hideListView(Lv_jiaoyi);
                hideListView(Lv_kaipiao);
                break;
            case R.id.tv_dingdan_start:
                //点击弹出日期对话框
                showDate(Tv_dingdan_startdate);
                break;
            case R.id.tv_dingdan_end:
                //点击弹出日期对话框
                showDate(Tv_dingdan_enddate);
                break;
            case R.id.btn_dingdan_my://popwindow下的查询我的订单按钮
                submitTypeInput = "1";
                changeThePresellSelectPosition(submitTypeInput);
                //popupWindow.dismiss();
                mDialog.dismiss();
                break;
            case R.id.btn_dingdan_all://popwindow下的查询所有订单按钮
                submitTypeInput = "2";
                changeThePresellSelectPosition(submitTypeInput);
                //popupWindow.dismiss();
                mDialog.dismiss();
                break;
            case R.id.linear7_dingdan_bottem:
               // popupWindow.dismiss();
                mDialog.dismiss();
                break;
        }
    }

    private void changeThePresellSelectPosition(String submitTypeInput) {
        /*胆小慎入*/
        String searchCompanyName = edt_dingdan_company.getText().toString().trim();//公司

        String searchOrderTypeInput = Tv_jiaoyi.getText().toString()+""; //订单类型(预售)
        String searchTrateType = "";
        String searchOrderType = "";//区分现货订单和预售订单的参数
        if (searchOrderTypeInput.equals("团购")){
            searchOrderType = "0";
            searchTrateType = "integratePurchaseOrder";
        }else if (searchOrderTypeInput.equals("现货")){
            searchOrderType = "0" ;
            searchTrateType = "fixedPricingOrder" ;
        }else if (searchOrderTypeInput.equals("公开竞拍")){
            searchOrderType = "0" ;
            searchTrateType = "openAuctionOrder" ;
        }else if (searchOrderTypeInput.equals("密封竞拍")){
            searchOrderType = "0" ;
            searchTrateType = "sealedAuctionOrder" ;
        }else if (searchOrderTypeInput.equals("计划订单")){
            searchOrderType = "0" ;
            searchTrateType = "scheduleOrder" ;
        }else if (searchOrderTypeInput.equals("预售")){
            Log.d("预售","预售");
            searchOrderType = "1" ;
            searchTrateType = "" ;
        }else if (searchOrderTypeInput.equals("")){
            if (title.equals("现货订单")){
                searchOrderType = "0" ;
                searchTrateType = "" ;
            }else {
                Log.d("预售订单","预售迪昂单");
                searchOrderType = "" ;
                searchTrateType = "forwardPricingOrder" ;
            }

        }

        String searchOrderState = Tv_dingdan.getText().toString()+"";//订单状态
        String pageNum = "1";
        String searchOrderId = edt_dingdan_num.getText().toString() + "";//订单
        String startDate = Tv_dingdan_startdate.getText().toString();//开始日期

        String endDate = Tv_dingdan_enddate.getText().toString();//结束日期
        String submitType = submitTypeInput;//查询类型（1 查询我的订单  2 查询所有订单  默认为1）

        String searchCheckType = Tv_kaipiao.getText().toString();//开票状态
        String KPstate = "";
        if (searchCheckType.equals("所有")) {
            KPstate = "";
        } else if (searchCheckType.equals("待开票")) {
            KPstate = "0";
        } else if (searchCheckType.equals("已开票")) {
            KPstate = "1";
        }
        boolean update = true;
        LogUtils.log("searchCompanyName-->" + searchCompanyName +
                "  \nsearchOrderType-->" + searchOrderType +
                "  \nsearchTrateType-->" + searchTrateType +
                "  \nsearchOrderState-->" + searchOrderState +
                "  \npageNum-->" + pageNum +
                "  \nsearchOrderId-->" + searchOrderId +
                "  \nstartDate-->" + startDate +
                "  \nendDate-->" + endDate +
                "  \nsubmitType-->" + submitType +
                "  \nsearchCheckType-->" + searchCheckType +
                "  \nKPstate-->" + KPstate);
        Log.d("searchOrderState的值",searchOrderState);
        if (searchOrderState.equals("")) {
            if (tlDemo.getSelectedTabPosition() == 0) {
                Log.d("进来了","进来了");
                PresellallFragment fragment = (PresellallFragment) listFragment.get(vpDemo.getCurrentItem());
                fragment.sendMy(searchTrateType,searchOrderType, pageNum, submitType, KPstate, startDate, endDate, searchCompanyName, searchOrderId, update,"dialog");
            } else if (tlDemo.getSelectedTabPosition() == 1) {
                PresellToBePaidFragment fragment = (PresellToBePaidFragment) listFragment.get(vpDemo.getCurrentItem());
                fragment.sendMy(searchTrateType,searchOrderType, pageNum, submitType, KPstate, startDate, endDate, searchCompanyName, searchOrderId, update,"dialog");
            } else if (tlDemo.getSelectedTabPosition() == 2) {
                PresellAlreadyPaidFragment fragment = (PresellAlreadyPaidFragment) listFragment.get(vpDemo.getCurrentItem());
                fragment.sendMy(searchTrateType,searchOrderType, pageNum, submitType, KPstate, startDate, endDate, searchCompanyName, searchOrderId, update,"dialog");
            } else if (tlDemo.getSelectedTabPosition() == 3) {
                PresellChangeFragment fragment = (PresellChangeFragment) listFragment.get(vpDemo.getCurrentItem());
                fragment.sendMy(searchTrateType,searchOrderType, pageNum, submitType, KPstate, startDate, endDate, searchCompanyName, searchOrderId, update,"dialog");
            } else if (tlDemo.getSelectedTabPosition() == 4) {
                PresellCancelFragment fragment = (PresellCancelFragment) listFragment.get(vpDemo.getCurrentItem());
                fragment.sendMy(searchTrateType,searchOrderType, pageNum, submitType, KPstate, startDate, endDate, searchCompanyName, searchOrderId, update,"dialog");
            }

        } else if (Tv_dingdan.getText().toString().equals("所有")) {
            PresellallFragment fragment = (PresellallFragment) listFragment.get(0);
            fragment.sendMy(searchTrateType,searchOrderType, pageNum, submitType, KPstate, startDate, endDate, searchCompanyName, searchOrderId, update,"dialog");
            vpDemo.setCurrentItem(0);
        } else if (Tv_dingdan.getText().toString().equals("待支付")) {
            PresellToBePaidFragment fragment = (PresellToBePaidFragment) listFragment.get(1);
            fragment.sendMy(searchTrateType,searchOrderType, pageNum, submitType, KPstate, startDate, endDate, searchCompanyName, searchOrderId, update,"dialog");
            vpDemo.setCurrentItem(1);
        } else if (Tv_dingdan.getText().toString().equals("已支付")) {
            PresellAlreadyPaidFragment fragment = (PresellAlreadyPaidFragment) listFragment.get(2);
            fragment.sendMy(searchTrateType,searchOrderType, pageNum, submitType, KPstate, startDate, endDate, searchCompanyName, searchOrderId, update,"dialog");
            vpDemo.setCurrentItem(2);
        } else if (Tv_dingdan.getText().toString().equals("已变更")) {
            vpDemo.setCurrentItem(3);
            PresellChangeFragment fragment = (PresellChangeFragment) listFragment.get(3);
            fragment.sendMy(searchTrateType,searchOrderType, pageNum, submitType, KPstate, startDate, endDate, searchCompanyName, searchOrderId, update,"dialog");
            //fragment.sendMy(searchTrateType,searchOrderType, pageNum, submitType, KPstate, startDate, endDate, searchCompanyName, searchOrderId, update);

        } else if (Tv_dingdan.getText().toString().equals("已取消")) {
            vpDemo.setCurrentItem(4);
            PresellCancelFragment fragment = (PresellCancelFragment) listFragment.get(4);
           // Log.d();
            fragment.sendMy(searchTrateType,searchOrderType, pageNum, submitType, KPstate, startDate, endDate, searchCompanyName, searchOrderId, update,"dialog");

        }
    }



    //给ListView添加虚假数据源
    private void initData() {
        List<String> list = new ArrayList<String>();
        List<String> Jylist = new ArrayList<String>();
        List<String> Kplist = new ArrayList<String>();
        if (tv_title_name.getText().equals("客服订单")) {
            list.add("所有");
            list.add("订单生成");
            list.add("已发货");
            list.add("已完成");
            list.add("已变更");
            Jylist.add("客服");
        } else {
            list.add("所有");
            list.add("待支付");
            list.add("已支付");
//            list.add("已发货");
//            list.add("已完成");
            list.add("已取消");
            list.add("已变更");
            if (tv_title_name.getText().equals("现货订单")) {
                Jylist.add("现货");
                Jylist.add("公开竞拍");
                Jylist.add("密封竞拍");
                Jylist.add("团购");
                Jylist.add("计划订单");
            } else if (tv_title_name.getText().equals("预售订单")) {
                Jylist.add("预售");
            }
        }
        LvAdapter = new ArrayAdapter<String>(this,
                R.layout.item_view,
                R.id.tv_item, list);
        Kplist.add("所有");
        Kplist.add("待开票");
        Kplist.add("已开票");
        KpLvAdapter = new ArrayAdapter<String>(this, R.layout.item_view,
                R.id.tv_item, Kplist);


        JyLvAdapter = new ArrayAdapter<String>(this, R.layout.item_view,
                R.id.tv_item, Jylist);

    }

    //弹出的popWindow
    private void showPopView() {
        //  popView=View.inflate(this,R.layout.popwindow,null);
        popView.measure(0, 0);
        //  Log.d("aa",""+popView);

        int w = getWindowManager().getDefaultDisplay().getWidth();
        popupWindow = new PopupWindow(popView, w, popView.getMeasuredHeight());
        popupWindow.setFocusable(true);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.showAsDropDown(relative, 0, 0);
    }

    //显示或隐藏listView
    private void showLv(ListView Lv) {
        if (flag) {
            Lv.setVisibility(View.VISIBLE);
//            Lv.setAdapter(LvAdapter);
            flag = false;
        } else {
            Lv.setVisibility(View.GONE);
            flag = true;
        }
    }

    //显示日期
    private void showDate(final TextView Tv) {
        Calendar calend1 = Calendar.getInstance();
        calend1.setTimeInMillis(System.currentTimeMillis());
        int year = calend1.get(Calendar.YEAR);
        int month = calend1.get(Calendar.MONTH);
        int day = calend1.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog dialog3 = new DatePickerDialog(
                OrderGoodsActivity.this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view,
                                          int year, int monthOfYear,
                                          int dayOfMonth) {

                        Tv.setText(year + "-" + (monthOfYear+1)
                                + "-" + dayOfMonth);
                    }
                }, year, month, day);
        dialog3.show();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (Lv_jiaoyi == parent) {
            Tv_jiaoyi.setText(JyLvAdapter.getItem(position));
            if (Tv_jiaoyi.getText().equals("现货")) {
                OrderType = "fixedPricingOrder";
            } else if (Tv_jiaoyi.getText().equals("公开竞拍")) {
                OrderType = "openAuctionOrder";
            } else if (Tv_jiaoyi.getText().equals("密封竞拍")) {
                OrderType = "sealedAuctionOrder";
            } else if (Tv_jiaoyi.getText().equals("团购")) {
                OrderType = "integratePurchaseOrder";
            } else if (Tv_jiaoyi.getText().equals("预售")) {
                OrderType = "forwardPricingOrder";
            }
            Lv_jiaoyi.setVisibility(View.GONE);
        }
        if (Lv_kaipiao == parent) {
            Tv_kaipiao.setText(KpLvAdapter.getItem(position));
            if (Tv_kaipiao.getText().equals("待开票")) {
                CheckType = "0";
            } else if (Tv_kaipiao.getText().equals("已开票")) {
                CheckType = "1";
            }
            Lv_kaipiao.setVisibility(View.GONE);
        }
        if (Lv_dingdan == parent) {
            Tv_dingdan.setText(LvAdapter.getItem(position));
            if (Tv_dingdan.getText().equals("所有")) {
                OrderState = "0";
            } else if (Tv_dingdan.getText().equals("待支付")) {
                OrderState = 1 + "";
            } else if (Tv_dingdan.getText().equals("已支付")) {
                OrderState = "QUOTED";
            }  else if (Tv_dingdan.getText().equals("已取消")) {
                OrderState = "REMOVED";
            } else if (Tv_dingdan.getText().equals("已变更")) {
                OrderState = "CHANGED";
            } else if (Tv_dingdan.getText().equals("")) {
                OrderState = "0";
            }/*else if (Tv_dingdan.getText().equals("已发货")) {
                OrderState = "PRESSING1";
            } else if (Tv_dingdan.getText().equals("已完成")) {
                OrderState = "NO_PENDING_ACTION";
            } else if(Tv_dingdan.getText().equals("订单生成")){
                OrderState="APPROVED";
            }*/
            Lv_dingdan.setVisibility(View.GONE);
        }
    }

    /**
     * 如果已经打开一个listView，点击另一个，这个listView影藏
     *
     * @param listview
     * @param first
     */
    private void logicdisplayshowListView(ListView listview, String first) {

        if (second.equals(first)) {
            showLv(listview);
            second = first;
        } else {
            flag = true;
            showLv(listview);
        }

    }

    private void hideListView(ListView listview) {
        listview.setVisibility(View.GONE);
    }
}


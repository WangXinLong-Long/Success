package com.silianchuangye.sumao.success.fragments.myPlasticTrade.OrderManagement.goodsInStock;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.SharedPreferences;
import android.graphics.drawable.BitmapDrawable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.OrderManagement.SpotOrder.OrderAlreadyFinishFragment;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.OrderManagement.SpotOrder.OrderAlreadygoodsFragment;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.OrderManagement.SpotOrder.OrderCancelFragment;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.OrderManagement.SpotOrder.OrderStaypayFragment;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.OrderManagement.SpotOrder.OrderUpdateFragment;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.OrderManagement.SpotOrder.OrderallFragment;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.OrderManagement.SpotOrder.OrderstayshipmentsFragment;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.OrderManagement.SpotOrder.PresellAreadyShipmentsFragment;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.OrderManagement.SpotOrder.PresellChangeFragment;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.OrderManagement.SpotOrder.PresellFinishFragment;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.OrderManagement.SpotOrder.PresellPayFragment;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.OrderManagement.SpotOrder.PresellShipmentsFragment;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.OrderManagement.SpotOrder.PresellUpdateFragment;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.OrderManagement.SpotOrder.PresellallFragment;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.OrderManagement.SpotOrder.ServiceAllFragment;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.OrderManagement.SpotOrder.ServiceBornFragment;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.OrderManagement.SpotOrder.ServiceChangeFragment;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.OrderManagement.SpotOrder.ServiceFinishFragment;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.OrderManagement.SpotOrder.ServiceShipmentsFragment;
import com.silianchuangye.sumao.success.utils.SuMaoConstant;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class OrderGoodsActivity extends AppCompatActivity implements View.OnClickListener,AdapterView.OnItemClickListener{
    //popViewdow
    private PopupWindow popupWindow;
    private View popView;
    private LinearLayout linear;
    private TextView Tv_jiaoyi,Tv_kaipiao,Tv_dingdan,Tv_dingdan_startdate,Tv_dingdan_enddate;
    private EditText edt_dingdan_company,edt_dingdan_num;
    private ListView Lv_jiaoyi,Lv_kaipiao,Lv_dingdan;
    private Button btn_dingdan_my,btn_dingdan_all;
    private List<String> list=new ArrayList<String>();
    private ArrayAdapter<String> LvAdapter;
    private List<String> Jylist=new ArrayList<String>();
    private ArrayAdapter<String> JyLvAdapter;
    private List<String> Kplist=new ArrayList<String>();
    private ArrayAdapter<String> KpLvAdapter;
    private boolean flag=true;
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
    private String  title;

    private int i;
    private String OrderType;
    private String OrderState;
    private int CheckType;
    private String startDate,endDate;
    private String company,OrderId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_goods);
        tv_title_name= (TextView) findViewById(R.id.tv_title_name_order);
        Bundle bundle=getIntent().getExtras();
         title=bundle.getString("title");
        listFragment=new ArrayList<Fragment>();
        if (title.equals("现货订单")){
            //现货订单访问数据库的内容
            OrderallFragment all=new OrderallFragment();
            listFragment.add(all);
            OrderStaypayFragment pay=new OrderStaypayFragment();
            listFragment.add(pay);
            OrderstayshipmentsFragment staygoods=new OrderstayshipmentsFragment();
            listFragment.add(staygoods);
            OrderAlreadygoodsFragment goods=new OrderAlreadygoodsFragment();
            listFragment.add(goods);
            OrderAlreadyFinishFragment finish=new OrderAlreadyFinishFragment();
            listFragment.add(finish);

            OrderUpdateFragment update=new OrderUpdateFragment();
            listFragment.add(update);
            OrderCancelFragment cancel=new OrderCancelFragment();
            listFragment.add(cancel);

        }else if(title.equals("预售订单")){
            //预售订单访问数据库的内容
            PresellallFragment all=new PresellallFragment();
            listFragment.add(all);
            PresellPayFragment pay=new PresellPayFragment();
            listFragment.add(pay);
            PresellShipmentsFragment shipments=new PresellShipmentsFragment();
            listFragment.add(shipments);
            PresellAreadyShipmentsFragment areadyShipments=new PresellAreadyShipmentsFragment();
            listFragment.add(areadyShipments);
            PresellFinishFragment finish=new PresellFinishFragment();
            listFragment.add(finish);
            PresellChangeFragment change=new PresellChangeFragment();
            listFragment.add(change);
            PresellUpdateFragment updateFragment=new PresellUpdateFragment();
            listFragment.add(updateFragment);
        }else if(title.equals("客服订单")){
            //客服订单访问数据库的内容
            ServiceAllFragment all=new ServiceAllFragment();
            listFragment.add(all);
            ServiceBornFragment bornFragment=new ServiceBornFragment();
            listFragment.add(bornFragment);
            ServiceShipmentsFragment shipmentsFragment=new ServiceShipmentsFragment();
            listFragment.add(shipmentsFragment);
            ServiceFinishFragment finishFragment=new ServiceFinishFragment();
            listFragment.add(finishFragment);
            ServiceChangeFragment change=new ServiceChangeFragment();
            listFragment.add(change);
        }

        tv_title_name.setText(title);


        relative= (RelativeLayout) findViewById(R.id.order_layout_top);
        back= (ImageView) findViewById(R.id.ivBack_order_layout_top);
        search= (ImageView) findViewById(R.id.ivSearch_order_layout_top);

        adapter=new MyPageAdapter(getSupportFragmentManager());
        adapter.setData(listFragment);


        ArrayList<String> listString=new ArrayList<String>();

        if (title.equals("客服订单")){
            listString.add("全部订单");
            listString.add("订单生成");
            listString.add("已发货");
            listString.add("已完成");
            listString.add("已变更");
        }
        if (title.equals("预售订单")||title.equals("现货订单"))
        {
            listString.add("全部订单");
            listString.add("待支付");
            listString.add("待发货");
            listString.add("已发货");
            listString.add("已完成");
            listString.add("已变更");
            listString.add("已取消");
        }



        adapter.setTitles(listString);


        tlDemo= (TabLayout) findViewById(R.id.tlDemo);
        vpDemo= (ViewPager) findViewById(R.id.vpDemo);
        vpDemo.setAdapter(adapter);
        tlDemo.setupWithViewPager(vpDemo);

            tlDemo.setTabMode(tlDemo.MODE_SCROLLABLE);

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
                initView();
                showPopView();
//                WindowManager.LayoutParams lp =getWindow().getAttributes();
//                lp.alpha =0.5f; //0.0-1.0
//                getWindow().setAttributes(lp);
            }
        });

}





    private void initView() {
        //加载popwindow以及popWindow不局下的控件
        popView=View.inflate(this,R.layout.popwindow,null);
       // popView=getLayoutInflater().inflate(R.layout.popwindow,null);
        Tv_jiaoyi= (TextView) popView.findViewById(R.id.tv_dingdan_jiaoyi);
        Tv_kaipiao= (TextView) popView.findViewById(R.id.tv_dingdan_kaioiao);
        Tv_dingdan= (TextView) popView.findViewById(R.id.tv_dingdan_zhuangtai);
        Lv_jiaoyi= (ListView) popView.findViewById(R.id.lv_dingdan_jiaoyi);
        Lv_kaipiao= (ListView) popView.findViewById(R.id.lv_dingdan_kaipiao);
        Lv_dingdan= (ListView) popView.findViewById(R.id.lv_dingdan_zhuangtai);
        Tv_dingdan_startdate= (TextView) popView.findViewById(R.id.tv_dingdan_start);
        Tv_dingdan_enddate= (TextView) popView.findViewById(R.id.tv_dingdan_end);
        edt_dingdan_company= (EditText) popView.findViewById(R.id.edt_dingdan_gongsi);
        edt_dingdan_num= (EditText) popView.findViewById(R.id.edt_dingdan_bianhao);
        btn_dingdan_my= (Button) popView.findViewById(R.id.btn_dingdan_my);
        btn_dingdan_all= (Button) popView.findViewById(R.id.btn_dingdan_all);
        linear= (LinearLayout) popView.findViewById(R.id.linear7_dingdan_bottem);
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
        Tv_jiaoyi.setText(JyLvAdapter.getItem(0));
//        Tv_dingdan.setText(LvAdapter.getItem(0));
//        Tv_kaipiao.setText(KpLvAdapter.getItem(0));

    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_dingdan_jiaoyi:
                //点击显示listview
//                showLv(Lv_jiaoyi);
                first = "one";
                logicdisplayshowListView(Lv_jiaoyi,first);
                hideListView(Lv_kaipiao);
                hideListView(Lv_dingdan);
                break;
            case R.id.tv_dingdan_kaioiao:
                //点击显示listview
                first = "two";
//                showLv(Lv_kaipiao);
                logicdisplayshowListView(Lv_kaipiao,first);
                hideListView(Lv_jiaoyi);
                hideListView(Lv_jiaoyi);
                break;
            case R.id.tv_dingdan_zhuangtai:
                //点击显示listview
                first = "three";
//                showLv(Lv_dingdan);
                logicdisplayshowListView(Lv_dingdan,first);
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
            case R.id.btn_dingdan_my:
                i=1;
//                sendMy();
                Toast.makeText(OrderGoodsActivity.this,"点击了我的意向单",Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_dingdan_all:
                i=2;
//                sendMy();
                Toast.makeText(OrderGoodsActivity.this,"点击了所有意向单",Toast.LENGTH_SHORT).show();
                break;
            case R.id.linear7_dingdan_bottem:
                popupWindow.dismiss();
                break;
        }
    }
    //给ListView添加虚假数据源
    private void initData() {
        List<String> list=new ArrayList<String>();
        List<String> Jylist=new ArrayList<String>();
        List<String> Kplist=new ArrayList<String>();
        list.add("所有");
       list.add("待支付");
        list.add("已支付");
        list.add("已发货");
        list.add("已完成");
        list.add("已取消");
        list.add("已变更");
        LvAdapter=new ArrayAdapter<String>(this,
                R.layout.item_view,
                R.id.tv_item,list);
        Kplist.add("所有");
        Kplist.add("代开票");
        Kplist.add("已开票");
        KpLvAdapter=new ArrayAdapter<String>(this,R.layout.item_view,
                R.id.tv_item,Kplist);
        Jylist.add("现货");
        Jylist.add("公开竞拍");
        Jylist.add("密封竞拍");
        Jylist.add("团购");
        Jylist.add("预售");
        JyLvAdapter =new ArrayAdapter<String>(this,R.layout.item_view,
                R.id.tv_item,Jylist);

    }
    //弹出的popWindow
    private void showPopView(){
      //  popView=View.inflate(this,R.layout.popwindow,null);
        popView.measure(0,0);
      //  Log.d("aa",""+popView);

        int w=getWindowManager().getDefaultDisplay().getWidth();
        popupWindow=new PopupWindow(popView,w,popView.getMeasuredHeight());
        popupWindow.setFocusable(true);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.showAsDropDown(relative,0,0);
    }
    //显示或隐藏listView
    private void showLv(ListView Lv){
        if(flag){
            Lv.setVisibility(View.VISIBLE);
//            Lv.setAdapter(LvAdapter);
            flag=false;
        }else{
            Lv.setVisibility(View.GONE);
            flag=true;
        }
    }
    //显示日期
    private void showDate(final TextView Tv){
        Calendar calend1 = Calendar.getInstance();
        calend1.setTimeInMillis(System.currentTimeMillis());
        int year = calend1.get(Calendar.YEAR);
        int month = calend1.get(Calendar.MONTH) + 1;
        int day = calend1.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog dialog3 = new DatePickerDialog(
                OrderGoodsActivity.this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view,
                                          int year, int monthOfYear,
                                          int dayOfMonth) {
                        Toast.makeText(OrderGoodsActivity.this,
                                "" + year + "年" + (monthOfYear + 1)
                                        + "月" + dayOfMonth + "日", Toast.LENGTH_LONG).show();
                        Tv.setText(year + "年" + (monthOfYear + 1)
                                + "月" + dayOfMonth + "日");
                    }
                }, year, month, day);
        dialog3.show();
    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if(Lv_jiaoyi==parent){
            Tv_jiaoyi.setText(JyLvAdapter.getItem(position));
            if(Tv_jiaoyi.getText().equals("现货")){
                OrderType="fixedPricingOrder";
            }else if(Tv_jiaoyi.getText().equals("公开竞拍")){
                OrderType="openAuctionOrder";
            }else if(Tv_jiaoyi.getText().equals("密封竞拍")){
                OrderType="sealedAuctionOrder";
            }else if(Tv_jiaoyi.getText().equals("团购")){
                OrderType="integratePurchaseOrder";
            }else if(Tv_jiaoyi.getText().equals("预售")){
                OrderType="forwardPricingOrder";
            }
            Lv_jiaoyi.setVisibility(View.GONE);
        }
        if(Lv_kaipiao==parent){
            Tv_kaipiao.setText(KpLvAdapter.getItem(position));
            if(Tv_kaipiao.getText().equals("待开票")){
                CheckType=0;
            }else if(Tv_kaipiao.getText().equals("已开票")){
                CheckType=1;
            }
            Lv_kaipiao.setVisibility(View.GONE);
        }
        if(Lv_dingdan==parent){
            Tv_dingdan.setText(LvAdapter.getItem(position));
            if(Tv_dingdan.getText().equals("所有")){
                OrderState="0";
            }else if(Tv_dingdan.getText().equals("待支付")){
                OrderState=1+"";
            }else if(Tv_dingdan.getText().equals("已支付")){
                OrderState="QUOTED";
            }else if(Tv_dingdan.getText().equals("已发货")){
                OrderState="PRESSING1";
            }else if(Tv_dingdan.getText().equals("已完成")){
                OrderState="NO_PENDING_ACTIO";
            }else if(Tv_dingdan.getText().equals("已取消")){
                OrderState="REMOVED";
            }else if(Tv_dingdan.getText().equals("已变更")){
                OrderState="CHANGED";
            }
            Lv_dingdan.setVisibility(View.GONE);
        }
    }
    /**
     * 如果已经打开一个listView，点击另一个，这个listView影藏
     * @param listview
     * @param first
     */
    private void logicdisplayshowListView(ListView listview,String first) {

        if (second.equals(first))
        {
            showLv(listview);
            second  = first;
        }else {
            flag  = true;
            showLv(listview);
        }

    }
    private void hideListView(ListView listview)
    {
        listview.setVisibility(View.GONE);
    }
    private void sendMy(){
        startDate=Tv_dingdan_startdate.getText().toString();//开始日期
        endDate=Tv_dingdan_enddate.getText().toString();//结束日期
        company=edt_dingdan_company.getText().toString();//公司
        OrderId=edt_dingdan_num.getText().toString();//订单号
        Log.e("TAG","OrderId---"+OrderId+"----startDate---"+startDate+"----endDate-----"+endDate+"---company===="+company);
        RequestParams params=new RequestParams(SuMaoConstant.SUMAO_IP+"/rest/model/atg/userprofiling/ProfileActor/myOrders");
        params.addParameter("pageNum",1);
        params.addParameter("searchOrderId",OrderId);
        params.addParameter("startDate",startDate);
        params.addParameter("endDate",endDate);
        params.addParameter("submitType",i);
        params.addParameter("searchOrderType",OrderType);
        params.addParameter("searchOrderState",OrderState);
//        params.addParameter("searchCompanyName",company);
        params.addParameter("searchCheckType",CheckType);
        SharedPreferences sp = getSharedPreferences("sumao", Activity.MODE_PRIVATE);
        String unique123 = sp.getString("unique", "");
        params.addParameter("_dynSessConf", unique123);
        Log.e("TAG","parames======"+params);
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.e("TAG","result----"+result);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.e("TAG","失败呢");
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }
}


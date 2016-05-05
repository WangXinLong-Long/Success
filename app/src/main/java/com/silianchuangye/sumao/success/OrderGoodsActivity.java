package com.silianchuangye.sumao.success;

import android.app.DatePickerDialog;
import android.graphics.drawable.BitmapDrawable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

import com.silianchuangye.sumao.success.adapter.MyPageAdapter;
import com.silianchuangye.sumao.success.fragments.OrderAlreadyFinishFragment;
import com.silianchuangye.sumao.success.fragments.OrderAlreadygoodsFragment;
import com.silianchuangye.sumao.success.fragments.OrderCancelFragment;
import com.silianchuangye.sumao.success.fragments.OrderStaypayFragment;
import com.silianchuangye.sumao.success.fragments.OrderUpdateFragment;
import com.silianchuangye.sumao.success.fragments.OrderallFragment;
import com.silianchuangye.sumao.success.fragments.OrderstayshipmentsFragment;

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
    private boolean flag=true;

    private ArrayList<Fragment> listFragment;
    private TabLayout tlDemo;
    private ViewPager vpDemo;
    private MyPageAdapter adapter;
    private ImageView back;
    private ImageView search;
    private RelativeLayout relative;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_goods);
        relative= (RelativeLayout) findViewById(R.id.order_layout_top);
        back= (ImageView) findViewById(R.id.ivBack_order_layout_top);
        search= (ImageView) findViewById(R.id.ivSearch_order_layout_top);
        listFragment=new ArrayList<Fragment>();
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
        OrderCancelFragment cancel=new OrderCancelFragment();
        listFragment.add(cancel);
        OrderUpdateFragment update=new OrderUpdateFragment();
        listFragment.add(update);
        adapter=new MyPageAdapter(getSupportFragmentManager());
        adapter.setData(listFragment);


        ArrayList<String> listString=new ArrayList<String>();
        listString.add("全部订单");
        listString.add("待支付");
        listString.add("待发货");
        listString.add("已发货");
        listString.add("已完成");
        listString.add("已变更");
        listString.add("已取消");
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
        Lv_jiaoyi.setOnItemClickListener(this);
        Lv_kaipiao.setOnItemClickListener(this);
        Lv_dingdan.setOnItemClickListener(this);
        Tv_jiaoyi.setText(LvAdapter.getItem(0));
        Tv_dingdan.setText(LvAdapter.getItem(0));
        Tv_kaipiao.setText(LvAdapter.getItem(0));
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_dingdan_jiaoyi:
                //点击显示listview
                showLv(Lv_jiaoyi);
                break;
            case R.id.tv_dingdan_kaioiao:
                //点击显示listview
                showLv(Lv_kaipiao);
                break;
            case R.id.tv_dingdan_zhuangtai:
                //点击显示listview
                showLv(Lv_dingdan);
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
                Toast.makeText(OrderGoodsActivity.this,"点击了我的意向单",Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_dingdan_all:
                Toast.makeText(OrderGoodsActivity.this,"点击了所有意向单",Toast.LENGTH_SHORT).show();
                break;
            case R.id.linear7_dingdan_bottem:
                popupWindow.dismiss();
                break;
        }
    }
    //给ListView添加虚假数据源
    private void initData() {
        for(int i=0;i<5;i++){
            list.add("itemview"+i);
        }
        LvAdapter=new ArrayAdapter<String>(this,
                R.layout.item_view,
                R.id.tv_item,list);
    }
    //弹出的popWindow
    private void showPopView(){
      //  popView=View.inflate(this,R.layout.popwindow,null);
        popView.measure(0,0);

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
            Lv.setAdapter(LvAdapter);
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
            Tv_jiaoyi.setText(LvAdapter.getItem(position));
            Lv_jiaoyi.setVisibility(View.GONE);
        }
        if(Lv_kaipiao==parent){
            Tv_kaipiao.setText(LvAdapter.getItem(position));
            Lv_kaipiao.setVisibility(View.GONE);
        }
        if(Lv_dingdan==parent){
            Tv_dingdan.setText(LvAdapter.getItem(position));
            Lv_dingdan.setVisibility(View.GONE);
        }
    }
}

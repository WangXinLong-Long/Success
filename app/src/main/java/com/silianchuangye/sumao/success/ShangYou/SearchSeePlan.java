package com.silianchuangye.sumao.success.ShangYou;

import android.content.ClipboardManager;
import android.content.Context;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.ShangYou.CaiGouMVP.bean.PlanOrderList;
import com.silianchuangye.sumao.success.adapter.SearchSeePlanAdapter;
import com.silianchuangye.sumao.success.utils.LogUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SearchSeePlan extends AppCompatActivity implements View.OnClickListener{
    String title;
    private ImageView img_back;
    private TextView tv_title;
    private ListView lv;
    private List<SearchSeePlanInfo> list;
    private SearchSeePlanAdapter adapter;
    private PlanOrderList planOrderList;
    private TextView tv_seeplan_right_date_old;
    private TextView tv_seeplan_right_name_old;
    private TextView tv_seeplan_right_cangku_old;
    private TextView tv_seeplan_right_num_old;
    private TextView tv_seeplan_right_peisong_old;
    private TextView tv_seeplan_right_date;
    private TextView tv_seeplan_right_name;
    private TextView tv_seeplan_right_cangku;
   // private TextView tv_seeplan_left_num;
    private TextView tv_seeplan_right_peisong;
    private TextView tv_seeplan_right_state;
    private TextView tv_seeplan_right_dingdannum;
    private Button btn_seeplan_copy;
    private TextView tv_seeplan_left_date;
    private TextView tv_seeplan_left_name;
    private TextView tv_seeplan_left_cangku;
    private TextView tv_seeplan_left_num;
    private TextView tv_seeplan_left_peisong;
    private TextView tv_seeplan_right_num;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_see_plan);
//        title=getIntent().getStringExtra("bianhao");
        tv_seeplan_left_date= (TextView) findViewById(R.id.tv_seeplan_left_date);
        tv_seeplan_left_name= (TextView) findViewById(R.id.tv_seeplan_left_name);
        tv_seeplan_left_cangku= (TextView) findViewById(R.id.tv_seeplan_left_cangku);
        tv_seeplan_left_num= (TextView) findViewById(R.id.tv_seeplan_left_num);
        tv_seeplan_left_peisong= (TextView) findViewById(R.id.tv_seeplan_left_peisong);
        planOrderList =(PlanOrderList) getIntent().getSerializableExtra("PlanOrderList");

//        title = planOrderList.getPlanID();
//        Log.d("title的值",title);

        initDate();
        initView();
    }

    /*
    *
    *  */
    private void initDate() {
        tv_seeplan_right_date_old =(TextView) findViewById(R.id.tv_seeplan_right_date_old);//计划日期
        tv_seeplan_right_date_old.setText(planOrderList.getScheduleDate());
        tv_seeplan_right_name_old =(TextView) findViewById(R.id.tv_seeplan_right_name_old);//产品名称
        tv_seeplan_right_name_old.setText(planOrderList.getProductName());
        tv_seeplan_right_cangku_old =(TextView) findViewById(R.id.tv_seeplan_right_cangku_old);//仓库
        tv_seeplan_right_cangku_old.setText(planOrderList.getWarehouseName());
        tv_seeplan_right_num_old =(TextView) findViewById(R.id.tv_seeplan_right_num_old);//数量
        tv_seeplan_right_num_old.setText(planOrderList.getQuantity());
        tv_seeplan_right_peisong_old =(TextView) findViewById(R.id.tv_seeplan_right_peisong_old);//配送方式
        tv_seeplan_right_peisong_old.setText(planOrderList.getLogisticsName());

        tv_seeplan_right_date =(TextView) findViewById(R.id.tv_seeplan_right_date);//改变计划日期
        if ( planOrderList.getScheduleDateChange()!=null){
            tv_seeplan_right_date.setText(planOrderList.getScheduleDateChange());
//            tv_seeplan_right_date.setTextColor(Color.RED);
//            tv_seeplan_left_date.setTextColor(Color.RED);
        }else {
            tv_seeplan_right_date.setText(planOrderList.getScheduleDate());
        }
        tv_seeplan_right_name =(TextView) findViewById(R.id.tv_seeplan_right_name);//改变产品名称
        if ( planOrderList.getProductNameChange()!=null){
            tv_seeplan_right_name.setText(planOrderList.getProductNameChange());
//            tv_seeplan_right_name.setTextColor(Color.RED);
//            tv_seeplan_left_name.setTextColor(Color.RED);
        }else {
            tv_seeplan_right_name.setText(planOrderList.getProductName());
        }
        tv_seeplan_right_cangku =(TextView) findViewById(R.id.tv_seeplan_right_cangku);//改变仓库
        if ( planOrderList.getWarehouseChangeName()!=null){
            tv_seeplan_right_cangku.setText(planOrderList.getWarehouseChangeName());
//            tv_seeplan_right_cangku.setTextColor(Color.RED);
//            tv_seeplan_left_cangku.setTextColor(Color.RED);
        }else {
            tv_seeplan_right_cangku.setText(planOrderList.getWarehouseName());
        }
        tv_seeplan_right_num =(TextView) findViewById(R.id.tv_seeplan_right_num);//改变数量
        if ( planOrderList.getQuantityChange()!=null){
            tv_seeplan_right_num.setText(planOrderList.getQuantityChange());
//            tv_seeplan_right_num.setTextColor(Color.RED);
//            tv_seeplan_left_num.setTextColor(Color.RED);
        }else {
            tv_seeplan_right_num.setText(planOrderList.getQuantity());
        }
        tv_seeplan_right_peisong =(TextView) findViewById(R.id.tv_seeplan_right_peisong);//改变配送方式
        if ( planOrderList.getLogisticsChangeName()!=null){
            tv_seeplan_right_peisong.setText(planOrderList.getLogisticsChangeName());
//            tv_seeplan_right_peisong.setTextColor(Color.RED);
//            tv_seeplan_left_peisong.setTextColor(Color.RED);
        }else {
            tv_seeplan_right_peisong.setText(planOrderList.getLogisticsName());
        }
        tv_seeplan_right_state =(TextView) findViewById(R.id.tv_seeplan_right_state);//状态
        if (null == planOrderList.getDemandScheduleState()){
            tv_seeplan_right_state.setText("已取消");

        }else if( planOrderList.getDemandScheduleState().equals("confirmed")){//Pending 待确定//null 已取消//confirmed 已确定
            tv_seeplan_right_state.setText("已确定");

        }else{
            tv_seeplan_right_state.setText("待确定");

        }


        tv_seeplan_right_dingdannum =(TextView) findViewById(R.id.tv_seeplan_right_dingdannum);//订单号
            tv_seeplan_right_dingdannum.setText(planOrderList.getOrderId());
        btn_seeplan_copy =(Button) findViewById(R.id.btn_seeplan_copy);//订单号
        btn_seeplan_copy.setOnClickListener(this);
        if (!tv_seeplan_right_date_old.getText().toString().equals(tv_seeplan_right_date.getText().toString())){
            tv_seeplan_right_date.setTextColor(Color.RED);
            tv_seeplan_left_date.setTextColor(Color.RED);
        }
        if (!tv_seeplan_right_name_old.getText().toString().equals(tv_seeplan_right_name.getText().toString())){
            tv_seeplan_right_name.setTextColor(Color.RED);
            tv_seeplan_left_name.setTextColor(Color.RED);
        }
        if (!tv_seeplan_right_cangku_old.getText().toString().equals(tv_seeplan_right_cangku.getText().toString())){
            tv_seeplan_right_cangku.setTextColor(Color.RED);
            tv_seeplan_left_cangku.setTextColor(Color.RED);
        }
        if (!tv_seeplan_right_num_old.getText().toString().equals(tv_seeplan_right_num.getText().toString())){
            tv_seeplan_right_num.setTextColor(Color.RED);
            tv_seeplan_left_num.setTextColor(Color.RED);
        }
        if (!tv_seeplan_right_peisong_old.getText().toString().equals(tv_seeplan_right_peisong.getText().toString())){
            tv_seeplan_right_peisong.setTextColor(Color.RED);
            tv_seeplan_left_peisong.setTextColor(Color.RED);
        }
    }

    private void initView() {
        img_back= (ImageView) findViewById(R.id.img_search_see_plan_back);
        tv_title= (TextView) findViewById(R.id.tv_search_see_plan_title);
        //tv_title.setText("计划编号 : "+title);
        tv_title.setText("查看原计划");
        img_back.setOnClickListener(this);
        img_back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_search_see_plan_back:
                finish();
                break;
            case R.id.btn_seeplan_copy:
                ClipboardManager copy = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                copy.setText(tv_seeplan_right_dingdannum.getText().toString());
                Toast.makeText(this,"订单号已复制",Toast.LENGTH_SHORT).show();
                break;
        }
    }
}

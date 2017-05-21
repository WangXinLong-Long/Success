package com.silianchuangye.sumao.success.fragments.myPlasticTrade.demandPlanning;

import android.content.ClipboardManager;
import android.content.Context;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.silianchuangye.sumao.success.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;
import java.util.Map;

public class SearchplanActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView tv_search_see_plan_title;
    private ImageView img_search_see_plan_back;
    private TextView tv_seeplan_right_date_old;//计划日期
    private TextView tv_seeplan_right_name_old;//产品名称
    private TextView tv_seeplan_right_cangku_old;//仓库
    private TextView tv_seeplan_right_num_old;//数量
    private TextView tv_seeplan_right_peisong_old;//配送方式

    private TextView tv_seeplan_right_date;//计划日期新
    private TextView tv_seeplan_right_name;//产品名称
    private TextView tv_seeplan_right_cangku;//仓库新
    private TextView tv_seeplan_right_num;//数量新
    private TextView tv_seeplan_right_peisong;//配送方式新
    private TextView tv_seeplan_right_state;//状态

    private TextView tv_seeplan_right_dingdannum;//订单号
    private Button btn_seeplan_copy;

    private String result;
    private String riqi;
    private String name;
    private String cangku;
    private String number;
    private String way;
    private String nameNew;
    private String numberNew;
    private String cangkuNew;
    private String wayNew;
    private String riqinew;
    private String orderId;
    private String state;
   private List<Map<String,Object>> resulr_list;
    private TextView tv_seeplan_left_date;
    private TextView tv_seeplan_left_name;
    private TextView tv_seeplan_left_cangku;
    private TextView tv_seeplan_left_num;
    private TextView tv_seeplan_left_peisong;
   // private TextView tv_seeplan_left_state;
   // img_search_see_plan_back

    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_see_plan);
        tv_seeplan_left_date= (TextView) findViewById(R.id.tv_seeplan_left_date);
        tv_seeplan_left_name= (TextView) findViewById(R.id.tv_seeplan_left_name);
        tv_seeplan_left_cangku= (TextView) findViewById(R.id.tv_seeplan_left_cangku);
        tv_seeplan_left_num= (TextView) findViewById(R.id.tv_seeplan_left_num);
        tv_seeplan_left_peisong= (TextView) findViewById(R.id.tv_seeplan_left_peisong);
        Bundle bundle=getIntent().getExtras();
     //   resulr_list=(List<Map<String,Object>>)bundle.getString("PlanOrderList");
       // Log.d("resultd的值",result);
        riqi=bundle.getString("riqi");
        name=bundle.getString("name");
        cangku=bundle.getString("cangku");
        number=bundle.getString("number");
        way=bundle.getString("logisticsName");
        riqinew=bundle.getString("riqinew");
        nameNew=bundle.getString("nameNew");
        cangkuNew=bundle.getString("cangkuNew");
        wayNew=bundle.getString("wayNew");
        numberNew=bundle.getString("numberNew");
        state=bundle.getString("state");
        orderId=bundle.getString("orderId");
        tv_seeplan_right_date_old= (TextView) findViewById(R.id.tv_seeplan_right_date_old);
        tv_seeplan_right_date_old.setText(riqi);
        tv_seeplan_right_name_old= (TextView) findViewById(R.id.tv_seeplan_right_name_old);
        tv_seeplan_right_name_old.setText(name);
        tv_seeplan_right_cangku_old= (TextView) findViewById(R.id.tv_seeplan_right_cangku_old);
        tv_seeplan_right_cangku_old.setText(cangku);
        tv_seeplan_right_num_old= (TextView) findViewById(R.id.tv_seeplan_right_num_old);
        tv_seeplan_right_num_old.setText(number);
        tv_seeplan_right_peisong_old= (TextView) findViewById(R.id.tv_seeplan_right_peisong_old);
        tv_seeplan_right_peisong_old.setText(way);
        tv_search_see_plan_title= (TextView) findViewById(R.id.tv_search_see_plan_title);
        tv_seeplan_right_date= (TextView) findViewById(R.id.tv_seeplan_right_date);
        if (!riqi.equals(riqinew)) {

            tv_seeplan_right_date.setTextColor(Color.RED);
            tv_seeplan_left_date.setTextColor(Color.RED);
        }
        tv_seeplan_right_date.setText(riqinew);
        tv_seeplan_right_name= (TextView) findViewById(R.id.tv_seeplan_right_name);
        if (!name.equals(nameNew)){
            tv_seeplan_left_name.setTextColor(Color.RED);
            tv_seeplan_right_name.setTextColor(Color.RED);
        }
        tv_seeplan_right_name.setText(nameNew);
        tv_seeplan_right_cangku= (TextView) findViewById(R.id.tv_seeplan_right_cangku);
        tv_seeplan_right_cangku.setText(cangkuNew);
        if (!cangku.equals(cangkuNew)){
            tv_seeplan_right_cangku.setTextColor(Color.RED);
            tv_seeplan_left_cangku.setTextColor(Color.RED);
        }
        tv_seeplan_right_num= (TextView) findViewById(R.id.tv_seeplan_right_num);
        tv_seeplan_right_num.setText(numberNew);
        if (!number.equals(numberNew)){
            tv_seeplan_right_num.setTextColor(Color.RED);
            tv_seeplan_left_num.setTextColor(Color.RED);
        }
        tv_seeplan_right_peisong= (TextView) findViewById(R.id.tv_seeplan_right_peisong);
        tv_seeplan_right_peisong.setText(wayNew);
        if (!way.equals(wayNew)){
            tv_seeplan_right_peisong.setTextColor(Color.RED);
            tv_seeplan_left_peisong.setTextColor(Color.RED);
        }
        tv_seeplan_right_state= (TextView) findViewById(R.id.tv_seeplan_right_state);
        if (state.equals("confirmed")){
            tv_seeplan_right_state.setText("已确定");
        }else if (state.equals("Pending")){
            tv_seeplan_right_state.setText("待确定");
        }else if (state.equals("canceled")){
            tv_seeplan_right_state.setText("已取消");
        }
       // tv_seeplan_right_state.setText(state);
        tv_search_see_plan_title.setText("查看原计划");
        img_search_see_plan_back= (ImageView) findViewById(R.id.img_search_see_plan_back);
        img_search_see_plan_back.setOnClickListener(this);
        btn_seeplan_copy= (Button) findViewById(R.id.btn_seeplan_copy);
        tv_seeplan_right_dingdannum= (TextView) findViewById(R.id.tv_seeplan_right_dingdannum);
        tv_seeplan_right_dingdannum.setText(orderId);


    }

    @Override
    protected void onStart() {
        super.onStart();
       // getinfo();
//    }}
    }

//    public void getinfo(){
//
//            Log.d("传过来的result的值",resulr_list.toString());
//            //JSONObject jsonObject=new JSONObject(result);
//            if (result.contains("orderId")){
//                Log.d("订单的id",resulr_list.get(0).get("orderId").toString());
//                String orderId=resulr_list.get(0).get("orderId").toString();
//                tv_seeplan_right_dingdannum.setText(orderId);
//            }
//            tv_seeplan_right_date_old.setText(resulr_list.get(0).get("scheduleDate").toString());
//            Log.d("日期",resulr_list.get(0).get("scheduleDate").toString());
//            tv_seeplan_right_name_old.setText(resulr_list.get(0).get("productName").toString());
//            tv_seeplan_right_cangku_old.setText(resulr_list.get(0).get("warehouseName").toString());
//            tv_seeplan_right_num_old.setText(resulr_list.get(0).get("quantity").toString());
//            tv_seeplan_right_peisong_old.setText(resulr_list.get(0).get("logisticsName").toString());
//            if (result.contains("scheduleDateChange")){
//                tv_seeplan_right_date.setText(resulr_list.get(0).get("scheduleDateChange").toString());
//                tv_seeplan_right_date.setTextColor(Color.RED);
//                tv_seeplan_left_date.setTextColor(Color.RED);
//            }else{
//                tv_seeplan_right_date.setText(resulr_list.get(0).get("scheduleDate").toString());
//            }
//            if (result.contains("productNameChange")){
//                tv_seeplan_right_name.setText(resulr_list.get(0).get("productNameChange").toString());
//                tv_seeplan_right_name.setTextColor(Color.RED);
//                tv_seeplan_left_name.setTextColor(Color.RED);
//            }else{
//                tv_seeplan_right_name.setText(resulr_list.get(0).get("productName").toString());
//            }
//            if (result.contains("warehouseChangeName")){
//                tv_seeplan_right_cangku.setText(resulr_list.get(0).get("warehouseChangeName").toString());
//                tv_seeplan_right_cangku.setTextColor(Color.RED);
//                tv_seeplan_left_cangku.setTextColor(Color.RED);
//            }else{
//                tv_seeplan_right_cangku.setText(resulr_list.get(0).get("warehouseName").toString());
//            }
//            if (result.contains("quantityChange")){
//                tv_seeplan_right_num.setText(resulr_list.get(0).get("quantityChange").toString());
//                tv_seeplan_right_num.setTextColor(Color.RED);
//                tv_seeplan_left_num.setTextColor(Color.RED);
//            }else{
//                tv_seeplan_right_num.setText(resulr_list.get(0).get("quantity").toString());
//            }
//            if (result.contains("logisticsChangeName")){
//                tv_seeplan_right_peisong.setText(resulr_list.get(0).get("logisticsChangeName").toString());
//                tv_seeplan_right_peisong.setTextColor(Color.RED);
//                tv_seeplan_left_peisong.setTextColor(Color.RED);
//            }else{
//                tv_seeplan_right_peisong.setText(resulr_list.get(0).get("logisticsName").toString());
//            }
//            if (resulr_list.contains("demandScheduleState")){
//               ///String state=jsonObject.getString("demandScheduleState").toString();
//                String state=resulr_list.get(0).get("demandScheduleState").toString();
//
//                if (state.equals("confirmed")){
//                    //已经确认
//                    tv_seeplan_right_state.setText("已确认");
//                }else if (state.equals("Pending")){
//                    //待确认
//                    tv_seeplan_right_state.setText("待确认");
//                }else if (state.equals("canceled")){
//                    //已经取消
//                    tv_seeplan_right_state.setText("已确认");
//                }
//            }
//
//    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_search_see_plan_back:
                SearchplanActivity.this.finish();
                break;
            case R.id.btn_seeplan_copy:
                ClipboardManager copy = (ClipboardManager) SearchplanActivity.this
                        .getSystemService(Context.CLIPBOARD_SERVICE);
                copy.setText(tv_seeplan_right_dingdannum.getText().toString());
                Toast.makeText(SearchplanActivity.this,"复制成功",Toast.LENGTH_SHORT).show();
                break;
        }
    }
}

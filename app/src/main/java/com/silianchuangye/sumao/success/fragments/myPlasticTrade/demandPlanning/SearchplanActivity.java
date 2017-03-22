package com.silianchuangye.sumao.success.fragments.myPlasticTrade.demandPlanning;

import android.content.ClipboardManager;
import android.content.Context;
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

    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_see_plan);
        Bundle bundle=getIntent().getExtras();
        result=bundle.getString("PlanOrderList");
        Log.d("resultd的值",result);
        tv_seeplan_right_date_old= (TextView) findViewById(R.id.tv_seeplan_right_date_old);
        tv_seeplan_right_name_old= (TextView) findViewById(R.id.tv_seeplan_right_name_old);
        tv_seeplan_right_cangku_old= (TextView) findViewById(R.id.tv_seeplan_right_cangku_old);
        tv_seeplan_right_num_old= (TextView) findViewById(R.id.tv_seeplan_right_num_old);
        tv_seeplan_right_peisong_old= (TextView) findViewById(R.id.tv_seeplan_right_peisong_old);
        tv_search_see_plan_title= (TextView) findViewById(R.id.tv_search_see_plan_title);
        tv_seeplan_right_date= (TextView) findViewById(R.id.tv_seeplan_right_date);
        tv_seeplan_right_name= (TextView) findViewById(R.id.tv_seeplan_right_name);
        tv_seeplan_right_cangku= (TextView) findViewById(R.id.tv_seeplan_right_cangku);
        tv_seeplan_right_num= (TextView) findViewById(R.id.tv_seeplan_right_num);
        tv_seeplan_right_peisong= (TextView) findViewById(R.id.tv_seeplan_right_peisong);
        tv_seeplan_right_state= (TextView) findViewById(R.id.tv_seeplan_right_state);
        tv_search_see_plan_title.setText("查看原计划");
        img_search_see_plan_back= (ImageView) findViewById(R.id.img_search_see_plan_back);
        btn_seeplan_copy= (Button) findViewById(R.id.btn_seeplan_copy);
        tv_seeplan_right_dingdannum= (TextView) findViewById(R.id.tv_seeplan_right_dingdannum);
        try {
            JSONObject jsonObject=new JSONObject(result);

            if (result.contains("orderId")){
                Log.d("订单的id",jsonObject.getString("orderId"));
                tv_seeplan_right_dingdannum.setText(jsonObject.getString("orderId"));
            }

            tv_seeplan_right_date_old.setText(jsonObject.getString("scheduleDate"));
            Log.d("日期",jsonObject.getString("scheduleDate"));
            tv_seeplan_right_name_old.setText(jsonObject.getString("productName"));
            tv_seeplan_right_cangku_old.setText(jsonObject.getString("warehouseName"));
            tv_seeplan_right_num_old.setText(jsonObject.getString("quantity"));
            tv_seeplan_right_peisong_old.setText(jsonObject.getString("logisticsName"));
            if (result.contains("scheduleDateChange")){
                tv_seeplan_right_date.setText(jsonObject.getString("scheduleDateChange"));
            }else{
                tv_seeplan_right_date.setText(jsonObject.getString("scheduleDate"));
            }
            if (result.contains("productNameChange")){
                tv_seeplan_right_name.setText(jsonObject.getString("productNameChange"));
            }else{
                tv_seeplan_right_name.setText(jsonObject.getString("productName"));
            }
            if (result.contains("warehouseChangeName")){
                tv_seeplan_right_cangku.setText(jsonObject.getString("warehouseChangeName"));
            }else{
                tv_seeplan_right_cangku.setText(jsonObject.getString("warehouseName"));
            }
            if (result.contains("quantityChange")){
                tv_seeplan_right_num.setText(jsonObject.getString("quantityChange"));
            }else{
                tv_seeplan_right_num.setText(jsonObject.getString("quantity"));
            }
            if (result.contains("logisticsChangeName")){
                tv_seeplan_right_peisong.setText(jsonObject.getString("logisticsChangeName"));
            }else{
                tv_seeplan_right_peisong.setText(jsonObject.getString("logisticsName"));
            }
            if (result.contains("demandScheduleState")){
                if (jsonObject.getString("demandScheduleState").equals("confirmed")){
                    //已经确认
                    tv_seeplan_right_state.setText("已确认");
                }else if (jsonObject.getString("demandScheduleState").equals("Pending")){
                    //待确认
                    tv_seeplan_right_state.setText("待确认");
                }else if (jsonObject.getString("demandScheduleState").equals("canceled")){
                    //已经取消
                    tv_seeplan_right_state.setText("已确认");
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

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

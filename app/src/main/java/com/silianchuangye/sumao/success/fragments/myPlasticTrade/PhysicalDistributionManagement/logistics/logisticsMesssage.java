package com.silianchuangye.sumao.success.fragments.myPlasticTrade.PhysicalDistributionManagement.logistics;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.silianchuangye.sumao.success.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class logisticsMesssage extends AppCompatActivity {
    private TextView tv_title_logistics,tv_tishi,tv_date;
    private EditText edt;
    private Button btn;
    String str,startTime,buy;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logistics_messsage);
        str= getIntent().getStringExtra("title");
        startTime=getIntent().getStringExtra("time");
        buy=getIntent().getStringExtra("buy");
        tv_title_logistics= (TextView) findViewById(R.id.tv_title_logistics);
        tv_title_logistics.setText(str);
        tv_tishi= (TextView) findViewById(R.id.tv_tishi);
        edt= (EditText) findViewById(R.id.edt_message);
        btn= (Button) findViewById(R.id.btn_logicits_save);
        tv_date= (TextView) findViewById(R.id.tv_date_message);
        setOnChange();
        Log.e("TAG","tv--"+tv_title_logistics.getText().toString());
        //
        if(tv_title_logistics.getText().toString().equals("提货时间")||tv_title_logistics.getText().toString().equals("期望提货时间")){
            tv_date.setVisibility(View.VISIBLE);
            edt.setVisibility(View.GONE);
        }else{
            tv_date.setVisibility(View.GONE);
            edt.setVisibility(View.VISIBLE);
        }
        tv_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDate(tv_date);
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(tv_title_logistics.getText().toString().equals("提货时间")||tv_title_logistics.getText().toString().equals("期望提货时间")){
                    if(!tv_date.getText().toString().equals("")){
                        String nowTime=tv_date.getText().toString();
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        long nowTime1= 0;
                        long startTime1=0;
                        try {
                            nowTime1 = dateFormat.parse(nowTime).getTime();
                            startTime1=dateFormat.parse(startTime).getTime();
                            if(nowTime1-startTime1<0){
                                Toast.makeText(logisticsMesssage.this, "提货时间应该至少大于交货的开始时间", Toast.LENGTH_SHORT).show();
                            }else{
                                show();
                            }
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }else{
                        tv_tishi.setText("填写内容不能为空");
                        tv_tishi.setVisibility(View.VISIBLE);
                    }
                    if(edt.getText().toString()==null){
                        edt.setText("");
                    }
                }else {
                    Log.e("TAG", "edt.getText().toString()====" + edt.getText().toString());
                    if (edt.getText().toString().equals("")) {
                        if (tv_title_logistics.getText().toString().equals("备注")
                                || tv_title_logistics.getText().toString().equals("托运联系人方式")
                                || tv_title_logistics.getText().toString().equals("托运联系人")
                                || tv_title_logistics.getText().toString().equals("收货公司")
                                ) {
                            show();
                        } else {
                            tv_tishi.setText("填写内容不能为空");
                            tv_tishi.setVisibility(View.VISIBLE);
                        }
                    } else {
                        if(tv_title_logistics.getText().toString().equals("提货人身份证号")){
                            if(edt.getText().length()<18){
                                tv_tishi.setText("身份证号不合理");
                                tv_tishi.setVisibility(View.VISIBLE);
                                Toast.makeText(logisticsMesssage.this,"身份证号不合理",Toast.LENGTH_SHORT).show();
                            }else{
                                show();
                            }
                        }else{
                            show();
                        }

                    }
                }
            }
        });
    }
    private void show(){
        Intent intent=new Intent();
        intent.putExtra("message",edt.getText().toString());
        if(buy.equals("1")) {
            if (str.equals("收货联系人")) {
                setResult(0, intent);
            } else if (str.equals("联系电话")) {
                setResult(1, intent);
            } else if (str.equals("期望提货时间")) {
                intent.putExtra("message", tv_date.getText().toString());
                setResult(2, intent);
            } else if (str.equals("收货公司")) {
                setResult(3, intent);
            } else if (str.equals("托运联系人")) {
                setResult(4, intent);
            } else if (str.equals("托运联系人方式")) {
                setResult(5, intent);
            } else if (str.equals("备注")) {
                setResult(6, intent);
            }
        } else {
             if (str.equals("提货车号")) {
                setResult(10, intent);
            } else if (str.equals("提货人")) {
                setResult(11, intent);
            } else if (str.equals("联系方式")) {
                setResult(12, intent);
            } else if (str.equals("提货人身份证号")) {
                setResult(13, intent);
            } else if (str.equals("备注")) {
                setResult(14, intent);
            } else if (str.equals("提货时间")) {
                intent.putExtra("message", tv_date.getText().toString());
                setResult(15, intent);
            }
        }
        finish();
    }
    private void showDate(final TextView Tv){
        Calendar calend1 = Calendar.getInstance();
        calend1.setTimeInMillis(System.currentTimeMillis());
        int year = calend1.get(Calendar.YEAR);
        int month = calend1.get(Calendar.MONTH) + 1;
        int day = calend1.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog dialog3 = new DatePickerDialog(
                logisticsMesssage.this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view,
                                          int year, int monthOfYear,
                                          int dayOfMonth) {
                        Tv.setText(year + "-" + (monthOfYear + 1)
                                + "-" + dayOfMonth);

                    }
                }, year, month, day);
        dialog3.show();
    }

    private void setOnChange(){
        edt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(edt.getText().length()!=0){
                    tv_tishi.setVisibility(View.INVISIBLE);
                }
            }
        });
        tv_date.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(tv_date.getText().length()!=0){
                    tv_tishi.setVisibility(View.INVISIBLE);
                }
            }
        });
    }
}

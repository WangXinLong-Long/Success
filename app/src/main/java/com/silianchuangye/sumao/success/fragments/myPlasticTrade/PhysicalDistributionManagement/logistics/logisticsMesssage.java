package com.silianchuangye.sumao.success.fragments.myPlasticTrade.PhysicalDistributionManagement.logistics;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.silianchuangye.sumao.success.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class logisticsMesssage extends AppCompatActivity implements View.OnClickListener{
    private TextView tv_title_logistics,tv_tishi,tv_date,tv_date_start,tv_date_end;
    private EditText edt;
    private Button btn;
    private ImageView img_logistics_title_bar_back;
    LinearLayout linear_date;
    String str,startTime,buy;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logistics_messsage);
        str= getIntent().getStringExtra("title");
        startTime=getIntent().getStringExtra("time");
        buy=getIntent().getStringExtra("buy");
        img_logistics_title_bar_back= (ImageView) findViewById(R.id.img_logistics_title_bar_back);
        linear_date= (LinearLayout) findViewById(R.id.linear_date);
        tv_date_start= (TextView) findViewById(R.id.tv_date_start);
        tv_date_end= (TextView) findViewById(R.id.tv_date_end);
        tv_title_logistics= (TextView) findViewById(R.id.tv_title_logistics);
        tv_title_logistics.setText(str);
        tv_tishi= (TextView) findViewById(R.id.tv_tishi);
        edt= (EditText) findViewById(R.id.edt_message);
        btn= (Button) findViewById(R.id.btn_logicits_save);
        tv_date= (TextView) findViewById(R.id.tv_date_message);
        setOnChange();
        Log.e("TAG","tv--"+tv_title_logistics.getText().toString());
        //
        if(tv_title_logistics.getText().toString().equals("提货时间")){
            tv_date.setVisibility(View.VISIBLE);
            edt.setVisibility(View.GONE);
            linear_date.setVisibility(View.GONE);
        }else if(tv_title_logistics.getText().toString().equals("期望提货时间")){
            linear_date.setVisibility(View.VISIBLE);
            tv_date.setVisibility(View.GONE);
            edt.setVisibility(View.GONE);
        }else{
            tv_date.setVisibility(View.GONE);
            edt.setVisibility(View.VISIBLE);
            linear_date.setVisibility(View.GONE);
        }
        tv_date.setOnClickListener(this);
        btn.setOnClickListener(this);
        tv_date_start.setOnClickListener(this);
        tv_date_end.setOnClickListener(this);

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
                intent.putExtra("message1", tv_date_start.getText().toString());
                intent.putExtra("message2",tv_date_end.getText().toString());
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
                 Log.e("TAG","提货车");
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
        final Calendar calend1 = Calendar.getInstance();
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
        tv_date_start.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(tv_date_start.getText().length()!=0){
                    tv_tishi.setVisibility(View.INVISIBLE);
                }
            }
        });
        tv_date_end.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(tv_date_end.getText().length()!=0){
                    tv_tishi.setVisibility(View.INVISIBLE);
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_logistics_title_bar_back:
                finish();
                break;
            case R.id.tv_date_message:
                showDate(tv_date);
                break;
            case R.id.tv_date_start:
                showDate(tv_date_start);
                break;
            case R.id.tv_date_end:
                showDate(tv_date_end);
                break;
            case R.id.btn_logicits_save:
                if(tv_title_logistics.getText().toString().equals("提货时间")){
                    if(!tv_date.getText().toString().equals("")){
                        String nowTime=tv_date.getText().toString();
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        long nowTime1= 0;
                        long startTime1=0;
                        try {
                            nowTime1 = dateFormat.parse(nowTime).getTime();
                            startTime1=dateFormat.parse(startTime).getTime();
                            if(nowTime1-startTime1<0){
                                tv_tishi.setText("提货时间应该至少大于交货的开始时间");
                                tv_tishi.setVisibility(View.VISIBLE);
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
                }else if(tv_title_logistics.getText().toString().equals("期望提货时间")){
                    if(!tv_date_start.getText().toString().equals("")&&!tv_date_end.getText().toString().equals("")){
                        String nowTime=tv_date_start.getText().toString();
                        String endTime=tv_date_end.getText().toString();
                        Log.e("TAG","endTime-----"+endTime);
                        Log.e("TAG","nowTime=----"+nowTime);
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        long nowTime1= 0;
                        long startTime1=0;
                        long endTime1=0;
                        try {
                            nowTime1 = dateFormat.parse(nowTime).getTime();
                            startTime1=dateFormat.parse(startTime).getTime();
                            endTime1=dateFormat.parse(endTime).getTime();
                            Log.e("TAG","noe==="+(nowTime1-endTime1));
                            if(nowTime1-startTime1<0){
                                tv_tishi.setText("期望提货开始时间应该至少大于交货的开始时间");
                                tv_tishi.setVisibility(View.VISIBLE);
                                Toast.makeText(logisticsMesssage.this, "提货时间应该至少大于交货的开始时间", Toast.LENGTH_SHORT).show();
                            }else if(endTime1-startTime1<0){
                                Log.e("TAG","进步");
                                tv_tishi.setText("期望提货结束时间应该至少大于交货的开始时间");
                                tv_tishi.setVisibility(View.VISIBLE);
                            }else{
                                show();
                            }
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }else{
                        Log.e("TAG","startTime"+tv_date_start.getText().toString());
                        Log.e("TAG","endTime-"+tv_date_end.getText().toString());
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
                        if (tv_title_logistics.getText().toString().equals("提货人身份证号")) {
                            //汽车牌号正则表达式
                            String regEx = "^[1-9]\\d{7}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}$|^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}([0-9]|X)$";
                            Pattern pattern = Pattern.compile(regEx);
                            Matcher matcher = pattern.matcher(edt.getText().toString());
                            boolean b = matcher.matches();
                            Log.e("TAG","b="+b);
                            if(!b){
                                tv_tishi.setText("身份证号不合理");
                                tv_tishi.setVisibility(View.VISIBLE);
                            }else{
                                show();
                            }
                        }else if(tv_title_logistics.getText().toString().equals("提货车号")){
                            //汽车牌号正则表达式
                            String regEx = "^[\\u4e00-\\u9fa5]{1}[A-Z]{1}[A-Z_0-9]{5}$";
                            Pattern pattern = Pattern.compile(regEx);
                            Matcher matcher = pattern.matcher(edt.getText().toString());
                            boolean b = matcher.matches();
                            Log.e("TAG","b="+b);
                            if(!b){
                                tv_tishi.setText("车牌号不合理");
                                tv_tishi.setVisibility(View.VISIBLE);
                            }else{
                                show();
                            }
                        }else if(tv_title_logistics.getText().toString().equals("联系方式")){
                            //汽车牌号正则表达式
                            String regEx = "[1][358]\\d{9}";
                            Pattern pattern = Pattern.compile(regEx);
                            Matcher matcher = pattern.matcher(edt.getText().toString());
                            boolean b = matcher.matches();
                            Log.e("TAG","b="+b);
                            if(!b){
                                tv_tishi.setText("手机号不合理");
                                tv_tishi.setVisibility(View.VISIBLE);
                            }else{
                                show();
                            }
                        }else {
                            show();
                        }
                    }
                }
//
                break;
        }
    }
}

package com.silianchuangye.sumao.success.fragments.myPlasticTrade.OrderManagement.SpotOrder.SpotOrderMVP.view.informationView;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.OrderManagement.SpotOrder.SpotOrderMVP.MarginRecordItemBean;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.OrderManagement.SpotOrder.SpotOrderMVP.bean.PaymentInformationBean;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

public class PaymentInformationActivity extends AppCompatActivity implements View.OnClickListener {

    private ListView margin_record_list_view;
    private TextView tv_child_title_bar_title;
    private ImageView iv_child_title_bar_back;
    private TextView payment_information_payer;
    private TextView payment_information_paying_bank;
    private TextView payment_information_payment_account;
    private TextView payment_information_payer2;
    private TextView payment_information_paying_bank2;
    private TextView payment_information_payment_account2;
    private PaymentInformationBean paymentInformationBean;
    private RelativeLayout counter_fee_rl;
    private TextView capital;
    private TextView frozen_date_number;
    private TextView frozen_time_number;
    private TextView counter_fee_has_paded_tv;
    private TextView frozen_tv;
    private TextView counter_fee_has_paded_number;
    //private TextView counter_fee_date_tv;
    private TextView counter_fee_time_number;
    private TextView counter_fee_date_number;
    private MarginRecordListViewAdapter marginRecordListViewAdapter;
    private List<Map<String,Object>> list;
    private List<Map<String,Object>> list_freeZe;
    private String payTail_type="";
    private String payTail_amount;
    private String payTail_time="";
    private String unFreeze_Type;
    private String unFreeze_amount;
    private String unFreeze_time="";
    private String Freeze_amount;
    private String shoukuanren,shoukuanbank,shoukuanzhanghao;
    private String fukuanren="",fukuanbank,fukuanzhanghao;
    private float money=0  ;
    private String lasttime;
    private String force_type;
    private String force_time="";
    private String force_amount;
    private List<Map<String,Object>> forcelist;
    private float forcemoney=0;
    private String forcelast;
    private String serviceFee="";
    private String State="";
    private RelativeLayout layout_bank;
    private RelativeLayout layout_huokuan;

    private TextView frozen_date_tv;
    private TextView frozen_time_tv;
    private TextView counter_fee_time_tv;
    private TextView counter_fee_date_tv;
    private RelativeLayout layout_baozhengjin;
    private String type="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_information);
        layout_baozhengjin= (RelativeLayout) findViewById(R.id.layout_baozhengjin);
        frozen_date_tv= (TextView) findViewById(R.id.frozen_date_tv);
        frozen_time_tv= (TextView) findViewById(R.id.frozen_time_tv);
        counter_fee_time_tv= (TextView) findViewById(R.id.counter_fee_time_tv);
        counter_fee_date_tv= (TextView) findViewById(R.id.counter_fee_date_tv);
        layout_bank= (RelativeLayout) findViewById(R.id.layout_bank);
        layout_huokuan=(RelativeLayout) findViewById(R.id.layout_huokuan);
        String result = getIntent().getStringExtra("result");
        Log.d("result的值",result);
        System.out.println("很多的result"+result);
        list = new ArrayList<>();
        list_freeZe = new ArrayList<>();
        forcelist = new ArrayList<>();
        if (result.contains("AA")) {

            try {
                JSONObject jsonObject = new JSONObject(result);
                if (result.contains("cl_fukf")) {
                    fukuanren = jsonObject.getString("cl_fukf");
                    fukuanbank = jsonObject.getString("cl_fukyh");
                    fukuanzhanghao = jsonObject.getString("cl_fukzh");
                    shoukuanren = jsonObject.getString("cl_shoukf");
                    shoukuanbank = jsonObject.getString("cl_shoukyh");
                    shoukuanzhanghao = jsonObject.getString("cl_shoukzh");
                }
                State = jsonObject.getString("orderState");
                Log.d("state的值", State);//SUBMITTED  待支付   QUOTED已支付    CHANGED已变更
                serviceFee = jsonObject.getString("serviceFee");
                String info = jsonObject.getString("AA");
                Log.d("aa的值", info + "");
                JSONArray json_AA = new JSONArray(info);
                for (int i = 0; i < json_AA.length(); i++) {
                    JSONObject object_paymentGroup = json_AA.getJSONObject(i);
                    //JSONObject json_paymentGroup=new JSONObject(paymentGroup);
                    String paymentGroup = object_paymentGroup.getString("paymentGroup");
                    Log.d("paymentGroup的值", paymentGroup);
                    JSONObject object_info = new JSONObject(paymentGroup);
                    String amountDetails = object_info.getString("amountDetails");
                    Log.d("amountDetails的值", amountDetails + "");
                    JSONArray array_amountDetails = new JSONArray(amountDetails);
                    for (int j = 0; j < array_amountDetails.length(); j++) {
                        JSONObject object_amountDetails = array_amountDetails.getJSONObject(j);
                        type = object_amountDetails.getString("type");
                        Log.d("type的值", type);
                        String amount = object_amountDetails.getString("amount");
                        Log.d("amont的值", amount);
                        String finishTime = object_amountDetails.getString("finishTime");
                        JSONObject object_finishTime = new JSONObject(finishTime);
                        String time = object_finishTime.getString("time");
                        Log.d("time的值", time);
                        if (type.equals("freeze")) {
                            Map<String, Object> map = new Hashtable<>();
                            map.put("type", type);
                            map.put("amount", amount);
                            map.put("time", time);
                            list.add(map);
                            //Freeze_amount=amount+amount;
                            Log.d("list的值", list.toString());
                        } else if (type.equals("payTail")) {
                            payTail_type = type;
                            payTail_amount = amount;
                            payTail_time = time;
                            Log.d("货款记录", payTail_amount + payTail_time);
                        } else if (type.equals("unfreeze")) {//解冻
                            unFreeze_Type = type;
                            unFreeze_amount = amount;
                            unFreeze_time = time;

                            Log.d("保证金解冻", unFreeze_amount + unFreeze_time);
                            Map<String, Object> map = new Hashtable<>();
                            map.put("type", unFreeze_Type);
                            map.put("amount", unFreeze_amount);
                            map.put("time", unFreeze_time);
                            list_freeZe.add(map);
                            Log.d("金额", unFreeze_amount);
                            Log.d("map的值", list_freeZe.size() + "");

                        } else if (type.equals("force")) {
                            force_amount = amount;
                            Log.d("请破", force_amount);
                            force_time = time;
                            force_type = type;
                            Map<String, Object> map = new Hashtable<>();
                            map.put("type", force_type);
                            map.put("amount", force_amount);
                            map.put("time", force_time);
                            forcelist.add(map);

                        }


                    }

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }else{
            try {
                JSONObject jsonObject=new JSONObject(result);
                serviceFee=jsonObject.getString("serviceFee");
                State=jsonObject.getString("orderState");
               // payTail_type
                String monoy=jsonObject.getString("in");
                JSONArray jsonArray=new JSONArray(monoy);
                String number="";
                for (int i=0;i<jsonArray.length();i++){
                    JSONObject object=jsonArray.getJSONObject(0);
                     number=object.getString("payamount");
                }
                payTail_amount=Float.parseFloat(serviceFee)+Float.parseFloat(number)+"";

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }


//        标题
        tv_child_title_bar_title = ((TextView) findViewById(R.id.tv_child_title_bar_title));
//        返回键
        iv_child_title_bar_back = ((ImageView) findViewById(R.id.iv_child_title_bar_back));
//        保证金记录的ListView
        margin_record_list_view = ((ListView) findViewById(R.id.margin_record_list_view));
        marginRecordListViewAdapter = new MarginRecordListViewAdapter(this);
        margin_record_list_view.setAdapter(marginRecordListViewAdapter);
        tv_child_title_bar_title.setText("付款信息");
        iv_child_title_bar_back.setOnClickListener(this);
        if (result.contains("AA")) {
            Log.d("冻结的list的值", "" + list.toString() + list.size());
            if (list_freeZe.size() > 0) {
                for (int k = 0; k < list_freeZe.size(); k++) {
                    lasttime = list_freeZe.get(list_freeZe.size() - 1).get("time").toString();
                    // Log.d("money的值",money+"");
                    Log.d("aaaaa", list_freeZe.get(k).get("amount").toString());
                    money += Float.parseFloat(list_freeZe.get(k).get("amount").toString());
                    Log.d("money的值", money + "");
                }
                Map<String, Object> map = new Hashtable<>();
                map.put("type", unFreeze_Type);
                map.put("amount", money + "");
                map.put("time", lasttime);
                list.add(map);
            }
            if (forcelist.size() > 0) {
                for (int k = 0; k < forcelist.size(); k++) {
                    forcelast = forcelist.get(forcelist.size() - 1).get("time").toString();
                    // Log.d("money的值",money+"");
                    Log.d("aaaaa", forcelist.get(k).get("amount").toString());
                    forcemoney += Float.parseFloat(forcelist.get(k).get("amount").toString());
                    Log.d("money的值", forcemoney + "");
                }
                Map<String, Object> map = new Hashtable<>();
                map.put("type", force_type);
                map.put("amount", forcemoney + "");
                map.put("time", forcelast);
                list.add(map);

            }
        }


        findView();
        event();
    }

    private void findView() {
        //        付款方
        payment_information_payer = ((TextView) findViewById(R.id.payment_information_payer));
        payment_information_payer.setText(fukuanren);
        //        付款银行
        payment_information_paying_bank = ((TextView) findViewById(R.id.payment_information_paying_bank));
        payment_information_paying_bank.setText(fukuanbank);
        //        付款账号
        payment_information_payment_account = ((TextView) findViewById(R.id.payment_information_payment_account));
        payment_information_payment_account.setText(fukuanzhanghao);
        //        收款方
        payment_information_payer2 = ((TextView) findViewById(R.id.payment_information_payer2));
        payment_information_payer2.setText(shoukuanren);
        //        收款银行
        payment_information_paying_bank2 = ((TextView) findViewById(R.id.payment_information_paying_bank2));
        payment_information_paying_bank2.setText(shoukuanbank);
        //        收款账号
        payment_information_payment_account2 = ((TextView) findViewById(R.id.payment_information_payment_account2));
        payment_information_payment_account2.setText(shoukuanzhanghao);
//        手续费状态
        counter_fee_has_paded_tv = ((TextView) findViewById(R.id.counter_fee_has_paded_tv));
//        货款记录状态
        frozen_tv = ((TextView) findViewById(R.id.frozen_tv));
//        货款记录已支付
        capital = ((TextView) findViewById(R.id.capital));
//        货款记录日期
        frozen_date_number = ((TextView) findViewById(R.id.frozen_date_number));
//        货款记录时间
        frozen_time_number = ((TextView) findViewById(R.id.frozen_time_number));
//
//        手续费
        counter_fee_rl = ((RelativeLayout) findViewById(R.id.counter_fee_rl));


        if (serviceFee.equals("0")) {
            counter_fee_rl.setVisibility(View.GONE);
        } else {
            counter_fee_rl.setVisibility(View.VISIBLE);
        }
////手续费金额
        counter_fee_date_number= (TextView) findViewById(R.id.counter_fee_date_number);
        counter_fee_time_number = ((TextView) findViewById(R.id.counter_fee_time_number));
        counter_fee_has_paded_number = ((TextView) findViewById(R.id.counter_fee_has_paded_number));
        Log.d("状态的值",State);
        if (serviceFee!=""){
            counter_fee_has_paded_number.setText(serviceFee);
            Log.d("状态的值",State);
            if (State.equals("QUOTED")||State.equals("CHANGED")){
                if (!payTail_time.equals("")) {
                    counter_fee_date_number.setText(new SimpleDateFormat("yyyy-MM-dd").
                            format(Double.parseDouble(payTail_time)));
                    counter_fee_time_number.setText(new SimpleDateFormat("HH:mm:ss").
                            format(Double.parseDouble(payTail_time)));
                }else{
                    counter_fee_date_number.setText("");
                    counter_fee_time_number.setText("");
                    counter_fee_date_number.setVisibility(View.GONE);
                    counter_fee_time_number.setVisibility(View.GONE);
                    //把日期时间隐藏
                    counter_fee_time_tv.setVisibility(View.GONE);
                    counter_fee_date_tv.setVisibility(View.GONE);
                }
            }else if(State.equals("SUBMITTED")||State.equals("PENDING_APPROVAL")||State.equals("APPROVED")||State.equals("FAILED_APPROVAL")||State.equals("REMOVED") ){
                counter_fee_date_number.setVisibility(View.GONE);
                counter_fee_time_number.setVisibility(View.GONE);
                //把日期时间隐藏
                counter_fee_time_tv.setVisibility(View.GONE);
                counter_fee_date_tv.setVisibility(View.GONE);
            }else{

            }

        }
//        if (paymentInformationBean.getServiceFee() != null)
//            counter_fee_has_paded_number.setText(paymentInformationBean.getServiceFee());
////手续费日期
//        counter_fee_date_number = ((TextView) findViewById(R.id.counter_fee_date_number));
//        if (paymentInformationBean.getCl_sxfrq() != null)
//            counter_fee_date_number.setText();
////手续费时间

//        if (paymentInformationBean.getCl_sxfsj() != null)
//            counter_fee_time_number.setText(new SimpleDateFormat("HH:mm:ss").
//                    format(Double.parseDouble(paymentInformationBean.getCl_sxfsj())));
//        List<PaymentInformationBean.InBean> in = paymentInformationBean.getIn();
//        List<MarginRecordItemBean> list = new ArrayList<>();
//        double amountAll = 0;
//        double cacheData = 0;
//        double cacheTime = 0;


//        for (PaymentInformationBean.InBean inBean : in) {
//
//            if (inBean.getCl_type() != null) {
//                if (inBean.getCl_type().equals("unfreeze") || inBean.getCl_type().equals("freeze")) {
//                    MarginRecordItemBean marginRecordItemBean = new MarginRecordItemBean();
//                    marginRecordItemBean.setDate(new SimpleDateFormat("yyyy-MM-dd").format(Double.parseDouble(inBean.getCl_yidrq())));
//                    marginRecordItemBean.setTime(new SimpleDateFormat("HH:mm:ss").format(Double.parseDouble(inBean.getCl_yidsj())));
//                    marginRecordItemBean.setMoney(inBean.getAmount());
//                    marginRecordItemBean.setState("已冻结:");
//                    list.add(marginRecordItemBean);
//
//                }
//                if (inBean.getCl_type().equals("unfreeze")) {
//                    amountAll += Double.parseDouble(inBean.getAmount());
//                    cacheData = Double.parseDouble(inBean.getCl_yidrq()) > cacheData ? Double.parseDouble(inBean.getCl_yidrq()) : cacheData;
//                    cacheTime = Double.parseDouble(inBean.getCl_yidsj()) > cacheTime ? Double.parseDouble(inBean.getCl_yidsj()) : cacheTime;
//                }
//                if (payTail_type.equals("payTail")) {
//                    //                    货款记录
//                    capital.setText(payTail_amount);
//                    //frozen_date_number.setText(inBean.getCl_yidrq());
//                    frozen_date_number.setText(new SimpleDateFormat("yyyy-MM-dd").
//                            format(Double.parseDouble(payTail_amount)));
//                    //frozen_time_number.setText(inBean.getCl_yidsj());
//                    frozen_time_number.setText(new SimpleDateFormat("HH:mm:ss").format(Double.parseDouble(payTail_amount)));
//                }
//            }
//
//        }
//        if (cacheData != 0) {
//            MarginRecordItemBean marginRecordItemBean = new MarginRecordItemBean();
//            marginRecordItemBean.setDate(new SimpleDateFormat("yyyy-MM-dd").format(cacheData));
//            marginRecordItemBean.setTime(new SimpleDateFormat("HH:mm:ss").format(cacheTime));
//            marginRecordItemBean.setMoney(amountAll+"");
//            marginRecordItemBean.setState("已解冻:");
//            list.add(marginRecordItemBean);
//        }
//        marginRecordListViewAdapter.addData(list);
////        手续费已支付、货款已支付（否则手续费和货款都为待支付）
//        if (paymentInformationBean.getOrderState() == "QUOTED" || paymentInformationBean.getOrderState() == "CHANGED") {
//            //        手续费状态
//            counter_fee_has_paded_tv.setText("已支付:");
//            //        货款记录状态
//            frozen_tv.setText("已支付:");
//        } else {

//        }

    }

    private void event(){

        Log.d("state的值",State);


            if (State.equals("QUOTED")||State.equals("CHANGED")){
                Log.d("payTail的值",payTail_type);
                if (payTail_type.equals("payTail") && payTail_type!=null && !payTail_type.equals("")) {
                    //                    货款记录
                    BigDecimal b1 = new BigDecimal(payTail_amount);
                    double f2 = b1.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                    //tv_item_cart_buy_num.setText(f2 + "");
                    capital.setText(f2+"");
                    //frozen_date_number.setText(inBean.getCl_yidrq());
                    frozen_date_number.setText(new SimpleDateFormat("yyyy-MM-dd").
                            format(Double.parseDouble(payTail_time)));
                    //frozen_time_number.setText(inBean.getCl_yidsj());
                    frozen_time_number.setText(new SimpleDateFormat("HH:mm:ss").format(Double.parseDouble(payTail_time)));
                    counter_fee_has_paded_tv.setText("已支付:");
                    //        货款记录状态
                    frozen_tv.setText("已支付:");
                    layout_bank.setVisibility(View.VISIBLE);
                }else {
                    Log.d("走这里了","走这里了");
                    layout_huokuan.setVisibility(View.VISIBLE);
                }


            }else if (State.equals("SUBMITTED")||State.equals("PENDING_APPROVAL")||State.equals("APPROVED")||State.equals("FAILED_APPROVAL") ){


                layout_bank.setVisibility(View.GONE);
                capital.setText(getIntent().getStringExtra("price"));
                Log.d("待支付的货款信息",getIntent().getStringExtra("price"));
                //        手续费状态
                counter_fee_has_paded_tv.setText("待支付:");


                //        货款记录状态
                frozen_tv.setText("待支付:");
                frozen_date_tv.setVisibility(View.GONE);
                frozen_time_tv.setVisibility(View.GONE);
                frozen_time_number.setVisibility(View.GONE);
                frozen_date_number.setVisibility(View.GONE);
            }else{
                layout_huokuan.setVisibility(View.GONE);
                layout_bank.setVisibility(View.GONE);
            }

        if (!type.equals("unfreeze")&&!type.equals("force")&&!type.equals("freeze") ||type.equals("")){
            layout_baozhengjin.setVisibility(View.GONE);
        }

//        }else {
//
//            capital.setText(getIntent().getStringExtra("price"));
//            Log.d("待支付的货款信息",getIntent().getStringExtra("price"));
//            //        手续费状态
//            counter_fee_has_paded_tv.setText("待支付:");
//            //        货款记录状态
//            frozen_tv.setText("待支付:");
//            frozen_date_tv.setVisibility(View.GONE);
//            frozen_time_tv.setVisibility(View.GONE);
//
//        }
        if (list.size()>0){
            marginRecordListViewAdapter =new MarginRecordListViewAdapter(PaymentInformationActivity.this);
            margin_record_list_view.setAdapter(marginRecordListViewAdapter);
            marginRecordListViewAdapter.addData(list);
            marginRecordListViewAdapter.notifyDataSetChanged();
        }

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_child_title_bar_back:
                finish();
                break;
        }
    }


}

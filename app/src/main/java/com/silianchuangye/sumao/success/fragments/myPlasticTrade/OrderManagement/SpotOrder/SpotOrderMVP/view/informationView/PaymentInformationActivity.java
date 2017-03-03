package com.silianchuangye.sumao.success.fragments.myPlasticTrade.OrderManagement.SpotOrder.SpotOrderMVP.view.informationView;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.OrderManagement.SpotOrder.SpotOrderMVP.MarginRecordItemBean;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.OrderManagement.SpotOrder.SpotOrderMVP.bean.PaymentInformationBean;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

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
    private TextView counter_fee_date_tv;
    private TextView counter_fee_time_number;
    private TextView counter_fee_date_number;
    private MarginRecordListViewAdapter marginRecordListViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_information);
        String result = getIntent().getStringExtra("result");
        Gson gson = new Gson();
        paymentInformationBean = gson.fromJson(result, PaymentInformationBean.class);

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

        findView();
    }

    private void findView() {
        //        付款方
        payment_information_payer = ((TextView) findViewById(R.id.payment_information_payer));
        payment_information_payer.setText(paymentInformationBean.getCl_fukf());
        //        付款银行
        payment_information_paying_bank = ((TextView) findViewById(R.id.payment_information_paying_bank));
        payment_information_paying_bank.setText(paymentInformationBean.getCl_fukyh());
        //        付款账号
        payment_information_payment_account = ((TextView) findViewById(R.id.payment_information_payment_account));
        payment_information_payment_account.setText(paymentInformationBean.getCl_fukzh());
        //        收款方
        payment_information_payer2 = ((TextView) findViewById(R.id.payment_information_payer2));
        payment_information_payer2.setText(paymentInformationBean.getCl_shoukf());
        //        收款银行
        payment_information_paying_bank2 = ((TextView) findViewById(R.id.payment_information_paying_bank2));
        payment_information_paying_bank2.setText(paymentInformationBean.getCl_shoukyh());
        //        收款账号
        payment_information_payment_account2 = ((TextView) findViewById(R.id.payment_information_payment_account2));
        payment_information_payment_account2.setText(paymentInformationBean.getCl_shoukzh());
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
        if (paymentInformationBean.getServiceFee().equals("0")) {
            counter_fee_rl.setVisibility(View.GONE);
        } else {
            counter_fee_rl.setVisibility(View.VISIBLE);
        }
//手续费金额
        counter_fee_has_paded_number = ((TextView) findViewById(R.id.counter_fee_has_paded_number));
        if (paymentInformationBean.getServiceFee() != null)
            counter_fee_has_paded_number.setText(paymentInformationBean.getServiceFee());
//手续费日期
        counter_fee_date_number = ((TextView) findViewById(R.id.counter_fee_date_number));
        if (paymentInformationBean.getCl_sxfrq() != null)
            counter_fee_date_number.setText(new SimpleDateFormat("yyyy-MM-dd").
                    format(Double.parseDouble(paymentInformationBean.getCl_sxfrq())));
//手续费时间
        counter_fee_time_number = ((TextView) findViewById(R.id.counter_fee_time_number));
        if (paymentInformationBean.getCl_sxfsj() != null)
            counter_fee_time_number.setText(new SimpleDateFormat("HH:mm:ss").
                    format(Double.parseDouble(paymentInformationBean.getCl_sxfsj())));
        List<PaymentInformationBean.InBean> in = paymentInformationBean.getIn();
        List<MarginRecordItemBean> list = new ArrayList<>();
        double amountAll = 0;
        double cacheData = 0;
        double cacheTime = 0;
        for (PaymentInformationBean.InBean inBean : in) {

            if (inBean.getCl_type() != null) {
                if (inBean.getCl_type().equals("unfreeze") || inBean.getCl_type().equals("freeze")) {
                    MarginRecordItemBean marginRecordItemBean = new MarginRecordItemBean();
                    marginRecordItemBean.setDate(new SimpleDateFormat("yyyy-MM-dd").format(Double.parseDouble(inBean.getCl_yidrq())));
                    marginRecordItemBean.setTime(new SimpleDateFormat("HH:mm:ss").format(Double.parseDouble(inBean.getCl_yidsj())));
                    marginRecordItemBean.setMoney(inBean.getAmount());
                    marginRecordItemBean.setState("已冻结:");
                    list.add(marginRecordItemBean);

                }
                if (inBean.getCl_type().equals("unfreeze")) {
                    amountAll += Double.parseDouble(inBean.getAmount());
                    cacheData = Double.parseDouble(inBean.getCl_yidrq()) > cacheData ? Double.parseDouble(inBean.getCl_yidrq()) : cacheData;
                    cacheTime = Double.parseDouble(inBean.getCl_yidsj()) > cacheTime ? Double.parseDouble(inBean.getCl_yidsj()) : cacheTime;
                }
                if (inBean.getCl_type().equals("payTail")) {
                    //                    货款记录
                    capital.setText(inBean.getAmount());
                    frozen_date_number.setText(inBean.getCl_yidrq());
                    frozen_time_number.setText(inBean.getCl_yidsj());
                }
            }

        }
        if (cacheData != 0) {
            MarginRecordItemBean marginRecordItemBean = new MarginRecordItemBean();
            marginRecordItemBean.setDate(new SimpleDateFormat("yyyy-MM-dd").format(cacheData));
            marginRecordItemBean.setTime(new SimpleDateFormat("HH:mm:ss").format(cacheTime));
            marginRecordItemBean.setMoney(amountAll+"");
            marginRecordItemBean.setState("已解冻:");
            list.add(marginRecordItemBean);
        }
        marginRecordListViewAdapter.addData(list);
//        手续费已支付、货款已支付（否则手续费和货款都为待支付）
        if (paymentInformationBean.getOrderState() == "QUOTED" || paymentInformationBean.getOrderState() == "CHANGED") {
            //        手续费状态
            counter_fee_has_paded_tv.setText("已支付:");
            //        货款记录状态
            frozen_tv.setText("已支付:");
        } else {
            //        手续费状态
            counter_fee_has_paded_tv.setText("待支付:");
            //        货款记录状态
            frozen_tv.setText("待支付:");
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

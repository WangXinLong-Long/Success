package com.silianchuangye.sumao.success.fragments.myPlasticTrade.OrderManagement.SpotOrder.SpotOrderMVP.view.informationView;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.OrderManagement.SpotOrder.SpotOrderMVP.bean.InvoiceInformationBean;

/**
 * 开票信息
 */
public class InvoiceInformationActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tv_child_title_bar_title;
    private ImageView iv_child_title_bar_back;
    private TextView billing_status;
    private TextView bill;
    private TextView courier_number;
    private TextView express;
    private TextView remark;
    private RelativeLayout bill_rl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invoice_information2);
        String result = getIntent().getStringExtra("result");
        Gson gson = new Gson();
        InvoiceInformationBean invoiceInformationBean = gson.fromJson(result, InvoiceInformationBean.class);
        tv_child_title_bar_title = ((TextView) findViewById(R.id.tv_child_title_bar_title));
        tv_child_title_bar_title.setText("发票信息");
//        返回按钮
        iv_child_title_bar_back = ((ImageView) findViewById(R.id.iv_child_title_bar_back));
        iv_child_title_bar_back.setOnClickListener(this);
        findView();
        if (invoiceInformationBean.getCl_kaipiao() == null) {
//        开票状态
            billing_status.setText("未开票");
//        票据
            bill.setText("无");
//        快递单号
            courier_number.setText("无");
//        快递公司
            express.setText("无");
//        备注
            remark.setText("无");
        } else if (invoiceInformationBean.getCl_kaipiao().equals("1")) {
            //        开票状态
            billing_status.setText("已开票");
//        票据
            bill.setText("查看扫描件");
            bill_rl.setOnClickListener(this);
//        快递单号
            courier_number.setText(invoiceInformationBean.getCl_kddanhao());
//        快递公司
            express.setText(invoiceInformationBean.getCl_kdgongsi());
//        备注
            remark.setText(invoiceInformationBean.getCl_beizhu());


        }

    }

    private void findView() {
//        开票状态
        billing_status = ((TextView) findViewById(R.id.billing_status));
//        票据
        bill = ((TextView) findViewById(R.id.bill));
//        快递单号
        courier_number = ((TextView) findViewById(R.id.courier_number));
//        快递公司
        express = ((TextView) findViewById(R.id.express));
//        备注
        remark = ((TextView) findViewById(R.id.remark));
//        查看扫描件
        bill_rl = ((RelativeLayout) findViewById(R.id.bill_rl));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_child_title_bar_back:
                finish();
                break;
            case R.id.bill_rl:
//                Intent intent = new Intent(InvoiceInformationActivity.this,ViewScanner.class);
                break;

            default:
                break;
        }
    }
}

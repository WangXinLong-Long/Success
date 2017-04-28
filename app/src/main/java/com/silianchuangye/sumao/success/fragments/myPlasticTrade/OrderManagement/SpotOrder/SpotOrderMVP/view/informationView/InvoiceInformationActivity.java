package com.silianchuangye.sumao.success.fragments.myPlasticTrade.OrderManagement.SpotOrder.SpotOrderMVP.view.informationView;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.OrderManagement.SpotOrder.SpotOrderMVP.bean.InvoiceInformationBean;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.OrderManagement.SpotOrder.SpotOrderMVP.view.ShowPictureActivity;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.qurchaseQualification.purchaseQualificationDetail.DataDetailsActivity;

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
    //private RelativeLayout bill_rl;
    private String unique;
    private String Id;
    private TextView dividing_line1,dividing_line2,dividing_line3,dividing_line4;
    private RelativeLayout express_rl,courier_number_rl,bill_rl,remark_rl;
    private String path;
    private RelativeLayout billing_status_rl;
    private TextView tv_info;
    private RelativeLayout layout_info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invoice_information2);
//        Bundle bundle=getIntent().getExtras();
//        Id=bundle.getString("id");
//        unique=bundle.getString("unique");
        billing_status_rl= (RelativeLayout) findViewById(R.id.billing_status_rl);
        layout_info= (RelativeLayout) findViewById(R.id.layout_info);
        tv_info= (TextView) findViewById(R.id.tv_info);
        String result=getIntent().getStringExtra("result");
        Log.d("发票信息的返回值",""+result);
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
            layout_info.setVisibility(View.GONE);
            tv_info.setVisibility(View.VISIBLE);
            billing_status_rl.setVisibility(View.GONE);
            express_rl.setVisibility(View.GONE);
            courier_number_rl.setVisibility(View.GONE);
            bill_rl.setVisibility(View.GONE);
            remark_rl.setVisibility(View.GONE);
            dividing_line1.setVisibility(View.GONE);
            dividing_line2.setVisibility(View.GONE);
            dividing_line3.setVisibility(View.GONE);
            dividing_line4.setVisibility(View.GONE);
//        票据
            //bill.setText("无");
//        快递单号
           // courier_number.setText("无");
//        快递公司
            //express.setText("无");
//        备注
            //remark.setText("无");
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
            path=invoiceInformationBean.getCl_piaoju();

        }

    }

    private void findView() {
        express_rl= (RelativeLayout) findViewById(R.id.express_rl);
        courier_number_rl= (RelativeLayout) findViewById(R.id.courier_number_rl);
        bill_rl= (RelativeLayout) findViewById(R.id.bill_rl);
        remark_rl= (RelativeLayout) findViewById(R.id.remark_rl);
        dividing_line1= (TextView) findViewById(R.id.dividing_line1);
        dividing_line2= (TextView) findViewById(R.id.dividing_line2);
        dividing_line3= (TextView) findViewById(R.id.dividing_line3);
        dividing_line4= (TextView) findViewById(R.id.dividing_line4);
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
//                Intent intent=new Intent();
//                intent.setClass(InvoiceInformationActivity.this, DataDetailsActivity.class);
//                intent.putExtra("title", "票据");
////                判断是否需要上传图片
//                intent.putExtra("picture", true);
//                intent.putExtra("resouce","search");
//                intent.putExtra("path","/mnt/docs/upload/order/"+path);
//                startActivity(intent);
                Intent intent=new Intent(InvoiceInformationActivity.this, ShowPictureActivity.class);
                intent.putExtra("path",path);
                intent.putExtra("title","票据");
                startActivity(intent);
                break;

            default:
                break;
        }
    }


}

package com.silianchuangye.sumao.success.fragments.companyInfomation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.silianchuangye.sumao.success.HX.ui.ModifyActivity;
import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.fragments.enterpriseInformation.SelectProvinceArea;
import com.silianchuangye.sumao.success.fragments.personalInformation.ModifyName;

/**
 * Created by Administrator on 2016/5/16 0016.
 */
public class InvoiceInformation extends Activity implements View.OnClickListener{
    ImageView iv_child_title_bar_back;
    TextView tv_child_title_bar_title;
    RelativeLayout invoice_information_address_rl,invoice_information_telephone_rl,
            invoice_information_bank_account_rl,invoice_information_account_number_rl,
            bill_mailing_address_name_rl,bill_mailing_address_telephone_rl,
            bill_mailing_address_fix_telephone_rl,bill_mailing_address_mailing_address_rl,
            bill_mailing_address_zip_code_rl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invoice_information);
        iv_child_title_bar_back = ((ImageView) findViewById(R.id.iv_child_title_bar_back));
        tv_child_title_bar_title = ((TextView) findViewById(R.id.tv_child_title_bar_title));
        tv_child_title_bar_title.setText("开票信息");
        invoice_information_address_rl = ((RelativeLayout) findViewById(R.id.invoice_information_address_rl));
        invoice_information_telephone_rl = ((RelativeLayout) findViewById(R.id.invoice_information_telephone_rl));
        invoice_information_bank_account_rl = ((RelativeLayout) findViewById(R.id.invoice_information_bank_account_rl));
        invoice_information_account_number_rl = ((RelativeLayout) findViewById(R.id.invoice_information_account_number_rl));
        bill_mailing_address_name_rl = ((RelativeLayout) findViewById(R.id.bill_mailing_address_name_rl));
        bill_mailing_address_telephone_rl = ((RelativeLayout) findViewById(R.id.bill_mailing_address_telephone_rl));
        bill_mailing_address_fix_telephone_rl = ((RelativeLayout) findViewById(R.id.bill_mailing_address_fix_telephone_rl));
        bill_mailing_address_mailing_address_rl = ((RelativeLayout) findViewById(R.id.bill_mailing_address_mailing_address_rl));
        bill_mailing_address_zip_code_rl = ((RelativeLayout) findViewById(R.id.bill_mailing_address_zip_code_rl));
        invoice_information_address_rl.setOnClickListener(this);
        invoice_information_telephone_rl.setOnClickListener(this);
        invoice_information_bank_account_rl.setOnClickListener(this);
        invoice_information_account_number_rl.setOnClickListener(this);
        bill_mailing_address_name_rl.setOnClickListener(this);
        bill_mailing_address_telephone_rl.setOnClickListener(this);
        bill_mailing_address_fix_telephone_rl.setOnClickListener(this);
        bill_mailing_address_mailing_address_rl.setOnClickListener(this);
        bill_mailing_address_zip_code_rl.setOnClickListener(this);
        iv_child_title_bar_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        bill_mailing_address_mailing_address_rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(InvoiceInformation.this,SelectProvinceArea.class);
                intent.putExtra("className","InvoiceInformation");
                startActivity(intent);
            }
        });
    }


    @Override
    public void onClick(View v) {
        Intent intent  = new Intent();
        Bundle bundle  = new Bundle();
        intent.setClass(InvoiceInformation.this, ModifyName.class);
        switch (v.getId())
        {

            case R.id.invoice_information_address_rl:
                bundle.putString("receivingInformation","修改地址");
                bundle.putBoolean("canBeAmpty",false);
                bundle.putString("message","地址");
                break;
            case R.id.invoice_information_telephone_rl:
                bundle.putString("receivingInformation","修改电话");
                bundle.putBoolean("canBeAmpty",false);
                bundle.putString("message","电话");
                break;
            case R.id.invoice_information_bank_account_rl:
                bundle.putString("receivingInformation","修改开户行");
                bundle.putBoolean("canBeAmpty",false);
                bundle.putString("message","开户行");
                break;
            case R.id.invoice_information_account_number_rl:
                bundle.putString("receivingInformation","修改账号");
                bundle.putBoolean("canBeAmpty",false);
                bundle.putString("message","账号");
                break;
            case R.id.bill_mailing_address_name_rl:
                bundle.putString("receivingInformation","修改姓名");
                bundle.putBoolean("canBeAmpty",false);
                bundle.putString("message","姓名");
                break;
            case R.id.bill_mailing_address_telephone_rl:
                bundle.putString("receivingInformation","修改手机");
                bundle.putBoolean("canBeAmpty",false);
                bundle.putString("message","手机");
                break;
            case R.id.bill_mailing_address_fix_telephone_rl:
                bundle.putString("receivingInformation","修改固定电话");
                bundle.putBoolean("canBeAmpty",false);
                bundle.putString("message","固定电话");
                break;

            case R.id.bill_mailing_address_zip_code_rl:
                bundle.putString("receivingInformation","修改邮编");
                bundle.putBoolean("canBeAmpty",false);
                bundle.putString("message","邮编");
                break;
            default:
                break;

        }
        intent.putExtras(bundle);
        startActivity(intent);
    }
}

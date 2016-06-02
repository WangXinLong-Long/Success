package com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.receiptAddress;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.silianchuangye.sumao.success.R;

/**
 * Created by Administrator on 2016/5/13 0013.
 */
public class AddAddress extends Activity implements View.OnClickListener {
    ImageView iv_child_title_bar_back;
    TextView tv_child_title_bar_title;
    RelativeLayout address_add_address;
    EditText consignee1_name,telephone_number,zip_code_number,fix_telehone_code_number;
    TextView address_county,address_detail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_address);
        iv_child_title_bar_back = ((ImageView) findViewById(R.id.iv_child_title_bar_back));
        tv_child_title_bar_title = ((TextView) findViewById(R.id.tv_child_title_bar_title));
        address_add_address = ((RelativeLayout) findViewById(R.id.address_add_address));
        tv_child_title_bar_title.setText("新增地址");
        consignee1_name = ((EditText) findViewById(R.id.consignee1_name));
        telephone_number = ((EditText) findViewById(R.id.telephone_number));
        zip_code_number = ((EditText) findViewById(R.id.zip_code_number));
        fix_telehone_code_number = ((EditText) findViewById(R.id.fix_telehone_code_number));
        address_county = ((TextView) findViewById(R.id.address_county));
        address_detail = ((TextView) findViewById(R.id.address_detail));
        address_add_address.setOnClickListener(this);
        iv_child_title_bar_back.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.iv_child_title_bar_back:
                finish();
                break;
            case R.id.address_add_address:
                Intent intent = new Intent();
                intent.putExtra("className","AddAddress");
                intent.setClass(AddAddress.this,SelectProvinceArea.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}

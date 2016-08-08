package com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.receiptAddress.AddAddressMVP.view;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.receiptAddress.AddAddressMVP.bean.AddAddressBean;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.receiptAddress.AddAddressMVP.presenter.AddAddressPresenter;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.receiptAddress.SelectDetailAreaMVP.view.ISelectDetailCallback;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.receiptAddress.SelectDetailAreaMVP.view.SelectDetailArea;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.receiptAddress.SelectProvinceAreaMVP.view.SelectProvinceArea;
import com.silianchuangye.sumao.success.utils.LogUtils;


public class AddAddress extends Activity implements View.OnClickListener , IAddAddress {
    ImageView iv_child_title_bar_back;
    TextView tv_child_title_bar_title;
    RelativeLayout address_add_address;
    EditText consignee1_name,telephone_number,zip_code_number,fix_telehone_code_number;
    TextView address_county,address_detail;
    Intent intent;
    private String address;
    private String detailAddress;
    private AddAddressPresenter presenter;
    private Button activity_add_address_determine;
    private StringBuilder builder;
    private StringBuilder sb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_address);
        presenter= new AddAddressPresenter(AddAddress.this);
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
        activity_add_address_determine = ((Button) findViewById(R.id.activity_add_address_determine));
        activity_add_address_determine.setOnClickListener(this);
         builder = new StringBuilder();
        sb = new StringBuilder();
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
                intent.setClass(AddAddress.this, SelectProvinceArea.class);
                startActivity(intent);
                break;
            case R.id.activity_add_address_determine:

                presenter.sendAddAddressToServer();
                break;
            default:
                break;
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        address = getIntent().getStringExtra("address");

        sb.append(address);

        presenter.setDetailAddress(sb.substring(0,4), sb.substring(0,6), sb.toString());
        detailAddress = getIntent().getStringExtra("detailAddress");
        address_detail.setText(detailAddress);
    }

    @Override
    public void setAddressInText(String address) {
        address_county.setText(address);
    }

    @Override
    public void sendAddAddressToServerSuccess() {
        Toast.makeText(this,"新增地址成功！",Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void sendAddAddressToServerFaild() {
        Toast.makeText(this,"新增地址失败，请稍后再试！",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void sendAddAddressToServer() {
        if (consignee1_name.getText().toString().trim() == null || consignee1_name.getText().toString().trim().isEmpty()) {
            Toast.makeText(AddAddress.this, "收货人为空", Toast.LENGTH_SHORT).show();
        } else if (telephone_number.getText().toString().trim() == null || telephone_number.getText().toString().trim().isEmpty()) {
            Toast.makeText(AddAddress.this, "手机号码为空", Toast.LENGTH_SHORT).show();
        } else if (sb.toString().trim() == null || sb.toString().trim().isEmpty()) {
            Toast.makeText(AddAddress.this, "地址为空", Toast.LENGTH_SHORT).show();
        }else {
            SharedPreferences sp=getSharedPreferences("sumao",Activity.MODE_PRIVATE);
            String unique=sp.getString("unique","");
//            builder.
//                    append(sb.substring(0,4)).append(",").//省的level
//                    append(sb.substring(0,6)).append(",").//市的level
//                    append( sb.toString()).append(",").//县的level
//                    append(unique).append(",").//sessionId
//                    append(consignee1_name.getText().toString().trim()).append(",").//收货人
//                    append(fix_telehone_code_number.getText().toString().trim()).append(",").//固定电话
//                    append(zip_code_number.getText().toString().trim()).append(",").//邮编
//                    append(telephone_number.getText().toString().trim()).append(",").//移动电话
//                    append(address_detail.getText().toString().trim());//详细地址
//            for (String s:builder.toString().split(",")){
//                LogUtils.log("StringBuilder--->s:"+s);
//            }

            presenter.sendAddAddressInfo(sb.toString(),unique,consignee1_name.getText().toString(),
                    fix_telehone_code_number.getText().toString(),zip_code_number.getText().toString(),
                    telephone_number.getText().toString(),address_detail.getText().toString());
        }

    }
}

package com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.receiptAddress.SelectDetailAreaMVP.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.EnterpriseUserManagement.InvoiceInformation;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.receiptAddress.AddAddressMVP.view.AddAddress;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.receiptAddress.ReceiptAddressDetailMVP.view.ReceiptAddressDetail;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.register.RegisterFirmActivityMVP.view.RegisterFirmActivity;
import com.silianchuangye.sumao.success.utils.LogUtils;

/**
 * Created by Administrator on 2016/5/13 0013.
 */
public class SelectDetailArea extends Activity implements View.OnClickListener,ISelectDetailAreaView{
    ImageView iv_child_title_bar_back;
    TextView tv_child_title_bar_title;
    Intent intent;
    String county;
    EditText modify_information;
    TextView prompt_information;
    Button modify_name_save;
    String className;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_address_name);
        iv_child_title_bar_back = ((ImageView) findViewById(R.id.iv_child_title_bar_back));
        tv_child_title_bar_title = ((TextView) findViewById(R.id.tv_child_title_bar_title));
        modify_information = ((EditText) findViewById(R.id.modify_information));
        prompt_information = ((TextView) findViewById(R.id.prompt_information));
        intent = getIntent();
        county = intent.getStringExtra("county");
        className = intent.getStringExtra("className");
        tv_child_title_bar_title.setText("填写详细地址");
        modify_name_save = ((Button) findViewById(R.id.modify_name_save));
        modify_name_save.setOnClickListener(this);
        iv_child_title_bar_back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
//            保存按钮
            case R.id.modify_name_save:
                canCloseThisActivity();
                break;
//            返回按钮
            case R.id.iv_child_title_bar_back:
                finish();
                break;
        }
    }
    public void canCloseThisActivity() {
        if (modify_information.getText().toString().equals("")) {

            prompt_information.setText("*请输入" + "详细地址");
            prompt_information.setVisibility(View.VISIBLE);


        } else {
            prompt_information.setVisibility(View.INVISIBLE);
            /**
             * 在这里吧EditText的文本信息获取到，调用接口传到服务器上
             */

            Intent intent = new Intent();
            intent.putExtra("address",county);
            LogUtils.log("county----------->"+county);
            intent.putExtra("detailAddress",modify_information.getText().toString().trim());
            if (className.equals("AddAddress")){
                intent.setClass(SelectDetailArea.this,AddAddress.class);
            }else if (className.equals("InvoiceInformation"))
            {
                intent.setClass(SelectDetailArea.this,InvoiceInformation.class);
            }else if (className.equals("RegisterFirmActivity")){
                intent.setClass(SelectDetailArea.this,RegisterFirmActivity.class);
            }else if (className.equals("ReceiptAddressDetail")){
                intent.setClass(SelectDetailArea.this,ReceiptAddressDetail.class);
            }


            startActivity(intent);
        }
    }


}

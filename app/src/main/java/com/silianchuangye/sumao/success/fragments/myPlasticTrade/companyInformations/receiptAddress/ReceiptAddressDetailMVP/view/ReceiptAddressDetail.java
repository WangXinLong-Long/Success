package com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.receiptAddress.ReceiptAddressDetailMVP.view;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.custom.CustomDialog;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.receiptAddress.ReceiptAddressDetailMVP.presenter.ReceiptAddressDetailPresenter;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.receiptAddress.ReceiptAddressMVP.presenter.ReceiptAddressPresenter;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.personalInformation.ModifyName;
import com.silianchuangye.sumao.success.utils.LogUtils;

/**
 * Created by Administrator on 2016/5/12 0012.
 */
public class ReceiptAddressDetail extends Activity implements View.OnClickListener,IReceiptAddressDetailView{
    ImageView iv_title_bar_logo,
            iv_title_bar_back,
            iv_title_bar_service,
            iv_title_bar_search;
    Button sv_title_bar_serachView;
    TextView tv_title_bar_title,tv;
    RelativeLayout receipt_address_detail_title;
    RelativeLayout add_address_rl;

    Intent intent;
    Bundle bundle;

    TextView consignee_name,address_text1,address_text2,zip_code_text,telephone_detail_text,fixed_telephone_detail_text;
    Button default_receiving_address_false,default_receiving_address_true,remove_receipt_address;
    RelativeLayout consignee1_rl,receip_address_address,zip_code_rl,telephone_rl,fixed_telephone_rl;
    private ReceiptAddressDetailPresenter presenter;
    private String id;
    private String sessionId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receipt_address_detail);
        presenter = new ReceiptAddressDetailPresenter(this);
        receipt_address_detail_title = ((RelativeLayout) findViewById(R.id.receipt_address_detail_title));
        receipt_address_detail_title.setBackgroundColor(getResources().getColor(R.color.textColor_expandable_listview_show));

        iv_title_bar_back = ((ImageView) findViewById(R.id.iv_title_bar_back));
        iv_title_bar_logo = ((ImageView) findViewById(R.id.iv_title_bar_logo));
        iv_title_bar_service = ((ImageView) findViewById(R.id.iv_title_bar_service));
        iv_title_bar_search = ((ImageView) findViewById(R.id.iv_title_bar_search));
        sv_title_bar_serachView = ((Button) findViewById(R.id.sv_title_bar_serachView));
        tv_title_bar_title  = ((TextView) findViewById(R.id.tv_title_bar_title));
        add_address_rl = ((RelativeLayout) findViewById(R.id.add_address_rl));

        iv_title_bar_logo.setVisibility(View.INVISIBLE);
        iv_title_bar_service.setVisibility(View.INVISIBLE);
        sv_title_bar_serachView.setVisibility(View.INVISIBLE);
        add_address_rl.setVisibility(View.INVISIBLE);
        iv_title_bar_search.setVisibility(View.INVISIBLE);
        tv_title_bar_title.setText("收货地址");
        tv_title_bar_title.setTextColor(Color.WHITE);


        intent = getIntent();
        bundle =  intent.getExtras();
        String state = bundle.getString("state");
        sessionId = bundle.getString("sessionId");
        id = bundle.getString("id");
        String name = bundle.getString("name");
        String address = bundle.getString("address");
        String zipCode = bundle.getString("zipCode");
        String telephone = bundle.getString("telephone");
        String fixedTelephone = bundle.getString("fixedTelephone");
        Log.i("test",state+"：是否为默认地址----"+name+"：收件人姓名-----"+address+"：收货地址----"+zipCode+"：邮编----"+telephone+"：电话号码----"+fixedTelephone+"：固定电话");

        consignee_name = ((TextView) findViewById(R.id.consignee_name));
        address_text1 = ((TextView) findViewById(R.id.address_text1));
        address_text2 = ((TextView) findViewById(R.id.address_text2));
        zip_code_text = ((TextView) findViewById(R.id.zip_code_text));
        telephone_detail_text = ((TextView) findViewById(R.id.telephone_detail_text));
        fixed_telephone_detail_text = ((TextView) findViewById(R.id.fixed_telephone_detail_text));
        default_receiving_address_false = ((Button) findViewById(R.id.default_receiving_address_false));
        default_receiving_address_true = ((Button) findViewById(R.id.default_receiving_address_true));
        remove_receipt_address = ((Button) findViewById(R.id.remove_receipt_address));

        if (state.equals("true"))
        {
            default_receiving_address_true.setVisibility(View.VISIBLE);
            default_receiving_address_false.setVisibility(View.INVISIBLE);
            remove_receipt_address.setVisibility(View.INVISIBLE);
        }else {
            default_receiving_address_true.setVisibility(View.INVISIBLE);
            default_receiving_address_false.setVisibility(View.VISIBLE);
            remove_receipt_address.setVisibility(View.VISIBLE);
        }
        consignee_name.setText(name);
        address_text1.setText(address);
        zip_code_text.setText(zipCode);
        telephone_detail_text.setText(telephone);
        fixed_telephone_detail_text.setText(fixedTelephone);
        default_receiving_address_false.setOnClickListener(this);
        default_receiving_address_true.setOnClickListener(this);
        remove_receipt_address.setOnClickListener(this);
        iv_title_bar_back.setOnClickListener(this);

        consignee1_rl = ((RelativeLayout) findViewById(R.id.consignee1_rl));
        receip_address_address = ((RelativeLayout) findViewById(R.id.receip_address_address));
        zip_code_rl = ((RelativeLayout) findViewById(R.id.zip_code_rl));
        telephone_rl = ((RelativeLayout) findViewById(R.id.telephone_rl));
        fixed_telephone_rl = ((RelativeLayout) findViewById(R.id.fixed_telephone_rl));
        consignee1_rl.setOnClickListener(this);
        receip_address_address.setOnClickListener(this);
        zip_code_rl.setOnClickListener(this);
        telephone_rl.setOnClickListener(this);
        fixed_telephone_rl.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();

        switch (v.getId())
        {
//            设为默认收货地址
            case R.id.default_receiving_address_false:
                presenter.setDefaultReceivingAddress(id,sessionId);
                finish();
                break;
            case R.id.default_receiving_address_true:
                newAlertDialog();
                break;
            case R.id.remove_receipt_address:
                newAlertDialog();
                break;
            case R.id.iv_title_bar_back:
                finish();
                break;
            case R.id.consignee1_rl:
                Toast.makeText(ReceiptAddressDetail.this,"改名字",Toast.LENGTH_SHORT).show();
                intent.setClass(ReceiptAddressDetail.this,ModifyName.class);
                bundle.putString("receivingInformation","修改收件人姓名");
                bundle.putString("message","姓名");
                bundle.putBoolean("canBeAmpty",false);
                intent.putExtras(bundle);
                startActivity(intent);
                break;
            case R.id.receip_address_address:
                Toast.makeText(ReceiptAddressDetail.this,"改地址",Toast.LENGTH_SHORT).show();
                intent.setClass(ReceiptAddressDetail.this,ModifyName.class);
                bundle.putString("receivingInformation","修改收货地址");
                bundle.putString("message","地址");
                bundle.putBoolean("canBeAmpty",false);
                intent.putExtras(bundle);
                startActivity(intent);
                break;
            case R.id.zip_code_rl:
                Toast.makeText(ReceiptAddressDetail.this,"改邮编",Toast.LENGTH_SHORT).show();
                intent.setClass(ReceiptAddressDetail.this,ModifyName.class);
                bundle.putString("receivingInformation","修改邮政编码");
                bundle.putString("message","邮编");
                bundle.putBoolean("canBeAmpty",true);
                intent.putExtras(bundle);
                startActivity(intent);

                break;
            case R.id.telephone_rl:
                Toast.makeText(ReceiptAddressDetail.this,"改手机号",Toast.LENGTH_SHORT).show();
                intent.setClass(ReceiptAddressDetail.this,ModifyName.class);
                bundle.putString("receivingInformation","修改手机号");
                bundle.putString("message","手机号");
                bundle.putBoolean("canBeAmpty",false);
                intent.putExtras(bundle);
                startActivity(intent);
                break;
            case R.id.fixed_telephone_rl:
                Toast.makeText(ReceiptAddressDetail.this,"改固定电话",Toast.LENGTH_SHORT).show();
                intent.setClass(ReceiptAddressDetail.this,ModifyName.class);
                bundle.putString("receivingInformation","修改固定电话");
                bundle.putString("message","固定电话");
                bundle.putBoolean("canBeAmpty",true);
                intent.putExtras(bundle);
                startActivity(intent);
                break;

            default:

                break;
        }
    }

    private void newAlertDialog() {
        CustomDialog.Builder builder = new CustomDialog.Builder(this);
        builder.setTitle("你确定删除当前地址？");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                presenter.RemoveReceiptAddress(id,sessionId);
                Intent intent = new Intent();
                intent.setAction("updateReceiptAddressListView");
                ReceiptAddressDetail.this.sendBroadcast(intent);
                dialog.dismiss();
                finish();
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.create().show();
    }

    @Override
    public void defaultReceivingAddress(String result) {
        LogUtils.log("defaultReceivingAddress--->"+result+"<---defaultReceivingAddress");
    }

    @Override
    public void removeReceiptAddress(String result) {
        LogUtils.log("defaultReceivingAddress--->"+result+"<---defaultReceivingAddress");
    }
}

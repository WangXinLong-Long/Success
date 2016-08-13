package com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.EnterpriseUserManagement.InvoiceInformationMVP.view;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.silianchuangye.sumao.success.R;
//import com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.receiptAddress.SelectProvinceAreaMVP.view.SelectProvinceArea;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.EnterpriseUserManagement.InvoiceInformationMVP.bean.InvoiceInformationBean;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.EnterpriseUserManagement.InvoiceInformationMVP.presenter.InvoiceInformationPresenter;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.receiptAddress.AddAddressMVP.presenter.AddAddressPresenter;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.receiptAddress.SelectProvinceAreaMVP.view.SelectProvinceArea;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.personalInformation.ModifyName;
import com.silianchuangye.sumao.success.utils.SuMaoConstant;

/**
 * Created by Administrator on 2016/5/16 0016.
 */
public class InvoiceInformation extends Activity implements View.OnClickListener, IInvoiceInformationView {
    ImageView iv_child_title_bar_back;
    TextView tv_child_title_bar_title;
    RelativeLayout invoice_information_address_rl, invoice_information_telephone_rl,
            invoice_information_bank_account_rl, invoice_information_account_number_rl,
            bill_mailing_address_name_rl, bill_mailing_address_telephone_rl,
            bill_mailing_address_fix_telephone_rl, bill_mailing_address_mailing_address_rl,
            bill_mailing_address_zip_code_rl;
    InvoiceInformationPresenter invoiceInformationPresenter;
    AddAddressPresenter addAddressPresenter;
    private String sessionId;
    private TextView type_of_taxpayer_tv;
    private TextView company_name;
    private TextView duty_paragraph;
    private TextView invoice_information_address;
    private TextView invoice_information_telephone;
    private TextView invoice_information_bank_account;
    private TextView invoice_information_account_number_tv;
    private TextView bill_mailing_address_name_tv;
    private TextView bill_mailing_address_telephone_tv;
    private TextView bill_mailing_address_fix_telephone_tv;
    private TextView bill_mailing_address_mailing_address_tv_region;
    private TextView bill_mailing_address_mailing_address_tv_detail;
    private TextView bill_mailing_address_zip_code_tv;
    private Button save_modify_info;
    private StringBuilder sb;
    private RelativeLayout duty_paragraph_rl;
    private String newduty;
    private String newaddress;
    private String newPhone;
    private String newbank;
    private String newaccount_number;
    private String newname;
    private String newMobile;
    private String newBelowPhone;
    private String newzip_code;
    private String detailAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invoice_information);
        initView();
        initListener();
        invoiceInformationPresenter = new InvoiceInformationPresenter(this);
        addAddressPresenter = new AddAddressPresenter(this);

        SharedPreferences sp = getSharedPreferences("sumao", Activity.MODE_PRIVATE);
        sessionId = sp.getString("unique", "");
        sb = new StringBuilder();
        iv_child_title_bar_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        invoiceInformationPresenter.getInvoiceInformationToBean(sessionId);
    }

    void initView() {
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
        duty_paragraph_rl = ((RelativeLayout) findViewById(R.id.duty_paragraph_rl));
//        纳税人类型
        type_of_taxpayer_tv = ((TextView) findViewById(R.id.type_of_taxpayer_tv));
//        公司名称
        company_name = ((TextView) findViewById(R.id.company_name_tv));
//        税号
        duty_paragraph = ((TextView) findViewById(R.id.duty_paragraph_tv));
//        地址
        invoice_information_address = ((TextView) findViewById(R.id.invoice_information_address_tv));
        //        固定电话
        invoice_information_telephone = ((TextView) findViewById(R.id.invoice_information_telephone_tv));
        //        开户行
        invoice_information_bank_account = ((TextView) findViewById(R.id.invoice_information_bank_account_tv));
        //        账号
        invoice_information_account_number_tv = ((TextView) findViewById(R.id.invoice_information_account_number_tv));
        //        姓名
        bill_mailing_address_name_tv = ((TextView) findViewById(R.id.bill_mailing_address_name_tv));
        //        手机号
        bill_mailing_address_telephone_tv = ((TextView) findViewById(R.id.bill_mailing_address_telephone_tv));
        //        固定电话
        bill_mailing_address_fix_telephone_tv = ((TextView) findViewById(R.id.bill_mailing_address_fix_telephone_tv));
        //        邮寄地址地区
        bill_mailing_address_mailing_address_tv_region = ((TextView) findViewById(R.id.bill_mailing_address_mailing_address_tv_region));
        //        邮寄地址详细
        bill_mailing_address_mailing_address_tv_detail = ((TextView) findViewById(R.id.bill_mailing_address_mailing_address_tv_detail));
        //        邮编
        bill_mailing_address_zip_code_tv = ((TextView) findViewById(R.id.bill_mailing_address_zip_code_tv));
//        保存按钮
        save_modify_info = ((Button) findViewById(R.id.save_modify_info));
    }

    void initListener() {
        invoice_information_address_rl.setOnClickListener(this);
        invoice_information_telephone_rl.setOnClickListener(this);
        invoice_information_bank_account_rl.setOnClickListener(this);
        invoice_information_account_number_rl.setOnClickListener(this);
        bill_mailing_address_name_rl.setOnClickListener(this);
        bill_mailing_address_telephone_rl.setOnClickListener(this);
        bill_mailing_address_fix_telephone_rl.setOnClickListener(this);
        bill_mailing_address_mailing_address_rl.setOnClickListener(this);
        bill_mailing_address_zip_code_rl.setOnClickListener(this);
        save_modify_info.setOnClickListener(this);
        duty_paragraph_rl.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        intent.setClass(InvoiceInformation.this, ModifyName.class);
        switch (v.getId()) {

            case R.id.duty_paragraph_rl:
                bundle.putString("receivingInformation", "修改税号");
                bundle.putBoolean("canBeAmpty", false);
                bundle.putString("message", "税号");
                intent.putExtras(bundle);
                startActivityForResult(intent, SuMaoConstant.CHANGE_POSITION_ONE);

                break;
            case R.id.invoice_information_address_rl:
                bundle.putString("receivingInformation", "修改地址");
                bundle.putBoolean("canBeAmpty", false);
                bundle.putString("message", "地址");
                intent.putExtras(bundle);
                startActivityForResult(intent, SuMaoConstant.CHANGE_POSITION_TWO);
                break;
            case R.id.invoice_information_telephone_rl:
                bundle.putString("receivingInformation", "修改电话");
                bundle.putBoolean("canBeAmpty", false);
                bundle.putString("message", "电话");
                intent.putExtras(bundle);
                startActivityForResult(intent, SuMaoConstant.CHANGE_POSITION_THREE);
                break;
            case R.id.invoice_information_bank_account_rl:
                bundle.putString("receivingInformation", "修改开户行");
                bundle.putBoolean("canBeAmpty", false);
                bundle.putString("message", "开户行");
                intent.putExtras(bundle);
                startActivityForResult(intent, SuMaoConstant.CHANGE_POSITION_FOUR);
                break;
            case R.id.invoice_information_account_number_rl:
                bundle.putString("receivingInformation", "修改账号");
                bundle.putBoolean("canBeAmpty", false);
                bundle.putString("message", "账号");
                intent.putExtras(bundle);
                startActivityForResult(intent, SuMaoConstant.CHANGE_POSITION_FIVE);
                break;
            case R.id.bill_mailing_address_name_rl:
                bundle.putString("receivingInformation", "修改姓名");
                bundle.putBoolean("canBeAmpty", false);
                bundle.putString("message", "姓名");
                intent.putExtras(bundle);
                startActivityForResult(intent, SuMaoConstant.CHANGE_POSITION_SIX);
                break;
            case R.id.bill_mailing_address_telephone_rl:
                bundle.putString("receivingInformation", "修改手机");
                bundle.putBoolean("canBeAmpty", false);
                bundle.putString("message", "手机");
                intent.putExtras(bundle);
                startActivityForResult(intent, SuMaoConstant.CHANGE_POSITION_SEVEN);
                break;
            case R.id.bill_mailing_address_fix_telephone_rl:
                bundle.putString("receivingInformation", "修改固定电话");
                bundle.putBoolean("canBeAmpty", true);
                bundle.putString("message", "固定电话");
                intent.putExtras(bundle);
                startActivityForResult(intent, SuMaoConstant.CHANGE_POSITION_EIGHT);
                break;

            case R.id.bill_mailing_address_zip_code_rl:
                bundle.putString("receivingInformation", "修改邮编");
                bundle.putBoolean("canBeAmpty", true);
                bundle.putString("message", "邮编");
                intent.putExtras(bundle);
                startActivityForResult(intent, SuMaoConstant.CHANGE_POSITION_TEN);
                break;
            case R.id.save_modify_info:
//                保存按钮
                invoiceInformationPresenter.saveModifyInvoiceInformation(
                        duty_paragraph.getText().toString().trim(),
                        invoice_information_address.getText().toString().trim(),
                        invoice_information_telephone.getText().toString().trim(),
                        invoice_information_bank_account.getText().toString().trim(),
                        invoice_information_account_number_tv.getText().toString().trim(),
                        bill_mailing_address_name_tv.getText().toString().trim(),
                        bill_mailing_address_telephone_tv.getText().toString().trim(),
                        bill_mailing_address_fix_telephone_tv.getText().toString().trim(),
                        sb.substring(0, 4).toString(), sb.substring(0, 6).toString(), sb.toString().toString(),
                        bill_mailing_address_mailing_address_tv_detail.getText().toString().trim(),
                        bill_mailing_address_zip_code_tv.getText().toString().trim(),
                        sessionId
                        );
                break;
//            修改邮寄地址
            case R.id.bill_mailing_address_mailing_address_rl:
                Intent intentpor = new Intent();
                intentpor.setClass(InvoiceInformation.this, SelectProvinceArea.class);
                intentpor.putExtra("className", "InvoiceInformation");
                startActivity(intentpor);
                break;
            default:
                break;

        }


    }
    //            修改地址
    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        String address = getIntent().getStringExtra("address");
        sb.delete(0,sb.length());
        sb.append(address);
        addAddressPresenter.setDisplaylAddress(sb.substring(0, 4), sb.substring(0, 6), sb.toString());
        detailAddress = getIntent().getStringExtra("detailAddress");
        bill_mailing_address_mailing_address_tv_detail.setText(detailAddress);
        showSaveButton();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (!(data.getStringExtra(SuMaoConstant.MODIFY_INFORMATION).equals("") || data.getStringExtra(SuMaoConstant.MODIFY_INFORMATION).isEmpty())) {
            switch (requestCode) {
                //        税号
                case SuMaoConstant.CHANGE_POSITION_ONE:
                    String oldduty = duty_paragraph.getText().toString();
                    newduty = data.getStringExtra(SuMaoConstant.MODIFY_INFORMATION);
                    if (!(oldduty.equals(newduty))) {
                        duty_paragraph.setText(newduty);
                        showSaveButton();
                    }
                    break;

                //        地址
                case SuMaoConstant.CHANGE_POSITION_TWO:
//                zip_code_text
                    String oldaddress = invoice_information_address.getText().toString();
                    newaddress = data.getStringExtra(SuMaoConstant.MODIFY_INFORMATION);
                    if (!(oldaddress.equals(newaddress))) {
                        invoice_information_address.setText(newaddress);
                        showSaveButton();
                    }
                    break;
                //        固定电话
                case SuMaoConstant.CHANGE_POSITION_THREE:
                    String oldPhone = invoice_information_telephone.getText().toString();
                    newPhone = data.getStringExtra(SuMaoConstant.MODIFY_INFORMATION);
                    if (!(oldPhone.equals(newPhone))) {
                        invoice_information_telephone.setText(newPhone);
                        showSaveButton();
                    }

                    break;
                //        开户行
                case SuMaoConstant.CHANGE_POSITION_FOUR:
                    String oldbank = invoice_information_bank_account.getText().toString();
                    newbank = data.getStringExtra(SuMaoConstant.MODIFY_INFORMATION);
                    if (!(oldbank.equals(newbank))) {
                        invoice_information_bank_account.setText(newbank);
                        showSaveButton();
                    }
                    break;
                //        账号
                case SuMaoConstant.CHANGE_POSITION_FIVE:
                    String oldaccount_number = invoice_information_account_number_tv.getText().toString();
                    newaccount_number = data.getStringExtra(SuMaoConstant.MODIFY_INFORMATION);
                    if (!(oldaccount_number.equals(newaccount_number))) {
                        invoice_information_account_number_tv.setText(newaccount_number);
                        showSaveButton();
                    }
                    break;
                //        姓名
                case SuMaoConstant.CHANGE_POSITION_SIX:
                    String oldname = bill_mailing_address_name_tv.getText().toString();
                    newname = data.getStringExtra(SuMaoConstant.MODIFY_INFORMATION);
                    if (!(oldname.equals(newname))) {
                        bill_mailing_address_name_tv.setText(newname);
                        showSaveButton();
                    }
                    break;
                //        手机号
                case SuMaoConstant.CHANGE_POSITION_SEVEN:
                    String oldMobile = bill_mailing_address_telephone_tv.getText().toString();
                    newMobile = data.getStringExtra(SuMaoConstant.MODIFY_INFORMATION);
                    if (!(oldMobile.equals(newMobile))) {
                        bill_mailing_address_telephone_tv.setText(newMobile);
                        showSaveButton();
                    }
                    break;
                //        固定电话
                case SuMaoConstant.CHANGE_POSITION_EIGHT:
                    String oldBelowPhone = bill_mailing_address_fix_telephone_tv.getText().toString();
                    newBelowPhone = data.getStringExtra(SuMaoConstant.MODIFY_INFORMATION);
                    if (!(oldBelowPhone.equals(newBelowPhone))) {
                        bill_mailing_address_fix_telephone_tv.setText(newBelowPhone);
                        showSaveButton();
                    }
                    break;
                //        邮编
                case SuMaoConstant.CHANGE_POSITION_TEN:
                    String oldzip_code = bill_mailing_address_zip_code_tv.getText().toString();
                    newzip_code = data.getStringExtra(SuMaoConstant.MODIFY_INFORMATION);
                    if (!(oldzip_code.equals(newzip_code))) {
                        bill_mailing_address_zip_code_tv.setText(newzip_code);
                        showSaveButton();
                    }
                    break;
            }
        }


    }

    private void showSaveButton() {
        save_modify_info.setVisibility(View.VISIBLE);
    }



    @Override
    public void setResultInText(InvoiceInformationBean bean) {
//        要获取省市县的level
        String province = bean.getInvoice().getNotesRecipientProvince();
        String city = bean.getInvoice().getNotesRecipientCity();
        String county = bean.getInvoice().getNotesRecipientCounty();
        sb.append(county);
        addAddressPresenter.sendrequestGetAddress(province,city,county);
        //        纳税人类型
        type_of_taxpayer_tv.setText(bean.getCl_nsrleixing());
        //        公司名称
        company_name.setText(bean.getCl_mingcheng());
        //        税号
        duty_paragraph.setText(bean.getInvoice().getTaxID());
        //        地址
        invoice_information_address.setText(bean.getInvoice().getAddress());
        //        固定电话
        invoice_information_telephone.setText(bean.getInvoice().getNotesRecipientPhone());
        //        开户行
        invoice_information_bank_account.setText(bean.getInvoice().getBank());
        //        账号
        invoice_information_account_number_tv.setText(bean.getInvoice().getBankAccount());
        //        姓名
        bill_mailing_address_name_tv.setText(bean.getInvoice().getNotesRecipientName());
        //        手机号
        bill_mailing_address_telephone_tv.setText(bean.getInvoice().getNotesRecipientMobile());
        //        固定电话
        bill_mailing_address_fix_telephone_tv.setText(bean.getInvoice().getNotesRecipientPhone());

        //        邮寄地址详细
        bill_mailing_address_mailing_address_tv_detail.setText(bean.getInvoice().getNotesRecipientAddress());
        //        邮编
        bill_mailing_address_zip_code_tv.setText(bean.getInvoice().getNotesRecipientZipCode());

    }

    @Override
    public void setRegionInText(String result) {
//        邮寄地址地区
        bill_mailing_address_mailing_address_tv_region.setText(result);
    }

    //    保存修改后的信息失败
    @Override
    public void saveModifyInvoiceInformationFailed() {
        Toast.makeText(this,"修改失败",Toast.LENGTH_SHORT).show();
    }


//    保存修改后的信息成功
    @Override
    public void saveModifyInvoiceInformationSuccess() {
        Toast.makeText(this,"修改成功",Toast.LENGTH_SHORT).show();
        finish();
    }
}

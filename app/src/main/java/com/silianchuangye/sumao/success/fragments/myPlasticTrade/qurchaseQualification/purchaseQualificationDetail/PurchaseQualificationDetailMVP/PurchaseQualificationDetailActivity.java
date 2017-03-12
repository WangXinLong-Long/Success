package com.silianchuangye.sumao.success.fragments.myPlasticTrade.qurchaseQualification.purchaseQualificationDetail.PurchaseQualificationDetailMVP;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.qurchaseQualification.purchaseQualificationDetail.DataDetailsActivity;
//import com.silianchuangye.sumao.success.fragments.myPlasticTrade.qurchaseQualification.purchaseQualificationDetail.PurchaseQualificationDetailMVP.presenter.PurchaseQualificationDetailPresenter;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.qurchaseQualification.purchaseQualificationDetail.PurchaseQualificationDetailMVP.view.IPurchaseQualificationDetailView;
import com.silianchuangye.sumao.success.utils.LogUtils;

public class PurchaseQualificationDetailActivity extends AppCompatActivity implements View.OnClickListener ,IPurchaseQualificationDetailView {

    private RelativeLayout corporate_name_rl;
    private RelativeLayout customer_bank_license_rl;
    private RelativeLayout annual_demand_rl;
    private RelativeLayout number_of_employees_rl;
    private RelativeLayout name_rl;
    private RelativeLayout mailbox_rl;
    private RelativeLayout telephone_rl;
    private RelativeLayout fax_number_rl;
    private TextView corporate_name;
    private TextView customer_bank_license;
    private TextView annual_demand;
    private TextView number_of_employees;
    private TextView name;
    private TextView mailbox;
    private TextView telephone;
    private TextView fax_number;
    public static final int CORPORATE_NAME = 1;
    public static final int CUSTOMER_BANK_LICENSE = 2;
    public static final int ANNUAL_DEMAND = 3;
    public static final int NUMBER_OF_EMPLOYEES = 4;
    public static final int NAME = 5;
    public static final int MAILBOX = 6;
    public static final int TELEPHONE = 7;
    public static final int FAX_NUMBER = 8;
    private TextView corporate_name_content;
    private TextView customer_bank_license_content;
    private TextView fax_number_content;
    private TextView telephone_content;
    private TextView mailbox_content;
    private TextView name_content;
    private TextView number_of_employees_content;
    private TextView annual_demand_content;
    private Button submit_button;
//    上游资源方id
    private String id;
    private String dynSessConf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase_qualification_detail);
//        上游资源方id
        id = getIntent().getStringExtra("id");
        //PurchaseQualificationDetailPresenter purchaseQualificationDetailPresenter = new PurchaseQualificationDetailPresenter(this);
        dynSessConf = getSharedPreferences("sumao", Activity.MODE_PRIVATE).getString("unique", "");
        //purchaseQualificationDetailPresenter.getAuditStatus(dynSessConf,id);
        findView();
    }

    private void findView() {
//        企业法人姓名
        corporate_name_rl = ((RelativeLayout) findViewById(R.id.corporate_name_rl));
        corporate_name_rl.setOnClickListener(this);
//        客户银行许可证
        customer_bank_license_rl = ((RelativeLayout) findViewById(R.id.customer_bank_license_rl));
        customer_bank_license_rl.setOnClickListener(this);
//        年需求量
        annual_demand_rl = ((RelativeLayout) findViewById(R.id.annual_demand_rl));
        annual_demand_rl.setOnClickListener(this);
//        从业人员数量
        number_of_employees_rl = ((RelativeLayout) findViewById(R.id.number_of_employees_rl));
        number_of_employees_rl.setOnClickListener(this);
//        姓名
        name_rl = ((RelativeLayout) findViewById(R.id.name_rl));
        name_rl.setOnClickListener(this);
//        邮箱
        mailbox_rl = ((RelativeLayout) findViewById(R.id.mailbox_rl));
        mailbox_rl.setOnClickListener(this);
//        手机号
        telephone_rl = ((RelativeLayout) findViewById(R.id.telephone_rl));
        telephone_rl.setOnClickListener(this);
//        传真号
        fax_number_rl = ((RelativeLayout) findViewById(R.id.fax_number_rl));
        fax_number_rl.setOnClickListener(this);
//        企业法人姓名
        corporate_name = ((TextView) findViewById(R.id.corporate_name));
        corporate_name_content = ((TextView) findViewById(R.id.corporate_name_content));
//        客户银行许可证
        customer_bank_license = ((TextView) findViewById(R.id.customer_bank_license));
        customer_bank_license_content = ((TextView) findViewById(R.id.customer_bank_license_content));
//        年需求量
        annual_demand = ((TextView) findViewById(R.id.annual_demand));
        annual_demand_content = ((TextView) findViewById(R.id.annual_demand_content));
//        从业人员数量
        number_of_employees = ((TextView) findViewById(R.id.number_of_employees));
        number_of_employees_content = ((TextView) findViewById(R.id.number_of_employees_content));
//        姓名
        name = ((TextView) findViewById(R.id.name));
        name_content = ((TextView) findViewById(R.id.name_content));
//        邮箱
        mailbox = ((TextView) findViewById(R.id.mailbox));
        mailbox_content = ((TextView) findViewById(R.id.mailbox_content));
//        手机号
        telephone = ((TextView) findViewById(R.id.telephone));
        telephone_content = ((TextView) findViewById(R.id.telephone_content));
//        传真号
        fax_number = ((TextView) findViewById(R.id.fax_number));
        fax_number_content = ((TextView) findViewById(R.id.fax_number_content));
//        提交审核
        submit_button = ((Button) findViewById(R.id.submit_button));
        submit_button.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.corporate_name_rl:
                intent.setClass(PurchaseQualificationDetailActivity.this, DataDetailsActivity.class);
                intent.putExtra("title", corporate_name.getText().toString());
//                判断是否需要上传图片
                intent.putExtra("picture", true);
                startActivityForResult(intent, CORPORATE_NAME);
                break;
            case R.id.customer_bank_license_rl:
                intent.setClass(PurchaseQualificationDetailActivity.this, DataDetailsActivity.class);
                intent.putExtra("title", customer_bank_license.getText().toString());
                intent.putExtra("picture", true);
                startActivityForResult(intent, CUSTOMER_BANK_LICENSE);
                break;
            case R.id.annual_demand_rl:
                intent.setClass(PurchaseQualificationDetailActivity.this, DataDetailsActivity.class);
                intent.putExtra("title", annual_demand.getText().toString());
                intent.putExtra("picture", false);
                startActivityForResult(intent, ANNUAL_DEMAND);
                break;
            case R.id.number_of_employees_rl:
                intent.setClass(PurchaseQualificationDetailActivity.this, DataDetailsActivity.class);
                intent.putExtra("title", number_of_employees.getText().toString());
                intent.putExtra("picture", false);
                startActivityForResult(intent, NUMBER_OF_EMPLOYEES);
                break;
            case R.id.name_rl:
                intent.setClass(PurchaseQualificationDetailActivity.this, DataDetailsActivity.class);
                intent.putExtra("title", name.getText().toString());
                intent.putExtra("picture", true);
                startActivityForResult(intent, NAME);
                break;
            case R.id.mailbox_rl:
                intent.setClass(PurchaseQualificationDetailActivity.this, DataDetailsActivity.class);
                intent.putExtra("title", mailbox.getText().toString());
                intent.putExtra("picture", false);
                startActivityForResult(intent, MAILBOX);
                break;
            case R.id.telephone_rl:
                intent.setClass(PurchaseQualificationDetailActivity.this, DataDetailsActivity.class);
                intent.putExtra("title", telephone.getText().toString());
                intent.putExtra("picture", false);
                startActivityForResult(intent, TELEPHONE);
                break;
            case R.id.fax_number_rl:
                intent.setClass(PurchaseQualificationDetailActivity.this, DataDetailsActivity.class);
                intent.putExtra("title", fax_number.getText().toString());
                intent.putExtra("picture", false);
                startActivityForResult(intent, FAX_NUMBER);
                break;
            case R.id.submit_button:
                String corporateName = corporate_name_content.getText().toString();
                LogUtils.log("企业法人姓名："+corporateName);
                break;
            default:
                break;
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case CORPORATE_NAME:
                if (resultCode == RESULT_OK) {
                    String returnInfo = data.getStringExtra("returnInfo");
                    corporate_name_content.setText(returnInfo);
                }
                break;
            case CUSTOMER_BANK_LICENSE:
                if (resultCode == RESULT_OK) {
                    String returnInfo = data.getStringExtra("returnInfo");
                    customer_bank_license_content.setText(returnInfo);
                }
                break;
            case ANNUAL_DEMAND:
                if (resultCode == RESULT_OK) {
                    String returnInfo = data.getStringExtra("returnInfo");
                    annual_demand_content.setText(returnInfo+"吨/年");
                }
                break;
            case NUMBER_OF_EMPLOYEES:
                if (resultCode == RESULT_OK) {
                    String returnInfo = data.getStringExtra("returnInfo");
                    number_of_employees_content.setText(returnInfo+"人");
                }
                break;
            case NAME:
                if (resultCode == RESULT_OK) {
                    String returnInfo = data.getStringExtra("returnInfo");
                    name_content.setText(returnInfo);
                }
                break;
            case MAILBOX:
                if (resultCode == RESULT_OK) {
                    String returnInfo = data.getStringExtra("returnInfo");
                    mailbox_content.setText(returnInfo);
                }
                break;
            case TELEPHONE:
                if (resultCode == RESULT_OK) {
                    String returnInfo = data.getStringExtra("returnInfo");
                    telephone_content.setText(returnInfo);
                }
                break;
            case FAX_NUMBER:
                if (resultCode == RESULT_OK) {
                    String returnInfo = data.getStringExtra("returnInfo");
                    fax_number_content.setText(returnInfo);
                }
                break;

        }
    }

    @Override
    public void setAuditStatus(String result) {

    }
}

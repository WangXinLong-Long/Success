package com.silianchuangye.sumao.success.fragments.SellerManagementPlatform.CustomerApproval;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.adapter.CustomerApprovalAdapter;
import com.silianchuangye.sumao.success.model.CustomerApprovalModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/7/13 0013.
 */
public class CustomerApproval extends Activity implements AdapterView.OnItemClickListener ,View.OnClickListener{

    private ListView mListView;
    private CustomerApprovalAdapter adapter;
    private List<CustomerApprovalModel> mLists;
    CustomerApprovalModel customerApprovalModel;
    private TextView tv_child_title_bar_title;
    private ImageView iv_child_title_bar_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.customer_approval_activity);
        mListView = ((ListView) findViewById(R.id.custom_approval_list_view));
        tv_child_title_bar_title = ((TextView) findViewById(R.id.tv_child_title_bar_title));
        iv_child_title_bar_back = ((ImageView) findViewById(R.id.iv_child_title_bar_back));
        iv_child_title_bar_back.setOnClickListener(this);
        tv_child_title_bar_title.setText("客户审批");
        initData();
        adapter = new CustomerApprovalAdapter(this,mLists);
        mListView.setAdapter(adapter);
        mListView.setOnItemClickListener(this);
    }

    private void initData() {
        mLists = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            customerApprovalModel = new CustomerApprovalModel();
            customerApprovalModel.setTitle("北京四联京津冀");
            if (i%2==0)
            {
                customerApprovalModel.setState("未审批");
            }else {
                customerApprovalModel.setState("未通过");
            }
            customerApprovalModel.setNumber(80+i+"");
            customerApprovalModel.setEnterpriseName("四联创业化工集团");
            customerApprovalModel.setTypeOfEnterprise("生产厂");
            customerApprovalModel.setOfficeAddress("河北省邢台市沙河县");
            customerApprovalModel.setCustomerStatus("失效");
            customerApprovalModel.setPurchasingQualification("有");
            customerApprovalModel.setSalesman("陈奥");
            customerApprovalModel.setNotApprovedReasons("企业三证号码与实际不符");
            mLists.add(customerApprovalModel);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.iv_child_title_bar_back:
                finish();
                break;
            default:
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//        Intent intent = new Intent();
//        intent.setClass()
    }
}

package com.silianchuangye.sumao.success.fragments.SellerManagementPlatform;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.RelativeLayout;

import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.adapter.ExpandableListViewAdapter;
import com.silianchuangye.sumao.success.custom.CustomExpandableListView;
import com.silianchuangye.sumao.success.fragments.SellerManagementPlatform.CustomerApproval.CustomerApproval;
import com.silianchuangye.sumao.success.fragments.SellerManagementPlatform.SellerOrderManagement.OrderManagement;

/**
 * Created by Administrator on 2016/7/13 0013.
 */
public class SellerManagementPlatformActivity extends Activity implements View.OnClickListener{

    private CustomExpandableListView seller_expandable_listView;
    ExpandableListViewAdapter adapter;
    private RelativeLayout customer_management;
    private RelativeLayout customer_approval;
    private RelativeLayout order_management;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.seller_management_platform_activity);
        seller_expandable_listView = ((CustomExpandableListView) findViewById(R.id.seller_expandable_listView));
        customer_management = ((RelativeLayout) findViewById(R.id.customer_management));
        customer_approval = ((RelativeLayout) findViewById(R.id.customer_approval));
        order_management = ((RelativeLayout) findViewById(R.id.order_management));
        customer_management.setOnClickListener(this);
        customer_approval.setOnClickListener(this);
        order_management.setOnClickListener(this);
        adapter  = new ExpandableListViewAdapter(this,"seller");
        seller_expandable_listView.setAdapter(adapter);
        seller_expandable_listView.setGroupIndicator(null);
//        默认列表展开
        for(int i = 0; i < adapter.getGroupCount(); i++){

            seller_expandable_listView.expandGroup(i);

        }

        seller_expandable_listView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {

                return false;
            }
        });

        //为ExpandableListView的子列表单击事件设置监听器
        seller_expandable_listView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                if (adapter.getChild(groupPosition, childPosition).toString().equals("报价查询"))
                {

                }else if (adapter.getChild(groupPosition, childPosition).toString().equals("竞拍结果查看"))
                {

                }else if (adapter.getChild(groupPosition, childPosition).toString().equals("日需求查看"))
                {

                }else if (adapter.getChild(groupPosition, childPosition).toString().equals("月需求查看"))
                {

                }else if (adapter.getChild(groupPosition, childPosition).toString().equals("在线商品查询"))
                {

                }

                return true;
            }
        });
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId())
        {
//            客户管理
            case R.id.customer_management:

                break;
//            订单管理
            case R.id.order_management:
                intent.setClass(this,OrderManagement.class);
                break;
//            客户审批
            case R.id.customer_approval:
                intent.setClass(this,CustomerApproval.class);
                break;
            default:
                break;
        }
        startActivity(intent);
    }
}

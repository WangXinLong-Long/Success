package com.silianchuangye.sumao.success.fragments;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.silianchuangye.sumao.success.OrderGoodsActivity;
import com.silianchuangye.sumao.success.R;

import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.adapter.ExpandableListViewAdapter;
import com.silianchuangye.sumao.success.custom.CustomExpandableListView;
import com.silianchuangye.sumao.success.fragments.companyInfomation.CompanyUserActivity;
import com.silianchuangye.sumao.success.fragments.personalInformation.InformationSubscription;
import com.silianchuangye.sumao.success.fragments.personalInformation.PasswordUpdate;
import com.silianchuangye.sumao.success.fragments.personalInformation.UserInformation;

/**
 * Created by Administrator on 2016/4/20 0020.
 */
public class PagerFour extends BasePager {
    private CustomExpandableListView expandableListView;
    private ExpandableListAdapter sela;
    private Context context;
    private ImageView
            my_sumao_to_be_paid,
            my_sumao_to_send_the_goods,
            my_sumao_has_been_shipped,
            my_sumao_has_been_completed,
            my_sumao_has_been_cancelled,
            my_sumao_have_to_return;
    private ImageView my_sumao_ib_setting;
    View view;
    @Override
    public void myClickSearch() {

    }

    @Override
    public void initDate() {
        rl_title.setVisibility(View.GONE);
        view = View.inflate(mActivity,R.layout.fragment_four,null);
        fl_content.addView(view);

        context =mActivity;
        expandableListView = (CustomExpandableListView) view.findViewById(R.id.expandable_listView);
        sela = new ExpandableListViewAdapter(context);
        expandableListView.setAdapter(sela);
        expandableListView.setGroupIndicator(null);
        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {

                return false;
            }
        });
        //为ExpandableListView的子列表单击事件设置监听器
        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                if (sela.getChild(groupPosition, childPosition).toString().equals("现货订单"))
                {
                    Intent intent = new Intent();
                    intent.setClass(context,OrderGoodsActivity.class);
                    startActivity(intent);
                } else if (sela.getChild(groupPosition, childPosition).toString().equals("用户信息"))
                {
                    Intent intent = new Intent();
                    intent.setClass(context,UserInformation.class);
                    startActivity(intent);
                }else if (sela.getChild(groupPosition, childPosition).toString().equals("资讯订阅"))
                {
                    Intent intent = new Intent();
                    intent.setClass(context,InformationSubscription.class);
                    startActivity(intent);
                }else if (sela.getChild(groupPosition, childPosition).toString().equals("密码修改")){
                    Intent intent=new Intent(context, PasswordUpdate.class);
                    startActivity(intent);
                }else if (sela.getChild(groupPosition, childPosition).toString().equals("企业用户管理")){
                    Intent intent=new Intent(context, CompanyUserActivity.class);
                    startActivity(intent);
                }

                return true;
            }
        });

        my_sumao_to_be_paid = ((ImageView) view.findViewById(R.id.my_sumao_to_be_paid));
        my_sumao_to_send_the_goods = ((ImageView) view.findViewById(R.id.my_sumao_to_send_the_goods));
        my_sumao_has_been_shipped = ((ImageView) view.findViewById(R.id.my_sumao_has_been_shipped));
        my_sumao_has_been_completed = ((ImageView) view.findViewById(R.id.my_sumao_has_been_completed));
        my_sumao_has_been_cancelled = ((ImageView) view.findViewById(R.id.my_sumao_has_been_cancelled));
        my_sumao_have_to_return = ((ImageView) view.findViewById(R.id.my_sumao_have_to_return));
        my_sumao_ib_setting = ((ImageView) view.findViewById(R.id.my_sumao_ib_setting));

        my_sumao_to_be_paid.setOnClickListener(this);
        my_sumao_to_send_the_goods.setOnClickListener(this);
        my_sumao_has_been_shipped.setOnClickListener(this);
        my_sumao_has_been_completed.setOnClickListener(this);
        my_sumao_has_been_cancelled.setOnClickListener(this);
        my_sumao_have_to_return.setOnClickListener(this);
        my_sumao_ib_setting.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            /**
             * 以下6个我的订单的子项
             */
            case R.id.my_sumao_to_be_paid:
               /* Intent intent = new Intent();
                intent.setClass(context,TestActivity.class);
                startActivity(intent);*/
                break;
            case R.id.my_sumao_to_send_the_goods:

                break;
            case R.id.my_sumao_has_been_shipped:

                break;
            case R.id.my_sumao_has_been_completed:

                break;
            case R.id.my_sumao_has_been_cancelled:

                break;
            case R.id.my_sumao_have_to_return:

                break;

            /**
             * 点击设置按钮，右上角的齿轮图标
             */

            case R.id.my_sumao_ib_setting:

                break;
        }
    }

    @Override
    public void myClickLeft() {

    }

    @Override
    public void myClickRight() {

    }
}

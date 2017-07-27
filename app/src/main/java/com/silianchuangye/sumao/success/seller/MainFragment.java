package com.silianchuangye.sumao.success.seller;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.seller.fragmentone.account.AccountInfoActivity;
import com.silianchuangye.sumao.success.seller.fragmentone.account.firm.SellerFirmInfoActivity;
import com.silianchuangye.sumao.success.seller.fragmentone.account.grounding.DemandActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment implements View.OnClickListener {
    private TextView my_sumao_tv_name;//公司名称
    private TextView tvUsername;//用户名称
    private ImageView iv_firm_info;//企业信息
    private ImageView iv_user_info;//账户信息
    private ImageView xianhuo_icon;//现货
    private ImageView xuqiu_icon;//需求计划
    private RelativeLayout layout_shepi;//产品审批布局
    private TextView tv_shenpi_num;//产品审批的数值
    private ImageView iv_zaishou;//在售产品
    private ImageView iv_shenqing;//申请产品
    private ImageView iv_daishangjia;//待上架
    private ImageView iv_all_order;//全部订单
    private ImageView iv_plan_xuqiu;//需求计划
    private RelativeLayout layout_shenpi_forwait;//待审批布局
    private TextView tv_shenpi_order;//待审批
    private RelativeLayout layout_chuli_wait;//待处理布局
    private TextView tv_chuli_number;//待处理的数量
    private RelativeLayout layout_yizhifu;//已支付的布局
    private TextView tv_zhifu_number;//已支付的数量
    private RelativeLayout shenpi_layout;//审批的布局
    private TextView tv_shenpi2_number;//审批的数量
    private ImageView iv_kehu_manager;//客户管理
    private ImageView add_customer;//新增管理















    public MainFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_main, container, false);
        init(view);
        return view;
    }
    public void init(View view){
        iv_all_order= (ImageView) view.findViewById(R.id.iv_all_order);
        iv_all_order.setOnClickListener(this);
        iv_plan_xuqiu= (ImageView) view.findViewById(R.id.iv_plan_xuqiu);
        iv_plan_xuqiu.setOnClickListener(this);
        layout_shenpi_forwait= (RelativeLayout) view.findViewById(R.id.layout_shenpi_forwait);
        layout_shenpi_forwait.setOnClickListener(this);
        tv_shenpi_order= (TextView) view.findViewById(R.id.tv_shenpi_order);
        layout_chuli_wait= (RelativeLayout) view.findViewById(R.id.layout_chuli_wait);
        layout_chuli_wait.setOnClickListener(this);
        tv_chuli_number= (TextView) view.findViewById(R.id.tv_chuli_number);
        layout_yizhifu= (RelativeLayout) view.findViewById(R.id.layout_yizhifu);
        layout_yizhifu.setOnClickListener(this);
        tv_zhifu_number= (TextView) view.findViewById(R.id.tv_zhifu_number);
        shenpi_layout= (RelativeLayout) view.findViewById(R.id.shenpi_layout);
        shenpi_layout.setOnClickListener(this);
        tv_shenpi2_number= (TextView) view.findViewById(R.id.tv_shenpi2_number);
        my_sumao_tv_name= (TextView) view.findViewById(R.id.my_sumao_tv_name);
        tvUsername= (TextView) view.findViewById(R.id.tvUsername);
        iv_firm_info= (ImageView) view.findViewById(R.id.iv_firm_info);
        iv_user_info= (ImageView) view.findViewById(R.id.iv_user_info);
        iv_firm_info.setOnClickListener(this);
        iv_user_info.setOnClickListener(this);
        xianhuo_icon= (ImageView) view.findViewById(R.id.xianhuo_icon);
        xuqiu_icon= (ImageView) view.findViewById(R.id.xuqiu_icon);
        iv_zaishou= (ImageView) view.findViewById(R.id.iv_zaishou);
        iv_shenqing= (ImageView) view.findViewById(R.id.iv_shenqing);
        iv_daishangjia= (ImageView) view.findViewById(R.id.iv_daishangjia);
        xianhuo_icon.setOnClickListener(this);
        xuqiu_icon.setOnClickListener(this);
        iv_zaishou.setOnClickListener(this);
        iv_shenqing.setOnClickListener(this);
        iv_daishangjia.setOnClickListener(this);
        layout_shepi= (RelativeLayout) view.findViewById(R.id.layout_shepi);
        layout_shepi.setOnClickListener(this);
        tv_shenpi_num= (TextView) view.findViewById(R.id.tv_shenpi_num);
        iv_kehu_manager= (ImageView) view.findViewById(R.id.iv_kehu_manager);
        iv_kehu_manager.setOnClickListener(this);
        add_customer= (ImageView) view.findViewById(R.id.add_customer);
        add_customer.setOnClickListener(this);




    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_firm_info:
              //  企业信息
                Intent intent_firm=new Intent(getActivity(), SellerFirmInfoActivity.class);
                startActivity(intent_firm);
                break;
            case R.id.layout_yizhifu:
                break;
            case R.id.shenpi_layout:
                break;
            case R.id.iv_kehu_manager:
                break;
            case R.id.add_customer:
                break;
            case R.id.layout_chuli_wait:
                break;
            case R.id.layout_shenpi_forwait:
                break;
            case R.id.iv_zaishou:
                break;
            case R.id.iv_shenqing:
                break;
            case R.id.iv_daishangjia:
                break;
            case R.id.iv_all_order:
                break;
            case R.id.iv_plan_xuqiu:
                break;
            case R.id.iv_user_info:
                //账户信息
                Intent intent=new Intent(getActivity(), AccountInfoActivity.class);
                startActivity(intent);
                break;
            case R.id.xianhuo_icon:
                break;
            case R.id.xuqiu_icon:
                //上架需求计划
                Intent intent2=new Intent(getActivity(), DemandActivity.class);
                startActivity(intent2);
                break;
            case R.id.layout_shepi:
                break;


        }

    }
}

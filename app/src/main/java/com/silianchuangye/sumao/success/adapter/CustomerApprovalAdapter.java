package com.silianchuangye.sumao.success.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.model.CustomerApprovalModel;

import java.util.List;

/**
 * Created by Administrator on 2016/7/14 0014.
 */
public class CustomerApprovalAdapter  extends BaseAdapter {
    private List<CustomerApprovalModel> mLists;
    private Context context;
    private LayoutInflater inflater;
    public CustomerApprovalAdapter(Context context, List<CustomerApprovalModel> lists) {
        this.context = context;
        this.mLists = lists;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return mLists.size();
    }

    @Override
    public Object getItem(int position) {
        return mLists.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null)
        {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.customer_approval_item,null);
            holder.title = ((TextView) convertView.findViewById(R.id.title));
            holder.state = ((TextView) convertView.findViewById(R.id.state));
            holder.number = ((TextView) convertView.findViewById(R.id.number2));
            holder.typeOfEnterprise = ((TextView) convertView.findViewById(R.id.typeOfEnterprise2));
            holder.customerStatus = ((TextView) convertView.findViewById(R.id.customerStatus2));
            holder.salesman = ((TextView) convertView.findViewById(R.id.salesman2));
            holder.enterpriseName = ((TextView) convertView.findViewById(R.id.enterpriseName2));
            holder.officeAddress = ((TextView) convertView.findViewById(R.id.officeAddress2));
            holder.purchasingQualification = ((TextView) convertView.findViewById(R.id.purchasingQualification2));
            holder.notApprovedReasons = ((TextView) convertView.findViewById(R.id.notApprovedReasons2));
            holder.notApprovedReasonsRL = ((RelativeLayout) convertView.findViewById(R.id.notApprovedReasons));
            convertView.setTag(holder);
        }else {
            holder = ((ViewHolder) convertView.getTag());
        }
        holder.title.setText(mLists.get(position).getTitle());
        holder.state.setText(mLists.get(position).getState());
        holder.number.setText(mLists.get(position).getNumber());
        holder.typeOfEnterprise.setText(mLists.get(position).getTypeOfEnterprise());
        holder.customerStatus.setText(mLists.get(position).getCustomerStatus());
        holder.salesman.setText(mLists.get(position).getSalesman());
        holder.enterpriseName.setText(mLists.get(position).getEnterpriseName());
        holder.officeAddress.setText(mLists.get(position).getOfficeAddress());
        //    购买资质
        holder.purchasingQualification.setText(mLists.get(position).getPurchasingQualification());
        if ("未通过".equals(mLists.get(position).getState()))
        {
            holder.notApprovedReasonsRL.setVisibility(View.VISIBLE);
            //    未通过审批原因
            holder.notApprovedReasons.setText(mLists.get(position).getNotApprovedReasons());
            holder.state.setTextColor(Color.RED);
        }
        return convertView;
    }
    class ViewHolder{
        //    标题
        private TextView title;
        //    审核状态
        private TextView state;
        //    企业编号
        private TextView number;
        //    企业类型
        private TextView typeOfEnterprise;
        //    客户状态
        private TextView customerStatus;
        //    业务员
        private TextView salesman;
        //    企业名称
        private TextView enterpriseName;
        //    办公地址
        private TextView officeAddress;
        //    购买资质
        private TextView purchasingQualification;
        //    未通过审批原因
        private TextView notApprovedReasons;
//          控制未通过原因的显示与影藏
        private RelativeLayout notApprovedReasonsRL;

    }
}

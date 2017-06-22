package com.silianchuangye.sumao.success.fragments.myPlasticTrade.messagePush.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.messagePush.bean.MessagePush;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.messagePush.view.MessageDetailsActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by junjun on 2017/6/21.
 */
public class SellerMessageAdapter extends BaseAdapter {
    private Context context;
    private List<Map<String,Object>> list;

    public SellerMessageAdapter(Context context) {
        this.context = context;
      //  this.list = list;
        list=new ArrayList<>();
    }

    public void addData(List<Map<String,Object>> list) {
        this.list.addAll(list);
    }

    public void clearData() {
        this.list.clear();
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        convertView=View.inflate(context, R.layout.item_message_push,null);
        TextView tv_Number_value= (TextView) convertView.findViewById(R.id.tv_Number_value);//通知编号
        TextView tv_Type= (TextView) convertView.findViewById(R.id.tv_Type);//通知类型
        TextView tv_Title= (TextView) convertView.findViewById(R.id.tv_Title); //通知标题
        RelativeLayout layout_More= (RelativeLayout) convertView.findViewById(R.id.layout_More);

        tv_Number_value.setText(list.get(position).get("id").toString());
        tv_Type.setText(list.get(position).get("orgName").toString());
        tv_Title.setText(list.get(position).get("title").toString());
        layout_More.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //界面跳转
                Intent intent=new Intent(context, MessageDetailsActivity.class);
                intent.putExtra("sendId",list.get(position).get("id").toString());
                intent.putExtra("resoure","卖家通知");
                context.startActivity(intent);
            }
        });

        return convertView;
    }
}

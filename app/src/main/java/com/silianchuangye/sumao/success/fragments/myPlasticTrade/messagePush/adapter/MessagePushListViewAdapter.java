package com.silianchuangye.sumao.success.fragments.myPlasticTrade.messagePush.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.fragments.homepage.groupbuying.GroupBuyingMVP.bean.GroupBuyingListBean;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.messagePush.bean.MessagePush;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by junjun on 2017/3/11.
 */
public class MessagePushListViewAdapter extends BaseAdapter {
    private Context context;
    private List<MessagePush.Message> list;

    public MessagePushListViewAdapter(Context context,List<MessagePush.Message> list){
        this.context=context;
     //   list=new ArrayList<>();
        this.list=list;

    }
    public void addData(List<MessagePush.Message> data) {
        list.addAll(data);
    }

    public void clearData() {
        list.clear();
    }


    @Override
    public int getCount() {
        Log.d("list的长度getCount()",list.size()+"");
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        Log.d("list的position","list"+position);
        return position;
    }

    @Override
    public long getItemId(int position) {
        Log.d("list的item","list"+position);
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Log.d("进来getView方法","进来getView方法");
        //View view=View.inflate(context, R.layout.item_message_push,null);
        View view= LayoutInflater.from(context).inflate(R.layout.item_message_push,null);
        TextView tv_Number= (TextView) view.findViewById(R.id.tv_Number_value);
        TextView tv_Type= (TextView) view.findViewById(R.id.tv_Type);
        TextView tv_Title= (TextView) view.findViewById(R.id.tv_title);
        RelativeLayout layout_More= (RelativeLayout) view.findViewById(R.id.layout_More);
        tv_Number.setText(list.get(position).getMessageId());
        Log.d("编号",tv_Number.getText().toString()+"编号");

        tv_Type.setText(list.get(position).getMessageType());
        tv_Title.setText(list.get(position).getMessageTitle());


        layout_More.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转界面
            }
        });
        return view;
    }
}

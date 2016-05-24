package com.silianchuangye.sumao.success.adapter;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.baidu.mapapi.map.Text;
import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.fragments.bean.CartInfo;
import com.silianchuangye.sumao.success.fragments.dialog.Cart_MyDialog;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 2016/5/20.
 */
public class CartAdapter extends BaseAdapter{
private List<CartInfo> list;
    private Context ctx;
    private Cart_MyDialog dialog;
    private SelectCallBack call;
    private SelectCallBack MyReciverCall;
    private MyReciver my;
    public CartAdapter(Context ctx,List<CartInfo>list,SelectCallBack call,SelectCallBack MyReciverCall){
        this.ctx=ctx;
        this.list=list;
        this.call=call;
        this.MyReciverCall=MyReciverCall;
        my=new MyReciver();
        IntentFilter intent=new IntentFilter();
        intent.addAction("close_cart_dialog");
        ctx.registerReceiver(my,intent);
        dialog=new Cart_MyDialog(ctx);
    }

    public class MyReciver extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent) {
            dialog.dismiss();
            MyReciverCall.MyReciverCall(my);
        }
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        final ViewHolder holder;
        if(convertView==null){
            convertView=View.inflate(ctx, R.layout.item_cart_activity,null);
            holder=new ViewHolder();
            holder.Tv_all_price= (TextView) convertView.findViewById(R.id.tv_item_cart_all_price);
            holder.Tv_buy_num= (TextView) convertView.findViewById(R.id.tv_item_cart_buy_num);
            holder.Tv_cangku_name= (TextView) convertView.findViewById(R.id.tv_item_cart_cangku_name);
            holder.Tv_company= (TextView) convertView.findViewById(R.id.tv_item_cart_cangku_conmpany_name);
            holder.Tv_name= (TextView) convertView.findViewById(R.id.tv_item_cart_name);
            holder.Tv_pai_num= (TextView) convertView.findViewById(R.id.tv_item_cart_paihao_num);
            holder.Tv_price= (TextView) convertView.findViewById(R.id.tv_item_cart_cangku_danjia_price);
            holder.Tv_qiye= (TextView) convertView.findViewById(R.id.tv_item_cart_shengchan_name);
            holder.Tv_sort_name= (TextView) convertView.findViewById(R.id.tv_item_cart_pinzhong_name);
            holder.Img_select= (ImageView) convertView.findViewById(R.id.img_item_cart_select);
            holder.Img_del= (ImageView) convertView.findViewById(R.id.img_item_cart_del);
            holder.Img_sub= (ImageView) convertView.findViewById(R.id.img_item_cart_buy_sub);
            holder.Img_Add= (ImageView) convertView.findViewById(R.id.img_item_cart_buy_add);
            convertView.setTag(holder);
        }else{
           holder= (ViewHolder) convertView.getTag();
        }
        holder.Tv_name.setText(list.get(position).name);
        holder.Tv_sort_name.setText(list.get(position).sort_name);
        holder.Tv_cangku_name.setText(list.get(position).cangku_name);
        holder.Tv_pai_num.setText(list.get(position).pai_num);
        holder.Tv_price.setText(list.get(position).price);
        holder.Tv_qiye.setText(list.get(position).qiye);
        holder.Tv_company.setText(list.get(position).company);
        holder.Tv_buy_num.setText(list.get(position).buy_num);
        holder.Tv_all_price.setText(list.get(position).all_price+"元");
        //选中与不选中
        if(list.get(position).Selsct_Flag){
            holder.Img_select.setImageResource(R.mipmap.adwords);
        }else {
            holder.Img_select.setImageResource(R.mipmap.ic_launcher);
        }
        holder.Img_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                call.Call(position);}
        });
        //删除按钮
        holder.Img_del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.remove(list.get(position));
                notifyDataSetChanged();
            }
        });
        //增加或减少购买数
        holder.Img_sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str=holder.Tv_buy_num.getText().toString();
                int num=Integer.valueOf(str);
                num--;
                holder.Tv_buy_num.setText(""+num);
            }
        });
        holder.Img_Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str=holder.Tv_buy_num.getText().toString();
                int num=Integer.valueOf(str);
                num++;
                if(num>=15){
                    dialog.show();
                }
                if(num<15) {
                    holder.Tv_buy_num.setText("" + num);
                }
            }
        });
        return convertView;
    }
    public class ViewHolder{
        TextView Tv_name;
        TextView Tv_sort_name;
        TextView Tv_cangku_name;
        TextView Tv_pai_num;
        TextView Tv_price;
        TextView Tv_qiye;
        TextView Tv_company;
        TextView Tv_buy_num;
        TextView Tv_all_price;
        ImageView Img_select,Img_del;
        ImageView Img_sub,Img_Add;
    }
    public interface SelectCallBack{
        public void Call(int index);
        public void MyReciverCall(MyReciver myReciver);
    }
}

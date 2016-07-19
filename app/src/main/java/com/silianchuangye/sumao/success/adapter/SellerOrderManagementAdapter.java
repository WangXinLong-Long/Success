package com.silianchuangye.sumao.success.adapter;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.text.InputFilter;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.model.SellerOrderanagementModel;

import java.util.List;

/**
 * Created by Administrator on 2016/7/15 0015.
 */
public class SellerOrderManagementAdapter extends BaseAdapter {
    private Context context;
    private  List<SellerOrderanagementModel> lists;
    LayoutInflater inflater;
    ViewHolder holder;

    public SellerOrderManagementAdapter(Context context, List<SellerOrderanagementModel> lists) {
        this.context = context;
        this.lists =lists;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return lists.size();
    }

    @Override
    public Object getItem(int position) {
        return lists.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
//        ViewHolder holder;
        if (convertView == null)
        {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.seller_order_management_item,null);
            holder.orderNumber = ((TextView) convertView.findViewById(R.id.seller_order_managemnet_orderNumber2));
            holder.orderType = ((TextView) convertView.findViewById(R.id.seller_order_managemnet_orderType2));
            holder.totalOrder = ((TextView) convertView.findViewById(R.id.seller_order_managemnet_totalOrder2));
            holder.nameOfBuyerEnterprise = ((TextView) convertView.findViewById(R.id.seller_order_managemnet_nameOfBuyerEnterprise2));
            holder.placeAnOrderPerson = ((TextView) convertView.findViewById(R.id.seller_order_managemnet_placeAnOrderPerson2));
            holder.orderDate = ((TextView) convertView.findViewById(R.id.seller_order_managemnet_orderDate2));
            holder.orderStatus = ((TextView) convertView.findViewById(R.id.seller_order_managemnet_orderStatus2));
            holder.salesman = ((TextView) convertView.findViewById(R.id.seller_order_managemnet_salesman2));
            convertView.setTag(holder);
        }else{
            holder = ((ViewHolder) convertView.getTag());
        }
        holder.orderNumber.setText(lists.get(position).getOrderNumber());
        holder.orderType.setText(lists.get(position).getOrderType());
        holder.totalOrder.setText(lists.get(position).getTotalOrder());
        holder.nameOfBuyerEnterprise.setText(lists.get(position).getNameOfBuyerEnterprise());
        holder.placeAnOrderPerson.setText(lists.get(position).getPlaceAnOrderPerson());
        holder.orderDate.setText(lists.get(position).getOrderDate());
        holder.orderStatus.setText(lists.get(position).getOrderStatus());
        holder.salesman.setText(lists.get(position).getSalesman());
       /* holder.orderNumber.setFilters(new InputFilter[] { new InputFilter() {
            public CharSequence filter(CharSequence source, int start, int end,
                                       Spanned dest, int dstart, int dend) {
                return source.length() < 1 ? dest.subSequence(dstart, dend)
                        : "";
            }
        } });*/
       /* holder.orderNumber.setOnLongClickListener(new View.OnLongClickListener() {

             @Override
             public boolean onLongClick(View arg0) {

                 String string = holder.orderNumber.getText().toString();
                 copy(string, context);
                 Toast.makeText(context, "文本已复制到粘贴板", Toast.LENGTH_SHORT)
                         .show();
             return false;
             }
         });*/
        return convertView;
    }
    class ViewHolder{
        //    订单号
        private TextView orderNumber;
        //    订单类型
        private TextView orderType;
        //    订单总额
        private TextView totalOrder;
        //    买方企业名称
        private TextView nameOfBuyerEnterprise;
        //    下单人
        private TextView placeAnOrderPerson;
        //    下单日期
        private TextView orderDate;
        //    订单状态
        private TextView orderStatus;
        //    业务员
        private TextView salesman;

    }
    /*public Dialog test() {

        Dialog dialog = new AlertDialog.Builder(context)
                .setNegativeButton("取消", null)
                .setItems(new String[] { "复制" }, new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        String string = holder.orderNumber.getText().toString();
                        copy(string, context);
                        Toast.makeText(context, "文本已复制到粘贴板", Toast.LENGTH_SHORT)
                                .show();
                    }
                }).create();
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
        return dialog;

    }*/
    public static void copy(String content, Context context) {
        // 得到剪贴板管理器
        ClipboardManager cmb = (ClipboardManager) context
                .getSystemService(Context.CLIPBOARD_SERVICE);
        cmb.setText(content.trim());
    }
}

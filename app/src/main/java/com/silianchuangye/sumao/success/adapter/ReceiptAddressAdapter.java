package com.silianchuangye.sumao.success.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.addressDisplayMVP.view.IAddressDisplayView;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.receiptAddress.ReceiptAddressMVP.bean.ReAddress;
import com.silianchuangye.sumao.success.utils.LogUtils;

import java.util.List;


/**
 * Created by Administrator on 2016/5/10 0010.
 */
public class ReceiptAddressAdapter extends BaseAdapter {

    Context context;
    List<ReAddress> lists;
    LayoutInflater inflater;
    private String[] addressDisplay ;
    private List<String > display;

    public ReceiptAddressAdapter(Context context, List<ReAddress> lists,String[] addressDisplay/* List<String> display*/) {
        this.context = context;
        this.lists = lists;
        inflater = LayoutInflater.from(context);
        this.addressDisplay = addressDisplay;
//        this.display =display;
    }

    @Override
    public int getCount() {
        if (lists.size() != 0 && lists != null) {
            return lists.size();
        }
        return 0;
    }

    @Override
    public ReAddress getItem(int position) {
        return lists.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.receipt_address_item, null);
            holder.consignee2 = ((TextView) convertView.findViewById(R.id.consignee2));
            holder.Default_receiving_address = ((TextView) convertView.findViewById(R.id.Default_receiving_address));
            holder.Zip_code_num = ((TextView) convertView.findViewById(R.id.Zip_code_num));
            holder.address = ((TextView) convertView.findViewById(R.id.address));
            holder.telephone = ((TextView) convertView.findViewById(R.id.phone_number_num));
            holder.fixed_telephone_num = ((TextView) convertView.findViewById(R.id.fixed_telephone_num));
            holder.fixed_telephone_tv = ((TextView) convertView.findViewById(R.id.fixed_telephone_tv));
            convertView.setTag(holder);
        } else {
            holder = ((ViewHolder) convertView.getTag());
        }
        if (lists.get(position).getAddressType().equals("1")) {
            holder.Default_receiving_address.setText("默认收货地址");
            holder.Default_receiving_address.setTextColor(context.getResources().getColor(R.color.zixun_topbg));
        } else {
            holder.Default_receiving_address.setText("");
        }
        holder.consignee2.setText(lists.get(position).getName());
        holder.Zip_code_num.setText(lists.get(position).getZipCode());
//        LogUtils.log("addressDisplay.get(position)--->Adapter"+addressDisplay.size());
        holder.address.setText(addressDisplay[position]/*display.get(position)*/+lists.get(position).getAddress());
        holder.telephone.setText(lists.get(position).getMobile());
        if (lists.get(position).getPhone() == null || lists.get(position).getPhone().equals("")) {
            holder.fixed_telephone_tv.setVisibility(View.INVISIBLE);
//            holder.fixed_telephone_num.setText("");
        } else {
            holder.fixed_telephone_num.setText(lists.get(position).getPhone());
            holder.fixed_telephone_tv.setVisibility(View.VISIBLE);
        }

        LogUtils.log("ReceiptAddress--->小蚂蚁 adapter  getView----->" );
        return convertView;
    }


    class ViewHolder {
        TextView consignee2;
        TextView Default_receiving_address;
        TextView Zip_code_num;
        TextView address;
        TextView telephone;
        TextView fixed_telephone_num;
        TextView fixed_telephone_tv;
    }

}

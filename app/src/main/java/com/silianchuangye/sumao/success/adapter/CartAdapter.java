package com.silianchuangye.sumao.success.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.fragments.bean.CartInfo;
import com.silianchuangye.sumao.success.fragments.shoppingCart.dialog.Cart_MyDialog;
import com.silianchuangye.sumao.success.utils.SuMaoConstant;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.List;

/**
 * Created by Administrator on 2016/5/20.
 * 购物车列表适配器
 */
public class CartAdapter extends BaseAdapter{
private List<CartInfo> list;
    private Context ctx;
    private Cart_MyDialog dialog;
    private SelectCallBack call;
    private String temp;
    private String name_chanpin;
    private List<String> kucong;
    private List<String> list_id;
    private String id;
    private String kucong_value;
    private String price;
    private String all_price;
    private int number;
    public CartAdapter(Context ctx,List<CartInfo>list,SelectCallBack call,Cart_MyDialog dialog,List<String> list_id,List<String> kucong){
        this.ctx=ctx;
        this.list=list;
        this.call=call;
        this.dialog=dialog;
        this.list_id=list_id;
        this.kucong=kucong;
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
            holder.Edt_buy_num= (EditText) convertView.findViewById(R.id.tv_item_cart_buy_num);
            holder.Tv_cangku_name= (TextView) convertView.findViewById(R.id.tv_item_cart_cangku_name);
            holder.Tv_company= (TextView) convertView.findViewById(R.id.tv_item_cart_cangku_conmpany_name);
            holder.Tv_name= (TextView) convertView.findViewById(R.id.tv_item_cart_name);
            holder.Tv_pai_num= (TextView) convertView.findViewById(R.id.tv_item_cart_paihao_num);
            holder.Tv_price= (TextView) convertView.findViewById(R.id.tv_item_cart_cangku_danjia_price);
            holder.Tv_qiye= (TextView) convertView.findViewById(R.id.tv_item_cart_shengchan_name);
            holder.Tv_sort_name= (TextView) convertView.findViewById(R.id.tv_item_cart_pinzhong_name);
            holder.Img_select= (ImageView) convertView.findViewById(R.id.img_item_cart_select);
            holder.Img_del= (ImageView) convertView.findViewById(R.id.img_item_cart_del);
            holder.Tv_buy_Sub= (TextView) convertView.findViewById(R.id.img_item_cart_buy_sub);
            holder.Tv_buy_Add= (TextView) convertView.findViewById(R.id.img_item_cart_buy_add);
            holder.Tv_no_much= (TextView) convertView.findViewById(R.id.item_cart_tv_no_much);
            holder.relative_item_cart= (RelativeLayout) convertView.findViewById(R.id.relative_item_cart);
            convertView.setTag(holder);
        }else{
           holder= (ViewHolder) convertView.getTag();
        }
        id=list_id.get(position).toString();
        kucong_value=kucong.get(position).toString();
        Log.d("库存的值",kucong_value);
        holder.Tv_name.setText(list.get(position).name);
        holder.Tv_sort_name.setText(list.get(position).sort_name);
        holder.Tv_cangku_name.setText(list.get(position).cangku_name);
        holder.Tv_pai_num.setText(list.get(position).pai_num);
        holder.Tv_price.setText(list.get(position).price);
        holder.Tv_qiye.setText(list.get(position).qiye);
        holder.Tv_company.setText(list.get(position).company);
        holder.Edt_buy_num.setText(list.get(position).buy_num);
        Log.d("选择的值",list.get(position).buy_num);
        holder.Tv_all_price.setText(list.get(position).all_price+"元");
        //选中与不选中
        if(list.get(position).Selsct_Flag){
            holder.Img_select.setImageResource(R.mipmap.cart_select);
        }else {
            holder.Img_select.setImageResource(R.mipmap.cart_select_null);
        }
        holder.Img_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                call.Call(position);

            }
        });
        //删除按钮
//        holder.Img_del.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                new Thread(){
//                    @Override
//                    public void run() {
//                        //super.run();
//                        delete_data(position);
//                        //notifyDataSetChanged();
//                    }
//                }.start();
//            }
//        });

        //增加或减少购买数
        holder.Tv_buy_Sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str=holder.Edt_buy_num.getText().toString();
                if(str.equals("")){
                    str="0";
                }
                int num=Integer.valueOf(str);
                num--;
                if(num>=0) {
                    holder.Edt_buy_num.setText("" + num);
                    number=num;
                    holder.relative_item_cart.setVisibility(View.GONE);
                   // update_data(number);
//                    holder.Tv_price.setText(price);
//                    holder.Tv_all_price.setText(all_price);
                    //notifyDataSetChanged();
                }
                if(num==0){
                    Toast.makeText(ctx,"购买数量不能为零",Toast.LENGTH_SHORT).show();
                }
            }
        });
//        holder.Tv_buy_Add.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String str=holder.Edt_buy_num.getText().toString();
//                if(str.equals("")){
//                    str="0";
//                }
//                int num=Integer.valueOf(str);
//                num++;
//                Log.d("库存的值",kucong.get(position));
//              //  Log.d("库存的数字",Integer.parseInt(kucong).get+"");
//                if(num>=new Double(kucong_value)){
//                    holder.relative_item_cart.setVisibility(View.VISIBLE);
//                    holder.Tv_no_much.setText("产品库存不足"+num+"吨");
//                    dialog.show();
//                }
//                if(num<new Double(kucong_value)&&num>=0) {
//                    holder.relative_item_cart.setVisibility(View.GONE);
//                    holder.Edt_buy_num.setText("" + num);
//                    number=num;
////                   String number=num+"";
////                    update_data(number);
////                    holder.Tv_price.setText(price);
////                    holder.Tv_all_price.setText(all_price);
//                    //notifyDataSetChanged();
//                }
//            }
//        });
//        holder.Edt_buy_num.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//                temp=holder.Edt_buy_num.getText().toString();
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                temp=holder.Edt_buy_num.getText().toString();
//                if(temp.equals("")){
//                    Toast.makeText(ctx, "购买数量不能为空", Toast.LENGTH_SHORT).show();
//                }
//                //else{
////                    update_data();
////                    holder.Tv_price.setText(price);
////                    holder.Tv_all_price.setText(all_price);
////                    //notifyDataSetChanged();
////                }
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//               temp=holder.Edt_buy_num.getText().toString();
//                if(temp.equals("")){
//                    temp="0";
////                    holder.Edt_buy_num.setText(str);
//                    Toast.makeText(ctx,"购买数量不能为零",Toast.LENGTH_SHORT).show();
//                }
//                int i=Integer.valueOf(temp);
////                Log.d("edit的值", new Double(kucong_value)+"");
//                if(i>= new Double(kucong_value)) {
//                    holder.relative_item_cart.setVisibility(View.VISIBLE);
//                    holder.Tv_no_much.setText("产品库存不足" + i + "吨");
//                    dialog.show();
//                }else{
//
//                    holder.relative_item_cart.setVisibility(View.GONE);
//                    update_data();
//                    holder.Tv_price.setText(price);
//                    holder.Tv_all_price.setText(all_price);
//                }
//
//           }
//        });
        return convertView;
    }
    public void delete_data(final int position){
        String url= SuMaoConstant.SUMAO_IP+"rest/model/atg/commerce/ShoppingCartActor/removeItemFromOrder";
        final RequestParams rp=new RequestParams(url);
        SharedPreferences sp=ctx.getSharedPreferences("sumao", Activity.MODE_PRIVATE);
        String unique_delete=sp.getString("unique","");
        Log.d("购物车删除时的唯一标识",unique_delete);
        rp.addParameter("_dynSessConf",unique_delete);
        rp.addParameter("commerceId",id);
        Log.d("商品id",id);
        Log.d("rp的值",rp +"");
        x.http().post(rp, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {

                Log.d("删除时的result",result);
                if (result.contains("commerceItem")){
                    list.remove(list.get(position));
                    Toast.makeText(ctx, "删除成功", Toast.LENGTH_SHORT).show();
                    notifyDataSetChanged();

                }else{
                    try {
                        JSONObject obj_result = new JSONObject(result);
                        String message=obj_result.getString("formExceptions");
                        JSONArray array=new JSONArray(message);
                        JSONObject obj_array=array.getJSONObject(0);
                        String info=obj_array.getString("localizedMessage");
                        Toast.makeText(ctx, ""+info, Toast.LENGTH_SHORT).show();
                    }catch (Exception  e){
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }
    public void update_data(){
        String url=SuMaoConstant.SUMAO_IP+"rest/model/atg/commerce/ShoppingCartActor/shoppingCartDetail";
        RequestParams rp=new RequestParams(url);
        rp.addParameter("amountUnitScale","1000");
        rp.addParameter("qty","3");
      ///  Log.d("当前选择数量",number);
        Log.d("number的值",""+number);
        rp.addParameter("commerceId",id);
        SharedPreferences sp=ctx.getSharedPreferences("sumao",Activity.MODE_PRIVATE);
        String unique_update=sp.getString("unique","");
        Log.d("修改时的唯一标识",unique_update);
        rp.addParameter("_dynSessConf",unique_update);
        Log.d("rp的值",rp+"");
        x.http().post(rp, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.d("修改时的result",result);
                if (result.contains("commerceItem")){
                    try{
                        JSONObject obj_result=new JSONObject(result);
                        String message=obj_result.getString("commerceItem");
                        JSONArray array=new JSONArray(message);
                        JSONObject onj_array=array.getJSONObject(0);
                         price=onj_array.getString("salePrice");
                        Log.d("单价",price);
                         all_price=onj_array.getString("amount");
                        Log.d("总价",all_price);
                       // notifyDataSetChanged();

                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    public class ViewHolder{
        TextView Tv_name;
        TextView Tv_sort_name;
        TextView Tv_cangku_name;
        TextView Tv_pai_num;
        TextView Tv_price;
        TextView Tv_qiye;
        TextView Tv_company;
        EditText Edt_buy_num;
        TextView Tv_buy_Sub,Tv_buy_Add;
        TextView Tv_all_price,Tv_no_much;
        ImageView Img_select,Img_del;
        RelativeLayout relative_item_cart;
    }

    public interface SelectCallBack{
        public void Call(int index);
        public void CartNull(List<CartInfo>list);
    }

}

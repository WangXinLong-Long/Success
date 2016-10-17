package com.silianchuangye.sumao.success.fragments;


import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.silianchuangye.sumao.success.MainActivity;
import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.adapter.CartAdapter;
import com.silianchuangye.sumao.success.adapter.CartItemAdapter;
import com.silianchuangye.sumao.success.custom.CustomListView;
import com.silianchuangye.sumao.success.fragments.bean.CartInfo;
import com.silianchuangye.sumao.success.fragments.bean.CartItemInfo;
import com.silianchuangye.sumao.success.fragments.homepage.goodInStock.PaymentsOrder;
import com.silianchuangye.sumao.success.fragments.homepage.theprice.MidpointsListctivity;
import com.silianchuangye.sumao.success.fragments.shoppingCart.dialog.Cart_MyDialog;
import com.silianchuangye.sumao.success.fragments.type.SelectCallBack;
import com.silianchuangye.sumao.success.utils.Loding;
import com.silianchuangye.sumao.success.utils.SuMaoConstant;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadFactory;

/**
 * Created by Administrator on 2016/4/20 0020.
 * 购物车界面
 */
public class PagerThree extends BasePager implements AdapterView.OnItemClickListener,SelectCallBack{
    private CustomListView lv_Cart;
    private CartAdapter adapter;
    private List<CartInfo> list;
    private ImageView img_Cart_All_Select;
    private TextView tv_Cart_All_Price;
    private Button btn_Cart_Ok;
    private boolean all_Flag;
    private float all_Price;
    private Context ctx;
    private MyReciver my;
    private Cart_MyDialog dialog;
    private RelativeLayout relative_cart_null, relative_cart_tishi, relative_activity_cart_bottom;
    private List<Integer> imgList = new ArrayList<Integer>();
    private List list1 = new ArrayList();
    private List<String> list_id = new ArrayList<String>();
    //  private List<String> name=new ArrayList<String>();
    private List<String> kucong = new ArrayList<String>();
    private String name;
    private String ku_cong_number;
    private Context context;
    private List<String> id_String=new ArrayList<String>();

    public class MyReciver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
//            if (intent.getAction().equals("close_cart_dialog")) {
//                dialog.dismiss();
//            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getActivity().unregisterReceiver(my);
    }

    @Override
    public void myClickSearch() {

    }

    @Override
    public void initDate() {
        initView();
    }
    private void initView() {
        //initList();
        ctx = getActivity();

        my = new MyReciver();
        IntentFilter intent = new IntentFilter();
        intent.addAction("close_cart_dialog");
        getActivity().registerReceiver(my, intent);
        rl_title.setVisibility(View.GONE);
        View v = View.inflate(getActivity(), R.layout.activity_cart, null);
        fl_content.addView(v);
        relative_cart_null = (RelativeLayout) v.findViewById(R.id.relative_cart_null);
        relative_cart_tishi = (RelativeLayout) v.findViewById(R.id.relative_cart_tishi);
        relative_activity_cart_bottom = (RelativeLayout) v.findViewById(R.id.relative_activity_cart_bottom);
        lv_Cart = (CustomListView) v.findViewById(R.id.lv_activity_cart);
        btn_Cart_Ok = (Button) v.findViewById(R.id.btn_activity_cart_ok);
        img_Cart_All_Select = (ImageView) v.findViewById(R.id.img_activity_cart_allselect);
        tv_Cart_All_Price = (TextView) v.findViewById(R.id.tv_activity_cart_all_price);
//        adapter = new CartAdapter(getActivity(),list,this,dialog,list_id,ku_cong_number);
//       //adapter=new CartAdapter(getActivity(),list,list_id,ku_cong_number));
//        lv_Cart.setAdapter(adapter);


        lv_Cart.setOnItemClickListener(this);
        img_Cart_All_Select.setOnClickListener(this);
        btn_Cart_Ok.setOnClickListener(this);

        pop_view = View.inflate(getActivity(), R.layout.popview_cart_price, null);
        pop_btn = (Button) pop_view.findViewById(R.id.pop_cart_price_btn);
        pop_tv = (TextView) pop_view.findViewById(R.id.pop_cart_price_money_tv);
        pop_lv = (ListView) pop_view.findViewById(R.id.pop_cart_price_lv);
        //popwindow的适配器
        cartItemAdapter = new CartItemAdapter(item_list, getActivity(), imgList);
        pop_lv.setAdapter(cartItemAdapter);


    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.img_activity_cart_allselect:
                if (!all_Flag) {
                    img_Cart_All_Select.setImageResource(R.mipmap.cart_select_null);
                    for (CartInfo info3 : list) {
                        info3.Selsct_Flag = true;
                    }
                    adapter.notifyDataSetChanged();
                    refrashPrice();
                } else {
                    img_Cart_All_Select.setImageResource(R.mipmap.cart_select);
                    for (CartInfo info4 : list) {
                        info4.Selsct_Flag = false;
                    }
                    adapter.notifyDataSetChanged();
                    refrashPrice();
                }
                all_Flag = !all_Flag;
                break;
            case R.id.btn_activity_cart_ok:
                boolean flag = false;
                for (CartInfo info : list) {
                    if (info.Selsct_Flag)
                        flag = true;

                }
                if (flag) {

                    Intent intent = new Intent();
                    intent.setClass(getActivity(), PaymentsOrder.class);
                    intent.putExtra("type","cart");
                    intent.putExtra("id", (Serializable) id_String);
                    startActivity(intent);
                } else {
                    Toast.makeText(getActivity(), "请选择要购买的商品", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.pop_cart_price_btn:
                boolean itemflag = false;
                for (CartItemInfo info : item_list) {
                    if (info.flag) {
                        itemflag = true;
                    }
                }
                if (itemflag) {
                    Toast.makeText(getActivity(), "生成订单", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getActivity(), "请选择要支付的银行", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        initList();
    }

    private void initList() {
        list = new ArrayList<CartInfo>();
        new Thread() {
            @Override
            public void run() {
                showGouwuche();
            }
        }.start();
    }

    public void showGouwuche() {
        Loding.show(mActivity,"加载中......",false,null);
        String url = SuMaoConstant.SUMAO_IP+"rest/model/atg/commerce/ShoppingCartActor/shoppingCartDetail";
        RequestParams rp = new RequestParams(url);
        SharedPreferences sp = getActivity().getSharedPreferences("sumao", Activity.MODE_PRIVATE);
        String unique_gouwuche = sp.getString("unique", "");
        Log.d("购物车的唯一标识", "" + unique_gouwuche);
        rp.addParameter("_dynSessConf", unique_gouwuche);
        Log.d("购物车的rp的值", rp.toString());
        x.http().post(rp, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.d("result", result);
                Log.d("购物车的返回结果", result);
                if (result.contains("commerceItem")) {
                    //购物车理由数据
                    try {
                        relative_activity_cart_bottom.setVisibility(View.VISIBLE);
                        relative_cart_tishi.setVisibility(View.VISIBLE);
                        relative_cart_null.setVisibility(View.GONE);
                        JSONObject obj_result = new JSONObject(result);
                        //总价
                        String count_price = obj_result.getString("total");
                        Log.d("total", count_price);
//                        tv_Cart_All_Price.setText(count_price + "元");
//                        Log.d("总价", tv_Cart_All_Price.getText().toString());
                        String item_count = obj_result.getString("commerceItem");
                        JSONArray array = new JSONArray(item_count);
                        for (int i = 0; i < array.length(); i++) {
                            JSONObject array_data = array.getJSONObject(i);
                            CartInfo info = new CartInfo();
                            //  info.name="兰州石化1111";
                            info.name = array_data.getString("manufacturer");
                            info.sort_name = array_data.getString("parentCategories");
                            Log.d("parentCategories", array_data.getString("parentCategories"));
                            info.cangku_name = array_data.getString("warehouse");
                            Log.d("warehouse", array_data.getString("warehouse"));
                            info.pai_num = array_data.getString("gradeNumber");
                            Log.d("pai_num", array_data.getString("gradeNumber"));
                            info.price = array_data.getString("salePrice");
                            Log.d("产品单价",info.price.toString()+"hhhhh"+info.pai_num);
                            info.qiye = array_data.getString("manufacturer");
                            Log.d("manufacturer", array_data.getString("manufacturer"));
                            info.company = array_data.getString("salesCompanyDisplayName");
                            Log.d("salesCompanyDisplayName", array_data.getString("salesCompanyDisplayName"));
                            info.buy_num = array_data.getString("quantity");
                            Log.d("minPurchaseQuantity", array_data.getString("quantity"));
                            info.all_price = array_data.getString("amount");
                            info.id=array_data.getString("commerceItemId");
                            Log.d("商品的id",array_data.getString("commerceItemId"));
                            Log.d("amount", array_data.getString("amount"));
                            list.add(info);
                            Log.d("list的值", list.size() + "");
                            String aa = array_data.getString("surplus");
                            kucong.add(aa);
                            Log.d("bb的值", "" + aa);
                            Log.d("kucong的值", kucong.toString());
                            list_id.add(array_data.getString("commerceItemId"));
                            Log.d("list_id的值",list_id.toString());

                        }
                       // Log.d("list的值","list-----"+list.);
                        Log.d("list的值","list-----"+list.size());
                        adapter = new CartAdapter(getActivity(),PagerThree.this,list, kucong);
                        lv_Cart.setAdapter(adapter);
                        adapter.notifyDataSetChanged();


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else if (result.contains("fail")) {
                    SharedPreferences sp=mActivity.getSharedPreferences("sumao", Activity.MODE_PRIVATE);
                    SharedPreferences.Editor editor=sp.edit();
                    editor.clear();
                    //      editor.clear();
                    editor.commit();
                    Toast.makeText(ctx, "登录已超时，请重新登录", Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(mActivity,MainActivity.class);
                    intent.putExtra("roles","buyer");
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                } else {
                    relative_activity_cart_bottom.setVisibility(View.GONE);
                    relative_cart_tishi.setVisibility(View.GONE);
                    relative_cart_null.setVisibility(View.VISIBLE);
                    // Toast.makeText(getActivity(), "购物车里暂无数据，请添加", Toast.LENGTH_SHORT).show();
                }
                Loding.dis();
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


    @Override
    public void myClickLeft() {

    }

    @Override
    public void myClickRight() {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
        if (parent.getId() == R.id.lv_activity_cart) {
            name = list.get(position).toString();
            Log.d("name的值",name);
            ku_cong_number = kucong.get(position).toString();
            Log.d("kucong", ku_cong_number);
            Log.d("商品id的值",list_id.get(position).toString());

            Toast.makeText(getContext(), "点击了一条item", Toast.LENGTH_SHORT).show();

        }
        if (parent.getId() == R.id.pop_cart_price_lv) {
            for (int i = 0; i < item_list.size(); i++) {
                if (i != position) {
                    item_list.get(i).flag = false;
                }
            }
            item_list.get(position).flag = !item_list.get(position).flag;
            cartItemAdapter.notifyDataSetChanged();
        }

    }


    @Override
    public void Call(int index) {
        list.get(index).Selsct_Flag = !list.get(index).Selsct_Flag;
        adapter.notifyDataSetChanged();
        //  refrashPrice();
    }

    @Override
    public void CartNull(List<CartInfo> list) {
        Log.e("TAG", "list.size()===" + list.size());
        if (list.size() == 0) {
            relative_activity_cart_bottom.setVisibility(View.GONE);
            relative_cart_tishi.setVisibility(View.GONE);
            relative_cart_null.setVisibility(View.VISIBLE);
        } else {
            relative_activity_cart_bottom.setVisibility(View.VISIBLE);
            relative_cart_tishi.setVisibility(View.VISIBLE);
            relative_cart_null.setVisibility(View.GONE);
        }
    }

    //统计总价方法
    private void refrashPrice() {
        all_Price = 0f;
        int index = 0;
        for (CartInfo info : list) {
            if (info.Selsct_Flag) {
                float price = Float.valueOf(info.all_price);
                all_Price += price;
                index++;
            }
        }
        tv_Cart_All_Price.setText("￥:" + all_Price);
        pop_tv.setText(all_Price + "元");
        if (index == list.size()) {
            img_Cart_All_Select.setImageResource(R.mipmap.cart_select);
        } else {
            img_Cart_All_Select.setImageResource(R.mipmap.cart_select_null);
        }
    }

    //popwindow
    private PopupWindow popupWindow;
    private View pop_view;
    private Button pop_btn;
    private TextView pop_tv;
    private ListView pop_lv;
    private CartItemAdapter cartItemAdapter;
    private List<CartItemInfo> item_list = new ArrayList<CartItemInfo>();





    class CartAdapter extends BaseAdapter {
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
        private float number;

        public CartAdapter(Context ctx,SelectCallBack call, List<CartInfo> list, List<String> kucong) {
            this.ctx = ctx;
            this.list = list;
            this.call = call;
          //  this.dialog = dialog;
          //  this.id = id;
            this.kucong = kucong;
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
            if (convertView == null) {
                convertView = View.inflate(ctx, R.layout.item_cart_activity, null);
                holder = new ViewHolder();
                holder.Tv_all_price = (TextView) convertView.findViewById(R.id.tv_item_cart_all_price);
                holder.Edt_buy_num = (EditText) convertView.findViewById(R.id.tv_item_cart_buy_num);
                holder.Tv_cangku_name = (TextView) convertView.findViewById(R.id.tv_item_cart_cangku_name);
                holder.Tv_company = (TextView) convertView.findViewById(R.id.tv_item_cart_cangku_conmpany_name);
                holder.Tv_name = (TextView) convertView.findViewById(R.id.tv_item_cart_name);
                holder.Tv_pai_num = (TextView) convertView.findViewById(R.id.tv_item_cart_paihao_num);
                holder.Tv_price = (TextView) convertView.findViewById(R.id.tv_item_cart_cangku_danjia_price);
                holder.Tv_qiye = (TextView) convertView.findViewById(R.id.tv_item_cart_shengchan_name);
                holder.Tv_sort_name = (TextView) convertView.findViewById(R.id.tv_item_cart_pinzhong_name);
                holder.Img_select = (ImageView) convertView.findViewById(R.id.img_item_cart_select);
                holder.Img_del = (ImageView) convertView.findViewById(R.id.img_item_cart_del);
                holder.Tv_buy_Sub = (TextView) convertView.findViewById(R.id.img_item_cart_buy_sub);
                holder.Tv_buy_Add = (TextView) convertView.findViewById(R.id.img_item_cart_buy_add);
                holder.Tv_no_much = (TextView) convertView.findViewById(R.id.item_cart_tv_no_much);
                holder.relative_item_cart = (RelativeLayout) convertView.findViewById(R.id.relative_item_cart);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            id = list.get(position).id.toString();
            Log.d("商品的--id",id);
            kucong_value = kucong.get(position).toString();
            Log.d("库存的值", kucong_value);
            holder.Tv_name.setText(list.get(position).name);
            holder.Tv_sort_name.setText(list.get(position).sort_name);
            holder.Tv_cangku_name.setText(list.get(position).cangku_name);
            holder.Tv_pai_num.setText(list.get(position).pai_num);
            holder.Tv_price.setText(list.get(position).price.toString());
            holder.Tv_qiye.setText(list.get(position).qiye);
            holder.Tv_company.setText(list.get(position).company);
            holder.Edt_buy_num.setText(list.get(position).buy_num);
            Log.d("选择的值", list.get(position).price.toString());
            holder.Tv_all_price.setText(list.get(position).all_price + "元");
            dialog=new Cart_MyDialog(ctx,holder.Tv_name.getText().toString(),kucong_value);
            //选中与不选中

            if (list.get(position).Selsct_Flag) {
                holder.Img_select.setImageResource(R.mipmap.cart_select);
                refrashPrice();
                id = list.get(position).id.toString();
                Log.d("选中时的产品","ssssssssssss"+id);
                id_String.add(id);
            } else {
                holder.Img_select.setImageResource(R.mipmap.cart_select_null);
               // tv_Cart_All_Price.setText("0.0元");
            }
            holder.Img_select.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    call.Call(position);
                    //这里改变金嗯

                }
            });
            //删除按钮
             holder.Img_del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialog=new AlertDialog.Builder(ctx);
                dialog.setTitle("确定要删除该商品吗？");
                dialog.setPositiveButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                dialog.setNegativeButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        new Thread(){
                            @Override
                            public void run() {
                                //super.run();
                                Log.d("商品的id",list.get(position).id.toString());
                                delete_data(list.get(position).id.toString(),position);
                                //notifyDataSetChanged();
                            }
                        }.start();
                    }
                });
                dialog.create().show();


            }
        });

            //增加或减少购买数
            holder.Tv_buy_Sub.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String str = holder.Edt_buy_num.getText().toString();
                    if (str.equals("")) {
                        str = "0";
                    }
                    float num = Float.parseFloat(str);
                    num=num-1;
                    if (num >= 0) {
                        holder.Edt_buy_num.setText("" + num);
                        number = num;
                       new Thread(){
                           @Override
                           public void run() {
                               super.run();
                               Log.d("商品的--id",list.get(position).id.toString());
                               //update_data(list.get(position).id.toString());
                               updataView(position,lv_Cart,list.get(position).id.toString());
                           }
                       }.start();
                        holder.relative_item_cart.setVisibility(View.GONE);
                        // update_data(number);
//                    holder.Tv_price.setText(price);
//                    holder.Tv_all_price.setText(all_price);
                        //notifyDataSetChanged();
                    }
                    if (num == 0) {
                        Toast.makeText(ctx, "购买数量不能为零", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        holder.Tv_buy_Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str=holder.Edt_buy_num.getText().toString();
                if(str.equals("")){
                    str="0";
                }
                float num=Float.parseFloat(str);
                num=num+1;
                Log.d("库存的值",kucong.get(position));

                if(num>=Float.parseFloat(kucong_value)){
                    holder.relative_item_cart.setVisibility(View.VISIBLE);
                    holder.Tv_no_much.setText("产品库存不足"+num+"吨");
                    dialog.show();
                }
                if(num<Float.parseFloat(kucong_value)&&num>=0) {
                    holder.relative_item_cart.setVisibility(View.GONE);
                    holder.Edt_buy_num.setText("" + num);
                    number=num;
                    new Thread(){
                        @Override
                        public void run() {
                            super.run();
                            //list.get(position).id.toString()
                            Log.d("商品的--id",id);
                            //update_data(list.get(position).id.toString());
                            updataView(position,lv_Cart,list.get(position).id.toString());
                        }
                    }.start();
//                   String number=num+"";
//                    update_data(number);
//                    holder.Tv_price.setText(price);
//                    holder.Tv_all_price.setText(all_price);
                    //notifyDataSetChanged();
                }
            }
        });
        holder.Edt_buy_num.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                temp=holder.Edt_buy_num.getText().toString();
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                temp=holder.Edt_buy_num.getText().toString();
                if(temp.equals("")){
                    Toast.makeText(ctx, "购买数量不能为空", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void afterTextChanged(Editable s) {
                Log.d("商品发生改变","商品发生改变");
               temp=holder.Edt_buy_num.getText().toString();
                if(temp.equals("")){
                    temp="0";
//                    holder.Edt_buy_num.setText(str);
                    Toast.makeText(ctx,"购买数量不能为零",Toast.LENGTH_SHORT).show();
                }
                float i=Float.parseFloat(temp);
//                Log.d("edit的值", new Double(kucong_value)+"");
                if(i>= Float.parseFloat(kucong_value)) {
                    holder.relative_item_cart.setVisibility(View.VISIBLE);
                    holder.Tv_no_much.setText("产品库存不足" + i + "吨");
                    dialog.show();
                }else{

                    holder.relative_item_cart.setVisibility(View.GONE);

//                    holder.Tv_price.setText(price);
//                    holder.Tv_all_price.setText(all_price);
                }

           }
        });
            return convertView;
        }

        public void delete_data(String commerceId,final  int position) {
            String url = SuMaoConstant.SUMAO_IP+"rest/model/atg/commerce/ShoppingCartActor/removeItemFromOrder";
            final RequestParams rp = new RequestParams(url);
            SharedPreferences sp = ctx.getSharedPreferences("sumao", Activity.MODE_PRIVATE);
            String unique_delete = sp.getString("unique", "");
            Log.d("购物车删除时的唯一标识", unique_delete);
            rp.addParameter("_dynSessConf", unique_delete);
            rp.addParameter("commerceId", commerceId);
            Log.d("商品的值", commerceId.trim());
            Log.d("rp的值", rp + "");
            x.http().post(rp, new Callback.CommonCallback<String>() {
                @Override
                public void onSuccess(String result) {

                    Log.d("删除时的result", result);
                    if (result.contains("orderid")) {
                       // list.clear();
                        list.remove(position);
                        notifyDataSetChanged();
                        Toast.makeText(ctx, "删除成功", Toast.LENGTH_SHORT).show();
                        if (list.size()==0){
                            relative_activity_cart_bottom.setVisibility(View.GONE);
                            relative_cart_tishi.setVisibility(View.GONE);
                            relative_cart_null.setVisibility(View.VISIBLE);
                        }

                    }
                    else {
                        try {
                            JSONObject obj_result = new JSONObject(result);
                            String message = obj_result.getString("formExceptions");
                            JSONArray array = new JSONArray(message);
                            JSONObject obj_array = array.getJSONObject(0);
                            String info = obj_array.getString("localizedMessage");
                            Toast.makeText(ctx, "" + info, Toast.LENGTH_SHORT).show();
                        } catch (Exception e) {
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

        public void update_data(String commerceId ) {
            //String url = SuMaoConstant.SUMAO_IP+"rest/model/atg/commerce/ShoppingCartActor/shoppingCartDetail";
            String url = SuMaoConstant.SUMAO_IP+"rest/model/atg/commerce/ShoppingCartActor/commerceItemUpdate";
            RequestParams rp = new RequestParams(url);
            rp.addParameter("amountUnitScale", "1000");
            rp.addParameter("qty", number);
            ///  Log.d("当前选择数量",number);
            Log.d("number的值", "" + number);
            rp.addParameter("commerceId", commerceId);
            Log.d("商品id的值", commerceId);
            SharedPreferences sp = ctx.getSharedPreferences("sumao", Activity.MODE_PRIVATE);
            String unique_update = sp.getString("unique", "");
            Log.d("修改时的唯一标识", unique_update);
            rp.addParameter("_dynSessConf", unique_update);
            Log.d("rp的值", rp + "");
            x.http().post(rp, new Callback.CommonCallback<String>() {
                @Override
                public void onSuccess(String result) {
                    Log.d("修改时的result", result);
                    if (result.contains("commerceItem")) {

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


        public void updataView(final int posi, final ListView listView, String commerceId ) {
            int visibleFirstPosi = listView.getFirstVisiblePosition();
            int visibleLastPosi = listView.getLastVisiblePosition();
            if (posi >= visibleFirstPosi && posi <= visibleLastPosi) {
                View view = listView.getChildAt(posi - visibleFirstPosi);
                final ViewHolder viewHolder = (ViewHolder) view.getTag();

//                String txt = holder.strText.getText().toString();
//                txt = txt + "++;";
//                holder.strText.setText(txt);
//                strList.set(posi, txt);
                String url = SuMaoConstant.SUMAO_IP+"rest/model/atg/commerce/ShoppingCartActor/commerceItemUpdate";
                RequestParams rp = new RequestParams(url);
                rp.addParameter("amountUnitScale", "1000");
                rp.addParameter("qty", number);
                ///  Log.d("当前选择数量",number);
                Log.d("number的值", "" + number);
                rp.addParameter("commerceId", commerceId);
                Log.d("商品id的值", commerceId);
                SharedPreferences sp = ctx.getSharedPreferences("sumao", Activity.MODE_PRIVATE);
                String unique_update = sp.getString("unique", "");
                Log.d("修改时的唯一标识", unique_update);
                rp.addParameter("_dynSessConf", unique_update);
                Log.d("rp的值", rp + "");
                x.http().post(rp, new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        Log.d("修改的返回值",result);
                        try {
                            JSONObject object = new JSONObject(result);
                            String item_count = object.getString("commerceItem");
                            JSONArray array = new JSONArray(item_count);
                            for (int i = 0; i < array.length(); i++) {
                                JSONObject array_data = array.getJSONObject(i);
                                CartInfo info = new CartInfo();

                                info.name = array_data.getString("manufacturer");
                                info.sort_name = array_data.getString("parentCategories");
                                Log.d("parentCategories", array_data.getString("parentCategories"));
                                info.cangku_name = array_data.getString("warehouse");
                                Log.d("warehouse", array_data.getString("warehouse"));
                                info.pai_num = array_data.getString("gradeNumber");
                                Log.d("pai_num", array_data.getString("gradeNumber"));
                                info.price = array_data.getString("salePrice");
                                Log.d("产品单价", info.price.toString() + "hhhhh" + info.pai_num);
                                info.qiye = array_data.getString("manufacturer");
                                Log.d("manufacturer", array_data.getString("manufacturer"));
                                info.company = array_data.getString("salesCompanyDisplayName");
                                Log.d("salesCompanyDisplayName", array_data.getString("salesCompanyDisplayName"));
                                info.buy_num = array_data.getString("quantity");
                                Log.d("minPurchaseQuantity", array_data.getString("quantity"));
                                info.all_price = array_data.getString("amount");
                                info.id = array_data.getString("commerceItemId");
                                Log.d("商品的id", array_data.getString("commerceItemId"));
                                Log.d("amount", array_data.getString("amount"));
                                list.set(posi,info);
                                adapter.notifyDataSetChanged();
                            }

                        }catch (JSONException e){
                            e.printStackTrace();
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

            } else {
//                String txt = strList.get(posi);
//                txt = txt + "++;";
//                strList.set(posi, txt);

            }
        }




        public class ViewHolder {
            TextView Tv_name;
            TextView Tv_sort_name;
            TextView Tv_cangku_name;
            TextView Tv_pai_num;
            TextView Tv_price;
            TextView Tv_qiye;
            TextView Tv_company;
            EditText Edt_buy_num;
            TextView Tv_buy_Sub, Tv_buy_Add;
            TextView Tv_all_price, Tv_no_much;
            ImageView Img_select, Img_del;
            RelativeLayout relative_item_cart;
        }


    }

}

package com.silianchuangye.sumao.success.fragments;


import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.adapter.CartAdapter;
import com.silianchuangye.sumao.success.adapter.CartItemAdapter;
import com.silianchuangye.sumao.success.custom.CustomListView;
import com.silianchuangye.sumao.success.fragments.bean.CartInfo;
import com.silianchuangye.sumao.success.fragments.bean.CartItemInfo;
import com.silianchuangye.sumao.success.fragments.homepage.goodInStock.PaymentsOrder;
import com.silianchuangye.sumao.success.fragments.homepage.theprice.MidpointsListctivity;
import com.silianchuangye.sumao.success.fragments.shoppingCart.dialog.Cart_MyDialog;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/4/20 0020.
 * 购物车界面
 */
public class PagerThree extends BasePager implements AdapterView.OnItemClickListener,CartAdapter.SelectCallBack{
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
    private RelativeLayout relative_cart_null,relative_cart_tishi,relative_activity_cart_bottom;
    private List<Integer>imgList=new ArrayList<Integer>();
    private List list1=new ArrayList();
    private List<String> list_id=new ArrayList<String>();
  //  private List<String> name=new ArrayList<String>();
    private List<String> kucong=new ArrayList<String>();
    private String name;
    private String ku_cong_number;
    public class MyReciver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if(intent.getAction().equals("close_cart_dialog")){
                dialog.dismiss();
            }
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
        initList();
        ctx=getActivity();
        dialog=new Cart_MyDialog(ctx,name,ku_cong_number);
        my=new MyReciver();
        IntentFilter intent=new IntentFilter();
        intent.addAction("close_cart_dialog");
        getActivity().registerReceiver(my,intent);
        rl_title.setVisibility(View.GONE);
        View v = View.inflate(getActivity(), R.layout.activity_cart, null);
        fl_content.addView(v);
        relative_cart_null= (RelativeLayout) v.findViewById(R.id.relative_cart_null);
        relative_cart_tishi= (RelativeLayout) v.findViewById(R.id.relative_cart_tishi);
        relative_activity_cart_bottom= (RelativeLayout) v.findViewById(R.id.relative_activity_cart_bottom);
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

        pop_view=View.inflate(getActivity(),R.layout.popview_cart_price,null);
        pop_btn= (Button) pop_view.findViewById(R.id.pop_cart_price_btn);
        pop_tv= (TextView) pop_view.findViewById(R.id.pop_cart_price_money_tv);
        pop_lv= (ListView) pop_view.findViewById(R.id.pop_cart_price_lv);
        //popwindow的适配器
        cartItemAdapter=new CartItemAdapter(item_list,getActivity(),imgList);
        pop_lv.setAdapter(cartItemAdapter);

    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.img_activity_cart_allselect:
                if(!all_Flag) {
                    img_Cart_All_Select.setImageResource(R.mipmap.cart_select_null);
                    for(CartInfo info3:list){
                        info3.Selsct_Flag=true;
                    }
                    adapter.notifyDataSetChanged();
                    refrashPrice();
                }else{
                    img_Cart_All_Select.setImageResource(R.mipmap.cart_select);
                    for(CartInfo info4:list){
                        info4.Selsct_Flag=false;
                    }
                    adapter.notifyDataSetChanged();
                    refrashPrice();
                }
                all_Flag=!all_Flag;
                break;
            case R.id.btn_activity_cart_ok:
                boolean flag = false;
                for(CartInfo info : list){
                    if(info.Selsct_Flag)
                        flag = true;
                }
                if(flag) {
//                    showPopWindow();
//                    backgroundAlpha(0.5f);
//                    Toast.makeText(getActivity(), "支付", Toast.LENGTH_SHORT).show();
                    //提交订单
                    commit_order();
                    Intent intent = new Intent();
                    intent.setClass(getActivity(), PaymentsOrder.class);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(getActivity(), "请选择要购买的商品", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.pop_cart_price_btn:
                boolean itemflag=false;
                for(CartItemInfo info:item_list){
                    if(info.flag){
                        itemflag=true;
                    }
                }
                if(itemflag) {
                    Toast.makeText(getActivity(), "生成订单", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getActivity(), "请选择要支付的银行", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    private void initList() {
        list=new ArrayList<CartInfo>();
        new Thread(){
            @Override
            public void run() {
                //super.run();
                String url="http://192.168.32.126:7023/rest/model/atg/commerce/ShoppingCartActor/shoppingCartDetail";
                RequestParams rp=new RequestParams(url);
                SharedPreferences sp=getActivity().getSharedPreferences("sumao", Activity.MODE_PRIVATE);
                String unique_gouwuche=sp.getString("unique","");
                Log.d("购物车的唯一标识",""+unique_gouwuche);
                rp.addParameter("_dynSessConf",unique_gouwuche);
                Log.d("购物车的rp的值",rp.toString());
                x.http().post(rp, new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        Log.d("result的值",result);
                        Log.d("购物车的返回结果",result);
                        if (result.contains("commerceItem")){
                            //购物车理由数据
                            try {
                                relative_activity_cart_bottom.setVisibility(View.VISIBLE);
                                relative_cart_tishi.setVisibility(View.VISIBLE);
                                relative_cart_null.setVisibility(View.GONE);
                                JSONObject obj_result = new JSONObject(result);
                                //总价
                                String count_price=obj_result.getString("total");
                                Log.d("total",count_price);
                                tv_Cart_All_Price.setText(count_price+"元");
                                Log.d("总价",tv_Cart_All_Price.getText().toString());
                                String item_count=obj_result.getString("commerceItem");
                                JSONArray array=new JSONArray(item_count);
                                for (int i=0;i<array.length();i++){
                                    JSONObject array_data=array.getJSONObject(i);
                                    CartInfo info=new CartInfo();
                                    info.name="兰州石化1111";
                                    info.sort_name=array_data.getString("parentCategories");
                                    Log.d("parentCategories",array_data.getString("parentCategories"));
                                    info.cangku_name=array_data.getString("warehouse");
                                    Log.d("warehouse",array_data.getString("warehouse"));
                                    info.pai_num=array_data.getString("gradeNumber");
                                    Log.d("pai_num",array_data.getString("gradeNumber"));
                                    info.price=array_data.getString("salePrice");
                                    Log.d("salePrice",array_data.getString("salePrice"));
                                    info.qiye=array_data.getString("manufacturer");
                                    Log.d("manufacturer",array_data.getString("manufacturer"));
                                    info.company=array_data.getString("salesCompanyDisplayName");
                                    Log.d("salesCompanyDisplayName",array_data.getString("salesCompanyDisplayName"));
                                    info.buy_num=array_data.getString("minPurchaseQuantity");
                                    Log.d("minPurchaseQuantity",array_data.getString("minPurchaseQuantity"));
                                    info.all_price=array_data.getString("amount");
                                    Log.d("amount",array_data.getString("amount"));
                                    list.add(info);
                                    Log.d("list的值",list.size()+"");
                                    String aa=array_data.getString("surplus");
                                    int len=array_data.getString("surplus").length()-1;
                                    String bb=aa.substring(1,len);
                                    kucong.add(bb);
                                    Log.d("bb的值",""+bb);
                                    Log.d("kucong的值",kucong.toString());

                                    list_id.add(array_data.getString("commerceItemId").trim());
                                    Log.d("list_id的值",array_data.getString("commerceItemId"));
                                }

                                adapter = new CartAdapter(getActivity(),list,PagerThree.this,dialog,list_id,kucong);
                                lv_Cart.setAdapter(adapter);


                            }catch (JSONException e){
                                e.printStackTrace();
                            }
                        }else{
                            relative_activity_cart_bottom.setVisibility(View.GONE);
                            relative_cart_tishi.setVisibility(View.GONE);
                            relative_cart_null.setVisibility(View.VISIBLE);
                           // Toast.makeText(getActivity(), "购物车里暂无数据，请添加", Toast.LENGTH_SHORT).show();
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
        }.start();


////popwindow的数据源
        CartItemInfo iteminfo1=new CartItemInfo();
        iteminfo1.bank_name="中国邮政银行";
        CartItemInfo iteminfo2=new CartItemInfo();
        iteminfo2.bank_name="中国建设银行";
        CartItemInfo iteminfo3=new CartItemInfo();
        iteminfo3.bank_name="中国工商银行";
        item_list.add(iteminfo1);
        item_list.add(iteminfo2);
        item_list.add(iteminfo3);
        imgList.add(R.mipmap.aa);
        imgList.add(R.mipmap.ic_launcher);
        imgList.add(R.mipmap.adwords);
    }

    @Override
    public void myClickLeft() {

    }

    @Override
    public void myClickRight() {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if(parent.getId()==R.id.lv_activity_cart){
            name=list.get(position).toString();
            ku_cong_number=kucong.get(position).toString();
            Log.d("kucong",ku_cong_number);
            Toast.makeText(getContext(),"点击了一条item",Toast.LENGTH_SHORT).show();
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
        list.get(index).Selsct_Flag=!list.get(index).Selsct_Flag;
        adapter.notifyDataSetChanged();
      //  refrashPrice();
    }

    @Override
    public void CartNull(List<CartInfo>list) {
        Log.e("TAG","list.size()==="+list.size());
        if(list.size()==0){
            relative_activity_cart_bottom.setVisibility(View.GONE);
            relative_cart_tishi.setVisibility(View.GONE);
            relative_cart_null.setVisibility(View.VISIBLE);
        }else{
            relative_activity_cart_bottom.setVisibility(View.VISIBLE);
            relative_cart_tishi.setVisibility(View.VISIBLE);
            relative_cart_null.setVisibility(View.GONE);
        }
    }

    //统计总价方法
   private void refrashPrice(){
        all_Price = 0f;
        int index = 0;
        for(CartInfo info : list){
            if(info.Selsct_Flag){
                float price = Float.valueOf(info.all_price);
                all_Price +=price;
                index ++;
            }
        }
        tv_Cart_All_Price.setText("￥:"+all_Price);
        pop_tv.setText(all_Price+"元");
        if(index == list.size()){
            img_Cart_All_Select.setImageResource(R.mipmap.cart_select);
        }else{
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
    private List<CartItemInfo>item_list=new ArrayList<CartItemInfo>();
    private void showPopWindow(){
        pop_view.measure(0,0);
        int w=getActivity().getWindowManager().getDefaultDisplay().getWidth();
        popupWindow=new PopupWindow(pop_view,w,pop_view.getMeasuredHeight());
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.setFocusable(true);
        popupWindow.showAtLocation(lv_Cart, Gravity.BOTTOM,0,0);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                backgroundAlpha(1f);
            }
        });
        pop_btn.setOnClickListener(this);
        pop_lv.setOnItemClickListener(this);
    }
    public void backgroundAlpha(float bgAlpha)
    {
        WindowManager.LayoutParams lp = getActivity().getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        getActivity().getWindow().setAttributes(lp);
    }
    public void commit_order(){
        new Thread(){
            @Override
            public void run() {
                //super.run();
                String url="http://192.168.32.126:7023/rest/model/atg/commerce/ShoppingCartActor/cartExpressCheckout";
                RequestParams rp=new RequestParams(url);
                String a=list_id.toString();
                int len=a.length()-1;
                String id=a.substring(1,len).replaceAll(" ","");
                Log.d("商品id",""+id);
                rp.addParameter("strCommerceItemIds",id);
                SharedPreferences sp=getActivity().getSharedPreferences("sumao",Activity.MODE_PRIVATE);
                String coummit_unique=sp.getString("unique","");
                rp.addParameter("_dynSessConf",coummit_unique);
                Log.d("rp的值",rp+"");
                x.http().post(rp, new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        Log.d("提交订单的result",result);
                        try {
                            JSONObject object = new JSONObject(result);
                            //支付总价
                            String total=object.getString("total");
                            Log.d("total",total);
                            //订单编号
                            String order_id=object.getString("orderid");
                            Log.d("order_id",order_id);
                            String message=object.getString("commerceItem");
                            JSONArray array=new JSONArray(message);
                            for (int i=0;i<array.length();i++){
                                JSONObject obj_array=array.getJSONObject(i);
                                String type=obj_array.getString("parentCategories");
                                String price=obj_array.getString("salePrice");
                                String paihao=obj_array.getString("gradeNumber");
                                String qiye=obj_array.getString("manufacturer");
                                String all_price=obj_array.getString("amount");
                                String cangku=obj_array.getString("warehouse");
                                String comm=obj_array.getString("salesCompanyDisplayName");



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
            }
        }.start();
    }

}

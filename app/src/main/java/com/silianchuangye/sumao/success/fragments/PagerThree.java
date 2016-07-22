package com.silianchuangye.sumao.success.fragments;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
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
    private RelativeLayout relative_cart_null,relative_cart_tishi;
 private List<Integer>imgList=new ArrayList<Integer>();
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
        ctx=getActivity();
        dialog=new Cart_MyDialog(ctx);
        my=new MyReciver();
        IntentFilter intent=new IntentFilter();
        intent.addAction("close_cart_dialog");
        getActivity().registerReceiver(my,intent);
        initList();
        rl_title.setVisibility(View.GONE);
        View v = View.inflate(getActivity(), R.layout.activity_cart, null);
        fl_content.addView(v);
        relative_cart_null= (RelativeLayout) v.findViewById(R.id.relative_cart_null);
        relative_cart_tishi= (RelativeLayout) v.findViewById(R.id.relative_cart_tishi);
        lv_Cart = (CustomListView) v.findViewById(R.id.lv_activity_cart);
        btn_Cart_Ok = (Button) v.findViewById(R.id.btn_activity_cart_ok);
        img_Cart_All_Select = (ImageView) v.findViewById(R.id.img_activity_cart_allselect);
        tv_Cart_All_Price = (TextView) v.findViewById(R.id.tv_activity_cart_all_price);
        adapter = new CartAdapter(getActivity(),list,this,dialog);
        lv_Cart.setAdapter(adapter);
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
        CartInfo info=new CartInfo();
        info.name="兰州石化1111";
        info.sort_name="LLDPE";
        info.cangku_name="北京讯帮1库";
        info.pai_num="7045";
        info.price="1,222";
        info.qiye="中海油";
        info.company="四联创业集团";
        info.buy_num="10";
        info.all_price="1";
        list.add(info);
        CartInfo info2=new CartInfo();
        info2.name="兰州石化1111";
        info2.sort_name="LLDPE";
        info2.cangku_name="北京讯帮1库";
        info2.pai_num="7045";
        info2.price="1,222";
        info2.qiye="中海油";
        info2.company="四联创业集团";
        info2.buy_num="10";
        info2.all_price="1";
        list.add(info2);
        CartInfo info3=new CartInfo();
        info3.name="兰州石化1113";
        info3.sort_name="LLDPE";
        info3.cangku_name="北京讯帮1库";
        info3.pai_num="7045";
        info3.price="1,222";
        info3.qiye="中海油";
        info3.company="四联创业集团";
        info3.buy_num="10";
        info3.all_price="1";
        list.add(info3);
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
        refrashPrice();
    }

    @Override
    public void CartNull(List<CartInfo>list) {
        Log.e("TAG","list.size()==="+list.size());
        if(list.size()==0){
            relative_cart_tishi.setVisibility(View.GONE);
            relative_cart_null.setVisibility(View.VISIBLE);
        }else{
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
}

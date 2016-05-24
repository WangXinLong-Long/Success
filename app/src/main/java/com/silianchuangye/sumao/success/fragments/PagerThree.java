package com.silianchuangye.sumao.success.fragments;


import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.adapter.CartAdapter;
import com.silianchuangye.sumao.success.fragments.bean.CartInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/4/20 0020.
 */
public class PagerThree extends BasePager implements AdapterView.OnItemClickListener,
        CartAdapter.SelectCallBack{
    private ListView lv_Cart;
    private CartAdapter adapter;
    private List<CartInfo> list;
    private ImageView img_Cart_All_Select;
    private TextView tv_Cart_All_Price;
    private Button btn_Cart_Ok;
    private boolean all_Flag;
    private float all_Price;
    @Override
    public void myClickSearch() {

    }

    @Override
    public void initDate() {
        initView();
    }

    private void initView() {
        initList();
        rl_title.setVisibility(View.GONE);
        View v = View.inflate(getActivity(), R.layout.activity_cart, null);
        fl_content.addView(v);
        lv_Cart = (ListView) v.findViewById(R.id.lv_activity_cart);
        btn_Cart_Ok = (Button) v.findViewById(R.id.btn_activity_cart_ok);
        img_Cart_All_Select = (ImageView) v.findViewById(R.id.img_activity_cart_allselect);
        tv_Cart_All_Price = (TextView) v.findViewById(R.id.tv_activity_cart_all_price);
        adapter = new CartAdapter(getActivity(), list,this,this);
        lv_Cart.setAdapter(adapter);
        lv_Cart.setOnItemClickListener(this);
        img_Cart_All_Select.setOnClickListener(this);
        btn_Cart_Ok.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.img_activity_cart_allselect:
                if(!all_Flag) {
                    Toast.makeText(getContext(),"全选",Toast.LENGTH_SHORT).show();
                    img_Cart_All_Select.setImageResource(R.mipmap.adwords);
                    for(CartInfo info3:list){
                        info3.Selsct_Flag=true;
                    }
                    adapter.notifyDataSetChanged();
                    refrashPrice();
                }else{
                    Toast.makeText(getContext(),"全不选",Toast.LENGTH_SHORT).show();
                    img_Cart_All_Select.setImageResource(R.mipmap.ic_launcher);
                    for(CartInfo info4:list){
                        info4.Selsct_Flag=false;
                    }
                    adapter.notifyDataSetChanged();
                    refrashPrice();
                }
                all_Flag=!all_Flag;
                break;
            case R.id.btn_activity_cart_ok:

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
    }

    @Override
    public void myClickLeft() {

    }

    @Override
    public void myClickRight() {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(getContext(),"onClick",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void Call(int index) {
        list.get(index).Selsct_Flag=!list.get(index).Selsct_Flag;
        adapter.notifyDataSetChanged();
        refrashPrice();
    }

    @Override
    public void MyReciverCall(CartAdapter.MyReciver myReciver) {
        getActivity().unregisterReceiver(myReciver);
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
        if(index == list.size()){
            img_Cart_All_Select.setImageResource(R.mipmap.adwords);
        }else{
            img_Cart_All_Select.setImageResource(R.mipmap.ic_launcher);
        }
    }
}

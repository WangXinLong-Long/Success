package com.silianchuangye.sumao.success.fragments.homepage.goodInStock;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.widget.ActionBarOverlayLayout;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.google.zxing.client.result.TextParsedResult;
import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.adapter.PopupWindowAdaptrer;
import com.silianchuangye.sumao.success.dialog.Ok_Dialog;
import com.silianchuangye.sumao.success.fragments.homepage.auction.OpenAuction;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/7/4 0004.
 */
public class PaymentsOrder extends Activity implements View.OnClickListener{

    private TextView title_bar_white_title;
    private TextView product_order_number;
    private TextView money_number;
    private TextView residual_time;
    private TextView surplus_amount_et;
    private TextView purchase_quantity_et;
    private TextView min_variable_et;
    private TextView delivery_time_et;
    private TextView classification_pre_sale_et;
    private TextView warehouse_et;
    private TextView region_et;
    private TextView company_et;
    private TextView total_money;
    private Button buy_immediately;
    private ImageView title_bar_white_shopping_cart;
    private ImageView title_bar_white_back;
    private TextView tv;
    private ListView lv;
    private EditText et;
    private PopupWindowAdaptrer adapter1;
    private PopupWindow popupWindow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payments_order_activity);
        //页面标题 “支付订单”
        title_bar_white_title = (TextView)findViewById(R.id.title_bar_white_title);
//        设置时：   产品订单号：+number
        product_order_number = (TextView)findViewById(R.id.product_order_number);
//        支付总金额
        money_number = (TextView)findViewById(R.id.money_number);
//        设置时： 提示：剩余时间+num+分
        residual_time = (TextView)findViewById(R.id.residual_time);
//        品种
        surplus_amount_et = (TextView)findViewById(R.id.surplus_amount_et);
//        产品编号
        purchase_quantity_et = (TextView)findViewById(R.id.purchase_quantity_et);
//        生产企业
        min_variable_et = (TextView)findViewById(R.id.min_variable_et);
//        仓库
        delivery_time_et = (TextView)findViewById(R.id.delivery_time_et);
//        产品单价
        classification_pre_sale_et = (TextView)findViewById(R.id.classification_pre_sale_et);
//        数量
        warehouse_et = (TextView)findViewById(R.id.warehouse_et);
//        产品总价
        region_et = (TextView)findViewById(R.id.region_et);
//        公司
        company_et = (TextView)findViewById(R.id.company_et);
//        总金额
        total_money = (TextView)findViewById(R.id.total_money);
//        立即购买
        buy_immediately = (Button) findViewById(R.id.buy_immediately);
//        因为标题是复用的，现在要把多余的图标影藏掉
        title_bar_white_shopping_cart = (ImageView) findViewById(R.id.title_bar_white_shopping_cart);
        title_bar_white_shopping_cart.setVisibility(View.INVISIBLE);
//        返回按钮设置监听
        title_bar_white_back = (ImageView) findViewById(R.id.title_bar_white_back);
        title_bar_white_back.setOnClickListener(this);

//        为  立即支付按钮  设置监听
        buy_immediately.setOnClickListener(this);
//          设置标题 支付订单
        title_bar_white_title.setText("支付订单");

    }

    @Override
    public void onClick(View v) {
       switch (v.getId()){
           case R.id.buy_immediately:

               Popupwindow();
               break;
           case R.id.title_bar_white_back:
               finish();
               break;


        }
    }
    public void Popupwindow(){
        View view=getLayoutInflater().inflate(R.layout.item_popupwindow_auction,null);
         popupWindow=new PopupWindow(findViewById(R.id.Layout_Button_Open), ActionBarOverlayLayout.LayoutParams.MATCH_PARENT, ActionBarOverlayLayout.LayoutParams.WRAP_CONTENT);
        popupWindow.setContentView(view);
        LinearLayout layout_one= (LinearLayout) view.findViewById(R.id.layout_one);
        LinearLayout layout_two= (LinearLayout) view.findViewById(R.id.layout_two);
        LinearLayout layout_three= (LinearLayout) view.findViewById(R.id.layout_three);
        layout_one.setVisibility(View.GONE);
        layout_two.setVisibility(View.GONE);
        layout_three.setVisibility(View.GONE);
        tv= (TextView) view.findViewById(R.id.tvPrice_popupwindow_auction);
        et= (EditText) view.findViewById(R.id.etZhifu_auction);
        lv= (ListView) view.findViewById(R.id.lv_popupwindow_auction);
        final List<OpenAuction> list_pop=new ArrayList<OpenAuction>();
        OpenAuction openauction1=new OpenAuction();
        openauction1.iv_icon=R.mipmap.direct;
        openauction1.tv_Name="北京工商银行";
        openauction1.tv_money="1234";
        list_pop.add(openauction1);
        OpenAuction openauction2=new OpenAuction();
        openauction2.iv_icon=R.mipmap.vertet;
        openauction2.tv_Name="北京建设银行";
        openauction2.tv_money="1234";
        list_pop.add(openauction2);
        Log.d("changdu",list_pop.size()+"");
        adapter1=new PopupWindowAdaptrer(list_pop,PaymentsOrder.this);
        lv.setAdapter(adapter1);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(parent.getId()==lv.getId()){
                    for(int i=0;i<list_pop.size();i++){
                        Log.d("Listview的item",position+"");
                        if(i!=position){

                            list_pop.get(i).Flag=false;

                        }
                    }
                    list_pop.get(position).Flag=!list_pop.get(position).Flag;
                    adapter1.notifyDataSetChanged();
                }
            }
        });
        Button bt= (Button) view.findViewById(R.id.btZhifu);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
                Intent intent=new Intent(PaymentsOrder.this,Ok_Dialog.class);
                intent.putExtra("number",product_order_number.getText().toString());
                startActivity(intent);

            }
        });
        popupWindow.setTouchable(true);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.setOutsideTouchable(true);
        popupWindow.setFocusable(true);

        popupWindow.showAtLocation(buy_immediately, Gravity.BOTTOM,0,0);

        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                //popupWindow.dismiss();
                backgroundAlpha(1f);
            }
        });

    }
    //设置背景透明
    public void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        getWindow().setAttributes(lp);
    }
}

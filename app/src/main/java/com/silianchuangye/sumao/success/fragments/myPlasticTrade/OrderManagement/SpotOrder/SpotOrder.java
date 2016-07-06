package com.silianchuangye.sumao.success.fragments.myPlasticTrade.OrderManagement.SpotOrder;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AlertDialog;
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
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.adapter.PopupWindowAdaptrer;
import com.silianchuangye.sumao.success.adapter.SpotOrderAdapter;
import com.silianchuangye.sumao.success.custom.CustomListView;
import com.silianchuangye.sumao.success.fragments.homepage.auction.OpenAuction;
import com.silianchuangye.sumao.success.model.SpotOrderModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

/**
 * Created by Administrator on 2016/5/3 0003.
 */
public class SpotOrder extends Activity implements View.OnClickListener {
    ImageView iv_title_bar_logo,
            iv_title_bar_back,
            iv_title_bar_service,
            iv_title_bar_search;
    Button sv_title_bar_serachView;
    TextView tv_title_bar_title;
    RelativeLayout spot_order_title;
    CustomListView spot_order_listView;
    /*定义一个倒计时的内部类*/
    private MyCount mc;
    //    SpotOrde模型
    private SpotOrderModel model, dada;
    //      创建SpotOrderModel对象集合
    List<SpotOrderModel> list;
    //    适配器
    SpotOrderAdapter adapter;
    private Button bt_zhifu;
    private TextView tv;
    private EditText et;
    private ListView lv;
    private PopupWindowAdaptrer adapter1;
     PopupWindow popupWindow;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spot_order);
        bt_zhifu = (Button) findViewById(R.id.bt_Zhifu);
        bt_zhifu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Popupwindow();
                backgroundAlpha(0.5f);

            }
        });
        spot_order_title = ((RelativeLayout) findViewById(R.id.spot_order_title));
        spot_order_title.setBackgroundColor(getResources().getColor(R.color.textColor_expandable_listview_show));

        iv_title_bar_back = ((ImageView) findViewById(R.id.iv_title_bar_back));
        iv_title_bar_logo = ((ImageView) findViewById(R.id.iv_title_bar_logo));
        iv_title_bar_service = ((ImageView) findViewById(R.id.iv_title_bar_service));
        iv_title_bar_search = ((ImageView) findViewById(R.id.iv_title_bar_search));
        sv_title_bar_serachView = ((Button) findViewById(R.id.sv_title_bar_serachView));
        tv_title_bar_title = ((TextView) findViewById(R.id.tv_title_bar_title));

        iv_title_bar_logo.setVisibility(View.INVISIBLE);
        iv_title_bar_service.setVisibility(View.INVISIBLE);
        sv_title_bar_serachView.setVisibility(View.INVISIBLE);

        iv_title_bar_back.setOnClickListener(this);
        iv_title_bar_search.setOnClickListener(this);
        tv_title_bar_title.setText("现货订单");
        tv_title_bar_title.setTextColor(Color.WHITE);

        tv = ((TextView) findViewById(R.id.tv));
        /**
         *
         * 根据系统下单时间，获取剩余结束时间
         */
        mc = new MyCount(50 * 1000, 1000);
        mc.start();

        list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            model = new SpotOrderModel();
            model.setUnivalent(i * 100);
            model.setNumber(i + 1);
            model.setEnterprise("中石油");
            model.setTotalMoney((i * 100) * (i + 1));
            model.setWarehouse("讯帮" + i + "库");
            model.setCompany(i + "联创业集团");
            model.setProductModel("中国石油" + i + "型产品");
            list.add(model);
        }

        spot_order_listView = ((CustomListView) findViewById(R.id.spot_order_listView));
        adapter = new SpotOrderAdapter(this, list);
        spot_order_listView.setAdapter(adapter);
       /* spot_order_listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(SpotOrder.this,"dianji"+position+"条",Toast.LENGTH_SHORT).show();
            }
        });*/
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_title_bar_back:
                finish();
                break;
            case R.id.iv_title_bar_search:
                Toast.makeText(this, "搜索", Toast.LENGTH_SHORT).show();

                break;
        }
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "SpotOrder Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.silianchuangye.sumao.success.fragments.myPlasticTrade.OrderManagement.SpotOrder/http/host/path")
        );
//        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "SpotOrder Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.silianchuangye.sumao.success.fragments.myPlasticTrade.OrderManagement.SpotOrder/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }

    /*定义一个倒计时的内部类*/
    class MyCount extends CountDownTimer {
        public MyCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onFinish() {
            tv.setText("finish");
        }

        @Override
        public void onTick(long millisUntilFinished) {
            SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
            String time = format.format(millisUntilFinished - TimeZone.getDefault().getRawOffset());
            tv.setText("剩余支付时间：" + time);
        }
    }

    public void Popupwindow() {
        View view = getLayoutInflater().inflate(R.layout.item_popupwindow_auction, null);
        popupWindow = new PopupWindow(findViewById(R.id.Layout_c), ActionBarOverlayLayout.LayoutParams.MATCH_PARENT, ActionBarOverlayLayout.LayoutParams.WRAP_CONTENT);
        popupWindow.setContentView(view);
        // yuan= (TextView) view.findViewById(R.id.yuan);
        LinearLayout layout_one= (LinearLayout) view.findViewById(R.id.layout_one);
        LinearLayout layout_tow= (LinearLayout) view.findViewById(R.id.layout_two);
        LinearLayout layout_three= (LinearLayout) view.findViewById(R.id.layout_three);
        layout_one.setVisibility(View.GONE);
        layout_tow.setVisibility(View.GONE);
        layout_three.setVisibility(View.GONE);
        tv = (TextView) view.findViewById(R.id.tvPrice_popupwindow_auction);
        et = (EditText) view.findViewById(R.id.etZhifu_auction);
        tv.setVisibility(View.GONE);
        et.setVisibility(View.GONE);
        lv = (ListView) view.findViewById(R.id.lv_popupwindow_auction);
        final List<OpenAuction> list_pop = new ArrayList<OpenAuction>();
        OpenAuction openauction1 = new OpenAuction();
        openauction1.iv_icon = R.mipmap.direct;
        openauction1.tv_Name = "北京工商银行";
        openauction1.tv_money = "1234";
        list_pop.add(openauction1);
        OpenAuction openauction2 = new OpenAuction();
        openauction2.iv_icon = R.mipmap.vertet;
        openauction2.tv_Name = "北京建设银行";
        openauction2.tv_money = "1234";
        list_pop.add(openauction2);
        Log.d("changdu", list_pop.size() + "");
        adapter1 = new PopupWindowAdaptrer(list_pop, SpotOrder.this);
        lv.setAdapter(adapter1);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (parent.getId() == lv.getId()) {
                    for (int i = 0; i < list_pop.size(); i++) {
                        Log.d("Listview的item", position + "");
                        if (i != position) {

                            list_pop.get(i).Flag = false;

                        }
                    }
                    list_pop.get(position).Flag = !list_pop.get(position).Flag;
                    adapter1.notifyDataSetChanged();
                }
            }
        });
        Button bt = (Button) view.findViewById(R.id.btZhifu);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 popupWindow.dismiss();
                int aa=1;
                AlertDialog.Builder alert=new AlertDialog.Builder(SpotOrder.this);
                if (aa==1){
                alert.setView(R.layout.dialog_error);
                    aa=2;
                    Log.d("aa",aa+"");
                }else if (aa==2){
                 alert.setView(R.layout.dialog_ok);
                }
                alert.create().show();

            }
        });
        popupWindow.setTouchable(true);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.setOutsideTouchable(true);
        popupWindow.setFocusable(true);

        popupWindow.showAtLocation(spot_order_listView, Gravity.BOTTOM, 0, 0);

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

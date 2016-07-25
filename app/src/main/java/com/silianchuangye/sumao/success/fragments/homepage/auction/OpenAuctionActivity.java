package com.silianchuangye.sumao.success.fragments.homepage.auction;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ActionBarOverlayLayout;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.silianchuangye.sumao.success.MainActivity;
import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.adapter.PopupWindowAdaptrer;
import com.silianchuangye.sumao.success.fragments.bean.ChinaNorth_Margin_info;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

public class OpenAuctionActivity extends AppCompatActivity {
    private ListView lv_auction;
    private List<Map<String,Object>> list;
    private Button btZhifu_auction;
    private TextView tv;
    private EditText et;
    private ListView lv;
    private ImageView ivBack;
    private ImageView gouwuche;

    private boolean flag;
    private PopupWindowAdaptrer adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_auction);
        ivBack= (ImageView) findViewById(R.id.ivBack);
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenAuctionActivity.this.finish();
            }
        });
//        gouwuche= (ImageView) findViewById(R.id.ivGouwuche);
//        gouwuche.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent=new Intent(OpenAuctionActivity.this, MainActivity.class);
//                intent.putExtra("cart",1);
//                startActivity(intent);
//                OpenAuctionActivity.this.finish();
//            }
//        });
        lv_auction= (ListView) findViewById(R.id.lv_auction);
        list=new ArrayList<Map<String,Object>>();
        Map<String,Object> map1=new Hashtable<String,Object>();
        map1.put("text","实时行情、我的报价");
        list.add(map1);
        Map<String,Object> map2=new Hashtable<String,Object>();
        map2.put("text","交易规则");
        list.add(map2);
        Map<String,Object> map3=new Hashtable<String,Object>();
        map3.put("text","合同详情、产品参数、量价图");
        list.add(map3);
        SimpleAdapter adapter=new SimpleAdapter(this,list,R.layout.item_open_auction,new String[]{"text"},new int[]{R.id.tvRule_auction});

        lv_auction.setAdapter(adapter);
        btZhifu_auction= (Button) findViewById(R.id.btZhifu_auction);
        btZhifu_auction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Popupwindow();
                backgroundAlpha(0.5f);
            }
        });
        lv_auction.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position==0){
                    Intent intent=new Intent(OpenAuctionActivity.this,VesselOneActivity.class);
                    startActivity(intent);
                }else if(position==1){
                    Intent intent=new Intent(OpenAuctionActivity.this,VesselTwoActivity.class);
                    startActivity(intent);
                }else if(position==2){
                    Intent intent=new Intent(OpenAuctionActivity.this,VesselThreeActivity.class);
                    startActivity(intent);
                }
            }
        });

    }
    public void Popupwindow(){
        View view=getLayoutInflater().inflate(R.layout.item_popupwindow_auction,null);
        PopupWindow popupWindow=new PopupWindow(findViewById(R.id.Layout_c), ActionBarOverlayLayout.LayoutParams.MATCH_PARENT, ActionBarOverlayLayout.LayoutParams.WRAP_CONTENT);
        popupWindow.setContentView(view);
         tv= (TextView) view.findViewById(R.id.tvPrice_popupwindow_auction);
         et= (EditText) view.findViewById(R.id.etZhifu_auction);
         lv= (ListView) view.findViewById(R.id.lv_popupwindow_auction);
        final List<OpenAuction> list1=new ArrayList<OpenAuction>();
        OpenAuction openauction1=new OpenAuction();
        openauction1.iv_icon=R.mipmap.direct;
        openauction1.tv_Name="北京工商银行";
        openauction1.tv_money="1234";
        list1.add(openauction1);
        OpenAuction openauction2=new OpenAuction();
        openauction2.iv_icon=R.mipmap.vertet;
        openauction2.tv_Name="北京建设银行";
        openauction2.tv_money="1234";
        list1.add(openauction2);

    adapter=new PopupWindowAdaptrer(list1,this);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(parent.getId()==lv.getId()){
                    for(int i=0;i<list1.size();i++){
                       Log.d("Listview的item",position+"");
                        if(i!=position){

                                list1.get(i).Flag=false;

                        }
                    }
                    list1.get(position).Flag=!list1.get(position).Flag;
                    adapter.notifyDataSetChanged();
                }
           }
        });
        Button bt= (Button) view.findViewById(R.id.btZhifu);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv.setText(et.getText().toString());
                et.setText("");
            }
        });
        popupWindow.setTouchable(true);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.setOutsideTouchable(true);
        popupWindow.setFocusable(true);

        popupWindow.showAtLocation(lv_auction, Gravity.BOTTOM,0,0);

        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                //popupWindow.dismiss();
                backgroundAlpha(1f);
            }
        });

    }
    //设置背景透明
    public void backgroundAlpha(float bgAlpha)
    {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        getWindow().setAttributes(lp);
    }




}

package com.silianchuangye.sumao.success.fragments.homepage.auction;

import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.drawable.BitmapDrawable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
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
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.adapter.MyPageAdapter;
import com.silianchuangye.sumao.success.adapter.PopupWindowAdaptrer;

import java.util.ArrayList;
import java.util.List;

public class VesselOneActivity extends AppCompatActivity {
    private ImageView ivBack_vessel_one;
    private ImageView ivShop_vessel_one;
    private TabLayout tab_vessel_one;
    private ViewPager vp_vessel_one;
    private ArrayList<Fragment> list;
    private MyPageAdapter adapter1;
    private Button btZhifu_auction;
    private TextView tv;
    private EditText et;
    private ListView lv;
    private PopupWindowAdaptrer adapter;
    private String type;
    private String id;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vessel_one);
        ivBack_vessel_one= (ImageView) findViewById(R.id.ivBack_vessel_one);
        ivBack_vessel_one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VesselOneActivity.this.finish();
            }
        });
        ivShop_vessel_one= (ImageView) findViewById(R.id.ivShop_vessel_one);
        ivShop_vessel_one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转购物车界面
            }
        });
        Bundle bundle=getIntent().getExtras();
        type=bundle.getString("type");
        id=bundle.getString("id");
        SharedPreferences sp=getSharedPreferences("sumao", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor=sp.edit();
        editor.putString("id",id);
        editor.putString("type",type);
        editor.commit();
        list=new ArrayList<Fragment>();
        list.add(new RealTimeMarketFragment());
        list.add(new MyOfferFragment());
//        if (type.equals("公开竞拍")){
//
//        }
        adapter1=new MyPageAdapter(getSupportFragmentManager());
        adapter1.setData(list);

        ArrayList<String> listString=new ArrayList<String>();
        listString.add("实时行情");
        listString.add("我的报价");
        adapter1.setTitles(listString);

        tab_vessel_one= (TabLayout) findViewById(R.id.tab_vessel_one);
        vp_vessel_one= (ViewPager) findViewById(R.id.vp_vessel_one);
        vp_vessel_one.setAdapter(adapter1);
        tab_vessel_one.setupWithViewPager(vp_vessel_one);

        btZhifu_auction= (Button) findViewById(R.id.btZhifu_auction);
        btZhifu_auction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Popupwindow();
                backgroundAlpha(0.5f);
            }
        });


     //   vp_vessel_one.setupWi

      //  tab_vessel_one.set
    }
    public void Popupwindow(){
        View view=getLayoutInflater().inflate(R.layout.item_popupwindow_auction,null);
        PopupWindow popupWindow=new PopupWindow(findViewById(R.id.Layout_c), ActionBarOverlayLayout.LayoutParams.MATCH_PARENT, ActionBarOverlayLayout.LayoutParams.WRAP_CONTENT);
        popupWindow.setContentView(view);
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
        adapter=new PopupWindowAdaptrer(list_pop,VesselOneActivity.this);
        lv.setAdapter(adapter);
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

        popupWindow.showAtLocation(ivShop_vessel_one, Gravity.BOTTOM,0,0);

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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SharedPreferences sp=getSharedPreferences("sumao", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor=sp.edit();
        editor.remove("id");
        editor.remove("type");
        editor.commit();

    }
}

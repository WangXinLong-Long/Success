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
import android.widget.Toast;

import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.adapter.MyPageAdapter;
import com.silianchuangye.sumao.success.adapter.PopupWindowAdaptrer;
import com.silianchuangye.sumao.success.utils.SuMaoConstant;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

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
    private List<OpenAuction> list1;



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
//        final List<OpenAuction> list_pop=new ArrayList<OpenAuction>();
//        OpenAuction openauction1=new OpenAuction();
//        openauction1.iv_icon=R.mipmap.direct;
//        openauction1.tv_Name="北京工商银行";
//        openauction1.tv_money="1234";
//        list_pop.add(openauction1);
//        OpenAuction openauction2=new OpenAuction();
//        openauction2.iv_icon=R.mipmap.vertet;
//        openauction2.tv_Name="北京建设银行";
//        openauction2.tv_money="1234";
//        list_pop.add(openauction2);
//        Log.d("changdu",list_pop.size()+"");
//        adapter=new PopupWindowAdaptrer(list_pop,VesselOneActivity.this);
//        lv.setAdapter(adapter);
        getinfo_Bank();
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

        popupWindow.showAtLocation(ivShop_vessel_one, Gravity.BOTTOM,0,0);

        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                //popupWindow.dismiss();
                backgroundAlpha(1f);
            }
        });

    }
    public void getinfo_Bank(){
        new Thread(){
            @Override
            public void run() {
                // super.run();
                String url= SuMaoConstant.SUMAO_IP+"rest/model/atg/commerce/catalog/ProductCatalogActor/availableBank";
                RequestParams rp=new RequestParams(url);
                rp.addParameter("productId",id);
                Log.d("银行列表的rp",""+rp);
                x.http().post(rp, new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        Log.d("银行的列表",result);
                        if (result.contains("amount")){
                            try {
                                list1=new ArrayList<OpenAuction>();
                                JSONObject obj=new JSONObject(result);
                                String message=obj.getString("bankList");
                                JSONArray array=new JSONArray(message);
                                for (int i=0;i<array.length();i++){
                                    JSONObject obj_array=array.getJSONObject(i);
                                    OpenAuction auction=new OpenAuction();
                                    auction.tv_money=obj_array.getString("balance");
                                    String type=obj_array.getString("bankType");
                                    if (type.equals("1")){
                                        //平安
                                        auction.iv_icon=R.mipmap.pingan;
                                        auction.tv_Name="平安银行";

                                    }else if (type.equals("2")){
                                        //昆仑
                                        auction.iv_icon=R.mipmap.kunlun;
                                        auction.tv_Name="昆仑银行";
                                    }else if (type.equals("3")){
                                        //建行
                                        auction.iv_icon=R.mipmap.jianshe;
                                        auction.tv_Name="中国建设银行";
                                    }
                                    list1.add(auction);

                                }
                                adapter=new PopupWindowAdaptrer(list1,VesselOneActivity.this);
                                lv.setAdapter(adapter);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }else{
                            Toast.makeText(VesselOneActivity.this, "该用户没有登录,无法获取可支付银行列表!", Toast.LENGTH_SHORT).show();
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

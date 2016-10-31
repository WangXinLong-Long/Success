package com.silianchuangye.sumao.success.fragments.homepage.auction;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
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
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.adapter.PopupWindowAdaptrer;
import com.silianchuangye.sumao.success.utils.SuMaoConstant;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

public class VesselTwoActivity extends AppCompatActivity {
    private ListView lv_vessel_tow;
    private List<Map<String,Object>> list;
    private ImageView ivBack_vessel_tow;
    private ImageView ivShop_vessel_tow;
    private Button btZhifu_auction;
    private TextView tv;
    private EditText et;
    private ListView lv;
    private PopupWindowAdaptrer adapter;
    private List<OpenAuction> list1;
    private String id;
    private String state;
    private LinearLayout layout_action,layout_not_action;
    private RelativeLayout layout_action_end;
    private Button bt_jinpai_colse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vessel_two);
        layout_action= (LinearLayout) findViewById(R.id.layout_action);
        layout_not_action= (LinearLayout) findViewById(R.id.layout_not_action);
        layout_action_end= (RelativeLayout) findViewById(R.id.layout_action_end);
        bt_jinpai_colse= (Button) findViewById(R.id.bt_jinpai_colse);
        Bundle bundle=getIntent().getExtras();
        id=bundle.getString("id");
        state=bundle.getString("state");

         if (state.equals("竞拍未开始")){
             layout_action.setVisibility(View.GONE);
             layout_action_end.setVisibility(View.GONE);
             layout_not_action.setVisibility(View.VISIBLE);

         }else if (state.equals("竞拍已开始")){
             layout_action.setVisibility(View.VISIBLE);
             layout_not_action.setVisibility(View.GONE);
             layout_action_end.setVisibility(View.GONE);

         }else if (state.equals("竞拍已结束")){
             layout_action.setVisibility(View.GONE);
             layout_not_action.setVisibility(View.GONE);
             layout_action_end.setVisibility(View.VISIBLE);

         }
        bt_jinpai_colse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Popupwindow();
                backgroundAlpha(0.5f);
            }
        });

        btZhifu_auction= (Button) findViewById(R.id.btZhifu_auction);
        btZhifu_auction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Popupwindow();
                backgroundAlpha(0.5f);
            }
        });
        lv_vessel_tow= (ListView) findViewById(R.id.lv_vessel_tow);
        list=new ArrayList<Map<String,Object>>();
        Map<String,Object> map1=new Hashtable<String,Object>();
        map1.put("text","支付保证金比例");
        map1.put("value","10%");
        list.add(map1);

        Map<String,Object> map2=new Hashtable<String,Object>();
        map2.put("text","支付保证金截止时间");
        map2.put("value","2015-02-09 12:00:00");
        list.add(map2);
        Map<String,Object> map3=new Hashtable<String,Object>();
        map3.put("text","支付保证金金额");
        map3.put("value","1000");
        list.add(map3);
        Map<String,Object> map4=new Hashtable<String,Object>();
        map4.put("text","仓库");
        map4.put("value","金山广安库");
        list.add(map4);
        Map<String,Object> map5=new Hashtable<String,Object>();
        map5.put("text","详细地址");
        map5.put("value","金山石化沪杭公路夏盛路38号");
        list.add(map5);
        Map<String,Object> map6=new Hashtable<String,Object>();
        map6.put("text","交货时间");
        map6.put("value","2016-05-19 00:00:00 至2016-05-31 00:00:00");
        list.add(map6);
        Map<String,Object> map7=new Hashtable<String,Object>();
        map7.put("text","竞价方式");
        map7.put("value","公开竞价");
        list.add(map7);
        Map<String,Object> map8=new Hashtable<String,Object>();
        map8.put("text","竞价时间");
        map8.put("value","2016-05-19 00:00:00 至2016-05-31 00:00:00");
        list.add(map8);
        Map<String,Object> map9=new Hashtable<String,Object>();
        map9.put("text","竞价规则");
        map9.put("value","拍卖过程中，竞拍产品的价格按最小加价单位递增，买方可多次出价并能实时获取市场信息...");
        list.add(map9);
        Map<String,Object> map10=new Hashtable<String,Object>();
        map10.put("text","允许延时");
        map10.put("value","是");
        list.add(map10);
        Map<String,Object> map11=new Hashtable<String,Object>();
        map11.put("text","延长时间");
        map11.put("value","4分钟");
        list.add(map11);
        Map<String,Object> map12=new Hashtable<String,Object>();
        map12.put("text","延时规则");
        map12.put("value","在剩余数量小于最小购买数量时，竞价进入倒计时， \n" +
                "当新报价打破本次倒计时触发时的有效价格排序， \n" +
                "再次触发倒计时 \n" +
                "4 分钟 ");
        list.add(map12);
        Map<String,Object> map13=new Hashtable<String,Object>();
        map13.put("text","成交价格判定");
        map13.put("value","按自报价");
        list.add(map13);

        SimpleAdapter adapter=new SimpleAdapter(this,list,R.layout.item_vessel_tow,new String[]{"text","value"},new int[]{R.id.tv_a,R.id.tv_b});
        lv_vessel_tow.setAdapter(adapter);

        ivBack_vessel_tow= (ImageView) findViewById(R.id.ivBack_vessel_two);
        ivBack_vessel_tow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VesselTwoActivity.this.finish();
            }
        });
        ivBack_vessel_tow= (ImageView) findViewById(R.id.ivShop_vessel_two);
        ivBack_vessel_tow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转购物车界面
            }
        });
        lv_vessel_tow.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(VesselTwoActivity.this,VlaueActivity.class);
                intent.putExtra("text",list.get(position).get("text").toString());
                intent.putExtra("value",list.get(position).get("value").toString());
                startActivity(intent);
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
//
//        adapter=new PopupWindowAdaptrer(list_pop,VesselTwoActivity.this);
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

        popupWindow.showAtLocation(lv_vessel_tow, Gravity.BOTTOM,0,0);

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
                                adapter=new PopupWindowAdaptrer(list1,VesselTwoActivity.this);
                                lv.setAdapter(adapter);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }else {
                            Toast.makeText(VesselTwoActivity.this, "该用户没有登录,无法获取可支付银行列表!", Toast.LENGTH_SHORT).show();
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

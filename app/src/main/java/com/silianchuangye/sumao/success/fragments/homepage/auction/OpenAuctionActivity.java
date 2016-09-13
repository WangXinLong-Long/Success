package com.silianchuangye.sumao.success.fragments.homepage.auction;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.silianchuangye.sumao.success.HX.Constant;
import com.silianchuangye.sumao.success.MainActivity;
import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.adapter.PopupWindowAdaptrer;
import com.silianchuangye.sumao.success.dialog.Ok_Dialog;
import com.silianchuangye.sumao.success.fragments.bean.ChinaNorth_Margin_info;
import com.silianchuangye.sumao.success.fragments.homepage.goodInStock.GoodsInStockDetailActivityMVP.bean.CLAttribute;
import com.silianchuangye.sumao.success.fragments.homepage.sumaoconsultMVP.SuMaoConsult;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.login.LoginUserActivity;
import com.silianchuangye.sumao.success.utils.SuMaoConstant;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

public class OpenAuctionActivity extends AppCompatActivity {
    private ListView lv_auction;
    private List<Map<String,Object>> list;
    private Button btZhifu_auction,bt_jinpai,bt_non_jingpai,bt_jinpai_colse;
    private TextView tv,tvTime,tvTimeValue;
    private EditText et;
    private ListView lv;
    private ImageView ivBack;
    private ImageView gouwuche;
    int i,j,k;
    EditText ed_shuzhi_min,ed_shuzhi_price,ed_shuzhi;
    private PopupWindow popupWindow;
    private String id_value,type_way;

    private boolean flag;
    private PopupWindowAdaptrer adapter;
    private RelativeLayout Layout_Button_Open,Layout_Button_close,layout_non;
    private PopupWindowAdaptrer adapter1;
    private TextView tvRemark_auction;
    private List<OpenAuction> list1;
    private String path;
    private ArrayList<CLAttribute> cl_attribute=new ArrayList<CLAttribute>();
    private TextView namechanpin,price,sheng,type,qigou,cangku,bianjiadanwei,diqu,time,company,cangkuaddress,way;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_auction);
        bt_jinpai_colse= (Button) findViewById(R.id.bt_jinpai_colse);
        Layout_Button_close= (RelativeLayout) findViewById(R.id.Layout_Button_close);
        Layout_Button_Open= (RelativeLayout) findViewById(R.id.Layout_Button_Open);
        layout_non= (RelativeLayout) findViewById(R.id.layout_non);
        bt_jinpai= (Button) findViewById(R.id.bt_jinpai);
        Bundle bundle=getIntent().getExtras();
        String name=bundle.getString("name");
        id_value=bundle.getString("id");
        type_way=bundle.getString("type");
        Log.d("id的值",id_value);
        Log.d("type的值",type_way);
        namechanpin= (TextView) findViewById(R.id.tvName_auction);
        price= (TextView) findViewById(R.id.tvPrice_auction);
        sheng= (TextView) findViewById(R.id.surplus_amount_et);
        qigou= (TextView) findViewById(R.id.purchase_quantity_et);
        bianjiadanwei= (TextView) findViewById(R.id.min_variable_et);
        time= (TextView) findViewById(R.id.delivery_time_et);
        cangkuaddress= (TextView) findViewById(R.id.warehouse_address_et);
        way= (TextView) findViewById(R.id.delivery_mode_et);
        type= (TextView) findViewById(R.id.classification_pre_sale_et);
        cangku= (TextView) findViewById(R.id.warehouse_et);
        diqu= (TextView) findViewById(R.id.region_et);
        company= (TextView) findViewById(R.id.company_et);
        tvRemark_auction= (TextView) findViewById(R.id.tvRemark_auction);
        new Thread(){
            @Override
            public void run() {
                super.run();
                getAcution();
                isSuccessorFail();
            }
        }.start();

        bt_jinpai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //显示popupwindow
                showPopupWindow();
                backgroundAlpha(0.5f);
            }
        });
       // bt_non_jingpai= (Button) findViewById(R.id.bt_non_jingpai);
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

        bt_jinpai_colse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Popupwindow();
                backgroundAlpha(0.5f);
            }
        });
        tvTimeValue= (TextView) findViewById(R.id.tvTimeValue);
        tvTime= (TextView) findViewById(R.id.tvTime);

        if (name.equals("竞拍未开始")){
            Layout_Button_Open.setVisibility(View.GONE);
            Layout_Button_close.setVisibility(View.VISIBLE);
            layout_non.setVisibility(View.INVISIBLE);
        }else if (name.equals("竞拍已结束")){
            tvTime.setVisibility(View.GONE);
            tvTimeValue.setText("竞拍已结束");
            tvTimeValue.setTextColor(getResources().getColor(R.color.gray_type));
            bt_jinpai.setBackgroundColor(getResources().getColor(R.color.gray_type));
        }
        lv_auction= (ListView) findViewById(R.id.lv_auction);
        list=new ArrayList<Map<String,Object>>();
        Map<String,Object> map1=new Hashtable<String,Object>();
        map1.put("text","实时行情、我的报价");
        list.add(map1);
        Map<String,Object> map2=new Hashtable<String,Object>();
        map2.put("text","交易规则");
        list.add(map2);
        Map<String,Object> map3=new Hashtable<String,Object>();
        map3.put("text","合同详情、产品参数");
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
                    SharedPreferences sp=getSharedPreferences("sumao", Activity.MODE_PRIVATE);
                    String name=sp.getString("name","");
                    Log.d("是否登录判断",name);
                    if (name==""){
                        //没有登录状态
                        Intent intent=new Intent(OpenAuctionActivity.this, LoginUserActivity.class);
                        startActivity(intent);
                    }else {
                        //登录状态
                        Intent intent=new Intent(OpenAuctionActivity.this,VesselOneActivity.class);
                        intent.putExtra("id",id_value);
                        intent.putExtra("type",type_way);
                        startActivity(intent);
                    }

                }else if(position==1){
                        Intent intent=new Intent(OpenAuctionActivity.this,VesselTwoActivity.class);
                        intent.putExtra("id",id_value);
                        startActivity(intent);



                }else if(position==2){
                    Intent intent=new Intent(OpenAuctionActivity.this,VesselThreeActivity.class);
                    intent.putExtra("title","团购");
                    intent.putExtra("cl_attribute",cl_attribute);
                    intent.putExtra("contract",path);
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
                    adapter1.notifyDataSetChanged();
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
    public void showPopupWindow(){
        View view=getLayoutInflater().inflate(R.layout.jingpai_baojia,null);
        final PopupWindow popupWindow=new PopupWindow(findViewById(R.id.Layout_c), ActionBarOverlayLayout.LayoutParams.MATCH_PARENT, ActionBarOverlayLayout.LayoutParams.WRAP_CONTENT);
        popupWindow.setContentView(view);
        TextView tv_Name= (TextView) view.findViewById(R.id.tv_Name);
        ImageView iv_error= (ImageView) view.findViewById(R.id.iv_error);
        TextView tv_price= (TextView) view.findViewById(R.id.tv_price);
        /**
         * 可接受最小成交数量
         */
        TextView tv_jia_min= (TextView) view.findViewById(R.id.tv_jia_min);
        TextView tv_jian_min= (TextView) view.findViewById(R.id.tv_jian_min);
        ed_shuzhi_min= (EditText) view.findViewById(R.id.ed_shuzhi_min);
        ed_shuzhi_min.setText("10");
        Log.d("edit的默认值",ed_shuzhi_min.getText().toString());

        tv_jia_min.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i=Integer.parseInt(ed_shuzhi_min.getText().toString());
                i=i+1;
                ed_shuzhi_min.setText(i+"");
            }
        });
        tv_jian_min.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                int j=i-1;
//                ed_shuzhi_min.setText(j+"");
                i=Integer.parseInt(ed_shuzhi_min.getText().toString());
                i=i+1;
                ed_shuzhi_min.setText(i+"");
            }
        });
        /**
         *
         * 竞拍单价
         */
        TextView tv_jia_price= (TextView) view.findViewById(R.id.tv_jia_price);
        TextView tv_jian_price= (TextView) view.findViewById(R.id.tv_jian_price);
         ed_shuzhi_price= (EditText) view.findViewById(R.id.ed_shuzhi_price);
        ed_shuzhi_price.setText("10");
        tv_jia_price.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                j=Integer.parseInt(ed_shuzhi_price.getText().toString());
                j=j+1;
                ed_shuzhi_price.setText(""+j);

            }
        });
        tv_jian_price.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                j=Integer.parseInt(ed_shuzhi_price.getText().toString());
                j=j-1;
                ed_shuzhi_price.setText(""+j);
            }
        });
        /**
         * 竞拍数量
         */
        TextView tv_jia= (TextView) view.findViewById(R.id.tv_jia_price);
        TextView tv_jian= (TextView) view.findViewById(R.id.tv_jian_price);
         ed_shuzhi= (EditText) view.findViewById(R.id.ed_shuzhi_price);
        ed_shuzhi.setText("10");
        tv_jia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                k=Integer.parseInt(ed_shuzhi.getText().toString());
                k=k+1;
                ed_shuzhi.setText(""+k);
            }
        });
        tv_jian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                k=Integer.parseInt(ed_shuzhi.getText().toString());
                k=k-1;
                ed_shuzhi.setText(""+k);
            }
        });
        Button bt_lijibaojia= (Button) view.findViewById(R.id.bt_lijibaojia);
        bt_lijibaojia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
                ZhifuPopupwindow();
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
    //支付时的popupwindow
    public void ZhifuPopupwindow(){
        View view=getLayoutInflater().inflate(R.layout.item_popupwindow_auction,null);

        popupWindow=new PopupWindow(findViewById(R.id.Layout_c), ActionBarOverlayLayout.LayoutParams.MATCH_PARENT, ActionBarOverlayLayout.LayoutParams.WRAP_CONTENT);
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
                    adapter1.notifyDataSetChanged();
                }
            }
        });
        Button bt= (Button) view.findViewById(R.id.btZhifu);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
                Intent intent=new Intent(OpenAuctionActivity.this,Ok_Dialog.class);
              //  intent.putExtra("number",tv_order_number1.getText().toString());
                startActivity(intent);

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
    public void getAcution(){
        String url="http://192.168.32.126:7023/rest/model/atg/commerce/catalog/ProductCatalogActor/auctionProduct";
        RequestParams rp=new RequestParams(url);
        Log.d("id的值",id_value);
        rp.addParameter("productId",id_value);
       // rp.addParameter("productId","");
        Log.d("竞拍详情的rp",rp.toString());
        x.http().post(rp, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.d("竞拍详情",result);
                try {
                    JSONObject obj_result = new JSONObject(result);

                    namechanpin.setText(obj_result.getString("cl_mingcheng").toString());
                    price.setText(obj_result.getString("cl_qipai"));
                    sheng.setText(obj_result.getString("cl_zongliang")+"吨");
                    type.setText(obj_result.getString("cl_fenlei"));
                    qigou.setText(obj_result.getString("cl_qigou")+"吨");
                    cangku.setText(obj_result.getString("cl_cangku"));
                    bianjiadanwei.setText(obj_result.getString("cl_xbianliang")+"吨");
                    diqu.setText(obj_result.getString("cl_diqu"));
                    cangkuaddress.setText(obj_result.getString("cl_xbianjia").toString()+"元");
                    Log.d("zuixia",obj_result.getString("cl_xbianjia").toString());
                    company.setText(obj_result.getString("cl_gongsi"));

                    String peisong_way=obj_result.getString("cl_fangshi");
                    int len=peisong_way.length()-2;
                    String aa=peisong_way.substring(2,len);
                    Log.d("配送方式",aa);
                    way.setText(aa);
                    String shuxing=obj_result.getString("cl_attribute");
                    Log.d("属性",shuxing+"aaaaaaaaaaaaaaaaaaaaaa");
                    JSONArray jay=new JSONArray(shuxing);
                    for(int i=0;i<jay.length();i++){
                        JSONObject jsonObject=jay.getJSONObject(i);
                        CLAttribute attribute=new CLAttribute();
                        attribute.setAttrName(jsonObject.getString("attrName"));
                        attribute.setAttrValue(jsonObject.getString("attrValue"));
                        cl_attribute.add(attribute);

                    }
                    Log.d("属性的条目",cl_attribute.size()+"");
                    path=obj_result.getString("contract");

                    //cl_attribute.addAll();

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
    public void getinfo_Bank(){
        new Thread(){
            @Override
            public void run() {
               // super.run();
                String url="http://192.168.32.126:7023/rest/model/atg/commerce/catalog/ProductCatalogActor/availableBank";
                RequestParams rp=new RequestParams(url);
                rp.addParameter("productId",id_value);
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
                                adapter1=new PopupWindowAdaptrer(list1,OpenAuctionActivity.this);
                                lv.setAdapter(adapter1);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }else {
                            Toast.makeText(OpenAuctionActivity.this, "该用户没有登录,无法获取可支付银行列表!", Toast.LENGTH_SHORT).show();
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
    public void isSuccessorFail(){
        String url= SuMaoConstant.SUMAO_IP+"/rest/model/atg/commerce/catalog/ProductCatalogActor/depositState";
        RequestParams rp=new RequestParams(url);
        rp.addParameter("productId",id_value);
        x.http().post(rp, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                if (result.contains("deposit_state")){
                    try {
                        JSONObject obj=new JSONObject(result);
                        String shifou=obj.getString("deposit_state");
                        String Message=obj.getString("msg");
                        String Success=obj.getString("flag");
                        Log.d("aaaaaa状态",shifou);
                        Log.d("aaaaaa信息",Message);
                        Log.d("aaaaaa是否成功",Success);

                    } catch (JSONException e) {
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





}

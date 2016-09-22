package com.silianchuangye.sumao.success.fragments.homepage.groupbuying;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ActionBarOverlayLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.adapter.PopupWindowAdaptrer;
import com.silianchuangye.sumao.success.fragments.homepage.auction.OpenAuction;
import com.silianchuangye.sumao.success.fragments.homepage.goodInStock.GoodsInStockDetailActivityMVP.bean.CLAttribute;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GroupBuyingSuccessActivity extends AppCompatActivity {
    private ListView lvDemo;
    private SimpleAdapter adapter;
    private List<Map<String,Object>> list;

    private TextView tv_success;
    private TextView tv_failed;
    private LinearLayout aaa;
    private RelativeLayout layout_number;
    private RelativeLayout layout_Bottom;
    private String Shangpinid;
    private TextView name;
    private TextView price;
    private TextView count;
    private TextView qigou;
    private TextView bianjia;
    private TextView bianliang;
    private TextView cangku;
    private TextView way;
    private TextView comm;
    private TextView type;
    private TextView add;
    private RelativeLayout jian;
    private RelativeLayout jia;
    private EditText number;
    private TextView count_price;
    private String path;
    private ArrayList<CLAttribute> cl_attribute=new ArrayList<CLAttribute>();
    private String bili,jiezhishijian,can_gku,xiangxi,kaishishijian,jieshushijian,tuan_start,tuan_end;
    private LinearLayout linear_tuangou_time,layoutService,linear_tuagou_line;
    private RelativeLayout relative_tuangou_end;
    private Button btn_tuangou_add;
    private PopupWindow popupWindow;
    private View popView;
    private TextView tv_pop_price,tv_pop_num,tv_pop_fenlei,tv_pop_start_num,
              tv_pop_type,tv_pop_small_null,tv_pop_diqu,tv_pop_cangku,
              tv_pop_gongsi,tv_pop_add,tv_pop_sub;
    private EditText edt_pop_num;
    private Button btn_pop_ok;
    private List<OpenAuction> list1;
    private PopupWindowAdaptrer popAdapter;
    private String strbianliang,strprice;//
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_buying_success);
        init();
        getinfo();
        event();
        addData();
    }
    public void init(){
        Bundle bundle=getIntent().getExtras();
        String state=bundle.getString("state");
        Shangpinid=bundle.getString("id");
        lvDemo= (ListView) findViewById(R.id.lv_demo);
        list=new ArrayList<Map<String,Object>>();
        tv_failed= (TextView) findViewById(R.id.tv_failed);
        tv_success= (TextView) findViewById(R.id.tv_success);
        aaa= (LinearLayout) findViewById(R.id.aaa);
        layout_number= (RelativeLayout) findViewById(R.id.layout_number);
        layout_Bottom= (RelativeLayout) findViewById(R.id.layout_Bottom);
        relative_tuangou_end= (RelativeLayout) findViewById(R.id.relative_tuangou_end);
        linear_tuangou_time= (LinearLayout) findViewById(R.id.linear_tuangou_time);
        layoutService= (LinearLayout) findViewById(R.id.layoutService);
        linear_tuagou_line= (LinearLayout) findViewById(R.id.linear_tuagou_line);
        if (state.equals("no")){
            tv_success.setVisibility(View.GONE);
            tv_failed.setVisibility(View.VISIBLE);
            aaa.setVisibility(View.GONE);
            layout_number.setVisibility(View.GONE);
            layout_Bottom.setVisibility(View.INVISIBLE);
            relative_tuangou_end.setVisibility(View.VISIBLE);
            linear_tuangou_time.setVisibility(View.GONE);
            layoutService.setVisibility(View.GONE);
            linear_tuagou_line.setVisibility(View.GONE);
        }
        name= (TextView) findViewById(R.id.tvName_auction);
        price= (TextView) findViewById(R.id.tvPrice_auction);
        count= (TextView) findViewById(R.id.surplus_amount_et);
        qigou= (TextView) findViewById(R.id.purchase_quantity_et);
        bianliang= (TextView) findViewById(R.id.min_variable_et);
        cangku= (TextView) findViewById(R.id.warehouse_address_et);
        way= (TextView) findViewById(R.id.delivery_mode_et);
        type= (TextView) findViewById(R.id.classification_pre_sale_et);
        bianjia= (TextView) findViewById(R.id.warehouse_et);
        add= (TextView) findViewById(R.id.region_et);
        comm= (TextView) findViewById(R.id.company_et);
        jian= (RelativeLayout) findViewById(R.id.layout_bb);
        jia= (RelativeLayout) findViewById(R.id.sa);
        number= (EditText) findViewById(R.id.ed_shuzhi_number);
        count_price= (TextView) findViewById(R.id.tv_count_value);
        btn_tuangou_add= (Button) findViewById(R.id.btn_tuangou_add);
        btn_tuangou_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //弹出popwindow
                showPopWindow();
                backgroundAlpha(0.5f);
            }
        });
        count_price.setText(10+"");
    }
    public void event(){
        lvDemo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position==0){
                    Intent intent=new Intent(GroupBuyingSuccessActivity.this,RuleActivity.class);
                    intent.putExtra("bili",bili);
                    intent.putExtra("jiezhi",jiezhishijian);
                    intent.putExtra("cangku",can_gku);
                    intent.putExtra("xiangxi",xiangxi);
                    intent.putExtra("kaishi",kaishishijian);
                    intent.putExtra("jieshu",jieshushijian);
                    intent.putExtra("tuan_start",tuan_start);
                    intent.putExtra("tuan_end",tuan_end);
                    startActivity(intent);
                }else if (position==1){
                    Intent intent=new Intent(GroupBuyingSuccessActivity.this,JoinActivity.class);
                    intent.putExtra("id",Shangpinid);
                    startActivity(intent);

                }else if (position==2){

                }
            }
        });
        jia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str=number.getText().toString();
                Log.d("number的值",str+"");
//                String bb=bianliang.getText().toString();
                int aa=Integer.parseInt(str);
                Log.e("TAG","strbinaliang=------"+strbianliang);
                int cc=Integer.parseInt(strbianliang);
                aa=aa+cc;
                number.setText(aa+"");
                count_price.setText((Integer.parseInt(number.getText().toString())*Integer.parseInt(strprice))+"");
            }
        });
        jian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str=number.getText().toString();
//                String bb=bianliang.getText().toString();
                int aa=Integer.parseInt(str);
                int cc=Integer.parseInt(strbianliang);
                aa=aa-cc;
                if (aa<=0){
                    aa=1;
//                    number.setText("1");
//                    count_price.setText(price.getText());
                }
                number.setText(aa+"");
                count_price.setText((Integer.parseInt(number.getText().toString())*Integer.parseInt(strprice))+"");
            }
        });


    }
    public void addData(){
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("name","交易规则");
        list.add(map);
        Map<String,Object> map1=new HashMap<String,Object>();
        map1.put("name","我的参团记录");
        list.add(map1);
        Map<String,Object> map2=new HashMap<String,Object>();
        map2.put("name","合同详情、产品参数");
        list.add(map2);

        adapter=new SimpleAdapter(this,list,R.layout.item_open_auction,
                new String[]{"name"},
                new int[]{R.id.tvRule_auction});
        lvDemo.setAdapter(adapter);


    }
    public void getinfo(){
        new Thread(){
            @Override
            public void run() {
                super.run();
                String url="http://192.168.32.126:7023/rest/model/atg/commerce/catalog/ProductCatalogActor/groupProduct";
                RequestParams rp=new RequestParams(url);
                rp.addParameter("productId",Shangpinid);
                Log.d("rp的值",rp+"");
                x.http().post(rp, new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        Log.d("团购商品的详情页",result);
                        try {
                            JSONObject obj=new JSONObject(result);
                            tv_success.setText("剩余团购时间:"+obj.getString("utilDate"));
                            name.setText(obj.getString("cl_mingcheng"));
                            strprice=obj.getString("cl_jine");
                            price.setText(obj.getString("cl_jine")+"元");
                            count.setText(obj.getString("cl_zongliang")+"吨");
                            qigou.setText(obj.getString("cl_qigou")+"吨");
                            strbianliang=obj.getString("cl_xbianliang");
                            bianliang.setText(obj.getString("cl_xbianliang")+"吨");
                            cangku.setText(obj.getString("cl_cangku"));
                            way.setText(obj.getString("cl_fangshi"));
                            type.setText(obj.getString("cl_fenlei"));
                            bianjia.setText(obj.getString("cl_xbianjia"));
                            add.setText(obj.getString("cl_diqu"));
                            comm.setText(obj.getString("cl_gongsi"));
                            String shuxing=obj.getString("cl_attribute");
                            Log.d("刷新",shuxing+"aaaaaaaaaa");
                            JSONArray array=new JSONArray(shuxing);
                            for (int i=0;i<array.length();i++){
                                JSONObject obj_array=array.getJSONObject(i);
                                CLAttribute attribute=new CLAttribute();
                                attribute.setAttrName(obj_array.getString("attrName"));
                                attribute.setAttrValue(obj_array.getString("attrValue"));
                                cl_attribute.add(attribute);
                            }
                            path=obj.getString("contract");
                            bili=obj.getString("cl_baozhj");
                           // jiezhishijian=obj.getString("");
                            jiezhishijian="2016-09-09 12:00";
                            can_gku=obj.getString("cl_cangku");
                            xiangxi=obj.getString("addressDetail");
                            kaishishijian=obj.getString("cl_shijian");
                            jieshushijian=obj.getString("cl_shijianend");
                            tuan_start=obj.getString("groupStartDate");
                            tuan_end=obj.getString("groupEndDate");

                        } catch (JSONException e) {
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
    private void showPopWindow(){
        popView=View.inflate(this,R.layout.pop_tuagou_add,null);
        tv_pop_price= (TextView)popView.findViewById(R.id.tvPrice_auction);//价钱
        tv_pop_num= (TextView) popView.findViewById(R.id.surplus_amount_et);//团购总量
        tv_pop_fenlei= (TextView) popView.findViewById(R.id.classification_pre_sale_et);//分类
        tv_pop_start_num= (TextView) popView.findViewById(R.id.purchase_quantity_et);//起购量
        tv_pop_type= (TextView) popView.findViewById(R.id.warehouse_et);//交货方式
        tv_pop_small_null= (TextView) popView.findViewById(R.id.min_variable_et);//最小变量单位
        tv_pop_diqu= (TextView) popView.findViewById(R.id.region_et);//地区
        tv_pop_cangku= (TextView) popView.findViewById(R.id.delivery_time_et);//仓库
        tv_pop_gongsi= (TextView)popView.findViewById(R.id.warehouse_address_et);//公司
        tv_pop_add= (TextView) popView.findViewById(R.id.img_item_cart_buy_add);//加号
        tv_pop_sub= (TextView) popView.findViewById(R.id.img_item_cart_buy_sub);//减号
        edt_pop_num= (EditText)popView.findViewById(R.id.tv_item_cart_buy_num);//中间变化的数量
        btn_pop_ok= (Button) popView.findViewById(R.id.determine_buy_immediately_button);//确定
        popView.measure(0, 0);
        int w = getWindowManager().getDefaultDisplay().getWidth();
        popupWindow = new PopupWindow(popView, w, popView.getMeasuredHeight());
        popupWindow.setFocusable(true);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.showAtLocation(btn_tuangou_add, Gravity.BOTTOM, 0, 0);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                backgroundAlpha(1f);
            }
        });
        tv_pop_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str=edt_pop_num.getText().toString();
                int num=Integer.valueOf(str);
                num++;
                edt_pop_num.setText(num+"");
            }
        });
        tv_pop_sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str=edt_pop_num.getText().toString();
                int num=Integer.valueOf(str);
                num--;
                if(num<0){
                    num=0;
                }
                edt_pop_num.setText(num+"");
            }
        });

        btn_pop_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //支付保证金popwindow
                popupWindow.dismiss();
                showpop();
                backgroundAlpha(0.5f);

            }
        });
    }
    public void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = this.getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        this.getWindow().setAttributes(lp);
    }
    private ListView lv;
    //支付保证金popwindow
    private void showpop(){
        View view=getLayoutInflater().inflate(R.layout.pop_yushou,null);
        PopupWindow popupWindow=new PopupWindow(findViewById(R.id.Layout_c), ActionBarOverlayLayout.LayoutParams.MATCH_PARENT, ActionBarOverlayLayout.LayoutParams.WRAP_CONTENT);
        popupWindow.setContentView(view);
       TextView tv= (TextView) view.findViewById(R.id.tv_pay);
//        et= (EditText) view.findViewById(R.id.etZhifu_auction);
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
                    adapter.notifyDataSetChanged();
                }
            }
        });
        Button bt= (Button) view.findViewById(R.id.btZhifu);
        //支付
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        popupWindow.setTouchable(true);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.setOutsideTouchable(true);
        popupWindow.setFocusable(true);

        popupWindow.showAtLocation(btn_tuangou_add, Gravity.BOTTOM,0,0);

        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                //popupWindow.dismiss();
                backgroundAlpha(1f);
            }
        });

    }
    //获取银行列表接口
    public void getinfo_Bank(){

        new Thread(){
            @Override
            public void run() {
                // super.run();
                String url="http://192.168.32.126:7023/rest/model/atg/commerce/catalog/ProductCatalogActor/availableBank";
                RequestParams rp=new RequestParams(url);
                rp.addParameter("productId",Shangpinid);
                Log.d("银行列表的rp",""+rp);
                Log.e("TAG","rp------"+rp);
                x.http().post(rp, new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        Log.d("银行的列表",result);
                        Log.e("TAG","result-----"+result);
                        if (result.contains("amount")){
                            try{
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
                                popAdapter=new PopupWindowAdaptrer(list1,GroupBuyingSuccessActivity.this);
                                lv.setAdapter(adapter);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }else {
                            Toast.makeText(GroupBuyingSuccessActivity.this, "该用户没有登录,无法获取可支付银行!", Toast.LENGTH_SHORT).show();
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

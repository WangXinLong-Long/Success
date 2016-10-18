package com.silianchuangye.sumao.success.fragments.homepage.groupbuying;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.BitmapDrawable;
import android.os.CountDownTimer;
import android.provider.ContactsContract;
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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.adapter.MyPageAdapter;
import com.silianchuangye.sumao.success.adapter.PopupWindowAdaptrer;
import com.silianchuangye.sumao.success.dialog.Error_Dialog;
import com.silianchuangye.sumao.success.dialog.Ok_Dialog;
import com.silianchuangye.sumao.success.fragments.homepage.auction.OpenAuction;
import com.silianchuangye.sumao.success.fragments.homepage.auction.VesselThreeActivity;
import com.silianchuangye.sumao.success.fragments.homepage.goodInStock.GoodsInStockDetailActivityMVP.bean.CLAttribute;
import com.silianchuangye.sumao.success.fragments.homepage.preSale.PreSaleMVP.bean.Group;
import com.silianchuangye.sumao.success.utils.SuMaoConstant;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

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
    private String strbianliang,strprice="";//
    private ImageView img;
    private TextView tv_pro_now;
    private ProgressBar pbDemo;
    private MyCount mc;
    TextView tv_all_num,tv_tuangou_time,tv_tuangou_line;
    RelativeLayout relative_tuangou;
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
        pbDemo= (ProgressBar) findViewById(R.id.pbDemo);
        pbDemo.setMax(100);
        img= (ImageView) findViewById(R.id.ivBack);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        Bundle bundle=getIntent().getExtras();
//        String state=bundle.getString("state");
        Shangpinid=bundle.getString("id");
        Log.e("TAG","shangp----"+Shangpinid);
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
        tv_pro_now= (TextView) findViewById(R.id.tv_pro_now);//当前显示的进度
        tv_all_num= (TextView) findViewById(R.id.tv_all_num);
        tv_tuangou_time= (TextView) findViewById(R.id.tv_tuangou_time);
        btn_tuangou_add= (Button) findViewById(R.id.btn_tuangou_add);
        relative_tuangou= (RelativeLayout) findViewById(R.id.relative_tuangou);
         tv_tuangou_line= (TextView) findViewById(R.id.tv_tuangou_line);
//        if (state.equals("no")){
//            tv_tuangou_line.setVisibility(View.VISIBLE);
//            relative_tuangou.setVisibility(View.GONE);
//            tv_success.setVisibility(View.GONE);
//            tv_failed.setVisibility(View.GONE);
//            aaa.setVisibility(View.GONE);
//            layout_number.setVisibility(View.GONE);
//            layout_Bottom.setVisibility(View.INVISIBLE);
//            relative_tuangou_end.setVisibility(View.VISIBLE);
//            linear_tuangou_time.setVisibility(View.GONE);
//            layoutService.setVisibility(View.VISIBLE);
//            linear_tuagou_line.setVisibility(View.GONE);
//            tv_tuangou_line.setVisibility(View.GONE);
//            tv_tuangou_time.setVisibility(View.GONE);
//        }
//        if(state.equals("ok1")){
//            relative_tuangou.setVisibility(View.VISIBLE);
//            tv_tuangou_time.setVisibility(View.GONE);
//            layoutService.setVisibility(View.GONE);
//            tv_tuangou_line.setVisibility(View.GONE);
//        }if(state.equals("ok2")){//未开始
//            tv_tuangou_line.setVisibility(View.VISIBLE);
//            relative_tuangou.setVisibility(View.GONE);
//            aaa.setVisibility(View.GONE);
//            layout_number.setVisibility(View.GONE);
//            layout_Bottom.setVisibility(View.GONE);
//            relative_tuangou_end.setVisibility(View.VISIBLE);
//            linear_tuangou_time.setVisibility(View.GONE);
//            layoutService.setVisibility(View.VISIBLE);
//            linear_tuagou_line.setVisibility(View.GONE);
//            tv_tuangou_time.setVisibility(View.VISIBLE);
//        }

        name= (TextView) findViewById(R.id.tvName_auction);
        price= (TextView) findViewById(R.id.tvPrice_auction);
        count= (TextView) findViewById(R.id.surplus_amount_et);
        qigou= (TextView) findViewById(R.id.purchase_quantity_et);
        bianliang= (TextView) findViewById(R.id.min_variable_et);
        cangku= (TextView) findViewById(R.id.warehouse_address_et);
        way= (TextView) findViewById(R.id.delivery_mode_et);
        type= (TextView) findViewById(R.id.classification_pre_sale_et);
//        bianjia= (TextView) findViewById(R.id.warehouse_et);
        add= (TextView) findViewById(R.id.region_et);
        comm= (TextView) findViewById(R.id.company_et);
        jian= (RelativeLayout) findViewById(R.id.layout_bb);
        jia= (RelativeLayout) findViewById(R.id.sa);
        number= (EditText) findViewById(R.id.ed_shuzhi_number);
        count_price= (TextView) findViewById(R.id.tv_count_value);

        btn_tuangou_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //弹出popwindow
                showPopWindow();
                backgroundAlpha(0.5f);
            }
        });
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
                    Intent intent=new Intent(GroupBuyingSuccessActivity.this,VesselThreeActivity.class);
                    intent.putExtra("title","竞拍");
                    intent.putExtra("cl_attribute",cl_attribute);
                    intent.putExtra("contract",path);
                    startActivity(intent);

                }
            }
        });
        jia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str=number.getText().toString();
                Log.d("number的值",str+"");
//                String bb=bianliang.getText().toString();
                double aa=Double.parseDouble(str);
                double cc=Double.parseDouble(strbianliang);
                aa=aa+cc;
                number.setText(aa+"");
                count_price.setText((Double.parseDouble(number.getText().toString())*Double.parseDouble(strprice))+"");
            }
        });
        jian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str=number.getText().toString();
//                String bb=bianliang.getText().toString();
                Double aa=Double.parseDouble(str);
                Double cc=Double.parseDouble(strbianliang);
                aa=aa-cc;
                if (aa<=cc){
                    aa=cc;
//                    number.setText("1");
//                    count_price.setText(price.getText());
                }
                number.setText(aa+"");
                count_price.setText((Double.parseDouble(number.getText().toString())*Double.parseDouble(strprice))+"");
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
                String url=SuMaoConstant.SUMAO_IP+"/rest/model/atg/commerce/catalog/ProductCatalogActor/groupProduct";
                RequestParams rp=new RequestParams(url);
                rp.addParameter("productId",Shangpinid);
                Log.e("TAG",Shangpinid);
                Log.d("rp的值",rp+"");
                x.http().post(rp, new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        Log.d("团购商品的详情页",result);
                        try {
                            JSONObject obj=new JSONObject(result);
                            String utilDate=obj.getString("utilDate");
                            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                            long time=0l;
                            try {
                                long strdate=dateFormat.parse(utilDate).getTime();
                                long nowTime=System.currentTimeMillis();
                                time=strdate-nowTime;
                                mc=new MyCount(time,1000);
                                mc.start();
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
//                            tv_success.setText(finaltime);
//                            tv_success.setText("剩余团购时间:"+obj.getString("utilDate"));

                            name.setText(obj.getString("cl_mingcheng"));
                            strprice=obj.getString("cl_jine");
                            price.setText(obj.getString("cl_jine")+"元");
                            count.setText(obj.getString("cl_zongliang")+"吨");
                            qigou.setText(obj.getString("cl_qigou")+"吨");
                            strbianliang=obj.getString("cl_xbianliang");
                            //默认购买数量
                            number.setText(obj.getString("cl_qigou")+"");
                            //默认总价
                            count_price.setText((Double.parseDouble(number.getText().toString())*Double.parseDouble(strprice))+"");
                            bianliang.setText(obj.getString("cl_xbianliang")+"吨");
                            cangku.setText(obj.getString("cl_cangku"));
                            String fangshi=obj.getString("cl_fangshi");
                            String str=fangshi.substring(2,fangshi.length()-2);
                            way.setText(str);
                            type.setText(obj.getString("cl_fenlei"));
//                            String bian=obj.getString("cl_xbianjia");
//                            bianjia.setText(obj.getString("cl_xbianjia"));//最小变价
                            add.setText(obj.getString("cl_diqu"));
                            comm.setText(obj.getString("cl_gongsi"));
//
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
                            String sumAddQty=obj.getString("sumAddQty");//数量
                            String groupPercent=obj.getString("groupPercent");//比例

                            int i= (int) (Double.valueOf(groupPercent)*100);
                            tv_pro_now.setText("当前成团量为 : "+i+"%");
                            tv_all_num.setText(obj.getString("cl_zongliang")+"t");
                            pbDemo.setProgress(i);
                            count_price.setText(Integer.valueOf(number.getText().toString())*Integer.valueOf(strprice)+"");
                            String state=obj.getString("section");
                            Log.e("TAG","state="+state);
                            if (state.equals("2")){
                                tv_tuangou_line.setVisibility(View.VISIBLE);
                                relative_tuangou.setVisibility(View.GONE);
                                tv_success.setVisibility(View.GONE);
                                tv_failed.setVisibility(View.GONE);
                                aaa.setVisibility(View.GONE);
                                layout_number.setVisibility(View.GONE);
                                layout_Bottom.setVisibility(View.INVISIBLE);
                                relative_tuangou_end.setVisibility(View.VISIBLE);
                                linear_tuangou_time.setVisibility(View.GONE);
                                layoutService.setVisibility(View.VISIBLE);
                                linear_tuagou_line.setVisibility(View.GONE);
                                tv_tuangou_line.setVisibility(View.GONE);
                                tv_tuangou_time.setVisibility(View.GONE);
                            }
                            if(state.equals("1")){
                                relative_tuangou.setVisibility(View.VISIBLE);
                                tv_tuangou_time.setVisibility(View.GONE);
                                layoutService.setVisibility(View.GONE);
                                tv_tuangou_line.setVisibility(View.GONE);
                                linear_tuangou_time.setVisibility(View.VISIBLE);
                            }if(state.equals("0")){//未开始
                                tv_tuangou_line.setVisibility(View.VISIBLE);
                                relative_tuangou.setVisibility(View.GONE);
                                aaa.setVisibility(View.GONE);
                                layout_number.setVisibility(View.GONE);
                                layout_Bottom.setVisibility(View.GONE);
                                relative_tuangou_end.setVisibility(View.VISIBLE);
                                linear_tuangou_time.setVisibility(View.GONE);
                                layoutService.setVisibility(View.VISIBLE);
                                linear_tuagou_line.setVisibility(View.GONE);
                                tv_tuangou_time.setVisibility(View.VISIBLE);
                            }

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
        edt_pop_num.setText(number.getText().toString());
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
                double num=Double.valueOf(str);
                double cc=Double.parseDouble(strbianliang);
                num+=cc;
                edt_pop_num.setText(num+"");
                tv_pop_price.setText(Double.valueOf(edt_pop_num.getText().toString())*Double.valueOf(strprice)+"");
                //
                number.setText(edt_pop_num.getText().toString());
                count_price.setText(Double.valueOf(number.getText().toString())*Double.valueOf(strprice)+"");
            }
        });
        tv_pop_sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str=edt_pop_num.getText().toString();
                double num=Double.valueOf(str);
                double cc=Double.parseDouble(strbianliang);
                num-=cc;
                if(num<cc){
                    num=cc;
                }
                edt_pop_num.setText(num+"");
                tv_pop_price.setText(Double.valueOf(edt_pop_num.getText().toString())*Double.valueOf(strprice)+"");
                //
                number.setText(edt_pop_num.getText().toString());
                count_price.setText(Double.valueOf(number.getText().toString())*Double.valueOf(strprice)+"");
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
        popDate();
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
        final PopupWindow popupWindow=new PopupWindow(findViewById(R.id.Layout_c), ActionBarOverlayLayout.LayoutParams.MATCH_PARENT, ActionBarOverlayLayout.LayoutParams.WRAP_CONTENT);
        popupWindow.setContentView(view);
       TextView tv= (TextView) view.findViewById(R.id.tv_pay);
//        et= (EditText) view.findViewById(R.id.etZhifu_auction);
        lv= (ListView) view.findViewById(R.id.lv_popupwindow_auction);
        list1=new ArrayList<OpenAuction>();
        getinfo_Bank();
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(parent.getId()==lv.getId()){
                    for(int i=0;i<list1.size();i++){
                        if(i!=position){
                            list1.get(i).Flag=false;
                        }
                    }
                    list1.get(position).Flag=!list1.get(position).Flag;
                    popAdapter.notifyDataSetChanged();
                }
            }
        });
        Button bt= (Button) view.findViewById(R.id.btZhifu);
        //支付
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO 这里应该是支付保证金金额的网络请求
                //先假写一下供测试
                if(list1.size()!=0) {
                    for (int i = 0; i < list1.size(); i++) {
                        if (list1.get(i).Flag) {
                            int j = 0;
                            if (j == 0) {
                                Intent intent = new Intent(GroupBuyingSuccessActivity.this, Ok_Dialog.class);
                                intent.putExtra("number", 1324);
                                intent.putExtra("type", "");
                                startActivity(intent);
                                popupWindow.dismiss();
                            } else {
                                Intent intent = new Intent(GroupBuyingSuccessActivity.this, Error_Dialog.class);
                                intent.putExtra("number", 1324);
                                startActivity(intent);
                            }
                        }else {
                            Toast.makeText(GroupBuyingSuccessActivity.this, "请选择支付银行", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
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
                String url= SuMaoConstant.SUMAO_IP+"/rest/model/atg/commerce/catalog/ProductCatalogActor/availableBank";
                RequestParams rp=new RequestParams(url);
                rp.addParameter("productId",Shangpinid);
                Log.d("银行列表的rp",""+rp);
                x.http().post(rp, new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        Log.d("银行的列表",result);
                        Log.e("TAG","result-----"+result);

                        try {
                            JSONObject obj = new JSONObject(result);
                            String info=obj.getString("info");
                            if(info.equals("fail")){
                                Toast.makeText(GroupBuyingSuccessActivity.this, "该用户没有登录,无法获取可支付银行!", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                        if (result.contains("amount")){
                            try{

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
                                lv.setAdapter(popAdapter);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }else {
//                            Toast.makeText(GroupBuyingSuccessActivity.this, "该用户没有登录,无法获取可支付银行!", Toast.LENGTH_SHORT).show();
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

    private void popDate(){
        edt_pop_num.setText(number.getText().toString());
        String str,str2;
        if(edt_pop_num.getText().toString().equals("")){
            str=0+"";
        }else{
            str=edt_pop_num.getText().toString();
        }
        if(strprice.equals("")){
            str2="0";
        }else{
            str2=strprice;
        }
        tv_pop_price.setText(Double.valueOf(str)*Double.valueOf(str2)+"");
       //TODO
        tv_pop_num.setText("");//剩余数量
        tv_pop_fenlei.setText(type.getText().toString());
        tv_pop_start_num.setText(qigou.getText().toString());
        //TODO
        tv_pop_type.setText("");//交易方式
        tv_pop_small_null.setText(bianliang.getText().toString());
        tv_pop_diqu.setText(add.getText().toString());
        tv_pop_cangku.setText(cangku.getText().toString());
        tv_pop_gongsi.setText(comm.getText().toString());
        //
    }
    class MyCount extends CountDownTimer {
        public MyCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }
        @Override
        public void onFinish() {
            tv_success.setText("finish");
        }
        @Override
        public void onTick(long millisUntilFinished) {
            SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
            long time=millisUntilFinished;
//            long time=millisUntilFinished- TimeZone.getDefault().getRawOffset();
            long mSec = time % 1000;
            time /= 1000;
            long year = time/(365*24*3600);
            time = time%(365*24*3600);
            long month = time/(30*24*3600);
            time = time % (30*24*3600);
            long day = time/(24*3600);
            time = time % (24*3600);
            long hour = time/3600;
            time = time % 3600;
            long min = time/60;
            time = time % 60;
            long sec = time;
//            Log.e("TAG","daojishi------"+day+"天"+hour+"小时"+min+"分"+sec+"秒");
            String finaltime=day+"天"+hour+"小时"+min+"分"+sec+"秒";
            tv_success.setText(finaltime);
            tv_tuangou_time.setText("距离开团时间剩余："+finaltime);
        }
    }

}

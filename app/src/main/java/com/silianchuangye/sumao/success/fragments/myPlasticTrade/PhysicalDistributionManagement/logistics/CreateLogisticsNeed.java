package com.silianchuangye.sumao.success.fragments.myPlasticTrade.PhysicalDistributionManagement.logistics;

import android.app.DatePickerDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.media.MediaBrowserCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.api.GoogleApiClient;
import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.adapter.Create_LogisticsNeed_Adapter;
import com.silianchuangye.sumao.success.fragments.bean.Create_Logistics_NeedInfo;
import com.silianchuangye.sumao.success.fragments.bean.Createlogistics_ListInfo;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.receiptAddress.AddAddressMVP.presenter.AddAddressPresenter;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.receiptAddress.AddAddressMVP.view.IAddAddress;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.receiptAddress.SelectProvinceAreaMVP.view.SelectProvinceArea;
import com.silianchuangye.sumao.success.utils.SuMaoConstant;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class CreateLogisticsNeed extends AppCompatActivity implements View.OnClickListener,AdapterView.OnItemClickListener,IAddAddress {
    private ImageView img_logistics_title_back, img_logistics_need_select_xunbang,
            img_logistics_need_select_buy, img_logistics_need_select_maifang;
    private TextView tv_logistics_need_canle;
    //本次发货数量和仓库名称
    private TextView tv_logistics_need_fahuo_num, tv_logistics_need_cangku_name;
    private RelativeLayout relative_time,relative_logistics_need_address,
            relative_logistics_need_shouhuo_address, relative_logistics_need_message_address;
    private LinearLayout relative_logistics_need_three;
    private Button btn_logistics_need_ok,btn_logistics_need_kefu;
    private ListView lv_logistics_need,lv_logistics_need_three;
    private TextView tv_logistics_need_give_address,tv_logistics_need_address_message;
    private List<Create_Logistics_NeedInfo> list=new ArrayList<Create_Logistics_NeedInfo>();
    private List<Create_Logistics_NeedInfo> list2=new ArrayList<Create_Logistics_NeedInfo>();
    private Create_LogisticsNeed_Adapter adapter;
    private Create_LogisticsNeed_Adapter adapter2;
    private MyReciver my=new MyReciver();
    String cangku,startTime,address,megList;
    double num;
    String strType="";
    Create_Logistics_NeedInfo info1,info2,info3,info4,info5,info6,info7,
            infoLv1,infoLv2,infoLv3,infoLv4,infoLv5,infoLv6;
    private StringBuilder sb;
    String start,end,address_num,address_people,address_phoneNum;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_logistics_need);
        num=getIntent().getDoubleExtra("num",-100);
        Log.e("TAG","num--"+num);
        cangku=getIntent().getStringExtra("cangku");
        startTime=getIntent().getStringExtra("date");
        megList=getIntent().getStringExtra("list");
        strType=getIntent().getStringExtra("logistic");
        initDate();
        initView();
    }

    private void initDate() {
       info1 =new Create_Logistics_NeedInfo();
        info1.tv_start="*";
        info1.tv_second="收货联系人";
        info1.tv_three="";
         info2=new Create_Logistics_NeedInfo();
        info2.tv_start="*";
        info2.tv_second="联系方式";
        info2.tv_three="";
         info3=new Create_Logistics_NeedInfo();
        info3.tv_start="*";
        info3.tv_second="期望提货时间";
        info3.tv_three="";
         info4=new Create_Logistics_NeedInfo();
        info4.tv_second="收货公司";
        info4.tv_three="";
         info5=new Create_Logistics_NeedInfo();
        info5.tv_second="托运联系人";
        info5.tv_three="";
         info6=new Create_Logistics_NeedInfo();
        info6.tv_second="托运联系人方式";
        info6.tv_three="";
         info7=new Create_Logistics_NeedInfo();
        info7.tv_second="备注";
        info7.tv_three="";
        list.add(info1);
        list.add(info2);
        list.add(info3);
        list.add(info4);
        list.add(info5);
        list.add(info6);
//        list.add(info7);

        infoLv1=new Create_Logistics_NeedInfo();
        infoLv1.tv_start="*";
        infoLv1.tv_second="提货车号";
        infoLv1.tv_three="";
        infoLv2=new Create_Logistics_NeedInfo();
        infoLv2.tv_start="*";
        infoLv2.tv_second="提货人";
        infoLv2.tv_three="";
       infoLv3=new Create_Logistics_NeedInfo();
        infoLv3.tv_start="*";
        infoLv3.tv_second="联系方式";
        infoLv3.tv_three="";
        infoLv4=new Create_Logistics_NeedInfo();
        infoLv4.tv_start="*";
        infoLv4.tv_second="提货人身份证号";
        infoLv4.tv_three="";
        infoLv5=new Create_Logistics_NeedInfo();
        infoLv5.tv_second="备注";
        infoLv5.tv_three="";
        infoLv6=new Create_Logistics_NeedInfo();
        infoLv6.tv_start="*";
        infoLv6.tv_second="提货时间";
        infoLv6.tv_three="";
        list2.add(infoLv1);
        list2.add(infoLv2);
        list2.add(infoLv3);
        list2.add(infoLv6);
        list2.add(infoLv4);
        list2.add(infoLv5);
    }

    private void initView() {

        IntentFilter filter=new IntentFilter();
        filter.addAction("select");
        registerReceiver(my,filter);
        img_logistics_title_back = (ImageView) findViewById(R.id.img_logistics_title_bar_back);
        tv_logistics_need_canle= (TextView) findViewById(R.id.tv_logistics_title_bar);
        img_logistics_need_select_xunbang = (ImageView) findViewById(R.id.img_logistics_need_select_xunbang);
        img_logistics_need_select_buy = (ImageView) findViewById(R.id.img_logistics_need_select_buy);
        img_logistics_need_select_maifang = (ImageView) findViewById(R.id.img_logistics_need_select_maifang);
        tv_logistics_need_fahuo_num = (TextView) findViewById(R.id.tv_logistics_need_fahuo_num);
        tv_logistics_need_fahuo_num.setText(num+"");
        tv_logistics_need_cangku_name = (TextView) findViewById(R.id.tv_logistics_need_cangku_name);
        tv_logistics_need_cangku_name.setText(cangku);
        relative_logistics_need_three= (LinearLayout) findViewById(R.id.linear_need_three);
        relative_logistics_need_address= (RelativeLayout) findViewById(R.id.relative_logistics_need_address);
        relative_logistics_need_shouhuo_address= (RelativeLayout) findViewById(R.id.relative_logistics_need_shouhuo_address);
        relative_logistics_need_message_address= (RelativeLayout) findViewById(R.id.relative_logistics_need_message_address);
        btn_logistics_need_ok= (Button) findViewById(R.id.btn_logistics_need_ok);
        lv_logistics_need= (ListView) findViewById(R.id.lv_logistics_need);
        btn_logistics_need_kefu= (Button) findViewById(R.id.btn_logistics_need_kefu);
        lv_logistics_need_three= (ListView) findViewById(R.id.lv_logistics_need_three);
        tv_logistics_need_give_address= (TextView) findViewById(R.id.tv_logistics_need_give_address);

        tv_logistics_need_address_message= (TextView) findViewById(R.id.tv_logistics_need_message_address);
        img_logistics_title_back.setOnClickListener(this);
        tv_logistics_need_canle.setOnClickListener(this);
        btn_logistics_need_kefu.setOnClickListener(this);
        btn_logistics_need_ok.setOnClickListener(this);
        relative_logistics_need_address.setOnClickListener(this);
        relative_logistics_need_message_address.setOnClickListener(this);
        relative_logistics_need_shouhuo_address.setOnClickListener(this);
        img_logistics_need_select_maifang.setOnClickListener(this);
        img_logistics_need_select_buy.setOnClickListener(this);
        img_logistics_need_select_xunbang.setOnClickListener(this);
        lv_logistics_need.setOnItemClickListener(this);
        lv_logistics_need_three.setOnItemClickListener(this);
        adapter=new Create_LogisticsNeed_Adapter(list,this);
        lv_logistics_need.setAdapter(adapter);

        adapter2=new Create_LogisticsNeed_Adapter(list2,this);
        lv_logistics_need_three.setAdapter(adapter2);
        setListViewHeightBasedOnChildren(lv_logistics_need);//测量listview的高度
        setListViewHeightBasedOnChildren(lv_logistics_need_three);
        Log.e("TAG","strtype--"+strType);
        if(strType.equals("自提")){
            showImg(img_logistics_need_select_buy);
        }else if(strType.equals("卖家配送")){
            Log.e("TAG","应该进的");
            showImg(img_logistics_need_select_maifang);
        }else{
            showImg(img_logistics_need_select_xunbang);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_logistics_title_bar_back:
                finish();
                break;
            case R.id.tv_logistics_title_bar:
                finish();
                break;
            case R.id.btn_logistics_need_kefu:
                Toast.makeText(this,"点击了客服",Toast.LENGTH_SHORT).show();
                break;
//            case R.id.img_logistics_need_select_xunbang:
//                Toast.makeText(this,"迅帮配送",Toast.LENGTH_SHORT).show();
//                showImg(img_logistics_need_select_xunbang);
//                break;
//            case R.id.img_logistics_need_select_buy:
//                Toast.makeText(this,"买家自提",Toast.LENGTH_SHORT).show();
//               showImg(img_logistics_need_select_buy);
//                break;
//            case R.id.img_logistics_need_select_maifang:
//                Toast.makeText(this,"卖家配送",Toast.LENGTH_SHORT).show();
//                showImg(img_logistics_need_select_maifang);
//                break;
            case R.id.relative_logistics_need_address:
                Toast.makeText(this,"选择已有地址",Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(this,Logistics_SelectAddress.class);
                startActivity(intent);
                break;
            case R.id.relative_logistics_need_shouhuo_address:
                Toast.makeText(this,"收货地址",Toast.LENGTH_SHORT).show();
                Intent intent2 = new Intent();
                intent2.putExtra("className","logistics");
                intent2.setClass(CreateLogisticsNeed.this, SelectProvinceArea.class);
                startActivity(intent2);
                break;
            case R.id.relative_logistics_need_message_address:
                Toast.makeText(this,"收货详细地址",Toast.LENGTH_SHORT).show();
                Intent intent1=new Intent(this,logisticsAddress.class);
                intent1.putExtra("title","收货详细地址");
                startActivityForResult(intent1,0);
                break;
            case R.id.btn_logistics_need_ok:
                IsNull();
                break;
        }
    }
    private void showImg(View v){
        switch (v.getId()){
            case R.id.img_logistics_need_select_xunbang:
                strType="迅帮配送";
                lv_logistics_need_three.setVisibility(View.GONE);
                relative_logistics_need_three.setVisibility(View.VISIBLE);
                lv_logistics_need.setVisibility(View.VISIBLE);
                img_logistics_need_select_xunbang.setImageResource(R.mipmap.cart_select);
                img_logistics_need_select_buy.setImageResource(R.mipmap.cart_select_null);
                img_logistics_need_select_maifang.setImageResource(R.mipmap.cart_select_null);
                break;
            case R.id.img_logistics_need_select_maifang:
                strType="卖家配送";
                lv_logistics_need_three.setVisibility(View.GONE);
                lv_logistics_need.setVisibility(View.VISIBLE);
                relative_logistics_need_three.setVisibility(View.VISIBLE);
                img_logistics_need_select_xunbang.setImageResource(R.mipmap.cart_select_null);
                img_logistics_need_select_buy.setImageResource(R.mipmap.cart_select_null);
                img_logistics_need_select_maifang.setImageResource(R.mipmap.cart_select);
                break;
            case R.id.img_logistics_need_select_buy:
                strType="自提";
                lv_logistics_need_three.setVisibility(View.VISIBLE);
                lv_logistics_need.setVisibility(View.GONE);
                relative_logistics_need_three.setVisibility(View.GONE);
                img_logistics_need_select_xunbang.setImageResource(R.mipmap.cart_select_null);
                img_logistics_need_select_buy.setImageResource(R.mipmap.cart_select);
                img_logistics_need_select_maifang.setImageResource(R.mipmap.cart_select_null);
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if(parent.getId()==R.id.lv_logistics_need){
            Toast.makeText(this,"点击了第"+position+"条数据",Toast.LENGTH_SHORT).show();
//            if(list.get(position).tv_second.equals("期望提货时间")){
//                showDate();
//                info3.tv_three="日期不能为空";
//                adapter.notifyDataSetChanged();
//            }else {
                Intent intent = new Intent(this, logisticsMesssage.class);
                intent.putExtra("title", list.get(position).tv_second.toString());
                intent.putExtra("buy","1");
                intent.putExtra("time",startTime);
                startActivityForResult(intent, 0);
//            }
        }
        if(parent.getId()==R.id.lv_logistics_need_three){
            Toast.makeText(this,"hehe点击了第"+position+"条数据",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, logisticsMesssage.class);
                intent.putExtra("buy","2");
                intent.putExtra("title", list2.get(position).tv_second.toString());
                intent.putExtra("time",startTime);
                startActivityForResult(intent, 0);
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        address = getIntent().getStringExtra("address");
        sb= new StringBuilder();
        sb.append(address);
        address_num=sb.toString();
        Log.e("TAG","address_num1111111111"+address_num);
        AddAddressPresenter presenter = new AddAddressPresenter(CreateLogisticsNeed.this);
        presenter.setDetailAddress(sb.substring(0, 4), sb.substring(0, 6), sb.toString());
    }

    @Override
    public void setAddressInText(String address) {
        tv_logistics_need_give_address.setText(address);
    }

    @Override
    public void sendAddAddressToServerSuccess() {

    }

    @Override
    public void sendAddAddressToServerFaild() {

    }

    @Override
    public void sendAddAddressToServer() {

    }

    private class MyReciver extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent) {
            String address=intent.getStringExtra("address");
            String address_message=intent.getStringExtra("address_message");
           address_num=intent.getStringExtra("address_number");
             address_people=intent.getStringExtra("address_people");
             address_phoneNum=intent.getStringExtra("address_phoneNum");
            Log.e("TAG","address_num2222222"+address_num);
            tv_logistics_need_give_address.setText(address);
            tv_logistics_need_address_message.setText(address_message);
            info1.tv_three=address_people;
            info2.tv_three=address_phoneNum;
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(my);
    }
//    计算listview高度
    public void setListViewHeightBasedOnChildren(ListView listView) {
        // 获取ListView对应的Adapter

        if (adapter == null) {
            return;
        }

        int totalHeight = 0;
        for (int i = 0, len = adapter.getCount(); i < len; i++) {
            // listAdapter.getCount()返回数据项的数目
            View listItem = adapter.getView(i, null, listView);
            // 计算子项View 的宽高
            listItem.measure(0, 0);
            // 统计所有子项的总高度
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight+ (listView.getDividerHeight() * (adapter.getCount() - 1));
        // listView.getDividerHeight()获取子项间分隔符占用的高度
        // params.height最后得到整个ListView完整显示需要的高度
        listView.setLayoutParams(params);
    }
    //判断是否为空
    private void IsNull(){
        Log.e("TAG","strTyep-------"+strType);
        if(strType.equals("自提")) {
            if (infoLv1.tv_three.length() != 0 && infoLv2.tv_three.length() != 0
                    && infoLv3.tv_three.length() != 0 && infoLv4.tv_three.length() != 0
                    && infoLv6.tv_three.length() != 0) {
                Toast.makeText(this, "提交物流需求", Toast.LENGTH_SHORT).show();
                showCreateNet();
            } else {
                Toast.makeText(CreateLogisticsNeed.this, "带*不能为空", Toast.LENGTH_SHORT).show();
            }
        }else{
            if(info1.tv_three.length()!=0&&info2.tv_three.length()!=0
                    &&info3.tv_three.length()!=0&&tv_logistics_need_address_message.length()!=0
                    &&tv_logistics_need_give_address.length()!=0){
                showCreateNet();
                Toast.makeText(this, "提交物流需求", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(CreateLogisticsNeed.this, "带*不能为空", Toast.LENGTH_SHORT).show();
            }
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        String str="";
        if(data!=null) {
            str = data.getStringExtra("message");
            Log.e("TAG","str-----"+str);
            Log.e("TAG","rescode---"+resultCode);
        }else{

        }
        if(resultCode==0){
            info1.tv_three=str;
        }else if(resultCode==1){
            info2.tv_three=str;
        }else if(resultCode==2){
            start=data.getStringExtra("message1");
            end=data.getStringExtra("message2");
            info3.tv_three=start+"至"+end;
        }else if(resultCode==3){
            info4.tv_three=str;
        }else if(resultCode==4){
            info5.tv_three=str;
        }else if(resultCode==5){
            info6.tv_three=str;
        }else if(resultCode==6){
            info7.tv_three=str;
        }else if(resultCode==10){
            infoLv1.tv_three=str;
        }else if(resultCode==11){
            infoLv2.tv_three=str;
        }else if(resultCode==12){
            infoLv3.tv_three=str;
        }else if(resultCode==13){
            infoLv4.tv_three=str;
        }else if(resultCode==14){
            infoLv5.tv_three=str;
        }else if(resultCode==15){
            infoLv6.tv_three=str;
        }else if(resultCode==99){
            Log.e("TAG","进来了");
            tv_logistics_need_address_message.setText(str);
        }
        adapter2.notifyDataSetChanged();
        adapter.notifyDataSetChanged();
    }
    //创建物流订单网络接口
    private void showCreateNet(){
        RequestParams params=new RequestParams(SuMaoConstant.SUMAO_IP+"/rest/model/atg/commerce/order/logistics/logisticsActor/createNewLogistics");
        params.addParameter("quantity",num);//本次发货数量
        params.addParameter("commerceJson",megList);
        if(strType.equals("自提")){
            params.addParameter("shippingMethod","pickUp");
            params.addParameter("shippingDate",list2.get(3).tv_three);//提货时间
            params.addParameter("deliveryerTel",list2.get(2).tv_three);//联系电话
            params.addParameter("idCard",list2.get(4).tv_three);//联系人身份证
            params.addParameter("wareHouse",cangku);
            params.addParameter("licensePlateNo",list2.get(0).tv_three);
            params.addParameter("deliveryer",list2.get(1).tv_three);
//            params.addParameter("remarks",list2.get(5).tv_three);
            JSONObject job=new JSONObject();
            try {
//                job.put("commerceJson",megList);
//                job.put("wareHouse",cangku);//仓库
//                job.put("licensePlateNo",list2.get(0).tv_three);//提货车号
//                job.put("deliveryer",list2.get(1));//提货人
                job.put("remarks",list2.get(5).tv_three);//备注
            } catch (JSONException e) {
                e.printStackTrace();
            }
            params.setBodyContent(job.toString());
        }else{
            if(strType.equals("卖家配送")){
                params.addParameter("shippingMethod","seller");
            }else{
                params.addParameter("shippingMethod","xunbang");
            }
            params.addParameter("provinceId",address_num.substring(0, 4));
            params.addParameter("cityId",address_num.substring(0, 6));
            params.addParameter("countyId",address_num.toString());
            params.addParameter("contactPhone",list.get(1).tv_three);//联系电话
            params.addParameter("pickUpDateStart",start);
            params.addParameter("pickUpDateEnd",end);
            params.addParameter("shippingContactTel",list.get(5).tv_three);
            params.addParameter("detailAddress",tv_logistics_need_address_message.getText().toString());
            JSONObject job=new JSONObject();
            try {
//                job.put("commerceJson",megList);
                job.put("detailAddress",tv_logistics_need_address_message.getText().toString());//详细地址
                job.put("contactPerson",list.get(0).tv_three);
                job.put("repeiptCompany",list.get(3).tv_three);
                job.put("shippingContact",list.get(4).tv_three);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            params.setBodyContent(job.toString());
        }
        Log.e("TAG","创建物流params-----"+params);
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.e("TAG","result======"+result);
                try {
                    JSONObject job=new JSONObject(result);
                    String status=job.getString("status");
                    if(status.equals("YES")){
                        Toast.makeText(CreateLogisticsNeed.this,"创建物流订单成功",Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.e("TAG","ex------"+ex.toString());
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

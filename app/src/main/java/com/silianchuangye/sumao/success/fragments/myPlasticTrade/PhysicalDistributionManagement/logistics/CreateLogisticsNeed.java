package com.silianchuangye.sumao.success.fragments.myPlasticTrade.PhysicalDistributionManagement.logistics;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.adapter.Create_LogisticsNeed_Adapter;
import com.silianchuangye.sumao.success.fragments.PagerThree;
import com.silianchuangye.sumao.success.fragments.bean.Create_Logistics_NeedInfo;

import java.util.ArrayList;
import java.util.List;

public class CreateLogisticsNeed extends AppCompatActivity implements View.OnClickListener,AdapterView.OnItemClickListener{
    private ImageView img_logistics_title_back, img_logistics_need_select_xunbang,
            img_logistics_need_select_buy, img_logistics_need_select_maifang;
    private TextView tv_logistics_need_canle;
    //本次发货数量和仓库名称
    private TextView tv_logistics_need_fahuo_num, tv_logistics_need_cangku_name;
    private RelativeLayout relative_logistics_need_address,
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_logistics_need);
        initDate();
        initView();
    }

    private void initDate() {
        Create_Logistics_NeedInfo info1=new Create_Logistics_NeedInfo();
        info1.tv_start="*";
        info1.tv_second="收货联系人";
        info1.tv_three="张三";
        Create_Logistics_NeedInfo info2=new Create_Logistics_NeedInfo();
        info2.tv_start="*";
        info2.tv_second="联系电话";
        info2.tv_three="1230945832";
        Create_Logistics_NeedInfo info3=new Create_Logistics_NeedInfo();
        info3.tv_start="*";
        info3.tv_second="期望提货时间";
        info3.tv_three="2016-05-23至2016-05-25";
        Create_Logistics_NeedInfo info4=new Create_Logistics_NeedInfo();
        info4.tv_second="收货公司";
        info4.tv_three="四联创业集团";
        Create_Logistics_NeedInfo info5=new Create_Logistics_NeedInfo();
        info5.tv_second="托运联系人";
        info5.tv_three="张四";
        Create_Logistics_NeedInfo info6=new Create_Logistics_NeedInfo();
        info6.tv_second="托运联系人方式";
        info6.tv_three="1232132113";
        Create_Logistics_NeedInfo info7=new Create_Logistics_NeedInfo();
        info7.tv_second="备注";
        info7.tv_three="危险物品，易燃易爆";
        list.add(info1);
        list.add(info2);
        list.add(info3);
        list.add(info4);
        list.add(info5);
        list.add(info6);
        list.add(info7);

        Create_Logistics_NeedInfo infoLv1=new Create_Logistics_NeedInfo();
        infoLv1.tv_start="*";
        infoLv1.tv_second="提货车号";
        infoLv1.tv_three="京A123456";
        Create_Logistics_NeedInfo infoLv2=new Create_Logistics_NeedInfo();
        infoLv2.tv_start="*";
        infoLv2.tv_second="提货人";
        infoLv2.tv_three="张三";
        Create_Logistics_NeedInfo infoLv3=new Create_Logistics_NeedInfo();
        infoLv3.tv_start="*";
        infoLv3.tv_second="联系方式";
        infoLv3.tv_three="1231231231";
        Create_Logistics_NeedInfo infoLv4=new Create_Logistics_NeedInfo();
        infoLv4.tv_start="*";
        infoLv4.tv_second="提货人身份证号";
        infoLv4.tv_three="123123123192837489";
        Create_Logistics_NeedInfo infoLv5=new Create_Logistics_NeedInfo();
        infoLv5.tv_second="备注";
        infoLv5.tv_three="危险品，易燃易爆";
        list2.add(infoLv1);
        list2.add(infoLv2);
        list2.add(infoLv3);
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
        tv_logistics_need_cangku_name = (TextView) findViewById(R.id.tv_logistics_need_cangku_name);
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
//        setListViewHeightBasedOnChildren(lv_logistics_need);
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
            case R.id.img_logistics_need_select_xunbang:
                Toast.makeText(this,"迅帮配送",Toast.LENGTH_SHORT).show();
                showImg(img_logistics_need_select_xunbang);
                break;
            case R.id.img_logistics_need_select_buy:
                Toast.makeText(this,"买家自提",Toast.LENGTH_SHORT).show();
               showImg(img_logistics_need_select_buy);
                break;
            case R.id.img_logistics_need_select_maifang:
                Toast.makeText(this,"卖家配送",Toast.LENGTH_SHORT).show();
                showImg(img_logistics_need_select_maifang);
                break;
            case R.id.relative_logistics_need_address:
                Toast.makeText(this,"选择已有地址",Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(this,Logistics_SelectAddress.class);
                startActivity(intent);
                break;
            case R.id.relative_logistics_need_shouhuo_address:
                Toast.makeText(this,"收货地址",Toast.LENGTH_SHORT).show();
                break;
            case R.id.relative_logistics_need_message_address:
                Toast.makeText(this,"收货详细地址",Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_logistics_need_ok:
                Toast.makeText(this,"提交物流需求",Toast.LENGTH_SHORT).show();
                break;
        }
    }
    private void showImg(View v){
        switch (v.getId()){
            case R.id.img_logistics_need_select_xunbang:
                lv_logistics_need_three.setVisibility(View.GONE);
                relative_logistics_need_three.setVisibility(View.VISIBLE);
                img_logistics_need_select_xunbang.setImageResource(R.mipmap.cart_select);
                img_logistics_need_select_buy.setImageResource(R.mipmap.cart_select_null);
                img_logistics_need_select_maifang.setImageResource(R.mipmap.cart_select_null);
                break;
            case R.id.img_logistics_need_select_maifang:
                lv_logistics_need_three.setVisibility(View.GONE);
                relative_logistics_need_three.setVisibility(View.VISIBLE);
                img_logistics_need_select_xunbang.setImageResource(R.mipmap.cart_select_null);
                img_logistics_need_select_buy.setImageResource(R.mipmap.cart_select_null);
                img_logistics_need_select_maifang.setImageResource(R.mipmap.cart_select);
                break;
            case R.id.img_logistics_need_select_buy:
                lv_logistics_need_three.setVisibility(View.VISIBLE);
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
        }
        if(parent.getId()==R.id.lv_logistics_need_three){
            Toast.makeText(this,"hehe点击了第"+position+"条数据",Toast.LENGTH_SHORT).show();
        }
    }
    private class MyReciver extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent) {
            String address=intent.getStringExtra("address");
            String address_message=intent.getStringExtra("address_message");
            tv_logistics_need_give_address.setText(address);
            tv_logistics_need_address_message.setText(address_message);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(my);
    }
    //计算listview高度
//    public void setListViewHeightBasedOnChildren(ListView listView) {
//        // 获取ListView对应的Adapter
//
//        if (adapter == null) {
//            return;
//        }
//
//        int totalHeight = 0;
//        for (int i = 0, len = adapter.getCount(); i < len; i++) {
//            // listAdapter.getCount()返回数据项的数目
//            View listItem = adapter.getView(i, null, listView);
//            // 计算子项View 的宽高
//            listItem.measure(0, 0);
//            // 统计所有子项的总高度
//            totalHeight += listItem.getMeasuredHeight();
//        }
//
//        ViewGroup.LayoutParams params = listView.getLayoutParams();
//        params.height = totalHeight+ (listView.getDividerHeight() * (adapter.getCount() - 1));
//        // listView.getDividerHeight()获取子项间分隔符占用的高度
//        // params.height最后得到整个ListView完整显示需要的高度
//        listView.setLayoutParams(params);
//    }
}

package com.silianchuangye.sumao.success.fragments.homepage.auction;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.adapter.PopupWindowAdaptrer;
import com.silianchuangye.sumao.success.fragments.homepage.auction.AuctionMVP.bean.AuctionDetailBean;

import java.text.SimpleDateFormat;
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
    private AuctionDetailBean auctionDetailBean;
    private String auctionMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vessel_two);
        layout_action= (LinearLayout) findViewById(R.id.layout_action);
        layout_not_action= (LinearLayout) findViewById(R.id.layout_not_action);
        layout_action_end= (RelativeLayout) findViewById(R.id.layout_action_end);
        bt_jinpai_colse= (Button) findViewById(R.id.bt_jinpai_colse);
        LinearLayout Layout_Button_vessel_two = (LinearLayout) findViewById(R.id.Layout_Button_vessel_two);
        Layout_Button_vessel_two.setVisibility(View.GONE);
        auctionDetailBean = (AuctionDetailBean)getIntent().getSerializableExtra("auctionDetailBean");
        auctionMode = getIntent().getStringExtra("auctionMode");
        String zhifujine = getIntent().getStringExtra("zhifujine");

        lv_vessel_tow= (ListView) findViewById(R.id.lv_vessel_tow);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        list=new ArrayList<Map<String,Object>>();
        Map<String,Object> map1=new Hashtable<String,Object>();
        if (auctionDetailBean.getCl_baozhj().equals("%")){
            map1.put("value","--");
        }else {
            map1.put("value",auctionDetailBean.getCl_baozhj());
        }
        map1.put("text","保证金比例");

        list.add(map1);

        Map<String,Object> map2=new Hashtable<String,Object>();
        map2.put("text","保证金支付截止时间");
        if (auctionDetailBean.getCl_baozhj().equals("%")){
            map2.put("value","--");
        }else {
            map2.put("value",simpleDateFormat.format(Double.parseDouble(auctionDetailBean.getDepositDeadline().getTime())));
        }
        list.add(map2);
        Map<String,Object> map3=new Hashtable<String,Object>();
        map3.put("text","当前保证金金额保证金金额");
        if (auctionDetailBean.getCl_baozhj().equals("%")){
            map3.put("value","--");
        }else {
            map3.put("value",zhifujine);
        }
        list.add(map3);
        Map<String,Object> map4=new Hashtable<String,Object>();
        map4.put("text","仓库");
        map4.put("value",auctionDetailBean.getCl_cangku());
        list.add(map4);
        Map<String,Object> map5=new Hashtable<String,Object>();
        map5.put("text","详细地址");
        if (auctionDetailBean.getAddressDetail()== null){
            map5.put("value","");
        }else {
            map5.put("value",auctionDetailBean.getAddressDetail());
        }
        list.add(map5);
        Map<String,Object> map6=new Hashtable<String,Object>();
        map6.put("text","交货时间");
        map6.put("value",new SimpleDateFormat("yyyy-MM-dd").format(Double.parseDouble(auctionDetailBean.getCl_shijian()))+"至"+new SimpleDateFormat("yyyy-MM-dd").format(Double.parseDouble(auctionDetailBean.getCl_shijianend())));
        list.add(map6);
        Map<String,Object> map7=new Hashtable<String,Object>();
        map7.put("text","竞拍方式");
        map7.put("value",auctionMode);
        list.add(map7);
        Map<String,Object> map8=new Hashtable<String,Object>();
        map8.put("text","竞拍时间");
        map8.put("value",simpleDateFormat.format(Double.parseDouble(auctionDetailBean.getStartDate()))+"至"+simpleDateFormat.format(Double.parseDouble(auctionDetailBean.getEndDate())));
        list.add(map8);
        Map<String,Object> map9=new Hashtable<String,Object>();
        map9.put("text","竞拍规则");
        String rule = "";
        if (auctionDetailBean.getAuctionRuleType() == null){
            rule = "--";
        }else if (auctionDetailBean.getAuctionRuleType().equals("englishAuctionRule-allow-surplus")){
            rule ="拍卖过程中，竞拍产品的价格按最小加价单位递增，买方可多次出价并能实时获取市场信息";
        }
        map9.put("value",rule);
        list.add(map9);
        Map<String,Object> map10=new Hashtable<String,Object>();
//        允许延时
        String runTimeDelay = "否";
        if (auctionDetailBean.getAuctionRuleType() == null){
            runTimeDelay = "--";
        }else if (auctionDetailBean.getAuctionRuleType().equals("englishAuctionRule-allow-fixed") || auctionDetailBean.getAuctionRuleType().equals("englishAuctionRule-allow-surplus")){
            runTimeDelay = "是";
        }
        map10.put("text","允许延时");
        map10.put("value",runTimeDelay);
        list.add(map10);
//        延时时长
        String delayTimeLength = "";
//        延时规则
        String delayRule = "";
        if (auctionDetailBean.getAuctionRuleType() == null){
            delayTimeLength = "--";
            delayRule = "--";
        }else if(auctionDetailBean.getAuctionRuleType().equals("englishAuctionRule-allow-fixed")){
            delayTimeLength = auctionDetailBean.getOvertimePeriod()+"分钟";
            delayRule = "初始"+auctionDetailBean.getFreeBiddingTime()+"分钟内不延时，当新报价在剩余"+auctionDetailBean.getTriggerOvertimePeriod()+"分钟内提交时，触发延时"+auctionDetailBean.getOvertimePeriod()+"分钟";
        }else if (auctionDetailBean.getAuctionRuleType().equals("englishAuctionRule-allow-surplus")){
            if(auctionDetailBean.getDelayDateNode().equals("")){
                delayTimeLength = auctionDetailBean.getOvertimePeriod()+"分钟";
            }else{
                if( auctionDetailBean.getDelayDateNode().equals(auctionDetailBean.getOvertimePeriod()) ){
                    delayTimeLength = auctionDetailBean.getDdnOvertimePeriod()+"分钟";
                }else{
                    delayTimeLength = auctionDetailBean.getDelayDateNode()+"前"+auctionDetailBean.getDdnOvertimePeriod()+"分钟"+
                            auctionDetailBean.getDelayDateNode()+"后"+auctionDetailBean.getOvertimePeriod()+"分钟";
                }
            }
            delayRule = "在竞拍产品剩余数量小于起购量时，竞拍进入倒计时， \n" +
                    "当新报价改变了实时行情时， \n" +
                    "再次触发倒计时 \n" +
                    delayTimeLength;
        }
        Map<String,Object> map11=new Hashtable<String,Object>();
        map11.put("text","延长时间");
        map11.put("value",delayTimeLength);
        list.add(map11);
        Map<String,Object> map12=new Hashtable<String,Object>();
        map12.put("text","延时规则");
        map12.put("value",delayRule);
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

}

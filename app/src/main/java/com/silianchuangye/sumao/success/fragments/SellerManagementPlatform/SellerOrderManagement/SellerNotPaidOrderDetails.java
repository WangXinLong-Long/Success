package com.silianchuangye.sumao.success.fragments.SellerManagementPlatform.SellerOrderManagement;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.adapter.SellerItemAdapter;
import com.silianchuangye.sumao.success.custom.CustomListView;
import com.silianchuangye.sumao.success.fragments.bean.SellerItemInfo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

/**
 * Created by Administrator on 2016/7/15 0015.
 * 待支付界面
 */
public class SellerNotPaidOrderDetails extends Activity implements View.OnClickListener,SellerItemAdapter.SellerItemCall {
    private TextView tv_title;
    private ImageView img_back;
    private RelativeLayout relative_logistics_title_bar_search;//保存外套relative
    private TextView tv_save;//保存
    private String title;
    private TextView tv_time,tv_order_num,tv_maifang_name,tv_buy_name,
                    tv_order_type,tv_order_time,tv_order_people,tv_order_zhuangtai,
                        tv_yewu_name,tv_shenpi_name,tv_order_allprice;
    private RelativeLayout relative_gaijia_yuanyin,relative_shenpi_yijian;
    private RelativeLayout relative_hetong_message,relative_pay_message,
                            relative_fapiao_message,relative_logistics_message;
    private Button btn_ok,btn_no;
    private CustomListView lv;

    private SellerItemAdapter adapter;
    private List<SellerItemInfo> list=new ArrayList<SellerItemInfo>();

    private MyCount my;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.seller_not_paid_order_details);
        title=getIntent().getStringExtra("title");
        initDate();
        initView();
    }

    private void initDate() {
        SellerItemInfo info=new SellerItemInfo();
        info.title="兰州石化7024";
        info.xiugai="修改";
        info.sort="默认";
        info.name="兰州石化7024";
        info.olr_price="6,100";
        info.num="10";
        info.new_price="6,000";
        info.all_price="6,000";
        info.cangku="迅帮物流一号库";
        SellerItemInfo info1=new SellerItemInfo();
        info1.title="兰州石化7025";
        info1.xiugai="修改";
        info1.sort="默认";
        info1.name="兰州石化7024";
        info1.olr_price="6,100";
        info1.num="10";
        info1.new_price="6,000";
        info1.all_price="6,000";
        info1.cangku="迅帮物流一号库";
        list.add(info);
        list.add(info1);
    }

    private void initView() {
        img_back= (ImageView) findViewById(R.id.img_logistics_title_bar_back);
        tv_title= (TextView) findViewById(R.id.tv_customer_manager_title);
        tv_title.setText(title);
        relative_logistics_title_bar_search= (RelativeLayout) findViewById(R.id.tv_logistics_title_bar_search);
        tv_save= (TextView) findViewById(R.id.tv_customer_message_editor);
        //商品信息有关控件
        tv_time= (TextView) findViewById(R.id.tv_time);
        tv_order_num= (TextView) findViewById(R.id.tv_order_num);
        tv_maifang_name= (TextView) findViewById(R.id.tv_maifang_name);
        tv_buy_name= (TextView) findViewById(R.id.tv_buy_name);
        tv_order_type= (TextView) findViewById(R.id.tv_order_type);
        tv_order_time= (TextView) findViewById(R.id.tv_order_time);
        tv_order_people= (TextView) findViewById(R.id.tv_order_people);
        tv_order_zhuangtai= (TextView) findViewById(R.id.tv_order_zhuangtai);
        tv_yewu_name= (TextView) findViewById(R.id.tv_yewu_name);
        tv_shenpi_name= (TextView) findViewById(R.id.tv_ask_name);
        tv_order_allprice= (TextView) findViewById(R.id.tv_order_allprice);

        relative_gaijia_yuanyin= (RelativeLayout) findViewById(R.id.relative_gaijia_yuanyin);
        relative_shenpi_yijian= (RelativeLayout) findViewById(R.id.relative_shepi_yijian);
        relative_hetong_message= (RelativeLayout) findViewById(R.id.relative_hetong_message);
        relative_pay_message= (RelativeLayout) findViewById(R.id.relative_pay_message);
        relative_fapiao_message= (RelativeLayout) findViewById(R.id.relative_fapiao_message);
        relative_logistics_message= (RelativeLayout) findViewById(R.id.relative_logistics_message);

        btn_no= (Button) findViewById(R.id.btn_no);
        btn_ok= (Button) findViewById(R.id.btn_ok);

        lv= (CustomListView) findViewById(R.id.lv);
        adapter=new SellerItemAdapter(this,list,this);
        lv.setAdapter(adapter);
        img_back.setOnClickListener(this);
        relative_logistics_title_bar_search.setOnClickListener(this);
        relative_gaijia_yuanyin.setOnClickListener(this);
        relative_shenpi_yijian.setOnClickListener(this);
        relative_hetong_message.setOnClickListener(this);
        relative_pay_message.setOnClickListener(this);
        relative_fapiao_message.setOnClickListener(this);
        relative_logistics_message.setOnClickListener(this);
        btn_no.setOnClickListener(this);
        btn_ok.setOnClickListener(this);

        my=new MyCount(50*1000,1000);
        my.start();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_logistics_title_bar_back:
                finish();
                break;
            case R.id.tv_logistics_title_bar_search:
                Toast.makeText(this,"保存",Toast.LENGTH_SHORT).show();
                finish();
                break;
            case R.id.relative_gaijia_yuanyin:
                Toast.makeText(this,"改价原因",Toast.LENGTH_SHORT).show();
                break;
            case R.id.relative_shepi_yijian:
                Toast.makeText(this,"改价审批意见",Toast.LENGTH_SHORT).show();
                break;
            case R.id.relative_hetong_message:
                Toast.makeText(this,"合同信息",Toast.LENGTH_SHORT).show();
                break;
            case R.id.relative_pay_message:
                Toast.makeText(this,"支付信息",Toast.LENGTH_SHORT).show();
                break;
            case R.id.relative_fapiao_message:
                Toast.makeText(this,"发票信息",Toast.LENGTH_SHORT).show();
                break;
            case R.id.relative_logistics_message:
                Toast.makeText(this,"物流信息",Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_ok:
                Toast.makeText(this,"同意",Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_no:
                Toast.makeText(this,"不同意",Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public void call(int position) {
        Intent intent=new Intent(this, ReNumAndPrice.class);
        intent.putExtra("title",list.get(position).title);
        intent.putExtra("sort",list.get(position).sort);
        intent.putExtra("name",list.get(position).name);
        intent.putExtra("old",list.get(position).olr_price);
        intent.putExtra("new",list.get(position).new_price);
        intent.putExtra("num",list.get(position).num);
        intent.putExtra("allprice",list.get(position).all_price);
        intent.putExtra("cangku",list.get(position).cangku);
        intent.putExtra("int",position);
        startActivityForResult(intent,0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==1){
            int i=data.getIntExtra("i",0);
            String str=data.getStringExtra("new");
            String num=data.getStringExtra("num");
            list.get(i).num=num;
            list.get(i).new_price=str;
            adapter.notifyDataSetChanged();
        }
    }
    /*定义一个倒计时的内部类*/
    class MyCount extends CountDownTimer {
        public MyCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }
        @Override
        public void onFinish() {
            tv_time.setText("00:00:00");
        }
        @Override
        public void onTick(long millisUntilFinished) {
            SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
            String time = format.format(millisUntilFinished- TimeZone.getDefault().getRawOffset());
            tv_time.setText(time);
        }
    }
}

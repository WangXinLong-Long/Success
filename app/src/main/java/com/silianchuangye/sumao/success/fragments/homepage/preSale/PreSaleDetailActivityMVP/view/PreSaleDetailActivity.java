package com.silianchuangye.sumao.success.fragments.homepage.preSale.PreSaleDetailActivityMVP.view;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.silianchuangye.sumao.success.MainActivity;
import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.custom.customCalendar.CalendarView;
import com.silianchuangye.sumao.success.custom.customCalendar.DayAndPrice;
import com.silianchuangye.sumao.success.custom.customCalendar.MonthDateView;
import com.silianchuangye.sumao.success.fragments.homepage.auction.VesselThreeActivity;
import com.silianchuangye.sumao.success.fragments.homepage.preSale.PreSaleDetailActivityMVP.bean.PreSaleDetailBean;
import com.silianchuangye.sumao.success.fragments.homepage.preSale.PreSaleDetailActivityMVP.bean.PreSaleDetailCalendarBean;
import com.silianchuangye.sumao.success.fragments.homepage.preSale.PreSaleDetailActivityMVP.bean.Sku;
import com.silianchuangye.sumao.success.fragments.homepage.preSale.PreSaleDetailActivityMVP.presenter.PreSaleDetailPresenter;
import com.silianchuangye.sumao.success.utils.LogUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Administrator on 2016/5/19 0019.
 */
public class PreSaleDetailActivity extends Activity implements View.OnClickListener, IPreSaleDetailView {
    CalendarView calendarView;
    ImageView title_bar_white_back;
    TextView title_bar_white_title;
    ImageView title_bar_white_shopping_cart;
    RelativeLayout pre_sale_sale_detail_detail;
    Button payment_security;
    private String productId;
    private String skuId;
    private PreSaleDetailPresenter preSaleDetailPresenter;
    private TextView tvName_auction;
    private TextView tvPrice_auction;
    private TextView pre_sale_detail_integral_rule;
    private TextView surplus_amount_et;
    private TextView purchase_quantity_et;
    private TextView min_variable_et;
    private TextView delivery_time_et;
    private TextView warehouse_address_et;
    private TextView delivery_mode_et;
    private TextView classification_pre_sale_et;
    private TextView warehouse_et;
    private TextView region_et;
    private TextView company_et;
    private TextView margin_proportion_et;
    private TextView pre_sale_detail_remark;
    private TextView delivery_time_et_end;
    private int year;
    private int mounth;
    private int day;
    private List<DayAndPrice> calendarlist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


//
        setContentView(R.layout.activity_pre_sale_detail);
        Intent intent = getIntent();
        calendarlist = new ArrayList<>();
        productId = intent.getStringExtra("productId");
        skuId = intent.getStringExtra("skuId");
        LogUtils.log("productId:" + productId + "skuId:" + skuId);
//        从服务器获取数据
        preSaleDetailPresenter = new PreSaleDetailPresenter(this);
        preSaleDetailPresenter.sendPreSaleDetailData(skuId, productId);
        preSaleDetailPresenter.sendPreSaleDetailCalendar(productId);
        calendarView = (CalendarView) findViewById(R.id.calendarView);
//        一定要在初始化控件以前把数据加载完毕了
        calendarView.setListDayAndPrice(calendarlist);
        calendarView.invalidate();

//        calendarView.invalidate();
        calendarView.setDateViewClick(new CalendarView.DateViewClick() {

            @Override
            public void dateClick() {
                Toast.makeText(getApplication(), "点击了：" + calendarView.getSelectMonth(), Toast.LENGTH_SHORT).show();
            }
        });
        initView();
        initListener();

    }

    private void initListener() {
        payment_security.setOnClickListener(this);
        title_bar_white_back.setOnClickListener(this);
        title_bar_white_shopping_cart.setOnClickListener(this);
        pre_sale_sale_detail_detail.setOnClickListener(this);
    }

    private void initView() {
        title_bar_white_back = ((ImageView) findViewById(R.id.title_bar_white_back));
        title_bar_white_title = ((TextView) findViewById(R.id.title_bar_white_title));
        title_bar_white_shopping_cart = ((ImageView) findViewById(R.id.title_bar_white_shopping_cart));
        pre_sale_sale_detail_detail = ((RelativeLayout) findViewById(R.id.pre_sale_sale_detail_detail));
        payment_security = ((Button) findViewById(R.id.payment_security));
        //        兰州石化7042
        tvName_auction = ((TextView) findViewById(R.id.tvName_auction));
        //        6000
        tvPrice_auction = ((TextView) findViewById(R.id.tvPrice_auction));
        //        积分规则:每吨商品积一分
        pre_sale_detail_integral_rule = ((TextView) findViewById(R.id.pre_sale_detail_integral_rule));
        //        剩余数量
        surplus_amount_et = ((TextView) findViewById(R.id.surplus_amount_et));
        //        起购量
        purchase_quantity_et = ((TextView) findViewById(R.id.purchase_quantity_et));
        //        最小变量单位
        min_variable_et = ((TextView) findViewById(R.id.min_variable_et));
        //       交货时间
        delivery_time_et = ((TextView) findViewById(R.id.delivery_time_et));
        delivery_time_et_end = ((TextView) findViewById(R.id.delivery_time_et_end));
        //       仓库地址
        warehouse_address_et = ((TextView) findViewById(R.id.warehouse_address_et));
        //       交货方式
        delivery_mode_et = ((TextView) findViewById(R.id.delivery_mode_et));
        //       分类
        classification_pre_sale_et = ((TextView) findViewById(R.id.classification_pre_sale_et));
        //      仓库
        warehouse_et = ((TextView) findViewById(R.id.warehouse_et));
        //      地区
        region_et = ((TextView) findViewById(R.id.region_et));
        //      公司
        company_et = ((TextView) findViewById(R.id.company_et));
        //      保证金比例
        margin_proportion_et = ((TextView) findViewById(R.id.margin_proportion_et));
        //        产品备注
        pre_sale_detail_remark = ((TextView) findViewById(R.id.pre_sale_detail_remark));


    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.title_bar_white_back:
                finish();
                break;
            case R.id.title_bar_white_shopping_cart:

                intent.setClass(PreSaleDetailActivity.this, MainActivity.class);
                intent.putExtra("cart", 1);
                startActivity(intent);
                break;
            case R.id.pre_sale_sale_detail_detail:
                intent.setClass(PreSaleDetailActivity.this, VesselThreeActivity.class);
                startActivity(intent);
                break;

//            点击的 支付保证金 按钮
            case R.id.payment_security:
                /**
                 * 这里需要弹出popupWindow，即支付的弹出窗
                 */
                break;

        }
    }

    @Override
    public void getPreSaleDetailData(PreSaleDetailBean preSaleDetailBean) {
        //        兰州石化7042
        tvName_auction.setText(preSaleDetailBean.getCl_mingcheng());
        //        6000
//        tvPrice_auction.setText(preSaleDetailBean.getj);
        //        积分规则:每吨商品积一分
        pre_sale_detail_integral_rule.setText(preSaleDetailBean.getCl_jifen());
        //        剩余数量
        surplus_amount_et.setText(preSaleDetailBean.getCl_shuliang());
        //        起购量
        purchase_quantity_et.setText(preSaleDetailBean.getCl_qigou());
        //        最小变量单位
        min_variable_et.setText(preSaleDetailBean.getCl_xiaobian());
        //       交货时间
        delivery_time_et.setText(preSaleDetailBean.getDeliveryPeriodStart());
        delivery_time_et_end.setText(preSaleDetailBean.getDeliveryPeriodEnd());
        //       仓库地址
        warehouse_address_et.setText(preSaleDetailBean.getCl_ckdizhi());
        //       交货方式
//        delivery_mode_et.setText(preSaleDetailBean.getj);
        //       分类
//        classification_pre_sale_et.setText(preSaleDetailBean.getf);
        //      仓库
        warehouse_et.setText(preSaleDetailBean.getCl_cangku());
        //      地区
        region_et.setText(preSaleDetailBean.getCl_diqu());
        //      公司
        company_et.setText(preSaleDetailBean.getCl_qiye());
        //      保证金比例
        margin_proportion_et.setText(preSaleDetailBean.getCl_baozhj());
        //        产品备注
//        pre_sale_detail_remark.setText(preSaleDetailBean.getch);

    }

    @Override
    public void getPreSaleDetailCalendarData(PreSaleDetailCalendarBean preSaleDetailCalendarBean/*,int position*/) {
        List<Sku> skus = preSaleDetailCalendarBean.getSku();
        LogUtils.log("lists" + skus.toString());
//        List<DayAndPrice> list = new ArrayList<DayAndPrice>();
        for (int i = 0; i < skus.size(); i++) {
            Sku sku = skus.get(i);
            String[] data = sku.getCl_date().split("-");
            LogUtils.log("sku.getCl_jiner()-->" + sku.getCl_jiner() + "<---new Integer(data[0]),new Integer(data[1]),new Integer(data[2])-->" + data[0] + "---" + data[1] + "---" + data[2]);

            try {
                year = Integer.parseInt(data[0]);
                mounth = Integer.parseInt(data[1]);
                day = Integer.parseInt(data[2]);
            } catch (NumberFormatException e) {
                throw new RuntimeException("没有转换成功");
            }
            LogUtils.log(year + "..." + mounth + "..." + day);
            calendarlist.add(new DayAndPrice("￥"+sku.getCl_jiner(), year, mounth, day));
        }
        calendarView.setSelected(true);
        for (int i = 0; i < calendarlist.size(); i++) {
            LogUtils.log("我这没问题：" + calendarlist.get(i).toString());
        }
    }
}

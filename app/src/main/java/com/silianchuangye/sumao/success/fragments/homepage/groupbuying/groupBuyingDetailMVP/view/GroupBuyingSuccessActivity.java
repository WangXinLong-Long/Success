package com.silianchuangye.sumao.success.fragments.homepage.groupbuying.groupBuyingDetailMVP.view;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ActionBarOverlayLayout;
import android.text.Editable;
import android.text.TextWatcher;
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

import com.google.gson.Gson;
import com.silianchuangye.sumao.success.HX.Constant;
import com.silianchuangye.sumao.success.HX.ui.LoginActivity;
import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.adapter.PopupWindowAdaptrer;
import com.silianchuangye.sumao.success.dialog.Ok_Dialog;
import com.silianchuangye.sumao.success.fragments.homepage.auction.OpenAuction;
import com.silianchuangye.sumao.success.fragments.homepage.auction.VesselThreeActivity;
import com.silianchuangye.sumao.success.fragments.homepage.goodInStock.goodsInStockDetail.GoodsInStockDetailActivityMVP.bean.GoodsInStockDetailBean;
import com.silianchuangye.sumao.success.fragments.homepage.groupbuying.JoinActivity;
import com.silianchuangye.sumao.success.fragments.homepage.groupbuying.RuleActivity;
import com.silianchuangye.sumao.success.fragments.homepage.groupbuying.groupBuyingDetailMVP.bean.GroupBuyingDetailBean;
import com.silianchuangye.sumao.success.fragments.homepage.groupbuying.groupBuyingDetailMVP.presenter.GroupBuyingDetailPresenter;
import com.silianchuangye.sumao.success.fragments.homepage.preSale.PreSaleDetailActivityMVP.bean.PreSaleBondBean;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.login.LoginUserActivity;
import com.silianchuangye.sumao.success.utils.Loding;
import com.silianchuangye.sumao.success.utils.SuMaoConstant;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GroupBuyingSuccessActivity extends AppCompatActivity implements IGroupBuyingDetailView, View.OnClickListener {
    private ListView lvDemo;
    private SimpleAdapter adapter;
    private List<Map<String, Object>> list;
    private TextView tv_success;
    private TextView tv_failed;

    private String result;
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
    private ArrayList<GoodsInStockDetailBean.ClAttributeBean> cl_attribute = new ArrayList<GoodsInStockDetailBean.ClAttributeBean>();
    private String bili, jiezhishijian, can_gku, xiangxi, kaishishijian, jieshushijian, tuan_start, tuan_end;
    private LinearLayout linear_tuangou_time, linear_tuagou_line;
    private RelativeLayout relative_tuangou_end;
    private Button btn_tuangou_add;
    private PopupWindow popupWindow;
    private View popView;
    private TextView tv_pop_price, tv_pop_num, tv_pop_fenlei, tv_pop_start_num,
            tv_pop_type, tv_pop_small_null, tv_pop_diqu, tv_pop_cangku,
            tv_pop_gongsi, tv_pop_add, tv_pop_sub;
    private EditText edt_pop_num;
    private Button btn_pop_ok;
    private List<OpenAuction> list1;
    private PopupWindowAdaptrer popAdapter;
    private String strbianliang, strprice = "";//
    private ImageView img;
    private TextView tv_pro_now;
    private ProgressBar pbDemo;
    private MyCount mc;
    TextView tv_all_num, tv_tuangou_time, tv_tuangou_line;
    RelativeLayout relative_tuangou, layout_not_action, layout_Bottom;
    private String state;
    private LinearLayout layoutContent_auction;
    private String cl_gongsiId;
    private GroupBuyingDetailPresenter groupBuyingDetailPresenter;
    private PopupWindow paymentSecurityPopupWindow;
    private TextView paidDeposit;
    private EditText amountOfMarginRequired;
    private PopupWindowAdaptrer bankListAdapter1;
    private double additionalMarginAmount;
    private String additionalMarginAmountString;
//    购买数量
    private EditText tv_item_cart_buy_num;
    private TextView img_item_cart_buy_sub;
    private TextView img_item_cart_buy_add;
    private TextView tv_item_cart_all_price;
//    已支付的保证金金额
    private String zhifujine = "0";
    private String cl_baozhj;
    private String percent;
    private String cl_id;
    private TextView group_amount_et;
    private GroupBuyingDetailBean groupBuyingDetailBean;
    private LinearLayout remarks_rl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        groupBuyingDetailPresenter = new GroupBuyingDetailPresenter(this);
        setContentView(R.layout.activity_group_buying_success);
        init();

        event();
        addData();
    }

    public void init() {
        layout_Bottom = (RelativeLayout) findViewById(R.id.layout_Bottom);
        layout_not_action = (RelativeLayout) findViewById(R.id.layout_not_action);
        //relative_tuangou= (RelativeLayout) findViewById(R.id.relative_tuangou_end);
        pbDemo = (ProgressBar) findViewById(R.id.pbDemo);
        pbDemo.setMax(100);
        img = (ImageView) findViewById(R.id.ivBack);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        Bundle bundle = getIntent().getExtras();
        result = bundle.getString("result");

        state = bundle.getString("state");
        relative_tuangou_end = (RelativeLayout) findViewById(R.id.relative_tuangou_end);
        if (state.equals("团购未开始")) {
            layout_not_action.setVisibility(View.VISIBLE);
            layout_Bottom.setVisibility(View.GONE);
            relative_tuangou_end.setVisibility(View.GONE);
        } else if (state.equals("团购已开始")) {
            layout_Bottom.setVisibility(View.VISIBLE);
            layout_not_action.setVisibility(View.GONE);
            relative_tuangou_end.setVisibility(View.GONE);

        } else if (state.equals("团购已结束")) {
            relative_tuangou_end.setVisibility(View.VISIBLE);
            layout_Bottom.setVisibility(View.GONE);
            layout_not_action.setVisibility(View.GONE);

        }
        lvDemo = (ListView) findViewById(R.id.lv_demo);
        list = new ArrayList<Map<String, Object>>();
        tv_failed = (TextView) findViewById(R.id.tv_failed);
        tv_success = (TextView) findViewById(R.id.tv_success);
        layout_Bottom = (RelativeLayout) findViewById(R.id.layout_Bottom);

        linear_tuangou_time = (LinearLayout) findViewById(R.id.linear_tuangou_time);
        linear_tuagou_line = (LinearLayout) findViewById(R.id.linear_tuagou_line);
        tv_pro_now = (TextView) findViewById(R.id.tv_pro_now);//当前显示的进度
        tv_all_num = (TextView) findViewById(R.id.tv_all_num);
        tv_tuangou_time = (TextView) findViewById(R.id.tv_tuangou_time);
        btn_tuangou_add = (Button) findViewById(R.id.btn_tuangou_add);
        relative_tuangou = (RelativeLayout) findViewById(R.id.relative_tuangou);
        tv_tuangou_line = (TextView) findViewById(R.id.tv_tuangou_line);

        name = (TextView) findViewById(R.id.tvName_auction);
//        团购价格
        price = (TextView) findViewById(R.id.tvPrice_auction);
//        团购总量
        count = (TextView) findViewById(R.id.surplus_amount_et);
//        起购量
        qigou = (TextView) findViewById(R.id.purchase_quantity_et);
//        最小变量单位
        bianliang = (TextView) findViewById(R.id.min_variable_et);
//        总价
        tv_item_cart_all_price = (TextView) findViewById(R.id.tv_item_cart_all_price);
//        成团量
        group_amount_et = ((TextView) findViewById(R.id.group_amount_et));
//        备注
        remarks_rl = ((LinearLayout) findViewById(R.id.remarks_rl));
        remarks_rl.setVisibility(View.GONE);
        cangku = (TextView) findViewById(R.id.warehouse_address_et);
        way = (TextView) findViewById(R.id.delivery_mode_et);
        type = (TextView) findViewById(R.id.classification_pre_sale_et);
//        bianjia= (TextView) findViewById(R.id.warehouse_et);
        add = (TextView) findViewById(R.id.region_et);
        comm = (TextView) findViewById(R.id.company_et);
        number = (EditText) findViewById(R.id.ed_shuzhi_number);
        layoutContent_auction = ((LinearLayout) findViewById(R.id.layoutContent_auction));
        btn_tuangou_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getSharedPreferences("sumao", Activity.MODE_PRIVATE).getString("unique", "").equals("false")) {
                    Intent intent = new Intent(GroupBuyingSuccessActivity.this, LoginUserActivity.class);
                    intent.putExtra("roles", "buyer");
                    startActivity(intent);
                } else {
                    //弹出popwindow
//                    showPopWindow();
                    showpop();
//                    backgroundAlpha(0.5f);
                }

            }
        });
        //        购买数量
        tv_item_cart_buy_num = ((EditText) findViewById(R.id.tv_item_cart_buy_num));
        getinfo(result);
//        减
        img_item_cart_buy_sub = ((TextView) findViewById(R.id.img_item_cart_buy_sub));
        img_item_cart_buy_sub.setOnClickListener(this);
//        增加
        img_item_cart_buy_add = ((TextView) findViewById(R.id.img_item_cart_buy_add));
        img_item_cart_buy_add.setOnClickListener(this);
        //       购买数量
        tv_item_cart_buy_num.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 0) {
                    tv_item_cart_buy_num.setText(qigou.getText().toString());
                    tv_item_cart_buy_num.setSelection(qigou.getText().toString().length());
                }
                if (s.toString().contains(".")) {
                    if (s.length() - 1 - s.toString().indexOf(".") > 3) {
                        s = s.toString().subSequence(0,
                                s.toString().indexOf(".") + 4);
                        tv_item_cart_buy_num.setText(s);
                        tv_item_cart_buy_num.setSelection(s.length());
                    }
                }
                if (s.toString().trim().substring(0).equals(".")) {
                    s = "0" + s;
                    tv_item_cart_buy_num.setText(s);
                    tv_item_cart_buy_num.setSelection(2);
                }

                if (s.toString().startsWith("0")
                        && s.toString().trim().length() > 1) {
                    if (!s.toString().substring(1, 2).equals(".")) {
                        tv_item_cart_buy_num.setText(s.subSequence(1, s.length()));
                        tv_item_cart_buy_num.setSelection(1);
                        return;
                    }
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

                if (Double.parseDouble(tv_item_cart_buy_num.getText().toString()) > Double.parseDouble(count.getText().toString())) {
                    tv_item_cart_buy_num.setText((count.getText().toString()));
                    tv_item_cart_buy_num.setSelection(count.getText().toString().length());

                }
                if (Double.parseDouble(tv_item_cart_buy_num.getText().toString()) < Double.parseDouble(qigou.getText().toString())) {
                    tv_item_cart_buy_num.setText((qigou.getText().toString()));
                    tv_item_cart_buy_num.setSelection(qigou.getText().toString().length());
                }
                if (!tv_item_cart_buy_num.getText().toString().equals("")) {
                    double num = (Double.parseDouble(tv_item_cart_buy_num.getText().toString()));
                    double price_num = (Double.parseDouble(price.getText().toString()));
                    double x = num * price_num;
                    DecimalFormat df = new DecimalFormat("#.##");
                    tv_item_cart_all_price.setText(df.format(x) + "");

                }
                if ((Double.parseDouble(tv_item_cart_buy_num.getText().toString()) - Double.parseDouble(qigou.getText().toString())) % Double.parseDouble(bianliang.getText().toString()) != 0) {
                    DecimalFormat df = new DecimalFormat("#");
                    double number = (Double.parseDouble(tv_item_cart_buy_num.getText().toString()) - Double.parseDouble(qigou.getText().toString())) / Double.parseDouble(bianliang.getText().toString());
                    double priceNum = (Integer.parseInt(df.format(number)) * Double.parseDouble(bianliang.getText().toString())) + Double.parseDouble(qigou.getText().toString());
                    String priceString = (priceNum + "");
                    tv_item_cart_buy_num.setText(priceString);
                    tv_item_cart_buy_num.setSelection(priceString.length());
                }
            }
        });

    }

    public void event() {
        lvDemo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    Intent intent = new Intent(GroupBuyingSuccessActivity.this, RuleActivity.class);
                    intent.putExtra("bili", bili);
                    intent.putExtra("jiezhi", jiezhishijian);
                    intent.putExtra("cangku", can_gku);
                    intent.putExtra("xiangxi", xiangxi);
                    intent.putExtra("kaishi", kaishishijian);
                    intent.putExtra("jieshu", jieshushijian);
                    intent.putExtra("tuan_start", tuan_start);
                    intent.putExtra("tuan_end", tuan_end);
                    intent.putExtra("state", state);
                    startActivity(intent);
                } else if (position == 1) {
                    groupBuyingDetailPresenter.getTheRecordResult(result);


                } else if (position == 2) {
                    Intent intent = new Intent(GroupBuyingSuccessActivity.this, VesselThreeActivity.class);
                    intent.putExtra("title", "团购");
                    intent.putExtra("auctionDetailBean", groupBuyingDetailBean);
                    intent.putExtra("contract", path);
                    startActivity(intent);

                }
            }
        });


    }

    public void addData() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("name", "交易规则");
        list.add(map);
        Map<String, Object> map1 = new HashMap<String, Object>();
        map1.put("name", "我的参团记录");
        list.add(map1);
        Map<String, Object> map2 = new HashMap<String, Object>();
        map2.put("name", "合同详情、产品参数");
        list.add(map2);

        adapter = new SimpleAdapter(this, list, R.layout.item_open_auction,
                new String[]{"name"},
                new int[]{R.id.tvRule_auction});
        lvDemo.setAdapter(adapter);


    }

    public void getinfo(String result) {
        Gson gson = new Gson();
        groupBuyingDetailBean = gson.fromJson(result,GroupBuyingDetailBean.class);
        cl_baozhj = groupBuyingDetailBean.getCl_baozhj();
        cl_id = groupBuyingDetailBean.getCl_id();
        NumberFormat nf = NumberFormat.getPercentInstance();
        Number number = null;
        try {
             number = nf.parse(cl_baozhj);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        percent = String.format("%.4f", Double.parseDouble(number.toString()));
        try {
            JSONObject obj = new JSONObject(result);
            String utilDate = groupBuyingDetailBean.getUtilDate();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            long time = 0l;
            try {
                long strdate = dateFormat.parse(utilDate).getTime();
                long nowTime = System.currentTimeMillis();
                time = strdate - nowTime;
                mc = new MyCount(time, 1000);
                mc.start();
            } catch (ParseException e) {
                e.printStackTrace();
            }
            name.setText(groupBuyingDetailBean.getCl_mingcheng());
            strprice = groupBuyingDetailBean.getCl_jine();
            price.setText(strprice);
            if (result.contains("cl_zongliang")) {
                count.setText(groupBuyingDetailBean.getCl_zongliang());
            } else {
                count.setText("");
            }
            cl_gongsiId = groupBuyingDetailBean.getCl_gongsiId();
            layoutContent_auction.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(GroupBuyingSuccessActivity.this, "客服聊天", Toast.LENGTH_SHORT).show();
                    Intent intentHX = new Intent();
                    intentHX.setClass(GroupBuyingSuccessActivity.this, LoginActivity.class);
                    intentHX.putExtra(Constant.MESSAGE_TO_INTENT_EXTRA, Constant.MESSAGE_TO_DEFAULT);
//                传入卖家id
                    intentHX.putExtra(Constant.IM_SERVICE_NUMBER, cl_gongsiId);
                    startActivity(intentHX);
                }
            });
            group_amount_et.setText(groupBuyingDetailBean.getCl_groupQuantity());
            qigou.setText(groupBuyingDetailBean.getCl_qigou());
            tv_item_cart_buy_num.setText(qigou.getText().toString());
            tv_item_cart_all_price.setText(String.format("%.2f",Double.parseDouble(tv_item_cart_buy_num.getText().toString())*Double.parseDouble(price.getText().toString())));
            strbianliang = groupBuyingDetailBean.getCl_xbianliang();
            bianliang.setText(strbianliang);
            cangku.setText(groupBuyingDetailBean.getCl_cangku());
            String fangshi = groupBuyingDetailBean.getCl_fangshi().toString();
            String str = fangshi.substring(2, fangshi.length() - 2);
            way.setText(str);
            type.setText(groupBuyingDetailBean.getCl_fenlei());
//                            String bian=obj.getString("cl_xbianjia");
//                            bianjia.setText(obj.getString("cl_xbianjia"));//最小变价
            add.setText(groupBuyingDetailBean.getCl_diqu());
            comm.setText(groupBuyingDetailBean.getCl_gongsi());
//
            List<GoodsInStockDetailBean.ClAttributeBean> cl_attribute = groupBuyingDetailBean.getCl_attribute();
            for (int i = 0; i < cl_attribute.size(); i++) {
                GoodsInStockDetailBean.ClAttributeBean attribute =cl_attribute.get(i);
                attribute.setAttrName(attribute.getAttrName());
                attribute.setAttrValue(attribute.getAttrValue());
                this.cl_attribute.add(attribute);
            }
            path = groupBuyingDetailBean.getContract();
            bili = groupBuyingDetailBean.getCl_baozhj();
            if (groupBuyingDetailBean.getDepositDeadline() == null){
                jiezhishijian = "";
            }else {
                jiezhishijian = groupBuyingDetailBean.getDepositDeadline();
            }

            can_gku = groupBuyingDetailBean.getCl_cangku();
            if (result.contains("addressDetail")) {
                xiangxi = groupBuyingDetailBean.getAddressDetail();
            } else {
                xiangxi = "";
            }
            kaishishijian = groupBuyingDetailBean.getCl_shijian();
            jieshushijian = groupBuyingDetailBean.getCl_shijianend();
            tuan_start = groupBuyingDetailBean.getGroupStartDate();
            tuan_end = groupBuyingDetailBean.getGroupEndDate();
            String sumAddQty = groupBuyingDetailBean.getSumAddQty();//数量
            String groupPercent = groupBuyingDetailBean.getGroupPercent();//比例

            int i = (int) (Double.valueOf(groupPercent) * 100);
            tv_pro_now.setText("当前成团量为 : " + i + "%");
            tv_all_num.setText(groupBuyingDetailBean.getCl_zongliang() + "t");
            pbDemo.setProgress(i);
            String state = groupBuyingDetailBean.getSection();
            Log.e("TAG", "state=" + state);
            if (state.equals("2")) {
                tv_tuangou_line.setVisibility(View.VISIBLE);
                relative_tuangou.setVisibility(View.GONE);
                tv_success.setVisibility(View.GONE);
                tv_failed.setVisibility(View.GONE);
                layout_Bottom.setVisibility(View.INVISIBLE);
                relative_tuangou_end.setVisibility(View.VISIBLE);
                linear_tuangou_time.setVisibility(View.GONE);
                linear_tuagou_line.setVisibility(View.GONE);
                tv_tuangou_line.setVisibility(View.GONE);
                tv_tuangou_time.setVisibility(View.GONE);
            }
            if (state.equals("1")) {//正在进行
                relative_tuangou.setVisibility(View.VISIBLE);
                tv_tuangou_time.setVisibility(View.GONE);
                tv_tuangou_line.setVisibility(View.GONE);
                linear_tuangou_time.setVisibility(View.VISIBLE);
            }
            if (state.equals("0")) {//未开始
                tv_tuangou_line.setVisibility(View.VISIBLE);
                relative_tuangou.setVisibility(View.GONE);
                layout_Bottom.setVisibility(View.GONE);
                relative_tuangou_end.setVisibility(View.VISIBLE);
                linear_tuangou_time.setVisibility(View.GONE);
                linear_tuagou_line.setVisibility(View.GONE);
                tv_tuangou_time.setVisibility(View.VISIBLE);
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    private void showPopWindow() {
        popView = View.inflate(this, R.layout.pop_tuagou_add, null);
        tv_pop_price = (TextView) popView.findViewById(R.id.tvPrice_auction);//价钱
        tv_pop_num = (TextView) popView.findViewById(R.id.surplus_amount_et);//团购总量
        tv_pop_fenlei = (TextView) popView.findViewById(R.id.classification_pre_sale_et);//分类
        tv_pop_start_num = (TextView) popView.findViewById(R.id.purchase_quantity_et);//起购量
        tv_pop_type = (TextView) popView.findViewById(R.id.warehouse_et);//交货方式
        tv_pop_small_null = (TextView) popView.findViewById(R.id.min_variable_et);//最小变量单位
        tv_pop_diqu = (TextView) popView.findViewById(R.id.region_et);//地区
        tv_pop_cangku = (TextView) popView.findViewById(R.id.delivery_time_et);//仓库
        tv_pop_gongsi = (TextView) popView.findViewById(R.id.warehouse_address_et);//公司
        tv_pop_add = (TextView) popView.findViewById(R.id.img_item_cart_buy_add);//加号
        tv_pop_sub = (TextView) popView.findViewById(R.id.img_item_cart_buy_sub);//减号
        edt_pop_num = (EditText) popView.findViewById(R.id.tv_item_cart_buy_num);//中间变化的数量
        edt_pop_num.setText(number.getText().toString());
        btn_pop_ok = (Button) popView.findViewById(R.id.determine_buy_immediately_button);//确定
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
                String str = edt_pop_num.getText().toString();
                double num = Double.valueOf(str);
                double cc = Double.parseDouble(strbianliang);
                num += cc;
                edt_pop_num.setText(num + "");
//                tv_pop_price.setText(Double.valueOf(edt_pop_num.getText().toString())*Double.valueOf(strprice)+"");
                number.setText(edt_pop_num.getText().toString());
                count_price.setText(Double.valueOf(number.getText().toString()) * Double.valueOf(strprice) + "");
            }
        });
        tv_pop_sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = edt_pop_num.getText().toString();
                double num = Double.valueOf(str);
                double cc = Double.parseDouble(strbianliang);
                num -= cc;
                if (num < cc) {
                    num = cc;
                }
                edt_pop_num.setText(num + "");
//                tv_pop_price.setText(Double.valueOf(edt_pop_num.getText().toString())*Double.valueOf(strprice)+"");
                //
                number.setText(edt_pop_num.getText().toString());
                count_price.setText(Double.valueOf(number.getText().toString()) * Double.valueOf(strprice) + "");
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
    private void showpop() {
        getinfo_Bank();
    }

    //获取银行列表接口
    public void getinfo_Bank() {
        Loding.show(this,"加载中...",true,null);
        groupBuyingDetailPresenter.getBankListResult(cl_id);
    }

    private void popDate() {
        edt_pop_num.setText(number.getText().toString());
        String str, str2;
        if (edt_pop_num.getText().toString().equals("")) {
            str = 0 + "";
        } else {
            str = edt_pop_num.getText().toString();
        }
        if (strprice.equals("")) {
            str2 = "0";
        } else {
            str2 = strprice;
        }
        tv_pop_price.setText(price.getText().toString());
//        String baozhengjin=Double.valueOf(str)*Double.valueOf(str2)+"";
//        tv_pop_price.setText(Double.valueOf(str)*Double.valueOf(str2)+"");

        tv_pop_num.setText(count.getText().toString());//剩余数量
        tv_pop_fenlei.setText(type.getText().toString());
        tv_pop_start_num.setText(qigou.getText().toString());
        tv_pop_type.setText(way.getText().toString());//交易方式
        tv_pop_small_null.setText(bianliang.getText().toString());
        tv_pop_diqu.setText(add.getText().toString());
        tv_pop_cangku.setText(cangku.getText().toString());
        tv_pop_gongsi.setText(comm.getText().toString());
        //
    }

    @Override
    public void setBankListResult(String result) {
        Loding.dis();
        if (result.contains("No Bank Info")) {
            Toast.makeText(this, "该商品暂不支持银行付款", Toast.LENGTH_SHORT).show();
        } else {
            backgroundAlpha(0.5f);
            View view = getLayoutInflater().inflate(R.layout.item_popupwindow_auction, null);
            paymentSecurityPopupWindow = new PopupWindow(findViewById(R.id.parent), ActionBarOverlayLayout.LayoutParams.MATCH_PARENT, ActionBarOverlayLayout.LayoutParams.WRAP_CONTENT);
            paymentSecurityPopupWindow.setContentView(view);
            paidDeposit = (TextView) view.findViewById(R.id.tvPrice_popupwindow_auction);
            paidDeposit.setText(zhifujine);
            amountOfMarginRequired = (EditText) view.findViewById(R.id.etZhifu_auction);
//            需保证金
            TextView totalAmount = (TextView) view.findViewById(R.id.total_amount);
            TextView count_Price = (TextView) view.findViewById(R.id.count_Price);
            totalAmount.setText("需保证金:");
//        数量*竞拍价格*保证金比例，是最小的保证金，可以输入比这个大的金额和追加保证金
//        补充保证金

            additionalMarginAmount = Double.parseDouble(tv_item_cart_all_price.getText().toString()) * Double.parseDouble(percent + "") - Double.parseDouble(paidDeposit.getText().toString());

            count_Price.setText(String.format("%.2f", Double.parseDouble(tv_item_cart_all_price.getText().toString()) * Double.parseDouble(percent + "")));
            additionalMarginAmountString = additionalMarginAmount > 0 ? additionalMarginAmount + "" : 0 + "";
            amountOfMarginRequired.setText(String.format("%.2f",Double.parseDouble(additionalMarginAmountString)));
            amountOfMarginRequired.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    if (s.length() == 0) {
                        amountOfMarginRequired.setText(additionalMarginAmountString);
                        amountOfMarginRequired.setSelection(additionalMarginAmountString.length());
                    }
                    if (s.toString().contains(".")) {
                        if (s.length() - 1 - s.toString().indexOf(".") > 2) {
                            s = s.toString().subSequence(0,
                                    s.toString().indexOf(".") + 3);
                            amountOfMarginRequired.setText(s);
                            amountOfMarginRequired.setSelection(s.length());
                        }
                    }
                    if (s.toString().trim().substring(0).equals(".")) {
                        s = "0" + s;
                        amountOfMarginRequired.setText(s);
                        amountOfMarginRequired.setSelection(3);
                    }

                    if (s.toString().startsWith("0")
                            && s.toString().trim().length() > 1) {
                        if (!s.toString().substring(1, 2).equals(".")) {
                            amountOfMarginRequired.setText(s.subSequence(1, s.length()));
                            amountOfMarginRequired.setSelection(1);
                            return;
                        }
                    }

                }

                @Override
                public void afterTextChanged(Editable s) {

                    if (Double.parseDouble(amountOfMarginRequired.getText().toString()) < Double.parseDouble(additionalMarginAmountString)) {
                        amountOfMarginRequired.setText(additionalMarginAmountString);
                        amountOfMarginRequired.setSelection(additionalMarginAmountString.length());

                    }
                }
            });
            lv = (ListView) view.findViewById(R.id.lv_popupwindow_auction);

            Gson gson = new Gson();
            list1 = new ArrayList<>();
            PreSaleBondBean preSaleBondBean = gson.fromJson(result, PreSaleBondBean.class);
            List<PreSaleBondBean.BankListBean> bankList = preSaleBondBean.getBankList();
            list1.clear();
            for (int i = 0; i < bankList.size(); i++) {
                OpenAuction auction = new OpenAuction();
                auction.tv_money = bankList.get(i).getBalance();
                String type = bankList.get(i).getBankType();
                if (type.equals("1")) {
                    //平安
                    auction.iv_icon = R.mipmap.pingan;
                    auction.tv_Name = "平安银行";
                    auction.tv_money = bankList.get(i).getBalance();
                    auction.message = 1 + "";
                } else if (type.equals("2")) {
                    //昆仑
                    auction.iv_icon = R.mipmap.kunlun;
                    auction.tv_Name = "昆仑银行";
                    auction.tv_money = bankList.get(i).getBalance();
                    auction.message = 2 + "";
                } else if (type.equals("3")) {
                    //建行
                    auction.iv_icon = R.mipmap.jianshe;
                    auction.tv_Name = "中国建设银行";
                    auction.tv_money = bankList.get(i).getBalance();
                    auction.message = 3 + "";
                }

                list1.add(auction);

            }
            bankListAdapter1 = new PopupWindowAdaptrer(list1, GroupBuyingSuccessActivity.this);
            lv.setAdapter(bankListAdapter1);

            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    if (parent.getId() == lv.getId()) {
                        for (int i = 0; i < list1.size(); i++) {
                            if (i != position) {
                                list1.get(i).Flag = false;
                            }
                        }
                        list1.get(position).Flag = !list1.get(position).Flag;
                        bankListAdapter1.notifyDataSetChanged();
                    }
                }
            });
            Button bt = (Button) view.findViewById(R.id.btZhifu);
            bt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    for (int i = 0; i < list1.size(); i++) {
                        if (list1.get(i).Flag) {
                            //支付保证金接口
                            if (Double.parseDouble(amountOfMarginRequired.getText().toString()) == 0) {
                                Toast.makeText(GroupBuyingSuccessActivity.this, "请填写支付保证金", Toast.LENGTH_SHORT).show();
                            } else {
                                zhifu_baozhengjin(amountOfMarginRequired.getText().toString(), list1.get(i).message);
                                paymentSecurityPopupWindow.dismiss();
                            }

                        } else if (i == list1.size() - 1) {
                            Toast.makeText(GroupBuyingSuccessActivity.this, "请选择的银行进行付款", Toast.LENGTH_SHORT).show();
                        }
                    }

                }
            });
            paymentSecurityPopupWindow.setTouchable(true);
            paymentSecurityPopupWindow.setBackgroundDrawable(new BitmapDrawable());
            paymentSecurityPopupWindow.setOutsideTouchable(true);
            paymentSecurityPopupWindow.setFocusable(true);

            paymentSecurityPopupWindow.showAtLocation(findViewById(R.id.parent), Gravity.BOTTOM, 0, 0);

            paymentSecurityPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                @Override
                public void onDismiss() {
                    //popupWindow.dismiss();
                    backgroundAlpha(1f);
                }
            });
        }
    }

    @Override
    public void setPaymentSecurityResult(String result) {
        Loding.dis();
        if (result.contains("formError\":true")) {
            Toast.makeText(GroupBuyingSuccessActivity.this, "您支付保证金失败！", Toast.LENGTH_SHORT).show();
        } else if (result.contains("\"info\":\"sucess\"")) {
            Toast.makeText(GroupBuyingSuccessActivity.this, "您支付保证金成功！", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(GroupBuyingSuccessActivity.this, Ok_Dialog.class);
            intent.putExtra("number", SuMaoConstant.MARGIN_PAYMENT_SUCCESS);
            intent.putExtra("type", "");
            startActivity(intent);
        }
    }



    //    支付保证金接口
    public void zhifu_baozhengjin(String amountOfMarginRequired, String platform) {
        Loding.show(this, "保证金支付中...", false, null);
//        保证金金额
        String depositAmount = amountOfMarginRequired;
//        产品Id
        String productId = cl_id;
//        支付平台
        String paymentPlatform = platform;
        SharedPreferences sp = getSharedPreferences("sumao", Activity.MODE_PRIVATE);
        String _dynSessConf = sp.getString("unique", "");
        groupBuyingDetailPresenter.getPaymentSecurityResult(depositAmount, productId, paymentPlatform, _dynSessConf);
    }

//    我的参团记录
    @Override
    public void setTheRecordResult(String result) {
        Intent intent = new Intent(GroupBuyingSuccessActivity.this, JoinActivity.class);
        intent.putExtra("result", result);
        startActivity(intent);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //            数量减
            case R.id.img_item_cart_buy_sub:
                double num1 = Double.valueOf(tv_item_cart_buy_num.getText().toString());
                num1 -= Double.valueOf(bianliang.getText().toString());
                    if (num1 < Double.valueOf(qigou.getText().toString())) {
                        Toast.makeText(this, "购买数量不能小于起购量", Toast.LENGTH_SHORT).show();
                    } else {
                        tv_item_cart_buy_num.setText(num1 + "");
                    }
                tv_item_cart_all_price.setText(Double.parseDouble(tv_item_cart_buy_num.getText().toString()) * Double.valueOf(price.getText().toString()) + "");
                break;
//            数量加
            case R.id.img_item_cart_buy_add:
                double num2 = Double.valueOf(tv_item_cart_buy_num.getText().toString());
                num2 += Double.valueOf(bianliang.getText().toString());
                if (num2 > Double.valueOf(count.getText().toString())) {
                    Toast.makeText(this, "购买数量不能大于竞拍总量", Toast.LENGTH_SHORT).show();
                } else {
                    tv_item_cart_buy_num.setText(num2 + "");
                    tv_item_cart_all_price.setText(Double.valueOf(tv_item_cart_buy_num.getText().toString()) * Double.valueOf(price.getText().toString()) + "");
                }
                break;
        }
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
            long time = millisUntilFinished;
//            long time=millisUntilFinished- TimeZone.getDefault().getRawOffset();
            long mSec = time % 1000;
            time /= 1000;
            long year = time / (365 * 24 * 3600);
            time = time % (365 * 24 * 3600);
            long month = time / (30 * 24 * 3600);
            time = time % (30 * 24 * 3600);
            long day = time / (24 * 3600);
            time = time % (24 * 3600);
            long hour = time / 3600;
            time = time % 3600;
            long min = time / 60;
            time = time % 60;
            long sec = time;
//            Log.e("TAG","daojishi------"+day+"天"+hour+"小时"+min+"分"+sec+"秒");
            String finaltime = day + "天" + hour + "小时" + min + "分" + sec + "秒";
            tv_success.setText("剩余团购时间  :  " + finaltime);
            tv_tuangou_time.setText("距离开团时间剩余  :  " + finaltime);
        }
    }
}

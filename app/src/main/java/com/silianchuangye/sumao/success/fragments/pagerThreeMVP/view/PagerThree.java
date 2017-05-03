package com.silianchuangye.sumao.success.fragments.pagerThreeMVP.view;


import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.silianchuangye.sumao.success.MainActivity;
import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.adapter.CartItemAdapter;
import com.silianchuangye.sumao.success.custom.CustomListView;
import com.silianchuangye.sumao.success.fragments.BasePager;
import com.silianchuangye.sumao.success.fragments.bean.CartInfo;
import com.silianchuangye.sumao.success.fragments.bean.CartItemInfo;
import com.silianchuangye.sumao.success.fragments.homepage.goodInStock.goodsInStockDetail.GoodsInStockDetailActivityMVP.bean.Orders;
import com.silianchuangye.sumao.success.fragments.homepage.goodInStock.goodsInStockDetail.PayMentsOrderMVP.view.PaymentsOrder;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.firmInfomation.decoding.Intents;
import com.silianchuangye.sumao.success.fragments.pagerThreeMVP.bean.CartBean;
import com.silianchuangye.sumao.success.fragments.pagerThreeMVP.bean.OrderErrorBean;
import com.silianchuangye.sumao.success.fragments.pagerThreeMVP.presenter.PagerThreePresenter;
import com.silianchuangye.sumao.success.fragments.shoppingCart.dialog.Cart_MyDialog;
import com.silianchuangye.sumao.success.fragments.type.SelectCallBack;
import com.silianchuangye.sumao.success.utils.LogUtils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/4/20 0020.
 * 购物车界面
 */
public class PagerThree extends BasePager implements AdapterView.OnItemClickListener, SelectCallBack, IPagerThreeView {
    private CustomListView lv_Cart;
    private CartAdapter adapter;
    private List<CartInfo> list;
    private ImageView img_Cart_All_Select;
    private TextView tv_Cart_All_Price;
    private Button btn_Cart_Ok;
    private boolean all_Flag;
    private float all_Price;
    private Context ctx;
    private String id;
    private MyReciver my;
    private Cart_MyDialog dialog;
    private RelativeLayout relative_cart_null, relative_cart_tishi, relative_activity_cart_bottom;
    private List<Integer> imgList = new ArrayList<Integer>();
    private List list1 = new ArrayList();
    private List<String> list_id = new ArrayList<String>();
    //  private List<String> name=new ArrayList<String>();
    private List<String> kucong = new ArrayList<String>();
    private String name;
    private String ku_cong_number;
    private float number;
    private Context context;
    private List<String> id_String = new ArrayList<String>();
    private PagerThreePresenter pagerThreePresenter;
    private List<CartBean.CommerceItemBean> commerceItem;
    private RelativeLayout layout_gouwuche_value;
    private String value;
    private String temp;
    //屏幕高度
    private int screenHeight = 0;
    //软件盘弹起后所占高度阀值
    private int keyHeight = 0;
    private String bianliang;
    private String qigouliang;
    private int shuaaa;
    private EditText numValue;


    public class MyReciver extends BroadcastReceiver  {
        @Override
        public void onReceive(Context context, Intent intent) {
//            if (intent.getAction().equals("close_cart_dialog")) {
//                dialog.dismiss();
//            }
        }


    }

    @Override
    public void onDestroy() {
        super.onDestroy();
//        getActivity().unregisterReceiver(my);
    }

    @Override
    public void myClickSearch() {
    }

    @Override
    public void initDate() {
        initView();
    }

    private void initView() {
        //initList();
        ctx = getActivity();

        my = new MyReciver();
        IntentFilter intent = new IntentFilter();
        intent.addAction("close_cart_dialog");
        getActivity().registerReceiver(my, intent);
        rl_title.setVisibility(View.GONE);
        View v = View.inflate(getActivity(), R.layout.activity_cart, null);
        fl_content.addView(v);

        layout_gouwuche_value= (RelativeLayout) v.findViewById(R.id.layout_gouwuche_value);
        layout_gouwuche_value.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                if(oldBottom != 0 && bottom != 0 &&(oldBottom - bottom > keyHeight)){

                    //Toast.makeText(getActivity(), "监听到软键盘弹起...", Toast.LENGTH_SHORT).show();

                }else if(oldBottom != 0 && bottom != 0 &&(bottom - oldBottom > keyHeight)){
                    SharedPreferences sp=ctx.getSharedPreferences("sumao",Activity.MODE_PRIVATE);
                    String shuru=sp.getString("temp","");
                    Log.d("shuru的值",shuru);
                    if (shuru.equals("")){
                        shuru=temp;
                    }
                    Double jianshu=sub(Double.parseDouble(shuru),Double.parseDouble(qigouliang));
                    Log.d("jianshu的值",jianshu+"");
                    int beishu=(int)div(jianshu,Float.parseFloat(bianliang),0);
                    Log.d("倍数的值",beishu+"");
                    if (beishu<=0){
                        beishu=1;
                    }
                    Double shuliang=mul(beishu,Double.parseDouble(bianliang));
                    Log.d("数量的值",shuliang+"");
                    double shuvalue=add(shuliang,Double.parseDouble(qigouliang));
                    Log.d("输入框应该填入的值",shuvalue+"");
                    //holder.Edt_buy_num.setText(shuvalue+"");
                    number=Float.parseFloat(shuvalue+"");
                    updataView(shuaaa, lv_Cart, id);
//                    adapter.isupdateInfo(true);
                    adapter.notifyDataSetChanged();




//                        SharedPreferences sp=ctx.getSharedPreferences("sumao",Activity.MODE_PRIVATE);
//                        SharedPreferences.Editor editor=sp.edit();
//                        editor.putString("surukuang",shuvalue+"");
//                        editor.commit();


                }
            }
        });
        //获取屏幕高度
        screenHeight = this.getActivity().getWindowManager().getDefaultDisplay().getHeight();
        //阀值设置为屏幕高度的1/3
        keyHeight = screenHeight/3;

        relative_cart_null = (RelativeLayout) v.findViewById(R.id.relative_cart_null);
        relative_cart_tishi = (RelativeLayout) v.findViewById(R.id.relative_cart_tishi);
        relative_activity_cart_bottom = (RelativeLayout) v.findViewById(R.id.relative_activity_cart_bottom);
        lv_Cart = (CustomListView) v.findViewById(R.id.lv_activity_cart);
        btn_Cart_Ok = (Button) v.findViewById(R.id.btn_activity_cart_ok);
        img_Cart_All_Select = (ImageView) v.findViewById(R.id.img_activity_cart_allselect);
        tv_Cart_All_Price = (TextView) v.findViewById(R.id.tv_activity_cart_all_price);
//        adapter = new CartAdapter(getActivity(),list,this,dialog,list_id,ku_cong_number);
//       //adapter=new CartAdapter(getActivity(),list,list_id,ku_cong_number));
//        lv_Cart.setAdapter(adapter);


        lv_Cart.setOnItemClickListener(this);
        img_Cart_All_Select.setOnClickListener(this);
        btn_Cart_Ok.setOnClickListener(this);

        pop_view = View.inflate(getActivity(), R.layout.popview_cart_price, null);
        pop_btn = (Button) pop_view.findViewById(R.id.pop_cart_price_btn);
        pop_tv = (TextView) pop_view.findViewById(R.id.pop_cart_price_money_tv);
        pop_lv = (ListView) pop_view.findViewById(R.id.pop_cart_price_lv);
        //popwindow的适配器
        cartItemAdapter = new CartItemAdapter(item_list, getActivity(), imgList);
        pop_lv.setAdapter(cartItemAdapter);
        pagerThreePresenter = new PagerThreePresenter(this);


    }
    public  double add(double d1, double d2)
    {        // 进行加法运算
        BigDecimal b1 = new BigDecimal(d1);
        BigDecimal b2 = new BigDecimal(d2);
        return b1.add(b2).doubleValue();
    }
    public  double sub(double d1, double d2)
    {        // 进行减法运算
        BigDecimal b1 = new BigDecimal(d1);
        BigDecimal b2 = new BigDecimal(d2);
        return b1.subtract(b2).doubleValue();
    }
    public  double mul(double d1, double d2)
    {        // 进行乘法运算
        BigDecimal b1 = new BigDecimal(d1);
        BigDecimal b2 = new BigDecimal(d2);
        return b1.multiply(b2).doubleValue();
    }
    public  double div(double d1,
                       double d2,int len) {// 进行除法运算
        BigDecimal b1 = new BigDecimal(d1);
        BigDecimal b2 = new BigDecimal(d2);
        return b1.divide(b2,len,BigDecimal.
                ROUND_HALF_UP).doubleValue();
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.img_activity_cart_allselect:
                selectSwitch();

                break;
            case R.id.btn_activity_cart_ok:
                boolean flag = false;
                StringBuffer sb = new StringBuffer();
//                CartBean.CommerceItemBean info : commerceItem
                for (int i = 0; i < commerceItem.size(); i++) {
                    if (adapter.checked[i]) {
                        flag = true;
                        if (i == commerceItem.size() - 1)
                            sb.append(commerceItem.get(i).getCommerceItemId());
                        else
                            sb.append(commerceItem.get(i).getCommerceItemId() + ",");
                    }

                }
                if (flag) {
                    SharedPreferences sp = getActivity().getSharedPreferences("sumao", Activity.MODE_PRIVATE);
                    String _dynSessConf = sp.getString("unique", "");
                    LogUtils.log("sb.toString()---->" + sb.toString());
                    String strCommerceItemIds = sb.toString();
                    pagerThreePresenter.getGenerateOrder(_dynSessConf, strCommerceItemIds);
//                    intent.putExtra("all_price", tv_Cart_All_Price.getText().toString());
                } else {
                    Toast.makeText(getActivity(), "请选择要购买的商品", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.pop_cart_price_btn:
                boolean itemflag = false;
                for (CartItemInfo info : item_list) {
                    if (info.flag) {
                        itemflag = true;
                    }
                }
                if (itemflag) {
                    Toast.makeText(getActivity(), "生成订单", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getActivity(), "请选择要支付的银行", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    //    提交订单到订单界面
    @Override
    public void sendDataToOrderPage(String result) {
        if (result.contains("formError")) {
            Gson gson = new Gson();
            OrderErrorBean orderErrorBean = gson.fromJson(result, OrderErrorBean.class);
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < orderErrorBean.getFormExceptions().size(); i++) {
                sb.append(orderErrorBean.getFormExceptions().get(i).getLocalizedMessage());
            }
            Toast.makeText(ctx, sb.toString(), Toast.LENGTH_SHORT).show();
        } else {
            Gson gson = new Gson();
            Orders orderList = gson.fromJson(result, Orders.class);
            List<Orders.OrderIdListBean> orderIdListBean = orderList.getOrderIdList();
            for (int i = 0; i < orderIdListBean.size(); i++) {
                LogUtils.log("产品名称---->" + orderIdListBean.get(i).getCommerceItem().get(0).getProductName());
            }
//      TODO  数量修改
            Intent intent = new Intent();
            intent.setClass(getActivity(), PaymentsOrder.class);
            intent.putExtra("type", "cart");
            intent.putExtra("fix", (Serializable) orderIdListBean);
            startActivity(intent);
        }

    }

    private void selectSwitch() {
        all_Flag = !all_Flag;
        if (!all_Flag) {
            img_Cart_All_Select.setImageResource(R.mipmap.cart_select_null);
//            for (CartBean.CommerceItemBean info3 : commerceItem) {
//                info3.setSelectFlag(true);
//            }
//            adapter.notifyDataSetChanged();
//            refrashPrice();
            adapter.clearAll();
        } else {
            img_Cart_All_Select.setImageResource(R.mipmap.cart_select);
//            for (CartBean.CommerceItemBean info4 : commerceItem) {
//                info4.setSelectFlag(false);
//            }
//            adapter.notifyDataSetChanged();
//            refrashPrice();
            adapter.checkAll();
        }

    }

    @Override
    public void onResume() {
        super.onResume();
        list = new ArrayList<CartInfo>();
        showGouwuche();
    }


    public void showGouwuche() {
//        Loding.show(mActivity,"加载中......",false,null);
        SharedPreferences sp = getActivity().getSharedPreferences("sumao", Activity.MODE_PRIVATE);
        String unique_gouwuche = sp.getString("unique", "");
        String _dynSessConf = unique_gouwuche;
        pagerThreePresenter.getPagerThreeResult(_dynSessConf);
    }

    //    得到方法showGouwuche中的数据
    @Override
    public void setDataInPagerThree(String result) {
        Log.d("购物车的返回值",""+result);
        if (result.contains("commerceItem")) {
            Gson gson = new Gson();
            CartBean cartBean = gson.fromJson(result, CartBean.class);
            commerceItem = cartBean.getCommerceItem();
            //购物车理由数据
            relative_activity_cart_bottom.setVisibility(View.VISIBLE);
            relative_cart_tishi.setVisibility(View.VISIBLE);
            relative_cart_null.setVisibility(View.GONE);
            adapter = new CartAdapter(getActivity(), PagerThree.this, commerceItem, kucong);
            lv_Cart.setAdapter(adapter);
            adapter.notifyDataSetChanged();

        } else if (result.contains("fail")) {
            SharedPreferences sp = mActivity.getSharedPreferences("sumao", Activity.MODE_PRIVATE);
            SharedPreferences.Editor editor = sp.edit();
            editor.clear();
            editor.commit();
            Toast.makeText(ctx, "请重新登录", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(mActivity, MainActivity.class);
            intent.putExtra("roles", "buyer");
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        } else {
            relative_activity_cart_bottom.setVisibility(View.GONE);
            relative_cart_tishi.setVisibility(View.GONE);
            relative_cart_null.setVisibility(View.VISIBLE);
        }
        //Loding.dis();
    }

    @Override
    public void setDeleteGoodsData(String result, int position) {
        if (result.contains("orderid")) {
            // list.clear();
            commerceItem.remove(position);
            adapter.notifyDataSetChanged();
            Toast.makeText(ctx, "删除成功", Toast.LENGTH_SHORT).show();
            if (commerceItem.size() == 0) {
                relative_activity_cart_bottom.setVisibility(View.GONE);
                relative_cart_tishi.setVisibility(View.GONE);
                relative_cart_null.setVisibility(View.VISIBLE);
            }

        } else {
            try {
                JSONObject obj_result = new JSONObject(result);
                String message = obj_result.getString("formExceptions");
                JSONArray array = new JSONArray(message);
                JSONObject obj_array = array.getJSONObject(0);
                String info = obj_array.getString("localizedMessage");
                Toast.makeText(ctx, "" + info, Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void setCarsUpdate(String result) {
        Gson gson = new Gson();
        CartBean cartBean = gson.fromJson(result, CartBean.class);
        commerceItem.clear();
        commerceItem.addAll(cartBean.getCommerceItem());
        adapter.notifyDataSetChanged();
    }


    @Override
    public void myClickLeft() {

    }

    @Override
    public void myClickRight() {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
        if (parent.getId() == R.id.lv_activity_cart) {
            name = commerceItem.get(position).getProductName();
            Log.d("name的值", name);
            ku_cong_number = commerceItem.get(position).getSalePrice();
//            numValue= (EditText) lv_Cart.getChildAt(position).findViewById(R.id.tv_item_cart_buy_num);
//            Log.d("num的值",numValue.getText().toString()+"aaa");
            Toast.makeText(getContext(), "点击了一条item", Toast.LENGTH_SHORT).show();

        }
        if (parent.getId() == R.id.pop_cart_price_lv) {
            for (int i = 0; i < item_list.size(); i++) {
                if (i != position) {
                    item_list.get(i).flag = false;
                }
            }
            item_list.get(position).flag = !item_list.get(position).flag;
            cartItemAdapter.notifyDataSetChanged();
        }


    }


    @Override
    public void Call(int index) {
//        commerceItem.get(index).setSelectFlag(!commerceItem.get(index).isSelectFlag());
        adapter.notifyDataSetChanged();
        //  refrashPrice();
    }

    @Override
    public void CartNull(List<CartBean.CommerceItemBean> list) {
        if (list.size() == 0) {
            relative_activity_cart_bottom.setVisibility(View.GONE);
            relative_cart_tishi.setVisibility(View.GONE);
            relative_cart_null.setVisibility(View.VISIBLE);
        } else {
            relative_activity_cart_bottom.setVisibility(View.VISIBLE);
            relative_cart_tishi.setVisibility(View.VISIBLE);
            relative_cart_null.setVisibility(View.GONE);
        }
    }

    //统计总价方法
    private void refrashPrice() {
        all_Price = 0f;
        int index = 0;
        for (int i = 0; i < commerceItem.size(); i++) {
            if (adapter.checked[i]) {
                float price = Float.valueOf(commerceItem.get(i).getAmount());
                all_Price += price;
                index++;
            }
        }
        tv_Cart_All_Price.setText("￥:" + all_Price);
        pop_tv.setText(all_Price + "元");
        if (index == commerceItem.size()) {
            img_Cart_All_Select.setImageResource(R.mipmap.cart_select);
        } else {
            img_Cart_All_Select.setImageResource(R.mipmap.cart_select_null);
        }
    }

    //popwindow
    private View pop_view;
    private Button pop_btn;
    private TextView pop_tv;
    private ListView pop_lv;
    private CartItemAdapter cartItemAdapter;
    private List<CartItemInfo> item_list = new ArrayList<CartItemInfo>();


    class CartAdapter extends BaseAdapter {
        private List<CartBean.CommerceItemBean> list;
        private Context ctx;
        private Cart_MyDialog dialog;
        private SelectCallBack call;

        private String name_chanpin;
        private List<String> kucong;
        private List<String> list_id;

        private String kucong_value;
        private String price;
        private String all_price;

        private boolean[] checked;



        public CartAdapter(Context ctx, SelectCallBack call, List<CartBean.CommerceItemBean> list, List<String> kucong) {
            this.ctx = ctx;
            this.list = list;
            this.call = call;
            this.kucong = kucong;
            checked = new boolean[list.size()];
            for (int i = 0; i < list.size(); i++) {
                checked[i] = false;
            }
        }

        public void chiceState(int post) {
            checked[post] = !checked[post];
            this.notifyDataSetChanged();
        }

        public void setUnCheck(int post) {
            checked[post] = false;
            this.notifyDataSetChanged();
        }

//        public boolean isupdateInfo(boolean info){
//            this.isupdate=info;
//            return isupdate;
//        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, final ViewGroup parent) {
            shuaaa=position;
            final ViewHolder holder;
            if (convertView == null) {
                convertView = View.inflate(ctx, R.layout.item_cart_activity, null);
                holder = new ViewHolder();
                holder.Tv_all_price = (TextView) convertView.findViewById(R.id.tv_item_cart_all_price);
                holder.Edt_buy_num = (EditText) convertView.findViewById(R.id.tv_item_cart_buy_num);
                holder.Tv_cangku_name = (TextView) convertView.findViewById(R.id.tv_item_cart_cangku_name);
                holder.Tv_company = (TextView) convertView.findViewById(R.id.tv_item_cart_cangku_conmpany_name);
                holder.Tv_name = (TextView) convertView.findViewById(R.id.tv_item_cart_name);
                holder.Tv_pai_num = (TextView) convertView.findViewById(R.id.tv_item_cart_paihao_num);
                holder.Tv_price = (TextView) convertView.findViewById(R.id.tv_item_cart_cangku_danjia_price);
                holder.Tv_qiye = (TextView) convertView.findViewById(R.id.tv_item_cart_shengchan_name);
                holder.Tv_sort_name = (TextView) convertView.findViewById(R.id.tv_item_cart_pinzhong_name);
                holder.Img_select = (ImageView) convertView.findViewById(R.id.img_item_cart_select);
                holder.Img_del = (ImageView) convertView.findViewById(R.id.img_item_cart_del);
                holder.Tv_buy_Sub = (TextView) convertView.findViewById(R.id.img_item_cart_buy_sub);
                holder.Tv_buy_Add = (TextView) convertView.findViewById(R.id.img_item_cart_buy_add);
                holder.Tv_no_much = (TextView) convertView.findViewById(R.id.item_cart_tv_no_much);
                holder.relative_item_cart = (RelativeLayout) convertView.findViewById(R.id.relative_item_cart);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            id = list.get(position).getCommerceItemId().toString();
            bianliang=list.get(position).getQuantityAdjustmentUnit();
            qigouliang=list.get(position).getMinPurchaseQuantity();

            kucong_value = list.get(position).getSurplus();
            holder.Tv_name.setText(list.get(position).getProductName());
            holder.Tv_sort_name.setText(list.get(position).getParentCategories());
            holder.Tv_cangku_name.setText(list.get(position).getWarehouse());
            holder.Tv_pai_num.setText(list.get(position).getGradeNumber());
            holder.Tv_price.setText(list.get(position).getSalePrice());
            holder.Tv_qiye.setText(list.get(position).getManufacturer());
            holder.Tv_company.setText(list.get(position).getSalesCompanyDisplayName());

            holder.Edt_buy_num.setText(Float.parseFloat(list.get(position).getQuantity())+"");
//            }else {
//                holder.Edt_buy_num.setHint(Float.parseFloat(list.get(position).getQuantity())+"");
//            }

            Log.d("数量",list.get(position).getQuantity());
            holder.Tv_all_price.setText(list.get(position).getAmount() + "元");
            dialog = new Cart_MyDialog(ctx, holder.Tv_name.getText().toString(), kucong_value);
            //选中与不选中

            if (checked[position]) {
                holder.Img_select.setImageResource(R.mipmap.cart_select);
                refrashPrice();
                id = list.get(position).getCommerceItemId().toString();
                id_String.add(id);

            } else {
                refrashPrice();
                holder.Img_select.setImageResource(R.mipmap.cart_select_null);
            }
            holder.Img_select.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checked[position] = !checked[position];
                    call.Call(position);
                    //这里改变金嗯
                }
            });


            //删除按钮
            holder.Img_del.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (checked[position]){
                        checked[position] = !checked[position];
                        call.Call(position);
                    }

                    AlertDialog.Builder dialog = new AlertDialog.Builder(ctx);
                    dialog.setTitle("确定要删除该商品吗？");
                    dialog.setPositiveButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    dialog.setNegativeButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            new Thread() {
                                @Override
                                public void run() {
                                    //super.run();
                                    Log.d("商品的id", list.get(position).getCommerceItemId().toString());
                                    delete_data(list.get(position).getCommerceItemId().toString(), position);
                                    //notifyDataSetChanged();
                                }
                            }.start();
                        }
                    });
                    dialog.create().show();
//                    if (checked[position]){
//                        refrashPrice();
//                        holder.Img_select.setImageResource(R.mipmap.cart_select_null);
//                    }


                }
            });

            //增加或减少购买数
            holder.Tv_buy_Sub.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    adapter.setUnCheck(position);
                    String str;
//                    if (isupdate) {
                         str= holder.Edt_buy_num.getText().toString();
//                    }else {
//                         str=holder.Edt_buy_num.getHint().toString();
//                    }
                    if (str.equals("")) {
                        str = "0";
                    }
                    float num = Float.parseFloat(str);
                    float qigouliang=Float.parseFloat(list.get(position).getMinPurchaseQuantity());
                    Log.d("num的值",num+"");
                    Log.d("最小qigouliang",qigouliang+"");
                    Log.d("num的值",num+"");

                    //如果数量小于最小qigouliang
                    if (num <= qigouliang){
                        Log.d("数量小雨qigouliang","是de");
                       // num=Float.parseFloat(list.get(position).getMinPurchaseQuantity());
                        holder.Edt_buy_num.setText("" + qigouliang);
                        number = qigouliang;
                        Log.d("商品的--id", list.get(position).getCommerceItemId().toString());
                        //update_data(list.get(position).id.toString());

                        updataView(position, lv_Cart, list.get(position).getCommerceItemId().toString());
                        holder.relative_item_cart.setVisibility(View.GONE);
                    }else  if (num>Float.parseFloat(list.get(position).getMinPurchaseQuantity())){//正常
                        Log.d("zhangchang","zhengchang");
                        num = num - Float.parseFloat(list.get(position).getQuantityAdjustmentUnit());
                        Log.d("num的值",num+"");
                        number=num;
                        holder.Edt_buy_num.setText(num+"");
                        updataView(position, lv_Cart, list.get(position).getCommerceItemId().toString());
                        holder.relative_item_cart.setVisibility(View.GONE);
                    }
                    if (num == 0) {
                        Toast.makeText(ctx, "购买数量不能为零", Toast.LENGTH_SHORT).show();
                    }
                }
            });
            //数量的增加
            holder.Tv_buy_Add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    adapter.setUnCheck(position);
                    String str;
//                    if (isupdate){
                        str = holder.Edt_buy_num.getText().toString();
//                    }else {
//                        str = holder.Edt_buy_num.getHint().toString();
//                    }

                    if (str.equals("")) {
                        str = "0";
                    }
                    float num = Float.parseFloat(str);

                    num = num + Float.parseFloat(list.get(position).getQuantityAdjustmentUnit());


                    if (num >= Float.parseFloat(kucong_value)) {
                        //num=num;
                        holder.relative_item_cart.setVisibility(View.VISIBLE);
                        holder.Tv_no_much.setText("产品库存为" + num + "吨");

                    }
                    if (num <= Float.parseFloat(kucong_value) && num >= Float.parseFloat(list.get(position).getMinPurchaseQuantity())) {
                        holder.relative_item_cart.setVisibility(View.GONE);
                        holder.Edt_buy_num.setText("" + num);
                        number = num;
                        //list.get(position).id.toString()
                        Log.d("商品的--id", id);
                        //update_data(list.get(position).id.toString());
                        updataView(position, lv_Cart, list.get(position).getCommerceItemId().toString());

                    }
                }
            });
            holder.Edt_buy_num.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {

                    temp=holder.Edt_buy_num.getText().toString();

                    if (Double.parseDouble(temp) > Double.parseDouble(kucong_value)) {
                        holder.Edt_buy_num.setText((kucong_value));
                        holder.Edt_buy_num.setSelection(kucong_value.length());
                        number=Float.parseFloat(kucong_value);
                        updataView(position, lv_Cart, list.get(position).getCommerceItemId().toString());

                    }else if (Double.parseDouble(temp) < Double.parseDouble(kucong_value) && Double.parseDouble(temp)>0){
                        holder.Edt_buy_num.setSelection(temp.length());
                        Log.d("temp的值",temp);
                        SharedPreferences sharedPreferences=ctx.getSharedPreferences("sumao",Activity.MODE_PRIVATE);
                        SharedPreferences.Editor editor=sharedPreferences.edit();
                        editor.putString("temp",temp);
                        editor.commit();
                    }

                    float i = Float.parseFloat(temp);
                    if (i >= Float.parseFloat(kucong_value)) {
                        holder.relative_item_cart.setVisibility(View.VISIBLE);
                        holder.Tv_no_much.setText("产品库存为" + i + "吨");
                       // dialog.show();
                    } else {
                        holder.relative_item_cart.setVisibility(View.GONE);
                    }
                }
            });
//            holder.Edt_buy_num.addTextChangedListener(new TextWatcher() {
//                @Override
//                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//                }
//
//                @Override
//                public void onTextChanged(CharSequence s, int start, int before, int count) {
//
//                }
//
//                @Override
//                public void afterTextChanged(Editable s) {
//temp=holder.Edt_buy_num.getText().toString();
//                    Log.d("temp的值",temp);
//                    SharedPreferences sharedPreferences=ctx.getSharedPreferences("sumao",Activity.MODE_PRIVATE);
//                    SharedPreferences.Editor editor=sharedPreferences.edit();
//                    editor.putString("temp",temp);
//                    editor.commit();
//                }
//            });



            //value=holder.Edt_buy_num.getText().toString();
//            holder.Edt_buy_num.addTextChangedListener(new TextWatcher() {
//                @Override
//                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//                    temp = holder.Edt_buy_num.getText().toString();
//                }
//
//                @Override
//                public void onTextChanged(CharSequence s, int start, int before, int count) {
//                    if (s.length() == 0) {
//                        holder.Edt_buy_num.setText("0");
//                        holder.Edt_buy_num.setSelection(1);
//                    }
//                    if (s.toString().contains(".")) {
//                        if (s.length() - 1 - s.toString().indexOf(".") > 3) {
//                            s = s.toString().subSequence(0,
//                                    s.toString().indexOf(".") + 4);
//                            holder.Edt_buy_num.setText(s);
//                            holder.Edt_buy_num.setSelection(s.length());
//                        }
//                    }
//                    if (s.toString().trim().substring(0).equals(".")) {
//                        s = "0" + s;
//                        holder.Edt_buy_num.setText(s);
//                        holder.Edt_buy_num.setSelection(3);
//                    }
//
//                    if (s.toString().startsWith("0")
//                            && s.toString().trim().length() > 1) {
//                        if (!s.toString().substring(1, 2).equals(".")) {
//                            holder.Edt_buy_num.setText(s.subSequence(1, s.length()));
//                            holder.Edt_buy_num.setSelection(1);
//                            return;
//                        }
//                    }
//
//
//                }
//
//                @Override
//                public void afterTextChanged(Editable s) {
//                    temp = holder.Edt_buy_num.getText().toString();
//                    Log.d("输入框后的值",temp);
//                    SharedPreferences sp=ctx.getSharedPreferences("sumao",Activity.MODE_PRIVATE);
//                    SharedPreferences.Editor editor=sp.edit();
//                    editor.putString("editText",temp);
//                    editor.commit();
////                    if (Double.parseDouble(temp) > Double.parseDouble(kucong_value)) {
////                        holder.Edt_buy_num.setText((kucong_value));
////                        holder.Edt_buy_num.setSelection(kucong_value.length());
////                        number=Float.parseFloat(kucong_value);
//                        updataView(position, lv_Cart, list.get(position).getCommerceItemId().toString());
//
//                    }else {
////                        SharedPreferences sharedPreferences=ctx.getSharedPreferences("sumao",Activity.MODE_PRIVATE);
////                        String aa=sharedPreferences.getString("surukuang","");
////                        if (!aa.equals("")){
////                            holder.Edt_buy_num.setText((aa));
////                            number=Float.parseFloat(aa);
////                            updataView(position, lv_Cart, list.get(position).getCommerceItemId().toString());
////                            adapter.notifyDataSetChanged();
////                        }
//                    }
//
//                    float i = Float.parseFloat(temp);
//                    if (i >= Float.parseFloat(kucong_value)) {
//                        holder.relative_item_cart.setVisibility(View.VISIBLE);
//                        holder.Tv_no_much.setText("产品库存为" + i + "吨");
//                       // dialog.show();
//                    } else {
//                        holder.relative_item_cart.setVisibility(View.GONE);
//
//                    }
//







//                }
//            });
            return convertView;
        }



        //    取消全选
        public void clearAll() {
            for (int i = 0; i < checked.length; i++) {
                checked[i] = false;
            }
            adapter.notifyDataSetChanged();
        }

        //    全选
        public void checkAll() {
            for (int i = 0; i < checked.length; i++) {
                checked[i] = true;
            }
            adapter.notifyDataSetChanged();

        }


        public void delete_data(String commerceId, int position) {
            SharedPreferences sp = ctx.getSharedPreferences("sumao", Activity.MODE_PRIVATE);
            String unique_delete = sp.getString("unique", "");
            String _dynSessConf = unique_delete;
            pagerThreePresenter.getDeleteGoodsResult(_dynSessConf, commerceId, position);
        }




    }
    public void updataView(final int posi, final ListView listView, String commerceId) {
        int visibleFirstPosi = listView.getFirstVisiblePosition();
        int visibleLastPosi = listView.getLastVisiblePosition();
        if (posi >= visibleFirstPosi && posi <= visibleLastPosi) {
            View view = listView.getChildAt(posi - visibleFirstPosi);
            final ViewHolder viewHolder = (ViewHolder) view.getTag();
            String amountUnitScale = "1000";
            String qty = number + "";
            SharedPreferences sp = ctx.getSharedPreferences("sumao", Activity.MODE_PRIVATE);
            String unique_update = sp.getString("unique", "");
            String _dynSessConf = unique_update;
            pagerThreePresenter.getCarsUpdateResult(amountUnitScale, qty, commerceId, _dynSessConf);
        } else {

        }
    }


    public class ViewHolder {
        TextView Tv_name;
        TextView Tv_sort_name;
        TextView Tv_cangku_name;
        TextView Tv_pai_num;
        TextView Tv_price;
        TextView Tv_qiye;
        TextView Tv_company;
        EditText Edt_buy_num;
        TextView Tv_buy_Sub, Tv_buy_Add;
        TextView Tv_all_price, Tv_no_much;
        ImageView Img_select, Img_del;
        RelativeLayout relative_item_cart;
    }




}

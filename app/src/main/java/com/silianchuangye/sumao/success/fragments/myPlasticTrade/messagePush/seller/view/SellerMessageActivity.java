package com.silianchuangye.sumao.success.fragments.myPlasticTrade.messagePush.seller.view;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.SharedPreferences;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ActionBarOverlayLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jingchen.pulltorefresh.PullToRefreshLayout;
import com.jingchen.pulltorefresh.PullableListView;
import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.messagePush.adapter.SellerMessageAdapter;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.messagePush.seller.presonter.SellerPresonter;
import com.silianchuangye.sumao.success.utils.MarqueeView;
import com.silianchuangye.sumao.success.utils.ShowCalendar;
import com.silianchuangye.sumao.success.utils.ThreeLevelLindage.MenuItemAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class SellerMessageActivity extends AppCompatActivity implements SellerMessageView,View.OnClickListener,AdapterView.OnItemClickListener {
    private SellerPresonter sellerPresonter;
    private ImageView img_create_logistics_back;
    private ImageView img_create_logistics_search;
    private PullToRefreshLayout layoutPull;
    private ListView pullList;
    private List<Map<String,Object>> list;
    private List<Map<String,Object>> listall;
    private PopupWindow popupWindow;
    private ImageView img_pop_back;
    private TextView tv_pop_canle;
    private TextView start_pop_date, end_pop_date, tv_state_pop;
    private EditText tv_pop_peisong;
    private EditText edt_pop_name, edt_pop_cangku;
    private ListView lv_start_date, lv_end_date, lv_state, lv_peisong;
    private LinearLayout linear2, linear_shi, linear_qu, linear_sheng;
    private Button btn_pop_customer_search;
    private List<String> popList;
    private List<String> planStateList;
    private List<String> planStateEnglish;
    private List<String> logisticsNameList;
    private ArrayAdapter pop_adapter;
    private RelativeLayout title;
    //private List<String> popList;
    private String tonghzhiId="";
    private String fasongfang="";
    private String tongzhititle="";
    private String startTime="";
    private String endTime="";
    private String unique="";
    private EditText tv_pop_customer_qu_edit;
    private  SellerMessageAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_push);
        sellerPresonter=new SellerPresonter(this);
        init();
       // initDate();
        SharedPreferences sp=getSharedPreferences("sumao", Activity.MODE_PRIVATE);
        unique=sp.getString("unique","");
        sellerPresonter.geinfo(tonghzhiId,fasongfang,tongzhititle,startTime,endTime,"1",unique);
    }
    public void init(){
        title= (RelativeLayout) findViewById(R.id.title);
        img_create_logistics_back= (ImageView) findViewById(R.id.img_create_logistics_back);
        img_create_logistics_back.setOnClickListener(this);
        img_create_logistics_search= (ImageView) findViewById(R.id.img_create_logistics_search);
        img_create_logistics_search.setOnClickListener(this);
        layoutPull= (PullToRefreshLayout) findViewById(R.id.layoutPull);
        //pullList= (PullableListView) findViewById(R.id.pullList);
        pullList=((ListView)layoutPull.getPullableView());
        layoutPull.setOnPullListener(new PullToRresh());
        list=new ArrayList<>();
        listall=new ArrayList<>();
    }
    private void showPop() {
        View popView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.pop_message, null);
        popupWindow = new PopupWindow(findViewById(R.id.layout_find), ActionBarOverlayLayout.LayoutParams.MATCH_PARENT, ActionBarOverlayLayout.LayoutParams.WRAP_CONTENT);
        TextView tv_name= (TextView) popView.findViewById(R.id.name);
        ImageView xia= (ImageView) popView.findViewById(R.id.xia);
        xia.setVisibility(View.GONE);
//        ImageView img_create_logistics_back1= (ImageView) popView.findViewById(R.id.img_create_logistics_back1);
//        img_create_logistics_back1.setOnClickListener(new View.OnClickListener() {
//                                                          @Override
//                                                          public void onClick(View v) {
//                                                              popupWindow.dismiss();
//                                                          }
//                                                      }
//        );

        tv_name.setText("编号通知:");
//        TextView tv_create_logistics_search= (TextView) popView.findViewById(R.id.tv_create_logistics_search);
//        tv_create_logistics_search.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                popupWindow.dismiss();
//            }
//        });

        TextView tv_Time= (TextView) popView.findViewById(R.id.Time);
        tv_Time.setText("发送时间:");
        TextView tv_sheng= (TextView) popView.findViewById(R.id.sheng);
        tv_sheng.setText("至");
        TextView tv_state= (TextView) popView.findViewById(R.id.state);
        tv_state.setText("发送方:");
//        TextView tv_cangku= (TextView) popView.findViewById(R.id.cangku);
//        tv_cangku.setText("仓库名称:");
        TextView tv_way= (TextView) popView.findViewById(R.id.way);
        tv_way.setText("通知标题:");

        // tv_pop_canle = (TextView) popView.findViewById(R.id.tv_create_logistics_search);
        //img_pop_back = (ImageView) popView.findViewById(R.id.img_create_logistics_back1);
        edt_pop_name = (EditText) popView.findViewById(R.id.tv_pop_customer_num);//产品名称
        edt_pop_cangku = (EditText) popView.findViewById(R.id.tv_pop_customer_name);//仓库名称
        start_pop_date = (TextView) popView.findViewById(R.id.demand_start_time);//计划开始日期
        end_pop_date = (TextView) popView.findViewById(R.id.demand_end_time);//计划结束日期
        tv_state_pop = (TextView) popView.findViewById(R.id.tv_pop_customer_qu);//提报状态
        tv_state_pop.setVisibility(View.GONE);
        tv_pop_customer_qu_edit= (EditText) popView.findViewById(R.id.tv_pop_customer_qu_edit);
        tv_pop_customer_qu_edit.setVisibility(View.VISIBLE);

        tv_pop_peisong = (EditText) popView.findViewById(R.id.tv_pop_customer_zhuangtai);//配送方式
        lv_start_date = (ListView) popView.findViewById(R.id.lv_sheng);//开始日期下
        lv_end_date = (ListView) popView.findViewById(R.id.lv_shi);//结束日期下的
        lv_state = (ListView) popView.findViewById(R.id.lv_qu);//提报状态下
        lv_peisong = (ListView) popView.findViewById(R.id.lv_zhuangtai);//配送方式下
        btn_pop_customer_search = (Button) popView.findViewById(R.id.btn_pop_customer_search);//立即查询
        linear2 = (LinearLayout) popView.findViewById(R.id.linear2);
        linear_sheng = (LinearLayout) popView.findViewById(R.id.linear_sheng);
        linear_shi = (LinearLayout) popView.findViewById(R.id.linear_shi);
       // linear_qu = (LinearLayout) popView.findViewById(R.id.mention_state_ll);
        // img_pop_back.setOnClickListener(this);
        //tv_pop_canle.setOnClickListener(this);
        linear2.setOnClickListener(this);
        linear_sheng.setOnClickListener(this);
        linear_shi.setOnClickListener(this);
        //linear_qu.setOnClickListener(this);
        // lv_peisong.setOnItemClickListener(this);
        lv_state.setOnItemClickListener(this);
        lv_end_date.setOnItemClickListener(this);
        lv_start_date.setOnItemClickListener(this);
        btn_pop_customer_search.setOnClickListener(this);
        popupWindow.setContentView(popView);
        popupWindow.setTouchable(true);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.setOutsideTouchable(true);
        popupWindow.setFocusable(true);

        // popupWindow.showAtLocation(iv_Search, Gravity.TOP, 0, 0);
        popupWindow.showAsDropDown(title,0,0);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                backgroundAlpha(1f);
                img_create_logistics_back.setVisibility(View.VISIBLE);
            }
        });



    }
    public void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = this.getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        this.getWindow().setAttributes(lp);
    }


    @Override
    public void getInfoforseller(String result) {
        Log.d("卖家通知的返回值",result);
        if (result.contains("noticeList")) {
            try {
                JSONObject jsonObject = new JSONObject(result);
                String noticeList = jsonObject.getString("noticeList");
                JSONArray jsonArray = new JSONArray(noticeList);
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject object = jsonArray.getJSONObject(i);
                    String sellerMessage = object.getString("sellerMessage");
                    Log.d("sellerMessage的值", sellerMessage);
                    JSONObject object_sellerMessage = new JSONObject(sellerMessage);
                    String orgName = object_sellerMessage.getString("orgName");
                    String id = object_sellerMessage.getString("id");
                    String title = object_sellerMessage.getString("title");
                    Log.d("得到的数据", orgName + id + title);

                    Map<String, Object> map = new Hashtable<>();
                    map.put("orgName", orgName);
                    map.put("id", id);
                    map.put("title", title);
                    list.add(map);
                }
                Log.d("list的长度", list.size() + "list的长度");
                adapter = new SellerMessageAdapter(SellerMessageActivity.this);
                pullList.setAdapter(adapter);
                adapter.clearData();
                adapter.addData(list);
                adapter.notifyDataSetChanged();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }else{
            Toast.makeText(SellerMessageActivity.this, "没有查询到数据!", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_create_logistics_back:
                finish();
                break;
            case R.id.img_create_logistics_search:
                //弹出搜索框
               // backgroundAlpha(0.5f);
                showPop();
                break;
            case R.id.linear_sheng:
//                ShowLv(lv_start_date);
//                ShowCalendar.showDate(start_pop_date, this, false);
                if (end_pop_date.getText().toString().equals("") || end_pop_date.getText().toString().isEmpty())
                    ShowCalendar.showDate(start_pop_date, this, false);
                else {
                    Calendar d = Calendar.getInstance(Locale.CHINA);
//创建一个日历引用d，通过静态方法getInstance() 从指定时区 Locale.CHINA 获得一个日期实例
                    Date myDate = new Date();
//创建一个Date实例
                    d.setTime(myDate);
//设置日历的时间，把一个新建Date实例myDate传入
                    int year = d.get(Calendar.YEAR);
                    int month = d.get(Calendar.MONTH);
                    int day = d.get(Calendar.DAY_OF_MONTH);
//获得日历中的 year month day
                    DatePickerDialog dlg = new DatePickerDialog(this, new MyClick1(), year, month, day);
                    DatePicker datePicker = dlg.getDatePicker();
                    try {
                        datePicker.setMaxDate(new SimpleDateFormat("yyyy-MM-dd").parse(end_pop_date.getText().toString())
                                .getTime());
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    dlg.show();
                }
                HideLv(lv_end_date);
                HideLv(lv_peisong);
                HideLv(lv_state);
                break;
            case R.id.linear_shi:
//                ShowLv(lv_end_date);

                if (start_pop_date.getText().toString().equals("") || start_pop_date.getText().toString().isEmpty())
                    ShowCalendar.showDate(end_pop_date, this, false);
                else {

                    Calendar d = Calendar.getInstance(Locale.CHINA);
//创建一个日历引用d，通过静态方法getInstance() 从指定时区 Locale.CHINA 获得一个日期实例
                    Date myDate = new Date();
//创建一个Date实例
                    d.setTime(myDate);
//设置日历的时间，把一个新建Date实例myDate传入
                    int year = d.get(Calendar.YEAR);
                    int month = d.get(Calendar.MONTH);
                    int day = d.get(Calendar.DAY_OF_MONTH);
//获得日历中的 year month day
                    DatePickerDialog dlg = new DatePickerDialog(this, new MyClick(), year, month, day);
                    DatePicker datePicker = dlg.getDatePicker();
                    try {
                        datePicker.setMinDate(new SimpleDateFormat("yyyy-MM-dd").parse(start_pop_date.getText().toString())
                                .getTime());
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    dlg.show();
                }


                HideLv(lv_start_date);
                HideLv(lv_peisong);
                HideLv(lv_state);
                break;
            case R.id.btn_pop_customer_search:
                //adapter.clearData();
                list.clear();
                popupWindow.dismiss();
                tonghzhiId=edt_pop_name.getText().toString();
                fasongfang=tv_pop_customer_qu_edit.getText().toString();
                tongzhititle=tv_pop_peisong.getText().toString();
                startTime=start_pop_date.getText().toString();
                endTime=end_pop_date.getText().toString();
                sellerPresonter.geinfo(tonghzhiId,fasongfang,tongzhititle,startTime,endTime,"1",unique);

                break;
//            case R.id.mention_state_ll:
//                ShowLv(lv_state);
//                HideLv(lv_end_date);
//                HideLv(lv_peisong);
//                HideLv(lv_start_date);
             //   break;


        }

    }



    private void HideLv(ListView lv) {
        lv.setVisibility(View.GONE);
    }
//    private void initDate() {
//        popList = new ArrayList<String>();
//        planStateList = new ArrayList<>();
////        confirmed:已确定，Pending: 待确定，canceled:已取消
//        planStateList.add("塑贸通知");
//        planStateList.add("企业信息修改");
//        //  planStateList.add("已取消");
//        planStateEnglish = new ArrayList<>();
//        planStateEnglish.add("0");
//        planStateEnglish.add("1");
//
//        logisticsNameList = new ArrayList<>();
//        logisticsNameList.add("卖家配送");
//        logisticsNameList.add("自提");
////        logisticsNameIDList = new ArrayList<>();
////        //TODO   这里需要修改
////        logisticsNameIDList.add("250072");
////        logisticsNameIDList.add("250072");
//    }
//    private void ShowLv(ListView lv) {
//        if (lv == lv_state) {
//            popList.clear();
//            popList.addAll(planStateList);
//        }
//        lv.setVisibility(View.VISIBLE);
//        pop_adapter = new ArrayAdapter<String>(this, R.layout.item_pop_lv, R.id.tv_pop_lv, popList);
//        lv.setAdapter(pop_adapter);
//    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()) {
            case R.id.lv_sheng:
                start_pop_date.setText(popList.get(position).toString());
                HideLv(lv_start_date);
                break;
            case R.id.lv_shi:
                end_pop_date.setText(popList.get(position).toString());
                HideLv(lv_end_date);
                break;
            case R.id.lv_qu:
                tv_state_pop.setText(popList.get(position).toString());
                HideLv(lv_state);
                break;
        }
    }

    class PullToRresh  implements PullToRefreshLayout.OnPullListener{
        int page=1;
        //下拉刷新
        @Override
        public void onRefresh(PullToRefreshLayout pullToRefreshLayout) {

            //adapter.clearData();
            list.clear();
            sellerPresonter.geinfo(tonghzhiId,fasongfang,tongzhititle,startTime,endTime,"1",unique);
        }
        //上拉加载
        @Override
        public void onLoadMore(PullToRefreshLayout pullToRefreshLayout) {
            page=page+1;
            sellerPresonter.geinfo(tonghzhiId,fasongfang,tongzhititle,startTime,endTime,page+"",unique);
        }
    }
    class MyClick implements DatePickerDialog.OnDateSetListener {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            end_pop_date.setText(year + "-" + (monthOfYear+1) + "-" + dayOfMonth);
        }
    }

    class MyClick1 implements DatePickerDialog.OnDateSetListener {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            start_pop_date.setText(year + "-" + (monthOfYear+1) + "-" + dayOfMonth);
        }
    }
}

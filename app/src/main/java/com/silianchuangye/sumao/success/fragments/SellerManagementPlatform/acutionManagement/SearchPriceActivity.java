package com.silianchuangye.sumao.success.fragments.SellerManagementPlatform.acutionManagement;


import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;

import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v7.widget.ActionBarOverlayLayout;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.api.GoogleApiClient;
import com.silianchuangye.sumao.success.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class SearchPriceActivity extends AppCompatActivity {
    private ImageView iv_back, iv_search;
    private ListView lv_Demo;
    private List<Map<String, Object>> list;
    private SimpleAdapter adapter;
    private RelativeLayout layout_f;
    private ImageView iv_way,iv_riqi,iv_zhuangtai,iv_fabufang;
    private ListView lv_way,lv_zhuangtai,lv_fabufang;
    private boolean isGone;
   // private EditText ed_fenlei,ed_paihao,ed_name;
    private TextView tv_way,tv_riqi,tv_zhuangtai,tv_fabufangmingcheng;
    private LinearLayout layout10,layout11,layout12,layout_13;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_price);
        init();
        addData();
        event();
        isGone=true;

    }

    /**
     * 初始化数据
     */
    public void init() {
        iv_back = (ImageView) findViewById(R.id.iv_Back_Price_Search);
        iv_search = (ImageView) findViewById(R.id.iv_search);
        lv_Demo = (ListView) findViewById(R.id.lv_Price_Search);
        list = new ArrayList<Map<String, Object>>();
        layout_f= (RelativeLayout) findViewById(R.id.layout_f);
    }

    /**
     * 添加数据
     */
    public void addData() {
        for (int i = 0; i <= 4; i++) {
            Map<String, Object> map1 = new Hashtable<String, Object>();
            map1.put("name", "兰州石化7042");
            map1.put("state", "公开竞拍");
            map1.put("time", "结束时间 2016-07-18 12:00:00");
            map1.put("firmname", "兰州石化7042");
            map1.put("type", "默认");
            map1.put("min", "1吨");
            map1.put("max", "5吨");
            map1.put("way", "自提");
            map1.put("address", "河南安阳");
            map1.put("cangku", "迅邦1号仓库");
            map1.put("number", "6100元");
            map1.put("count", "100吨");
            list.add(map1);

        }

        adapter = new SimpleAdapter(SearchPriceActivity.this, list, R.layout.item_price_search,
                new String[]{"name", "state", "time", "firmname", "type", "min", "max", "way", "address", "cangku", "number", "count"},
                new int[]{R.id.tv_price_search_item,
                        R.id.tv_price_search_type,
                        R.id.tv_time_price_search,
                        R.id.tv_price_search_name_firm,
                        R.id.tv_price_search_type_firm,
                        R.id.tv_price_search_min,
                        R.id.tv_price_search_max,
                        R.id.tv_price_search_way,
                        R.id.tv_price_search_address,
                        R.id.tv_price_search_cangku,
                        R.id.tv_price_search_number,
                        R.id.tv_price_search_count}
        );
        lv_Demo.setAdapter(adapter);

    }

    /**
     * 事件监听
     */
    public void event() {
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SearchPriceActivity.this.finish();
            }
        });
        iv_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backgroundAlpha(0.5f);
                //弹出popupwindow
                View view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.item_popupwindow_search, null);
                final PopupWindow popupWindow = new PopupWindow(findViewById(R.id.layout_f), ActionBarOverlayLayout.LayoutParams.MATCH_PARENT, ActionBarOverlayLayout.LayoutParams.WRAP_CONTENT);
                popupWindow.setContentView(view);
                EditText ed_fenlei= (EditText) view.findViewById(R.id.ed_fenlai);
                EditText ed_paihao= (EditText) view.findViewById(R.id.ed_paihao);
                EditText ed_name= (EditText) view.findViewById(R.id.ed_name);
                tv_way= (TextView) view.findViewById(R.id.tv_way);
                tv_riqi= (TextView) view.findViewById(R.id.tv_riqi);
                tv_zhuangtai= (TextView) view.findViewById(R.id.tv_zhuangtai);
                tv_fabufangmingcheng= (TextView) view.findViewById(R.id.tv_fabufangmingcheng);
                Button bt_Search= (Button) view.findViewById(R.id.bt_Search);
                iv_way= (ImageView) view.findViewById(R.id.iv_way);
                iv_riqi= (ImageView) view.findViewById(R.id.iv_riqi);
                iv_zhuangtai= (ImageView) view.findViewById(R.id.iv_zhuangtai);
                iv_fabufang= (ImageView) view.findViewById(R.id.iv_fangbufang);
                layout10= (LinearLayout) view.findViewById(R.id.layout_10);
                //  layout11= (LinearLayout) view.findViewById(R.id.layout_11);
                layout12= (LinearLayout) view.findViewById(R.id.layout_12);
                layout_13= (LinearLayout) view.findViewById(R.id.layout_13);
                lv_way= (ListView) view.findViewById(R.id.lv_way);
                lv_zhuangtai= (ListView) view.findViewById(R.id.lv_zhuangtai);
                lv_fabufang= (ListView) view.findViewById(R.id.lv_fabufang);
                //  cv_riqi= (CalendarView) view.findViewById(R.id.cv_riqi);

                /**
                 * 事件监听
                 */
                bt_Search.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        /**
                         * 调用搜索接口
                         */
                    }
                });
                tv_way.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        /**
                         * ListView的显示与隐藏
                         */
                        if (isGone){
                            layout10.setVisibility(View.VISIBLE);
//                            layout11.setVisibility(View.GONE);
//                            layout_13.setVisibility(View.GONE);
                            final List<String> list=new ArrayList<String>();
                            for (int i=0;i<=4;i++){
                                list.add("测试数据");
                            }
                            ArrayAdapter<String> adapter=new ArrayAdapter<String>(SearchPriceActivity.this,R.layout.item_for_textview,R.id.tv_text,list);
                            lv_way.setAdapter(adapter);
                            lv_way.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                                    tv_way.setText(list.get(position).toString());

                                    layout10.setVisibility(View.GONE);

                                }
                            });
                            //  lv_way.setVisibility(View.GONE);
                        }
                    }
                });

                iv_way.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        /**
                         * ListView的显示与隐藏
                         */
                        if (isGone){
                            layout10.setVisibility(View.VISIBLE);
//                            layout11.setVisibility(View.GONE);
//                            layout_13.setVisibility(View.GONE);
                            final List<String> list=new ArrayList<String>();
                            for (int i=0;i<=4;i++){
                                list.add("测试数据");
                            }
                            ArrayAdapter<String> adapter=new ArrayAdapter<String>(SearchPriceActivity.this,R.layout.item_for_textview,R.id.tv_text,list);
                            lv_way.setAdapter(adapter);
                            lv_way.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                                    tv_way.setText(list.get(position).toString());

                                    layout10.setVisibility(View.GONE);

                                }
                            });
                            //  lv_way.setVisibility(View.GONE);
                        }
                    }
                });

                tv_riqi.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Calendar calend1 = Calendar.getInstance();
                        calend1.setTimeInMillis(System.currentTimeMillis());
                        int year = calend1.get(Calendar.YEAR);
                        int month = calend1.get(Calendar.MONTH) + 1;
                        int day = calend1.get(Calendar.DAY_OF_MONTH);
                        DatePickerDialog dialog3 = new DatePickerDialog(
                                SearchPriceActivity.this,
                                new DatePickerDialog.OnDateSetListener() {

                                    @Override
                                    public void onDateSet(DatePicker view,
                                                          int year, int monthOfYear,
                                                          int dayOfMonth) {
//                                        Toast.makeText(SearchPriceActivity.this,
//                                                "" + year + "年" + (monthOfYear)
//                                                        + "月" + dayOfMonth + "日", Toast.LENGTH_LONG).show();
                                        tv_riqi.setText(year + "年" + (monthOfYear +1)
                                                + "月" + dayOfMonth + "日");
                                    }
                                }, year, month, day);
                        dialog3.show();
                    }



                });
                iv_riqi.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        /**
                         * 调用系统日期
                         */
                        //显示日期

                        Calendar calend1 = Calendar.getInstance();
                        calend1.setTimeInMillis(System.currentTimeMillis());
                        int year = calend1.get(Calendar.YEAR);
                        int month = calend1.get(Calendar.MONTH) + 1;
                        int day = calend1.get(Calendar.DAY_OF_MONTH);
                        DatePickerDialog dialog3 = new DatePickerDialog(
                                SearchPriceActivity.this,
                                new DatePickerDialog.OnDateSetListener() {

                                    @Override
                                    public void onDateSet(DatePicker view,
                                                          int year, int monthOfYear,
                                                          int dayOfMonth) {
                                        Toast.makeText(SearchPriceActivity.this,
                                                "" + year + "年" + (monthOfYear)
                                                        + "月" + dayOfMonth + "日", Toast.LENGTH_LONG).show();
                                        tv_riqi.setText(year + "年" + (monthOfYear)
                                                + "月" + dayOfMonth + "日");
                                    }
                                }, year, month, day);
                        dialog3.show();
                    }

                });
                tv_zhuangtai.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        /**
                         * Listview的隐藏与显示
                         */
                        if (isGone){
                            layout12.setVisibility(View.VISIBLE);
                            final List<String> list=new ArrayList<String>();
                            for (int i=0;i<=4;i++){
                                list.add("测试数据");
                            }
                            ArrayAdapter<String> adapter=new ArrayAdapter<String>(SearchPriceActivity.this,R.layout.item_for_textview,R.id.tv_text,list);
                            lv_zhuangtai.setAdapter(adapter);
                            lv_zhuangtai.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                                    tv_zhuangtai.setText(list.get(position).toString());
                                    layout12.setVisibility(View.GONE);
                                }
                            });


                        }
                    }
                });
                iv_zhuangtai.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        /**
                         * ListView的隐藏与显示
                         */
                        if (isGone){
                            layout12.setVisibility(View.VISIBLE);
                            final List<String> list=new ArrayList<String>();
                            for (int i=0;i<=4;i++){
                                list.add("测试数据");
                            }
                            ArrayAdapter<String> adapter=new ArrayAdapter<String>(SearchPriceActivity.this,R.layout.item_for_textview,R.id.tv_text,list);
                            lv_zhuangtai.setAdapter(adapter);
                            lv_zhuangtai.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                                    tv_zhuangtai.setText(list.get(position).toString());
                                    layout12.setVisibility(View.GONE);
                                }
                            });


                        }
                    }
                });
                tv_fabufangmingcheng.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        /**
                         * 发布方的ListView的隐藏与显示
                         */
                        if (isGone){
                            layout_13.setVisibility(View.VISIBLE);
                            final List<String> list=new ArrayList<String>();
                            for (int i=0;i<=4;i++){
                                list.add("测试数据");
                            }
                            ArrayAdapter<String> adapter=new ArrayAdapter<String>(SearchPriceActivity.this,R.layout.item_for_textview,R.id.tv_text,list);
                            lv_fabufang.setAdapter(adapter);
                            lv_fabufang.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                                    tv_fabufangmingcheng.setText(list.get(position).toString());
                                    layout_13.setVisibility(View.GONE);
                                }
                            });

                        }
                    }
                });
                iv_fabufang.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        /**
                         * 发布方的ListView的隐藏与显示
                         */
                        if (isGone){
                            layout_13.setVisibility(View.VISIBLE);
                            final List<String> list=new ArrayList<String>();
                            for (int i=0;i<=4;i++){
                                list.add("测试数据");
                            }
                            ArrayAdapter<String> adapter=new ArrayAdapter<String>(SearchPriceActivity.this,R.layout.item_for_textview,R.id.tv_text,list);
                            lv_fabufang.setAdapter(adapter);
                            lv_fabufang.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                                    tv_fabufangmingcheng.setText(list.get(position).toString());
                                    layout_13.setVisibility(View.GONE);
                                }
                            });

                        }
                    }
                });



                popupWindow.setTouchable(true);
                popupWindow.setBackgroundDrawable(new BitmapDrawable());
                popupWindow.setOutsideTouchable(true);
                popupWindow.setFocusable(true);

                popupWindow.showAtLocation(lv_Demo, Gravity.TOP, 0,65);

                popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                    @Override
                    public void onDismiss() {
                        popupWindow.dismiss();
                        backgroundAlpha(1f);
                    }
                });

            }
        });
        /**
         * ListView的item的点击事件
         */
        lv_Demo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //跳转到详情界面
               // Intent intent = new Intent(SearchPriceActivity.this, PriceSearchinfoActivity.class);
                //startActivity(intent);
                if (position%2==0){
                     Intent intent = new Intent(SearchPriceActivity.this, PriceSearchinfoActivity.class);
                    startActivity(intent);
                }else{
                     Intent intent = new Intent(SearchPriceActivity.this, AcutionResultActivity.class);
                    startActivity(intent);

                }
            }
        });

    }

    /**
     * popupwindow
     */

    public void popupWindow_toShow() {


    }
    //设置背景透明
    public void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        getWindow().setAttributes(lp);
    }



}

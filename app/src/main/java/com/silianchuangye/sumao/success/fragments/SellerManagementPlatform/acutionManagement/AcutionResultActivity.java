package com.silianchuangye.sumao.success.fragments.SellerManagementPlatform.acutionManagement;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ActionBarOverlayLayout;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.Log;
import android.view.Gravity;
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
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.custom.customCalendar.CalendarView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AcutionResultActivity extends AppCompatActivity {
    private ImageView iv_Back,iv_Search,iv_way,iv_riqi,iv_zhuangtai,iv_fabufang;
    private ListView lv_acution_result,lv_way,lv_zhuangtai,lv_fabufang;
    private List<Map<String,Object>> list;
    private SimpleAdapter adapter;
    private boolean isGone;
  //  private EditText ed_fenlei,ed_paihao,ed_name;
    private TextView tv_way,tv_riqi,tv_zhuangtai,tv_fabufangmingcheng;
    private LinearLayout layout10,layout11,layout12,layout_13;
    //private CalendarView cv_riqi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acution_result);
        init();
        event();
        addDate();
    }
    public void init(){
        iv_Back= (ImageView) findViewById(R.id.iv_Back_acution_Result);
        iv_Search= (ImageView) findViewById(R.id.iv_auction_result_search);
        lv_acution_result= (ListView) findViewById(R.id.lv_acution_result);
        list=new ArrayList<Map<String,Object>>();
        isGone=true;

    }
    public void event(){
        iv_Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AcutionResultActivity.this.finish();
            }
        });

        iv_Search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                backgroundAlpha(0.5f);
                View view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.item_popupwindow_search, null);
                final PopupWindow popupWindow = new PopupWindow(findViewById(R.id.layout_g), ActionBarOverlayLayout.LayoutParams.MATCH_PARENT, ActionBarOverlayLayout.LayoutParams.WRAP_CONTENT);
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
                            ArrayAdapter<String> adapter=new ArrayAdapter<String>(AcutionResultActivity.this,R.layout.item_for_textview,R.id.tv_text,list);
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
                            ArrayAdapter<String> adapter=new ArrayAdapter<String>(AcutionResultActivity.this,R.layout.item_for_textview,R.id.tv_text,list);
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
                                    AcutionResultActivity.this,
                                    new DatePickerDialog.OnDateSetListener() {

                                        @Override
                                        public void onDateSet(DatePicker view,
                                                              int year, int monthOfYear,
                                                              int dayOfMonth) {
                                            Toast.makeText(AcutionResultActivity.this,
                                                    "" + year + "年" + (monthOfYear)
                                                            + "月" + dayOfMonth + "日", Toast.LENGTH_LONG).show();
                                            tv_riqi.setText(year + "年" + (monthOfYear )
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
                                    AcutionResultActivity.this,
                                    new DatePickerDialog.OnDateSetListener() {

                                        @Override
                                        public void onDateSet(DatePicker view,
                                                              int year, int monthOfYear,
                                                              int dayOfMonth) {
                                            Toast.makeText(AcutionResultActivity.this,
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
                            ArrayAdapter<String> adapter=new ArrayAdapter<String>(AcutionResultActivity.this,R.layout.item_for_textview,R.id.tv_text,list);
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
                            ArrayAdapter<String> adapter=new ArrayAdapter<String>(AcutionResultActivity.this,R.layout.item_for_textview,R.id.tv_text,list);
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
                            ArrayAdapter<String> adapter=new ArrayAdapter<String>(AcutionResultActivity.this,R.layout.item_for_textview,R.id.tv_text,list);
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
                            ArrayAdapter<String> adapter=new ArrayAdapter<String>(AcutionResultActivity.this,R.layout.item_for_textview,R.id.tv_text,list);
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

                popupWindow.showAtLocation(lv_acution_result, Gravity.TOP, 0,65);

                popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                    @Override
                    public void onDismiss() {
                        popupWindow.dismiss();
                      backgroundAlpha(1f);
                    }
                });

            }
        });
        lv_acution_result.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (list.get(position).get("isok").toString().equals("竞拍成功")){
                    /**
                     * 跳转到竞拍成功界面
                     */
                    Intent intent=new Intent(AcutionResultActivity.this,AuctionSuccessActivity.class);
                    startActivity(intent);
                }else if (list.get(position).get("isok").toString().equals("竞拍失败")){
                    /**
                     * 跳转到竞拍失败的界面
                     */
                    Intent intent=new Intent(AcutionResultActivity.this,AcutionFailActivity.class);
                    startActivity(intent);
                }
            }
        });

    }

    //设置背景透明
    public void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        getWindow().setAttributes(lp);
    }

    public void addDate(){
        for (int i=0;i<=5;i++){
            Map<String,Object> map=new HashMap<String,Object>();
            map.put("name","兰州石化6038");
            if (i%2==0){
              map.put("isok","竞拍成功");

            }else{
              map.put("isok","竞拍失败");
            }
            map.put("firm_name","燕山石化7032");
            map.put("state","公开竞拍");
            map.put("price","10000元");
            map.put("number","200吨");
            map.put("min","1吨");
            map.put("max","1吨");
            map.put("way","自提");
            map.put("address","河南安阳");
            map.put("cangku","迅邦物流1号库");
            list.add(map);
        }
        adapter=new SimpleAdapter(AcutionResultActivity.this,list,R.layout.item_auction_result,
                 new String[]{"name","isok","firm_name","state","price","number",
                              "min","max","way","address","cangku"
                 },new int[]{
                R.id.tv_price_search_item,
                R.id.tv_isok,
                R.id.tv_price_search_name_firm,
                R.id.tv_price_search_type_firm,
                R.id.tv_price_search_number,
                R.id.tv_price_search_count,
                R.id.tv_price_search_min,
                R.id.tv_price_search_max,
                R.id.tv_price_search_way,
                R.id.tv_price_search_address,
                R.id.tv_price_search_cangku

        }
                );
        lv_acution_result.setAdapter(adapter);


    }
}

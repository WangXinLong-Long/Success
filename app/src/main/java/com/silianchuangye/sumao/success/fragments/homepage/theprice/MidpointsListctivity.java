package com.silianchuangye.sumao.success.fragments.homepage.theprice;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.location.Poi;
import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.adapter.MyPageAdapter;


import java.util.ArrayList;
import java.util.List;

public class MidpointsListctivity extends AppCompatActivity {
    private ArrayList<Fragment> listFragment;
    private TabLayout tlDemo;
    private ViewPager vpDemo;
    private MyPageAdapter adapter;
    private ImageView back;
    private ImageView search;
    private TextView dingwei;
    private LocationClient myloaction;
    private BDLocationListener mylistenner=new MyLocationListener();

    private  String Location1;
    private PopupWindow popupWindow;
    private View popView;
    private ImageView img_chinanorth_back;
    private Button btn_chinanorth_ok;
    private CheckBox checkBox1_chinanorth;
    private CheckBox checkBox2_chinanorth;
    private WebView web_chinanorth;
    private int i=1;
    private SharedPreferences share;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_midpoints_listctivity);
        /**
         * 初始化定位类
         */
        myloaction=new LocationClient(getApplicationContext());
        /**
         * 设置监听参数
         */
        myloaction.registerLocationListener(mylistenner);
        BDLocation location=new BDLocation();
        Toast.makeText(MidpointsListctivity.this, "定位", Toast.LENGTH_SHORT).show();
        initLocation();
        myloaction.start();
        MidpointsListctivity.MyLocationListener inner=new MidpointsListctivity.MyLocationListener();
        inner.onReceiveLocation(location);
        back= (ImageView) findViewById(R.id.ivBack_orderPrice_layout_top);
        search= (ImageView) findViewById(R.id.ivSearch_orderPrice_layout_top);
        listFragment=new ArrayList<Fragment>();
        ChinaNorthFragment north=new ChinaNorthFragment();
        listFragment.add(north);
        ChinaCenterFragment center=new ChinaCenterFragment();
        listFragment.add(center);
        ChinaEastFragment east=new ChinaEastFragment();
        listFragment.add(east);
        ChinaSouthFragment south=new ChinaSouthFragment();
        listFragment.add(south);
        ChinaWestFragment west=new ChinaWestFragment();
        listFragment.add(west);
        adapter=new MyPageAdapter(getSupportFragmentManager());
        adapter.setData(listFragment);

        ArrayList<String> listString=new ArrayList<String>();
        listString.add("华北");
        listString.add("华中");
        listString.add("华东");
        listString.add("华南");
        listString.add("华西");

        adapter.setTitles(listString);

        tlDemo= (TabLayout) findViewById(R.id.tlDemo_OrderPrice_activity);
        vpDemo= (ViewPager) findViewById(R.id.vpDemo_OrderPrice_activity);
        vpDemo.setAdapter(adapter);
        tlDemo.setupWithViewPager(vpDemo);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MidpointsListctivity.this.finish();
            }
        });
        dingwei= (TextView) findViewById(R.id.ivSearch_orderPrice_layout_top_dingwei);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }
    private void initLocation(){
        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy
        );//可选，默认高精度，设置定位模式，高精度，低功耗，仅设备
        option.setCoorType("bd09ll");//可选，默认gcj02，设置返回的定位结果坐标系
        int span=1000;
        option.setScanSpan(span);//可选，默认0，即仅定位一次，设置发起定位请求的间隔需要大于等于1000ms才是有效的
        option.setIsNeedAddress(true);//可选，设置是否需要地址信息，默认不需要
        option.setOpenGps(true);//可选，默认false,设置是否使用gps
        option.setLocationNotify(true);//可选，默认false，设置是否当gps有效时按照1S1次频率输出GPS结果
        option.setIsNeedLocationDescribe(true);//可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”
        option.setIsNeedLocationPoiList(true);//可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到
        option.setIgnoreKillProcess(false);//可选，默认true，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认不杀死
        option.SetIgnoreCacheException(false);//可选，默认false，设置是否收集CRASH信息，默认收集
        option.setEnableSimulateGps(false);//可选，默认false，设置是否需要过滤gps仿真结果，默认需要
        myloaction.setLocOption(option);
    }
    class MyLocationListener implements BDLocationListener {
        public void onReceiveLocation(BDLocation location) {
            //Receive Location
            StringBuffer sb = new StringBuffer(256);

            if (location.getLocType() == BDLocation.TypeGpsLocation) {// GPS定位结果

                sb.append("\naddr : ");
                Location1=location.getAddrStr();
                sb.append(location.getAddrStr());

                




            } else if (location.getLocType() == BDLocation.TypeNetWorkLocation) {// 网络定位结果
                sb.append("\naddr : ");
                sb.append(location.getAddrStr());

                //运营商信息
                sb.append("\noperationers : ");
                sb.append(location.getOperators());
                sb.append("\ndescribe : ");
                sb.append("网络定位成功");

                MidpointsListctivity.this.dingwei.setText(location.getCity());
            } else if (location.getLocType() == BDLocation.TypeOffLineLocation) {// 离线定位结果
                sb.append("\ndescribe : ");
                sb.append("离线定位成功，离线定位结果也是有效的");
            } else if (location.getLocType() == BDLocation.TypeServerError) {
                sb.append("\ndescribe : ");
                sb.append("服务端网络定位失败，可以反馈IMEI号和大体定位时间到loc-bugs@baidu.com，会有人追查原因");
            } else if (location.getLocType() == BDLocation.TypeNetWorkException) {
                sb.append("\ndescribe : ");
                sb.append("网络不同导致定位失败，请检查网络是否通畅");
            } else if (location.getLocType() == BDLocation.TypeCriteriaException) {
                sb.append("\ndescribe : ");
                sb.append("无法获取有效定位依据导致定位失败，一般是由于手机的原因，处于飞行模式下一般会造成这种结果，可以试着重启手机");
            }

            sb.append("\nlocationdescribe : ");
            sb.append(location.getLocationDescribe());// 位置语义化信息
            List<Poi> list = location.getPoiList();// POI数据
            if (list != null) {
                sb.append("\npoilist size = : ");
                sb.append(list.size());
                for (Poi p : list) {
                    sb.append("\npoi= : ");
                    sb.append(p.getId() + " " + p.getName() + " " + p.getRank());
                }
            }

            Log.i("BaiduLocationApiDem", sb.toString());
        }



    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SharedPreferences share=getSharedPreferences("share",Context.MODE_PRIVATE);
        SharedPreferences.Editor edt=share.edit();
        edt.putString("s1","");
        edt.commit();
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        SharedPreferences share = getSharedPreferences("share", Context.MODE_PRIVATE);
        String str = share.getString("s", "");
        String str1=share.getString("s1","");
        Log.e("TAG", "s" + str);
        if(hasFocus){
            if (!str.equals("1")&&!str1.equals("0")) {
                showPopWindow();
                backgroundAlpha(0.5f);
            }
        }

    }

    public void showPopWindow(){
        popView=View.inflate(MidpointsListctivity.this,R.layout.chinanorth_popwindow,null);
        popView.measure(0,0);
        img_chinanorth_back= (ImageView) popView.findViewById(R.id.img_chinanorth_back);
        checkBox1_chinanorth= (CheckBox) popView.findViewById(R.id.check1_chinanorth_pop);
        checkBox2_chinanorth= (CheckBox) popView.findViewById(R.id.check2_chinanorth_pop);
        btn_chinanorth_ok= (Button) popView.findViewById(R.id.btn_chinanarth_ok);
        web_chinanorth= (WebView) popView.findViewById(R.id.web_chinanarth);
        web_chinanorth.getSettings().setJavaScriptEnabled(true);//支持js
        web_chinanorth.loadUrl("http://www.baidu.com/");//载入网页地址
        web_chinanorth.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                web_chinanorth.loadUrl(url);
                return true;
            }
        });
        int w=MidpointsListctivity.this.getWindowManager().getDefaultDisplay().getWidth();
        popupWindow=new PopupWindow(popView,w,popView.getMeasuredHeight());
//        pop.setFocusable(true);
//        pop.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.showAtLocation(tlDemo, Gravity.BOTTOM,0,0);
        img_chinanorth_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
        btn_chinanorth_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkBox1_chinanorth.isChecked()){
                    popupWindow.dismiss();
                    SharedPreferences share=MidpointsListctivity.this.getSharedPreferences("share", Context.MODE_PRIVATE);
                    SharedPreferences.Editor edt=share.edit();
                    edt.putString("s1","0");
                    edt.commit();
                }
                if(checkBox2_chinanorth.isChecked()){
                    SharedPreferences share=MidpointsListctivity.this.getSharedPreferences("share", Context.MODE_PRIVATE);
                    SharedPreferences.Editor edt=share.edit();
                    edt.putString("s","1");
                    edt.commit();
                }
            }
        });

        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                backgroundAlpha(1f);
            }
        });
    }
    public void backgroundAlpha(float bgAlpha)
    {
        WindowManager.LayoutParams lp = MidpointsListctivity.this.getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        MidpointsListctivity.this.getWindow().setAttributes(lp);
    }

}

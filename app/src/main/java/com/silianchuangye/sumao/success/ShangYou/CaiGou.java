package com.silianchuangye.sumao.success.ShangYou;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.ShangYou.PlanDayMVP.view.PlanDay;
import com.silianchuangye.sumao.success.adapter.MyPageAdapter;
import com.silianchuangye.sumao.success.fragments.homepage.UpstreamDirectSellingMVP.bean.vipProductBean.VipProductBean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CaiGou extends AppCompatActivity implements OnClickListener,AdapterView.OnItemClickListener{
    private ArrayList<Fragment> listFragment;
    private TabLayout tlDemo;
    private ViewPager vpDemo;
    private MyPageAdapter adapter;
    private ImageView img_create_logistics_back;
    private ImageView img_create_logistics_search;
    private PopupWindow popupWindow;
    private View popView;
    private ImageView img_pop_back;
    private TextView tv_pop_canle;
    private TextView start_pop_date,end_pop_date,tv_state_pop,tv_pop_peisong;
    private EditText edt_pop_name,edt_pop_cangku;
    private ListView lv_start_date,lv_end_date,lv_state,lv_peisong;
    private LinearLayout linear2,linear_shi,linear_qu,linear_sheng;
    private Button btn_pop_customer_search;
    private List<String> popList;
    private ArrayAdapter pop_adapter;
    private VipProductBean vipProductBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cai_gou);
        Intent intent = getIntent();
        vipProductBean =(VipProductBean)  intent.getSerializableExtra("vipProductBean");
        initDate();
        initView();
    }

    private void initDate() {
        popList=new ArrayList<String>();
        for(int i=0;i<5;i++){
            popList.add("listitem"+i);
        }
    }

    private void initView() {
        img_create_logistics_back= (ImageView) findViewById(R.id.img_create_logistics_back);
        img_create_logistics_search= (ImageView) findViewById(R.id.img_create_logistics_search);
        img_create_logistics_search.setOnClickListener(this);
        img_create_logistics_back.setOnClickListener(this);

        listFragment=new ArrayList<Fragment>();
        PlanDay plan=new PlanDay();
        Bundle bundle = new Bundle();
        bundle.putSerializable("vipProductBean",vipProductBean);
        plan.setArguments(bundle);
        listFragment.add(plan);
        SeePlan see=new SeePlan();
        listFragment.add(see);

        adapter=new MyPageAdapter(getSupportFragmentManager());
        adapter.setData(listFragment);

        ArrayList<String> listString=new ArrayList<String>();
        listString.add("日计划提报");
        listString.add("查看计划");

        adapter.setTitles(listString);

        tlDemo= (TabLayout) findViewById(R.id.tlDemo_OrderPrice_activity);
        vpDemo= (ViewPager) findViewById(R.id.vpDemo_OrderPrice_activity);
        vpDemo.setAdapter(adapter);
        tlDemo.setupWithViewPager(vpDemo);

        popView=View.inflate(this,R.layout.plan_popwindow,null);
        tv_pop_canle= (TextView) popView.findViewById(R.id.tv_create_logistics_search);
        img_pop_back= (ImageView) popView.findViewById(R.id.img_create_logistics_back1);
        edt_pop_name= (EditText) popView.findViewById(R.id.tv_pop_customer_num);//产品名称
        edt_pop_cangku= (EditText) popView.findViewById(R.id.tv_pop_customer_name);//仓库名称
        start_pop_date= (TextView) popView.findViewById(R.id.tv_pop_customer_sheng);//计划开始日期
        end_pop_date= (TextView) popView.findViewById(R.id.tv_pop_customer_shi);//计划结束日期
        tv_state_pop= (TextView) popView.findViewById(R.id.tv_pop_customer_qu);//提报状态
        tv_pop_peisong= (TextView) popView.findViewById(R.id.tv_pop_customer_zhuangtai);//配送方式
        lv_start_date= (ListView) popView.findViewById(R.id.lv_sheng);//开始日期下
        lv_end_date= (ListView) popView.findViewById(R.id.lv_shi);//结束日期下的
        lv_state= (ListView) popView.findViewById(R.id.lv_qu);//提报状态下
        lv_peisong= (ListView) popView.findViewById(R.id.lv_zhuangtai);//配送方式下
        btn_pop_customer_search= (Button) popView.findViewById(R.id.btn_pop_customer_search);//立即查询
        linear2= (LinearLayout) popView.findViewById(R.id.linear2);
        linear_sheng= (LinearLayout) popView.findViewById(R.id.linear_sheng);
        linear_shi= (LinearLayout) popView.findViewById(R.id.linear_shi);
        linear_qu= (LinearLayout) popView.findViewById(R.id.linear_qu);
        img_pop_back.setOnClickListener(this);
        tv_pop_canle.setOnClickListener(this);
        linear2.setOnClickListener(this);
        linear_sheng.setOnClickListener(this);
        linear_shi.setOnClickListener(this);
        linear_qu.setOnClickListener(this);
        lv_peisong.setOnItemClickListener(this);
        lv_state.setOnItemClickListener(this);
        lv_end_date.setOnItemClickListener(this);
        lv_start_date.setOnItemClickListener(this);
        btn_pop_customer_search.setOnClickListener(this);
    }
    private void ShowLv(ListView lv){
        lv.setVisibility(View.VISIBLE);
        pop_adapter=new ArrayAdapter<String>(this,R.layout.item_pop_lv,R.id.tv_pop_lv,popList);
        lv.setAdapter(pop_adapter);
    }
    private void HideLv(ListView lv){
        lv.setVisibility(View.GONE);
    }

    private void showPop(){

        int w=getWindowManager().getDefaultDisplay().getWidth();
        popView.measure(0,0);
        popupWindow=new PopupWindow(popView,w,popView.getMeasuredHeight());
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.setFocusable(true);
        popupWindow.showAtLocation(img_create_logistics_search,Gravity.TOP,0,0);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                backgroundAlpha(1f);
            }
        });
    }
    public void backgroundAlpha(float bgAlpha){
        WindowManager.LayoutParams lp = this.getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        this.getWindow().setAttributes(lp);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_create_logistics_back:
                finish();
                break;
            case R.id.img_create_logistics_search:
                showPop();
                backgroundAlpha(0.5f);
                break;
            case R.id.linear_sheng:
                ShowLv(lv_start_date);
                HideLv(lv_end_date);
                HideLv(lv_peisong);
                HideLv(lv_state);
                break;
            case R.id.linear_shi:
                ShowLv(lv_end_date);
                HideLv(lv_start_date);
                HideLv(lv_peisong);
                HideLv(lv_state);
                break;
            case R.id.linear_qu:
                ShowLv(lv_state);
                HideLv(lv_end_date);
                HideLv(lv_peisong);
                HideLv(lv_start_date);
                break;
            case R.id.linear2:
                ShowLv(lv_peisong);
                HideLv(lv_end_date);
                HideLv(lv_start_date);
                HideLv(lv_state);
                break;
            case R.id.btn_pop_customer_search:
                popupWindow.dismiss();
                break;
            case R.id.tv_create_logistics_search:
                popupWindow.dismiss();
                break;
            case R.id.img_create_logistics_back1:
                popupWindow.dismiss();
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()){
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
            case R.id.lv_zhuangtai:
                tv_pop_peisong.setText(popList.get(position).toString());
                HideLv(lv_peisong);
                break;
        }
    }
}

package com.silianchuangye.sumao.success.customermanger;

import android.graphics.drawable.BitmapDrawable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.adapter.MyPageAdapter;

import java.util.ArrayList;

public class CustomerManager extends AppCompatActivity implements View.OnClickListener{
    private ImageView img_back,img_search;
    private ViewPager vp;
    private TabLayout tab;
    private ArrayList<Fragment> listFragment;
    ArrayList<String> listString=new ArrayList<String>();
    private MyPageAdapter adapter;
    private View popView;
    private PopupWindow popupWindow;
    private ImageView img_pop_back;
    private TextView tv_pop_canle,tv_pop_type,tv_pop_num,tv_pop_sheng,tv_pop_shi,
                        tv_pop_qu,tv_pop_name,tv_pop_zhuangtai,tv_pop_buy,tv_pop_maifang,
                        tv_pop_customer;
    private Button btn_pop_search;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customermanager);
        initDate();
        initView();

    }
    private void initDate() {
        listFragment=new ArrayList<Fragment>();
        All_Manager all=new All_Manager();
        Valid_Manager valid=new Valid_Manager();
        Inavild_Manager inavild=new Inavild_Manager();
        listFragment.add(all);
        listFragment.add(valid);
        listFragment.add(inavild);

        listString.add("全部客户");
        listString.add("有效客户");
        listString.add("无效客户");
    }

    private void initView() {
        img_back= (ImageView) findViewById(R.id.img_logistics_title_bar_back);
        img_search= (ImageView) findViewById(R.id.img_logistics_title_bar_search);
        tab= (TabLayout) findViewById(R.id.tlDemo_OrderPrice_activity);
        vp= (ViewPager) findViewById(R.id.vpDemo_OrderPrice_activity);

        adapter=new MyPageAdapter(getSupportFragmentManager());
        adapter.setData(listFragment);
        adapter.setTitles(listString);
        vp.setAdapter(adapter);
        tab.setupWithViewPager(vp);

        img_back.setOnClickListener(this);
        img_search.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_logistics_title_bar_back:
                finish();
                break;
            case R.id.img_logistics_title_bar_search:
                Toast.makeText(this,"pop",Toast.LENGTH_SHORT).show();
                showPop();
                backgroundAlpha(0.5f);
                break;
        }
    }
    private void showPop(){
        popView= View.inflate(this,R.layout.pop_customer,null);
        int w=getWindowManager().getDefaultDisplay().getWidth();
        popView.measure(0,0);
        popupWindow=new PopupWindow(popView,w,popView.getMeasuredHeight());
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.setFocusable(true);
        popupWindow.showAtLocation(vp, Gravity.TOP,0,50);
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
}

package com.silianchuangye.sumao.success.customermanger;

import android.graphics.drawable.BitmapDrawable;
import android.support.v4.app.Fragment;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.adapter.MyPageAdapter;

import java.util.ArrayList;
import java.util.List;

public class CustomerParent extends AppCompatActivity implements View.OnClickListener,AdapterView.OnItemClickListener{
    private ImageView img_back;
    private RelativeLayout relative_top;
    private ViewPager vp;
    private TabLayout tab;
    private ArrayList<Fragment> listFragment;
    ArrayList<String> listString=new ArrayList<String>();
    private MyPageAdapter adapter;
    private View popView;
    private PopupWindow popupWindow;
    private ImageView img_pop_back;
    private TextView tv_pop_canle,tv_pop_type,tv_pop_sheng,tv_pop_shi,
            tv_pop_qu,tv_pop_zhuangtai,tv_pop_buy,tv_pop_maifang,
            tv_pop_customer;
    private EditText tv_pop_num;
    private EditText edt_pop_name;
    private Button btn_pop_search;
    private ListView lv_type,lv_sheng,lv_shi,lv_qu,lv_zhuangtai,lv_buy,lv_maifang,lv_customer;
    private LinearLayout linear1,linear2,linear3,linear4,linear5,linear_shi,linear_qu,linear_sheng;
    private List<String> popList;
    private ArrayAdapter<String> pop_adapter;

    public CustomerParent() {
    }

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

        popList=new ArrayList<String>();
        for(int i=0;i<5;i++){
            popList.add("listitem"+i);
        }
    }

    private void initView() {
        img_back= (ImageView) findViewById(R.id.img_logistics_title_bar_back);
        relative_top= (RelativeLayout) findViewById(R.id.img_logistics_title_bar_search);
        tab= (TabLayout) findViewById(R.id.tlDemo_OrderPrice_activity);
        vp= (ViewPager) findViewById(R.id.vpDemo_OrderPrice_activity);

        adapter=new MyPageAdapter(getSupportFragmentManager());
        adapter.setData(listFragment);
        adapter.setTitles(listString);
        vp.setAdapter(adapter);
        tab.setupWithViewPager(vp);

        //popView下的控件
        popView= View.inflate(this,R.layout.pop_customer,null);
        img_pop_back= (ImageView) popView.findViewById(R.id.img_pop_title_bar_back);
        tv_pop_canle= (TextView) popView.findViewById(R.id.tv_pop_customer_canler);
        tv_pop_type= (TextView) popView.findViewById(R.id.tv_pop_customer_type);
        tv_pop_num= (EditText) popView.findViewById(R.id.tv_pop_customer_num);
        tv_pop_sheng= (TextView) popView.findViewById(R.id.tv_pop_customer_sheng);
        tv_pop_shi= (TextView) popView.findViewById(R.id.tv_pop_customer_shi);
        tv_pop_qu= (TextView) popView.findViewById(R.id.tv_pop_customer_qu);
        edt_pop_name= (EditText) popView.findViewById(R.id.tv_pop_customer_name);
        tv_pop_zhuangtai= (TextView) popView.findViewById(R.id.tv_pop_customer_zhuangtai);
        tv_pop_buy= (TextView) popView.findViewById(R.id.tv_pop_customer_buy);
        tv_pop_buy= (TextView) popView.findViewById(R.id.tv_pop_customer_buy);
        tv_pop_maifang= (TextView) popView.findViewById(R.id.tv_pop_customer_maifang);
        tv_pop_customer= (TextView) popView.findViewById(R.id.tv_pop_customer_customer);
        btn_pop_search= (Button) popView.findViewById(R.id.btn_pop_customer_search);
        lv_type= (ListView) popView.findViewById(R.id.lv_type);
        lv_sheng= (ListView) popView.findViewById(R.id.lv_sheng);
        lv_shi= (ListView) popView.findViewById(R.id.lv_shi);
        lv_qu= (ListView) popView.findViewById(R.id.lv_qu);
        lv_zhuangtai= (ListView) popView.findViewById(R.id.lv_zhuangtai);
        lv_buy= (ListView) popView.findViewById(R.id.lv_buy);
        lv_maifang= (ListView) popView.findViewById(R.id.lv_maifang);
        lv_customer= (ListView) popView.findViewById(R.id.lv_customer);
        linear1= (LinearLayout) popView.findViewById(R.id.linear1);
        linear2= (LinearLayout) popView.findViewById(R.id.linear2);
        linear3= (LinearLayout) popView.findViewById(R.id.linear3);
        linear4= (LinearLayout) popView.findViewById(R.id.linear4);
        linear5= (LinearLayout) popView.findViewById(R.id.linear5);
        linear_sheng= (LinearLayout) popView.findViewById(R.id.linear_sheng);
        linear_shi= (LinearLayout) popView.findViewById(R.id.linear_shi);
        linear_qu= (LinearLayout) popView.findViewById(R.id.linear_qu);
        linear1.setOnClickListener(this);
        linear2.setOnClickListener(this);
        linear3.setOnClickListener(this);
        linear4.setOnClickListener(this);
        linear5.setOnClickListener(this);
        linear_sheng.setOnClickListener(this);
        linear_shi.setOnClickListener(this);
        linear_qu.setOnClickListener(this);
        img_pop_back.setOnClickListener(this);
        tv_pop_canle.setOnClickListener(this);
        btn_pop_search.setOnClickListener(this);
        img_back.setOnClickListener(this);
        relative_top.setOnClickListener(this);
        lv_type.setOnItemClickListener(this);
        lv_sheng.setOnItemClickListener(this);
        lv_shi.setOnItemClickListener(this);
        lv_qu.setOnItemClickListener(this);
        lv_zhuangtai.setOnItemClickListener(this);
        lv_buy.setOnItemClickListener(this);
        lv_maifang.setOnItemClickListener(this);
        lv_customer.setOnItemClickListener(this);
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
            case R.id.img_pop_title_bar_back:
                popupWindow.dismiss();
                break;
            case R.id.tv_pop_customer_canler:
                popupWindow.dismiss();
                break;
            case R.id.linear1:
                ShowLv(lv_type);
                HideLv(lv_qu);
                HideLv(lv_sheng);
                HideLv(lv_shi);
                HideLv(lv_zhuangtai);
                HideLv(lv_buy);
                HideLv(lv_maifang);
                HideLv(lv_customer);
                break;
            case R.id.linear_sheng:
                ShowLv(lv_sheng);
                HideLv(lv_qu);
                HideLv(lv_type);
                HideLv(lv_shi);
                HideLv(lv_zhuangtai);
                HideLv(lv_buy);
                HideLv(lv_maifang);
                HideLv(lv_customer);
                break;
            case R.id.linear_shi:
                ShowLv(lv_shi);
                HideLv(lv_qu);
                HideLv(lv_type);
                HideLv(lv_sheng);
                HideLv(lv_zhuangtai);
                HideLv(lv_buy);
                HideLv(lv_maifang);
                HideLv(lv_customer);
                break;
            case R.id.linear_qu:
                ShowLv(lv_qu);
                HideLv(lv_sheng);
                HideLv(lv_type);
                HideLv(lv_shi);
                HideLv(lv_zhuangtai);
                HideLv(lv_buy);
                HideLv(lv_maifang);
                HideLv(lv_customer);
                break;
            case R.id.linear2:
                ShowLv(lv_zhuangtai);
                HideLv(lv_qu);
                HideLv(lv_type);
                HideLv(lv_shi);
                HideLv(lv_sheng);
                HideLv(lv_buy);
                HideLv(lv_maifang);
                HideLv(lv_customer);
                break;
            case R.id.linear3:
                ShowLv(lv_buy);
                HideLv(lv_qu);
                HideLv(lv_type);
                HideLv(lv_shi);
                HideLv(lv_zhuangtai);
                HideLv(lv_qu);
                HideLv(lv_maifang);
                HideLv(lv_customer);
                break;
            case R.id.linear4:
                ShowLv(lv_maifang);
                HideLv(lv_qu);
                HideLv(lv_type);
                HideLv(lv_shi);
                HideLv(lv_zhuangtai);
                HideLv(lv_buy);
                HideLv(lv_sheng);
                HideLv(lv_customer);
                break;
            case R.id.linear5:
                ShowLv(lv_customer);
                HideLv(lv_qu);
                HideLv(lv_type);
                HideLv(lv_shi);
                HideLv(lv_zhuangtai);
                HideLv(lv_buy);
                HideLv(lv_maifang);
                HideLv(lv_sheng);
                break;
            case R.id.btn_pop_customer_search:
                popupWindow.dismiss();
                Toast.makeText(this,"立即查询",Toast.LENGTH_SHORT).show();
                break;
        }
    }
    //显示popwindow
    private void showPop(){

        int w=getWindowManager().getDefaultDisplay().getWidth();
        popView.measure(0,0);
        popupWindow=new PopupWindow(popView,w,popView.getMeasuredHeight());
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.setFocusable(true);
        popupWindow.showAtLocation(vp, Gravity.TOP,0,65);
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
    private void ShowLv(ListView lv){
        lv.setVisibility(View.VISIBLE);
        pop_adapter=new ArrayAdapter<String>(this,R.layout.item_pop_lv,R.id.tv_pop_lv,popList);
        lv.setAdapter(pop_adapter);
    }
    private void HideLv(ListView lv){
        lv.setVisibility(View.GONE);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()){
            case R.id.lv_type:
                tv_pop_type.setText(popList.get(position).toString());
                HideLv(lv_type);
                break;
            case R.id.lv_sheng:
                tv_pop_sheng.setText(popList.get(position).toString());
                HideLv(lv_sheng);
                break;
            case R.id.lv_shi:
                tv_pop_shi.setText(popList.get(position).toString());
                HideLv(lv_shi);
                break;
            case R.id.lv_qu:
                tv_pop_qu.setText(popList.get(position).toString());
                HideLv(lv_qu);
                break;
            case R.id.lv_buy:
                tv_pop_buy.setText(popList.get(position).toString());
                HideLv(lv_buy);
                break;
            case R.id.lv_zhuangtai:
                tv_pop_zhuangtai.setText(popList.get(position).toString());
                HideLv(lv_zhuangtai);
                break;
            case R.id.lv_maifang:
                tv_pop_maifang.setText(popList.get(position).toString());
                HideLv(lv_maifang);
                break;
            case R.id.lv_customer:
                tv_pop_customer.setText(popList.get(position).toString());
                HideLv(lv_customer);
                break;
        }
    }
}

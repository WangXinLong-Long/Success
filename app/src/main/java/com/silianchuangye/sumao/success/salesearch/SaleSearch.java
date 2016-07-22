package com.silianchuangye.sumao.success.salesearch;

import android.graphics.drawable.BitmapDrawable;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.adapter.SaleSearh_Adapter;
import com.silianchuangye.sumao.success.fragments.bean.SaleSearchInfo;

import java.util.ArrayList;
import java.util.List;

public class SaleSearch extends AppCompatActivity implements View.OnClickListener{
    private ImageView img_back;
    private RelativeLayout relative_search;
    private ListView lv;
    private List<SaleSearchInfo> list;
    private SaleSearh_Adapter adapter;
    private View popView;
    private PopupWindow popWindow;
    private ImageView img_pop_back;
    private RelativeLayout relative_pop_calce;
    private Button btn_pop_search;
    private EditText edt_num,edt_cangku_name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sale_search);
        initDate();
        initView();
    }

    private void initDate() {
        list=new ArrayList<SaleSearchInfo>();
        for(int i=0;i<3;i++){
            SaleSearchInfo info1=new SaleSearchInfo();
            info1.title="兰州石化7024"+i;
            info1.num="00123489888"+i;
            info1.sort="聚乙烯"+i;
            info1.product_name="中石化华北公司"+i;
            info1.pai_num="LLDPE"+i;
            info1.price="4,500元";
            info1.sheng_num="10吨";
            info1.cangku_name="北京迅帮1库"+i;
            list.add(info1);
        }

    }

    private void initView() {
        img_back= (ImageView) findViewById(R.id.img_logistics_title_bar_back);
        relative_search= (RelativeLayout) findViewById(R.id.img_logistics_title_bar_search);
        lv= (ListView) findViewById(R.id.lv_sale_search);
        adapter=new SaleSearh_Adapter(list,this);
        lv.setAdapter(adapter);
        img_back.setOnClickListener(this);
        relative_search.setOnClickListener(this);

        popView=View.inflate(this,R.layout.salepop,null);
        img_pop_back= (ImageView) popView.findViewById(R.id.img_logistics_title_bar_back_sale_pop);
        relative_pop_calce= (RelativeLayout) popView.findViewById(R.id.img_logistics_title_bar_search_sale_pop);
        btn_pop_search= (Button) popView.findViewById(R.id.btn_salepop_search);
        edt_num= (EditText) popView.findViewById(R.id.tv_salepop_num);
        edt_cangku_name= (EditText) popView.findViewById(R.id.tv_salepop_cangku);
        img_pop_back.setOnClickListener(this);
        relative_pop_calce.setOnClickListener(this);
        btn_pop_search.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_logistics_title_bar_back:
                finish();
                break;
            case R.id.img_logistics_title_bar_search:
                Toast.makeText(this,"popwindow",Toast.LENGTH_SHORT).show();
                showPop();
                backgroundAlpha(0.5f);
                break;
            case R.id.img_logistics_title_bar_back_sale_pop:
                Toast.makeText(this,"返回",Toast.LENGTH_SHORT).show();
                popWindow.dismiss();
                break;
            case R.id.img_logistics_title_bar_search_sale_pop:
                popWindow.dismiss();
                break;
            case R.id.btn_salepop_search:
                popWindow.dismiss();
                break;
        }
    }
    private void showPop(){
        popView.measure(0,0);
        int w=getWindowManager().getDefaultDisplay().getWidth();
        popWindow=new PopupWindow(popView,w,popView.getMeasuredHeight());
        popWindow.setFocusable(true);
        popWindow.setBackgroundDrawable(new BitmapDrawable());
        popWindow.showAtLocation(lv, Gravity.TOP,0,55);
        popWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                backgroundAlpha(1f);
            }
        });
    }
    public void backgroundAlpha(float bgAlpha){
        WindowManager.LayoutParams lp=this.getWindow().getAttributes();
        lp.alpha=bgAlpha;
        this.getWindow().setAttributes(lp);
    }
}

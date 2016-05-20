package com.silianchuangye.sumao.success.fragments.homepage.preSale;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.silianchuangye.sumao.success.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/5/17 0017.
 */
public class PreSale extends Activity implements View.OnClickListener,AdapterView.OnItemClickListener{
    TextView tv_screen_title_bar_title;
    ImageView iv_screen_title_bar_search,iv_screen_title_bar_back;
    View popwindowView ;
    ListView application_lv,classification_lv,region_lv;
    List<String> lists;
    ArrayAdapter<String> adapter;
    PopupWindow popupWindow;
    RelativeLayout pre_sale_title;
    TextView application_et,classification_et,region_et;
    boolean flag=true;
    String first = "first";
    String second = "second";
    TextView classification,application,region;
    ListView pre_sale_listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pre_sale);
        tv_screen_title_bar_title = ((TextView) findViewById(R.id.tv_screen_title_bar_title));
        tv_screen_title_bar_title.setText("预售");
        iv_screen_title_bar_search = ((ImageView) findViewById(R.id.iv_screen_title_bar_search));
        iv_screen_title_bar_back = ((ImageView) findViewById(R.id.iv_screen_title_bar_back));
        iv_screen_title_bar_search.setOnClickListener(this);
        iv_screen_title_bar_back.setOnClickListener(this);
        pre_sale_title = ((RelativeLayout) findViewById(R.id.pre_sale_title));
        classification = ((TextView) findViewById(R.id.classification));
        application = ((TextView) findViewById(R.id.application));
        region = ((TextView) findViewById(R.id.region));
        pre_sale_listView = ((ListView) findViewById(R.id.pre_sale_listView));
        initdata();
        pre_sale_listView.setAdapter(adapter);
        pre_sale_listView.setOnItemClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.iv_screen_title_bar_search:
                Toast.makeText(this,"弹出选择窗口",Toast.LENGTH_SHORT).show();
                initView();
                initdata();
                showSearchDialog();
                backgroundAlpha(0.5f);
                break;
            case R.id.application_et:
                    first = "one";
                logicdisplayshowListView(application_lv,first);
                    hideListView(classification_lv);
                    hideListView(region_lv);

                break;
            case R.id.classification_et:
                first = "two";
                logicdisplayshowListView(classification_lv,first);
                hideListView(application_lv);
                hideListView(region_lv);

                break;
            case R.id.region_et:
                     first = "three";
                logicdisplayshowListView(region_lv,first);
                    hideListView(classification_lv);
                    hideListView(application_lv);

                break;

            case R.id.search_title:
                popupWindow.dismiss();
                break;
            case R.id.iv_screen_title_bar_back:
                finish();
                break;
            default:
                break;
        }

    }

    private void initdata() {
        lists = new ArrayList<>();
        for (int i = 0; i < 15 ; i++) {
            lists.add("列表"+i);
        }
        adapter = new ArrayAdapter<String>(this,
                R.layout.item_view,
                R.id.tv_item,lists);

    }

    /**
     * 如果已经打开一个listView，点击另一个，这个listView影藏
     * @param listview
     * @param first
     */
    private void logicdisplayshowListView(ListView listview,String first) {

        if (second.equals(first))
        {
            showListView(listview);
        }else {
            flag  = true;
            showListView(listview);
        }

    }
    private void showListView(ListView listview)
    {
        if(flag){
            listview.setVisibility(View.VISIBLE);
            listview.setAdapter(adapter);
            flag = false;
            second  = first;
        }else {
            listview.setVisibility(View.GONE);
            flag = true;
        }
    }

    private void hideListView(ListView listview)
    {
            listview.setVisibility(View.GONE);
    }

    private void initView() {
        popwindowView = View.inflate(this,R.layout.pre_sale_search,null);
        TextView tv_screen_title_bar_title = ((TextView) popwindowView.findViewById(R.id.tv_screen_title_bar_title));
        RelativeLayout search_title = ((RelativeLayout) popwindowView.findViewById(R.id.search_title));
        tv_screen_title_bar_title.setText("预售");

        application_et = ((TextView) popwindowView.findViewById(R.id.application_et));
        classification_et = ((TextView) popwindowView.findViewById(R.id.classification_et));
        region_et = ((TextView) popwindowView.findViewById(R.id.region_et));
        application_lv = ((ListView) popwindowView.findViewById(R.id.application_lv));
        classification_lv = ((ListView) popwindowView.findViewById(R.id.classification_lv));
        region_lv = ((ListView) popwindowView.findViewById(R.id.region_lv));
        application_et.setOnClickListener(this);
        classification_et.setOnClickListener(this);
        region_et.setOnClickListener(this);

        search_title.setOnClickListener(this);
        application_lv.setOnItemClickListener(this);
        classification_lv.setOnItemClickListener(this);
        region_lv.setOnItemClickListener(this);

    }

    private void showSearchDialog() {
        popwindowView.measure(0,0);
        int width = getWindowManager().getDefaultDisplay().getWidth();
        popupWindow = new PopupWindow(popwindowView,width,getWindowManager().getDefaultDisplay().getWidth());
        Log.e("TAG","width-------"+width);
        Log.e("TAG","heigh----------"+popwindowView.getMeasuredHeight());
        popupWindow.setFocusable(true);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
//        popupWindow.showAsDropDown(pre_sale_title,0,0);
        popupWindow.showAtLocation(pre_sale_title, Gravity.TOP,0,0);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                //popupWindow.dismiss();
                backgroundAlpha(1f);
            }
        });
    }
    public void backgroundAlpha(float bgAlpha)
    {
        WindowManager.LayoutParams lp =this.getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        this.getWindow().setAttributes(lp);
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        if(application_lv==parent){
            application_et.setText(adapter.getItem(position));
            application.setText(adapter.getItem(position));

            application_lv.setVisibility(View.GONE);
        }
        if(classification_lv==parent){
            classification_et.setText(adapter.getItem(position));
            classification.setText(adapter.getItem(position));
            classification_lv.setVisibility(View.GONE);
        }
        if(region_lv==parent){
            region_et.setText(adapter.getItem(position));
            region.setText(adapter.getItem(position));
            region_lv.setVisibility(View.GONE);
        }
        if(pre_sale_listView==parent){
            Intent intent = new Intent();
            intent.setClass(this,PreSaleDetailActivity.class);
            startActivity(intent);
        }

    }
}

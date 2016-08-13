package com.silianchuangye.sumao.success.fragments.homepage.preSale;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.adapter.PreSaleAdapter;
import com.silianchuangye.sumao.success.fragments.homepage.goodInStock.GoodsInStockActivityMVP.bean.SMCl;
import com.silianchuangye.sumao.success.model.PreSaleModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/5/17 0017.
 */
public class PreSale extends Activity implements View.OnClickListener, AdapterView.OnItemClickListener {
    TextView tv_screen_title_bar_title;
    ImageView iv_screen_title_bar_search, iv_screen_title_bar_back;
    View popwindowView;
    View listPopupWindowView;
    ListView popupWindowListView;
    LinearLayout bottom_pre_sale_search,popup_window_back;

    List<SMCl> lists;
    SMCl preSaleModel;
    PreSaleAdapter adapter;
    PopupWindow popupWindow;
    PopupWindow listPopupWindow;
    RelativeLayout pre_sale_title;
    TextView classification, application, region;
    //    TextView manufacturing_enterprise;
    ListView pre_sale_listView;
    ArrayList<String> mArrayList;
    RelativeLayout selection_condition;
    TextView split_line;
    private RelativeLayout application1;
    private RelativeLayout classification1;
    private RelativeLayout region1;

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
        application1 = ((RelativeLayout) findViewById(R.id.application1));
        classification1 = ((RelativeLayout) findViewById(R.id.classification1));
        region1 = ((RelativeLayout) findViewById(R.id.region1));
//        manufacturing_enterprise = ((TextView) findViewById(R.id.manufacturing_enterprise));
        pre_sale_listView = ((ListView) findViewById(R.id.pre_sale_listView));
        selection_condition = ((RelativeLayout) findViewById(R.id.selection_condition));
        split_line = ((TextView) findViewById(R.id.split_line));
        initdata();
        pre_sale_listView.setAdapter(adapter);
        pre_sale_listView.setOnItemClickListener(this);
        application1.setOnClickListener(this);
        classification1.setOnClickListener(this);
        region1.setOnClickListener(this);
//        manufacturing_enterprise.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_screen_title_bar_search:
                initView();
                showSearchDialog();
                break;
            case R.id.region1:

                initListPopupWindowView("地区");
                showListPopupWindow();
                break;
            case R.id.classification1:
                initListPopupWindowView("分类");
                showListPopupWindow();
                break;
            case R.id.application1:
                initListPopupWindowView("应用");
                showListPopupWindow();
                break;
         /*   case R.id.manufacturing_enterprise:
                initListPopupWindowView("生产企业");
                showListPopupWindow();
                break;*/

            case R.id.bottom_pre_sale_search:
                popupWindow.dismiss();
                break;
            case R.id.popup_window_back:
                listPopupWindow.dismiss();
                break;
            case R.id.iv_screen_title_bar_back:
                finish();
                break;

            default:
                break;
        }

    }

    private void showListPopupWindow() {
        listPopupWindowView.measure(0, 0);
        int width = getWindowManager().getDefaultDisplay().getWidth();
        listPopupWindow = new PopupWindow(listPopupWindowView, width,
                listPopupWindowView.getMeasuredHeight());
        listPopupWindow.setFocusable(true);
        listPopupWindow.setBackgroundDrawable(new BitmapDrawable());
        listPopupWindow.showAsDropDown(split_line, 0, 0);
    }

    private void initListPopupWindowView(final String s) {
        listPopupWindowView = View.inflate(this, R.layout.list_popup_window_view, null);
        popupWindowListView = (ListView) listPopupWindowView.findViewById(R.id.popup_window_list_view);
        popup_window_back = ((LinearLayout) listPopupWindowView.findViewById(R.id.popup_window_back));
        popup_window_back.setOnClickListener(this);
        mArrayList = new ArrayList<String>();
        final ArrayAdapter<String> popupWindowListViewAdpter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, getData(s));
        popupWindowListView.setAdapter(popupWindowListViewAdpter);
        popupWindowListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (s.equals("地区"))
                {
                    region.setText(popupWindowListViewAdpter.getItem(position));
                }else if (s.equals("分类"))
                {
                    classification.setText(popupWindowListViewAdpter.getItem(position));
                }else if (s.equals("应用"))
                {
                    application.setText(popupWindowListViewAdpter.getItem(position));
                }/*else if (s.equals("生产企业"))
                {
                    manufacturing_enterprise.setText(popupWindowListViewAdpter.getItem(position));
                }*/
                    listPopupWindow.dismiss();
            }
        });

    }

    private ArrayList<String> getData(String s) {
        mArrayList.add(s+"测哈哈和哈哈哈哈哈哈哈哈哈和1");
        mArrayList.add(s+"测哈哈和哈哈哈哈哈哈哈哈哈和2");
        mArrayList.add(s+"测哈哈和哈哈哈哈哈哈哈哈哈和3");
        mArrayList.add(s+"测哈哈和哈哈哈哈哈哈哈哈哈和34");
        mArrayList.add(s+"测试数据5");
        mArrayList.add(s+"测试数据6");
        mArrayList.add(s+"测试数据1");
        mArrayList.add(s+"测试数据2");
        mArrayList.add(s+"测试数据3");
        mArrayList.add("测试数据4");
        mArrayList.add("测试数据5");
        mArrayList.add("测试数据6");
        mArrayList.add("测试数据1");
        mArrayList.add("测试数据2");
        mArrayList.add("测试数据3");
        mArrayList.add("测试数据4");
        mArrayList.add("测试数据5");
        mArrayList.add("测试数据6");
        mArrayList.add("测试数据1");
        mArrayList.add("测试数据2");
        mArrayList.add("测试数据3");
        mArrayList.add("测试数据4");
        mArrayList.add("测试数据5");
        mArrayList.add("测试数据6");


        return mArrayList;
    }

    private void initdata() {

        lists = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            preSaleModel = new SMCl();
//            preSaleModel.setCompany("北京公司" + i + "分公司");
//            preSaleModel.setWarehouse(i + "仓库");
//            preSaleModel.setName("产品" + i);
//            preSaleModel.setNumber(i + "");
//            preSaleModel.setPrice(i * 100 + "");
//            preSaleModel.setProductType("预售");

            lists.add(preSaleModel);
        }
        adapter = new PreSaleAdapter(this, lists);

    }

    private void initView() {
        popwindowView = View.inflate(this, R.layout.pre_sale_search, null);
        bottom_pre_sale_search = ((LinearLayout) popwindowView.findViewById(R.id.bottom_pre_sale_search));
        bottom_pre_sale_search.setOnClickListener(this);
    }

    private void showSearchDialog() {
        popwindowView.measure(0, 0);
        int width = getWindowManager().getDefaultDisplay().getWidth();
        popupWindow = new PopupWindow(popwindowView, width, popwindowView.getMeasuredHeight());
        popupWindow.setFocusable(true);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.showAsDropDown(pre_sale_title, 0, 0);
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


        if (pre_sale_listView == parent) {
            Intent intent = new Intent();
            intent.setClass(this, PreSaleDetailActivity.class);
            startActivity(intent);
        }

    }
}

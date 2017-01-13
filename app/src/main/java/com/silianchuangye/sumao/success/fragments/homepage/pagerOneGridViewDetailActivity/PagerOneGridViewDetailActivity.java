package com.silianchuangye.sumao.success.fragments.homepage.pagerOneGridViewDetailActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.fragments.homepage.pagerOneGridViewDetailActivity.CustomPopupWindow.MultiselectPopupWindow;
import com.silianchuangye.sumao.success.fragments.homepage.pagerOneGridViewDetailActivity.CustomPopupWindow.MultiselectPopupWindowCompleteClickListener;
import com.silianchuangye.sumao.success.fragments.homepage.pagerOneGridViewDetailActivity.screenPopupWindow.ScreenMultiselectPopupWindowCompleteClickListener;
import com.silianchuangye.sumao.success.fragments.homepage.pagerOneGridViewDetailActivity.screenPopupWindow.ScreenPopupWindow;
import com.silianchuangye.sumao.success.utils.ThreeLevelLindage.Area;
import com.silianchuangye.sumao.success.utils.ThreeLevelLindage.CascadingMenuPopWindow;
import com.silianchuangye.sumao.success.utils.ThreeLevelLindage.CascadingMenuViewOnSelectListener;
import com.silianchuangye.sumao.success.utils.ThreeLevelLindage.DBHelper;
import com.silianchuangye.sumao.success.utils.ThreeLevelLindage.ThreeLevelCompletedListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jobs Created on 2016/12/15.
 * 首页点击GridView，进入列表筛选页
 */
public class PagerOneGridViewDetailActivity extends Activity implements View.OnClickListener {
    private RelativeLayout tradingPatterns;
    private RelativeLayout region;
    private RelativeLayout classification;
    private RelativeLayout application;
    private RelativeLayout grade;
    private CascadingMenuPopWindow cascadingMenuPopWindow;
    DBHelper dbHelper = new DBHelper(this);

    ArrayList<Area> provinceList = null;
    ArrayList<String> datas = new ArrayList<>();
    ArrayList<ArrayList<String>> lists = new ArrayList<>();
    private ImageView screen;
    private ArrayList<String> classification1;
    private ArrayList<String> application1;
    private ArrayList<String> gride;
    private ThreeLevelCompletedListener threeLevelCompletedListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pager_one_grid_view_detail_activity);
        provinceList = dbHelper.getProvince();

//        交易模式
        tradingPatterns = ((RelativeLayout) findViewById(R.id.supplier_patterns_rl));
        tradingPatterns.setOnClickListener(this);
//        地区
        region = ((RelativeLayout) findViewById(R.id.region_rl));
        region.setOnClickListener(this);
//        分类
        classification = ((RelativeLayout) findViewById(R.id.classification_rl));
        classification.setOnClickListener(this);
//        应用
        application = ((RelativeLayout) findViewById(R.id.application_rl));
        application.setOnClickListener(this);


//        筛选
        screen = ((ImageView) findViewById(R.id.iv_screen_title_bar_search));
        screen.setVisibility(View.VISIBLE);
        screen.setOnClickListener(this);

//
        for (Area area : provinceList) {
            datas.add(area.getName());
        }

        classification1 = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            classification1.add("分类"+i);
        }
        application1 = new ArrayList<>();
        for (int i = 0; i < 40; i++) {
            application1.add("应用"+i);
        }
        gride = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            gride.add("牌号"+i);
        }
            lists.add(datas);
            lists.add(classification1);
            lists.add(application1);
            lists.add(gride);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
//            交易模式
            case R.id.supplier_patterns_rl:
//                这里填写类型（交易模式、分类、应用、牌号）
                showTradingPatternPopupWindow("", datas);
                break;
//            地区
            case R.id.region_rl:
                showPopMenu();
                break;
//            分类
            case R.id.classification_rl:
                showTradingPatternPopupWindow("classification", classification1);
                break;
//            应用
            case R.id.application_rl:
                showTradingPatternPopupWindow("application", application1);
                break;
//            筛选
            case R.id.iv_screen_title_bar_search:
                screenPopupWindow(lists);
                break;

        }
    }

    //    （交易模式、分类、应用、牌号）PopupWindow
    private void showTradingPatternPopupWindow(String type, List<String> datas) {
        MultiselectPopupWindow multiselectPopupWindow = null;
        if (multiselectPopupWindow == null) {
            multiselectPopupWindow = new MultiselectPopupWindow(getApplicationContext(), datas, type);
            multiselectPopupWindow.setOnClickCompleteListener(new CompleteClickListener());
            multiselectPopupWindow.setFocusable(true);
            multiselectPopupWindow.showAsDropDown(tradingPatterns, 5, 5);
        } else if (multiselectPopupWindow != null && multiselectPopupWindow.isShowing()) {
            multiselectPopupWindow.dismiss();
        } else {
            multiselectPopupWindow.showAsDropDown(tradingPatterns, 5, 5);
        }
    }

//    筛选的PopupWindow
    private void screenPopupWindow(ArrayList<ArrayList<String>>  datas) {
        ScreenPopupWindow screenPopupWindow = null;
        if (screenPopupWindow == null) {
            screenPopupWindow = new ScreenPopupWindow(getApplicationContext(), datas);
            screenPopupWindow.setOnClickCompleteListener(new ScreenCompleteClickListener());
            screenPopupWindow.setFocusable(true);
            screenPopupWindow.showAtLocation(findViewById(R.id.parent), Gravity.TOP, 5, 5);
        } else if (screenPopupWindow != null && screenPopupWindow.isShowing()) {
            screenPopupWindow.dismiss();
        } else {
            screenPopupWindow.showAsDropDown(findViewById(R.id.parent), 5, 5);
        }
    }

    //      地区PopupWindow
    private void showPopMenu() {

        if (cascadingMenuPopWindow == null) {
            cascadingMenuPopWindow = new CascadingMenuPopWindow(this, provinceList);
            cascadingMenuPopWindow.setMenuViewOnSelectListener(new NMCascadingMenuViewOnSelectListener());
            cascadingMenuPopWindow.setThreeLevelCompletedListener(new ThreeLevelCompleteClickListener());
            cascadingMenuPopWindow.setFocusable(true);
            cascadingMenuPopWindow.showAsDropDown(region, 5, 5);
        } else if (cascadingMenuPopWindow != null && cascadingMenuPopWindow.isShowing()) {
            cascadingMenuPopWindow.dismiss();
        } else {
            cascadingMenuPopWindow.showAsDropDown(region, 5, 5);
        }
    }

    //点击 市 的点击事件
    class NMCascadingMenuViewOnSelectListener implements CascadingMenuViewOnSelectListener {

        @Override
        public void getValue(String provinceId,String cityId,String countyId) {
            Toast.makeText(getApplicationContext(), "省市县的id--"+provinceId+"--"+cityId+"--"+countyId, Toast.LENGTH_SHORT).show();
        }
    }

    //      点击完成后，进行网络查询(把CompleteClickListener通过 cascadingMenuPopWindow.setMenuViewOnSelectListener(new NMCascadingMenuViewOnSelectListener());传递过去，
    // 在CascadingMenuPopWindow的内部类MCascadingMenuViewOnSelectListener的getSelectionValues中使用)
    class CompleteClickListener implements MultiselectPopupWindowCompleteClickListener {

        @Override
        public void getSelectionValues(List<String> selectItems,String type) {
            Toast.makeText(PagerOneGridViewDetailActivity.this, "search" + selectItems.toString(), Toast.LENGTH_SHORT).show();
        }
    }

    class ScreenCompleteClickListener implements ScreenMultiselectPopupWindowCompleteClickListener {
        @Override
        public void getSelectionValues(List<String> regionSelectItems, List<String> classifcationSelectItems, List<String> applicationSelectItems,String grade) {

            Toast.makeText(PagerOneGridViewDetailActivity.this, "search"+"地区:"+regionSelectItems+"   分类:"+classifcationSelectItems+"   应用:"+applicationSelectItems, Toast.LENGTH_SHORT).show();
        }

    }


    //
    public class ThreeLevelCompleteClickListener implements ThreeLevelCompletedListener{

        @Override
        public void getSelectionProvinceCityCounty(String provinceId, String cityId) {
            Toast.makeText(PagerOneGridViewDetailActivity.this, "搜索地区id为：" + provinceId + "   省的id为：" + cityId, Toast.LENGTH_SHORT).show();
        }
    }

}

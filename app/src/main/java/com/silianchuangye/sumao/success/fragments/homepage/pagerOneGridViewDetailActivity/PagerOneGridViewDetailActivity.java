package com.silianchuangye.sumao.success.fragments.homepage.pagerOneGridViewDetailActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.fragments.homepage.pagerOneGridViewDetailActivity.CustomPopupWindow.MultiselectPopupWindow;
import com.silianchuangye.sumao.success.fragments.homepage.pagerOneGridViewDetailActivity.CustomPopupWindow.MultiselectPopupWindowCompleteClickListener;
import com.silianchuangye.sumao.success.fragments.homepage.pagerOneGridViewDetailActivity.screenPopupWindow.ScreenPopupWindow;
import com.silianchuangye.sumao.success.utils.ThreeLevelLindage.Area;
import com.silianchuangye.sumao.success.utils.ThreeLevelLindage.CascadingMenuPopWindow;
import com.silianchuangye.sumao.success.utils.ThreeLevelLindage.CascadingMenuViewOnSelectListener;
import com.silianchuangye.sumao.success.utils.ThreeLevelLindage.DBHelper;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pager_one_grid_view_detail_activity);
        provinceList = dbHelper.getProvince();

//        交易模式
        tradingPatterns = ((RelativeLayout) findViewById(R.id.trading_patterns_rl));
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
//        牌号
        grade = ((RelativeLayout) findViewById(R.id.grade_rl));
        grade.setOnClickListener(this);

//        筛选
        screen = ((ImageView) findViewById(R.id.iv_screen_title_bar_search));
        screen.setVisibility(View.VISIBLE);
        screen.setOnClickListener(this);

//
        for (Area area : provinceList) {
            datas.add(area.getName());
        }
        for (int i = 0; i < 4; i++) {
            lists.add(datas);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
//            交易模式
            case R.id.trading_patterns_rl:
//                这里填写类型（交易模式、分类、应用、牌号）
                showTradingPatternPopupWindow("", datas);
                break;
//            地区
            case R.id.region_rl:
                showPopMenu();
                break;
//            分类
            case R.id.classification_rl:

                break;
//            应用
            case R.id.application_rl:

                break;
//            牌号
            case R.id.grade_rl:

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
            screenPopupWindow.setOnClickCompleteListener(new CompleteClickListener());
            screenPopupWindow.setFocusable(true);
            screenPopupWindow.showAsDropDown(findViewById(R.id.pre_sale_title), 5, 5);
        } else if (screenPopupWindow != null && screenPopupWindow.isShowing()) {
            screenPopupWindow.dismiss();
        } else {
            screenPopupWindow.showAsDropDown(findViewById(R.id.pre_sale_title), 5, 5);
        }
    }

    //      地区PopupWindow
    private void showPopMenu() {

        if (cascadingMenuPopWindow == null) {
            cascadingMenuPopWindow = new CascadingMenuPopWindow(getApplicationContext(), provinceList);
            cascadingMenuPopWindow.setMenuViewOnSelectListener(new NMCascadingMenuViewOnSelectListener());
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
        public void getValue(Area area) {
            Toast.makeText(getApplicationContext(), "" + area.getName(), Toast.LENGTH_SHORT).show();
        }
    }

    //      点击完成后，进行网络查询(把CompleteClickListener通过 cascadingMenuPopWindow.setMenuViewOnSelectListener(new NMCascadingMenuViewOnSelectListener());传递过去，
    // 在CascadingMenuPopWindow的内部类MCascadingMenuViewOnSelectListener的getSelectionValues中使用)
    class CompleteClickListener implements MultiselectPopupWindowCompleteClickListener {

        @Override
        public void getSelectionValues(List<String> selectItems) {
            Toast.makeText(PagerOneGridViewDetailActivity.this, "search" + selectItems.toString(), Toast.LENGTH_SHORT).show();
        }
    }
}

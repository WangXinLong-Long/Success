package com.silianchuangye.sumao.success.fragments.pagerTwoMVP;

import android.app.DatePickerDialog;
import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.adapter.MyPageAdapter;
import com.silianchuangye.sumao.success.fragments.BasePager;
import com.silianchuangye.sumao.success.fragments.pagerTwoMVP.pagerTwoChilds.AnalysisFragmentMVP.view.AnalysisFragment;
import com.silianchuangye.sumao.success.fragments.pagerTwoMVP.pagerTwoChilds.DataFragmentMVP.view.DataFragment;
import com.silianchuangye.sumao.success.fragments.pagerTwoMVP.pagerTwoChilds.InformationPageFragmentMVP.view.InformationPageFragment;
import com.silianchuangye.sumao.success.fragments.pagerTwoMVP.pagerTwoChilds.NewsFragmentMVP.view.NewsFragment;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by Administrator on 2016/4/20 0020.
 */
public class PagerTwo extends BasePager {
    private RelativeLayout view;
    private Context context;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ImageView calendarImageView;
    private DataFragment dataFragment;
    private AnalysisFragment analysisFragment;
    private NewsFragment newsFragment;

    @Override
    public void initDate() {
//        设置父类的标题不可见
        rl_title.setVisibility(View.GONE);
//        将PagerTwo的视图添加到父容器中
        view = (RelativeLayout) View.inflate(mActivity, R.layout.fragmenttwo, null);
        fl_content.addView(view);
//        将父类的Activity转化为Context
        context = mActivity;
//
        calendarImageView = ((ImageView) view.findViewById(R.id.calendar_view));
//        初始化四个Fragment
        InformationPageFragment informationPageFragment = new InformationPageFragment();
        dataFragment = new DataFragment();
        analysisFragment = new AnalysisFragment();
        newsFragment = new NewsFragment();
//        找到控件tab_layout、view_pager
        tabLayout = ((TabLayout) view.findViewById(R.id.tab_layout));
        viewPager = ((ViewPager) view.findViewById(R.id.view_pager));
//         初始化ViewPagerAdapter
        final MyPageAdapter viewPagerAdapter = new MyPageAdapter(getChildFragmentManager());
//          将ViewPagerAdapter中需要的数据传递进去
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(informationPageFragment);
        fragments.add(dataFragment);
        fragments.add(analysisFragment);
        fragments.add(newsFragment);
        ArrayList<String> titles = new ArrayList<>();
        titles.add("资讯首页");
        titles.add("数据");
        titles.add("分析");
        titles.add("新闻");
        viewPagerAdapter.setData(fragments);
        viewPagerAdapter.setTitles(titles);
//          为ViewPager设置Adapter
        viewPager.setAdapter(viewPagerAdapter);
//          设置ViewPager和tabLayout相关联
        tabLayout.setupWithViewPager(viewPager);
//        设置关联模式
        tabLayout.setTabMode(tabLayout.MODE_FIXED);
//          显示或隐藏日历
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tabLayout.getSelectedTabPosition() == 0) {
                    view.findViewById(R.id.calendar_view).setVisibility(View.INVISIBLE);
                    viewPager.setCurrentItem(tab.getPosition());
                } else {
                    view.findViewById(R.id.calendar_view).setVisibility(View.VISIBLE);
                    viewPager.setCurrentItem(tab.getPosition());
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        calendarImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "当前的位置为：" + viewPager.getCurrentItem(), Toast.LENGTH_SHORT).show();
                Calendar c = Calendar.getInstance();
                // 直接创建一个DatePickerDialog对话框实例，并将它显示出来
                new DatePickerDialog(getActivity(),
                        // 绑定监听器
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                monthOfYear++;
                                if (viewPager.getCurrentItem() == 1) {
                                    dataFragment.searchData(year,monthOfYear,dayOfMonth);
                                }else if (viewPager.getCurrentItem() == 2){
                                    analysisFragment.searchData(year,monthOfYear,dayOfMonth);
                                }else if(viewPager.getCurrentItem() == 3){
                                    newsFragment.searchData(year,monthOfYear,dayOfMonth);
                                }
                            }
                        }
                        // 设置初始日期
                        , c.get(Calendar.YEAR), c.get(Calendar.MONTH), c
                        .get(Calendar.DAY_OF_MONTH)).show();
            }
        });
    }


    @Override
    public void myClickSearch() {
    }

    @Override
    public void myClickLeft() {
    }

    @Override
    public void myClickRight() {
    }


}

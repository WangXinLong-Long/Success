package com.silianchuangye.sumao.success.fragments.homepage.pagerOneGridViewDetailActivity.screenPopupWindow;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.fragments.homepage.pagerOneGridViewDetailActivity.CustomPopupWindow.MultiselectPopupWindowCompleteClickListener;
import com.silianchuangye.sumao.success.fragments.homepage.pagerOneGridViewDetailActivity.CustomPopupWindow.MultiselectPopupWindowGrideViewAdpter;

import java.util.ArrayList;

/**
 * Created by Jobs Created on 2016/12/16.
 * 筛选PopupWindow的视图
 */
public class ScreenPopupWindowView extends ScrollView implements View.OnClickListener {
    private Context context;
    //    为GrideView填充数据
    private ArrayList<ArrayList<String>> data;
    //    设置适配器
    private MultiselectPopupWindowGrideViewAdpter adpter;
    //    （交易模式、分类、应用、牌号中的一种）
    private String type;

    private MultiselectPopupWindowCompleteClickListener multiselectPopupWindowCompleteClickListener;
    private MultiselectPopupWindowGrideViewAdpter regionAdpter;
    private MultiselectPopupWindowGrideViewAdpter classifcationAdpter;
    private MultiselectPopupWindowGrideViewAdpter applicationAdpter;
    private MultiselectPopupWindowGrideViewAdpter gradeAdpter;

    public ScreenPopupWindowView(Context context, ArrayList<ArrayList<String>> data) {
        super(context);
        this.context = context;
        this.data = data;
        init(this.context);
    }

    private void init(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.screen_multi_select_popupwindow_view, this, true);
//        setBackgroundColor(getResources().getColor(R.color.white));//设置背景为白色
        GridView regionGrid = (GridView) findViewById(R.id.region_grid);//地区
        GridView classificationGrid = (GridView) findViewById(R.id.classification_grid);//分类
        GridView applicationGrid = (GridView) findViewById(R.id.application_grid);//应用
        GridView gradeGrid = (GridView) findViewById(R.id.grade_grid);//牌号

        Button completed = (Button) findViewById(R.id.completed);//完成按钮
        Button reset = (Button) findViewById(R.id.reset);//重置按钮
        completed.setOnClickListener(this);
        reset.setOnClickListener(this);

        TextView regionMoreTv = (TextView) findViewById(R.id.region_more_tv);
        TextView classificationMoreTv = (TextView) findViewById(R.id.classification_more_tv);
        TextView applicationMoreTv = (TextView) findViewById(R.id.application_more_tv);
        TextView gradeMoreTv = (TextView) findViewById(R.id.grade_more_tv);
        regionMoreTv.setOnClickListener(this);
        classificationMoreTv.setOnClickListener(this);
        applicationMoreTv.setOnClickListener(this);
        gradeMoreTv.setOnClickListener(this);

//        地区
        regionAdpter = new MultiselectPopupWindowGrideViewAdpter( context);
        regionAdpter.setData(getTenItem(data.get(0)));
        regionGrid.setAdapter(regionAdpter);
        regionGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1,
                                    int position, long arg3) {
                regionAdpter.chiceState(position);
            }
        });
//        分类
        classifcationAdpter = new MultiselectPopupWindowGrideViewAdpter( context);
        classifcationAdpter.setData(getTenItem(data.get(1)));
        classificationGrid.setAdapter(classifcationAdpter);
        classificationGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1,
                                    int position, long arg3) {
                classifcationAdpter.chiceState(position);
            }
        });
//        应用
        applicationAdpter = new MultiselectPopupWindowGrideViewAdpter( context);
        applicationAdpter.setData(getTenItem(data.get(2)));
        applicationGrid.setAdapter(applicationAdpter);
        applicationGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1,
                                    int position, long arg3) {
                applicationAdpter.chiceState(position);
            }
        });
//        牌号
        gradeAdpter = new MultiselectPopupWindowGrideViewAdpter( context);
        gradeAdpter.setData(getTenItem(data.get(3)));
        gradeGrid.setAdapter(gradeAdpter);
        gradeGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1,
                                    int position, long arg3) {
                gradeAdpter.chiceState(position);
            }
        });

    }

    public ArrayList<String> getTenItem(ArrayList<String> data) {
        ArrayList<String> list = new ArrayList<>();
        if (data.size() > 10) {
            list.clear();
            for (int i = 0; i < 10; i++) {
                list.add(data.get(i));
            }

        }
        return list;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.completed:
                completedMethod(type);
                break;
            case R.id.reset:
                resetMethod();
                break;
            case R.id.region_more_tv:
                regionAdpter.clearData();
                regionAdpter.setData(data.get(0));
                regionAdpter.notifyDataSetChanged();
                break;

        }
    }

    private void resetMethod() {
        adpter.clear();
        adpter.notifyDataSetChanged();
    }

    private void completedMethod(String type) {
        StringBuilder sb = new StringBuilder();
        ArrayList<String> selects = new ArrayList<>();
        Log.i("test", adpter.getCheseState().get(1) + "");
        for (int i = 0; i < adpter.getCheseState().size(); i++) {
            boolean flag = adpter.getCheseState().get(i);
            if (flag) {
//                sb.append(data.get(i) + "");
                selects.add(data.get(i).get(i));
            }
        }
        if (multiselectPopupWindowCompleteClickListener != null) {
            multiselectPopupWindowCompleteClickListener.getSelectionValues(selects);
        }

    }

    public void setMultiselectPopupWindowCompleteClickListener(MultiselectPopupWindowCompleteClickListener listener) {
        this.multiselectPopupWindowCompleteClickListener = listener;
    }
}

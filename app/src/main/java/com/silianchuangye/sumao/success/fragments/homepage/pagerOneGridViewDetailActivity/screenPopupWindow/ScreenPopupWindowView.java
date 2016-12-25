package com.silianchuangye.sumao.success.fragments.homepage.pagerOneGridViewDetailActivity.screenPopupWindow;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.fragments.homepage.pagerOneGridViewDetailActivity.CustomPopupWindow.MultiselectPopupWindowGrideViewAdpter;

import java.util.ArrayList;

/**
 * Created by Jobs Created on 2016/12/16.
 * 筛选PopupWindow的视图
 */
public class ScreenPopupWindowView extends RelativeLayout implements View.OnClickListener {
    private Context context;
    //    为GrideView填充数据
    private ArrayList<ArrayList<String>> data;

    private ScreenMultiselectPopupWindowCompleteClickListener multiselectPopupWindowCompleteClickListener;
    private MultiselectPopupWindowGrideViewAdpter regionAdpter;
    private MultiselectPopupWindowGrideViewAdpter classifcationAdpter;
    private MultiselectPopupWindowGrideViewAdpter applicationAdpter;
    private MultiselectPopupWindowGrideViewAdpter gradeAdpter;
    private EditText tv_screen_title_bar_title;

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
        tv_screen_title_bar_title = (EditText) findViewById(R.id.tv_screen_title_bar_title);
        Button completed = (Button) findViewById(R.id.completed);//完成按钮
        Button reset = (Button) findViewById(R.id.reset);//重置按钮
        completed.setOnClickListener(this);
        reset.setOnClickListener(this);

        TextView regionMoreTv = (TextView) findViewById(R.id.region_more_tv);
        TextView classificationMoreTv = (TextView) findViewById(R.id.classification_more_tv);
        TextView applicationMoreTv = (TextView) findViewById(R.id.application_more_tv);
        regionMoreTv.setOnClickListener(this);
        classificationMoreTv.setOnClickListener(this);
        applicationMoreTv.setOnClickListener(this);

//        地区
        regionAdpter = new MultiselectPopupWindowGrideViewAdpter(context);
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
        classifcationAdpter = new MultiselectPopupWindowGrideViewAdpter(context);
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
        applicationAdpter = new MultiselectPopupWindowGrideViewAdpter(context);
        applicationAdpter.setData(getTenItem(data.get(2)));
        applicationGrid.setAdapter(applicationAdpter);
        applicationGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1,
                                    int position, long arg3) {
                applicationAdpter.chiceState(position);
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

        }else {
            list.addAll(data);
        }
        return list;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.completed:
                completedMethod();
                break;
            case R.id.reset:
                resetMethod();
                break;
            case R.id.region_more_tv:
                regionAdpter.clearData();
                regionAdpter.setData(data.get(0));
                regionAdpter.notifyDataSetChanged();
                break;
            case R.id.classification_more_tv:
                classifcationAdpter.clearData();
                classifcationAdpter.setData(data.get(1));
                classifcationAdpter.notifyDataSetChanged();
                break;
            case R.id.application_more_tv:
                applicationAdpter.clearData();
                applicationAdpter.setData(data.get(2));
                applicationAdpter.notifyDataSetChanged();
                break;

        }
    }

    private void resetMethod() {
        regionAdpter.clear();
        regionAdpter.notifyDataSetChanged();

        classifcationAdpter.clear();
        classifcationAdpter.notifyDataSetChanged();

        applicationAdpter.clear();
        applicationAdpter.notifyDataSetChanged();
    }

    private void completedMethod() {
//        地区选择的内容
        ArrayList<String> regionSelects = new ArrayList<>();
        for (int i = 0; i < regionAdpter.getCheseState().size(); i++) {
            boolean flag = regionAdpter.getCheseState().get(i);
            if (flag) {
                regionSelects.add(data.get(0).get(i));
            }
        }

        ArrayList<String> classificationSelects = new ArrayList<>();
        for (int i = 0; i < classifcationAdpter.getCheseState().size(); i++) {
            boolean flag = classifcationAdpter.getCheseState().get(i);
            if (flag) {
                classificationSelects.add(data.get(1).get(i));
            }
        }

        ArrayList<String> applicationSelects = new ArrayList<>();
        for (int i = 0; i < applicationAdpter.getCheseState().size(); i++) {
            boolean flag = applicationAdpter.getCheseState().get(i);
            if (flag) {
                applicationSelects.add(data.get(2).get(i));
            }
        }


        if (multiselectPopupWindowCompleteClickListener != null) {
            multiselectPopupWindowCompleteClickListener.getSelectionValues(regionSelects, classificationSelects, applicationSelects,tv_screen_title_bar_title.getText().toString());
        }

    }

    public void setMultiselectPopupWindowCompleteClickListener(ScreenMultiselectPopupWindowCompleteClickListener listener) {
        this.multiselectPopupWindowCompleteClickListener = listener;
    }
}

package com.silianchuangye.sumao.success.fragments.homepage.pagerOneGridViewDetailActivity.CustomPopupWindow;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.RelativeLayout;

import com.silianchuangye.sumao.success.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jobs Created on 2016/12/16.
 * 筛选PopupWindow的视图
 */
public class MultiselectPopupWindowView extends RelativeLayout implements View.OnClickListener {
    private Context context;
    //    为GrideView填充数据
    private List<String> data;
//    设置适配器
    private MultiselectPopupWindowGrideViewAdpter adpter;
//    （交易模式、分类、应用、牌号中的一种）
    private String type;

    private MultiselectPopupWindowCompleteClickListener multiselectPopupWindowCompleteClickListener;
    public MultiselectPopupWindowView(Context context, List<String> data, String type) {
        super(context);
        this.context = context;
        this.data = data;
        this.type = type;
        init(this.context);
    }

    private void init(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.multi_select_popupwindow_view, this, true);
//        setBackgroundColor(getResources().getColor(R.color.white));//设置背景为白色
        GridView gridView = (GridView) findViewById(R.id.grid);//
        Button completed = (Button) findViewById(R.id.completed);//完成按钮
        Button reset = (Button) findViewById(R.id.reset);//重置按钮
        completed.setOnClickListener(this);
        reset.setOnClickListener(this);

        adpter = new MultiselectPopupWindowGrideViewAdpter(context);
        adpter.setData(data);
        gridView.setAdapter(adpter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1,
                                    int position, long arg3) {
                adpter.chiceState(position);
            }
        });
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

        }
    }
//  清空所选项目
    private void resetMethod() {
        adpter.clear();
        adpter.notifyDataSetChanged();
    }
//  重置
    private void completedMethod(String type) {
        StringBuilder sb = new StringBuilder();
        ArrayList<String> selects = new ArrayList<>();
        Log.i("test", adpter.getCheseState().get(1) + "");
        for (int i = 0; i < adpter.getCheseState().size(); i++) {
            boolean flag = adpter.getCheseState().get(i);
            if (flag) {
                selects.add(data.get(i));
            }
        }
        if (multiselectPopupWindowCompleteClickListener!=null){
            multiselectPopupWindowCompleteClickListener.getSelectionValues(selects);
        }

    }

    public void setMultiselectPopupWindowCompleteClickListener(MultiselectPopupWindowCompleteClickListener listener){
        this.multiselectPopupWindowCompleteClickListener = listener;
    }
}

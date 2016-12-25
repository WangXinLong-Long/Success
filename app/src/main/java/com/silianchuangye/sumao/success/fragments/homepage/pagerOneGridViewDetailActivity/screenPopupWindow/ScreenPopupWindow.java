package com.silianchuangye.sumao.success.fragments.homepage.pagerOneGridViewDetailActivity.screenPopupWindow;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.PopupWindow;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jobs Created on 2016/12/16.
 * 列表页筛选的PopupWindow
 */
public class ScreenPopupWindow extends PopupWindow {

    private Context context;
    private ArrayList<ArrayList<String>> data;
    ScreenMultiselectPopupWindowCompleteClickListener completeclicklistener;
    public ScreenPopupWindow(Context context, ArrayList<ArrayList<String>> data) {
        super(context);
        this.context = context;
        this.data = data;
        init();
    }

    private void init() {
        ScreenPopupWindowView screenPopupWindowView = new ScreenPopupWindowView(this.context, this.data);
        setContentView(screenPopupWindowView);
        setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        screenPopupWindowView.setMultiselectPopupWindowCompleteClickListener(new CompleteClickListener());
    }

    public void setOnClickCompleteListener(ScreenMultiselectPopupWindowCompleteClickListener completeClickListener) {
        this.completeclicklistener = completeClickListener;
    }

    class CompleteClickListener implements ScreenMultiselectPopupWindowCompleteClickListener{
        @Override
        public void getSelectionValues(List<String> regionSelectItems, List<String> classifcationSelectItems, List<String> applicationSelectItems,String grade) {

            if (completeclicklistener!=null){
                completeclicklistener.getSelectionValues(regionSelectItems,classifcationSelectItems,applicationSelectItems, grade);
            }
            dismiss();

        }
    }
}

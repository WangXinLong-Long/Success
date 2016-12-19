package com.silianchuangye.sumao.success.fragments.homepage.pagerOneGridViewDetailActivity.screenPopupWindow;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.PopupWindow;

import com.silianchuangye.sumao.success.fragments.homepage.pagerOneGridViewDetailActivity.CustomPopupWindow.MultiselectPopupWindowCompleteClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jobs Created on 2016/12/16.
 * 列表页筛选的PopupWindow
 */
public class ScreenPopupWindow extends PopupWindow {

    private Context context;
    private ArrayList<ArrayList<String>> data;
        MultiselectPopupWindowCompleteClickListener completeclicklistener;
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

    public void setOnClickCompleteListener(MultiselectPopupWindowCompleteClickListener completeClickListener) {
        this.completeclicklistener = completeClickListener;
    }

    class CompleteClickListener implements MultiselectPopupWindowCompleteClickListener{

        @Override
        public void getSelectionValues(List<String> selectItems) {
            if (completeclicklistener!=null){
                completeclicklistener.getSelectionValues(selectItems);
            }

            dismiss();
        }
    }
}

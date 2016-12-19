package com.silianchuangye.sumao.success.fragments.homepage.pagerOneGridViewDetailActivity.CustomPopupWindow;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.PopupWindow;

import java.util.List;

/**
 * Created by Jobs Created on 2016/12/16.
 * 列表页的PopupWindow
 */
public class MultiselectPopupWindow extends PopupWindow {

    private Context context;
    private List<String> data;
    private String type;
        MultiselectPopupWindowCompleteClickListener completeclicklistener;
    public MultiselectPopupWindow(Context context, List<String> data, String type) {
        super(context);
        this.context = context;
        this.data = data;
        this.type = type;
        init();
    }

    private void init() {
        MultiselectPopupWindowView multiselectPopupWindowView = new MultiselectPopupWindowView(this.context, this.data, this.type);
        setContentView(multiselectPopupWindowView);
        setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        multiselectPopupWindowView.setMultiselectPopupWindowCompleteClickListener(new CompleteClickListener());
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

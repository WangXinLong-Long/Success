package com.silianchuangye.sumao.success.fragments.PagerOneMVP.model.grideViewModel;

/**
 * Created by Jobs Created on 2016/11/30.
 */
public class GrideViewModel implements IGrideViewModel {

    @Override
    public void getGrideViewData(GrideViewCallback callback) {
callback.callbackGrideViewData();
    }
}

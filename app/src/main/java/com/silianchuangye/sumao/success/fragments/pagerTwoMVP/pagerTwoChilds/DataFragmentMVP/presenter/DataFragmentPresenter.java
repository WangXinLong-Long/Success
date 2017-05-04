package com.silianchuangye.sumao.success.fragments.pagerTwoMVP.pagerTwoChilds.DataFragmentMVP.presenter;

import com.silianchuangye.sumao.success.fragments.pagerTwoMVP.pagerTwoChilds.DataFragmentMVP.model.DataFragmentCallback;
import com.silianchuangye.sumao.success.fragments.pagerTwoMVP.pagerTwoChilds.DataFragmentMVP.model.DataFragmentModel;
import com.silianchuangye.sumao.success.fragments.pagerTwoMVP.pagerTwoChilds.DataFragmentMVP.model.IDataFragmentModel;
import com.silianchuangye.sumao.success.fragments.pagerTwoMVP.pagerTwoChilds.DataFragmentMVP.view.IDataFragmentView;

/**
 * Created by Jobs Created on 2016/12/27.
 */
public class DataFragmentPresenter {
    IDataFragmentView dataFragmentView ;

    public DataFragmentPresenter(IDataFragmentView dataFragmentView) {
        this.dataFragmentView = dataFragmentView;
    }

//    获取数据页的列表内容
    public void getDataFragmentResult(String dateType,String SearchDate){
        IDataFragmentModel dataFragmentModel = new DataFragmentModel(dateType,SearchDate);
        dataFragmentModel.getDataFragmentResult(new DataFragmentCallback() {
            @Override
            public void callbackDataFragmentResult(String result) {
                dataFragmentView.setDataInDataFragmentView(result);
            }
        });
    }
}

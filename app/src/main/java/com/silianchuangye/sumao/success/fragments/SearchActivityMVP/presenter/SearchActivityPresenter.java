package com.silianchuangye.sumao.success.fragments.SearchActivityMVP.presenter;

import com.silianchuangye.sumao.success.fragments.SearchActivityMVP.model.ISearchActivityModel;
import com.silianchuangye.sumao.success.fragments.SearchActivityMVP.model.SearchActivityCallback;
import com.silianchuangye.sumao.success.fragments.SearchActivityMVP.model.SearchActivityModel;
import com.silianchuangye.sumao.success.fragments.SearchActivityMVP.view.ISearchActivityView;

/**
 * Created by Administrator on 2016/8/15 0015.
 */
public class SearchActivityPresenter {
    ISearchActivityView searchActivityView ;

    public SearchActivityPresenter(ISearchActivityView searchActivityView) {
        this.searchActivityView = searchActivityView;
    }
    public void sendSearchActivityData(String Ntt){
        ISearchActivityModel searchActivityModel = new SearchActivityModel(Ntt);
        searchActivityModel.getSearchActivityInfo(new SearchActivityCallback() {
            @Override
            public void callbackSearchActivity() {

            }
        });
    }
}

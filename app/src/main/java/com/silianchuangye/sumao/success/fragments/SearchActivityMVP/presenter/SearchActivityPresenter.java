package com.silianchuangye.sumao.success.fragments.SearchActivityMVP.presenter;

import android.widget.Toast;

import com.silianchuangye.sumao.success.fragments.SearchActivityMVP.bean.SearchActivityBean;
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
        ISearchActivityModel searchActivityModel = new SearchActivityModel(Ntt,10,0);
        searchActivityModel.getSearchActivityInfo(new SearchActivityCallback() {
            @Override
            public void callbackSearchActivity(SearchActivityBean searchActivityBean) {
                searchActivityView.getSearchActivityData(searchActivityBean);

            }
        });
    }
}

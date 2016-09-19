package com.silianchuangye.sumao.success.ShangYou.SeePlanMVP.presenter;

import com.silianchuangye.sumao.success.ShangYou.CaiGouMVP.bean.CaiGouBean;
import com.silianchuangye.sumao.success.ShangYou.CaiGouMVP.model.CaiGouCallback;
import com.silianchuangye.sumao.success.ShangYou.CaiGouMVP.model.CaiGouModel;
import com.silianchuangye.sumao.success.ShangYou.CaiGouMVP.model.ICaiGouModel;
import com.silianchuangye.sumao.success.ShangYou.CaiGouMVP.view.ICaiGouView;
import com.silianchuangye.sumao.success.ShangYou.SeePlanMVP.view.ISeePlanView;

/**
 * Created by Administrator on 2016/9/18 0018.
 */
public class SeePlanPresenter {
    ISeePlanView seePlanView;

    public SeePlanPresenter(ISeePlanView seePlanView) {
        this.seePlanView = seePlanView;
    }
    //页数、每页条数、上游资源方ID、sessionID、商品名字、开始日、结束日、提报状态、仓库名字、物流ID
    public void requestSeePlanDate(String pageNum,String pageSize,String sellerEnterpriseId,
                                   String _dynSessConf, String productName,String planStartDate,
                                   String planEndDate,String planState,String warehouseName,
                                   String logisticsID){
        ICaiGouModel caiGouModel = new CaiGouModel( pageNum, pageSize, sellerEnterpriseId,_dynSessConf,  productName, planStartDate,  planEndDate, planState, warehouseName,logisticsID);
        caiGouModel.getSeePlanInfo(new CaiGouCallback() {
            @Override
            public void callbackCaiGou(CaiGouBean caiGouBean,String pageNum,String planStartDate,
                                       String planEndDate, String planState, String warehouseName,
                                       String logisticsID) {
                seePlanView.getSeePlanInfoInFragment( caiGouBean,pageNum,  planStartDate,
                         planEndDate,  planState,  warehouseName,
                         logisticsID);
            }
        });

    }
}

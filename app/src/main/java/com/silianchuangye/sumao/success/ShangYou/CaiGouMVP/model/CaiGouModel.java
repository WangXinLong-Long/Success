package com.silianchuangye.sumao.success.ShangYou.CaiGouMVP.model;

import com.google.gson.Gson;
import com.silianchuangye.sumao.success.ShangYou.CaiGouMVP.bean.CaiGouBean;
import com.silianchuangye.sumao.success.ShangYou.PlanDayMVP.Bean.PlanDayBean;
import com.silianchuangye.sumao.success.utils.LogUtils;
import com.silianchuangye.sumao.success.utils.SuMaoConstant;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.HttpMethod;
import org.xutils.http.RequestParams;
import org.xutils.x;

/**
 * Created by Administrator on 2016/9/14 0014.
 */
public class CaiGouModel implements ICaiGouModel {

    String pageNum; //页数
    String pageSize;//每页条数
    String sellerEnterpriseId;//上游资源方ID
    String _dynSessConf; //sessionID
    String productName;//商品名字
    String planStartDate;//开始日
    String planEndDate;//结束日
    String planState;//提报状态
    String warehouseName;//仓库名字
    String logisticsID;//物流ID
    String url = SuMaoConstant.SUMAO_IP+"/rest/model/com/sumao/mobile/order/purchase/PlanOrderActor/vipPlanOrderSearch";
    public CaiGouModel(String pageNum, String pageSize, String sellerEnterpriseId,
                       String _dynSessConf, String productName, String planStartDate,
                       String planEndDate, String planState, String warehouseName,
                       String logisticsID) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.sellerEnterpriseId = sellerEnterpriseId;
        this._dynSessConf = _dynSessConf;
        this.productName = productName;
        this.planStartDate = planStartDate;
        this.planEndDate = planEndDate;
        this.planState = planState;
        this.warehouseName = warehouseName;
        this.logisticsID = logisticsID;
    }

    @Override
    public void getSeePlanInfo(final CaiGouCallback callback) {
        RequestParams rp = new RequestParams(url);
        rp.setAsJsonContent(true);
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("pageNum",pageNum);
            jsonObject.put("pageSize",pageSize);
            jsonObject.put("sellerEnterpriseId",sellerEnterpriseId);
            jsonObject.put("_dynSessConf",_dynSessConf);
            jsonObject.put("productName",productName);
            jsonObject.put("planStartDate",planStartDate);
            jsonObject.put("planEndDate",planEndDate);
            jsonObject.put("planState",planState);
            jsonObject.put("warehouseName",warehouseName);
            jsonObject.put("logisticsID",logisticsID);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        rp.setBodyContent(jsonObject.toString());
        LogUtils.log("URL---->"+rp);
        LogUtils.log("URL---->"+jsonObject.toString());
        x.http().request(HttpMethod.POST, rp, new Callback.CommonCallback<String>() {
            private CaiGouBean caiGouBean;

            @Override
            public void onSuccess(String result) {
                LogUtils.log("上游直销查看计划-->"+result);
                Gson gson = new Gson();
                caiGouBean = gson.fromJson(result,CaiGouBean.class);
                callback.callbackCaiGou( caiGouBean,pageNum, planStartDate,
                         planEndDate,  planState,  warehouseName,
                         logisticsID);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
//
    }
}

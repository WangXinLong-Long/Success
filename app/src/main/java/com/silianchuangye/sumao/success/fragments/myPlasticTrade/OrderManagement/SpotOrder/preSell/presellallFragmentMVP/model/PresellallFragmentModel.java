package com.silianchuangye.sumao.success.fragments.myPlasticTrade.OrderManagement.SpotOrder.preSell.presellallFragmentMVP.model;

import android.util.Log;

import com.silianchuangye.sumao.success.utils.LogUtils;
import com.silianchuangye.sumao.success.utils.SuMaoConstant;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

/**
 * Created by Jobs Created on 2016/12/28.
 */
public class PresellallFragmentModel implements IPresellallFragmentModel {
    private String searchTrateType;
    private String searchCompanyName;
    private String searchOrderType;
    private String searchOrderState;
    private String pageNum;
    private String searchOrderId;
    private String startDate;
    private String endDate;
    private String submitType;
    private String searchCheckType;
    private String _dynSessConf;
    private boolean update;

    public PresellallFragmentModel(String searchTrateType,String searchCompanyName, String searchOrderType,
                                   String searchOrderState, String pageNum, String searchOrderId,
                                   String startDate, String endDate, String submitType,
                                   String searchCheckType, String _dynSessConf, boolean update) {
        this.searchTrateType = searchTrateType;
        this.searchCompanyName = searchCompanyName;
        this.searchOrderType = searchOrderType;
        this.searchOrderState = searchOrderState;
        this.pageNum = pageNum;
        this.searchOrderId = searchOrderId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.submitType = submitType;
        this.searchCheckType = searchCheckType;
        this.update = update;
        this._dynSessConf=_dynSessConf;
    }

    @Override
    public void getPresellallFragmentResult(final PresellallFragmentCallback callback) {
        RequestParams params = new RequestParams(SuMaoConstant.SUMAO_IP + "/rest/model/atg/userprofiling/ProfileActor/myOrders");
        params.setCharset("UTF-8");
        params.setAsJsonContent(true);
        if (searchCompanyName != null&&!searchCompanyName.equals("")) {
            JSONObject job = new JSONObject();
            try {
                job.put("searchCompanyName", searchCompanyName);//公司
            } catch (JSONException e) {
                e.printStackTrace();
            }
            params.setBodyContent(job.toString());
        }
        if (searchOrderType != null&&!searchOrderType.equals("")) {
            params.addParameter("searchOrderType", searchOrderType);
        }
        if (searchOrderState != null&&!searchOrderState.equals("")) {
            params.addParameter("searchOrderState", searchOrderState);
        }
        if (pageNum != null&&!pageNum.equals("")) {
            params.addParameter("pageNum", pageNum);
        }
        if (searchOrderId != null&&!searchOrderId.equals("")) {
            params.addParameter("searchOrderId", searchOrderId);//订单
        }

        if (startDate != null&&!startDate.equals("")) {
            params.addParameter("startDate", startDate);//开始日期
        }

        if (endDate != null&&!endDate.equals("")) {
            params.addParameter("endDate", endDate);//结束日期
        }
        if (submitType != null&&!submitType.equals("")) {
            params.addParameter("submitType", submitType);//查询类型
        }
        if (searchCheckType != null&&!searchCheckType.equals("")) {
            params.addParameter("searchCheckType", searchCheckType);//开票状态
        }
        if (searchTrateType != null&&!searchTrateType.equals("")) {
            params.addParameter("searchTrateType", searchTrateType);
        }
        params.addParameter("_dynSessConf",_dynSessConf);


        LogUtils.log("获取预售订单列表URL--->" + params);
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                LogUtils.log("获取预售订单列表--->" + result);
                callback.callbackPresellallFragmentResult(result, update);

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.e("TAG", "ex===" + ex);
                Log.e("TAG", "失败呢");
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {
            }
        });
    }
}

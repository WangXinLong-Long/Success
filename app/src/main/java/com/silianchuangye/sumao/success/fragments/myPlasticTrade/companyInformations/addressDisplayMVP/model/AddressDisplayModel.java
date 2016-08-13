package com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.addressDisplayMVP.model;

import com.google.gson.Gson;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.addressDisplayMVP.bean.AddressDisplay;
import com.silianchuangye.sumao.success.utils.LogUtils;
import com.silianchuangye.sumao.success.utils.SuMaoConstant;

import org.xutils.common.Callback;
import org.xutils.http.HttpMethod;
import org.xutils.http.RequestParams;
import org.xutils.x;

/**
 * Created by Administrator on 2016/7/28 0028.
 */
public class AddressDisplayModel implements IaddressDisplayModel {
    String province;
    String city;
    String county;
    int position;
    Gson gson = new Gson();
    AddressDisplay addressDisplay;
    public AddressDisplayModel(String province, String city, String county,int position) {
        this.province = province;
        this.city = city;
        this.county = county;
        this.position = position;
    }

    @Override
    public void getAddressDisplayInfo(final IaddressDisplayCallback callback) {

        String url = SuMaoConstant.SUMAO_IP+"/rest/model/atg/userprofiling/ProfileActor/addressDisplay"+"?prov="+province+"&city="+city+"&county="+county;
        final RequestParams requestParams = new RequestParams(url);


                try {
                    x.http().request(HttpMethod.POST, requestParams, new Callback.CacheCallback<String>() {
                        private String address;
                        @Override
                        public boolean onCache(String result) {
                            return false;
                        }

                        @Override
                        public void onSuccess(String result) {
                            LogUtils.log("AddressDisplayModel:result--->" + result + "<---result");
                            addressDisplay = gson.fromJson(result,AddressDisplay.class);
                            address = addressDisplay.getAddress();
                            LogUtils.log("AddressDisplayModel:result--->" + address + "<---result");

                            callback.callbackAddressDisplayInfo(address,position);
                        }

                        @Override
                        public void onError(Throwable ex, boolean isOnCallback) {
                            LogUtils.log("--------->" + "AddressDisplayModel:3.2+onError" +ex.toString()+ "<-----------");
                        }

                        @Override
                        public void onCancelled(CancelledException cex) {
                            LogUtils.log("--------->" + "AddressDisplayModel:3.2+onCancelled" + "<-----------");
                        }

                        @Override
                        public void onFinished() {
                            LogUtils.log("--------->" + "AddressDisplayModel:3.2+onFinished" + "<-----------");
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                    LogUtils.log("--------->" + "AddressDisplayModel:3.2+printStackTrace"+e.toString() + "<-----------");
                }


    }
}

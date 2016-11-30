package com.silianchuangye.sumao.success.fragments.myPlasticTrade.personalInformation;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.OrderManagement.SpotOrder.TiQu;
import com.silianchuangye.sumao.success.utils.SuMaoConstant;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

/**
 * 资讯订阅
 * Created by Administrator on 2016/5/9 0009.
 */
public class InformationSubscription extends Activity implements View.OnClickListener{
    private ImageView backImg,secondImg,topImg;
    private boolean flag;
    boolean JinFlag;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.zixun);
        initView();
        showDateNet();
    }

    private void initView() {
        backImg= (ImageView) findViewById(R.id.img_zixun_back);
        topImg= (ImageView) findViewById(R.id.img_zixun_top);
        secondImg= (ImageView) findViewById(R.id.img_zixun_second);
        backImg.setOnClickListener(this);
        topImg.setOnClickListener(this);
        secondImg.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_zixun_back:
                finish();
                break;
            case R.id.img_zixun_top:
                Log.e("TAG","flag--"+flag);
                if(flag) {
                    showDingyue("y");
                    topImg.setImageResource(R.mipmap.zixun_select);
                    flag=false;
                }else{
                    showDingyue("n");
                    topImg.setImageResource(R.mipmap.zixun_select_null);
                    flag=true;
                }
                break;
            case R.id.img_zixun_second:
                Log.e("TAG","jinFlag=="+JinFlag);
                if(JinFlag) {
                    showJingPaiNet("y");
                    secondImg.setImageResource(R.mipmap.zixun_select);
                    JinFlag=false;
                }else{
                    showJingPaiNet("n");
                    secondImg.setImageResource(R.mipmap.zixun_select_null);
                    JinFlag=true;
                }
                break;
        }
    }
    //资讯订阅显示
    private  void showDateNet(){
        RequestParams rp=new RequestParams(SuMaoConstant.SUMAO_IP+"/rest/model/atg/userprofiling/ProfileActor/subInfo");
        x.http().post(rp, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                //{"isSub":"n","isSubProduct":"y","U_uname":"刘郦雅","info":"sucess"}
                JSONObject job= null;
                try {
                    job = new JSONObject(result);
                    String info=job.getString("info");
                    if(info.equals("fail")){
                        Toast.makeText(InformationSubscription.this,"请重新登陆",Toast.LENGTH_SHORT).show();
                        new TiQu(InformationSubscription.this).showLogin();
                        InformationSubscription.this.finish();
                    }
                    String isSub=job.getString("isSub");//是否订阅
                    String isSubProduct=job.getString("isSubProduct");//是否订阅竞拍
                    if(isSub.equals("y")){
                        topImg.setImageResource(R.mipmap.zixun_select);
                        flag=false;
                    }else{
                        topImg.setImageResource(R.mipmap.zixun_select_null);
                        flag=true;
                    }
                    if(isSubProduct.equals("y")){
                        secondImg.setImageResource(R.mipmap.zixun_select);
                        JinFlag=false;
                    }else{
                        secondImg.setImageResource(R.mipmap.zixun_select_null);
                        JinFlag=true;
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
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
    }
    //竞拍订阅消息
    private void showJingPaiNet(final String state){
        RequestParams params=new RequestParams(SuMaoConstant.SUMAO_IP+"/rest/model/atg/userprofiling/ProfileActor/subscribeAuction");
        params.addParameter("isSubProduct",state);
        SharedPreferences sp=getSharedPreferences("sumao", Activity.MODE_PRIVATE);
        String unique123= sp.getString("unique", "");
        params.addParameter("_dynSessConf",unique123);
        Log.e("TAG","params-------"+params);
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.e("TAG","订阅result--------"+result);
                JSONObject job= null;
                try {
                    job = new JSONObject(result);
                    String info=job.getString("info");
                    if(info.equals("fail")){
                        Toast.makeText(InformationSubscription.this,"请重新登陆",Toast.LENGTH_SHORT).show();
                        new TiQu(InformationSubscription.this).showLogin();
                        InformationSubscription.this.finish();
                    }
                    String status=job.getString("status");
                    if(status.equals("YES")&&state.equals("y")){
                        Toast.makeText(InformationSubscription.this,"订阅成功",Toast.LENGTH_SHORT).show();
                        secondImg.setImageResource(R.mipmap.zixun_select);
                    }else if(status.equals("YES")&&state.equals("n")){
                        secondImg.setImageResource(R.mipmap.zixun_select_null);
                        Toast.makeText(InformationSubscription.this,"取消订阅",Toast.LENGTH_SHORT).show();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

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
    }
    //订阅资讯公告
    private void showDingyue(final String state){
        RequestParams params=new RequestParams(SuMaoConstant.SUMAO_IP+"/rest/model/atg/userprofiling/ProfileActor/subscribe");
        params.addParameter("isSub",state);
        SharedPreferences sp=getSharedPreferences("sumao", Activity.MODE_PRIVATE);
        String unique123= sp.getString("unique", "");
        params.addParameter("_dynSessConf",unique123);
        Log.e("TAG","params-------"+params);
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.e("TAG","订阅result--------"+result);
                JSONObject job= null;
                try {
                    job = new JSONObject(result);
                    String info=job.getString("info");
                    if(info.equals("fail")){
                        Toast.makeText(InformationSubscription.this,"请重新登陆",Toast.LENGTH_SHORT).show();
                        new TiQu(InformationSubscription.this).showLogin();
                        InformationSubscription.this.finish();
                    }
                    String status=job.getString("status");
                    if(status.equals("YES")&&state.equals("y")){
                        topImg.setImageResource(R.mipmap.zixun_select);
                        Toast.makeText(InformationSubscription.this,"订阅成功",Toast.LENGTH_SHORT).show();
                    }else if(status.equals("YES")&&state.equals("n")){
                        topImg.setImageResource(R.mipmap.zixun_select_null);
                        Toast.makeText(InformationSubscription.this,"取消订阅",Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

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
    }
}

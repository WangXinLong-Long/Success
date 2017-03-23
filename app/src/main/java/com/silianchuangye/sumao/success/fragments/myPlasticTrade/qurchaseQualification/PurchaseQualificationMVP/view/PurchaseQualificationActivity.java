package com.silianchuangye.sumao.success.fragments.myPlasticTrade.qurchaseQualification.PurchaseQualificationMVP.view;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.qurchaseQualification.PurchaseQualificationMVP.bean.PurchaseQualificationBean;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.qurchaseQualification.PurchaseQualificationMVP.presenter.PurchaseQualificationPresenter;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.qurchaseQualification.purchaseQualificationDetail.PurchaseQualificationDetailMVP.PurchaseQualificationDetailActivity;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.qurchaseQualification.purchaseQualificationDetail.ResultActivity;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.qurchaseQualification.purchaseQualificationDetail.WaitActivity;
import com.silianchuangye.sumao.success.utils.Loding;
import com.silianchuangye.sumao.success.utils.SuMaoConstant;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * 购买资质申请
 */
public class PurchaseQualificationActivity extends AppCompatActivity implements View.OnClickListener, IPurchaseQualificationView {

    private ListView upstreamResourcesListView;
    private TextView tv_child_title_bar_title;
    private ImageView iv_child_title_bar_back;
    private PurchaseQualificationPresenter purchaseQualificationPresenter;
    private List<String> nameList = new ArrayList<>();;
    private List<PurchaseQualificationBean.OrgBean> org;
    String unique;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences sp=getSharedPreferences("sumao", Activity.MODE_PRIVATE);
        unique=sp.getString("unique","");
        setContentView(R.layout.activity_purchase_qualification);
        purchaseQualificationPresenter = new PurchaseQualificationPresenter(this);
        Loding.show(this,"加载中...",true,null);
        purchaseQualificationPresenter.getPurchaseQualificationDetail(unique);
        upstreamResourcesListView = ((ListView) findViewById(R.id.upstream_resources));
        tv_child_title_bar_title = ((TextView) findViewById(R.id.tv_child_title_bar_title));
        tv_child_title_bar_title.setText("购买资质申请");
        iv_child_title_bar_back = ((ImageView) findViewById(R.id.iv_child_title_bar_back));


        upstreamResourcesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                getStatus(org.get(position).getId());
            }
        });
    }

    public void getStatus(final String id){
        Log.d("企业id",id);

        String url= SuMaoConstant.SUMAO_IP+"/rest/model/atg/userprofiling/ProfileActor/upresource";
        RequestParams rp=new RequestParams(url);
        rp.addParameter("_dynSessConf",unique);
        rp.addParameter("up",id);
        x.http().post(rp, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.d("判断状态的返回值",""+result);
                try {
                    JSONObject object=new JSONObject(result);
                    String booleancommit=object.getString("result");
                    if (booleancommit.equals("true")){
                        if (object.getString("state").equals("待审核")){
                            //已经提交过
                            Intent intent = new Intent(PurchaseQualificationActivity.this, WaitActivity.class);
                            intent.putExtra("id",id);
                            intent.putExtra("result",result);
                            startActivity(intent);
                        }else if (object.getString("state").equals("有")){
                            Intent intent=new Intent(PurchaseQualificationActivity.this, ResultActivity.class);
                            intent.putExtra("state","已通过");
                            startActivity(intent);
                        }else if (object.getString("state").equals("无")){
                            Intent intent=new Intent(PurchaseQualificationActivity.this, ResultActivity.class);
                            intent.putExtra("state","未通过");

                            startActivity(intent);
                        }



                    }else if (booleancommit.equals("false")){

                        //没有提交过
                        Intent intent = new Intent(PurchaseQualificationActivity.this, PurchaseQualificationDetailActivity.class);
                        intent.putExtra("id",id);
                        startActivity(intent);
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


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_child_title_bar_back:
                finish();
                break;
        }
    }

    @Override
    public void setPurchaseQualificationDetailInView(String result) {
        Log.d("返回值",result);
        Loding.dis();
        if (result != null) {
            Gson gson = new Gson();
            PurchaseQualificationBean purchaseQualificationBean = gson.fromJson(result, PurchaseQualificationBean.class);
            org = purchaseQualificationBean.getOrg();
            for (int i = 0; i < org.size(); i++) {
                nameList.add(org.get(i).getName());
            }
            ArrayAdapter<String> upstreamadapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, nameList);
            upstreamResourcesListView.setAdapter(upstreamadapter);
        }
    }
}

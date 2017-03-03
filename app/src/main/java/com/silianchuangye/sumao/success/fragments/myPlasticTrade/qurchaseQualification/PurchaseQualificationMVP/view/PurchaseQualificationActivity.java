package com.silianchuangye.sumao.success.fragments.myPlasticTrade.qurchaseQualification.PurchaseQualificationMVP.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
import com.silianchuangye.sumao.success.utils.Loding;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase_qualification);
        purchaseQualificationPresenter = new PurchaseQualificationPresenter(this);
        Loding.show(this,"加载中...",true,null);
        purchaseQualificationPresenter.getPurchaseQualificationDetail();
        upstreamResourcesListView = ((ListView) findViewById(R.id.upstream_resources));
        tv_child_title_bar_title = ((TextView) findViewById(R.id.tv_child_title_bar_title));
        tv_child_title_bar_title.setText("购买资质申请");
        iv_child_title_bar_back = ((ImageView) findViewById(R.id.iv_child_title_bar_back));


        upstreamResourcesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(PurchaseQualificationActivity.this, PurchaseQualificationDetailActivity.class);
                intent.putExtra("id",org.get(position).getId());
                startActivity(intent);
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

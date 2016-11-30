package com.silianchuangye.sumao.success.fragments.homepage.UpstreamDirectSellingMVP.UpstreamDirectDetailMVP.view;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.ShangYou.CaiGouMVP.view.CaiGou;
import com.silianchuangye.sumao.success.fragments.homepage.UpstreamDirectSellingMVP.UpstreamDirectDetailMVP.bean.DirectSellingBean;
import com.silianchuangye.sumao.success.fragments.homepage.UpstreamDirectSellingMVP.UpstreamDirectDetailMVP.presenter.UpstreamDirectDetailPresenter;
import com.silianchuangye.sumao.success.fragments.homepage.UpstreamDirectSellingMVP.bean.vipProductBean.VipProductBean;
import com.silianchuangye.sumao.success.fragments.homepage.auction.AuctionMVP.AuctionActivity;
import com.silianchuangye.sumao.success.fragments.homepage.goodInStock.GoodsInStockActivityMVP.view.GoodsInStockActivity;
import com.silianchuangye.sumao.success.utils.LogUtils;

public class UpstreamDirectSellingDetailsActivity extends AppCompatActivity implements View.OnClickListener, IUpstreamDirectDetailView {

    private TextView tv_child_title_bar_title;
    private ImageView iv_child_title_bar_back;
    private RelativeLayout auction_session_rl;
    private RelativeLayout direct_selling_rl;
    private RelativeLayout procurement_planning_rl;
    private VipProductBean vipProductBean;
    private String sellerCompanyId;
    private UpstreamDirectDetailPresenter upstreamDirectDetailPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        upstreamDirectDetailPresenter = new UpstreamDirectDetailPresenter(this);
        Intent intent = getIntent();
        vipProductBean = (VipProductBean) intent.getSerializableExtra("vipProductBean");
        sellerCompanyId = intent.getStringExtra("sellerCompanyId");
        String title = intent.getStringExtra("title");
        setContentView(R.layout.activity_upstream_direct_selling_details);
        tv_child_title_bar_title = ((TextView) findViewById(R.id.tv_child_title_bar_title));
        LogUtils.log("title--->" + title);
        tv_child_title_bar_title.setText(title);
        iv_child_title_bar_back = ((ImageView) findViewById(R.id.iv_child_title_bar_back));
//        竞拍专场
        auction_session_rl = ((RelativeLayout) findViewById(R.id.auction_session_rl));
        auction_session_rl.setOnClickListener(this);
//        现货直销
        direct_selling_rl = ((RelativeLayout) findViewById(R.id.direct_selling_rl));
        direct_selling_rl.setOnClickListener(this);
//        采购计划
        procurement_planning_rl = ((RelativeLayout) findViewById(R.id.procurement_planning_rl));
        iv_child_title_bar_back.setOnClickListener(this);
        procurement_planning_rl.setOnClickListener(this);
        direct_selling_rl.setOnClickListener(this);
        iv_child_title_bar_back.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_child_title_bar_back:
                finish();
                break;
            case R.id.procurement_planning_rl://        采购计划
                Intent intent = new Intent(this, CaiGou.class);
                intent.putExtra("vipProductBean", vipProductBean);
                intent.putExtra("sellerCompanyId", sellerCompanyId);
                startActivity(intent);
                break;
            case R.id.direct_selling_rl://        现货直销
                upstreamDirectDetailPresenter.getDirectSelling(1 + "", sellerCompanyId, getSharedPreferences("sumao", Activity.MODE_PRIVATE).getString("unique", ""));

                break;
            case R.id.auction_session_rl://
                //      竞拍专场
                Log.d("竞拍专场的点击事件", "点击事件");
                Intent intent_Acution = new Intent(UpstreamDirectSellingDetailsActivity.this, AuctionActivity.class);
                intent_Acution.putExtra("name", "竞拍专场");
                intent_Acution.putExtra("sellerCompanyId", sellerCompanyId);
                startActivity(intent_Acution);
                break;

        }
    }

    @Override
    public void setUpstreanDirectDataInView(DirectSellingBean directSellingBean) {
        Toast.makeText(this, "现货直销跳转到现货列表页", Toast.LENGTH_SHORT).show();
        Intent intent1 = new Intent(UpstreamDirectSellingDetailsActivity.this, GoodsInStockActivity.class);
        intent1.putExtra("title", "现货直销");
        intent1.putExtra("directSellingBean", directSellingBean);
        startActivity(intent1);
    }
}

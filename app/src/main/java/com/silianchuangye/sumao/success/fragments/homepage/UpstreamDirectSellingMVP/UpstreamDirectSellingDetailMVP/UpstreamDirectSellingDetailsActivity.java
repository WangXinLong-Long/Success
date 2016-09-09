package com.silianchuangye.sumao.success.fragments.homepage.UpstreamDirectSellingMVP.UpstreamDirectSellingDetailMVP;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.ShangYou.CaiGou;

public class UpstreamDirectSellingDetailsActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView tv_child_title_bar_title;
    private ImageView iv_child_title_bar_back;
    private RelativeLayout auction_session_rl;
    private RelativeLayout direct_selling_rl;
    private RelativeLayout procurement_planning_rl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upstream_direct_selling_details);
        tv_child_title_bar_title = ((TextView) findViewById(R.id.tv_child_title_bar_title));
        iv_child_title_bar_back = ((ImageView) findViewById(R.id.iv_child_title_bar_back));
//        竞拍专场
        auction_session_rl = ((RelativeLayout) findViewById(R.id.auction_session_rl));
//        现货直销
        direct_selling_rl = ((RelativeLayout) findViewById(R.id.direct_selling_rl));
//        采购计划
        procurement_planning_rl = ((RelativeLayout) findViewById(R.id.procurement_planning_rl));
        iv_child_title_bar_back.setOnClickListener(this);
        procurement_planning_rl.setOnClickListener(this);
        direct_selling_rl.setOnClickListener(this);
        iv_child_title_bar_back.setOnClickListener(this);
//        修改标题文字
        tv_child_title_bar_title.setText("");

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_child_title_bar_back:
                finish();
                break;
            case R.id.procurement_planning_rl://        采购计划
                Intent intent=new Intent(this,CaiGou.class);
                startActivity(intent);
                break;
            case R.id.direct_selling_rl://        现货直销

                break;
            case R.id.auction_session_rl://        竞拍专场

                break;

        }
    }
}

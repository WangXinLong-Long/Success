package com.silianchuangye.sumao.success.fragments.myPlasticTrade.qurchaseQualification.purchaseQualificationDetail;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.silianchuangye.sumao.success.R;

public class ResultActivity extends AppCompatActivity {
    private ImageView ivResult;
    private TextView enterprise_additional_information_tv;
    private TextView split_line1;
    private TextView split_line2;
    private ImageView iv_jieguo;
    private TextView tv_Result;
    private TextView tv_child_title_bar_title;
    private ImageView iv_child_title_bar_back;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        init();
        tv_child_title_bar_title.setText("申请成为上游资源方客户");
        iv_child_title_bar_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ResultActivity.this.finish();
            }
        });
        Bundle bundle=getIntent().getExtras();
        String state=bundle.getString("state");
        if (state.equals("已通过")){
             ivResult.setImageResource(R.mipmap.pass_i);
             iv_jieguo.setImageResource(R.mipmap.pass_icon);
             tv_Result.setText("审核通过");
        }else if (state.equals("未通过")){
            ivResult.setImageResource(R.mipmap.nopass);
            iv_jieguo.setImageResource(R.mipmap.nopass_icon);
            tv_Result.setText("审核未通过");
        }
    }

    public void init(){
        ivResult= (ImageView) findViewById(R.id.ivResult);
        enterprise_additional_information_tv= (TextView) findViewById(R.id.enterprise_additional_information_tv);
        split_line1= (TextView) findViewById(R.id.split_line1);
        split_line2= (TextView) findViewById(R.id.split_line2);
        iv_jieguo= (ImageView) findViewById(R.id.iv_jieguo);
        tv_Result= (TextView) findViewById(R.id.tv_Result);
        tv_child_title_bar_title= (TextView) findViewById(R.id.tv_child_title_bar_title);
        iv_child_title_bar_back= (ImageView) findViewById(R.id.iv_child_title_bar_back);
    }
}

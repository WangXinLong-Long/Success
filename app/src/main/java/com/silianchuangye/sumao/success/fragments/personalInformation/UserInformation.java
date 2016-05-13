package com.silianchuangye.sumao.success.fragments.personalInformation;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.silianchuangye.sumao.success.R;

/**
 * Created by Administrator on 2016/5/9 0009.
 */
public class UserInformation extends Activity implements  View.OnClickListener{

            ImageView iv_title_bar_logo,iv_title_bar_back,iv_title_bar_service,iv_title_bar_search;
            Button sv_title_bar_serachView;
            TextView tv_title_bar_title;
            RelativeLayout title_user_information;

    TextView nu_information,mu_information,tu_information,ac_name;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_user_information);

            /**
             * 标题栏显示与隐藏的内容
             */
            title_user_information = ((RelativeLayout) findViewById(R.id.title_user_information));
            title_user_information.setBackgroundColor(getResources().getColor(R.color.textColor_expandable_listview_show));

            iv_title_bar_back = ((ImageView) findViewById(R.id.iv_title_bar_back));
            iv_title_bar_logo = ((ImageView) findViewById(R.id.iv_title_bar_logo));
            iv_title_bar_service = ((ImageView) findViewById(R.id.iv_title_bar_service));
            iv_title_bar_search = ((ImageView) findViewById(R.id.iv_title_bar_search));
            sv_title_bar_serachView = ((Button) findViewById(R.id.sv_title_bar_serachView));
            tv_title_bar_title  = ((TextView) findViewById(R.id.tv_title_bar_title));

            iv_title_bar_logo.setVisibility(View.INVISIBLE);
            iv_title_bar_service.setVisibility(View.INVISIBLE);
            sv_title_bar_serachView.setVisibility(View.INVISIBLE);
            iv_title_bar_search.setVisibility(View.INVISIBLE);
            iv_title_bar_back.setOnClickListener(this);
            tv_title_bar_title.setText("用户信息");
            tv_title_bar_title.setTextColor(Color.WHITE);
/**
 * 标题栏一下的内容
 */
            ac_name = ((TextView) findViewById(R.id.ac_name));
            nu_information = ((TextView) findViewById(R.id.nu_information));
            mu_information = ((TextView) findViewById(R.id.mu_information));
            tu_information = ((TextView) findViewById(R.id.tu_information));
        }

        @Override
        public void onClick(View v) {
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                switch (v.getId())
                {
                        case R.id.iv_title_bar_back:
                        finish();
                        break;

                        case R.id.name_user_information:
                        intent.setClass(this,ModifyName.class);
                        bundle.putString("receivingInformation","修改姓名");
                        bundle.putBoolean("canBeAmpty",false);
                        intent.putExtras(bundle);
                        startActivity(intent);
                        break;
                        case R.id.mailbox_user_information:
                        intent.setClass(this,ModifyName.class);
                        bundle.putString("receivingInformation","修改邮箱");
                        bundle.putBoolean("canBeAmpty",true);
                        intent.putExtras(bundle);
                        startActivity(intent);
                        break;
                        case R.id.telephone_user_information:
                        intent.setClass(this,ModifyTelephone.class);
                        startActivity(intent);
                        break;

                }
        }
}


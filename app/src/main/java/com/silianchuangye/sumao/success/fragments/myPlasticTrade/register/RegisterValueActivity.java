package com.silianchuangye.sumao.success.fragments.myPlasticTrade.register;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.silianchuangye.sumao.success.R;

public class RegisterValueActivity extends AppCompatActivity {
    ImageView iv_title_bar_logo,
            iv_title_bar_back,
            iv_title_bar_service,
            add_address,
            iv_title_bar_search;
    Button sv_title_bar_serachView;
    TextView tv_title_bar_title,tv,tvUpdate;
    RelativeLayout layoutTop,add_address_rl;
    private EditText ed_content_value;
    private TextView tv_a;
    private Button bt_save_register_value;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_value);
        tv_a= (TextView) findViewById(R.id.tv_a);
        ed_content_value= (EditText) findViewById(R.id.et_content_value);
        bt_save_register_value= (Button) findViewById(R.id.bt_save_register_value);
        Bundle bundle=getIntent().getExtras();
        String title=bundle.getString("title");
        String content=bundle.getString("content");
        title_Bar(title);
        if(!content.equals("")){
            tv_a.setVisibility(View.VISIBLE);
            tv_a.setText(content+",可由中英文,数字,'-','_'组成");
        }
        bt_save_register_value.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //保存按钮的点击事件
            }
        });

    }
    public void title_Bar(String string){

        iv_title_bar_back = ((ImageView) findViewById(R.id.iv_title_bar_back));
        iv_title_bar_logo = ((ImageView) findViewById(R.id.iv_title_bar_logo));
        iv_title_bar_service = ((ImageView) findViewById(R.id.iv_title_bar_service));
        iv_title_bar_search = ((ImageView) findViewById(R.id.iv_title_bar_search));
        sv_title_bar_serachView = ((Button) findViewById(R.id.sv_title_bar_serachView));
        tv_title_bar_title  = ((TextView) findViewById(R.id.tv_title_bar_title));

        tvUpdate= (TextView) findViewById(R.id.tvUpdate);
        iv_title_bar_logo.setVisibility(View.GONE);
        iv_title_bar_service.setVisibility(View.GONE);
        sv_title_bar_serachView.setVisibility(View.GONE);
        iv_title_bar_search.setVisibility(View.GONE);

        tvUpdate.setVisibility(View.GONE);
        tv_title_bar_title.setText(string);


        tv_title_bar_title.setTextColor(Color.WHITE);
        iv_title_bar_back.setVisibility(View.VISIBLE);
        layoutTop= (RelativeLayout) findViewById(R.id.layoutTop_register_value);
        layoutTop.setBackgroundColor(getResources().getColor(R.color.textColor_expandable_listview_show));

        iv_title_bar_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RegisterValueActivity.this.finish();
            }
        });

    }
}

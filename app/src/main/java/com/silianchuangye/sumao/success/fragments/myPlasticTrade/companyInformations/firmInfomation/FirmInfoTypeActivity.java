package com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.firmInfomation;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.silianchuangye.sumao.success.R;

public class FirmInfoTypeActivity extends AppCompatActivity {
    ImageView iv_title_bar_logo,
            iv_title_bar_back,
            iv_title_bar_service,
            iv_title_bar_search;
    Button sv_title_bar_serachView;
    TextView tv_title_bar_title, tv;
    RelativeLayout layoutTop;
    RadioButton one,two,three;
    RadioGroup rgDemo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firm_info_type);
        one= (RadioButton) findViewById(R.id.rbone_firm_info_type);
        two= (RadioButton) findViewById(R.id.rbtwo_firm_info_type);
        three= (RadioButton) findViewById(R.id.rbthree_firm_info_type);
        rgDemo= (RadioGroup) findViewById(R.id.rgDemo);
       // rgDemo.setOnCheckedChangeListener(radiogpchange);
        int id=rgDemo.getCheckedRadioButtonId();
        if (id==-1){
            Toast.makeText(FirmInfoTypeActivity.this, "请选择一个", Toast.LENGTH_SHORT).show();
        }
        rgDemo.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                Toast.makeText(FirmInfoTypeActivity.this, "进来了",Toast.LENGTH_SHORT).show();
                if (checkedId ==one.getId()) {
                    Toast.makeText(FirmInfoTypeActivity.this, "one选中", Toast.LENGTH_SHORT).show();
                } else if (checkedId == two.getId()) {
                    Toast.makeText(FirmInfoTypeActivity.this, "two选中", Toast.LENGTH_SHORT).show();
                }else if (checkedId==three.getId()){
                    Toast.makeText(FirmInfoTypeActivity.this, "three选中", Toast.LENGTH_SHORT).show();
                }
            }
        });



        title_Bar();
    }



    public void title_Bar() {
        iv_title_bar_back = ((ImageView) findViewById(R.id.iv_title_bar_back));
        iv_title_bar_logo = ((ImageView) findViewById(R.id.iv_title_bar_logo));
        iv_title_bar_service = ((ImageView) findViewById(R.id.iv_title_bar_service));
        iv_title_bar_search = ((ImageView) findViewById(R.id.iv_title_bar_search));
        sv_title_bar_serachView = ((Button) findViewById(R.id.sv_title_bar_serachView));
        tv_title_bar_title = ((TextView) findViewById(R.id.tv_title_bar_title));

        iv_title_bar_logo.setVisibility(View.GONE);
        iv_title_bar_service.setVisibility(View.GONE);
        sv_title_bar_serachView.setVisibility(View.GONE);
        iv_title_bar_search.setVisibility(View.GONE);
        tv_title_bar_title.setText("企业类型");
        tv_title_bar_title.setTextColor(Color.WHITE);
        iv_title_bar_back.setVisibility(View.VISIBLE);
        layoutTop = (RelativeLayout) findViewById(R.id.layoutTop_firm_info_type);
        layoutTop.setBackgroundColor(getResources().getColor(R.color.textColor_expandable_listview_show));

        iv_title_bar_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirmInfoTypeActivity.this.finish();
            }
        });
    }
}
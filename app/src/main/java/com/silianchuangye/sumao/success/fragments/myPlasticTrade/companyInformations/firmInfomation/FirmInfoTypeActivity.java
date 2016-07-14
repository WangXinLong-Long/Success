package com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.firmInfomation;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
    private String name;
    private Button btSave;
    private LinearLayout three_layout;
    String title;
    int number;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firm_info_type);
        one= (RadioButton) findViewById(R.id.rbone_firm_info_type);
        two= (RadioButton) findViewById(R.id.rbtwo_firm_info_type);
        three= (RadioButton) findViewById(R.id.rbthree_firm_info_type);
       // three.setChecked(true);
        one.setChecked(false);
        three_layout= (LinearLayout) findViewById(R.id.three);
        Bundle bundle=getIntent().getExtras();
         title=bundle.getString("title");
          number=bundle.getInt("number");
        Log.d("number",number+"");
        if (title.equals("企业类型")){
            one.setText("贸易商");
            two.setText("工贸一体");
            three.setText("生产厂");
        }else if (title.equals("纳税人类型")){
            one.setText("一般纳税人");
            two.setText("小规模纳税人");
            three_layout.setVisibility(View.GONE);
            three.setVisibility(View.GONE);
        }
        rgDemo= (RadioGroup) findViewById(R.id.rgDemo);
       // rgDemo.setOnCheckedChangeListener(radiogpchange);
        int id=rgDemo.getCheckedRadioButtonId();
        if (id==-1){
            Toast.makeText(FirmInfoTypeActivity.this, "请选择一个", Toast.LENGTH_SHORT).show();
        }
        rgDemo.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

              //  Toast.makeText(FirmInfoTypeActivity.this, "进来了",Toast.LENGTH_SHORT).show();
                if (checkedId == one.getId()) {
                Toast.makeText(FirmInfoTypeActivity.this, "one选中", Toast.LENGTH_SHORT).show();
                    name=one.getText().toString();
                    Log.d("企业类型",name);
                } else if (checkedId == two.getId()) {
                    Toast.makeText(FirmInfoTypeActivity.this, "two选中", Toast.LENGTH_SHORT).show();
                    name=two.getText().toString();
                    Log.d("企业类型",name);
                }else if (checkedId==three.getId()){
                    Toast.makeText(FirmInfoTypeActivity.this, "three选中", Toast.LENGTH_SHORT).show();
                    name=three.getText().toString();
                    Log.d("企业类型",name);
                }
            }
        });
        btSave= (Button) findViewById(R.id.btSave);
        btSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Log.d("点击事件","真");
                Intent intent=new Intent();
                intent.putExtra("name",name);
                FirmInfoTypeActivity.this.setResult(number,intent);
                FirmInfoTypeActivity.this.finish();
            }
        });

        title_Bar(title);
    }



    public void title_Bar(String name) {
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
        tv_title_bar_title.setText(name);
        tv_title_bar_title.setTextColor(Color.WHITE);
        iv_title_bar_back.setVisibility(View.GONE);
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
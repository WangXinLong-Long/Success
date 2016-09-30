package com.silianchuangye.sumao.success.fragments.myPlasticTrade.PhysicalDistributionManagement.logistics;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.silianchuangye.sumao.success.R;

public class logisticsAddress extends AppCompatActivity {
    private TextView tv_title_logistics,tv_tishi,tv_date;
    private EditText edt;
    private Button btn;
    String str;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logistics_address);
        str= getIntent().getStringExtra("title");
        tv_title_logistics= (TextView) findViewById(R.id.tv_title_logistics);
        tv_title_logistics.setText(str);
        tv_tishi= (TextView) findViewById(R.id.tv_tishi);
        edt= (EditText) findViewById(R.id.edt_message);
        btn= (Button) findViewById(R.id.btn_logicits_save);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edt.getText().toString().length()<=0){
                    tv_tishi.setVisibility(View.VISIBLE);
                }else{
                    Intent intent=new Intent();
                    intent.putExtra("message",edt.getText().toString());
                    setResult(99,intent);
                    finish();
                }
            }
        });
    }
}

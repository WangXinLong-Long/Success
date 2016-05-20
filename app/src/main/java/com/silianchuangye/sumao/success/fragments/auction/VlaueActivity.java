package com.silianchuangye.sumao.success.fragments.auction;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.silianchuangye.sumao.success.R;

public class VlaueActivity extends AppCompatActivity {
    private TextView tv_Value;
    private TextView tv_value_content;
    private ImageView ivBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vlaue);
        tv_Value= (TextView) findViewById(R.id.tv_Value);
        tv_value_content= (TextView) findViewById(R.id.tv_value_content);
        Bundle bundle=this.getIntent().getExtras();
        String a= bundle.getString("text");
        String b=bundle.getString("value");
        tv_Value.setText(a);
        tv_value_content.setText(b);
        ivBack= (ImageView) findViewById(R.id.ivBack_value);
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VlaueActivity.this.finish();
            }
        });

    }
}

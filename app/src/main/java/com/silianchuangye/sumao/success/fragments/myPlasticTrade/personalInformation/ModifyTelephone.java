package com.silianchuangye.sumao.success.fragments.myPlasticTrade.personalInformation;

import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.silianchuangye.sumao.success.R;


/**
 * Created by Administrator on 2016/5/9 0009.
 */
public class ModifyTelephone extends Activity implements View.OnClickListener {

    ImageView iv_title_bar_logo,iv_title_bar_back,iv_title_bar_service,iv_title_bar_search;
    Button sv_title_bar_serachView;
    TextView tv_title_bar_title;

    RelativeLayout title_modify_telephone;
    TextView omp_number;
    EditText nmb_number;
    SharedPreferences sp;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.modify_telephone);
        title_modify_telephone = ((RelativeLayout) findViewById(R.id.title_modify_telephone));
        title_modify_telephone.setBackgroundColor(getResources().getColor(R.color.textColor_expandable_listview_show));
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
        tv_title_bar_title.setText("修改电话");
        tv_title_bar_title.setTextColor(Color.WHITE);

        omp_number = ((TextView) findViewById(R.id.Omp_number));
        nmb_number = ((EditText) findViewById(R.id.Nmb_number));
        sp = getSharedPreferences("silian",MODE_PRIVATE);
        omp_number.setText(sp.getString("count",""));
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.iv_title_bar_back:
                finish();
                break;
            case R.id.modify_telephone_save:
                Toast.makeText(this,"修改了手机号",Toast.LENGTH_SHORT).show();

                /**
                 * 这里要加n多层判断
                 */
                if (true)
                {
                    String telephone = nmb_number.getText().toString();
                     sp = getSharedPreferences("silian",MODE_PRIVATE);
                     editor = sp.edit();
                    editor.putString("count",telephone);
                    editor.commit();
                }
                 finish();
                break;
        }
    }
}

package com.silianchuangye.sumao.success.ShangYou;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.silianchuangye.sumao.success.R;

/**
 * Created by Administrator on 2016/9/6.
 */
public class DayPlanAlert extends AlertDialog implements View.OnClickListener{
    private ImageView img;
    private Button btn_canle;
    private Button btn_tijiao;
    public DayPlanAlert(Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dayplan_alert);
        img= (ImageView) findViewById(R.id.img_margin_dialog_errey);
        btn_canle= (Button) findViewById(R.id.btn_dayplan_alert_canle);
        btn_tijiao= (Button) findViewById(R.id.btn_dayplan_alert_tijiao);
        img.setOnClickListener(this);
        btn_canle.setOnClickListener(this);
        btn_tijiao.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_margin_dialog_errey:
                this.dismiss();
                break;
            case R.id.btn_dayplan_alert_canle:
                Toast.makeText(getContext(),"取消计划",Toast.LENGTH_LONG).show();
                break;
            case R.id.btn_dayplan_alert_tijiao:
                Toast.makeText(getContext(),"提交计划",Toast.LENGTH_LONG).show();
                break;
        }
    }
}

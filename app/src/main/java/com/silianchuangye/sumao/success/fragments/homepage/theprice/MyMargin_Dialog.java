package com.silianchuangye.sumao.success.fragments.homepage.theprice;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.mapapi.map.Text;
import com.silianchuangye.sumao.success.R;

/**
 * Created by Administrator on 2016/5/19.
 */
public class MyMargin_Dialog extends AlertDialog implements View.OnClickListener{
    private Context ctx;
    private ImageView img_MyMargin_Dialog_Erroy;
    private TextView tv_MyMargin_Dialog_dan_num;
    protected MyMargin_Dialog(Context context) {
        super(context);
        this.ctx=context;
        setCanceledOnTouchOutside(true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.margin_dialog);
        initView();
    }

    private void initView() {
        img_MyMargin_Dialog_Erroy= (ImageView) findViewById(R.id.img_margin_dialog_errey);
        tv_MyMargin_Dialog_dan_num= (TextView) findViewById(R.id.tv_margin_dialog_dan_num);
        img_MyMargin_Dialog_Erroy.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.img_margin_dialog_errey){
            Intent intent=new Intent();
            intent.setAction("erroy");
            ctx.sendBroadcast(intent);
        }
    }
}

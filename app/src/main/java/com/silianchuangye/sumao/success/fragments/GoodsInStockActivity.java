package com.silianchuangye.sumao.success.fragments;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.custom.CustomPayDialog;

/**
 * Created by Administrator on 2016/5/20 0020.
 */
public class GoodsInStockActivity extends Activity implements View.OnClickListener{
    Button button_dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_in_stock);
        button_dialog = ((Button) findViewById(R.id.button_dialog));
        button_dialog.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.button_dialog:
                showPayDialog();
                break;
        }
    }
    public void showPayDialog()
    {
        CustomPayDialog.Builder builder = new CustomPayDialog.Builder(this);
        builder.setMessage("这个就是自定义的提示框");
        builder.setTitle("提示");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {

                //设置你的操作事项
                dialog.dismiss();
            }
        });

        builder.setNegativeButton("取消",
                new android.content.DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

        builder.create().show();
    }
}

package com.silianchuangye.sumao.success.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.silianchuangye.sumao.success.MainActivity;
import com.silianchuangye.sumao.success.R;


/**
 * Created by Administrator on 2016/5/20 0020.
 */
public class WelcomeActivity extends Activity {
    int num = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                    Intent intent = new Intent(WelcomeActivity.this,MainActivity.class);
                    WelcomeActivity.this.finish();
                    startActivity(intent);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

}

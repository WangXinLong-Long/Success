package com.silianchuangye.sumao.success.fragments.myPlasticTrade.demandPlanning.PlanPresentationMVP.view;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.silianchuangye.sumao.success.R;

public class ValueActivity extends AppCompatActivity implements View.OnClickListener{
    private TextView tv_child_title_bar_title;
    private String title;
    private EditText et_content_value;
    private Button bt_save_register_value;
    private ImageView iv_child_title_bar_back;
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_value);
        iv_child_title_bar_back= (ImageView) findViewById(R.id.iv_child_title_bar_back);
        et_content_value= (EditText) findViewById(R.id.et_content_value);
        bt_save_register_value= (Button) findViewById(R.id.bt_save_register_value);
        bt_save_register_value.setOnClickListener(this);
        tv_child_title_bar_title= (TextView) findViewById(R.id.tv_child_title_bar_title);
        Bundle bundle=getIntent().getExtras();
        title=bundle.getString("title");
        id=bundle.getInt("id");
        iv_child_title_bar_back.setOnClickListener(this);
        Log.d("传过来position的值",id+"");
        tv_child_title_bar_title.setText(title);



    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_child_title_bar_back:
                Intent intent1=new Intent();
                intent1.putExtra("content","");
                setResult(id,intent1);
                ValueActivity.this.finish();
                break;
            case R.id.bt_save_register_value:
                if (et_content_value.getText().toString()==""||et_content_value.getText().toString().isEmpty()){
                    Log.d("进入","if");
                    Toast.makeText(ValueActivity.this, "您没有输入,请重新输入！", Toast.LENGTH_SHORT).show();
                }else{
                    Log.d("进入","else");
                    Intent intent=new Intent();
                    intent.putExtra("content",et_content_value.getText().toString());
                    setResult(id,intent);
                    ValueActivity.this.finish();
                }
                break;
        }

    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Intent intent=new Intent();
            intent.putExtra("content","");
            setResult(id,intent);
            ValueActivity.this.finish();
            return true;
        } else if (keyCode == KeyEvent.KEYCODE_FOCUS || keyCode == KeyEvent.KEYCODE_CAMERA) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
    public  boolean isShouldHideInput(View v, MotionEvent event) {
        if (v != null && (v instanceof EditText)) {
            int[] leftTop = { 0, 0 };
            //获取输入框当前的location位置
            v.getLocationInWindow(leftTop);
            int left = leftTop[0];
            int top = leftTop[1];
            int bottom = top + v.getHeight();
            int right = left + v.getWidth();
            return !(event.getX() > left && event.getX() < right
                    && event.getY() > top && event.getY() < bottom);
        }
        return false;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (isShouldHideInput(v, ev)) {

                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                if (imm != null) {
                    // Search.clearFocus();
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }
            return super.dispatchTouchEvent(ev);
        }
        // 必不可少，否则所有的组件都不会有TouchEvent了
        if (getWindow().superDispatchTouchEvent(ev)) {
            return true;
        }
        return onTouchEvent(ev);
    }
}

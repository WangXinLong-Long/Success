package com.silianchuangye.sumao.success.ShangYou;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.utils.LogUtils;
import com.silianchuangye.sumao.success.utils.SuMaoConstant;

public class ShangYouSelectNumber extends AppCompatActivity implements View.OnClickListener {

    private EditText modify_information;
    private TextView tv_child_title_bar_title;
    private String titleName;
    private Button modify_name_save;
    private Intent intent;
    private TextView prompt_information;
    private ImageView iv_child_title_bar_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shang_you_select_number);
        iv_child_title_bar_back= (ImageView) findViewById(R.id.iv_child_title_bar_back);
        iv_child_title_bar_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        intent = getIntent();
        titleName = intent.getStringExtra("titleName");
        modify_information = ((EditText) findViewById(R.id.modify_information));
        modify_information.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        tv_child_title_bar_title = ((TextView) findViewById(R.id.tv_child_title_bar_title));
        tv_child_title_bar_title.setText(titleName);
        modify_name_save = ((Button) findViewById(R.id.modify_name_save));
        modify_name_save.setOnClickListener(this);
        prompt_information = ((TextView) findViewById(R.id.prompt_information));
        //限制数量不能超过两位小数
        EditTextWitcher editTextWitcher = new EditTextWitcher();
        modify_information.addTextChangedListener(editTextWitcher);
        modify_information.removeTextChangedListener(editTextWitcher);
    }

    class EditTextWitcher implements TextWatcher {
        @Override
        public void onTextChanged(CharSequence s, int start, int before,
                                  int count) {
            if (s.toString().contains(".")) {
                if (s.length() - 1 - s.toString().indexOf(".") > 2) {
                    s = s.toString().subSequence(0,
                            s.toString().indexOf(".") + 3);
                    modify_information.setText(s);
                    modify_information.setSelection(s.length());
                }
            }
            if (s.toString().trim().substring(0).equals(".")) {
                s = "0" + s;
                modify_information.setText(s);
                modify_information.setSelection(2);
            }

            if (s.toString().startsWith("0")
                    && s.toString().trim().length() > 1) {
                if (!s.toString().substring(1, 2).equals(".")) {
                    modify_information.setText(s.subSequence(0, 1));
                    modify_information.setSelection(1);
                    return;
                }
            }

        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count,
                                      int after) {

        }

        @Override
        public void afterTextChanged(Editable s) {
//                LogUtils.log(s.length() + "");
//                String str = "";
//                if (s.length() == s.toString().trim().indexOf(".")) {
//                    str = s.subSequence(0, s.toString().trim().length() - 2).toString();
//                    modify_information.setText(str);
//                    modify_information.setSelection(s.length());
//                }
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.modify_name_save:
                canSave();
                break;
        }
    }

    private void canSave() {

        if (modify_information.getText().toString().equals("")) {
            prompt_information.setText("*请输入" + titleName);
            prompt_information.setVisibility(View.VISIBLE);
        } else {
            if (modify_information.getText().toString().endsWith(".")) {
                intent.putExtra("number", modify_information.getText().toString().substring(0, modify_information.getText().toString().trim().length() - 1));
            } else {
                intent.putExtra("number", modify_information.getText().toString());
            }


            ShangYouSelectNumber.this.setResult(SuMaoConstant.VIP_SELECTNUMBER_ID, intent);
            finish();
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            intent.putExtra("number", "");
            ShangYouSelectNumber.this.setResult(SuMaoConstant.VIP_SELECTNUMBER_ID, intent);
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
            if (event.getX() > left && event.getX() < right
                    && event.getY() > top && event.getY() < bottom) {
                // 点击的是输入框区域，保留点击EditText的事件
                return false;
            } else {
                return true;
            }
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

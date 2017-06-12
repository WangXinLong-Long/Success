package com.silianchuangye.sumao.success.fragments.myPlasticTrade.register;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.text.method.PasswordTransformationMethod;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.utils.LogUtils;
import com.silianchuangye.sumao.success.utils.SuMaoConstant;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.HttpMethod;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterValueActivity extends AppCompatActivity {
    ImageView iv_title_bar_logo,
            iv_title_bar_back,
            iv_title_bar_service,
            add_address,
            iv_title_bar_search;
    Button sv_title_bar_serachView;
    TextView tv_title_bar_title, tv, tvUpdate;
    RelativeLayout layoutTop, add_address_rl;
    private EditText ed_content_value;
    private TextView tv_a;
    private Button bt_save_register_value;
    Intent intent;
    String title;
    String content;
    String pass;
    String type;
    String url = SuMaoConstant.SUMAO_IP + "/rest/model/atg/store/profile/RegistrationActor/userVerify";
    SharedPreferences sharePreference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_value);
        tv_a = (TextView) findViewById(R.id.tv_a);

        ed_content_value = (EditText) findViewById(R.id.et_content_value);
        bt_save_register_value = (Button) findViewById(R.id.bt_save_register_value);
        intent = new Intent();
        // RegisterFirmActivity registerFirmActivity = new RegisterFirmActivity();

        Bundle bundle = getIntent().getExtras();
        title = bundle.getString("title");
        content = bundle.getString("content");
        pass = bundle.getString("pass");
        LogUtils.log("title:" + title + " content:" + content + " pass:" + pass);
        if (!content.equals("6-16个字符")) {
            ed_content_value.setText(content);
        }
        title_Bar(title);
        if (content.equals("")) {
            tv_a.setVisibility(View.VISIBLE);
            if (title.equals("企业名称")) {
                tv_a.setVisibility(View.GONE);
            } else {
                tv_a.setVisibility(View.GONE);
                tv_a.setText("6-16个字符,可由中英文,数字,'-','_'组成");
            }

        }
        if (title.equals("登录密码") || title.equals("确认密码")) {

            ed_content_value.setText(pass);
            ed_content_value.setTransformationMethod(PasswordTransformationMethod.getInstance());
        } else if (title.equals("邮箱")) {
            tv_a.setVisibility(View.GONE);
            tv_a.setText("6-16个字符,可由中英文,数字,'-','_'组成");
            ed_content_value.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
        } else if (title.equals("地址详情")) {
            tv_a.setVisibility(View.GONE);
            tv_a.setText("6-16个字符,可由中英文,数字,'-','_'组成");
        } else if (title.equals("登录账号")) {
            tv_a.setVisibility(View.GONE);
            tv_a.setText("登录账号不能为纯数字，不能包含特殊字符");
        } else if (title.equals("传真号")) {
            tv_a.setVisibility(View.GONE);
            ed_content_value.setInputType(InputType.TYPE_CLASS_NUMBER);
        } else {
            tv_a.setVisibility(View.GONE);
            tv_a.setText("6-16个字符,可由中英文,数字,'-','_'组成");
        }

//        保存按钮
        bt_save_register_value.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String regEx = "[`~!@#$%^&*()_\\-+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？\\s]";
                Pattern p = Pattern.compile(regEx);
                Matcher matcher = p.matcher(ed_content_value.getText().toString());
//                需要进行网络请求
                if (title.equals("登录账号")) {
                    type = "login";
                } else if (title.equals("邮箱")) {
                    type = "mail";
                }
                LogUtils.log("ed_content_value------->" + ed_content_value.getText().toString() + "<---");
                if (ed_content_value.getText().toString().trim() == null || ed_content_value.getText().toString().trim().isEmpty()) {
                    tv_a.setVisibility(View.VISIBLE);
                    tv_a.setText(title + "不能为空");
                    tv_a.setTextColor(Color.RED);
                    LogUtils.log("tv_a------->" + tv_a.getText().toString() + "<---");
                } /*else if (ed_content_value.getText().toString().length() < 6&&!title.equals("姓名")) {
                    tv_a.setText("6-16个字符,可由中英文,数字组成");
                    tv_a.setVisibility(View.VISIBLE);
                    tv_a.setTextColor(Color.RED);
                    LogUtils.log("监测字符长度--->！姓名");
                } */else if (ed_content_value.getText().toString().length() < 2&&title.equals("姓名")) {
                    tv_a.setText("姓名不符合规则");
                    tv_a.setVisibility(View.VISIBLE);
                    tv_a.setTextColor(Color.RED);
                    LogUtils.log("监测字符长度--->姓名");
                } else if (title.equals("登录账号") && ed_content_value.getText().toString().matches("^[0-9]*$")) {
                    tv_a.setText("登录账号不能为纯数字，不能包含特殊字符");
                    tv_a.setTextColor(Color.RED);
                    tv_a.setVisibility(View.VISIBLE);
                } else if (title.equals("登录账号") && matcher.find()) {
                    tv_a.setText("登录账号不能为纯数字，不能包含特殊字符");
                    tv_a.setTextColor(Color.RED);
                    tv_a.setVisibility(View.VISIBLE);
                } else if (type == "login" || type == "mail") {
                    LogUtils.log("邮箱或用户名的校验--->");
                    toDetermineWhetherOrNotToRegister(type, ed_content_value.getText().toString().trim());
                } else if (title.equals("登录密码")||title.equals("确认密码")){
                    if (ed_content_value.getText().length()<8||ed_content_value.getText().length()>16){
                        Toast.makeText(RegisterValueActivity.this, "密码的长度是8-16位字符", Toast.LENGTH_SHORT).show();
                    }else {
                        setResultW();
                    }


                } else {
                    LogUtils.log("走这里---->");
                    setResultW();
                }

            }
        });

    }

    private void toDetermineWhetherOrNotToRegister(String type, String attr) {
        RequestParams rp = new RequestParams(url);
        rp.setCharset("utf-8");
        rp.setAsJsonContent(true);
//        {"type":"login","attr":"本宝宝在此"}
        JSONObject json = new JSONObject();
        try {
            json.put("type", type);
            json.put("attr", attr.trim());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        rp.setBodyContent(json.toString());
        LogUtils.log(json.toString());
        x.http().request(HttpMethod.POST, rp, new Callback.CacheCallback<String>() {
            @Override
            public boolean onCache(String result) {
                return false;
            }

            @Override
            public void onSuccess(String result) {
                LogUtils.log("onSuccess------->" + result + "<---onSuccess");
                if (result.equals("true")) {
                    setResultW();
                } else {
//                    判断返回值，如果为false，代表不能注册，显示因为那个类型已被注册，不能进行下一步
//                    tv_a.setText("该" + title + "已被注册");
//                    tv_a.setTextColor(Color.RED);
                    Toast.makeText(RegisterValueActivity.this, "该" + title + "已被注册", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    public void title_Bar(String string) {

        iv_title_bar_back = ((ImageView) findViewById(R.id.iv_title_bar_back));
        iv_title_bar_logo = ((ImageView) findViewById(R.id.iv_title_bar_logo));
        iv_title_bar_service = ((ImageView) findViewById(R.id.iv_title_bar_service));
        iv_title_bar_search = ((ImageView) findViewById(R.id.iv_title_bar_search));
        sv_title_bar_serachView = ((Button) findViewById(R.id.sv_title_bar_serachView));
        tv_title_bar_title = ((TextView) findViewById(R.id.tv_title_bar_title));

        tvUpdate = (TextView) findViewById(R.id.tvUpdate);
        iv_title_bar_logo.setVisibility(View.GONE);
        iv_title_bar_service.setVisibility(View.GONE);
        sv_title_bar_serachView.setVisibility(View.GONE);
        iv_title_bar_search.setVisibility(View.GONE);
        iv_title_bar_back.setVisibility(View.GONE);
        tvUpdate.setVisibility(View.GONE);
        tv_title_bar_title.setText(string);


        tv_title_bar_title.setTextColor(Color.WHITE);
        iv_title_bar_back.setVisibility(View.GONE);
        layoutTop = (RelativeLayout) findViewById(R.id.layoutTop_register_value);
        layoutTop.setBackgroundColor(getResources().getColor(R.color.textColor_expandable_listview_show));


    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            intent.putExtra("name", "");
            RegisterValueActivity.this.setResult(RESULT_OK, intent);
        }
        return super.onKeyDown(keyCode, event);
    }

    public void setResultW() {
        /*if (ed_content_value.getText().toString().trim() == null|| ed_content_value.getText().toString().trim().isEmpty()) {
            tv_a.setText( title + "不能为空");
            tv_a.setTextColor(Color.RED);
            tv_a.setVisibility(View.VISIBLE);
            LogUtils.log("tv_a------->"+tv_a.getText().toString()+"<---");
        }else{*/
        intent.putExtra("name", ed_content_value.getText().toString().trim());
        LogUtils.log("ed_content_value------->setResultW:" + ed_content_value.getText().toString() + "<---");
        RegisterValueActivity.this.setResult(RESULT_OK, intent);
        this.finish();
       /* }*/


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

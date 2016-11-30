package com.silianchuangye.sumao.success.fragments.myPlasticTrade.personalInformation;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.utils.SuMaoConstant;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;


/**
 * Created by Administrator on 2016/5/9 0009.
 */
public class ModifyName extends Activity implements View.OnClickListener {
    ImageView iv_title_bar_logo,
            iv_title_bar_back,
            iv_title_bar_service,
            iv_title_bar_search;
    Button sv_title_bar_serachView, modify_name_save;
    TextView tv_title_bar_title;
    RelativeLayout modify_title;
    String classname;
    String receivingInformation;
    Boolean canBeAmpty;
    EditText modify_information;
    TextView prompt_information;
    String message;
    private Intent intent;
    String email, phoneNum, name, i;
    private SharedPreferences sp;
    private boolean isInvoiceInformation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.modify_name);

        modify_title = ((RelativeLayout) findViewById(R.id.modify_title));
        modify_title.setBackgroundColor(getResources().getColor(R.color.textColor_expandable_listview_show));

        iv_title_bar_back = ((ImageView) findViewById(R.id.iv_title_bar_back));
        iv_title_bar_logo = ((ImageView) findViewById(R.id.iv_title_bar_logo));
        iv_title_bar_service = ((ImageView) findViewById(R.id.iv_title_bar_service));
        iv_title_bar_search = ((ImageView) findViewById(R.id.iv_title_bar_search));
        sv_title_bar_serachView = ((Button) findViewById(R.id.sv_title_bar_serachView));
        tv_title_bar_title = ((TextView) findViewById(R.id.tv_title_bar_title));

        iv_title_bar_logo.setVisibility(View.INVISIBLE);
        iv_title_bar_service.setVisibility(View.INVISIBLE);
        sv_title_bar_serachView.setVisibility(View.INVISIBLE);
        iv_title_bar_search.setVisibility(View.INVISIBLE);
        iv_title_bar_back.setOnClickListener(this);

        Intent intents = getIntent();
        Bundle bundle = intents.getExtras();
        receivingInformation = bundle.getString("receivingInformation");
        if (receivingInformation == null) {
            isInvoiceInformation = false;
        } else {
            isInvoiceInformation = true;
        }
        email = bundle.getString("email");
        phoneNum = bundle.getString("phoneNum");
        name = bundle.getString("name");
        i = bundle.getString("i");
        canBeAmpty = bundle.getBoolean("canBeAmpty");
        message = bundle.getString("message");
        tv_title_bar_title.setText(receivingInformation);
        tv_title_bar_title.setTextColor(Color.WHITE);

        modify_name_save = ((Button) findViewById(R.id.modify_name_save));
        modify_information = ((EditText) findViewById(R.id.modify_information));
        prompt_information = ((TextView) findViewById(R.id.prompt_information));
        intent = new Intent();
        checkNamingSpecifications();
    }

    private void checkNamingSpecifications() {

        modify_information.addTextChangedListener(new SearchWather(modify_information));

    }

    //        新建类 SearchWather.
    class SearchWather implements TextWatcher {
        private EditText pedt;

        public SearchWather(EditText edt) {
            this.pedt = edt;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String editable = pedt.getText().toString();
            String str = stringFilter(editable.toString());
            if (!editable.equals(str)) {
                pedt.setText(str);
                pedt.setSelection(str.length());
            }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }

        public String stringFilter(String str) throws PatternSyntaxException {
            // 只允许字母和数字
            String regEx = "";
            if (receivingInformation.equals("修改税号")) {
                regEx = "[^a-zA-Z0-9]";
            } else if (receivingInformation.equals("修改地址")) {
                regEx = "[.]";
            } else if (receivingInformation.equals("修改电话")) {
                regEx = "[^(\\d)]";
            } else if (receivingInformation.equals("修改开户行")) {
                regEx = "[^\\u4e00-\\u9fa5]";
            } else if (receivingInformation.equals("修改账号")) {
                regEx = "[^\\d]";
            } else if (receivingInformation.equals("修改姓名")) {
                regEx = "[^\\u4e00-\\u9fa5a-zA-Z]";
            } else if (receivingInformation.equals("修改固定电话")) {
                regEx = "[^\\d]";
            } else if (receivingInformation.equals("修改手机")) {
                regEx = "[^\\d]";
            } else if (receivingInformation.equals("修改邮编")) {
                regEx = "[\\u4e00-\\u9fa5]";
            }


            Pattern p = Pattern.compile(regEx);
            Matcher m = p.matcher(str);
            return m.replaceAll("").trim();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_title_bar_back:
                finish();
                break;
            case R.id.modify_name_save:
                /**
                 * 接口，修改信息
                 */
                if (!isInvoiceInformation) {

                    canCloseThisActivity();
                } else {
//      判断是否从 involiceInformation （开票信息）中跳转过来
                    involiceInformationModify();
                }

                break;

        }
    }

    private void involiceInformationModify() {
        if (modify_information.getText().toString().equals("")) {
            if (canBeAmpty) {
                if (receivingInformation.equals("修改固定电话")) {
                    intent.putExtra(SuMaoConstant.MODIFY_INFORMATION, "");
                    ModifyName.this.setResult(1, intent);
                }else if (receivingInformation.equals("修改邮编")){
                    intent.putExtra(SuMaoConstant.MODIFY_INFORMATION, modify_information.getText().toString());
                    ModifyName.this.setResult(2, intent);
                }
                finish();
            } else {
                prompt_information.setText("*请输入" + message);
                prompt_information.setVisibility(View.VISIBLE);
            }

        } else {

            if (receivingInformation.equals("修改固定电话")||receivingInformation.equals("修改手机")) {
                String regex = "^(0\\d{2,3}-\\d{7,8}(-\\d{3,5}){0,1})|(((13[0-9])|(15([0-3]|[5-9]))|(18[0,5-9]))\\d{8})$";
                Pattern pattern = Pattern.compile(regex);
                Matcher matcher = pattern.matcher(modify_information.getText().toString());
                boolean matches = matcher.matches();
                if (!matches){
                    Toast.makeText(ModifyName.this,"您输入的格式不符合规则，请检查后重新输入",Toast.LENGTH_SHORT).show();
                }else {
                    intent.putExtra(SuMaoConstant.MODIFY_INFORMATION, modify_information.getText().toString());
                    ModifyName.this.setResult(RESULT_OK, intent);
                    finish();
                }
            }else if (receivingInformation.equals("修改邮编")) {
                String regex = "^[a-zA-Z0-9_]{6,12}@[a-zA-Z0-9]+(\\.[a-zA-Z]+){1,3}$";
                if (!Pattern.matches(regex,modify_information.getText().toString())){
                    Toast.makeText(ModifyName.this,"您输入的邮箱格式不符合规则，请检查后重新输入",Toast.LENGTH_SHORT).show();
                }else {
                    intent.putExtra(SuMaoConstant.MODIFY_INFORMATION, modify_information.getText().toString());
                    ModifyName.this.setResult(RESULT_OK, intent);
                    finish();
                }
            }else {
                intent.putExtra(SuMaoConstant.MODIFY_INFORMATION, modify_information.getText().toString());
                ModifyName.this.setResult(RESULT_OK, intent);
                finish();
            }

        }
    }

    public void canCloseThisActivity() {
        if (modify_information.getText().toString().equals("")) {
            if (canBeAmpty) {
                intent.putExtra(SuMaoConstant.MODIFY_INFORMATION, "");
                ModifyName.this.setResult(RESULT_OK, intent);
                finish();
            } else {
                prompt_information.setText("*请输入" + message);
                prompt_information.setVisibility(View.VISIBLE);
            }

        } else {
            Log.e("TAG", "修改用户名");
            RequestParams params = new RequestParams(SuMaoConstant.SUMAO_IP + "/rest/model/atg/store/profile/RegistrationActor/updateUser");
            if (receivingInformation.equals("修改姓名")) {
                params.setCharset("UTF-8");
                JSONObject job = new JSONObject();
                try {
                    job.put("firstName", modify_information.getText().toString().trim());
                    job.put("email", email.trim());
                    job.put("phoneNumber", phoneNum.trim());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                params.setBodyContent(job.toString());
            } else if (receivingInformation.equals("修改邮箱")) {
                params.setCharset("UTF-8");
                JSONObject job1 = new JSONObject();
                try {
                    job1.put("firstName", name);
                    job1.put("email", modify_information.getText().toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                params.setBodyContent(job1.toString());
                params.addParameter("phoneNumber", phoneNum);
            }
            sp = getSharedPreferences("sumao", Activity.MODE_PRIVATE);
            String unique = sp.getString("unique", "");
            params.addParameter("_dynSessConf", unique);
            Log.e("TAG", "parames------" + params);
            x.http().post(params, new Callback.CommonCallback<String>() {
                @Override
                public void onSuccess(String result) {
                    if (!result.contains("changeResult")) {
                        if (receivingInformation.equals("修改姓名")) {
                            Toast.makeText(ModifyName.this, "您输入的用户名不符合规则", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(ModifyName.this, "您输入的邮箱不符合规则", Toast.LENGTH_SHORT).show();
                        }
                    }
                    try {
                        JSONObject job = new JSONObject(result);
                        String changeResult = job.getString("changeResult");

                        if (changeResult.equals("YES")) {
                            Toast.makeText(ModifyName.this, "修改成功", Toast.LENGTH_SHORT).show();
                            //Jobs Created 首页姓名的刷新
                            SharedPreferences.Editor edit = sp.edit();
                            edit.putString("name", modify_information.getText().toString().trim());
                            edit.commit();

                            intent.putExtra(SuMaoConstant.MODIFY_INFORMATION, modify_information.getText().toString().trim());
                            Log.e("TAG", "RESULT_OK====" + RESULT_OK);
                            setResult(RESULT_OK, intent);
                            finish();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }

                @Override
                public void onError(Throwable ex, boolean isOnCallback) {
                    Log.e("TAG", "ex---" + ex);
                }

                @Override
                public void onCancelled(CancelledException cex) {

                }

                @Override
                public void onFinished() {

                }
            });
            prompt_information.setVisibility(View.INVISIBLE);
            /**
             * 在这里吧EditText的文本信息获取到，调用接口传到服务器上
             */


        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            intent.putExtra(SuMaoConstant.MODIFY_INFORMATION, "");
            ModifyName.this.setResult(RESULT_OK, intent);
        }
        return super.onKeyDown(keyCode, event);
    }
}

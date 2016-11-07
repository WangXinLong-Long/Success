package com.silianchuangye.sumao.success.fragments.myPlasticTrade.login;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.silianchuangye.sumao.success.MainActivity;
import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.fragments.SellerManagementPlatform.SellerManagementPlatformActivity;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.register.RegisterPhoneMVP.RegisterPhoneView.RegisterPhoneActivity;
import com.silianchuangye.sumao.success.utils.GlobalVariable;
import com.silianchuangye.sumao.success.utils.Loding;
import com.silianchuangye.sumao.success.utils.LogUtils;
import com.silianchuangye.sumao.success.utils.SuMaoConstant;

import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

public class LoginUserActivity extends AppCompatActivity {
    ImageView iv_title_bar_logo,
            iv_title_bar_back,
            iv_title_bar_service ,
            add_address,
            iv_title_bar_search;
    Button sv_title_bar_serachView;
    TextView tv_title_bar_title, tv, tvUpdate;
    RelativeLayout layoutTop, add_address_rl;
    private EditText et_account_login;
    private EditText et_pass_login;
    private Button bt_Login;
    private TextView tv_register, tv_findpass;
    public static SQLiteDatabase db;
    private String name;
    private String password;
    private String user_Name;
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;
    private String roles;

    int str;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_for_mai_mai);
        title_Bar();
        db = SQLiteDatabase.openOrCreateDatabase(this.getFilesDir().toString() + "/test.dbs", null);
        et_account_login = (EditText) findViewById(R.id.et_account_login);
        et_pass_login = (EditText) findViewById(R.id.et_pass_login);
        bt_Login = (Button) findViewById(R.id.bt_login);
        Intent intent = getIntent();
        roles = intent.getStringExtra("roles");
        LogUtils.log("roles-->" + roles + "<--roles");
//        登录账号按钮
        bt_Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (roles.equals("seller")) {
                    //卖家登录
                    sellerlogin();
                } else if (roles.equals("buyer")) {
                    //买家登录功能
                    buyerlogin();
                }


            }
        });
        tv_register = (TextView) findViewById(R.id.tv_register_login);
        tv_findpass = (TextView) findViewById(R.id.tv_findpass_login);
        tv_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginUserActivity.this, RegisterPhoneActivity.class);
                startActivity(intent);
            }
        });
        tv_findpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转找回密码界面
//                Log.d("跳进了","买卖方交换界面");
//                Intent intent=new Intent(LoginUserActivity.this,LoginActivity.class);
//                startActivity(intent);
            }
        });
        str = getIntent().getIntExtra("cart1", 0);
    }

    public void title_Bar() {

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

        tvUpdate.setVisibility(View.GONE);
        tv_title_bar_title.setText("登录账号");


        tv_title_bar_title.setTextColor(Color.WHITE);
        iv_title_bar_back.setVisibility(View.VISIBLE);
        layoutTop = (RelativeLayout) findViewById(R.id.layoutTop_Login);
        layoutTop.setBackgroundColor(getResources().getColor(R.color.textColor_expandable_listview_show));

        iv_title_bar_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginUserActivity.this.finish();
            }
        });

    }

    private void sellerlogin() {
        Intent intent = new Intent(this, SellerManagementPlatformActivity.class);
        startActivity(intent);
    }

    private void buyerlogin() {
        Loding.show( this,"登录中...",false,null);
        sp = getSharedPreferences("sumao", Activity.MODE_PRIVATE);
        editor = sp.edit();
        name = et_account_login.getText().toString().trim();
        password = et_pass_login.getText().toString().trim();

        RequestParams rp = new RequestParams(SuMaoConstant.SUMAO_IP + "/rest/model/atg/userprofiling/ProfileActor/login");
        rp.addParameter("login", name.trim());
        rp.addParameter("password", password.trim());
        x.http().post(rp, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                LogUtils.log("登录返回的值：----->" + result);
                if (result.contains("formError")) {
                    Toast.makeText(LoginUserActivity.this, "账号或密码错误请重新登录！", Toast.LENGTH_SHORT).show();
                } else {
//                    Log.d("object",result);
                    sp = getSharedPreferences("sumao", Activity.MODE_PRIVATE);
                    editor=sp.edit();
                    Log.d("账号的值",name.trim());
                    editor.putString("zhanghao",name.trim());
                    editor.commit();
                    String unique = sp.getString("unique", "");
                    Log.d("唯一标识", unique);
                    if (unique.equals("") || unique == null||unique.equals("false")) {
                        sp = getSharedPreferences("sumao", Activity.MODE_PRIVATE);
                        editor = sp.edit();
                        RequestParams unique_rp = new RequestParams(SuMaoConstant.SUMAO_IP + "/rest/model/atg/rest/SessionConfirmationActor/getSessionConfirmationNumber");
                        x.http().post(unique_rp, new CommonCallback<String>() {
                            @Override
                            public void onSuccess(String result) {
                                try {
                                    JSONObject object = new JSONObject(result);
                                    String unique = object.getString("sessionConfirmationNumber");
                                    /**
                                     * 把唯一标识储存在SharedPreferences
                                     */

                                    editor.putString("unique", unique);

                                    editor.commit();
                                    Log.d("唯一标识----------->", unique);
                                } catch (Exception e) {
                                    Log.d("exception", "解析唯一标识时错误！");
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
                }
                try {
                    JSONObject object = new JSONObject(result);
                    String name = object.getString("U_name");
                    /**
                     * 把姓名储存在SharedPreferences
                     */
                    editor.putString("name", name);
                    editor.commit();


                } catch (Exception e) {
                    Log.d("exception", "解析异常");
                }

                Loding.dis();
                    //接收mainactivity传递过来的参数
                    if (str == 9) {
                        Intent intent = new Intent();
                        intent.putExtra("cart", 4);
                        intent.setClass(LoginUserActivity.this, MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                    } else {
                        Intent intent = new Intent();
                        intent.setClass(LoginUserActivity.this, MainActivity.class);
                        intent.putExtra("cart", 3);
                        intent.putExtra("name", name);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                    }
                    GlobalVariable.FLAG = true;
                    LoginUserActivity.this.finish();


            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                LogUtils.log("LoginUserActivity-->onError--->" + ex);
                Toast.makeText(LoginUserActivity.this, "请检查网络设置！", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {
                Loding.dis();
            }
        });

    }


}

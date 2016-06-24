package com.silianchuangye.sumao.success.fragments.myPlasticTrade.login;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
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
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.register.RegisterActivity;
import com.silianchuangye.sumao.success.utils.GlobalVariable;

import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

public class LoginUserActivity extends AppCompatActivity {
    ImageView iv_title_bar_logo,
            iv_title_bar_back,
            iv_title_bar_service,
            add_address,
            iv_title_bar_search;
    Button sv_title_bar_serachView;
    TextView tv_title_bar_title,tv,tvUpdate;
    RelativeLayout layoutTop,add_address_rl;
    private EditText et_account_login;
    private EditText et_pass_login;
    private Button bt_Login;
    private TextView tv_register,tv_findpass;
    public static  SQLiteDatabase db;
    private String name;
    private String password;
    private String user_Name;
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_user);

        title_Bar();
        db = SQLiteDatabase.openOrCreateDatabase(this.getFilesDir().toString()+"/test.dbs",null);
        et_account_login= (EditText) findViewById(R.id.et_account_login);
        et_pass_login= (EditText) findViewById(R.id.et_pass_login);
        bt_Login= (Button) findViewById(R.id.bt_login);
        bt_Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //登录功能
                login();
            }
        });
        tv_register= (TextView) findViewById(R.id.tv_register_login);
        tv_findpass= (TextView) findViewById(R.id.tv_findpass_login);
        tv_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LoginUserActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
        tv_findpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //跳转找回密码界面
            }
        });
    }



    public void title_Bar(){

        iv_title_bar_back = ((ImageView) findViewById(R.id.iv_title_bar_back));
        iv_title_bar_logo = ((ImageView) findViewById(R.id.iv_title_bar_logo));
        iv_title_bar_service = ((ImageView) findViewById(R.id.iv_title_bar_service));
        iv_title_bar_search = ((ImageView) findViewById(R.id.iv_title_bar_search));
        sv_title_bar_serachView = ((Button) findViewById(R.id.sv_title_bar_serachView));
        tv_title_bar_title  = ((TextView) findViewById(R.id.tv_title_bar_title));

        tvUpdate= (TextView) findViewById(R.id.tvUpdate);
        iv_title_bar_logo.setVisibility(View.GONE);
        iv_title_bar_service.setVisibility(View.GONE);
        sv_title_bar_serachView.setVisibility(View.GONE);
        iv_title_bar_search.setVisibility(View.GONE);

        tvUpdate.setVisibility(View.GONE);
        tv_title_bar_title.setText("登录账号");


        tv_title_bar_title.setTextColor(Color.WHITE);
        iv_title_bar_back.setVisibility(View.VISIBLE);
        layoutTop= (RelativeLayout) findViewById(R.id.layoutTop_Login);
        layoutTop.setBackgroundColor(getResources().getColor(R.color.textColor_expandable_listview_show));

        iv_title_bar_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginUserActivity.this.finish();
            }
        });

    }
    private void login() {
        sp=getSharedPreferences("sumao", Activity.MODE_PRIVATE);
        editor=sp.edit();
         name = et_account_login.getText().toString();
         password = et_pass_login.getText().toString();
//        if (name.equals("")||password.equals(""))
//        {
//            new AlertDialog.Builder(LoginUserActivity.this).setTitle("失败").setMessage("用户名或者密码为空").setPositiveButton("确定",null).show();
//        }else {
//            isUser(name,password);
//        }
        RequestParams rp=new RequestParams("http://192.168.32.126:7023/rest/model/atg/userprofiling/ProfileActor/login");
        rp.addParameter("login",name);
        rp.addParameter("password",password);
        x.http().post(rp, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                if (result.contains("formError")){
                    Toast.makeText(LoginUserActivity.this, "账号或密码错误请重新登录！", Toast.LENGTH_SHORT).show();
                }else{
//                    Log.d("object",result);
                    try{
                    JSONObject object=new JSONObject(result);
                        user_Name=object.getString("U_name");
//                        Log.d("name",""+name);1233
                        editor.putString("name",user_Name);
                        editor.commit();



                    }catch (Exception e){
                        Log.d("exception","解析异常");
                    }
                    RequestParams unique_rp=new RequestParams("http://192.168.32.126:7023/rest/model/atg/rest/SessionConfirmationActor/getSessionConfirmationNumber");
                    x.http().post(unique_rp, new CommonCallback<String>() {
                        @Override
                        public void onSuccess(String result) {
                            try{
                            JSONObject object=new JSONObject(result);
                                String unique=object.getString("sessionConfirmationNumber");
                                Log.d("unique","unique"+unique);
                                /**
                                 * 把唯一标识储存在SharedPreferences
                                 */

                                editor.putString("unique",unique);
                                editor.commit();
                            }catch (Exception e){
                                Log.d("exception","解析唯一标识时错误！");
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

                    Intent intent = new Intent();
                    intent.setClass(LoginUserActivity.this, MainActivity.class);
                    intent.putExtra("cart",3);
//                    intent.putExtra("name",user_Name);
                    startActivity(intent);
                    GlobalVariable.FLAG = true;


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

    private Boolean isUser(String name,String password) {
        try {
            String str = "select * from tb_silian where name=? and password=?";
            Cursor cursor = db.rawQuery(str,new String[]{name,password});
            if (cursor.getCount()<=0)
            {
                new AlertDialog.Builder(LoginUserActivity.this).setTitle("失败").setMessage("用户名或者密码错误").setPositiveButton("确定",null).show();
            }else {
                Toast.makeText(LoginUserActivity.this,"登陆成功",Toast.LENGTH_SHORT).show();

            }
        } catch (Exception e) {
            createDb();
        }
        return false;
    }

    public  void createDb() {
        db.execSQL("create table tb_silian (name varchar(30) primary key,password varchar(30))");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        db.close();
    }
}

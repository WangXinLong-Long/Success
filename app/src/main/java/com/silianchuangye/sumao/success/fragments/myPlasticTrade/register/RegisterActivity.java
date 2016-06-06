package com.silianchuangye.sumao.success.fragments.myPlasticTrade.register;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.firmInfomation.FirmInfoUpdateActivity;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.login.LoginUserActivity;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {
    private ListView lv_register;

    ImageView iv_title_bar_logo,
            iv_title_bar_back,
            iv_title_bar_service,
            add_address,
            iv_title_bar_search;
    Button sv_title_bar_serachView;
    TextView tv_title_bar_title,tv,tvUpdate;
    RelativeLayout layoutTop,add_address_rl;
    private List<Map<String,Object>> list;
    private EditText et_phone_register,editText;
    private Button bt_get_register,tv_next_register;
    SQLiteDatabase db;
    String name;
    String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        //电话号码
        et_phone_register= (EditText) findViewById(R.id.et_phone_register);
        //手机验证码
        editText= (EditText) findViewById(R.id.et_phone);
        //获取验证码按钮
        bt_get_register= (Button) findViewById(R.id.bt_get_register);
        tv_next_register= (Button) findViewById(R.id.tv_next_register);
        tv_next_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register();

                Intent intent=new Intent(RegisterActivity.this, FirmInfoUpdateActivity.class);
                intent.putExtra("add","新建");
                startActivity(intent);

            }
        });
        title_Bar();
        lv_register= (ListView) findViewById(R.id.lv_register);
        list=new ArrayList<Map<String,Object>>();
        Map<String,Object> map1=new Hashtable<String,Object>();
        map1.put("text","登录账号");
        map1.put("minute","6-16个字符");
        map1.put("icon",R.mipmap.my_sumao_iv_order);
        list.add(map1);
        Map<String,Object> map2=new Hashtable<String,Object>();
        map2.put("text","登录密码");
        map2.put("minute","6-16个字符");
        map2.put("icon",R.mipmap.my_sumao_iv_order);
        list.add(map2);
        Map<String,Object> map3=new Hashtable<String,Object>();
        map3.put("text","确认密码");
        map3.put("minute","6-16个字符");
        map3.put("icon",R.mipmap.my_sumao_iv_order);
        list.add(map3);
        Map<String,Object> map4=new Hashtable<String,Object>();
        map4.put("text","姓名");
        map4.put("minute","");
        map4.put("icon",R.mipmap.my_sumao_iv_order);
        list.add(map4);
        Map<String,Object> map5=new Hashtable<String,Object>();
        map5.put("text","邮箱");
        map5.put("minute","");
        map5.put("icon",R.mipmap.my_sumao_iv_order);
        list.add(map5);
        SimpleAdapter adapter=new SimpleAdapter(this,list,R.layout.item_firm_info,new String[]{"text","minute","icon"},new int[]{R.id.tv_firm_info,R.id.tvValue_firm_info,R.id.ivMore_firm_info});
        lv_register.setAdapter(adapter);
        lv_register.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(RegisterActivity.this,RegisterValueActivity.class);
                intent.putExtra("title",list.get(position).get("text").toString());
                intent.putExtra("content",list.get(position).get("minute").toString());
                startActivityForResult(intent,position);
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
        layoutTop= (RelativeLayout) findViewById(R.id.layoutTop_register);
        layoutTop.setBackgroundColor(getResources().getColor(R.color.textColor_expandable_listview_show));

        iv_title_bar_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RegisterActivity.this.finish();
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode)
        {
            case 0:
               name  = data.getStringExtra("name");
                break;
            case 1:
                password = data.getStringExtra("name");
                break;
        }
    }
    private void register() {
        if (!(name.equals("")||password.equals("")))
        {
            if (addUser(name,password)){
                DialogInterface.OnClickListener ss = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent  = new Intent();
                        intent.setClass(RegisterActivity.this, LoginUserActivity.class);
                        startActivity(intent);
                        RegisterActivity.this.onDestroy();
                    }
                };
                new AlertDialog.Builder(RegisterActivity.this).setTitle("注册成功").setMessage("保存成功").setPositiveButton("确定",ss).show();
            }else {
                new AlertDialog.Builder(RegisterActivity.this).setTitle("注册失败").setMessage("保存失败").setPositiveButton("确定",null).show();

            }
        }else {
            new AlertDialog.Builder(RegisterActivity.this).setTitle("失败").setMessage("用户名或密码为空").setPositiveButton("确定",null).show();

        }
    }

    private Boolean addUser(String name,String password)
    {
        String str = "insert into tb_silian values(?,?)";
        db = SQLiteDatabase.openOrCreateDatabase(RegisterActivity.this.getFilesDir().toString()+"/test.dbs",null);
        LoginUserActivity loginuserActivity = new LoginUserActivity();
        loginuserActivity.db = db;
        try {
            db.execSQL(str,new String[]{name,password});
            return true;
        } catch (SQLException e) {
            loginuserActivity.createDb();
        }
        return false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        db.close();
    }
}

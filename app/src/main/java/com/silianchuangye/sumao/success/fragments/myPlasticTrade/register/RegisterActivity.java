package com.silianchuangye.sumao.success.fragments.myPlasticTrade.register;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.firmInfomation.FirmInfoUpdateActivity;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.login.LoginUserActivity;
import com.silianchuangye.sumao.success.utils.LogUtils;

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
    TextView tv_title_bar_title, tv, tvUpdate;
    RelativeLayout layoutTop, add_address_rl;
    private List<Map<String, Object>> list;
    private EditText et_phone_register, editText;
    private Button bt_get_register, tv_next_register;
    SQLiteDatabase db;
    String account = "";
    String name = "";
    String password = "";
    String rePassword = "";
    String email = "";
    private SimpleAdapter adapter;
    //    如果在修改页面中写了文字，并保存，设置为true,即使写了“6-16个字符”字样
    private boolean sixToSixteenCharacter = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
//        //电话号码
//       et_phone_register= (EditText) findViewById(R.id.et_phone_register);
//        //手机验证码
//        editText= (EditText) findViewById(R.id.et_phone);
//        //获取验证码按钮
//        bt_get_register= (Button) findViewById(R.id.bt_get_register);
        Bundle bundle = getIntent().getExtras();
        final String phone = bundle.getString("phone");
        tv_next_register = (Button) findViewById(R.id.tv_next_register);
        tv_next_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //  register();
                if (!(password.trim() .equals(rePassword.trim())) ) {
                    Toast.makeText(RegisterActivity.this, "两次输入密码不一致", Toast.LENGTH_SHORT).show();
                } else if (account.trim() == null || account.trim().isEmpty()) {
                    Toast.makeText(RegisterActivity.this, "登录账号为空", Toast.LENGTH_SHORT).show();
                } else if (password.trim() == null || password.trim().isEmpty()) {
                    Toast.makeText(RegisterActivity.this, "登录密码为空", Toast.LENGTH_SHORT).show();
                } else if (rePassword.trim() == null || rePassword.trim().isEmpty()) {
                    Toast.makeText(RegisterActivity.this, "确认密码为空", Toast.LENGTH_SHORT).show();
                } else if (name.trim() == null || name.trim().isEmpty()) {
                    Toast.makeText(RegisterActivity.this, "姓名为空", Toast.LENGTH_SHORT).show();
                } else if (email.trim() == null || email.trim().isEmpty()) {
                    Toast.makeText(RegisterActivity.this, "邮箱为空", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(RegisterActivity.this, RegisterFirmActivity.class);
                    intent.putExtra("account", password.trim());
                    intent.putExtra("pass",password.trim());
                    intent.putExtra("repass",rePassword.trim() );
                    intent.putExtra("name", name.trim() );
                    intent.putExtra("email", email.trim());
                    intent.putExtra("phone", phone);
                    startActivity(intent);
                }


            }
        });
        title_Bar();
        lv_register = (ListView) findViewById(R.id.lv_register);

        list = new ArrayList<Map<String, Object>>();
        Map<String, Object> map1 = new Hashtable<String, Object>();
        map1.put("text", "登录账号");
        map1.put("minute", "6-16个字符");
        map1.put("icon", R.mipmap.my_sumao_iv_order);
        map1.put("pass", "");
        list.add(map1);
        Map<String, Object> map2 = new Hashtable<String, Object>();
        map2.put("text", "登录密码");
        map2.put("minute", "6-16个字符");
        map2.put("icon", R.mipmap.my_sumao_iv_order);
        map2.put("pass", "");
        list.add(map2);
        Map<String, Object> map3 = new Hashtable<String, Object>();
        map3.put("text", "确认密码");
        map3.put("minute", "6-16个字符");
        map3.put("icon", R.mipmap.my_sumao_iv_order);
        map3.put("pass", "");
        list.add(map3);
        Map<String, Object> map4 = new Hashtable<String, Object>();
        map4.put("text", "姓名");
        map4.put("minute", "");
        map4.put("icon", R.mipmap.my_sumao_iv_order);
        map4.put("pass", "");
        list.add(map4);
        Map<String, Object> map5 = new Hashtable<String, Object>();
        map5.put("text", "邮箱");
        map5.put("minute", "");
        map5.put("icon", R.mipmap.my_sumao_iv_order);
        map5.put("pass", "");
        list.add(map5);
        adapter = new SimpleAdapter(this, list, R.layout.item_firm_info,
                new String[]{"text", "minute", "icon", "pass"},
                new int[]{R.id.tv_firm_info, R.id.tvValue_firm_info, R.id.ivMore_firm_info, R.id.tvPass_firm_info});
        lv_register.setAdapter(adapter);
        lv_register.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(RegisterActivity.this, RegisterValueActivity.class);
                intent.putExtra("title", list.get(position).get("text").toString());
                intent.putExtra("content", list.get(position).get("minute").toString());
                intent.putExtra("pass", list.get(position).get("pass").toString());
                startActivityForResult(intent, position);
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Log.d("参数",requestCode+"");
        switch (requestCode) {

            case 0:

                account = data.getStringExtra("name");
                Log.d("zhanghao",account);
                if (!account.equals("")) {
                    list.get(0).put("minute", account);
                    sixToSixteenCharacter = true;
                } else {
                    sixToSixteenCharacter = false;
                }

                adapter.notifyDataSetChanged();

                break;
            case 1:
                password = data.getStringExtra("name");
                LogUtils.log("password:---------------->" + password);
                if (!password.equals("")) {
                    sixToSixteenCharacter = true;
                    list.get(1).put("pass", password);
                    list.get(1).put("minute", "");
                } else {
                    sixToSixteenCharacter = false;

                }

                adapter.notifyDataSetChanged();
                break;
            case 2:
                rePassword = data.getStringExtra("name");
                LogUtils.log("password:---------------->" + rePassword);
                if (!rePassword.equals("")) {
                    sixToSixteenCharacter = true;
                    list.get(2).put("pass", rePassword);
                    list.get(2).put("minute", "");
                } else {
                    sixToSixteenCharacter = false;
                }

                adapter.notifyDataSetChanged();
                break;
            case 3:
                name = data.getStringExtra("name");
                if (!name.equals("")) {
                    sixToSixteenCharacter = true;
                    list.get(3).put("minute", name);
                } else {
                    sixToSixteenCharacter = false;
                }

                adapter.notifyDataSetChanged();
                break;
            case 4:
                email = data.getStringExtra("name");
                if (!email.equals("")) {
                    sixToSixteenCharacter = true;
                    list.get(4).put("minute", email);
                } else {
                    sixToSixteenCharacter = false;
                }

                adapter.notifyDataSetChanged();
                break;

        }


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
        tv_title_bar_title.setText("注册账号");


        tv_title_bar_title.setTextColor(Color.WHITE);
        iv_title_bar_back.setVisibility(View.VISIBLE);
        layoutTop = (RelativeLayout) findViewById(R.id.layoutTop_register);
        layoutTop.setBackgroundColor(getResources().getColor(R.color.textColor_expandable_listview_show));

        iv_title_bar_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RegisterActivity.this.finish();
            }
        });

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        //    db.close();
    }
}

package com.silianchuangye.sumao.success.seller.fragmentone.account;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.utils.SuMaoConstant;

import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

public class AccountInfoActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView iv_back;
    private TextView tv_Title;
    private TextView save;
    private ListView lv_account_info;
    private List<Map<String,Object>> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_info);
        init();
        addData();
    }
    public void init(){
        iv_back= (ImageView) findViewById(R.id.iv_back);
        tv_Title= (TextView) findViewById(R.id.tv_Title);
        save= (TextView) findViewById(R.id.save);
        tv_Title.setText("账户信息管理");
        //save.setText("保存");

        iv_back.setOnClickListener(this);
        lv_account_info= (ListView) findViewById(R.id.lv_account_info);
        list=new ArrayList<>();
    }

    public void addData(){
        SharedPreferences sp=getSharedPreferences("sumao", Activity.MODE_PRIVATE);
        String seller_session=sp.getString("seller_session","");
        String url= SuMaoConstant.Seller_SUMAO_IP+"/smp/moble/shop/HttpSession";
        RequestParams rp=new RequestParams(url);
        rp.addParameter("sessionId",seller_session);
        x.http().post(rp, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                try {
                    JSONObject jsonObejct = new JSONObject(result);
                    String executionResult = jsonObejct.getString("executionResult");
                    if (executionResult.equals("fasle")) {
                        Toast.makeText(AccountInfoActivity.this, "请登录", Toast.LENGTH_SHORT).show();
                    } else {
                        String shopSessionInfo = jsonObejct.getString("shopSessionInfo");
                        JSONObject json_shopAccount = new JSONObject(shopSessionInfo);
                        String shopAccount = json_shopAccount.getString("shopAccount");
                        String shopAccountMenuResource = json_shopAccount.getString("shopAccountMenuResource");//权限
                        JSONObject json_shopAccount1 = new JSONObject(shopAccount);
                        String username = json_shopAccount1.getString("username");//登录名称
                        String name = json_shopAccount1.getString("name");//用户名称
                        String phone = json_shopAccount1.getString("phone");//手机
                        String email = json_shopAccount1.getString("email");//邮箱
                        String shopId = json_shopAccount1.getString("shopId"); //组织Id
                        String roles = json_shopAccount1.getString("roles");//角色
                        String password=json_shopAccount1.getString("password");//密码
                        String zhiwei=json_shopAccount1.getString("accountType");//职位
                        Log.d("用户信息", username + name + phone + email + shopId);
                        Log.d("用户的角色信息", roles);
                        Log.d("用户的权限信息", shopAccountMenuResource);
                        JSONObject json_jiaose=new JSONObject(roles);
                        String jiaose=json_jiaose.getString("name");
                        Map<String,Object> map1=new Hashtable<String, Object>();
                        map1.put("left","登录帐号");
                        map1.put("right",username);
                        map1.put("icon","");
                        map1.put("pass","");
                        Map<String,Object> map2=new Hashtable<String, Object>();
                        map2.put("left","登录密码");
                        map2.put("right","");
                        map2.put("icon",R.mipmap.my_sumao_iv_order);
                        map2.put("pass",password.substring(0,7));
                        list.add(map1);
                        list.add(map2);
                        Map<String,Object> map3=new Hashtable<String, Object>();
                        map3.put("left","姓名");
                        map3.put("right",name);
                        map3.put("icon","");
                        map3.put("pass","");
                        list.add(map3);
                        Map<String,Object> map4=new Hashtable<String, Object>();
                        map4.put("left","邮箱");
                        map4.put("right",email);
                        map4.put("icon","");
                        map4.put("pass","");
                        list.add(map4);
                        Map<String,Object> map5=new Hashtable<String, Object>();
                        map5.put("left","手机号码");
                        map5.put("right",phone);
                        map5.put("icon","");
                        map5.put("pass","");
                        list.add(map5);
                        Map<String,Object> map6=new Hashtable<String, Object>();
                        map6.put("left","角色");
                        map6.put("right",jiaose);
                        map6.put("icon","");
                        map6.put("pass","");
                        list.add(map6);
                        Map<String,Object> map7=new Hashtable<String, Object>();
                        map7.put("left","职位");
                        map7.put("right",zhiwei);
                        map7.put("icon","");
                        map7.put("pass","");
                        list.add(map7);
                        SimpleAdapter adapter=new SimpleAdapter(AccountInfoActivity.this,list,R.layout.item_firm_info
                             ,new String[]{"left","right","icon","pass"},
                                new int[]{R.id.tv_firm_info,R.id.tvValue_firm_info,R.id.ivMore_firm_info,R.id.tvPass_firm_info}
                        );
                        lv_account_info.setAdapter(adapter);
                        adapter.notifyDataSetChanged();


                    }
                }catch (Exception e){
                    e.printStackTrace();
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
        lv_account_info.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position==1){
                    Intent intent=new Intent(AccountInfoActivity.this,SellerUpdatePasssActivity.class);
                    startActivity(intent);

                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_back:
                finish();
                break;
        }
    }
}

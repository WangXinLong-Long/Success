package com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.EnterpriseUserManagement;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.silianchuangye.sumao.success.R;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

public class CompanyUserUpdateActivity extends AppCompatActivity {
    private ListView lvupdate;
    private List<Map<String,Object>> list;
    private Button btSave;
    private RelativeLayout layoutTop;
    ImageView iv_title_bar_logo,
            iv_title_bar_back,
            iv_title_bar_service,
            iv_title_bar_search;
    Button sv_title_bar_serachView;
    TextView tv_title_bar_title,tv;
    private String account,name,phone,jiaose,state,email,zhuangtai,role;
    private SimpleAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_user_update);
        init();
        iv_title_bar_back = ((ImageView) findViewById(R.id.iv_title_bar_back));
        iv_title_bar_logo = ((ImageView) findViewById(R.id.iv_title_bar_logo));
        iv_title_bar_service = ((ImageView) findViewById(R.id.iv_title_bar_service));
        iv_title_bar_search = ((ImageView) findViewById(R.id.iv_title_bar_search));
        sv_title_bar_serachView = ((Button) findViewById(R.id.sv_title_bar_serachView));
        tv_title_bar_title  = ((TextView) findViewById(R.id.tv_title_bar_title));

        iv_title_bar_logo.setVisibility(View.GONE);
        iv_title_bar_service.setVisibility(View.GONE);
        sv_title_bar_serachView.setVisibility(View.GONE);
        iv_title_bar_search.setVisibility(View.GONE);
        tv_title_bar_title.setText("企业用户管理");
        tv_title_bar_title.setTextColor(Color.WHITE);
        iv_title_bar_back.setVisibility(View.VISIBLE);
        layoutTop= (RelativeLayout) findViewById(R.id.layouttop_update);
        layoutTop.setBackgroundColor(getResources().getColor(R.color.textColor_expandable_listview_show));

        iv_title_bar_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CompanyUserUpdateActivity.this.finish();
            }
        });
    }
    public void init(){
        lvupdate= (ListView) findViewById(R.id.lvupdate_company_user);
        btSave= (Button) findViewById(R.id.btsave_company_user_update);
        layoutTop= (RelativeLayout) findViewById(R.id.layouttop_update);
        Bundle bundle=getIntent().getExtras();
        account=bundle.getString("id");
        name=bundle.getString("name");
        phone=bundle.getString("phone");
        jiaose=bundle.getString("jiaose");
        state=bundle.getString("state");
        email=bundle.getString("email");
        zhuangtai="";
        if (state.equals("31")){
            zhuangtai="有效";
        }else if (state.equals("32")){
            zhuangtai="无效";
        }
        role="";
        if (jiaose.contains("50")){
            role=role+" 企业管理员";
            Log.d("role的值",role);

        }
        if (jiaose.contains("52")){
            role=role+" 业务员";
            Log.d("role的值",role);

        }
        if (jiaose.contains("53")){
            role=role+" 财务员";
            Log.d("role的值",role);

            //map.put("role",listitem.get(i).get("role")+" 财务员");
        }

        list=new ArrayList<Map<String,Object>>();
        Map<String,Object> map1=new Hashtable<String,Object>();
        map1.put("text","登录账号");
        map1.put("value",account);
       // map1.put("ispass","");
        list.add(map1);

        Map<String,Object> map3=new Hashtable<String,Object>();
        map3.put("text","姓名");
        map3.put("value",name);
        //map3.put("ispass","");
        list.add(map3);
        Map<String,Object> map4=new Hashtable<String,Object>();
        map4.put("text","手机号码");
        map4.put("value",phone);
        //map4.put("ispass","");
        list.add(map4);
        Map<String,Object> map5=new Hashtable<String,Object>();
        map5.put("text","邮箱");
        map5.put("value",email);
       // map5.put("ispass","");
        list.add(map5);
        Map<String,Object> map6=new Hashtable<String,Object>();
        map6.put("text","职责");
        map6.put("value",jiaose);
        //map6.put("ispass","");
        list.add(map6);
        Map<String,Object> map7=new Hashtable<String,Object>();
        map7.put("text","状态");
        map7.put("value",state);
        //map7.put("ispass","");
        list.add(map7);
        adapter=new SimpleAdapter(CompanyUserUpdateActivity.this,list,R.layout.item_company_user_update,new String[]{"text","value","ispass"},new int[]{R.id.tvText,R.id.tvValue_company_user_update,R.id.tvValue_company_user_update_passWord});
        lvupdate.setAdapter(adapter);
        //Listview的子组件的点击事件///////////
        lvupdate.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position==0||position==1||position==2||position==3){
                    Intent intent=new Intent(CompanyUserUpdateActivity.this,CompanyUserValueActivity.class);
                    intent.putExtra("title",list.get(position).get("text").toString());
                    intent.putExtra("number",position);
                    startActivityForResult(intent,position);
                }else if (position==4){
                    Intent intent=new Intent(CompanyUserUpdateActivity.this,CompanyUserDutyActivity.class);
                    startActivityForResult(intent,4);
                }else if (position==5){
                    Intent intent=new Intent(CompanyUserUpdateActivity.this,CompanyUserStateActivity.class);
                    intent.putExtra("title","状态");
                    startActivityForResult(intent,100);
                }

            }
        });
        btSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(){
                    @Override
                    public void run() {

                        updateUser();
                    }
                }.start();
            }
        });




    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case 0:
                String account=data.getStringExtra("content");
                Log.d("account",account);
                if(account!=null) {
                    list.get(0).put("value", account);
                }
                adapter.notifyDataSetChanged();
                break;
            case 1:
                String name=data.getStringExtra("content");
                Log.d("account",name);
                if (name!=null) {
                    list.get(1).put("value", name);
                }
                adapter.notifyDataSetChanged();
                break;
            case 2:
                String phone=data.getStringExtra("content");
                Log.d("account",phone);
                if (phone!=null) {
                    list.get(2).put("value", phone);
                }
                adapter.notifyDataSetChanged();
                break;
            case 3:
                String email=data.getStringExtra("content");
                Log.d("account",email);
                if (email!=null) {
                    list.get(3).put("value", email);
                }
                adapter.notifyDataSetChanged();
                break;
            case 4:
                String people=data.getStringExtra("people");
                Log.d("account",people);
                if (people!=null) {
                    list.get(4).put("value", state);
                }
                adapter.notifyDataSetChanged();
                break;
            case 100:
                String state=data.getStringExtra("state");
                Log.d("account",state);
                if(state!=null) {
                    list.get(5).put("value", state);
                }
                adapter.notifyDataSetChanged();
                break;
        }
    }
    public void updateUser(){
        String url="http://192.168.32.126:7023/rest/model/atg/userprofiling/ProfileActor/modifyUser";
        RequestParams rp=new RequestParams(url);
        rp.addParameter("cl_yhid","52250065");
        rp.addParameter("cl_mingcheng",list.get(1).get("value").toString());
        rp.addParameter("cl_jiner",list.get(2).get("value").toString());
        rp.addParameter("cl_shuliang",list.get(3).get("value").toString());
        String role_id="";
        if (role.contains("企业管理员")){
            role_id=role_id+" 50";
        }
        if (role.contains("财务员")){
            role_id=role_id+" 53";
        }
        if (role.contains("业务员")){
            role_id=role_id+" 52";
        }
        rp.addParameter("cl_qiye","50");
        if (list.get(5).get("value").toString().equals("有效")){
            rp.addParameter("cl_cangku",31);
        }else{
            rp.addParameter("cl_cangku",32);
        }
        SharedPreferences sp=getSharedPreferences("sumao", Activity.MODE_PRIVATE);
        String unique=sp.getString("unique","");
        Log.d("unique",unique);
        rp.addParameter("_dynSessConf",unique);
        Log.d("rp的值",rp.toString());
        x.http().post(rp, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.d("result",result);
                if(result.contains("sucess")){
                    Toast.makeText(CompanyUserUpdateActivity.this, "您已修改成功", Toast.LENGTH_SHORT).show();
                    finish();
                }else{
                    Toast.makeText(CompanyUserUpdateActivity.this, "修改失败", Toast.LENGTH_SHORT).show();
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

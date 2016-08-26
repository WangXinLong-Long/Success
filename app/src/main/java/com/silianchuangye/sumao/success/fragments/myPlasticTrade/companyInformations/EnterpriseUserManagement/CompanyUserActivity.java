package com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.EnterpriseUserManagement;

import android.content.Intent;
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

import com.silianchuangye.sumao.success.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

public class CompanyUserActivity extends AppCompatActivity {
    private ListView elistview;
    private List<Map<String,Object>> listitem;
    ImageView iv_title_bar_logo,
            iv_title_bar_back,
            iv_title_bar_service,
            add,
            iv_title_bar_search;
    Button sv_title_bar_serachView;
    TextView tv_title_bar_title,tv;
    RelativeLayout layoutTop,add_address_rl;
    private String name,email,phone,jiaose,state,account;
    private String role;
    private String user_id;
    private SimpleAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_user);
        elistview= (ListView) findViewById(R.id.elvinfo_company_user);
        listitem=new ArrayList<Map<String,Object>>();
//        if (adapter!=null){
//            adapter.notifyDataSetChanged();
//        }
        iv_title_bar_back = ((ImageView) findViewById(R.id.iv_title_bar_back));
        iv_title_bar_logo = ((ImageView) findViewById(R.id.iv_title_bar_logo));
        iv_title_bar_service = ((ImageView) findViewById(R.id.iv_title_bar_service));
        iv_title_bar_search = ((ImageView) findViewById(R.id.iv_title_bar_search));
        sv_title_bar_serachView = ((Button) findViewById(R.id.sv_title_bar_serachView));
        tv_title_bar_title  = ((TextView) findViewById(R.id.tv_title_bar_title));
        add_address_rl= (RelativeLayout) findViewById(R.id.add_address_rl);
        tv= (TextView) findViewById(R.id.tvupdate);
        tv.setText("新增用户");
        add= (ImageView) findViewById(R.id.add_address);
        add_address_rl.setVisibility(View.VISIBLE);
        iv_title_bar_logo.setVisibility(View.GONE);
        iv_title_bar_service.setVisibility(View.GONE);
        sv_title_bar_serachView.setVisibility(View.GONE);
        iv_title_bar_search.setVisibility(View.GONE);

        iv_title_bar_back.setVisibility(View.VISIBLE);
        layoutTop= (RelativeLayout) findViewById(R.id.layoutTop);
        layoutTop.setBackgroundColor(getResources().getColor(R.color.textColor_expandable_listview_show));

        iv_title_bar_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CompanyUserActivity.this.finish();
            }
        });


       // iv_title_bar_search.setOnClickListener(this);
        tv_title_bar_title.setText("企业用户管理");
        tv_title_bar_title.setTextColor(Color.WHITE);
        add_address_rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(CompanyUserActivity.this,CompanyUserAddActivity.class);
                startActivity(intent);
            }
        });
        new Thread(){
            @Override
            public void run() {
                super.run();
                getAllUser();
            }
        }.start();
        /**
         * 修改用户
         */
        elistview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(CompanyUserActivity.this,CompanyUserUpdateActivity.class);
                intent.putExtra("name",listitem.get(position).get("name").toString());
                intent.putExtra("id",listitem.get(position).get("account").toString());
                intent.putExtra("email",listitem.get(position).get("email").toString());
                intent.putExtra("phone",listitem.get(position).get("phone").toString());
                intent.putExtra("jiaose",listitem.get(position).get("role").toString());
                intent.putExtra("state",listitem.get(position).get("state").toString());
                intent.putExtra("userid",user_id);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
//        new Thread(){
//            @Override

    }

   @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("再次回到该界面","");
        adapter.notifyDataSetChanged();
    }

    /**
     * 显示所有用户
     */
    public void getAllUser(){
        String url="http://192.168.32.126:7023/rest/model/atg/userprofiling/ProfileActor/allUser";
        RequestParams rp=new RequestParams(url);
        x.http().post(rp, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.d("result的返回值",""+result);
                try {
                    JSONObject object = new JSONObject(result);
                    Log.d("object的值",object.toString());
                    String object_cl=object.getString("cl");
                    Log.d("object_cl的值",object_cl.toString());
                    JSONArray array=new JSONArray(object_cl.toString());
                    for (int i=0;i<array.length();i++){
                        Log.d("解析json数组",array.toString());
                        JSONObject object_info=array.getJSONObject(i);
                        name=object_info.getString("cl_mingcheng");
                        email=object_info.getString("cl_shuliang");
                        phone=object_info.getString("cl_jiner");
                        jiaose=object_info.getString("cl_qiye");
                        state=object_info.getString("cl_cangku");
                        account=object_info.getString("cl_cpid");
                        user_id=object_info.getString("cl_uid");
                        Log.d("角色的值",jiaose);
                        role="";
                        Map<String,Object> map=new Hashtable<String,Object>();
                        map.put("account",account);
                        map.put("name",name);
                        map.put("phone",phone);
                        map.put("email",email);

                        if (jiaose.contains("50")){
                            role=role+" 企业管理员";
                            Log.d("role的值",role);
                            map.put("role",role);
                        }
                        if (jiaose.contains("52")){
                            role=role+" 业务员";
                            Log.d("role的值",role);
                            map.put("role",role);
                        }
                        if (jiaose.contains("53")){
                            role=role+" 财务员";
                            Log.d("role的值",role);
                            map.put("role",role);
                            //map.put("role",listitem.get(i).get("role")+" 财务员");
                        }



                        if (state.equals("31"))
                        {
                            map.put("state","有效");
                        }else if (state.equals("32")){
                            map.put("state","无效");
                        }
                        listitem.add(map);

                    }
                     adapter=new SimpleAdapter(
                            CompanyUserActivity.this,
                            listitem,
                            R.layout.item_company_user,
                            new String[]{"account","name","phone","email","role","state"},
                            new int[]{R.id.tvAccount_company_user,R.id.tvName_company_user,R.id.tvTwo_company_user,R.id.tvThree_company_user,R.id.tvrole_company_user,R.id.tvstate_company_user});
                    elistview.setAdapter(adapter);
                    adapter.notifyDataSetChanged();

                }catch (Exception e){
                    //Log.d("解析异常",""+e.printStackTrace());
                    e.printStackTrace();
                }

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                ex.printStackTrace();

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

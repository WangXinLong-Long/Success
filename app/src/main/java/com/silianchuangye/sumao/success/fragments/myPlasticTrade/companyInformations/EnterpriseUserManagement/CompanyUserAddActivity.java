package com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.EnterpriseUserManagement;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.api.GoogleApiClient;
import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.firmInfomation.FirmInfoTypeActivity;
import com.silianchuangye.sumao.success.utils.SuMaoConstant;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

public class CompanyUserAddActivity extends AppCompatActivity {
    private ListView lVadd;
    private List<Map<String, Object>> list;
    ImageView iv_title_bar_logo,
            iv_title_bar_back,
            iv_title_bar_service,
            iv_title_bar_search;
    Button sv_title_bar_serachView;
    TextView tv_title_bar_title,tv;
    RelativeLayout layoutTop;
    private SimpleAdapter adapter;
    private String account,phone,email,name,role,state;
    private Button btsave_company_user_add;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_user_add);
        title_Bar();

        lVadd = (ListView) findViewById(R.id.lvAdd_company_user);
        list = new ArrayList<Map<String, Object>>();
        Map<String,Object> map=new Hashtable<String,Object>();
        map.put("text","登录账号");
        map.put("right","");
        list.add(map);

        Map<String,Object> map1=new Hashtable<String,Object>();
        map1.put("text","姓名");
        map1.put("right","");
        list.add(map1);

        Map<String,Object> map2=new Hashtable<String,Object>();
        map2.put("text","电话号码");
        map2.put("right","");
        list.add(map2);

        Map<String,Object> map3=new Hashtable<String,Object>();
        map3.put("text","邮箱");
        map3.put("right","");
        list.add(map3);

        Map<String,Object> map4=new Hashtable<String,Object>();
        map4.put("text","职责");
        map4.put("right","");
        map4.put("icon",R.mipmap.my_sumao_iv_order);
        list.add(map4);




        adapter = new SimpleAdapter(CompanyUserAddActivity.this, list, R.layout.item_company_user_add,
                          new String[]{"text","right", "icon"},
                          new int[]{
                                  R.id.tvText_company_user_add,
                                  R.id.etContext_company_user,
                                  R.id.ivMore_company_user_add
                          });
        lVadd.setAdapter(adapter);


       lVadd.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

               if (position==4){
                   Intent intent=new Intent(CompanyUserAddActivity.this,CompanyUserDutyActivity.class);
                   startActivityForResult(intent,4);
               }else if (position==0||position==1||position==2||position==3){
                   Intent intent=new Intent(CompanyUserAddActivity.this,CompanyUserValueActivity.class);
                   intent.putExtra("title",list.get(position).get("text").toString());
                   intent.putExtra("number",position);
                   intent.putExtra("content","");
                   startActivityForResult(intent,position);
               }

           }
       });
        btsave_company_user_add= (Button) findViewById(R.id.btsave_company_user_add);
        btsave_company_user_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                new Thread(){
//                    @Override
//                    public void run() {
//                        addUser();
//                    }
//                }.start();
                String url= SuMaoConstant.SUMAO_IP+"/rest/model/atg/userprofiling/ProfileActor/addUser";
                RequestParams rp=new RequestParams(url);
                rp.addParameter("cl_cpid",account);
                rp.setCharset("UTF-8");
                rp.setAsJsonContent(true);
                JSONObject object=new JSONObject();
                try {

                    object.put("cl_mingcheng","小红");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
               // rp.addParameter("cl_mingcheng",name);
                rp.setBodyContent(object.toString());
                rp.addParameter("cl_shuliang",email);
                rp.addParameter("cl_jiner",phone);
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
                Log.d("role_id的值",role_id);
                String zhize=role_id.trim().replaceAll(" ",",");
                Log.d("职责",zhize);
                rp.addParameter("cl_qiye",zhize);
                SharedPreferences sp=getSharedPreferences("sumao", Activity.MODE_PRIVATE);
                String unique=sp.getString("unique","");
                Log.d("unique",unique);
                rp.addParameter("_dynSessConf",unique);
                Log.d("rp",rp.toString());

                x.http().post(rp, new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        Log.d("reult",result);
                        if (result.contains("sucess")){
                            Toast.makeText(CompanyUserAddActivity.this, "您已注册成功", Toast.LENGTH_SHORT).show();
                            finish();
                        }else{
                            Toast.makeText(CompanyUserAddActivity.this, "请重新输入您的信息", Toast.LENGTH_SHORT).show();

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
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case 0:
                account=data.getStringExtra("content");
                Log.d("account",account);
                list.get(0).put("right",account);
                adapter.notifyDataSetChanged();
                break;
            case 1:
                name=data.getStringExtra("content");
                Log.d("account",name);
                list.get(1).put("right",name);
                adapter.notifyDataSetChanged();
                break;
            case 2:
                phone=data.getStringExtra("content");
                Log.d("account",phone);
                list.get(2).put("right",phone);
                adapter.notifyDataSetChanged();
                break;
            case 3:
                email=data.getStringExtra("content");
                Log.d("account",email);
                list.get(3).put("right",email);
                adapter.notifyDataSetChanged();
                break;
            case 4:
                role=data.getStringExtra("people");
                Log.d("account",role);
                list.get(4).put("right",role);
                adapter.notifyDataSetChanged();
                break;

        }
    }

    public void title_Bar(){
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
        tv_title_bar_title.setText("新建用户");
        tv_title_bar_title.setTextColor(Color.WHITE);
        iv_title_bar_back.setVisibility(View.VISIBLE);
        layoutTop= (RelativeLayout) findViewById(R.id.layouttop_add);
        layoutTop.setBackgroundColor(getResources().getColor(R.color.textColor_expandable_listview_show));

        iv_title_bar_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CompanyUserAddActivity.this.finish();
            }
        });
    }




//    class Adapter extends SimpleAdapter {
//
//
//        public Adapter(Context context, List<? extends Map<String, ?>> data, int resource, String[] from, int[] to) {
//            super(context, data, resource, from, to);
//        }
//
//        @Override
//        public int getCount() {
//            return list.size();
//        }
//
//        @Override
//        public Object getItem(int position) {
//            return list.get(position);
//        }
//
//        @Override
//        public View getView(final int position, View convertView, ViewGroup parent) {
//            convertView = super.getView(position, convertView, parent);
//
//            if (convertView != null) {
//                convertView = getLayoutInflater().inflate(R.layout.item_company_user_add, null);
//                TextView et = (TextView) convertView.findViewById(R.id.etContext_company_user);
//                RelativeLayout layout =(RelativeLayout)convertView.findViewById(R.id.layouttop_add_item);
//                TextView tv= (TextView) convertView.findViewById(R.id.tvText_company_user_add);
//              //  EditText et_pass= (EditText) convertView.findViewById(R.id.etContext_company_user_pass);
//                tv.setText(list.get(position).get("text").toString());
//                ImageView iv= (ImageView) convertView.findViewById(R.id.ivMore_company_user_add);
//                if (position==5||position==6){
//                    iv.setVisibility(View.VISIBLE);
//                    et.setVisibility(View.INVISIBLE);
//                }else{
//                    iv.setVisibility(View.GONE);
//                }
//                if(position==1){
//                    //et_pass.setVisibility(View.VISIBLE);
//                    et.setVisibility(View.GONE);
//
//                }
//            }
//
//
//
//
//            return convertView;
//        }
//    }
}

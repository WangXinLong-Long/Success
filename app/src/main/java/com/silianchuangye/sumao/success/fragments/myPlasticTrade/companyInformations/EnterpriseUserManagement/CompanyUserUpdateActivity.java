package com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.EnterpriseUserManagement;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.silianchuangye.sumao.success.R;

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
        btSave= (Button) findViewById(R.id.btSave_password_update);
        layoutTop= (RelativeLayout) findViewById(R.id.layouttop_update);
        list=new ArrayList<Map<String,Object>>();
        Map<String,Object> map1=new Hashtable<String,Object>();
        map1.put("text","登录账号");
        map1.put("value","123456789");
       // map1.put("ispass","");
        list.add(map1);
        Map<String,Object> map2=new Hashtable<String,Object>();
        map2.put("text","密码");
        //map2.put("value",);
        map2.put("ispass","123456789");
        list.add(map2);
        Map<String,Object> map3=new Hashtable<String,Object>();
        map3.put("text","姓名");
        map3.put("value","张三");
        //map3.put("ispass","");
        list.add(map3);
        Map<String,Object> map4=new Hashtable<String,Object>();
        map4.put("text","手机号码");
        map4.put("value","12345678901");
        //map4.put("ispass","");
        list.add(map4);
        Map<String,Object> map5=new Hashtable<String,Object>();
        map5.put("text","邮箱");
        map5.put("value","123456789");
       // map5.put("ispass","");
        list.add(map5);
        Map<String,Object> map6=new Hashtable<String,Object>();
        map6.put("text","职责");
        map6.put("value","业务员");
        //map6.put("ispass","");
        list.add(map6);
        Map<String,Object> map7=new Hashtable<String,Object>();
        map7.put("text","状态");
        map7.put("value","不可用");
        //map7.put("ispass","");
        list.add(map7);
        SimpleAdapter adapter=new SimpleAdapter(CompanyUserUpdateActivity.this,list,R.layout.item_company_user_update,new String[]{"text","value","ispass"},new int[]{R.id.tvText,R.id.tvValue_company_user_update,R.id.tvValue_company_user_update_passWord});
        lvupdate.setAdapter(adapter);
        //Listview的子组件的点击事件




    }
}

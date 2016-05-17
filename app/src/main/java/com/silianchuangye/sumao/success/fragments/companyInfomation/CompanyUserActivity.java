package com.silianchuangye.sumao.success.fragments.companyInfomation;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ExpandableListView;
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

public class CompanyUserActivity extends AppCompatActivity {
    private ListView elistview;
    private List<Map<String,Object>> listitem;
    ImageView iv_title_bar_logo,
            iv_title_bar_back,
            iv_title_bar_service,
            iv_title_bar_search;
    Button sv_title_bar_serachView;
    TextView tv_title_bar_title,tv;
    RelativeLayout layoutTop,add_address_rl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_user);
        elistview= (ListView) findViewById(R.id.elvinfo_company_user);
        listitem=new ArrayList<Map<String,Object>>();
        Map<String,Object> map=new Hashtable<String,Object>();
        map.put("account","123456789");
        map.put("name","管俊");
        map.put("phone","1840167528");
        map.put("email","24147866@QQ.com");
        map.put("role","企业管理员");
        map.put("state","不可用");
        listitem.add(map);

        SimpleAdapter adapter=new SimpleAdapter(this,listitem,R.layout.item_company_user,new String[]{"account","name","phone","email","role","state"},new int[]{R.id.tvAccount_company_user,R.id.tvName_company_user,R.id.tvTwo_company_user,R.id.tvThree_company_user,R.id.tvrole_company_user,R.id.tvstate_company_user});
        elistview.setAdapter(adapter);

        iv_title_bar_back = ((ImageView) findViewById(R.id.iv_title_bar_back));
        iv_title_bar_logo = ((ImageView) findViewById(R.id.iv_title_bar_logo));
        iv_title_bar_service = ((ImageView) findViewById(R.id.iv_title_bar_service));
        iv_title_bar_search = ((ImageView) findViewById(R.id.iv_title_bar_search));
        sv_title_bar_serachView = ((Button) findViewById(R.id.sv_title_bar_serachView));
        tv_title_bar_title  = ((TextView) findViewById(R.id.tv_title_bar_title));
        add_address_rl= (RelativeLayout) findViewById(R.id.add_address_rl);
        tv= (TextView) findViewById(R.id.tvupdate);
        tv.setText("新增用户");
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

        elistview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(CompanyUserActivity.this,CompanyUserUpdateActivity.class);
                startActivity(intent);
            }
        });




    }
}

package com.silianchuangye.sumao.success.fragments.companyInfomation;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
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

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.silianchuangye.sumao.success.R;

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
        Map<String, Object> map = new Hashtable<String, Object>();
        map.put("text", "登录账号");
        // map.put("is","");
        list.add(map);
        Map<String, Object> map1 = new Hashtable<String, Object>();
        map1.put("text", "密码");
        // map.put("is","");
        list.add(map1);
        Map<String, Object> map2 = new Hashtable<String, Object>();
        map2.put("text", "姓名");
        // map.put("is","");
        list.add(map2);
        Map<String, Object> map3 = new Hashtable<String, Object>();
        map3.put("text", "手机号码");
        // map.put("is","");
        list.add(map3);
        Map<String, Object> map4 = new Hashtable<String, Object>();
        map4.put("text", "邮箱");
        list.add(map4);
        Map<String, Object> map5 = new Hashtable<String, Object>();
        map5.put("text", "职责");

        map5.put("icon", R.mipmap.my_sumao_iv_order);
        list.add(map5);
        Map<String, Object> map6 = new Hashtable<String, Object>();
        map6.put("text", "状态");
        map6.put("icon", R.mipmap.my_sumao_iv_order);
        list.add(map6);


        Adapter adapter = new Adapter(CompanyUserAddActivity.this, list, R.layout.item_company_user_add, new String[]{"text", "icon"}, new int[]{R.id.tvText_company_user_add, R.id.ivMore_company_user_add});
        lVadd.setAdapter(adapter);

       lVadd.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               if (position==5){
                   Intent intent=new Intent(CompanyUserAddActivity.this,CompanyUserDutyActivity.class);
                   startActivity(intent);
               }else if (position==6){
                   Intent intent=new Intent(CompanyUserAddActivity.this,CompanyUserStateActivity.class);
                   startActivity(intent);
               }
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


    class Adapter extends SimpleAdapter {


        public Adapter(Context context, List<? extends Map<String, ?>> data, int resource, String[] from, int[] to) {
            super(context, data, resource, from, to);
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            convertView = super.getView(position, convertView, parent);
            if (convertView != null) {
                convertView = getLayoutInflater().inflate(R.layout.item_company_user_add, null);
                EditText et = (EditText) convertView.findViewById(R.id.etContext_company_user);
                RelativeLayout layout =(RelativeLayout)convertView.findViewById(R.id.layouttop_add_item);
                TextView tv= (TextView) convertView.findViewById(R.id.tvText_company_user_add);
                EditText et_pass= (EditText) convertView.findViewById(R.id.etContext_company_user_pass);
                tv.setText(list.get(position).get("text").toString());
                ImageView iv= (ImageView) convertView.findViewById(R.id.ivMore_company_user_add);
                if (position==5||position==6){
                    iv.setVisibility(View.VISIBLE);
                    et.setVisibility(View.INVISIBLE);
                }else{
                    iv.setVisibility(View.GONE);
                }
                if(position==1){
                    et_pass.setVisibility(View.VISIBLE);
                    et.setVisibility(View.GONE);

                }
            }


            return convertView;
        }
    }
}

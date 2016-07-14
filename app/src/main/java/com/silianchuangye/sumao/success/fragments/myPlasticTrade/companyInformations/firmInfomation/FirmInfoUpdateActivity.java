package com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.firmInfomation;

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
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.receiptAddress.SelectProvinceArea;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.register.RegisterValueActivity;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

public class FirmInfoUpdateActivity extends AppCompatActivity {
    ImageView iv_title_bar_logo,
            iv_title_bar_back,
            iv_title_bar_service,
            iv_title_bar_search;
    Button sv_title_bar_serachView;
    TextView tv_title_bar_title,tv;
    RelativeLayout layoutTop;
    private ListView lvupdate_firm_info;
    private List<Map<String,Object>> list;
    private List<Map<String,Object>> list1;
    private ListView lvupdate_firm_info_two;
    private Spinner sp_firm_info;
    String title;
    private SimpleAdapter adapter;
    private SimpleAdapter adapter1;
    private Button bt_save_register_value;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_firm);


        title = "修改企业信息";
        String add="修改";
        title_Bar(title);
        initList(add);
    }
    public void initList(String add){
        lvupdate_firm_info= (ListView) findViewById(R.id.lvUpdate_firm_info);
        list=new ArrayList<Map<String,Object>>();
        if (add.equals("修改")){

        }

        Map<String,Object> map2=new Hashtable<String,Object>();
        map2.put("left","企业类型");
        map2.put("right","");
        map2.put("icon",R.mipmap.my_sumao_iv_order);
        list.add(map2);
        Map<String,Object> map3=new Hashtable<String,Object>();
        map3.put("left","企业名称");
        map3.put("right","");
        map3.put("icon",R.mipmap.my_sumao_iv_order);
        list.add(map3);
        Map<String,Object> map4=new Hashtable<String,Object>();
        map4.put("left","业务部门");
        map4.put("right","");
        map4.put("icon",R.mipmap.my_sumao_iv_order);
        list.add(map4);
        Map<String,Object> map5=new Hashtable<String,Object>();
        map5.put("left","办公地址");
        map5.put("right","");
        map5.put("icon",R.mipmap.my_sumao_iv_order);
        list.add(map5);
        Map<String,Object> map6=new Hashtable<String,Object>();
        map6.put("left","传真号");
        map6.put("right","");
        map6.put("icon",R.mipmap.my_sumao_iv_order);
        list.add(map6);

        adapter=new SimpleAdapter(FirmInfoUpdateActivity.this,list,R.layout.item_firm_info,new String[]{"left","center","right","icon"},new int[]{R.id.tv_firm_info,R.id.tvTitle_firm_info,R.id.tvValue_firm_info,R.id.ivMore_firm_info});
        lvupdate_firm_info.setAdapter(adapter);
        Log.d("list.size",""+list.size());

        lvupdate_firm_info.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position==0){
                    Intent intent=new Intent(FirmInfoUpdateActivity.this,FirmInfoTypeActivity.class);
                    intent.putExtra("title",list.get(position).get("left").toString());
                    intent.putExtra("number",position);
                    startActivityForResult(intent,position);
                }else if(position==2||position==1||position==4){
                    Intent intent=new Intent(FirmInfoUpdateActivity.this,RegisterValueActivity.class);
                    intent.putExtra("title",list.get(position).get("left").toString());
                    intent.putExtra("content",list.get(position).get("right").toString());
                    startActivityForResult(intent,position);
                }else if(position==3){
                    Intent intent=new Intent(FirmInfoUpdateActivity.this,SelectProvinceArea.class);
                    startActivityForResult(intent,position);

                }
            }
        });

        lvupdate_firm_info_two= (ListView) findViewById(R.id.lvUpdate_firm_info_two);
        list1=new ArrayList<Map<String,Object>>();
        sp_firm_info= (Spinner) findViewById(R.id.sp_firm_info);
        Map<String,Object> map12=new Hashtable<String, Object>();
        map12.put("left","统一社会信用代码");
        map12.put("right","");
        map12.put("icon",R.mipmap.my_sumao_iv_order);
        list1.add(map12);
        Map<String,Object> map11=new Hashtable<String,Object>();
        map11.put("left","纳税人类型");
        map11.put("right","");
        map11.put("icon",R.mipmap.my_sumao_iv_order);
        list1.add(map11);
        adapter1=new SimpleAdapter(this,list1,R.layout.item_firm_info,new String[]{"left","center","right","icon"},new int[]{R.id.tv_firm_info,R.id.tvTitle_firm_info,R.id.tvValue_firm_info,R.id.ivMore_firm_info});
        lvupdate_firm_info_two.setAdapter(adapter1);

        sp_firm_info.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(sp_firm_info.getItemAtPosition(position).toString().equals("三证独立")){
                    if(list1.size()==2){
                        list1.remove(0);
                        Map<String,Object> map8=new Hashtable<String,Object>();
                        map8.put("left","企业执照号");
                        map8.put("right","");
                        map8.put("icon",R.mipmap.my_sumao_iv_order);
                        list1.add(map8);
                        Map<String,Object> map9=new Hashtable<String,Object>();
                        map9.put("left","组织机构代码");
                        map9.put("right","");
                        map9.put("icon",R.mipmap.my_sumao_iv_order);
                        list1.add(map9);
                        Map<String,Object> map10=new Hashtable<String,Object>();
                        map10.put("left","税务登记号");
                        map10.put("right","");
                        map10.put("icon",R.mipmap.my_sumao_iv_order);
                        list1.add(map10);

                        adapter1=new SimpleAdapter(FirmInfoUpdateActivity.this,list1,R.layout.item_firm_info,new String[]{"left","center","right","icon"},new int[]{R.id.tv_firm_info,R.id.tvTitle_firm_info,R.id.tvValue_firm_info,R.id.ivMore_firm_info});
                        lvupdate_firm_info_two.setAdapter(adapter1);}
                    Toast.makeText(FirmInfoUpdateActivity.this, ""+list1.size(), Toast.LENGTH_SHORT).show();
                }else if (sp_firm_info.getItemAtPosition(position).toString().equals("三证合一")){
                    if(list1.size()>2){
                        for (int i=0;i<4;i++){
                            list1.remove(0);
                            //   list1.remove(1);
                        }
                        Map<String,Object> map12=new Hashtable<String, Object>();
                        map12.put("left","统一社会信用代码");
                        map12.put("right","");
                        map12.put("icon",R.mipmap.my_sumao_iv_order);
                        list1.add(map12);
                        Map<String,Object> map11=new Hashtable<String,Object>();
                        map11.put("left","纳税人类型");
                        map11.put("right","");
                        map11.put("icon",R.mipmap.my_sumao_iv_order);
                        list1.add(map11);
                        adapter1=new SimpleAdapter(FirmInfoUpdateActivity.this,list1,R.layout.item_firm_info,new String[]{"left","center","right","icon"},new int[]{R.id.tv_firm_info,R.id.tvTitle_firm_info,R.id.tvValue_firm_info,R.id.ivMore_firm_info});
                        lvupdate_firm_info_two.setAdapter(adapter1);
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(FirmInfoUpdateActivity.this, "请选择", Toast.LENGTH_SHORT).show();
            }
        });
        lvupdate_firm_info_two.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (list1.size()==2){
                    if (position==0){
                        Intent intent=new Intent(FirmInfoUpdateActivity.this, FirmInfoPictureActivity.class);
                        intent.putExtra("name",list1.get(0).get("left").toString());
                        intent.putExtra("number",10);
                        //startActivity(intent);
                        startActivityForResult(intent,10);
                    }else if (position==1){
                        Intent intent=new Intent(FirmInfoUpdateActivity.this, FirmInfoTypeActivity.class);
                        intent.putExtra("title",list1.get(1).get("left").toString());
                        //startActivity(intent);
                        intent.putExtra("number",21);
                        startActivityForResult(intent,21);
                    }

                }else if (list1.size()==4){
                    if (position==0){
                        Intent intent=new Intent(FirmInfoUpdateActivity.this, FirmInfoTypeActivity.class);
                        intent.putExtra("title",list1.get(0).get("left").toString());
                        intent.putExtra("number",20);

                        startActivityForResult(intent,20);
                    }else if (position==1){
                        Intent intent=new Intent(FirmInfoUpdateActivity.this, FirmInfoPictureActivity.class);
                        intent.putExtra("name",list1.get(0).get("left").toString());
                        intent.putExtra("number",11);
                        startActivityForResult(intent,11);
                    }else if (position==2){
                        Intent intent=new Intent(FirmInfoUpdateActivity.this, FirmInfoPictureActivity.class);
                        intent.putExtra("name",list1.get(0).get("left").toString());
                        intent.putExtra("number",12);
                        startActivityForResult(intent,12);
                    }else if (position==3){
                        Intent intent=new Intent(FirmInfoUpdateActivity.this, FirmInfoPictureActivity.class);
                        intent.putExtra("name",list1.get(0).get("left").toString());
                        intent.putExtra("number",13);
                        startActivityForResult(intent,13);
                    }

                }
            }
        });
//        bt_save_register_value= (Button) findViewById(R.id.bt_save_register_value);
//        bt_save_register_value.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                new Thread(){
//                    @Override
//                    public void run() {
//                        RequestParams rp=new RequestParams("http://192.168.32.126:7023/rest/model/atg/store/profile/RegistrationActor/updateEnterprise");
//                        rp.addParameter("cl_mingcheng",list.get(1).get("right").toString());
//                        rp.addParameter("cl_yewu",list.get(2).get("right").toString());
//                        rp.addParameter("province","1414");
//                        rp.addParameter("city","141410");
//                        rp.addParameter("county","14141029");
//                        rp.addParameter("cl_dizhi",list.get(3).get("right".toString()));
//                        rp.addParameter("cl_chuanzhen",list.get(4).get("right").toString());
//                        rp.addParameter("cl_zhengjian","1");
//                        rp.addParameter("cl_zhizhao",list1.get(0).get("right").toString());
//                        rp.addParameter("cl_nashuiren",list1.get(1).get("right").toString());
//                        rp.addParameter("cl_leixing",1);
////                        rp.addParameter("cl_jigou",list1.get(2).get("right").toString());
////                        rp.addParameter("cl_shuiwu",list1.get(3).get("right").toString());
//                        rp.addParameter("cl_zhizhaoimage","/mnt/docs/100.jpg");
//                        rp.addParameter("cl_jigouimage","/mnt/docs/100.jpg");
//                        rp.addParameter("cl_shuiwuimage","/mnt/docs/100.jpg");
//                        rp.addParameter("cl_nashuirenimage","/mnt/docs/100.jpg");
//                        SharedPreferences sp=getSharedPreferences("sumao", Activity.MODE_PRIVATE);
//                        String unique=sp.getString("unique","");
//                        Log.d("唯一标识",unique);
//                        rp.addParameter("_dynSessConf",unique);
//                        //还有图片路径没有写
//
//                        x.http().post(rp, new Callback.CommonCallback<String>() {
//                            @Override
//                            public void onSuccess(String result) {
//                                Toast.makeText(FirmInfoUpdateActivity.this, "修改成功", Toast.LENGTH_SHORT).show();
//                            }
//
//                            @Override
//                            public void onError(Throwable ex, boolean isOnCallback) {
//
//                            }
//
//                            @Override
//                            public void onCancelled(CancelledException cex) {
//
//                            }
//
//                            @Override
//                            public void onFinished() {
//
//                            }
//                        });
//                    }
//                }.start();
//            }
//        });






    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode){
            case 0:
                String one=data.getStringExtra("name");
                list.get(0).put("right",one);
                adapter.notifyDataSetChanged();
                break;
            case 1:
                String name=data.getStringExtra("name");
                list.get(1).put("right",name);
                adapter.notifyDataSetChanged();
                break;
            case 2:
                String name1=data.getStringExtra("name");
                list.get(2).put("right",name1);
                adapter.notifyDataSetChanged();
                break;

            case 4:
                String number=data.getStringExtra("name");
                list.get(4).put("right",number);
                adapter.notifyDataSetChanged();
                break;
            case 10:
                String value=data.getStringExtra("value");
                list1.get(0).put("right",value);
                adapter1.notifyDataSetChanged();
                break;
            case 11:
                String value1=data.getStringExtra("value");
                list1.get(1).put("right",value1);
                adapter1.notifyDataSetChanged();
                break;
            case 12:
                String value2=data.getStringExtra("value");
                list1.get(2).put("right",value2);
                adapter1.notifyDataSetChanged();
                break;
            case 13:
                String value3=data.getStringExtra("value");
                list1.get(3).put("right",value3);
                adapter1.notifyDataSetChanged();
                break;
            case 20:
                String value4=data.getStringExtra("name");
                list1.get(0).put("right",value4);
                adapter1.notifyDataSetChanged();
                break;
            case 21:
                String value5=data.getStringExtra("name");
                Log.d("nnnnnn",value5);
                list1.get(1).put("right",value5);
                adapter1.notifyDataSetChanged();
                break;


        }
    }

    public void title_Bar(String title){
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
        tv_title_bar_title.setText(title);
        tv_title_bar_title.setTextColor(Color.WHITE);
        iv_title_bar_back.setVisibility(View.VISIBLE);
        layoutTop= (RelativeLayout) findViewById(R.id.layoutTop_firm_info_update);
        layoutTop.setBackgroundColor(getResources().getColor(R.color.textColor_expandable_listview_show));

        iv_title_bar_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirmInfoUpdateActivity.this.finish();
            }
        });
    }
}

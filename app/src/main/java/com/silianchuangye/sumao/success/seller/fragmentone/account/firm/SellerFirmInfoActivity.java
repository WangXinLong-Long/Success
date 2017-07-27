package com.silianchuangye.sumao.success.seller.fragmentone.account.firm;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.accountmeneger.open.BankwebActivity;
import com.silianchuangye.sumao.success.utils.SuMaoConstant;

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

public class SellerFirmInfoActivity extends AppCompatActivity {
    private ImageView iv_back;
    private TextView tv_Title;
    private ListView firm_info;
    private List<Map<String, Object>> list;
    private ListView lv_bankName;
    private List<Map<String, Object>> list_bank;
    private RelativeLayout layout_add;
    private  String enterpriseID;



    public void init() {
        firm_info = (ListView) findViewById(R.id.firm_info);
        list = new ArrayList<>();
        lv_bankName = (ListView) findViewById(R.id.lv_bankName);
        list_bank = new ArrayList<>();
        iv_back = (ImageView) findViewById(R.id.iv_back);
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tv_Title = (TextView) findViewById(R.id.tv_Title);
        tv_Title.setText("企业信息管理");
        layout_add = (RelativeLayout) findViewById(R.id.layout_add);
        layout_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //页面跳转
                Intent intent=new Intent(SellerFirmInfoActivity.this, BankInfoActivity.class);
                intent.putExtra("laiyuan","add");
                intent.putExtra("id","");
                intent.putExtra("enterpriseID",enterpriseID);
                intent.putExtra("displayName","");
                intent.putExtra("paymentType","");
                intent.putExtra("bankName","");
                intent.putExtra("account","");
                intent.putExtra("useFlag","");
                startActivity(intent);
            }
        });
        lv_bankName.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(SellerFirmInfoActivity.this, BankInfoActivity.class);
                intent.putExtra("laiyuan","update");
                intent.putExtra("enterpriseID",enterpriseID);
                intent.putExtra("id",list_bank.get(position).get("id").toString());
                intent.putExtra("displayName",list_bank.get(position).get("displayName").toString());
                intent.putExtra("paymentType",list_bank.get(position).get("paymentType").toString());
                intent.putExtra("bankName",list_bank.get(position).get("bankName").toString());
                intent.putExtra("account",list_bank.get(position).get("account").toString());
                intent.putExtra("useFlag",list_bank.get(position).get("useFlag").toString());
                startActivity(intent);
            }
        });


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_firm_info);
        init();
        getFirminfo();
    }

    public void getFirminfo(){
        SharedPreferences sp=getSharedPreferences("sumao", Activity.MODE_PRIVATE);
        String seller_session=sp.getString("seller_session","");
        String url= SuMaoConstant.Seller_SUMAO_IP+"/smp/moble/shop/enterpriseInfo/enterpriseInfoPageMobile.html";
        RequestParams rp=new RequestParams(url);
        rp.addParameter("sessionId",seller_session);
        Log.d("卖家企业信息的rp",rp+"");
        x.http().post(rp, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.d("卖家的企业信息",result);
                try {
                    JSONObject jsonObject=new JSONObject(result);
                    String executionResult=jsonObject.getString("executionResult");
                    if (executionResult.equals("true")){
                        String enterpriseInfoVo=jsonObject.getString("enterpriseInfoVo");
                        JSONObject jsonEnterprise=new JSONObject(enterpriseInfoVo);
                        String enterpriseName=jsonEnterprise.getString("enterpriseName");
                        String name=jsonEnterprise.getString("name");//销售组织
                        enterpriseID=jsonEnterprise.getString("enterpriseID");
                        String address=jsonEnterprise.getString("address");
                        String fax=jsonEnterprise.getString("fax");
                        String corporationName=jsonEnterprise.getString("corporationName");
                        Map<String,Object> map=new Hashtable<String, Object>();
                        map.put("left","公司名称");
                        map.put("right",enterpriseName);
                        list.add(map);
                        Map<String,Object> map1=new Hashtable<String, Object>();
                        map1.put("left","销售组织");
                        map1.put("right",name);
                        list.add(map1);
                        Map<String,Object> map2=new Hashtable<String, Object>();
                        map2.put("left","企业编号");
                        map2.put("right",enterpriseID);
                        list.add(map2);
                        Map<String,Object> map3=new Hashtable<String, Object>();
                        map3.put("left","注册地址");
                        map3.put("right",address);
                        list.add(map3);
                        Map<String,Object> map4=new Hashtable<String, Object>();
                        map4.put("left","传真号码");
                        map4.put("right",fax);
                        list.add(map4);
                        Map<String,Object> map5=new Hashtable<String, Object>();
                        map5.put("left","公司法人");
                        map5.put("right",corporationName);
                        list.add(map5);
                        SimpleAdapter adapter=new SimpleAdapter(SellerFirmInfoActivity.this,
                                list,R.layout.item_firm_info,
                                new String[]{"left","right"},new int[]{
                                R.id.tv_firm_info,R.id.tvValue_firm_info
                        }
                                );
                        firm_info.setAdapter(adapter);
                        adapter.notifyDataSetChanged();
                        String banks=jsonEnterprise.getString("banks");
                        JSONArray jsonArray=new JSONArray(banks);
                        for (int i=0;i<jsonArray.length();i++){
                            JSONObject jsonBank=jsonArray.getJSONObject(i);
                            String id=jsonBank.getString("id");
                            String displayName=jsonBank.getString("displayName");
                            String paymentType=jsonBank.getString("paymentType");
                            String bankName=jsonBank.getString("bankName");
                            String account=jsonBank.getString("account");
                            String useFlag=jsonBank.getString("useFlag");
                            Map<String,Object> map6=new Hashtable<String, Object>();
                            map6.put("id",id);
                            map6.put("displayName",displayName);
                            map6.put("paymentType",paymentType);
                            map6.put("bankName",bankName);
                            map6.put("account",account);
                            map6.put("useFlag",useFlag);
                            map6.put("icon",R.mipmap.my_sumao_iv_order);
                            list_bank.add(map6);
                            SimpleAdapter adapter_bank=new SimpleAdapter(SellerFirmInfoActivity.this,list_bank,
                                    R.layout.item_firm_info,new String []{"bankName","icon"},
                                    new int[]{R.id.tv_firm_info,R.id.ivMore_firm_info}
                                    );
                            lv_bankName.setAdapter(adapter_bank);
                            adapter_bank.notifyDataSetChanged();

                        }


                    }else{
                        Toast.makeText(SellerFirmInfoActivity.this, "用户没有登录!", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
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
    }


}

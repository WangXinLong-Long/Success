package com.silianchuangye.sumao.success.seller.fragmentone.account.firm;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.login.LoginUserActivity;
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

public class BankInfoActivity extends AppCompatActivity {
    private String laiyuan;
    private ListView lvDemo;
    private List<Map<String,Object>> list;
    private ImageView iv_back;
    private TextView tv_Title;
    private TextView save;
    private String id="";
    private String displayName="";
    private String paymentType="";
    private String bankName="";
    private String account="";
    private String useFlag="";
    private SimpleAdapter adapter;
    private String enterpriseID="";
    private String seller_session="";

//    intent.putExtra("laiyuan","add");
//    intent.putExtra("id","");
//    intent.putExtra("displayName","");
//    intent.putExtra("paymentType","");
//    intent.putExtra("bankName","");
//    intent.putExtra("account","");
//    intent.putExtra("useFlag","");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank_info);
        Intent intent=getIntent();
        laiyuan=intent.getStringExtra("laiyuan");
        id=intent.getStringExtra("id");
        displayName=intent.getStringExtra("displayName");
        paymentType=intent.getStringExtra("paymentType");
        bankName=intent.getStringExtra("bankName");
        account=intent.getStringExtra("account");
        useFlag=intent.getStringExtra("useFlag");
        enterpriseID=intent.getStringExtra("enterpriseID");
        SharedPreferences sp=getSharedPreferences("sumao", Activity.MODE_PRIVATE);
        seller_session=sp.getString("seller_session","");
        Log.d("来源",laiyuan);
        init();
        addData();
    }
    public void addData(){
        Map<String,Object> map=new Hashtable<String, Object>();
        map.put("left","收款账户名称");
        map.put("right",displayName);
        map.put("icon",R.mipmap.my_sumao_iv_order);
        list.add(map);
        Map<String,Object> map1=new Hashtable<String, Object>();
        map1.put("left","收款银行");
        if (paymentType.equals("1")){
            map1.put("right","平安银行");
        }else if (paymentType.equals("2")){
            map1.put("right","昆仑银行");
        }else if (paymentType.equals("3")){
            map1.put("right","中国建设银行");
        }
        map1.put("icon",R.mipmap.my_sumao_iv_order);
        list.add(map1);
        Map<String,Object> map2=new Hashtable<String, Object>();
        map2.put("left","开户银行");
        map2.put("right",bankName);
        map2.put("icon",R.mipmap.my_sumao_iv_order);
        list.add(map2);
        Map<String,Object> map3=new Hashtable<String, Object>();
        map3.put("left","收款帐号");
        map3.put("right",account);
        map3.put("icon",R.mipmap.my_sumao_iv_order);
        list.add(map3);
        Map<String,Object> map4=new Hashtable<String, Object>();
        map4.put("left","收款帐号状态");
        if (useFlag.length()>0) {
            if (useFlag.equals("true")) {
                map4.put("right", "有效");
            } else {
                map4.put("right", "无效");
            }
        }else{
            map4.put("right", "");
        }
        map4.put("icon",R.mipmap.my_sumao_iv_order);
        list.add(map4);
         adapter=new SimpleAdapter(BankInfoActivity.this,
                list,R.layout.item_firm_info,
                new String[]{"left","right","icon"},new int[]{
                R.id.tv_firm_info,R.id.tvValue_firm_info,R.id.ivMore_firm_info
        }
        );
        lvDemo.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    public void init(){
        lvDemo= (ListView) findViewById(R.id.lvDemo);
        list=new ArrayList<>();
        iv_back= (ImageView) findViewById(R.id.iv_back);
        tv_Title= (TextView) findViewById(R.id.tv_Title);
        save= (TextView) findViewById(R.id.save);
        save.setText("保存");
        if (laiyuan.equals("add")){
            tv_Title.setText("添加");
        }else{
            tv_Title.setText(bankName);
        }
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (laiyuan.equals("add")){
                    //新增银行信息
                    addBank();
                }else{
                    //修改银行信息
                    updateBank();
                }
            }
        });
        lvDemo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if (position==4){
                    dialogtype(list.get(position).get("left").toString(),position);
                }else {
                    dialog(list.get(position).get("left").toString(),position);
                }
            }
        });
    }
    public void dialog(String name, final int position){
        final AlertDialog.Builder dialog=new AlertDialog.Builder(BankInfoActivity.this);

        View view=View.inflate(BankInfoActivity.this,R.layout.activity_dilog_input,null);
        dialog.setView(view);
        //dialog.setCanceledOnTouchOutside(false);
        final AlertDialog dialog1=dialog.show();
        dialog1.setCanceledOnTouchOutside(false);
        TextView tv_dialog_error= (TextView) view.findViewById(R.id.tv_dialog_error);
        tv_dialog_error.setText(name);
        ImageView iv_dialog_ok_cancel= (ImageView) view.findViewById(R.id.iv_dialog_ok_cancel);
        iv_dialog_ok_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog1.dismiss();
            }
        });
        final EditText input= (EditText) view.findViewById(R.id.input);
        Button btSure= (Button) view.findViewById(R.id.btSure);
        btSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.get(position).put("right",input.getText().toString());
                adapter.notifyDataSetChanged();
                dialog1.dismiss();
            }
        });

    }

    public void dialogtype(String name, final int position){
        final AlertDialog.Builder dialog=new AlertDialog.Builder(BankInfoActivity.this);

        View view=View.inflate(BankInfoActivity.this,R.layout.layout_dialogtype,null);
        dialog.setView(view);
        //dialog.setCanceledOnTouchOutside(false);
        final AlertDialog dialog1=dialog.show();
        dialog1.setCanceledOnTouchOutside(false);
        TextView tv_dialog_error= (TextView) view.findViewById(R.id.tv_dialog_error);
        tv_dialog_error.setText(name);
        ImageView iv_dialog_ok_cancel= (ImageView) view.findViewById(R.id.iv_dialog_ok_cancel);
        iv_dialog_ok_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog1.dismiss();
            }
        });
//        final EditText input= (EditText) view.findViewById(R.id.input);
//        Button btSure= (Button) view.findViewById(R.id.btSure);
//        btSure.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                list.get(position).put("right",input.getText().toString());
//                adapter.notifyDataSetChanged();
//                dialog1.dismiss();
//            }
//        });
        final RadioButton rb2= (RadioButton) view.findViewById(R.id.rb2);
        rb2.setText("有效");
        rb2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.get(position).put("right",rb2.getText().toString());
                adapter.notifyDataSetChanged();
                dialog1.dismiss();
            }
        });
        final RadioButton rb1= (RadioButton) view.findViewById(R.id.rb1);
        rb1.setText("无效");
        rb1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.get(position).put("right",rb1.getText().toString());
                adapter.notifyDataSetChanged();
                dialog1.dismiss();
            }
        });


    }

    public void addBank(){

        String url= SuMaoConstant.Seller_SUMAO_IP+"/smp/moble/shop/enterpriseInfo/addBankInfoMobile.html";
        RequestParams rp=new RequestParams(url);
        rp.addParameter("enterpriseId",enterpriseID);
        rp.addParameter("enterpriseName",list.get(0).get("right").toString());

        if (list.get(1).get("right").toString().equals("平安银行")){
            rp.addParameter("bankType","1");
        }else if (list.get(1).get("right").toString().equals("昆仑银行")){
            rp.addParameter("bankType","2");
        }else if (list.get(1).get("right").toString().equals("中国建设银行")){
            rp.addParameter("bankType","3");
        }
        rp.addParameter("bankName",list.get(2).get("right").toString());
        rp.addParameter("account",list.get(3).get("right").toString());
        //
        if (list.get(4).get("right").toString().equals("有效")){
            rp.addParameter("useFlag","true");
        }else{
            rp.addParameter("useFlag","false");
        }
        rp.addParameter("sessionId",seller_session);
        Log.d("新增银行信息的rp",rp+"");
        x.http().post(rp, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.d("新增银行的返回值",result);
               // if ()
                try {
                    JSONObject jsonObject=new JSONObject(result);
                    String executionResult=jsonObject.getString("executionResult");
                    if(executionResult.equals("true")){
                        Toast.makeText(BankInfoActivity.this, "银行新增成功", Toast.LENGTH_SHORT).show();
                    }else{
                        String errors=jsonObject.getString("errors");
                        JSONArray jsonarray=new JSONArray(errors);
//                        String errors=jsonobject.getString("errors");
//                        JSONArray json_info=new JSONArray(errors);
                        String errorinfo=jsonarray.toString().substring(2,jsonarray.toString().length()-2);
                        if (errorinfo.equals("找不到相应用户")){
                            Toast.makeText(BankInfoActivity.this, "用户没有登录!", Toast.LENGTH_SHORT).show();
                            Intent intent=new Intent(BankInfoActivity.this, LoginUserActivity.class);
                            startActivity(intent);
                        }else{
                            Toast.makeText(BankInfoActivity.this, errorinfo, Toast.LENGTH_SHORT).show();
                        }
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

    public void updateBank(){
        String url=SuMaoConstant.Seller_SUMAO_IP+"/smp/moble/shop/enterpriseInfo/updateBankInfoMobile.html";
        RequestParams rp=new RequestParams(url);
        rp.addParameter("enterpriseId",enterpriseID);
        rp.addParameter("id",id);
        rp.addParameter("enterpriseName",list.get(0).get("right").toString());

        if (list.get(1).get("right").toString().equals("平安银行")){
            rp.addParameter("bankType","1");
        }else if (list.get(1).get("right").toString().equals("昆仑银行")){
            rp.addParameter("bankType","2");
        }else if (list.get(1).get("right").toString().equals("中国建设银行")){
            rp.addParameter("bankType","3");
        }
        rp.addParameter("bankName",list.get(2).get("right").toString());
        rp.addParameter("account",list.get(3).get("right").toString());
        //
        if (list.get(4).get("right").toString().equals("有效")){
            rp.addParameter("useFlag","true");
        }else{
            rp.addParameter("useFlag","false");
        }
        rp.addParameter("sessionId",seller_session);
        Log.d("修改银行账户信息",rp+"");
        x.http().post(rp, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.d("修改银行账户信息的返回值",result);
                try {
                    JSONObject jsonObject=new JSONObject(result);
                    String executionResult=jsonObject.getString("executionResult");
                    if(executionResult.equals("true")){
                        Toast.makeText(BankInfoActivity.this, "银行信息修改成功", Toast.LENGTH_SHORT).show();
                    }else{
                        String errors=jsonObject.getString("errors");
                        JSONArray jsonarray=new JSONArray(errors);
//                        String errors=jsonobject.getString("errors");
//                        JSONArray json_info=new JSONArray(errors);
                        String errorinfo=jsonarray.toString().substring(2,jsonarray.toString().length()-2);
                        if (errorinfo.equals("找不到相应用户")){
                            Toast.makeText(BankInfoActivity.this, "用户没有登录!", Toast.LENGTH_SHORT).show();
                            Intent intent=new Intent(BankInfoActivity.this, LoginUserActivity.class);
                            startActivity(intent);
                        }else{
                            Toast.makeText(BankInfoActivity.this, errorinfo, Toast.LENGTH_SHORT).show();
                        }
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

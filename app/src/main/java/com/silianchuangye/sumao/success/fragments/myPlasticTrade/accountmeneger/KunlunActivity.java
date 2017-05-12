package com.silianchuangye.sumao.success.fragments.myPlasticTrade.accountmeneger;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.utils.SuMaoConstant;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

public class KunlunActivity extends AppCompatActivity implements View.OnClickListener {
    private int position;
    private TextView tv_child_title_bar_title;
    private ImageView iv_child_title_bar_back;
    private String unique;
    private SharedPreferences sp;
    private RelativeLayout layout_bank;
    private RelativeLayout layout_bank_rig;
    private TextView monent_value;
    private TextView monent_use_value;
    private Button bt_Update;
    private Button bt_Open;
    private List<Map<String,Object>> list;
    private ListView lv_Info;
    private Button bt_Qianyue;

    private String money="abc";
    private String available="abc";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kunlun);
        SharedPreferences sp=getSharedPreferences("sumao",Activity.MODE_PRIVATE);
        unique=sp.getString("unique","");
        lv_Info= (ListView) findViewById(R.id.lv_Info);
        bt_Qianyue= (Button) findViewById(R.id.bt_Qianyue);
        bt_Qianyue.setOnClickListener(this);//签约
        layout_bank= (RelativeLayout) findViewById(R.id.layout_bank);
        layout_bank_rig= (RelativeLayout) findViewById(R.id.layout_bank_rig);
        monent_value= (TextView) findViewById(R.id.monent_value);
        monent_use_value= (TextView) findViewById(R.id.monent_use_value);
        bt_Update= (Button) findViewById(R.id.bt_Update);
        bt_Update.setOnClickListener(this);//变更
        bt_Open= (Button) findViewById(R.id.bt_Open);
        bt_Open.setOnClickListener(this);//解约
        tv_child_title_bar_title= (TextView) findViewById(R.id.tv_child_title_bar_title);
        iv_child_title_bar_back= (ImageView) findViewById(R.id.iv_child_title_bar_back);
        iv_child_title_bar_back.setOnClickListener(this);
        tv_child_title_bar_title.setText("昆仑银行");
        Bundle bundle=getIntent().getExtras();
        available=bundle.getString("available");
        money=bundle.getString("money");
        Log.d("available的值",available);
        Log.d("money",money);
        if (!available.equals("") && !money.equals("")){
            //显示账户信息
            layout_bank.setVisibility(View.VISIBLE);
            monent_value.setText(money);
            monent_use_value.setText(available);

        }else{
            //显示注册账户
            layout_bank_rig.setVisibility(View.VISIBLE);
            list=new ArrayList<>();
            Map<String,Object> map=new HashMap<>();
            map.put("icon","*");
            map.put("left","银行开户名称");
            map.put("right","填写银行开户名称");
            list.add(map);
            Map<String,Object> map1=new Hashtable<>();
            map1.put("icon","*");
            map1.put("left","银行账号");
            map1.put("right","填写银行账号");
            list.add(map1);
            Map<String,Object> map2=new Hashtable<>();
            map2.put("icon","");
            map2.put("left","采购商类型");
            map2.put("right","填写采购商类型");
            list.add(map2);
            Map<String,Object> map3=new Hashtable<>();
            map3.put("icon","*");
            map3.put("left","开户电话号码");
            map3.put("right","填写电话号码");
            list.add(map3);
            SimpleAdapter adapter=new SimpleAdapter(KunlunActivity.this,list,R.layout.item_info,new String[]{"icon","left","right"},new int[]{
                    R.id.tv_Xing,R.id.tv_lv_dayplan_date,R.id.tv_lv_dayplan_date_right
            });
            lv_Info.setAdapter(adapter);

        }


    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    public void getKunluninfo(){
        String url= SuMaoConstant.SUMAO_IP+"/rest/model/atg/commerce/order/myorder/OrderPaymentInfo/myaccountList";
        RequestParams rp=new RequestParams(url);
        rp.addParameter("_dynSessConf",unique);
        rp.addParameter("bankId",2);
        Log.d("昆仑银行的返回值rp",rp+"");
        x.http().post(rp, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.d("昆仑银行的返回值",result);
                if (result.contains("available")){
                    Log.d("进来","if");
                    layout_bank.setVisibility(View.VISIBLE);
                    layout_bank_rig.setVisibility(View.GONE);
                    try {
                        JSONObject jsonObject=new JSONObject(result);
                        monent_value.setText(jsonObject.getString("money"));
                        monent_use_value.setText(jsonObject.getString("available"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }else{
                   // Log.d("进来","else");
                    layout_bank_rig.setVisibility(View.VISIBLE);
                    layout_bank.setVisibility(View.GONE);
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

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_child_title_bar_back:
                KunlunActivity.this.finish();
                break;
            case R.id.bt_Update:
                //跳到变更界面

                break;
            case R.id.bt_Open:
                //解约
                Intent intent=new Intent(KunlunActivity.this,KunlunJieyueActivity.class);
                startActivity(intent);
                break;
            case R.id.bt_Qianyue:
                //调用签约接口
                Qianyue();
                break;
        }

    }
    public void Qianyue(){
        String url=SuMaoConstant.SUMAO_IP+"/rest/model/atg/commerce/order/myorder/OrderPaymentInfo/kunLunSign";
        RequestParams rp=new RequestParams(url);
        rp.addParameter("accountNo",list.get(1).get("right"));
        rp.addParameter("accountName",list.get(0).get("right"));
        rp.addParameter("merchantType","采购商类型");
        rp.addParameter("mobileNo",list.get(3).get("right"));
        x.http().post(rp, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                if (result.contains("success")){//签约成功
                    getKunluninfo();
                }else{
                    Toast.makeText(KunlunActivity.this, "签约失败!", Toast.LENGTH_SHORT).show();
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

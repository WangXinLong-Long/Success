package com.silianchuangye.sumao.success.fragments.myPlasticTrade.accountmeneger;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.utils.Constanse;
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

public class Account_Activity extends AppCompatActivity implements View.OnClickListener{
    private ImageView iv_child_title_bar_back;
    private TextView tv_child_title_bar_title;
    private ListView lv_Bank;
    private List<Map<String,Object>> list;
   // private ImageView iv_child_title_bar_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_);
        iv_child_title_bar_back= (ImageView) findViewById(R.id.iv_child_title_bar_back);
        iv_child_title_bar_back.setOnClickListener(this);
        tv_child_title_bar_title= (TextView) findViewById(R.id.tv_child_title_bar_title);
        lv_Bank= (ListView) findViewById(R.id.lv_Bank);
        list=new ArrayList<>();
        tv_child_title_bar_title.setText("账户管理");
        Map<String,Object> map=new Hashtable<String, Object>();
        map.put("icon",R.mipmap.pingan);
        map.put("name","平安银行");
        list.add(map);
        Map<String,Object> map1=new Hashtable<>();
        map1.put("icon",R.mipmap.kunlun);
        map1.put("name","昆仑银行");
        list.add(map1);
//        Map<String,Object> map2=new Hashtable<>();
//        map2.put("icon",R.mipmap.jianshe);
//        map2.put("name","中国建设银行");
//        list.add(map2);
        SimpleAdapter adapter=new SimpleAdapter(Account_Activity.this,list,R.layout.layout_item_banklist,new String[]{"icon","name"},new int[]{R.id.iv_Bank_icon,R.id.tv_Bank_name});
        lv_Bank.setAdapter(adapter);
        lv_Bank.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if (position==0){
                    Intent intent=new Intent(Account_Activity.this,inganActivity.class);
                    intent.putExtra("position",1);
                    startActivity(intent);
                }else if(position==1){
//                    Intent intent=new Intent(Account_Activity.this,KunlunActivity.class);
//                    intent.putExtra("position",2);
//                    startActivity(intent);
                    getinfoBank(2);
                }/*else if (position==2){
                    Intent intent=new Intent(Account_Activity.this,JiansheActivity.class);
                    intent.putExtra("position",3);
                    startActivity(intent);
                }*/


            }
        });

    }
    public void getinfoBank(final int position){
        String url= SuMaoConstant.SUMAO_IP+"/rest/model/atg/commerce/order/myorder/OrderPaymentInfo/myaccountList";
        SharedPreferences sp=getSharedPreferences("sumao", Activity.MODE_PRIVATE);
        String unique=sp.getString("unique","");
        RequestParams rp=new RequestParams(url);
        rp.addParameter("bankId",position);
        rp.addParameter("_dynSessConf",unique);
        Log.d("rp的值",rp+"");
        x.http().post(rp, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.d("银行的返回值",result);
                if (!result.equals("")||result!=""){
                    //有银行的信息
                    try {
                        JSONObject jsonObject=new JSONObject(result);
                        String money=jsonObject.getString("money");
                        String available=jsonObject.getString("available");
                        if (!money.equals("连接银行出错")) {
                            if (position == 2) {
                                //昆仑银行
                                Intent intent = new Intent(Account_Activity.this, KunlunActivity.class);
                                intent.putExtra("money", money);
                                intent.putExtra("available", available);
                                startActivity(intent);
                            } else if (position == 3) {
                                //建设银行
                            }
                        }else{
                            Toast.makeText(Account_Activity.this, "连接银行出错!", Toast.LENGTH_SHORT).show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }else{
                    if (position==2){
                        //昆仑银行
                        Intent intent=new Intent(Account_Activity.this,KunlunActivity.class);
                        intent.putExtra("money","abc");
                        intent.putExtra("available","abc");
                        startActivity(intent);

                    }else if (position==3){
                        //建设银行
                    }
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
                Account_Activity.this.finish();
                break;

        }
    }
}

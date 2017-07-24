package com.silianchuangye.sumao.success.fragments.myPlasticTrade.accountmeneger.open;

import android.content.Intent;
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
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.utils.SuMaoConstant;


import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

public class KunlunUpdateinfoActivity extends AppCompatActivity implements View.OnClickListener {
    private String name="",cardNo="",mobilePhone="";
    private ImageView iv_child_title_bar_back;
    private TextView tv_child_title_bar_title;
    private ListView lvdemo;
    private Button btUpdate;
    private Button btCancel;
    private List<Map<String,Object>> list;
    private SimpleAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kunlun_updateinfo);
        Bundle bundle=getIntent().getExtras();
        name=bundle.getString("name");
        Log.d("name的值",name);
        cardNo=bundle.getString("cardNo");
        mobilePhone=bundle.getString("mobilePhone");
        init();
    }
    public void init(){
        iv_child_title_bar_back= (ImageView) findViewById(R.id.iv_child_title_bar_back);
        tv_child_title_bar_title= (TextView) findViewById(R.id.tv_child_title_bar_title);
        btUpdate= (Button) findViewById(R.id.btUpdate);
        btCancel= (Button) findViewById(R.id.btCancel);
        lvdemo= (ListView) findViewById(R.id.lvdemo);
        list=new ArrayList<>();
        tv_child_title_bar_title.setText("变更帐号");
        iv_child_title_bar_back.setOnClickListener(this);
        btUpdate.setOnClickListener(this);
        btCancel.setOnClickListener(this);
        Map<String,Object> map1=new HashMap<>();
        map1.put("left","银行开户名称");
        map1.put("right",name);
        list.add(map1);
        Map<String,Object> map2=new HashMap<>();
        map2.put("left","银行帐号");
        map2.put("right",cardNo);
        list.add(map2);
//        Map<String,Object> map3=new HashMap<>();
//        map3.put("left","采购商类型");
//        map3.put("right","");
//        list.add(map3);
        Map<String,Object> map4=new Hashtable<>();
        map4.put("left","开户行电话号码");
        map4.put("right",mobilePhone);
        list.add(map4);
        adapter=new SimpleAdapter(KunlunUpdateinfoActivity.this,list,R.layout.item_kunlun,
                new String []{"left","right"},
                new int[]{R.id.tvName,R.id.tvValue});
        lvdemo.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        adapter.notifyDataSetChanged();
        lvdemo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                dialog(list.get(position).get("left").toString(),position);
            }
        });

    }
    public void dialog(String name, final int position){
        final AlertDialog.Builder dialog=new AlertDialog.Builder(KunlunUpdateinfoActivity.this);

        View view=View.inflate(KunlunUpdateinfoActivity.this,R.layout.activity_dilog_input,null);
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

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_child_title_bar_back:
                finish();
                break;
            case R.id.btUpdate://变更帐号
                biangeng();
                break;
            case R.id.btCancel://取消
                Intent intent=new Intent(KunlunUpdateinfoActivity.this,KunlunUpdateinfoActivity.class);
                startActivity(intent);
                finish();

                break;
        }
    }
    public void biangeng(){
        String url= SuMaoConstant.SUMAO_IP+"/rest/model/atg/commerce/order/myorder/OrderPaymentInfo/kunLunModifySign";
        RequestParams rp=new RequestParams(url);
        rp.addParameter("accountNo",list.get(1).get("right").toString());
        rp.addParameter("accountName",list.get(0).get("right").toString());
        rp.addParameter("mobileNo",list.get(2).get("right").toString());
        Log.d("昆仑银行的变更",rp+"");
        x.http().post(rp, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.d("变更帐号的result",result);
                if (result.contains("success")){
                    Toast.makeText(KunlunUpdateinfoActivity.this, "变更成功", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(KunlunUpdateinfoActivity.this, "变更失败", Toast.LENGTH_SHORT).show();
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

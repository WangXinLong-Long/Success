package com.silianchuangye.sumao.success.fragments.myPlasticTrade.accountmeneger.my;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.utils.SuMaoConstant;

import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

/**
 * Created by junjun on 2017/7/23.
 */
public class Chujin extends AppCompatActivity {
    private ImageView iv_child_title_bar_back;
    private TextView tv_child_title_bar_title;
    private Button btChujin;
    private ListView lvDemo;
    private List<Map<String,Object>> list;
    private RelativeLayout layout_center;
    private TextView tvKeyong;
    private String ky="";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_value);
        Bundle bundle=getIntent().getExtras();
        ky=bundle.getString("ky");
        init();
        getinfo();
    }
    public void getinfo(){
        String url= SuMaoConstant.SUMAO_IP+"/rest/model/atg/commerce/order/myorder/OrderPaymentInfo/myaccountListInfo";
        RequestParams rp=new RequestParams(url);
        rp.addParameter("bankId","1");
        Log.d("平安银行的rp",rp.toString());
        x.http().post(rp, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.d("平安银行的rp",result);
                try {

                    if(!result.contains("pingAnAccount")){
//                        layout_open_no.setVisibility(View.VISIBLE);
//                        layout_open.setVisibility(View.GONE);
                    }else{

                        JSONObject jsonObject=new JSONObject(result);
                        String pingAnAccount=jsonObject.getString("pingAnAccount");
                        if (!pingAnAccount.isEmpty()&&!pingAnAccount.equals("")&&pingAnAccount!=null){
//                            layout_open_no.setVisibility(View.GONE);
//                            layout_open.setVisibility(View.VISIBLE);
                            JSONObject object=new JSONObject(pingAnAccount);
                            String name=object.getString("bankName");
                            String cardNo=object.getString("cardNo");
                            String acctName=object.getString("acctName");
                            Map<String,Object> map=new Hashtable<>();
                            map.put("left","收款账户");
                            map.put("right",acctName);
                            list.add(map);
                            Map<String,Object> map1=new Hashtable<>();
                            map1.put("left","收款银行");
                            map1.put("right",name);
                            list.add(map1);
                            Map<String,Object> map2=new Hashtable<>();
                            map2.put("left","收款账号");
                            map2.put("right",cardNo);
                            list.add(map2);
                            SimpleAdapter adapter=new SimpleAdapter(Chujin.this,list,
                                    R.layout.layout_item,new String[]{"left","right"},
                                    new int[]{R.id.left,R.id.right}
                            );
                            lvDemo.setAdapter(adapter);
                            adapter.notifyDataSetChanged();

                        }
                    }

                } catch (Exception e) {
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
    public void init(){
        layout_center= (RelativeLayout) findViewById(R.id.layout_center);
        tvKeyong= (TextView) findViewById(R.id.tvKeyong);
        tvKeyong.setHint("可用金额:"+ky);
        iv_child_title_bar_back= (ImageView) findViewById(R.id.iv_child_title_bar_back);
        tv_child_title_bar_title= (TextView) findViewById(R.id.tv_child_title_bar_title);
        btChujin= (Button) findViewById(R.id.btChujin);
        lvDemo= (ListView) findViewById(R.id.lvDemo);
        list=new ArrayList<>();
        iv_child_title_bar_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tv_child_title_bar_title.setText("出金");

        btChujin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tvKeyong.getText().toString()!=null&&!tvKeyong.getText().toString().isEmpty()&&!tvKeyong.getText().toString().equals("")){
                    qanyue();
                }else{
                    Toast.makeText(Chujin.this, "请输入正确的出金数额", Toast.LENGTH_SHORT).show();
                }

            }
        });
        layout_center.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog("出金金额",0);
            }
        });




    }
    public void dialog(String name, final int position){
        final AlertDialog.Builder dialog=new AlertDialog.Builder(Chujin.this);

        View view=View.inflate(Chujin.this,R.layout.activity_dilog_input,null);
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
//                list.get(position).put("right",input.getText().toString());
//                adapter.notifyDataSetChanged();
                tvKeyong.setText(input.getText().toString());
               dialog1.dismiss();
            }
        });

    }

    public void qanyue(){
        String url=SuMaoConstant.SUMAO_IP+"/rest/model/atg/commerce/order/myorder/OrderPaymentInfo/myaccountListPingAnBank";
        RequestParams rp=new RequestParams(url);
        rp.addParameter("amount",tvKeyong.getText().toString());
        x.http().post(rp, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                if (result.contains("true")){
                    Toast.makeText(Chujin.this, "出金成功", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(Chujin.this, "出金失败", Toast.LENGTH_SHORT).show();
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

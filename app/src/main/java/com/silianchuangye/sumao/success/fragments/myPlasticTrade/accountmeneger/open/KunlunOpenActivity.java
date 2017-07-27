package com.silianchuangye.sumao.success.fragments.myPlasticTrade.accountmeneger.open;

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

public class KunlunOpenActivity extends AppCompatActivity {
    private ListView lvDemo;
    private List<Map<String,Object>> list;
    private Button btqianyue;
    private TextView tv_child_title_bar_title;
    private ImageView iv_child_title_bar_back;
    private SimpleAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kunlun_open);
        init();
    }

    public void init(){
        tv_child_title_bar_title= (TextView) findViewById(R.id.tv_child_title_bar_title);
        tv_child_title_bar_title.setText("昆仑银行在线签约");
        iv_child_title_bar_back= (ImageView) findViewById(R.id.iv_child_title_bar_back);
        iv_child_title_bar_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btqianyue= (Button) findViewById(R.id.btqianyue);
        lvDemo= (ListView) findViewById(R.id.lvDemo);
        list=new ArrayList<>();
        Map<String,Object> map1=new HashMap<>();
        map1.put("left","银行开户名称");
        map1.put("right","");
        list.add(map1);
        Map<String,Object> map2=new HashMap<>();
        map2.put("left","银行帐号");
        map2.put("right","");
        list.add(map2);
        Map<String,Object> map3=new HashMap<>();
        map3.put("left","采购商类型");
        map3.put("right","");
        list.add(map3);
        Map<String,Object> map4=new Hashtable<>();
        map4.put("left","开户行电话号码");
        map4.put("right","");
        list.add(map4);
        adapter=new SimpleAdapter(KunlunOpenActivity.this,list,R.layout.item_kunlun,
                new String []{"left","right"},
                new int[]{R.id.tvName,R.id.tvValue});
        lvDemo.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        lvDemo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Intent intent=new Intent(KunlunOpenActivity.this,DilogInputActivity.class);
//                intent.putExtra("position",position);
//                intent.putExtra("content",list.get(position).get("left").toString());
//                intent.putExtra("content_right",list.get(position).get("right").toString());
//              //  setResult(position,intent);
//                startActivityForResult(intent,position);
             //   set

                if (position==2){
                    dialogtype(list.get(position).get("left").toString(),position);
                }else{
                    dialog(list.get(position).get("left").toString(),position);
                }
            }
        });
        btqianyue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  qianyue();
                if (list.get(0).get("right").toString().equals("")||list.get(1).get("right").toString().equals("")||
                        list.get(2).get("right").toString().equals("")||list.get(3).get("right").toString().equals("")
                        ) {
                    Toast.makeText(KunlunOpenActivity.this, "请把签约信息补充完整!", Toast.LENGTH_SHORT).show();

                }else{
                    qianyue();
                }
            }
        });

    }
    public void qianyue(){
        String url= SuMaoConstant.SUMAO_IP+"/rest/model/atg/commerce/order/myorder/OrderPaymentInfo/kunLunSign";
        RequestParams rp=new RequestParams(url);
        rp.setAsJsonContent(true);
        JSONObject jsonObject=new JSONObject();
        try {
            jsonObject.put("accountNo",list.get(1).get("right").toString());
            jsonObject.put("accountName",list.get(0).get("right").toString());
            jsonObject.put("merchantType",list.get(2).get("right").toString());
            jsonObject.put("mobileNo",list.get(3).get("right").toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        rp.setBodyContent(jsonObject.toString());
        Log.d("rp",jsonObject.toString());
        x.http().post(rp, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.d("返回值",result);
                if (result.contains("success")){
                    try {
//                        JSONObject jsonObject=new JSONObject(result);
//                        String message=jsonObject.getString("message");
                       // if (message.equals("success")){
                            Toast.makeText(KunlunOpenActivity.this, "签约成功", Toast.LENGTH_SHORT).show();
                            finish();
//                        }else{
//                            Toast.makeText(KunlunOpenActivity.this, "签约失败", Toast.LENGTH_SHORT).show();
//                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }else{
                    Toast.makeText(KunlunOpenActivity.this, "签约失败", Toast.LENGTH_SHORT).show();
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

    public void dialog(String name, final int position){
        final AlertDialog.Builder dialog=new AlertDialog.Builder(KunlunOpenActivity.this);

        View view=View.inflate(KunlunOpenActivity.this,R.layout.activity_dilog_input,null);
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
        final AlertDialog.Builder dialog=new AlertDialog.Builder(KunlunOpenActivity.this);

        View view=View.inflate(KunlunOpenActivity.this,R.layout.layout_dialogtype,null);
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
        rb2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.get(position).put("right",rb2.getText().toString());
                adapter.notifyDataSetChanged();
                dialog1.dismiss();
            }
        });
        final RadioButton rb1= (RadioButton) view.findViewById(R.id.rb1);
        rb1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.get(position).put("right",rb1.getText().toString());
                adapter.notifyDataSetChanged();
                dialog1.dismiss();
            }
        });


    }



//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        switch (requestCode){
//            case 0:
//                String data0=data.getStringExtra("info");
//                list.get(0).put("right",data0);
//                adapter.notifyDataSetChanged();
//                break;
//            case 1:
//                String data1=data.getStringExtra("info");
//                list.get(1).put("right",data1);
//                adapter.notifyDataSetChanged();
//                break;
//            case 2:
//                String data2=data.getStringExtra("info");
//                list.get(2).put("right",data2);
//                adapter.notifyDataSetChanged();
//                break;
//            case 3:
//                String data3=data.getStringExtra("info");
//                list.get(3).put("right",data3);
//                adapter.notifyDataSetChanged();
//                break;
//        }
    //}
}

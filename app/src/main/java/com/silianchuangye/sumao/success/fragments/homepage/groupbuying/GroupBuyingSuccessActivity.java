package com.silianchuangye.sumao.success.fragments.homepage.groupbuying;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.silianchuangye.sumao.success.R;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GroupBuyingSuccessActivity extends AppCompatActivity {
    private ListView lvDemo;
    private SimpleAdapter adapter;
    private List<Map<String,Object>> list;

    private TextView tv_success;
    private TextView tv_failed;
    private LinearLayout aaa;
    private RelativeLayout layout_number;
    private RelativeLayout layout_Bottom;
    private String Shangpinid;
    private TextView name;
    private TextView price;
    private TextView count;
    private TextView qigou;
    private TextView bianjia;
    private TextView bianliang;
    private TextView cangku;
    private TextView way;
    private TextView comm;
    private TextView type;
    private TextView add;
    private RelativeLayout jian;
    private RelativeLayout jia;
    private EditText number;
    private TextView count_price;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_buying_success);
        init();
        getinfo();
        event();
        addData();
    }
    public void init(){
        Bundle bundle=getIntent().getExtras();
        String state=bundle.getString("state");
        Shangpinid=bundle.getString("id");
        lvDemo= (ListView) findViewById(R.id.lv_demo);
        list=new ArrayList<Map<String,Object>>();
        tv_failed= (TextView) findViewById(R.id.tv_failed);
        tv_success= (TextView) findViewById(R.id.tv_success);
        aaa= (LinearLayout) findViewById(R.id.aaa);
        layout_number= (RelativeLayout) findViewById(R.id.layout_number);
        layout_Bottom= (RelativeLayout) findViewById(R.id.layout_Bottom);
        if (state.equals("no")){
            tv_success.setVisibility(View.GONE);
            tv_failed.setVisibility(View.VISIBLE);
            aaa.setVisibility(View.GONE);
            layout_number.setVisibility(View.GONE);
            layout_Bottom.setVisibility(View.INVISIBLE);
        }
        name= (TextView) findViewById(R.id.tvName_auction);
        price= (TextView) findViewById(R.id.tvPrice_auction);
        count= (TextView) findViewById(R.id.surplus_amount_et);
        qigou= (TextView) findViewById(R.id.purchase_quantity_et);
        bianliang= (TextView) findViewById(R.id.min_variable_et);
        cangku= (TextView) findViewById(R.id.warehouse_address_et);
        way= (TextView) findViewById(R.id.delivery_mode_et);
        type= (TextView) findViewById(R.id.classification_pre_sale_et);
        bianjia= (TextView) findViewById(R.id.warehouse_et);
        add= (TextView) findViewById(R.id.region_et);
        comm= (TextView) findViewById(R.id.company_et);
        jian= (RelativeLayout) findViewById(R.id.layout_bb);
        jia= (RelativeLayout) findViewById(R.id.sa);
        number= (EditText) findViewById(R.id.ed_shuzhi_number);
        count_price= (TextView) findViewById(R.id.tv_count_value);





    }
    public void event(){
        lvDemo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position==0){
                    Intent intent=new Intent(GroupBuyingSuccessActivity.this,RuleActivity.class);
                    startActivity(intent);
                }else if (position==1){
                    Intent intent=new Intent(GroupBuyingSuccessActivity.this,JoinActivity.class);
                    intent.putExtra("id",Shangpinid);
                    startActivity(intent);

                }else if (position==2){

                }
            }
        });
        jia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str=number.getText().toString();
                Log.d("number的值",str+"");
                String bb=bianliang.getText().toString();
                int aa=Integer.parseInt(str);
                int cc=Integer.parseInt(bb);
                aa=aa+cc;
                number.setText(aa);
                count_price.setText(Integer.parseInt(number.getText().toString())*Integer.parseInt(price.getText().toString()));
            }
        });
        jian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str=number.getText().toString();
                String bb=bianliang.getText().toString();
                int aa=Integer.parseInt(str);
                int cc=Integer.parseInt(bb);
                aa=aa-cc;
                if (aa<=0){
                    number.setText("1");
                    count_price.setText(price.getText());
                }
                number.setText(aa);
                count_price.setText(Integer.parseInt(number.getText().toString())*Integer.parseInt(price.getText().toString()));
            }
        });


    }
    public void addData(){
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("name","交易规则");
        list.add(map);
        Map<String,Object> map1=new HashMap<String,Object>();
        map1.put("name","我的参团记录");
        list.add(map1);
        Map<String,Object> map2=new HashMap<String,Object>();
        map2.put("name","合同详情、产品参数、量价图");
        list.add(map2);

        adapter=new SimpleAdapter(this,list,R.layout.item_open_auction,
                new String[]{"name"},
                new int[]{R.id.tvRule_auction});
        lvDemo.setAdapter(adapter);


    }
    public void getinfo(){
        new Thread(){
            @Override
            public void run() {
                super.run();
                String url="http://192.168.32.126:7023/rest/model/atg/commerce/catalog/ProductCatalogActor/groupProduct";
                RequestParams rp=new RequestParams(url);
                rp.addParameter("productId",Shangpinid);
                Log.d("rp的值",rp+"");
                x.http().post(rp, new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        Log.d("团购商品的详情页",result);
                        try {
                            JSONObject obj=new JSONObject(result);
                            tv_success.setText("剩余团购时间:"+obj.getString("utilDate"));
                            name.setText(obj.getString("cl_mingcheng"));
                            price.setText(obj.getString("cl_jine")+"元");
                            count.setText(obj.getString("cl_zongliang")+"吨");
                            qigou.setText(obj.getString("cl_qigou")+"吨");
                            bianliang.setText(obj.getString("cl_xbianliang")+"吨");
                            cangku.setText(obj.getString("cl_cangku"));
                            way.setText(obj.getString("cl_fangshi"));
                            type.setText(obj.getString("cl_fenlei"));
                            bianjia.setText(obj.getString("cl_xbianjia"));
                            add.setText(obj.getString("cl_diqu"));
                            comm.setText(obj.getString("cl_gongsi"));


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
        }.start();
    }
}

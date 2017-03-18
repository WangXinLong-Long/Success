package com.silianchuangye.sumao.success.fragments.myPlasticTrade.demandPlanning.planType;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.model.EnterpriseInformation;
import com.silianchuangye.sumao.success.utils.SuMaoConstant;

import org.json.JSONArray;
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

public class TypeActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView iv_child_title_bar_back;
    private TextView tv_child_title_bar_title;
    private SharedPreferences sp;
    private String unique;
    private List<Map<String,String>> list;
    private ListView lv_Firm_Type;
    private Button bt_upload;

    private String name;

    private String title;
    private String sellerCompanyId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_type);
        bt_upload= (Button) findViewById(R.id.bt_upload);
        bt_upload.setOnClickListener(this);
        lv_Firm_Type= (ListView) findViewById(R.id.lv_Firm_Type);
        tv_child_title_bar_title= (TextView) findViewById(R.id.tv_child_title_bar_title);
        iv_child_title_bar_back= (ImageView) findViewById(R.id.iv_child_title_bar_back);
        iv_child_title_bar_back.setOnClickListener(this);
        Bundle bundle=getIntent().getExtras();
        title=bundle.getString("title");

        tv_child_title_bar_title.setText(title);
        sp=getSharedPreferences("sumao", Activity.MODE_PRIVATE);
        unique=sp.getString("unique","");
        if (title.equals("卖方企业")){
            new Thread(){
                @Override
                public void run() {
                    super.run();
                    get_qiye();
                }
            }.start();
        }



    }
    public void get_qiye(){
        String url= SuMaoConstant.SUMAO_IP+"/rest/model/atg/commerce/order/demand/DemandActor/directEnterpriseDetailStore";
        RequestParams rp=new RequestParams(url);
        rp.addParameter("_dynSessConf",unique);
        Log.d("得到卖方企业",rp+"");
        x.http().post(rp, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.d("得到卖方企业",result);
                try {
                    list=new ArrayList<Map<String, String>>();
                    JSONObject jsonObject=new JSONObject(result);
                    String enterprisesItem=jsonObject.getString("enterprisesItem");
                    JSONArray jsonArray=new JSONArray(enterprisesItem);
                    for (int i=0;i<jsonArray.length();i++){
                        JSONObject object=jsonArray.getJSONObject(i);
                        Map<String,String> map=new Hashtable<String, String>();
                        map.put("id",object.getString("sellerCompanyId"));
                        map.put("name",object.getString("sellerCompanyName"));
                        list.add(map);

                    }
                    ListAdapter  adapter = new ListAdapter(getApplication(), list);
                    lv_Firm_Type.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
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



    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_child_title_bar_back:
                TypeActivity.this.finish();
                break;
            case R.id.bt_upload:
                Intent intent=new Intent();
                intent.putExtra("id",sellerCompanyId);
                intent.putExtra("name",name);
                setResult(0,intent);
                TypeActivity.this.finish();
                break;
        }

    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Intent intent=new Intent();
            intent.putExtra("id","");
            intent.putExtra("name","");
            setResult(0,intent);
            TypeActivity.this.finish();
            return true;
        } else if (keyCode == KeyEvent.KEYCODE_FOCUS || keyCode == KeyEvent.KEYCODE_CAMERA) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    class ViewHolder {

        RadioButton rb_state;
    }

    class ListAdapter extends BaseAdapter {
        private Context context;
        //        private List<EnterpriseInformation> list;
        private List<Map<String,String>> list;

        Map<String, Boolean> states = new HashMap<String, Boolean>();


        public ListAdapter(Context context,List<Map<String,String>> list) {
            this.context = context;
            this.list = list;
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
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            LayoutInflater inflater = LayoutInflater.from(TypeActivity.this);
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.item_type_radio_button, null);
                holder = new ViewHolder();
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            final RadioButton radio = (RadioButton) convertView.findViewById(R.id.rbCheck);
            holder.rb_state = radio;
            radio.setText(list.get(position).get("name"));
            holder.rb_state.setOnClickListener(new View.OnClickListener() {

                public void onClick(View v) {
                    name= list.get(position).get("name").toString();
                    sellerCompanyId = list.get(position).get("id").trim();
                    for (String key : states.keySet()) {
                        states.put(key, false);
                    }
                    //选中点中的
                    states.put(String.valueOf(position), radio.isChecked());
                    Log.d("states", String.valueOf(position) + "");
                    ListAdapter.this.notifyDataSetChanged();
                }
            });

            boolean res = false;
            if (states.get(String.valueOf(position)) == null || states.get(String.valueOf(position)) == false) {
                res = false;
                states.put(String.valueOf(position), false);
            } else
                res = true;


            holder.rb_state.setChecked(res);
            return convertView;


        }
    }

}

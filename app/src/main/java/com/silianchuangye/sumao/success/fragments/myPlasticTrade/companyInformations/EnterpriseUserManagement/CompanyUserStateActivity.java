package com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.EnterpriseUserManagement;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.utils.SuMaoConstant;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CompanyUserStateActivity extends AppCompatActivity {
    private ListView lv_Firm_Type;
    private List<String> list;
    private String name;
    ImageView iv_title_bar_logo,
            iv_title_bar_back,
            iv_title_bar_service,
            iv_title_bar_search;
    Button sv_title_bar_serachView;
    TextView tv_title_bar_title, tv;
    RelativeLayout layoutTop;
    private Button btSave;
    private TextView prompt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firm_info_type);
        Bundle bundle=getIntent().getExtras();
        String title=bundle.getString("title");
        title_Bar(title);
        lv_Firm_Type= (ListView) findViewById(R.id.lv_Firm_Type);
        list=new ArrayList<String>();
//        list.add("有效");
//        list.add("无效");
        new Thread(){
            @Override
            public void run() {
                String url= SuMaoConstant.SUMAO_IP+"/rest/model/atg/userprofiling/ProfileActor/mstActive";
                RequestParams rp=new RequestParams(url);
                x.http().post(rp, new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        try {
                            JSONObject obi_result = new JSONObject(result);
                            String obj_cl=obi_result.getString("active");
                            JSONArray array=new JSONArray(obj_cl);
                            for (int i=0;i<array.length();i++){
                                JSONObject info=array.getJSONObject(i);
                                list.add(info.getString("displayName"));
                            }
                            ListViewAdapter adapter=new ListViewAdapter(CompanyUserStateActivity.this,list);
                            lv_Firm_Type.setAdapter(adapter);
                        }catch (JSONException e){
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

        prompt= (TextView) findViewById(R.id.prompt);
        btSave= (Button) findViewById(R.id.btSave);
        //Log.d("name是",name);
        btSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (name==null ||name.isEmpty()){
                 Log.d("name","name=null");
                    prompt.setText("请选择类型");
                    prompt.setTextColor(Color.RED);
                    prompt.setVisibility(View.VISIBLE);
                }else{
                    Log.d("name",name);
                    Intent intent=new Intent();
                    intent.putExtra("state",name);
                    setResult(100,intent);
                    finish();
                }
            }
        });


    }
    public void title_Bar(String name) {
        iv_title_bar_back = ((ImageView) findViewById(R.id.iv_title_bar_back));
        iv_title_bar_logo = ((ImageView) findViewById(R.id.iv_title_bar_logo));
        iv_title_bar_service = ((ImageView) findViewById(R.id.iv_title_bar_service));
        iv_title_bar_search = ((ImageView) findViewById(R.id.iv_title_bar_search));
        sv_title_bar_serachView = ((Button) findViewById(R.id.sv_title_bar_serachView));
        tv_title_bar_title = ((TextView) findViewById(R.id.tv_title_bar_title));

        iv_title_bar_logo.setVisibility(View.GONE);
        iv_title_bar_service.setVisibility(View.GONE);
        sv_title_bar_serachView.setVisibility(View.GONE);
        iv_title_bar_search.setVisibility(View.GONE);
        tv_title_bar_title.setText(name);
        tv_title_bar_title.setTextColor(Color.WHITE);
        iv_title_bar_back.setVisibility(View.GONE);
        layoutTop = (RelativeLayout) findViewById(R.id.layoutTop_firm_info_type);
        layoutTop.setBackgroundColor(getResources().getColor(R.color.textColor_expandable_listview_show));

        iv_title_bar_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CompanyUserStateActivity.this.finish();
            }
        });
    }
    class ViewHolder {

        RadioButton rb_state;
    }
    class ListViewAdapter extends BaseAdapter {
        private Context context;
        private List<String> list;
        Map<String, Boolean> states = new HashMap<String, Boolean>();

        public ListViewAdapter(Context context,List<String> list) {
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
            LayoutInflater inflater = LayoutInflater.from(CompanyUserStateActivity.this);
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.item_type_radio_button, null);
                holder = new ViewHolder();
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            final RadioButton radio = (RadioButton) convertView.findViewById(R.id.rbCheck);
            holder.rb_state = radio;
            radio.setText(list.get(position).toString());
            holder.rb_state.setOnClickListener(new View.OnClickListener() {

                public void onClick(View v) {
                    name=list.get(position).toString();
                    for (String key : states.keySet()) {
                        states.put(key, false);
                    }
                    //选中点中的
                    states.put(String.valueOf(position), radio.isChecked());
                    Log.d("states", String.valueOf(position) + "");
                    ListViewAdapter.this.notifyDataSetChanged();
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
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            setValueName();
        }
        return super.onKeyDown(keyCode, event);
    }
    void setValueName(){
        Intent intent=new Intent();
        intent.putExtra("state", "");
        CompanyUserStateActivity.this.setResult(100, intent);
        finish();
    }
}

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
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.silianchuangye.sumao.success.R;

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

public class CompanyUserDutyActivity extends AppCompatActivity {
    ImageView iv_title_bar_logo,
            iv_title_bar_back,
            iv_title_bar_service,
            iv_title_bar_search;
    Button sv_title_bar_serachView;
    TextView tv_title_bar_title,tv;
    RelativeLayout layoutTop;
    private ListView lvDemo;
    private TextView pormt;
    private Button btSave;
    private List<String> list;
    private String name;
    private List<String> names;
    private boolean isAdd=true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_user_duty);
        title_Bar();
        pormt= (TextView) findViewById(R.id.prompt);
        lvDemo= (ListView) findViewById(R.id.lv_Firm_Type);
        list=new ArrayList<String>();
        names=new ArrayList<String>();

        new Thread(){
            @Override
            public void run() {
                //super.run();
                String url="http://192.168.32.126:7023/rest/model/atg/userprofiling/ProfileActor/roleTypeModel";
                RequestParams rp=new RequestParams(url);
                x.http().post(rp, new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        try {
                            JSONObject obj_result = new JSONObject(result);
                            String message=obj_result.getString("roleType");
                            JSONArray array=new JSONArray(message);
                            for (int i=0;i<array.length();i++){
                                JSONObject info=array.getJSONObject(i);
                                list.add(info.getString("displayName"));
                            }
                            ListViewAdapter adapter=new ListViewAdapter(CompanyUserDutyActivity.this,list);
                            lvDemo.setAdapter(adapter);
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

        btSave= (Button) findViewById(R.id.btSave);
        btSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (names.size()<=0){
                    Log.d("name","null");
                    pormt.setText("请选择类型");
                    pormt.setTextColor(Color.RED);
                    pormt.setVisibility(View.VISIBLE);
                }else {
                    Intent intent=new Intent();
                    intent.putExtra("people",names.toString());
                    Log.d("names数组的值",names.toString());
                    setResult(4,intent);
                    finish();
                }
            }
        });


    }

    public void title_Bar(){
        iv_title_bar_back = ((ImageView) findViewById(R.id.iv_title_bar_back));
        iv_title_bar_logo = ((ImageView) findViewById(R.id.iv_title_bar_logo));
        iv_title_bar_service = ((ImageView) findViewById(R.id.iv_title_bar_service));
        iv_title_bar_search = ((ImageView) findViewById(R.id.iv_title_bar_search));
        sv_title_bar_serachView = ((Button) findViewById(R.id.sv_title_bar_serachView));
        tv_title_bar_title  = ((TextView) findViewById(R.id.tv_title_bar_title));

        iv_title_bar_logo.setVisibility(View.GONE);
        iv_title_bar_service.setVisibility(View.GONE);
        sv_title_bar_serachView.setVisibility(View.GONE);
        iv_title_bar_search.setVisibility(View.GONE);
        tv_title_bar_title.setText("职责");
        tv_title_bar_title.setTextColor(Color.WHITE);
        iv_title_bar_back.setVisibility(View.VISIBLE);
        layoutTop= (RelativeLayout) findViewById(R.id.layoutTop_duty);
        layoutTop.setBackgroundColor(getResources().getColor(R.color.textColor_expandable_listview_show));

        iv_title_bar_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CompanyUserDutyActivity.this.finish();
            }
        });
    }
    class ViewHolder {

        CheckBox cb_check;
    }
    class ListViewAdapter extends BaseAdapter {
        private Context context;
        private List<String> list;
        //Map<String, Boolean> states = new HashMap<String, Boolean>();

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
            final ViewHolder holder;
            LayoutInflater inflater = LayoutInflater.from(CompanyUserDutyActivity.this);
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.checkboxitem, null);
                holder = new ViewHolder();
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            final CheckBox radio = (CheckBox) convertView.findViewById(R.id.rbCheck);
            holder.cb_check = radio;
            radio.setText(list.get(position).toString());
            holder.cb_check.setOnClickListener(new View.OnClickListener() {

                public void onClick(View v) {
                    //ames.get(position).toString()=list.get(position).toString();
                   // names.add(list.get(position).toString());
//                    if (isAdd){
//                        name=list.get(position).toString();
//                        names.add(name);
//                        isAdd=false;
//                        Log.d("真的数据是",names.toString());
//                    }else{
//                        names.remove(list.get(position).toString());
//                        Log.d("假的数据是",names.toString());
//                    }
                    Log.d("选中状态",holder.cb_check.isChecked()+"");
                    if (holder.cb_check.isChecked()==true){
                        names.add(list.get(position).toString());
                    }
                    if (holder.cb_check.isChecked()==false){
                        if (names.contains(list.get(position).toString())){
                            names.remove(list.get(position).toString());
                        }
                    }



                }

            });


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
        intent.putExtra("name", "");
        CompanyUserDutyActivity.this.setResult(4, intent);
        finish();
    }
}

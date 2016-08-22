package com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.firmInfomation;

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
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.register.FirmInfoTypeActivityMVP.presenter.GetEntInformationPresenter;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.register.FirmInfoTypeActivityMVP.view.IFirmInfoTypeActivity;
import com.silianchuangye.sumao.success.model.EnterpriseInformation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FirmInfoTypeActivity extends AppCompatActivity implements IFirmInfoTypeActivity {
    ImageView iv_title_bar_logo,
            iv_title_bar_back,
            iv_title_bar_service,
            iv_title_bar_search;
    Button sv_title_bar_serachView;
    TextView tv_title_bar_title, tv;
    RelativeLayout layoutTop;
    // RadioButton one,two,three;
    RadioGroup rgDemo;
    private String name;
    private Button btSave;
    private LinearLayout three_layout;
    String title;
    int number;
    private ListView lv_type;
    private List<String> list;
    GetEntInformationPresenter getEntInformation;
    Intent intent;
    ListAdapter adapter;
    private TextView prompt;
    private List<EnterpriseInformation> typeAndLevel;
    private String level;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firm_info_type);
        getEntInformation = new GetEntInformationPresenter(this);
        lv_type = (ListView) findViewById(R.id.lv_Firm_Type);
        prompt = ((TextView) findViewById(R.id.prompt));
        list = new ArrayList<String>();
        intent = new Intent();
        Bundle bundle = getIntent().getExtras();
        title = bundle.getString("title");
        number = bundle.getInt("number");
        Log.d("number", number + "");

        if (title.equals("企业类型")) {
            getEntInformation.setDataInView(1);
        } else if (title.equals("纳税人类型")) {
            getEntInformation.setDataInView(2);
        }


        lv_type.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        lv_type.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(FirmInfoTypeActivity.this, "进来listview的item的点击事件", Toast.LENGTH_SHORT).show();
                name = list.get(position).toString();
                Log.d("选中的文字", name);
            }
        });
        btSave = (Button) findViewById(R.id.btSave);
        btSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (name == null||name.isEmpty()){

                    prompt.setText("请选择类型");
                    prompt.setTextColor(Color.RED);
                    prompt.setVisibility(View.VISIBLE);

                }else {
                    Intent intent = new Intent();
                    intent.putExtra("name", name);
                    intent.putExtra("level",level);
                    FirmInfoTypeActivity.this.setResult(number, intent);
                    FirmInfoTypeActivity.this.finish();

                }
            }
        });

        title_Bar(title);
    }

    @Override
    public void initFirmInfoTypeActivityView(List<EnterpriseInformation> names) {
//        list.add(names.get(i).getDisplayName());
        typeAndLevel = names;
        adapter = new ListAdapter(getApplication(), names);
        lv_type.setAdapter(adapter);
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
                FirmInfoTypeActivity.this.finish();
            }
        });
    }

    class ViewHolder {

        RadioButton rb_state;
    }

    class ListAdapter extends BaseAdapter {
        private Context context;
        private List<EnterpriseInformation> list;
        Map<String, Boolean> states = new HashMap<String, Boolean>();

        public ListAdapter(Context context,List<EnterpriseInformation> list) {
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
            LayoutInflater inflater = LayoutInflater.from(FirmInfoTypeActivity.this);
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.item_type_radio_button, null);
                holder = new ViewHolder();
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            final RadioButton radio = (RadioButton) convertView.findViewById(R.id.rbCheck);
            holder.rb_state = radio;
            radio.setText(list.get(position).getDisplayName());
            holder.rb_state.setOnClickListener(new View.OnClickListener() {

                public void onClick(View v) {
                    name = list.get(position).getDisplayName().toString();
                    level = list.get(position).getId().trim();
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



    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            setValueName();
        }
        return super.onKeyDown(keyCode, event);
    }
    void setValueName(){
        intent.putExtra("name", "");
        intent.putExtra("level","");
        FirmInfoTypeActivity.this.setResult(RESULT_OK, intent);
    }
}
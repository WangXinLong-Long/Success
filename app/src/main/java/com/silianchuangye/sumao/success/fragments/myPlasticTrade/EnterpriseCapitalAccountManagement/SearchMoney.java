package com.silianchuangye.sumao.success.fragments.myPlasticTrade.EnterpriseCapitalAccountManagement;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.adapter.searchitemAdapter;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

//账户金额明细界面
public class SearchMoney extends AppCompatActivity implements View.OnClickListener,AdapterView.OnItemClickListener{
    ImageView iv_title_bar_logo,
            iv_title_bar_back,
            iv_title_bar_service,
            iv_title_bar_search;
    Button sv_title_bar_serachView;
    TextView tv_title_bar_title,tv;
    RelativeLayout spot_order_title;

    private int i=0;
    private TextView tv_searchmoney_money,tv_searchmoney_start,tv_searchmoney_end;
    private Button btn_searchmoney_search;
    private ListView lv_searchmoney;
    private List<String>list=new ArrayList<String>();
    private searchitemAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.searchmoney);
        initDate();
        initView();
    }

    private void initDate() {
        list.add("12434239856729834789");
        list.add("423423423");
        list.add("02384.00");
        list.add("2016-5-3");
        list.add("成功");
    }

    private void initView() {
        //include的布局里的控件title_bar.xmln
        spot_order_title = ((RelativeLayout) findViewById(R.id.title_searchmoney_top));
        spot_order_title.setBackgroundColor(getResources().getColor(R.color.textColor_expandable_listview_show));
        iv_title_bar_back = ((ImageView) findViewById(R.id.iv_title_bar_back));
        iv_title_bar_logo = ((ImageView) findViewById(R.id.iv_title_bar_logo));
        iv_title_bar_service = ((ImageView) findViewById(R.id.iv_title_bar_service));
        iv_title_bar_search = ((ImageView) findViewById(R.id.iv_title_bar_search));
        sv_title_bar_serachView = ((Button) findViewById(R.id.sv_title_bar_serachView));
        tv_title_bar_title  = ((TextView) findViewById(R.id.tv_title_bar_title));

        iv_title_bar_back.setVisibility(View.VISIBLE);
        iv_title_bar_logo.setVisibility(View.INVISIBLE);
        iv_title_bar_service.setVisibility(View.INVISIBLE);
        sv_title_bar_serachView.setVisibility(View.INVISIBLE);
        iv_title_bar_search.setVisibility(View.GONE);
        tv_title_bar_title.setText("出入金明细查询");
        tv_title_bar_title.setTextColor(Color.WHITE);
        iv_title_bar_back.setOnClickListener(this);

        tv_searchmoney_money= (TextView) findViewById(R.id.tv_searchmoney_current);
        tv_searchmoney_start= (TextView) findViewById(R.id.tv_searchmoney_start);
        tv_searchmoney_end= (TextView) findViewById(R.id.tv_searchmoney_end);
        lv_searchmoney= (ListView) findViewById(R.id.lv_searchmoney);
        btn_searchmoney_search= (Button) findViewById(R.id.btn_searchmoney_search);
        btn_searchmoney_search.setOnClickListener(this);
        tv_searchmoney_start.setOnClickListener(this);
        tv_searchmoney_end.setOnClickListener(this);
        lv_searchmoney.setOnItemClickListener(this);
        adapter=new searchitemAdapter(SearchMoney.this,list);
        lv_searchmoney.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_title_bar_back:
                finish();
                break;
            case R.id.tv_searchmoney_start:
                showDate(tv_searchmoney_start);
                break;
            case R.id.tv_searchmoney_end:
                showDate(tv_searchmoney_end);
                break;
            case R.id.btn_searchmoney_search:
                //搜索按钮
                Toast.makeText(SearchMoney.this,"点击搜索按钮",Toast.LENGTH_LONG).show();
                break;
        }
    }
    private void showDate(final TextView Tv){
        Calendar calend1 = Calendar.getInstance();
        calend1.setTimeInMillis(System.currentTimeMillis());
        int year = calend1.get(Calendar.YEAR);
        int month = calend1.get(Calendar.MONTH) + 1;
        int day = calend1.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog dialog3 = new DatePickerDialog(
                SearchMoney.this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view,
                                          int year, int monthOfYear,
                                          int dayOfMonth) {
                        Toast.makeText(SearchMoney.this,
                                "" + year + "-" + (monthOfYear + 1)
                                        + "-" + dayOfMonth , Toast.LENGTH_LONG).show();
                        Tv.setText(year + "-" + (monthOfYear + 1)
                                + "-" + dayOfMonth );
                    }
                }, year, month, day);
        dialog3.show();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(this,"点击了"+position+"条item",Toast.LENGTH_LONG).show();

    }

}

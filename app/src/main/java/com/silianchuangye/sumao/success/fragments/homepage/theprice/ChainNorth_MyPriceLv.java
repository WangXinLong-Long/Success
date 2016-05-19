package com.silianchuangye.sumao.success.fragments.homepage.theprice;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.custom.CustomListView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

//我的点价页点价listview跳转到这页
public class ChainNorth_MyPriceLv extends AppCompatActivity implements View.OnClickListener,AdapterView.OnItemClickListener{
    private ImageView img_MyPriceLv_back,img_MyPriceLv_cart;
    private TextView tv_MyPriceLv_title,tv_MyPriceLv_diqu,
            tv_MyPriceLv_sort,tv_MyPriceLv_price,tv_MyPriceLv_name,
            tv_MyPriceLv_telnum;
    private TextView tv_MyPriceLv_sort_title,tv_MyPriceLv_heyue,
            tv_MyPriceLv_date_start,tv_MyPriceLv_date_end,
          tv_MyPriceLv_small_start,
            tv_MyPriceLv_samll_num,tv_MyPriceLv_margin;
    private EditText tv_MyPriceLv_price_start,tv_MypriceLv_price_end,tv_MyPriceLv_mang_num ;
    private CustomListView lv_MyPriceLv_day_price;//显示今日价格的listview
    private ListView lv_MyPriceLv_heyue;
    private TextView tv_MyPriceLv_price_jiner;//保证金金额
    private Button btn_MyPriceLv_ok;
    private List<String> list=new ArrayList<String>();
    private List<String> list_sort=new ArrayList<String>();
    private ArrayAdapter<String>adapter;
    private ArrayAdapter<String>sort_adapter;
    private boolean flag=true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myprice_lv);
        initDate();
        initView();
    }

    private void initDate() {
        list.add("3月下旬交货=意向期货价+100元");
        list.add("4月下旬交货=意向期货价+120元");
        list.add("5月下旬交货=意向期货价+140元");
        list.add("6月下旬交货=意向期货价+160元");
        list.add("7月下旬交货=意向期货价+180元");

        for(int i=0;i<9;i++){
            list_sort.add("itemview"+i);
        }
    }

    private void initView() {
        img_MyPriceLv_back = (ImageView) findViewById(R.id.img_mypricelv_back);
        img_MyPriceLv_cart= (ImageView) findViewById(R.id.img_mypricelv_cart);
        tv_MyPriceLv_title= (TextView) findViewById(R.id.tv_mypricelv_title);
        tv_MyPriceLv_diqu= (TextView) findViewById(R.id.tv_mypricelv_diqu);
        tv_MyPriceLv_sort= (TextView) findViewById(R.id.tv_mypricelv_sort);
        tv_MyPriceLv_price= (TextView) findViewById(R.id.tv_mypricelv_price);
        tv_MyPriceLv_name= (TextView) findViewById(R.id.tv_mypricelv_name);
        tv_MyPriceLv_telnum= (TextView) findViewById(R.id.tv_mypricelv_telnum);
        tv_MyPriceLv_sort_title= (TextView) findViewById(R.id.tv_mypricelv_sorttitle);
        tv_MyPriceLv_heyue= (TextView) findViewById(R.id.tv_mypricelv_heyue_sort);
        tv_MyPriceLv_date_start= (TextView) findViewById(R.id.tv_mypricelv_start);
        tv_MyPriceLv_date_end= (TextView) findViewById(R.id.tv_mypricelv_end);
        tv_MyPriceLv_price_start= (EditText) findViewById(R.id.edt_mypricelv_price_start);
        tv_MypriceLv_price_end= (EditText) findViewById(R.id.edt_mypricelv_price_end);
        tv_MyPriceLv_mang_num= (EditText) findViewById(R.id.tv_mypricelv_mang_num);
        tv_MyPriceLv_small_start= (TextView) findViewById(R.id.tv_mypricelv_small_num);
        tv_MyPriceLv_samll_num= (TextView) findViewById(R.id.tv_mypricelv_small_unit);
        tv_MyPriceLv_margin= (TextView) findViewById(R.id.tv_mypricelv_margin);
        lv_MyPriceLv_day_price= (CustomListView) findViewById(R.id.lv_mypricelv_day);
        lv_MyPriceLv_heyue= (ListView) findViewById(R.id.lv_mypricelv_heyue);
        tv_MyPriceLv_price_jiner= (TextView) findViewById(R.id.tv_mypricelv_jiner);
        btn_MyPriceLv_ok= (Button) findViewById(R.id.btn_mypricelv_ok);
        adapter=new ArrayAdapter<String>(this,R.layout.item_chinanorth_mypricelv,R.id.tv_item_myprivatelv,list);
        lv_MyPriceLv_day_price.setAdapter(adapter);
        sort_adapter=new ArrayAdapter<String>(this,R.layout.item_chinanorth_mypricelv,R.id.tv_item_myprivatelv,list_sort);
        tv_MyPriceLv_heyue.setText(sort_adapter.getItem(0));
        lv_MyPriceLv_heyue.setAdapter(sort_adapter);

        tv_MyPriceLv_heyue.setOnClickListener(this);
        img_MyPriceLv_back.setOnClickListener(this);
        img_MyPriceLv_cart.setOnClickListener(this);
        btn_MyPriceLv_ok.setOnClickListener(this);
        lv_MyPriceLv_heyue.setOnItemClickListener(this);
        tv_MyPriceLv_date_start.setOnClickListener(this);
        tv_MyPriceLv_date_end.setOnClickListener(this);
        String title=getIntent().getStringExtra("title");
        String diqu=getIntent().getStringExtra("diqu");
        String sort=getIntent().getStringExtra("sort");
        String price=getIntent().getStringExtra("price");
        String name=getIntent().getStringExtra("name");
        String telnum=getIntent().getStringExtra("telnum");
        String sorttitle=getIntent().getStringExtra("sorttitle");
        tv_MyPriceLv_title.setText(title);
        tv_MyPriceLv_diqu.setText(diqu);
        tv_MyPriceLv_sort.setText(sort);
        tv_MyPriceLv_price.setText(price);
        tv_MyPriceLv_name.setText(name);
        tv_MyPriceLv_telnum.setText(telnum);
        tv_MyPriceLv_sort_title.setText(sorttitle);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_mypricelv_back:
                finish();
                break;
            case R.id.img_mypricelv_cart:
                //跳转到购物车
                break;
            case R.id.tv_mypricelv_heyue_sort:
                if(flag){
                    lv_MyPriceLv_heyue.setVisibility(View.VISIBLE);
                    flag=false;
                }else{
                    lv_MyPriceLv_heyue.setVisibility(View.GONE);
                    flag=true;
                }
                break;
            case R.id.tv_mypricelv_start:
                showDate(tv_MyPriceLv_date_start);
                break;
            case R.id.tv_mypricelv_end:
                showDate(tv_MyPriceLv_date_end);
                break;
            case R.id.btn_mypricelv_ok:
                //跳转到支付保证金界面
                Intent intent=new Intent(ChainNorth_MyPriceLv.this,ChinaNorth_Margin.class);
                intent.putExtra("margin_price",tv_MyPriceLv_price_jiner.getText().toString());
                intent.putExtra("num",tv_MyPriceLv_mang_num.getText().toString());
                startActivity(intent);
                break;
        }
    }
    //显示日期
    private void showDate(final TextView Tv){
        Calendar calend1 = Calendar.getInstance();
        calend1.setTimeInMillis(System.currentTimeMillis());
        int year = calend1.get(Calendar.YEAR);
        int month = calend1.get(Calendar.MONTH) + 1;
        int day = calend1.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog dialog3 = new DatePickerDialog(
                ChainNorth_MyPriceLv.this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view,
                                          int year, int monthOfYear,
                                          int dayOfMonth) {
                        Toast.makeText(ChainNorth_MyPriceLv.this,
                                "" + year + "-" + (monthOfYear + 1)
                                        + "-" + dayOfMonth, Toast.LENGTH_LONG).show();
                        Tv.setText(year + "-" + (monthOfYear + 1)
                                + "-" + dayOfMonth);
                    }
                }, year, month, day);
        dialog3.show();
    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        tv_MyPriceLv_heyue.setText(sort_adapter.getItem(position));
        lv_MyPriceLv_heyue.setVisibility(View.GONE);
    }
}

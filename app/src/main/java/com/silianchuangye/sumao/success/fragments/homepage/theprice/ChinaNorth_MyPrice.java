package com.silianchuangye.sumao.success.fragments.homepage.theprice;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.silianchuangye.sumao.success.MainActivity;
import com.silianchuangye.sumao.success.R;

import java.util.ArrayList;
import java.util.List;

//我的点价规则界面
public class ChinaNorth_MyPrice extends AppCompatActivity implements View.OnClickListener,AdapterView.OnItemClickListener{
    private ImageView img_myprice_back,img_myprice_cart;
    private TextView tv_myprice_diqu,tv_myprice_sort,
            tv_myprice_price,tv_myprice_name,
            tv_myprice_telnum,tv_myprice_title;
    private ListView lv_myprivce;
    private List<String> list=new ArrayList<String>();
    private ArrayAdapter<String>adapter;
    private RelativeLayout relative_chinanorth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chinanorth_mypricee);
        initView();
        initDate();
    }

    private void initDate() {
       list.add("L 1603");
        list.add("L 1607");
        list.add("L 1613");
    }

    private void initView() {
        img_myprice_back= (ImageView) findViewById(R.id.img_myprice_back);
        img_myprice_cart= (ImageView) findViewById(R.id.img_myprice_cart);
        tv_myprice_diqu= (TextView) findViewById(R.id.tv_myprice_diqu);
        tv_myprice_sort= (TextView) findViewById(R.id.tv_myprice_sort);
        tv_myprice_price= (TextView) findViewById(R.id.tv_myprice_price);
        tv_myprice_name= (TextView) findViewById(R.id.tv_myprice_name);
        tv_myprice_telnum= (TextView) findViewById(R.id.tv_myprice_telnum);
        tv_myprice_title= (TextView) findViewById(R.id.tv_myprice_title);
        lv_myprivce= (ListView) findViewById(R.id.lv_myprice_bottem);
        relative_chinanorth= (RelativeLayout) findViewById(R.id.relative_chinanorth);

        img_myprice_back.setOnClickListener(this);
        img_myprice_cart.setOnClickListener(this);
        lv_myprivce.setOnItemClickListener(this);
        relative_chinanorth.setOnClickListener(this);
        //接收道传递回来的数据
        Intent intent=this.getIntent();
        String str=intent.getStringExtra("name");
        tv_myprice_title.setText(str);

        adapter=new ArrayAdapter<String>(this,R.layout.item_myprice,R.id.tv_item_myprice,list);
        lv_myprivce.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_myprice_back:
                finish();
                break;
            case R.id.img_myprice_cart:
                //跳转到购物车界面
                Intent intent = new Intent(ChinaNorth_MyPrice.this,MainActivity.class);
                intent.putExtra("cart", 1);
                startActivity(intent);
                Log.e("TAG","发送广播");
                break;
            case R.id.relative_chinanorth:
                Intent intent2=new Intent(ChinaNorth_MyPrice.this,ChinaNorth_Price.class);
                startActivity(intent2);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //跳转到我的点价界面
        Intent intent=new Intent(ChinaNorth_MyPrice.this,ChainNorth_MyPriceLv.class);
        intent.putExtra("title",tv_myprice_title.getText().toString());
        intent.putExtra("diqu",tv_myprice_diqu.getText().toString());
        intent.putExtra("sort",tv_myprice_sort.getText().toString());
        intent.putExtra("price",tv_myprice_price.getText().toString());
        intent.putExtra("name",tv_myprice_name.getText().toString());
        intent.putExtra("telnum",tv_myprice_telnum.getText().toString());
        intent.putExtra("sorttitle",adapter.getItem(position).toString());
        startActivity(intent);
    }
}

package com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.firmInfomation;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.receiptAddress.SelectCityAreaMVP.view.SelectCityArea;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.receiptAddress.SelectCountyAreaMVP.view.SelectCountyArea;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.receiptAddress.SelectProvinceAreaMVP.view.SelectProvinceArea;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.register.RegisterValueActivity;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

public class FirmActivity extends AppCompatActivity {
    ImageView iv_title_bar_logo,
            iv_title_bar_back,
            iv_title_bar_service,
            add_address,
            iv_title_bar_search;
    Button sv_title_bar_serachView;
    TextView tv_title_bar_title,tv,tvUpdate;
    RelativeLayout layoutTop,add_address_rl;
    private ListView lv_Demo;
    private RelativeLayout layout_bottom;
    private TextView tv_xiangsi_dizhi;
    private Button bt_save;
    private List<Map<String,Object>>  list;
    private SimpleAdapter adapter;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firm);
        title_Bar();
       // tv_xiangsi_dizhi= (TextView) findViewById(R.id.tv_xiangsi_dizhi);
        list=new ArrayList<Map<String,Object>>();
        lv_Demo= (ListView) findViewById(R.id.lv_province);
        Map<String,Object> map1=new Hashtable<String,Object>();
        map1.put("text","省");
        map1.put("minute","");
        map1.put("icon",R.mipmap.my_sumao_iv_order);
        map1.put("pass","");
        list.add(map1);
        Map<String,Object> map2=new Hashtable<String,Object>();
        map2.put("text","市");
        map2.put("minute","");
        map2.put("icon",R.mipmap.my_sumao_iv_order);
        map2.put("pass","");
        list.add(map2);
        Map<String,Object> map3=new Hashtable<String,Object>();
        map3.put("text","县(区)");
        map3.put("minute","");
        map3.put("icon",R.mipmap.my_sumao_iv_order);
        map3.put("pass","");

        list.add(map3);
         adapter=new SimpleAdapter(this,list,R.layout.item_firm_info,
                new String[]{"text","minute","icon","pass"},
                new int[]{R.id.tv_firm_info,R.id.tvValue_firm_info,R.id.ivMore_firm_info,R.id.tvPass_firm_info});
        lv_Demo.setAdapter(adapter);
        lv_Demo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position==0){
                    //跳到省级界面
                    Intent intent=new Intent(FirmActivity.this, SelectProvinceArea.class);
                    startActivityForResult(intent,0);
                }else if (position==1){
                    //跳到市级界面
                    Intent intent=new Intent(FirmActivity.this, SelectCityArea.class);
                    startActivityForResult(intent,1);
                }else if (position==2){
                    //跳到区县级界面
                    Intent intent=new Intent(FirmActivity.this, SelectCountyArea.class);
                    startActivityForResult(intent,2);
                }
            }
        });
       // layout_bottom= (RelativeLayout) findViewById(R.id.layout_bottom);
       // layout_bottom.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //跳到详细地址页
//                Intent intent=new Intent(FirmActivity.this,RegisterValueActivity.class);
//                intent.putExtra("title","地址详情");
//                intent.putExtra("pass","");
//                intent.putExtra("content","");
//                startActivityForResult(intent,3);
//            }
//        });
        bt_save= (Button) findViewById(R.id.bt_save);
        bt_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                String address=list.get(0).get("minute").toString()+list.get(1).get("minute").toString()+list.get(2).get("minute").toString()+tv_xiangsi_dizhi.getText().toString();
                Log.d("地址",address);
                intent.putExtra("address",address);
                setResult(4,intent);
                FirmActivity.this.finish();
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case 0:
                String sheng=data.getStringExtra("sheng");
                Log.d("接收过来的省",sheng);
                list.get(0).put("minute",sheng);
                adapter.notifyDataSetChanged();
                break;
            case 1:
                String city=data.getStringExtra("city");
                Log.d("接收过来的市",city);
                list.get(1).put("minute",city);
                adapter.notifyDataSetChanged();
                break;
            case 2:
                String xianqu=data.getStringExtra("xianqu");
                list.get(2).put("minute",xianqu);
                Log.d("接受过来的区县",xianqu);
                adapter.notifyDataSetChanged();
                break;
//            case 3:
//                String xiangxi=data.getStringExtra("name");
//                tv_xiangsi_dizhi.setText(xiangxi);
//                break;
        }
    }

    public void title_Bar(){

        iv_title_bar_back = ((ImageView) findViewById(R.id.iv_title_bar_back));
        iv_title_bar_logo = ((ImageView) findViewById(R.id.iv_title_bar_logo));
        iv_title_bar_service = ((ImageView) findViewById(R.id.iv_title_bar_service));
        iv_title_bar_search = ((ImageView) findViewById(R.id.iv_title_bar_search));
        sv_title_bar_serachView = ((Button) findViewById(R.id.sv_title_bar_serachView));
        tv_title_bar_title  = ((TextView) findViewById(R.id.tv_title_bar_title));

        tvUpdate= (TextView) findViewById(R.id.tvUpdate);
        iv_title_bar_logo.setVisibility(View.GONE);
        iv_title_bar_service.setVisibility(View.GONE);
        sv_title_bar_serachView.setVisibility(View.GONE);
        iv_title_bar_search.setVisibility(View.GONE);

        tvUpdate.setVisibility(View.GONE);
        tv_title_bar_title.setText("注册账号");


        tv_title_bar_title.setTextColor(Color.WHITE);
        iv_title_bar_back.setVisibility(View.VISIBLE);
        layoutTop= (RelativeLayout) findViewById(R.id.layoutTop_register);
        layoutTop.setBackgroundColor(getResources().getColor(R.color.textColor_expandable_listview_show));

        iv_title_bar_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirmActivity.this.finish();
            }
        });

        intent = new Intent();

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            intent.putExtra("address", "");
            FirmActivity.this.setResult(RESULT_OK, intent);
        }
        return super.onKeyDown(keyCode, event);
    }
}

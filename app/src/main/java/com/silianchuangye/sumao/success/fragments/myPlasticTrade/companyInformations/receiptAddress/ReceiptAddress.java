package com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.receiptAddress;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.adapter.ReceiptAddressAdapter;
import com.silianchuangye.sumao.success.model.ReceiptAddressModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/5/10 0010.
 */
public class ReceiptAddress extends Activity implements View.OnClickListener{

    ImageView iv_title_bar_logo,
            iv_title_bar_back,
            iv_title_bar_service,
            iv_title_bar_search;
    Button sv_title_bar_serachView;
    TextView tv_title_bar_title,tv;
    RelativeLayout receip_address_title;
    RelativeLayout add_address_rl;
    ListView listView;
    ReceiptAddressModel receiptAddressModel;
    List<ReceiptAddressModel> lists;
    ReceiptAddressAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receipt_address);

        receip_address_title = ((RelativeLayout) findViewById(R.id.receip_address_title));
        receip_address_title.setBackgroundColor(getResources().getColor(R.color.textColor_expandable_listview_show));

        iv_title_bar_back = ((ImageView) findViewById(R.id.iv_title_bar_back));
        iv_title_bar_logo = ((ImageView) findViewById(R.id.iv_title_bar_logo));
        iv_title_bar_service = ((ImageView) findViewById(R.id.iv_title_bar_service));
        iv_title_bar_search = ((ImageView) findViewById(R.id.iv_title_bar_search));
        sv_title_bar_serachView = ((Button) findViewById(R.id.sv_title_bar_serachView));
        tv_title_bar_title  = ((TextView) findViewById(R.id.tv_title_bar_title));
        add_address_rl = ((RelativeLayout) findViewById(R.id.add_address_rl));

        iv_title_bar_logo.setVisibility(View.INVISIBLE);
        iv_title_bar_service.setVisibility(View.INVISIBLE);
        sv_title_bar_serachView.setVisibility(View.INVISIBLE);
        add_address_rl.setVisibility(View.VISIBLE);
        iv_title_bar_search.setVisibility(View.INVISIBLE);
        tv_title_bar_title.setText("收货地址");
        tv_title_bar_title.setTextColor(Color.WHITE);
        add_address_rl.setOnClickListener(this);
        iv_title_bar_back.setOnClickListener(this);
        listView = ((ListView) findViewById(R.id.receipt_address_listView));
        lists = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            receiptAddressModel = new ReceiptAddressModel();
            receiptAddressModel.setAddress("北京市"+i+"区");
            if (i!=3){
                receiptAddressModel.setFixedTelephone("110");
            }else {
                receiptAddressModel.setFixedTelephone("");
            }

            receiptAddressModel.setName("张三"+i+"号");
            /**
             * 判断是否是默认地址，若是，直接就是删除，若不是，则可以设置为默认地址，也可以删除
             */
            if (i==0)
            {
                receiptAddressModel.setState(true);
            }else
            {
                receiptAddressModel.setState(false);
            }
            receiptAddressModel.setTelephone("123456"+i);
            receiptAddressModel.setZipCode("014400");
            lists.add(receiptAddressModel);
        }

        adapter = new ReceiptAddressAdapter(this,lists);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(ReceiptAddress.this,"dianji"+position+"条",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                intent.setClass(ReceiptAddress.this,ReceiptAddressDetail.class);
                bundle.putBoolean("state",lists.get(position).isState());
                bundle.putString("name",lists.get(position).getName());
                bundle.putString("address",lists.get(position).getAddress());
                bundle.putString("zipCode",lists.get(position).getZipCode());
                bundle.putString("telephone",lists.get(position).getTelephone());
                bundle.putString("fixedTelephone",lists.get(position).getFixedTelephone());
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.add_address_rl:
                Intent intent = new Intent();
                intent.setClass(ReceiptAddress.this,AddAddress.class);
                startActivity(intent);
                break;
            case R.id.iv_title_bar_back:
                finish();
                break;
            default:
                break;
        }
    }
}

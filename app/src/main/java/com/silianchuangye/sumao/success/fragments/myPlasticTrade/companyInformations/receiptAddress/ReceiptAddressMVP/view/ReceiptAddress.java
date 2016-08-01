package com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.receiptAddress.ReceiptAddressMVP.view;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
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
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.addressDisplayMVP.presenter.AddressDisplayPresenter;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.addressDisplayMVP.view.IAddressDisplayView;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.receiptAddress.AddAddress;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.receiptAddress.ReceiptAddressDetail;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.receiptAddress.ReceiptAddressMVP.bean.ReAddress;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.receiptAddress.ReceiptAddressMVP.presenter.ReceiptAddressPresenter;
import com.silianchuangye.sumao.success.utils.LogUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/5/10 0010.
 */
public class ReceiptAddress extends Activity implements View.OnClickListener,IReceiptAddressView,IAddressDisplayView {

    ImageView iv_title_bar_logo,
            iv_title_bar_back,
            iv_title_bar_service,
            iv_title_bar_search;
    Button sv_title_bar_serachView;
    TextView tv_title_bar_title,tv;
    RelativeLayout receip_address_title;
    RelativeLayout add_address_rl;
    ListView listView;
    List<ReAddress> lists;
    ReceiptAddressAdapter adapter;
    ReceiptAddressPresenter presenter;

    String addressDisplay;
    List<String> addressDisplays;
    private AddressDisplayPresenter addressDisplayPresenter;
    private String[] addressDis;
    int position=0;


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
        presenter = new ReceiptAddressPresenter(this);


        SharedPreferences sp=getSharedPreferences("sumao",Activity.MODE_PRIVATE);
        String unique=sp.getString("unique","");
        presenter.setReceiptAddressListView(unique);
        LogUtils.log("address----->"+lists.size()+"--------------------->");
        addressDisplayPresenter = new AddressDisplayPresenter(this);

//
//        LogUtils.log("address----->"+lists.get(0).getProvince()+"--------------------->");
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(ReceiptAddress.this,"dianji"+position+"条",Toast.LENGTH_SHORT).show();

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

    @Override
    public void initReceiptAddressListView(List<ReAddress> address) {
        lists = address;
//        LogUtils.log("address----->"+lists.size()+"--------------------->");
        LogUtils.log("ReceiptAddress--->lists.size();----->"+lists.size());
        addressDis = new String[lists.size()];
        for (int i = 0; i < lists.size(); i++) {
            LogUtils.log("ReceiptAddress--->addressDisplay.size()----->"+lists.get(i).getAddress());
            addressDisplayPresenter.setDetailAddress(lists.get(i).getProvince(),lists.get(i).getCity(),lists.get(i).getCounty());
        }

    }

    @Override
    public void onReceiptAddressListViewClick(int position) {
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        intent.setClass(ReceiptAddress.this,ReceiptAddressDetail.class);
        bundle.putString("state",lists.get(position).getAddressType());
        bundle.putString("name",lists.get(position).getName());
        bundle.putString("address",lists.get(position).getAddress());
        bundle.putString("zipCode",lists.get(position).getZipCode());
        bundle.putString("telephone",lists.get(position).getMobile());
        bundle.putString("fixedTelephone",lists.get(position).getPhone());
        intent.putExtras(bundle);
        startActivity(intent);

    }

    @Override
    public void setAddressDisplay(String display) {
        LogUtils.log("ReceiptAddress--->setAddressDisplay----->"+display);
        addressDisplay = display;
        LogUtils.log("ReceiptAddress---> addressDisplay----->"+  addressDisplay);
        addressDis[position++] = addressDisplay;
        for (String s:addressDis){

            LogUtils.log("ReceiptAddress---> addressDisplay----->"+  s);
        }
        if (position == lists.size()){
            adapter = new ReceiptAddressAdapter(this,lists,addressDis);
            listView.setAdapter(adapter);
        }
    }
}

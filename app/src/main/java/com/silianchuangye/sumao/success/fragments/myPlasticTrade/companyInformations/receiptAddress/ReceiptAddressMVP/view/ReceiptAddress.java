package com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.receiptAddress.ReceiptAddressMVP.view;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.adapter.ReceiptAddressAdapter;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.addressDisplayMVP.presenter.AddressDisplayPresenter;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.addressDisplayMVP.view.IAddressDisplayView;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.receiptAddress.AddAddressMVP.view.AddAddress;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.receiptAddress.ReceiptAddressDetailMVP.view.ReceiptAddressDetail;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.receiptAddress.ReceiptAddressMVP.bean.ReAddress;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.receiptAddress.ReceiptAddressMVP.presenter.ReceiptAddressPresenter;
import com.silianchuangye.sumao.success.utils.LogUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/5/10 0010.
 */
public class ReceiptAddress extends Activity implements View.OnClickListener, IReceiptAddressView, IAddressDisplayView {

    ImageView iv_title_bar_logo,
            iv_title_bar_back,
            iv_title_bar_service,
            iv_title_bar_search;
    Button sv_title_bar_serachView;
    TextView tv_title_bar_title, tv;
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
    int position = 0;
    int count = 0;
    private String unique;
    private ArrayList<String> al;

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
        tv_title_bar_title = ((TextView) findViewById(R.id.tv_title_bar_title));
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
        al = new ArrayList<>();

        SharedPreferences sp = getSharedPreferences("sumao", Activity.MODE_PRIVATE);
        unique = sp.getString("unique", "");

        LogUtils.log("address----->" + lists.size() + "--------------------->");
        addressDisplayPresenter = new AddressDisplayPresenter(this);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                presenter.onReceiptAddressListViewClick(position);

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add_address_rl:
                Intent intent = new Intent();
                intent.setClass(ReceiptAddress.this, AddAddress.class);
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
        LogUtils.log("ReceiptAddress--->lists.size();----->" + lists.size());
        for (int i = 0; i < address.size(); i++) {
            StringBuilder builder = new StringBuilder();
            builder.append(lists.get(i).getProvince());
            builder.append( lists.get(i).getCity());
            builder.append(lists.get(i).getCounty());
        }
        addressDis = new String[lists.size()];
        for (int i = 0; i < lists.size(); i++) {
            LogUtils.log("ReceiptAddress--->addressDisplay.size()----->" + lists.get(i).getAddress());
            addressDisplayPresenter.setDetailAddress(lists.get(i).getProvince(), lists.get(i).getCity(), lists.get(i).getCounty(),i);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        LogUtils.log("onResume--->");
        lists.clear();
        presenter.setReceiptAddressListView(unique);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        LogUtils.log("onRestart--->");
//        lists.clear();
        presenter.setReceiptAddressListView(unique);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onReceiptAddressListViewClick(int position) {
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        intent.setClass(ReceiptAddress.this, ReceiptAddressDetail.class);
        bundle.putString("sessionId", unique);
        bundle.putString("addressType",  lists.get(position).getAddressType());
        bundle.putString("id", lists.get(position).getId());
        bundle.putString("state", lists.get(position).getAddressType());
        bundle.putString("name", lists.get(position).getName());
        bundle.putString("address", lists.get(position).getAddress());
        bundle.putString("displayAddress",addressDis[position]);
        bundle.putString("zipCode", lists.get(position).getZipCode());
        bundle.putString("telephone", lists.get(position).getMobile());
        bundle.putString("fixedTelephone", lists.get(position).getPhone());
        intent.putExtras(bundle);
        startActivity(intent);

    }

    @Override
    public void setAddressDisplay(String display,int position) {
        addressDisplay = display;
        addressDis[position] = addressDisplay;
        count++;
        if (count == lists.size()) {
            adapter = new ReceiptAddressAdapter(this, lists, addressDis/*al*/);
            listView.setAdapter(adapter);
            count = 0;
        }
//
    }
}

package com.silianchuangye.sumao.success.fragments.myPlasticTrade.PhysicalDistributionManagement.logistics;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.adapter.LogisticsSelectAddress_Adapter;
import com.silianchuangye.sumao.success.adapter.ReceiptAddressAdapter;
import com.silianchuangye.sumao.success.fragments.bean.Logistics_SelectAddress_Info;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.addressDisplayMVP.presenter.AddressDisplayPresenter;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.addressDisplayMVP.view.IAddressDisplayView;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.receiptAddress.ReceiptAddressMVP.bean.ReAddress;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.receiptAddress.ReceiptAddressMVP.presenter.ReceiptAddressPresenter;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.receiptAddress.ReceiptAddressMVP.view.IReceiptAddressView;
import com.silianchuangye.sumao.success.utils.LogUtils;
import com.silianchuangye.sumao.success.utils.SuMaoConstant;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

public class Logistics_SelectAddress extends AppCompatActivity implements View.OnClickListener,AdapterView.OnItemClickListener,IReceiptAddressView ,IAddressDisplayView {
    private ImageView img_back;
    private TextView tv_manager;
    private ListView lv;
    private List<Logistics_SelectAddress_Info> list=new ArrayList<Logistics_SelectAddress_Info>();
    private LogisticsSelectAddress_Adapter adater;
    List<ReAddress> lists;
    private String[] addressDis;
    ReceiptAddressPresenter presenter;
    private AddressDisplayPresenter addressDisplayPresenter;
    private String unique;
    String addressDisplay;
    int count = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logistics__select_address);
        SharedPreferences sp = getSharedPreferences("sumao", Activity.MODE_PRIVATE);
        unique = sp.getString("unique", "");
        lists = new ArrayList<>();
        addressDisplayPresenter = new AddressDisplayPresenter(this);
        presenter = new ReceiptAddressPresenter(this);
        presenter.setReceiptAddressListView(unique);
//        initDate();
        initView();
    }


    private void initView() {
        img_back= (ImageView) findViewById(R.id.img_logistics_title_bar_back);
        tv_manager= (TextView) findViewById(R.id.tv_logistics_title_bar);
        lv= (ListView) findViewById(R.id.lv_logistics_select);
        img_back.setOnClickListener(this);
        tv_manager.setOnClickListener(this);
        lv.setOnItemClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_logistics_title_bar_back:
                finish();
                break;
            case R.id.tv_logistics_title_bar:
                Toast.makeText(this,"管理",Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        presenter.onReceiptAddressListViewClick(position);

    }

    @Override
    public void initReceiptAddressListView(List<ReAddress> address) {
        lists = address;
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
    public void onReceiptAddressListViewClick(int position) {
        Intent intent=new Intent();
        intent.putExtra("address",list.get(position).tv_address_title);
        intent.putExtra("address_message",list.get(position).tv_address_message);
        intent.putExtra("address_number",lists.get(position).getCounty());
        intent.putExtra("address_people",list.get(position).tv_address_name);
        intent.putExtra("address_phoneNum",list.get(position).tv_address_phone_num);
        intent.setAction("select");
        sendBroadcast(intent);
        finish();
    }

    @Override
    public void setAddressDisplay(String display, int position) {
        addressDisplay = display;
        addressDis[position] = addressDisplay;
        count++;
        Logistics_SelectAddress_Info info=new Logistics_SelectAddress_Info();
        info.tv_address_title=addressDisplay;
        info.tv_address_message=lists.get(position).getAddress();
        info.tv_address_name=lists.get(position).getName();
        info.tv_address_phone_num=lists.get(position).getMobile();
        list.add(info);
        if (count == lists.size()) {
            adater=new LogisticsSelectAddress_Adapter(list,this);
            lv.setAdapter(adater);
            count = 0;
        }
    }
}

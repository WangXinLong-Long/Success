package com.silianchuangye.sumao.success.fragments.homepage.auction;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.adapter.MyPageAdapter;
import com.silianchuangye.sumao.success.fragments.homepage.auction.AuctionMVP.bean.AuctionDetailBean;
import com.silianchuangye.sumao.success.fragments.homepage.goodInStock.goodsInStockDetail.GoodsInStockDetailActivityMVP.bean.GoodsInStockDetailBean;
import com.silianchuangye.sumao.success.fragments.homepage.groupbuying.groupBuyingDetailMVP.bean.GroupBuyingDetailBean;
import com.silianchuangye.sumao.success.utils.LogUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class VesselThreeActivity extends AppCompatActivity {
    private ImageView ivBack_vessel_three;
    private ImageView ivShop_vessel_three;
    private TabLayout tab_vessel_three;
    private ViewPager vp_vessel_three;
    private ArrayList<Fragment> list;
    private MyPageAdapter adapter;
    private  List<GoodsInStockDetailBean.ClAttributeBean> cl_attribute;
    private TextView activity_vessel_three_title;
    private String contract;
    private AuctionDetailBean auctionDetailBean;
    private GroupBuyingDetailBean groupBuyingDetailBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vessel_three);
        activity_vessel_three_title = ((TextView) findViewById(R.id.activity_vessel_three_title));
        Intent intent = getIntent();
        cl_attribute = ( List<GoodsInStockDetailBean.ClAttributeBean>) intent.getSerializableExtra("cl_attribute");
//        标题
        String title = intent.getStringExtra("title");
//        合同
        contract = intent.getStringExtra("contract");
//        竞拍详情的数据
        if (title.equals("团购")){

            groupBuyingDetailBean = (GroupBuyingDetailBean) intent.getSerializableExtra("auctionDetailBean");
        }else {
            auctionDetailBean = (AuctionDetailBean) intent.getSerializableExtra("auctionDetailBean");
        }

        LogUtils.log(contract+"-------WXL合同页VesselThreeActivity");
        LogUtils.log("VesselThreeActivity--->cl_attribute:"+cl_attribute);
        activity_vessel_three_title.setText(title);

        Bundle bundle = new Bundle();
        bundle.putSerializable("cl_attribute",(Serializable) cl_attribute);
        if (title.equals("团购")){
            bundle.putString("type","group");
            bundle.putSerializable("auctionDetailBean", groupBuyingDetailBean);

        }else {
            bundle.putString("type","auction");
            bundle.putSerializable("auctionDetailBean", auctionDetailBean);
        }
        ParameterFragment pf =  new ParameterFragment();
        pf.setArguments(bundle);

        ivBack_vessel_three= (ImageView) findViewById(R.id.ivBack_vessel_three);
        ivBack_vessel_three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VesselThreeActivity.this.finish();
            }
        });
        ivShop_vessel_three= (ImageView) findViewById(R.id.ivShop_vessel_three);
        ivShop_vessel_three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转购物车界面
            }
        });
        Bundle bundle1= new Bundle();
        ContractFragment contractFragment = new ContractFragment();
        bundle1.putString("contract",contract);
        contractFragment.setArguments(bundle1);
        list=new ArrayList<Fragment>();
        list.add(contractFragment);
        list.add(pf);
       // list.add(new PhotographFragment());
        adapter=new MyPageAdapter(getSupportFragmentManager());
        adapter.setData(list);

        ArrayList<String> listString=new ArrayList<String>();
        listString.add("合同详情");
        listString.add("产品参数");
//        listString.add("量价图");
        adapter.setTitles(listString);
        vp_vessel_three= (ViewPager) findViewById(R.id.vp_vessel_three);
        tab_vessel_three= (TabLayout) findViewById(R.id.tab_vessel_three);
        vp_vessel_three.setAdapter(adapter);
        tab_vessel_three.setupWithViewPager(vp_vessel_three );



    }
}

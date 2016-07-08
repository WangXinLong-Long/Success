package com.silianchuangye.sumao.success.fragments.myPlasticTrade.PhysicalDistributionManagement.logistics;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.Toast;

import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.adapter.CreateLogisticsAdapter;
import com.silianchuangye.sumao.success.fragments.bean.Createlogistics_ExpandInfo;
import com.silianchuangye.sumao.success.fragments.bean.Createlogistics_ListInfo;

import java.util.ArrayList;
import java.util.List;

public class CreateLogistics extends AppCompatActivity implements View.OnClickListener{
private ExpandableListView expand_lv_create_logistics;
    private ImageView img_create_logistics_back;
    private ImageView img_create_logistics_search;
    private ImageView img_create_logistics_allselect;
    private Button btn_create_logistics_ok;
    private List<Createlogistics_ExpandInfo> expandList=new ArrayList<Createlogistics_ExpandInfo>();
    private List<List<Createlogistics_ListInfo>> allList=new ArrayList<List<Createlogistics_ListInfo>>();
    private CreateLogisticsAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_logistics);
        initDate();
        initView();
    }

    private void initDate() {
        Createlogistics_ExpandInfo expandInfo1=new Createlogistics_ExpandInfo();
        expandInfo1.company_name="中国石油天然气股份有限公司";
        expandInfo1.date="2016-06-21";
        expandInfo1.order_name="张一";
        expandInfo1.order_num="100000000000";
        Createlogistics_ExpandInfo expandInfo2=new Createlogistics_ExpandInfo();
        expandInfo2.company_name="中国石油天然气股份有限公司";
        expandInfo2.date="2016-06-22";
        expandInfo2.order_name="张二";
        expandInfo2.order_num="120000000000";
        Createlogistics_ExpandInfo expandInfo3=new Createlogistics_ExpandInfo();
        expandInfo3.company_name="中国石油天然气股份有限公司";
        expandInfo3.date="2016-06-23";
        expandInfo3.order_name="张三";
        expandInfo3.order_num="100300000000";

        Createlogistics_ListInfo listInfo1=new Createlogistics_ListInfo();
        listInfo1.can_num=10;
        listInfo1.cangku_name="迅邦物流一号库";
        listInfo1.date="2016-2-2";
        listInfo1.logistics_name="买家自提";
        listInfo1.num=10;
        listInfo1.only_price=6100;
        listInfo1.product_name="兰州石化7024";
        listInfo1.sort="LLDPE";

        Createlogistics_ListInfo listInfo2=new Createlogistics_ListInfo();
        listInfo2.can_num=10;
        listInfo2.cangku_name="迅邦物流一号库";
        listInfo2.date="2016-2-2";
        listInfo2.logistics_name="买家自提";
        listInfo2.num=10;
        listInfo2.only_price=6100;
        listInfo2.product_name="兰州石化7024";
        listInfo2.sort="LLDPE";

        List<Createlogistics_ListInfo> listList2=new ArrayList<Createlogistics_ListInfo>();
        Createlogistics_ListInfo listInfo3=new Createlogistics_ListInfo();
        listInfo3.can_num=10;
        listInfo3.cangku_name="迅邦物流一号库";
        listInfo3.date="2016-2-2";
        listInfo3.logistics_name="买家自提";
        listInfo3.num=10;
        listInfo3.only_price=6100;
        listInfo3.product_name="兰州石化7024";
        listInfo3.sort="LLDPE";

        expandInfo1.list.add(listInfo1);
        expandInfo1.list.add(listInfo2);

        expandInfo2.list.add(listInfo1);
        expandInfo2.list.add(listInfo2);

        expandInfo3.list.add(listInfo1);
        expandInfo3.list.add(listInfo2);
        expandInfo3.list.add(listInfo3);

        expandList.add(expandInfo1);
        expandList.add(expandInfo2);
        expandList.add(expandInfo3);
    }

    private void initView() {
        img_create_logistics_back= (ImageView) findViewById(R.id.img_create_logistics_back);
        img_create_logistics_search= (ImageView) findViewById(R.id.img_create_logistics_search);
        img_create_logistics_allselect= (ImageView) findViewById(R.id.img_create_logistics_allselsect);
        btn_create_logistics_ok= (Button) findViewById(R.id.btn_create_logistics_ok);
        expand_lv_create_logistics= (ExpandableListView) findViewById(R.id.create_logistics_expand_lv);
        //去掉expandListview的特别的下拉标志
        expand_lv_create_logistics.setGroupIndicator(null);
        //去掉ListView之间的线
//        expand_lv_create_logistics.setDivider(null);

        adapter=new CreateLogisticsAdapter(this,expandList);
        expand_lv_create_logistics.setAdapter(adapter);
        if(adapter!=null && expandList!=null){
            for (int i = 0; i < adapter.getGroupCount(); i++) {
                expand_lv_create_logistics.expandGroup(i);
            }}
        img_create_logistics_back.setOnClickListener(this);
        img_create_logistics_search.setOnClickListener(this);
        img_create_logistics_allselect.setOnClickListener(this);
        btn_create_logistics_ok.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_create_logistics_back:
                finish();
                break;
            case R.id.img_create_logistics_search:
                Toast.makeText(this,"显示pop",Toast.LENGTH_SHORT).show();
                break;
            case R.id.img_create_logistics_allselsect:
                Toast.makeText(this,"全选",Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_create_logistics_ok:
                Toast.makeText(this,"创建物流需求",Toast.LENGTH_SHORT).show();
                break;
        }
    }
}

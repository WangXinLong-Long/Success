package com.silianchuangye.sumao.success.fragments.homepage.UpstreamDirectSellingMVP.UpstreamDirectSellingDetailMVP;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.utils.SuMaoConstant;

import java.io.Serializable;
import java.util.List;

public class VipProductList extends AppCompatActivity {

    private TextView tv_child_title_bar_title;
    private ListView vip_producted_list_view;
    private List<String> Name;
    private List<String> ID;
    private String titleName;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vip_product_list);
        tv_child_title_bar_title = ((TextView) findViewById(R.id.tv_child_title_bar_title));

        vip_producted_list_view = ((ListView) findViewById(R.id.vip_producted_list_view));
        intent = getIntent();
        ID =( List<String>) intent.getSerializableExtra("ID");
        Name =( List<String>) intent.getSerializableExtra("Name");

        titleName = intent.getStringExtra("titleName");
        tv_child_title_bar_title.setText(titleName);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,Name);

        vip_producted_list_view.setAdapter(adapter);
        vip_producted_list_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedId  = ID.get(position);
                String selectedName = Name.get(position);
                intent.putExtra("selectedId",selectedId);
                intent.putExtra("selectedName",selectedName);
                if (titleName.equals("产品名称")){
                    VipProductList.this.setResult(SuMaoConstant.VIP_PRODUCT_ID, intent);
                }else if (titleName.equals("仓库")){
                    VipProductList.this.setResult(SuMaoConstant.VIP_WAREHOUSE_ID, intent);
//
                }else if(titleName.equals("配送方式")){
                    VipProductList.this.setResult(SuMaoConstant.VIP_DELIVERYMETHOD_ID, intent);
                }

                finish();
            }
        });


    }


}

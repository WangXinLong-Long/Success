package com.silianchuangye.sumao.success.fragments.homepage.UpstreamDirectSellingMVP.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.fragments.homepage.UpstreamDirectSellingMVP.UpstreamDirectSellingDetailMVP.UpstreamDirectSellingDetailsActivity;
import com.silianchuangye.sumao.success.fragments.homepage.UpstreamDirectSellingMVP.bean.EnterprisesItem;
import com.silianchuangye.sumao.success.fragments.homepage.UpstreamDirectSellingMVP.bean.UpstreamDirectorySellingBean;
import com.silianchuangye.sumao.success.fragments.homepage.UpstreamDirectSellingMVP.bean.vipProductBean.VipProductBean;
import com.silianchuangye.sumao.success.fragments.homepage.UpstreamDirectSellingMVP.presenter.UpstreamDirectSellingPresenter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UpstreamDirectSellingActivity extends AppCompatActivity implements IUpstreamDirectSellingView ,View.OnClickListener{

    private ListView upstream_listview;
    private UpstreamDirectSellingPresenter upstreamDirectSellingPresenter;
    private TextView tv_child_title_bar_title;
    private ImageView iv_child_title_bar_back;
    private UpstreamDirectorySellingBean upstreamDirectorySellingBean;
    private List<EnterprisesItem> enterprisesItem;
    private List<String> idList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upstream_direct_selling);
        //从首页接收到的值
        Intent intent = getIntent();
        upstreamDirectorySellingBean = ((UpstreamDirectorySellingBean) intent.getSerializableExtra("upstreamDirectorySellingBean"));
        enterprisesItem = upstreamDirectorySellingBean.getEnterprisesItem();
//        初始化控件
        tv_child_title_bar_title = ((TextView) findViewById(R.id.tv_child_title_bar_title));
        tv_child_title_bar_title.setText("上游直销");
        upstreamDirectSellingPresenter = new UpstreamDirectSellingPresenter(this);
        upstream_listview = ((ListView) findViewById(R.id.upstream_listview));
        iv_child_title_bar_back = ((ImageView) findViewById(R.id.iv_child_title_bar_back));
        iv_child_title_bar_back.setOnClickListener(this);
//        添加加载数据
        final List<String> nameList = new ArrayList<>();
        idList = new ArrayList<>();
        for (int i = 0; i < enterprisesItem.size(); i++) {
            nameList.add(enterprisesItem.get(i).getSellerCompanyName());
            idList.add(enterprisesItem.get(i).getSellerCompanyId());
        }
        ArrayAdapter<String> upstreamadapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,nameList);
        upstream_listview.setAdapter(upstreamadapter);
        upstream_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String sessionId = getSharedPreferences("sumao",MODE_PRIVATE).getString("unique","");
                if(sessionId.equals("false")){
                    Toast.makeText(UpstreamDirectSellingActivity.this,"请登录后进行操作",Toast.LENGTH_LONG).show();
                }else {

                    upstreamDirectSellingPresenter.getUpstreamDirectSellingDetail(sessionId,idList.get(position),nameList.get(position));
                }
            }
        });
    }

    @Override
    public void getUpstreamDirectSellingDetailInfo(VipProductBean vipProductBean,String sellerCompanyId,String title) {

        Intent intent = new Intent();
        intent.setClass(this,UpstreamDirectSellingDetailsActivity.class);
        intent.putExtra("vipProductBean",vipProductBean);
        intent.putExtra("sellerCompanyId",sellerCompanyId);
        intent.putExtra("title",title);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_child_title_bar_back:
                finish();
                break;
        }
    }
}

package com.silianchuangye.sumao.success.fragments.homepage.UpstreamDirectSellingMVP.view;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.fragments.homepage.UpstreamDirectSellingMVP.UpstreamDirectDetailMVP.view.UpstreamDirectSellingDetailsActivity;
import com.silianchuangye.sumao.success.fragments.homepage.UpstreamDirectSellingMVP.bean.EnterprisesItem;
import com.silianchuangye.sumao.success.fragments.homepage.UpstreamDirectSellingMVP.bean.UpstreamDirectorySellingBean;
import com.silianchuangye.sumao.success.fragments.homepage.UpstreamDirectSellingMVP.bean.vipProductBean.VipProductBean;
import com.silianchuangye.sumao.success.fragments.homepage.UpstreamDirectSellingMVP.presenter.UpstreamDirectSellingPresenter;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.login.LoginUserActivity;
import com.silianchuangye.sumao.success.utils.SuMaoConstant;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

public class UpstreamDirectSellingActivity extends AppCompatActivity implements IUpstreamDirectSellingView ,View.OnClickListener{

    private ListView upstream_listview;
    private UpstreamDirectSellingPresenter upstreamDirectSellingPresenter;
    private TextView tv_child_title_bar_title;
    private ImageView iv_child_title_bar_back;
    private UpstreamDirectorySellingBean upstreamDirectorySellingBean;
    private List<EnterprisesItem> enterprisesItem;
    private List<String> idList;
    private List<String> nameList;
    private VipProductBean vipProductBean=new VipProductBean();
    private String Id;
    String name;
    String sessionId;

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
        nameList= new ArrayList<>();
        idList = new ArrayList<>();
        for (int i = 0; i < enterprisesItem.size(); i++) {

            idList.add(enterprisesItem.get(i).getSellerCompanyId());
            Log.d("id的值是",enterprisesItem.get(i).getSellerCompanyId());
            nameList.add(enterprisesItem.get(i).getSellerCompanyName());
            Log.d("name的值是",enterprisesItem.get(i).getSellerCompanyName());
        }
        ArrayAdapter<String> upstreamadapter = new ArrayAdapter<String>(UpstreamDirectSellingActivity.this,android.R.layout.simple_list_item_1,nameList);
       // ArrayAdapter<String> adapter=new ArrayAdapter<String>()
        upstream_listview.setAdapter(upstreamadapter);

        upstream_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

//                Id=idList.get(position).toString();
//                name=nameList.get(position).toString();
//                Log.d("要传id的值",idList.get(position));
//                Log.d("要传title的值",nameList.get(position));
//               // booleanPower(Id,name);
             ///upstreamDirectSellingPresenter.getUpstreamDirectSellingDetail(sessionId,Id,name);
                   //
               // }

                Intent intent = new Intent();
                intent.setClass(UpstreamDirectSellingActivity.this, UpstreamDirectSellingDetailsActivity.class);
                intent.putExtra("vipProductBean", vipProductBean);
                intent.putExtra("sellerCompanyId", idList.get(position).toString());
                intent.putExtra("title", nameList.get(position).toString());
                startActivity(intent);

            }
        });
    }


    @Override
    public void getUpstreamDirectSellingDetailInfo(VipProductBean vipProductBean,String sellerCompanyId,String title) {
        Log.d("这个方法有没有走","这个方法有没有走");

            Intent intent = new Intent();
            intent.setClass(UpstreamDirectSellingActivity.this, UpstreamDirectSellingDetailsActivity.class);
            intent.putExtra("vipProductBean", vipProductBean);
            intent.putExtra("sellerCompanyId", sellerCompanyId);
            intent.putExtra("title", title);
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

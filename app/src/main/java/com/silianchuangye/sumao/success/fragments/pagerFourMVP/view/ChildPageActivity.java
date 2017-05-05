package com.silianchuangye.sumao.success.fragments.pagerFourMVP.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.fragments.pagerFourMVP.pagerFourDetail.PagerFourDetailActivity;
import com.silianchuangye.sumao.success.fragments.pagerTwoMVP.pagerTwoChilds.informationDetailsMVP.InformationDetailsActivity;
import com.silianchuangye.sumao.success.utils.SuMaoConstant;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

public class ChildPageActivity extends AppCompatActivity {
    private String result;
    private String title;
    private String type;
    private TextView tv_child_title_bar_title;
    private List<String> list_name;
    private List<String> list_id;
    private ListView lv_Holp;
    private ArrayAdapter<String> adapter;
    private String url;
    private String newsid;
    private ImageView iv_child_title_bar_back;
    private String titleName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child_page);
        Bundle bundle=getIntent().getExtras();
        result=bundle.getString("result");
        type=bundle.getString("group");
        title=bundle.getString("title");
        tv_child_title_bar_title= (TextView) findViewById(R.id.tv_child_title_bar_title);
        tv_child_title_bar_title.setText(title);
        lv_Holp= (ListView) findViewById(R.id.lv_Holp);
        iv_child_title_bar_back= (ImageView) findViewById(R.id.iv_child_title_bar_back);
        iv_child_title_bar_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChildPageActivity.this.finish();
            }
        });


        try {
            JSONObject object=new JSONObject(result);
            String fanhuijieguo=object.getString("newsItem");
            JSONArray jsonArray=new JSONArray(fanhuijieguo);
            list_name=new ArrayList<String>();
            list_id=new ArrayList<String>();
            for (int i=0;i<jsonArray.length();i++){
                JSONObject jsonObject=jsonArray.getJSONObject(i);
                list_name.add(jsonObject.getString("caption"));
                Log.d("listname的值",jsonObject.getString("caption"));
                list_id.add(jsonObject.getString("id"));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        adapter=new ArrayAdapter<String>(ChildPageActivity.this,R.layout.item_holp,R.id.tv_item,list_name);
        lv_Holp.setAdapter(adapter);



        lv_Holp.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                newsid=list_id.get(position).toString();
                titleName=list_name.get(position).toString();
                if (type.equals("塑贸指南")){
                    url= SuMaoConstant.SUMAO_IP+"/rest/model/atg/commerce/news/SumaoNewsActor/selectDetailDroplet";
                }else if (type.equals("客服帮助")){
                    url=SuMaoConstant.SUMAO_IP+"/rest/model/atg/commerce/news/SumaoNewsActor/selectDetailDroplet";
                }
                new Thread(){
                    @Override
                    public void run() {
                        super.run();
                        getInfo(newsid,titleName);
                    }
                }.start();
            }
        });



    }
    public void getInfo(String rp_id,final String title123){
        RequestParams rp=new RequestParams(url);
        rp.addParameter("newsId",rp_id);
        x.http().post(rp, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                //Log.d("fanhuizhi",result);
                Intent intent=new Intent(ChildPageActivity.this,PagerFourDetailActivity.class);
                intent.putExtra("result",result);
                intent.putExtra("title",title123);
                startActivity(intent);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }
}

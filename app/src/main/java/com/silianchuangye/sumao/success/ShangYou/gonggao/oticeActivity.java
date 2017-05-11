package com.silianchuangye.sumao.success.ShangYou.gonggao;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.jingchen.pulltorefresh.PullToRefreshLayout;
import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.utils.SuMaoConstant;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

public class oticeActivity extends AppCompatActivity implements View.OnClickListener {
    private String sellerCompanyId;
    private SharedPreferences sp;
    private String unique;
    private ImageView iv_child_title_bar_back;
    private TextView tv_child_title_bar_title;
    private PullToRefreshLayout refresh_view;
    private ListView pre_sale_listView;
    private List<Map<String,Object>> listNews;
    private boolean isUpload;
    private OticeAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice);
        Bundle bundle=getIntent().getExtras();
        sellerCompanyId=bundle.getString("orgCode");
        sp=getSharedPreferences("sumao", Activity.MODE_PRIVATE);
        unique=sp.getString("unique","");
        iv_child_title_bar_back= (ImageView) findViewById(R.id.iv_child_title_bar_back);
        iv_child_title_bar_back.setOnClickListener(this);
        tv_child_title_bar_title= (TextView) findViewById(R.id.tv_child_title_bar_title);
        tv_child_title_bar_title.setText("全部新闻");
        refresh_view= (PullToRefreshLayout) findViewById(R.id.refresh_view);
        pre_sale_listView= (ListView) refresh_view.getPullableView();
        refresh_view.setOnPullListener(new PagerTwoInformationPagePullListener());
        listNews=new ArrayList<>();
        getinfo("1");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_child_title_bar_back:
                oticeActivity.this.finish();
                break;
        }

    }

    public void getinfo(String number){
        if (isUpload){
            Log.d("走这里没","走这里没");
            if (listNews.size()>0) {
                listNews.clear();
                adapter.cleardata();
            }
        }
        String url= SuMaoConstant.SUMAO_IP+"/rest/model/atg/commerce/userprofiling/PurchaseJurisdictionCheckActor/CMSNoticeResult";
        RequestParams rp=new RequestParams(url);
        rp.addParameter("pageNum",number);
        rp.addParameter("orgCode",sellerCompanyId);
        rp.addParameter("_dynSessConf",unique);
        Log.d("上游公告的rp",rp+"");

        x.http().post(rp, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.d("上游直销deresult",result);
                if (!result.equals("")) {
                    try {
                        listNews.clear();
                        JSONObject jsonObject = new JSONObject(result);

                        String info = jsonObject.getString("announcementItems");
                        JSONArray jsonArray = new JSONArray(info);
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject object = jsonArray.getJSONObject(i);
                            String channelName = object.getString("channelName");
                            String name = object.getString("name");
                            String id = object.getString("id");
                            String createTime = object.getString("createDate");
                            JSONObject object_time = new JSONObject(createTime);
                            String time = object_time.getString("time");
                            Map<String, Object> map = new Hashtable<String, Object>();
                            map.put("channelName", channelName);
                            map.put("id", id);
                            map.put("name", name);
                            map.put("createTime", time);
                            listNews.add(map);
                        }
                        adapter = new OticeAdapter(listNews, oticeActivity.this);
                        pre_sale_listView.setAdapter(adapter);
                        adapter.notifyDataSetChanged();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }else{
                    Toast.makeText(oticeActivity.this, "暂时没有通知公告", Toast.LENGTH_SHORT).show();
                }
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
        pre_sale_listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(oticeActivity.this,NoticeActivityInfo.class);
                intent.putExtra("id",listNews.get(position).get("id").toString());
                startActivity(intent);
            }
        });

    }
    public class OticeAdapter extends BaseAdapter{
        private List<Map<String,Object> > list;
        private Context context;

        public OticeAdapter(List<Map<String,Object>> list,Context context){
            this.list=list;
            this.context=context;
        }

        public void cleardata(){
            list.clear();
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view=View.inflate(context,R.layout.data_list_view_adapter,null);
            TextView title_text_view= (TextView) view.findViewById(R.id.title_text_view);
            TextView time_text_view= (TextView) view.findViewById(R.id.time_text_view);
            title_text_view.setText("["+list.get(position).get("channelName").toString()+"]"+list.get(position).get("name").toString());
            time_text_view.setText(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Double.parseDouble(list.get(position).get("createTime").toString())));
            return view;
        }
    }

    public class PagerTwoInformationPagePullListener implements PullToRefreshLayout.OnPullListener {
        String pageNum = "1";
        @Override
        public void onRefresh(final PullToRefreshLayout pullToRefreshLayout) {
            // 千万别忘了告诉控件刷新完毕了哦！
            isUpload=true;
            getinfo(pageNum);
            pullToRefreshLayout.refreshFinish(PullToRefreshLayout.SUCCEED);
        }

        @Override
        public void onLoadMore(final PullToRefreshLayout pullToRefreshLayout) {
            // 加载更多操作
            new Handler() {
                @Override
                public void handleMessage(Message msg) {
                    int num = Integer.parseInt(pageNum) + 1;
                    pageNum = num + "";
                    isUpload=false;
                    // 千万别忘了告诉控件加载完毕了哦！
                    getinfo(pageNum);
                    pullToRefreshLayout.loadmoreFinish(PullToRefreshLayout.SUCCEED);
                }
            }.sendEmptyMessageDelayed(0, 5000);
        }
    }
}

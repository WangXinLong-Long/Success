package com.silianchuangye.sumao.success.fragments.homepage.groupbuying;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.silianchuangye.sumao.success.R;

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

public class JoinActivity extends AppCompatActivity {
   // private GridView gvDemo;
    private List<Map<String,Object>> list;
    private ImageView ivBack,ivSearch;
    private ListView lvDemo;
    private String shangpinId;
    private LinearLayout layout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);
        init();
        event();

    }
    public void init(){
        Bundle bundle=getIntent().getExtras();
        shangpinId=bundle.getString("id");
        Log.d("商品的编号",shangpinId);
        lvDemo= (ListView)findViewById(R.id.lvDemo);
        layout= (LinearLayout) findViewById(R.id.layout_center);
        list=new ArrayList<Map<String,Object>>();
        ivBack= (ImageView) findViewById(R.id.ivBack);
        new Thread(){
            @Override
            public void run() {

                getData();
            }
        }.start();

    }
    public void getData(){
        String url="http://192.168.32.126:7023/rest/model/atg/commerce/catalog/ProductCatalogActor/groupHistory";
        RequestParams requestParams=new RequestParams(url);
        requestParams.addParameter("productId",shangpinId);
        x.http().post(requestParams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.d("实时行情的result",result);
                if (result.contains("createDate")) {
                    layout.setVisibility(View.VISIBLE);
                    try {
                        JSONObject obj_result = new JSONObject(result);
                        String info = obj_result.getString("history");
                        JSONArray array = new JSONArray(info);

                        for (int i = 0; i < array.length(); i++) {
                            JSONObject obj_info = array.getJSONObject(i);
                            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 hh:mm:mm");
                            String data=simpleDateFormat.format(obj_info.getString("createDate"));

                            Map<String,Object> map=new Hashtable<String, Object>();
//
                            map.put("bianhao",obj_info.getString("index"));

                            map.put("shuliang",obj_info.getString("addQty"));

                            map.put("time",data);
                            list.add(map);

                        }


                        Log.d("list的值",list.toString());
                        GridAdpater adapter=new GridAdpater(JoinActivity.this,list,R.layout.itemtuangou,
                                new String[]{"bianhao","shuliang","time"},
                                new int[]{
                                        R.id.tv_bianhao,

                                        R.id.tv_shuliang,

                                        R.id.tv_time
                                });
                        lvDemo.setAdapter(adapter);


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }else {
                    layout.setVisibility(View.GONE);
                    Toast.makeText(JoinActivity.this, "暂无人竞拍该商品", Toast.LENGTH_SHORT).show();
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
    }

    public void event(){
        /**
         *
         */
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JoinActivity.this.finish();
            }
        });

    }

    class GridAdpater extends SimpleAdapter {


        public GridAdpater(Context context, List<? extends Map<String, ?>> data, int resource, String[] from, int[] to) {
            super(context, data, resource, from, to);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;
            if (convertView==null){
                viewHolder=new ViewHolder();
                convertView=getLayoutInflater().inflate(R.layout.itemtuangou,null);
                viewHolder.tv_bianhao= (TextView) convertView.findViewById(R.id.tv_bianhao);
                viewHolder.tv_shuliang= (TextView) convertView.findViewById(R.id.tv_shuliang);
                viewHolder.tv_time= (TextView) convertView.findViewById(R.id.tv_time);
                viewHolder.layout= (LinearLayout) convertView.findViewById(R.id.layout_item_vessel_one);
                convertView.setTag(viewHolder);
            }else{
                viewHolder= (ViewHolder) convertView.getTag();
            }

            //viewHolder.tv_bianhao.setText(list.get(position).get("bianhao").toString());
            Log.d("nihao",list.get(position).get("bianhao").toString());
            viewHolder.tv_bianhao.setText(list.get(position).get("bianhao").toString());

            viewHolder.tv_shuliang.setText(list.get(position).get("shuliang").toString());

            viewHolder.tv_time.setText(list.get(position).get("time").toString());
            if (position%2==0){
                viewHolder.layout.setBackgroundColor(getResources().getColor(R.color.bottom_background));
            }else{
                viewHolder.layout.setBackgroundColor(getResources().getColor(R.color.colorGreen));
            }

            return convertView;

        }
        class ViewHolder{
            LinearLayout layout;
            TextView tv_bianhao,tv_shuliang,tv_time;

        }

    }
}

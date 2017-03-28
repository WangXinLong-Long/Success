package com.silianchuangye.sumao.success.fragments.homepage.groupbuying;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.fragments.homepage.goodInStock.goodsInStockDetail.GoodsInStockDetailActivityMVP.bean.GoodsInStockDetailBean;
import com.silianchuangye.sumao.success.fragments.homepage.groupbuying.groupBuyingDetailMVP.bean.GroupBuyingDetailBean;
import com.silianchuangye.sumao.success.utils.SuMaoConstant;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

public class JoinActivity extends AppCompatActivity {
    // private GridView gvDemo;
    private List<Map<String, Object>> list;
    private ImageView ivBack, ivSearch;
    private ListView lvDemo;

    private LinearLayout layout;
    private String state;
    private String Id;
    private GroupBuyingDetailBean groupBuyingDetailBean;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);
        init();
        event();

    }

    public void init() {
        Bundle bundle=getIntent().getExtras();
        Id=bundle.getString("Id");
        lvDemo = (ListView) findViewById(R.id.lvDemo);
        layout = (LinearLayout) findViewById(R.id.layout_center);

        ivBack = (ImageView) findViewById(R.id.ivBack);
        getData();

    }

    public void getData() {
        String url = SuMaoConstant.SUMAO_IP + "/rest/model/atg/commerce/catalog/ProductCatalogActor/groupHistory";
        RequestParams rp=new RequestParams(url);
        rp.addParameter("productId",Id);
        x.http().post(rp, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.d("fanhuideresult",result);
                try {
                    JSONObject jsonObject=new JSONObject(result);
                    String info=jsonObject.getString("history");
                    Log.d("info",info);
                    list = new ArrayList<Map<String, Object>>();
                    JSONArray jsonArray=new JSONArray(info);
                    if (jsonArray.length()>0){
                        Log.d("進來l",jsonArray.length()+"");
                        layout.setVisibility(View.VISIBLE);

                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject obj_info = jsonArray.getJSONObject(i);
                            Log.d("虛幻",jsonArray.length()+"aaa");
//                            Map<String, Object> map = new Hashtable<String, Object>();
//                            map.put("bianhao", obj_info.getString("index"));
//                            map.put("shuliang", obj_info.getString("addQty"));
//                            map.put("time", simpleDateFormat.format(obj_info.getString("createDate")));
//                            list.add(map);
//                            Log.d("list的值",list.toString());
                            Map<String,Object> map=new Hashtable<String, Object>();
                            map.put("bianhao",obj_info.getString("index"));
                            map.put("shuliang",obj_info.getString("addQty"));
                            map.put("time",obj_info.getString("createDate"));
                            list.add(map);

                        }
                        Log.d("添加數據成功",list.toString());
                        GridAdpater adapter = new GridAdpater(JoinActivity.this, list, R.layout.itemtuangou,
                                new String[]{"bianhao", "shuliang", "time"},
                                new int[]{
                                        R.id.tv_bianhao,

                                        R.id.tv_shuliang,

                                        R.id.tv_time
                                });
                        lvDemo.setAdapter(adapter);

                }else{
                        layout.setVisibility(View.GONE);
                       // Toast.makeText(JoinActivity.this, "", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
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

    public void event() {
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
            if (convertView == null) {
                viewHolder = new ViewHolder();
                convertView = getLayoutInflater().inflate(R.layout.itemtuangou, null);
                viewHolder.tv_bianhao = (TextView) convertView.findViewById(R.id.tv_bianhao);
                viewHolder.tv_shuliang = (TextView) convertView.findViewById(R.id.tv_shuliang);
                viewHolder.tv_time = (TextView) convertView.findViewById(R.id.tv_time);
                viewHolder.layout = (LinearLayout) convertView.findViewById(R.id.layout_item_vessel_one);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }

            //viewHolder.tv_bianhao.setText(list.get(position).get("bianhao").toString());
            Log.d("nihao", list.get(position).get("bianhao").toString());
            viewHolder.tv_bianhao.setText(list.get(position).get("bianhao").toString());

           // SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 hh:mm:mm");
            viewHolder.tv_shuliang.setText(list.get(position).get("shuliang").toString());

            viewHolder.tv_time.setText(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Double.parseDouble(list.get(position).get("time").toString())));
            if (position % 2 == 0) {
                viewHolder.layout.setBackgroundColor(getResources().getColor(R.color.bottom_background));
            } else {
                viewHolder.layout.setBackgroundColor(getResources().getColor(R.color.colorGreen));
            }

            return convertView;

        }

        class ViewHolder {
            LinearLayout layout;
            TextView tv_bianhao, tv_shuliang, tv_time;

        }

    }
}

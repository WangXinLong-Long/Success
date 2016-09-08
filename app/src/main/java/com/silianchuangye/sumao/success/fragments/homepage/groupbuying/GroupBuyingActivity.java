package com.silianchuangye.sumao.success.fragments.homepage.groupbuying;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.silianchuangye.sumao.success.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

public class GroupBuyingActivity extends AppCompatActivity{
    private ListView lv_group_buying;
    private List<Map<String,Object>> list;
    private MyAdapter adapter;
    private ImageView back,search;
    private String shangpinId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_buying);
        init();
        addData();
        event();

    }

    /**
     * 初始化数据
     */
    public void init(){
        back= (ImageView) findViewById(R.id.ivBack_group_layout_top);
        search= (ImageView) findViewById(R.id.ivSearch_group_layout_top);
        lv_group_buying= (ListView) findViewById(R.id.lvAuction_Auction_Layout);




    }

    /**
     * 添加数据
     */
    public void addData(){
//        for (int i=0;i<=7;i++){
//            Map<String,Object> map=new HashMap<String, Object>();
//            map.put("name","北京四联");
//            map.put("starttime","2016-08-03 18:00");
//            map.put("price","8888");
//            map.put("count","12345");
//            map.put("number","60t");
//            map.put("way","自提");
//            map.put("comm","北京四联创业集团");
//            map.put("cangku","讯帮物流1号库");
//            map.put("endtime","2016-08-03 18:00");
//            list.add(map);
//
//        }

        new Thread(){
            @Override
            public void run() {
                super.run();
                String url="http://192.168.32.126:7023/rest/model/atg/commerce/catalog/ProductCatalogActor/groupProductlist";
                RequestParams rp=new RequestParams(url);
                x.http().post(rp, new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        Log.d("result的值",result);
                        if (result.contains("prod")){
                            try {
                                list=new ArrayList<Map<String,Object>>();
                                JSONObject obj=new JSONObject(result);
                                String info=obj.getString("prod");
                                JSONArray array=new JSONArray(info);
                                for (int i=0;i<array.length();i++){
                                    JSONObject obj_array=array.getJSONObject(i);
                                    Map<String,Object> map=new Hashtable<String, Object>();
                                    map.put("id",obj_array.getString("id"));
                                    map.put("state",obj_array.getString("runing"));
                                    map.put("name",obj_array.getString("cl_mingcheng"));
                                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat
                                            ("yyyy年MM月dd日 hh:mm:mm");
                                    String Start_data=simpleDateFormat.format(Double.parseDouble(obj_array.getString("groupStartDate")));
                                    map.put("startTime",Start_data);
                                    map.put("price",obj_array.getString("cl_jine"));
                                    map.put("number",obj_array.getString("cl_zongliang"));
                                    map.put("chengtuan",obj_array.getString("cl_groupQuantity"));
                                    map.put("way","自提");
                                    map.put("cangku",obj_array.getString("cl_cangku"));
                                    map.put("comm",obj_array.getString("cl_gongsi"));
//                                    SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat
//                                            ("yyyy年MM月dd日 hh:mm:mm");
                                    String end_data=simpleDateFormat.format(Double.parseDouble(obj_array.getString("groupEndDate")));
                                    map.put("endTime",end_data);
                                    list.add(map);
                                }
                                adapter=new MyAdapter(GroupBuyingActivity.this,list,R.layout.layout_for_group_buying,
                                        new String[]{"state","name","startTime","price","number",
                                                "chengtuan","way","cangku","comm","endTime"},
                                        new int[]{
                                                R.id.iv_auction_icon,
                                                R.id.tv_auction_name,
                                                R.id.tv_auction_time,
                                                R.id.tv_auction_price,
                                                R.id.tv_auction_number,
                                                R.id.tv_group_number,
                                                R.id.tv_group_way_value,
                                                R.id.tv_auction_acdress,
                                                R.id.tv_auction_commAdress,
                                                R.id.tv_time_end
                                        });
                                lv_group_buying.setAdapter(adapter);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }else{
                            Toast.makeText(GroupBuyingActivity.this, "请求网络数据失败!", Toast.LENGTH_SHORT).show();
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
        }.start();

    }

    /**
     * 事件监听
     */
    public void event(){
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GroupBuyingActivity.this.finish();
            }
        });
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**
                 * 搜索按钮的点击事件
                 */
            }
        });
        /**
         * ListView的item的点击事件
         */
        lv_group_buying.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                 if (position%2==0){
                     Intent intent=new Intent(GroupBuyingActivity.this,GroupBuyingSuccessActivity.class);
                     intent.putExtra("state","ok");
                     intent.putExtra("id",shangpinId);
                     Log.d("商品id的值",shangpinId);
                     startActivity(intent);

                 }else{

                     Intent intent=new Intent(GroupBuyingActivity.this,GroupBuyingSuccessActivity.class);
                     intent.putExtra("state","no");
                     intent.putExtra("id",shangpinId);
                     Log.d("商品id",shangpinId);
                     startActivity(intent);

                 }
            }
        });

    }
    class MyAdapter extends SimpleAdapter{

        public MyAdapter(Context context, List<? extends Map<String, ?>> data, int resource, String[] from, int[] to) {
            super(context, data, resource, from, to);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView==null){
                convertView=getLayoutInflater().inflate(R.layout.layout_for_group_buying,null);
            }

            ImageView iv_icon= (ImageView) convertView.findViewById(R.id.iv_auction_icon);
            TextView tv_name= (TextView) convertView.findViewById(R.id.tv_auction_name);
            TextView tv_StartTime= (TextView) convertView.findViewById(R.id.tv_auction_time);
            TextView tv_price= (TextView) convertView.findViewById(R.id.tv_auction_price);
            TextView tv_count= (TextView) convertView.findViewById(R.id.tv_auction_number);
            Button bt_join= (Button) convertView.findViewById(R.id.bt_auctionitem);
            TextView tv_chengtuan= (TextView) convertView.findViewById(R.id.tv_group_number);
            TextView tv_way= (TextView) convertView.findViewById(R.id.tv_group_way_value);
            TextView tv_cangku= (TextView) convertView.findViewById(R.id.tv_auction_acdress);
            TextView tv_comm= (TextView) convertView.findViewById(R.id.tv_auction_commAdress);
            TextView tv_endTime= (TextView) convertView.findViewById(R.id.tv_group_end_time);
            shangpinId=list.get(position).get("id").toString();
            tv_name.setText(list.get(position).get("name").toString());
            tv_StartTime.setText(list.get(position).get("startTime").toString());
            tv_price.setText(list.get(position).get("price").toString());
            tv_count.setText(list.get(position).get("number").toString());
            tv_chengtuan.setText(list.get(position).get("chengtuan").toString());
            tv_way.setText(list.get(position).get("way").toString());
            tv_cangku.setText(list.get(position).get("cangku").toString());
            tv_comm.setText(list.get(position).get("comm").toString());
            tv_endTime.setText(list.get(position).get("endTime").toString());
            if (list.get(position).get("state").equals("true")){//正在团购
                iv_icon.setImageResource(R.mipmap.underwey);
                bt_join.setBackgroundColor(getResources().getColor(R.color.btn_blue_normal));
            }else if (list.get(position).get("state").equals("false")){//团购没开始
                 iv_icon.setImageResource(R.mipmap.tuangouselect);
                bt_join.setBackgroundColor(getResources().getColor(R.color.btn_blue_normal));
            }else if (list.get(position).get("state").equals("end")){//团购已结束
                iv_icon.setImageResource(R.mipmap.tuangou);
                bt_join.setBackgroundColor(getResources().getColor(R.color.gray));

            }

            return convertView;
        }
    }



}

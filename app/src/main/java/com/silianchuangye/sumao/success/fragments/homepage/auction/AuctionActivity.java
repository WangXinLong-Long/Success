package com.silianchuangye.sumao.success.fragments.homepage.auction;

import android.content.Context;
import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

public class AuctionActivity extends AppCompatActivity {
    private ImageView imageback;
    private ListView lvAuction;
    private List<Map<String,Object>> list;
    private String name;
    private List<String> order_id;
    private List<String> SKUID;

    private Button bu;
    private String id,namechuanpin,startTime,endTime,startPrice,count,cangku,company,state,type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auction);
        init();
        event();
        new Thread(){
            @Override
            public void run() {
              //  super.run();
                getInfo();
            }
        }.start();
    }
    public void init(){
        imageback= (ImageView) findViewById(R.id.ivBack_auction_layout_top);
        imageback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AuctionActivity.this.finish();
            }
        });
        lvAuction= (ListView) findViewById(R.id.lvAuction_Auction_Layout);
        list=new ArrayList<Map<String,Object>>();
//        Map<String,Object> map=new Hashtable<String,Object>();
//        map.put("name","福建联合");
//        list.add(map);
//        Map<String,Object> map1=new Hashtable<String,Object>();
//        map1.put("name","福建联合");
//        list.add(map1);
        order_id=new ArrayList<String>();
        SKUID=new ArrayList<String>();

        lvAuction.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if (list.get(position).get("kaishi").toString().equals("开始时间:")){
                    //竞拍未开始
                    Intent intent=new Intent(AuctionActivity.this,OpenAuctionActivity.class);
                    intent.putExtra("name","竞拍未开始");
                    intent.putExtra("id",order_id.get(position).toString());
                    if (list.get(position).get("way").equals(R.mipmap.publicauction)){
                        intent.putExtra("type","公开竞拍");
                    }else if (list.get(position).get("way").equals(R.mipmap.mifengauction)){
                        intent.putExtra("type","密封报价");
                    }
                    Log.d("id",order_id.get(position).toString());
                    startActivity(intent);

                }else if (list.get(position).get("kaishi").toString().equals("距离结束时间还剩:")){
                    //竞拍已开始
                    Intent intent=new Intent(AuctionActivity.this,OpenAuctionActivity.class);
                    intent.putExtra("name","竞拍已开始");
                    intent.putExtra("id",order_id.get(position).toString());
                    Log.d("id",order_id.get(position).toString());
                    if (list.get(position).get("way").equals(R.mipmap.publicauction)){
                        intent.putExtra("type","公开竞拍");
                    }else if (list.get(position).get("way").equals(R.mipmap.mifengauction)){
                        intent.putExtra("type","密封报价");
                    }
                    startActivity(intent);
                }else if (list.get(position).get("kaishi").toString().equals("")){
                    //竞拍已结束
                    Intent intent=new Intent(AuctionActivity.this,OpenAuctionActivity.class);
                    intent.putExtra("name","竞拍已结束");
                    intent.putExtra("id",order_id.get(position).toString());
                    Log.d("id",order_id.get(position).toString());
                    if (list.get(position).get("way").equals(R.mipmap.publicauction)){
                        intent.putExtra("type","公开竞拍");
                    }else if (list.get(position).get("way").equals(R.mipmap.mifengauction)){
                        intent.putExtra("type","密封报价");
                    }
                    startActivity(intent);
                    //Toast.makeText(AuctionActivity.this, "竞拍已结束,不能参与", Toast.LENGTH_SHORT).show();
                }


            }
        });


    }
    public void event(){


    }
    public void getInfo(){
        String url="http://192.168.32.126:7023/rest/model/atg/commerce/catalog/ProductCatalogActor/auctionProductlist";
        RequestParams rp=new RequestParams(url);
        x.http().post(rp, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.d("result",result);
                try {
                    JSONObject obj_result = new JSONObject(result);
                    String info=obj_result.getString("prod");
                    JSONArray array=new JSONArray(info);
                    for (int i=0;i<array.length();i++){
                        JSONObject obj_info=array.getJSONObject(i);
                         id=obj_info.getString("id");

                         namechuanpin=obj_info.getString("cl_mingcheng");
                        // startTime=obj_info.getString("startDate");
                         startTime=obj_info.getString("cl_mingcheng");
                         endTime=obj_info.getString("cl_mingcheng");
                         startPrice=obj_info.getString("cl_qipai");
                         count=obj_info.getString("cl_zongliang");
                         cangku=obj_info.getString("cl_cangku");
                         company=obj_info.getString("cl_gongsi");
                         state=obj_info.getString("status");
                         type=obj_info.getString("type");
                         order_id.add(id);
                         Map<String,Object> map=new HashMap<String, Object>();
                         map.put("name",namechuanpin);

                        if (state==null||state.equals("0")){
                            //竞拍未开始
                            map.put("kaishi","开始时间:");
                            map.put("startTime",startTime);
                            map.put("icon","");

                        }else if (state.equals("1")){
                            //正在竞拍
                            map.put("kaishi","开始时间:");
                            map.put("startTime",startTime);
                            map.put("icon","");
                        }else if (state.equals("2")){
                            //竞拍结束
                            map.put("kaishi","");
                            map.put("startTime","");
                            map.put("icon",R.mipmap.end);
                        }
                        map.put("price",startPrice);
                        map.put("count",count);
                        map.put("cangku",cangku);
                        map.put("company",company);

                        if (type.equals("englishAuctionProduct")){
                            //公开竞拍
                            map.put("way",R.mipmap.publicauction);

                        }else if (type.equals("sealedAuctionProduct")){
                            //密封竞拍
                            map.put("way",R.mipmap.mifengauction);
                        }
                        list.add(map);

                    }
                    MyAdapter adapter=new MyAdapter(AuctionActivity.this,
                            list,
                            R.layout.auctionitem,
                            new String[]{"name","kaishi","startTime","icon","price","count","cangku","company","way"},
                            new int[]{R.id.tv_auction_name,
                                    R.id.tv_auction_start,
                                    R.id.tv_auction_time,
                                    R.id.iv_auction_end,
                                    R.id.tv_auction_price,
                                    R.id.tv_auction_number,
                                    R.id.tv_auction_acdress,
                                    R.id.tv_auction_commAdress,
                                    R.id.iv_auction_icon

                                        });
                    lvAuction.setAdapter(adapter);
                    adapter.notifyDataSetChanged();

                }catch (JSONException e){
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
    class MyAdapter extends SimpleAdapter{


        public MyAdapter(Context context, List<? extends Map<String, ?>> data, int resource, String[] from, int[] to) {
            super(context, data, resource, from, to);
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view=getLayoutInflater().inflate(R.layout.auctionitem,null);
            bu= (Button) view.findViewById(R.id.bt_auctionitem);
            TextView tv_name= (TextView) view.findViewById(R.id.tv_auction_name);
            tv_name.setText(list.get(position).get("name").toString());
            TextView tv_kaishi= (TextView) view.findViewById(R.id.tv_auction_start);
            tv_kaishi.setText(list.get(position).get("kaishi").toString());
            TextView startTime= (TextView) view.findViewById(R.id.tv_auction_time);
            startTime.setText(list.get(position).get("startTime").toString());
            ImageView icon= (ImageView) view.findViewById(R.id.iv_auction_end);
            //icon.setImageResource(Integer.parseInt(list.get(position).get("icon").toString()));
            if (list.get(position).get("kaishi").toString().equals("")){
                icon.setImageResource(R.mipmap.end);
                bu.setVisibility(View.GONE);
            }
            TextView tv_price= (TextView) view.findViewById(R.id.tv_auction_price);
            tv_price.setText(list.get(position).get("price").toString());
            TextView tv_count= (TextView) view.findViewById(R.id.tv_auction_number);
            tv_count.setText(list.get(position).get("count").toString());
            TextView tv_cangku= (TextView) view.findViewById(R.id.tv_auction_acdress);
            tv_cangku.setText(list.get(position).get("cangku").toString());
            TextView tv_commpany= (TextView) view.findViewById(R.id.tv_auction_commAdress);
            tv_commpany.setText(list.get(position).get("company").toString());

            ImageView iv_way= (ImageView) view.findViewById(R.id.iv_auction_icon);
//            if (type.equals("englishAuctionProduct")){
//
//            }else if (type.equals("sealedAuctionProduct")){
//
//            }
            if (list.get(position).get("way").equals(R.mipmap.publicauction)){
                iv_way.setImageResource(R.mipmap.publicauction);
            }else if (list.get(position).get("way").equals(R.mipmap.mifengauction)){
                iv_way.setImageResource(R.mipmap.mifengauction);
            }


            return view;
        }
    }
}

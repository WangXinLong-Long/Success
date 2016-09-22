package com.silianchuangye.sumao.success.fragments.homepage.auction;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.utils.SuMaoConstant;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.sql.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

public class AuctionActivity extends AppCompatActivity {
    private ImageView imageback;
    private PullToRefreshListView lvAuction;
    private String name;
    private List<String> order_id;
    private List<String> SKUID;
    private ArrayList<Map<String,Object>> list_message;

    private Button bu;

    private String id,namechuanpin,startTime,endTime,startPrice,count,cangku,company,state,type;
    private int currentPage = 1;
    // 默认每页条数
    private int pageSize = 10;
    // 如果已取得所有为true
    private boolean isAllGot = false;
    private MyAdapter adapter;
    private List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auction);
        getParme();
        init();
        getAddData();

        event();
    }
    public void init(){
        list.clear();
        imageback= (ImageView) findViewById(R.id.ivBack_auction_layout_top);
        imageback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AuctionActivity.this.finish();
            }
        });
        lvAuction= (PullToRefreshListView) findViewById(R.id.lvAuction_Auction_Layout);
        adapter=new MyAdapter(AuctionActivity.this,
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
        lvAuction.getLoadingLayoutProxy().setLoadingDrawable(null);
        lvAuction.setDividerDrawable(null);
        lvAuction.setMode(PullToRefreshBase.Mode.BOTH);
        setPullrefreshLable(lvAuction, isAllGot);
        lvAuction.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                currentPage = 1;// 刷新 当前页改成1
                isAllGot = false;
                getInfo();

            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                if (isAllGot) {
                    lvAuction.onRefreshComplete();
                } else {
                    getInfo();
                }

            }
        });


        order_id=new ArrayList<String>();
        SKUID=new ArrayList<String>();

        lvAuction.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("商品的position",position+"hhhhhhh");
                String max=list_message.get(position).get("max").toString();
                String min=list_message.get(position).get("min").toString();
                String people_Number=list_message.get(position).get("pNumber").toString();
                String quty=list_message.get(position).get("quty").toString();
                Log.d("最高竞标价",max+"sasasa");
                Log.d("最高竞标价",min+"sasasasa");
                Log.d("最高竞标价",people_Number+"sasasasa");
                Log.d("最高竞标价",quty+"sasasaas");
                SharedPreferences sp=getSharedPreferences("sumao", Activity.MODE_PRIVATE);
                String name=sp.getString("name","");
                Log.d("用户名称",name);
                //Log.d("竞拍时name的值",name+"asasasasasasasasaasasasasasasasasasasasasssssssssssss");
                String state;
                //Log.d("竞拍时name的值",name+"aa");
                if (name!=""){
                    state="yes";
                }else{
                    state="no";
                }
                Log.d("登录状态的值",state);
                Log.d("position的值",position+"");
                if (list.get(position-1).get("kaishi").toString().equals("开始时间:")){
                    //竞拍未开始
                    Intent intent=new Intent(AuctionActivity.this,OpenAuctionActivity.class);
                    intent.putExtra("name","竞拍未开始");
                    intent.putExtra("id",order_id.get(position-1).toString());
                    //intent.putCharSequenceArrayListExtra("list",list_message);
                    intent.putExtra("max",max);
                    intent.putExtra("min",min);
                    intent.putExtra("people_Number",people_Number);
                    intent.putExtra("quty",quty);
                    intent.putExtra("state",state);
                    if (list.get(position-1).get("way").equals(R.mipmap.publicauction)){
                        intent.putExtra("type","公开竞拍");
                    }else if (list.get(position-1).get("way").equals(R.mipmap.mifengauction)){
                        intent.putExtra("type","密封报价");
                    }
                    Log.d("id",order_id.get(position).toString());
                    startActivity(intent);

                }else if (list.get(position-1).get("kaishi").toString().equals("正在竞拍:")){
                    //竞拍已开始
                    Intent intent=new Intent(AuctionActivity.this,OpenAuctionActivity.class);
                    intent.putExtra("name","竞拍已开始");
                    intent.putExtra("id",order_id.get(position-1).toString());
                    intent.putExtra("state",state);
                    intent.putExtra("max",max);
                    intent.putExtra("min",min);
                    intent.putExtra("people_Number",people_Number);
                    intent.putExtra("quty",quty);
                    Log.d("id",order_id.get(position).toString());
                    if (list.get(position-1).get("way").equals(R.mipmap.publicauction)){
                        intent.putExtra("type","公开竞拍");
                    }else if (list.get(position-1).get("way").equals(R.mipmap.mifengauction)){
                        intent.putExtra("type","密封报价");
                    }
                    startActivity(intent);
                }else if (list.get(position-1).get("kaishi").toString().equals("")){
                    Log.d("仓库名称",list.get(position-1).get("cangku").toString()+"aaassssasasas");

                    //竞拍已结束
                    Intent intent=new Intent(AuctionActivity.this,OpenAuctionActivity.class);
                    intent.putExtra("name","竞拍已结束");
                    intent.putExtra("id",order_id.get(position-1).toString());
                    intent.putExtra("max",max);
                    intent.putExtra("min",min);
                    intent.putExtra("people_Number",people_Number);
                    intent.putExtra("quty",quty);
                    Log.d("商品详情的id",order_id.get(position-1).toString());
                    intent.putExtra("state",state);
                    Log.d("id",order_id.get(position-1).toString());
                    if (list.get(position-1).get("way").equals(R.mipmap.publicauction)){
                        intent.putExtra("type","公开竞拍");
                    }else if (list.get(position-1).get("way").equals(R.mipmap.mifengauction)){
                        intent.putExtra("type","密封报价");
                    }
                    startActivity(intent);
                    Toast.makeText(AuctionActivity.this, "竞拍已结束,不能参与", Toast.LENGTH_SHORT).show();
                }


           }
        });


    }
    public void getAddData(){
        getInfo();
    }
    public void setPullrefreshLable(@SuppressWarnings("rawtypes") PullToRefreshBase p, boolean isAll) {
        if (isAll) {
            p.getLoadingLayoutProxy(false, true).setPullLabel("已显示全部内容");
            p.getLoadingLayoutProxy(false, true).setRefreshingLabel("已显示全部内容");
            p.getLoadingLayoutProxy(false, true).setReleaseLabel("已显示全部内容");
            p.getLoadingLayoutProxy(true, false).setPullLabel("下拉刷新");
            p.getLoadingLayoutProxy(true, false).setRefreshingLabel("数据加载中...");
            p.getLoadingLayoutProxy(true, false).setReleaseLabel("放开刷新");
        } else {
            p.getLoadingLayoutProxy(false, true).setPullLabel("向上滑动加载更多");
            p.getLoadingLayoutProxy(false, true).setRefreshingLabel("数据加载中...");
            p.getLoadingLayoutProxy(false, true).setReleaseLabel("放开加载更多");
            p.getLoadingLayoutProxy(true, false).setPullLabel("下拉刷新");
            p.getLoadingLayoutProxy(true, false).setRefreshingLabel("数据加载中...");
        }
    }
    public void event(){
        //isSuccessorFail();



    }
    public void getInfo(){
        String url="http://192.168.32.126:7023/rest/model/atg/commerce/catalog/ProductCatalogActor/auctionProductlist";
        RequestParams rp=new RequestParams(url);
        rp.addParameter("pageNum",currentPage+"");
        x.http().post(rp, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.d("竞拍列表的result",result);
                try {
                    JSONObject obj_result = new JSONObject(result);
                    String info=obj_result.getString("prod");
                    JSONArray array=new JSONArray(info);
                    List<Map<String,Object>> list_info=new ArrayList<Map<String, Object>>();
                    list_message=new ArrayList<Map<String, Object>>();
                    for (int i=0;i<array.length();i++){
                        JSONObject obj_info=array.getJSONObject(i);
                         id=obj_info.getString("id");
                         Log.d("竞拍时id的值",id);
                         namechuanpin=obj_info.getString("cl_mingcheng");
                        // startTime=obj_info.getString("startDate");
                        // startTime=obj_info.getString("startDate");
                        startTime="1122243223";
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat
                                ("yyyy年MM月dd日 hh:mm:mm");
                        String data=simpleDateFormat.format(Double.parseDouble(startTime));

                        endTime=obj_info.getString("cl_mingcheng");
                         startPrice=obj_info.getString("cl_qipai");
                         count=obj_info.getString("cl_zongliang");
                         cangku=obj_info.getString("cl_cangku");
                         company=obj_info.getString("cl_gongsi");
                         state=obj_info.getString("status");
                         type=obj_info.getString("type");
                         order_id.add(id);

                        Log.d("竞拍时id的值",order_id.size()+"");
                         Map<String,Object> map=new HashMap<String, Object>();
                         map.put("name",namechuanpin);

                        if (state==null||state.equals("0")){
                            //竞拍未开始
                            map.put("kaishi","开始时间:");
                            map.put("startTime",data);
                            map.put("icon","");

                        }else if (state.equals("1")){
                            //正在竞拍
                            map.put("kaishi","正在竞拍:");
                            map.put("startTime",data );
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
                        list_info.add(map);
                        String message=obj_info.getString("List");
                        JSONArray array_message=new JSONArray(message);
                        Map<String,Object> map_message;
                        if (message.contains("max")){
                            for (int j=0;j<array_message.length();j++){
                                JSONObject obj_message=array_message.getJSONObject(j);
                                map_message=new Hashtable<String, Object>();
                                map_message.put("classname",obj_message.getString("@class"));
                                map_message.put("max",obj_message.getString("max"));
                                map_message.put("min",obj_message.getString("min"));
                                map_message.put("pNumber",obj_message.getString("pNumber"));
                                map_message.put("quty",obj_message.getString("quty"));
                                list_message.add(map_message);
                            }
                            //  Log.d("长度",list_message.size()+"");
                        }else{
                            map_message=new Hashtable<String, Object>();
                            map_message.put("classname","最高竞拍价");
                            map_message.put("max","最高竞拍价");
                            map_message.put("min","最高竞拍价");
                            map_message.put("pNumber","最高竞拍价");
                            map_message.put("quty","最高竞拍价");
                            list_message.add(map_message);
                        }
                    }
                    Log.d("长度",list_message.size()+"");
                    if (currentPage==1){
                        if (list_info.size()!=0){
                            list.clear();
                        }
                    }
                    list.addAll(list_info);
                    if (array.length()<pageSize){
                        isAllGot=true;
                    }else {
                        isAllGot=false;
                    }
                    currentPage++;
                    // videoListView.getLoadingLayoutProxy().setLastUpdatedLabel(sf.format(new Date()));
                    setPullrefreshLable(lvAuction, isAllGot);
                    adapter.notifyDataSetChanged();
                    lvAuction.onRefreshComplete();

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
    public void getParme(){
        isAllGot=false;
        currentPage=1;

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

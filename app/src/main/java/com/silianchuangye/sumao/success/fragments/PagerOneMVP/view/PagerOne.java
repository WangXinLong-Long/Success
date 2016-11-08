package com.silianchuangye.sumao.success.fragments.PagerOneMVP.view;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;


import com.silianchuangye.sumao.success.adapter.LvFragmentoneAuctionsAdapter;
import com.silianchuangye.sumao.success.adapter.LvFragmentoneGrouponAdapter;
import com.silianchuangye.sumao.success.custom.customCalendar.DayAndPrice;
import com.silianchuangye.sumao.success.fragments.BasePager;
import com.silianchuangye.sumao.success.fragments.PagerOneMVP.bean.AnnounceBean;
import com.silianchuangye.sumao.success.fragments.PagerOneMVP.bean.AnnouncementBean;
import com.silianchuangye.sumao.success.fragments.PagerOneMVP.bean.BannerBean;
import com.silianchuangye.sumao.success.fragments.PagerOneMVP.presenter.PagerOnePresenter;
import com.silianchuangye.sumao.success.fragments.homepage.AnnouncementDetailMVP.view.AnnouncementDetailActivity;
import com.silianchuangye.sumao.success.fragments.homepage.UpstreamDirectSellingMVP.bean.UpstreamDirectorySellingBean;
import com.silianchuangye.sumao.success.fragments.homepage.UpstreamDirectSellingMVP.view.UpstreamDirectSellingActivity;
import com.silianchuangye.sumao.success.fragments.homepage.auction.OpenAuctionActivity;
import com.silianchuangye.sumao.success.fragments.homepage.goodInStock.GoodsInStockActivityMVP.bean.GoodsInStockActivityBean;
import com.silianchuangye.sumao.success.fragments.homepage.groupbuying.GroupBuyingActivity;
import com.silianchuangye.sumao.success.fragments.homepage.groupbuying.GroupBuyingSuccessActivity;
import com.silianchuangye.sumao.success.fragments.homepage.preSale.PreSaleDetailActivityMVP.view.PreSaleDetailActivity;
import com.silianchuangye.sumao.success.fragments.homepage.preSale.PreSaleMVP.bean.Auction;
import com.silianchuangye.sumao.success.fragments.homepage.preSale.PreSaleMVP.bean.Cl;
import com.silianchuangye.sumao.success.fragments.homepage.preSale.PreSaleMVP.bean.Forward;
import com.silianchuangye.sumao.success.fragments.homepage.preSale.PreSaleMVP.bean.Group;
import com.silianchuangye.sumao.success.fragments.homepage.preSale.PreSaleMVP.bean.PreSaleBean;
import com.silianchuangye.sumao.success.fragments.homepage.sumaoconsultMVP.SuMaoConsult;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.login.LoginUserActivity;
import com.silianchuangye.sumao.success.fragments.SearchActivityMVP.view.SearchActivity;
import com.silianchuangye.sumao.success.fragments.homepage.auction.AuctionActivity;
import com.silianchuangye.sumao.success.HX.Constant;
import com.silianchuangye.sumao.success.HX.ui.LoginActivity;
import com.silianchuangye.sumao.success.fragments.homepage.goodInStock.GoodsInStockActivityMVP.view.GoodsInStockActivity;
import com.silianchuangye.sumao.success.fragments.homepage.preSale.PreSaleMVP.view.PreSale;
import com.silianchuangye.sumao.success.fragments.homepage.theprice.MidpointsListctivity;
import com.silianchuangye.sumao.success.utils.Loding;
import com.silianchuangye.sumao.success.utils.LogUtils;
import com.silianchuangye.sumao.success.utils.MarqueeView;
import com.silianchuangye.sumao.success.utils.SuMaoConstant;
import com.silianchuangye.sumao.success.utils.scrollviewAD.MyGallery;
import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.adapter.ImageAdapter;
import com.zhy.m.permission.MPermissions;
import com.zhy.m.permission.PermissionDenied;
import com.zhy.m.permission.PermissionGrant;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/4/20 0020.
 */
public class PagerOne extends BasePager implements IPagerOneView {
    private LinearLayout layouticon;
    private List<Bitmap> listDrawable = new ArrayList<Bitmap>();
    private MyGallery gallery;
    private GridView gvFragmentone;
    private List<Map<String, Object>> list;
    private LinearLayout view;
    private ListView lvFragmentAdwords;
    private List<Map<String, Object>> listString;
    private RelativeLayout rlHorn;
    private PagerOnePresenter pagerOnePresenter;
    private List<String> banners;
    private MarqueeView tvFragmentHord;
    private List<AnnouncementBean> items;
//    团购的listview还没有创建对象
    private ListView lvFragmentoneGroupon;
//    预售信息的listview【
    private ListView lvFragmentoneAD;
    private LvFragmentoneGrouponAdapter lvFragmentoneGrouponAdapter;
    private List<Forward> forwards;
    private int year;
    private int mounth;
    private int day;
    private List<DayAndPrice> calendarlist;
    private Intent calendarintent;
    List<Group> cls;
    private String type,max,min,number,people,statu="";
    private List<Auction> auctions;
    private static final int REQUEST_PERMISSION_CAMERA_CODE = 1;
    static final String[] PERMISSIONS = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA};
    @Override
    public void myClickSearch() {
        //调到搜索页
        Log.d("点击搜索", "点击搜素");
        Intent intent = new Intent(mActivity, SearchActivity.class);
        startActivity(intent);
    }

    @Override
    public void initDate() {
        pagerOnePresenter = new PagerOnePresenter(this);
        view = (LinearLayout) View.inflate(mActivity, R.layout.fragmentone, null);
        gridview();
        vpad();
        listString = new ArrayList<Map<String, Object>>();
//        listAdwords();
        initHorn();
        initlvFragmentoneGroupon();
        SharedPreferences sp=getActivity().getSharedPreferences("sumao",Activity.MODE_PRIVATE);
        String zhanghao=sp.getString("zhanghao","");
        Log.d("账号的值",zhanghao);
    }
//     预售的listview
    private void initlvFragmentoneGroupon() {
        lvFragmentoneAD = ((ListView) view.findViewById(R.id.lvFragmentoneAD));
        lvFragmentAdwords = (ListView) view.findViewById(R.id.lvFragmentAdwords);
        lvFragmentoneGroupon = (ListView) view.findViewById(R.id.lvFragmentoneGroupon);

        lvFragmentoneGroupon.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //跳转到团购详情界面
                Intent intent=new Intent(getActivity(), GroupBuyingSuccessActivity.class);
                intent.putExtra("id",cls.get(position).getCl_id());
                startActivity(intent);
            }
        });
        lvFragmentAdwords.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //跳转到竞拍详情界面
//                Intent intent=new Intent(getActivity(), OpenAuctionActivity.class);
//                startActivity(intent);
                SharedPreferences sp=getActivity().getSharedPreferences("sumao", Activity.MODE_PRIVATE);
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
                Log.d("竞拍商品的id","aa"+auctions.get(position).getCl_id());
                getResult(auctions.get(position).getCl_id());

                Log.d("竞拍商品的类型",statu);
                if (statu.equals("竞拍未开始")){
                    Intent intent=new Intent(mActivity,OpenAuctionActivity.class);
                    intent.putExtra("name","竞拍未开始");
                    intent.putExtra("id",auctions.get(position).getCl_id());
                    //intent.putCharSequenceArrayListExtra("list",list_message);
                    intent.putExtra("max",max);
                    intent.putExtra("min",min);
                    intent.putExtra("people_Number",people);
                    intent.putExtra("quty",number);
                    intent.putExtra("state",state);
                    if (type.equals("公开竞拍")){
                        intent.putExtra("type","公开竞拍");
                    }else if (type.equals("密封竞拍")){
                        intent.putExtra("type","密封报价");
                    }
                    Log.d("id",auctions.get(position).getCl_id());
                    startActivity(intent);
                }else if (statu.equals("正在竞拍")){
                    Intent intent=new Intent(mActivity,OpenAuctionActivity.class);
                    intent.putExtra("name","竞拍已开始");
                    intent.putExtra("id",auctions.get(position).getCl_id());
                    intent.putExtra("state",state);
                    intent.putExtra("max",max);
                    intent.putExtra("min",min);
                    intent.putExtra("people_Number",people);
                    intent.putExtra("quty",number);
                    Log.d("id",auctions.get(position).getCl_id());
                    if (type.equals("公开竞拍")){
                        intent.putExtra("type","公开竞拍");
                    }else if (type.equals("密封竞拍")){
                        intent.putExtra("type","密封报价");
                    }
                    startActivity(intent);
                }else if (statu.equals("竞拍已结束")){
                    Intent intent=new Intent(mActivity,OpenAuctionActivity.class);
                    intent.putExtra("name","竞拍已结束");
                    intent.putExtra("id",auctions.get(position).getCl_id());
                    intent.putExtra("max",max);
                    intent.putExtra("min",min);
                    intent.putExtra("people_Number",people);
                    intent.putExtra("quty",number);
                    Log.d("商品详情的id",auctions.get(position).getCl_id());
                    intent.putExtra("state",state);
                    Log.d("id",auctions.get(position).getCl_id());
                    if (type.equals("公开竞拍")){
                        intent.putExtra("type","公开竞拍");
                    }else if (type.equals("密封竞拍")){
                        intent.putExtra("type","密封报价");
                    }
                    startActivity(intent);
                }





            }
        });

        pagerOnePresenter.getHomeSaleInfoToPagerOneFragment();
        lvFragmentoneAD.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                calendarlist  =new ArrayList<DayAndPrice>();
                calendarintent = new Intent();
                calendarintent.putExtra("calendarlist",(Serializable) calendarlist);
                // 产品编号
                calendarintent.putExtra("productId",forwards.get(position).getCl_id());
                // skuId
                calendarintent.setClass(mActivity, PreSaleDetailActivity.class);
                startActivity(calendarintent);

            }
        });
    }

    public void getResult(String productId){
        String uri=SuMaoConstant.SUMAO_IP+"/rest/model/atg/commerce/catalog/ProductCatalogActor/auctionResultList";
        RequestParams rp=new RequestParams(uri);
        rp.addParameter("productId",productId);
        Log.d("竞拍结果的返回值的rp",rp+"woshi");
        x.http().post(rp, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.d("竞拍的返回值",result);
                try {
                    JSONObject object=new JSONObject(result);
                    String object_result=object.getString("cl_type");
                    String array_result=object.getString("List");
                    String status=object.getString("status");
                    if (status.equals("0")){
                        statu="竞拍未开始";
                    }else if (status.equals("1")){
                        statu="正在竞拍";
                    }else if (status.equals("2")){
                        statu="竞拍已结束";
                    }

                     if (object_result.equals("englishAuctionProduct")){
                         type="公开竞拍";
                     }else if (object_result.equals("sealedAuctionProduct")){
                         type="密封竞拍";
                     }
                    if (array_result.equals("[]")){
                       max="最高竞拍价";
                       min="最高竞拍价";
                       people="最高竞拍价";
                       number="最高竞拍价";
                    }else {
                        JSONArray array=new JSONArray(array_result);
                        for (int i=0;i<array.length();i++){
                            JSONObject obj_array=array.getJSONObject(i);
                            max=obj_array.getString("max");
                            min=obj_array.getString("min");
                            people=obj_array.getString("pNumber");
                            number=obj_array.getString("quty");
                        }

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

    private void initHorn() {
        rlHorn = (RelativeLayout) view.findViewById(R.id.rlFragmentGridView);
        tvFragmentHord = ((MarqueeView) view.findViewById(R.id.tvFragmentHord));
        pagerOnePresenter.getAnnouncementInfoToPagerOneFragment();
        tvFragmentHord.setOnItemClickListener(new MarqueeView.OnItemClickListener(){
            @Override
            public void onItemClick(int position, TextView textView) {
                Intent intent = new Intent();
                intent.putExtra("id",items.get(position).getId());
                intent.setClass(mActivity,AnnouncementDetailActivity.class);
                startActivity(intent);
            }
        });
    }

    public void listAdwords() {
        lvFragmentAdwords = (ListView) view.findViewById(R.id.lvFragmentAdwords);
        Map<String, Object> map = new Hashtable<String, Object>();
        map.put("icon", R.mipmap.underwey);
        map.put("price", "8200");
        map.put("city", "北京");
        map.put("com", "福建联合");
        map.put("number", "8000");
        listString.add(map);
        Map<String, Object> map1 = new Hashtable<String, Object>();
        map1.put("icon", R.mipmap.underwey);
        map1.put("price", "8200");
        map1.put("city", "北京");
        map1.put("com", "福建联合");
        map1.put("number", "8000");
        listString.add(map1);
        Map<String, Object> map2 = new Hashtable<String, Object>();
        map2.put("icon", R.mipmap.underwey);
        map2.put("price", "8200");
        map2.put("city", "北京");
        map2.put("com", "福建联合");
        map2.put("number", "8000");
        listString.add(map2);
        Map<String, Object> map3 = new Hashtable<String, Object>();
        map3.put("icon", R.mipmap.underwey);
        map3.put("price", "8200");
        map3.put("city", "北京");
        map3.put("com", "福建联合");
        map3.put("number", "8000");
        listString.add(map3);
        SimpleAdapter adapter = new SimpleAdapter(mActivity, listString, R.layout.ragmentoneitemfordate,
                new String[]{"icon", "price", "city", "com", "number"},
                new int[]{R.id.ivfragmenticon, R.id.tvfragmentfordate, R.id.tvfragmentforcity, R.id.tvFragmentforcom, R.id.tvfragmentfornumber});
        lvFragmentAdwords.setAdapter(adapter);


    }

    /**
     * gridview实例化icon，并添加点击事件
     */
    public void gridview() {

        fl_content.addView(view);

        gvFragmentone = (GridView) view.findViewById(R.id.gvfragmentone);
        list = new ArrayList<Map<String, Object>>();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("icon", R.mipmap.goods);
        list.add(map);
        Map<String, Object> map1 = new HashMap<String, Object>();
        map1.put("icon", R.mipmap.presell);
        list.add(map1);
        Map<String, Object> map2 = new HashMap<String, Object>();
        map2.put("icon", R.mipmap.adwords);
        list.add(map2);
        Map<String, Object> map3 = new HashMap<String, Object>();
        map3.put("icon", R.mipmap.order);
        list.add(map3);
        Map<String, Object> map8 = new HashMap<String, Object>();
        map8.put("icon", R.mipmap.groupon);
        list.add(map8);
        Map<String, Object> map4 = new HashMap<String, Object>();
        map4.put("icon", R.mipmap.direct);
        list.add(map4);
        Map<String, Object> map5 = new HashMap<String, Object>();
        map5.put("icon", R.mipmap.aa);
        list.add(map5);
        Map<String, Object> map6 = new HashMap<String, Object>();
        map6.put("icon", R.mipmap.consult);
        list.add(map6);
        Map<String, Object> map13 = new HashMap<String, Object>();
        map13.put("icon", R.mipmap.maifang);
        list.add(map13);
        Map<String, Object> map7 = new HashMap<String, Object>();
        map7.put("icon", R.mipmap.more);
        list.add(map7);
        SimpleAdapter adapter = new SimpleAdapter(mActivity, list, R.layout.fragmentoneitem, new String[]{"icon"}, new int[]{R.id.ivIcon});
        gvFragmentone.setAdapter(adapter);
        /**
         * gridView里面icon的点击事件
         */
        gvFragmentone.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                if(list.get(position).get("icon").equals(R.mipmap.more)){
//                    if(list.size()==8){
//                    list.remove(position);
//                   // Toast.makeText(mActivity, "点击了更多按钮", Toast.LENGTH_SHORT).show();
//                    Map<String,Object> map7=new HashMap<String,Object>();
//                    map7.put("icon",R.mipmap.togther);
//                    list.add(map7);
//                    Map<String,Object> map8=new HashMap<String,Object>();
//                    map8.put("icon",R.mipmap.groupon);
//                    list.add(map8);
//                    Map<String,Object> map9=new HashMap<String,Object>();
//                    map9.put("icon",R.mipmap.more);
//                    list.add(map9);
//                    SimpleAdapter adapter=new SimpleAdapter(mActivity,list,R.layout.fragmentoneitem,new String[]{"icon"},new int[]{R.id.ivIcon});
//                    gvFragmentone.setAdapter(adapter);
//                }
                //   }else
                if (list.get(position).get("icon").equals(R.mipmap.togther)) {
                    Toast.makeText(mActivity, "点击了撮合按钮", Toast.LENGTH_SHORT).show();
                } else if (list.get(position).get("icon").equals(R.mipmap.groupon)) {
                    Intent intent = new Intent(mActivity, GroupBuyingActivity.class);
                    startActivity(intent);
                    //Toast.makeText(mActivity, "点击了团购按钮", Toast.LENGTH_SHORT).show();
                } else if (list.get(position).get("icon").equals(R.mipmap.goods)) {
                    Loding.show(mActivity,"加载中...",true,null);
                    pagerOnePresenter.getGoodsInStockInfo("","","",10,0);

                } else if (list.get(position).get("icon").equals(R.mipmap.presell)) {
                    //Toast.makeText(mActivity, "点击了预售按钮", Toast.LENGTH_SHORT).show();
                    Loding.show(mActivity,"加载中...",true,null);
                    pagerOnePresenter.getPreSaleInfo("","","",10,0);

                } else if (list.get(position).get("icon").equals(R.mipmap.adwords)) {
                    //Toast.makeText(mActivity, "点击了竞拍按钮", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(mActivity, AuctionActivity.class);
                    intent.putExtra("name","竞拍");
                    intent.putExtra("sellerCompanyId","no");
                   // startActivity(intent);
                    startActivityForResult(intent,1);
                } else if (list.get(position).get("icon").equals(R.mipmap.order)) {
                    Toast.makeText(mActivity, "点击了点价按钮", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(mActivity, MidpointsListctivity.class);
                    startActivity(intent);
                } else if (list.get(position).get("icon").equals(R.mipmap.direct)) {
//                    Toast.makeText(mActivity, "点击了上游直销按钮", Toast.LENGTH_SHORT).show();
                   SharedPreferences sp = mActivity.getSharedPreferences("sumao", Activity.MODE_PRIVATE);
                    String uniqued = sp.getString("unique","");
//                    unique
                    Loding.show(mActivity,"加载中...",true,null);
                    pagerOnePresenter.getUpstreamDirectSellingInfo(uniqued);

                } else if (list.get(position).get("icon").equals(R.mipmap.aa)) {
                    Toast.makeText(mActivity, "点击了物流按钮", Toast.LENGTH_SHORT).show();
//                    联系客服跳转示例
                   /* Intent intent = new Intent();
                    intent.setClass(mActivity, LoginActivity.class);
                    intent.putExtra(Constant.MESSAGE_TO_INTENT_EXTRA, Constant.MESSAGE_TO_DEFAULT);
                    intent.putExtra(Constant.IM_SERVICE_NUMBER, "feisumaokefu1");
                    startActivity(intent);*/
                } else if (list.get(position).get("icon").equals(R.mipmap.consult)) {
                    Loding.show(mActivity,"加载中...",true,null);
                   pagerOnePresenter.getSuMaoConsultInfo();
                    Toast.makeText(mActivity, "点击了塑贸咨询按钮", Toast.LENGTH_SHORT).show();
                } else if (list.get(position).get("icon").equals(R.mipmap.maifang)) {
//                    Toast.makeText(mActivity, "点击了卖方中心按钮", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent();
                    intent.setClass(mActivity, LoginUserActivity.class);
                    intent.putExtra("roles", "seller");
                    startActivity(intent);
                }
            }
        });
    }

    /**
     * 广告的Viewpager
     */
    private int preSelImgIndex = 0;

    public void vpad() {
        layouticon = (LinearLayout) view.findViewById(R.id.layout_icon);

        gallery = (MyGallery) view.findViewById(R.id.fragment_gallery);
        //给Listview填充数据
        InitImgList();


        gallery.setFocusable(true);
        gallery.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            public void onItemSelected(AdapterView<?> arg0, View arg1, int selIndex, long arg3) {
                //修改上一次选中项的背景
                selIndex = selIndex % listDrawable.size();

                ImageView preSelImg = (ImageView) layouticon.findViewById(preSelImgIndex);
                preSelImg.setImageDrawable(mActivity.getResources().getDrawable(R.drawable.ic_focus));
                //修改当前选中项的背景
                ImageView curSelImg = (ImageView) layouticon.findViewById(selIndex);
                curSelImg.setImageDrawable(mActivity.getResources().getDrawable(R.drawable.ic_focus_select));
                preSelImgIndex = selIndex;
            }

            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });
        gallery.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //广告条的点击事件
                Toast.makeText(mActivity, "点击了广告条", Toast.LENGTH_SHORT).show();
            }
        });

    }

    //实例化哪些点点
    private void InitFocusIndicatorContainer() {
        for (int i = 0; i < listDrawable.size(); i++) {
            ImageView localImageView = new ImageView(mActivity);
            localImageView.setId(i);
            ImageView.ScaleType localScaleType = ImageView.ScaleType.FIT_XY;
            localImageView.setScaleType(localScaleType);
            LinearLayout.LayoutParams localLayoutParams = new LinearLayout.LayoutParams(
                    30, 30);
            localImageView.setLayoutParams(localLayoutParams);
            localImageView.setPadding(8, 8, 8, 8);
            localImageView.setImageResource(R.drawable.ic_focus);
            this.layouticon.addView(localImageView);
        }

    }

    private void InitImgList() {
        // 加载图片数据
        pagerOnePresenter.getPagerOneInfoToPagerOneFragment();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
//        gallery.destroy();
    }

    @Override
    public void myClickLeft() {

    }

    // 客服界面
    @Override
    public void myClickRight() {
        Intent intent = new Intent();
        intent.setClass(mActivity, LoginActivity.class);
        intent.putExtra(Constant.MESSAGE_TO_INTENT_EXTRA, Constant.MESSAGE_TO_DEFAULT);
        intent.putExtra(Constant.IM_SERVICE_NUMBER, "sumaokefu");
        startActivity(intent);
    }

//获取到图片存放的路径，分别进行网络请求
    @Override
    public void setPagerOneBannerDataInFragment(BannerBean bannerBean) {
        banners = new ArrayList<>();

        banners.add(bannerBean.getImageUrl1());
        banners.add(bannerBean.getImageUrl2());
        banners.add(bannerBean.getImageUrl3());
        LogUtils.log("PagerOne-->" + banners);
        Iterator<String> stringIterator = banners.iterator();
        while (stringIterator.hasNext()) {
//            LogUtils.log("PagerOne-->"+stringIterator.next());
            pagerOnePresenter.getPictureFromServerToPagerOneFragment(stringIterator.next());
        }

    }

//    测试添加了多少张图片，可忽略
    int i = 0;
//把加载进来的图片添加到容器中
    @Override
    public void savePictureInPagerOneCollection(Drawable result) {

        LogUtils.log("PagerOne-->添加第" + i++ + "张图片");
        BitmapDrawable bd = (BitmapDrawable) result;
        listDrawable.add(bd.getBitmap());
        if (banners.size() == listDrawable.size()) {
            gallery.setAdapter(new ImageAdapter(mActivity, listDrawable));
            //哪些点点的初始化
            InitFocusIndicatorContainer();
        }
    }

    @Override
    public void saveAnnounceInAnnounceList(AnnounceBean announceBean) {
        LogUtils.log("PagerOne------------>"+announceBean.toString());
        items = new ArrayList<>();
        items.addAll(announceBean.getArticles());
        LogUtils.log("PagerOne------------>"+ items.toString());
        List<String> strings = new ArrayList<>();
        for (int i = 0; i < items.size(); i++) {
            strings.add(items.get(i).getHeadline());
        }
        tvFragmentHord.startWithList(strings);
    }

    @Override
    public void saveHomeSaleInFragmentList(PreSaleBean preSaleBean) {
//        预售信息
        forwards = preSaleBean.getForward();
        lvFragmentoneGrouponAdapter = new LvFragmentoneGrouponAdapter(forwards,mActivity);
        lvFragmentoneAD.setAdapter(lvFragmentoneGrouponAdapter);
//      竞拍信息
        auctions = preSaleBean.getAuction();
        LvFragmentoneAuctionsAdapter lvFragmentoneAuctionsAdapter = new LvFragmentoneAuctionsAdapter(auctions,mActivity);
        lvFragmentAdwords.setAdapter(lvFragmentoneAuctionsAdapter);
        //现货信息
        cls= preSaleBean.getGroup();
        LogUtils.log("现货信息cls.size()--->"+cls.size()+"");
        LvFragmentoneClsAdapter lvFragmentoneClsAdapter = new LvFragmentoneClsAdapter(cls,mActivity);
        lvFragmentoneGroupon.setAdapter(lvFragmentoneClsAdapter);

    }
    //把请求下的数据放置到现货界面
    @Override
    public void setDataInActivity(GoodsInStockActivityBean goodsInStockActivityBean) {
        Loding.dis();
        Intent intent = new Intent(mActivity, GoodsInStockActivity.class);
        //Toast.makeText(mActivity, "点击了现货按钮", Toast.LENGTH_SHORT).show();
        intent.putExtra("goodsInStockActivityBean",goodsInStockActivityBean);
        startActivity(intent);
    }
//把请求下的数据放置到预售界面

    @Override
    public void setPreSaleDataInActivity(GoodsInStockActivityBean goodsInStockActivityBean) {
        Loding.dis();
        Intent intent = new Intent();
        intent.setClass(mActivity, PreSale.class);
        intent.putExtra("preSaleActivityBean",goodsInStockActivityBean);
        startActivity(intent);

    }
//    点击塑贸资讯
    @Override
    public void setSuMaoConsultInActivity(AnnounceBean announceBean) {
        Loding.dis();
        Intent intent = new Intent();
        intent.setClass(mActivity,SuMaoConsult.class);
        intent.putExtra("announceBean",announceBean);
        startActivity(intent);

    }
//上游直销
    @Override
    public void setUpstreamDirectSellingInActivity(UpstreamDirectorySellingBean upstreamDirectorySellingBean) {
        Loding.dis();
        Intent intent = new Intent();
        intent.setClass(mActivity,UpstreamDirectSellingActivity.class);
        intent.putExtra("upstreamDirectorySellingBean",upstreamDirectorySellingBean);
        LogUtils.log("upstreamDirectorySellingBean"+upstreamDirectorySellingBean.getInfo());
        startActivity(intent);
    }

}

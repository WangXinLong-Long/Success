package com.silianchuangye.sumao.success.fragments.PagerOneMVP.view;

import android.Manifest;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.silianchuangye.sumao.success.HX.Constant;
import com.silianchuangye.sumao.success.HX.ui.LoginActivity;
import com.silianchuangye.sumao.success.MainActivity;
import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.adapter.ImageAdapter;
import com.silianchuangye.sumao.success.adapter.LvFragmentoneGrouponAdapter;
import com.silianchuangye.sumao.success.custom.customCalendar.DayAndPrice;
import com.silianchuangye.sumao.success.fragments.BasePager;
import com.silianchuangye.sumao.success.fragments.PagerOneMVP.MyAdapterItemClickListener;
import com.silianchuangye.sumao.success.fragments.PagerOneMVP.adapter.TextGrideViewAdapter;
import com.silianchuangye.sumao.success.fragments.PagerOneMVP.bean.AnnouncementBean;
import com.silianchuangye.sumao.success.fragments.PagerOneMVP.bean.ApplicationBean;
import com.silianchuangye.sumao.success.fragments.PagerOneMVP.bean.BannerBean;
import com.silianchuangye.sumao.success.fragments.PagerOneMVP.bean.ClassificationBean;
import com.silianchuangye.sumao.success.fragments.PagerOneMVP.bean.SupplierBean;
import com.silianchuangye.sumao.success.fragments.PagerOneMVP.presenter.PagerOnePresenter;
import com.silianchuangye.sumao.success.fragments.SearchActivityMVP.view.SearchActivity;
import com.silianchuangye.sumao.success.fragments.homepage.UpstreamDirectSellingMVP.UpstreamDirectDetailMVP.bean.DirectSellingBean;
import com.silianchuangye.sumao.success.fragments.homepage.UpstreamDirectSellingMVP.bean.UpstreamDirectorySellingBean;
import com.silianchuangye.sumao.success.fragments.homepage.UpstreamDirectSellingMVP.view.UpstreamDirectSellingActivity;
import com.silianchuangye.sumao.success.fragments.homepage.auction.AuctionMVP.view.AuctionActivity;
import com.silianchuangye.sumao.success.fragments.homepage.goodInStock.GoodsInStockActivityMVP.bean.GoodsInStockActivityBean;
import com.silianchuangye.sumao.success.fragments.homepage.goodInStock.GoodsInStockActivityMVP.view.GoodsInStockActivity;
import com.silianchuangye.sumao.success.fragments.homepage.groupbuying.GroupBuyingMVP.view.GroupBuyingActivity;
import com.silianchuangye.sumao.success.fragments.homepage.preSale.PreSaleMVP.bean.Auction;
import com.silianchuangye.sumao.success.fragments.homepage.preSale.PreSaleMVP.bean.Forward;
import com.silianchuangye.sumao.success.fragments.homepage.preSale.PreSaleMVP.bean.Group;
import com.silianchuangye.sumao.success.fragments.homepage.theprice.MidpointsListctivity;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.login.LoginUserActivity;
import com.silianchuangye.sumao.success.utils.Loding;
import com.silianchuangye.sumao.success.utils.LogUtils;
import com.silianchuangye.sumao.success.utils.MarqueeView;
import com.silianchuangye.sumao.success.utils.NetWorkChangeBroadcastReceiver;
import com.silianchuangye.sumao.success.utils.SuMaoConstant;
import com.silianchuangye.sumao.success.utils.ThreeLevelLindage.Area;
import com.silianchuangye.sumao.success.utils.ThreeLevelLindage.DBHelper;
import com.silianchuangye.sumao.success.utils.scrollviewAD.MyGallery;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Jobs on 2016/4/20 0020.
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
    private String type, max, min, number, people, statu = "";
    private List<Auction> auctions;
    private static final int REQUEST_PERMISSION_CAMERA_CODE = 1;
    static final String[] PERMISSIONS = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA};
    private GridView areaGridView;
    private GridView classificationGridView;
    private GridView applicationDetail;
    private GoodsInStockActivityBean goodsInStockActivityBean;
    private ClassificationBean classificationBean;
    private ApplicationBean applicationBean;
    private SupplierBean supplierBean;
    private GridView supplierDetail;
    private BroadcastReceiver netReceiver;
    private DirectSellingBean directSellingBean;
    private List<String> areaList;
    private List<String> areaListId;
    private List<String> classificationList;
    private List<String > yingyongId;
    private List<String> applicationList;
    private List<String > ShengchanId;
    private List<String> supplierBeanList;
    private List<String> fenlei_List;

    @Override
    public void myClickSearch() {
        //调到搜索页
        Intent intent = new Intent(mActivity, SearchActivity.class);
        startActivity(intent);
    }

    @Override
    public void initDate() {
        pagerOnePresenter = new PagerOnePresenter(this);
        view = (LinearLayout) View.inflate(mActivity, R.layout.fragmentone, null);
        fl_content.addView(view);
        initReceiver();
        gridview();
//        轮播图
        vpad();
        listString = new ArrayList<Map<String, Object>>();
        SharedPreferences sp = getActivity().getSharedPreferences("sumao", Activity.MODE_PRIVATE);
        String zhanghao = sp.getString("zhanghao", "");
        Log.d("账号的值", zhanghao);
        areaGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(mActivity, GoodsInStockActivity.class);
                intent.putExtra("title", "地区");
                intent.putExtra("id",areaListId.get(position).toString());
                intent.putExtra("goodsInStockActivityBean", goodsInStockActivityBean);
                intent.putExtra("classificationBean", classificationBean);
                intent.putExtra("applicationBean", applicationBean);
                intent.putExtra("supplierBean", supplierBean);
                //Log.d("传过去有的值",searchField+goodsInStockActivityBean+classificationBean+applicationBean+supplierBean+"");
                startActivity(intent);
            }
        });
        //分类
        classificationGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(mActivity, GoodsInStockActivity.class);
                intent.putExtra("title", "分类");
                intent.putExtra("id",fenlei_List.get(position).toString());
                intent.putExtra("goodsInStockActivityBean", goodsInStockActivityBean);
                intent.putExtra("classificationBean", classificationBean);
                intent.putExtra("applicationBean", applicationBean);
                intent.putExtra("supplierBean", supplierBean);
                //Log.d("传过去有的值",searchField+goodsInStockActivityBean+classificationBean+applicationBean+supplierBean+"");
                startActivity(intent);
            }
        });
        //应用
        applicationDetail.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(mActivity, GoodsInStockActivity.class);
                intent.putExtra("title", "应用");
                intent.putExtra("id",yingyongId.get(position).toString());
                intent.putExtra("goodsInStockActivityBean", goodsInStockActivityBean);
                intent.putExtra("classificationBean", classificationBean);
                intent.putExtra("applicationBean", applicationBean);
                intent.putExtra("supplierBean", supplierBean);
                //Log.d("传过去有的值",searchField+goodsInStockActivityBean+classificationBean+applicationBean+supplierBean+"");
                startActivity(intent);
            }
        });
        //供应商
        supplierDetail.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("供应商",ShengchanId.size()+"");
                Intent intent = new Intent(mActivity, GoodsInStockActivity.class);
                intent.putExtra("title", "供应商");
                intent.putExtra("id",ShengchanId.get(position).toString());
                intent.putExtra("goodsInStockActivityBean", goodsInStockActivityBean);
                intent.putExtra("classificationBean", classificationBean);
                intent.putExtra("applicationBean", applicationBean);
                intent.putExtra("supplierBean", supplierBean);
                //Log.d("传过去有的值",searchField+goodsInStockActivityBean+classificationBean+applicationBean+supplierBean+"");
                startActivity(intent);
            }
        });




    }

    public void getResult(String productId) {
        pagerOnePresenter.getAuctionResult(productId);

    }

    /**
     * gridview实例化icon，并添加点击事件
     */
    public void gridview() {
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
        Map<String, Object> map8 = new HashMap<String, Object>();
        map8.put("icon", R.mipmap.groupon);
        list.add(map8);
        Map<String, Object> map4 = new HashMap<String, Object>();
        map4.put("icon", R.mipmap.direct);
        list.add(map4);
        SimpleAdapter adapter = new SimpleAdapter(mActivity, list, R.layout.fragmentoneitem, new String[]{"icon"}, new int[]{R.id.ivIcon});
        gvFragmentone.setAdapter(adapter);
        LogUtils.log("预售等GrideView---------------》");
        /**
         * gridView里面icon的点击事件
         */
        gvFragmentone.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                boolean openNetwork = ((MainActivity) mActivity).isOpenNetwork();
                if (openNetwork) {


                    if (list.get(position).get("icon").equals(R.mipmap.togther)) {
                        Toast.makeText(mActivity, "点击了撮合按钮", Toast.LENGTH_SHORT).show();
                    } else if (list.get(position).get("icon").equals(R.mipmap.groupon)) {
                        Intent intent = new Intent(mActivity, GroupBuyingActivity.class);
                        startActivity(intent);
                        //Toast.makeText(mActivity, "点击了团购按钮", Toast.LENGTH_SHORT).show();
                    } else if (list.get(position).get("icon").equals(R.mipmap.goods)) {
                        //Toast.makeText(mActivity, "点击了现货按钮", Toast.LENGTH_SHORT).show();
                      //  Loding.show(mActivity, "加载中...", true, null);
                        String pattern = "fixedProduct";
                        String regions = null;
                        String provinces = null;
                        String cities = null;
                        String categoryNames = null;
                        String appNames = null;
                        String gradeNums = null;
                        String groups = null;
                        String pageNum = "1";
                        String updateOrLoad = "update";
                        Intent intent = new Intent(mActivity, GoodsInStockActivity.class);
                        intent.putExtra("title", "现货");
                        intent.putExtra("id","abc");
                        intent.putExtra("goodsInStockActivityBean", goodsInStockActivityBean);
                        intent.putExtra("classificationBean", classificationBean);
                        intent.putExtra("applicationBean", applicationBean);
                        intent.putExtra("supplierBean", supplierBean);
                        //Log.d("传过去有的值",searchField+goodsInStockActivityBean+classificationBean+applicationBean+supplierBean+"");
                        startActivity(intent);
//                        pagerOnePresenter .getGoodsInStockInfo(groups, pattern, regions, provinces, cities,
//                                categoryNames, appNames, gradeNums, pageNum, updateOrLoad);


                    } else if (list.get(position).get("icon").equals(R.mipmap.presell)) {
                        //Toast.makeText(mActivity, "点击了预售按钮", Toast.LENGTH_SHORT).show();
                     //   Loding.show(mActivity, "加载中...", true, null);
                        String pattern = "forward-pricing-product";
                        String regions = null;
                        String provinces = null;
                        String cities = null;
                        String categoryNames = null;
                        String appNames = null;
                        String gradeNums = null;
                        String pageNum = "1";
                        Intent intent = new Intent(mActivity, GoodsInStockActivity.class);
                        intent.putExtra("title", "预售");
                        intent.putExtra("id","abc");
                        intent.putExtra("goodsInStockActivityBean", goodsInStockActivityBean);
                        intent.putExtra("classificationBean", classificationBean);
                        intent.putExtra("applicationBean", applicationBean);
                        intent.putExtra("supplierBean", supplierBean);
                        startActivity(intent);
//                        pagerOnePresenter.getPreSaleInfo(pattern, regions, provinces, cities,
//                                categoryNames, appNames, gradeNums, pageNum, "update");
                        //pagerOnePresenter.getGoodsInStockForsearch();

                    } else if (list.get(position).get("icon").equals(R.mipmap.adwords)) {
                        //Toast.makeText(mActivity, "点击了竞拍按钮", Toast.LENGTH_SHORT).show();
                        String pageNum = 1 + "";
                        pagerOnePresenter.getAuctionListResult(pageNum, "update");

                    } else if (list.get(position).get("icon").equals(R.mipmap.order)) {
                        Toast.makeText(mActivity, "点击了点价按钮", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(mActivity, MidpointsListctivity.class);
                        startActivity(intent);
                    } else if (list.get(position).get("icon").equals(R.mipmap.direct)) {
//                    Toast.makeText(mActivity, "点击了上游直销按钮", Toast.LENGTH_SHORT).show();
                        SharedPreferences sp = mActivity.getSharedPreferences("sumao", Activity.MODE_PRIVATE);
                        String uniqued = sp.getString("unique", "");
//                    unique
                        Loding.show(mActivity, "加载中...", true, null);
                        pagerOnePresenter.getUpstreamDirectSellingInfo(uniqued);
                    } else if (list.get(position).get("icon").equals(R.mipmap.aa)) {
                        Toast.makeText(mActivity, "点击了物流按钮", Toast.LENGTH_SHORT).show();
//                    联系客服跳转示例
                   /* Intent intent = new Intent();
                    intent.setClass(mActivity, LoginActivity.class);
                    intent.putExtra(Constant.MESSAGE_TO_INTENT_EXTRA, Constant.MESSAGE_TO_DEFAULT);
                    intent.putExtra(Constant.IM_SERVICE_NUMBER, "feisumaokefu1");
                    startActivity(intent);*/
                    } else if (list.get(position).get("icon").equals(R.mipmap.maifang)) {
                        Intent intent = new Intent();
                        intent.setClass(mActivity, LoginUserActivity.class);
                        intent.putExtra("roles", "seller");
                        startActivity(intent);
                    }
                }else {
                    Toast.makeText(mActivity, "暂时没有网络连接,请检查您的网络设置", Toast.LENGTH_SHORT).show();
                }
            }
        });

//        地区的GridView
        areaGridView = ((GridView) view.findViewById(R.id.area_detail));

//        分类的GridView
        classificationGridView = ((GridView) view.findViewById(R.id.classification_detail));

//        应用的GridView
        applicationDetail = ((GridView) view.findViewById(R.id.application_detail));

//        供应商的GridView
        supplierDetail = ((GridView) view.findViewById(R.id.supplier_detail));
        pagerOnePresenter.getClassification("现货");

    }

    /**
     * 广告的Viewpager
     */
    private int preSelImgIndex = 0;

    public void vpad() {
        layouticon = (LinearLayout) view.findViewById(R.id.layout_icon);

        gallery = (MyGallery) view.findViewById(R.id.fragment_gallery);
        //给Listview填充数据
        initImgList();


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

    // 加载图片数据
    private void initImgList() {
        pagerOnePresenter.getPagerOneInfoToPagerOneFragment();
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
        intent.putExtra(Constant.IM_SERVICE_NUMBER, "塑贸客服");
        startActivity(intent);
    }

    //获取到图片存放的路径，分别进行网络请求
    @Override
    public void setPagerOneBannerDataInFragment(BannerBean bannerBean) {
//        TODO 注释掉这些
        LogUtils.log("添加三张静态图片--->");
        listDrawable.add(BitmapFactory.decodeResource(getResources(), R.mipmap.carousel_figure1));
        listDrawable.add(BitmapFactory.decodeResource(getResources(), R.mipmap.carousel_figure2));
        listDrawable.add(BitmapFactory.decodeResource(getResources(), R.mipmap.carousel_figure3));
        listDrawable.add(BitmapFactory.decodeResource(getResources(), R.mipmap.carousel_figure4));
        gallery.setAdapter(new ImageAdapter(mActivity, listDrawable));
        InitFocusIndicatorContainer();
//        TODO 网络图片放开底下的就行
//        banners = new ArrayList<>();
//
//        banners.add(bannerBean.getImageUrl1());
//        banners.add(bannerBean.getImageUrl2());
//        banners.add(bannerBean.getImageUrl3());
//        LogUtils.log("PagerOne-->" + banners);
//        Iterator<String> stringIterator = banners.iterator();
//        while (stringIterator.hasNext()) {
//        pagerOnePresenter.getPictureFromServerToPagerOneFragment(stringIterator.next());
//        }

    }

    //    测试添加了多少张图片，可忽略
    int i = 0;

    //把加载进来的图片添加到容器中
    @Override
    public void savePictureInPagerOneCollection(Drawable result) {
        LogUtils.log("PagerOne-->添加第" + i++ + "张图片");
        listDrawable.add(BitmapFactory.decodeResource(getResources(), R.mipmap.carousel_figure1));
        listDrawable.add(BitmapFactory.decodeResource(getResources(), R.mipmap.carousel_figure2));
        listDrawable.add(BitmapFactory.decodeResource(getResources(), R.mipmap.carousel_figure3));
        BitmapDrawable bd = (BitmapDrawable) result;
        listDrawable.add(bd.getBitmap());
        if (banners.size() == listDrawable.size()) {
            gallery.setAdapter(new ImageAdapter(mActivity, listDrawable));
            //哪些点点的初始化
            InitFocusIndicatorContainer();
        }
    }

    //把请求下的数据放置到现货界面
    @Override
    public void setDataInActivity(GoodsInStockActivityBean goodsInStockActivityBean, String updateOrLoad) {
//        Loding.dis();
        String transactionType = "现货";
        Intent intent = new Intent(mActivity, GoodsInStockActivity.class);
        if (transactionType.equals("现货")) {
            intent.putExtra("title", "现货");
        } else if (transactionType.equals("预售")) {
            intent.putExtra("title", "预售");
        }
        intent.putExtra("id","abc");
        intent.putExtra("goodsInStockActivityBean", goodsInStockActivityBean);
        intent.putExtra("classificationBean", classificationBean);
        intent.putExtra("applicationBean", applicationBean);
        intent.putExtra("supplierBean", supplierBean);
        startActivity(intent);
    }

    //把请求下的数据放置到预售界面
    @Override
    public void setPreSaleDataInActivity(GoodsInStockActivityBean goodsInStockActivityBean, String updateOrLoad) {
        Loding.dis();
        pagerOnePresenter.getClassification("预售");
        String transactionType = "预售";
        Intent intent = new Intent(mActivity, GoodsInStockActivity.class);
        if (transactionType.equals("现货")) {
            intent.putExtra("title", "现货");
        } else if (transactionType.equals("预售")) {
            intent.putExtra("title", "预售");
        }
        intent.putExtra("id","abc");
        intent.putExtra("goodsInStockActivityBean", goodsInStockActivityBean);
        intent.putExtra("classificationBean", classificationBean);
        intent.putExtra("applicationBean", applicationBean);
        intent.putExtra("supplierBean", supplierBean);
        startActivity(intent);

    }
    public void tuichu(){
        RequestParams  rp=new RequestParams(SuMaoConstant.SUMAO_IP+"/rest/model/atg/userprofiling/ProfileActor/logout");
        SharedPreferences sp=mActivity.getSharedPreferences("sumao",0);
        //SharedPreferences sp1=g
        String unique=sp.getString("unique","");
        rp.addParameter("_dynSessConf",unique);
        Log.d("退出时的唯一标识",unique);
        x.http().post(rp, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                SharedPreferences sp=mActivity.getSharedPreferences("sumao", Activity.MODE_PRIVATE);
                SharedPreferences.Editor editor=sp.edit();
                editor.clear();
                //      editor.clear();
                editor.commit();
                //Toast.makeText(context, "用户名称已删除", Toast.LENGTH_SHORT).show();

//                                            SettingActivity.this.finish();

//                                        }
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
//                            }
//                        }.start();


    }
    //上游直销
    @Override
    public void setUpstreamDirectSellingInActivity(UpstreamDirectorySellingBean upstreamDirectorySellingBean) {
        Loding.dis();
        SharedPreferences sp=mActivity.getSharedPreferences("sumao", Activity.MODE_PRIVATE);
        String unique=sp.getString("unique","");
        Log.d("上游的unique",""+unique);

        if (unique.equals("false")||unique==""||unique==null||unique.equals("")){
            tuichu();
            Intent intent=new Intent(mActivity, LoginUserActivity.class);
            intent.putExtra("roles","buyer");
            startActivity(intent);
        }else{
          //  if ()//用户没有登录的状态
            infoLogin(unique,upstreamDirectorySellingBean);

        }
    }

    public void infoLogin(String _dynSessConf,final UpstreamDirectorySellingBean upstreamDirectorySellingBean){
        String url=SuMaoConstant.SUMAO_IP+"/rest/model/atg/userprofiling/ProfileActor/appCustomerInfo";
        RequestParams rp=new RequestParams(url);
        rp.addParameter("_dynSessConf",_dynSessConf);
        Log.d("上游的rp",rp+"");
        x.http().post(rp, new Callback.CommonCallback<String>() {

            @Override
            public void onSuccess(String result) {
                Log.d("判断是否denglu",result);
                if(result.contains("fail")){
                    tuichu();
                    Intent intent=new Intent(mActivity, LoginUserActivity.class);
                    intent.putExtra("roles","buyer");
                    startActivity(intent);
                }else{
                     Intent intent = new Intent();
                     intent.setClass(mActivity, UpstreamDirectSellingActivity.class);
                     intent.putExtra("upstreamDirectorySellingBean", upstreamDirectorySellingBean);
                     LogUtils.log("upstreamDirectorySellingBean" + upstreamDirectorySellingBean.getInfo());
                     startActivity(intent);
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



    //将请求下来的值设置到Adapter中
    @Override
    public void setGrideViewDataInActivity() {
        //        TODO  设置适配器

    }

    @Override
    public void getClassificationData(ClassificationBean classificationBean, String transactionType) {
        this.classificationBean = classificationBean;
        pagerOnePresenter.getSupplier(transactionType);


    }

    @Override
    public void getApplicationData(ApplicationBean applicationBean, String transactionType) {
        ArrayList<Area> provinceList = new DBHelper(getActivity().getApplicationContext()).getProvince();
//        Loding.dis();
        this.applicationBean = applicationBean;
        areaList= new ArrayList<>();
        classificationList= new ArrayList<>();
        applicationList= new ArrayList<>();
        supplierBeanList = new ArrayList<>();
//        for (int i = 0; i < classificationBean.getIn_flname().size(); i++) {
//            classificationList.add(classificationBean.getIn_flname().get(i));
//        }
        new Thread(){
            @Override
            public void run() {
                super.run();
                get_area();
//                get_yingyong();
//                get_shengchan();
//                get_fenlei();
            }
        }.start();


    }

    public void get_area(){
        String url= SuMaoConstant.SUMAO_IP+"/rest/model/atg/endeca/HomeActor/EndecaAppHome";
        RequestParams rp=new RequestParams(url);
        x.http().post(rp, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.d("分类的返回值",result);
                try {
                    areaListId=new ArrayList<String>();
                    JSONObject jsonObject=new JSONObject(result);
                    String object_fenlei=jsonObject.getString("dq");
                    JSONArray array_Fenlei=new JSONArray(object_fenlei);

                    for (int i=0;i<array_Fenlei.length();i++){
                        JSONObject ob_fl=array_Fenlei.getJSONObject(i);
                        areaList.add(ob_fl.getString("dq_name"));
                        areaListId.add(ob_fl.getString("dq_id"));
                    }
                    Log.d("area的值",areaList.size()+"条");
                    TextGrideViewAdapter areaAdapter = new TextGrideViewAdapter(getActivity());
                    areaAdapter.addData(areaList);
                    areaGridView.setAdapter(areaAdapter);
                    yingyongId=new ArrayList<String>();
                    //JSONObject jsonObject=new JSONObject(result);
                    String object_yingyong=jsonObject.getString("yy");
                    JSONArray array_yingyong=new JSONArray(object_yingyong);

                    if (array_yingyong.length()>12){
                        for (int i=0;i<12;i++){
                            JSONObject ob_fl=array_yingyong.getJSONObject(i);
                            classificationList.add(ob_fl.getString("yy_name"));
                            yingyongId.add(ob_fl.getString("yy_id"));
                        }
                    }else {
                        for (int i=0;i<array_yingyong.length();i++){
                            JSONObject ob_fl=array_yingyong.getJSONObject(i);
                            classificationList.add(ob_fl.getString("yy_name"));
                            yingyongId.add(ob_fl.getString("yy_id"));
                        }
                    }

                    TextGrideViewAdapter classificationAdapter = new TextGrideViewAdapter(getActivity());
                    classificationAdapter.addData(classificationList);
                    //classificationGridView.setAdapter(classificationAdapter);
                    applicationDetail.setAdapter(classificationAdapter);
                    //分类
                    fenlei_List=new ArrayList<String>();

                    String object_fl=jsonObject.getString("fl");
                    JSONArray array_fl=new JSONArray(object_fl);
                    if (array_Fenlei.length()>15){
                        for (int i=0;i<15;i++){
                            JSONObject ob_fl=array_fl.getJSONObject(i);
                            applicationList.add(ob_fl.getString("fl_name"));
                            fenlei_List.add(ob_fl.getString("fl_id"));
                        }
                    }else{
                        for (int i=0;i<array_fl.length();i++){
                            JSONObject ob_fl=array_fl.getJSONObject(i);
                            applicationList.add(ob_fl.getString("fl_name"));
                            fenlei_List.add(ob_fl.getString("fl_id"));
                        }
                    }

                    TextGrideViewAdapter supplierAdapter = new TextGrideViewAdapter(getActivity());
                    supplierAdapter.addData(applicationList);
                    classificationGridView.setAdapter(supplierAdapter);
                    //生产商ShengchanId
                    ShengchanId=new ArrayList<String>();
                    String object_sc=jsonObject.getString("sc");
                    JSONArray array_sc=new JSONArray(object_sc);
                    if (array_sc.length()>10){
                        for (int i=0;i<10;i++){
                            JSONObject ob_fl=array_sc.getJSONObject(i);
                            supplierBeanList.add(ob_fl.getString("sc_name"));
                            ShengchanId.add(ob_fl.getString("sc_id"));
                        }
                    }else {
                        for (int i=0;i<array_sc.length();i++){
                            JSONObject ob_fl=array_sc.getJSONObject(i);
                            supplierBeanList.add(ob_fl.getString("sc_name"));
                            ShengchanId.add(ob_fl.getString("sc_id"));
                        }
                    }




                    TextGrideViewAdapter applicationAdapter = new TextGrideViewAdapter(getActivity());
                    applicationAdapter.addData(supplierBeanList);
                    supplierDetail.setAdapter(applicationAdapter);


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

//    public void get_fenlei(){
//        String url= SuMaoConstant.SUMAO_IP+"/rest/model/atg/endeca/HomeActor/EndecaAppApplication";
//        RequestParams rp=new RequestParams(url);
//        x.http().post(rp, new Callback.CommonCallback<String>() {
//            @Override
//            public void onSuccess(String result) {
//                Log.d("分类的返回值",result);
//                try {
//
//
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//
//            @Override
//            public void onError(Throwable ex, boolean isOnCallback) {
//
//            }
//
//            @Override
//            public void onCancelled(CancelledException cex) {
//
//            }
//
//            @Override
//            public void onFinished() {
//
//            }
//        });
//    }

//    public void get_yingyong(){
//        String url= SuMaoConstant.SUMAO_IP+"/rest/model/atg/endeca/HomeActor/EndecaAppSupplier";
//        RequestParams rp=new RequestParams(url);
//        x.http().post(rp, new Callback.CommonCallback<String>() {
//            @Override
//            public void onSuccess(String result) {
//                Log.d("分类的返回值",result);
//                try {
//                    ShengchanId=new ArrayList<String>();
//                    JSONObject jsonObject=new JSONObject(result);
//                    String object_fenlei=jsonObject.getString("cl");
//                    JSONArray array_Fenlei=new JSONArray(object_fenlei);
//                    for (int i=0;i<array_Fenlei.length();i++){
//                        JSONObject ob_fl=array_Fenlei.getJSONObject(i);
//                        applicationList.add(ob_fl.getString("cl_name"));
//                        ShengchanId.add(ob_fl.getString("cl_id"));
//                    }
//                    TextGrideViewAdapter supplierAdapter = new TextGrideViewAdapter(getActivity());
//                    supplierAdapter.addData(applicationList);
//                    supplierDetail.setAdapter(supplierAdapter);
//
//
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//
//            @Override
//            public void onError(Throwable ex, boolean isOnCallback) {
//
//            }
//
//            @Override
//            public void onCancelled(CancelledException cex) {
//
//            }
//
//            @Override
//            public void onFinished() {
//
//            }
//        });
//    }
//
//    public void get_shengchan(){
//        String url= SuMaoConstant.SUMAO_IP+"/rest/model/atg/endeca/HomeActor/EndecaAppCategory";
//        RequestParams rp=new RequestParams(url);
//        x.http().post(rp, new Callback.CommonCallback<String>() {
//            @Override
//            public void onSuccess(String result) {
//                Log.d("分类的返回值",result);
//                try {
//                    fenlei_List=new ArrayList<String>();
//                    JSONObject jsonObject=new JSONObject(result);
//                    String object_fenlei=jsonObject.getString("cl");
//                    JSONArray array_Fenlei=new JSONArray(object_fenlei);
//                    for (int i=0;i<array_Fenlei.length();i++){
//                        JSONObject ob_fl=array_Fenlei.getJSONObject(i);
//                        supplierBeanList.add(ob_fl.getString("cl_name"));
//                        fenlei_List.add(ob_fl.getString("cl_id"));
//                    }
//
//
//
//                    TextGrideViewAdapter applicationAdapter = new TextGrideViewAdapter(getActivity());
//                    applicationAdapter.addData(supplierBeanList);
//                    applicationDetail.setAdapter(applicationAdapter);
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//
//            @Override
//            public void onError(Throwable ex, boolean isOnCallback) {
//
//            }
//
//            @Override
//            public void onCancelled(CancelledException cex) {
//
//            }
//
//            @Override
//            public void onFinished() {
//
//            }
//        });
  //  }

    @Override
    public void getSupplier(SupplierBean supplierBean, String transactionType) {
        this.supplierBean = supplierBean;
        pagerOnePresenter.getApplication(transactionType);
    }

    @Override
    public void getAuctionData(String result) {
        try {
            JSONObject object = new JSONObject(result);
            String object_result = object.getString("cl_type");
            String array_result = object.getString("List");
            String status = object.getString("status");
            if (status.equals("0")) {
                statu = "竞拍未开始";
            } else if (status.equals("1")) {
                statu = "正在竞拍";
            } else if (status.equals("2")) {
                statu = "竞拍已结束";
            }
            if (object_result.equals("englishAuctionProduct")) {
                type = "公开竞拍";
            } else if (object_result.equals("sealedAuctionProduct")) {
                type = "密封竞拍";
            }
            if (array_result.equals("[]")) {
                max = "最高竞拍价";
                min = "最高竞拍价";
                people = "最高竞拍价";
                number = "最高竞拍价";
            } else {
                JSONArray array = new JSONArray(array_result);
                for (int i = 0; i < array.length(); i++) {
                    JSONObject obj_array = array.getJSONObject(i);
                    max = obj_array.getString("max");
                    min = obj_array.getString("min");
                    people = obj_array.getString("pNumber");
                    number = obj_array.getString("quty");
                }

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    //    竞拍
    @Override
    public void setAuctionListResult(String result, String updateOrLoad) {
        Intent intent = new Intent(mActivity, AuctionActivity.class);
        intent.putExtra("name", "竞拍");
        intent.putExtra("result", result);
        startActivityForResult(intent, 1);
    }

    //    GrideView 的筛选
    @Override
    public void setDataInSearchActivity(GoodsInStockActivityBean goodsInStockActivityBean, String updateOrLoad, String searchField) {
        Loding.dis();
        String transactionType = "现货";
        Intent intent = new Intent(mActivity, GoodsInStockActivity.class);
        intent.putExtra("title", searchField);
        intent.putExtra("id","abc");
        intent.putExtra("goodsInStockActivityBean", goodsInStockActivityBean);
        intent.putExtra("classificationBean", classificationBean);
        intent.putExtra("applicationBean", applicationBean);
        intent.putExtra("supplierBean", supplierBean);
        Log.d("传过去有的值",searchField+goodsInStockActivityBean+classificationBean+applicationBean+supplierBean+"");
        startActivity(intent);
    }

    //      监听广播
    private void initReceiver() {
        netReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                //                判断当前网络状态
                if (intent.getIntExtra(NetWorkChangeBroadcastReceiver.NET_TYPE, 0) == 0) {
                    //                    0表示无网络连接,
                    //这里填你想要执行的东西
                    Toast.makeText(context, "暂时没有网络,请检查您的网络设置", Toast.LENGTH_SHORT).show();
                } else {
                    //                    1表示有
                    pagerOnePresenter.getClassification("现货");
                    initImgList();
                }
            }
        };
        IntentFilter filter = new IntentFilter(NetWorkChangeBroadcastReceiver.NET_CHANGE);
        getActivity().registerReceiver(netReceiver, filter);
    }
}

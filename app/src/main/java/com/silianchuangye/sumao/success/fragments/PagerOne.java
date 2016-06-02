package com.silianchuangye.sumao.success.fragments;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.Toast;


import com.silianchuangye.sumao.success.fragments.homepage.auction.AuctionActivity;
import com.silianchuangye.sumao.success.HX.Constant;
import com.silianchuangye.sumao.success.HX.ui.LoginActivity;
import com.silianchuangye.sumao.success.fragments.homepage.goodInStock.GoodsInStockActivity;
import com.silianchuangye.sumao.success.fragments.homepage.preSale.PreSale;
import com.silianchuangye.sumao.success.fragments.homepage.theprice.MidpointsListctivity;
import com.silianchuangye.sumao.success.utils.scrollviewAD.MyGallery;
import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.adapter.ImageAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/4/20 0020.
 */
public class PagerOne extends BasePager {
    private LinearLayout layouticon;
    private List<Drawable> listDrawable=new ArrayList<Drawable>();
    private MyGallery gallery;
    private GridView gvFragmentone;
    private List<Map<String,Object>> list;
    private LinearLayout view;
    private ListView lvFragmentAdwords;
    private List<Map<String,Object>> listString;
    private RelativeLayout rlHorn;
    @Override
    public void myClickSearch() {
    }

    @Override
    public void initDate() {
        view= (LinearLayout) View.inflate(mActivity,R.layout.fragmentone,null);
        gridview();
        vpad();
        listString=new ArrayList<Map<String,Object>>();
        listAdwords();
        initHorn();
    }
    private void initHorn(){
        rlHorn= (RelativeLayout) view.findViewById(R.id.rlFragmentGridView);
        rlHorn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //小喇叭的点击事件，调到详情展示
                Toast.makeText(mActivity, "这是详情展示", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void listAdwords(){
        lvFragmentAdwords= (ListView) view.findViewById(R.id.lvFragmentAdwords);
        Map<String,Object> map=new Hashtable<String,Object>();
        map.put("icon",R.mipmap.underwey);
        map.put("price","8200");
        map.put("city","北京");
        map.put("com","福建联合");
        map.put("number","8000");
        listString.add(map);
        Map<String,Object> map1=new Hashtable<String,Object>();
        map1.put("icon",R.mipmap.underwey);
        map1.put("price","8200");
        map1.put("city","北京");
        map1.put("com","福建联合");
        map1.put("number","8000");
        listString.add(map1);
        Map<String,Object> map2=new Hashtable<String,Object>();
        map2.put("icon",R.mipmap.underwey);
        map2.put("price","8200");
        map2.put("city","北京");
        map2.put("com","福建联合");
        map2.put("number","8000");
        listString.add(map2);
        Map<String,Object> map3=new Hashtable<String,Object>();
        map3.put("icon",R.mipmap.underwey);
        map3.put("price","8200");
        map3.put("city","北京");
        map3.put("com","福建联合");
        map3.put("number","8000");
        listString.add(map3);
        SimpleAdapter adapter=new SimpleAdapter(mActivity,listString,R.layout.ragmentoneitemfordate,new String[]{"icon","price","city","com","number"},new int[]{R.id.ivfragmenticon,R.id.tvfragmentfordate,R.id.tvfragmentforcity,R.id.tvFragmentforcom,R.id.tvfragmentfornumber});
        lvFragmentAdwords.setAdapter(adapter);


    }

    /**
     * gridview实例化icon，并添加点击事件
     */
    public void gridview(){

        fl_content.addView(view);

        gvFragmentone= (GridView) view.findViewById(R.id.gvfragmentone);
        list=new ArrayList<Map<String,Object>>();
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("icon",R.mipmap.goods);
        list.add(map);
        Map<String,Object> map1=new HashMap<String,Object>();
        map1.put("icon",R.mipmap.presell);
        list.add(map1);
        Map<String,Object> map2=new HashMap<String,Object>();
        map2.put("icon",R.mipmap.adwords);
        list.add(map2);
        Map<String,Object> map3=new HashMap<String,Object>();
        map3.put("icon",R.mipmap.order);
        list.add(map3);
        Map<String,Object> map4=new HashMap<String,Object>();
        map4.put("icon",R.mipmap.direct);
        list.add(map4);
        Map<String,Object> map5=new HashMap<String,Object>();
        map5.put("icon",R.mipmap.aa);
        list.add(map5);
        Map<String,Object> map6=new HashMap<String,Object>();
        map6.put("icon",R.mipmap.consult);
        list.add(map6);
        Map<String,Object> map7=new HashMap<String,Object>();
        map7.put("icon",R.mipmap.more);
        list.add(map7);
        SimpleAdapter adapter=new SimpleAdapter(mActivity,list,R.layout.fragmentoneitem,new String[]{"icon"},new int[]{R.id.ivIcon});
        gvFragmentone.setAdapter(adapter);
        /**
         * gridView里面icon的点击事件
         */
        gvFragmentone.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(list.get(position).get("icon").equals(R.mipmap.more)){
                    if(list.size()==8){
                    list.remove(position);
                   // Toast.makeText(mActivity, "点击了更多按钮", Toast.LENGTH_SHORT).show();
                    Map<String,Object> map7=new HashMap<String,Object>();
                    map7.put("icon",R.mipmap.togther);
                    list.add(map7);
                    Map<String,Object> map8=new HashMap<String,Object>();
                    map8.put("icon",R.mipmap.groupon);
                    list.add(map8);
                    Map<String,Object> map9=new HashMap<String,Object>();
                    map9.put("icon",R.mipmap.more);
                    list.add(map9);
                    SimpleAdapter adapter=new SimpleAdapter(mActivity,list,R.layout.fragmentoneitem,new String[]{"icon"},new int[]{R.id.ivIcon});
                    gvFragmentone.setAdapter(adapter);
                }
                }else if(list.get(position).get("icon").equals(R.mipmap.togther)){
                    Toast.makeText(mActivity, "点击了撮合按钮", Toast.LENGTH_SHORT).show();

                }else if(list.get(position).get("icon").equals(R.mipmap.groupon)){
                    Toast.makeText(mActivity, "点击了团购按钮", Toast.LENGTH_SHORT).show();
                }else if(list.get(position).get("icon").equals(R.mipmap.goods)){
                    Intent intent=new Intent(mActivity, GoodsInStockActivity.class);
                    //Toast.makeText(mActivity, "点击了现货按钮", Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                }else if(list.get(position).get("icon").equals(R.mipmap.presell)){
                    //Toast.makeText(mActivity, "点击了预售按钮", Toast.LENGTH_SHORT).show();
                   Intent intent = new Intent();
                    intent.setClass(mActivity,PreSale.class);
                    startActivity(intent);
                }else if(list.get(position).get("icon").equals(R.mipmap.adwords)){
                   //Toast.makeText(mActivity, "点击了竞拍按钮", Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(mActivity, AuctionActivity.class);
                    startActivity(intent);
                }else if(list.get(position).get("icon").equals(R.mipmap.order)){
                    Toast.makeText(mActivity, "点击了点价按钮", Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(mActivity, MidpointsListctivity.class);
                    startActivity(intent);
                }else if(list.get(position).get("icon").equals(R.mipmap.direct)){
                    Toast.makeText(mActivity, "点击了上游直销按钮", Toast.LENGTH_SHORT).show();
                }else if(list.get(position).get("icon").equals(R.mipmap.aa)){
                    Toast.makeText(mActivity, "点击了物流按钮", Toast.LENGTH_SHORT).show();
                }else if(list.get(position).get("icon").equals(R.mipmap.consult)){
                    Toast.makeText(mActivity, "点击了塑贸咨询按钮", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    /**
     * 广告的Viewpager
     */
    private int preSelImgIndex = 0;
    public void vpad(){
        layouticon= (LinearLayout)view.findViewById(R.id.layout_icon);

        //给Listview填充数据
        InitImgList();
        //哪些点点的初始化
        InitFocusIndicatorContainer();
        gallery = (MyGallery) view.findViewById(R.id.fragment_gallery);
        gallery.setAdapter(new ImageAdapter(mActivity,listDrawable));
        gallery.setFocusable(true);
        gallery.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int selIndex, long arg3) {
                //修改上一次选中项的背景
                selIndex = selIndex % listDrawable.size();

                ImageView preSelImg = (ImageView) layouticon
                        .findViewById(preSelImgIndex);
                preSelImg.setImageDrawable(mActivity
                        .getResources().getDrawable(R.drawable.ic_focus));
                //修改当前选中项的背景
                ImageView curSelImg = (ImageView) layouticon
                        .findViewById(selIndex);
                curSelImg
                        .setImageDrawable(mActivity
                                .getResources().getDrawable(
                                        R.drawable.ic_focus_select));
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
        listDrawable.add(this.getResources().getDrawable(R.drawable.img1));
        listDrawable.add(this.getResources().getDrawable(R.drawable.img2));
        listDrawable.add(this.getResources().getDrawable(R.drawable.img3));
        listDrawable.add(this.getResources().getDrawable(R.drawable.img4));
       // listDrawable.add(this.getResources().getDrawable(R.drawable.img5));

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        gallery.destroy();
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
        startActivity(intent);
    }
}

package com.silianchuangye.sumao.success.fragments.pagerTwoMVP.pagerTwoChilds.InformationPageFragmentMVP.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.fragments.pagerTwoMVP.pagerTwoChilds.InformationPageFragmentMVP.bean.PagerTwoInformationPageBean;
import com.silianchuangye.sumao.success.utils.SuMaoConstant;

import org.xutils.common.Callback;
import org.xutils.common.util.DensityUtil;
import org.xutils.http.RequestParams;
import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/12/14.
 */

public class PagerTwoInformationPageListViewAdapter extends BaseAdapter {
    List<PagerTwoInformationPageBean.InBean> pagerTwoListViewBeen;
    Context context;
    LayoutInflater inflater;
    ImageOptions imageOptions;
    String unique;

    public PagerTwoInformationPageListViewAdapter( Context context) {
        this.pagerTwoListViewBeen = new ArrayList<>();
        this.context = context;
        inflater = LayoutInflater.from(context);
//        BitmapUtils bitmapUtils = new BitmapUtils(this);
        imageOptions = new ImageOptions.Builder().
                setSize(DensityUtil.dip2px(120), DensityUtil.dip2px(120))
                .setRadius(DensityUtil.dip2px(5))
                .setCrop(true)
                .setImageScaleType(ImageView.ScaleType.CENTER_CROP)
                .setLoadingDrawableId(R.mipmap.logo)
                .setFailureDrawableId(R.mipmap.logo)
                .build();
    }

    public void addSrc(List<PagerTwoInformationPageBean.InBean> pagerTwoListViewBeen) {
        this.pagerTwoListViewBeen.addAll(pagerTwoListViewBeen);
    }

    public void addSrc(PagerTwoInformationPageBean.InBean imgUrl) {
        this.pagerTwoListViewBeen.add(imgUrl);
    }
    @Override
    public int getCount() {
        return pagerTwoListViewBeen.size();
    }

    @Override
    public Object getItem(int i) {
        return pagerTwoListViewBeen.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View contentView, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if (contentView == null) {
            viewHolder = new ViewHolder();
            contentView = inflater.inflate(R.layout.pager_two_information_page_listview_item, null);
            //在这里请求数据
           // getdianzan(position);
            viewHolder.image_view = (ImageView) contentView.findViewById(R.id.image_view);
            viewHolder.title = (TextView) contentView.findViewById(R.id.title);
            viewHolder.time = (TextView) contentView.findViewById(R.id.time);
            viewHolder.from = (TextView) contentView.findViewById(R.id.from);
            viewHolder.count = (TextView) contentView.findViewById(R.id.count);
            viewHolder.dianzan= (TextView) contentView.findViewById(R.id.tv_dianzannumber);
            viewHolder.iv_dianzan= (ImageView) contentView.findViewById(R.id.iv_dianzan);
            contentView.setTag(viewHolder);
        } else {
            viewHolder = ((ViewHolder) contentView.getTag());
        }
        x.image().bind(viewHolder.image_view, pagerTwoListViewBeen.get(position).getNewspri(), imageOptions);
        viewHolder.title.setText(pagerTwoListViewBeen.get(position).getNewsname());
        viewHolder.time.setText(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Double.parseDouble(pagerTwoListViewBeen.get(position).getNewstime())));
        viewHolder.from.setText(pagerTwoListViewBeen.get(position).getNewsfrom());
        viewHolder.count.setText(pagerTwoListViewBeen.get(position).getNewsnum());
        viewHolder.dianzan.setText(pagerTwoListViewBeen.get(position).getThumupCount());
        return contentView;
    }
    public void getdianzan(int position){
        SharedPreferences sp=context.getSharedPreferences("sumao", Activity.MODE_PRIVATE);
        unique=sp.getString("unique","");
        if (!unique.equals("false") && !unique.equals("") && unique!=null) {
            String url = SuMaoConstant.SUMAO_IP + "/rest/model/atg/commerce/news/newsAnalyzeActor/GiveThumbUp";
            RequestParams rp = new RequestParams(url);
            rp.addParameter("userId", unique);
            rp.addParameter("adId", pagerTwoListViewBeen.get(position).getNewsid());
           //rp.addParameter("type","");
            Log.d("点赞的rp",rp+"");
            x.http().post(rp, new Callback.CommonCallback<String>() {
                @Override
                public void onSuccess(String result) {
                    Log.d("点赞功能的返回值", "" + result);
                }

                @Override
                public void onError(Throwable ex, boolean isOnCallback) {
                    Log.d("点赞功能的返回值", "onError" );
                }

                @Override
                public void onCancelled(CancelledException cex) {
                    Log.d("点赞功能的返回值", "onCancelled" );
                }

                @Override
                public void onFinished() {
                    Log.d("点赞功能的返回值", "onFinished " );
                }
            });
        }
    }

    class ViewHolder {
        ImageView image_view;
        TextView title;
        TextView time;
        TextView from;
        TextView count;
        TextView dianzan;
        ImageView iv_dianzan;
    }
}

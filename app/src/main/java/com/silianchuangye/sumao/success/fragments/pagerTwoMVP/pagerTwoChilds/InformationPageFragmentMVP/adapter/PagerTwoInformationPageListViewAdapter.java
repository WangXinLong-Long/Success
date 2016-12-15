package com.silianchuangye.sumao.success.fragments.pagerTwoMVP.pagerTwoChilds.InformationPageFragmentMVP.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.fragments.pagerTwoMVP.pagerTwoChilds.InformationPageFragmentMVP.bean.PagerTwoListViewBean;

import org.xutils.common.util.DensityUtil;
import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/12/14.
 */

public class PagerTwoInformationPageListViewAdapter extends BaseAdapter {
    List<PagerTwoListViewBean> pagerTwoListViewBeen;
    Context context;
    LayoutInflater inflater;
    ImageOptions imageOptions;

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
                .setLoadingDrawableId(R.mipmap.ic_launcher)
                .setFailureDrawableId(R.mipmap.ic_launcher)
                .build();
    }

    public void addSrc(List<PagerTwoListViewBean> pagerTwoListViewBeen) {
        this.pagerTwoListViewBeen.addAll(pagerTwoListViewBeen);
    }

    public void addSrc(PagerTwoListViewBean imgUrl) {
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
            viewHolder.image_view = (ImageView) contentView.findViewById(R.id.image_view);
            viewHolder.title = (TextView) contentView.findViewById(R.id.title);
            viewHolder.time = (TextView) contentView.findViewById(R.id.time);
            viewHolder.from = (TextView) contentView.findViewById(R.id.from);
            viewHolder.count = (TextView) contentView.findViewById(R.id.count);
            contentView.setTag(viewHolder);
        } else {
            viewHolder = ((ViewHolder) contentView.getTag());
        }
        x.image().bind(viewHolder.image_view, pagerTwoListViewBeen.get(position).getIcon(), imageOptions);
        viewHolder.title.setText(pagerTwoListViewBeen.get(position).getTitle());
        viewHolder.time.setText(pagerTwoListViewBeen.get(position).getTime());
        viewHolder.from.setText(pagerTwoListViewBeen.get(position).getFrom());
        viewHolder.count.setText(pagerTwoListViewBeen.get(position).getCount());
        return contentView;
    }

    class ViewHolder {
        ImageView image_view;
        TextView title;
        TextView time;
        TextView from;
        TextView count;
    }
}

package com.silianchuangye.sumao.success.fragments.myPlasticTrade.OrderManagement.SpotOrder.SpotOrderMVP.view.informationView;

import android.content.Context;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.silianchuangye.sumao.success.R;

import org.xutils.common.util.DensityUtil;
import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.util.List;
import java.util.Map;

/**
 * Created by junjun on 2017/3/29.
 */
public class Image extends BaseAdapter {
    // 要显示的数据的集合
    private List<String> data;
    // 接受上下文
    private Context context;
    // 声明内部类对象
    private ViewHolder viewHolder;

    private ImageOptions imageOptions;

    public Image(List<String> list,Context context){
        this.data=list;
        this.context=context;
    }
    @Override
    public int getCount() {
        Log.d("list的长度",data.size()+"");
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = View.inflate(context, R.layout.item_image, null);
            viewHolder.iv_image = (ImageView) convertView
                    .findViewById(R.id.iv_Image);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        // 获取List集合中的map对象
        //Map<String, Object> map = data.get(position);
        // 获取图片的url路径
        String url = data.get(position).toString();
        Log.d("url",url);
        // 这里调用了图片加载工具类的setImage方法将图片直接显示到控件上
//        GetImageByUrl getImageByUrl = new GetImageByUrl();
//        getImageByUrl.setImage(viewHolder.iv_image, url);
       // viewHolder.tv_url.setText(url);
        imageOptions = new ImageOptions
                .Builder()
                .setRadius(10)
                .setSize(DensityUtil.dip2px(120), DensityUtil.dip2px(120))
                .setRadius(DensityUtil.dip2px(5))
                // 如果ImageView的大小不是定义为wrap_content, 不要crop.
                 .setCrop(true) // 很多时候设置了合适的scaleType也不需要它.
                // 加载中或错误图片的ScaleType
                .setPlaceholderScaleType(ImageView.ScaleType.MATRIX)
                .setImageScaleType(ImageView.ScaleType.FIT_CENTER)
//                    .setLoadingDrawableId(R.mipmap.ic_launcher)
//                    .setFailureDrawableId(R.mipmap.ic_launcher)
                .build();
        x.image().bind(viewHolder.iv_image, url, imageOptions);

        return convertView;
    }

    class ViewHolder {
        public ImageView iv_image;
       // public TextView tv_url;
    }
}

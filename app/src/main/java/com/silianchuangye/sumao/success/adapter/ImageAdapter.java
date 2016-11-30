package com.silianchuangye.sumao.success.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;

import java.util.List;

/**
 * Created by junjun on 2016/4/27.
 */
public class ImageAdapter extends BaseAdapter {
    private Context _context;
    private List<Bitmap> list1;
    public ImageAdapter(Context context, List<Bitmap> list) {
        _context = context;
        list1=list;
    }

    public int getCount() {
        return Integer.MAX_VALUE;
    }

    public Object getItem(int position) {

        return position;
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if(convertView == null)
        {
            viewHolder = new ViewHolder();
            ImageView imageView = new ImageView(_context);
            imageView.setAdjustViewBounds(true);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setLayoutParams(new Gallery.LayoutParams(
                    Gallery.LayoutParams.MATCH_PARENT, Gallery.LayoutParams.MATCH_PARENT));
            convertView = imageView;
            viewHolder.imageView = (ImageView)convertView;
            convertView.setTag(viewHolder);

        }
        else
        {
            viewHolder = (ViewHolder)convertView.getTag();
        }
        viewHolder.imageView.setImageBitmap(list1.get(position%list1.size()));

        return convertView;
    }
    private static class ViewHolder
    {
        ImageView imageView;
    }

}

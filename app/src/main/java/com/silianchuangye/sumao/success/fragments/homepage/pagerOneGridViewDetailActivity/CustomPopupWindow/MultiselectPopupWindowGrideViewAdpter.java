package com.silianchuangye.sumao.success.fragments.homepage.pagerOneGridViewDetailActivity.CustomPopupWindow;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.silianchuangye.sumao.success.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class MultiselectPopupWindowGrideViewAdpter extends BaseAdapter {
    private List<String> image;
    private Boolean isChice[];
    private Context context;


    public MultiselectPopupWindowGrideViewAdpter(Context context) {
        this.image = new ArrayList<>();

        this.context = context;
    }

    public void setData(List<String> data) {
        image.addAll(data);
        isChice = new Boolean[image.size()];
        for (int i = 0; i < image.size(); i++) {
            isChice[i] = false;

        }
    }
    public void clearData(){
        image.clear();
        isChice = null;

    }

    @Override
    public int getCount() {
        return image.size();
    }


    @Override
    public Object getItem(int arg0) {
        return image.get(arg0);
    }


    @Override
    public long getItemId(int arg0) {
        return arg0;
    }


    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public View getView(int arg0, View arg1, ViewGroup arg2) {
        View view = arg1;
        ViewHolder getView = null;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.jing_dong_item, null);
            getView = new ViewHolder();
            getView.textView = (TextView) view.findViewById(R.id.image_item);
            view.setTag(getView);
        } else {
            getView = (ViewHolder) view.getTag();
        }
        getView.textView.setText(image.get(arg0));
        if (isChice[arg0]) {
            getView.textView.setBackground(context.getResources().getDrawable(R.drawable.button_back_blue));
            getView.textView.setTextColor(context.getResources().getColor(R.color.zixun_topbg));
        } else {
            getView.textView.setBackground(context.getResources().getDrawable(R.drawable.button_back_gray));
            getView.textView.setTextColor(context.getResources().getColor(R.color.my_sumao_text_color_black));
        }
        return view;
    }

    //    重置
    public void clear() {
        for (int i = 0; i < isChice.length; i++) {
            isChice[i] = false;
        }
    }


    static class ViewHolder {
        TextView textView;
    }

    public void chiceState(int post) {
        isChice[post] = !isChice[post];
        this.notifyDataSetChanged();
    }


    public List<Boolean> getCheseState() {
        return Arrays.asList(isChice);
    }
}
package com.silianchuangye.sumao.success.fragments;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SearchView;
import android.widget.TextView;

import com.silianchuangye.sumao.success.R;

/**
 * Created by Administrator on 2016/4/20 0020.
 */
public abstract class BasePager extends Fragment implements View.OnClickListener {
    //    获取宿主Activity
    protected Activity mActivity;
//    每个孩子的具体内容的容器
    protected FrameLayout fl_content;
//    BasePager的视图
    private View view;
//    标题栏最左边的logo
    protected ImageView logo;
//    标题栏搜索框
    protected Button searchView;
//    标题栏标题
    protected TextView title;
//    标题栏右边图标
    protected ImageView service;
    //    整个标题栏
    protected RelativeLayout rl_title;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (view==null)
        {
            view = View.inflate(mActivity, R.layout.base_pager,null);
            rl_title = (RelativeLayout) view.findViewById(R.id.fl_title);

            logo = ((ImageView) view.findViewById(R.id.iv_title_bar_logo));
            searchView = ((Button) view.findViewById(R.id.sv_title_bar_serachView));
            title = ((TextView) view.findViewById(R.id.tv_title_bar_title));
            service = ((ImageView) view.findViewById(R.id.iv_title_bar_service));
            fl_content = ((FrameLayout) view.findViewById(R.id.fl_basepager_content));
//            logo.setOnClickListener(this);
//            searchView.setIconifiedByDefault(false);
            initDate();
        }
        ViewGroup parent = ((ViewGroup) view.getParent());
        if (parent!=null)
        {
            parent.removeView(view);
        }
        logo.setOnClickListener(this);
        title.setOnClickListener(this);
        service.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.iv_title_bar_logo:
                myClickLeft();
                break;
            case R.id.iv_title_bar_service:
                myClickRight();
                break;
            case R.id.sv_title_bar_serachView:
                myClickSearch();
                break;
            default:
                break;

        }
    }

    public abstract void myClickSearch();
    public abstract void initDate();
    public abstract void myClickLeft();
    public abstract void myClickRight();

}

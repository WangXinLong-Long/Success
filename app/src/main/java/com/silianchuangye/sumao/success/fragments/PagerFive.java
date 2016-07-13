package com.silianchuangye.sumao.success.fragments;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.fragments.SellerManagementPlatform.SellerManagementPlatformActivity;

/**
 * Created by Administrator on 2016/7/13 0013.
 */
public class PagerFive extends Fragment implements View.OnClickListener{

    Activity mActivity;
    View view;
    private Button login_seller;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view==null)
        {
            view = View.inflate(mActivity, R.layout.pager_five,null);
            login_seller = ((Button) view.findViewById(R.id.login_seller));
            login_seller.setOnClickListener(this);
        }
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {

            case R.id.login_seller:
                Intent intent = new Intent();
                intent.setClass(mActivity,SellerManagementPlatformActivity.class);
                startActivity(intent);
                break;
        }
    }
}

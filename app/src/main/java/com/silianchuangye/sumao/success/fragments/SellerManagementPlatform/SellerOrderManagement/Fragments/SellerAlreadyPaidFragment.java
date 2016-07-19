package com.silianchuangye.sumao.success.fragments.SellerManagementPlatform.SellerOrderManagement.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.silianchuangye.sumao.success.R;

/**
 * Created by Administrator on 2016/7/14 0014.
 */
public class SellerAlreadyPaidFragment extends Fragment{

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.seller_already_paid_fragment,container,false);
        return view;
    }
}

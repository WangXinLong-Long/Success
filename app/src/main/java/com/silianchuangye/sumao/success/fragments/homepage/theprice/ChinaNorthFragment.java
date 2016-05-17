package com.silianchuangye.sumao.success.fragments.homepage.theprice;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.silianchuangye.sumao.success.R;

/**
 * A simple {@link Fragment} subclass.
 */
//华北界面
public class ChinaNorthFragment extends Fragment {
    private View chinanorth;
    private TextView tv_chinanorth;
    private ImageView img_chiannorth;
    private ListView lv_chinanorth;
    public ChinaNorthFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragmentsfdjkh
        chinanorth =inflater.inflate(R.layout.fragment_china_north, container, false);
        initView();
        return chinanorth;
    }

    private void initView() {
        tv_chinanorth= (TextView) chinanorth.findViewById(R.id.tv_chinanorth);
        img_chiannorth= (ImageView) chinanorth.findViewById(R.id.img_chinanorth);
        lv_chinanorth= (ListView) chinanorth.findViewById(R.id.lv_chainanorth);

    }

}

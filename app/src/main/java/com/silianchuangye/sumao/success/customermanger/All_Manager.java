package com.silianchuangye.sumao.success.customermanger;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.adapter.AllManagerAdapter;
import com.silianchuangye.sumao.success.fragments.bean.AllCustomInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class All_Manager extends Fragment {
    private ListView lv;
    private View v;
    private List<AllCustomInfo>list;
    private AllManagerAdapter adapter;
    public All_Manager() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v=inflater.inflate(R.layout.fragment_blank, container, false);
        initDate();
        initView();
        return v;
    }

    private void initDate() {
        list=new ArrayList<AllCustomInfo>();
        AllCustomInfo info=new AllCustomInfo();
        info.address="河北省邢台市沙河市";
        info.buy="有";
        info.name="四联创业化工集团";
        info.person="张三";
        info.title="北京四联京津冀";
        info.num="80";
        info.type="生产厂";
        info.zhuangtai="有效";

        AllCustomInfo info1=new AllCustomInfo();
        info1.address="河北省邢台市沙河市11";
        info1.buy="有243";
        info1.name="四联创业化工集团213";
        info1.person="张三234";
        info1.title="北京四联京津冀234";
        info1.num="80234";
        info1.type="生产厂234";
        info1.zhuangtai="有效243";

        list.add(info);
        list.add(info1);
    }

    private void initView() {
        lv= (ListView) v.findViewById(R.id.lv_all_manager);
        adapter=new AllManagerAdapter(list,getActivity());
        lv.setAdapter(adapter);
    }

}

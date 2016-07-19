package com.silianchuangye.sumao.success.customermanger;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.INotificationSideChannel;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.adapter.AllManagerAdapter;
import com.silianchuangye.sumao.success.fragments.bean.AllCustomInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class All_Manager extends Fragment implements AdapterView.OnItemClickListener,AllManagerAdapter.CallBackType {
    private ListView lv;
    private View v;
    private List<AllCustomInfo>list;
    private AllManagerAdapter adapter;
    private My my=new My();
    int i;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v=inflater.inflate(R.layout.fragment_blank, container, false);
        IntentFilter intent=new IntentFilter();
        intent.addAction("type");
        getActivity().registerReceiver(my,intent);
        initDate();
        initView();
        return v;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getActivity().unregisterReceiver(my);
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
        adapter=new AllManagerAdapter(list,getActivity(),this);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Log.e("TAG","点击了第"+i+"条数据");
    }
    private String str;
    private class My extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            str =intent.getStringExtra("type");
            list.get(i).zhuangtai=str;
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void call(int poistion) {
        i=poistion;
        list.get(poistion).flag=!list.get(poistion).flag;
        if(list.get(poistion).flag){
            Intent intent=new Intent(getActivity(),CustomerType.class);
            startActivity(intent);
        }

    }
}

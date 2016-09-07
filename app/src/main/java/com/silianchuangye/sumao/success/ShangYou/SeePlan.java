package com.silianchuangye.sumao.success.ShangYou;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.adapter.SeePlanAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class SeePlan extends Fragment {
    private ListView lv;
    private List<SeePlanInfo> list;
    private SeePlanAdapter adapter;
    public SeePlan() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_see_plan, container, false);
        lv= (ListView) v.findViewById(R.id.lv_seeplan);
        initDate();
        adapter=new SeePlanAdapter(getActivity(),list);
        lv.setAdapter(adapter);
        return  v;
    }

    private void initDate() {
        list=new ArrayList<SeePlanInfo>();
        SeePlanInfo info1=new SeePlanInfo();
        info1.bianhao="1111111";
        info1.date="2016-07-17";
        info1.name="兰州石化7042";
        info1.cnagku="迅帮一号库";
        info1.num="50";
        info1.peisong="自提";
        info1.state="待确定";
        info1.dingdannum="000000111111111";
        SeePlanInfo info2=new SeePlanInfo();
        info2.bianhao="2222222";
        info2.date="2016-07-17";
        info2.name="兰州石化7042";
        info2.cnagku="迅帮一号库";
        info2.num="50";
        info2.peisong="自提";
        info2.state="已确定";
        info2.dingdannum="0000000000000";
        SeePlanInfo info3=new SeePlanInfo();
        info3.bianhao="333333333333";
        info3.date="2016-07-17";
        info3.name="兰州石化7042";
        info3.cnagku="迅帮一号库";
        info3.num="50";
        info3.peisong="自提";
        info3.state="已取消";
        info3.dingdannum="0000000000000";
        list.add(info1);
        list.add(info2);
        list.add(info3);
    }
}

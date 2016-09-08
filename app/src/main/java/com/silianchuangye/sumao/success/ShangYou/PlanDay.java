package com.silianchuangye.sumao.success.ShangYou;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.adapter.DayPlanAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class PlanDay extends Fragment implements OnClickListener{
    private Button btn_day_plan_add,btn_day_plan_tijiao;
    private ListView lv_day_plan;
    private List<DayPlanInfo>list;
    private DayPlanAdapter adapter;
    private DayPlanAlert alert;
    public PlanDay() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=View.inflate(getActivity(),R.layout.fragment_plan_day2,null);
        lv_day_plan= (ListView) v.findViewById(R.id.lv_day_plan);
        btn_day_plan_add= (Button) v.findViewById(R.id.btn_day_plan_add);
        btn_day_plan_tijiao= (Button) v.findViewById(R.id.btn_day_plan_tijiao);
        btn_day_plan_tijiao.setOnClickListener(this);
        btn_day_plan_add.setOnClickListener(this);
        initDate();
        adapter=new DayPlanAdapter(getActivity(),list);
        lv_day_plan.setAdapter(adapter);
        alert=new DayPlanAlert(getActivity());
        return  v;
    }

    private void initDate() {
       list=new ArrayList<DayPlanInfo>();
        DayPlanInfo info1=new DayPlanInfo();
        info1.date="2016-1-23";
        info1.nam="兰州石化7024";
        info1.num="20";
        info1.cangku="迅帮一号库";
        info1.peisong="自提";
        info1.beizhu="无";
        DayPlanInfo info2=new DayPlanInfo();
        info2.date="2016-1-23";
        info2.nam="选择产品名称";
        info2.num="选择数量";
        info2.cangku="选择仓库";
        info2.peisong="选择配送方式";
        info2.beizhu="填写备注";
        list.add(info1);
        list.add(info2);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_day_plan_add:
                Toast.makeText(getActivity(),"添加",Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_day_plan_tijiao:
                Toast.makeText(getActivity(),"提交",Toast.LENGTH_SHORT).show();
                alert.show();
                break;
        }
    }
}

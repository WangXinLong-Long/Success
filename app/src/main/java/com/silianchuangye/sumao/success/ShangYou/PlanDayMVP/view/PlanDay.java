package com.silianchuangye.sumao.success.ShangYou.PlanDayMVP.view;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.Toast;

import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.ShangYou.DayPlanAlert;
import com.silianchuangye.sumao.success.ShangYou.DayPlanInfo;
import com.silianchuangye.sumao.success.adapter.DayPlanAdapter;
import com.silianchuangye.sumao.success.utils.LogUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class PlanDay extends Fragment implements OnClickListener {
    private Button btn_day_plan_add, btn_day_plan_tijiao;
    private ExpandableListView lv_day_plan;
    private List<List<DayPlanInfo>> list;
    private List<String> group;
    private DayPlanAdapter adapter;
    private DayPlanAlert alert;
    private List<DayPlanInfo> lists;

    public PlanDay() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = View.inflate(getActivity(), R.layout.fragment_plan_day2, null);
        lv_day_plan = (ExpandableListView) v.findViewById(R.id.lv_day_plan);
        btn_day_plan_add = (Button) v.findViewById(R.id.btn_day_plan_add);
        btn_day_plan_tijiao = (Button) v.findViewById(R.id.btn_day_plan_tijiao);
        btn_day_plan_tijiao.setOnClickListener(this);
        btn_day_plan_add.setOnClickListener(this);
        group = new ArrayList<>();
        list = new ArrayList<List<DayPlanInfo>>();
        initDate();
        adapter = new DayPlanAdapter(list, getActivity(),group);

        lv_day_plan.setAdapter(adapter);
        for (int i = 0; i < group.size(); i++) {
            lv_day_plan.expandGroup(i);
        }
        lv_day_plan .setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {

            @Override
            public boolean onGroupClick(ExpandableListView parent, View v,
                                        int groupPosition, long id) {
                return true;
            }
        });
        lv_day_plan.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                if (adapter.getChild(groupPosition, childPosition).getLevel().equals("计划日期")) {
                  Toast.makeText(getActivity(),"计划日期--"+groupPosition+"--childPosition"+childPosition,Toast.LENGTH_SHORT).show();
                } else if (adapter.getChild(groupPosition, childPosition).getLevel().equals("产品名称")) {
                    Toast.makeText(getActivity(),"产品名称"+groupPosition+"--childPosition"+childPosition,Toast.LENGTH_SHORT).show();
                } else if (adapter.getChild(groupPosition, childPosition).getLevel().equals("数量（吨）")) {
                    Toast.makeText(getActivity(),"数量（吨）"+groupPosition+"--childPosition"+childPosition,Toast.LENGTH_SHORT).show();
                } else if (adapter.getChild(groupPosition, childPosition).getLevel().equals("仓库")) {
                    Toast.makeText(getActivity(),"仓库"+groupPosition+"--childPosition"+childPosition,Toast.LENGTH_SHORT).show();
                } else if (adapter.getChild(groupPosition, childPosition).getLevel().equals("配送方式")) {
                    Toast.makeText(getActivity(),"配送方式"+groupPosition+"--childPosition"+childPosition,Toast.LENGTH_SHORT).show();
                } else if (adapter.getChild(groupPosition, childPosition).getLevel().equals("备注")) {
                    Toast.makeText(getActivity(),"备注"+groupPosition+"--childPosition"+childPosition,Toast.LENGTH_SHORT).show();
                } else if (adapter.getChild(groupPosition, childPosition).getLevel().equals("")) {
                    Toast.makeText(getActivity(),"删除的是："+groupPosition+"组。"+"--childPosition"+childPosition+"位置",Toast.LENGTH_SHORT).show();
                    list.remove(groupPosition);
                    group.remove(groupPosition);
                    adapter.notifyDataSetChanged();
                }

                return true;
            }
        });
        alert = new DayPlanAlert(getActivity());
        return v;
    }

    private void initDate() {
        LogUtils.log("将要进行ExpandableListView的初始化");

        for (int i = 0; i < 2; i++) {
            lists = new ArrayList<DayPlanInfo>();
            lists.add(new DayPlanInfo("计划日期","", "名称"));
            lists.add(new DayPlanInfo("产品名称","", "名称"));
            lists.add(new DayPlanInfo("数量（吨）","", "名称"));
            lists.add(new DayPlanInfo("仓库","", "名称"));
            lists.add(new DayPlanInfo("配送方式","", "名称"));
            lists.add(new DayPlanInfo("备注", "","名称"));
            lists.add(new DayPlanInfo("", "删除计划",""));
            list.add(lists);
            group.add("");

        }
        LogUtils.log("进行ExpandableListView的初始化完毕");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_day_plan_add:

                lists = new ArrayList<DayPlanInfo>();
                lists.add(new DayPlanInfo("计划日期","", "名称"));
                lists.add(new DayPlanInfo("产品名称","", "名称"));
                lists.add(new DayPlanInfo("数量（吨）","", "名称"));
                lists.add(new DayPlanInfo("仓库", "","名称"));
                lists.add(new DayPlanInfo("配送方式","", "名称"));
                lists.add(new DayPlanInfo("备注","", "名称"));
                lists.add(new DayPlanInfo("", "删除计划", ""));
                list.add(lists);
                group.add("");
                adapter.notifyDataSetChanged();

                for (int i = 0; i < group.size(); i++) {
                    lv_day_plan.expandGroup(i);
                }
                break;
            case R.id.btn_day_plan_tijiao:
                Toast.makeText(getActivity(), "提交", Toast.LENGTH_SHORT).show();
                alert.show();
                break;
        }
    }
}

package com.silianchuangye.sumao.success.fragments.homepage.theprice;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.silianchuangye.sumao.success.MyApp;
import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.adapter.ChinaNorthAdapter;
//import com.silianchuangye.sumao.success.customermanger.CustomerManager;
import com.silianchuangye.sumao.success.salesearch.SaleSearch;

import java.util.ArrayList;
import java.util.List;
/**
 * A simple {@link Fragment} subclass.
 */
//华北界面
public class ChinaNorthFragment extends Fragment implements View.OnClickListener,AdapterView.OnItemClickListener{
    private View chinanorth;
    private RelativeLayout relative_chinanorth;//点价规则，点价流程
    private TextView tv_chinanorth;
    private ImageView img_chiannorth;
    private ListView lv_chinanorth;
    private List<ChinaNorthInfo> list;
    private ChinaNorthAdapter adapter;
    private boolean flag=true;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        chinanorth =inflater.inflate(R.layout.fragment_china_north, container, false);
        initDate();
        initView();
        return chinanorth;
    }

    private void initDate() {
        list=new ArrayList<ChinaNorthInfo>();
        for(int i=0;i<3;i++){
            ChinaNorthInfo info=new ChinaNorthInfo();
            info.title="假数据-油化工线性聚乙烯LLDPE-"+i;
            info.name="假-张晓明-"+i;
            info.telnum="假电话-"+i;
            list.add(info);
        }
    }

    private void initView() {
        tv_chinanorth= (TextView) chinanorth.findViewById(R.id.tv_chinanorth);
        img_chiannorth= (ImageView) chinanorth.findViewById(R.id.img_chinanorth);
        lv_chinanorth= (ListView) chinanorth.findViewById(R.id.lv_chainanorth);
        relative_chinanorth= (RelativeLayout) chinanorth.findViewById(R.id.relative_chinanorth);
        adapter=new ChinaNorthAdapter(list,getActivity());
        lv_chinanorth.setAdapter(adapter);

        relative_chinanorth.setOnClickListener(this);
        lv_chinanorth.setOnItemClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.relative_chinanorth:
                //跳转到点价界面
                Intent intent=new Intent(getActivity(),ChinaNorth_Price.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent=new Intent(getActivity(),ChinaNorth_MyPrice.class);
        intent.putExtra("name",list.get(position).title);
        startActivity(intent);
    }

}

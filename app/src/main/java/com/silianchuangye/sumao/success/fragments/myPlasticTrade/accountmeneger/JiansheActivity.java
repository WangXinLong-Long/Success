package com.silianchuangye.sumao.success.fragments.myPlasticTrade.accountmeneger;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.silianchuangye.sumao.success.R;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

public class JiansheActivity extends AppCompatActivity implements View.OnClickListener {
    private ListView lv_Jianshe;
    private ImageView iv_child_title_bar_back;
    private TextView  tv_child_title_bar_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jianshe);
        lv_Jianshe= (ListView) findViewById(R.id.lv_Jianshe);
        iv_child_title_bar_back= (ImageView) findViewById(R.id.iv_child_title_bar_back);
        tv_child_title_bar_title= (TextView) findViewById(R.id.tv_child_title_bar_title);
        tv_child_title_bar_title.setText("E商贸通");
        Bundle bundle=getIntent().getExtras();
        int position=bundle.getInt("position");
        List<Map<String,Object>> list=new ArrayList<>();
        Map<String ,Object> map=new Hashtable<>();
        map.put("icon"," ");
        map.put("name","建设银行");
        list.add(map);
        Map<String,Object> map1=new Hashtable<>();
        map1.put("icon","");
        map1.put("name","其他银行");
        list.add(map1);
        SimpleAdapter adapter=new SimpleAdapter(JiansheActivity.this,list,R.layout.layout_item_banklist,new String[]{"icon","name"},new int[]{R.id.iv_Bank_icon,R.id.tv_Bank_name});
        lv_Jianshe.setAdapter(adapter);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_child_title_bar_back:
                JiansheActivity.this.finish();
                break;
        }
    }
}

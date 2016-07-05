package com.silianchuangye.sumao.success.fragments.type;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.easemob.easeui.domain.EaseEmojicon;
import com.silianchuangye.sumao.success.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class TypeInfoActivity extends AppCompatActivity {
    private ListView lv_Type;
    private List<Map<String,Object>> list;
    private SimpleAdapter adapter;
    private ImageView iv_Back;
    private TextView tv_Search;
    private EditText Search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_type_info);
       // closeInputMethod();
        iv_Back= (ImageView) findViewById(R.id.iv_Back_Type);
        iv_Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TypeInfoActivity.this.finish();
            }
        });
        tv_Search= (TextView) findViewById(R.id.tv_Info_Type);
        tv_Search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //进行搜索功能
            }
        });

        Search= (EditText) findViewById(R.id.et_Search_Type);
        Search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // openInputMethod(Search);
            }
        });
        init_listView();
    }

    /**
     * 关闭软键盘
     */
//    public void closeInputMethod(){
//        try {
//            ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE))
//                    .hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
//                            InputMethodManager.HIDE_NOT_ALWAYS);
//        } catch (Exception e) { }finally{ }
//    }

    /**
     * 打开软键盘
     * @param
     */
//    public void openInputMethod(final EditText editText){
//        Timer timer = new Timer();
//        timer.schedule(new TimerTask() {
//            public void run() {
//                InputMethodManager inputManager = (InputMethodManager) editText
//                        .getContext().getSystemService(
//                                Context.INPUT_METHOD_SERVICE);
//                inputManager.showSoftInput(editText, 0);
//            }
//        }, 200);
//    }
    public void init_listView(){
        lv_Type= (ListView) findViewById(R.id.lv_Type);
        list = new ArrayList<Map<String, Object>>();
        for (int i=0;i<=10;i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("name","燕山石化7042");
            map.put("number","1234.6吨");
            map.put("price","10000");
            map.put("address","北京市房山区东流水路四联创业集团");
            map.put("cangku","讯邦仓库1号");
            map.put("state","现货产品");
            list.add(map);

        }
        adapter=new SimpleAdapter(this,list,R.layout.item_type_two,
                new String[]
                        {"name","number","price","address","cangku","state"},
                new int[]
                        {R.id.tv_name_type_two,R.id.tv_number_type_two,
                         R.id.tv_price_type_two,R.id.tv_address_type_two,
                         R.id.tv_cangku_type_two,R.id.tv_state_type_two
                        });
        lv_Type.setAdapter(adapter);

    }
}

package com.silianchuangye.sumao.success.fragments.myPlasticTrade.OrderManagement.SpotOrder.SpotOrderMVP.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.OrderManagement.SpotOrder.SpotOrderMVP.view.informationView.Image;
import com.silianchuangye.sumao.success.utils.SuMaoConstant;

import org.xutils.common.util.DensityUtil;
import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ShowPictureActivity extends AppCompatActivity {
    private ListView lvdemo;
    private TextView tv_child_title_bar_title;
    private ImageView iv_child_title_bar_back;
    private String path;
    private String title;
    private List<String> listImage;
    private List<String> listString;
    private String url;
    private ImageOptions imageOptions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_picture);
        Bundle bundle=getIntent().getExtras();
        title=bundle.getString("title");
        path=bundle.getString("path");
        init();
        listString=new ArrayList<>();
        listImage=new ArrayList<>();

        if (!path.contains(",")){
            listString.add(path);
        }else{
            String [] str=path.split(",");
            for (String stringpath:str){
                listString.add(stringpath);
            }
            Log.d("输出list的值",listString.toString()+listString.size());
        }
        for (int i=0;i<listString.size();i++){
            listImage.add(SuMaoConstant.SUMAO_CONTRACT_IP+"/mnt/docs/upload/order/"+listString.get(i));

        }
        Log.d("image的之",listImage.size()+"");
        Image adapterImage=new Image(listImage,ShowPictureActivity.this);
        lvdemo.setAdapter(adapterImage);
        adapterImage.notifyDataSetChanged();
    }
    public void init(){
        lvdemo= (ListView) findViewById(R.id.lvdemo);
        tv_child_title_bar_title= (TextView) findViewById(R.id.tv_child_title_bar_title);
        tv_child_title_bar_title.setText(title);
        iv_child_title_bar_back= (ImageView) findViewById(R.id.iv_child_title_bar_back);
        iv_child_title_bar_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowPictureActivity.this.finish();
            }
        });


    }

}

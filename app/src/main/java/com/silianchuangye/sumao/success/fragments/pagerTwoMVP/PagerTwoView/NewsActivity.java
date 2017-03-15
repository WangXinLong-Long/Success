package com.silianchuangye.sumao.success.fragments.pagerTwoMVP.PagerTwoView;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.silianchuangye.sumao.success.R;

public class NewsActivity extends AppCompatActivity {
    private TextView tv_Title;
    private TextView tv_Time;
    private TextView tv_Content;
    private TextView tv_child_title_bar_title;
    private String id;
    private ImageView iv_child_title_bar_back;
    private String num=null;
    private String from=null;
    private TextView tv_Num,tv_From;
    //private TextView tv_ab,tv_ac;
    private LinearLayout layout_All;
    private RelativeLayout layout_center;
    private TextView tv_time1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        tv_Num= (TextView) findViewById(R.id.tv_num);
        tv_From= (TextView) findViewById(R.id.tv_from);
        layout_All= (LinearLayout) findViewById(R.id.layout_All);
        layout_center= (RelativeLayout) findViewById(R.id.layout_center);
        tv_time1= (TextView) findViewById(R.id.tv_Time2);
        iv_child_title_bar_back= (ImageView) findViewById(R.id.iv_child_title_bar_back);
        tv_child_title_bar_title= (TextView) findViewById(R.id.tv_child_title_bar_title);
        tv_Time= (TextView) findViewById(R.id.tv_Time);
        tv_Title= (TextView) findViewById(R.id.tv_Title);
        tv_Content= (TextView) findViewById(R.id.tv_Content);
        Bundle bundle=getIntent().getExtras();
        tv_child_title_bar_title.setText("资讯");
        tv_Title.setText(bundle.getString("title"));

        tv_Content.setText(bundle.getString("title"));
        id=bundle.getString("id");
        num=bundle.getString("number");
        from=bundle.getString("from");
        Log.d("传过来的值",num+from+"");
        if (num.equals("abc") && from.equals("abc")){
            Log.d("if条件","if条件");
            layout_All.setVisibility(View.GONE);
            layout_center.setVisibility(View.VISIBLE);
            tv_time1.setText(bundle.getString("time"));

        }else{
            Log.d("判断进了else","判断进了else");
            layout_center.setVisibility(View.GONE);
            layout_All.setVisibility(View.VISIBLE);
            tv_Time.setText(bundle.getString("time"));
            tv_Num.setText(num);
            tv_From.setText(from);

        }

        iv_child_title_bar_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NewsActivity.this.finish();
            }
        });
    }
}

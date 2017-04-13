package com.silianchuangye.sumao.success.fragments.pagerTwoMVP.PagerTwoView;

import android.app.Activity;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.utils.SuMaoConstant;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

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
    private ImageView iv_dianzan;
    private boolean dianzan;
    private SharedPreferences sp;
    private String unique;
    private String count;
    private String msg;
    private TextView tv_dianzan;
    private String dianzanString;
    private List<String> list_dianzan;
    private SQLiteDatabase db;
    private String username;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        if(!getDatabasePath("sumao.db").exists()){
            getDatabasePath("sumao.db").getParentFile().mkdirs();
        }
        db = SQLiteDatabase.openOrCreateDatabase(getDatabasePath("demo.db"), null);
        //db.execSQL("");
        db.execSQL("create table if not exists newsinfo (name text,username text)");

        tv_dianzan= (TextView) findViewById(R.id.tv_dianzan);
        tv_Num= (TextView) findViewById(R.id.tv_num);
        tv_From= (TextView) findViewById(R.id.tv_from);
        layout_All= (LinearLayout) findViewById(R.id.layout_All);
        layout_center= (RelativeLayout) findViewById(R.id.layout_center);
        tv_time1= (TextView) findViewById(R.id.tv_Time2);
        iv_dianzan= (ImageView) findViewById(R.id.iv_dianzan);
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
        dianzanString=bundle.getString("dianzan");
        list_dianzan=new ArrayList<>();
        Log.d("传过来的值",num+from+"");
        if (num.equals("abc") && from.equals("abc") && dianzanString.equals("abc")){
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
            tv_dianzan.setText(dianzanString);
            //资讯首页
            iv_dianzan.setVisibility(View.VISIBLE);
            sp=getSharedPreferences("sumao",Activity.MODE_PRIVATE);
            SharedPreferences.Editor editor=sp.edit();
            editor.putString("wenzhangId",id);
            editor.commit();

        }
        iv_dianzan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if (list_dianzan.toString().contains(id)){
//                    getDianzan(false);
//                }else{
//                    //Log.d("")
//                    Toast.makeText(NewsActivity.this, "你已经点赞", Toast.LENGTH_SHORT).show();
//                }
                sp=getSharedPreferences("sumao",Activity.MODE_PRIVATE);
                dianzan=sp.getBoolean("dianzan",false);
                Log.d("点赞的值",dianzan+"");
                if (dianzan){
                    sp=getSharedPreferences("sumao",Activity.MODE_PRIVATE);
                    username=sp.getString("name","");
                    Log.d("username",username);
                    Cursor cursor=db.rawQuery("select * from newsinfo where username=?",new String[]{username});
                    while(cursor.moveToNext()){
                        Log.d("获取内容：", ""+cursor.getString(0) );
                        list_dianzan.add(cursor.getString(0));
                    }
                }
                if (list_dianzan.toString().contains(id)){
                    //已经点赞过
                    Toast.makeText(NewsActivity.this, "你已经点赞", Toast.LENGTH_SHORT).show();
                }else{
                    //还没有点赞
                    getDianzan(false);
                }


            }
        });

        iv_child_title_bar_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NewsActivity.this.finish();
            }
        });
    }
    public void getDianzan(final boolean dianzan){
        String url= SuMaoConstant.SUMAO_IP+"/rest/model/atg/commerce/news/NewsAnalyzeActor/GiveThumbUp";
        RequestParams rp=new RequestParams(url);
        sp=getSharedPreferences("sumao",Activity.MODE_PRIVATE);
        unique=sp.getString("unique","");
        if (unique.equals("false")||unique.equals("")||unique==null){
            //没有登陆过
            Toast.makeText(NewsActivity.this, "该用户没有登录,无法进行该操作", Toast.LENGTH_SHORT).show();
        }else{
            rp.addParameter("userId",unique);
            rp.addParameter("adId",id);
            if (!dianzan){
                Log.d("点赞的之",dianzan+"");
                rp.addParameter("type","add");
                Log.d("没有点赞","add");
            }else{
                //点赞过
                Toast.makeText(NewsActivity.this,"你已经点赞过",Toast.LENGTH_SHORT).show();
            }
            Log.d("点赞的返回值",rp+"");
            x.http().post(rp, new Callback.CommonCallback<String>() {

                @Override
                public void onSuccess(String result) {
                    Log.d("点赞成功的返回值",result);
                   // {"resultJson":{"count":11,"msg":"haveThumbUp"}}
                    try {
                        JSONObject jsonObject=new JSONObject(result);
                        String info=jsonObject.getString("resultJson");
                        JSONObject object=new JSONObject(info);
                        count=object.getString("count");
                        msg=object.getString("msg");
                        if (msg.equals("haveThumbUp")){
                            sp=getSharedPreferences("sumao",Activity.MODE_PRIVATE);
                            SharedPreferences.Editor editor=sp.edit();
                            editor.putBoolean("dianzan",true);
                            editor.commit();
                            username=sp.getString("name","");

                            tv_dianzan.setText((Integer.parseInt(count)+1)+"");
                            Log.d("username的值",username);
                            if (!username.equals("")||username!=null){
                                Log.d("进来这个方法了","添加数据");
                                db.execSQL("insert into newsinfo (name,username) values (?,?) ", new Object[]{id,username});
                            }
                            //tv_dianzan.setText(count+"");
                           // list_dianzan.add(id);
                            Cursor cursor=db.rawQuery("select * from newsinfo where username=?",new String[]{username});
                            //Cursor cursor1=db.rawQuery("select * from news",""+username);
                            while(cursor.moveToNext()){
                                Log.d("获取内容：", ""+cursor.getString(0) );
                                list_dianzan.add(cursor.getString(0));
                            }




                        }else {
                           if( msg.equals("needThumbUp")){

                                Toast.makeText(NewsActivity.this,"你已经点赞过",Toast.LENGTH_LONG).show();

                            }
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }

                @Override
                public void onError(Throwable ex, boolean isOnCallback) {
                    Log.d("点赞失败的返回值",ex.getMessage());

                }

                @Override
                public void onCancelled(CancelledException cex) {


                }

                @Override
                public void onFinished() {
                    Log.d("点赞结束的返回值","finish");

                }
            });
        }

    }
}

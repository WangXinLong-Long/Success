package com.silianchuangye.sumao.success.fragments.SearchActivityMVP.view;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.fragments.SearchActivityMVP.bean.SearchActivityBean;
import com.silianchuangye.sumao.success.fragments.SearchActivityMVP.presenter.SearchActivityPresenter;
import com.silianchuangye.sumao.success.fragments.type.view.TypeInfoActivity;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity implements ISearchActivityView  {
    private GridView gv_More_Search;
    private ArrayAdapter<String> adapter;
    private List<String> list;
    private ImageView back;
    private TextView search;
    private SearchActivityPresenter searchActivityPresenter;
    private EditText search_content;
    private SQLiteDatabase db;
    private String infoSearch;
    private TextView tv_clear;//清除搜索历史
    private boolean flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        if(!getDatabasePath("sumao.db").exists()){
            getDatabasePath("sumao.db").getParentFile().mkdirs();
        }
        db = SQLiteDatabase.openOrCreateDatabase(getDatabasePath("demo.db"), null);
        db.execSQL("create table if not exists search (name text)");
       // db.execSQL("delete from search");
        list=new ArrayList<String>();
        searchActivityPresenter = new SearchActivityPresenter(SearchActivity.this);
        tv_clear= (TextView) findViewById(R.id.tv_clear);
        tv_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //清楚搜索历史
                db.execSQL("delete from search");
                list.clear();
                adapter.notifyDataSetChanged();
            }
        });
        back= (ImageView) findViewById(R.id.iv_Back_Search);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SearchActivity.this.finish();
            }
        });
        search= (TextView) findViewById(R.id.tv_Info_Search);
        search_content = ((EditText) findViewById(R.id.search_content));

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(search_content.getText().toString().trim().equals("")||search_content.getText().toString().trim().isEmpty())
                {
                    Toast.makeText(SearchActivity.this,"请输入内容",Toast.LENGTH_SHORT).show();
                }else {
                    //list.add(search_content.getText().toString());
                    infoSearch=search_content.getText().toString();
                    if (infoSearch!=null) {
                        db.execSQL("insert into search(name) values(?)", new Object[]{infoSearch});
                        flag = true;
                    }
                    SharedPreferences sp=getSharedPreferences("sumao", Activity.MODE_PRIVATE);
                    SharedPreferences.Editor editor=sp.edit();
                    editor.putBoolean("search",true);
                    editor.commit();
                    searchActivityPresenter.sendSearchActivityData(search_content.getText().toString().trim());
                }
            }


        });
        //添加到数据库
//        ContentValues cv = new ContentValues();
//        cv.put("name",infoSearch);
//        //cv.put("borndate","2015-12-28");
//        //参数是ContentValues不能为null
//        db.insert("search",null,cv);

    }

    @Override
    protected void onStart() {
        super.onStart();
        init_GridView();
    }

    public void init_GridView(){
        gv_More_Search= (GridView) findViewById(R.id.gv_More_Search);
//从数据S
        SharedPreferences sp=getSharedPreferences("sumao",Activity.MODE_PRIVATE);
        flag=sp.getBoolean("search",false);


        if (flag) {
            Cursor cursor=db.query("search",null,null,null,null,null,null);
            while(cursor.moveToNext()){
                Log.d("获取内容：", ""+cursor.getString(0) );
                list.add(cursor.getString(0));

                for (int j=0;j<list.size();j++){
                    if (list.get(j).equals(null)){
                        list.remove(j);
                    }
                    for (int k=1;k<list.size()-j;k++){
                        if (list.get(j).toString().equals(list.get(k).toString())){
                            list.remove(k);
                        }
                    }
                }
                if (list.size()>=10){
                    for (int i=0;i<list.size()-10;i++){
                        list.remove(i);
                    }
                }
            }
            Log.d("list的值",list.size()+list.toString());
            if (list.size()>0) {
                adapter = new ArrayAdapter<String>(this, R.layout.item_gridview_search, R.id.tv_item_More_Info_Search, list);
                //SimpleAdapter simpleAdapter=new SimpleAdapter(this)
                gv_More_Search.setAdapter(adapter);
            }
        }
        gv_More_Search.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                search_content.setText(list.get(position).toString());
            }
        });


    }

    @Override
    public void getSearchActivityData(SearchActivityBean searchActivityBean) {
        if (searchActivityBean.getIn().size()==0){
            Toast.makeText(this,"没有找到相关商品，请重新搜索",Toast.LENGTH_SHORT).show();
        }else {
            Intent intent=new Intent(SearchActivity.this, TypeInfoActivity.class);
            intent.putExtra("searchActivityBean",searchActivityBean);
            intent.putExtra("Ntt",search_content.getText().toString().trim());
            startActivity(intent);
        }

    }
}

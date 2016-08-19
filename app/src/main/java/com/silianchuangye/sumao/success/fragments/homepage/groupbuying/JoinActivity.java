package com.silianchuangye.sumao.success.fragments.homepage.groupbuying;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.silianchuangye.sumao.success.R;

import java.util.ArrayList;
import java.util.List;

public class JoinActivity extends AppCompatActivity {
    private GridView gvDemo;
    private List<String> list;
    private ImageView ivBack,ivSearch;

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_join);
//        init();
//        event();
//        adddata();
//    }
//    public void init(){
//        gvDemo= (GridView) findViewById(R.id.gvDemo);
//        ivBack= (ImageView) findViewById(R.id.ivBack);
//
//
//    }
//    public void event(){
//        /**
//         *
//         */
//        ivBack.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                JoinActivity.this.finish();
//            }
//        });
//
//    }
//    public void adddata(){
//        list=new ArrayList<String>();
//        for (int i=0;i<=20;i++){
//
//            if (i%3==0){
//                list.add("报价编号");
//            }else if (i%3==1){
//                list.add("金牌数量(吨)");
//            }else if (i%3==2){
//                list.add("提交数量");
//
//            }
//            //list.add("aaaa");
//        }
//       // ArrayAdapter<String> adapter=new ArrayAdapter<String>(JoinActivity.this,R.layout.item_gridview_vessel_one,R.id.tv_item_vessel_one,list);
//        GridViewAdapter adapter=new GridViewAdapter(JoinActivity.this,R.layout.item_gridview_vessel_one,R.id.tv_item_vessel_one,list);
//        gvDemo.setAdapter(adapter);
//
//    }
//    class GridViewAdapter extends ArrayAdapter {
//
//
//        public GridViewAdapter(Context context, int resource, int textViewResourceId, List objects) {
//            super(context, resource, textViewResourceId, objects);
//        }
//
//        @Override
//        public View getView(int position, View convertView, ViewGroup parent) {
//            ViewHolder viewHolder;
//            if (convertView==null){
//                viewHolder=new ViewHolder();
//                convertView=getLayoutInflater().inflate(R.layout.item_gridview_vessel_one,null);
//                viewHolder.tv= (TextView) convertView.findViewById(R.id.tv_item_vessel_one);
//                viewHolder.layout= (RelativeLayout) convertView.findViewById(R.id.layout_item_vessel_one);
//               // AbsListView.LayoutParams params;
//
////                if(gvDemo.getChildCount()/3==0){
////                    params=new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,200);
////                }else{
////                    params=new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,100);
////                }
////                viewHolder.layout.setLayoutParams(params);
//                convertView.setTag(viewHolder);
//            }else{
//                viewHolder= (ViewHolder) convertView.getTag();
//            }
//            if (position%3==0 &&position!=0){
//                viewHolder.tv.setText("0007");
//            }else if(position%3==1 &&position!=1){
//                viewHolder.tv.setText("40");
//            }else if(position%3==2&&position!=2){
//                viewHolder.tv.setText("10:22:44");
//
//            }else {
//                viewHolder.tv.setText(list.get(position));
//            }
//            if (gvDemo.getChildCount()/3==0){
//                viewHolder.layout.setBackgroundColor(getResources().getColor(R.color.white));
//            }else if((gvDemo.getChildCount()/3)%2==0 &&gvDemo.getChildCount()/3!=0){
//                viewHolder.layout.setBackgroundColor(getResources().getColor(R.color.colorGreen));
//            }else{
//                viewHolder.layout.setBackgroundColor(Color.WHITE);
//            }
//            return convertView;
//
//        }
//        class ViewHolder{
//            RelativeLayout layout;
//            TextView tv;
//        }
//
//    }

}

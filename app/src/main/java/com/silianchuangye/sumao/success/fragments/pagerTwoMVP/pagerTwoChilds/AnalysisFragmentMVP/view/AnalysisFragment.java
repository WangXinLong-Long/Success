package com.silianchuangye.sumao.success.fragments.pagerTwoMVP.pagerTwoChilds.AnalysisFragmentMVP.view;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.google.gson.Gson;
import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.fragments.pagerTwoMVP.PagerTwoView.NewsActivity;
import com.silianchuangye.sumao.success.fragments.pagerTwoMVP.pagerTwoChilds.AnalysisFragmentMVP.presenter.AnalysisFragmentPresenter;
import com.silianchuangye.sumao.success.fragments.pagerTwoMVP.pagerTwoChilds.DataFragmentMVP.adapter.ListViewDataAdapter;
import com.silianchuangye.sumao.success.fragments.pagerTwoMVP.pagerTwoChilds.DataFragmentMVP.bean.PagerTwoDataGridViewBean;
import com.silianchuangye.sumao.success.fragments.pagerTwoMVP.pagerTwoChilds.DataFragmentMVP.bean.PagerTwoDataListViewBean;
import com.silianchuangye.sumao.success.fragments.pagerTwoMVP.pagerTwoChilds.DataFragmentMVP.presenter.DataFragmentPresenter;
import com.silianchuangye.sumao.success.fragments.pagerTwoMVP.pagerTwoChilds.NewsFragmentMVP.bean.NewsFragmentBean;
import com.silianchuangye.sumao.success.utils.NetWorkChangeBroadcastReceiver;
import com.silianchuangye.sumao.success.utils.SuMaoConstant;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;


/**
 * Created by Administrator on 2016/12/13.
 */

public class AnalysisFragment extends Fragment {
    private ListView listViewData;
   // private ListViewDataAdapter listViewAdapter;
    private DataFragmentPresenter dataFragmentPresenter;
    private List<PagerTwoDataListViewBean.InBean> in;
   // private List<NewsFragmentBean.InBean> in;
    private AnalysisFragmentPresenter analysisFragmentPresenter;
    private BroadcastReceiver netReceiver;
    private List<Map<String,Object>> list;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initReceiver();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.analysis_fragment, container, false);

       // analysisFragmentPresenter = new AnalysisFragmentPresenter(this);
//        列表内容
        listViewData = ((ListView) view.findViewById(R.id.list_view_data));
        listViewData.setOnItemClickListener(new MyAdapterItemClickListener());

//        listViewAdapter = new ListViewDataAdapter(getActivity());
//        listViewData.setAdapter(listViewAdapter);

//        加载数据
        //initData();
        getAnalysis("");

        return view;
    }

    public void getAnalysis(String time){
        String url= SuMaoConstant.SUMAO_IP+"/rest/model/atg/commerce/news/NewsAnalyzeActor/NewsAnalyzeDateList";
        RequestParams rp=new RequestParams(url);
        if (time!=""||time!=null){
          rp.addParameter("searchDate",time);
        }
        x.http().post(rp, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.d("result",result);
                try {
                    JSONObject jsonObject=new JSONObject(result);
                    String array=jsonObject.getString("in");
                    JSONArray jsonArray=new JSONArray(array);
                    list=new ArrayList<Map<String, Object>>();

                    for (int i=0;i<jsonArray.length();i++){
                        JSONObject object=jsonArray.getJSONObject(i);


                            Map<String,Object> map=new Hashtable<String, Object>();
                            map.put("id",object.getString("in_in_zxid"));
                            map.put("title",object.getString("in_zxbt"));
                           // map.put("time",object.getString("in_zxtime"));
                            map.put("time",new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Double.parseDouble(object.getString("in_zxtime"))));

                            list.add(map);



                    }
                    SimpleAdapter adapter=new SimpleAdapter(getActivity(),list,R.layout.data_list_view_adapter
                    ,new String[]{"title","time"},new int[]{R.id.title_text_view,R.id.time_text_view});
                    listViewData.setAdapter(adapter);
                    adapter.notifyDataSetChanged();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });



    }



    private void initData() {
        String dateType = null;
        analysisFragmentPresenter.getAnalysisFragmentResult();

    }


    //    从网络加载数据
//    @Override
//    public void setAnalysisFragmentResult(String result) {
//        Gson gson = new Gson();
//        PagerTwoDataListViewBean pagerTwoDataListViewBean = gson.fromJson(result, PagerTwoDataListViewBean.class);
//        in = pagerTwoDataListViewBean.getIn();
//        listViewAdapter.clearData();
//        listViewAdapter.addPagerTwoDataListViewBeens(in);
//        listViewAdapter.notifyDataSetChanged();
//
//    }

    class MyAdapterItemClickListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            switch (parent.getId()) {
                case R.id.list_view_data:
                     Intent intent=new Intent(getActivity(), NewsActivity.class);
                     intent.putExtra("title",list.get(position).get("title").toString());
                     intent.putExtra("id",list.get(position).get("id").toString());
                     intent.putExtra("time",list.get(position).get("time").toString());
                     intent.putExtra("number","abc");
                     intent.putExtra("from","abc");
                     startActivity(intent);
                    //Toast.makeText(getActivity(), "List--->" + in.get(position).getDataid(), Toast.LENGTH_SHORT).show();
                    break;


            }
        }
    }

    public void searchData(int year, int monthOfYear, int dayOfMonth) {
        Toast.makeText(getActivity(), "在分析页面中操作", Toast.LENGTH_SHORT).show();
    }


    //      监听广播
    private void initReceiver() {
        netReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                //                判断当前网络状态
                if(intent.getIntExtra(NetWorkChangeBroadcastReceiver.NET_TYPE,0)==0){
                    //                    0表示无网络连接,
                    //这里填你想要执行的东西
                    Toast.makeText(context, "暂时没有网络,请检查您的网络设置", Toast.LENGTH_SHORT).show();
                }else {
                    //                    1表示有
                    analysisFragmentPresenter.getAnalysisFragmentResult();
                }
            }
        };
        IntentFilter filter = new IntentFilter(NetWorkChangeBroadcastReceiver.NET_CHANGE);
        getActivity().registerReceiver(netReceiver,filter);
    }

}

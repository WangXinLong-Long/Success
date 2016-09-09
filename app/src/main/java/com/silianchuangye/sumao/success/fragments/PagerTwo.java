package com.silianchuangye.sumao.success.fragments;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.silianchuangye.sumao.success.HX.Constant;
import com.silianchuangye.sumao.success.HX.ui.LoginActivity;
import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.fragments.SearchActivityMVP.bean.SearchActivityBean;
import com.silianchuangye.sumao.success.fragments.SearchActivityMVP.presenter.SearchActivityPresenter;
import com.silianchuangye.sumao.success.fragments.SearchActivityMVP.view.ISearchActivityView;
import com.silianchuangye.sumao.success.fragments.SearchActivityMVP.view.SearchActivity;
import com.silianchuangye.sumao.success.fragments.type.view.TypeInfoActivity;
import com.silianchuangye.sumao.success.utils.SuMaoConstant;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/4/20 0020.
 */
public class PagerTwo extends BasePager implements ISearchActivityView,AdapterView.OnItemClickListener{
     private RelativeLayout view;
     private ListView lv_Show_Type;
     private List<String> list;
     private ArrayAdapter<String> Appleadapter,Numberadapter,Addressadapter,Firmadapter;
     private GridView gv_Apple_Type;
     private GridView gv_Numebr_Type;
     private GridView gv_Address_Type;
     private GridView gv_Firm_Type;
     private RelativeLayout layout_Apple;
     private RelativeLayout layout_Number;
     private RelativeLayout layout_Address;
     private RelativeLayout layout_Firm;
     private ListAdapter adapter1;
    SearchActivityPresenter searchActivityPresenter;
    List<String> list_Number;
    List<String> listId;
    String str;
    @Override
    public void myClickSearch() {
        //调到搜索页
        Log.d("点击搜索","点击搜素");
        Intent intent=new Intent(mActivity,SearchActivity.class);
        startActivity(intent);
    }
    @Override
    public void initDate() {
      view= (RelativeLayout) View.inflate(mActivity,R.layout.fragmenttwo,null);
        searchActivityPresenter = new SearchActivityPresenter(this);
      fl_content.addView(view);
        gv_Address_Type= (GridView) view.findViewById(R.id.gv_Address_Type);
        lv_Show_Type= (ListView) view.findViewById(R.id.lv_Show_Type);
        gv_Apple_Type= (GridView) view.findViewById(R.id.gv_Apple_Type);
        gv_Numebr_Type= (GridView) view.findViewById(R.id.gv_Number_Type);
        gv_Firm_Type= (GridView) view.findViewById(R.id.gv_Firm_Type);
        layout_Firm = (RelativeLayout) view.findViewById(R.id.layout_Firm_Type);
        layout_Address = (RelativeLayout) view.findViewById(R.id.layout_Address_Type);
        layout_Number = (RelativeLayout) view.findViewById(R.id.layout_Number_Type);
        layout_Apple = (RelativeLayout) view.findViewById(R.id.layout_Apple_Type);
        lv_Show_Type.setDividerHeight(2);
        lv_Show_Type.setOnItemClickListener(this);

//        apple_Type();
//        number_Type();
//        address_Type();
//        firm_Type();
        ShowLeft();
    }
    public void apple_Type(){
        gv_Apple_Type= (GridView) view.findViewById(R.id.gv_Apple_Type);
        List<String> list_Apple=new ArrayList<String>();
        list_Apple.add("拉丝料");
        Appleadapter=new ArrayAdapter<String>(getActivity(),R.layout.item_type,R.id.tv_Type,list_Apple);
        gv_Apple_Type.setAdapter(Appleadapter);
        layout_Apple= (RelativeLayout) view.findViewById(R.id.layout_Apple_Type);

        gv_Apple_Type.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Log.d("点击","gridview的item");
                    str=list_Number.get(position).toString().trim();
                    searchActivityPresenter.sendSearchActivityData(str);
                }
            });
        if (list_Apple.size()<=0){
            layout_Apple.setVisibility(View.GONE);
        }




    }
    public void number_Type(){
        gv_Numebr_Type= (GridView) view.findViewById(R.id.gv_Number_Type);
        list_Number=new ArrayList<String>();
        list_Number.add("8305");
        list_Number.add("Ncbc-8320");
        list_Number.add("L5e89");
        list_Number.add("L5e90");
        list_Number.add("s1003");
        list_Number.add("T305");
        list_Number.add("T8395");
        list_Number.add("Xd-040");
        Numberadapter=new ArrayAdapter<String>(getActivity(),R.layout.layout,R.id.tv_Type,list_Number);
        gv_Numebr_Type.setAdapter(Numberadapter);
        layout_Number= (RelativeLayout) view.findViewById(R.id.layout_Number_Type);
        if(list_Number.size()<=0){
            layout_Number.setVisibility(View.GONE);
        }
        gv_Numebr_Type.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Intent intent=new Intent(getActivity(),TypeInfoActivity.class);
//                startActivity(intent);
                str=list_Number.get(position).toString().trim();
                searchActivityPresenter.sendSearchActivityData(str);
            }
        });

    }
    public void address_Type(){
        gv_Address_Type= (GridView) view.findViewById(R.id.gv_Address_Type);
        List<String> list_Address=new ArrayList<String>();
        list_Address.add("上海市");
        list_Address.add("兰州市");
        list_Address.add("天津市");
        list_Address.add("太原市");
        list_Address.add("常州市");
        list_Address.add("成都市");
        list_Address.add("武汉市");
        list_Address.add("其他");
        list_Address.add("上海市");
        list_Address.add("兰州市");
        list_Address.add("天津市");
        list_Address.add("太原市");
        list_Address.add("常州市");
        list_Address.add("成都市");
        list_Address.add("武汉市");
        list_Address.add("其他");
        list_Address.add("上海市");
        list_Address.add("兰州市");
        list_Address.add("天津市");
        list_Address.add("太原市");
        list_Address.add("常州市");
        list_Address.add("成都市");
        list_Address.add("武汉市");
        list_Address.add("其他");
        list_Address.add("上海市");
        list_Address.add("兰州市");
        list_Address.add("天津市");
        list_Address.add("太原市");
        list_Address.add("常州市");
        list_Address.add("成都市");
        list_Address.add("武汉市");
        list_Address.add("其他");
        list_Address.add("上海市");
        list_Address.add("兰州市");
        list_Address.add("天津市");
        list_Address.add("太原市");
        list_Address.add("常州市");
        list_Address.add("成都市");
        list_Address.add("武汉市");
        list_Address.add("其他");
        Addressadapter=new ArrayAdapter<String>(getActivity(),R.layout.item_type,R.id.tv_Type,list_Address);
        gv_Address_Type.setAdapter(Addressadapter);
        layout_Address= (RelativeLayout) view.findViewById(R.id.layout_Address_Type);
        if (list_Address.size()<=0){
            layout_Address.setVisibility(View.GONE);
        }

    }
    public void firm_Type(){
        gv_Firm_Type= (GridView) view.findViewById(R.id.gv_Firm_Type);
        List<String> list_Firm=new ArrayList<String>();
        list_Firm.add("神化煤炭");
        list_Firm.add("哈尔滨石化");
        list_Firm.add("大庆石化");
        list_Firm.add("广西石化");
        list_Firm.add("抚顺石化");
        list_Firm.add("独山子石化");
        Firmadapter=new ArrayAdapter<String>(getActivity(),R.layout.layout,R.id.tv_Type,list_Firm);
        gv_Firm_Type.setAdapter(Firmadapter);
        gv_Firm_Type.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                str=list_Number.get(position).toString().trim();
                searchActivityPresenter.sendSearchActivityData(str);
            }
        });
        layout_Firm= (RelativeLayout) view.findViewById(R.id.layout_Firm_Type);
        if (list_Firm.size()<=0){
            layout_Firm.setVisibility(View.GONE);
        }

    }

    @Override
    public void myClickLeft() {

    }

    @Override
    public void myClickRight() {

        Intent intent = new Intent();
        intent.setClass(mActivity, LoginActivity.class);
        intent.putExtra(Constant.MESSAGE_TO_INTENT_EXTRA, Constant.MESSAGE_TO_DEFAULT);
        startActivity(intent);
    }

    @Override
    public void getSearchActivityData(SearchActivityBean searchActivityBean) {
        String total = searchActivityBean.getTotal();
        if (total.equals("0")) {
            Toast.makeText(getActivity(), "抱歉暂时没有搜索到您需要的产品", Toast.LENGTH_LONG).show();
        } else {
            Intent intent=new Intent(getActivity(), TypeInfoActivity.class);
            intent.putExtra("searchActivityBean",searchActivityBean);
            intent.putExtra("Ntt",str);
            startActivity(intent);
        }

    }
    String caidan;
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        adapter1.setSelectedPosition(position);
        adapter1.notifyDataSetInvalidated();
        String str=listId.get(position).toString();
        caidan=list.get(position).toString();
        Log.e("TAG","caidna-------"+caidan);
        ShowRight(caidan,str);
//        Log.e("TAG","str-----"+str);
    }

    class ListAdapter extends BaseAdapter{
        List<String> list = null;
        LayoutInflater inflater;
        View view;
        LayoutHolder Layoutholder;
        RelativeLayout Layout = null;
        TextView text = null;
        private int selectedPosition = -1;// 选中的位置


        public ListAdapter(List<String> List) {

            list = List;
        }


        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        public void setSelectedPosition(int selectedPosition) {
            this.selectedPosition = selectedPosition;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            inflater = (LayoutInflater) mActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view=inflater.inflate(R.layout.item_type_show,parent,false);
            Layoutholder= (LayoutHolder) view.getTag();
            if (Layoutholder==null){
                Layoutholder=new LayoutHolder();
                Layoutholder.layout_Type= (RelativeLayout) view.findViewById(R.id.layout_Type);
                Layoutholder.tv_item= (TextView) view.findViewById(R.id.tv_type_item);
                view.setTag(Layoutholder);

            }
            Layout = Layoutholder.layout_Type;
            text = Layoutholder.tv_item;
            if (selectedPosition == position) {
                text.setSelected(true);
                text.setPressed(true);
                Layout.setBackgroundColor(getResources().getColor(R.color.gray_type));
            } else {
                text.setSelected(false);
                text.setPressed(false);
                Layout.setBackgroundColor(Color.WHITE);

            }
            text.setTextColor(Color.BLACK);
            text.setText(list.get(position).toString());

            return view;
        }
    }
    class LayoutHolder{

        RelativeLayout layout_Type;
        TextView tv_item;
    }
    //右边二级菜单
    private void ShowRight(final String caidan,String id){
        RequestParams params=new RequestParams(SuMaoConstant.SUMAO_IP+"/rest/model/atg/commerce/catalog/ProductCatalogActor/homePanel2");
        params.addParameter("parentId",id);
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                final List<String> list_Address=new ArrayList<String>();
                list_Number=new ArrayList<String>();
                final List<String> list_Firm=new ArrayList<String>();
                final List<String> list_Apple=new ArrayList<String>();
                if(!result.equals("{}")) {
                    try {
                        JSONObject job = new JSONObject(result);
                        String type = job.getString("type");
                        JSONArray jay = new JSONArray(type);
                        for (int i = 0; i < jay.length(); i++) {
                            JSONObject job2 = (JSONObject) jay.get(i);
                            String id = job2.getString("id");
                            String name = job2.getString("name");
                            String type2 = job2.getString("type");
                            if (type2.equals("1")) {
                                list_Apple.add(name);
                                Appleadapter = new ArrayAdapter<String>(getActivity(), R.layout.item_type, R.id.tv_Type, list_Apple);
                                gv_Apple_Type.setAdapter(Appleadapter);
                                gv_Apple_Type.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                        Log.d("点击", "gridview的item");
                                        str = list_Apple.get(position).toString().trim();
                                        Log.e("TAG","str+caidna====="+str+caidan);
                                        searchActivityPresenter.sendSearchActivityData(caidan+"+"+str);
                                    }
                                });
                                if (list_Apple.size() <= 0) {
                                    layout_Apple.setVisibility(View.GONE);
                                }
                            } else if (type2.equals("2")) {
                                list_Number.add(name);
                                Numberadapter = new ArrayAdapter<String>(getActivity(), R.layout.layout, R.id.tv_Type, list_Number);
                                gv_Numebr_Type.setAdapter(Numberadapter);
                                if (list_Number.size() <= 0) {
                                    layout_Number.setVisibility(View.GONE);
                                }
                                gv_Numebr_Type.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                        str = list_Number.get(position).toString().trim();
                                        searchActivityPresenter.sendSearchActivityData(caidan+"+"+str);
                                    }
                                });

                            } else if (type2.equals("3")) {
                                list_Address.add(name);
                                Addressadapter = new ArrayAdapter<String>(getActivity(), R.layout.item_type, R.id.tv_Type, list_Address);
                                gv_Address_Type.setAdapter(Addressadapter);
                                gv_Address_Type.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                        Log.d("点击", "gridview的item");
                                        str = list.get(position).toString().trim();
                                        searchActivityPresenter.sendSearchActivityData(caidan+"+"+str);
                                    }
                                });

                                if (list.size() <= 0) {
                                    layout_Address.setVisibility(View.GONE);
                                }
                            } else if (type2.equals("4")) {
                                list_Firm.add(name);
                                Firmadapter = new ArrayAdapter<String>(getActivity(), R.layout.layout, R.id.tv_Type, list_Firm);
                                gv_Firm_Type.setAdapter(Firmadapter);
                                gv_Firm_Type.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                        str = list_Firm.get(position).toString().trim();
                                        searchActivityPresenter.sendSearchActivityData(caidan+"+"+str);
                                    }
                                });

                                if (list_Firm.size() <= 0) {
                                    layout_Firm.setVisibility(View.GONE);
                                }
                            }
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }else{
                    //为空时，二级菜单为空
                    list_Address.clear();
                    list_Apple.clear();
                    list_Firm.clear();
                    list_Number.clear();
                    Appleadapter = new ArrayAdapter<String>(getActivity(), R.layout.item_type, R.id.tv_Type, list_Apple);
                    gv_Apple_Type.setAdapter(Appleadapter);
                    Numberadapter = new ArrayAdapter<String>(getActivity(), R.layout.layout, R.id.tv_Type, list_Number);
                    gv_Numebr_Type.setAdapter(Numberadapter);
                    Addressadapter = new ArrayAdapter<String>(getActivity(), R.layout.item_type, R.id.tv_Type, list_Address);
                    gv_Address_Type.setAdapter(Addressadapter);
                    Firmadapter = new ArrayAdapter<String>(getActivity(), R.layout.layout, R.id.tv_Type, list_Firm);
                    gv_Firm_Type.setAdapter(Firmadapter);
//                    Appleadapter.notifyDataSetChanged();
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
    //左边一级菜单
    private void ShowLeft(){
        RequestParams params=new RequestParams(SuMaoConstant.SUMAO_IP+"/rest/model/atg/commerce/catalog/ProductCatalogActor/homePanel");
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.e("TAG","result-----"+result);
                list=new ArrayList<String>();
                listId=new ArrayList<String>();
                try {
                    JSONObject job=new JSONObject(result);
                    String type=job.getString("type");
                    JSONArray jay=new JSONArray(type);
                    for(int i=0;i<jay.length();i++){
                        JSONObject job2= (JSONObject) jay.get(i);
                        String id=job2.getString("id");
                        String name=job2.getString("name");
                        list.add(name);
                        listId.add(id);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Log.e("TAG","list------"+list.size());
                adapter1=new ListAdapter(list);
                lv_Show_Type.setAdapter(adapter1);
                adapter1.setSelectedPosition(0);
                ShowRight(list.get(0),"");
                adapter1.notifyDataSetInvalidated();
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
}

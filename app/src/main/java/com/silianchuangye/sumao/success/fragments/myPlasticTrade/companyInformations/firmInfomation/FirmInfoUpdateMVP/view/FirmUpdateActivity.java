package com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.firmInfomation.FirmInfoUpdateMVP.view;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.OrderManagement.SpotOrder.TiQu;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.addressDisplayMVP.presenter.AddressDisplayPresenter;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.firmInfomation.FirmInfoMVP.model.FirmBean;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.firmInfomation.FirmInfoTypeActivity;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.receiptAddress.ReceiptAddressMVP.bean.ReAddress;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.receiptAddress.SelectProvinceAreaMVP.view.SelectProvinceArea;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.register.RegisterFirmActivityMVP.presenter.RegisterFirmActivityPresenter;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.register.RegisterFirmActivityMVP.view.IRegisterFirmActivityView;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.register.RegisterPicture;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.register.RegisterValueActivity;
import com.silianchuangye.sumao.success.utils.LogUtils;
import com.silianchuangye.sumao.success.utils.SuMaoConstant;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

public class FirmUpdateActivity extends AppCompatActivity implements View.OnClickListener,IRegisterFirmActivityView {
    private TextView tv_child_title_bar_title;
    private ImageView iv_child_title_bar_back;
    private ListView lvdemo;
    private Button bt_commit;
    private List<Map<String,Object>> list;
   // private String leixing;
    private String mingcheng;
    private String fare;
    private String bumen;
    //private String address;
    private String chuanzhen;
   // private String zhengjian;
    private String jigou;
    private String zhizhao;
    private String shuiwu;
   // private String nashuiren;
    private String sheng;
//    private String city;
//    private String countty;
//    private String cl_jigouImg;
//    private String cl_zhizhaoImg;
//    private String cl_shuiwuImg;
//    private String cl_nashuirenImg;
    private SimpleAdapter adapter;
    private  String leixingId="";
    private String zhengjianId="";
    private String nashuirenId="";
    private StringBuilder sb;
    private RegisterFirmActivityPresenter presenter;
    private String detailAddress="";
    private String leixing;
    private String zhengjian;
    private String address;
    private String nashuiren;
    private TextView firm_info_title;
    private String name;
    private String unique;
    private String laiyuan;
    private String  enterpriseInfoReview="提示语";
    private String cl_state;
    private String xinyong;
    private String cl_shuiwuImg="abc";
    private String cl_zhizhaoImg="abc";
    private String cl_nashuirenImg="abc";
    private String cl_jigouImg="abc";
    String addressDisplay;
    List<String> addressDisplays;
    private AddressDisplayPresenter addressDisplayPresenter;
    private String[] addressDis;
    private List<ReAddress> lists;
    String province="";
    String countty="";
    String city="";
    String cl_leixing_info = "";
    String cl_corporationinfo="";
    String cl_yewu_info = "";
    String cl_dizhi_info = "";
    String cl_zhengjian_info = "";
    String cl_chuanzheniinfo="传真";
    String nashuipeople_info = "";

    String jigou_info = "机构";
    String cl_shuiwu_info = "税务";
    //private String cl_jigou_info;
    private String cl_id;
    private String cl_zhizhao="";
    private String xiangxiaddress="";
    private String cl_zhengjian="";
    private String cl_shuiwuImgfanhui="abc";
    private String cl_zhizhaoImgfanhui="abc";
    private String cl_nashuirenImgfanhui="abc";
    private String cl_jigouImgfanhui="abc";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firm_update);
        tv_child_title_bar_title= (TextView) findViewById(R.id.tv_child_title_bar_title);
        tv_child_title_bar_title.setText("企业信息修改");
        iv_child_title_bar_back= (ImageView) findViewById(R.id.iv_child_title_bar_back);
        iv_child_title_bar_back.setOnClickListener(this);
        lvdemo= (ListView) findViewById(R.id.lvdemo);
        bt_commit= (Button) findViewById(R.id.bt_commit);
        bt_commit.setOnClickListener(this);
        init();
        lvdemo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position==0||position==10||position==6){
                    Intent intent=new Intent(FirmUpdateActivity.this, FirmInfoTypeActivity.class);
                    if (position==0){
                        intent.putExtra("title", "企业类型");
                    }else if (position==6){
                        intent.putExtra("title", "企业注册证件");
                    }else if (position==10){
                        intent.putExtra("title","纳税人类型");
                    }

                    startActivityForResult(intent, position);
                }else if (position==7||position==8||position==9){
                    Intent intent=new Intent(FirmUpdateActivity.this, RegisterPicture.class);
                    if (position==7){
                        intent.putExtra("name","组织机构代码");
                    }else if (position==8){
                        intent.putExtra("name","营业执照号");
                    }else if (position==9){
                        intent.putExtra("name","税务登记号");
                    }
                    intent.putExtra("number",position);
                    startActivity(intent);

                }else if (position==1||position==2||position==3||position==5){
                    Intent intent=new Intent();
                    intent.setClass(FirmUpdateActivity.this, RegisterValueActivity.class);
                    intent.putExtra("content", list.get(position).get("right").toString());
                    intent.putExtra("title", list.get(position).get("left").toString());
                    startActivityForResult(intent, position);
                }else if (position==4){
                    Intent intent=new Intent();
                    intent.putExtra("className", "updateFirmActivity");
                    intent.setClass(FirmUpdateActivity.this, SelectProvinceArea.class);
                    startActivityForResult(intent, position);
                }
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case 0:
                leixingId=data.getStringExtra("");
                leixing=data.getStringExtra("name");
                list.get(0).put("right",leixing);
                adapter.notifyDataSetChanged();
                break;
            case 1:
                mingcheng=data.getStringExtra("name");
                list.get(1).put("right",mingcheng);
                adapter.notifyDataSetChanged();
                break;
            case 2:
                fare=data.getStringExtra("name");
                list.get(2).put("right",fare);
                adapter.notifyDataSetChanged();
                break;
            case 3:
                bumen=data.getStringExtra("name");
                list.get(3).put("right",bumen);
                adapter.notifyDataSetChanged();
                break;

            case 5:
                chuanzhen=data.getStringExtra("name");
                list.get(5).put("right",chuanzhen);
                adapter.notifyDataSetChanged();
                break;
            case 6:
                zhengjianId=data.getStringExtra("");
                zhengjian=data.getStringExtra("name");
                list.get(6).put("right",zhengjian);
                adapter.notifyDataSetChanged();
                break;
            case 7:
                jigou=data.getStringExtra("value");
                cl_jigouImgfanhui=data.getStringExtra("picturePath");
                list.get(7).put("right",jigou);
                adapter.notifyDataSetChanged();
                break;
            case 8:
                zhizhao=data.getStringExtra("value");
                cl_zhizhaoImgfanhui=data.getStringExtra("picturePath");
                list.get(8).put("right",zhizhao);
                adapter.notifyDataSetChanged();
                break;
            case 9:
                shuiwu=data.getStringExtra("value");
                cl_shuiwuImgfanhui=data.getStringExtra("picturePath");
                list.get(9).put("right",shuiwu);
                adapter.notifyDataSetChanged();
                break;
            case 10:
                nashuirenId=data.getStringExtra("");
                nashuiren=data.getStringExtra("name");
                list.get(10).put("right",nashuiren);
                cl_nashuirenImgfanhui=data.getStringExtra("picturePath");
                adapter.notifyDataSetChanged();
                break;

        }
    }

    public void init(){
        sendNet();
//        intent.putExtra("leixing",cl_leixing_info);
//        intent.putExtra("mingcheng",name);
//        intent.putExtra("fare",cl_corporationinfo);
//        intent.putExtra("bumen",cl_yewu_info);
//        intent.putExtra("address",xiangxiaddress);
//        intent.putExtra("chuanzhen",cl_chuanzheniinfo);
//        intent.putExtra("zhengjian",cl_zhengjian_info);
//        intent.putExtra("jigou",jigou_info);
//        intent.putExtra("zhizhao",cl_zhizhao);
//        intent.putExtra("shuiwu",cl_shuiwu_info);
//        intent.putExtra("nashuiren",nashuipeople_info);
//        intent.putExtra("sheng",province);
//        intent.putExtra("city",city);
//        intent.putExtra("countty",countty);
//        intent.putExtra("cl_jigouImg",cl_jigouImg);
//        intent.putExtra("cl_zhizhaoImg",cl_zhizhaoImg);
//        intent.putExtra("cl_shuiwuImg",cl_shuiwuImg);
//        intent.putExtra("cl_nashuirenImg",cl_nashuirenImg);

//        Bundle bundle=getIntent().getExtras();
//        sheng=bundle.getString("sheng");
//        city=bundle.getString("city");
//        countty=bundle.getString("countty");
//        address=bundle.getString("province");
//        cl_jigouImg=bundle.getString("cl_jigouImg");
//        cl_zhizhaoImg=bundle.getString("cl_zhizhaoImg");
//        cl_shuiwuImg=bundle.getString("cl_shuiwuImg");
//        cl_nashuirenImg=bundle.getString("cl_nashuirenImg");
//        list=new ArrayList<>();
//        Map<String,Object> map1=new Hashtable<>();
//        map1.put("left", "企业类型");
//        map1.put("right", sheng);
//        Log.d("类型的值", bundle.getString("leixing"));
//        map1.put("icon", R.mipmap.my_sumao_iv_order);
//        list.add(map1);
//        Map<String,Object> map2=new Hashtable<>();
//        map2.put("left", "企业名称");
//        map2.put("right", bundle.getString("mingcheng"));
//        Log.d("类型的值", bundle.getString("mingcheng"));
//        map2.put("icon", R.mipmap.my_sumao_iv_order);
//        list.add(map2);
//        Map<String,Object> map3=new Hashtable<>();
//        map3.put("left", "企业法人");
//        map3.put("right", bundle.getString("fare"));
//        map3.put("icon", R.mipmap.my_sumao_iv_order);
//        list.add(map3);
//        Map<String,Object> map4=new Hashtable<>();
//        map4.put("left", "业务部门");
//        map4.put("right", bundle.getString("bumen"));
//        map4.put("icon", R.mipmap.my_sumao_iv_order);
//        list.add(map4);
//        Map<String,Object> map5=new Hashtable<>();
//        map5.put("left", "办公地址");
//        map5.put("right", bundle.getString("address"));
//        map5.put("icon", R.mipmap.my_sumao_iv_order);
//        list.add(map5);
//        Map<String,Object> map6=new Hashtable<>();
//        map6.put("left", "传真号");
//        if (bundle.getString("chuanzhen").equals("传真")){
//            map6.put("right", "");
//        }else{
//            map6.put("right", bundle.getString("chuanzhen"));
//        }
//
//        map6.put("icon", R.mipmap.my_sumao_iv_order);
//        list.add(map6);
//        Map<String,Object> map7=new Hashtable<>();
//        map7.put("left", "企业注册证件");
//        map7.put("right", bundle.getString("zhengjian"));
//        map7.put("icon", R.mipmap.my_sumao_iv_order);
//        list.add(map7);
//        Map<String,Object> map8=new Hashtable<>();
//        map8.put("left", "组织机构代码");
//        if (bundle.getString("jigou").equals("机构")){
//            map8.put("right", "");
//        }else {
//            map8.put("right", bundle.getString("jigou"));
//        }
//
//        map8.put("icon", R.mipmap.my_sumao_iv_order);
//        list.add(map8);
//        Map<String,Object> map9=new Hashtable<>();
//        map9.put("left", "营业执照号");
//        map9.put("right", bundle.getString("zhizhao"));
//        map9.put("icon", R.mipmap.my_sumao_iv_order);
//        list.add(map9);
//        Map<String,Object> map10=new Hashtable<>();
//        map10.put("left", "税务登记号");
//        if (bundle.getString("shuiwu").equals("税务")){
//            map10.put("right", "");
//        }else{
//            map10.put("right", bundle.getString("shuiwu"));
//        }
//
//        map10.put("icon", R.mipmap.my_sumao_iv_order);
//        list.add(map10);
//        Map<String,Object> map11=new Hashtable<>();
//        map11.put("left", "纳税人类型");
//        if (bundle.getString("nashuiren").equals("7")){
//            map11.put("right", "小规模纳税人");
//        }else if (bundle.getString("nashuiren").equals("8")){
//            map11.put("right", "一般纳税人");
//        }
//       // map11.put("right", );
//        map11.put("icon", R.mipmap.my_sumao_iv_order);
//        list.add(map11);
//        adapter = new SimpleAdapter(FirmUpdateActivity.this, list, R.layout.item_firm_info, new String[]{"left", "center", "right", "icon"}, new int[]{R.id.tv_firm_info, R.id.tvTitle_firm_info, R.id.tvValue_firm_info, R.id.ivMore_firm_info});
//        lvdemo.setAdapter(adapter);
//        adapter.notifyDataSetChanged();





    }
    private void sendNet(){
        LogUtils.log("企业信息-->");
        RequestParams params=new RequestParams(SuMaoConstant.SUMAO_IP+"/rest/model/atg/userprofiling/ProfileActor/enterpriseInfo");
        SharedPreferences sp=getSharedPreferences("sumao",Activity.MODE_PRIVATE);
        String unique=sp.getString("unique","");
        Log.d("session的之",unique);

        params.addParameter("_dynSessConf",unique);
        Log.d("企业信息的rp",params+"");
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                LogUtils.log("企业信息result-->onSuccess-->"+result);
                try {
                    JSONObject job = new JSONObject(result);
                    String info = job.getString("info");
                    if (info.equals("fail")) {
                        Toast.makeText(FirmUpdateActivity.this, "请重新登陆", Toast.LENGTH_SHORT).show();
                        new TiQu(FirmUpdateActivity.this).showLogin();
                        finish();
                    } else {
                        Log.d("企业信息的返回值",info);
                        Log.d("result的值",result);
                        Gson gson=new Gson();
                        FirmBean firmBean=gson.fromJson(result,FirmBean.class);
                        if (result.contains("cl_chuanzhen")) {
                            cl_chuanzheniinfo = firmBean.getCl_chuanzhen();
                        }
                        Log.d("传真的值",cl_chuanzheniinfo+"nnnnn");
                        cl_state=firmBean.getCl_stat();
                        Log.d("状态的值",cl_state);
                        if (result.contains("cl_shuiwuImg")) {
                            cl_shuiwuImg = firmBean.getCl_shuiwuImg();
                        }
                        name=firmBean.getCl_mingcheng();
                        cl_corporationinfo=firmBean.getCl_corporation();
                        if (result.contains("cl_jigouImg")) {
                            cl_jigouImg = firmBean.getCl_jigouImg();
                        }
                        province=firmBean.getU_province();
                        cl_yewu_info=firmBean.getCl_yewu();
                        cl_zhengjian_info=zhengjianType(firmBean.getCl_zhengjian());
                        if (result.contains("cl_jigou")){
                            jigou_info=firmBean.getCl_jigou();
                        }

                        cl_id=firmBean.getCl_id();
                        if (result.contains("enterpriseInfoReview"))
                        {
                            enterpriseInfoReview=firmBean.getEnterpriseInfoReview();
                        }
                        // enterpriseInfoReview=firmBean.get"cl_zhengjian":"5","cl_id":"1666758","info":"sucess","enterpriseInfoReview":"35","U_city":"124201","cl_zhizhaoImg":"/mnt/docs/rCgXbJuV2HFoC20161130043419.jpg","cl_dizhi":"武汉市","U_county":"12420114","cl_zhizhao":"79294954-7","cl_leixing":"99","cl_nashuirenImg":"/mnt/docs/hXPSudH8vTKl74yBehT20161130043424.jpg","cl_nashuiren":"6();
                        city=firmBean.getU_city();
                        if (result.contains("cl_zhizhaoImg")) {
                            cl_zhizhaoImg = firmBean.getCl_zhizhaoImg();
                        }
                        cl_dizhi_info=firmBean.getCl_dizhi();
                        countty=firmBean.getU_county();
                        if (result.contains("cl_shuiwu")) {
                            cl_shuiwu_info = firmBean.getCl_shuiwu();
                        }
                        cl_zhizhao=firmBean.getCl_zhizhao();
                        cl_leixing_info=qiyeType(firmBean.getCl_leixing());
                        if (result.contains("cl_nashuirenImg")) {
                            cl_nashuirenImg = firmBean.getCl_nashuirenImg();
                        }
                        nashuipeople_info=firmBean.getCl_nashuiren();
                        getAddress_info(province,city,countty,cl_dizhi_info);

                    }
                }catch(JSONException e){
                    e.printStackTrace();
                }

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                LogUtils.log("企业信息onError-->"+ex);
            }

            @Override
            public void onCancelled(CancelledException cex) {
                LogUtils.log("企业信息onCancelled-->"+cex);
            }

            @Override
            public void onFinished() {
                LogUtils.log("企业信息onFinished-->");
            }
        });
    }
    private String qiyeType(String i){
        String str="";
        if(i.equals("1")){
            str="贸易商";
        }else if(i.equals("2")){
            str="工贸一体";
        }else if(i.equals("3")){
            str="生产厂";
        }else if(i.equals("99")){
            str="物流公司";
        }
        return str;
    }

    private String zhengjianType(String i){
        String str="";
        if(i.equals("4")){
            str="三证独立";
        }else if(i.equals("5")){
            str="三证合一";
        }
        return str;
    }
    public void getAddress_info(String province, String city, String county, final String cl_dizhi ){
        String url = SuMaoConstant.SUMAO_IP+"/rest/model/atg/userprofiling/ProfileActor/addressDisplay"+"?prov="+province+"&city="+city+"&county="+county;
        RequestParams requestParams=new RequestParams(url);
        x.http().get(requestParams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.d("详细地址的返回值",""+result);
                list = new ArrayList<Map<String, Object>>();
                Log.d("enterpriseInfoReview",enterpriseInfoReview);
                if (cl_state.equals("35")) {
                    Log.d("返回","您企业信息正在重新审核,塑贸网将审核企业新信息，在新信息完成审核前，企业信息将仍然采用原信息。");
                   // firm_info_title.setText("您企业信息正在重新审核,塑贸网将审核企业新信息，在新信息完成审核前，企业信息将仍然采用原信息。");
                    //tvUpdate.setVisibility(View.GONE);
                } else if (cl_state.equals("25")) {
                    Log.d("返回","您企业信息正在审核中，请您耐心等待！如果您有任何问题，请与我们联系!");
                   // firm_info_title.setText("您企业信息正在审核中，请您耐心等待！如果您有任何问题，请与我们联系!");
                } else if (cl_state.equals("26")) {
                    Log.d("返回","对不起,您的企业信息未通过审核");
                    //firm_info_title.setText("对不起,您的企业信息未通过审核");

                } else {
                   // firm_info_title.setText("恭喜您,企业信息已通过审核。");
                    Log.d("返回","恭喜您,企业信息已通过审核。");
                }
                Map<String, Object> map2 = new Hashtable<String, Object>();
                map2.put("left", "企业类型");
                map2.put("right", cl_leixing_info);
                map2.put("icon", R.mipmap.my_sumao_iv_order);
                list.add(map2);
                Log.d("类型",cl_leixing_info+name);
                if (!name.equals("")) {
                    Map<String, Object> map3 = new Hashtable<String, Object>();
                    map3.put("left", "企业名称");
                    map3.put("right", name);
                    map3.put("icon", R.mipmap.my_sumao_iv_order);
                    list.add(map3);
                }else{
                    Map<String, Object> map3 = new Hashtable<String, Object>();
                    map3.put("left", "企业名称");
                    map3.put("right", "");
                    map3.put("icon", R.mipmap.my_sumao_iv_order);
                    list.add(map3);
                }
                // Log.d("企业法人",qiyefaren_info);
                Map<String,Object> map_fare=new Hashtable<String, Object>();
                map_fare.put("left", "企业法人");
                map_fare.put("right", cl_corporationinfo);
                map_fare.put("icon", R.mipmap.my_sumao_iv_order);
                list.add(map_fare);
                Map<String, Object> map4 = new Hashtable<String, Object>();
                map4.put("left", "业务部门");
                map4.put("right", cl_yewu_info);
                map4.put("icon", R.mipmap.my_sumao_iv_order);
                list.add(map4);
                Log.d("夜袭",cl_corporationinfo+cl_corporationinfo);
                String province="";
                try {
                    JSONObject json=new JSONObject(result);
                    province=json.getString("address");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                xiangxiaddress=province+cl_dizhi_info;
                Map<String,Object> map5=new Hashtable<String, Object>();
                map5.put("left","办公地址");
                map5.put("right",xiangxiaddress);
                map5.put("icon", R.mipmap.my_sumao_iv_order);
                list.add(map5);
                Log.d("穿着",cl_chuanzheniinfo);
                if (cl_chuanzheniinfo.equals("传真")) {
                    Log.d("if","if");
                    Map<String, Object> map6 = new Hashtable<String, Object>();
                    map6.put("left", "传真号");
                    map6.put("right", "");
                    map6.put("icon", R.mipmap.my_sumao_iv_order);
                    Log.e("TAG", "chuanzhen======" + cl_chuanzheniinfo);
                    list.add(map6);
                } else {
                    Log.d("else","else");
                    Map<String, Object> map6 = new Hashtable<String, Object>();
                    map6.put("left", "传真号");
                    map6.put("right", cl_chuanzheniinfo);
                    map6.put("icon", R.mipmap.my_sumao_iv_order);
                    Log.e("TAG", "chuanzhen======" + cl_chuanzheniinfo);
                    list.add(map6);
                }

                Map<String, Object> map7 = new Hashtable<String, Object>();
                map7.put("left", "企业注册证件");
                map7.put("right", cl_zhengjian_info);
                map7.put("icon", R.mipmap.my_sumao_iv_order);
                list.add(map7);
                if (jigou_info.equals("机构")){
                    Log.d("祖居if","if");
                    Map<String, Object> map8 = new HashMap<String, Object>();
                    map8.put("left", "组织机构代码");
                    map8.put("right", "");
                    map8.put("icon", R.mipmap.my_sumao_iv_order);
                    list.add(map8);
                }else{
                    Log.d("zuzhiif","else");
                    Map<String, Object> map8 = new HashMap<String, Object>();
                    map8.put("left", "组织机构代码");
                    map8.put("right", jigou_info);
                    map8.put("icon", R.mipmap.my_sumao_iv_order);
                    list.add(map8);
                }
                if (cl_zhizhao.equals("")){
                    Log.d("执照if","if");
                    Map<String, Object> map9 = new Hashtable<String, Object>();
                    map9.put("right", "");
                    map9.put("icon", R.mipmap.my_sumao_iv_order);
                    if (cl_zhengjian.equals("三证合一")) {
                        map9.put("left", "统一社会信用代码");
                    } else {
                        map9.put("left", "营业执照号");

                    }
                    list.add(map9);
                }else{
                    Log.d("执照else","else");
                    Map<String, Object> map9 = new Hashtable<String, Object>();
                    map9.put("right", cl_zhizhao);
                    map9.put("icon", R.mipmap.my_sumao_iv_order);
                    if (cl_zhengjian.equals("三证合一")) {
                        map9.put("left", "统一社会信用代码");
                    } else {
                        map9.put("left", "营业执照号");

                    }
                    list.add(map9);
                }
                if (cl_shuiwu_info.equals("税务")){
                    Map<String, Object> map10 = new HashMap<String, Object>();
                    map10.put("left", "税务登记号");
                    map10.put("right", "");
                    map10.put("icon", R.mipmap.my_sumao_iv_order);
                    list.add(map10);
                    Log.e("TAG", "xinyong======" + cl_zhizhao+"123");
                }else {
                    Map<String, Object> map10 = new HashMap<String, Object>();
                    map10.put("left", "税务登记号");
                    map10.put("right", cl_shuiwu_info);
                    map10.put("icon", R.mipmap.my_sumao_iv_order);
                    list.add(map10);
                    Log.e("TAG", "xinyong======" + cl_zhizhao);
                }





                Map<String, Object> map11 = new Hashtable<String, Object>();
                map11.put("left", "纳税人类型");
                if (nashuipeople_info.equals("7")) {
                    map11.put("right", "小规模纳税人");
                }else {
                    map11.put("right", " 一般纳税人");
                }
                map11.put("icon", R.mipmap.my_sumao_iv_order);
                Log.e("TAG", "nashuipeople======" + nashuipeople_info);
                if (nashuipeople_info.equals("")) {

                } else {
                    list.add(map11);
                }
                Log.e("TAG", "list.size()=====" + list.size());
                adapter = new SimpleAdapter(FirmUpdateActivity.this, list, R.layout.item_firm_info, new String[]{"left", "center", "right", "icon"}, new int[]{R.id.tv_firm_info, R.id.tvTitle_firm_info, R.id.tvValue_firm_info, R.id.ivMore_firm_info});
                lvdemo.setAdapter(adapter);
                adapter.notifyDataSetChanged();
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

    public void SubmitUpdate(){
        String url= SuMaoConstant.SUMAO_IP+"/rest/model/atg/store/profile/RegistrationActor/updateEnterprise";
        RequestParams requestParams=new RequestParams(url);
        requestParams.setAsJsonContent(true);
        JSONObject jsonObject=new JSONObject();
        try {
            jsonObject.put("cl_mingcheng",list.get(1).get("right").toString());
            jsonObject.put("cl_yewu",list.get(3).get("right").toString());
            if(list.get(4).get("right").toString().equals(xiangxiaddress)) {

                jsonObject.put("province", province);
                jsonObject.put("city",city );
                jsonObject.put("county", countty);
                jsonObject.put("cl_dizhi", cl_dizhi_info);
            }else{
                jsonObject.put("province", sb.substring(0, 4));
                jsonObject.put("city", sb.substring(0, 6));
                jsonObject.put("county", sb.toString());
                jsonObject.put("cl_dizhi", detailAddress);
            }

            jsonObject.put("cl_chuanzhen",list.get(5).get("right").toString());
            if (list.get(0).get("right").toString().equals("贸易商")){
                jsonObject.put("cl_leixing","1");

            }else if (list.get(0).get("right").toString().equals("工贸一体")){
                jsonObject.put("cl_leixing","2");
            }else if (list.get(0).get("right").toString().equals("生产厂")){
                jsonObject.put("cl_leixing","3");
            }else{
                jsonObject.put("cl_leixing","99");
            }
            if(list.get(6).get("right").toString().equals("三证独立")){
                jsonObject.put("cl_zhengjian",4);
                jsonObject.put("cl_zhizhao",list.get(8).get("right").toString());
                jsonObject.put("cl_jigou",list.get(7).get("right").toString());
                jsonObject.put("cl_shuiwu",list.get(9).get("right").toString());
                if (cl_zhizhaoImgfanhui.equals("abc")) {
                    jsonObject.put("cl_zhizhaoimage", cl_zhizhaoImg);
                }else{
                    jsonObject.put("cl_zhizhaoimage", cl_zhizhaoImgfanhui);
                }
                if (cl_jigouImgfanhui.equals("abc")){
                    jsonObject.put("cl_jigouimage",cl_jigouImg);
                }else{
                    jsonObject.put("cl_jigouimage",cl_jigouImgfanhui);
                }
                if (cl_shuiwuImgfanhui.equals("abc")){
                    jsonObject.put("cl_shuiwuimage",cl_shuiwuImg);
                }else{
                    jsonObject.put("cl_shuiwuimage",cl_shuiwuImgfanhui);
                }

            }else{
                jsonObject.put("cl_zhengjian",5);
                jsonObject.put("cl_zhizhao",list.get(8).get("right").toString());
                if (cl_zhizhaoImgfanhui.equals("abc")) {
                    jsonObject.put("cl_zhizhaoimage", cl_zhizhaoImg);
                }else {
                    jsonObject.put("cl_zhizhaoimage", cl_zhizhaoImgfanhui);
                }
            }
            jsonObject.put("cl_entName",list.get(2).get("right").toString());

            jsonObject.put("cl_nashuiren",list.get(10).get("right").toString());
            if (cl_nashuirenImgfanhui.equals("abc")) {
                jsonObject.put("cl_nashuirenimage", cl_nashuirenImg);
            }else {
                jsonObject.put("cl_nashuirenimage", cl_nashuirenImgfanhui);
            }
            SharedPreferences sp=getSharedPreferences("sumao", Activity.MODE_PRIVATE);
            String unique=sp.getString("unique","");
            jsonObject.put("_dynSessConf",unique);
            requestParams.setBodyContent(jsonObject.toString());
            Log.d("传的参数",jsonObject.toString());
            x.http().post(requestParams, new Callback.CommonCallback<String>() {
                @Override
                public void onSuccess(String result) {
                    Log.d("修改企业信息的值",result);
                    try {
                        JSONObject jsonObject=new JSONObject(result);
                        String formExceptions=jsonObject.getString("formExceptions");
                        JSONArray jsonArray=new JSONArray(formExceptions);
                        for (int i=0;i<jsonArray.length();i++){
                            JSONObject json=jsonArray.getJSONObject(i);
                            String localizedMessage=json.getString("localizedMessage");
                            Toast.makeText(FirmUpdateActivity.this, localizedMessage, Toast.LENGTH_SHORT).show();
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onError(Throwable ex, boolean isOnCallback) {
                    Toast.makeText(FirmUpdateActivity.this, "修改失败!", Toast.LENGTH_SHORT).show();

                }

                @Override
                public void onCancelled(CancelledException cex) {

                }

                @Override
                public void onFinished() {

                }
            });




        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.iv_child_title_bar_back:
                FirmUpdateActivity.this.finish();
                break;
            case R.id.bt_commit:
                //提交信息改变
                SubmitUpdate();
                break;
        }

    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        String address = getIntent().getStringExtra("address");
        detailAddress=getIntent().getStringExtra("detailAddress");
        LogUtils.log("address------->RegisterFirmActivity:" + address);
        sb = new StringBuilder();
        sb.append(address);
        LogUtils.log("StringBuilder------->RegisterFirmActivity:" + sb.toString());
        presenter = new RegisterFirmActivityPresenter(this);
        LogUtils.log("StringBuilder------->RegisterFirmActivity:" + sb.substring(0, 4) + "+" + sb.substring(0, 6) + "+" + sb.toString());
        presenter.setDetailAddress(sb.substring(0, 4), sb.substring(0, 6), sb.toString());

    }

    @Override
    public void setStringInText(String address) {
        LogUtils.log("address------->RegisterFirmActivity:" + address);
        //office_address_image.setText(address);
        list.get(4).put("right",address+detailAddress);
        adapter.notifyDataSetChanged();
    }
}

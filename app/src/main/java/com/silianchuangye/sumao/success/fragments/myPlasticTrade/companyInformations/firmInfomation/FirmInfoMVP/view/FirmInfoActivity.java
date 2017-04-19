package com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.firmInfomation.FirmInfoMVP.view;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.silianchuangye.sumao.success.MainActivity;
import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.OrderManagement.SpotOrder.TiQu;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.addressDisplayMVP.presenter.AddressDisplayPresenter;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.addressDisplayMVP.view.IAddressDisplayView;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.firmInfomation.FirmInfoMVP.model.FirmBean;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.firmInfomation.FirmInfoMVP.presenter.FirmInfoPresenter;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.firmInfomation.FirmInfoPicture.FirmInfoPictureMVP.view.FirmInfoPictureActivity;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.firmInfomation.FirmInfoUpdateMVP.bean.FirmInfoUpdateActivityBean;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.firmInfomation.FirmInfoUpdateMVP.view.FirmInfoUpdateActivity;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.firmInfomation.FirmShowImageActivity;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.receiptAddress.ReceiptAddressMVP.bean.ReAddress;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.receiptAddress.ReceiptAddressMVP.view.IReceiptAddressView;
import com.silianchuangye.sumao.success.model.DifferentTypes;
import com.silianchuangye.sumao.success.utils.GlobalVariable;
import com.silianchuangye.sumao.success.utils.LogUtils;
import com.silianchuangye.sumao.success.utils.SuMaoConstant;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class FirmInfoActivity extends AppCompatActivity implements IFirmInfoView,IAddressDisplayView,IReceiptAddressView {
    ImageView iv_title_bar_logo,
            iv_title_bar_back,
            iv_title_bar_service,
            add_address,
            iv_title_bar_search;
    Button sv_title_bar_serachView;
    TextView tv_title_bar_title,tv,tvUpdate;
    RelativeLayout layoutTop,add_address_rl;
    private ListView lv_firm_info;
    private List<Map<String,Object>> list;
    SimpleAdapter adapter;
    private FirmInfoPresenter firmInfoPresenter;
    private DifferentTypes value4;
    private HashMap<String, String> cl_leixing;
    private HashMap<String, String> cl_zhengjian;
    private HashMap<String, String> cl_applyType;
    private HashMap<String, String> cl_nashuiren;
    private FirmInfoUpdateActivityBean firmInfoUpdateActivityBea;
    private String leixing;
    private String zhengjian;
    private String address;
    private String nashuiren;
    private TextView firm_info_title;
    private String name;
    private String unique;
    private String laiyuan;
    private String  enterpriseInfoReview;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firm_info);
        firmInfoPresenter = new FirmInfoPresenter(this);
        lists=new ArrayList<>();
        addressDisplayPresenter = new AddressDisplayPresenter(this);
        firmInfoPresenter.setDataInView();
        firm_info_title = ((TextView) findViewById(R.id.firm_info_title));
//            initList();


        title_Bar();
        lv_firm_info= (ListView) findViewById(R.id.lvShow_firm_info);
        Bundle bundle=getIntent().getExtras();
        laiyuan=bundle.getString("resource");
        Log.d("来源",laiyuan);



    }

    @Override
    protected void onStart() {
        super.onStart();
        SharedPreferences sp=getSharedPreferences("sumao", Activity.MODE_PRIVATE);
        unique=sp.getString("unique","");
        Log.d("取出来的session",""+unique);
        sendNet();
        lv_firm_info.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                if (position==7||position==8){
//                    Intent intent=new Intent(FirmInfoActivity.this,FirmInfoPictureActivity.class);
//                    startActivity(intent);
//                }
                if (position==7||position==8||position==9||position==10){
                    Intent intent=new Intent(FirmInfoActivity.this,FirmShowImageActivity.class);
                    if (position==7){
                        intent.putExtra("path",cl_jigouImg);
                    }else if (position==8){
                        intent.putExtra("path",cl_shuiwuImg);
                    }else if (position==9){
                        intent.putExtra("path",cl_zhizhaoImg);
                    }else if (position==10){
                        intent.putExtra("path",cl_nashuirenImg);
                    }
                    intent.putExtra("title",list.get(position).get("left").toString());
                    startActivity(intent);
                }
            }
        });
    }


    public void title_Bar(){

        iv_title_bar_back = ((ImageView) findViewById(R.id.iv_title_bar_back));
        iv_title_bar_logo = ((ImageView) findViewById(R.id.iv_title_bar_logo));
        iv_title_bar_service = ((ImageView) findViewById(R.id.iv_title_bar_service));
        iv_title_bar_search = ((ImageView) findViewById(R.id.iv_title_bar_search));
        sv_title_bar_serachView = ((Button) findViewById(R.id.sv_title_bar_serachView));
        tv_title_bar_title  = ((TextView) findViewById(R.id.tv_title_bar_title));

        tvUpdate= (TextView) findViewById(R.id.tvUpdate);
        iv_title_bar_logo.setVisibility(View.GONE);
        iv_title_bar_service.setVisibility(View.GONE);
        sv_title_bar_serachView.setVisibility(View.GONE);
        iv_title_bar_search.setVisibility(View.GONE);
        tv_title_bar_title.setText("企业信息");

        tvUpdate.setText("修改");
        tv_title_bar_title.setTextColor(Color.WHITE);
        iv_title_bar_back.setVisibility(View.VISIBLE);
        layoutTop= (RelativeLayout) findViewById(R.id.layoutTop_firm_info);
        layoutTop.setBackgroundColor(getResources().getColor(R.color.textColor_expandable_listview_show));

        iv_title_bar_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent=new Intent();
//                setResult(105,intent);
//                FirmInfoActivity.this.finish();
                if (laiyuan.equals("我的sumao")){
                    FirmInfoActivity.this.finish();
                }else if (laiyuan.equals("注册")){
                    Intent intent = new Intent();
                    intent.setClass(FirmInfoActivity.this, MainActivity.class);
                    intent.putExtra("cart", 3);
                    intent.putExtra("name", name);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    GlobalVariable.FLAG = true;
                    FirmInfoActivity.this.finish();
                }
            }
        });

        tvUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firmInfoPresenter.getFirmInfoUpdatePresenterInActivity();
            }
        });
    }

    private void sendNet(){
        LogUtils.log("企业信息-->");
        RequestParams params=new RequestParams(SuMaoConstant.SUMAO_IP+"/rest/model/atg/userprofiling/ProfileActor/enterpriseInfo");
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
                        Toast.makeText(FirmInfoActivity.this, "请重新登陆", Toast.LENGTH_SHORT).show();
                        new TiQu(FirmInfoActivity.this).showLogin();
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
                        enterpriseInfoReview=firmBean.getEnterpriseInfoReview();
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







//                        if (job.toString().contains("cl_mingcheng")) {
//                            name = job.getString("cl_mingcheng");
//                        }else{
//                            name="";
//                        }
//                        Log.d("名称的值",name);
//                         cl_leixing_info = qiyeType(job.getString("cl_leixing"));
//                         cl_yewu_info = job.getString("cl_yewu");
//                         cl_dizhi_info = job.getString("cl_dizhi");
//                        cl_state = job.getString("cl_stat");
//                        Log.d("状态的值",cl_state+cl_leixing_info+cl_yewu_info+cl_dizhi_info);
//                        if (job.toString().contains("enterpriseInfoReview")) {
//                             enterpriseInfoReview = job.getString("enterpriseInfoReview");
//                        }
//                         cl_zhengjian_info = zhengjianType(job.getString("cl_zhengjian"));
//
//                         nashuipeople_info = job.getString("cl_nashuiren");
//
//                         qiyefaren_info=job.getString("cl_corporation");
//                        Log.d("返回的值",enterpriseInfoReview+cl_zhengjian_info+nashuipeople_info);
//                        if (result.contains("cl_shuiwuImg")){
//                            cl_shuiwuImg=job.getString("cl_shuiwuImg");
//                        }else{
//                            cl_shuiwuImg="";
//                        }
//                        if (result.contains("cl_jigouImg")){
//                            cl_jigouImg=job.getString("cl_jigouImg");
//                        }else{
//                            cl_jigouImg="";
//                        }
//                        if (result.contains("cl_zhizhaoImg")){
//                            cl_zhizhaoImg=job.getString("cl_zhizhaoImg");
//                        }else{
//                            cl_zhizhaoImg="";
//                        }
//                        if (result.contains("cl_nashuirenImg")){
//                            cl_nashuirenImg=job.getString("cl_nashuirenImg");
//                        }else{
//                            cl_nashuirenImg="";
//                        }
//
//
//
//
//
//                        if (result.contains("cl_chuanzhen")) {
//                            chuanzhen_info = job.getString("cl_chuanzhen");
//                        } else {
//                            chuanzhen_info = "";
//                        }
//                        Log.d("传真",chuanzhen_info);
//
//                        if (cl_zhengjian.equals("5")) {
//                            if (result.contains("cl_zhizhao")){
//                                xinyong=job.getString("cl_zhizhao");
//                            }else{
//                                xinyong="";
//                            }
//
//                        } else {
//                            if (result.contains("cl_zhizhao")){
//                                xinyong=job.getString("cl_zhizhao");
//                            }else{
//                                xinyong="";
//                            }
//                            if (result.contains("cl_jigou")){
//                                jigou_info=job.getString("cl_jigou");
//                            }else{
//                                jigou_info="";
//                            }
//                            if (result.contains("cl_shuiwu")){
//cl_shuiwu_info=job.getString("cl_shuiwu");
//                            }
//                            cl_shuiwu_info="";
//
//                        }
//                        if (result.contains("U_province") && result.contains("U_county") && result.contains("U_city")){
//                             province=job.getString("U_province");
//                             countty=job.getString("U_county");
//                             city=job.getString("U_city");
//                            Log.d("地址",province+countty+city);
//                            getAddress_info(province,city,countty,cl_dizhi_info);
//                        }
//
//
//                        //这里判断是新增还是修改，如果新增则没有后面的值
//
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
    public void getAddress_info(String province, String city, String county, final String cl_dizhi ){
       String url = SuMaoConstant.SUMAO_IP+"/rest/model/atg/userprofiling/ProfileActor/addressDisplay"+"?prov="+province+"&city="+city+"&county="+county;
       RequestParams requestParams=new RequestParams(url);
        x.http().get(requestParams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.d("详细地址的返回值",""+result);
                list = new ArrayList<Map<String, Object>>();
                Log.d("enterpriseInfoReview",enterpriseInfoReview);
                if (enterpriseInfoReview.equals("35")) {
                    firm_info_title.setText("您企业信息正在重新审核,塑贸网将审核企业新信息，在新信息完成审核前，企业信息将仍然采用原信息。");
                    tvUpdate.setVisibility(View.GONE);
                } else if (enterpriseInfoReview.equals("25")) {
                    firm_info_title.setText("您企业信息正在审核中，请您耐心等待！如果您有任何问题，请与我们联系!");
                } else if (enterpriseInfoReview.equals("26")) {
                    firm_info_title.setText("对不起,您的企业信息未通过审核");

                } else {
                    firm_info_title.setText("恭喜您,企业信息已通过审核。");
                }
                Map<String, Object> map2 = new Hashtable<String, Object>();
                map2.put("left", "企业类型");
                map2.put("right", cl_leixing_info);
                list.add(map2);
                Log.d("类型",cl_leixing_info+name);
                if (!name.equals("")) {
                    Map<String, Object> map3 = new Hashtable<String, Object>();
                    map3.put("left", "企业名称");
                    map3.put("right", name);
                    list.add(map3);
                }else{
                    Map<String, Object> map3 = new Hashtable<String, Object>();
                    map3.put("left", "企业名称");
                    map3.put("right", "");
                    list.add(map3);
                }
               // Log.d("企业法人",qiyefaren_info);
                Map<String,Object> map_fare=new Hashtable<String, Object>();
                map_fare.put("left", "企业法人");
                map_fare.put("right", cl_corporationinfo);
                list.add(map_fare);
                Map<String, Object> map4 = new Hashtable<String, Object>();
                map4.put("left", "业务部门");
                map4.put("right", cl_yewu_info);
                list.add(map4);
Log.d("夜袭",cl_corporationinfo+cl_corporationinfo);
                String province="";
                try {
                    JSONObject json=new JSONObject(result);
                    province=json.getString("address");
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                Map<String,Object> map5=new Hashtable<String, Object>();
                map5.put("left","办公地址");
                map5.put("right",province+cl_dizhi_info);
                list.add(map5);
                Log.d("穿着",cl_chuanzheniinfo);
                if (cl_chuanzheniinfo.equals("传真")) {
                    Log.d("if","if");
                    Map<String, Object> map6 = new Hashtable<String, Object>();
                    map6.put("left", "传真号");
                    map6.put("right", "");
                    Log.e("TAG", "chuanzhen======" + cl_chuanzheniinfo);
                    list.add(map6);
                } else {
                    Log.d("else","else");
                    Map<String, Object> map6 = new Hashtable<String, Object>();
                    map6.put("left", "传真号");
                    map6.put("right", cl_chuanzheniinfo);
                    Log.e("TAG", "chuanzhen======" + cl_chuanzheniinfo);
                    list.add(map6);
                }

                Map<String, Object> map7 = new Hashtable<String, Object>();
                map7.put("left", "企业注册证件");
                map7.put("right", cl_zhengjian_info);
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
                adapter = new SimpleAdapter(FirmInfoActivity.this, list, R.layout.item_firm_info, new String[]{"left", "center", "right", "icon"}, new int[]{R.id.tv_firm_info, R.id.tvTitle_firm_info, R.id.tvValue_firm_info, R.id.ivMore_firm_info});
                lv_firm_info.setAdapter(adapter);
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

    @Override
    protected void onResume() {
        super.onResume();
        sendNet();
    }

    private String qiyeType(String i){
        String str="";
        if(i.equals("1")){
            str="贸易商";
        }else if(i.equals("2")){
            str="工贸一体";
        }else if(i.equals("3")){
            str="生产商";
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

    @Override
    public void setInfoInActivity(FirmInfoUpdateActivityBean firmInfoUpdateActivityBean) {
        address = firmInfoUpdateActivityBean.getU_county();
        firmInfoUpdateActivityBea = firmInfoUpdateActivityBean;
        leixing = cl_leixing.get(firmInfoUpdateActivityBean.getCl_leixing());
        zhengjian = cl_zhengjian.get(firmInfoUpdateActivityBean.getCl_zhengjian());
        nashuiren = cl_nashuiren.get(firmInfoUpdateActivityBean.getCl_nashuiren());
        firmInfoPresenter.setDetailAddress(address.substring(0, 4), address.substring(0, 6), address.toString());
    }

    @Override
    public void setStringInText(String result) {
        Intent intent=new Intent(FirmInfoActivity.this,FirmInfoUpdateActivity.class);
        intent.putExtra("addressnumber",address);
        intent.putExtra("add","修改");
        intent.putExtra("address",result);
        intent.putExtra("leixing",leixing);
        intent.putExtra("zhengjian",zhengjian);
        intent.putExtra("nashuiren",nashuiren);
        intent.putExtra("firmInfoUpdateActivityBean",firmInfoUpdateActivityBea);
        startActivity(intent);
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
//            intent.putExtra("name", "");
//            RegisterValueActivity.this.setResult(RESULT_OK, intent);
            if (laiyuan.equals("我的sumao")){
                FirmInfoActivity.this.finish();
            }else if (laiyuan.equals("注册")){
                Intent intent = new Intent();
                intent.setClass(FirmInfoActivity.this, MainActivity.class);
                intent.putExtra("cart", 3);
                intent.putExtra("name", name);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                GlobalVariable.FLAG = true;
                FirmInfoActivity.this.finish();
            }

        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void initFirmInfoTypeActivityView(DifferentTypes differentTypes) {
//        list.add(names.get(i).getDisplayName());
        value4 = differentTypes;
//        企业类型
        cl_leixing = new HashMap<>();
        for (int i = 0; i< value4.getCl_leixing().size(); i++)
        {
            cl_leixing.put(value4.getCl_leixing().get(i).getId(), value4.getCl_leixing().get(i).getDisplayName());
        }
//        证件类型
        cl_zhengjian = new HashMap<>();
        for (int i = 0; i< value4.getCl_zhengjian().size(); i++)
        {
            cl_zhengjian.put(value4.getCl_zhengjian().get(i).getId(), value4.getCl_zhengjian().get(i).getDisplayName());
        }
//      注册人身份
        cl_applyType = new HashMap<>();
        for (int i = 0; i< value4.getCl_applyType().size(); i++)
        {
            cl_applyType.put(value4.getCl_applyType().get(i).getId(), value4.getCl_applyType().get(i).getDisplayName());
        }
//        纳税人类型
        cl_nashuiren = new HashMap<>();
        for (int i = 0; i < value4.getCl_nashuiren().size(); i++) {
            cl_nashuiren.put(value4.getCl_nashuiren().get(i).getId(), value4.getCl_nashuiren().get(i).getDisplayName());
        }

    }

    @Override
    public void setAddressDisplay(String display, int position) {

    }

    @Override
    public void initReceiptAddressListView(List<ReAddress> address) {
        lists = address;
        LogUtils.log("ReceiptAddress--->lists.size();----->" + lists.size());
        for (int i = 0; i < address.size(); i++) {
            StringBuilder builder = new StringBuilder();
            builder.append(lists.get(i).getProvince());
            builder.append( lists.get(i).getCity());
            builder.append(lists.get(i).getCounty());
        }
        addressDis = new String[lists.size()];
        for (int i = 0; i < lists.size(); i++) {
            LogUtils.log("ReceiptAddress--->addressDisplay.size()----->" + lists.get(i).getAddress());
            addressDisplayPresenter.setDetailAddress(lists.get(i).getProvince(), lists.get(i).getCity(), lists.get(i).getCounty(),i);
        }
    }

    @Override
    public void onReceiptAddressListViewClick(int position) {

    }
}

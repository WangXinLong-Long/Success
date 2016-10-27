package com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.firmInfomation.FirmInfoMVP.view;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.OrderManagement.SpotOrder.TiQu;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.firmInfomation.FirmInfoMVP.presenter.FirmInfoPresenter;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.firmInfomation.FirmInfoPicture.FirmInfoPictureMVP.view.FirmInfoPictureActivity;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.firmInfomation.FirmInfoUpdateMVP.bean.FirmInfoUpdateActivityBean;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.firmInfomation.FirmInfoUpdateMVP.view.FirmInfoUpdateActivity;
import com.silianchuangye.sumao.success.model.DifferentTypes;
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

public class FirmInfoActivity extends AppCompatActivity implements IFirmInfoView {
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firm_info);
        firmInfoPresenter = new FirmInfoPresenter(this);
        firmInfoPresenter.setDataInView();
        firm_info_title = ((TextView) findViewById(R.id.firm_info_title));
//            initList();
        sendNet();
            title_Bar();
        lv_firm_info= (ListView) findViewById(R.id.lvShow_firm_info);

        lv_firm_info.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position==7||position==8){
                    Intent intent=new Intent(FirmInfoActivity.this,FirmInfoPictureActivity.class);
                    startActivity(intent);
                }
            }
        });

    }
    public void initList(){

         //这里判断是新增还是修改，如果新增则没有后面的值
        list=new ArrayList<Map<String,Object>>();
        Map<String,Object> map1=new Hashtable<String,Object>();
        map1.put("center","恭喜你，企业已经注册成功");
        list.add(map1);
        Map<String,Object> map2=new Hashtable<String,Object>();
        map2.put("left","企业类型");
        map2.put("right","贸易商");
        list.add(map2);
        Map<String,Object> map3=new Hashtable<String,Object>();
        map3.put("left","企业名称");
        map3.put("right","四联创业");
        list.add(map3);
        Map<String,Object> map4=new Hashtable<String,Object>();
        map4.put("left","业务部门");
        map4.put("right","京津冀办事处");
        list.add(map4);
        Map<String,Object> map5=new Hashtable<String,Object>();
        map5.put("left","办公地址");
        map5.put("right","1111111111111111");
        list.add(map5);
        Map<String,Object> map6=new Hashtable<String,Object>();
        map6.put("left","传真号");
        map6.put("right","010-88888888");
        list.add(map6);
        Map<String,Object> map7=new Hashtable<String,Object>();
        map7.put("left","企业注册证件");
        map7.put("right","三证合一");
        list.add(map7);
        Map<String,Object> map8=new Hashtable<String,Object>();
        map8.put("left","统一社会信用代码");
        map8.put("right","1111111111");
        map8.put("icon",R.mipmap.my_sumao_iv_order);
        list.add(map8);

        Map<String,Object> map9=new Hashtable<String,Object>();
        map9.put("left","纳税人类型");
        map9.put("right","一般纳税人");
        map9.put("icon",R.mipmap.my_sumao_iv_order);
        list.add(map9);
       adapter=new SimpleAdapter(FirmInfoActivity.this,list,R.layout.item_firm_info,new String[]{"left","center","right","icon"},new int[]{R.id.tv_firm_info,R.id.tvTitle_firm_info,R.id.tvValue_firm_info,R.id.ivMore_firm_info});



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
                FirmInfoActivity.this.finish();
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
        RequestParams params=new RequestParams(SuMaoConstant.SUMAO_IP+"/rest/model/atg/userprofiling/ProfileActor/enterpriseInfo");
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.e("TAG","result====="+result);
//                {"cl_stat":"27","cl_mingcheng":"四联创业深圳分公司","cl_yewu":"销售部1hao ",
// "U_province":"1315","cl_zhengjian":"5","info":"sucess","cl_id":"161","U_city":"131509",
// "U_county":"13150981","cl_dizhi":"倒档到","cl_zhizhao":"91120116724472468Y","cl_leixing":"3"}
//                {"cl_stat":"27","cl_mingcheng":"北京义广达商贸有限公司","cl_corporation":"段绍昌",
// "U_province":"1311","cl_yewu":"市场部","cl_zhengjian":"4","cl_jigou":"74672227-X","cl_id":"1000009",
// "info":"sucess","U_city":"131101","cl_dizhi":"燕房路113号\n","U_county":"13110111","cl_shuiwu":"11019174672227X",
// "cl_zhizhao":"110304005278473","cl_leixing":"1","cl_nashuiren":"一般纳税人"}
                try {
                    JSONObject job=new JSONObject(result);
                    String info=job.getString("info");
                    if(info.equals("fail")){
                        Toast.makeText(FirmInfoActivity.this,"请重新登陆",Toast.LENGTH_SHORT).show();
                        new TiQu(FirmInfoActivity.this).showLogin();
                        finish();
                    }
                    String cl_mingcheng=job.getString("cl_mingcheng");
                    String cl_leixing=qiyeType(job.getString("cl_leixing"));
                    String cl_yewu=job.getString("cl_yewu");
                    String cl_dizhi=job.getString("cl_dizhi");
                    String cl_state = job.getString("cl_stat");
                    String enterpriseInfoReview = job.getString("enterpriseInfoReview");
                    String cl_zhengjian=zhengjianType(job.getString("cl_zhengjian"));
                    String nashuipeople=job.getString("cl_nashuiren");
                    String xinyong;
                    //判断机构
                    if(result.contains("cl_jigou")) {
                        xinyong= job.getString("cl_jigou");
                    }else{
                        xinyong="";
                    }
                    String chuanzhen="";
                    String zhizhao="";
                    String cl_shuiwu="";
                    if(result.contains("cl_chuanzhen")){
                        chuanzhen=job.getString("cl_chuanzhen");
                    }else{
                        chuanzhen="";
                    }

                    if(cl_zhengjian.equals("三证合一")){

                    }else{
                        zhizhao=job.getString("cl_zhizhao");
                        cl_shuiwu=job.getString("cl_shuiwu");
                    }

                    //这里判断是新增还是修改，如果新增则没有后面的值
                    list=new ArrayList<Map<String,Object>>();
                    if (enterpriseInfoReview.equals("35")){
                        firm_info_title.setText("您的企业信息正在重新审核，塑贸网将审核企业新信息，在新信息完成审核前，企业信息将任然采用原信息");
                        tvUpdate.setVisibility(View.GONE);
//                        Map<String,Object> map1=new Hashtable<String,Object>();
//                        map1.put("center","您的企业信息正在重新审核，塑贸网将审核企业新信息，在新信息完成审核前，企业\n" +
//                                "信息将任然采用原信息");
//                        list.add(map1);
                    }else if (cl_state.equals("25")){
                        firm_info_title.setText("您的企业信息正在重新审核，塑贸网将审核企业新信息，在新信息完成审核前，企业信息将任然采用原信息");
//                        Map<String,Object> map1=new Hashtable<String,Object>();
//                        map1.put("center","您的企业信息正在重新审核，塑贸网将审核企业新信息，在新信息完成审核前，企业\n" +
//                                "信息将任然采用原信息");
//                        list.add(map1);
                    }else if (cl_state.equals("26"))
                    {
                        firm_info_title.setText("对不起，您的企业信息未通过审核");
//                        Map<String,Object> map1=new Hashtable<String,Object>();
//                        map1.put("center","对不起，您的企业信息未通过审核");
//                        list.add(map1);
                    }else {
                        firm_info_title.setText("恭喜你，企业已经注册成功");
//                        Map<String,Object> map1=new Hashtable<String,Object>();
//                        map1.put("center","恭喜你，企业已经注册成功");
//                        list.add(map1);
                    }
                    Map<String,Object> map2=new Hashtable<String,Object>();
                    map2.put("left","企业类型");
                    map2.put("right",cl_leixing);
                    list.add(map2);
                    Map<String,Object> map3=new Hashtable<String,Object>();
                    map3.put("left","企业名称");
                    map3.put("right",cl_mingcheng);
                    list.add(map3);
                    Map<String,Object> map4=new Hashtable<String,Object>();
                    map4.put("left","业务部门");
                    map4.put("right",cl_yewu);
                    list.add(map4);
                    Map<String,Object> map5=new Hashtable<String,Object>();
                    map5.put("left","办公地址");
                    map5.put("right",cl_dizhi);
                    list.add(map5);
                    Map<String,Object> map6=new Hashtable<String,Object>();
                    map6.put("left","传真号");
                    map6.put("right",chuanzhen);
                    Log.e("TAG","chuanzhen======"+chuanzhen);
                    if(chuanzhen.equals("")){

                    }else{
                        list.add(map6);
                    }

                    Map<String,Object> map7=new Hashtable<String,Object>();
                    map7.put("left","企业注册证件");
                    map7.put("right",cl_zhengjian);
                    list.add(map7);
                    Map<String,Object>map8=new HashMap<String, Object>();
                    map8.put("left","营业执照号");
                    map8.put("right",zhizhao);
                    map8.put("icon",R.mipmap.my_sumao_iv_order);
                    Map<String,Object> map9=new Hashtable<String,Object>();
                    map9.put("right",xinyong);
                    map9.put("icon",R.mipmap.my_sumao_iv_order);

                    Map<String,Object>map10=new HashMap<String, Object>();
                    map10.put("left","税务登记号");
                    map10.put("right",cl_shuiwu);
                    map10.put("icon",R.mipmap.my_sumao_iv_order);
                    Log.e("TAG","xinyong======"+xinyong);
                    if(cl_zhengjian.equals("三证合一")){
                        map9.put("left","统一社会信用代码");
                    }else{
                        map9.put("left","组织机构代码");
                        list.add(map8);
                        list.add(map10);
                    }
                    if(xinyong.equals("")){

                    }else{
                        list.add(map9);
                    }

                    Map<String,Object> map11=new Hashtable<String,Object>();
                    map11.put("left","纳税人类型");
                    map11.put("right",nashuipeople);
                    map11.put("icon",R.mipmap.my_sumao_iv_order);
                    Log.e("TAG","nashuipeople======"+nashuipeople);
                    if(nashuipeople.equals("")){

                    }else{
                        list.add(map11);
                    }
                    Log.e("TAG","list.size()====="+list.size());
                    adapter=new SimpleAdapter(FirmInfoActivity.this,list,R.layout.item_firm_info,new String[]{"left","center","right","icon"},new int[]{R.id.tv_firm_info,R.id.tvTitle_firm_info,R.id.tvValue_firm_info,R.id.ivMore_firm_info});
                    lv_firm_info.setAdapter(adapter);
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
}

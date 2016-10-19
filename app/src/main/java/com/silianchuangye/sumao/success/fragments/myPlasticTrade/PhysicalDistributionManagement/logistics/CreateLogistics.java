package com.silianchuangye.sumao.success.fragments.myPlasticTrade.PhysicalDistributionManagement.logistics;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.adapter.CreateLogisticsAdapter;
import com.silianchuangye.sumao.success.fragments.bean.Createlogistics_ExpandInfo;
import com.silianchuangye.sumao.success.fragments.bean.Createlogistics_ListInfo;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.OrderManagement.SpotOrder.TiQu;
import com.silianchuangye.sumao.success.utils.SuMaoConstant;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class CreateLogistics extends AppCompatActivity implements View.OnClickListener,CreateLogisticsAdapter.LogisticsCall{
    private ExpandableListView expand_lv_create_logistics;
    private ImageView img_create_logistics_back;
    private ImageView img_create_logistics_search;
    private ImageView img_create_logistics_allselect;
    private Button btn_create_logistics_ok,btn_ok;
    private List<Createlogistics_ExpandInfo> expandList=new ArrayList<Createlogistics_ExpandInfo>();
    private CreateLogisticsAdapter adapter;
    boolean All_Flag;
    private int i;
    boolean flag;
    //popwindow里的控件
    private View popView;
    private ImageView img_pop_back;
    private TextView tv_pop_canle;
    private EditText edt_pop_product_order_num,edt_pop_cangku_name,
                        edt_pop_product_name,edt_pop_company,edt_pop_order_num;
    private TextView tv_pop_start_date,tv_pop_end_date;
    private Button btn_pop_ok;
    private PopupWindow popWindow,pop;
    Createlogistics_ListInfo listInfo;
    String edt_num,tv_changku,startTime;
    private String strjson,strNum,logistics,edtNum,canStr,idCallNum;//idCallNum——用来保存点击子item里edt控件的id
    List<String>list=new ArrayList<String>();
    double sum2;
    JSONArray array;
    private EditText pop_edt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_logistics);
//        initDate();
        initView();
        showNet();
    }

    private void initDate() {
        //给group添加数据
        Createlogistics_ExpandInfo expandInfo1=new Createlogistics_ExpandInfo();
        expandInfo1.company_name="中国石油天然气股份有限公司";
        expandInfo1.date="2016-06-21";
        expandInfo1.order_name="张一";
        expandInfo1.order_num="100000000000";
        Createlogistics_ExpandInfo expandInfo2=new Createlogistics_ExpandInfo();
        expandInfo2.company_name="中国石油天然气股份有限公司";
        expandInfo2.date="2016-06-22";
        expandInfo2.order_name="张二";
        expandInfo2.order_num="120000000000";
        Createlogistics_ExpandInfo expandInfo3=new Createlogistics_ExpandInfo();
        expandInfo3.company_name="中国石油天然气股份有限公司";
        expandInfo3.date="2016-06-23";
        expandInfo3.order_name="张三";
        expandInfo3.order_num="100300000000";
        //给子item添加数据
        Createlogistics_ListInfo listInfo1=new Createlogistics_ListInfo();
        listInfo1.can_num=10+"";
        listInfo1.cangku_name="迅邦物流一号库";
        listInfo1.date="2016-2-2";
        listInfo1.logistics_name="买家自提";
        listInfo1.num=10+"";
        listInfo1.only_price=6100+"";
        listInfo1.product_name="兰州石化7024";
        listInfo1.sort="LLDPE";

        Createlogistics_ListInfo listInfo2=new Createlogistics_ListInfo();
        listInfo2.can_num=10+"";
        listInfo2.cangku_name="迅邦物流一号库";
        listInfo2.date="2016-2-2";
        listInfo2.logistics_name="迅帮配送";
        listInfo2.num=10+"";
        listInfo2.only_price=6100+"";
        listInfo2.product_name="兰州石化7024";
        listInfo2.sort="LLDPE";

        Createlogistics_ListInfo listInfo3=new Createlogistics_ListInfo();
        listInfo3.can_num=10+"";
        listInfo3.cangku_name="迅邦物流一号库";
        listInfo3.date="2016-2-2";
        listInfo3.logistics_name="卖方配送";
        listInfo3.num=1+"";
        listInfo3.only_price=6100+"";
        listInfo3.product_name="兰州石化7024";
        listInfo3.sort="LLDPE";
        Createlogistics_ListInfo listInfo4=new Createlogistics_ListInfo();
        listInfo4.can_num=10+"";
        listInfo4.cangku_name="迅邦物流一号库";
        listInfo4.date="2016-2-2";
        listInfo4.logistics_name="卖方配送";
        listInfo4.num="10";
        listInfo4.only_price=6100+"";
        listInfo4.product_name="兰州石化7024";
        listInfo4.sort="LLDPE";

        Createlogistics_ListInfo listInfo5=new Createlogistics_ListInfo();
        listInfo5.can_num=10+"";
        listInfo5.cangku_name="迅邦物流二号库";
        listInfo5.date="2016-2-2";
        listInfo5.logistics_name="卖方配送";
        listInfo5.num="10";
        listInfo5.only_price=6100+"";
        listInfo5.product_name="兰州石化7024";
        listInfo5.sort="LLDPE";
        //先把子item的数据添加到group里的list容器中
        expandInfo1.list.add(listInfo1);

        expandInfo2.list.add(listInfo2);
        expandInfo2.list.add(listInfo3);
        expandInfo2.list.add(listInfo4);

        expandInfo3.list.add(listInfo5);
        //把组中的数据添加到expandList中
        expandList.add(expandInfo1);
        expandList.add(expandInfo2);
        expandList.add(expandInfo3);

    }

    private void initView() {
        img_create_logistics_back= (ImageView) findViewById(R.id.img_create_logistics_back);
        img_create_logistics_search= (ImageView) findViewById(R.id.img_create_logistics_search);
        img_create_logistics_allselect= (ImageView) findViewById(R.id.img_create_logistics_allselsect);
        btn_create_logistics_ok= (Button) findViewById(R.id.btn_create_logistics_ok);
        expand_lv_create_logistics= (ExpandableListView) findViewById(R.id.create_logistics_expand_lv);
        //去掉expandListview的特别的下拉标志
        expand_lv_create_logistics.setGroupIndicator(null);
        //去掉ListView之间的线
        expand_lv_create_logistics.setDivider(null);

//        adapter=new CreateLogisticsAdapter(this,expandList,this);
//        expand_lv_create_logistics.setAdapter(adapter);
//        if(adapter!=null && expandList!=null){
//            for (int i = 0; i < adapter.getGroupCount(); i++) {
//                expand_lv_create_logistics.expandGroup(i);
//            }}
        img_create_logistics_back.setOnClickListener(this);
        img_create_logistics_search.setOnClickListener(this);
        img_create_logistics_allselect.setOnClickListener(this);
        btn_create_logistics_ok.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_create_logistics_back:
                finish();
                break;
            case R.id.img_create_logistics_search:
                Toast.makeText(this,"显示pop",Toast.LENGTH_SHORT).show();
                backgroundAlpha(0.5f);
                showPopWindow();

                break;
            case R.id.img_create_logistics_allselsect:
                break;
            case R.id.btn_create_logistics_ok:
                int count=0,NumCount=0;
                double edtNumShu=0;
                for(int i=0;i<expandList.size();i++){
                    int k= expandList.get(i).list.size();
                    for(int j=0;j<k;j++){
                        if(!expandList.get(i).list.get(j).SelectFlag) {
                            count++;
                        }else{
                            edtNumShu=Double.valueOf(expandList.get(i).list.get(j).edt_num);
                            if(edtNumShu<=0){
                                NumCount++;
                            }
                        }
                    }
                }
                int sum=0;
                for(int i=0;i<expandList.size();i++){
                    int all= expandList.get(i).list.size();
                    sum+=all;
                }
                if(count==sum) {
                    Toast.makeText(this,"最少选中其中一条",Toast.LENGTH_SHORT).show();
                }else if(NumCount>0){
                    Toast.makeText(this,"本次购买数量不能为0",Toast.LENGTH_SHORT).show();
                }else{
                    ArrayList<LogisticsJson> persons = new ArrayList<LogisticsJson>();
                    for(int i=0;i<expandList.size();i++){
                        for(int j=0;j<expandList.get(i).list.size();j++){
                            if(expandList.get(i).list.get(j).SelectFlag){
                                adapter.notifyDataSetChanged();
                                logistics=expandList.get(i).list.get(j).logistics_name;
                                array= new JSONArray();
                                persons.add(new LogisticsJson(expandList.get(i).list.get(j).id,expandList.get(i).list.get(j).edt_num));//填充Java实体类集合
                                // Json格式的数组形式
                                JSONObject obj;//json格式的单个对象形式
                                Log.e("TAG","persons"+persons.size());
                                for(int k=0;k<persons.size();k++) {
                                    obj = new JSONObject();
                                    try {
                                        obj.put("commerItemId", persons.get(k).commerItemId);//json通过put方式以key-value形式填充
                                        obj.put("shipmentsQuantity", persons.get(k).shipmentsQuantity);
                                        if(k==persons.size()-1){
                                            sum2+=Double.valueOf(persons.get(k).shipmentsQuantity);
                                        }
                                        array.put(obj);//将JSONObject添加入JSONArray
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }

                            }
                        }
                    Toast.makeText(this, "创建物流需求", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(this, CreateLogisticsNeed.class);
                    intent.putExtra("num", sum2);//本次发货数量总和
                    intent.putExtra("cangku", tv_changku);//仓库
                    intent.putExtra("date", startTime);//交货开始时间
                    intent.putExtra("logistic", logistics);
                    intent.putExtra("list", array.toString());
                    startActivity(intent);
                }
                break;
            case R.id.img_logistics_title_bar_back:
                popWindow.dismiss();
                break;
            case R.id.tv_logistics_title_bar:
                popWindow.dismiss();
                break;
            case R.id.btn_pop_ok:
                Toast.makeText(this,"确认按钮",Toast.LENGTH_SHORT).show();
                popWindow.dismiss();
                break;
            case R.id.tv_pop_logistics_start_date:
                showDate(tv_pop_start_date);
                break;
            case R.id.tv_pop_logistics_end_date:
                showDate(tv_pop_end_date);
                break;
            case R.id.btn_canle:
                pop.dismiss();
                backgroundAlpha(1f);
                break;
            case R.id.btn_ok:
                if(pop_edt.getText().toString().equals("")){
                    Toast.makeText(CreateLogistics.this,"购买数量不能为空",Toast.LENGTH_SHORT).show();
                }else {
                    double canNum=Double.valueOf(canStr);
                    double num=Double.valueOf(pop_edt.getText().toString());
                    if(num<canNum){
                          for(int i=0;i<expandList.size();i++) {
                              for (int j = 0; j < expandList.get(i).list.size(); j++) {
                                  if (idCallNum.equals(expandList.get(i).list.get(j).id)) {
                                      expandList.get(i).list.get(j).edt_num = pop_edt.getText().toString();
                                  }
                              }
                          }
                        adapter.notifyDataSetChanged();
                        pop.dismiss();
                        backgroundAlpha(1f);
                    }
                    else {
                        Toast.makeText(CreateLogistics.this, "本次购买数量不能大于可发货数量", Toast.LENGTH_SHORT).show();
                    }

                }
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(adapter!=null) {
            sum2=0;
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void call(int groupPosition, int childPosition, String num,RelativeLayout view,RelativeLayout childView) {
        expandList.get(groupPosition).list.get(childPosition).SelectFlag = !expandList.get(groupPosition).list.get(childPosition).SelectFlag;
        edt_num=num;
        if(expandList.get(groupPosition).list.get(childPosition).SelectFlag){
            tv_changku=expandList.get(groupPosition).list.get(childPosition).cangku_name;
            String date=expandList.get(groupPosition).list.get(childPosition).date;
            strNum=expandList.get(groupPosition).list.get(childPosition).can_num;
            startTime=date.substring(0,10);
        }
        adapter.notifyDataSetChanged();

    }
    //修改数量的回调
    @Override
    public void callNum(final int groupPosition, final int childPosotion, final String can_num){
         canStr=can_num.substring(0,can_num.length());
        final View popview=View.inflate(this,R.layout.pop_num,null);
        int w=getWindowManager().getDefaultDisplay().getWidth();
        popview.measure(0,0);
        pop=new PopupWindow(popview,500,popview.getMeasuredHeight());
        pop_edt= (EditText) popview.findViewById(R.id.edt);
        final Button btn_canle= (Button) popview.findViewById(R.id.btn_canle);
         btn_ok= (Button) popview.findViewById(R.id.btn_ok);
        btn_canle.setOnClickListener(this);
//        pop_edt.setText(str);
        pop.setBackgroundDrawable(new BitmapDrawable());
        pop.setFocusable(true);
        pop.showAtLocation(btn_create_logistics_ok, Gravity.CENTER,0,0);
        backgroundAlpha(0.5f);
        idCallNum=expandList.get(groupPosition).list.get(childPosotion).id;
        btn_ok.setOnClickListener(CreateLogistics.this);

//        pop_edt.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//
//            }
//
//            @Override
//            public void afterTextChanged(final Editable s) {
//                for(int i=0;i<expandList.size();i++){
//                    for(int j=0;j<expandList.get(i).list.size();j++){
//                        if(expandList.get(groupPosition).list.get(childPosotion).id.equals(expandList.get(i).list.get(j).id)){
//                            if(!s.toString().equals("")){
//                                    strCallNum=s.toString();
////                                    pop.dismiss();
////                                    backgroundAlpha(1f);
////                                    adapter.notifyDataSetChanged();
//
//                            }else if(s.toString().equals("")){
//                                Log.e("TAG","数量为空");
//                                Toast.makeText(CreateLogistics.this,"购买数量不能为空",Toast.LENGTH_SHORT).show();
//                            }
//                        }
//                    }
//                }
//            }
//        });
        pop.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                backgroundAlpha(1f);
            }
        });
    }

    @Override
    public void callVisiable(int groupPosition, int childPosition) {
        expandList.get(groupPosition).list.get(childPosition).visiableFlag = !expandList.get(groupPosition).list.get(childPosition).visiableFlag;
        adapter.notifyDataSetChanged();
    }

    //点击搜索按钮弹出popwindow
    private void showPopWindow(){
        popView=View.inflate(this,R.layout.popwindow_logistics,null);
        img_pop_back= (ImageView) popView.findViewById(R.id.img_logistics_title_bar_back);
        tv_pop_canle= (TextView) popView.findViewById(R.id.tv_logistics_title_bar);
        edt_pop_product_order_num= (EditText) popView.findViewById(R.id.edt_pop_logistics_product_order_num);
        edt_pop_cangku_name= (EditText) popView.findViewById(R.id.edt_pop_logistics_cangku);
        edt_pop_product_name= (EditText) popView.findViewById(R.id.edt_pop_logistics_product_name);
        edt_pop_company= (EditText) popView.findViewById(R.id.edt_pop_logistics_company);
        edt_pop_order_num= (EditText) popView.findViewById(R.id.edt_pop_logistics_order_num);
        tv_pop_start_date= (TextView) popView.findViewById(R.id.tv_pop_logistics_start_date);
        tv_pop_end_date= (TextView) popView.findViewById(R.id.tv_pop_logistics_end_date);
        btn_pop_ok= (Button) popView.findViewById(R.id.btn_pop_ok);

        img_pop_back.setOnClickListener(this);
        tv_pop_canle.setOnClickListener(this);
        btn_pop_ok.setOnClickListener(this);
        tv_pop_start_date.setOnClickListener(this);
        tv_pop_end_date.setOnClickListener(this);

        popView.measure(0,0);
        int w=getWindowManager().getDefaultDisplay().getWidth();
        popWindow=new PopupWindow(popView,w,popView.getMeasuredHeight());
        popWindow.setBackgroundDrawable(new BitmapDrawable());
        popWindow.setFocusable(true);
        popWindow.showAtLocation(img_create_logistics_allselect, Gravity.TOP,0,55);
        popWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                backgroundAlpha(1f);
            }
        });
    }
    public void backgroundAlpha(float bgAlpha){
        WindowManager.LayoutParams lp = CreateLogistics.this.getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        CreateLogistics.this.getWindow().setAttributes(lp);
    }
    private void showDate(final TextView Tv){
        Calendar calend1 = Calendar.getInstance();
        calend1.setTimeInMillis(System.currentTimeMillis());
        int year = calend1.get(Calendar.YEAR);
        int month = calend1.get(Calendar.MONTH) + 1;
        int day = calend1.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog dialog3 = new DatePickerDialog(
                CreateLogistics.this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view,
                                          int year, int monthOfYear,
                                          int dayOfMonth) {
                        Toast.makeText(CreateLogistics.this,
                                "" + year + "年" + (monthOfYear + 1)
                                        + "月" + dayOfMonth + "日", Toast.LENGTH_LONG).show();
                        Tv.setText(year + "年" + (monthOfYear + 1)
                                + "月" + dayOfMonth + "日");
                    }
                }, year, month, day);
        dialog3.show();
    }
    private void showNet(){
        RequestParams rp=new RequestParams(SuMaoConstant.SUMAO_IP+"/rest/model/atg/commerce/order/logistics/logisticsActor/logisticsInfo");
        x.http().post(rp, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.e("TAG", "result------" + result);
                if (result.contains("order")) {
                    try {
                        JSONObject j = new JSONObject(result);
                        String order = j.getString("order");
                        JSONArray ja = new JSONArray(order);

                        for (int i = 0; i < ja.length(); i++) {
                            Createlogistics_ExpandInfo expandInfo1 = new Createlogistics_ExpandInfo();
                            JSONObject job = (JSONObject) ja.get(i);
                            //给group添加数据
                            expandInfo1.order_name = job.getString("firstName");
                            if (job.toString().contains("cl_gongsi")) {
                                expandInfo1.company_name = job.getString("cl_gongsi");
                            } else {
                                expandInfo1.company_name = "";
                            }
                            expandInfo1.date = new SimpleDateFormat("yyyy-MM-dd").format(Long.valueOf(job.getString("submittedDate")));
                            expandInfo1.order_num = job.getString("id");
                            //给子item添加
                            listInfo = new Createlogistics_ListInfo();
                            String cl = job.getString("cl");
                            JSONArray jay = new JSONArray(cl);
                            for (int z = 0; z < jay.length(); z++) {
                                JSONObject childJob = (JSONObject) jay.get(z);
                                listInfo.id = childJob.getString("commerceId");
                                if (cl.contains("cl_fenlei")) {
                                    listInfo.sort = childJob.getString("cl_fenlei");
                                } else {
                                    listInfo.sort = "";
                                }
                                if (cl.contains("cl_mingcheng")) {
                                    listInfo.product_name = childJob.getString("cl_mingcheng");
                                } else {
                                    listInfo.product_name = "";
                                }
                                if (cl.contains("cl_shuliang")) {
                                    listInfo.num = childJob.getString("cl_shuliang");
                                } else {
                                    listInfo.num = "";
                                }
                                if (cl.contains("shippingMethod")) {
                                    listInfo.logistics_name = childJob.getString("shippingMethod");
                                } else {
                                    listInfo.logistics_name = "";
                                }
                                listInfo.only_price = childJob.getString("cl_jine");
                                if (cl.contains("vailableQuantity")) {
                                    listInfo.can_num = childJob.getString("vailableQuantity");
                                } else {
                                    listInfo.can_num = "";
                                }
                                if (cl.contains("cl_cangku")) {
                                    listInfo.cangku_name = childJob.getString("cl_cangku");
                                } else {
                                    listInfo.cangku_name = "";
                                }
                                if (cl.contains("deliveryEndDate")) {
                                    listInfo.date = new SimpleDateFormat("yyyy-MM-dd").format(Long.valueOf(childJob.getString("deliveryStartDate"))) + "    " +
                                            new SimpleDateFormat("yyyy-MM-dd").format(Long.valueOf(childJob.getString("deliveryEndDate")));
                                } else {
                                    listInfo.date = "";
                                }
                                if (cl.contains("cl_gongsi")) {
                                    listInfo.gongsi = job.getString("cl_gongsi");
                                } else {
                                    listInfo.gongsi = "";
                                }
                                listInfo.edt_num = 0 + "";
                                expandInfo1.list.add(listInfo);
                            }
                            expandList.add(expandInfo1);
                        }
                        adapter = new CreateLogisticsAdapter(CreateLogistics.this, expandList, CreateLogistics.this);
                        expand_lv_create_logistics.setAdapter(adapter);
                        if (adapter != null && expandList != null) {
                            for (int i = 0; i < adapter.getGroupCount(); i++) {
                                expand_lv_create_logistics.expandGroup(i);
                            }
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }else{
                    Toast.makeText(CreateLogistics.this,"请重新登陆",Toast.LENGTH_SHORT).show();
                    new TiQu(CreateLogistics.this).showLogin();
                    CreateLogistics.this.finish();
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.e("TAG","ex-----"+ex);
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

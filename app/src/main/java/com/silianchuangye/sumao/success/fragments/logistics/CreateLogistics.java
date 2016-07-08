package com.silianchuangye.sumao.success.fragments.logistics;

import android.app.DatePickerDialog;
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
import android.widget.TextView;
import android.widget.Toast;

import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.adapter.CreateLogisticsAdapter;
import com.silianchuangye.sumao.success.fragments.bean.Createlogistics_ExpandInfo;
import com.silianchuangye.sumao.success.fragments.bean.Createlogistics_ListInfo;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class CreateLogistics extends AppCompatActivity implements View.OnClickListener,CreateLogisticsAdapter.LogisticsCall{
private ExpandableListView expand_lv_create_logistics;
    private ImageView img_create_logistics_back;
    private ImageView img_create_logistics_search;
    private ImageView img_create_logistics_allselect;
    private Button btn_create_logistics_ok;
    private List<Createlogistics_ExpandInfo> expandList=new ArrayList<Createlogistics_ExpandInfo>();
    private CreateLogisticsAdapter adapter;
    boolean All_Flag;
    private int i;

    //popwindow里的控件
    private View popView;
    private ImageView img_pop_back;
    private TextView tv_pop_canle;
    private EditText edt_pop_product_order_num,edt_pop_cangku_name,
                        edt_pop_product_name,edt_pop_company,edt_pop_order_num;
    private TextView tv_pop_start_date,tv_pop_end_date;
    private Button btn_pop_ok;
    private PopupWindow popWindow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_logistics);
        initDate();
        initView();
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
        listInfo1.can_num=10;
        listInfo1.cangku_name="迅邦物流一号库";
        listInfo1.date="2016-2-2";
        listInfo1.logistics_name="买家自提";
        listInfo1.num=10;
        listInfo1.only_price=6100;
        listInfo1.product_name="兰州石化7024";
        listInfo1.sort="LLDPE";

        Createlogistics_ListInfo listInfo2=new Createlogistics_ListInfo();
        listInfo2.can_num=10;
        listInfo2.cangku_name="迅邦物流一号库";
        listInfo2.date="2016-2-2";
        listInfo2.logistics_name="迅帮配送";
        listInfo2.num=10;
        listInfo2.only_price=6100;
        listInfo2.product_name="兰州石化7024";
        listInfo2.sort="LLDPE";

        Createlogistics_ListInfo listInfo3=new Createlogistics_ListInfo();
        listInfo3.can_num=10;
        listInfo3.cangku_name="迅邦物流一号库";
        listInfo3.date="2016-2-2";
        listInfo3.logistics_name="卖方配送";
        listInfo3.num=10;
        listInfo3.only_price=6100;
        listInfo3.product_name="兰州石化7024";
        listInfo3.sort="LLDPE";
        Createlogistics_ListInfo listInfo4=new Createlogistics_ListInfo();
        listInfo4.can_num=10;
        listInfo4.cangku_name="迅邦物流一号库";
        listInfo4.date="2016-2-2";
        listInfo4.logistics_name="卖方配送";
        listInfo4.num=10;
        listInfo4.only_price=6100;
        listInfo4.product_name="兰州石化7024";
        listInfo4.sort="LLDPE";
        //先把子item的数据添加到group里的list容器中
        expandInfo1.list.add(listInfo1);

        expandInfo2.list.add(listInfo2);
        expandInfo2.list.add(listInfo3);
        expandInfo2.list.add(listInfo4);

        //把组中的数据添加到expandList中
        expandList.add(expandInfo1);
        expandList.add(expandInfo2);
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

        adapter=new CreateLogisticsAdapter(this,expandList,this);
        expand_lv_create_logistics.setAdapter(adapter);
        if(adapter!=null && expandList!=null){
            for (int i = 0; i < adapter.getGroupCount(); i++) {
                expand_lv_create_logistics.expandGroup(i);
            }}
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
                if(!All_Flag){
                    img_create_logistics_allselect.setImageResource(R.mipmap.cart_select);
                        for(int i=0;i<expandList.size();i++){
                           int k= expandList.get(i).list.size();
                            for(int j=0;j<k;j++){
                                expandList.get(i).list.get(j).SelectFlag=true;
                            }
                        }
                    adapter.notifyDataSetChanged();
                }else{
                    img_create_logistics_allselect.setImageResource(R.mipmap.cart_select_null);
                    for(int i=0;i<expandList.size();i++){
                        int k= expandList.get(i).list.size();
                        for(int j=0;j<k;j++){
                            expandList.get(i).list.get(j).SelectFlag=false;
                        }
                    }
                    adapter.notifyDataSetChanged();
                }
                All_Flag=!All_Flag;
                Toast.makeText(this,"全选",Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_create_logistics_ok:
                for(int i=0;i<expandList.size();i++){
                    int k= expandList.get(i).list.size();
                    for(int j=0;j<k;j++){
                        if(!expandList.get(i).list.get(j).SelectFlag){
                            Toast.makeText(this,"最少选中其中一条",Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                Toast.makeText(this,"创建物流需求",Toast.LENGTH_SHORT).show();
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
        }
    }

    @Override
    public void call(int groupPosition,int childPosition) {
        expandList.get(groupPosition).list.get(childPosition).SelectFlag=!expandList.get(groupPosition).list.get(childPosition).SelectFlag;
        adapter.notifyDataSetChanged();
        int count=0;
    //如果子item全选后，全选按钮变为选中
        for(int i=0;i<expandList.size();i++){
            int k= expandList.get(i).list.size();
            for(int j=0;j<k;j++){
                if(expandList.get(i).list.get(j).SelectFlag) {
                    count++;
                }
            }
        }
        int sum=0;
        for(int i=0;i<expandList.size();i++){
            int all= expandList.get(i).list.size();
            sum+=all;
        }
        if(count==sum) {
            img_create_logistics_allselect.setImageResource(R.mipmap.cart_select);
        }else{
            img_create_logistics_allselect.setImageResource(R.mipmap.cart_select_null);
        }
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
    public void backgroundAlpha(float bgAlpha)
    {
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
}

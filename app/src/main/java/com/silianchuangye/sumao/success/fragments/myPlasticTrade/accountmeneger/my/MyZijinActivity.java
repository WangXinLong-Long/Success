package com.silianchuangye.sumao.success.fragments.myPlasticTrade.accountmeneger.my;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.accountmeneger.open.BankwebActivity;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.firmInfomation.decoding.Intents;
import com.silianchuangye.sumao.success.utils.SuMaoConstant;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

public class MyZijinActivity extends AppCompatActivity {
    private TextView tv_child_title_bar_title;
    private ImageView iv_child_title_bar_back;
    private TextView tvZijin;
    private TextView tvzhanghuyve;
    private TextView tvdongjie;
    //    private ListView lvInfoBank;
//    private List<Map<String,Object>> list;
    private String info = "";
    int j = 0;
    private double totalmoery = 0;
    private double dongjie = 0;
    private double usermoery = 0;
    private LinearLayout layout_1;
    private ImageView bank_icon;
    private TextView tvChujin;
    private TextView kyjine;
    private TextView tvDJ;
    private TextView tvZC;
    private LinearLayout layout_2;
    private ImageView bank_icon_two;
    private TextView tvChujin_two;
    private TextView kyjine_two;
    private TextView tvDJ_two;
    private TextView tvZC_two;
    private LinearLayout layout_3;
    private ImageView bank_icon_three;
    private TextView tvChujin_three;
    private TextView kyjine_three;
    private TextView tvDJ_three;
    private TextView tvZC_three;
    private double zijin1=0;
    private double userzijin1=0;
    private double dongjiezijin1=0;
    private double zijin2=0;
    private double userzijin2=0;
    private double dongjiezijin2=0;
    private double zijin3=0;
    private double userzijin3=0;
    private double dongjiezijin3=0;
    private String keyong="";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_zijin);

        init();
        getinfo("1");
        getinfo("2");
        getinfo("3");
         //addData();


    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("onstart","onstart");

    }

    public void init() {
        layout_3= (LinearLayout) findViewById(R.id.layout_3);
        bank_icon_three= (ImageView) findViewById(R.id.bank_icon_three);
        tvChujin_three= (TextView) findViewById(R.id.tvChujin_three);
        kyjine_three= (TextView) findViewById(R.id.kyjine_three);
        tvDJ_three= (TextView) findViewById(R.id.tvDJ_three);
        tvZC_three= (TextView) findViewById(R.id.tvZC_three);
        layout_2= (LinearLayout) findViewById(R.id.layout_2);
        bank_icon_two= (ImageView) findViewById(R.id.bank_icon_two);
        tvChujin_two= (TextView) findViewById(R.id.tvChujin_two);
        kyjine_two= (TextView) findViewById(R.id.kyjine_two);
        tvDJ_two= (TextView) findViewById(R.id.tvDJ_two);
        tvZC_two= (TextView) findViewById(R.id.tvZC_two);
        layout_1 = (LinearLayout) findViewById(R.id.layout_1);
        bank_icon = (ImageView) findViewById(R.id.bank_icon);
        tvChujin = (TextView) findViewById(R.id.tvChujin);
        kyjine = (TextView) findViewById(R.id.kyjine);
        tvDJ = (TextView) findViewById(R.id.tvDJ);
        tvZC = (TextView) findViewById(R.id.tvZC);

        tvZijin = (TextView) findViewById(R.id.tvZijin);
        tvzhanghuyve = (TextView) findViewById(R.id.tvzhanghuyve);
        tvdongjie = (TextView) findViewById(R.id.tvdongjie);
        tv_child_title_bar_title = (TextView) findViewById(R.id.tv_child_title_bar_title);
        tv_child_title_bar_title.setText("我的资金账户");
        iv_child_title_bar_back = (ImageView) findViewById(R.id.iv_child_title_bar_back);
        iv_child_title_bar_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
//        lvInfoBank= (ListView) findViewById(R.id.lvInfoBank);
//        list=new ArrayList<>();
        tvChujin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //平安出金
                Intent intent=new Intent(MyZijinActivity.this,Chujin.class);
//                intent.putExtra("zhanghu","");
//                intent.putExtra("yinhang","");
//                intent.putExtra("")
                intent.putExtra("ky",keyong);
                startActivity(intent);
            }
        });
        tvChujin_two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MyZijinActivity.this,BankwebActivity.class);
                intent.putExtra("url","http://www.klb.cn/");
                intent.putExtra("name","银行出金");
                startActivity(intent);
            }
        });
        tvChujin_three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MyZijinActivity.this,BankwebActivity.class);
                intent.putExtra("url","http://www.ccb.com");
                intent.putExtra("name","银行出金");
                startActivity(intent);
            }
        });



    }

    public void getinfo(final String position) {


        String url = SuMaoConstant.SUMAO_IP + "/rest/model/atg/commerce/order/myorder/OrderPaymentInfo/myaccountListBank";
        RequestParams rp = new RequestParams(url);
        rp.addParameter("bankId", position);
        Log.d("银行的信息", rp + "");
        x.http().post(rp, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.d("银行列表i", result);
                if (position.equals("1")) {
                    if (result.contains("useAmount")) {
                        try {
                            JSONObject jsonObject = new JSONObject(result);
                            String frezeAmount = jsonObject.getString("frezeAmount");
                            String totalAmount = jsonObject.getString("totalAmount");
                             String useAmount = jsonObject.getString("useAmount");
                            keyong=useAmount;
//                            zijin1=Double.parseDouble(totalAmount);
//                            userzijin1=Double.parseDouble(useAmount);
//                            dongjiezijin1=Double.parseDouble(frezeAmount);
                            SharedPreferences sp=getSharedPreferences("sumao", Activity.MODE_PRIVATE);
                            SharedPreferences.Editor editor=sp.edit();
                           // editor.putDouble("zijin1",zijin1);
                            editor.putString("zijin1",totalAmount);
                            editor.putString("userzijin1",useAmount);
                            editor.putString("dongjiezijin1",frezeAmount);
                            editor.commit();

                        //    bank_icon.setImageBitmap(getResources().getDrawable(R.mipmap.pingan_bank).);
                            bank_icon.setImageDrawable(getResources().getDrawable(R.mipmap.pingan_bank));
                            tvChujin.setText("出金");
                            kyjine.setText(useAmount);
                            tvDJ.setText(frezeAmount);
                            tvZC.setText(totalAmount);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    } else {
                        layout_1.setVisibility(View.GONE);
                    }
                }else if (position.equals("3")){
                    if (result.contains("useAmount")) {
                        try {
                            JSONObject jsonObject = new JSONObject(result);
                            String frezeAmount = jsonObject.getString("frezeAmount");
                            String totalAmount = jsonObject.getString("totalAmount");
                            String useAmount = jsonObject.getString("useAmount");
                            //    bank_icon.setImageBitmap(getResources().getDrawable(R.mipmap.pingan_bank).);
                            bank_icon_three.setImageDrawable(getResources().getDrawable(R.mipmap.jianshe_bank));
                            tvChujin_three.setText("网络出金");
//                            zijin3=Double.parseDouble(totalAmount);
//                            userzijin3=Double.parseDouble(useAmount);
//                            dongjiezijin3=Double.parseDouble(frezeAmount);
                            SharedPreferences sp=getSharedPreferences("sumao", Activity.MODE_PRIVATE);
                            SharedPreferences.Editor editor=sp.edit();
                            // editor.putDouble("zijin1",zijin1);
                            editor.putString("zijin3",totalAmount);
                            editor.putString("userzijin3",useAmount);
                            editor.putString("dongjiezijin3",frezeAmount);
                            editor.commit();
                            kyjine_three.setText(useAmount);
                            tvDJ_three.setText(frezeAmount);
                            tvZC_three.setText(totalAmount);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }else{
                        layout_3.setVisibility(View.GONE);

                    }
                }else if (position.equals("2")){
                    if (result.contains("useAmount")) {
                        try {
                            JSONObject jsonObject = new JSONObject(result);
                            String frezeAmount = jsonObject.getString("frezeAmount");
                            String totalAmount = jsonObject.getString("totalAmount");
                            String useAmount = jsonObject.getString("useAmount");
                            //    bank_icon.setImageBitmap(getResources().getDrawable(R.mipmap.pingan_bank).);
                            bank_icon_two.setImageDrawable(getResources().getDrawable(R.mipmap.kunlun_bank));
                            tvChujin_two.setText("网络出金");
//                            zijin2=Double.parseDouble(totalAmount);
//                            userzijin2=Double.parseDouble(useAmount);
//                            dongjiezijin2=Double.parseDouble(frezeAmount);
                            SharedPreferences sp=getSharedPreferences("sumao", Activity.MODE_PRIVATE);
                            SharedPreferences.Editor editor=sp.edit();
                            // editor.putDouble("zijin1",zijin1);
                            editor.putString("zijin2",totalAmount);
                            editor.putString("userzijin2",useAmount);
                            editor.putString("dongjiezijin2",frezeAmount);
                            editor.commit();
                            kyjine_two.setText(useAmount);
                            tvDJ_two.setText(frezeAmount);
                            tvZC_two.setText(totalAmount);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }else{
                        layout_2.setVisibility(View.GONE);

                    }

                }
                if (layout_1.getVisibility()==View.GONE&&layout_2.getVisibility()==View.GONE&&layout_3.getVisibility()==View.GONE){

                }else{
//Log.d("info",zijin1+zijin2+zijin3+)
//            tvZijin.setText((zijin1+zijin2+zijin3)+"");
//            tvzhanghuyve.setText((userzijin1+userzijin2+userzijin3)+"");
//            tvdongjie.setText((dongjiezijin1+dongjiezijin2+dongjiezijin3)+"");
                    SharedPreferences sp=getSharedPreferences("sumao",Activity.MODE_PRIVATE);
                    String keyong1=sp.getString("userzijin1","");
                    String keyong2=sp.getString("userzijin2","");
                    String keyong3=sp.getString("userzijin3","");
                    tvzhanghuyve.setText((Float.parseFloat(keyong1)+Float.parseFloat(keyong2)+Float.parseFloat(keyong3))+"");
                    String yigong=sp.getString("zijin1","");
                    String yigong2=sp.getString("zijin2","");
                    String yigong3=sp.getString("zijin3","");
                    tvZijin.setText((Float.parseFloat(yigong)+Float.parseFloat(yigong2)+Float.parseFloat(yigong3))+"");
                    String dongjie=sp.getString("dongjiezijin1","");
                    String dongjie2=sp.getString("dongjiezijin2","");
                    String dongjie3=sp.getString("dongjiezijin3","");
                    tvdongjie.setText((Float.parseFloat(dongjie)+Float.parseFloat(dongjie2)+Float.parseFloat(dongjie3))+"");

                }
                //  Log.d("info的ue值", info);

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

        // }
    }

   // http:172.22.15.21:7003/rest/model/atg/commerce/order/myorder/OrderPaymentInfo/myaccountListPingAnBank?amount=2
}

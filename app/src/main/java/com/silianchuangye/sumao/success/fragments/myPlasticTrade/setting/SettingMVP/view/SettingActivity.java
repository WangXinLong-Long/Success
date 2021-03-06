package com.silianchuangye.sumao.success.fragments.myPlasticTrade.setting.SettingMVP.view;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.silianchuangye.sumao.success.MainActivity;
import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.setting.FunctionIntroduction.FunctionIntroductionMVP.view.FunctionIntroductionActivity;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.setting.HelpAndFeedbackMVP.view.HelpAndFeedbackActivity;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.setting.ScoreMVP.view.ScoreActivity;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.setting.SettingMVP.bean.FunctionIntroductionBean;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.setting.SettingMVP.presenter.SettingPresenter;
import com.silianchuangye.sumao.success.utils.SuMaoConstant;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

public class SettingActivity extends AppCompatActivity implements ISettingView{
    ImageView iv_title_bar_logo,
            iv_title_bar_back,
            iv_title_bar_service,
            add_address,
            iv_title_bar_search;
    Button sv_title_bar_serachView;
    TextView tv_title_bar_title,tv,tvUpdate;
    RelativeLayout layoutTop,add_address_rl;
    private RelativeLayout layouthelp;
    private RelativeLayout layoutgrade;
    private RelativeLayout layoutintroduction;
    private TextView tvVersion;
    private Button btEdit;
    public final static String DATA_URL = "/data/data/";
    public final static String SHARED_MAIN_XML = "sumao.xml";
    private SettingPresenter settingPresenter;
    private TextView tv_version_setting_value;
    private int versionCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        settingPresenter = new SettingPresenter(this);
        title_Bar();
        layouthelp= (RelativeLayout) findViewById(R.id.layout_help_setting);
        layoutgrade= (RelativeLayout) findViewById(R.id.layout_grade_setting);
        layoutintroduction= (RelativeLayout) findViewById(R.id.layout_introduction_setting);
        tvVersion= (TextView) findViewById(R.id.tv_version_setting);
//        当前的版本号
        tv_version_setting_value = ((TextView) findViewById(R.id.tv_version_setting_value));
        try {
            PackageInfo pi = getPackageManager().getPackageInfo(getPackageName(),0);
            versionCode = pi.versionCode;
            Log.d("值","sssss"+versionCode);

            //tv_version_setting_value.setText(versionCode);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        btEdit= (Button) findViewById(R.id.btEdit);
        layoutintroduction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                settingPresenter.getFunctionIntroduction();

            }
        });
        layouthelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//Help and feedback
                Intent intent = new Intent(SettingActivity.this,HelpAndFeedbackActivity.class);
//                intent.putExtra("FunctionIntroductionBean",functionIntroductionBean);
                startActivity(intent);
            }
        });
        layoutgrade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SettingActivity.this,ScoreActivity.class);
                startActivity(intent);
            }
        });
        btEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder alert=new AlertDialog.Builder(SettingActivity.this);
                alert.setMessage("你确定退出当前登录吗？");
                alert.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                alert.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
//                        new Thread(){
//                            @Override
//                            public void run() {
                                RequestParams  rp=new RequestParams(SuMaoConstant.SUMAO_IP+"/rest/model/atg/userprofiling/ProfileActor/logout");
                                SharedPreferences sp=getSharedPreferences("sumao",0);
                                String unique=sp.getString("unique","默认值");
                                rp.addParameter("_dynSessConf",unique);
                                Log.d("退出时的唯一标识",unique);
                                x.http().post(rp, new Callback.CommonCallback<String>() {
                                    @Override
                                    public void onSuccess(String result) {
//                                        if (result.contains("messageCode")){
//
//                                            Toast.makeText(SettingActivity.this, "用户必须登录才能访问资源", Toast.LENGTH_SHORT).show();
//                                            Intent intent = new Intent(SettingActivity.this,LoginUserActivity.class);
//                                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                                            intent.putExtra("roles","buyer");
//                                            startActivity(intent);
//                                            SettingActivity.this.finish();
//                                            MainActivity.instancefinish.finish();
//                                        }else{
                                            SharedPreferences sp=getSharedPreferences("sumao", Activity.MODE_PRIVATE);
                                            SharedPreferences.Editor editor=sp.edit();
                                            editor.clear();
                                      //      editor.clear();
                                            editor.commit();
                                            Toast.makeText(SettingActivity.this, "用户名称已删除", Toast.LENGTH_SHORT).show();
                                            Intent intent=new Intent(SettingActivity.this,MainActivity.class);
                                            intent.putExtra("roles","buyer");
                                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                            startActivity(intent);
//                                            SettingActivity.this.finish();

//                                        }
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
//                            }
//                        }.start();

                    }
                });
                alert.create().show();

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

        tvUpdate.setVisibility(View.GONE);
        tv_title_bar_title.setText("设置");


        tv_title_bar_title.setTextColor(Color.WHITE);
        iv_title_bar_back.setVisibility(View.VISIBLE);
        layoutTop= (RelativeLayout) findViewById(R.id.layoutTop_setting);
        layoutTop.setBackgroundColor(getResources().getColor(R.color.textColor_expandable_listview_show));

        iv_title_bar_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SettingActivity.this.finish();
            }
        });
//        tvUpdate.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent=new Intent(SettingActivity.this,FirmInfoUpdateActivity.class);
//                startActivity(intent);
//            }
//        });
    }
//功能介绍
    @Override
    public void sendInfoInFunctionIntroduction(FunctionIntroductionBean functionIntroductionBean) {

//        LogUtils
        Intent intent = new Intent(this,FunctionIntroductionActivity.class);
        intent.putExtra("FunctionIntroductionBean",functionIntroductionBean);
        startActivity(intent);
    }
}

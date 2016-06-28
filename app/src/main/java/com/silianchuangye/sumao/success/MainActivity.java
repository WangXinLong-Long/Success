package com.silianchuangye.sumao.success;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import com.easemob.EMConnectionListener;
import com.easemob.EMError;
import com.easemob.EMEventListener;
import com.easemob.EMNotifierEvent;
import com.easemob.chat.EMChatManager;
import com.easemob.chat.EMMessage;
import com.silianchuangye.sumao.success.HX.DemoHelper;
import com.silianchuangye.sumao.success.HX.ui.ChatActivity;
import com.silianchuangye.sumao.success.fragments.PagerFour;
import com.silianchuangye.sumao.success.fragments.PagerOne;
import com.silianchuangye.sumao.success.fragments.PagerThree;
import com.silianchuangye.sumao.success.fragments.PagerTwo;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.login.LoginUserActivity;
import com.silianchuangye.sumao.success.utils.GlobalVariable;

import java.util.ArrayList;
import java.util.List;

import cn.jpush.android.api.JPushInterface;

public class MainActivity extends FragmentActivity implements EMEventListener {

    public Activity instance;
    private FragmentTabHost mTabHost;
    //布局填充器
    private LayoutInflater mLayoutInflater;
    //Fragment界面数组
    private List<Class> mFragmentArray;
    //存放图片数组
    private List<Integer> mImageArray;
    //存放文字数组
    private List<String> mTextArray;
    // 退出时间
    private long exitTime = 0;
    private MyConnectionListener connectionListener = null;
    boolean flag = false;
    int id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        初始化数据
        initData();
//         初始化组件
        initView();
        init();

    }
    private void initView() {
        mLayoutInflater = LayoutInflater.from(this);
        mTabHost  = ((FragmentTabHost) findViewById(android.R.id.tabhost));
        mTabHost.setup(this,getSupportFragmentManager(),R.id.fl_activity_main_content);
//        得到Fragment的个数
        int count = mFragmentArray.size();
        for (int i = 0; i < count; i++) {
            TabHost.TabSpec tabSpec = mTabHost.newTabSpec(mTextArray.get(i)).setIndicator(getTabItemView(i));
            mTabHost.addTab(tabSpec,mFragmentArray.get(i),null);
        }

        /**
         * 设置默认选中第几个
         */
//        mTabHost.setCurrentTab(1);
        mTabHost.getTabWidget().getChildTabViewAt(3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (GlobalVariable.FLAG){
                    mTabHost.setCurrentTab(3);
                }else {
                    Intent intent = new Intent();
                    intent.setClass(MainActivity.this, LoginUserActivity.class);
                    startActivity(intent);
                }

            }
        });
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        getChildLogin();
    }
    //购物车按钮---判断是否登陆过，没登录跳的登陆界面
    private void getChildLogin(){
        mTabHost.getTabWidget().getChildTabViewAt(2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (GlobalVariable.FLAG){
                    mTabHost.setCurrentTab(2);
//                    Bundle bundle=getIntent().getExtras();
//                    String name=bundle.getString("name");
//                    Log.d("name",""+name);

                }else {
                    Intent intent = new Intent();
                    intent.putExtra("cart1", 9);
                    intent.setClass(MainActivity.this, LoginUserActivity.class);
                    startActivity(intent);
                    MainActivity.this.finish();

                }

            }
        });
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }
    private View getTabItemView(int i) {
        View view = mLayoutInflater.inflate(R.layout.tab_item_view,null);
        ImageView iv = ((ImageView) view.findViewById(R.id.iv_tab_itmem_view_picture));
        TextView tv = ((TextView) view.findViewById(R.id.iv_tab_itmem_view_character));
        iv.setBackgroundResource(mImageArray.get(i));
        tv.setText(mTextArray.get(i));
        return view;
    }

    private void initData() {
        instance = this;
        mFragmentArray = new ArrayList<>();
        mImageArray = new ArrayList<>();
        mTextArray = new ArrayList<>();

        mFragmentArray.add(PagerOne.class);
        mFragmentArray.add(PagerTwo.class);
        mFragmentArray.add(PagerThree.class);
        mFragmentArray.add(PagerFour.class);

        mImageArray.add(R.drawable.home_page);
        mImageArray.add(R.drawable.classification);
        mImageArray.add(R.drawable.shopping_car);
        mImageArray.add(R.drawable.mine_sumao);

        mTextArray.add("首页");
        mTextArray.add("分类");
        mTextArray.add("购物车");
        mTextArray.add("我的塑贸");
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode==KeyEvent.KEYCODE_BACK&&event.getAction()==KeyEvent.ACTION_DOWN)
        {
            if (System.currentTimeMillis()-exitTime>2000)
            {
                Toast.makeText(this,"再按一次退出程序",Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            }else {
                finish();
            }
            return  true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @SuppressLint("NewApi")
    private void init() {
        //注册一个监听连接状态的listener
        connectionListener = new MyConnectionListener();
        EMChatManager.getInstance().addConnectionListener(connectionListener);


    }

    public class MyConnectionListener implements EMConnectionListener {

        @Override
        public void onConnected() {

        }

        @Override
        public void onDisconnected(final int error) {
            runOnUiThread(new Runnable() {

                @Override
                public void run() {
                    if(error == EMError.USER_REMOVED){
                        //账号被移除
                        DemoHelper.getInstance().logout(true, null);
                        if(ChatActivity.activityInstance != null){
                            ChatActivity.activityInstance.finish();
                        }
                    }else if(error == EMError.CONNECTION_CONFLICT){
                        //账号在其他地方登录
                        DemoHelper.getInstance().logout(true, null);
                        if(ChatActivity.activityInstance != null){
                            ChatActivity.activityInstance.finish();
                        }
                    }else{
                        //连接不到服务器


                    }

                }
            });
        }

    }
    private BroadcastReceiver internalDebugReceiver;
    /**
     * 内部测试代码，开发者请忽略
     */
    private void registerInternalDebugReceiver() {
        internalDebugReceiver = new BroadcastReceiver() {

            @Override
            public void onReceive(Context context, Intent intent) {
                DemoHelper.getInstance().logout(true, null);
                if(ChatActivity.activityInstance != null){
                    ChatActivity.activityInstance.finish();
                }
            }
        };
        IntentFilter filter = new IntentFilter(getPackageName() + ".em_internal_debug");
        registerReceiver(internalDebugReceiver, filter);
    }



    @Override
    protected void onStop() {
        super.onStop();
        // 把此activity 从foreground activity 列表里移除
        DemoHelper.getInstance().popActivity(this);
        EMChatManager.getInstance().unregisterEventListener(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(connectionListener != null){
            EMChatManager.getInstance().removeConnectionListener(connectionListener);
        }
        try {
            unregisterReceiver(internalDebugReceiver);
        } catch (Exception e) {
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        DemoHelper.getInstance().pushActivity(this);
        //register the event listener when enter the foreground
        EMChatManager.getInstance().registerEventListener(this,
                new EMNotifierEvent.Event[] { EMNotifierEvent.Event.EventNewMessage,
                        EMNotifierEvent.Event.EventOfflineMessage });
        //Jpush推送
        JPushInterface.onResume(this);
        //注册广播
        int num=getIntent().getIntExtra("cart",0);
        switch (num)
        {
            case 1:
                if (GlobalVariable.FLAG){
                    mTabHost.setCurrentTab(2);
                }else {
                    Intent intent = new Intent();
                    intent.putExtra("cart1", 9);
                    intent.setClass(MainActivity.this, LoginUserActivity.class);
                    startActivity(intent);
                    MainActivity.this.finish();
                }
                GlobalVariable.FLAG = true;
                mTabHost.setCurrentTab(2);
                break;
            case 3:
                GlobalVariable.FLAG = true;
                mTabHost.setCurrentTab(3);
                break;
            case 4:
                GlobalVariable.FLAG = true;
                mTabHost.setCurrentTab(2);
            default:
                break;

        }

    }

    @Override
    protected void onNewIntent(Intent intent) {
         id = intent.getIntExtra("cart",0);
        Log.d("id",id+"------------>");


    }

    @Override
    protected void onPause() {
        super.onPause();
        //Jpush推送
        JPushInterface.onPause(this);
    }

    @Override
    public void onEvent(EMNotifierEvent event) {
        switch (event.getEvent()) {
            case EventNewMessage:
                EMMessage message = (EMMessage) event.getData();
                //提示新消息
                DemoHelper.getInstance().getNotifier().onNewMsg(message);
                break;
            case EventOfflineMessage:
                //处理离线消息
                List<EMMessage> messages = (List<EMMessage>) event.getData();
                //消息提醒或只刷新UI
                DemoHelper.getInstance().getNotifier().onNewMesg(messages);
                break;
            default:
                break;
        }


    }
//    public String getName(){
//
//        return name;
//    }

}


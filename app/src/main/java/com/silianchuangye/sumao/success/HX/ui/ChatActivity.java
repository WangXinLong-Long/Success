package com.silianchuangye.sumao.success.HX.ui;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;

import com.easemob.easeui.ui.EaseChatFragment;
import com.silianchuangye.sumao.success.HX.Constant;
import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.utils.HXUtils.HelpDeskPreferenceUtils;
import com.zhy.m.permission.MPermissions;
import com.zhy.m.permission.PermissionDenied;
import com.zhy.m.permission.PermissionGrant;

/**
 * 聊天页面，需要fragment的使用{@link EaseChatFragment}
 */
public class ChatActivity extends BaseActivity {

    public static ChatActivity activityInstance;
    private ChatFragment chatFragment;
    String toChatUsername;
    private static final int REQUEST_PERMISSION_CAMERA_CODE = 5;
    static final String[] PERMISSIONS = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA};
    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);

        setContentView(R.layout.em_activity_chat);
        activityInstance = this;
        // 可以直接new EaseChatFratFragment使用
        chatFragment = new ChatFragment();
        Intent intent = getIntent();
        // 聊天人或群id
        toChatUsername = intent.getStringExtra(Constant.IM_SERVICE_NUMBER);
        ;
        intent.putExtra(Constant.EXTRA_USER_ID, toChatUsername);
        intent.putExtra(Constant.EXTRA_SHOW_USERNICK, true);
        // 传入参数
        chatFragment.setArguments(intent.getExtras());
        getSupportFragmentManager().beginTransaction().add(R.id.container, chatFragment).commit();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        activityInstance = null;
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        // 点击notification bar进入聊天页面，保证只有一个聊天页面
        String username = intent.getStringExtra("userId");
        if (toChatUsername.equals(username))
            super.onNewIntent(intent);
        else {
            finish();
            startActivity(intent);
        }

    }

    @Override
    public void onBackPressed() {
        chatFragment.onBackPressed();
    }

//    public String getToChatUsername() {
//        return toChatUsername;
//    }

//	public void sendTextMessage(String txtContent){
//		chatFragment.sendTextMessage(txtContent);
//	}

    public void sendRobotMessage(String txtContent, String menuId) {
        chatFragment.sendRobotMessage(txtContent, menuId);
    }



}

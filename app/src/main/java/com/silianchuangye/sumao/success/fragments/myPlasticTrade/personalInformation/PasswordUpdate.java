package com.silianchuangye.sumao.success.fragments.myPlasticTrade.personalInformation;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.silianchuangye.sumao.success.R;

import org.json.JSONArray;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

public class PasswordUpdate extends AppCompatActivity {
    private ImageView ivBack_password_update;
    private EditText etOld_password_update;
    private EditText etNew_password_update;
    private EditText etRenew_password_update;
    private Button btSave_password_update;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_update1);
        ivBack_password_update= (ImageView) findViewById(R.id.ivback_password_update);
        ivBack_password_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PasswordUpdate.this.finish();
            }
        });
        etOld_password_update= (EditText) findViewById(R.id.etold_password_update);
        etNew_password_update= (EditText) findViewById(R.id.etnew_password_update);
        etRenew_password_update= (EditText) findViewById(R.id.etrenew_password_update);
        btSave_password_update= (Button) findViewById(R.id.btSave_password_update);
        btSave_password_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //  if (etNew_password_update.getText().toString().equals(etRenew_password_update.getText().toString())){
//                    AlertDialog.Builder alert=new AlertDialog.Builder(PasswordUpdate.this);
//                    alert.setTitle("您的密码修改成功");
//                   // alert.setMessage("确认");
//                    alert.setPositiveButton("确认", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//                            etNew_password_update.setText("");
//                            etRenew_password_update.setText("");
//                        }
//                    });
//                    alert.show();
//                    //保存密码
//                }else{
//                     AlertDialog.Builder alert=new AlertDialog.Builder(PasswordUpdate.this);
//                    alert.setMessage("您两次输入的密码不一致，请重新输入！");
//                    alert.setPositiveButton("我知道了！", new DialogInterface.OnClickListener() {
//                       @Override
//                       public void onClick(DialogInterface dialog, int which) {
//                           etNew_password_update.setText("");
//                           etRenew_password_update.setText("");
//                       }
//                   });
//                    alert.show();

                // }
                if (etNew_password_update.getText().toString().equals(etRenew_password_update.getText().toString())) {
                    new Thread() {
                        @Override
                        public void run() {
                            String uri = "http://192.168.32.126:7023/rest/model/atg/userprofiling/ProfileActor/changePassword";
                            RequestParams rp = new RequestParams(uri);
                            rp.addParameter("oldPassword", etOld_password_update.getText().toString());//原来密码
                            rp.addParameter("password", etNew_password_update.getText().toString());//新密码
                            rp.addParameter("confirmPassword", etRenew_password_update.getText().toString());//确认新密码
                            SharedPreferences sp = getSharedPreferences("sumao", Activity.MODE_PRIVATE);
                            String unique = sp.getString("unique", "");
                            Log.d("修改密码的唯一标识", unique);
                            Log.d("修改密码的rp", rp + "");
                            rp.addParameter("_dynSessConf", unique);
                            x.http().post(rp, new Callback.CommonCallback<String>() {
                                @Override
                                public void onSuccess(String result) {
                                    if (result.contains("formExceptions")) {
                                        try {
                                            JSONObject object = new JSONObject(result);
                                            String Exception = object.getString("formExceptions");
                                            JSONArray array = new JSONArray(Exception);
                                            JSONObject exception = array.getJSONObject(0);
                                            String type = exception.getString("localizedMessage");
                                            Log.d("type", type);
                                            Toast.makeText(PasswordUpdate.this, "" + type, Toast.LENGTH_SHORT).show();
                                            etOld_password_update.setText("");
                                            etNew_password_update.setText("");
                                            etRenew_password_update.setText("");
                                        } catch (Exception e) {
                                            e.printStackTrace();

                                        }

                                    } else {
                                        //修改成功
                                        Toast.makeText(PasswordUpdate.this, "密码修改成功！", Toast.LENGTH_SHORT).show();
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
                    }.start();
                }else {
                    Toast.makeText(PasswordUpdate.this, "两次输入的密码不一致,请重新输入", Toast.LENGTH_SHORT).show();
                    etOld_password_update.setText("");
                    etNew_password_update.setText("");
                    etRenew_password_update.setText("");
                }
            }
        });
    }
}

package com.silianchuangye.sumao.success.fragments.myPlasticTrade.personalInformation;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.silianchuangye.sumao.success.R;

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
                if (etNew_password_update.getText().toString().equals(etRenew_password_update.getText().toString())){
                    AlertDialog.Builder alert=new AlertDialog.Builder(PasswordUpdate.this);
                    alert.setTitle("您的密码修改成功");
                   // alert.setMessage("确认");
                    alert.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            etNew_password_update.setText("");
                            etRenew_password_update.setText("");
                        }
                    });
                    alert.show();
                    //保存密码
                }else{
                     AlertDialog.Builder alert=new AlertDialog.Builder(PasswordUpdate.this);
                    alert.setMessage("您两次输入的密码不一致，请重新输入！");
                    alert.setPositiveButton("我知道了！", new DialogInterface.OnClickListener() {
                       @Override
                       public void onClick(DialogInterface dialog, int which) {
                            etNew_password_update.setText("");
                           etRenew_password_update.setText("");
                       }
                   });
                    alert.show();

                }
            }
        });
    }
}

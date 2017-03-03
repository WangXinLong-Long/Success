package com.silianchuangye.sumao.success.fragments.myPlasticTrade.EnterpriseCapitalAccountManagement;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.silianchuangye.sumao.success.R;

/**
 * 账户余额
 */
public class AccountBalanceActivity extends AppCompatActivity {

    private TextView current_account_available_balance_num;
    private TextView current_account_amount_num;
    private TextView tv_child_title_bar_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_balance);
//        账户余额
        tv_child_title_bar_title = ((TextView) findViewById(R.id.tv_child_title_bar_title));
        tv_child_title_bar_title.setText("账户余额");
//        当前账户可用余额
        current_account_available_balance_num = ((TextView) findViewById(R.id.current_account_available_balance_num));
//        当前账户总金额
        current_account_amount_num = ((TextView) findViewById(R.id.current_account_amount_num));
    }
}

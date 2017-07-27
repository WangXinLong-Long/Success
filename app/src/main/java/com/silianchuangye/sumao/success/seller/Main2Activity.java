package com.silianchuangye.sumao.success.seller;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import com.silianchuangye.sumao.success.R;

public class Main2Activity extends AppCompatActivity implements View.OnClickListener {
    private FrameLayout fragLayout;
    private FragmentTransaction fragmentTransaction;
    private FragmentManager fragmentManager;
    private MainFragment mainFragment;
    private ProductFragment productFragment;
    private TransactionFragment transactionFragment;
    private CustomerFragment customerFragment;
    private MyFragment myFragment;
    private ImageView iv_one;
    private ImageView iv_two;
    private ImageView iv_three;
    private ImageView iv_four;
    private ImageView iv_five;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        init();
        addData();
    }
    public void init(){
        iv_one= (ImageView) findViewById(R.id.iv_one);
        iv_two= (ImageView) findViewById(R.id.iv_two);
        iv_three= (ImageView) findViewById(R.id.iv_three);
        iv_four= (ImageView) findViewById(R.id.iv_four);
        iv_five= (ImageView) findViewById(R.id.iv_five);
        iv_five.setOnClickListener(this);
        iv_one.setOnClickListener(this);
        iv_two.setOnClickListener(this);
        iv_three.setOnClickListener(this);
        iv_four.setOnClickListener(this);
        fragLayout= (FrameLayout) findViewById(R.id.fragLayout);
    }
    public void addData(){
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        mainFragment=new MainFragment();
        myFragment=new MyFragment();
        productFragment=new ProductFragment();
        customerFragment=new CustomerFragment();
        transactionFragment=new TransactionFragment();

        fragmentTransaction.add(R.id.fragLayout, mainFragment,null);
        fragmentTransaction.add(R.id.fragLayout, productFragment,null);
        fragmentTransaction.add(R.id.fragLayout, transactionFragment,null);
        fragmentTransaction.add(R.id.fragLayout, customerFragment,null);
        fragmentTransaction.add(R.id.fragLayout, myFragment,null);
        fragmentTransaction.show(mainFragment);
        fragmentTransaction.hide(productFragment).hide(transactionFragment).hide(customerFragment).hide(myFragment);
        //fragmentTransaction.add(R.id.fragLayout, productFragment,null);

        /*fragmentTransaction.replace(R.id.content_layout, mContentFragment);
        fragmentTransaction.replace(R.id.buttom_layout, mButtomFragment);*/
        fragmentTransaction.commit();


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_one:
                getSupportFragmentManager().beginTransaction().show(mainFragment).hide(productFragment).hide(transactionFragment).hide(customerFragment).hide(myFragment).commit();
                //必需继承FragmentActivity,嵌套fragment只需要这行代码
                //getSupportFragmentManager().beginTransaction().replace(R.id.fragLayout, mainFragment).commitAllowingStateLoss();
                break;
            case R.id.iv_two:
                Toast.makeText(Main2Activity.this, "产品管理", Toast.LENGTH_SHORT).show();
               // getSupportFragmentManager().beginTransaction().show(productFragment).hide(mainFragment).hide(transactionFragment).hide(customerFragment).hide(myFragment).commit();
               // getSupportFragmentManager().beginTransaction().replace(R.id.fragLayout, productFragment).commitAllowingStateLoss();
                break;
            case R.id.iv_three:
                Toast.makeText(Main2Activity.this, "交易管理", Toast.LENGTH_SHORT).show();
               // getSupportFragmentManager().beginTransaction().show(transactionFragment).hide(productFragment).hide(mainFragment).hide(customerFragment).hide(myFragment).commit();
                //getSupportFragmentManager().beginTransaction().replace(R.id.fragLayout, transactionFragment).commitAllowingStateLoss();
                break;
            case R.id.iv_four:
                Toast.makeText(Main2Activity.this, "客户管理", Toast.LENGTH_SHORT).show();
               // getSupportFragmentManager().beginTransaction().show(customerFragment).hide(productFragment).hide(transactionFragment).hide(mainFragment).hide(myFragment).commit();
               // getSupportFragmentManager().beginTransaction().replace(R.id.fragLayout, customerFragment).commitAllowingStateLoss();
                break;
            case R.id.iv_five:
                getSupportFragmentManager().beginTransaction().show(myFragment).hide(productFragment).hide(transactionFragment).hide(customerFragment).hide(mainFragment).commit();
              //  getSupportFragmentManager().beginTransaction().replace(R.id.fragLayout, myFragment).commitAllowingStateLoss();
                break;
        }

    }
}

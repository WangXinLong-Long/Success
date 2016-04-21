package com.silianchuangye.sumao.success;

import android.app.Activity;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import com.silianchuangye.sumao.success.fragments.PagerFour;
import com.silianchuangye.sumao.success.fragments.PagerOne;
import com.silianchuangye.sumao.success.fragments.PagerThree;
import com.silianchuangye.sumao.success.fragments.PagerTwo;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity {

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        初始化数据
        initData();
//         初始化组件
        initView();

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

        mImageArray.add(R.mipmap.home_page);
        mImageArray.add(R.mipmap.classification);
        mImageArray.add(R.mipmap.shopping_car);
        mImageArray.add(R.mipmap.mine_sumao);

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
}


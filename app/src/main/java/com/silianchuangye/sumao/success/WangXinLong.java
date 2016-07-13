package com.silianchuangye.sumao.success;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;

import com.silianchuangye.sumao.success.custom.CustomPayDialog;

/**
 * Created by Administrator on 2016/4/13 0013.
 */
public class WangXinLong extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        5月10整理项目结构 创建
//        5月13收货地址
//        5月17日开票信息
//        5月19 预售
//        5月30
//        6月1日 修改预售完毕
//        准备hellochart
//        改变图片11
//        改变图片11
//        改变图片11
//        Android6.0权限问题
//        Android6.0权限问题
//        6月30日查看照相机返回后崩溃的页面
//        6月30日查看照相机返回后崩溃的页面
//        7月1日
//        7月5日   现货加入购物车界面动画和支付订单界面
//        7月7日  区分已支付订单和未支付订单
//        7月8日 合并代码
//        7月13日 查看物理需求
//        7月13 修改清单文件

        showPayDialog();

    }

    public void showPayDialog()
    {
        CustomPayDialog.Builder builder = new CustomPayDialog.Builder(this);
        builder.setMessage("这个就是自定义的提示框");
        builder.setTitle("提示");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {

                //设置你的操作事项
                dialog.dismiss();
            }
        });

        builder.setNegativeButton("取消",
                new android.content.DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

        builder.create().show();
    }
}

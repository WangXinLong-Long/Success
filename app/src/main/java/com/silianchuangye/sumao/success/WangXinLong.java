package com.silianchuangye.sumao.success;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.silianchuangye.sumao.success.custom.CustomPayDialog;

import java.util.ArrayList;
import java.util.LinkedList;

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
//        7月19日 卖家订单管理已支付状态页面
//        7月19日 卖家订单管理已支付状态页面
//        7月19日 卖家订单管理已支付状态页面
//        7月19日 卖家订单管理已支付状态页面

//        7月25日 解决注册页面中返回崩溃问题
//        7月26日 使用MVP架构模式，进行注册界面的开发
//        7月27日 解决MVP中model层往Presenter传递数据的空指针异常
//        7月28日 注册，地址相关接口，，处理注册接口
//        7月29日 我的塑贸，企业信息，收货地址一级页面接口
//        8月1日  解决收货地址，获取省市县请求数据后，存储不到list的问题
//        8月2日  解决收货地址确定后的参数传递崩溃问题
//        8月2日 解决办公地址放回数据的问题
//        8月3日 系统权限二次处理
//        8月4日  调通新增收货地址接口
//        8月8日  收货地址更新问题解决
//        8月9日   解决乱码问题
//        8月10日  收货地址新增，删除，修改全部做完
//        8月11日  开票信息接口处理完毕
//        8月13日  修改类名
//        8月15日  预售界面接口已基本完成
//        8月18日   滚动条数，即公告
//        8月19日     修改获取相册相片的6.0问题
//        8月22日   预售日历
//        8月25日  注册上传文件
//        9月6日    搜索页面刷新
//                  筛选处理
//                  现货页面修改
//                  SessionId过期处理，塑贸资讯
//                  卖家客服，合同处理
//        上游直销
//        9月14日上有直销、日计划
//        9月19日  查看计划
//        9月22日 修改现货订单bean类
//        9月26日 网络加载时，显示的dialog
//        9月27日 获取账号、
//        10月8日 环信的聊天界面中，解决拍照不给授权的情况的处理
//        10月11日 创建物流需求的收货地址
        showPayDialog();
        ArrayList<String> arrayList = new ArrayList<>();
        LinkedList<String> linkedList = new LinkedList<>();

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

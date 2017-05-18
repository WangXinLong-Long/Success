package com.silianchuangye.sumao.success.fragments.type;

import com.silianchuangye.sumao.success.fragments.pagerThreeMVP.bean.CartBean;

import java.util.List;

/**
 * Created by junjun on 2016/9/1.
 */
public interface SelectCallBack {
    void Call(int index);

    void CartNull(List<CartBean.CommerceItemBean> list);
//    public void CartNull(List<CartInfo> list);

    //1111111111111111111111111111111
}

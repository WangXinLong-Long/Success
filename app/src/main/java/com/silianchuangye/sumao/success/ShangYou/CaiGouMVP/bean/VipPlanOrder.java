package com.silianchuangye.sumao.success.ShangYou.CaiGouMVP.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2016/9/14 0014.
 */
public class VipPlanOrder implements Serializable {
    String pageCount;//总页数
    List<PlanOrderList> planOrderList;
    String orderCount;//订单数
    String pageNum;//页码

    public String getPageCount() {
        return pageCount;
    }

    public void setPageCount(String pageCount) {
        this.pageCount = pageCount;
    }

    public List<PlanOrderList> getPlanOrderList() {
        return planOrderList;
    }

    public void setPlanOrderList(List<PlanOrderList> planOrderList) {
        this.planOrderList = planOrderList;
    }

    public String getOrderCount() {
        return orderCount;
    }

    public void setOrderCount(String orderCount) {
        this.orderCount = orderCount;
    }

    public String getPageNum() {
        return pageNum;
    }

    public void setPageNum(String pageNum) {
        this.pageNum = pageNum;
    }
}

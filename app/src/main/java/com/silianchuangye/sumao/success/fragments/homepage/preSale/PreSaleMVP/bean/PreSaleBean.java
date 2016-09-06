package com.silianchuangye.sumao.success.fragments.homepage.preSale.PreSaleMVP.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2016/8/19 0019.
 */
public class PreSaleBean implements Serializable {
    private List<Forward> forward;
    private List<Auction> auction;
    private List<Group> group;
    public List<Forward> getForward() {
        return forward;
    }

    public void setForward(List<Forward> forward) {
        this.forward = forward;
    }

    public List<Auction> getAuction() {
        return auction;
    }

    public void setAuction(List<Auction> auction) {
        this.auction = auction;
    }

    public List<Group> getGroup() {
        return group;
    }

    public void setGroup(List<Group> group) {
        this.group = group;
    }
}

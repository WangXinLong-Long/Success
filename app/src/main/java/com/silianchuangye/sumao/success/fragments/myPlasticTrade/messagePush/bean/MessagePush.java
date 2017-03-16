package com.silianchuangye.sumao.success.fragments.myPlasticTrade.messagePush.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by junjun on 2017/3/11.
 */
public class MessagePush implements Serializable {
    private String pageCount;
    private List<NoticeList> noticeList;

    public String getPageCount() {
        return pageCount;
    }

    public void setPageCount(String pageCount) {
        this.pageCount = pageCount;
    }

    public List<NoticeList> getNoticeList() {
        return noticeList;
    }

    public void setNoticeList(List<NoticeList> noticeList) {
        this.noticeList = noticeList;
    }

    public static class NoticeList implements Serializable{
        private String id;
        private String images;
        private String sendTime;
        private String sendType;
        private String status;
        private String title;

        public String getImages() {
            return images;
        }

        public void setImages(String images) {
            this.images = images;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getSendTime() {
            return sendTime;
        }

        public void setSendTime(String sendTime) {
            this.sendTime = sendTime;
        }

        public String getSendType() {
            return sendType;
        }

        public void setSendType(String sendType) {
            this.sendType = sendType;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }
}

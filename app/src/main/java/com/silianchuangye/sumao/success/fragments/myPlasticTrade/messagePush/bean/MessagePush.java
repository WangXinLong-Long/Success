package com.silianchuangye.sumao.success.fragments.myPlasticTrade.messagePush.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by junjun on 2017/3/11.
 */
public class MessagePush implements Serializable {
    private String pageCount;
    private List<Message> list;

    public String getPageCount() {
        return pageCount;
    }

    public void setPageCount(String pageCount) {
        this.pageCount = pageCount;
    }

    public List<Message> getList() {
        return list;
    }

    public void setList(List<Message> list) {
        this.list = list;
    }

    public static class Message implements Serializable{
        private String MessageId;
        private String MessageType;
        private String MessageTime;
        private String MessageTitle;

        public String getMessageId() {
            return MessageId;
        }

        public void setMessageId(String messageId) {
            MessageId = messageId;
        }

        public String getMessageType() {
            return MessageType;
        }

        public void setMessageType(String messageType) {
            MessageType = messageType;
        }

        public String getMessageTime() {
            return MessageTime;
        }

        public void setMessageTime(String messageTime) {
            MessageTime = messageTime;
        }

        public String getMessageTitle() {
            return MessageTitle;
        }

        public void setMessageTitle(String messageTitle) {
            MessageTitle = messageTitle;
        }
    }
}

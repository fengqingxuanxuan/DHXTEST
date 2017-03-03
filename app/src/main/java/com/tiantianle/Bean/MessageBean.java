package com.tiantianle.Bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/2/8.
 * <p>
 * 我的消息Bean
 */

public class MessageBean implements Serializable {

    private String state;

    private List<message> biz_content = new ArrayList<message>();

    public class message implements Serializable {
        private String msgid;// id
        private String time;// 时间
        private String title;// 标题
        private String body;// 内容

        public String getMsgid() {
            return msgid;
        }

        public void setMsgid(String msgid) {
            this.msgid = msgid;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getBody() {
            return body;
        }

        public void setBody(String body) {
            this.body = body;
        }
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public List<message> getBiz_content() {

        return biz_content;
    }

    public void setBiz_content(List<message> biz_content) {
        this.biz_content = biz_content;
    }
}

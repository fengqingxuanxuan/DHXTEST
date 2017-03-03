package com.tiantianle.Bean;

import java.util.List;

/**
 * Created by PengBo  on 2017/2/15.
 */

public class AllIndianaBean {


    /**
     * state : success
     * biz_content : [{"id":2,"orderid":60,"ordercode":"20170214223351","warecode":"g100","warename":"魔豆","speccode":"md1k","specname":"1000魔豆","issuenum":"md1k1702003","status":0,"totalfee":1000,"price":1000,"type":1,"num":1,"content":"","changeid":11,"createtime":"2017-02-15 11:33:48","nickname":"237533","winname":null,"winnum":0,"usernum":1300,"playnum":239,"totalcount":13}]
     */

    private String state;
    private List<BizContentBean> biz_content;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public List<BizContentBean> getBiz_content() {
        return biz_content;
    }

    public void setBiz_content(List<BizContentBean> biz_content) {
        this.biz_content = biz_content;
    }

    public static class BizContentBean {
        /**
         * id : 2
         * orderid : 60
         * ordercode : 20170214223351
         * warecode : g100
         * warename : 魔豆
         * speccode : md1k
         * specname : 1000魔豆
         * issuenum : md1k1702003
         * status : 0
         * totalfee : 1000.0
         * price : 1000.0
         * type : 1
         * num : 1
         * content :
         * changeid : 11
         * createtime : 2017-02-15 11:33:48
         * nickname : 237533
         * winname : null
         * winnum : 0
         * usernum : 1300
         * playnum : 239
         * totalcount : 13
         */

        private int id;
        private int orderid;
        private String ordercode;
        private String warecode;
        private String warename;
        private String speccode;
        private String specname;
        private String issuenum;
        private int status;
        private double totalfee;
        private double price;
        private int type;
        private int num;
        private String content;
        private int changeid;
        private String createtime;
        private String nickname;
        private String winname;
        private int winnum;
        private int usernum;
        private int playnum;
        private int totalcount;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getOrderid() {
            return orderid;
        }

        public void setOrderid(int orderid) {
            this.orderid = orderid;
        }

        public String getOrdercode() {
            return ordercode;
        }

        public void setOrdercode(String ordercode) {
            this.ordercode = ordercode;
        }

        public String getWarecode() {
            return warecode;
        }

        public void setWarecode(String warecode) {
            this.warecode = warecode;
        }

        public String getWarename() {
            return warename;
        }

        public void setWarename(String warename) {
            this.warename = warename;
        }

        public String getSpeccode() {
            return speccode;
        }

        public void setSpeccode(String speccode) {
            this.speccode = speccode;
        }

        public String getSpecname() {
            return specname;
        }

        public void setSpecname(String specname) {
            this.specname = specname;
        }

        public String getIssuenum() {
            return issuenum;
        }

        public void setIssuenum(String issuenum) {
            this.issuenum = issuenum;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public double getTotalfee() {
            return totalfee;
        }

        public void setTotalfee(double totalfee) {
            this.totalfee = totalfee;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public int getChangeid() {
            return changeid;
        }

        public void setChangeid(int changeid) {
            this.changeid = changeid;
        }

        public String getCreatetime() {
            return createtime;
        }

        public void setCreatetime(String createtime) {
            this.createtime = createtime;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getWinname() {
            return winname;
        }

        public void setWinname(String winname) {
            this.winname = winname;
        }

        public int getWinnum() {
            return winnum;
        }

        public void setWinnum(int winnum) {
            this.winnum = winnum;
        }

        public int getUsernum() {
            return usernum;
        }

        public void setUsernum(int usernum) {
            this.usernum = usernum;
        }

        public int getPlaynum() {
            return playnum;
        }

        public void setPlaynum(int playnum) {
            this.playnum = playnum;
        }

        public int getTotalcount() {
            return totalcount;
        }

        public void setTotalcount(int totalcount) {
            this.totalcount = totalcount;
        }
    }
}

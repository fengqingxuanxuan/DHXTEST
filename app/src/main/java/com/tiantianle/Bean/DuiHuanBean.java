package com.tiantianle.Bean;

import java.util.List;

/**
 * Created by PengBo  on 2017/2/17.
 */

public class DuiHuanBean {

    /**
     * state : success
     * biz_content : [{"id":1,"orderid":90,"ordercode":"63ba5443-3edf-46b7-bdb6-6304d47535cd","warecode":"jdekm","warename":"京东e卡卡密","speccode":"jdekm02","specname":"20元","issuenum":"","status":0,"totalfee":1,"price":1,"type":0,"num":1,"content":"","changeid":0,"createtime":"2017-02-15 17:42:09","nickname":"943353","winname":null,"winnum":null,"usernum":null,"playnum":null,"totalcount":22}]
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
         * id : 1
         * orderid : 90
         * ordercode : 63ba5443-3edf-46b7-bdb6-6304d47535cd
         * warecode : jdekm
         * warename : 京东e卡卡密
         * speccode : jdekm02
         * specname : 20元
         * issuenum :
         * status : 0
         * totalfee : 1.0
         * price : 1.0
         * type : 0
         * num : 1
         * content :
         * changeid : 0
         * createtime : 2017-02-15 17:42:09
         * nickname : 943353
         * winname : null
         * winnum : null
         * usernum : null
         * playnum : null
         * totalcount : 22
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

        public Object getWinname() {
            return winname;
        }

        public void setWinname(String  winname) {
            this.winname = winname;
        }

        public Object getWinnum() {
            return winnum;
        }

        public void setWinnum(int winnum) {
            this.winnum = winnum;
        }

        public Object getUsernum() {
            return usernum;
        }

        public void setUsernum(int  usernum) {
            this.usernum = usernum;
        }

        public Object getPlaynum() {
            return playnum;
        }

        public void setPlaynum(int  playnum) {
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

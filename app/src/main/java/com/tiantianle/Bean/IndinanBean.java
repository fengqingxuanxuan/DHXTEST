package com.tiantianle.Bean;

import java.util.List;

/**
 * Created by PengBo  on 2017/2/14.
 */

public class IndinanBean {

    /**
     * state : success
     * biz_content : [{"id":1,"changeid":11,"issuenum":"md1k1702003","warecode":"g100","warename":"魔豆","speccode":"md1k","specname":"1000魔豆","status":0,"price":1000,"usernum":1300,"totalfee":1300,"playnum":0,"type":1,"totalcount":4},{"id":2,"changeid":10,"issuenum":"md1b1702003","warecode":"g100","warename":"魔豆","speccode":"md1b","specname":"100魔豆","status":0,"price":100,"usernum":120,"totalfee":120,"playnum":0,"type":1,"totalcount":4},{"id":3,"changeid":9,"issuenum":"md5b1702003","warecode":"g100","warename":"魔豆","speccode":"md5b","specname":"500魔豆","status":0,"price":500,"usernum":600,"totalfee":600,"playnum":0,"type":1,"totalcount":4},{"id":4,"changeid":8,"issuenum":"md2k1702002","warecode":"g100","warename":"魔豆","speccode":"md2k","specname":"2000魔豆","status":0,"price":2000,"usernum":2501,"totalfee":2501,"playnum":0,"type":1,"totalcount":4}]
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
         * changeid : 11
         * issuenum : md1k1702003
         * warecode : g100
         * warename : 魔豆
         * speccode : md1k
         * specname : 1000魔豆
         * status : 0
         * price : 1000.0
         * usernum : 1300
         * totalfee : 1300.0
         * playnum : 0
         * type : 1
         * totalcount : 4
         */

        private int id;
        private int changeid;
        private String issuenum;
        private String warecode;
        private String warename;
        private String speccode;
        private String specname;
        private int status;
        private double price;
        private int usernum;
        private double totalfee;
        private int playnum;
        private int type;
        private int totalcount;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getChangeid() {
            return changeid;
        }

        public void setChangeid(int changeid) {
            this.changeid = changeid;
        }

        public String getIssuenum() {
            return issuenum;
        }

        public void setIssuenum(String issuenum) {
            this.issuenum = issuenum;
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

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public int getUsernum() {
            return usernum;
        }

        public void setUsernum(int usernum) {
            this.usernum = usernum;
        }

        public double getTotalfee() {
            return totalfee;
        }

        public void setTotalfee(double totalfee) {
            this.totalfee = totalfee;
        }

        public int getPlaynum() {
            return playnum;
        }

        public void setPlaynum(int playnum) {
            this.playnum = playnum;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getTotalcount() {
            return totalcount;
        }

        public void setTotalcount(int totalcount) {
            this.totalcount = totalcount;
        }
    }
}


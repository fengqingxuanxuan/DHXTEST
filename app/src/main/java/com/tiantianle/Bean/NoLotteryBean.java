package com.tiantianle.Bean;

import java.util.List;

/**
 * Created by PengBo  on 2017/2/21.
 */

public class NoLotteryBean {

    /**
     * state : success
     * biz_content : [{"lotteryid":252,"type":11,"expect":"2107842","opencode":null,"opentime":"2017-02-21 13:14:11","orderresult":null,"first":null,"second":null,"three":null,"result":null,"resultnum":null,"resulttype":null,"status":0,"allsec":876,"addtime":"2017-02-21 12:56:49"},{"lotteryid":250,"type":11,"expect":"2107841","opencode":null,"opentime":"2017-02-21 13:10:45","orderresult":null,"first":null,"second":null,"three":null,"result":null,"resultnum":null,"resulttype":null,"status":0,"allsec":670,"addtime":"2017-02-21 12:53:19"},{"lotteryid":248,"type":11,"expect":"2107840","opencode":null,"opentime":"2017-02-21 13:07:17","orderresult":null,"first":null,"second":null,"three":null,"result":null,"resultnum":null,"resulttype":null,"status":0,"allsec":462,"addtime":"2017-02-21 12:49:49"},{"lotteryid":247,"type":11,"expect":"2107839","opencode":null,"opentime":"2017-02-21 13:03:48","orderresult":null,"first":null,"second":null,"three":null,"result":null,"resultnum":null,"resulttype":null,"status":0,"allsec":253,"addtime":"2017-02-21 12:46:19"},{"lotteryid":245,"type":11,"expect":"2107838","opencode":null,"opentime":"2017-02-21 13:00:16","orderresult":null,"first":null,"second":null,"three":null,"result":null,"resultnum":null,"resulttype":null,"status":0,"allsec":41,"addtime":"2017-02-21 12:42:49"}]
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
         * lotteryid : 252
         * type : 11
         * expect : 2107842
         * opencode : null
         * opentime : 2017-02-21 13:14:11
         * orderresult : null
         * first : null
         * second : null
         * three : null
         * result : null
         * resultnum : null
         * resulttype : null
         * status : 0
         * allsec : 876
         * addtime : 2017-02-21 12:56:49
         */

        private int lotteryid;
        private int type;
        private String expect;
        private Object opencode;
        private String opentime;
        private Object orderresult;
        private Object first;
        private Object second;
        private Object three;
        private Object result;
        private Object resultnum;
        private Object resulttype;
        private int status;
        private int allsec;
        private String addtime;

        public int getLotteryid() {
            return lotteryid;
        }

        public void setLotteryid(int lotteryid) {
            this.lotteryid = lotteryid;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getExpect() {
            return expect;
        }

        public void setExpect(String expect) {
            this.expect = expect;
        }

        public Object getOpencode() {
            return opencode;
        }

        public void setOpencode(Object opencode) {
            this.opencode = opencode;
        }

        public String getOpentime() {
            return opentime;
        }

        public void setOpentime(String opentime) {
            this.opentime = opentime;
        }

        public Object getOrderresult() {
            return orderresult;
        }

        public void setOrderresult(Object orderresult) {
            this.orderresult = orderresult;
        }

        public Object getFirst() {
            return first;
        }

        public void setFirst(Object first) {
            this.first = first;
        }

        public Object getSecond() {
            return second;
        }

        public void setSecond(Object second) {
            this.second = second;
        }

        public Object getThree() {
            return three;
        }

        public void setThree(Object three) {
            this.three = three;
        }

        public Object getResult() {
            return result;
        }

        public void setResult(Object result) {
            this.result = result;
        }

        public Object getResultnum() {
            return resultnum;
        }

        public void setResultnum(Object resultnum) {
            this.resultnum = resultnum;
        }

        public Object getResulttype() {
            return resulttype;
        }

        public void setResulttype(Object resulttype) {
            this.resulttype = resulttype;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getAllsec() {
            return allsec;
        }

        public void setAllsec(int allsec) {
            this.allsec = allsec;
        }

        public String getAddtime() {
            return addtime;
        }

        public void setAddtime(String addtime) {
            this.addtime = addtime;
        }
    }
}

package com.tiantianle.Bean;

import java.util.List;

/**
 * Created by PengBo  on 2017/2/21.
 *
 */

public class TheLotteryBean {

    /**
     * state : success
     * biz_content : [{"lotteryid":243,"type":11,"expect":"2107837","opencode":"05,06,07,10,15,17,25,31,42,44,46,52,53,54,61,64,65,67,68,78","opentime":"2017-02-21 12:56:47","orderresult":"05,06,07,10,15,17,25,31,42,44,46,52,53,54,61,64,65,67,68,78","first":"7","second":"6","three":"4","result":"7+6+4=17","resultnum":"17","resulttype":"大,单","status":2,"allsec":0,"addtime":"2017-02-21 12:39:19"},{"lotteryid":242,"type":11,"expect":"2107836","opencode":"23,29,37,41,42,43,45,47,49,51,52,61,62,65,67,71,74,76,77,80","opentime":"2017-02-21 12:53:18","orderresult":"23,29,37,41,42,43,45,47,49,51,52,61,62,65,67,71,74,76,77,80","first":"9","second":"3","three":"7","result":"9+3+7=19","resultnum":"19","resulttype":"大,单","status":2,"allsec":0,"addtime":"2017-02-21 12:35:51"},{"lotteryid":240,"type":11,"expect":"2107835","opencode":"02,08,11,12,13,16,17,23,26,35,37,53,55,62,67,69,76,77,79,80","opentime":"2017-02-21 12:49:51","orderresult":"02,08,11,12,13,16,17,23,26,35,37,53,55,62,67,69,76,77,79,80","first":"9","second":"0","three":"7","result":"9+0+7=16","resultnum":"16","resulttype":"大,双","status":2,"allsec":0,"addtime":"2017-02-21 12:32:29"},{"lotteryid":238,"type":11,"expect":"2107834","opencode":"01,03,04,07,15,21,23,28,30,31,41,44,46,51,59,60,63,66,72,80","opentime":"2017-02-21 12:46:10","orderresult":"01,03,04,07,15,21,23,28,30,31,41,44,46,51,59,60,63,66,72,80","first":"1","second":"4","three":"9","result":"1+4+9=14","resultnum":"14","resulttype":"大,双","status":2,"allsec":0,"addtime":"2017-02-21 12:28:49"},{"lotteryid":236,"type":11,"expect":"2107833","opencode":"12,18,19,29,31,33,35,36,39,41,49,50,54,55,61,63,65,72,75,76","opentime":"2017-02-21 12:42:40","orderresult":"12,18,19,29,31,33,35,36,39,41,49,50,54,55,61,63,65,72,75,76","first":"4","second":"4","three":"7","result":"4+4+7=15","resultnum":"15","resulttype":"大,单","status":2,"allsec":0,"addtime":"2017-02-21 12:25:11"},{"lotteryid":235,"type":11,"expect":"2107832","opencode":"05,12,33,37,39,46,47,50,53,54,56,58,59,61,62,65,73,75,76,80","opentime":"2017-02-21 12:39:15","orderresult":"05,12,33,37,39,46,47,50,53,54,56,58,59,61,62,65,73,75,76,80","first":"1","second":"7","three":"8","result":"1+7+8=16","resultnum":"16","resulttype":"大,双","status":2,"allsec":0,"addtime":"2017-02-21 12:21:49"},{"lotteryid":233,"type":11,"expect":"2107831","opencode":"04,07,15,16,22,23,26,29,30,31,34,36,39,40,52,53,67,68,71,78","opentime":"2017-02-21 12:35:42","orderresult":"04,07,15,16,22,23,26,29,30,31,34,36,39,40,52,53,67,68,71,78","first":"9","second":"4","three":"6","result":"9+4+6=19","resultnum":"19","resulttype":"大,单","status":2,"allsec":0,"addtime":"2017-02-21 12:18:19"},{"lotteryid":231,"type":11,"expect":"2107830","opencode":"07,09,10,16,18,19,20,24,27,28,33,34,45,60,62,68,69,76,77,79","opentime":"2017-02-21 12:32:16","orderresult":"07,09,10,16,18,19,20,24,27,28,33,34,45,60,62,68,69,76,77,79","first":"3","second":"8","three":"4","result":"3+8+4=15","resultnum":"15","resulttype":"大,单","status":2,"allsec":0,"addtime":"2017-02-21 12:14:49"},{"lotteryid":230,"type":11,"expect":"2107829","opencode":"09,13,16,20,33,37,40,41,43,46,54,55,62,64,66,69,71,73,75,76","opentime":"2017-02-21 12:28:51","orderresult":"09,13,16,20,33,37,40,41,43,46,54,55,62,64,66,69,71,73,75,76","first":"6","second":"0","three":"2","result":"6+0+2=8","resultnum":"8","resulttype":"小,双","status":2,"allsec":0,"addtime":"2017-02-21 12:11:29"},{"lotteryid":228,"type":11,"expect":"2107828","opencode":"03,08,10,18,19,27,35,40,44,45,47,49,53,60,61,62,67,75,77,80","opentime":"2017-02-21 12:25:19","orderresult":"03,08,10,18,19,27,35,40,44,45,47,49,53,60,61,62,67,75,77,80","first":"1","second":"6","three":"0","result":"1+6+0=7","resultnum":"7","resulttype":"小,单","status":2,"allsec":0,"addtime":"2017-02-21 12:07:49"}]
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
         * lotteryid : 243
         * type : 11
         * expect : 2107837
         * opencode : 05,06,07,10,15,17,25,31,42,44,46,52,53,54,61,64,65,67,68,78
         * opentime : 2017-02-21 12:56:47
         * orderresult : 05,06,07,10,15,17,25,31,42,44,46,52,53,54,61,64,65,67,68,78
         * first : 7
         * second : 6
         * three : 4
         * result : 7+6+4=17
         * resultnum : 17
         * resulttype : 大,单
         * status : 2
         * allsec : 0
         * addtime : 2017-02-21 12:39:19
         */

        private int lotteryid;
        private int type;
        private String expect;
        private String opencode;
        private String opentime;
        private String orderresult;
        private String first;
        private String second;
        private String three;
        private String result;
        private String resultnum;
        private String resulttype;
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

        public String getOpencode() {
            return opencode;
        }

        public void setOpencode(String opencode) {
            this.opencode = opencode;
        }

        public String getOpentime() {
            return opentime;
        }

        public void setOpentime(String opentime) {
            this.opentime = opentime;
        }

        public String getOrderresult() {
            return orderresult;
        }

        public void setOrderresult(String orderresult) {
            this.orderresult = orderresult;
        }

        public String getFirst() {
            return first;
        }

        public void setFirst(String first) {
            this.first = first;
        }

        public String getSecond() {
            return second;
        }

        public void setSecond(String second) {
            this.second = second;
        }

        public String getThree() {
            return three;
        }

        public void setThree(String three) {
            this.three = three;
        }

        public String getResult() {
            return result;
        }

        public void setResult(String result) {
            this.result = result;
        }

        public String getResultnum() {
            return resultnum;
        }

        public void setResultnum(String resultnum) {
            this.resultnum = resultnum;
        }

        public String getResulttype() {
            return resulttype;
        }

        public void setResulttype(String resulttype) {
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

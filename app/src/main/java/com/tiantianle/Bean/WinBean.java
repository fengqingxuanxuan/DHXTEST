package com.tiantianle.Bean;

import java.util.List;

/**
 * Created by PengBo  on 2017/2/24.
 */

public class WinBean {

    /**
     * state : success
     * biz_content : [{"id":1,"lotterynum":"808908","money":10,"luckresult":380,"nickname":"cycle               ","resultnum":"23","totalcount":2},{"id":2,"lotterynum":"808908","money":10,"luckresult":380,"nickname":"633136              ","resultnum":"23","totalcount":2}]
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
         * lotterynum : 808908
         * money : 10
         * luckresult : 380.0
         * nickname : cycle
         * resultnum : 23
         * totalcount : 2
         */

        private int id;
        private String lotterynum;
        private int money;
        private double luckresult;
        private String nickname;
        private String resultnum;
        private int totalcount;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getLotterynum() {
            return lotterynum;
        }

        public void setLotterynum(String lotterynum) {
            this.lotterynum = lotterynum;
        }

        public int getMoney() {
            return money;
        }

        public void setMoney(int money) {
            this.money = money;
        }

        public double getLuckresult() {
            return luckresult;
        }

        public void setLuckresult(double luckresult) {
            this.luckresult = luckresult;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getResultnum() {
            return resultnum;
        }

        public void setResultnum(String resultnum) {
            this.resultnum = resultnum;
        }

        public int getTotalcount() {
            return totalcount;
        }

        public void setTotalcount(int totalcount) {
            this.totalcount = totalcount;
        }
    }
}

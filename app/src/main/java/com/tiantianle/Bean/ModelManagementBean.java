package com.tiantianle.Bean;

import java.util.List;

/**
 * Created by Administrator on 2017/2/23.
 *
 * 模式管理bean
 */
public class ModelManagementBean {
    /**
     * state : success
     * biz_content : [{"id":1,"uid":0,"mid":1,"lotteryid":10,"name":"赚钱模式","status":0,"money":600,"addtime":"2017-02-21 16:47:09","updatetime":"2017-02-23 10:33:30","totalcount":1}]
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
         * uid : 0
         * mid : 1
         * lotteryid : 10
         * name : 赚钱模式
         * status : 0
         * money : 600
         * addtime : 2017-02-21 16:47:09
         * updatetime : 2017-02-23 10:33:30
         * totalcount : 1
         */

        private int id;     //投注类型id
        private int uid;
        private int mid;    //模式id
        private int lotteryid;  //彩种
        private String name;    //名称
        private int status;
        private int money;          //投注总额
        private String addtime;     //添加时间
        private String updatetime;  //修改时间
        private int totalcount;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getUid() {
            return uid;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }

        public int getMid() {
            return mid;
        }

        public void setMid(int mid) {
            this.mid = mid;
        }

        public int getLotteryid() {
            return lotteryid;
        }

        public void setLotteryid(int lotteryid) {
            this.lotteryid = lotteryid;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getMoney() {
            return money;
        }

        public void setMoney(int money) {
            this.money = money;
        }

        public String getAddtime() {
            return addtime;
        }

        public void setAddtime(String addtime) {
            this.addtime = addtime;
        }

        public String getUpdatetime() {
            return updatetime;
        }

        public void setUpdatetime(String updatetime) {
            this.updatetime = updatetime;
        }

        public int getTotalcount() {
            return totalcount;
        }

        public void setTotalcount(int totalcount) {
            this.totalcount = totalcount;
        }
    }
}

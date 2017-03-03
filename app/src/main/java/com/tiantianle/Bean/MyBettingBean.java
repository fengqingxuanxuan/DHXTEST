package com.tiantianle.Bean;

import java.util.List;

/**
 * Created by PengBo  on 2017/2/24.
 */

public class MyBettingBean {

    /**
     * state : success
     * biz_content : [{"bettid":19,"uid":5,"lotteryid":10,"roomid":20,"vipid":24,"bttypeid":11,"lotterynum":"808905","money":10,"isread":true,"addtime":"2017-02-23 15:47:39","updatetime":"2017-02-23 15:51:00","luckresult":-10,"nickname":"237533              ","odds":"288.0"},{"bettid":20,"uid":5,"lotteryid":10,"roomid":20,"vipid":24,"bttypeid":12,"lotterynum":"808905","money":10,"isread":true,"addtime":"2017-02-23 15:47:39","updatetime":"2017-02-23 15:51:00","luckresult":-10,"nickname":"237533              ","odds":"188.0"},{"bettid":29,"uid":5,"lotteryid":10,"roomid":20,"vipid":24,"bttypeid":21,"lotterynum":"808905","money":10,"isread":true,"addtime":"2017-02-23 15:47:39","updatetime":"2017-02-23 15:51:00","luckresult":-10,"nickname":"237533              ","odds":"13.0"},{"bettid":30,"uid":5,"lotteryid":10,"roomid":20,"vipid":24,"bttypeid":22,"lotterynum":"808905","money":10,"isread":true,"addtime":"2017-02-23 15:47:39","updatetime":"2017-02-23 15:51:00","luckresult":-10,"nickname":"237533              ","odds":"13.0"},{"bettid":31,"uid":5,"lotteryid":10,"roomid":20,"vipid":24,"bttypeid":23,"lotterynum":"808905","money":10,"isread":true,"addtime":"2017-02-23 15:47:39","updatetime":"2017-02-23 15:51:00","luckresult":-10,"nickname":"237533              ","odds":"12.0"}]
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
         * bettid : 19
         * uid : 5
         * lotteryid : 10
         * roomid : 20
         * vipid : 24
         * bttypeid : 11
         * lotterynum : 808905
         * money : 10
         * isread : true
         * addtime : 2017-02-23 15:47:39
         * updatetime : 2017-02-23 15:51:00
         * luckresult : -10
         * nickname : 237533
         * odds : 288.0
         */

        private int bettid;
        private int uid;
        private int lotteryid;
        private int roomid;
        private int vipid;
        private int bttypeid;
        private String lotterynum;
        private int money;
        private boolean isread;
        private String addtime;
        private String updatetime;
        private int luckresult;
        private String nickname;
        private String odds;
        private String num;

        public String getNum() {
            return num;
        }

        public void setNum(String num) {
            this.num = num;
        }

        public int getBettid() {
            return bettid;
        }

        public void setBettid(int bettid) {
            this.bettid = bettid;
        }

        public int getUid() {
            return uid;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }

        public int getLotteryid() {
            return lotteryid;
        }

        public void setLotteryid(int lotteryid) {
            this.lotteryid = lotteryid;
        }

        public int getRoomid() {
            return roomid;
        }

        public void setRoomid(int roomid) {
            this.roomid = roomid;
        }

        public int getVipid() {
            return vipid;
        }

        public void setVipid(int vipid) {
            this.vipid = vipid;
        }

        public int getBttypeid() {
            return bttypeid;
        }

        public void setBttypeid(int bttypeid) {
            this.bttypeid = bttypeid;
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

        public boolean isIsread() {
            return isread;
        }

        public void setIsread(boolean isread) {
            this.isread = isread;
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

        public int getLuckresult() {
            return luckresult;
        }

        public void setLuckresult(int luckresult) {
            this.luckresult = luckresult;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getOdds() {
            return odds;
        }

        public void setOdds(String odds) {
            this.odds = odds;
        }
    }
}

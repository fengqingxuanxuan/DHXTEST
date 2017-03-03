package com.tiantianle.Bean;

import java.util.List;

/**
 * Created by PengBo  on 2017/2/17.
 */

public class NewDongTaiBean {
    /**
     * state : success
     * biz_content : [{"id":1,"orderid":90,"ordercode":"63ba5443-3edf-46b7-bdb6-6304d47535cd","warecode":"jdekm","warename":"京东e卡卡密","speccode":"jdekm02","specname":"20元","issuenum":"","status":0,"totalfee":1,"price":1,"type":0,"num":1,"content":"","changeid":0,"createtime":"2017-02-15 17:42:09","nickname":"943353","winname":null,"winnum":null,"usernum":null,"playnum":null,"totalcount":22},{"id":2,"orderid":89,"ordercode":"6b4c0c38-7e07-47f0-ba88-e0bb699a7c9f","warecode":"shkm","warename":"石化油卡卡密","speccode":"shkm04","specname":"200元","issuenum":"","status":0,"totalfee":1,"price":1,"type":0,"num":1,"content":"","changeid":0,"createtime":"2017-02-15 17:41:51","nickname":"943353","winname":null,"winnum":null,"usernum":null,"playnum":null,"totalcount":22},{"id":3,"orderid":88,"ordercode":"43fcc33c-45c6-4a2a-b4df-ac388f4b3bfe","warecode":"shkm","warename":"石化油卡卡密","speccode":"shkm01","specname":"10元","issuenum":"","status":0,"totalfee":1,"price":1,"type":0,"num":1,"content":"","changeid":0,"createtime":"2017-02-15 17:41:45","nickname":"943353","winname":null,"winnum":null,"usernum":null,"playnum":null,"totalcount":22},{"id":4,"orderid":83,"ordercode":"f17ae8da-bfb9-4d5a-ba9a-527ae5482f4b","warecode":"ydkm","warename":"移动卡密","speccode":"ydkm01","specname":"10元","issuenum":"","status":0,"totalfee":1,"price":1,"type":0,"num":1,"content":"","changeid":0,"createtime":"2017-02-15 14:40:33","nickname":"943353","winname":null,"winnum":null,"usernum":null,"playnum":null,"totalcount":22},{"id":5,"orderid":82,"ordercode":"fd044a18-9fb1-4b8d-b341-640fb4eb34a2","warecode":"ydkm","warename":"移动卡密","speccode":"ydkm01","specname":"10元","issuenum":"","status":0,"totalfee":1,"price":1,"type":0,"num":1,"content":"","changeid":0,"createtime":"2017-02-15 14:40:31","nickname":"943353","winname":null,"winnum":null,"usernum":null,"playnum":null,"totalcount":22},{"id":6,"orderid":81,"ordercode":"4d03c340-8461-45e4-a2c2-2c95782def77","warecode":"ydkm","warename":"移动卡密","speccode":"ydkm05","specname":"200元","issuenum":"","status":0,"totalfee":1,"price":1,"type":0,"num":1,"content":"","changeid":0,"createtime":"2017-02-15 14:40:28","nickname":"943353","winname":null,"winnum":null,"usernum":null,"playnum":null,"totalcount":22},{"id":7,"orderid":80,"ordercode":"a217a14f-ed25-49d6-8869-9bbebe97bae4","warecode":"ydkm","warename":"移动卡密","speccode":"ydkm05","specname":"200元","issuenum":"","status":0,"totalfee":1,"price":1,"type":0,"num":1,"content":"","changeid":0,"createtime":"2017-02-15 14:40:26","nickname":"943353","winname":null,"winnum":null,"usernum":null,"playnum":null,"totalcount":22},{"id":8,"orderid":79,"ordercode":"c4bbde96-d58a-4d4d-9258-4ebef84b745c","warecode":"ydkm","warename":"移动卡密","speccode":"ydkm02","specname":"20元","issuenum":"","status":0,"totalfee":1,"price":1,"type":0,"num":1,"content":"","changeid":0,"createtime":"2017-02-15 14:40:23","nickname":"943353","winname":null,"winnum":null,"usernum":null,"playnum":null,"totalcount":22},{"id":9,"orderid":78,"ordercode":"32daa55e-57b8-4eff-b438-c1924dc41366","warecode":"ydkm","warename":"移动卡密","speccode":"ydkm02","specname":"20元","issuenum":"","status":0,"totalfee":1,"price":1,"type":0,"num":1,"content":"","changeid":0,"createtime":"2017-02-15 14:40:21","nickname":"943353","winname":null,"winnum":null,"usernum":null,"playnum":null,"totalcount":22},{"id":10,"orderid":77,"ordercode":"71123ccb-8e31-4eb8-b55c-4d71b1886592","warecode":"ydkm","warename":"移动卡密","speccode":"ydkm02","specname":"20元","issuenum":"","status":0,"totalfee":1,"price":1,"type":0,"num":1,"content":"","changeid":0,"createtime":"2017-02-15 14:40:19","nickname":"943353","winname":null,"winnum":null,"usernum":null,"playnum":null,"totalcount":22}]
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
        private Object winname;
        private Object winnum;
        private Object usernum;
        private Object playnum;
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

        public void setWinname(Object winname) {
            this.winname = winname;
        }

        public Object getWinnum() {
            return winnum;
        }

        public void setWinnum(Object winnum) {
            this.winnum = winnum;
        }

        public Object getUsernum() {
            return usernum;
        }

        public void setUsernum(Object usernum) {
            this.usernum = usernum;
        }

        public Object getPlaynum() {
            return playnum;
        }

        public void setPlaynum(Object playnum) {
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

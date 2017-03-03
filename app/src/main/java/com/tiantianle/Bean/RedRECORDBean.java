package com.tiantianle.Bean;

import java.util.List;

/**
 * Created by Administrator on 2017/2/18.
 *
 * 中奖红包列表bean
 *
 */
public class RedRECORDBean {
    /**
     * state : success
     * biz_content : [{"id":1,"packeid":1," detailid ":1,"dataid":1,"ctime":"2017-02-13 15:38:47","name":"一元红包","price":1000,"num":80,"nickname":"张三","totalcount":1}]
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
         * packeid : 1
         *  detailid  : 1
         * dataid : 1
         * ctime : 2017-02-13 15:38:47
         * name : 一元红包
         * price : 1000
         * num : 80
         * nickname : 张三
         * totalcount : 1
         */

        private int id;         //序号
        private int packeid;    //订单id
        private int detailid;   //明细id
        private int dataid;     //红包id
        private String ctime;   //创建时间
        private String name;    //红包名称
        private int price;      //红包金额
        private int num;
        private String nickname;    //会员昵称
        private int totalcount;     //总条数

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getPackeid() {
            return packeid;
        }

        public void setPackeid(int packeid) {
            this.packeid = packeid;
        }

        public int getDetailid() {
            return detailid;
        }

        public void setDetailid(int detailid) {
            this.detailid = detailid;
        }

        public int getDataid() {
            return dataid;
        }

        public void setDataid(int dataid) {
            this.dataid = dataid;
        }

        public String getCtime() {
            return ctime;
        }

        public void setCtime(String ctime) {
            this.ctime = ctime;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public int getTotalcount() {
            return totalcount;
        }

        public void setTotalcount(int totalcount) {
            this.totalcount = totalcount;
        }
    }
}

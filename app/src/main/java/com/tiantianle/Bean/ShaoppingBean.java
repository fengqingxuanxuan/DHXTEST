package com.tiantianle.Bean;

import java.util.List;

/**
 * Created by PengBo  on 2017/2/16.
 */

public class ShaoppingBean {

    /**
     * state : success
     * biz_content : [{"id":1,"wareid":6,"warecode":"ydkm","warename":"移动卡密","status":0,"type":0,"price":0,"imgsrc":null,"totalcount":3},{"id":2,"wareid":3,"warecode":"shkm","warename":"石化油卡卡密","status":0,"type":0,"price":1000,"imgsrc":null,"totalcount":3},{"id":3,"wareid":2,"warecode":"jdekm","warename":"京东e卡卡密","status":0,"type":0,"price":500,"imgsrc":null,"totalcount":3}]
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
         * wareid : 6
         * warecode : ydkm
         * warename : 移动卡密
         * status : 0
         * type : 0
         * price : 0.0
         * imgsrc : null
         * totalcount : 3
         */

        private int id;
        private int wareid;
        private String warecode;
        private String warename;
        private int status;
        private int type;
        private double price;
        private Object imgsrc;
        private int totalcount;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getWareid() {
            return wareid;
        }

        public void setWareid(int wareid) {
            this.wareid = wareid;
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

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public Object getImgsrc() {
            return imgsrc;
        }

        public void setImgsrc(Object imgsrc) {
            this.imgsrc = imgsrc;
        }

        public int getTotalcount() {
            return totalcount;
        }

        public void setTotalcount(int totalcount) {
            this.totalcount = totalcount;
        }
    }
}

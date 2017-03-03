package com.tiantianle.Bean;

import java.util.List;

/**
 * Created by PengBo  on 2017/2/17.
 */

public class YidongBean {

    /**
     * state : success
     * biz_content : [{"id":1,"specid":13,"warecode":"jdekm","warename":"京东e卡卡密","status":0,"type":0,"price":2000,"imgsrc":null,"speccode":"jdekm05","specname":"200元","usernum":1},{"id":2,"specid":12,"warecode":"jdekm","warename":"京东e卡卡密","status":0,"type":0,"price":1000,"imgsrc":null,"speccode":"jdekm04","specname":"100元","usernum":1},{"id":3,"specid":11,"warecode":"jdekm","warename":"京东e卡卡密","status":0,"type":0,"price":500,"imgsrc":null,"speccode":"jdekm03","specname":"50元","usernum":1},{"id":4,"specid":10,"warecode":"jdekm","warename":"京东e卡卡密","status":0,"type":0,"price":200,"imgsrc":null,"speccode":"jdekm02","specname":"20元","usernum":1},{"id":5,"specid":9,"warecode":"jdekm","warename":"京东e卡卡密","status":0,"type":0,"price":100,"imgsrc":null,"speccode":"jdekm01","specname":"10元","usernum":1}]
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
         * specid : 13
         * warecode : jdekm
         * warename : 京东e卡卡密
         * status : 0
         * type : 0
         * price : 2000.0
         * imgsrc : null
         * speccode : jdekm05
         * specname : 200元
         * usernum : 1
         */

        private int id;
        private int specid;
        private String warecode;
        private String warename;
        private int status;
        private int type;
        private double price;
        private Object imgsrc;
        private String speccode;
        private String specname;
        private int usernum;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getSpecid() {
            return specid;
        }

        public void setSpecid(int specid) {
            this.specid = specid;
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

        public int getUsernum() {
            return usernum;
        }

        public void setUsernum(int usernum) {
            this.usernum = usernum;
        }
    }
}

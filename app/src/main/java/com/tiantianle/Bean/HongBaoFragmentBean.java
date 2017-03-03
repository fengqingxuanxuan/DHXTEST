package com.tiantianle.Bean;

import java.util.List;

/**
 * Created by Administrator on 2017/2/17.
 *
 * 红包列表  bean
 */
public class HongBaoFragmentBean {
    /**
     * state : success
     * biz_content : [{"id":1,"packeid":1,"status":0,"currentcode":null,"detailid":3,"btime":"20:00","etime":"20:30","ctime":"2017-02-13 15:38:47","detailstatus":0,"num":80,"rules":"1:20,2:10,3:10,5:5,10:5","totalcount":3},{"id":2,"packeid":1,"status":0,"currentcode":null,"detailid":2,"btime":"14:00","etime":"14:30","ctime":"2017-02-13 15:38:34","detailstatus":0,"num":50,"rules":"1:16,2:13,3:10,5:7,10:4","totalcount":3},{"id":3,"packeid":1,"status":0,"currentcode":null,"detailid":1,"btime":"10:00","etime":"10:30","ctime":"2017-02-13 15:38:15","detailstatus":0,"num":30,"rules":"1:10,2:8,3:6,5:4,10:2","totalcount":3}]
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
         * status : 0
         * currentcode : null
         * detailid : 3
         * btime : 20:00
         * etime : 20:30
         * ctime : 2017-02-13 15:38:47
         * detailstatus : 0
         * num : 80
         * rules : 1:20,2:10,3:10,5:5,10:5
         * totalcount : 3
         */

        private int id;  //序号
        private int packeid;    //订单id
        private int status;     //状态 0 正常 1 停止
        private Object currentcode; //当前id
        private int detailid;       //明细id
        private String btime;       //开始时间
        private String etime;       //结束时间
        private String ctime;       //创建时间
        private int detailstatus;   //明细状态 0 未开始 1一开始2 已结束
        private int num;            //红包数量
        private String rules;       //详细规格
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

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public Object getCurrentcode() {
            return currentcode;
        }

        public void setCurrentcode(Object currentcode) {
            this.currentcode = currentcode;
        }

        public int getDetailid() {
            return detailid;
        }

        public void setDetailid(int detailid) {
            this.detailid = detailid;
        }

        public String getBtime() {
            return btime;
        }

        public void setBtime(String btime) {
            this.btime = btime;
        }

        public String getEtime() {
            return etime;
        }

        public void setEtime(String etime) {
            this.etime = etime;
        }

        public String getCtime() {
            return ctime;
        }

        public void setCtime(String ctime) {
            this.ctime = ctime;
        }

        public int getDetailstatus() {
            return detailstatus;
        }

        public void setDetailstatus(int detailstatus) {
            this.detailstatus = detailstatus;
        }

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }

        public String getRules() {
            return rules;
        }

        public void setRules(String rules) {
            this.rules = rules;
        }

        public int getTotalcount() {
            return totalcount;
        }

        public void setTotalcount(int totalcount) {
            this.totalcount = totalcount;
        }
    }
}

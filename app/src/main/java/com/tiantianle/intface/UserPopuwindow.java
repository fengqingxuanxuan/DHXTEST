package com.tiantianle.intface;

/**
 * Created by PengBo  on 2017/2/10.
 */

public class UserPopuwindow {
        private  MyInterface mMyInterface;
    public void setMyInterface(MyInterface myInterface){
        mMyInterface=myInterface;
    }
    public void userPopu(int changeid,String issuenum,String warecode,String warename,String speccode,String specname,double price,int type,String ordercode ,int usernum,int playnum){
        mMyInterface.showPopuwindow(changeid,issuenum,warecode,warename,speccode,specname,price,type,ordercode,usernum,playnum);
    }
}

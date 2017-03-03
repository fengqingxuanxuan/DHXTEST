package com.tiantianle.intface;

/**
 * Created by PengBo  on 2017/2/17.
 */

public class UserDuiHuanRemb {
    private DuiHuan mDuiHuan;
    public void setDuiHuan(DuiHuan duiHuan){
        mDuiHuan=duiHuan;
    }
    public  void UserDuiTotal(int totalcount){
        mDuiHuan.DuiHuanRemb(totalcount);
    }

}

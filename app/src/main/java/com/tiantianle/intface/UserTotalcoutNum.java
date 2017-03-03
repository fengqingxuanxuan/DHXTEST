package com.tiantianle.intface;

/**
 * Created by PengBo  on 2017/2/16.
 */

public class UserTotalcoutNum {
    private ShowTotalcount mShowTotalcount;
    public void setShowTotalcout(ShowTotalcount showTotalcout){
        mShowTotalcount=showTotalcout;
    };
    public void  UserTotal(int totalcoutNum){
        mShowTotalcount.TotalcountNum(totalcoutNum);

    }
}

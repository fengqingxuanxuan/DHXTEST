package com.tiantianle.intface;

import java.util.List;
import java.util.Map;

/**
 * Created by PengBo  on 2017/2/23.
 */

public class UserMingDetaile  {
    private MingDetailed mMingDetailed;
    public void setUserMingDetaile(MingDetailed mingDetaile){
        mMingDetailed=mingDetaile;

    }

   /* public  void UserDetaie(String expect,String type,String frist,String second,String three,String resultnum,String time){
        mMingDetailed.MDetailed(expect,type,frist,second,three,resultnum,time);
    }*/
   public  void UserDetaie( List<Map<String,String >> mMaps){
       mMingDetailed.MDetailed(mMaps);
   }
}

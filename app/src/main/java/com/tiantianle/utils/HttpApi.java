package com.tiantianle.utils;

/**
 * Created by Administrator on 2017/2/8.
 * <p>
 * 所有api接口
 */

public interface HttpApi {

  String API = "http://192.168.1.192:8022"; //主地址

//   String API = "http://192.168.1.194:24528/";  //测试地址

    //  宗

    String HOME_PAGE_INFORMATION = API + "/appaccount/homedata"; // 主页信息

    String USER_INFOTMATION = API + "/appaccount/userinfo";// 用户信息

    String LONGIN = API + "/appaccount/login"; //登录

    String REGISTERED = API + "/appaccount/register";//注册

    String YES_NO_TREAD_PASSWORD = API + "/appaccount/existsdrawpd";//是否设置过交易密码

    String RESET_PASSWPRD = API + "/appaccount/resetpwd"; //重置密码

    String MY_MESSAGE = API + "/api/message"; //我的消息

    String MY_MESSAGE_DELETE_ALL = API + "/api/deletemessagebyaccount"; //删除全部消息接口

    String MY_MESSAGE_DELETE = API + "/api/delmessage"; //删除一个消息接口

    String CHANGE_PASSWORD = API + "/appaccount/updatepwd"; //修改登录密码

    String MODIFY_TRADING_PASSWORD = API + "/appaccount/drawpwd"; //修改交易密码

    String GET_CODE = API + "/appaccount/sendsms";//发送短信

    String UPDATE_AVATAR = API + "/appaccount/updateimg"; //更新头像

    String UPDATE_INFORMATION = API +  "/appaccount/updateuserinfo"; // 更新昵称，签名

    String BALANCE = API + "/api/getmoney"; // 余额

    String RED_LIST = API + "/RedPacke/GetRedPackeList";//获取红包列表

    String GRAD_RED_ENVELOPE = API + "/RedPacke/GrabRedPacke"; //抢红包

    String RED_ENVELOPE_RECORD = API + "/RedPacke/GetRedPackeRecord"; // 获取最新红包记录

    String MODEL_RECORDS = API + "/lottery/GetLotteryModelList";  //模式记录获取

    String MODIFY_NAME = API + "/lottery/UpdLotteryModelName";    //修改模式名字

    String DELETE_MODEL = API + "/lottery/UpdLotteryModelStatus";   //删除模式

    // 彭博

    String INDIANAFRAGMENT=API+"/changeware/getchangeware";//夺宝系统
    String BUY_INDIANAFRAGMENT=API+"/changeware/addchangeware";//购买接口
    String  REMB_ALL=API+"/changeware/getuserorder";//夺宝记录 全部
    String SHAOPPING=API+"/changeware/getwarelist";//兑换商城
    String SHAOPPINGREMB=API+"/changeware/getwareskulist";//兑换记录
    String  MINGXI=API+"/changeware/getwareskulist";             //兑换明细





}

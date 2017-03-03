package com.tiantianle.Bean;

/**
 * Created by Administrator on 2017/2/16.
 */
public class UserInformationBean {
    /**
     * state : success
     * biz_content : {"account":"18237730903","nickname":"like","signname":"","avatar":"http://120.25.225.161:828/upload/imgs/defaultcall.png"}
     */

    private String state;
    private BizContentBean biz_content;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public BizContentBean getBiz_content() {
        return biz_content;
    }

    public void setBiz_content(BizContentBean biz_content) {
        this.biz_content = biz_content;
    }

    public static class BizContentBean {
        /**
         * account : 18237730903
         * nickname : like
         * signname :
         * avatar : http://120.25.225.161:828/upload/imgs/defaultcall.png
         */

        private String account; //手机号
        private String nickname; //昵称
        private String signname; //个性签名
        private String avatar;   //图片地址

        public String getAccount() {
            return account;
        }

        public void setAccount(String account) {
            this.account = account;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getSignname() {
            return signname;
        }

        public void setSignname(String signname) {
            this.signname = signname;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }
    }
}

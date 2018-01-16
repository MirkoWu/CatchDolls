package com.softgarden.catchdolls.bean;

import com.google.gson.annotations.SerializedName;
import com.softgarden.baselibrary.utils.EmptyUtil;
import com.softgarden.baselibrary.utils.L;
import com.softgarden.baselibrary.utils.SPUtil;

/**
 * @author by DELL
 * @date on 2017/12/22
 * @describe
 */

public class UserBean {

    public static final String USER_ID = "user_uid";
    public static final String USER_NICKNAME = "user_name";
    public static final String USER_HEADERIMG = "user_headerimg";
    public static final String USER_TOKEN = "user_token";

    @SerializedName(value = "name", alternate = {"nickname"})
    public String name;
    public String uid;
    public String headerImg;
    public String token;
    public String number; //用户ID
    public int sex; //用户性别，0：男，1：女
    public String fullAddress;//收货地址

    public String loginTime;
    public String createTime;
    public String memberGag;
    public String loginType;

    public UserBean(String uid, String name, String headerImg, String token) {
        this.name = name;
        this.uid = uid;
        this.headerImg = headerImg;
        this.token = token;
    }

    public UserBean() {

    }

    private volatile static UserBean userBean;

    public static UserBean getUser() {//实现单例
        if (userBean == null) {
            synchronized (UserBean.class) {
                if (userBean == null) {
                    String uid = (String) SPUtil.get(USER_ID, "");
                    String nickname = (String) SPUtil.get(USER_NICKNAME, "");
                    String headerImg = (String) SPUtil.get(USER_HEADERIMG, "");
                    String token = (String) SPUtil.get(USER_TOKEN, "");
                    userBean = new UserBean(uid, nickname, headerImg, token);
                    L.d(userBean.toString());
                }
            }
        }
        return userBean;
    }

    public static void saveUser(UserBean bean) {
        if (bean == null) return;
        if (getUser() == null) {
            userBean = new UserBean();
        }

        L.d(bean.toString());

        if (EmptyUtil.isNotEmpty(bean.name)) {
            SPUtil.put(USER_NICKNAME, bean.name);
            getUser().name = bean.name;
        }

        if (EmptyUtil.isNotEmpty(bean.headerImg)) {
            SPUtil.put(USER_HEADERIMG, bean.headerImg);
            getUser().headerImg = bean.headerImg;
        }

        if (EmptyUtil.isNotEmpty(bean.uid)) {
            SPUtil.put(USER_ID, bean.uid);
            getUser().uid = bean.uid;
        }

        if (EmptyUtil.isNotEmpty(bean.token)) {
            SPUtil.put(USER_TOKEN, bean.token);
            getUser().token = bean.token;
        }

    }

    public static void clear() {
        getUser().uid = null;
        getUser().name = null;
        getUser().headerImg = null;
        getUser().token = null;
        SPUtil.remove(USER_NICKNAME);
        SPUtil.remove(USER_HEADERIMG);
        SPUtil.remove(USER_ID);
        SPUtil.remove(USER_TOKEN);
    }

    @Override
    public String toString() {
        return "UserBean{" +
                "name='" + name + '\'' +
                ", uid='" + uid + '\'' +
                ", headerImg='" + headerImg + '\'' +
                ", token='" + token + '\'' +
                ", number='" + number + '\'' +
                ", sex=" + sex +
                ", fullAddress='" + fullAddress + '\'' +
                '}';
    }
}

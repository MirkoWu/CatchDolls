package com.softgarden.catchdolls.utils;

import android.app.Activity;
import android.text.TextUtils;

import com.softgarden.baselibrary.utils.EmptyUtil;
import com.softgarden.baselibrary.utils.RegularUtil;
import com.softgarden.baselibrary.utils.ToastUtil;
import com.softgarden.catchdolls.R;
import com.softgarden.catchdolls.bean.AddressBean;
import com.softgarden.catchdolls.bean.UserBean;
import com.softgarden.catchdolls.ui.login.LoginActivity;

/**
 * @author by DELL
 * @date on 2017/12/22
 * @describe
 */

public class CheckUtil {

    public static boolean checkIsLogin(Activity activity) {
        if (UserBean.getUser() != null && EmptyUtil.isNotEmpty(UserBean.getUser().uid)) {
            return true;
        } else {
            ToastUtil.s("请先登录");
            LoginActivity.Companion.start(activity);
            return false;
        }

    }

    public static boolean checkEmpty(String str, String hint) {
        if (EmptyUtil.isEmpty(str)) {
            ToastUtil.s(hint);
            return true;
        }
        return false;
    }

    /**
     * 检查手机号码是否非法
     *
     * @param str
     * @return
     */
    public static boolean checkPhoneIllegal(String str) {
        if (EmptyUtil.isEmpty(str)) {
            ToastUtil.s(R.string.input_phone_num_please);
            return true;
        }
        if (!RegularUtil.isMobileSimple(str)) {
            ToastUtil.s(R.string.phonenum_illegal);
            return true;
        }
        return false;
    }

    /**
     * 检查登录密码是否非法
     *
     * @param str
     * @return
     */
    public static boolean checkPwdIllegal(String str) {
        if (EmptyUtil.isEmpty(str)) {
            ToastUtil.s(R.string.input_pwd);
            return true;
        }
        if (!RegularUtil.isPassword(str)) {
            ToastUtil.s(R.string.pwd_rule);
            return true;
        }
        return false;
    }

    /**
     * 检查邮箱是否非法
     *
     * @param str
     * @return
     */
    public static boolean checkEmailIllegal(String str) {
        if (EmptyUtil.isEmpty(str)) {
            ToastUtil.s(R.string.input_email);
            return true;
        }
        if (!RegularUtil.isEmail(str)) {
            ToastUtil.s(R.string.email_illegal);
            return true;
        }
        return false;
    }

    public static String checkAddress(AddressBean bean) {
        if (bean == null) return null;
        StringBuilder address = new StringBuilder(bean.provinceName);
        if (!TextUtils.equals(bean.provinceName, bean.cityName)) {
            address.append(bean.cityName);
            if (!TextUtils.equals(bean.provinceName, bean.areaName)) {
                address.append(bean.areaName);
            }
        } else {
            if (!TextUtils.equals(bean.provinceName, bean.areaName)) {
                address.append(bean.areaName);
            }
        }
        return address.toString();
    }

}

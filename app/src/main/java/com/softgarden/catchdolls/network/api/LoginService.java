package com.softgarden.catchdolls.network.api;

import com.softgarden.catchdolls.bean.UserBean;
import com.softgarden.catchdolls.network.BaseBean;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

import static com.softgarden.catchdolls.network.api.HostUrl.LOGIN_APP_LOGIN;
import static com.softgarden.catchdolls.network.api.HostUrl.LOGIN_CHECK_CAPTCHA;
import static com.softgarden.catchdolls.network.api.HostUrl.LOGIN_GET_CAPTCHA;
import static com.softgarden.catchdolls.network.api.HostUrl.LOGIN_REGISTER;
import static com.softgarden.catchdolls.network.api.HostUrl.LOGIN_RESET_PASSWORD;
import static com.softgarden.catchdolls.network.api.HostUrl.LOGIN_UPDATE_PWD;


public interface LoginService {

    /**
     * 登录
     *
     * @param loginType （必须）登录类型，1：QQ, 2: 微信, 3：微博, 4：手机号
     * @param phone     （非第三方登录）手机号
     * @param password  （非第三方登录）密码
     * @return
     */
    @FormUrlEncoded
    @POST(LOGIN_APP_LOGIN)
    Observable<BaseBean<UserBean>> loginPhone(@Field("loginType") int loginType,
                                              @Field("phone") String phone,
                                              @Field("password") String password
    );
    /**
     * 登录
     *
     * @param loginType （必须）登录类型，1：QQ, 2: 微信, 3：微博, 4：手机号
     * @param openid    （第三方登录）第三方登录时返回的唯一标识
     * @param name      （第三方登录）用户名
     * @param headerImg （第三方登录）用户头像
     * @return
     */
    @FormUrlEncoded
    @POST(LOGIN_APP_LOGIN)
    Observable<BaseBean<String>> loginThridParty(@Field("loginType") int loginType,
                                                 @Field("openid") String openid,
                                                 @Field("name") String name,
                                                 @Field("headerImg") String headerImg);

    /**
     * 获取验证码
     *
     * @param phone       （必须）手机号
     * @param captchaType （必须）验证码类型，1：注册用户，2：忘记密码
     * @return
     */
    @FormUrlEncoded
    @POST(LOGIN_GET_CAPTCHA)
    Observable<BaseBean<String>> getVerifyCode(@Field("phone") String phone,
                                               @Field("captchaType") int captchaType);

    /**
     * 校验验证码
     *
     * @param phone       （必须）手机号
     * @param captchaType （必须）验证码类型，1：注册用户，2：忘记密码
     * @param captcha     （必须）验证码
     * @return
     */
    @FormUrlEncoded
    @POST(LOGIN_CHECK_CAPTCHA)
    Observable<BaseBean<String>> checkVerifyCode(@Field("phone") String phone,
                                                 @Field("captchaType") int captchaType,
                                                 @Field("captcha") String captcha);

    /**
     * 注册
     *
     * @param phone    （必须）手机号
     * @param password （必须）密码
     * @param captcha  （必须）验证码
     * @return
     */
    @FormUrlEncoded
    @POST(LOGIN_REGISTER)
    Observable<BaseBean<UserBean>> register(@Field("phone") String phone,
                                          @Field("password") String password,
                                          @Field("captcha") String captcha);

    /**
     * 重置密码
     *
     * @param phone    （必须）手机号
     * @param password （必须）密码
     * @param captcha  （必须）验证码
     * @return
     */
    @FormUrlEncoded
    @POST(LOGIN_RESET_PASSWORD)
    Observable<BaseBean<String>> resetPwd(@Field("phone") String phone,
                                          @Field("password") String password,
                                          @Field("captcha") String captcha);

    /**
     * 修改密码
     *
     * @param password    （必须）新密码
     * @param oldPassword （必须）旧密码
     * @return
     */
    @FormUrlEncoded
    @POST(LOGIN_UPDATE_PWD)
    Observable<BaseBean<String>> updatePwd(
            @Field("password") String password,
            @Field("oldPassword") String oldPassword);


}

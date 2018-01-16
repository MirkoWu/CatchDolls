package com.softgarden.catchdolls.network.api;

import com.softgarden.catchdolls.network.BaseBean;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

import static com.softgarden.catchdolls.network.api.HostUrl.MORE_SUBMIT_FEEDBACK;

/**
 * @author by DELL
 * @date on 2018/1/5
 * @describe
 */

public interface MoreService {
    /**
     * 提交用户反馈信息
     *
     * @param type       联系方式的类型，0邮箱，1QQ，2微信
     * @param content    （必须）反馈内容
     * @param contactWay （必须）联系方式
     * @return
     */
    @FormUrlEncoded
    @POST(MORE_SUBMIT_FEEDBACK)
    Observable<BaseBean<Object>> submitFeedback(@Field("type") int type,
                                                @Field("content") String content,
                                                @Field("contactWay") String contactWay
    );
}

package com.softgarden.catchdolls.network.api;

import com.softgarden.catchdolls.bean.AddressBean;
import com.softgarden.catchdolls.bean.RegionBean;
import com.softgarden.catchdolls.bean.UserBean;
import com.softgarden.catchdolls.network.BaseBean;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

import static com.softgarden.catchdolls.network.api.HostUrl.USER_ADDRESSLIST;
import static com.softgarden.catchdolls.network.api.HostUrl.USER_ADDRESS_DEL;
import static com.softgarden.catchdolls.network.api.HostUrl.USER_ADDRESS_REGIONINFO;
import static com.softgarden.catchdolls.network.api.HostUrl.USER_ADDRESS_UPDATE;
import static com.softgarden.catchdolls.network.api.HostUrl.USER_INFO;
import static com.softgarden.catchdolls.network.api.HostUrl.USER_INFO_DETAIL;
import static com.softgarden.catchdolls.network.api.HostUrl.USER_INFO_UPDATE;

/**
 * @author by DELL
 * @date on 2018/1/5
 * @describe
 */

public interface UserService {

    /**
     * 获取个人信息
     *
     * @return
     */
    @POST(USER_INFO)
    Observable<BaseBean<UserBean>> getUserInfo();

    /**
     * 获取个人信息 详情
     *
     * @return
     */
    @POST(USER_INFO_DETAIL)
    Observable<BaseBean<UserBean>> getUserInfoDetail();

    /**
     * 修改个人信息
     *
     * @param nickname
     * @param headerImg
     * @param sex       用户性别，0：男，1：女
     * @return
     */
    @FormUrlEncoded
    @POST(USER_INFO_UPDATE)
    Observable<BaseBean<Object>> updateUserInfo(@Field("nickname") String nickname,
                                                @Field("headerImg") String headerImg,
                                                @Field("sex") String sex
    );

    /**
     * 获取寄送地址列表
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    @FormUrlEncoded
    @POST(USER_ADDRESSLIST)
    Observable<BaseBean<List<AddressBean>>> getAddressList(@Field("pageNum") int pageNum,
                                                           @Field("pageSize") int pageSize
    );


    /**
     * 添加或修改收货地址
     *
     * @param id          （必须）新增时ID传0， 修改传地址ID
     * @param provinceId  int	（必须）省ID
     * @param cityId      int	（必须）市ID
     * @param areaId      int	（必须）区ID
     * @param address     String	（必须）地址
     * @param userName    String	（必须）用户名称
     * @param phone       String	（必须）电话
     * @param fullAddress ss	String	（必须）完整的地址
     * @return
     */
    @FormUrlEncoded
    @POST(USER_ADDRESS_UPDATE)
    Observable<BaseBean<List<AddressBean>>> addOrUpdateAddress(@Field("id") String id,
                                                               @Field("provinceId") String provinceId,
                                                               @Field("cityId") String cityId,
                                                               @Field("areaId") String areaId,
                                                               @Field("address") String address,
                                                               @Field("userName") String userName,
                                                               @Field("phone") String phone,
                                                               @Field("fullAddress") String fullAddress
    );

    /**
     * 删除地址
     *
     * @param id
     * @return
     */
    @FormUrlEncoded
    @POST(USER_ADDRESS_DEL)
    Observable<BaseBean<Object>> delAddress(@Field("id") String id
    );

    /**
     * 获取省市区地址列表
     *
     * @param parentId 获取省信息传1，获取市信息传省ID，获取区信息传市ID
     * @return
     */
    @FormUrlEncoded
    @POST(USER_ADDRESS_REGIONINFO)
    Observable<BaseBean<List<RegionBean>>> getRegionInfo(@Field("parentId") String parentId
    );


}

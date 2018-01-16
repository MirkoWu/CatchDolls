package com.softgarden.catchdolls.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author by DELL
 * @date on 2018/1/5
 * @describe
 */

public class AddressBean implements Parcelable {

    /**
     * address : 测试测试车测试
     * areaId : 1742
     * areaName : 冷水滩区
     * cityId : 208
     * cityName : 永州
     * fullAddress : 是打发士大夫
     * id : 100029
     * phone : 12131231211
     * provinceId : 14
     * provinceName : 湖南
     * status : 1
     * userName : 测试-张三
     */

   public String address;
   public String areaId;
   public String areaName;
   public String cityId;
   public String cityName;
   public String fullAddress;
   public String id;
   public String phone;
   public String provinceId;
   public String provinceName;
   public String status;
   public String userName;

    public AddressBean(String provinceName , String cityName,String areaName ) {
        this.areaName = areaName;
        this.cityName = cityName;
        this.provinceName = provinceName;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.address);
        dest.writeString(this.areaId);
        dest.writeString(this.areaName);
        dest.writeString(this.cityId);
        dest.writeString(this.cityName);
        dest.writeString(this.fullAddress);
        dest.writeString(this.id);
        dest.writeString(this.phone);
        dest.writeString(this.provinceId);
        dest.writeString(this.provinceName);
        dest.writeString(this.status);
        dest.writeString(this.userName);
    }

    public AddressBean() {
    }

    protected AddressBean(Parcel in) {
        this.address = in.readString();
        this.areaId = in.readString();
        this.areaName = in.readString();
        this.cityId = in.readString();
        this.cityName = in.readString();
        this.fullAddress = in.readString();
        this.id = in.readString();
        this.phone = in.readString();
        this.provinceId = in.readString();
        this.provinceName = in.readString();
        this.status = in.readString();
        this.userName = in.readString();
    }

    public static final Parcelable.Creator<AddressBean> CREATOR = new Parcelable.Creator<AddressBean>() {
        @Override
        public AddressBean createFromParcel(Parcel source) {
            return new AddressBean(source);
        }

        @Override
        public AddressBean[] newArray(int size) {
            return new AddressBean[size];
        }
    };
}

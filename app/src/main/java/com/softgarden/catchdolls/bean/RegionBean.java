package com.softgarden.catchdolls.bean;

import com.bigkoo.pickerview.model.IPickerViewData;

/**
 * @author by DELL
 * @date on 2018/1/6
 * @describe
 */

public class RegionBean implements IPickerViewData {
    public int id;//当前id
    public String name;//地区名称
    public int parent_id;//父id
    public int type;//地区类型(1:省,2:市,3:区)

    public RegionBean() {
    }

    public RegionBean(String name) {
        this.name = name;
    }


    @Override
    public String toString() {
        return "RegionBean{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", parent_id=" + parent_id +
                ", type=" + type +
                '}';
    }

    @Override
    public String getPickerViewText() {
        return name;
    }
}

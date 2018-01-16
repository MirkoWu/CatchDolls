package com.softgarden.catchdolls.ui.userCenter.userInfo.address

import android.app.Activity
import android.content.Intent
import android.view.View
import com.bigkoo.pickerview.OptionsPickerView
import com.softgarden.baselibrary.utils.EmptyUtil
import com.softgarden.baselibrary.utils.L
import com.softgarden.baselibrary.utils.ToastUtil
import com.softgarden.baselibrary.widget.CommonToolbar
import com.softgarden.catchdolls.R
import com.softgarden.catchdolls.base.ToolbarActivity
import com.softgarden.catchdolls.bean.AddressBean
import com.softgarden.catchdolls.bean.RegionBean
import com.softgarden.catchdolls.db.DBHelper
import com.softgarden.catchdolls.db.DBManager
import com.softgarden.catchdolls.network.LocalTransformer
import com.softgarden.catchdolls.network.NetworkTransformer
import com.softgarden.catchdolls.network.RetrofitManager
import com.softgarden.catchdolls.network.SampleCallback
import com.softgarden.catchdolls.utils.CheckUtil
import io.reactivex.Observable
import io.reactivex.ObservableOnSubscribe
import kotlinx.android.synthetic.main.activity_edit_address.*
import java.util.*

class EditAddressActivity : ToolbarActivity(), OptionsPickerView.OnOptionsSelectListener {


    companion object {

        fun start(context: Activity, requestCode: Int) {
            val starter = Intent(context, EditAddressActivity::class.java)
            //starter.putExtra("", "")
            context.startActivityForResult(starter, requestCode)
        }

        fun start(context: Activity, requestCode: Int, bean: AddressBean) {
            val starter = Intent(context, EditAddressActivity::class.java)
            starter.putExtra("AddressBean", bean)
            context.startActivityForResult(starter, requestCode)
        }
    }

    private var bean: AddressBean? = null
    private var pickerView: OptionsPickerView<RegionBean>? = null

    private var provinceList: List<RegionBean>? = null
    private var cityList: List<List<RegionBean>>? = null
    private var areaList: List<List<List<RegionBean>>>? = null

    private var dbManager: DBManager? = null

    override fun getLayoutId() = R.layout.activity_edit_address

    override fun setToolbar(builder: CommonToolbar.Builder): CommonToolbar.Builder? {
        return builder
    }

    override fun initialize() {
        initDB()

        bean = intent.getParcelableExtra("AddressBean")
        if (bean == null) {
            toolbar.title = "新建寄送地址"
        } else {
            toolbar.title = "编辑寄送地址"
            initData(bean)
        }
        pickerView = OptionsPickerView.Builder(this, this).build() as OptionsPickerView<RegionBean>?
        tvRegion.setOnClickListener { selectRegion() }
        btnSave.setOnClickListener { saveAddress(bean) }
    }

    private fun initDB() {
        provinceList = ArrayList()
        cityList = ArrayList()
        cityList
        areaList = ArrayList()

        val dbHelper = DBHelper(this)
        dbManager = DBManager(dbHelper.writableDatabase)
    }


    private fun initData(bean: AddressBean?) {
        edtName.setText(bean!!.userName)
        edtPhone.setText(bean.phone)
        tvRegion.text = CheckUtil.checkAddress(bean)
        edtDetailAddress.setText(bean.address)
    }


    private fun selectRegion() {
        Observable.create(ObservableOnSubscribe<Any> { e ->
            run {
                queryRegions()
                e.onNext("")
            }
        }).compose(LocalTransformer(this))
                .subscribe(object : SampleCallback<Any>() {
                    override fun onSuccess(data: Any?) {
                        runOnUiThread {
                            pickerView!!.setPicker(provinceList, cityList, areaList)
                            pickerView!!.show()
                        }
                    }

                    override fun onFinish() {
                       hideProgressDialog()
                    }
                })
    }

    private fun queryRegions() {
        val cityList = ArrayList<List<RegionBean>>()
        val areaList = ArrayList<List<List<RegionBean>>>()

        val pro_list = dbManager!!.queryRegions(1, 1)
        L.d(pro_list.toString())
        for (proviceBean in pro_list) {
            val pro_cityList = ArrayList<RegionBean>()
            val pro_regionList = ArrayList<List<RegionBean>>()
            val city_list = dbManager!!.queryRegions(2, proviceBean.id)
            for (cityBean in city_list) {
                pro_cityList.add(cityBean)
                val city_list = dbManager!!.queryRegions(3, cityBean.id)

                val city_regionList = ArrayList<RegionBean>()
                if (city_list == null || city_list.isEmpty()) {
                    city_regionList.add(RegionBean(""))
                } else {
                    city_regionList.addAll(city_list)
                }
                pro_regionList.add(city_regionList)
            }
            cityList.add(pro_cityList)
            areaList.add(pro_regionList)
        }

        this.provinceList = pro_list
        this.cityList = cityList
        this.areaList = areaList
    }


    override fun onOptionsSelect(options1: Int, options2: Int, options3: Int, v: View?) {
        if (bean == null) bean = AddressBean()

        val provinceBean = provinceList!![options1]
        val cityBean = cityList!![options1][options2]
        var areaName = ""
        if (EmptyUtil.isEmpty(areaList!![options1][options2])) {
            bean!!.areaId = null
        } else {
            val areaBean = areaList!![options1][options2][options3]
            bean!!.areaId = areaBean.id.toString()
            areaName = areaBean.name
        }

        bean!!.provinceId = provinceBean.id.toString()
        bean!!.cityId = cityBean.id.toString()
        tvRegion.text = CheckUtil.checkAddress(AddressBean(provinceBean.name, cityBean.name, areaName))
    }

    private fun saveAddress(data: AddressBean?) {
        var bean = data
        if (bean == null) bean = AddressBean()

        bean.userName = edtName.text.toString().trim()
        bean.phone = edtPhone.text.toString().trim()
        bean.address = edtDetailAddress.text.toString().trim()
        val region = tvRegion.text.toString().trim()

        if (CheckUtil.checkEmpty(bean.userName, "请输入收货人")) return
        if (CheckUtil.checkEmpty(bean.phone, "请输入联系方式")) return
        if (CheckUtil.checkPhoneIllegal(bean.phone)) return
        if (CheckUtil.checkEmpty(region, "请选择所在地址")) return
        if (CheckUtil.checkEmpty(bean.address, "请输入详细地址")) return


        RetrofitManager.getUserService()
                .addOrUpdateAddress(
                        bean!!.id,
                        bean.provinceId,
                        bean.cityId,
                        bean.areaId,
                        bean.address,
                        bean.userName,
                        bean.phone,
                        region + bean.address)
                .compose(NetworkTransformer(this))
                .subscribe(object : SampleCallback<Any>() {
                    override fun onSuccess(data: Any?) {
                        ToastUtil.s(R.string.save_succeed)
                        setResult(RESULT_OK)
                        finish()
                    }
                })
    }

}

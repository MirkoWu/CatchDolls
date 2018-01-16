package com.softgarden.catchdolls.ui.home

import com.softgarden.baselibrary.base.BaseRVAdapter
import com.softgarden.baselibrary.base.BaseRVHolder
import com.softgarden.catchdolls.R
import com.softgarden.catchdolls.bean.GoodsBean

/**
 * @author by DELL
 * @date on 2017/12/21
 * @describe
 */

class HomeAdapter : BaseRVAdapter<GoodsBean>(R.layout.item_home) {

    override fun onBindVH(holder: BaseRVHolder, data: GoodsBean, position: Int) {
       // ImageUtil.load(holder.getView(R.id.ivGoods), "")
        holder.setImageResource(R.id.ivGoods, R.mipmap.ic_launcher)
    }
}

package com.start.retrofitlibrarytest_20220610.adpaters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.start.retrofitlibrarytest_20220610.R
import com.start.retrofitlibrarytest_20220610.datas.ProductData

class ProductAdapter(
    val mContext: Context,
     resId: Int,
    val mList: List<ProductData>): ArrayAdapter<ProductData>(mContext, resId, mList) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var tempRow = convertView
        if(tempRow == null){
            tempRow = LayoutInflater.from(mContext).inflate(R.layout.fragment_product_list, null)
        }

        val row = tempRow!!
        return row
    }
}
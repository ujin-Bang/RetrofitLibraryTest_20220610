package com.start.retrofitlibrarytest_20220610.adpaters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.core.graphics.createBitmap
import com.bumptech.glide.Glide
import com.start.retrofitlibrarytest_20220610.R
import com.start.retrofitlibrarytest_20220610.datas.ProductData

class ProductAdapter(
    val mContext: Context,
     resId: Int,
    val mList: List<ProductData>): ArrayAdapter<ProductData>(mContext, resId, mList) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var tempRow = convertView
        if(tempRow == null){
            tempRow = LayoutInflater.from(mContext).inflate(R.layout.product_list_item, null)
        }

        val row = tempRow!!

        val data = mList[position]

        val imgStoreLogo = row.findViewById<ImageView>(R.id.imgStoreLogo)
        val txtStoreName = row.findViewById<TextView>(R.id.txtStoreName)
        val txtProductName = row.findViewById<TextView>(R.id.txtProductName)
        val txtCategoryName = row.findViewById<TextView>(R.id.txtCategoryName)

        Glide.with(mContext).load(data.image_url).into(imgStoreLogo)
        txtProductName.text = data.name


        return row
    }
}
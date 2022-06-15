package com.start.retrofitlibrarytest_20220610.datas

import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.text.NumberFormat
import java.util.*

class ProductData(
    var id: Int,
    var name: String,
    var price: Int,
    var image_url: String,
    @SerializedName("store_id")
    var storeId: Int,

    var store: StoreData,

// 모든상품조회시 사용
    @SerializedName("small_category")
    var smallCategory: SmallCategoryData,

): Serializable {

    fun getFormattedPrice(): String{

        return "${NumberFormat.getInstance(Locale.KOREA).format(this.price)}원"
    }

}
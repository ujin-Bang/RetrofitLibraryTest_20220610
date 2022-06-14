package com.start.retrofitlibrarytest_20220610.datas

import com.google.gson.annotations.SerializedName

class ProductData(
    var id: Int,
    var name: String,
    var price: Int,
    var image_url: String,
    @SerializedName("store_id")
    var storeId: Int,

    var store: StoreData,

) {
}
package com.start.retrofitlibrarytest_20220610.datas

import com.google.gson.annotations.SerializedName

class StoreData(
    var id: Int,
    var name: String,
    @SerializedName("logo_url")
    var logoURL: String,
) {
}
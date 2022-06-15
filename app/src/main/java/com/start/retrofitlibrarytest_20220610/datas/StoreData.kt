package com.start.retrofitlibrarytest_20220610.datas

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class StoreData(
    var id: Int,
    var name: String,
    @SerializedName("logo_url")
    var logoURL: String,
) : Serializable{
}
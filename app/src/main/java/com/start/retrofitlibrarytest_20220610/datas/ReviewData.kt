package com.start.retrofitlibrarytest_20220610.datas

import android.view.inspector.IntFlagMapping
import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.util.*

class ReviewData(
    var id: Int,
    var title: String,
    var content: String,
    var score: Float,
    var user: UserData,
    var product: ProductData,
    @SerializedName("created_at")
    var createdAt: Date,

): Serializable {
}
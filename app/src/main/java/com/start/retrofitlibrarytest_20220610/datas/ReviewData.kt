package com.start.retrofitlibrarytest_20220610.datas

import android.view.inspector.IntFlagMapping
import java.io.Serializable

class ReviewData(
    var id: Int,
    var title: String,
    var content: String,
    var score: Float,
    var user: UserData,
    var product: ProductData,

): Serializable {
}
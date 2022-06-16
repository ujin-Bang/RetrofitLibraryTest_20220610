package com.start.retrofitlibrarytest_20220610.datas

import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.util.*

class UserData(
    var id : Int,
    var provider: String,
    var uid: String?,
    var email : String,

//    서버의 이름표와 변수의 이름이 다를 때
    @SerializedName("nick_name")
    var nickname : String,
    @SerializedName("profile_img")
    var profileImageURL: String,
    @SerializedName("created_at")
    var createdAt: Date,

):Serializable {
}
package com.start.retrofitlibrarytest_20220610.api

import com.start.retrofitlibrarytest_20220610.datas.BasicResponse
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.*

interface ServerAPIService {

//    로그인 기능 명세
    @FormUrlEncoded
    @POST("/user")
    fun postRequestLogin(
        @Field("email") email: String,
        @Field("password") pw: String,
    ) : Call<BasicResponse>


//    회원가입 기능
    @FormUrlEncoded
    @PUT("/user")
    fun putRequestSignUp(
        @Field("email") email: String,
        @Field("password") pw: String,
        @Field("nick_name") nick: String,
    ) : Call<BasicResponse>


//    이메일/닉네임 중복 확인 기능.
    @GET("/user/check")
    fun getRequestDuplicatedCheck(
        @Query("type") type: String,
        @Query("value") value: String,
    ) : Call<BasicResponse>


//    소셜 로그인 기능 - POST
    @FormUrlEncoded
    @POST("/user/social")
    fun postRequestSocialLogin(
        @Field("provider") provider: String,
        @Field("uid") uid: String,
        @Field("nick_name") name: String,
    ): Call<BasicResponse>


//    내 정보조회 - GET
    @GET("/user")
    fun getRequestMyInfo(): Call<BasicResponse>


//    상품목록 보기 - GET ,아무 파라미터도 없음 (서버의 임시API)
    @GET("product")
    fun getRequestProductInfo()
    : Call<BasicResponse>


//    소분류 전체목록 받아오기 -GET / 아무 파라미터 없음(서버의 임시API)
    @GET("/category/small")
    fun getRequestSmallCategory() : Call<BasicResponse>


//    닉네임 변경
    @FormUrlEncoded
    @PATCH("/user")
    fun patchRequestEditUserInfo(
        @Field("field") field: String,
        @Field("value") value: String,
    ): Call<BasicResponse>


//  리뷰 작성
    @FormUrlEncoded
    @POST("/review")
    fun postRequestReview(
        @Field("product_id") productId: Int,
        @Field("title") title: String,
        @Field("content") content: String,
        @Field("score") rating: Float,
        @Field("tag_list") tag: String,
    ): Call<BasicResponse>


//    전체 리뷰 목록(임시)
    @GET("/review")
    fun getRequestReview(): Call<BasicResponse>


//    프로필 사진첨부 -> Field대신 Multipart 사용
    @Multipart
    @PUT("/user/image")
    fun putRequestProfileImg(
        @Part img:MultipartBody.Part
    ): Call<BasicResponse>
}

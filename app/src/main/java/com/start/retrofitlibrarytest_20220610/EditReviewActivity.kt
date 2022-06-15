package com.start.retrofitlibrarytest_20220610

import android.os.Bundle
import android.util.Log
import android.view.animation.BounceInterpolator
import androidx.databinding.DataBindingUtil
import com.start.retrofitlibrarytest_20220610.databinding.ActivityEditReviewBinding
import com.start.retrofitlibrarytest_20220610.datas.BasicResponse
import com.start.retrofitlibrarytest_20220610.datas.ProductData
import com.start.retrofitlibrarytest_20220610.utils.GlobalData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*


class EditReviewActivity : BaseActivity() {

    lateinit var binding: ActivityEditReviewBinding

    lateinit var mProductData: ProductData

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_edit_review)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {

        binding.btnWrite.setOnClickListener {

            val inputTitle = binding.edtReviewTitle.text.toString()
            val inputContent = binding.edtContent.text.toString()
            val inputTeg = binding.edtTeg.text.toString()

//            몇점 입력?
            val rating = binding.reviewRatingbar.rating
            Log.d("평점 점수", rating.toString())

            apiService.postRequestReview(mProductData.id, inputTitle,inputContent,rating,inputTeg).enqueue(object :Callback<BasicResponse>{
                override fun onResponse(
                    call: Call<BasicResponse>,
                    response: Response<BasicResponse>
                ) {
                    if(response.isSuccessful){
                        val br = response.body()!!
                        Log.d("리뷰작성응답",br.toString())
                    }
                }

                override fun onFailure(call: Call<BasicResponse>, t: Throwable) {
                }

            })

        }

    }

    override fun setValues() {

        mProductData = intent.getSerializableExtra("product") as ProductData

        binding.txtProductName.text = mProductData.name
        binding.txtUserNickname.text = GlobalData.loginUser!!.nickname

//        오늘날짜 -> 2021.1.5 형태로 가공 -> 텍스트뷰에 반영

//        1. 오늘 날짜?
        //현재일시 자동 기록
        val now = Calendar.getInstance()
//        원하는 형태로 현재일시를 가공.(String으로)
        val sdf = SimpleDateFormat("yyyy.M.d")
        val nowString = sdf.format(now.time)

        binding.txtToday.text = nowString

    }

}
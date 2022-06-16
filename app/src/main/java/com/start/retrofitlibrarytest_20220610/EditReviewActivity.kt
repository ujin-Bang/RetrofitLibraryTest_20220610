package com.start.retrofitlibrarytest_20220610

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.animation.BounceInterpolator
import android.widget.TextView
import androidx.core.widget.addTextChangedListener
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
import kotlin.collections.ArrayList


class EditReviewActivity : BaseActivity() {

    lateinit var binding: ActivityEditReviewBinding

    lateinit var mProductData: ProductData

    val mInputTagList = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_edit_review)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {

//        한글자 입력할때마다 -> 스페이스를 넣었는지 검사.
        binding.edtTeg.addTextChangedListener {

            val nowText = it.toString()

            if(nowText ==""){
//                빈칸일때는 밑의 코드실행 X
                return@addTextChangedListener
            }

            Log.d("입력값",nowText)

//            지금 입력된 내용의 마지막 글자가 ' '인가?
            if(nowText.last() == ' ' ){
                Log.d("입력값", "스페이스가 들어옴")

//            입력된 값을 태그로 등록
//            태그로 등록될 문구 => " "공백 제거
                val tag = nowText.replace(" ","")

//            태그목록으로 추가해보자.
                mInputTagList.add( tag )

//                태그목록 보여줄 레이아웃에 텍스트뷰를 생성 => xml이 아닌 코틀린에서 텍스트뷰 생성하기
                val tagTextView = TextView(mContext)
                tagTextView.text = tag

                binding.tagListLayout.addView(tagTextView)

//                입력값 초기화.
                binding.edtTeg.setText("")

            }
        }

        binding.btnWrite.setOnClickListener {

            for(tag in mInputTagList){
                Log.d("입력태그",tag)
            }

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

                        val myIntent = Intent(mContext, MainActivity::class.java)
                        startActivity(myIntent)

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
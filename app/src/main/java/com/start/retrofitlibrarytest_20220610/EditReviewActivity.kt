package com.start.retrofitlibrarytest_20220610

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.start.retrofitlibrarytest_20220610.databinding.ActivityEditReviewBinding
import com.start.retrofitlibrarytest_20220610.databinding.ActivityMainBinding
import com.start.retrofitlibrarytest_20220610.datas.ProductData
import com.start.retrofitlibrarytest_20220610.utils.GlobalData
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

    }

    override fun setValues() {

        mProductData = intent.getSerializableExtra("product") as ProductData

        binding.txtProductName.text = mProductData.name
        binding.txtUserNickname.text = GlobalData.loginUser!!.nickname

//        오늘날짜 -> 2021.1.5 형태로 가공 -> 텍스트뷰에 반영

//        1. 오늘 날짜?
        val now = Calendar.getInstance() //현재일시 자동 기록

//        원하는 형태로 현재일시를 가공.(String으로)
        val sdf = SimpleDateFormat("yyyy.M.d")
        val nowString = sdf.format( now.time )

        binding.txtToday.text = nowString

    }
}
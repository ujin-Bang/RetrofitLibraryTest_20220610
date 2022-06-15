package com.start.retrofitlibrarytest_20220610.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.start.retrofitlibrarytest_20220610.api.ServerAPI
import com.start.retrofitlibrarytest_20220610.api.ServerAPIService

abstract class BaseFragment : Fragment() {

    lateinit var mContext: Context

    //    상속 시켜 줄것 : API 호출 명세를 담은 변수
    lateinit var apiService : ServerAPIService

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        mContext = requireContext()

        val retrofit = ServerAPI.getRetrofit(mContext)
        apiService = retrofit.create(ServerAPIService::class.java)
    }

    abstract fun setupEvents()
    abstract fun setValues()

}
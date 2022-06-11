package com.start.retrofitlibrarytest_20220610

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.start.retrofitlibrarytest_20220610.api.ServerAPI
import com.start.retrofitlibrarytest_20220610.api.ServerAPIService

abstract class BaseActivity : AppCompatActivity() {

    lateinit var mContext: Context

    lateinit var apiService : ServerAPIService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mContext = this

        val retrofit = ServerAPI.getRetrofit()
        apiService = retrofit.create(ServerAPIService::class.java)
    }

    abstract fun setupEvents()
    abstract fun setValues()
}
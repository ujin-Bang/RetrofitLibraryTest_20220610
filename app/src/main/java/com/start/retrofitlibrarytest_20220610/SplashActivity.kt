package com.start.retrofitlibrarytest_20220610

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.databinding.DataBindingUtil.setContentView
import com.start.retrofitlibrarytest_20220610.datas.BasicResponse
import com.start.retrofitlibrarytest_20220610.datas.UserData
import com.start.retrofitlibrarytest_20220610.utils.ContextUtil
import com.start.retrofitlibrarytest_20220610.utils.GlobalData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SplashActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {

    }

    override fun setValues() {


        apiService.getRequestMyInfo().enqueue(object :
            Callback<BasicResponse> {
            override fun onResponse(call: Call<BasicResponse>, response: Response<BasicResponse>) {

                if (response.isSuccessful) {
//                    올바른 토큰일때 -> loginUser에 파싱해서 객체 대입.

                   GlobalData.loginUser =  response.body()!!.data.user

                }

            }

            override fun onFailure(call: Call<BasicResponse>, t: Throwable) {

            }

        })



        val myHandler = Handler(Looper.getMainLooper())
        myHandler.postDelayed({

          val myIntent: Intent

          if(GlobalData.loginUser != null) {
              myIntent = Intent(mContext, MainActivity::class.java)
          }
          else{
              myIntent = Intent(mContext,LoginActivity::class.java)
          }
            startActivity(myIntent)
            finish()
        },1500)
    }
}
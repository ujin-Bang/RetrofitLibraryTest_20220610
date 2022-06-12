package com.start.retrofitlibrarytest_20220610

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.start.retrofitlibrarytest_20220610.databinding.ActivityLoginBinding
import com.start.retrofitlibrarytest_20220610.datas.BasicResponse
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
//로그인 화면 - 방우진이 작업합니다.
class LoginActivity : BaseActivity() {

    lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {

        binding.btnSignUp.setOnClickListener {

            val myIntent = Intent(mContext, SignUpActivity::class.java)
            startActivity(myIntent)
        }

        binding.btnLogin.setOnClickListener {

            val inputEamil = binding.edtEmail.text.toString()
            val inputPw = binding.edtPassword.text.toString()

            apiService.postRequestLogin(inputEamil, inputPw)
                .enqueue(object : Callback<BasicResponse> {
                    override fun onResponse(
                        call: Call<BasicResponse>,
                        response: Response<BasicResponse>
                    ) {

                        if (response.isSuccessful) {

                            val basicResponse = response.body()!!

//                        Toast.makeText(mContext, basicResponse.message, Toast.LENGTH_SHORT).show()

//                        추가파싱 -> 로그인한 사람의 닉네임 활용 "~님 환영합니다!" 토스트
                            val userNickname = basicResponse.data.user.nickname

                            Toast.makeText(mContext, "${userNickname}님 환영합니다!!", Toast.LENGTH_SHORT)
                                .show()

                        } else {

                            val errorJson = JSONObject(response.errorBody()!!.string())
                            Log.d("에러경우", errorJson.toString())

                            val message = errorJson.getString("message")
                            Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show()
                        }
                    }

                    override fun onFailure(call: Call<BasicResponse>, t: Throwable) {

                    }

                })
        }

    }

    override fun setValues() {

    }
}
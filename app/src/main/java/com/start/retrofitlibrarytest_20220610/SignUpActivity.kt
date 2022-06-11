package com.start.retrofitlibrarytest_20220610

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.start.retrofitlibrarytest_20220610.databinding.ActivitySignUpBinding
import com.start.retrofitlibrarytest_20220610.datas.BasicResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUpActivity : BaseActivity() {

    lateinit var binding: ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sign_up)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {
        binding.btnSignUp.setOnClickListener {

            val inputEmail = binding.edtEmail.text.toString()
            val inputPassword = binding.edtPassword.text.toString()
            val inputNickname = binding.edtNickname.text.toString()

            apiService.putRequestSignUp(inputEmail, inputPassword, inputNickname).enqueue(object : Callback<BasicResponse>{
                override fun onResponse(
                    call: Call<BasicResponse>,
                    response: Response<BasicResponse>
                ) {

                }

                override fun onFailure(call: Call<BasicResponse>, t: Throwable) {

                }

            })

        }

    }

    override fun setValues() {

    }
}
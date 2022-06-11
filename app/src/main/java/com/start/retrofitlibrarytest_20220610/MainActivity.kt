package com.start.retrofitlibrarytest_20220610

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import com.start.retrofitlibrarytest_20220610.databinding.ActivityMainBinding

class MainActivity : BaseActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {

        binding.btnLogin.setOnClickListener {

            val inputEamil = binding.edtEmail.text.toString()
            val inputPw = binding.edtPassword.text.toString()

            apiService.postRequestLogin(inputEamil, inputPw)
        }

    }

    override fun setValues() {

    }
}
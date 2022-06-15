package com.start.retrofitlibrarytest_20220610

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.databinding.DataBindingUtil.setContentView
import com.start.retrofitlibrarytest_20220610.utils.ContextUtil

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

        val myHandler = Handler(Looper.getMainLooper())
        myHandler.postDelayed({

          val myIntent: Intent

          if(ContextUtil.getToken(mContext) != null) {
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
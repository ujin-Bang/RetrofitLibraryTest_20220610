package com.start.retrofitlibrarytest_20220610

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.start.retrofitlibrarytest_20220610.adpaters.MainViewPagerAdapter
import com.start.retrofitlibrarytest_20220610.databinding.ActivityMainBinding

class MainActivity : BaseActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var mvpa: MainViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {

    }

    override fun setValues() {
        mvpa = MainViewPagerAdapter(supportFragmentManager)
        binding.mainViewPager.adapter = mvpa
        binding.mainTabLayout.setupWithViewPager(binding.mainViewPager)
    }
}
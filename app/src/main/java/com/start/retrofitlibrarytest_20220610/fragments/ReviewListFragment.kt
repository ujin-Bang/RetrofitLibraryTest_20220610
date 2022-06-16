package com.start.retrofitlibrarytest_20220610.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.start.retrofitlibrarytest_20220610.R
import com.start.retrofitlibrarytest_20220610.databinding.FragmentReviewListBinding

class ReviewListFragment : BaseFragment() {

    lateinit var binding: FragmentReviewListBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_review_list, container,false)
        return binding.root
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupEvents()
        setValues()

    }

    override fun setupEvents() {

    }

    override fun setValues() {

    }


}
package com.start.retrofitlibrarytest_20220610.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.start.retrofitlibrarytest_20220610.R
import com.start.retrofitlibrarytest_20220610.adpaters.MainRecylerAdapter
import com.start.retrofitlibrarytest_20220610.databinding.FragmentRecyclerviewPracticeBinding
import com.start.retrofitlibrarytest_20220610.datas.BasicResponse
import com.start.retrofitlibrarytest_20220610.datas.ReviewData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RecyclerViewPracticeFragment: BaseFragment() {

    lateinit var binding: FragmentRecyclerviewPracticeBinding

    val mReviewList = ArrayList<ReviewData>()
    lateinit var mMainRecyclerAdapter: MainRecylerAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_recyclerview_practice, container,false)
        return  binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {

    }

    override fun setValues() {

        getReviewListFromServer()

        mMainRecyclerAdapter = MainRecylerAdapter(mContext, mReviewList)
        binding.mainRecyclerView.adapter = mMainRecyclerAdapter
        binding.mainRecyclerView.layoutManager = LinearLayoutManager(mContext)
    }

    fun getReviewListFromServer(){
        apiService.getRequestReview().enqueue( object : Callback<BasicResponse>{
            override fun onResponse(call: Call<BasicResponse>, response: Response<BasicResponse>) {

                if(response.isSuccessful){
                    val br = response.body()!!

                    mReviewList.clear()
                    mReviewList.addAll(br.data.reviews)

                    mMainRecyclerAdapter.notifyDataSetChanged()
                }
            }

            override fun onFailure(call: Call<BasicResponse>, t: Throwable) {

            }

        })
    }
}
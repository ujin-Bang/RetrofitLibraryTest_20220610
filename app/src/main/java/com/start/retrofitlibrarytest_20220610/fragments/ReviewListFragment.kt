package com.start.retrofitlibrarytest_20220610.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.start.retrofitlibrarytest_20220610.R
import com.start.retrofitlibrarytest_20220610.adpaters.CategoryRecyclerAdapter
import com.start.retrofitlibrarytest_20220610.databinding.FragmentReviewListBinding
import com.start.retrofitlibrarytest_20220610.datas.BasicResponse
import com.start.retrofitlibrarytest_20220610.datas.SmallCategoryData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ReviewListFragment : BaseFragment() {

    lateinit var binding: FragmentReviewListBinding

    val mSmallCategoryList = ArrayList<SmallCategoryData>()
    lateinit var mCategoryAdapter: CategoryRecyclerAdapter

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
        getCategoryListFromServer()

        mCategoryAdapter = CategoryRecyclerAdapter(mContext, mSmallCategoryList)
        binding.categoryListRecyclerview.adapter = mCategoryAdapter
        binding.categoryListRecyclerview.layoutManager = LinearLayoutManager(mContext)
    }

    fun getCategoryListFromServer(){

        apiService.getRequestSmallCategory().enqueue(object : Callback<BasicResponse>{
            override fun onResponse(call: Call<BasicResponse>, response: Response<BasicResponse>) {

                if(response.isSuccessful){

                    val br = response.body()!!

                    mSmallCategoryList.clear()
                    mSmallCategoryList.addAll(br.data.categories)

                    mCategoryAdapter.notifyDataSetChanged()
                }
            }

            override fun onFailure(call: Call<BasicResponse>, t: Throwable) {

            }


        })

    }
}
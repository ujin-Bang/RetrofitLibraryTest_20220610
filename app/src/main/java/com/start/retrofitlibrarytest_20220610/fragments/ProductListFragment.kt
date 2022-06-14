package com.start.retrofitlibrarytest_20220610.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.start.retrofitlibrarytest_20220610.R
import com.start.retrofitlibrarytest_20220610.databinding.FragmentProductListBinding
import com.start.retrofitlibrarytest_20220610.datas.BasicResponse
import com.start.retrofitlibrarytest_20220610.datas.ProductData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductListFragment: BaseFragment() {

    lateinit var binding : FragmentProductListBinding

    val mProductList = ArrayList<ProductData>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_product_list, container, false)
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

        apiService.getRequestProductInfo().enqueue( object : Callback<BasicResponse>{
            override fun onResponse(call: Call<BasicResponse>, response: Response<BasicResponse>) {

                if(response.isSuccessful){
                    val br = response.body()!!
                }

            }

            override fun onFailure(call: Call<BasicResponse>, t: Throwable) {

            }

        })

    }

}
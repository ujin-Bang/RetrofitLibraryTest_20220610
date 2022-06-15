package com.start.retrofitlibrarytest_20220610.fragments

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.start.retrofitlibrarytest_20220610.R
import com.start.retrofitlibrarytest_20220610.databinding.FragmentMyProfileBinding
import com.start.retrofitlibrarytest_20220610.datas.BasicResponse
import com.start.retrofitlibrarytest_20220610.utils.ContextUtil
import com.start.retrofitlibrarytest_20220610.utils.GlobalData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyProfileFragment: BaseFragment() {

    lateinit var binding: FragmentMyProfileBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_my_profile, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {

        binding.btnEditNickname.setOnClickListener {

//            닉네임 변경 입력 (AretDialog 커스텀뷰) + API 호출
            val alert = AlertDialog.Builder(mContext)
                alert.setMessage("닉네임 변경")
                alert.setPositiveButton("확인", DialogInterface.OnClickListener { dialog, which ->

                })
                alert.setNegativeButton("취소", null)
                alert.show()

        }

    }

    override fun setValues() {

    getMyInfoFromServer()

        binding.txtNickname.text = GlobalData.loginUser!!.nickname
        Glide.with(mContext).load(GlobalData.loginUser!!.profileImageURL).into(binding.imgProfile)

        when(GlobalData.loginUser!!.provider){
            "facebook" -> {
                binding.imgProvider.setImageResource(R.drawable.facebook_logo)
            }
            "kakao" -> {
                binding.imgProvider.setImageResource(R.drawable.kakao_logo)
            }
            else -> {
                binding.imgProvider.visibility = View.GONE
            }
        }
    }




    fun getMyInfoFromServer() {

        apiService.getRequestMyInfo().enqueue(object :
            Callback<BasicResponse> {
            override fun onResponse(call: Call<BasicResponse>, response: Response<BasicResponse>) {

                if (response.isSuccessful) {

                    val br = response.body()!!

                    binding.txtNickname.text = br.data.user.nickname
                    Glide.with(mContext).load(br.data.user.profileImageURL).into(binding.imgProfile)


                }

            }

            override fun onFailure(call: Call<BasicResponse>, t: Throwable) {

            }

        })
    }
}
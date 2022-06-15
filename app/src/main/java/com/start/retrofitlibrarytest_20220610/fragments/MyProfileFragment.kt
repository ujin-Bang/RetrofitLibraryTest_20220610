package com.start.retrofitlibrarytest_20220610.fragments

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
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

class MyProfileFragment : BaseFragment() {

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

//            닉네임 변경 입력 (AlretDialog 커스텀뷰) + API 호출
            val alert = AlertDialog.Builder(mContext)
            alert.setTitle("닉네임 변경")

//            얼럿의 내부 뷰를 커스텀뷰로 (xml -> View로 가져와서)
//            xml 내부 UI접근 필요 -> inflate 해와서 사용하자.

            val customView = LayoutInflater.from(mContext).inflate(R.layout.my_custom_alert_edit_nickname, null)

            alert.setView(customView)

            val edtNickname = customView.findViewById<EditText>(R.id.edtNickname)


            alert.setPositiveButton("확인", DialogInterface.OnClickListener { dialog, which ->

                val inputNickname = edtNickname.text.toString()

                Toast.makeText(mContext, inputNickname, Toast.LENGTH_SHORT).show()

            })
            alert.setNegativeButton("취소", null)
            alert.show()

        }

    }

    override fun setValues() {

        getMyInfoFromServer()

        binding.txtNickname.text = GlobalData.loginUser!!.nickname
        Glide.with(mContext).load(GlobalData.loginUser!!.profileImageURL).into(binding.imgProfile)

        when (GlobalData.loginUser!!.provider) {
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
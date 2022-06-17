package com.start.retrofitlibrarytest_20220610.fragments

import android.app.Activity
import android.app.Activity.RESULT_OK
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
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
import com.start.retrofitlibrarytest_20220610.utils.URIPathHelper
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File

class MyProfileFragment : BaseFragment() {

    val REQ_FOR_GALLERY = 1000

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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(resultCode == REQ_FOR_GALLERY) {
            if(resultCode == Activity.RESULT_OK){

//                선택한 이미지 받아오기
                val selectedImageUri = data?.data!!

                Log.d("선택된이미지URI",selectedImageUri.toString())

//                Uri ->실제 첨부 가능한 파일로 변환해야함.
//                Uri -> File 형태로 변환 -> 그 실재 경로를 추출해서 , Retrofit에 첨부할 수 있게됨.
                val file = File(URIPathHelper().getPath(mContext, selectedImageUri))
            }
        }
    }

    override fun setupEvents() {

        binding.imgProfile.setOnClickListener {

//            갤러리(안드로이드 제공)로 사진 가지러 이동(왕복 이동).
            val myIntent = Intent()
            myIntent.action = Intent.ACTION_PICK
            myIntent.type = android.provider.MediaStore.Images.Media.CONTENT_TYPE
            startActivityForResult(myIntent, REQ_FOR_GALLERY)

        }

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

                apiService.patchRequestEditUserInfo("nickname", inputNickname).enqueue(object : Callback<BasicResponse>{
                    override fun onResponse(
                        call: Call<BasicResponse>,
                        response: Response<BasicResponse>
                    ) {
                        if(response.isSuccessful){

                            val br = response.body()!!

//                            토큰값 추출 -> 다시 저장.
                            val token = br.data.token

                            ContextUtil.setToken(mContext, token)

//                            로그인한 사용자 정보도 갱신.
                            GlobalData.loginUser = br.data.user

                            Toast.makeText(mContext, "닉네임 변경에 성공했습니다.", Toast.LENGTH_SHORT).show()

                            getMyInfoFromServer()
                        }
                        else{
//                            닉네임 변셩 실패 -> 서버에서 중복 막는 경우
                            val jsonObj = JSONObject(response.errorBody()!!.string())
                            Log.e("닉네임변경 실패이유",jsonObj.toString())

                            val message = jsonObj.getString("message")
                            Toast.makeText(mContext, "실패사유:${message}", Toast.LENGTH_SHORT).show()
                        }
                    }

                    override fun onFailure(call: Call<BasicResponse>, t: Throwable) {

                    }

                })

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
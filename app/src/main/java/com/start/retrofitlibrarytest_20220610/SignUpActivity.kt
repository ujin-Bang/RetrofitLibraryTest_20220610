package com.start.retrofitlibrarytest_20220610

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.databinding.DataBindingUtil
import com.start.retrofitlibrarytest_20220610.databinding.ActivitySignUpBinding
import com.start.retrofitlibrarytest_20220610.datas.BasicResponse
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUpActivity : BaseActivity() {

    lateinit var binding: ActivitySignUpBinding

    var isDuplOk = false
    var isPasswordLengthOk = false
    var isPasswordSame = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sign_up)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {
        
//        1. 비밀번호 타이핑 이벤트 => 8글자 이상인지 아닌지 검사.
//         =>검사 결과를 txtPasswordCheckResult에 반영
        binding.edtPassword.addTextChangedListener {

            if(it.toString().length >= 8){
                binding.txtPasswordCheckResult1.text = "사용해도 좋은 비밀번호입니다."
                isPasswordLengthOk = true
            }
            else{
                binding.txtPasswordCheckResult1.text = "8글자 이상 입력해주세요!"
                isPasswordLengthOk = false
            }
           isPasswordSame = compareTwoPasswords()
        }
                
//        2.비밀번호 확인 타이핑 이벤트 => 첫 비밀번호 입력과 같은지?
//            => 검사 결과를 txtPasswordCheckResult에 반영
           binding.edtPasswordRepeat.addTextChangedListener {

             isPasswordSame = compareTwoPasswords()

           }


//          3. 회원가입 API 호출 전, 비번 8글자이상 / 두개 비번이 같은지 둘다 통과해야, 서버에 호출.
//        =>통과 못하면 이메일 중복검사처럼 토스트로 안내 / 함수 강제 종료.


        binding.edtEmail.addTextChangedListener {
//            Log.d("입력된 내용", it.toString())

//            입력이 바뀌면 재검사 요청.
            binding.txtEmailCheckResult.text = "이메일 중복검사를 해주세요"
            isDuplOk = false
        }

        binding.btnEmailDupleCheck.setOnClickListener {

            val inputEmail = binding.edtEmail.text.toString()
            if(inputEmail.isEmpty() ){
                binding.txtEmailCheckResult.text = "이메일을 입력해주세요!!"
                return@setOnClickListener
            }

            apiService.getRequestDuplicatedCheck("EMAIL", inputEmail).enqueue(object : Callback<BasicResponse>{
                override fun onResponse(
                    call: Call<BasicResponse>,
                    response: Response<BasicResponse>
                ) {

                    if(response.isSuccessful){
//                        code: 200 -> 사용해도 좋은 이메일
                        binding.txtEmailCheckResult.text ="사용해도 좋은 이메일입니다."
                        isDuplOk = true
                    }
                    else{
//                        사용하면 안되는 이메일
                        binding.txtEmailCheckResult.text = "다른 이메일로 다시 검사해주세요"
                        isDuplOk = false
                    }
                }

                override fun onFailure(call: Call<BasicResponse>, t: Throwable) {

                }

            })
        }

        binding.btnSignUp.setOnClickListener {

            if(!isDuplOk ){
                Toast.makeText(mContext, "이메일 중복검사를 해주세요.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if(!isPasswordLengthOk){
                Toast.makeText(mContext, "비밀번호는 8자 이상 입력하셔야합니다.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if(!isPasswordSame){
                Toast.makeText(mContext, "두개의 비밀번호는 서로 같아야합니다.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }


            val inputEmail = binding.edtEmail.text.toString()
            val inputPassword = binding.edtPassword.text.toString()
            val inputNickname = binding.edtNickname.text.toString()

            apiService.putRequestSignUp(inputEmail, inputPassword, inputNickname).enqueue(object : Callback<BasicResponse>{
                override fun onResponse(
                    call: Call<BasicResponse>,
                    response: Response<BasicResponse>
                ) {

                    if(response.isSuccessful){

                        val br = response.body()!!

                        Log.d("가입한사람토큰", br.data.token)

                        val signUpUserNickname = br.data.user.nickname
                        Toast.makeText(
                            mContext,
                            "${signUpUserNickname}님 가입을 축하합니다!",
                            Toast.LENGTH_SHORT
                        ).show()

                        finish()
                    }

                }

                override fun onFailure(call: Call<BasicResponse>, t: Throwable) {

                }

            })

        }

    }   fun compareTwoPasswords() : Boolean {

        val originalPassword = binding.edtPassword.text.toString()
        val repeatPassword = binding.edtPasswordRepeat.text.toString()

        if(originalPassword == repeatPassword){
            binding.txtPasswordCheckResult2.text = "사용해도 좋습니다."
            return true
        }
        else {
            binding.txtPasswordCheckResult2.text = "위의 비밀번호와 일치해야합니다."
            return false
        }
    }


    override fun setValues() {

    }
}
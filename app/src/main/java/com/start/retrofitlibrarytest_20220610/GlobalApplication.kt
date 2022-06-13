package com.start.retrofitlibrarytest_20220610

import android.app.Application
import com.kakao.sdk.common.KakaoSdk

class GlobalApplication : Application() {

    override fun onCreate() {
        super.onCreate()

//        Kakao SDK초기화
        KakaoSdk.init(this, "df15793f2a4a6bb3ab7dc93e012fe5a5")

    }
}
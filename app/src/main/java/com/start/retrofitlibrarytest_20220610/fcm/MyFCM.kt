package com.start.retrofitlibrarytest_20220610.fcm

import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyFCM : FirebaseMessagingService() {

    override fun onMessageReceived(message: RemoteMessage){
        super.onMessageReceived(message)

//    실제 푸시 신호가 왔을때 할 행동 - message변수: 어떤메시지? 담아주는 역할
    }
}
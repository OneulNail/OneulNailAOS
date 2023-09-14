package com.today.nail.service.config

import android.app.Application
import android.util.Log
import com.kakao.sdk.common.KakaoSdk
import com.kakao.sdk.common.util.Utility
import com.today.nail.service.BuildConfig
import com.today.nail.service.data.TokenSharedPreferences
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MainApplication  : Application() {
    companion object{
        lateinit var token_prefs : TokenSharedPreferences
    }
    override fun onCreate() {
        super.onCreate()
        // 다른 초기화 코드들
        token_prefs = TokenSharedPreferences(applicationContext)
        // Kakao SDK 초기화
        KakaoSdk.init(this, BuildConfig.KAKAO_NATIVE_APP_KEY)
//        val TAG = "해시"
//        Log.d(TAG, "keyhash : ${Utility.getKeyHash(this)}")
    }
}
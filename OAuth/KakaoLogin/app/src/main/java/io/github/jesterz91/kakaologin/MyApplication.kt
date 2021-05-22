package io.github.jesterz91.kakaologin

import android.app.Application
import android.util.Log
import com.kakao.sdk.common.KakaoSdk
import com.kakao.sdk.common.util.Utility

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        KakaoSdk.init(this, "{NATIVE_APP_KEY}")

        if (BuildConfig.DEBUG) {
            Log.d("kakao keyHash", Utility.getKeyHash(this))
        }
    }
}
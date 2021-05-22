package io.github.jesterz91.kakaologin

import android.os.Bundle
import android.util.Log
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.user.UserApiClient
import com.kakao.sdk.user.rx
import io.github.jesterz91.kakaologin.databinding.ActivityLoginBinding
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo

class LoginActivity : BaseActivity<ActivityLoginBinding>(ActivityLoginBinding::inflate) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.kakaoLoginButton.setOnClickListener {
            if (UserApiClient.instance.isKakaoTalkLoginAvailable(this)) {
                loginWithKakaoTalk()
            } else {
                loginWithKakaoAccount()
            }
        }
    }

    private fun loginWithKakaoTalk() {
        UserApiClient.rx.loginWithKakaoTalk(this)
            .map(OAuthToken::accessToken)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(::handleAccessToke, ::handleError)
            .addTo(disposables)
    }

    private fun loginWithKakaoAccount() {
        UserApiClient.rx.loginWithKakaoAccount(this)
            .map(OAuthToken::accessToken)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(::handleAccessToke, ::handleError)
            .addTo(disposables)
    }

    private fun handleAccessToke(token: String) {
        Log.i(TAG, "로그인 성공 $token")
        redirectMainActivity()
    }

    companion object {
        const val TAG = "LoginActivity"
    }
}
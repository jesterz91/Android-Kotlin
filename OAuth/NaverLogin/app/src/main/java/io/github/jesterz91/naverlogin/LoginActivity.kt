package io.github.jesterz91.naverlogin

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.nhn.android.naverlogin.OAuthLogin
import com.nhn.android.naverlogin.OAuthLoginHandler
import io.github.jesterz91.naverlogin.databinding.ActivityLoginBinding
import java.lang.ref.WeakReference

class LoginActivity : AppCompatActivity() {

    private val naverLoginModule: OAuthLogin = OAuthLogin.getInstance()

    private val naverLoginHandler: NaverLoginHandler = NaverLoginHandler(this)

    private val binding: ActivityLoginBinding by lazy { ActivityLoginBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        naverLoginModule.init(
            this,
            BuildConfig.NAVER_CLIENT_ID,
            BuildConfig.NAVER_CLIENT_SECRET,
            BuildConfig.NAVER_CLIENT_NAME
        )

        with(binding) {
            setContentView(root)
            naverLoginButton.setOAuthLoginHandler(naverLoginHandler)
            naverLoginButton.setOnClickListener {
                naverLoginModule.startOauthLoginActivity(this@LoginActivity, naverLoginHandler)
            }
        }
    }

    private fun redirectMainActivity() {
        val intent = Intent(this, MainActivity::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        }
        startActivity(intent)
    }

    companion object {

        const val TAG = "LoginActivity"

        class NaverLoginHandler(activity: LoginActivity) : OAuthLoginHandler() {

            private val activity = WeakReference(activity)

            override fun run(success: Boolean) {
                val loginActivity: LoginActivity? = activity.get()

                if (success) {
                    loginActivity?.redirectMainActivity()
                } else {
                    loginActivity?.naverLoginModule?.run {
                        // 에러 로그
                        Log.e(TAG, "ErrorCode : ${getLastErrorCode(loginActivity).code}")
                        Log.e(TAG, "ErrorDesc : ${getLastErrorDesc(loginActivity)}")
                    }
                }
            }
        }
    }
}
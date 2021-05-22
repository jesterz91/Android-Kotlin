package io.github.jesterz91.naverlogin

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.gson.Gson
import com.nhn.android.naverlogin.OAuthLogin
import com.nhn.android.naverlogin.data.OAuthLoginState
import io.github.jesterz91.naverlogin.data.NaverLoginResponse
import io.github.jesterz91.naverlogin.data.User
import io.github.jesterz91.naverlogin.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    private val naverLoginModule: OAuthLogin = OAuthLogin.getInstance()

    private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        with(binding) {
            setContentView(root)
            logoutButton.setOnClickListener { logout() }
            unlinkButton.setOnClickListener { unlink() }
        }
    }

    override fun onResume() {
        super.onResume()
        checkLoginState()
    }

    private fun checkLoginState() {
        when (naverLoginModule.getState(this)) {
            OAuthLoginState.OK -> {
                naverLoginModule.getAccessToken(this).run(::requestUserInfo)
            }
            else -> {
                redirectLoginActivity()
            }
        }
    }

    private fun requestUserInfo(accessToken: String) = lifecycleScope.launch {
        // 네이버 액세스 토큰으로 네이버 API 에 접근하여 사용자 정보를 가져옴
        val url = "https://openapi.naver.com/v1/nid/me"

        val response = withContext(Dispatchers.IO) {
            naverLoginModule.requestApi(this@MainActivity, accessToken, url)
        }
        val loginResponse = Gson().fromJson(response, NaverLoginResponse::class.java)

        bindUser(loginResponse.user)
    }

    private fun bindUser(user: User): Unit = with(binding) {
        nameTextView.text = user.name
        nickNameTextView.text = user.nickname
        emailTextView.text = user.email

        Glide.with(this@MainActivity)
            .load(user.profileImage)
            .apply(RequestOptions.circleCropTransform())
            .into(imageView)
    }

    private fun logout() {
        naverLoginModule.logout(this)
        redirectLoginActivity()
    }

    private fun unlink() = lifecycleScope.launch {
        val isSuccessDeleteToken = withContext(Dispatchers.IO) {
            naverLoginModule.logoutAndDeleteToken(this@MainActivity)
        }

        if (!isSuccessDeleteToken) {
            Log.e(TAG, "ErrorCode : ${naverLoginModule.getLastErrorCode(this@MainActivity).code}")
            Log.e(TAG, "ErrorDesc : ${naverLoginModule.getLastErrorDesc(this@MainActivity)}")
        }

        redirectLoginActivity()
    }

    private fun redirectLoginActivity() {
        val intent = Intent(this, LoginActivity::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        }
        startActivity(intent)
    }

    companion object {
        const val TAG = "MainActivity"
    }
}
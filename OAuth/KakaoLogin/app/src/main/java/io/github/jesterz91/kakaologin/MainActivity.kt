package io.github.jesterz91.kakaologin

import android.os.Bundle
import android.util.Log
import com.bumptech.glide.Glide
import com.kakao.sdk.user.UserApiClient
import com.kakao.sdk.user.model.User
import com.kakao.sdk.user.rx
import io.github.jesterz91.kakaologin.databinding.ActivityMainBinding
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers

class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requestMe() // 사용자 정보 요쳥

        binding.logoutButton.setOnClickListener { logout() }
        binding.unlinkButton.setOnClickListener { unlink() }
    }

    private fun bindUser(user: User): Unit = with(binding) {
        val account = user.kakaoAccount

        nameTextView.text = "${user.id}"
        emailTextView.text = account?.email
        nickNameTextView.text = account?.profile?.nickname

        Glide.with(this@MainActivity)
            .load(account?.profile?.thumbnailImageUrl)
            .centerCrop()
            .circleCrop()
            .into(imageView)
    }

    /* 사용자 정보 요쳥 */
    private fun requestMe() {
        UserApiClient.rx.me()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(::bindUser) { error ->
                Log.e(TAG, "사용자 정보 요청 실패", error)
                redirectLoginActivity()
            }.addTo(disposables)
    }

    /* 로그아웃 */
    private fun logout() {
        UserApiClient.rx.logout()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Log.i(TAG, "로그아웃 성공. SDK에서 토큰 삭제 됨")
                redirectLoginActivity()
            }, { error ->
                Log.e(TAG, "로그아웃 실패. SDK에서 토큰 삭제 됨", error)
                redirectLoginActivity()
            }).addTo(disposables)
    }

    /* 연동해제 */
    private fun unlink() {
        UserApiClient.rx.unlink()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Log.i(TAG, "연결 끊기 성공. SDK에서 토큰 삭제 됨")
                redirectLoginActivity()
            }, { error ->
                Log.e(TAG, "연결 끊기 실패", error)
                redirectLoginActivity()
            }).addTo(disposables)
    }

    companion object {
        const val TAG = "MainActivity"
    }
}
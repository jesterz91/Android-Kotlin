package io.github.jesterz91.kakaologin

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import io.reactivex.disposables.CompositeDisposable

abstract class BaseActivity<B : ViewBinding>(inflater: (LayoutInflater) -> B) : AppCompatActivity() {

    protected val binding: B by lazy { inflater.invoke(layoutInflater) }

    protected val disposables: CompositeDisposable by lazy(::CompositeDisposable)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }

    override fun onDestroy() {
        disposables.clear()
        super.onDestroy()
    }

    protected fun handleError(error: Throwable) {
        Log.e("Error", "${error.message}")
    }

    protected fun redirectLoginActivity() {
        val intent = Intent(this, LoginActivity::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        }
        startActivity(intent)
    }

    protected fun redirectMainActivity() {
        val intent = Intent(this, MainActivity::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        }
        startActivity(intent)
    }
}
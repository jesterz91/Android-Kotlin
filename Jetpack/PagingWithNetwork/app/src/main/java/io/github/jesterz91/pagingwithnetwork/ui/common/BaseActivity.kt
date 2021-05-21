package io.github.jesterz91.pagingwithnetwork.ui.common

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.ViewDataBinding
import io.reactivex.rxjava3.disposables.CompositeDisposable

abstract class BaseActivity<VM: BaseViewModel, B: ViewDataBinding>(private val inflater: (LayoutInflater) -> B) : AppCompatActivity() {

    protected val disposables: CompositeDisposable by lazy(::CompositeDisposable)

    protected val binding: B by lazy { inflater(layoutInflater) }

    abstract val viewModel: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.lifecycleOwner = this
    }

    override fun onDestroy() {
        disposables.clear()
        super.onDestroy()
    }
}
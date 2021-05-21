package io.github.jesterz91.pagingwithnetwork.ui.common

import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.disposables.CompositeDisposable

abstract class BaseViewModel: ViewModel() {

    protected val disposables: CompositeDisposable by lazy(::CompositeDisposable)

    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }
}
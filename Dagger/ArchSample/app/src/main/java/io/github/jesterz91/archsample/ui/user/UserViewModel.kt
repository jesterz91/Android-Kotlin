package io.github.jesterz91.archsample.ui.user

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.github.jesterz91.archsample.network.UserService
import io.github.jesterz91.archsample.network.data.User
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.addTo
import javax.inject.Inject

class UserViewModel @Inject constructor(private val userService: UserService) : ViewModel() {

    private val compositeDisposable: CompositeDisposable by lazy(::CompositeDisposable)

    private val _loading: MutableLiveData<Boolean> = MutableLiveData(false)
    val loading: LiveData<Boolean> = _loading

    private val _userLiveData: MutableLiveData<User> = MutableLiveData()
    val user: LiveData<User> = _userLiveData

    fun load(userId: Long) {
        userService.getUser(userId)
            .doOnSubscribe { _loading.postValue(true) }
            .doAfterSuccess { _loading.postValue(false) }
            .subscribe(_userLiveData::postValue)
            .addTo(compositeDisposable)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}
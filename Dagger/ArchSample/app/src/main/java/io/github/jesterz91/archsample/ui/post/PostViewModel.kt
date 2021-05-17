package io.github.jesterz91.archsample.ui.post

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.github.jesterz91.archsample.network.PostService
import io.github.jesterz91.archsample.ui.post.data.PostItem
import io.github.jesterz91.archsample.util.SingleLiveEvent
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class PostViewModel @Inject constructor(private val service: PostService) : ViewModel() {

    private val compositeDisposable by lazy(::CompositeDisposable)

    private val _loading: MutableLiveData<Boolean> = MutableLiveData(false)
    val loading: LiveData<Boolean> = _loading

    private val _postsLiveData: MutableLiveData<List<PostItem>> = MutableLiveData()
    val postsLiveData: LiveData<List<PostItem>> = _postsLiveData

    val postClickEvent: SingleLiveEvent<PostItem> = SingleLiveEvent()

    fun loadPosts() {
        service.getPosts()
            .map { it.map { post -> PostItem(post, postClickEvent::postValue) } }
            .subscribeOn(Schedulers.io())
            .onErrorReturn { emptyList() }
            .doOnSubscribe { _loading.postValue(true) }
            .doAfterSuccess { _loading.postValue(false) }
            .subscribe(_postsLiveData::postValue)
            .addTo(compositeDisposable)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}
package io.github.jesterz91.archsample.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.github.jesterz91.archsample.network.CommentService
import io.github.jesterz91.archsample.network.UserService
import io.github.jesterz91.archsample.network.data.Post
import io.github.jesterz91.archsample.network.data.User
import io.github.jesterz91.archsample.ui.detail.data.PostDetailCommentItem
import io.github.jesterz91.archsample.ui.detail.data.PostDetailItem
import io.github.jesterz91.archsample.ui.detail.data.PostDetailPostItem
import io.github.jesterz91.archsample.ui.detail.data.PostDetailUserItem
import io.github.jesterz91.archsample.util.SingleLiveEvent
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.Singles
import io.reactivex.rxjava3.kotlin.addTo
import javax.inject.Inject

class PostDetailViewModel @Inject constructor(
    private val userService: UserService,
    private val commentService: CommentService
) : ViewModel() {

    private val compositeDisposable by lazy(::CompositeDisposable)

    private val _loading: MutableLiveData<Boolean> = MutableLiveData(false)
    val loading: LiveData<Boolean> = _loading

    private val _postDetailLiveData: MutableLiveData<List<PostDetailItem>> = MutableLiveData()
    val postDetailLiveData: LiveData<List<PostDetailItem>> = _postDetailLiveData

    val postDetailClickEvent: SingleLiveEvent<User> = SingleLiveEvent()

    fun load(post: Post) {
        Singles.zip(userService.getUser(post.userId), commentService.getComments(post.id))
            .map { (user, comments) ->
                val list = mutableListOf(
                    PostDetailUserItem(user, postDetailClickEvent::postValue),
                    PostDetailPostItem(post),
                )
                list.addAll(comments.map { PostDetailCommentItem(it) })
                return@map list
            }
            .doOnSubscribe { _loading.postValue(true) }
            .doAfterSuccess { _loading.postValue(false) }
            .subscribe(_postDetailLiveData::postValue)
            .addTo(compositeDisposable)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}
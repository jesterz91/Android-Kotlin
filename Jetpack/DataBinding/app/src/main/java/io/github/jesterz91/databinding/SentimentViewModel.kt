package io.github.jesterz91.databinding

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class SentimentViewModel : ViewModel() {

    private val _user: MutableStateFlow<User> = MutableStateFlow(User("토니 스타크", "아이언맨", 0))
    val user: LiveData<User> = _user.asLiveData()

    fun increase() {
        val hero = _user.value
        viewModelScope.launch {
            _user.emit(hero.copy(like = hero.like + 1))
        }
    }

    fun decrease() {
        val hero = _user.value

        if (hero.like == 0) return

        viewModelScope.launch {
            _user.emit(hero.copy(like = hero.like - 1))
        }
    }
}
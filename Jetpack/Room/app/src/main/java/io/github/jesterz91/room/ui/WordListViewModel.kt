package io.github.jesterz91.room.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.jesterz91.room.data.WordRepository
import io.github.jesterz91.room.data.entity.Word
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class WordListViewModel(private val repository: WordRepository) : ViewModel() {

    val allWordList: Flow<List<Word>> = repository.selectAll()

    fun insert(word: Word) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insert(word)
        }
    }

    fun update(word: Word) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.update(word)
        }
    }

    fun delete(word: Word) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.delete(word)
        }
    }

    fun deleteAll() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAll()
        }
    }
}
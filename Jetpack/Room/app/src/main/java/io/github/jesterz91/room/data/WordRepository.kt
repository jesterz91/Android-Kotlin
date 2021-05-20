package io.github.jesterz91.room.data

import androidx.annotation.WorkerThread
import io.github.jesterz91.room.data.entity.Word
import kotlinx.coroutines.flow.Flow

interface WordRepository {

    fun selectAll(): Flow<List<Word>>

    @WorkerThread
    suspend fun insert(word: Word)

    @WorkerThread
    suspend fun update(word: Word)

    @WorkerThread
    suspend fun delete(word: Word)

    @WorkerThread
    suspend fun deleteAll()

}
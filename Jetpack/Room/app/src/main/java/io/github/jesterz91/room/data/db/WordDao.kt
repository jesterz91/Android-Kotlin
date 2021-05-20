package io.github.jesterz91.room.data.db

import androidx.room.*
import io.github.jesterz91.room.data.entity.Word
import kotlinx.coroutines.flow.Flow

@Dao
interface WordDao {
    // @Query 는 컴파일시 검증
    @Query("SELECT * from word_table ORDER BY id ASC")
    fun selectAll(): Flow<List<Word>>

    @Query("DELETE FROM word_table")
    suspend fun deleteAll()

    @Insert
    suspend fun insert(word: Word)

    @Update
    suspend fun update(word: Word)

    @Delete
    suspend fun delete(word: Word)
}
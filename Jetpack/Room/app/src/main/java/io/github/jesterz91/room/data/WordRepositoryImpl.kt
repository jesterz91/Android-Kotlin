package io.github.jesterz91.room.data

import androidx.annotation.WorkerThread
import io.github.jesterz91.room.data.db.WordDao
import io.github.jesterz91.room.data.entity.Word
import kotlinx.coroutines.flow.Flow

// Repository 가 DAO 객체를 가지고 쿼리수행
class WordRepositoryImpl(private val wordDao: WordDao): WordRepository {

    override fun selectAll(): Flow<List<Word>> = wordDao.selectAll()

    // 작업 스레드에서 호출, UI 스레드에서 호출하게 되면 App 종료
    @WorkerThread
    override suspend fun insert(word: Word) = wordDao.insert(word)

    @WorkerThread
    override suspend fun update(word: Word) = wordDao.update(word)

    @WorkerThread
    override suspend fun delete(word: Word) = wordDao.delete(word)

    @WorkerThread
    override suspend fun deleteAll() = wordDao.deleteAll()
}
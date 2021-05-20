package io.github.jesterz91.pagingwithroom.db

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface CheeseDao {

    @Query("SELECT * FROM Cheese ORDER BY id ASC")
    fun selectAll(): PagingSource<Int, Cheese>

    @Query("SELECT * FROM Cheese ORDER BY id ASC")
    fun getCheeseList(): List<Cheese>

    @Insert
    fun insert(cheeses: List<Cheese>)

    @Insert
    suspend fun insert(cheese: Cheese)

    @Delete
    suspend fun delete(cheese: Cheese)
}

package io.github.jesterz91.pagingwithroom.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Cheese::class], version = 1)
abstract class CheeseDatabase : RoomDatabase() {

    abstract fun cheeseDao(): CheeseDao

    companion object {
        const val NAME = "CheeseDatabase"
    }
}
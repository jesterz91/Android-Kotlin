package io.github.jesterz91.pagingwithroom

import android.app.Application
import com.facebook.stetho.Stetho
import io.github.jesterz91.pagingwithroom.db.Cheese
import io.github.jesterz91.pagingwithroom.db.CheeseDao
import io.github.jesterz91.pagingwithroom.db.CheeseDatabase
import io.github.jesterz91.pagingwithroom.util.data
import io.github.jesterz91.pagingwithroom.di.dbModule
import io.github.jesterz91.pagingwithroom.di.vmModule
import io.github.jesterz91.pagingwithroom.util.ioThread
import org.koin.android.ext.android.inject
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class CheeseApp : Application() {

    private val database: CheeseDatabase by inject()

    private val dao: CheeseDao by lazy { database.cheeseDao() }

    override fun onCreate() {
        super.onCreate()

        // 데이터베이스 디버깅 chrome://inspect
        Stetho.initializeWithDefaults(this)

        startKoin {
            androidContext(this@CheeseApp)
            modules(listOf(vmModule, dbModule))
        }

        fillInDb()
    }

    private fun fillInDb() = ioThread {
        if (dao.getCheeseList().isEmpty()) {
            val data = database.data.map { Cheese(id = 0, name = it) }
            dao.insert(data)
        }
    }
}
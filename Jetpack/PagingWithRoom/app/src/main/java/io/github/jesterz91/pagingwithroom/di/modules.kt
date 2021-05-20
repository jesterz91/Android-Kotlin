package io.github.jesterz91.pagingwithroom.di

import androidx.room.Room
import io.github.jesterz91.pagingwithroom.db.CheeseDatabase
import io.github.jesterz91.pagingwithroom.ui.CheeseViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val vmModule = module {

    viewModel { CheeseViewModel(get()) }
}

val dbModule = module {

    single { get<CheeseDatabase>().cheeseDao() }

    single { Room.databaseBuilder(get(), CheeseDatabase::class.java, CheeseDatabase.NAME).build() }
}
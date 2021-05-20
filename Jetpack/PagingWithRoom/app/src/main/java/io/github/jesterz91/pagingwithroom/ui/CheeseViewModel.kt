package io.github.jesterz91.pagingwithroom.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSource
import io.github.jesterz91.pagingwithroom.db.Cheese
import io.github.jesterz91.pagingwithroom.db.CheeseDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class CheeseViewModel(private val dao: CheeseDao) : ViewModel() {

    val stream: Flow<PagingData<Cheese>> by lazy {
        Pager(
            config = PagingConfig(
                pageSize = 10,
                initialLoadSize = 10,
                prefetchDistance = 5,
                enablePlaceholders = false,
                maxSize = PagingConfig.MAX_SIZE_UNBOUNDED,
                jumpThreshold = PagingSource.LoadResult.Page.COUNT_UNDEFINED
            ),
            pagingSourceFactory = {
                dao.selectAll()
            }
        ).flow
    }

    fun insert(text: CharSequence) {
        viewModelScope.launch(Dispatchers.IO) {
            dao.insert(Cheese(id = 0, name = text.toString()))
        }
    }

    fun remove(cheese: Cheese) {
        viewModelScope.launch(Dispatchers.IO) {
            dao.delete(cheese)
        }
    }
}
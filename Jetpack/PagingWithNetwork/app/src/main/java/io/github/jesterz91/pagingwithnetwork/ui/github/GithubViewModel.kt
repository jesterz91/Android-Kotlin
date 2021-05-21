package io.github.jesterz91.pagingwithnetwork.ui.github

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSource
import io.github.jesterz91.pagingwithnetwork.data.GithubService
import io.github.jesterz91.pagingwithnetwork.data.model.github.Repo
import io.github.jesterz91.pagingwithnetwork.ui.common.BaseViewModel
import kotlinx.coroutines.flow.Flow

class GithubViewModel(private val service: GithubService) : BaseViewModel() {

    val pagingData: Flow<PagingData<Repo>> by lazy {
        Pager(
            config = PagingConfig(
                pageSize = GithubPagingSource.PAGE_SIZE,
                prefetchDistance = GithubPagingSource.PREFETCH_DISTANCE,
                initialLoadSize = GithubPagingSource.INITIAL_LOAD_SIZE,
                enablePlaceholders = false,
                maxSize = PagingConfig.MAX_SIZE_UNBOUNDED,
                jumpThreshold = PagingSource.LoadResult.Page.COUNT_UNDEFINED
            ),
            pagingSourceFactory = {
                GithubPagingSource(service)
            }
        ).flow
    }
}
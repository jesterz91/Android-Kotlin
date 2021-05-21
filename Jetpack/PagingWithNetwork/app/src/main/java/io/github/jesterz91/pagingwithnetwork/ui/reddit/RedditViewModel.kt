package io.github.jesterz91.pagingwithnetwork.ui.reddit

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSource
import io.github.jesterz91.pagingwithnetwork.data.RedditService
import io.github.jesterz91.pagingwithnetwork.data.model.reddit.RedditPost
import io.github.jesterz91.pagingwithnetwork.ui.common.BaseViewModel
import kotlinx.coroutines.flow.Flow

class RedditViewModel(private val service: RedditService) : BaseViewModel() {

    val pagingData: Flow<PagingData<RedditPost>> by lazy {
        Pager(
            config = PagingConfig(
                pageSize = RedditPagingSource.PAGE_SIZE,
                prefetchDistance = RedditPagingSource.PREFETCH_DISTANCE,
                initialLoadSize = RedditPagingSource.INITIAL_LOAD_SIZE,
                enablePlaceholders = false,
                maxSize = PagingConfig.MAX_SIZE_UNBOUNDED,
                jumpThreshold = PagingSource.LoadResult.Page.COUNT_UNDEFINED
            ),
            pagingSourceFactory = {
                RedditPagingSource(service)
            }
        ).flow
    }
}
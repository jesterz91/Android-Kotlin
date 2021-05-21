package io.github.jesterz91.pagingwithnetwork.ui.reddit

import androidx.paging.PagingSource
import androidx.paging.PagingState
import io.github.jesterz91.pagingwithnetwork.data.RedditService
import io.github.jesterz91.pagingwithnetwork.data.model.reddit.RedditChildrenResponse
import io.github.jesterz91.pagingwithnetwork.data.model.reddit.RedditPost

class RedditPagingSource(private val service: RedditService) : PagingSource<String, RedditPost>() {

    private val subreddit = "ProgrammerHumor"

    override suspend fun load(params: LoadParams<String>): LoadResult<String, RedditPost> {
        return try {
            val response = service.requestSubreddit(subreddit, params.loadSize, params.key)
            LoadResult.Page(
                data = response.listingData.children.map(RedditChildrenResponse::data),
                prevKey = null, // Only paging forward
                nextKey = response.listingData.after
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<String, RedditPost>): String? {
        return null
    }

    companion object {
        const val PAGE_SIZE = 15
        const val INITIAL_LOAD_SIZE = 15
        const val PREFETCH_DISTANCE = 3
    }
}
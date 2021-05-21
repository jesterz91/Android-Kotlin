package io.github.jesterz91.pagingwithnetwork.ui.github

import androidx.paging.PagingSource
import androidx.paging.PagingState
import io.github.jesterz91.pagingwithnetwork.data.GithubService
import io.github.jesterz91.pagingwithnetwork.data.model.github.Repo

class GithubPagingSource(private val service: GithubService) : PagingSource<Int, Repo>() {

    private val query = "kotlin"

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Repo> {
        return try {
            val page = params.key ?: DEFAULT_INIT_KEY // 정의 되지 않았다면, page1에서 리프레시.
            val response = service.searchRepos(query, page, params.loadSize)
            LoadResult.Page(
                data = response.items,
                prevKey = null,
                nextKey = page + 1
            )

        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Repo>): Int? {
        return DEFAULT_INIT_KEY
    }

    companion object {
        const val DEFAULT_INIT_KEY = 1
        const val PAGE_SIZE = 15
        const val INITIAL_LOAD_SIZE = 15
        const val PREFETCH_DISTANCE = 3
    }
}
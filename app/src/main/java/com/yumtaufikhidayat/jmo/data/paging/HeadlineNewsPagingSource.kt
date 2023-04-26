package com.yumtaufikhidayat.jmo.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.yumtaufikhidayat.jmo.data.remote.ApiService
import com.yumtaufikhidayat.jmo.model.news.NewsArticle
import com.yumtaufikhidayat.jmo.utils.Common
import retrofit2.HttpException
import java.io.IOException

class HeadlineNewsPagingSource(
    private val apiKey: String,
    private val apiService: ApiService
) : PagingSource<Int, NewsArticle>() {
    override fun getRefreshKey(state: PagingState<Int, NewsArticle>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, NewsArticle> {
        val query = "labour"
        val searchIn = "title"
        val currentPage = params.key ?: Common.STARTING_PAGE_INDEX
        return try {
            val response = apiService.getEverythingNews(query, searchIn, currentPage, Common.PAGE_SIZE, apiKey)
            val data = response.body()?.newsArticles
            val responseData = mutableListOf<NewsArticle>()
            if (data != null) responseData.addAll(data)

            LoadResult.Page(
                data = responseData,
                prevKey = if (currentPage == Common.STARTING_PAGE_INDEX) null else - 1,
                nextKey = if (data?.isEmpty() == true) null else currentPage + 1
            )
        } catch (httpEx: HttpException) {
            LoadResult.Error(httpEx)
        } catch (ioEx: IOException) {
            LoadResult.Error(ioEx)
        } catch (ex: Exception) {
            LoadResult.Error(ex)
        }
    }
}
package com.yumtaufikhidayat.jmo.data.source

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.yumtaufikhidayat.jmo.BuildConfig
import com.yumtaufikhidayat.jmo.data.BaseApiResponse
import com.yumtaufikhidayat.jmo.data.NetworkResult
import com.yumtaufikhidayat.jmo.data.paging.HeadlineNewsPagingSource
import com.yumtaufikhidayat.jmo.data.remote.ApiService
import com.yumtaufikhidayat.jmo.model.news.NewsResponse
import com.yumtaufikhidayat.jmo.utils.Common
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val apiService: ApiService): BaseApiResponse() {
    private val apiKey = BuildConfig.API_KEY

    fun getHeadlineNews(sources: String): Flow<NetworkResult<NewsResponse>> {
        return flow {
            emit(safeApiCall { apiService.getHeadlineNews(sources, apiKey) })
        }.flowOn(Dispatchers.IO)
    }

    fun getEverythingNews() = Pager(
        PagingConfig(
            pageSize = Common.STARTING_PAGE_INDEX,
            maxSize = Common.PAGE_SIZE,
            enablePlaceholders = false
        ), pagingSourceFactory = {
            HeadlineNewsPagingSource(apiKey, apiService)
        }).liveData
}
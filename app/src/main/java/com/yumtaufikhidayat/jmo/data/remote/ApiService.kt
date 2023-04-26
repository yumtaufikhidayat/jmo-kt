package com.yumtaufikhidayat.jmo.data.remote

import com.yumtaufikhidayat.jmo.model.news.NewsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET(UrlEndpoint.QUERY_TOP_HEADLINE_NEWS)
    suspend fun getHeadlineNews(
        @Query(UrlEndpoint.QUERY_SOURCES) sources: String, // sources = bbc-news
        @Query(UrlEndpoint.QUERY_API_KEY) apiKey: String
    ): Response<NewsResponse>

    @GET(UrlEndpoint.QUERY_EVERYTHING)
    suspend fun getEverythingNews(
        @Query(UrlEndpoint.QUERY_Q) q: String, // q=labour
        @Query(UrlEndpoint.QUERY_SEARCH_IN) searchIn: String, // searchIn=title
        @Query(UrlEndpoint.QUERY_PAGE) page: Int, // page=1
        @Query(UrlEndpoint.QUERY_PAGE_SIZE) pageSize: Int, // pageSize=10
        @Query(UrlEndpoint.QUERY_API_KEY) apiKey: String
    ): Response<NewsResponse>
}
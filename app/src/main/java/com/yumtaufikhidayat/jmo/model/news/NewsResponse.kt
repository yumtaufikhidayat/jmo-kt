package com.yumtaufikhidayat.jmo.model.news


import com.google.gson.annotations.SerializedName

data class NewsResponse(
    @SerializedName("articles")
    val newsArticles: List<NewsArticle>? = null,
    @SerializedName("status")
    val status: String? = null, // ok
    @SerializedName("totalResults")
    val totalResults: Int? = null // 10
)
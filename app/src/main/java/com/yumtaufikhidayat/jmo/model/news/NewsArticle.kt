package com.yumtaufikhidayat.jmo.model.news


import com.google.gson.annotations.SerializedName

data class NewsArticle(
    @SerializedName("author")
    val author: String? = null, // BBC News
    @SerializedName("content")
    val content: String? = null, // Former Strictly Come Dancing head judge Len Goodman has died at the age of 78, his manager has confirmed.He died on Saturday at a hospice in Tunbridge Wells, Kent, surrounded by his family. He had … [+556 chars]
    @SerializedName("description")
    val description: String? = null, // Former Strictly Come Dancing head judge Len Goodman has died at the age of 78, his manager says
    @SerializedName("publishedAt")
    val publishedAt: String? = null, // 2023-04-24T08:52:15.7284819Z
    @SerializedName("source")
    val newsSource: NewsSource? = null,
    @SerializedName("title")
    val title: String? = null, // Ex-Strictly head judge Len Goodman dies at 78
    @SerializedName("url")
    val url: String? = null, // http://www.bbc.co.uk/news/entertainment-arts-65373373
    @SerializedName("urlToImage")
    val urlToImage: String? = null // https://ichef.bbci.co.uk/news/1024/branded_news/11D88/production/_129469037_lenindex_bbc.jpg
)
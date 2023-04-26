package com.yumtaufikhidayat.jmo.model.news


import com.google.gson.annotations.SerializedName

data class NewsSource(
    @SerializedName("id")
    val id: String? = null, // bbc-news
    @SerializedName("name")
    val name: String? = null // BBC News
)
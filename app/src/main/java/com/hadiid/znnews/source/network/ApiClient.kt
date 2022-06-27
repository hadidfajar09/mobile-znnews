package com.hadiid.znnews.source.network

import com.hadiid.znnews.source.news.NewsModel
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiClient {

    @GET("api/news-category")
    suspend fun fetchNews(
        @Query("category_id") category: Int,
        @Query("keyword") keyword: String,
        @Query("page") page: Int,

        ): NewsModel

}
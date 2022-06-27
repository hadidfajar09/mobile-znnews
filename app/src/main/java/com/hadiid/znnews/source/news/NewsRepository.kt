package com.hadiid.znnews.source.news

import com.hadiid.znnews.source.network.ApiClient
import org.koin.dsl.module

val repositoryModule = module {
    factory { NewsRepository(get(), get()) }
}

class NewsRepository(
    private val api: ApiClient,
    val db: NewsDao
) {

    suspend fun fetch(
        category: Int?,
        keyword: String,
        page: Int,
    ): NewsModel{
        return api.fetchNews(
            category!!,
            keyword,
            page
        )
    }

    suspend fun find(articleModel: ArticleModel) = db.find(articleModel.id)

    suspend fun save(articleModel: ArticleModel) {
        db.save(articleModel)
    }

    suspend fun remove(articleModel: ArticleModel) {
        db.remove(articleModel)
    }

}
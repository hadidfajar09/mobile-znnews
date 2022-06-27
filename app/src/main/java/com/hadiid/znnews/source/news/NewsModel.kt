package com.hadiid.znnews.source.news

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

data class NewsModel(

    val status: String,
    val reply: String,
    val totalNews: Int,
    val totalResults: Int,
    val articles: List<ArticleModel>,

)

@Entity(tableName = "tableArticle")
data class ArticleModel (
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val category: String? = "",
    val name: String? = "",
    val title: String? = "",
    val kolumnis: String? = "",
    val description: String? = "",
    val url: String? = "",
    val urlToImage: String? = "",
    val publishedAt: String? ="",
) : Serializable



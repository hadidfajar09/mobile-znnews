package com.hadiid.znnews.source.news

import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface NewsDao {

    @Query("SELECT * FROM tableArticle")
    fun findAll(): LiveData<List<ArticleModel>>

    @Query("SELECT COUNT(*) FROM tableArticle WHERE id=:publish")
    suspend fun find(publish: Int): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(articleModel: ArticleModel)

    @Delete
    suspend fun remove(articleModel: ArticleModel)



}
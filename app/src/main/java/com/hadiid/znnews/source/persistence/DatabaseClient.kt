package com.hadiid.znnews.source.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.hadiid.znnews.source.news.ArticleModel
import com.hadiid.znnews.source.news.NewsDao

@Database(
    entities = [ArticleModel::class],
    version = 4,
    exportSchema = false
)


abstract class DatabaseClient:RoomDatabase() {
    abstract val newsDao: NewsDao

}
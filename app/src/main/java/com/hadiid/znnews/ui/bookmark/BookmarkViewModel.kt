package com.hadiid.znnews.ui.bookmark

import androidx.lifecycle.ViewModel
import com.hadiid.znnews.source.news.NewsRepository
import org.koin.dsl.module

val bookmarkViewModel = module {
    factory { BookmarkViewModel(get()) }
}

class BookmarkViewModel(
    val repository: NewsRepository
): ViewModel() {

    val title = "Baca Nanti"
    val articles = repository.db.findAll()

}
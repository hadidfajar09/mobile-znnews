package com.hadiid.znnews.ui.info

import androidx.lifecycle.ViewModel
import com.hadiid.znnews.source.news.NewsRepository
import org.koin.dsl.module

val infoViewModel = module {
    factory { InfoViewModel(get()) }
}


//provide data utk UI
class InfoViewModel(
    val repository: NewsRepository
): ViewModel() {
    val title = "Tentang"
}
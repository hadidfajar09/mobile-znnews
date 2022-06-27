package com.hadiid.znnews.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hadiid.znnews.source.news.CategoryModel
import com.hadiid.znnews.source.news.NewsModel
import com.hadiid.znnews.source.news.NewsRepository
import kotlinx.coroutines.launch
import org.koin.dsl.module
import kotlin.math.ceil

val homeViewModel = module {
    factory { HomeViewModel(get()) }
}

class HomeViewModel(
    val repository: NewsRepository
): ViewModel() {

    val title = "ZNNews"
    val category by lazy { MutableLiveData<Int>() }
    val message by lazy { MutableLiveData<String>() }
    val loading by lazy { MutableLiveData<Boolean>() }
    val loadMore by lazy { MutableLiveData<Boolean>() }
    val news by lazy { MutableLiveData<NewsModel>() }


    init {
        category.value = 1
        message.value = null
    }

    var keyword = ""
    var page = 1
    var total = 1

    fun fetch(){
        if(page > 1) loadMore.value = true else loading.value = true
        viewModelScope.launch {
            try {
                val response = repository.fetch(
                    category.value!!,
                    keyword,
                    page
                )
                news.value = response
                val totalResults: Double = response.totalResults / 20.0
                total = ceil(totalResults).toInt()
                page ++
                loading.value = false
                loadMore.value = false
            }catch (e: Exception){
                message.value = "Ada Kesalahan"
            }
        }
    }

    //data yg tampil d aplikasi n id parameter yg dgunakan
    val categories = listOf<CategoryModel>(
        CategoryModel(1,"Berita Utama"),
        CategoryModel(2,"HIburan"),
        CategoryModel(3,"Bisnis"),
        CategoryModel(4,"Kesehatan"),
        CategoryModel(5,"Sains"),
        CategoryModel(7,"Teknologi"),
        CategoryModel(8,"Agama"),
        CategoryModel(14,"Olahraga"),

    )

}
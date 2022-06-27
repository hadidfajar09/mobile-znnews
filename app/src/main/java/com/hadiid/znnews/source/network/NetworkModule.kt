package com.hadiid.znnews.source.network

import com.google.gson.GsonBuilder
import com.hadiid.znnews.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val baseUrl = "https://cakramorowali.com/"

val networkModule = module {
    single { provideOkhttpClient() }
    single { provideRetrofit(get()) }
    single { provideNewsApi(get()) }
}

fun provideOkhttpClient() : OkHttpClient{
    return OkHttpClient.Builder()
        .addInterceptor(
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        )
        .build()
}

fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder()
        .baseUrl(baseUrl)
        .client( okHttpClient )
        .addConverterFactory (
            GsonConverterFactory.create(
                GsonBuilder().serializeNulls().create()
            )
        )
        .build()
}

fun provideNewsApi(retrofit: Retrofit):ApiClient = retrofit.create(ApiClient::class.java)
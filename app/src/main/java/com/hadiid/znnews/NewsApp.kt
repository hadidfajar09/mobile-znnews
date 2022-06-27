package com.hadiid.znnews

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.hadiid.znnews.source.network.networkModule
import com.hadiid.znnews.source.news.repositoryModule
import com.hadiid.znnews.source.persistence.databaseModule
import com.hadiid.znnews.ui.bookmark.bookmarkModule
import com.hadiid.znnews.ui.bookmark.bookmarkViewModel
import com.hadiid.znnews.ui.detail.detailModule
import com.hadiid.znnews.ui.detail.detailViewModel
import com.hadiid.znnews.ui.home.homeModule
import com.hadiid.znnews.ui.home.homeViewModel
import com.hadiid.znnews.ui.info.infoModule
import com.hadiid.znnews.ui.info.infoViewModel
import com.hadiid.znnews.util.PrefManager
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import timber.log.Timber

class NewsApp: Application() {

    private val pref by lazy { PrefManager(this) }


    override fun onCreate() {
        super.onCreate()

        Timber.plant(Timber.DebugTree())

        when (pref.getBoolean("pref_is_dark")){
            true->{
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }
            false->{
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }

        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@NewsApp)
            modules(
                networkModule,
                repositoryModule,
                homeViewModel,
                homeModule,
                bookmarkViewModel,
                bookmarkModule,
                databaseModule,
                detailViewModel,
                detailModule,
                infoViewModel,
                infoModule

            )
        }
    }
}
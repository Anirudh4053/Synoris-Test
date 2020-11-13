package com.anirudh.synoristestapp

import android.app.Application
import com.anirudh.synoristestapp.data.db.AppDatabase
import com.anirudh.synoristestapp.data.network.MyApi
import com.anirudh.synoristestapp.ui.MainRepository
import com.anirudh.synoristestapp.ui.MainViewModel
import com.anirudh.synoristestapp.ui.MainViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class MVVMApplication : Application(), KodeinAware {
    override val kodein =  Kodein.lazy{
        import(androidXModule(this@MVVMApplication))

        bind() from singleton { MyApi() }
        bind() from singleton { AppDatabase(instance()) }
        bind() from singleton { MainRepository(instance(),instance())}
        bind() from provider { MainViewModelFactory(instance()) }
    }
}
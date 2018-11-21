package ru.kizapp.yadiskclient

import android.app.Application
import org.koin.android.ext.android.startKoin
import ru.kizapp.yadiskclient.di.appModule


class App : Application() {

    override fun onCreate() {
        super.onCreate()
        //TODO init di
        startKoin(this, listOf(appModule))

    }

}
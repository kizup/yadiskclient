package ru.kizapp.yadiskclient.di

import android.content.Context
import android.content.SharedPreferences
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module
import ru.kizapp.yadiskclient.data.prefs.PrefsRepo
import ru.kizapp.yadiskclient.data.prefs.PrefsRepoImpl
import ru.kizapp.yadiskclient.viewmodel.app.AppViewModel
import ru.kizapp.yadiskclient.viewmodel.list.ListFilesViewModel
import ru.kizapp.yadiskclient.viewmodel.main.MainContainerViewModel
import ru.kizapp.yadiskclient.viewmodel.splash.SplashViewModel
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.Router

const val MAIN_VIEW_MODEL = "main_view_model"

val appModule = module {
    val cicerone: Cicerone<Router> = Cicerone.create()
    single { cicerone }
    single { cicerone.router }
    single { cicerone.navigatorHolder }

    single {
        androidApplication()
                .getSharedPreferences("YaDiskClient_prefs", Context.MODE_PRIVATE) as SharedPreferences
    }
    single { PrefsRepoImpl(get()) as PrefsRepo }

    // SplashViewModel
    viewModel { SplashViewModel(get(), get()) }
    // AppViewModel
    viewModel { AppViewModel(get(), get()) }
    // ListFilesViewModel
    viewModel { ListFilesViewModel(get(), get()) }
    // MainContainerViewModel
    single (name = MAIN_VIEW_MODEL) {
        MainContainerViewModel(get())
    }
}
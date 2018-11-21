package ru.kizapp.yadiskclient.di

import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module
import ru.kizapp.yadiskclient.viewmodel.splash.SplashViewModel
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.Router

val appModule = module {
    val cicerone: Cicerone<Router> = Cicerone.create()
    single { cicerone }
    single { cicerone.router }
    single { cicerone.navigatorHolder }

    viewModel { SplashViewModel(get()) }
}
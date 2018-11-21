package ru.kizapp.yadiskclient.viewmodel.splash

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import ru.kizapp.yadiskclient.model.Screens
import ru.kizapp.yadiskclient.viewmodel.base.BaseViewModel
import ru.terrakok.cicerone.Router
import java.util.concurrent.TimeUnit

class SplashViewModel(router: Router) : BaseViewModel(router) {

    fun created() {
        val d = Observable.timer(2, TimeUnit.SECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                router.navigateTo(Screens.OAuth)
            }
    }

}
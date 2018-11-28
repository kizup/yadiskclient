package ru.kizapp.yadiskclient.viewmodel.splash

import android.text.TextUtils
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import ru.kizapp.yadiskclient.data.prefs.PrefsRepo
import ru.kizapp.yadiskclient.model.ROOT_DIR
import ru.kizapp.yadiskclient.model.Screens
import ru.kizapp.yadiskclient.viewmodel.base.BaseViewModel
import ru.terrakok.cicerone.Router
import java.util.concurrent.TimeUnit

class SplashViewModel(router: Router, private val mPrefs: PrefsRepo) : BaseViewModel(router) {

    fun created() {
        if (TextUtils.isEmpty(mPrefs.getToken())) {
            val d = Observable.timer(2, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    router.navigateTo(Screens.OAuth)
                }
        } else {
            val d = Observable.timer(2, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    router.newRootScreen(Screens.MainFlow)
                }
        }
    }

}
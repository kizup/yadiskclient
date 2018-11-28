package ru.kizapp.yadiskclient.viewmodel.app

import android.net.Uri
import android.text.TextUtils
import android.util.Log
import ru.kizapp.yadiskclient.data.prefs.PrefsRepo
import ru.kizapp.yadiskclient.model.Screens
import ru.kizapp.yadiskclient.viewmodel.base.BaseViewModel
import ru.terrakok.cicerone.Router
import java.util.regex.Pattern

class AppViewModel(router: Router, private val mPrefs: PrefsRepo) : BaseViewModel(router) {

    companion object {
        private const val TAG = "YaDiskClient"
    }

    fun coldStart() {
        router.newRootScreen(Screens.SplashFlow)
    }

    fun onLogin(data: Uri) {
        val pattern = Pattern.compile("access_token=(.*?)(&|$)")
        val matcher = pattern.matcher(data.toString())
        if (matcher.find()) {
            val token = matcher.group(1)
            if (!TextUtils.isEmpty(token)) {
                Log.d(TAG, "onLogin: token: $token")
                mPrefs.saveToken(token)
                router.newRootScreen(Screens.SplashFlow)
            } else {
                Log.w(TAG, "onRegistrationSuccess: empty token")
            }
        } else {
            Log.w(TAG, "onRegistrationSuccess: token not found in return url")
        }
    }

}
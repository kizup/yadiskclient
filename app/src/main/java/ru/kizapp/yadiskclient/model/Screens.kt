package ru.kizapp.yadiskclient.model

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.fragment.app.Fragment
import ru.kizapp.yadiskclient.cicerone.SupportAppScreen
import ru.kizapp.yadiskclient.view.splash.SplashFlowFragment

object Screens {

    object Splash : SupportAppScreen() {
        override fun getFragment(): Fragment? {
            return SplashFlowFragment()
        }
    }

    object OAuth : SupportAppScreen() {
        override fun getActivityIntent(context: Context): Intent? {
            return Intent(Intent.ACTION_VIEW, Uri.parse(OAUTH_URL))
        }
    }

}
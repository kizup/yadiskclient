package ru.kizapp.yadiskclient.model

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.fragment.app.Fragment
import ru.kizapp.yadiskclient.cicerone.SupportAppScreen
import ru.kizapp.yadiskclient.view.list.ListFilesFragment
import ru.kizapp.yadiskclient.view.main.MainFlowFragment
import ru.kizapp.yadiskclient.view.splash.SplashFlowFragment
import ru.kizapp.yadiskclient.view.splash.SplashFragment

object Screens {

    object SplashFlow : SupportAppScreen() {
        override fun getFragment(): Fragment? {
            return SplashFlowFragment()
        }
    }

    object Splash : SupportAppScreen() {
        override fun getFragment(): Fragment? {
            return SplashFragment()
        }
    }

    object OAuth : SupportAppScreen() {
        override fun getActivityIntent(context: Context): Intent? {
            return Intent(Intent.ACTION_VIEW, Uri.parse(OAUTH_URL))
        }
    }

    object MainFlow : SupportAppScreen() {
        override fun getFragment(): Fragment? {
            return MainFlowFragment()
        }
    }

    data class ListFiles(val dir: String) : SupportAppScreen() {
        override fun getFragment(): Fragment? {
            return ListFilesFragment.newInstance(dir)
        }
    }

}
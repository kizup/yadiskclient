package ru.kizapp.yadiskclient.cicerone

import android.content.Context
import android.content.Intent
import androidx.fragment.app.Fragment
import ru.terrakok.cicerone.Screen

open class SupportAppScreen : Screen() {

    open fun getFragment(): Fragment? {
        return null
    }

    open fun getActivityIntent(context: Context): Intent? {
        return null
    }

}
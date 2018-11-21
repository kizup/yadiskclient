package ru.kizapp.yadiskclient

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import org.koin.android.ext.android.inject
import ru.kizapp.yadiskclient.cicerone.SupportAppNavigator
import ru.kizapp.yadiskclient.model.Screens
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.Router

class AppActivity : AppCompatActivity() {

    private val mCicerone: Cicerone<Router> by inject()
    private val mRouter: Router by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_app)

        mCicerone.navigatorHolder.setNavigator(SupportAppNavigator(this, R.id.container))

        val data = intent

        if (data != null) {
            println(data)
        }

        if (savedInstanceState == null) {
            mRouter.newRootChain(Screens.Splash)
        }
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        println(intent)
    }

}

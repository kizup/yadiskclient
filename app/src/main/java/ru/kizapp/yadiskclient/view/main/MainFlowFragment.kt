package ru.kizapp.yadiskclient.view.main

import android.os.Bundle
import ru.kizapp.yadiskclient.R
import ru.kizapp.yadiskclient.ext.setLaunchScreen
import ru.kizapp.yadiskclient.model.ROOT_DIR
import ru.kizapp.yadiskclient.model.Screens
import ru.kizapp.yadiskclient.view.base.FlowFragment

class MainFlowFragment : FlowFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (childFragmentManager.fragments.isEmpty() && savedInstanceState == null) {
//            mNavigator.setLaunchScreen(Screens.ListFiles(ROOT_DIR))
            mNavigator.setLaunchScreen(Screens.MainContainer)
        }
    }

}
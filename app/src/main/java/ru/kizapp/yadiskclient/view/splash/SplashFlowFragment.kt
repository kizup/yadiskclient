package ru.kizapp.yadiskclient.view.splash

import android.os.Bundle
import android.view.View
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.kizapp.yadiskclient.R
import ru.kizapp.yadiskclient.cicerone.SupportAppNavigator
import ru.kizapp.yadiskclient.ext.setLaunchScreen
import ru.kizapp.yadiskclient.model.Screens
import ru.kizapp.yadiskclient.view.base.FlowFragment
import ru.kizapp.yadiskclient.viewmodel.splash.SplashViewModel

class SplashFlowFragment : FlowFragment() {

    private val mSplashViewModel: SplashViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (childFragmentManager.fragments.isEmpty()) {
            mNavigator.setLaunchScreen(Screens.Splash)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mSplashViewModel.created()
    }

}
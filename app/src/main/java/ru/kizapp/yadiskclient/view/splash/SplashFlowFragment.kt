package ru.kizapp.yadiskclient.view.splash

import android.os.Bundle
import android.view.View
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.kizapp.yadiskclient.R
import ru.kizapp.yadiskclient.cicerone.SupportAppNavigator
import ru.kizapp.yadiskclient.view.base.FlowFragment
import ru.kizapp.yadiskclient.viewmodel.splash.SplashViewModel
import ru.terrakok.cicerone.Navigator

class SplashFlowFragment : FlowFragment() {

    private val mSplashViewModel: SplashViewModel by viewModel()

    override fun getNavigator(): Navigator? {
        return SupportAppNavigator(mBaseActivity, R.id.container)
    }

    override fun getLayoutId(): Int = R.layout.fragment_splash

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mSplashViewModel.created()
    }

}
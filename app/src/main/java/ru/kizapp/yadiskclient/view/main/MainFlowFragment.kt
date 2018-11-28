package ru.kizapp.yadiskclient.view.main

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.fragment_main_container.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.kizapp.yadiskclient.R
import ru.kizapp.yadiskclient.di.MAIN_VIEW_MODEL
import ru.kizapp.yadiskclient.ext.setLaunchScreen
import ru.kizapp.yadiskclient.model.ROOT_DIR
import ru.kizapp.yadiskclient.model.Screens
import ru.kizapp.yadiskclient.view.base.FlowFragment
import ru.kizapp.yadiskclient.viewmodel.main.MainContainerViewModel

class MainFlowFragment : FlowFragment() {

    private val mMainViewModel: MainContainerViewModel by viewModel(name = MAIN_VIEW_MODEL)

    override fun getLayoutId() = R.layout.fragment_main_container

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (childFragmentManager.fragments.isEmpty() && savedInstanceState == null) {
            mNavigator.setLaunchScreen(Screens.ListFiles(ROOT_DIR))
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mMainViewModel.titleLiveData.observe(this, Observer {
            appBarTitle.text = it
        })
    }

}
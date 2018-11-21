package ru.kizapp.yadiskclient.view.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.koin.android.ext.android.inject
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.Router

abstract class FlowFragment : BaseFragment() {

    abstract fun getNavigator(): Navigator?
    abstract fun getLayoutId(): Int

    private val mCicerone: Cicerone<Router> by inject()

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        mCicerone.navigatorHolder.setNavigator(getNavigator())
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(getLayoutId(), container, false)
    }

}
package ru.kizapp.yadiskclient.view.base

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import org.koin.android.ext.android.inject
import ru.kizapp.yadiskclient.R
import ru.kizapp.yadiskclient.cicerone.SupportAppNavigator
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import ru.terrakok.cicerone.commands.Command

abstract class FlowFragment : BaseFragment() {

    override fun getLayoutId(): Int = R.layout.layout_container

    protected val mNavigator: Navigator by lazy {
        object : SupportAppNavigator(mBaseActivity, childFragmentManager, R.id.container) {
            override fun setupFragmentTransaction(
                command: Command,
                currentFragment: Fragment?,
                nextFragment: Fragment?,
                fragmentTransaction: FragmentTransaction
            ) {
                fragmentTransaction.setReorderingAllowed(true)
            }
        }
    }

    private val mCicerone: Cicerone<Router> by inject()
    private val mNavigatorHolder: NavigatorHolder by lazy {
        mCicerone.navigatorHolder
    }
    private val mCurrentFragment: BaseFragment?
        get() = childFragmentManager.findFragmentById(R.id.container) as? BaseFragment

    override fun onBackPressed() {
        mCurrentFragment?.onBackPressed() ?: super.onBackPressed()
    }

    override fun onResume() {
        super.onResume()
        mNavigatorHolder.setNavigator(mNavigator)
    }

    override fun onPause() {
        mNavigatorHolder.removeNavigator()
        super.onPause()
    }

}
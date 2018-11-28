package ru.kizapp.yadiskclient

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.kizapp.yadiskclient.cicerone.SupportAppNavigator
import ru.kizapp.yadiskclient.view.base.BaseFragment
import ru.kizapp.yadiskclient.viewmodel.app.AppViewModel
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.Router
import ru.terrakok.cicerone.commands.Command

class AppActivity : AppCompatActivity() {

    private val mViewModel: AppViewModel by viewModel()
    private val mCicerone: Cicerone<Router> by inject()
    private val mCurrentFragment: BaseFragment?
        get() = supportFragmentManager.findFragmentById(R.id.container) as? BaseFragment

    private val mNavigator: Navigator by lazy {
        object : SupportAppNavigator(this, supportFragmentManager, R.id.container){
            override fun setupFragmentTransaction(command: Command, currentFragment: Fragment?, nextFragment: Fragment?, fragmentTransaction: FragmentTransaction) {
                fragmentTransaction.setReorderingAllowed(true)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_container)

        val data = intent

        if (data != null && data.data != null) {
            mViewModel.onLogin(data.data!!)
            return
        }

        if (savedInstanceState == null) {
            mViewModel.coldStart()
        }
    }

    override fun onBackPressed() {
        mCurrentFragment?.onBackPressed() ?: super.onBackPressed()
    }

    override fun onResume() {
        super.onResume()
        mCicerone.navigatorHolder.setNavigator(mNavigator)
    }

    override fun onPause() {
        mCicerone.navigatorHolder.removeNavigator()
        super.onPause()
    }

}

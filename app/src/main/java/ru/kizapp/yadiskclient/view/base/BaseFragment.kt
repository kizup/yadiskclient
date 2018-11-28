package ru.kizapp.yadiskclient.view.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

abstract class BaseFragment : Fragment() {

    abstract fun getLayoutId(): Int

    protected lateinit var mBaseActivity: AppCompatActivity

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        mBaseActivity = context as AppCompatActivity
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(getLayoutId(), container, false)
    }

    open fun onBackPressed() {}

}
package ru.kizapp.yadiskclient.view.base

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

abstract class BaseFragment : Fragment() {

    protected lateinit var mBaseActivity: AppCompatActivity

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        mBaseActivity = context as AppCompatActivity
    }

}
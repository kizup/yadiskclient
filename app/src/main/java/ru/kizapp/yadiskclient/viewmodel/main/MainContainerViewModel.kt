package ru.kizapp.yadiskclient.viewmodel.main

import androidx.lifecycle.MutableLiveData
import ru.kizapp.yadiskclient.viewmodel.base.BaseViewModel
import ru.terrakok.cicerone.Router

class MainContainerViewModel(router: Router) : BaseViewModel(router) {

    val titleLiveData = MutableLiveData<String>()

    fun setTitle(title: String) {
        titleLiveData.value = title
    }

}
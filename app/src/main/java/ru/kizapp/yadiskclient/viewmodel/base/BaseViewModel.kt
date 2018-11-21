package ru.kizapp.yadiskclient.viewmodel.base

import androidx.lifecycle.ViewModel
import ru.terrakok.cicerone.Router

abstract class BaseViewModel(protected val router: Router) : ViewModel()
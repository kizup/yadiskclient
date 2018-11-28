package ru.kizapp.yadiskclient.viewmodel.base

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import ru.terrakok.cicerone.Router

abstract class BaseViewModel(protected val router: Router) : ViewModel() {

    private val mCompositeDisposable = CompositeDisposable()

    fun launch(disposable: () -> Disposable) {
        mCompositeDisposable.add(disposable.invoke())
    }

    override fun onCleared() {
        super.onCleared()
        if (mCompositeDisposable.isDisposed.not()) {
            mCompositeDisposable.clear()
        }
    }

}
package ru.kizapp.yadiskclient.viewmodel.list

import androidx.lifecycle.MutableLiveData
import com.yandex.disk.rest.Credentials
import com.yandex.disk.rest.RestClient
import com.yandex.disk.rest.json.Resource
import com.yandex.disk.rest.util.ResourcePath
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import ru.kizapp.yadiskclient.data.disk.DiskRepo
import ru.kizapp.yadiskclient.data.disk.DiskRepoImpl
import ru.kizapp.yadiskclient.data.prefs.PrefsRepo
import ru.kizapp.yadiskclient.model.Screens
import ru.kizapp.yadiskclient.viewmodel.base.BaseViewModel
import ru.terrakok.cicerone.Router

class ListFilesViewModel(router: Router, mPrefs: PrefsRepo) : BaseViewModel(router) {

    private val mDiskRepo : DiskRepo
    var mCurrentDir: String = ""
    val mFilesLiveData = MutableLiveData<Resource>()

    init {
        mDiskRepo = DiskRepoImpl(RestClient(Credentials("", mPrefs.getToken())))
    }

    fun loadFiles() {
        launch {
            mDiskRepo.listFiles(mCurrentDir)
                .flatMap { src ->
                    return@flatMap Observable.fromIterable(src.resourceList.items)
                        .flatMapSingle { item ->
                            return@flatMapSingle mDiskRepo.metainfo(item)
                                .map {
                                    val index = src.resourceList.items.indexOf(item)
                                    src.resourceList.items[index] = it
                                    return@map index
                                }
                        }
                        .toList()
                        .map { return@map src }
                }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    mFilesLiveData.value = it
                }, {
                    it.printStackTrace()
                })
        }
    }

    fun resourceSelected(resource: Resource) {
        if (resource.isDir) {
            val path = resource.path
            router.navigateTo(Screens.ListFiles(path.path))
        }
    }

    fun onBackPressed() {
        router.exit()
    }

}
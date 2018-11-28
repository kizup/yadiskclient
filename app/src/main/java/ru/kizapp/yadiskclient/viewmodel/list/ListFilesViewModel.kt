package ru.kizapp.yadiskclient.viewmodel.list

import androidx.lifecycle.MutableLiveData
import com.yandex.disk.rest.Credentials
import com.yandex.disk.rest.RestClient
import com.yandex.disk.rest.json.Resource
import com.yandex.disk.rest.util.ResourcePath
import io.reactivex.functions.Consumer
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
        mDiskRepo.listFiles(mCurrentDir)
                .subscribe(Consumer {
                    mFilesLiveData.value = it
                }, Consumer {
                    it.printStackTrace()
                })
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
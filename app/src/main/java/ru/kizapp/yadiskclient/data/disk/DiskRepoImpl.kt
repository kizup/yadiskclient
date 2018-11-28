package ru.kizapp.yadiskclient.data.disk

import com.yandex.disk.rest.ResourcesArgs
import com.yandex.disk.rest.RestClient
import com.yandex.disk.rest.json.Resource
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class DiskRepoImpl(private val mClient: RestClient) : DiskRepo {

    override fun listFiles(dir: String): Single<Resource> {
        return Single.fromCallable {
            mClient.getResources(ResourcesArgs.Builder()
                    .setPath(dir)
                    .setSort(ResourcesArgs.Sort.name)
                    .build())
        }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

}
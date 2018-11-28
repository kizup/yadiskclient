package ru.kizapp.yadiskclient.data.disk

import com.yandex.disk.rest.json.Resource
import io.reactivex.Single

interface DiskRepo {

    fun listFiles(dir: String): Single<Resource>

}
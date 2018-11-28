package ru.kizapp.yadiskclient.data.disk

import com.yandex.disk.rest.ResourcesArgs
import com.yandex.disk.rest.RestClient
import com.yandex.disk.rest.json.Resource
import io.reactivex.Single
import retrofit.mime.FormUrlEncodedTypedOutput

class DiskRepoImpl(private val mClient: RestClient) : DiskRepo {

    override fun listFiles(dir: String): Single<Resource> {
        return Single.fromCallable {
            mClient.getResources(
                ResourcesArgs.Builder()
                    .setPath(dir)
                    .setLimit(1000)
                    .setSort(ResourcesArgs.Sort.name)
                    .build()
            )
        }
    }

    override fun metainfo(src: Resource): Single<Resource> {
        return Single.fromCallable {
            mClient.patchResource(
                ResourcesArgs.Builder()
                    .setPath(src.path.path)
                    .setPreviewCrop(true)
                    .setPreviewSize("S")
                    .setBody(FormUrlEncodedTypedOutput())
                    .build()
            )
        }
    }

}
package daboya.task.pagination

import android.os.Handler
import android.os.Looper
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.paging.PageKeyedDataSource
import androidx.recyclerview.widget.RecyclerView
import com.ethanhua.skeleton.Skeleton
import daboya.task.R
import daboya.task.adapter.ImageAdapter
import daboya.task.di.NetworkManager
import daboya.task.extras.MyApplication
import daboya.task.rest.ImageResponse
import javax.inject.Inject

class ImagesDataSource(
    var context: FragmentActivity,
    var adapter: ImageAdapter,
    var recyclerView: RecyclerView
) : PageKeyedDataSource<Int, ImageResponse>() {

    @Inject
    lateinit var networkManager: NetworkManager

    private var pageNumber: Int = 1

    init {
        MyApplication.getAppComponent().inject(this)
    }

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, ImageResponse>
    ) {

        Handler(Looper.getMainLooper()).post {

            val skeletonScreen = Skeleton.bind(recyclerView)
                .adapter(adapter)
                .load(R.layout.item_images)
                .count(5)
                .show()

            networkManager.mainScreenManager.getImages(pageNumber)
                .observe(context, Observer<List<ImageResponse>> {
                    skeletonScreen.hide()
                    if (it == null) return@Observer
                    pageNumber++
                    callback.onResult(it, null, pageNumber)
                })
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, ImageResponse>) {
        Handler(Looper.getMainLooper()).post {
            networkManager.mainScreenManager.getImages(pageNumber).observe(context,
                Observer<List<ImageResponse>> {
                    if (it == null) return@Observer
                    pageNumber++
                    callback.onResult(it, pageNumber)
                })
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, ImageResponse>) {
    }
}
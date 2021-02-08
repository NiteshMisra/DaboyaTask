package daboya.task.pagination

import androidx.fragment.app.FragmentActivity
import androidx.paging.DataSource
import androidx.recyclerview.widget.RecyclerView
import daboya.task.adapter.ImageAdapter
import daboya.task.rest.ImageResponse

class ImagesDataFactory(
    var context: FragmentActivity, var adapter: ImageAdapter,
    var recyclerView: RecyclerView
) : DataSource.Factory<Int, ImageResponse>() {

    override fun create(): DataSource<Int, ImageResponse> {
        return ImagesDataSource(context, adapter, recyclerView)
    }
}
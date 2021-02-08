package daboya.task.viewmodel

import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import androidx.recyclerview.widget.RecyclerView
import daboya.task.adapter.ImageAdapter
import daboya.task.pagination.ImagesDataFactory
import daboya.task.rest.ImageResponse

class MyViewModel : ViewModel() {

    fun getImages(context : FragmentActivity, adapter: ImageAdapter, recyclerView: RecyclerView
    ) : LiveData<PagedList<ImageResponse>>{
        val itemDataSourceFactory = ImagesDataFactory(context,adapter, recyclerView)

        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setPageSize(20)
            .build()

        return LivePagedListBuilder<Int, ImageResponse>(itemDataSourceFactory, config).build()
    }

}
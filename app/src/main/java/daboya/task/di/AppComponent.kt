package daboya.task.di

import daboya.task.activity.BaseActivity
import daboya.task.extras.MyApplication
import daboya.task.pagination.ImagesDataSource
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class])
interface AppComponent {
    fun inject(myApplication: MyApplication)
    fun inject(baseActivity: BaseActivity)
    fun inject(imagesDataSource: ImagesDataSource)
}

package daboya.task.di

import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class NetworkModule(var context : Context) {

    @Provides
    @Singleton
    fun getNetworkManager() = NetworkManager(context)

}
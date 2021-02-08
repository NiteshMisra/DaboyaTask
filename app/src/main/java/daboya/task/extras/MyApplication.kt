package daboya.task.extras

import android.app.Application
import android.content.Context
import daboya.task.di.AppComponent
import daboya.task.di.DaggerAppComponent
import daboya.task.di.NetworkModule

class MyApplication : Application(){

    override fun onCreate() {
        super.onCreate()
        context = this

        appComponent = DaggerAppComponent.builder()
            .networkModule(NetworkModule(context))
            .build()
        appComponent.inject(this)

    }

    companion object {
        private lateinit var context : Context
        private lateinit var appComponent: AppComponent

        fun getApplicationContext() : Context = context

        fun getAppComponent() : AppComponent = appComponent
    }

}
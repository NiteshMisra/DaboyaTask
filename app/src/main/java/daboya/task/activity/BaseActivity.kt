package daboya.task.activity

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import daboya.task.di.NetworkManager
import daboya.task.extras.MyApplication
import javax.inject.Inject

open class BaseActivity : AppCompatActivity() {

    @Inject
    lateinit var networkManager: NetworkManager

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        MyApplication.getAppComponent().inject(this)
    }

}
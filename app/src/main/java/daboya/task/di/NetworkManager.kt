package daboya.task.di

import android.content.Context
import daboya.task.manager.MainScreenManager

// We define all the required object in this class
class NetworkManager(var context: Context){

    var mainScreenManager: MainScreenManager = MainScreenManager(context)

}
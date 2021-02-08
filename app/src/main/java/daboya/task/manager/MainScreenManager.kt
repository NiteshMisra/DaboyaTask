package daboya.task.manager

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import daboya.task.rest.ImageResponse
import daboya.task.rest.RetrofitClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class MainScreenManager(var context: Context) {

    private var retrofitClient = RetrofitClient.getInstance()

    fun getImages(pageNumber : Int) : LiveData<List<ImageResponse>>{
        val data : MutableLiveData<List<ImageResponse>> = MutableLiveData()
        CoroutineScope(Dispatchers.IO).launch {
            val response = retrofitClient.api.getImages(pageNumber,20)
            if (response.isSuccessful){
                data.postValue(response.body())
            }else{
                data.postValue(null)
            }
        }
        return data;
    }

}
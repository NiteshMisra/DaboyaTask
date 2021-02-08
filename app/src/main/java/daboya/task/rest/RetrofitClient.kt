package daboya.task.rest

import com.google.gson.GsonBuilder
import daboya.task.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitClient {

    private val retrofit: Retrofit

    companion object {

        private var instance: RetrofitClient? = null

        @Synchronized
        fun getInstance(): RetrofitClient {
            if (instance == null) {
                instance = RetrofitClient()
            }
            return instance!!
        }
    }

    val api: Api
        get() {
            return retrofit.create(Api::class.java)
        }

    init {

        val client = getAuthHttpClient()!!.build()

        val gson = GsonBuilder().setLenient().create()

        retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(client)
            .build()
    }

    private fun getAuthHttpClient(): OkHttpClient.Builder? {
        val httpClient = OkHttpClient.Builder()
        httpClient.readTimeout(120, TimeUnit.SECONDS)
        httpClient.addInterceptor(Interceptor { chain: Interceptor.Chain ->
            val original = chain.request()

            // Customize the request
            val request = original.newBuilder()
                .header("Content-Type", "application/json")
                .method(original.method, original.body)
                .build()
            chain.proceed(request)
        })
        if (BuildConfig.DEBUG) {
            val mLogging = HttpLoggingInterceptor()
            mLogging.setLevel(HttpLoggingInterceptor.Level.BODY)
            httpClient.addInterceptor(mLogging)
        }
        return httpClient
    }

}
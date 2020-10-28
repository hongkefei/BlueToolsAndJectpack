package com.wf.tkt.trecycle.net
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

//私有化构造方法
class RetrofitUtils private constructor(){

//    单例 静态
val retrofitu :Retrofit
//    构造方法中第一行执行的代码
    init {
//    初始化咱们的retrofit和ok
    val okhttp = OkHttpClient.Builder().build()

     retrofitu = Retrofit.Builder().
     baseUrl("https://gank.io/api/").
     client(okhttp).
     addConverterFactory(GsonConverterFactory.create()).
     build()
    }

//    静态区
    companion object {
//    单例
        val retrofitUtils: RetrofitUtils by lazy {
            RetrofitUtils()
        }

    }

    fun <T> getApiServices(services: Class<T>):T{
        return retrofitu.create(services)
    }
}




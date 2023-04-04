package com.ms.quizapp.data.network

import android.content.Context
import com.google.gson.GsonBuilder
import com.ms.quizapp.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

import java.util.concurrent.TimeUnit



object RetrofitClient {
    val SUCCESS = "success"
    val SUCESS = "sucess"
    private const val REQUEST_TIMEOUT = 600L
    private var okHttpClient: OkHttpClient? = null
    private var retrofit: Retrofit? = null


    fun restApi(context: Context? = null): RestApi {
        val gson = GsonBuilder()
            .setLenient()
            .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
            .create()

        if (okHttpClient == null)
            initOkHttp(context)


        if (retrofit == null)
            retrofit = Retrofit.Builder()
                .baseUrl(Apis.getBaseUrl())
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addConverterFactory(ScalarsConverterFactory.create())
                /*.addCallAdapterFactory(NetworkResponseAdapterFactory())*/
                .build()
        return retrofit?.create(RestApi::class.java)!!
    }

    private fun initOkHttp(context: Context?) {
        val httpClient: OkHttpClient.Builder = OkHttpClient().newBuilder()
            .connectTimeout(2, TimeUnit.MINUTES)
            .readTimeout(1, TimeUnit.MINUTES)
            .writeTimeout(2, TimeUnit.MINUTES)

        httpClient.addInterceptor(Interceptor { chain: Interceptor.Chain ->
            val original = chain.request()
            val requestBuilder: Request.Builder =
                original.newBuilder()
                    .addHeader("Content-Type", "application/json")
            val request: Request = requestBuilder.build()
            chain.proceed(request)
        })

        val interceptor = HttpLoggingInterceptor()
        //interceptor.level = HttpLoggingInterceptor.Level.HEADERS
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        if (BuildConfig.DEBUG) {
            httpClient.addInterceptor(interceptor)
        }
        okHttpClient = httpClient.build()
    }




}
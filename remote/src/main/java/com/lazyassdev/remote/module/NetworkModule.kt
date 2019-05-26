package com.lazyassdev.remote.module

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.lazyassdev.remote.deserializer.DateDeserializer
import com.lazyassevs.remote.BuildConfig
import dagger.Module
import dagger.Provides
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class NetworkModule {

    @Singleton
    @Provides
    fun httpLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor(HttpLoggingInterceptor.Logger { message ->
            Timber.d(message)
        }).apply {
            level = when {
                BuildConfig.DEBUG -> HttpLoggingInterceptor.Level.BODY
                else -> HttpLoggingInterceptor.Level.NONE
            }
        }

    /**
     * todo: add cache and other interceptors
     */
    @Singleton
    @Provides
    fun okHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(httpLoggingInterceptor)
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .build()

    @Singleton
    @Provides
    fun gson(): Gson = GsonBuilder()
        .setDateFormat("yyyy-MM-dd HH:mm:ss")
        .registerTypeAdapter(Date::class.java, DateDeserializer())
        .create()

    @Singleton
    @Provides
    fun converterFactory(gson: Gson): Converter.Factory = GsonConverterFactory.create(gson)

    /**
     * todo: createWithScheduler passed as parameter, injected so that can be changed on test
     */
    @Singleton
    @Provides
    fun callAdapterFactory(): CallAdapter.Factory = RxJava2CallAdapterFactory
        .createWithScheduler(Schedulers.io())

    /**
     * todo add base url to build config
     */
    @Singleton
    @Provides
    fun retrofit(
        okHttpClient: OkHttpClient,
        converterFactory: Converter.Factory,
        callAdapterFactory: CallAdapter.Factory
    ): Retrofit = Retrofit.Builder()
        .baseUrl("https://jsonplaceholder.typicode.com/")
        .client(okHttpClient)
        .addConverterFactory(converterFactory)
        .addCallAdapterFactory(callAdapterFactory)
        .build()

}
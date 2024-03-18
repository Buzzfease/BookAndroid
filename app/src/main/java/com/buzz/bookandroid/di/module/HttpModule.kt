package com.buzz.bookandroid.di.module

import com.buzz.bookandroid.network.repository.BookAndroidApi
import com.buzz.bookandroid.network.repository.BookAndroidRepository
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class HttpModule {

    companion object {
        const val DEFAULT_TIMEOUT = 10
        const val SERVER_ENDPOINT = "http://8.138.132.255:10010"
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        val logInterceptor:HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        val builder:OkHttpClient.Builder = OkHttpClient.Builder()
        return builder.readTimeout(DEFAULT_TIMEOUT.toLong(), TimeUnit.SECONDS)
            .connectTimeout(DEFAULT_TIMEOUT.toLong(), TimeUnit.SECONDS)
            .addInterceptor(logInterceptor)
            .retryOnConnectionFailure(true).build()
    }

    @Singleton
    @Provides
    fun provideRemoteService(okHttpClient: OkHttpClient): BookAndroidApi {
        val retrofit: Retrofit = Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(SERVER_ENDPOINT)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(BookAndroidApi::class.java)
    }

    @Provides
    @Singleton
    fun providesRepository(
        retrofitService: BookAndroidApi,
    ): BookAndroidRepository = BookAndroidRepository(retrofitService)

}

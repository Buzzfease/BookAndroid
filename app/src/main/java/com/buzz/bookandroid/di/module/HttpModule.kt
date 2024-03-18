package com.buzz.bookandroid.di.module

import android.annotation.SuppressLint
import com.buzz.bookandroid.network.repository.BookAndroidApi
import com.buzz.bookandroid.network.repository.BookAndroidRepository
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.security.SecureRandom
import java.security.cert.X509Certificate
import java.util.concurrent.TimeUnit
import javax.inject.Singleton
import javax.net.ssl.SSLContext
import javax.net.ssl.SSLSocketFactory
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager

@Module
class HttpModule {

    companion object {
        const val DEFAULT_TIMEOUT = 10
        const val SERVER_ENDPOINT = "http://8.138.132.255:10010"
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        val trustAllCerts = buildTrustManagers()
        val sslContext: SSLContext = SSLContext.getInstance("SSL")
        sslContext.init(null, trustAllCerts, SecureRandom())
        val sslSocketFactory: SSLSocketFactory = sslContext.socketFactory

        val logInterceptor:HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        val builder:OkHttpClient.Builder = OkHttpClient.Builder()
        return builder.readTimeout(DEFAULT_TIMEOUT.toLong(), TimeUnit.SECONDS)
            .sslSocketFactory(sslSocketFactory, trustAllCerts[0] as X509TrustManager)
            .hostnameVerifier { _, _ -> true  }
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

    private fun buildTrustManagers(): Array<TrustManager> {
        return arrayOf(
            @SuppressLint("CustomX509TrustManager")
            object : X509TrustManager {
                @SuppressLint("TrustAllX509TrustManager")
                override fun checkClientTrusted(chain: Array<X509Certificate>, authType: String) {
                }

                @SuppressLint("TrustAllX509TrustManager")
                override fun checkServerTrusted(chain: Array<X509Certificate>, authType: String) {
                }

                override fun getAcceptedIssuers(): Array<X509Certificate> {
                    return arrayOf()
                }
            }
        )
    }
}

package com.wally.disneyinterview.di

import com.wally.disneyinterview.BuildConfig
import com.wally.disneyinterview.data.MarvelComicsApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.math.BigInteger
import java.security.MessageDigest
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient().newBuilder()
            .addInterceptor(authInterceptor)
            .readTimeout(READ_TIMEOUT_MS, TimeUnit.MILLISECONDS)
            .writeTimeout(WRITE_TIMEOUT_MS, TimeUnit.MILLISECONDS)
            .connectTimeout(CONNECT_TIMEOUT_MS, TimeUnit.MILLISECONDS)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideMarvelComicsApi(retrofit: Retrofit): MarvelComicsApi {
        return retrofit.create(MarvelComicsApi::class.java)
    }

    companion object {
        private const val BASE_URL = "https://gateway.marvel.com/"

        private const val READ_TIMEOUT_MS = 10000L
        private const val WRITE_TIMEOUT_MS = 10000L
        private const val CONNECT_TIMEOUT_MS = 2500L

        private val authInterceptor = { chain: Interceptor.Chain ->
            val timeMs = System.currentTimeMillis()
            val key = "$timeMs${BuildConfig.MARVEL_PRIVATE_API_KEY}${BuildConfig.MARVEL_PUBLIC_API_KEY}"
            val md = MessageDigest.getInstance("MD5")
            val hash = BigInteger(1, md.digest(key.toByteArray())).toString(16).padStart(32, '0')
            val url = chain.request().url
                .newBuilder()
                .addQueryParameter("ts", timeMs.toString())
                .addQueryParameter("apikey", BuildConfig.MARVEL_PUBLIC_API_KEY)
                .addQueryParameter("hash", hash)
                .build()
            val request = chain.request().newBuilder()
                .url(url)
                .build()

            chain.proceed(request)
        }
    }
}
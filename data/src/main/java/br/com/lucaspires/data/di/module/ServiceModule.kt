package br.com.lucaspires.data.di.module

import android.content.Context
import android.net.ConnectivityManager
import br.com.lucaspires.data.CheckConnectionInterceptor
import br.com.lucaspires.data.source.remote.MarvelAPI
import br.com.lucaspires.data.toMD5
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
class ServiceModule {

    companion object {
        private const val API_KEY_PUBLIC = "5b8380c7b5f4ebf0fab9c1b8278d8556"
        private const val API_KEY_PRIVATE = "3d29f1415a4378f5c3a4f4f3471268801f2b31ab"
        private const val BASE_URL = "http://gateway.marvel.com/v1/public/"
    }

    @Provides
    @Singleton
    fun providesRetrofit(okHttpClient: OkHttpClient): MarvelAPI {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(MarvelAPI::class.java)
    }

    @Provides
    @Singleton
    fun providesIntercepter(context: Context): OkHttpClient {
        return OkHttpClient.Builder()
            .apply {
                addInterceptor(CheckConnectionInterceptor(context))
                addInterceptor(HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                })
                addInterceptor { chain ->
                    val originalChain = chain.request()
                    val url = originalChain.url.newBuilder()
                        .addQueryParameter("ts", "1")
                        .addQueryParameter("apikey", API_KEY_PUBLIC)
                        .addQueryParameter("hash", ("1$API_KEY_PRIVATE$API_KEY_PUBLIC").toMD5())
                        .build()
                    chain.proceed(originalChain.newBuilder().url(url).build())
                }
            }.build()
    }
}
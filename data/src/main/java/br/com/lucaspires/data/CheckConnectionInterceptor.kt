package br.com.lucaspires.data

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class CheckConnectionInterceptor(private val context: Context) : Interceptor {
    @SuppressLint("MissingPermission")
    override fun intercept(chain: Interceptor.Chain): Response {
        val connectionManger =
            (context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager)
        val networkinfo =
            connectionManger.activeNetworkInfo ?: throw NoNetworkExpcetion("Sem conexão")
        networkinfo.let {
            if (!it.isConnected) {
                NoNetworkExpcetion("Sem conexão")
            }
        }
        return chain.proceed(chain.request())
    }

    class NoNetworkExpcetion(message: String) : IOException(message)
}
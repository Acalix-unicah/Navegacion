package com.example.navegacion.data.client

import com.example.navegacion.data.api.NetworkConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ApiClient {

    private fun createOkHttpClient(): OkHttpClient {
        val logging = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        return OkHttpClient.Builder()
            .connectTimeout(NetworkConfig.TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(NetworkConfig.TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(NetworkConfig.TIMEOUT, TimeUnit.SECONDS)
            .addInterceptor(logging)
            .build()
    }

    private fun createRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(NetworkConfig.BASE_URL)
            .client(createOkHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val api: ApiService = createRetrofit().create(ApiService::class.java)
}

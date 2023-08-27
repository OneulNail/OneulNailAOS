package com.today.nail.service.data

import android.util.Log
import com.today.nail.service.data.onBoard.service.OnBoardService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object ServiceConnector {

    fun makeOnBoardService() : OnBoardService {
        val interceptor = httpLoggingInterceptor()

        val client = interceptor?.let {
            OkHttpClient.Builder()
                .addInterceptor(it)
                .build()
        }

        val retrofit = client?.let {
            Retrofit.Builder()
                .baseUrl("http://43.201.115.69:8080/")
                .client(it)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        val onBoardService = retrofit?.create(OnBoardService::class.java)

        return onBoardService ?: throw IllegalAccessException("retrofit cannot be null")
    }

    private fun httpLoggingInterceptor(): HttpLoggingInterceptor? {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return interceptor
    }

}
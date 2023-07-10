package com.today.nail.service.data

import com.today.nail.service.data.onBoard.service.OnBoardService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object ServiceConnector {

    val retrofit = Retrofit.Builder()
        .baseUrl("https://api.github.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val onBoardService: OnBoardService = retrofit.create(OnBoardService::class.java)
}
package com.example.dinergate.network

import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET

private const val BASE_URL =
    "https://webservice.recruit.co.jp/hotpepper/gourmet/v1/?key=baf926709d928da3&lat=34.67&lng=135.52&range=5&order=4&format=json"


private val retrofit = Retrofit.Builder()
    .addConverterFactory(ScalarsConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

interface HotPepperApiService {
    @GET
    suspend fun getData(): String
}

object HotPepperApi {
    val retrofitService : HotPepperApiService by lazy {
        retrofit.create(HotPepperApiService::class.java)
    }
}
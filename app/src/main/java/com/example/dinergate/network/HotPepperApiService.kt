package com.example.dinergate.network

import com.example.dinergate.model.HotPepperApiResult
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import retrofit2.Retrofit

import retrofit2.http.GET

private const val BASE_URL =
    "https://webservice.recruit.co.jp/hotpepper/gourmet/v1/"

private val retrofit = Retrofit.Builder()
    .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
    .baseUrl(BASE_URL)
    .build()

interface HotPepperApiService {
    @GET("?key=baf926709d928da3&lat=34.84&lng=135.79&range=5&order=4&format=json")
    suspend fun getData(): HotPepperApiResult
}

object HotPepperApi {
    val retrofitService : HotPepperApiService by lazy {
        retrofit.create(HotPepperApiService::class.java)
    }
}
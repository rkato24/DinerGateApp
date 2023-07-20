package com.example.dinergate.network

import com.example.dinergate.model.HotPepperApiResult
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

import retrofit2.http.GET
import retrofit2.http.Query

private const val BASE_URL =
    "https://webservice.recruit.co.jp/hotpepper/"

private const val API_KEY =
    ""

private val retrofit = Retrofit.Builder()
    .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
    .baseUrl(BASE_URL)
    .build()

interface HotPepperApiService {

    @GET("gourmet/v1/")
    suspend fun getData(
        @Query("key") key: String = API_KEY,
        @Query("lat") lat: Double,
        @Query("lng") lng: Double,
        @Query("range") range: Int = 3,
        @Query("order") order: Int = 4,
        @Query("format") format: String = "json"
    ): HotPepperApiResult
}

object HotPepperApi {
    val retrofitService : HotPepperApiService by lazy {
        retrofit.create(HotPepperApiService::class.java)
    }
}
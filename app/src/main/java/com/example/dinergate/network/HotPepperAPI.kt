package com.example.dinergate.network

import android.R
import android.app.Activity
import android.app.ProgressDialog
import android.os.AsyncTask
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.serialization.json.Json
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.http.GET
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL
import java.util.concurrent.ExecutionException
import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeoutException
import kotlinx.serialization.Serializable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.converter.gson.GsonConverterFactory
import androidx.appcompat.app.AppCompatActivity
import com.example.dinergate.model.HotPepperApiResult


const val URL = "https://webservice.recruit.co.jp/hotpepper/gourmet/v1/?key=baf926709d928da3&lat=34.67&lng=135.52&range=5&order=4&format=json"


private val itemInterface by lazy { createService() }

interface ItemInterface {
    @GET(URL)
    fun items(): retrofit2.Call<HotPepperApiResult>
}

fun createService(): ItemInterface {
    val baseApiUrl = URL

    val httpLogging = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    val httpClientBuilder = OkHttpClient.Builder().addInterceptor(httpLogging)

    val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(baseApiUrl)
        .client(httpClientBuilder.build())
        .build()

    return retrofit.create(ItemInterface::class.java)
}

fun getRanking(){
    itemInterface.items().enqueue(object : retrofit2.Callback<HotPepperApiResult> {
        override fun onFailure(call: retrofit2.Call<HotPepperApiResult>?, t: Throwable?) {
        }

        override fun onResponse(call: retrofit2.Call<HotPepperApiResult>?, response: retrofit2.Response<HotPepperApiResult>) {
            if (response.isSuccessful) {
                response.body()?.let {

                    var items = mutableListOf<String>()
                    var res = response.body()
                    val text = res.toString()

                    println(text)


                }
            }
        }
    })
}



@Preview
@Composable
fun ApiPreview() {
    Column(){
        Text(getRanking().toString())
    }
}


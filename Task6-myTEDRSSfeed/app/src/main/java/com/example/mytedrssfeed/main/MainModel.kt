package com.example.mytedrssfeed.main

import android.util.Log
import com.example.mytedrssfeed.main.data.DataTEDXML
import com.example.mytedrssfeed.main.data.Item
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

interface TEDApi {
    @GET("/themes/rss/id)")
    suspend fun getListTED(): List<DataTEDXML>
}

object TEDApiImpl {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://www.ted.com")
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    private val TEDService = retrofit.create(TEDApi::class.java)

    suspend fun getListTED(): List<Item> {
        return withContext(Dispatchers.IO) {
            TEDService.getListTED().map {
                Log.d("DataTED", it.toString())
                Log.d("rss", it.rss.toString())
                Log.d("rss", it.rss.channel?.image?.link)
                it.rss.channel?.item
            } as List<Item>
        }
    }
}
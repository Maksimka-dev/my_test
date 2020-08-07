package com.example.mytedrssfeed.main

import android.os.Build
import android.os.Environment
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.example.mytedrssfeed.main.data.Item
import com.example.mytedrssfeed.main.data.Rss
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.simplexml.SimpleXmlConverterFactory
import retrofit2.http.GET
import java.io.File


interface TEDApi {
    @GET("/themes/rss/id")
    suspend fun getListTED(): Rss
}

object TEDApiImpl {

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://www.ted.com")
        //.addConverterFactory(MoshiConverterFactory.create())
        .addConverterFactory(SimpleXmlConverterFactory.create())
        .build()


    private val TEDService = retrofit.create(TEDApi::class.java)

    @RequiresApi(Build.VERSION_CODES.O)
    suspend fun getListTED(): List<Item>? {
        return withContext(Dispatchers.IO) {
            TEDService.getListTED().channel?.itemList?.map {
                it
            }
        }
    }
}
package com.example.thecatapi

import com.example.thecatapi.data.ApiData
import com.example.thecatapi.data.Cat
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface CatApi {
    @Headers("x-api_key: 35775b64-180a-43dc-ac64-377738261b2c")
    @GET("/v1/images/search")
    suspend fun getListOfCats(
        @Query("limit") limit: Int,
        @Query("page") page: Int,
        @Query("order") order: String
    ): List<ApiData>
}


object CatApiImpl {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.thecatapi.com")
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    private val catService = retrofit.create(CatApi::class.java)

    suspend fun getListOfCats(limit: Int, page: Int, order: String): List<Cat> {
        return withContext(Dispatchers.IO) {
            catService.getListOfCats(limit, page, order).map { Cat(it.url, it.id, it.height, it.width) }
        }
    }
}
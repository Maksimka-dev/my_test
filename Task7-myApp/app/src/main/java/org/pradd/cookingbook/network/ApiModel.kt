package org.pradd.cookingbook.network

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.pradd.cookingbook.data.Category
import org.pradd.cookingbook.data.Recipe
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path


interface RecipesApi {
    @GET("/api/recipes")
    @Headers("Content-Type: application/json")
    suspend fun getListRecipes(): List<Recipe>

    @GET("/api/recipes_category/{categoryId}")
    @Headers("Content-Type: application/json")
    suspend fun getListRecipesCategory(@Path("categoryId") categoryId: Int): List<Recipe>

    @GET("/api/recipe/{id}")
    @Headers("Content-Type: application/json")
    suspend fun getRecipe(@Path("id") id: Int): List<Recipe>

    @GET("/api/categories")
    @Headers("Content-Type: application/json")
    suspend fun getCategories(): List<Category>
}

object RecipesApiImpl {

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://cookingbook.org")
        .addConverterFactory(MoshiConverterFactory.create().asLenient())
        .build()

    private val RecipesService = retrofit.create(RecipesApi::class.java)

    suspend fun getListRecipes(): List<Recipe> {
        return withContext(Dispatchers.IO) {
            RecipesService.getListRecipes().map { it }
        }
    }

    suspend fun getListRecipesCategory(categoryId: Int): List<Recipe> {
        return withContext(Dispatchers.IO) {
            RecipesService.getListRecipesCategory(categoryId).map { it }
        }
    }

    suspend fun getRecipe(id: Int): Recipe {
        return withContext(Dispatchers.IO) {
            RecipesService.getRecipe(id)[0]
        }
    }

    suspend fun getCategories(): List<Category> {
        return withContext(Dispatchers.IO) {
            RecipesService.getCategories().map { it }
        }
    }
}
package org.pradd.cookingbook

import android.app.Application
import androidx.room.Room
import org.pradd.cookingbook.database.RecipeDao
import org.pradd.cookingbook.database.RecipeDatabase


class App : Application() {

    var database: RecipeDatabase? = null
        private set

     val recipeDao by lazy<RecipeDao> {
         database?.recipeDao()!!
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        database = Room.databaseBuilder(this, RecipeDatabase::class.java, "booking-cook")
            .build()
    }

    companion object {
        var instance: App? = null
    }
}
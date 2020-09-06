package org.pradd.cookingbook.database

import androidx.room.Database
import androidx.room.RoomDatabase
import org.pradd.cookingbook.model.Recipe


@Database(entities = [Recipe::class], version = 1)
abstract class RecipeDatabase : RoomDatabase() {

    abstract fun recipeDao(): RecipeDao

}
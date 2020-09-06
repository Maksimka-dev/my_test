package org.pradd.cookingbook.database

import androidx.lifecycle.LiveData
import androidx.room.*
import org.pradd.cookingbook.model.Recipe


@Dao
interface RecipeDao {

    @Query("SELECT * FROM recipes")
    fun getRecipes(): LiveData<List<Recipe>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(recipe: Recipe)

    @Update
    fun update(recipe: Recipe)

    @Delete
    fun delete(recipe: Recipe)
}
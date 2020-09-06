package org.pradd.cookingbook.database

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import org.pradd.cookingbook.App
import org.pradd.cookingbook.model.Recipe


open class RecipeViewModel(application: Application) : AndroidViewModel(application) {

    val allRecipes: LiveData<List<Recipe>> = App.instance?.recipeDao?.getRecipes()!!

}
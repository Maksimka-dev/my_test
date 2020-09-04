package org.pradd.cookingbook.user

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.pradd.cookingbook.data.Recipe
import org.pradd.cookingbook.database.DBModel


class UserRecipesPresenter(view: UserRecipesActivity) {
    private val mModel = DBModel
    private val mView = view

    fun setRecipe(recipe: Recipe, userId: String) {
        CoroutineScope(Dispatchers.Main).launch {
            mModel.writeDB(recipe, userId)
        }
    }

    fun getRecipes() {
        CoroutineScope(Dispatchers.Main).launch {
            mView.getRecipesDB()
        }
    }

}
package org.pradd.cookingbook.recipe

import kotlinx.coroutines.*
import org.pradd.cookingbook.network.RecipesApiImpl

class DetailsRecipePresenter(view: DetailsRecipeActivity) {
    private val mModel = RecipesApiImpl
    private val mView = view

    fun getItems(id: Int) {
        CoroutineScope(Dispatchers.Main).launch {
            val recipe = mModel.getRecipe(id)
            mView.setRecipe(recipe)
            mView.setIngredients(recipe.ingredients)
        }
    }
}
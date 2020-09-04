package org.pradd.cookingbook.recipes

import kotlinx.coroutines.*
import org.pradd.cookingbook.network.RecipesApiImpl

class RecipesPresenter(view: RecipesActivity) {
    private val mModel = RecipesApiImpl
    private val mView = view

    fun getItems(categoryId: Int? = null) {
        CoroutineScope(Dispatchers.Main).launch {
            val data = categoryId?.let {
                when (categoryId) {
                    0 -> mModel.getListRecipes()
                    else -> mModel.getListRecipesCategory(it)
                }
            }
            data?.let{
                mView.setItems(it)
            }
        }
    }
}
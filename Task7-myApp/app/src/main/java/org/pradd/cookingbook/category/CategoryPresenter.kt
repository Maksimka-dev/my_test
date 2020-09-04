package org.pradd.cookingbook.category

import kotlinx.coroutines.*
import org.pradd.cookingbook.network.RecipesApiImpl

class CategoryPresenter(view: CategoryActivity) {
    private val mModel = RecipesApiImpl
    private val mView = view

    fun getItems() {
        CoroutineScope(Dispatchers.Main).launch{
            val category = mModel.getCategories()
            mView.setItems(category)
        }
    }
}
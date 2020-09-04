package org.pradd.cookingbook.database

import androidx.lifecycle.ViewModel
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.pradd.cookingbook.data.Recipe


object DBModel : ViewModel() {

    private val database = FirebaseDatabase.getInstance()

    suspend fun writeDB(recipe: Recipe, userId: String){
        val myRef = database.getReference("recipes").child(userId)
        withContext(Dispatchers.IO) {
            myRef.setValue(recipe)
        }
    }
}
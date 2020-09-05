package org.pradd.cookingbook.database

import androidx.lifecycle.ViewModel
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.pradd.cookingbook.data.Recipe
import java.util.*


object DBModel : ViewModel() {

    private val database = FirebaseDatabase.getInstance()

    suspend fun writeDB(recipe: Recipe, userId: String){
        val calendar: Calendar = GregorianCalendar()
        val time = calendar.time
        val myRef = database.getReference("recipes").child(userId).child(time.toString())
        withContext(Dispatchers.IO) {
            myRef.setValue(recipe)
        }
    }
}
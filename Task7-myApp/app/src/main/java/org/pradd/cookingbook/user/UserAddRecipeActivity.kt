package org.pradd.cookingbook.user

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.gson.Gson
import org.pradd.cookingbook.data.Ingredients
import org.pradd.cookingbook.data.Recipe
import org.pradd.cookingbook.databinding.ActivityUserAddRecipeBinding


class UserAddRecipeActivity: AppCompatActivity() {

    private lateinit var binding: ActivityUserAddRecipeBinding
    private val user = FirebaseAuth.getInstance().currentUser!!
    private val listIngredient = mutableListOf<Ingredients>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserAddRecipeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnCancel.setOnClickListener {
            val myIntent = Intent(this, UserRecipesActivity::class.java)
            startActivityForResult(myIntent, 0)
        }

        binding.btnSave.setOnClickListener {
            val recipe = Recipe(
                user.uid,
                binding.etAddRecipeTitle.text.toString(),
                "null",
                binding.etAddRecipeDescription.text.toString(),
                binding.etAddRecipeBody.text.toString(),
                listIngredient
            )
            val gson = Gson()
            val recipeJson = gson.toJson(recipe)
            val intent = Intent(this, UserRecipesActivity::class.java)
            intent.putExtra("recipe", recipeJson)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivityForResult(intent, 0)
        }

        
    }

}
package org.pradd.cookingbook.user

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.firebase.ui.auth.AuthUI
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_recipes.*
import org.pradd.cookingbook.R
import org.pradd.cookingbook.model.Recipe
import org.pradd.cookingbook.database.LoginActivity
import org.pradd.cookingbook.database.RecipeViewModel


class UserRecipesActivity: AppCompatActivity(), OnUserRecipeClickListener {

    private lateinit var auth: FirebaseAuth
    private val itemAdapter = UserRecipesAdapter(this)
    private lateinit var recipeViewModel: RecipeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_recipes)

        auth = Firebase.auth

        recycler_view.apply {
            adapter = itemAdapter
            layoutManager = LinearLayoutManager(this@UserRecipesActivity)
        }

        recipeViewModel = ViewModelProvider(this).get(RecipeViewModel::class.java)

        recipeViewModel.allRecipes.observe(this, Observer { recipe ->
            recipe?.let { setItems(it) }
        })

        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener {
            val intent = Intent(this@UserRecipesActivity, UserAddRecipeActivity::class.java)
            startActivityForResult(intent, RECIPE_REQUEST_CODE)
        }
    }

    override fun onItemClick(item: Recipe, position: Int) {
        goActivityRecipe(item)
    }

    private fun setItems(items: List<Recipe>) {
        itemAdapter.addItems(items)
    }

    public override fun onStart() {
        super.onStart()
        val currentUser = auth.currentUser
        if (currentUser == null) {
            login()
        }
    }

    private fun login() {
        val intent = Intent(this, LoginActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
    }

    private fun signOut() {
        AuthUI.getInstance()
            .signOut(this)
            .addOnCompleteListener {
                login()
            }
    }

    private fun goActivityRecipe(recipe: Recipe) {
        val gson = Gson()
        val intent = Intent(this, UserDetailsRecipeActivity::class.java)
        val recipeJson = gson.toJson(recipe)
        intent.putExtra("recipe", recipeJson)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
    }


    companion object {
        private val RECIPE_REQUEST_CODE = 107
    }
}
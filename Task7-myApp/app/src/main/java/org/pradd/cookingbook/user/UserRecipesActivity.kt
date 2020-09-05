package org.pradd.cookingbook.user

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.firebase.ui.auth.AuthUI
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.ktx.Firebase
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_recipes.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.pradd.cookingbook.R
import org.pradd.cookingbook.data.Ingredients
import org.pradd.cookingbook.data.Recipe
import org.pradd.cookingbook.database.LoginActivity


class UserRecipesActivity: AppCompatActivity(), OnUserRecipeClickListener {

    private lateinit var auth: FirebaseAuth
    private val user by lazy {
        FirebaseAuth.getInstance().currentUser!!
    }

    private val database = FirebaseDatabase.getInstance()

    private val mPresenter = UserRecipesPresenter(this)
    private val itemAdapter = UserRecipesAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_recipes)

        auth = Firebase.auth
        mPresenter.getRecipes()

        val arguments = intent.extras
        val recipeJson = arguments?.getString("recipe")
        if (recipeJson != null) {
            val gson = Gson()
            val recipe: Recipe = gson.fromJson(recipeJson, Recipe::class.java)
            mPresenter.setRecipe(recipe, user.uid)
        }

        recycler_view.apply {
            adapter = itemAdapter
            layoutManager = LinearLayoutManager(this@UserRecipesActivity)
        }

        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener {
            goToAddActivity()
        }
    }

    override fun onItemClick(item: Recipe, position: Int) {
        goActivityRecipe(item)
    }

    fun setItems(items: List<Recipe>) {
        itemAdapter.addItems(items)
    }

    suspend fun getRecipesDB(){
        withContext(Dispatchers.IO) {
            val myRef = database.getReference("recipes").child(user.uid)
            myRef.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    val recipe = dataSnapshot.children.map {
                        Recipe(
                            it.child("id").value.toString(),
                            it.child("name").value as String,
                            it.child("image").value as String,
                            it.child("description").value as String,
                            it.child("text").value as String,
                            it.child("ingredients").value as List<Ingredients>?
                        )
                    }
                    setItems(recipe)
                }

                override fun onCancelled(error: DatabaseError) {
                    Log.w("getRecipesDB", "Failed to read value.", error.toException())
                }
            })
        }
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

    private fun goToAddActivity(){
        val intent = Intent(this, UserAddRecipeActivity::class.java)
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
}
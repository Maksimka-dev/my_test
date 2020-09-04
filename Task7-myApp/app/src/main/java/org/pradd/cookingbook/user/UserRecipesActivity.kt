package org.pradd.cookingbook.user

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_recipes.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.pradd.cookingbook.R
import org.pradd.cookingbook.data.Ingredients
import org.pradd.cookingbook.data.Recipe
import org.pradd.cookingbook.database.DBModel
import org.pradd.cookingbook.database.LoginActivity

class UserRecipesActivity: AppCompatActivity(), OnUserRecipeClickListener {

    private lateinit var auth: FirebaseAuth
    private lateinit var user: FirebaseUser

    private val database = FirebaseDatabase.getInstance()

    private val mPresenter = UserRecipesPresenter(this)
    private val itemAdapter = UserRecipesAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipes)

        auth = Firebase.auth
        mPresenter.getRecipes()

        recycler_view.apply {
            adapter = itemAdapter
            layoutManager = LinearLayoutManager(this@UserRecipesActivity)
        }

        //mPresenter.setRecipe(recipe, user.uid)
    }

    override fun onItemClick(item: Recipe, position: Int) {
        Log.d("onItemClick user", item.id.toString())
    }

    fun setItems(items: List<Recipe>) {
        itemAdapter.addItems(items)
    }

    suspend fun getRecipesDB(){
        withContext(Dispatchers.IO) {
            val myRef = database.getReference("recipes")
            myRef.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    dataSnapshot.children.forEach {
                        val recipe = Recipe(
                            Integer.parseInt(it.child("id").value.toString()),
                            it.child("name").value as String,
                            it.child("image").value as String,
                            it.child("description").value as String,
                            it.child("text").value as String,
                            it.child("ingredients").value as List<Ingredients>?
                        )
                        setItems(listOf(recipe))
                    }
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
        }else{
            user = FirebaseAuth.getInstance().currentUser!!
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
}
package org.pradd.cookingbook.category

import android.annotation.SuppressLint
import android.content.Intent
import android.content.res.ColorStateList
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomnavigation.BottomNavigationItemView
import kotlinx.android.synthetic.main.activity_categories.*
import kotlinx.android.synthetic.main.activity_categories.bottom_navigation
import org.pradd.cookingbook.MainActivity
import org.pradd.cookingbook.R
import org.pradd.cookingbook.model.Category
import org.pradd.cookingbook.recipes.RecipesActivity

class CategoryActivity: AppCompatActivity(), OnCategoryClickListener {

    private val itemAdapter = CategoryAdapter(this)
    private val mPresenter = CategoryPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_categories)

        val btnHome = findViewById<BottomNavigationItemView>(R.id.bottom_menu_home)
        val btnCategory = findViewById<BottomNavigationItemView>(R.id.bottom_menu_category)
        val btnMyRecipe = findViewById<BottomNavigationItemView>(R.id.bottom_menu_my_recipe)
        myColorStateList(listOf(btnHome, btnCategory, btnMyRecipe))

        bottom_navigation.selectedItemId = R.id.bottom_menu_category

        val name = findViewById<TextView>(R.id.category_title)
        name.text = getString(R.string.categories)

        recycler_view_categories.apply {
            adapter = itemAdapter
            layoutManager = LinearLayoutManager(this@CategoryActivity)
        }

        mPresenter.getItems()

        bottom_navigation.setOnNavigationItemSelectedListener { item ->
            when(item.itemId) {
                R.id.bottom_menu_home -> {
                    goMainActivity()
                    true
                }
                R.id.bottom_menu_category -> {
                    true
                }
                R.id.bottom_menu_my_recipe -> {
                    Log.d("BottomNavigationView", "bottom_menu_my_recipe")
                    true
                }
                else -> false
            }
        }
    }

    override fun onItemClick(item: Category, position: Int) {
        val intent = Intent(this, RecipesActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        intent.putExtra("name", item.name)
        intent.putExtra("categoryId", item.id)
        startActivity(intent)
    }

    fun setItems(items: List<Category>) {
        itemAdapter.addItems(items)
    }

    private fun goMainActivity(){
        val intent = Intent(this, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
    }

    @SuppressLint("RestrictedApi")
    private fun myColorStateList(navigationItems: List<BottomNavigationItemView>) {
        val color = ColorStateList(
            arrayOf(
                intArrayOf( android.R.attr.state_checked ),
                intArrayOf( android.R.attr.state_enabled )
            ),
            intArrayOf(
                getColor(R.color.colorAccent),
                getColor(R.color.colorBottomMenuEnabled)
            )
        )
        navigationItems.forEach {
            it.setIconTintList(color)
            it.setTextColor(color)
        }
    }
}
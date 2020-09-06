package org.pradd.cookingbook.model

import androidx.room.*

@Entity(tableName = "recipes")
class Recipe {
    @PrimaryKey
    var id: Long = 0
    @ColumnInfo(name = "name")
    lateinit var name: String
    @ColumnInfo(name = "image")
    lateinit var image: String
    @ColumnInfo(name = "description")
    lateinit var description: String
    @ColumnInfo(name = "text")
    lateinit var text: String
    @Ignore
    lateinit var ingredients: List<Ingredients>
}


@Entity(foreignKeys = [
    ForeignKey(
        entity = Recipe::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("recipe_id")
    )
])
data class Ingredients(
    @PrimaryKey
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "recipe_id") val recipe_id: Long)

data class Category(val id: Int, val name: String)
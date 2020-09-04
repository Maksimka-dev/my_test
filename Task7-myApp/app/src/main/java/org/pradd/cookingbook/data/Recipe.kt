package org.pradd.cookingbook.data

data class Recipe(
    var id: Int,
    var name: String,
    var image: String,
    var description: String,
    var text: String,
    var ingredients: List<Ingredients>?
)

data class Ingredients(val name: String)

data class Category(val id: Int, val name: String)
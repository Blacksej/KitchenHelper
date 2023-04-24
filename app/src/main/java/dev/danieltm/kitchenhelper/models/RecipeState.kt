package dev.danieltm.kitchenhelper.models

data class RecipeState(
    val recipes: List<Recipe> = emptyList(),
    val recipeName: String = "",
    val recipeLink: String = ""
)
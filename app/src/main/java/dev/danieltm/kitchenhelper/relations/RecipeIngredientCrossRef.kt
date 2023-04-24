package dev.danieltm.kitchenhelper.relations

import androidx.room.Entity

@Entity(
    primaryKeys = ["recipeId", "ingredientId"]
)
data class RecipeIngredientCrossRef(
    val recipeId: Int,
    val ingredientId: Int,

)
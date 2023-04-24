package dev.danieltm.kitchenhelper.relations

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import dev.danieltm.kitchenhelper.models.Ingredient
import dev.danieltm.kitchenhelper.models.Recipe

data class RecipeWithIngredients(
    @Embedded val recipe: Recipe,
    @Relation(
        parentColumn = "recipeId",
        entityColumn = "ingredientId",
        associateBy = Junction(RecipeIngredientCrossRef::class)
    )
    val ingredients: List<Ingredient>
)

package dev.danieltm.kitchenhelper.relations

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import dev.danieltm.kitchenhelper.models.Ingredient
import dev.danieltm.kitchenhelper.models.Recipe

data class IngredientWithRecipes(
    @Embedded val ingredient: Ingredient,
    @Relation(
        parentColumn = "ingredientId",
        entityColumn = "recipeId",
        associateBy = Junction(RecipeIngredientCrossRef::class)
    )
    val recipes: List<Recipe>
)
package dev.danieltm.kitchenhelper.utilities

import dev.danieltm.kitchenhelper.models.Recipe

sealed interface RecipeEvent{

    object SaveRecipe: RecipeEvent

    data class SetRecipeName(val recipeName: String): RecipeEvent

    data class SetRecipeLink(val recipeLink: String): RecipeEvent

    data class DeleteRecipe(val recipe: Recipe): RecipeEvent

    object ShowDialog: RecipeEvent
    object HideDialog: RecipeEvent
}
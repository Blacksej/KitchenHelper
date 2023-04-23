package dev.danieltm.kitchenhelper.utilities

import dev.danieltm.kitchenhelper.models.Ingredient

sealed interface IngredientEvent {

    object SaveIngredient: IngredientEvent

    data class SetIngredientName(val ingredientName: String): IngredientEvent
    data class SetIngredientCategory(val ingredientCategory: String): IngredientEvent

    data class DeleteIngredient(val ingredient: Ingredient): IngredientEvent

    object ShowDialog: IngredientEvent
    object HideDialog: IngredientEvent

    data class SortIngredients(val sortType: SortType): IngredientEvent


}
package dev.danieltm.kitchenhelper.models

import dev.danieltm.kitchenhelper.utilities.SortType

data class IngredientState(
    val ingredients: List<Ingredient> = emptyList(),
    val ingredientName: String = "",
    val category: String = "",
    val sortType: SortType = SortType.CATEGORY_NAME,
    val isAddingIngredient: Boolean = false
)
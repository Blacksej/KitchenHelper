package dev.danieltm.kitchenhelper.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.danieltm.kitchenhelper.daos.IngredientDao
import dev.danieltm.kitchenhelper.models.Ingredient
import dev.danieltm.kitchenhelper.models.IngredientState
import dev.danieltm.kitchenhelper.utilities.IngredientEvent
import dev.danieltm.kitchenhelper.utilities.SortType
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class IngredientViewModel(
    private val dao: IngredientDao
): ViewModel() {

    /*private val _sortType = MutableStateFlow(SortType.CATEGORY_NAME)

    // Whenever our _sortType changes it returns a new flow and get the contacts from that flow
    @OptIn(ExperimentalCoroutinesApi::class)
    private val _ingredients = _sortType
        .flatMapLatest { sortType ->
            when(sortType){
                SortType.INGREDIENT_NAME -> dao.getIngredientsOrderedByIngredientName()
                SortType.CATEGORY_NAME -> dao.getIngredientsOrderedByCategoryName()
            }
        }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())

    private val _state = MutableStateFlow(IngredientState())

    // The code in state underneath will be executed when there is a change in
    // either _state, _sortType or _contacts.
    val state = combine(_state, _sortType, _ingredients) { state, sortType, ingredients ->
        state.copy(
            ingredients = ingredients,
            sortType = sortType
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), IngredientState())

    fun onEvent(event: IngredientEvent){
        when(event){
            is IngredientEvent.DeleteIngredient -> {
                viewModelScope.launch {
                    dao.deleteIngredient(event.ingredient)
                }
            }
            IngredientEvent.HideDialog -> {
                _state.update { it.copy(
                    isAddingIngredient = false
                ) }
            }
            IngredientEvent.SaveIngredient -> {
                val ingredientName = state.value.ingredientName
                val category = state.value.category

                if(ingredientName.isBlank() || category.isBlank()){
                    return
                }

                val ingredient = Ingredient(
                    // The ID will automatically be set
                    ingredientName = ingredientName,
                    category = category
                )
                viewModelScope.launch {
                    dao.upsertIngredient(ingredient)
                }

                _state.update {it.copy(
                    isAddingIngredient = false,
                    ingredientName = "",
                    category = ""
                ) }
            }
            is IngredientEvent.SetIngredientCategory -> {
                _state.update { it.copy(
                    category = event.ingredientCategory
                ) }
            }
            is IngredientEvent.SetIngredientName -> {
                _state.update { it.copy(
                    ingredientName = event.ingredientName
                ) }
            }
            IngredientEvent.ShowDialog -> {
                _state.update { it.copy(
                    isAddingIngredient = true
                ) }
            }
            is IngredientEvent.SortIngredients -> {
                _sortType.value = event.sortType
            }
        }
    }
    */
}
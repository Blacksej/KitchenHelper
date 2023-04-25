package dev.danieltm.kitchenhelper.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.danieltm.kitchenhelper.daos.IngredientDao
import dev.danieltm.kitchenhelper.models.IngredientState
import dev.danieltm.kitchenhelper.models.Recipe
import dev.danieltm.kitchenhelper.models.RecipeState
import dev.danieltm.kitchenhelper.utilities.RecipeEvent
import dev.danieltm.kitchenhelper.utilities.SortType
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class RecipeViewModel(
    private val dao: IngredientDao
): ViewModel() {

    // Recipe UI state
    private val _recipeState = MutableStateFlow(RecipeState())

    // asStateFlow() makes this mutablestateflow a read-only stateflow.
    val recipeState: StateFlow<RecipeState> = _recipeState.asStateFlow()

    init {
        getAllRecipes()
    }

    fun getAllRecipes(){

        }
    }

    fun onEvent(event: RecipeEvent){
        when(event){
            is RecipeEvent.DeleteRecipe -> {
                viewModelScope.launch {
                    dao.deleteRecipe(event.recipe)
                }
            }
            RecipeEvent.HideDialog -> {
                _recipeState.update { it.copy(
                    isAddingRecipe = false
                ) }
            }
            RecipeEvent.SaveRecipe -> {
                val recipeName = _recipeState.value.recipeName
                val recipeLink = _recipeState.value.recipeLink

                if(recipeName.isBlank() || recipeLink.isBlank()){
                    return
                }

                val recipe = Recipe(
                    recipeName = recipeName,
                    recipeLink = recipeLink
                )

                viewModelScope.launch {
                    dao.upsertRecipe(recipe = recipe)
                }

                _recipeState.update { it.copy(
                    recipeName = "",
                    recipeLink = "",
                    isAddingRecipe = false
                ) }
            }
            is RecipeEvent.SetRecipeLink -> {
                _recipeState.update { it.copy(
                    recipeLink = event.recipeLink
                ) }
            }
            is RecipeEvent.SetRecipeName -> {
                _recipeState.update { it.copy(
                    recipeName = event.recipeName
                ) }
            }
            RecipeEvent.ShowDialog -> {
                _recipeState.update { it.copy(
                    isAddingRecipe = true
                ) }
            }
        }
    }
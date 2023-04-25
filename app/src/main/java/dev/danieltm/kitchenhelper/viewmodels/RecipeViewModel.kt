package dev.danieltm.kitchenhelper.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.danieltm.kitchenhelper.daos.IngredientDao
import dev.danieltm.kitchenhelper.models.IngredientState
import dev.danieltm.kitchenhelper.models.Recipe
import dev.danieltm.kitchenhelper.models.RecipeState
import dev.danieltm.kitchenhelper.utilities.RecipeEvent
import dev.danieltm.kitchenhelper.utilities.SortType
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

class RecipeViewModel(
    private val dao: IngredientDao
): ViewModel() {

    // Recipe UI state
    private val _recipes = MutableStateFlow<List<Recipe>>(emptyList())

    // asStateFlow() makes this mutablestateflow a read-only stateflow.
    val recipes: StateFlow<List<Recipe>> = _recipes.asStateFlow()

    private val _isLoading = MutableStateFlow<Boolean>(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    init {
        getAllRecipes()
    }

    fun getAllRecipes() {

        viewModelScope.launch{
            _isLoading.value = true
            _recipes.value = withContext(Dispatchers.IO){
                dao.getAllRecipes()
            }
            _isLoading.value = false
        }

        /*CoroutineScope(Dispatchers.IO).launch {
            _isLoading.value = true
            _recipes.value = dao.getAllRecipes()
            _isLoading.value = false
        }*/
    }

    fun deleteRecipe(recipe: Recipe){
        CoroutineScope(Dispatchers.IO).launch{
            dao.deleteRecipeWithCascade(recipeId = recipe.recipeId)
        }

        val currentList = _recipes.value.toMutableList()
        currentList.remove(recipe)
       _recipes.value = currentList
    }
}
package dev.danieltm.kitchenhelper.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.danieltm.kitchenhelper.daos.IngredientDao
import dev.danieltm.kitchenhelper.models.IngredientState
import dev.danieltm.kitchenhelper.models.Recipe
import dev.danieltm.kitchenhelper.models.RecipeState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class RecipeViewModel(
    private val dao: IngredientDao
): ViewModel() {

    private val _allRecipes = MutableStateFlow(emptyList<Recipe>())
    val allRecipes = _allRecipes.asStateFlow()

    init {

        getAllRecipes()
    }

    fun getAllRecipes(){
        CoroutineScope(Dispatchers.IO).launch {
            _allRecipes.value = dao.getAllRecipes()
        }
    }

    fun deleteRecipe(recipe: Recipe){
        CoroutineScope(Dispatchers.IO).launch {
            dao.deleteRecipe(recipe = recipe)
        }
    }
}
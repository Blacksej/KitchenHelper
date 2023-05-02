package dev.danieltm.kitchenhelper.views

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import dev.danieltm.kitchenhelper.models.Recipe
import dev.danieltm.kitchenhelper.models.RecipeState
import dev.danieltm.kitchenhelper.utilities.IngredientEvent
import dev.danieltm.kitchenhelper.viewmodels.RecipeViewModel

@Composable
fun RecipesScreen(
    recipeViewModel: RecipeViewModel
){
    val recipes by recipeViewModel.recipes.collectAsState()
    //val isLoading by recipeViewModel.isLoading.collectAsState()

        LazyColumn(
            //modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ){
            items(recipes) {recipe ->
                Row(
                    modifier = Modifier.fillMaxWidth()
                ){
                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .padding(8.dp)
                    ) {
                        Text(
                            text = "${recipe.recipeName}",
                            fontSize = 20.sp
                        )
                        Text(text = recipe.recipeLink, fontSize = 12.sp)
                    }
                    Button(onClick = { recipeViewModel.deleteRecipe(recipe) }) {
                        Text(text = "X")
                    }
                }
            }
        }
}
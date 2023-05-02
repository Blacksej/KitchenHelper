package dev.danieltm.kitchenhelper.views

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import dev.danieltm.kitchenhelper.viewmodels.IngredientViewModel
import dev.danieltm.kitchenhelper.viewmodels.RecipeViewModel

@Composable
fun AddIngredientScreen(
    ingredientViewModel: IngredientViewModel
){
    
    val ingredientsState by ingredientViewModel.ingredients.collectAsState()
    val ingredientNameState by ingredientViewModel.ingredientName.collectAsState()
    val ingredientCategoryState by ingredientViewModel.ingredienCategory.collectAsState()
    
    Box(
        modifier = Modifier.fillMaxSize()
    ){
        Column(
            
        ) {
            OutlinedTextField(
                value = ingredientNameState,
                onValueChange = { ingredientViewModel.SetIngredientName(it) },
                label = { Text(text = "Ingredient Name") },
                placeholder = { Text(text = "Enter ingredient name") }
            )
            OutlinedTextField(
                value = ingredientCategoryState,
                onValueChange = { ingredientViewModel.SetIngredientCategory(it) },
                label = { Text(text = "Ingredient Category") },
                placeholder = { Text(text = "Enter category") }
            )
            Button(
                onClick = {
                    if(ingredientNameState != "" && ingredientCategoryState != ""){
                        ingredientViewModel.AddIngredient()
                    }
                }
            ) {
                Text(text = "Create Ingredient")
            }
        }
    }
}
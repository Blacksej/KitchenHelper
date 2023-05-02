package dev.danieltm.kitchenhelper.views

import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.danieltm.kitchenhelper.models.IngredientState
import dev.danieltm.kitchenhelper.utilities.IngredientEvent
import dev.danieltm.kitchenhelper.utilities.SortType
import dev.danieltm.kitchenhelper.viewmodels.IngredientViewModel

@Composable
fun IngredientScreen(
    ingredientViewModel: IngredientViewModel
){
    val ingredients by ingredientViewModel.ingredients.collectAsState()
    //val isLoading by recipeViewModel.isLoading.collectAsState()

    LazyColumn(
        //modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ){
        items(ingredients) {ingredient ->
            Row(
                modifier = Modifier.fillMaxWidth()
            ){
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .padding(8.dp)
                ) {
                    Text(
                        text = "${ingredient.ingredientName}",
                        fontSize = 20.sp
                    )
                    Text(text = ingredient.category, fontSize = 12.sp)
                }
                Button(onClick = { ingredientViewModel.deleteIngredient(ingredient = ingredient) }) {
                    Text(text = "X")
                }
            }
        }
    }
}
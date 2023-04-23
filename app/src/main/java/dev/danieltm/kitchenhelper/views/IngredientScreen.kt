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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.danieltm.kitchenhelper.models.IngredientState
import dev.danieltm.kitchenhelper.utilities.IngredientEvent
import dev.danieltm.kitchenhelper.utilities.SortType

@Composable
fun IngredientScreen(
    state: IngredientState,
    onEvent: (IngredientEvent) -> Unit
){
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {
                onEvent(IngredientEvent.ShowDialog)
            }) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add ingredient"
                )
            }
        },
        modifier = Modifier.padding(16.dp)
    ) { padding ->
        if(state.isAddingIngredient){
            AddIngredientDialog(state = state, onEvent = onEvent)
        }

        LazyColumn(
            contentPadding = padding,
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ){
            item{
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalScroll(rememberScrollState()),
                    verticalAlignment = Alignment.CenterVertically
                ){
                    SortType.values().forEach { sortType ->
                        Row(
                            modifier = Modifier
                                .clickable{
                                    onEvent(IngredientEvent.SortIngredients(sortType))
                                },
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            RadioButton(
                                selected = state.sortType == sortType,
                                onClick = {
                                    onEvent(IngredientEvent.SortIngredients(sortType))
                                }
                            )
                            Text(text = sortType.name)
                        }
                    }
                }
            }
            items(state.ingredients) {ingredient ->
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
                    IconButton(onClick = {
                        onEvent(IngredientEvent.DeleteIngredient(ingredient))
                    }) {
                        Icon(
                            imageVector = Icons.Default.Delete,
                            contentDescription = "Delete ingredient"
                        )
                    }
                }
            }
        }
    }
}
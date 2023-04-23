package dev.danieltm.kitchenhelper.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.danieltm.kitchenhelper.models.IngredientState
import dev.danieltm.kitchenhelper.utilities.IngredientEvent

@Composable
fun AddIngredientDialog(
    state: IngredientState,
    onEvent: (IngredientEvent) -> Unit,
    modifier: Modifier = Modifier
){
    AlertDialog(
        modifier = modifier,
        onDismissRequest = {
            onEvent(IngredientEvent.HideDialog)
        },
        title = { Text(text = "Add contact") },
        text = {
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                TextField(
                    value = state.ingredientName,
                    onValueChange = {
                        onEvent(IngredientEvent.SetIngredientName(it))
                    },
                    placeholder = {
                        Text(text = "Ingredient name")
                    }
                )
                TextField(
                    value = state.category,
                    onValueChange = {
                        onEvent(IngredientEvent.SetIngredientCategory(it))
                    },
                    placeholder = {
                        Text(text = "Category")
                    }
                )
            }
        },
        buttons = {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.CenterEnd
            ){
                Button(onClick = {
                    onEvent(IngredientEvent.SaveIngredient)
                }){
                    Text(text = "Save")
                }
            }
        }
    )
}
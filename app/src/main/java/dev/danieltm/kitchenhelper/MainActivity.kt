package dev.danieltm.kitchenhelper

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import dev.danieltm.kitchenhelper.daos.IngredientDao
import dev.danieltm.kitchenhelper.databases.IngredientDatabase
import dev.danieltm.kitchenhelper.models.Ingredient
import dev.danieltm.kitchenhelper.ui.theme.KitchenHelperTheme
import dev.danieltm.kitchenhelper.viewmodels.IngredientViewModel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    private val db by lazy {
        Room.databaseBuilder(
            applicationContext,
            IngredientDatabase::class.java,
            name = "ingredients.db"
        ).build()
    }

    private val viewModel by viewModels<IngredientViewModel>(
        factoryProducer = {
            object : ViewModelProvider.Factory{
                override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                    return IngredientViewModel(db.dao) as T
                }
            }
        }
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KitchenHelperTheme {

            }
        }
    }
}

fun addIngredient(ingredientDao: IngredientDao){
    ingredientDao.upsertIngredient(Ingredient(ingredientName = "Sugar", category = "Dry ingredients"))
}
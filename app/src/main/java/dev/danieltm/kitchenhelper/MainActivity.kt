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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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
import dev.danieltm.kitchenhelper.views.IngredientScreen
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    private val db by lazy {
        Room.databaseBuilder(
            applicationContext,
            IngredientDatabase::class.java,
            name = "ingredients.db"
        )
            .fallbackToDestructiveMigration()
            .build()
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
                val state by viewModel.state.collectAsState()
                IngredientScreen(state = state, onEvent = viewModel::onEvent)
            }
        }
    }
}
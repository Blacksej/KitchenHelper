package dev.danieltm.kitchenhelper

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dev.danieltm.kitchenhelper.databases.RecipeDatabase
import dev.danieltm.kitchenhelper.models.Ingredient
import dev.danieltm.kitchenhelper.models.Recipe
import dev.danieltm.kitchenhelper.relations.RecipeIngredientCrossRef
import dev.danieltm.kitchenhelper.ui.theme.KitchenHelperTheme
import dev.danieltm.kitchenhelper.viewmodels.IngredientViewModel
import dev.danieltm.kitchenhelper.viewmodels.MainViewModel
import dev.danieltm.kitchenhelper.viewmodels.RecipeViewModel
import dev.danieltm.kitchenhelper.views.AddIngredientScreen
import dev.danieltm.kitchenhelper.views.BottomNavigationBar
import dev.danieltm.kitchenhelper.views.IngredientScreen
import dev.danieltm.kitchenhelper.views.RecipesScreen
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    /*private val db by lazy {
        Room.databaseBuilder(
            applicationContext,
            RecipeDatabase::class.java,
            name = "ingredients.db"
        )
            .fallbackToDestructiveMigration()
            .build()
    }*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val dao = RecipeDatabase.getInstance(this).dao

        val ingredientViewModel by viewModels<IngredientViewModel>(
            factoryProducer = {
                object : ViewModelProvider.Factory{
                    override fun <T : ViewModel> create(modelClass: Class<T>): T {
                        return IngredientViewModel(dao) as T
                    }
                }
            }
        )

        val recipeViewModel by viewModels<RecipeViewModel>(
            factoryProducer = {
                object : ViewModelProvider.Factory{
                    override fun <T : ViewModel> create(modelClass: Class<T>): T {
                        return RecipeViewModel(dao) as T
                    }
                }
            }
        )
        //val recipeViewModel: RecipeViewModel by viewModels()

        /*val ingredients = listOf(
            Ingredient(
                "Dairy",
                "Milk"
            ),
            Ingredient(
                "Dairy",
                "Egg"
            ),
            Ingredient(
                "Dry Ingredients",
                "Sugar"
            ),
            Ingredient(
                "Sweets",
                "Chocolate"
            ),
            Ingredient(
                "Dry Ingredients",
                "Flour"
            )
        )

        val recipes = listOf(
            Recipe(
                "Eggs benedict",
                "TESTLINK.com"
            ),
            Recipe(
                "Pancakes",
                "TESTLINK.com"
            ),
            Recipe(
                "Muffins",
                "TESTLINK.com"
            )
        )

        val recipeIngredientRelations = listOf(
            RecipeIngredientCrossRef(1, 0),
            RecipeIngredientCrossRef(1, 1),
            RecipeIngredientCrossRef(1, 2),

            RecipeIngredientCrossRef(2, 0),
            RecipeIngredientCrossRef(2, 1),
            RecipeIngredientCrossRef(2, 2),
            RecipeIngredientCrossRef(2, 4),

            RecipeIngredientCrossRef(3, 4),
            RecipeIngredientCrossRef(3, 0),
            RecipeIngredientCrossRef(3, 1)
        )

        lifecycleScope.launch{
            recipes.forEach { dao.upsertRecipe(it) }
            ingredients.forEach { dao.upsertIngredient(it) }
            recipeIngredientRelations.forEach { dao.upsertRecipeIngredientCrossRef(it) }
        }*/

        setContent {

            KitchenHelperTheme {


                val mainViewModel = viewModel<MainViewModel>()
                val navController = rememberNavController()

                ScaffoldBottomNavigationBarAndTopBar(
                    mainViewModel = mainViewModel,
                    recipeViewModel = recipeViewModel ,
                    ingredientViewModel = ingredientViewModel,
                    navController = navController,
                    navHostController = navController
                )
                //val state by ingredientViewModel.state.collectAsState()
                //IngredientScreen(state = state, onEvent = viewModel::onEvent)

                /*val isLoading by recipeViewModel.isLoading.collectAsState()

                if(isLoading){
                    CircularProgressIndicator()
                }
                else{
                    RecipesScreen(recipeViewModel = recipeViewModel)
                }*/
            }
        }
    }
}

@Composable
fun Navigation(
    navController: NavHostController,
    recipeViewModel: RecipeViewModel,
    ingredientViewModel: IngredientViewModel
){
    NavHost(navController = navController, startDestination = "home"){
        composable("home"){
            RecipesScreen(recipeViewModel)
        }
        composable("addIngredient"){
            AddIngredientScreen(ingredientViewModel)
        }
        composable("ingredients"){
            IngredientScreen(ingredientViewModel)
        }
    }
}

@Composable
fun ScaffoldBottomNavigationBarAndTopBar(
    mainViewModel: MainViewModel,
    recipeViewModel: RecipeViewModel,
    ingredientViewModel: IngredientViewModel,
    navController: NavController,
    navHostController: NavHostController
) {
    val scaffoldState = rememberScaffoldState()
    val scope =  rememberCoroutineScope()

    Scaffold(
        scaffoldState = scaffoldState,
        /*topBar = {
            TopBar(
                navController = navController,
                onNavigationClick = {
                    scope.launch{
                        scaffoldState.drawerState.open()
                    }
                }
            ) },*/
        bottomBar = {
            BottomNavigationBar(
                items = mainViewModel.getNavList(),
                navController = navController,
                modifier = Modifier
                    /*.clip(
                        RoundedCornerShape(
                            topStart = 15.dp,
                            topEnd = 15.dp
                        )
                    )*/
                    .height(60.dp),
                onItemClick = {
                    navController.navigate(it.route)
                }
            )
        }
    )
    { paddingValues ->
        Modifier.padding(paddingValues)
    }

        Navigation(
            navController = navHostController,
            recipeViewModel = recipeViewModel,
            ingredientViewModel = ingredientViewModel
        )
    }
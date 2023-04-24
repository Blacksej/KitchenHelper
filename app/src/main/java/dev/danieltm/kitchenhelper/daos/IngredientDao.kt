package dev.danieltm.kitchenhelper.daos

import androidx.room.*
import dev.danieltm.kitchenhelper.models.Ingredient
import dev.danieltm.kitchenhelper.models.Recipe
import dev.danieltm.kitchenhelper.relations.IngredientWithRecipes
import dev.danieltm.kitchenhelper.relations.RecipeIngredientCrossRef
import dev.danieltm.kitchenhelper.relations.RecipeWithIngredients
import kotlinx.coroutines.flow.Flow

@Dao
interface IngredientDao {

    @Upsert
    suspend fun upsertIngredient(ingredient: Ingredient)

    @Upsert
    suspend fun upsertRecipe(recipe: Recipe)

    @Upsert
    suspend fun upsertRecipeIngredientCrossRef(crossRef: RecipeIngredientCrossRef)

    @Delete
    suspend fun deleteIngredient(ingredient: Ingredient)

    @Delete
    suspend fun deleteRecipe(recipe: Recipe)

    // QUERIES
    @Query("SELECT * FROM ingredient WHERE category = :category")
    fun getIngredientsByCategory(category: String): List<Ingredient>

    @Query("SELECT * FROM ingredient")
    fun getAllIngredients(): List<Ingredient>

    @Query("SELECT * FROM ingredient ORDER BY ingredientName ASC")
    fun getIngredientsOrderedByIngredientName(): List<Ingredient>

    @Query("SELECT * FROM ingredient ORDER BY category ASC")
    fun getIngredientsOrderedByCategoryName(): List<Ingredient>


    @Query("SELECT * FROM recipe WHERE recipeId = (SELECT recipeId FROM recipeingredientcrossref WHERE ingredientId = :ingredientId)")
    fun getRecipesOfIngredient(ingredientId: Int): List<RecipeWithIngredients>

    @Query("SELECT * FROM ingredient WHERE ingredientId = (SELECT ingredientId FROM recipeingredientcrossref WHERE recipeId = :recipeId)")
    fun getIngredientsOfRecipe(recipeId: Int): List<IngredientWithRecipes>

    @Query("SELECT * FROM recipe")
    fun getAllRecipes(): List<Recipe>

}
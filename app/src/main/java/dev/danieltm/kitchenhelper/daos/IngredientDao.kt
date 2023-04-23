package dev.danieltm.kitchenhelper.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import dev.danieltm.kitchenhelper.models.Ingredient
import kotlinx.coroutines.flow.Flow

@Dao
interface IngredientDao {

    @Upsert
    fun upsertIngredient(ingredient: Ingredient)

    @Delete
    suspend fun deleteIngredient(ingredient: Ingredient)

    // QUERIES
    @Query("SELECT * FROM ingredient WHERE category == :category")
    fun getIngredientsByCategory(category: String): Flow<List<Ingredient>>

    @Query("SELECT * FROM ingredient")
    fun getAllIngredients(): Flow<List<Ingredient>>

    @Query("SELECT * FROM ingredient ORDER BY ingredientName ASC")
    fun getIngredientsOrderedByIngredientName(): Flow<List<Ingredient>>

    @Query("SELECT * FROM ingredient ORDER BY category ASC")
    fun getIngredientsOrderedByCategoryName(): Flow<List<Ingredient>>
}
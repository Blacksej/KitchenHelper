package dev.danieltm.kitchenhelper.databases

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import dev.danieltm.kitchenhelper.daos.IngredientDao
import dev.danieltm.kitchenhelper.models.Ingredient
import dev.danieltm.kitchenhelper.models.Recipe
import dev.danieltm.kitchenhelper.relations.RecipeIngredientCrossRef

@Database(
    entities = [Ingredient::class, Recipe::class, RecipeIngredientCrossRef::class],
    version = 16
)
abstract class RecipeDatabase: RoomDatabase() {
    abstract val dao: IngredientDao

    companion object{
        @Volatile
        private var INSTANCE: RecipeDatabase? = null

        fun getInstance(context: Context): RecipeDatabase{
            synchronized(this){
                return INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    RecipeDatabase::class.java,
                    "recipe_db"
                )
                    .fallbackToDestructiveMigration()
                    .build().also {
                    INSTANCE = it
                }
            }
        }
    }
}
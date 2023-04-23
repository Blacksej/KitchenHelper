package dev.danieltm.kitchenhelper.databases

import androidx.room.Database
import androidx.room.RoomDatabase
import dev.danieltm.kitchenhelper.daos.IngredientDao
import dev.danieltm.kitchenhelper.models.Ingredient

@Database(
    entities = [Ingredient::class],
    version = 1
)
abstract class IngredientDatabase: RoomDatabase() {
    abstract val dao: IngredientDao
}
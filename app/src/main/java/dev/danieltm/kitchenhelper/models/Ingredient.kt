package dev.danieltm.kitchenhelper.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Ingredient(
    val category: String,
    val ingredientName: String,
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
)

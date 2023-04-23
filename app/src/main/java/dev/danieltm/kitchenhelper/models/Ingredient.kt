package dev.danieltm.kitchenhelper.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Ingredient(
    @PrimaryKey(autoGenerate = false)
    val id: Int? = 0,
    val category: String,
    val ingredientName: String
)

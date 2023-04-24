package dev.danieltm.kitchenhelper.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Recipe(
    val recipeName: String,
    val recipeLink: String,

    @PrimaryKey(autoGenerate = true)
    val recipeId: Int = 0
)

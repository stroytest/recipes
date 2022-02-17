package com.abstudio.recipes.domain.entities

import androidx.room.Entity
import androidx.room.ForeignKey

@Entity(
    tableName = "recipe_ingredient_joins",
    primaryKeys = ["recipeId", "ingredientId", "amountId"],
    foreignKeys = [
        ForeignKey(
            entity = Recipe::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("recipeId"),
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = Ingredient::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("ingredientId"),
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = Amount::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("amountId"),
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class RecipeIngredientJoin(
    var recipeId: String,
    var indexNum: Int,
    var ingredientId: String,
    var amountId: String,
)

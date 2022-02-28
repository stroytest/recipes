package com.abstudio.recipes.data.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE

@Entity(
    tableName = "category_recipe_joins",
    primaryKeys = ["categoryId", "recipeId"],
    foreignKeys = [
        ForeignKey(
            entity = Category::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("categoryId"),
            onDelete = CASCADE
        ),
        ForeignKey(
            entity = Recipe::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("recipeId"),
            onDelete = CASCADE
        )
    ]
)
data class CategoryRecipeJoin(
    var categoryId: String,
    var recipeId: String
)
{
    var oldId: String = ""
}




package com.abstudio.recipes.domain.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.Index
import androidx.room.PrimaryKey
import java.util.*

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

/*
{
    @PrimaryKey
    var id: String = UUID.randomUUID().toString()

    // TODO: 10.01.2022 https://medium.com/android-news/android-architecture-components-room-relationships-bf473510c14a

}*/
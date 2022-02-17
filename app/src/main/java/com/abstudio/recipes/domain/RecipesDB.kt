package com.abstudio.recipes.domain

import androidx.room.Database
import androidx.room.RoomDatabase
import com.abstudio.recipes.domain.entities.*
import com.abstudio.recipes.domain.stores.*


@Database(
    entities =
    [Category::class,
        Recipe::class,
        Ingredient::class,
        Amount::class,
        CategoryRecipeJoin::class,
        RecipeIngredientJoin::class],
    version = 1
)
abstract class RecipesDB : RoomDatabase() {

    abstract fun categories(): CategoryStore

    abstract fun recipes(): RecipeStore

    abstract fun ingredients(): IngredientStore

    abstract fun amounts(): AmountStore

    abstract fun category_recipe_joins(): CategoryRecipeJoinStore

    abstract fun recipe_ingredient_joins(): RecipeIngredientJoinStore

}
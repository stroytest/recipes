package com.abstudio.recipes.domain

import androidx.room.Database
import androidx.room.RoomDatabase
import com.abstudio.recipes.domain.entities.Amount
import com.abstudio.recipes.domain.entities.Category
import com.abstudio.recipes.domain.entities.Ingredient
import com.abstudio.recipes.domain.entities.Recipe
import com.abstudio.recipes.domain.stores.AmountStore
import com.abstudio.recipes.domain.stores.CategoryStore
import com.abstudio.recipes.domain.stores.IngredientStore
import com.abstudio.recipes.domain.stores.RecipeStore


@Database(entities = [Category::class, Recipe::class, Ingredient::class, Amount::class], version = 1)
abstract class RecipesDB: RoomDatabase() {

    abstract fun categories(): CategoryStore

    abstract fun recipes(): RecipeStore

    abstract fun ingredients(): IngredientStore

    abstract fun amounts(): AmountStore
}
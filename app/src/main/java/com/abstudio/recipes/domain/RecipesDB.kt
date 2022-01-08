package com.abstudio.recipes.domain

import androidx.room.Database
import androidx.room.RoomDatabase
import com.abstudio.recipes.domain.entities.Category
import com.abstudio.recipes.domain.stores.CategoryStore


@Database(entities = [Category::class], version = 1)
abstract class RecipesDB: RoomDatabase() {

    abstract fun categories(): CategoryStore

}
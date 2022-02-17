package com.abstudio.recipes.domain.stores

import androidx.room.*
import com.abstudio.recipes.domain.entities.Category
import com.abstudio.recipes.domain.entities.Recipe

@Dao
interface RecipeStore {

    //For testing
    @Query("SELECT * FROM recipes ORDER BY name")
    fun loadAll(): List<Recipe>

    @Query("SELECT * FROM recipes WHERE id = :id")
    fun findById(id: String): Recipe

    @Insert
    fun insert(recipe: Recipe)

    @Update
    fun update(recipe: Recipe)

    @Delete
    fun delete(recipe: Recipe)

}
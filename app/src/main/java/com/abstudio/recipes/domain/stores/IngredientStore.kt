package com.abstudio.recipes.domain.stores

import androidx.room.*
import com.abstudio.recipes.domain.entities.Category
import com.abstudio.recipes.domain.entities.Ingredient

@Dao
interface IngredientStore {


    @Query("SELECT * FROM ingredients ORDER BY name")
    fun loadAll(): List<Ingredient>

    @Query("SELECT * FROM ingredients WHERE id = :id")
    fun findById(id: String): Ingredient

    @Insert
    fun insert(ingredient: Ingredient)

    @Update
    fun update(ingredient: Ingredient)

    @Delete
    fun delete(ingredient: Ingredient)
}
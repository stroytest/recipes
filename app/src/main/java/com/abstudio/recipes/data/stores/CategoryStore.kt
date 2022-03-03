package com.abstudio.recipes.data.stores

import androidx.lifecycle.LiveData
import androidx.room.*
import com.abstudio.recipes.data.entities.Category

@Dao
interface CategoryStore {

    @Query("SELECT * FROM categories ORDER BY name")
    fun getAllCategories(): LiveData<List<Category>>





    @Query("SELECT * FROM categories ORDER BY name")
    fun loadAll(): List<Category>

    @Query("SELECT * FROM categories WHERE id = :id")
    fun findById(id: String): Category

    @Insert
    fun insert(category: Category)

    @Update
    fun update(category: Category)

    @Delete
    fun delete(category: Category)

}
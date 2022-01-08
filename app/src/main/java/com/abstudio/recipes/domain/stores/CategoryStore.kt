package com.abstudio.recipes.domain.stores

import androidx.room.*
import com.abstudio.recipes.domain.entities.Category

@Dao
interface CategoryStore {

    @Query("SELECT * FROM categories")
    fun loadAll(): List<Category>

    @Insert
    fun insert(category: Category)

    @Update
    fun update(category: Category)

    @Delete
    fun delete(vararg categories: Category)
}
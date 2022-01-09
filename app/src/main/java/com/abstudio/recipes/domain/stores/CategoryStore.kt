package com.abstudio.recipes.domain.stores

import androidx.room.*
import com.abstudio.recipes.domain.entities.Category

@Dao
interface CategoryStore {

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

    // TODO: 09.01.2022 GetRecipesCountByCategory(Category category)
    //  SELECT * FROM BridgeCategoryRecipes WHERE BridgeCategoryRecipes.CategoryID = " + category.CategoryID
}
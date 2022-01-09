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


    // TODO: 09.01.2022 @Query("SELECT * FROM recipes WHERE id = :id")
    //  fun loadAllCategoriesByRecipeId(id: String): List<Category>
    //  SELECT* FROM Categories JOIN BridgeCategoryRecipes ON Categories.CategoryID = BridgeCategoryRecipes.CategoryID JOIN Recipes ON BridgeCategoryRecipes.RecipeID = Recipes.RecipeID WHERE Recipes.RecipeID = '" + recipe.RecipeID + "'ORDER BY Categories.CategoryName");


    // TODO: 09.01.2022 loadRecipesByCategoryId
    //  @Query("SELECT * FROM recipes WHERE id = :id ORDER BY name")
    //  fun loadRecipesByCategoryId(id: String): List<Recipe>


}
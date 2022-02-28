package com.abstudio.recipes.data.stores

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.abstudio.recipes.data.entities.Category
import com.abstudio.recipes.data.entities.CategoryRecipeJoin
import com.abstudio.recipes.data.entities.Recipe

@Dao
interface CategoryRecipeJoinStore {

    @Insert
    fun insert(join: CategoryRecipeJoin)

    @Query("SELECT * FROM category_recipe_joins")
    fun loadAll(): List<CategoryRecipeJoin>

    @Query("SELECT * FROM categories "+
            "INNER JOIN category_recipe_joins " +
            "ON categories.id=category_recipe_joins.categoryId "+
            "WHERE category_recipe_joins.recipeId=:recipeId "+
            "ORDER BY name" )
    fun getCategoriesForRecipe(recipeId: String): List<Category>

    @Query(
        "SELECT * FROM recipes " +
                "INNER JOIN category_recipe_joins " +
                "ON recipes.id=category_recipe_joins.recipeId " +
                "WHERE category_recipe_joins.categoryId=:categoryId " +
                "ORDER BY name"
    )
    fun getRecipesForCategory(categoryId: String): List<Recipe>

}
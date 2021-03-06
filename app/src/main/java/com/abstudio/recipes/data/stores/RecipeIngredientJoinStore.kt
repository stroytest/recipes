package com.abstudio.recipes.data.stores

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.abstudio.recipes.data.entities.Amount
import com.abstudio.recipes.data.entities.Ingredient
import com.abstudio.recipes.data.entities.RecipeIngredientJoin

@Dao
interface RecipeIngredientJoinStore {

    @Insert
    fun insert(join: RecipeIngredientJoin)

    @Query("SELECT * FROM recipe_ingredient_joins")
    fun loadAll(): List<RecipeIngredientJoin>

    @Query(
        "SELECT * FROM recipe_ingredient_joins " +
                "WHERE recipe_ingredient_joins.recipeId=:recipeId " +
                "ORDER BY indexNum"
    )
    fun getIngredientJoinsForRecipe(recipeId: String): List<RecipeIngredientJoin>

    @Query(
        "SELECT * FROM ingredients " +
                "INNER JOIN recipe_ingredient_joins " +
                "ON ingredients.id=recipe_ingredient_joins.ingredientId " +
                "WHERE recipe_ingredient_joins.recipeId=:recipeId " +
                "ORDER BY recipe_ingredient_joins.indexNum"
    )
    fun getIngredientsForRecipe(recipeId: String): List<Ingredient>

    @Query(
        "SELECT * FROM amounts " +
                "INNER JOIN recipe_ingredient_joins " +
                "ON amounts.id=recipe_ingredient_joins.amountId " +
                "WHERE recipe_ingredient_joins.recipeId=:recipeId " +
                "ORDER BY recipe_ingredient_joins.indexNum"
    )
    fun getAmountsForRecipe(recipeId: String): List<Amount>

}
package com.abstudio.recipes.data

import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.abstudio.recipes.data.entities.Amount
import com.abstudio.recipes.data.entities.Ingredient
import com.abstudio.recipes.data.entities.Recipe
import com.abstudio.recipes.data.entities.RecipeIngredientJoin
import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import com.natpryce.hamkrest.hasSize
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class RecipeIngredientJoinStoreTest {

    private val db = Room.inMemoryDatabaseBuilder(
        InstrumentationRegistry.getInstrumentation().targetContext,
        RecipesDB::class.java
    ).build()

    private val recipes = db.recipes()
    private val ingredients = db.ingredients()
    private val amounts = db.amounts()
    private val joins = db.recipe_ingredient_joins()

    val recA = Recipe("RecipeA")
    val recB = Recipe("RecipeB")

    val ingA = Ingredient("IngredientA")
    val ingB = Ingredient("IngredientB")

    val amA = Amount("AmountA")
    val amB = Amount("AmountB")

    @Before
    fun setUp() {
        recipes.insert(recA)
        recipes.insert(recB)

        ingredients.insert(ingA)
        ingredients.insert(ingB)

        amounts.insert(amA)
        amounts.insert(amB)

        val joinA_B_A = RecipeIngredientJoin(recA.id, 0, ingB.id, amA.id)
        joins.insert(joinA_B_A)
        val joinA_A_B = RecipeIngredientJoin(recA.id, 1, ingA.id, amB.id)
        joins.insert(joinA_A_B)

        val joinA_A_A = RecipeIngredientJoin(recB.id, 0, ingA.id, amA.id)
        joins.insert(joinA_A_A)
        val joinA_B_B = RecipeIngredientJoin(recB.id, 1, ingB.id, amB.id)
        joins.insert(joinA_B_B)
    }

    @Test
    fun loadAll() {

        recipes.loadAll().let {
            assertThat(it, hasSize(equalTo(2)))
        }

        ingredients.loadAll().let {
            assertThat(it, hasSize(equalTo(2)))
        }

        amounts.loadAll().let {
            assertThat(it, hasSize(equalTo(2)))
        }

        joins.loadAll().let {
            assertThat(it, hasSize(equalTo(4)))
        }

    }

    @Test
    fun getIngredientJoinsByRecipeId() {

        val ingredientJoinsA: List<RecipeIngredientJoin> =
            joins.getIngredientJoinsForRecipe(recA.id)
        assertThat(ingredientJoinsA, hasSize(equalTo(2)))

        assertThat(ingredientJoinsA[0].ingredientId, equalTo(ingB.id))
        assertThat(ingredientJoinsA[1].ingredientId, equalTo(ingA.id))


        val ingredientJoinsB: List<RecipeIngredientJoin> =
            joins.getIngredientJoinsForRecipe(recB.id)
        assertThat(ingredientJoinsB, hasSize(equalTo(2)))

        assertThat(ingredientJoinsB[0].ingredientId, equalTo(ingA.id))
        assertThat(ingredientJoinsB[1].ingredientId, equalTo(ingB.id))
    }

    @Test
    fun getIngredientsForRecipe() {
        val ingredientsA: List<Ingredient> = joins.getIngredientsForRecipe(recA.id)

        assertThat(ingredientsA, hasSize(equalTo(2)))

        assertThat(ingredientsA[0], equalTo(ingB))
        assertThat(ingredientsA[1], equalTo(ingA))

        val ingredientsB: List<Ingredient> = joins.getIngredientsForRecipe(recB.id)

        assertThat(ingredientsB, hasSize(equalTo(2)))

        assertThat(ingredientsB[0], equalTo(ingA))
        assertThat(ingredientsB[1], equalTo(ingB))
    }

    @Test
    fun getAmountsForRecipe() {
        val amountsA: List<Amount> = joins.getAmountsForRecipe(recA.id)

        assertThat(amountsA, hasSize(equalTo(2)))

        assertThat(amountsA[0], equalTo(amA))
        assertThat(amountsA[1], equalTo(amB))

        val amountsB: List<Amount> = joins.getAmountsForRecipe(recB.id)

        assertThat(amountsB, hasSize(equalTo(2)))

        assertThat(amountsB[0], equalTo(amA))
        assertThat(amountsB[1], equalTo(amB))
    }

}
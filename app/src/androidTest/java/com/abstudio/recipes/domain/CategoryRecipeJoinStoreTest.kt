package com.abstudio.recipes.domain

import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.abstudio.recipes.domain.entities.Category
import com.abstudio.recipes.domain.entities.CategoryRecipeJoin
import com.abstudio.recipes.domain.entities.Recipe
import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import com.natpryce.hamkrest.hasSize
import com.natpryce.hamkrest.isEmpty
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CategoryRecipeJoinStoreTest {

    private val db = Room.inMemoryDatabaseBuilder(
        InstrumentationRegistry.getInstrumentation().targetContext,
        RecipesDB::class.java
    ).build()

    private val categories = db.categories()
    private val recipes = db.recipes()
    private val joins = db.category_recipe_joins()


    val catA = Category("CategoryA")
    val catB = Category("CategoryB")
    val recA = Recipe("RecipeA")
    val recB = Recipe("RecipeB")

    @Before
    fun setUp(){
        categories.insert(catA)
        categories.insert(catB)

        recipes.insert(recA)
        recipes.insert(recB)

        val joinA_A = CategoryRecipeJoin(catA.id, recA.id)
        joins.insert(joinA_A)
        val joinA_B = CategoryRecipeJoin(catA.id, recB.id)
        joins.insert(joinA_B)

        val joinB_A = CategoryRecipeJoin(catB.id, recA.id)
        joins.insert(joinB_A)
        val joinB_B = CategoryRecipeJoin(catB.id, recB.id)
        joins.insert(joinB_B)
    }

    @Test
    fun loadAll() {

        categories.loadAll().let {
            assertThat(it, hasSize(equalTo(2)))
        }

        recipes.loadAll().let {
            assertThat(it, hasSize(equalTo(2)))
        }

        joins.loadAll().let {
            assertThat(it, hasSize(equalTo(4)))
        }
    }

    @Test
    fun getRecipesByCategory() {

        val recipes = joins.getRecipesForCategory(catA.id)

        assertThat(recipes, hasSize(equalTo(2)))

        assertThat(recipes[0], equalTo(recA))

        assertThat(recipes[1], equalTo(recB))
    }

    @Test
    fun getCategoriesByRecipe() {

        val categories = joins.getCategoriesForRecipe(recA.id)

        assertThat(categories, hasSize(equalTo(2)))

        assertThat(categories[0], equalTo(catA))

        assertThat(categories[1], equalTo(catB))
    }
}